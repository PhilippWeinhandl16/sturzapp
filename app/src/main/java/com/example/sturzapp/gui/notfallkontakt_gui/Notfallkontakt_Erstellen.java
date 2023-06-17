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

                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPasswort = findViewById(R.id.editTextNFK_Passwort_neu);
                EditText editTextVorname = findViewById(R.id.editTextNFK_Vorname_neu);
                EditText editTextNachname = findViewById(R.id.editTextNFK_Nachname_neu);
                EditText editTextAdresse = findViewById(R.id.editTextNFK_Adresse_neu);
                EditText editTextPLZ = findViewById(R.id.editTextNFK_PLZ_neu);

                String e_mail = editTextEmail.getText().toString();

                Intent intent2 = new Intent(Notfallkontakt_Erstellen.this, Notfallkontakt_Startseite.class);
                intent2.putExtra("e_mail", e_mail);

                startActivity(intent2);

            }
        });


    }
}