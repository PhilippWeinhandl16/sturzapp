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


        Button button1 = findViewById(R.id.buttonCreateAccount);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_e_mail = findViewById(R.id.editTextEmail);

                String e_mail = editText_e_mail.getText().toString();

                Intent intent1 = new Intent(risikopatient_erstellen.this, risikopatient_startseite.class);
                intent1.putExtra("e_mail", e_mail);

                startActivity(intent1);
            }
        });

        Button button2 = findViewById(R.id.buttonZurückZurMainActivity);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(risikopatient_erstellen.this, MainActivity.class);

                startActivity(intent2);
            }
        });

    }
}