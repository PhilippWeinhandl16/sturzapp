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
import com.example.sturzapp.PasswordHasher;
import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;
import com.example.sturzapp.gui.risikopatient_gui.RisikopatientStartseite;

public class RisikopatientErstellen extends AppCompatActivity {

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_erstellen);

        EditText editTextemailRP_input = findViewById(R.id.editTextemailRP_input);
        EditText editTextpasswordRP_input = findViewById(R.id.editTextpasswordRP_input);
        EditText editTextfirstNameRP_input = findViewById(R.id.editTextfirstNameRP_input);
        EditText editTextlastNameRP_input = findViewById(R.id.editTextlastNameRP_input);
        EditText editTextemailNFK_input = findViewById(R.id.editTextemailNFK_input);
        EditText editTextnameNFK_input = findViewById(R.id.editTextnameNFK_input);

        Button button = findViewById(R.id.buttonZurückZurMainActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RisikopatientErstellen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.buttonCreateAccount);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                String hashedPasswordRP = PasswordHasher.hashPassword(passwordRP);
                SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

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
        });
    }
}
