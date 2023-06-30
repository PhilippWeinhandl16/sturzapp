package com.example.sturzapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SturzerkennungsService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static final int SHAKE_THRESHOLD = 50;
    //vorher 600

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Werte für jede Achse erhalten
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Aktuelle Zeit speichern
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                // Hier kannst du weitere Logik implementieren, um zu überprüfen, ob ein Sturz stattgefunden hat

                // Berechne die Änderung der Beschleunigungswerte
                float deltaX = x - last_x;
                float deltaY = y - last_y;
                float deltaZ = z - last_z;

                // Berechne die Gesamtbeschleunigung
                float acceleration = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

                // Überprüfe, ob die Gesamtbeschleunigung den Schwellenwert überschreitet
                if (acceleration > SHAKE_THRESHOLD) {
                    // Sturz erkannt, hier kannst du entsprechende Aktionen ausführen
                    // Beispiel: Eine Meldung anzeigen
                    Toast.makeText(this, "Sturz erkannt!", Toast.LENGTH_LONG).show();
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
}

