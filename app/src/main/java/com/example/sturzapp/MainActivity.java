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

    private EditText editTextemailRP;
    private EditText editTextpasswordRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();


        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailRP = editTextemailRP.getText().toString();
                String passwordRP = editTextpasswordRP.getText().toString();

                SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());
                //speichern

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AccountEntity entity = db.accountDao().getAccountByEmail(emailRP);

                        if (entity != null) {
                            String hashedPasswordRP = PasswordHasher.hashPassword(passwordRP);

                            if (hashedPasswordRP.equals(entity.getPasswordRP())) {
                                navigateToRisikopatientStartseite(entity.getId());
                            } else {
                                showToast("Ihr eingegebenes Passwort ist nicht richtig");
                            }
                        } else {
                            showToast("Ihr Account wurde nicht gefunden");
                        }
                    }
                }).start();
            }
        });

        Button button_accountErstellen = findViewById(R.id.button_createAccount);
        button_accountErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientErstellen();

            }
        });
        // Sturzerkennungs-Service starten
        Intent serviceIntent = new Intent(MainActivity.this, SturzerkennungsService.class);
            startService(serviceIntent);

    }

    private void initializeViews() {

        editTextemailRP = findViewById(R.id.editTextemailRP);
        editTextpasswordRP = findViewById(R.id.editTextpasswordRP);

    }

    private void navigateToRisikopatientStartseite(long accountId) {

        Intent intent2 = new Intent(MainActivity.this, RisikopatientStartseite.class);
        intent2.putExtra("id", (long) accountId);
        startActivity(intent2);

    }

    private void showToast(String message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void navigateToRisikopatientErstellen() {

        Intent intent = new Intent(MainActivity.this, RisikopatientErstellen.class);
        startActivity(intent);

    }


}
