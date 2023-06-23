package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

public class Risikopatient_Daten_Aendern extends AppCompatActivity {


    private AccountEntity entity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_daten_aendern);

        EditText editTextemailRP_change = findViewById(R.id.editTextemailRP_change);
        EditText editTextpasswordRP_change = findViewById(R.id.editTextpasswordRP_change);
        EditText editTextfirstNameRP_change = findViewById(R.id.editTextfirstNameRP_change);
        EditText editTextlastNameRP_change = findViewById(R.id.editTextlastNameRP_change);


        Button saveChanges = findViewById(R.id.buttonChangeAccount);

        Intent intent = getIntent();

        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        //auslesen
        new Thread(() -> {
            long id = intent.getLongExtra("id", -1);

            // aus db auslesen
            entity = db.accountDao().getAccountById((int) id);

            if(entity != null) {
                editTextemailRP_change.setText(entity.getEmailRP());
                editTextpasswordRP_change.setText(entity.getPasswordRP());
                editTextfirstNameRP_change.setText(entity.getFirstNameRP());
                editTextlastNameRP_change.setText(entity.getLastNameRP());
            }


        }).start();

        saveChanges.setOnClickListener(it -> {
            if (entity != null) {                // check obs eh gesetzt ist --> sont fehler
                new Thread(() -> {
                    entity.setEmailRP(editTextemailRP_change.getText() + "");
                    entity.setPasswordRP(editTextpasswordRP_change.getText() + "");
                    entity.setFirstNameRP(editTextfirstNameRP_change.getText() + "");
                    entity.setLastNameRP(editTextlastNameRP_change.getText() + "");


                    // update
                    db.accountDao().update(entity);

                    runOnUiThread(new Thread(() -> {
                        onBackPressed();
                    }));

                }).start();
            }

            });


        }
    }