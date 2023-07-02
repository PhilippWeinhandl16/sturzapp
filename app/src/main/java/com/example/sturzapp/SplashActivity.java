package com.example.sturzapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Die Klasse {@code SplashActivity} ist eine Aktivität, die beim Start der Anwendung angezeigt wird
 * Sie dient als Splash-Bildschirm und zeigt das App-Logo an
 * Nach einer bestimmten Zeit wechselt sie zur Hauptaktivität der Anwendung
 */
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
