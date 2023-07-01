package com.example.sturzapp.gui.risikopatient_gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sturzapp.R;

public class RisikopatientNotfallbutton extends AppCompatActivity {


    private Button button_notfallbutton;
    private Button button_backToStartseite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_notfallbutton);

        initializeViews();
        setupButtonListeners();

    }
    public void initializeViews() {

        button_notfallbutton = findViewById(R.id.Button_Notfallbutton);
        button_backToStartseite = findViewById(R.id.Button_backToStartseite);
    }

    public void setupButtonListeners() {
        button_notfallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmergencyEmail();
            }
        });

        button_backToStartseite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientStartseite();
            }
        });

    }

    public void sendEmergencyEmail(){

        String [] emailNFK = {"daniel.manser2002@gmail.com"};
        String subject = "Notfallmeldung";
        String body = "Ihr Risikopatient hat einen Notfall ausgelöst! Womöglich benötigt er Hilfe";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, emailNFK);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(intent, "E-Mail senden"));

    }

    public void navigateToRisikopatientStartseite(){
        Intent intent = new Intent(RisikopatientNotfallbutton.this, RisikopatientStartseite.class);
        startActivity(intent);

    }

}