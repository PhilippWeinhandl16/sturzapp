package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sturzapp.R;


public class Risikopatient_Startseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);

        Intent intent = getIntent();
        String e_mail = intent.getStringExtra("e_mail");

        TextView textViewEmail = findViewById(R.id.textViewEmail);

        textViewEmail.setText(e_mail);

        Button button1 = findViewById(R.id.buttonNotfallkontakt_Hinzufuegen);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Risikopatient_Startseite.this, Riskiopatient_Notfallkontakt_Hinzufuegen.class);

                startActivity(intent1);
            }
        });

    }
}