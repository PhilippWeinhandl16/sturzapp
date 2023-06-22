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






        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextemailRP = findViewById(R.id.editTextemailRP);
                EditText editTextpasswordRP = findViewById(R.id.editTextpasswordRP);

                String email_adresse = editTextemailRP.getText().toString();
                String passwort = editTextpasswordRP.getText().toString();

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
