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

/**
 * Die Klasse {@code RisikopatientDatenAendern} erweitert {@link AppCompatActivity}
 * und in dieser Activity können die Daten des Risikopatienten geändert werden.
 */
public class RisikopatientDatenAendern extends AppCompatActivity {


    private AccountEntity entity = null;

    private EditText editTextEmailRPChange;
    private EditText editTextPasswordRPChange;
    private EditText editTextFirstNameRPChange;
    private EditText editTextLastNameRPChange;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_daten_aendern);

        initializeViews();

        Button buttonSaveChanges = findViewById(R.id.buttonChangeAccount);
        Button buttonBackToStartingPage = findViewById(R.id.buttonZurückZurMainActivity);

        Intent intent = getIntent();

        /**
         * Hier wird eine {@link SturzappDatabase} Instanz erstellt
         */
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        new Thread(() -> {
            long id = intent.getLongExtra("id", -1);
            entity = db.accountDao().getAccountById((int) id);

            if (entity != null) {
                runOnUiThread(() -> displayAccountInfo());
            }
        }).start();

        /**
         * Bei Klick des Buttons "buttonsaveChanges" werden die Änderungen des Accounts durchgeführt
         */

        buttonSaveChanges.setOnClickListener(view -> {
            if (entity != null) {
                String emailRP = editTextEmailRPChange.getText().toString().trim();
                String passwordRP = editTextPasswordRPChange.getText().toString().trim();
                String firstNameRP = editTextFirstNameRPChange.getText().toString().trim();
                String lastNameRP = editTextLastNameRPChange.getText().toString().trim();

                if (emailRP.isEmpty() || !isValidEmail(emailRP)) {
                    Toast.makeText(this, "Ungültige E-Mail-Adresse", Toast.LENGTH_SHORT).show();
                    return;
                }

                updateAccount(emailRP, passwordRP, firstNameRP, lastNameRP);
            }
        });

        /**
         * Bei Klick des Buttons "buttonBackToStartingPage" wird man zurück zur RisikopatientStartseite
         * Klasse navigiert in dem die "navigateToRisikopatientStartseite() Methode
         * aufgerufen wird
         */

        buttonBackToStartingPage.setOnClickListener(view -> navigateToRisikopatientStartseite());
    }

    /**
     * Die Methode {@code initializeViews} initialisiert die EditText-Felder der Aktivität
     */
    private void initializeViews() {
        editTextEmailRPChange = findViewById(R.id.editTextemailRP_change);
        editTextPasswordRPChange = findViewById(R.id.editTextpasswordRP_change);
        editTextFirstNameRPChange = findViewById(R.id.editTextfirstNameRP_change);
        editTextLastNameRPChange = findViewById(R.id.editTextlastNameRP_change);
    }

    /**
     * Die Methode {@code displayAccountInfo} füllt die EditText-Felder mit den Daten des AccountEntity-Objekts
     */
    private void displayAccountInfo() {
        editTextEmailRPChange.setText(entity.getEmailRP());
        editTextPasswordRPChange.setText(entity.getPasswordRP());
        editTextFirstNameRPChange.setText(entity.getFirstNameRP());
        editTextLastNameRPChange.setText(entity.getLastNameRP());
    }

    /**
     * Die Methode {@code updateAccount} aktualisiert die Accountdaten in der {@link SturzappDatabase}
     * @param emailRP       ist die aktualisierte Email des Risikopatienten
     * @param passwordRP    ist das aktualisierte Passwort des Risikopatienten
     * @param firstNameRP   ist der aktualisierte Vorname des Risikopatienten
     * @param lastNameRP    ist der aktualisierte Nachname des Risikopatienten
     */
    private void updateAccount(String emailRP, String passwordRP, String firstNameRP, String lastNameRP) {
        new Thread(() -> {
            entity.setEmailRP(emailRP);
            entity.setPasswordRP(passwordRP);
            entity.setFirstNameRP(firstNameRP);
            entity.setLastNameRP(lastNameRP);

            SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
            db.accountDao().update(entity);

            runOnUiThread(this::onBackPressed);
        }).start();
    }

    /**
     * Die Methode {@code isValidEmail} überprüft, ob eine gültige Email-Adresse eingegeben wurde
     * @param email  ist die zu überprüfende Email-Adresse
     * @return {@code true} wenn die Email-Adresse gültig ist, {@code false} wenn sie nicht gültig ist
     */
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Mit der {@code navigateToRisikopatientStartseite} Methode wird zurück zur
     * RisikopatientStartseite navigiert
     */

    private void navigateToRisikopatientStartseite() {
        onBackPressed();
    }
}
