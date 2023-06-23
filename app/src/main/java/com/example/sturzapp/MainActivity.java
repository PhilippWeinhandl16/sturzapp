package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;
import com.example.sturzapp.gui.risikopatient_gui.Risikopatient_Erstellen;
import com.example.sturzapp.gui.risikopatient_gui.Risikopatient_Startseite;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextemailRP = findViewById(R.id.editTextemailRP);
                EditText editTextpasswordRP = findViewById(R.id.editTextpasswordRP);

                String email = editTextemailRP.getText().toString();
                String password = editTextpasswordRP.getText().toString();

                SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
                //speichern
                new Thread(() -> {
                    // Füge den Account in die Datenbank ein
                    AccountEntity entity = db.accountDao().getAccountByEmailAndPassword(email, password);

                    if(entity != null) {
                        System.out.println(entity.getId());
                        Intent intent2 = new Intent(MainActivity.this, Risikopatient_Startseite.class);
                        intent2.putExtra("id", (long) entity.getId());

                        startActivity(intent2);

                    } else {
                        Toast.makeText(getApplicationContext(), R.string.wrong_password_text, Toast.LENGTH_SHORT).show();
                    }

                }).start();

                Intent intent;

            }
        });

        Button button_accountErstellen = findViewById(R.id.button_createAccount);

        button_accountErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent  intent = new Intent(MainActivity.this, Risikopatient_Erstellen.class);

                    startActivity(intent);
        }
        });

        //Sturzerkennungs-Service starten
        Intent serviceIntent = new Intent(this, SturzerkennungsService.class);
        startService(serviceIntent);

    }

}
