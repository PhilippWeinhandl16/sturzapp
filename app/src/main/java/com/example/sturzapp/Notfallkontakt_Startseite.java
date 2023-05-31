package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Notfallkontakt_Startseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_startseite);

        Intent intent = getIntent();
        String e_mail = intent.getStringExtra("e_mail");

        TextView textViewEmail = findViewById(R.id.textViewEmail);

        textViewEmail.setText(e_mail);

    }
}