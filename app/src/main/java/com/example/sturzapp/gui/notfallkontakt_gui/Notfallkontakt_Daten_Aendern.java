package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.sturzapp.R;

public class Notfallkontakt_Daten_Aendern extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_daten_aendern);

        //Variablen für EditText Objekte erzeugen
        EditText editTextEmail = findViewById(R.id.editTextNFK_Email_neu);
        EditText editTextPasswort = findViewById(R.id.editTextNFK_Passwort_neu);
        EditText editTextVorname = findViewById(R.id.editTextNFK_Vorname_neu);
        EditText editTextNachname = findViewById(R.id.editTextNFK_Nachname_neu);
        EditText editTextAdresse = findViewById(R.id.editTextNFK_Adresse_neu);
        EditText editTextPLZ = findViewById(R.id.editTextNFK_PLZ_neu);

        Intent intent = getIntent();

        //Daten für Notfallkontakt aus Intent abrufen
        String nfk_e_mail = intent.getStringExtra("nfk_Email_alt");
        String nfk_passwort = intent.getStringExtra("nfk_passwort_alt");
        String nfk_vorname = intent.getStringExtra("nfk_vorname_alt");
        String nfk_nachname = intent.getStringExtra("nfk_nachname_alt");
        String nfk_adresse = intent.getStringExtra("nfk_adresse_alt");
        String nfk_plz = intent.getStringExtra("nfk_plz_alt");

        editTextEmail.setText(nfk_e_mail);
        editTextPasswort.setText(nfk_passwort);
        editTextVorname.setText(nfk_vorname);
        editTextNachname.setText(nfk_nachname);
        editTextAdresse.setText(nfk_adresse);
        editTextPLZ.setText(nfk_plz);

    }



}