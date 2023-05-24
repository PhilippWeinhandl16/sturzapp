package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RadioGroup radioGroup_account_auswahl1 = findViewById(R.id.radioGroup_account_auswahl1);


        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_emailadresse = findViewById(R.id.editText_Email);
                EditText editText_passwort = findViewById(R.id.editTextPassword);

                String email_adresse = editText_emailadresse.getText().toString();
                String passwort = editText_passwort.getText().toString();

                int selected_RadioButton = radioGroup_account_auswahl1.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selected_RadioButton);
                String ausgewaehlterRadioButtonText = radioButton.getText().toString();

                Intent intent;

                    if (ausgewaehlterRadioButtonText.equals("Risikopatient")){
                        intent = new Intent(MainActivity.this, risikopatient_erstellen.class);

                }
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