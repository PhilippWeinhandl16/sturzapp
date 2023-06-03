package com.example.sturzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sturzapp.database.Account_Entity;
import com.example.sturzapp.database.DAO_Sturzapp;
import com.example.sturzapp.database.Database_Sturzapp;
import com.example.sturzapp.gui.notfallkontakt_gui.Notfallkontakt_Erstellen;
import com.example.sturzapp.gui.risikopatient_gui.Risikopatient_Erstellen;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Database_Sturzapp database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Datenbankverbindung herstellen
        database = Room.databaseBuilder(getApplicationContext(), Database_Sturzapp.class, "sturzapp-db")
                .build();

        // Ein Konto in die Datenbank einfügen
        Account_Entity account = new Account_Entity("email@example.com", "password", "John", "Doe",
                "123 Main Street", "City", "Emergency Contact", "Contact Address", "Contact Zip",
                "contact@example.com");
        insertAccount(account);

        // Alle Konten aus der Datenbank abrufen
        getAllAccounts();

        RadioGroup radioGroup_account_auswahl1 = findViewById(R.id.radioGroup_account_auswahl1);

        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_emailadresse = findViewById(R.id.editText_Email);
                EditText editText_passwort = findViewById(R.id.editTextPassword);

                String email_adresse = editText_emailadresse.getText().toString();
                String passwort = editText_passwort.getText().toString();

                int selected_RadioButton = radioGroup_account_auswahl1.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selected_RadioButton);
                String ausgewaehlterRadioButtonText = radioButton.getText().toString();

                Intent intent;

            }
        });

        RadioButton risikopatient2 = findViewById(R.id.radioButtonRiskPatient2);
        RadioButton notfallkontakt2 = findViewById(R.id.radioButton_Notfallkontakt2);

        Button button_accountErstellen = findViewById(R.id.button_createAccount);

        button_accountErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup_account_auswahl2 = findViewById(R.id.radioGroup_account_auswahl2);
                int selected_RadioButton = radioGroup_account_auswahl2.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selected_RadioButton);

                if (radioButton != null) {
                    String ausgewaehlterRadioButtonText = radioButton.getText().toString();

                    Intent intent;

                    if (ausgewaehlterRadioButtonText.equals("Risikopatient")) {
                        intent = new Intent(MainActivity.this, Risikopatient_Erstellen.class);
                    } else if (ausgewaehlterRadioButtonText.equals("Notfallkontakt")) {
                        intent = new Intent(MainActivity.this, Notfallkontakt_Erstellen.class);
                    } else {
                        intent = new Intent(MainActivity.this, DefaultActivity.class);
                    }

                    startActivity(intent);
                } else {
                    // Wenn kein RadioButton ausgewählt ist
                    Toast.makeText(MainActivity.this, "Bitte wählen Sie eine Option aus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertAccount(Account_Entity account) {
        new Thread(() -> {
            DAO_Sturzapp dao = database.Dao();
            dao.insertAccount(account);
            Log.d(TAG, "Account inserted: " + account.getEmail());
            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Account inserted", Toast.LENGTH_SHORT).show());
        }).start();
    }

    private void getAllAccounts() {
        new Thread(() -> {
            DAO_Sturzapp dao = database.Dao();
            List<Account_Entity> accounts = dao.getAllAccounts();
            for (Account_Entity account : accounts) {
                Log.d(TAG, "Account: " + account.getEmail());
            }
        }).start();
    }
}
