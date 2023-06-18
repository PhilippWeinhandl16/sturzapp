package com.example.sturzapp.gui.notfallkontakt_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sturzapp.R;

public class Notfallkontakt_RP_Daten_Anzeigen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_rp_daten_anzeigen);

        TextView TextViewRP_name = findViewById(R.id.textViewNFK_zugehoeriger_risikopatient_name);
        TextView TextViewRP_address = findViewById(R.id.textViewNFK_zugehoeriger_risikopatient_adress);
        TextView TextViewRP_plz = findViewById(R.id.textViewNFK_zugehoeriger_risikopatient_plz);
        TextView TextViewRP_email = findViewById(R.id.textViewNFK_zugehoeriger_risikopatient_email);

        Intent intent1 = getIntent();

        String rp_name = intent1.getStringExtra("zugehoeriger_RP_name");
        String rp_address = intent1.getStringExtra("zugehoeriger_RP_address");
        String rp_plz = intent1.getStringExtra("zugehoeriger_RP_plz");
        String rp_email = intent1.getStringExtra("zugehoeriger_RP_email");

        TextViewRP_name.setText(rp_name);
        TextViewRP_address.setText(rp_address);
        TextViewRP_plz.setText(rp_plz);
        TextViewRP_email.setText(rp_email);

        Button button = findViewById(R.id.Zurueck_zur_Notfallkontakt_Startseite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Notfallkontakt_RP_Daten_Anzeigen.this, Notfallkontakt_Startseite.class);

                startActivity(intent);
            }
        });

    }
}