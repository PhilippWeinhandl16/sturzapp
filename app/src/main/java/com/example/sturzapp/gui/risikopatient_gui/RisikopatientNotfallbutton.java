package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

public class RisikopatientNotfallbutton extends AppCompatActivity {


    private Button button_notfallbutton;
    private Button button_backToStartseite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_notfallbutton);

        initializeViews();
        setupButtonListeners();

    }

    public void initializeViews() {

        button_notfallbutton = findViewById(R.id.Button_Notfallbutton);
        button_backToStartseite = findViewById(R.id.Button_backToStartseite);
    }

    public void setupButtonListeners() {
        button_notfallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmergencyEmail();
            }
        });

        button_backToStartseite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientStartseite();
            }
        });

    }

    public void sendEmergencyEmail() {

        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
        new Thread(() -> {
            // Account-Informationen aus der Datenbank abrufen
            AccountEntity account = db.accountDao().getAccountById(1); // Ersetze 1 durch die entsprechende ID des eingeloggten Benutzers

            String subject = "Notfallmeldung";
            String body = "Ihr Risikopatient meldet einen Notfall! Womöglich benötigt er Hilfe";

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
        public void navigateToRisikopatientStartseite () {
            Intent intent = new Intent(RisikopatientNotfallbutton.this, RisikopatientStartseite.class);
            startActivity(intent);
    }
}