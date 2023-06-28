package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.MainActivity;
import com.example.sturzapp.PasswordHasher;
import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

public class RisikopatientErstellen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_erstellen);


        //Daten für den Risikopatienten
        EditText editTextemailRP_input = findViewById(R.id.editTextemailRP_input);
        EditText editTextpasswordRP_input = findViewById(R.id.editTextpasswordRP_input);
        EditText editTextfirstNameRP_input = findViewById(R.id.editTextfirstNameRP_input);
        EditText editTextlastNameRP_input = findViewById(R.id.editTextlastNameRP_input);


        //Daten für den Notfallkontakt des Risikopatienten
        //1. Vor- und Nachname, 2. Straße und Hausnummer, 3. PLZ und Ort, 4. E-Mail Adresse
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

        Context context = getApplicationContext();

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Eingaben der Daten für Risikopatienten speichern
                    String emailRP = editTextemailRP_input.getText().toString();
                    String passwordRP = editTextpasswordRP_input.getText().toString();
                    String firstNameRP = editTextfirstNameRP_input.getText().toString();
                    String lastNameRP = editTextlastNameRP_input.getText().toString();

                    //Eingaben der Daten für Notfallkontakt speichern
                    String emailNFK = editTextemailNFK_input.getText().toString();
                    String nameNFK = editTextnameNFK_input.getText().toString();

                    String hashedPasswordRP = PasswordHasher.hashPassword(passwordRP);

                    //db
                    SturzappDatabase db = SturzappDatabase.getInstance(context);
                    //speichern
                    new Thread(() -> {
                        // Erstelle ein neues AccountEntity-Objekt
                        AccountEntity account = new AccountEntity(
                                emailRP,
                                hashedPasswordRP,
                                firstNameRP,
                                lastNameRP,
                                emailNFK,
                                nameNFK
                        );

                        // Füge den Account in die Datenbank ein
                        long id = db.accountDao().insert(account);

                        Intent intent2 = new Intent(RisikopatientErstellen.this, RisikopatientStartseite.class);
                        intent2.putExtra("id", id);

                        startActivity(intent2);
                    }).start();



                }
            });

    }

}