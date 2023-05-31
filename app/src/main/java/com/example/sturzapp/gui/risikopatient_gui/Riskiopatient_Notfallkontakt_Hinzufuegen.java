package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.R;

public class Riskiopatient_Notfallkontakt_Hinzufuegen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riskiopatient_notfallkontakt_hinzufuegen);

        Button button_notfallkontakt_hinzufuegen = findViewById(R.id.Button_Notfallkontakt_Hinzufuegen);
        button_notfallkontakt_hinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText_eMail = findViewById(R.id.editTextEmail);
                EditText editText_Vorname = findViewById(R.id.editTextVorname);
                EditText editText_Nachname = findViewById(R.id.editTextNachname);
                EditText editText_Strasse_und_Hausnummer = findViewById(R.id.EditText_Strasse_und_Hausnummer);
                EditText editText_PLZ_und_Ort = findViewById(R.id.editPostleitzahl_und_Ort);

                String e_mail = editText_eMail.getText().toString();
                String vorname = editText_Vorname.getText().toString();
                String nachname = editText_Nachname.getText().toString();
                String strasse_und_hausnummer = editText_Strasse_und_Hausnummer.getText().toString();
                String plz_und_ort = editText_PLZ_und_Ort.getText().toString();

                Intent intent = new Intent(Riskiopatient_Notfallkontakt_Hinzufuegen.this, Risikopatient_Startseite.class);

                intent.putExtra("e_mail", e_mail);
                intent.putExtra("vorname", vorname);
                intent.putExtra("nachname", nachname);
                intent.putExtra("strasse_und_hausnummer", strasse_und_hausnummer);
                intent.putExtra("plz_und_ort", plz_und_ort);

                startActivity(intent);
            }
        });



    }
}