package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sturzapp.MainActivity;
//import com.example.sturzapp.PasswordHasher;
import com.example.sturzapp.PasswordHasher;
import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;
import com.example.sturzapp.gui.risikopatient_gui.RisikopatientStartseite;


/**
 * Die Klasse {@code RisikopatientErstellen} erweitert {@link AppCompatActivity} und in dieser Klasse
 * kann ein neuer Risikopatient-Account erstellt werden
 */
public class RisikopatientErstellen extends AppCompatActivity {

    private EditText editTextemailRP_input;
    private EditText editTextpasswordRP_input;
    private EditText editTextfirstNameRP_input;
    private EditText editTextlastNameRP_input;
    private EditText editTextemailNFK_input;
    private EditText editTextnameNFK_input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_erstellen);

        /**
         * Die {@code initializeViews} Methode initialisiert die EditText-Felder
         */
        initializeViews();

        Button button = findViewById(R.id.buttonZurückZurMainActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Mit der {@code navigateToMainActivity()} Methode wird bei Klick des Buttons:
                 * {@code button} zurück zur {@link MainActivity} Klasse navigiert
                 */
                navigateToMainActivity();

            }
        });

        Button button2 = findViewById(R.id.buttonCreateAccount);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Mit der {@code createAccount()} Methode wird bei Klick des Buttons:
                 * {@code button2} ein neuer Risikopatient-Account erstellt
                 */
                createAccount();

            }
        });
    }

    /**
     * Mit der {@code initializeViews()} Methode werden die EditText-Felder initialisiert
     */
    private void initializeViews() {

        editTextemailRP_input = findViewById(R.id.editTextemailRP_input);
        editTextpasswordRP_input = findViewById(R.id.editTextpasswordRP_input);
        editTextfirstNameRP_input = findViewById(R.id.editTextfirstNameRP_input);
        editTextlastNameRP_input = findViewById(R.id.editTextlastNameRP_input);
        editTextemailNFK_input = findViewById(R.id.editTextemailNFK_input);
        editTextnameNFK_input = findViewById(R.id.editTextnameNFK_input);

    }

    /**
     * Mit der {@code createAccount()} Methode wird ein neuer Risikopatient-Account erstellt
     */
    private void createAccount() {

        String emailRP = editTextemailRP_input.getText().toString();
        String passwordRP = editTextpasswordRP_input.getText().toString();
        String firstNameRP = editTextfirstNameRP_input.getText().toString();
        String lastNameRP = editTextlastNameRP_input.getText().toString();
        String emailNFK = editTextemailNFK_input.getText().toString();
        String nameNFK = editTextnameNFK_input.getText().toString();

        if (!isValidEmail(emailRP)) {
            Toast.makeText(RisikopatientErstellen.this, "Ungültige E-Mail-Adresse", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(emailNFK)) {
            Toast.makeText(RisikopatientErstellen.this, "Ungültige E-Mail-Adresse für Notfallkontakt", Toast.LENGTH_SHORT).show();
            return;
        }

        /**
         * Hier wird das {@code hashedPasswordRp} des Risikopatienten-Accounts gehasht
         */
       String hashedPasswordRP = PasswordHasher.hashPassword(passwordRP);


        /**
         * Hier wird eine {@link SturzappDatabase} Instanz erstellt
         */
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        /**
         * Der Account wird hier mit Daten befüllt
         */
        new Thread(() -> {
            AccountEntity account = new AccountEntity(
                    emailRP,
                    hashedPasswordRP,
                    firstNameRP,
                    lastNameRP,
                    emailNFK,
                    nameNFK
            );
            long id = db.accountDao().insert(account);
            Intent intent2 = new Intent(RisikopatientErstellen.this, RisikopatientStartseite.class);
            intent2.putExtra("id", id);
            startActivity(intent2);
        }).start();

    }

    /**
     * Mit der {@code navigateToMainActivity()} Methode wird
     * zurück zur {@link MainActivity} Klasse navigiert
     */
    private void navigateToMainActivity() {

        Intent intent = new Intent(RisikopatientErstellen.this, MainActivity.class);
        startActivity(intent);

    }


    /**
     * Die Methode überprüft, ob eine gültige Email-Adresse eingegeben wurde
     * @param email  ist die zu überprüfende Email-Adresse
     * @return {@code true} wenn die Email-Adresse gültig ist, {@code false} wenn sie nicht gültig ist
     */
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }


}
