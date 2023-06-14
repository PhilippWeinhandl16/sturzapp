package com.example.sturzapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sturzapp.MainActivity;
import com.example.sturzapp.R;

public class Splash_Activity extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 3000; // Zeit in Millisekunden, bis zum Wechsel zur Hauptaktivität

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setze das Layout für diese Aktivität
        setContentView(R.layout.activity_splash);

        // Erstelle einen Handler, um das automatische Weitergehen zu verzögern
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Erstelle eine Intent für die Hauptaktivität
                Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                startActivity(intent);

                // Beende die SplashActivity
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
