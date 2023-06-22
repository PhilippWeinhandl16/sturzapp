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
        intent.getStringExtra("nfk_address");
        intent.getStringExtra("nfk_plz");
        intent.getStringExtra("nfk_email");

        //EditText Objekte erzeugen
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);

        //EditText - Notfallkontakt Daten anzeigen lassen

        editTextName.setText("nfk_name");
        editTextEmail.setText("nfk_email");

        Button button_notfallkontakt_aendern = findViewById(R.id.Button_Notfallkontakt_Hinzufuegen);

        button_notfallkontakt_aendern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });



    }
}