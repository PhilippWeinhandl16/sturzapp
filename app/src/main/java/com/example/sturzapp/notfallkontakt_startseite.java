package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sturzapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class notfallkontakt_startseite extends AppCompatActivity {

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