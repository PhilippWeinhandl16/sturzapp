package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sturzapp.R;

public class Notfallkontakt_Startseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_startseite);

        Intent intent = getIntent();

        //Daten für Notfallkontakt aus Intent abrufen
        String nfk_e_mail = intent.getStringExtra("nfk_e_mail");
        String nfk_passwort = intent.getStringExtra("nfk_Passwort");
        String nfk_vorname = intent.getStringExtra("nfk_Vorname");
        String nfk_nachname = intent.getStringExtra("nfk_Nachname");
        String nfk_adresse = intent.getStringExtra("nfk_Adresse");
        String nfk_plz = intent.getStringExtra("nfk_plz");

        //Text View und EditText Objekte erzeugen
        TextView textViewEmail = findViewById(R.id.textViewEmail);

        textViewEmail.setText(nfk_e_mail);



        //Button - Daten für Notfallkontakt ändern / weiterleiten zu Notfallkontakt_Daten_Aendern
        Button button = findViewById(R.id.buttonDatenAendern);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Notfallkontakt_Startseite.this, Notfallkontakt_Daten_Aendern.class);

                //Daten über Notfallkontakt an Notfallkontakt_Daten_Aendern Klasse weiterleiten
                intent2.putExtra("nfk_Email_alt", nfk_e_mail);
                intent2.putExtra("nfk_passwort_alt", nfk_passwort);
                intent2.putExtra("nfk_vorname_alt", nfk_vorname);
                intent2.putExtra("nfk_nachname_alt", nfk_nachname);
                intent2.putExtra("nfk_adresse_alt", nfk_adresse);
                intent2.putExtra("nfk_plz_alt", nfk_plz);

                startActivity(intent2);
            }
        });

    }
}