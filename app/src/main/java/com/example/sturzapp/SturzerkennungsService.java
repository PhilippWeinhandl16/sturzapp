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
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

public class SturzerkennungsService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static final int SHAKE_THRESHOLD = 50;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "SturzerkennungsServiceChannel";

    private LocationManager locationManager;
    private LocationListener locationListener;

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
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float deltaX = x - last_x;
                float deltaY = y - last_y;
                float deltaZ = z - last_z;

                float acceleration = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

                if (acceleration > SHAKE_THRESHOLD) {
                    Toast.makeText(this, "Sturz erkannt!", Toast.LENGTH_LONG).show();
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
        // Hier kannst du die Genauigkeit des Sensors behandeln, falls erforderlich
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

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

    private Notification buildNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("SturzerkennungsService läuft")
                .setContentText("Überwache Beschleunigungssensor")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder.build();
    }

    public void sendEmergencyEmail() {
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        new Thread(() -> {
            // Account-Informationen aus der Datenbank abrufen
            AccountEntity account = db.accountDao().getAccountById(1);

            // Ersetze 1 durch die entsprechende ID des eingeloggten Benutzers

            String subject = "Notfallmeldung";
            String body = "Ihr Risikopatient hat einen Sturz gehabt! Womöglich benötigt er Hilfe";

            // Standort abrufen
            String location = getCurrentLocation();

            // Standort zur E-Mail hinzufügen
            body += "\n\nStandort: " + location;

            // Google Maps Link erstellen
            String mapsLink = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(location);
            body += "\n\nGoogle Maps Standort: " + mapsLink;

            // Intent erstellen, um die E-Mail zu senden
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{account.getEmailNFK()});
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Setze die FLAG_ACTIVITY_NEW_TASK Flag

            // Überprüfen, ob eine Activity zum Handhaben des Intents verfügbar ist
            PackageManager packageManager = getPackageManager();
            if (intent.resolveActivity(packageManager) != null) {
                // E-Mail-Activity starten
                startActivity(intent);
            } else {
                // Keine E-Mail-App verfügbar
                Toast.makeText(getApplicationContext(), "Keine E-Mail-App gefunden", Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    private String getCurrentLocation() {
        // Überprüfen, ob die Berechtigung für den Standortzugriff gewährt wurde
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Berechtigung nicht vorhanden, Abbruch oder Berechtigung anfordern
            return "Standortberechtigung nicht erteilt";
        }

        // Den Standortanbieter auswählen und Standort abrufen
        LocationListener locationListener = new LocationListenerImpl();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownLocation != null) {
            double latitude = lastKnownLocation.getLatitude();
            double longitude = lastKnownLocation.getLongitude();
            return latitude + ", " + longitude;
        } else {
            return "Standort nicht verfügbar";
        }
    }

    private class LocationListenerImpl implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            // Standortaktualisierungen empfangen
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            // Hier kannst du den aktuellen Standort verwenden oder speichern
            // Zum Beispiel: latitude + ", " + longitude;
            // Den LocationListener wieder entfernen, wenn der Standort empfangen wurde
            locationManager.removeUpdates(this);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            // Standortanbieter aktiviert
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            // Standortanbieter deaktiviert
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Standortanbieterstatus geändert
        }
    }
}
