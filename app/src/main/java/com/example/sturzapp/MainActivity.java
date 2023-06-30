package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;
import com.example.sturzapp.gui.risikopatient_gui.RisikopatientErstellen;
import com.example.sturzapp.gui.risikopatient_gui.RisikopatientStartseite;

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

                String emailRP = editTextemailRP.getText().toString();
                String passwordRP = editTextpasswordRP.getText().toString();

                SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
                //speichern
                new Thread(() -> {
                    // Füge den Account in die Datenbank ein
                    AccountEntity entity = db.accountDao().getAccountByEmail(emailRP);


                    if (entity != null) {

                        String hashedPasswordRP = PasswordHasher.hashPassword(passwordRP);

                        if (hashedPasswordRP.equals(entity.getPasswordRP())) {
                            Intent intent2 = new Intent(MainActivity.this, RisikopatientStartseite.class);
                            intent2.putExtra("id", (long) entity.getId());
                            startActivity(intent2);
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Ihr eingegebenes Passwort ist nicht richtig", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Ihr Account wurde nicht gefunden", Toast.LENGTH_SHORT).show();
                            }
                        });

                        }


                }).start();
            }
        });

        Button button_accountErstellen = findViewById(R.id.button_createAccount);

        button_accountErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RisikopatientErstellen.class);
                startActivity(intent);
            }
        });

        // Sturzerkennungs-Service starten
        Intent serviceIntent = new Intent(MainActivity.this, SturzerkennungsService.class);
            startService(serviceIntent);

    }
}
