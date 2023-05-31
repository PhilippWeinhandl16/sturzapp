package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Risikopatient_Erstellen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_erstellen);

        Button button = findViewById(R.id.buttonZurückZurMainActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Risikopatient_Erstellen.this, MainActivity.class);

                startActivity(intent);

            }
        });

        Button button2 = findViewById(R.id.buttonCreateAccount);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editTextEmail = findViewById(R.id.editTextEmail);

                String e_mail = editTextEmail.getText().toString();

                Intent intent2 = new Intent(Risikopatient_Erstellen.this, Risikopatient_Startseite.class);
                intent2.putExtra("e_mail", e_mail);

                startActivity(intent2);

            }
        });


    }
}