package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sturzapp.database.SturzappDatabase;
import com.example.sturzapp.database.entity.AccountEntity;
import com.example.sturzapp.gui.risikopatient_gui.RisikopatientErstellen;
import com.example.sturzapp.gui.risikopatient_gui.RisikopatientStartseite;

/**
 * Die {@code MainActivity} ist die Einstiegsaktivität der Sturzapp
 * Der Benutzer kann sich in dieser anmelden, um auf die {@link RisikopatientStartseite} zu gelangen
 * Der Benutzer kann auch einen neuen Risikopatienten-Account erstellen
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextemailRP;
    private EditText editTextpasswordRP;

    /**
     * Die Methode {@starteSturzerkennungsService()} startet den SturzerkennungsService
     */
    private void starteSturzerkennungsService() {
        Intent serviceIntent = new Intent(this, SturzerkennungsService.class);
        startService(serviceIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        starteSturzerkennungsService();

        /**
         * Der Button {@code button_login} wird hier instanziert
         */
        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailRP = editTextemailRP.getText().toString();
                String passwordRP = editTextpasswordRP.getText().toString();

                /**
                 * Hier wird eine {@link SturzappDatabase} Instanz erstellt
                 */
                SturzappDatabase db = SturzappDatabase.getInstance(getApplicationContext());

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

        /**
         * Der Button {@code button_accountErstellen} wird hier instanziert
         */
        Button button_accountErstellen = findViewById(R.id.button_createAccount);
        button_accountErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRisikopatientErstellen();
            }
        });
    }

    /**
     * Die {@code initializeViews()} Methode initialisiert die editText-Elemente der Aktivität
     */
    private void initializeViews() {
        editTextemailRP = findViewById(R.id.editTextemailRP);
        editTextpasswordRP = findViewById(R.id.editTextpasswordRP);
    }

    /**
     * Navigiert zur Startseite des Risikopatienten mit der gegebenen Account-ID
     * @param accountId Die ID des Risikopatienten-Accounts
     */
    private void navigateToRisikopatientStartseite(long accountId) {
        Intent intent2 = new Intent(MainActivity.this, RisikopatientStartseite.class);
        intent2.putExtra("id", accountId);
        startActivity(intent2);
    }

    /**
     * Zeigt eine Toast-Nachricht mit der gegebenen Nachricht an
     *
     * @param message Die Nachricht, die angezeigt werden soll
     */
    private void showToast(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Navigiert zur {@link RisikopatientErstellen} Aktivität zum Erstellen eines Risikopatienten-Accounts
     */
    private void navigateToRisikopatientErstellen() {
        Intent intent = new Intent(MainActivity.this, RisikopatientErstellen.class);
        startActivity(intent);
    }
}
