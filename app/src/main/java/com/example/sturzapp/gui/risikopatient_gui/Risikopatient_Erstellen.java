package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.MainActivity;
import com.example.sturzapp.R;

public class Risikopatient_Erstellen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_erstellen);


        //Daten für den Risikopatienten
        EditText editTextRP_Email = findViewById(R.id.editText_RP_Email);
        EditText editTextRP_Passwort = findViewById(R.id.editTextRP_Passwort);
        EditText editTextRP_firstName = findViewById(R.id.editTextRP_firstName);
        EditText editTextRP_lastName = findViewById(R.id.editTextRP_lastName);
        EditText editTextRP_address = findViewById(R.id.editTextRP_address);
        EditText editTextRP_PLZ = findViewById(R.id.editTextRP_PLZ);

        //Daten für den Notfallkontakt des Risikopatienten
        //1. Vor- und Nachname, 2. Straße und Hausnummer, 3. PLZ und Ort, 4. E-Mail Adresse
        EditText editTextNFK_Name = findViewById(R.id.editTextNFK_Name);
        EditText editTextNFK_address = findViewById(R.id.editTextNFK_address);
        EditText editTextNFK_PLZ = findViewById(R.id.editTextNFK_PLZ);
        EditText editTextNFK_Email = findViewById(R.id.editTextNFK_Email);

        Button button = findViewById(R.id.buttonZurückZurMainActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Risikopatient_Erstellen.this, MainActivity.class);

                startActivity(intent);

            }
        });


            Button button2 = findViewById(R.id.buttonCreateAccount);

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //Eingaben der Daten für Risikopatienten speichern
                    String rp_e_mail = editTextRP_Email.getText().toString();
                    String rp_passwort = editTextRP_Passwort.getText().toString();
                    String rp_firstName = editTextRP_firstName.getText().toString();
                    String rp_lastName = editTextRP_lastName.getText().toString();
                    String rp_address = editTextRP_address.getText().toString();
                    String rp_plz = editTextRP_PLZ.getText().toString();

                    //Eingaben der Daten für Notfallkontakt speichern
                    String nfk_name = editTextNFK_Name.getText().toString();
                    String nfk_address = editTextNFK_address.getText().toString();
                    String nfk_plz = editTextNFK_PLZ.getText().toString();
                    String nfk_email = editTextNFK_Email.getText().toString();

                    Intent intent2 = new Intent(Risikopatient_Erstellen.this, Risikopatient_Startseite.class);

                    //Risikopatient Daten in Intent speichern
                    intent2.putExtra("rp_e_mail", rp_e_mail);
                    intent2.putExtra("rp_passwort", rp_passwort);
                    intent2.putExtra("rp_firstName", rp_firstName);
                    intent2.putExtra("rp_lastName", rp_lastName);
                    intent2.putExtra("rp_address", rp_address);
                    intent2.putExtra("rp_plz", rp_plz);

                    //Notfallkontaktdaten zu Risikopatient in Intent speichern
                    intent2.putExtra("nfk_name", nfk_name);
                    intent2.putExtra("nfk_address", nfk_address);
                    intent2.putExtra("nfk_plz", nfk_plz);
                    intent2.putExtra("nfk_email", nfk_email);

                    startActivity(intent2);

                }
            });

    }

}