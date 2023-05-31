package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Notfallkontakt_Erstellen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfallkontakt_erstellen);

        Button button1 = findViewById(R.id.buttonZurückZurMainActivity);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Notfallkontakt_Erstellen.this, MainActivity.class);

                startActivity(intent1);
            }
        });

        Button button2 = findViewById(R.id.buttonCreateAccount);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editTextEmail = findViewById(R.id.editTextEmail);

                String e_mail = editTextEmail.getText().toString();

                Intent intent2 = new Intent(Notfallkontakt_Erstellen.this, Notfallkontakt_Startseite.class);
                intent2.putExtra("e_mail", e_mail);

                startActivity(intent2);

            }
        });


    }
}