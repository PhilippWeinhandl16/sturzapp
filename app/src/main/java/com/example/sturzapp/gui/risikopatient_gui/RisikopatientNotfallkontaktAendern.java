package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sturzapp.R;
import com.example.sturzapp.database.entity.AccountEntity;

public class RisikopatientNotfallkontaktAendern extends AppCompatActivity {

    private AccountEntity entity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riskiopatient_notfallkontakt_aendern);

        Intent intent = getIntent();


        String emailNFK = intent.getStringExtra("emailNFK");
        String nameNFK = intent.getStringExtra("nameNFK");

        //EditText Objekte erzeugen
        EditText editTextemailNFK = findViewById(R.id.editTextemailNFK_change);
        EditText editTextnameNFK = findViewById(R.id.editTextnameNFK_change);

        //EditText - Notfallkontakt Daten anzeigen lassen

        editTextemailNFK.setText(emailNFK);
        editTextnameNFK.setText(nameNFK);

        Button button_notfallkontakt_aendern = findViewById(R.id.Button_NFK_change);

        button_notfallkontakt_aendern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(RisikopatientNotfallkontaktAendern.this, RisikopatientStartseite.class);

                String emailNFK_updatet = editTextemailNFK.getText().toString();
                String nameNFK_updatet = editTextnameNFK.getText().toString();

                intent2.putExtra("emailNFK_updatet", emailNFK_updatet);
                intent2.putExtra("nameNFK_updatet", nameNFK_updatet);

                startActivity(intent2);




            }
        });



    }
}