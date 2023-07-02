package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

/**
 * Die Aktivität "RisikopatientNotfallbutton" ermöglicht es dem Risikopatienten, einen Notfall
 * mit Klick auf einen Notfallbutton zu melden und es wird eine Email an den zuständigen Notfallkontakt
 * ausgeschickt
 * Die Aktivität enthält auch einen Button, um zur Startseite des Risikopatienten zurückzukehren.
 */
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

    /**
     * Initialisiert die Button der Aktivität
     * Setzt die Hintergrundfarbe des Notfall-Buttons auf Rot
     */
    public void initializeViews() {
        button_notfallbutton = findViewById(R.id.Button_Notfallbutton);
        ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.red));
        button_notfallbutton.setBackgroundTintList(colorStateList);

        button_backToStartseite = findViewById(R.id.Button_backToStartseite);
    }

    /**
     * Legt die Button-Listener für den {@code button_notfallbutton} und den
     * Button {@code button_backToStartseite} fest
     */
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

    /**
     * Sendet eine Notfall-E-Mail an den zuständigen Notfallkontakt
     * Holt die Account-Informationen aus der Datenbank und verwendet die E-Mail-Adresse des Kontakts
     * Erstellt einen Intent zum Senden der E-Mail, startet die E-Mail-Activity und
     * zeigt dem Benutzer eine Toast-Nachricht an, wenn keine E-Mail-App verfügbar ist
     */
    public void sendEmergencyEmail() {
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
        new Thread(() -> {
            AccountEntity account = db.accountDao().getAccountById(1);

            String subject = "Notfallmeldung";
            String body = "Ihr Risikopatient meldet einen Notfall! Womöglich benötigt er Hilfe";

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
     * Bei Klick auf den Button {@code button_backToStartseite} wird zurück zur
     * {@link RisikopatientStartseite} navigiert
     */
    public void navigateToRisikopatientStartseite() {
        onBackPressed();
    }
}
