package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        String nfk_email_neu = editTextEmail.getText().toString();
        String nfk_passwort_neu = editTextPasswort.getText().toString();
        String nfk_vorname_neu = editTextVorname.getText().toString();
        String nfk_nachname_neu = editTextNachname.getText().toString();
        String nfk_adresse_neu = editTextAdresse.getText().toString();
        String nfk_plz_neu = editTextPLZ.getText().toString();


        Button button = findViewById(R.id.Button_Notfallkontakt_Daten_Updaten);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Eingabe der EditText Objekte in Intent speichern und an Notfallkontakt_Startseite übergeben

                Intent intent2 = new Intent(Notfallkontakt_Daten_Aendern.this, Notfallkontakt_Startseite.class);
                intent2.putExtra("nfk_email_aktualisiert", nfk_email_neu);
                intent2.putExtra("nfk_passwort_aktualisiert", nfk_passwort_neu);
                intent2.putExtra("nfk_vorname_aktualisiert", nfk_vorname_neu);
                intent2.putExtra("nfk_nachname_aktualisiert", nfk_nachname_neu);
                intent2.putExtra("nfk_adresse_aktualisiert", nfk_adresse_neu);
                intent2.putExtra("nfk_plz_aktualisiert", nfk_plz_neu);

                startActivity(intent2);
            }
        });

    }



}