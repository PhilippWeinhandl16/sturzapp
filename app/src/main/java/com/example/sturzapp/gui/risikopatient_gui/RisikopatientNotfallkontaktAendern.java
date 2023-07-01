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

public class RisikopatientNotfallkontaktAendern extends AppCompatActivity {

    private AccountEntity entity = null;

    private EditText editTextemailNFK;
    private EditText editTextnameNFK;
    private Button button_saveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riskiopatient_notfallkontakt_aendern);

        initializeViews();

        button_saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveChanges();

            }
        });

        Intent intent = getIntent();

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


    private void initializeViews() {

        editTextemailNFK = findViewById(R.id.editTextemailNFK_change);
        editTextnameNFK = findViewById(R.id.editTextnameNFK_change);
        button_saveChanges = findViewById(R.id.Button_NFK_change);


    }

    private void displayAccountInfo () {

        editTextemailNFK.setText(entity.getEmailNFK());
        editTextnameNFK.setText(entity.getNameNFK());

    }

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

    private boolean isValidEmail(String email) {
        // Verwendung des vordefinierten Musters für E-Mail-Validierung
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
