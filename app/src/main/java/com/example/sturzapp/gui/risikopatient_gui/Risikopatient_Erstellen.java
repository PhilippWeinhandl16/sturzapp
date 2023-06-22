package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.MainActivity;
import com.example.sturzapp.R;

public class Risikopatient_Erstellen extends AppCompatActivity {

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

                Intent intent = new Intent(Risikopatient_Erstellen.this, MainActivity.class);

                startActivity(intent);

            }
        });


            Button button2 = findViewById(R.id.buttonCreateAccount);

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

                    Intent intent2 = new Intent(Risikopatient_Erstellen.this, Risikopatient_Startseite.class);

                    //Risikopatient Daten in Intent speichern
                    intent2.putExtra("emailRP", emailRP);
                    intent2.putExtra("passwordRP", passwordRP);
                    intent2.putExtra("firstNameRP", firstNameRP);
                    intent2.putExtra("lastNameRP", lastNameRP);

                    //Notfallkontaktdaten zu Risikopatient in Intent speichern
                    intent2.putExtra("emailNFK", emailNFK);
                    intent2.putExtra("nameNFK", nameNFK);

                    startActivity(intent2);

                }
            });

    }

}