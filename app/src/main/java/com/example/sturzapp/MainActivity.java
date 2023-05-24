package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton risikopatient1 = findViewById(R.id.radioButton_Risikopatient1);
        RadioButton notfallpatient1 = findViewById(R.id.radioButton_Notfallkontakt1);

        EditText editText_emailadresse = findViewById(R.id.editText_Email);
        EditText editText_passwort = findViewById(R.id.editTextPassword);

        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_adresse = editText_emailadresse.getText().toString();
                String passwort = editText_passwort.getText().toString();

            }
        });

        RadioButton risikopatient2 = findViewById(R.id.radioButtonRiskPatient2);
        RadioButton notfallkontakt2 = findViewById(R.id.radioButton_Notfallkontakt2);

        Button button_accountErstellen = findViewById(R.id.button_createAccount);

        button_accountErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //das ist ein Test 3
    }
}