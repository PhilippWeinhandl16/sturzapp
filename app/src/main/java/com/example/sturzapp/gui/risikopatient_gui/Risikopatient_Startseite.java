package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sturzapp.R;


public class Risikopatient_Startseite extends AppCompatActivity {

    Intent intent = getIntent();

    //Risikopatientdaten
    private String emailRP;
    private String passwordRP;
    private String firstNameRP;
    private String lastNameRP;

    //Notfallkontaktdaten
    private String emailNFK;
    private String nameNFK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);


            // Daten aus dem Intent abrufen
            Intent intent = getIntent();

            //E-Mail des Risikopatienten in View anzeigen lassen
            String emailRP = intent.getStringExtra("emailRP");
            String passwordRP = intent.getStringExtra("passwordRP");
            String firstNameRP = intent.getStringExtra("firstNameRP");
            String lastNameRP = intent.getStringExtra("lastNameRP");


            TextView textViewemailRP_display = findViewById(R.id.textViewemailRP_display);
            TextView textViewpasswordRP_display = findViewById(R.id.textViewpasswordRP_display);
            TextView textViewfirstNameRP_display = findViewById(R.id.textViewemailRP_display);
            TextView textViewlastNameRP_display = findViewById(R.id.textViewlastNameRP_display);

        textViewemailRP_display.setText(emailRP);
        textViewpasswordRP_display.setText(passwordRP);
        textViewfirstNameRP_display.setText(firstNameRP);
        textViewlastNameRP_display.setText(lastNameRP);

        //Notfallkontaktdaten abspeichern
        emailNFK = intent.getStringExtra("emailNFK");
        nameNFK = intent.getStringExtra("nameNFK");


        Button button1 = findViewById(R.id.buttonDatenAendern);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Risikopatient_Startseite.this, Risikopatient_Daten_Aendern.class);

                intent1.putExtra("emailRP", emailRP);
                intent1.putExtra("passwordRP", passwordRP);
                intent1.putExtra("firstNameRP", firstNameRP);
                intent1.putExtra("lastNameRP", lastNameRP);

                startActivity(intent1);

            }
        });

        //Button um Daten von Notfallkontakt zu ändern
        Button Button2 = findViewById(R.id.buttonNotfallkontakt_aendern);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Risikopatient_Startseite.this, Risikopatient_Notfallkontakt_Aendern.class);

                intent2.putExtra("emailNFK", emailNFK);
                intent2.putExtra("nameNFK", nameNFK);

                startActivity(intent2);
            }
        });

        // Daten in einem neuen Intent speichern

              /*  intent2.putExtra("rp_e_mail", rp_e_mail);
                intent2.putExtra("rp_passwort", rp_passwort);
                intent2.putExtra("rp_firstName", rp_firstName);
                intent2.putExtra("rp_lastName", rp_lastName);
                intent2.putExtra("rp_address", rp_address);
                intent2.putExtra("rp_plz", rp_plz); */



    }


}
