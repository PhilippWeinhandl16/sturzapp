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

        String rp_name = intent.getStringExtra("rp_name");
        String rp_adresse = intent.getStringExtra("rp_adresse");
        String rp_plz = intent.getStringExtra("rp_plz");
        String rp_email = intent.getStringExtra("rp_email");


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

        Button button1 = findViewById(R.id.buttonRisikopatientAnzeigen);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(Notfallkontakt_Startseite.this, Notfallkontakt_RP_Daten_Anzeigen.class);

                intent3.putExtra("zugehoeriger_RP_name", rp_name);
                intent3.putExtra("zugehoeriger_RP_adresse", rp_adresse);
                intent3.putExtra("zugehoeriger_RP_plz", rp_plz);
                intent3.putExtra("zugehoeriger_RP_email", rp_email);

                startActivity(intent3);
            }
        });

    }
}