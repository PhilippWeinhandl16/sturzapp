package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class risikopatient_startseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);

        String email = getIntent().getStringExtra("e_mail");

        TextView textView = findViewById(R.id.textViewEmail);

        textView.setText(email);



    }
}