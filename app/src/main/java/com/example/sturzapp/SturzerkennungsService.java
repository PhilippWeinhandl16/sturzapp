package com.example.sturzapp;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

/**
 * Der {@code SturzerkennungsService} ist ein Hintergrunddienst,
 * der den Beschleunigungssensor des Geräts überwacht
 * um Stürze zu erkennen und bei Sturz eine Notfallbenachrichtigungen an den NFK zu senden
 * Außerdem erfasst er den aktuellen Standort des Geräts für die Notfallbenachrichtigung
 */
public class SturzerkennungsService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static final int SHAKE_THRESHOLD = 40;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "SturzerkennungsServiceChannel";

    private LocationManager locationManager;
    private LocationListener locationListener;

    private static final int PERMISSION_REQUEST_CODE = 123;

    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListenerImpl();

        createNotificationChannel();
        startForeground(NOTIFICATION_ID, buildNotification());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                lastUpdate = curTime;

                float deltaX = x - last_x;
                float deltaY = y - last_y;
                float deltaZ = z - last_z;

                float acceleration = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

                if (acceleration > SHAKE_THRESHOLD) {
                    Toast.makeText(this, "Sturz erkannt!", Toast.LENGTH_LONG).show();

                    /**
                     * Bei zu hoher Beschleunigung wird die {@code sendEmergencyEmail()} Methode
                     * aufgerufen
                     */
                    sendEmergencyEmail();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Erstellt einen Notification-Kanal für den Service.
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "SturzerkennungsService",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    /**
     * Erstellt und gibt die Service-Notification zurück
     * @return Die erstellte Notification
     */
    private Notification buildNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("SturzerkennungsService läuft")
                .setContentText("Überwache Beschleunigungssensor")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder.build();
    }

    /**
     * Sendet eine Notfall-E-Mail an den verknüpften NFK, wenn ein Sturz erkannt wurde
     * Die E-Mail enthält Informationen zum Sturz und den aktuellen GPS-Standort des Geräts
     */
    public void sendEmergencyEmail() {
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        new Thread(() -> {
            AccountEntity account = db.accountDao().getAccountById(1);

            String subject = "Notfallmeldung";
            String body = "Ihr Risikopatient hat einen Sturz gehabt! Womöglich benötigt er Hilfe";

            String location = getCurrentLocation();

            body += "\n\nStandort: " + location;

            String mapsLink = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(location);
            body += "\n\nGoogle Maps Standort: " + mapsLink;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{account.getEmailNFK()});
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PackageManager packageManager = getPackageManager();
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Keine E-Mail-App gefunden", Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    /**
     * Die {@code getCurrentLocation()} Methode ermittelt den aktuellen Standort des Geräts
     *
     * @return Der aktuelle Standort des Geräts als Zeichenkette ("Breitengrad, Längengrad")
     */
    public String getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return "Standortberechtigung nicht erteilt";
        }

        try {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                double latitude = lastKnownLocation.getLatitude();
                double longitude = lastKnownLocation.getLongitude();
                return latitude + ", " + longitude;
            } else {
                return "Standort nicht verfügbar";
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            return "Standortberechtigung nicht erteilt";
        }
    }

    /**
     * Implementierung des LocationListener-Interface, um auf Standortänderungen zu reagieren.
     */
    class LocationListenerImpl implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            locationManager.removeUpdates(this);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}
