package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.sturzapp.R;

public class Risikopatient_Daten_Aendern extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_daten_aendern);

        EditText editTextemailRP_change = findViewById(R.id.editTextemailRP_change);
        EditText editTextpasswordRP_change = findViewById(R.id.editTextpasswordRP_change);
        EditText editTextfirstNameRP_change = findViewById(R.id.editTextfirstNameRP_change);
        EditText editTextlastNameRP_change = findViewById(R.id.editTextlastNameRP_change);

        Intent intent1 = getIntent();

        String emailRP = intent1.getStringExtra("emailRP");
        String passwordRP = intent1.getStringExtra("passwordRP");
        String firstNameRP = intent1.getStringExtra("firstNameRP");
        String lastNameRP = intent1.getStringExtra("lastNameRP");

        editTextemailRP_change.setText(emailRP);
        editTextpasswordRP_change.setText(passwordRP);
        editTextfirstNameRP_change.setText(firstNameRP);
        editTextlastNameRP_change.setText(lastNameRP);

    }
}