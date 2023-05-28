package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class risikopatient_erstellen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_erstellen);

        Button button = findViewById(R.id.buttonZurückZurMainActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(risikopatient_erstellen.this, MainActivity.class);

                startActivity(intent);

            }
        });

        Button button2 = findViewById(R.id.buttonCreateAccount);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editTextEmail = findViewById(R.id.editText_Email);

                String e_mail = editTextEmail.getText().toString();

                Intent intent2 = new Intent(risikopatient_erstellen.this, notfallkontakt_startseite.class);
                intent2.putExtra("e_mail", e_mail);

                startActivity(intent2);

            }
        });


    }
}