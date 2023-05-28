package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class risikopatient_startseite extends AppCompatActivity {

    String email = getIntent().getStringExtra("email");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);
    }
}