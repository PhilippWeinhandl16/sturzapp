package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sturzapp.R;


public class Risikopatient_Startseite extends AppCompatActivity {

    Intent intent = getIntent();

    //Risikopatientdaten
    private String rp_e_mail;
    private String rp_passwort;
    private String rp_firstName;
    private String rp_lastName;
    private String rp_address;
    private String rp_plz;

    //Notfallkontaktdaten
    private String nfk_name;
    private String nfk_address;
    private String nfk_plz;
    private String nfk_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);


            // Daten aus dem Intent abrufen
            Intent intent = getIntent();

            //E-Mail des Risikopatienten in View anzeigen lassen
            String e_mail = intent.getStringExtra("rp_e_mail");
            TextView textViewEmail = findViewById(R.id.textViewEmail);
            textViewEmail.setText(e_mail);


        //Risikopatientendaten abspeichern
        rp_e_mail = intent.getStringExtra("rp_e_mail");
        rp_passwort = intent.getStringExtra("rp_passwort");
        rp_firstName = intent.getStringExtra("rp_firstName");
        rp_lastName = intent.getStringExtra("rp_lastName");
        rp_address = intent.getStringExtra("rp_adresse");
        rp_plz = intent.getStringExtra("rp_plz");

        //Notfallkontaktdaten abspeichern
        nfk_name = intent.getStringExtra("nfk_name");
        nfk_address = intent.getStringExtra("nfk_address");
        nfk_plz = intent.getStringExtra("nfk_plz");
        nfk_email = intent.getStringExtra("nfk_email");



        //Button um Daten von Notfallkontakt zu ändern
        Button Button2 = findViewById(R.id.buttonNotfallkontakt_aendern);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Risikopatient_Startseite.this, Risikopatient_Notfallkontakt_Aendern.class);

                intent2.putExtra("nfk_name", nfk_name);
                intent2.putExtra("nfk_address", nfk_address);
                intent2.putExtra("nfk_plz", nfk_plz);
                intent2.putExtra("nfk_email", nfk_email);

                startActivity(intent2);
            }
        });

        // Daten in einem neuen Intent speichern

              /*  intent2.putExtra("rp_e_mail", rp_e_mail);
                intent2.putExtra("rp_passwort", rp_passwort);
                intent2.putExtra("rp_firstName", rp_firstName);
                intent2.putExtra("rp_lastName", rp_lastName);
                intent2.putExtra("rp_address", rp_address);
                intent2.putExtra("rp_plz", rp_plz); */



    }


}
