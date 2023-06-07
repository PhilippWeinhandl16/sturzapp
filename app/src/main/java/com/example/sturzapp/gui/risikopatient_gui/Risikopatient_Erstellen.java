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

                //Daten für den Risikopatienten
                EditText editTextRP_Email = findViewById(R.id.editTextEmail);
                EditText editTextRP_Passwort = findViewById(R.id.editTextRP_Passwort);
                EditText editTextRP_Vorname = findViewById(R.id.editTextVorname);
                EditText editTextRP_Nachname = findViewById(R.id.editTextNachname);
                EditText editTextRP_Adresse = findViewById(R.id.editTextAddress);
                EditText editTextRP_PLZ = findViewById(R.id.editTextRP_PLZ);

                //Daten für den Notfallkontakt des Risikopatienten
                //1. Vor- und Nachname, 2. Straße und Hausnummer, 3. PLZ und Ort, 4. E-Mail Adresse
                EditText editTextNFK_Name = findViewById(R.id.editTextNFK_Name);
                EditText editTextNFK_Adresse = findViewById(R.id.editTextNFK_Adresse);
                EditText editTextNFK_PLZ = findViewById(R.id.editTextNFK_PLZ);
                EditText editTextNFK_Email = findViewById(R.id.editTextNFK_Email);


                //Eingaben der Daten für Risikopatienten speichern
                String rp_e_mail = editTextRP_Email.getText().toString();
                String rp_passwort = editTextRP_Passwort.getText().toString();
                String rp_vorname = editTextRP_Vorname.getText().toString();
                String rp_nachname = editTextRP_Nachname.getText().toString();
                String rp_adresse = editTextRP_Adresse.getText().toString();
                String rp_plz = editTextRP_PLZ.getText().toString();

                Intent intent2 = new Intent(Risikopatient_Erstellen.this, Risikopatient_Startseite.class);
                intent2.putExtra("e_mail", rp_e_mail);

                startActivity(intent2);

            }
        });


    }
}