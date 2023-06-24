package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;


public class RisikopatientStartseite extends AppCompatActivity {

    Intent intent = getIntent();

    private TextView textViewemailRP_display;
    private TextView textViewpasswordRP_display;
    private TextView textViewfirstNameRP_display;
    private TextView textViewlastNameRP_display;
    private long id;

    @Override
    protected void onResume() {
        super.onResume();

        textViewemailRP_display = findViewById(R.id.textViewemailRP_display);
        textViewpasswordRP_display = findViewById(R.id.textViewpasswordRP_display);
        textViewfirstNameRP_display = findViewById(R.id.textViewemailRP_display);
        textViewlastNameRP_display = findViewById(R.id.textViewlastNameRP_display);

        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
        new Thread(() -> {
            // aus db auslesen
            AccountEntity entity = db.accountDao().getAccountById((int) id);

            runOnUiThread(new Thread(() -> {
                if (entity != null) {
                    textViewemailRP_display.setText(entity.getEmailRP());
                    textViewpasswordRP_display.setText(entity.getPasswordRP());
                    textViewfirstNameRP_display.setText(entity.getFirstNameRP());
                    textViewlastNameRP_display.setText(entity.getLastNameRP());
                }
            }));



        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);


        // Daten aus dem Intent abrufen
        Intent intent = getIntent();


        textViewemailRP_display = findViewById(R.id.textViewemailRP_display);
        textViewpasswordRP_display = findViewById(R.id.textViewpasswordRP_display);
        textViewfirstNameRP_display = findViewById(R.id.textViewemailRP_display);
        textViewlastNameRP_display = findViewById(R.id.textViewlastNameRP_display);


        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        //auslesen
        new Thread(() -> {
            id = intent.getLongExtra("id", -1);

            System.out.println(id);

            // aus db auslesen
            AccountEntity entity = db.accountDao().getAccountById((int) id);

            if(entity != null) {

                textViewemailRP_display.setText(entity.getEmailRP());
                textViewpasswordRP_display.setText(entity.getPasswordRP());
                textViewfirstNameRP_display.setText(entity.getFirstNameRP());
                textViewlastNameRP_display.setText(entity.getLastNameRP());
            }


        }).start();

        Button button1 = findViewById(R.id.buttonDatenAendern);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(RisikopatientStartseite.this, RisikopatientDatenAendern.class);

                intent1.putExtra("id", id);

                startActivity(intent1);

            }
        });

        //Button um Daten von Notfallkontakt zu ändern
        Button Button2 = findViewById(R.id.buttonNotfallkontakt_aendern);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(RisikopatientStartseite.this, RisikopatientNotfallkontaktAendern.class);

                intent2.putExtra("id", id);

                startActivity(intent2);
            }
        });

        Button button_zumNotfallbutton = findViewById(R.id.button_zumNotfallbutton);

        button_zumNotfallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(RisikopatientStartseite.this, RisikopatientNotfallbutton.class);

                startActivity(intent1);
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
