package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.sturzapp.PasswordHasher;
import com.example.sturzapp.R;
import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;


public class RisikopatientStartseite extends AppCompatActivity {


    public TextView textViewemailRP_display;
    public TextView textViewfirstNameRP_display;
    public TextView textViewlastNameRP_display;
    public long id;

    @Override
    protected void onResume() {
        super.onResume();

        initializeViews();
        loadAccountInfo();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);

        // Daten aus dem Intent abrufen
        Intent intent = getIntent();
        id = intent.getLongExtra("id", -1);

        initializeViews();
        loadAccountInfo();


        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());


        Button button1 = findViewById(R.id.buttonDatenAendern);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientDatenAendern();

            }
        });

        //Button um Daten von Notfallkontakt zu ändern
        Button Button2 = findViewById(R.id.buttonNotfallkontakt_aendern);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientNotfallkontaktAendern();

            }
        });


        Button button_zumNotfallbutton = findViewById(R.id.button_zumNotfallbutton);
        button_zumNotfallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientNotfallbutton();

            }
        });
    }

    private void initializeViews() {

        textViewemailRP_display = findViewById(R.id.textViewemailRP_display);
        textViewfirstNameRP_display = findViewById(R.id.textViewfirstNameRP_display);
        textViewlastNameRP_display = findViewById(R.id.textViewlastNameRP_display);

    }

    private void loadAccountInfo() {
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
        new Thread(() -> {
            // aus db auslesen
            AccountEntity entity = db.accountDao().getAccountById((int) id);

            runOnUiThread(new Thread(() -> {
                if (entity != null) {
                    textViewemailRP_display.setText(entity.getEmailRP());
                    textViewfirstNameRP_display.setText(entity.getFirstNameRP());
                    textViewlastNameRP_display.setText(entity.getLastNameRP());
                }
            }));



        }).start();

    }

    private void navigateToRisikopatientDatenAendern() {

        Intent intent = new Intent(RisikopatientStartseite.this, RisikopatientDatenAendern.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }

    private void navigateToRisikopatientNotfallbutton() {

        Intent intent1 = new Intent(RisikopatientStartseite.this, RisikopatientNotfallbutton.class);
        startActivity(intent1);

    }

    private void navigateToRisikopatientNotfallkontaktAendern() {

        Intent intent2 = new Intent(RisikopatientStartseite.this, RisikopatientNotfallkontaktAendern.class);
        intent2.putExtra("id", id);
        startActivity(intent2);

    }


}
