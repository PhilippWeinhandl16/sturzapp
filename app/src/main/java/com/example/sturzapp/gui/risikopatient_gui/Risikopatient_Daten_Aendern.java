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

        intent1.getStringExtra("");
        intent1.getStringExtra("");
        intent1.getStringExtra("");
        intent1.getStringExtra("");

        String emailRP = editTextemailRP_change.getText().toString();
        String passwordRP = editTextpasswordRP_change.getText().toString();
        String firstNameRP = editTextfirstNameRP_change.getText().toString();
        String lastNameRP = editTextlastNameRP_change.getText().toString();

    }
}