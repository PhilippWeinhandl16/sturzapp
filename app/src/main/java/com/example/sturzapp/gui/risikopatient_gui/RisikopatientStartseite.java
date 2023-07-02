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

/**
 * Die Klasse {@code RisikopatientStartseite} erweitert {@link AppCompatActivity} und
 * ist die Startseite für einen Risikopatienten
 */
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

        Intent intent = getIntent();
        boolean showData = intent.getBooleanExtra("SHOW_DATA", false);
        if (showData) {
            loadAccountInfo();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_startseite);


        Intent intent = getIntent();
        id = intent.getLongExtra("id", -1);

        initializeViews();
        loadAccountInfo();

        /**
         * Hier wird eine {@link SturzappDatabase} Instanz erstellt
         */
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

        Button button1 = findViewById(R.id.buttonDatenAendern);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientDatenAendern();
            }
        });

        /**
         * Mit dem {@code Button2} wird bei Klick die {@code navigateToRisikopatientNotfallkontaktAendern()}
         * Methode aufgerufen
         */
        Button Button2 = findViewById(R.id.buttonNotfallkontakt_aendern);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientNotfallkontaktAendern();
            }
        });

        /**
         * Mit dem {@code button_zumNotfallbutton} wird bei Klick die
         * {@code navigateToRisikopatientNotfallbutton()} Methode aufgerufen
         */
        Button button_zumNotfallbutton = findViewById(R.id.button_zumNotfallbutton);
        button_zumNotfallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientNotfallbutton();
            }
        });
    }

    /**
     * Mit der {@code initializeViews()} Methode werden die textView-Felder initialisiert
     */
    private void initializeViews() {
        textViewemailRP_display = findViewById(R.id.textViewemailRP_display);
        textViewfirstNameRP_display = findViewById(R.id.textViewfirstNameRP_display);
        textViewlastNameRP_display = findViewById(R.id.textViewlastNameRP_display);
    }

    /**
     * Die Methode {@code loadAccountInfo()} lädt, zeigt die Accountinformationen des Risikopatienten
     * in den textView-Feldern an und aktualisiert diese
     */
    private void loadAccountInfo() {

        /**
         * Hier wird eine {@link SturzappDatabase} Instanz erstellt
         */
        SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
        new Thread(() -> {
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

    /**
     * Die Methode {@code navigateToRisikopatientDatenAendern()} navigiert zur Aktivität
     * {@link RisikopatientDatenAendern}, um die Daten des Risikopatienten ändern zu können
     */
    private void navigateToRisikopatientDatenAendern() {
        Intent intent = new Intent(RisikopatientStartseite.this, RisikopatientDatenAendern.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /**
     * Die Methode {@code navigateToRisikopatientNotfallbutton()} navigiert zur Aktivität
     * {@link RisikopatientNotfallbutton}
     */
    private void navigateToRisikopatientNotfallbutton() {
        Intent intent1 = new Intent(RisikopatientStartseite.this, RisikopatientNotfallbutton.class);
        intent1.putExtra("id", id);
        startActivity(intent1);
    }

    /**
     * Die Methode {@code navigateToRisikopatientNotfallkontaktAendern()} navigiert zur Aktivität
     * {@code RisikopatientNotfallkontaktAendern}, in der man den Notfallkontakt ändern kann
     */
    private void navigateToRisikopatientNotfallkontaktAendern() {
        Intent intent2 = new Intent(RisikopatientStartseite.this, RisikopatientNotfallkontaktAendern.class);
        intent2.putExtra("id", id);
        startActivity(intent2);
    }
}
