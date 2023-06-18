package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.MainActivity;
import com.example.sturzapp.R;

public class Notfallkontakt_Erstellen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_erstellen);

        Button button1 = findViewById(R.id.buttonZurückZurMainActivity);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Notfallkontakt_Erstellen.this, MainActivity.class);

                startActivity(intent1);
            }
        });

        Button button2 = findViewById(R.id.buttonCreateAccount);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Notfallkontakt Objekte
                EditText editTextEmail = findViewById(R.id.editTextNFK_Email);
                EditText editTextPasswort = findViewById(R.id.editTextNFK_Passwort);
                EditText editTextVorname = findViewById(R.id.editTextNFK_Vorname);
                EditText editTextNachname = findViewById(R.id.editTextNFK_Nachname);
                EditText editTextAdresse = findViewById(R.id.editTextNFK_Adresse);
                EditText editTextPLZ = findViewById(R.id.editTextNFK_PLZ);

                //Zugehöriger Risikopatient zu Notfallkontakt
                EditText editTextRP_Name = findViewById(R.id.editTextRP_Name);
                EditText editTextRP_Adresse = findViewById(R.id.editTextRP_Adresse);
                EditText editTextRP_plz = findViewById(R.id.editTextRP_PLZ);
                EditText editTextRP_email = findViewById(R.id.editTextRP_Email);

                String nfk_email = editTextEmail.getText().toString();
                String nfk_passwort = editTextPasswort.getText().toString();
                String nfk_vorname = editTextVorname.getText().toString();
                String nfk_nachname = editTextNachname.getText().toString();
                String nfk_adresse = editTextAdresse.getText().toString();
                String nfk_plz = editTextPLZ.getText().toString();

                String zugehoerigerRP_name = editTextRP_Name.getText().toString();
                String zugehoerigerRP_adresse = editTextRP_Adresse.getText().toString();
                String zugehoerigerRP_plz = editTextRP_plz.getText().toString();
                String zugehoerigerRP_email = editTextRP_email.getText().toString();

                //Eingabe im Intent abspeichern
                Intent intent2 = new Intent(Notfallkontakt_Erstellen.this, Notfallkontakt_Startseite.class);
                intent2.putExtra("nfk_e_mail", nfk_email);
                intent2.putExtra("nfk_Passwort", nfk_passwort);
                intent2.putExtra("nfk_Vorname", nfk_vorname);
                intent2.putExtra("nfk_Nachname", nfk_nachname);
                intent2.putExtra("nfk_Adresse", nfk_adresse);
                intent2.putExtra("nfk_plz", nfk_plz);

                intent2.putExtra("rp_name", zugehoerigerRP_name);
                intent2.putExtra("rp_adresse", zugehoerigerRP_adresse);
                intent2.putExtra("rp_plz", zugehoerigerRP_plz);
                intent2.putExtra("rp_email", zugehoerigerRP_email);

                startActivity(intent2);

            }
        });


    }
}