package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.R;

public class Notfallkontakt_Daten_Aendern extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_daten_aendern);

        //Variablen für EditText Objekte erzeugen
        EditText editTextEmail = findViewById(R.id.editTextNFK_Email_neu);
        EditText editTextPasswort = findViewById(R.id.editTextNFK_Passwort_neu);
        EditText editTextfirstName = findViewById(R.id.editTextNFK_firstName_neu);
        EditText editTextlastName = findViewById(R.id.editTextNFK_lastName_neu);
        EditText editTextaddress = findViewById(R.id.editTextNFK_address_new);
        EditText editTextPLZ = findViewById(R.id.editTextNFK_PLZ_neu);

        Intent intent = getIntent();

        //Daten für Notfallkontakt aus Intent abrufen
        String nfk_e_mail = intent.getStringExtra("nfk_Email_alt");
        String nfk_passwort = intent.getStringExtra("nfk_passwort_alt");
        String nfk_firstName = intent.getStringExtra("nfk_firstName_alt");
        String nfk_lastName = intent.getStringExtra("nfk_lastName_alt");
        String nfk_address = intent.getStringExtra("nfk_address_alt");
        String nfk_plz = intent.getStringExtra("nfk_plz_alt");

        editTextEmail.setText(nfk_e_mail);
        editTextPasswort.setText(nfk_passwort);
        editTextfirstName.setText(nfk_firstName);
        editTextlastName.setText(nfk_lastName);
        editTextaddress.setText(nfk_address);
        editTextPLZ.setText(nfk_plz);

        String nfk_email_neu = editTextEmail.getText().toString();
        String nfk_passwort_neu = editTextPasswort.getText().toString();
        String nfk_firstName_neu = editTextfirstName.getText().toString();
        String nfk_lastName_neu = editTextlastName.getText().toString();
        String nfk_address_new = editTextaddress.getText().toString();
        String nfk_plz_neu = editTextPLZ.getText().toString();


        Button button = findViewById(R.id.Button_Notfallkontakt_Daten_Updaten);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Eingabe der EditText Objekte in Intent speichern und an Notfallkontakt_Startseite übergeben

                Intent intent2 = new Intent(Notfallkontakt_Daten_Aendern.this, Notfallkontakt_Startseite.class);
                intent2.putExtra("nfk_email_aktualisiert", nfk_email_neu);
                intent2.putExtra("nfk_passwort_aktualisiert", nfk_passwort_neu);
                intent2.putExtra("nfk_firstName_aktualisiert", nfk_firstName_neu);
                intent2.putExtra("nfk_lastName_aktualisiert", nfk_lastName_neu);
                intent2.putExtra("nfk_address_aktualisiert", nfk_address_new);
                intent2.putExtra("nfk_plz_aktualisiert", nfk_plz_neu);

                startActivity(intent2);
            }
        });

    }



}