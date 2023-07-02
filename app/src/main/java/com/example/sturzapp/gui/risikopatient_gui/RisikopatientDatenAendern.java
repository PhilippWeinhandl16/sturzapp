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

        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        new Thread(() -> {
            long id = intent.getLongExtra("id", -1);
            entity = db.accountDao().getAccountById((int) id);

            if (entity != null) {
                runOnUiThread(() -> displayAccountInfo());
            }
        }).start();

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

        buttonBackToStartingPage.setOnClickListener(view -> navigateToRisikopatientStartseite());
    }

    private void initializeViews() {
        editTextEmailRPChange = findViewById(R.id.editTextemailRP_change);
        editTextPasswordRPChange = findViewById(R.id.editTextpasswordRP_change);
        editTextFirstNameRPChange = findViewById(R.id.editTextfirstNameRP_change);
        editTextLastNameRPChange = findViewById(R.id.editTextlastNameRP_change);
    }

    private void displayAccountInfo() {
        editTextEmailRPChange.setText(entity.getEmailRP());
        editTextPasswordRPChange.setText(entity.getPasswordRP());
        editTextFirstNameRPChange.setText(entity.getFirstNameRP());
        editTextLastNameRPChange.setText(entity.getLastNameRP());
    }

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

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void navigateToRisikopatientStartseite() {
        onBackPressed();
    }
}
