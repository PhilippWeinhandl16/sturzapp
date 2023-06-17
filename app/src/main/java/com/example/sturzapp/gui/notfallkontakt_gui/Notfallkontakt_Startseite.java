package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sturzapp.R;

public class Notfallkontakt_Startseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_startseite);

        Intent intent = getIntent();
        String e_mail = intent.getStringExtra("e_mail");

        TextView textViewEmail = findViewById(R.id.textViewEmail);

        textViewEmail.setText(e_mail);



        //Button - Daten für Notfallkontakt ändern / weiterleiten zu Notfallkontakt_Daten_Aendern
        Button button = findViewById(R.id.buttonDatenAendern);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Notfallkontakt_Startseite.this, Notfallkontakt_Daten_Aendern.class);

                startActivity(intent2);
            }
        });

    }
}