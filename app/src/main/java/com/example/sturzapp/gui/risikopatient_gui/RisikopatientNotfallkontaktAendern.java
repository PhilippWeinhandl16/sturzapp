package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;

public class RisikopatientNotfallkontaktAendern extends AppCompatActivity {

    private AccountEntity entity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riskiopatient_notfallkontakt_aendern);

        // EditText Objekte erzeugen
        EditText editTextemailNFK = findViewById(R.id.editTextemailNFK_change);
        EditText editTextnameNFK = findViewById(R.id.editTextnameNFK_change);

        Button saveChanges = findViewById(R.id.Button_NFK_change);

        Intent intent = getIntent();

        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        // auslesen
        new Thread(() -> {
            long id = intent.getLongExtra("id", -1);

            // aus db auslesen
            entity = db.accountDao().getAccountById((int) id);

            if (entity != null) {
                editTextemailNFK.setText(entity.getEmailNFK());
                editTextnameNFK.setText(entity.getNameNFK());
            }
        }).start();

        saveChanges.setOnClickListener(it -> {
            if (entity != null) {
                // check obs eh gesetzt ist --> sonst Fehler
                new Thread(() -> {
                    entity.setEmailNFK(editTextemailNFK.getText().toString());
                    entity.setNameNFK(editTextnameNFK.getText().toString());

                    // update
                    db.accountDao().update(entity);

                    runOnUiThread(() -> {
                        onBackPressed();
                    });
                }).start();
            }
        });
    }
}
