package com.example.sturzapp.gui.risikopatient_gui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

public class RisikopatientDatenAendern extends AppCompatActivity {

    private AccountEntity entity = null;

    EditText editTextemailRP_change;
    EditText editTextpasswordRP_change;
    EditText editTextfirstNameRP_change;
    EditText editTextlastNameRP_change;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_daten_aendern);

        initializeViews();

        Button saveChanges = findViewById(R.id.buttonChangeAccount);

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

        saveChanges.setOnClickListener(it -> {
            if (entity != null) {
                // Eingaben aus den Textfeldern auslesen
                String emailRP = editTextemailRP_change.getText().toString();
                String passwordRP = editTextpasswordRP_change.getText().toString();
                String firstNameRP = editTextfirstNameRP_change.getText().toString();
                String lastNameRP = editTextlastNameRP_change.getText().toString();

                if (!isValidEmail(emailRP)) {
                    Toast.makeText(RisikopatientDatenAendern.this, "Ungültige E-Mail-Adresse", Toast.LENGTH_SHORT).show();
                    return;
                }

                updateAccount(emailRP, passwordRP, firstNameRP, lastNameRP);

            }
        });
    }

    private void initializeViews() {

        editTextemailRP_change = findViewById(R.id.editTextemailRP_change);
        editTextpasswordRP_change = findViewById(R.id.editTextpasswordRP_change);
        editTextfirstNameRP_change = findViewById(R.id.editTextfirstNameRP_change);
        editTextlastNameRP_change = findViewById(R.id.editTextlastNameRP_change);

    }

    private void displayAccountInfo() {

        editTextemailRP_change.setText(entity.getEmailRP());
        editTextpasswordRP_change.setText(entity.getPasswordRP());
        editTextfirstNameRP_change.setText(entity.getFirstNameRP());
        editTextlastNameRP_change.setText(entity.getLastNameRP());

    }

    private void updateAccount(String emailRP, String passwordRP, String firstNameRP, String lastNameRP) {
        // Aktualisieren der AccountEntity
        new Thread(() -> {
            entity.setEmailRP(emailRP);
            entity.setPasswordRP(passwordRP);
            entity.setFirstNameRP(firstNameRP);
            entity.setLastNameRP(lastNameRP);

            // Account in der Datenbank aktualisieren
            SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
            db.accountDao().update(entity);

            runOnUiThread(new Thread(() -> {
                onBackPressed();
            }));
        }).start();



    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
