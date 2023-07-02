package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Patterns;
import android.widget.Toast;

import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;


/**
 * Die Klasse {@code RisikopatientNotfallkontaktAendern} erweitert {@link AppCompatActivity}
 * und sie ermöglicht die Änderung des Notfallkontakts für einen Risikopatienten
 */
public class RisikopatientNotfallkontaktAendern extends AppCompatActivity {

    private AccountEntity entity = null;

    private EditText editTextemailNFK;
    private EditText editTextnameNFK;
    private Button button_saveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riskiopatient_notfallkontakt_aendern);


        /**
         * Die {@code initializeViews()} Methode initialisiert die EditText und Button Views
         */
        initializeViews();

        button_saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Mit der {@code saveChanges()} Methode werden die Änderungen
                 * bei Klick auf den {@code button_saveChanges} gespeichert.
                 */
                saveChanges();

            }
        });

        Intent intent = getIntent();

        /**
         * Hier wird eine {@link SturzappDatabase} Instanz erstellt
         */
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        // Auslesen
        new Thread(() -> {
            long id = intent.getLongExtra("id", -1);

            // Aus Datenbank auslesen
            entity = db.accountDao().getAccountById((int) id);

            if (entity != null) {
                displayAccountInfo();
            }
        }).start();


    }

    /**
     * Die {@code initializeViews()} Methode initialisiert die EditText und Button Objekte
     */
    private void initializeViews() {

        editTextemailNFK = findViewById(R.id.editTextemailNFK_change);
        editTextnameNFK = findViewById(R.id.editTextnameNFK_change);
        button_saveChanges = findViewById(R.id.Button_NFK_change);


    }

    /**
     * Die {@code displayAccountInfo()} Methode zeigt die Informationen des
     * Risikopatienten-Accounts in den EditText Feldern an
     */
    private void displayAccountInfo () {

        editTextemailNFK.setText(entity.getEmailNFK());
        editTextnameNFK.setText(entity.getNameNFK());

    }

    /**
     * Die {@code saveChanges()} Methode speichert die vorgenommenen Änderungen der Daten
     */
    private void saveChanges() {

        if (entity != null) {
            // Eingabe überprüfen
            String emailNFK = editTextemailNFK.getText().toString();
            String nameNFK = editTextnameNFK.getText().toString();

            if (!isValidEmail(emailNFK)) {
                Toast.makeText(this, "Ungültige E-Mail-Adresse", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check ob eh gesetzt ist --> sonst Fehler
            new Thread(() -> {
                entity.setEmailNFK(emailNFK);
                entity.setNameNFK(nameNFK);

                // Update
                SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
                db.accountDao().update(entity);

                runOnUiThread(() -> {
                    onBackPressed();
                });
            }).start();
        }

    }

    /**
     * Die Methode überprüft, ob eine gültige Email-Adresse eingegeben wurde
     * @param email  ist die zu überprüfende Email-Adresse
     * @return {@code true} wenn die Email-Adresse gültig ist, {@code false} wenn sie nicht gültig ist
     */
    private boolean isValidEmail(String email) {
        // Verwendung des vordefinierten Musters für E-Mail-Validierung
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
