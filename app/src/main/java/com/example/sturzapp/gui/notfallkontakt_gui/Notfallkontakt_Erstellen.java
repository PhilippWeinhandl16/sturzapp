package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.MainActivity;
import com.example.sturzapp.R;

public class Notfallkontakt_Erstellen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_erstellen);

        Button button1 = findViewById(R.id.buttonZurückZurMainActivity);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Notfallkontakt_Erstellen.this, MainActivity.class);

                startActivity(intent1);
            }
        });

        Button button2 = findViewById(R.id.buttonCreateAccount);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Notfallkontakt Objekte
                EditText editTextEmail = findViewById(R.id.editTextNFK_Email);
                EditText editTextPasswort = findViewById(R.id.editTextNFK_Passwort);
                EditText editTextfirstName = findViewById(R.id.editTextNFK_firstName);
                EditText editTextlastName = findViewById(R.id.editTextNFK_lastName);
                EditText editTextaddress = findViewById(R.id.editTextNFK_address);
                EditText editTextPLZ = findViewById(R.id.editTextNFK_PLZ);

                //Zugehöriger Risikopatient zu Notfallkontakt
                EditText editTextRP_Name = findViewById(R.id.editTextRP_Name);
                EditText editTextRP_address = findViewById(R.id.editTextRP_address);
                EditText editTextRP_plz = findViewById(R.id.editTextRP_PLZ);
                EditText editTextRP_email = findViewById(R.id.editTextRP_Email);

                String nfk_email = editTextEmail.getText().toString();
                String nfk_passwort = editTextPasswort.getText().toString();
                String nfk_firstName = editTextfirstName.getText().toString();
                String nfk_lastName = editTextlastName.getText().toString();
                String nfk_address = editTextaddress.getText().toString();
                String nfk_plz = editTextPLZ.getText().toString();

                String zugehoerigerRP_name = editTextRP_Name.getText().toString();
                String zugehoerigerRP_address = editTextRP_address.getText().toString();
                String zugehoerigerRP_plz = editTextRP_plz.getText().toString();
                String zugehoerigerRP_email = editTextRP_email.getText().toString();

                //Eingabe im Intent abspeichern
                Intent intent2 = new Intent(Notfallkontakt_Erstellen.this, Notfallkontakt_Startseite.class);
                intent2.putExtra("nfk_e_mail", nfk_email);
                intent2.putExtra("nfk_Passwort", nfk_passwort);
                intent2.putExtra("nfk_firstName", nfk_firstName);
                intent2.putExtra("nfk_lastName", nfk_lastName);
                intent2.putExtra("nfk_address", nfk_address);
                intent2.putExtra("nfk_plz", nfk_plz);

                intent2.putExtra("rp_name", zugehoerigerRP_name);
                intent2.putExtra("rp_address", zugehoerigerRP_address);
                intent2.putExtra("rp_plz", zugehoerigerRP_plz);
                intent2.putExtra("rp_email", zugehoerigerRP_email);

                startActivity(intent2);

            }
        });


    }
}