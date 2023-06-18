package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sturzapp.R;

public class Notfallkontakt_Daten_Anzeigen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_daten_anzeigen);

        //Objekte für die TextView Elemente erstellen
        TextView textView_email = findViewById(R.id.TextViewNFK_Email);
        TextView textView_passwort = findViewById(R.id.TextViewNFK_Passwort);
        TextView textView_firstName = findViewById(R.id.TextViewNFK_firstName);
        TextView textView_lastName = findViewById(R.id.TextViewNFK_lastName);
        TextView textView_address = findViewById(R.id.TextViewNFK_address);
        TextView textView_plz = findViewById(R.id.TextViewNFK_plz);


        Intent intent = getIntent();

        String NFK_email = intent.getStringExtra("nfk_email");
        String NFK_passwort = intent.getStringExtra("nfk_passwort");
        String NFK_firstName =  intent.getStringExtra("nfk_firstName");
        String NFK_lastName = intent.getStringExtra("nfk_lastName");
        String NFK_address = intent.getStringExtra("nfk_address");
        String NFK_plz = intent.getStringExtra("nfk_plz");


        //Text ausgeben lassen
        textView_email.setText(NFK_email);
        textView_passwort.setText(NFK_passwort);
        textView_firstName.setText(NFK_firstName);
        textView_lastName.setText(NFK_lastName);
        textView_address.setText(NFK_address);
        textView_plz.setText(NFK_plz);


        Button button1 = findViewById(R.id.Zurueck_zur_Notfallkontakt_Startseite);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Notfallkontakt_Daten_Anzeigen.this, Notfallkontakt_Startseite.class);

                startActivity(intent2);
            }
        });

    }
}