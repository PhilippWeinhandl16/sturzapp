package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.R;

public class Risikopatient_Notfallkontakt_Aendern extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riskiopatient_notfallkontakt_aendern);

        Intent intent = getIntent();

        intent.getStringExtra("nfk_name");
        intent.getStringExtra("nfk_adresse");
        intent.getStringExtra("nfk_plz");
        intent.getStringExtra("nfk_email");

        //EditText Objekte erzeugen
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editText_Strasse_und_Hausnummer = findViewById(R.id.EditText_Strasse_und_Hausnummer);
        EditText editTextPostleitzahl_und_Ort = findViewById(R.id.editPostleitzahl_und_Ort);
        EditText editTextEmail = findViewById(R.id.editTextEmail);

        //EditText - Notfallkontakt Daten anzeigen lassen

        editTextName.setText("nfk_name");
        editText_Strasse_und_Hausnummer.setText("nfk_adresse");
        editTextPostleitzahl_und_Ort.setText("nfk_plz");
        editTextEmail.setText("nfk_email");

        Button button_notfallkontakt_aendern = findViewById(R.id.Button_Notfallkontakt_Hinzufuegen);

        button_notfallkontakt_aendern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });



    }
}