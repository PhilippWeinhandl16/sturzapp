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

import com.example.sturzapp.database.AccountEntity;
import com.example.sturzapp.database.DaoSturzapp;
import com.example.sturzapp.database.DatabaseSturzapp;
import com.example.sturzapp.gui.risikopatient_gui.Risikopatient_Erstellen;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private DatabaseSturzapp database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Datenbankverbindung herstellen
        database = Room.databaseBuilder(getApplicationContext(), DatabaseSturzapp.class, "sturzapp-db")
                .build();

        // Ein Konto in die Datenbank einfügen
        AccountEntity account = new AccountEntity();
        insertAccount(account);

        // Alle Konten aus der Datenbank abrufen
        getAllAccounts();






        Button button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextemailRP = findViewById(R.id.editTextemailRP);
                EditText editTextpasswordRP = findViewById(R.id.editTextpasswordRP);

                String email = editTextemailRP.getText().toString();
                String password = editTextpasswordRP.getText().toString();

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

    private void insertAccount(AccountEntity account) {
        new Thread(() -> {
            DaoSturzapp dao = database.daoSturzapp();
            dao.insertAccount(account);
            Log.d(TAG, "Account inserted: " + account.getEmailRP());
            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Account inserted", Toast.LENGTH_SHORT).show());
        }).start();
    }

    private void getAllAccounts() {
        new Thread(() -> {
            DaoSturzapp dao = database.daoSturzapp();
            List<AccountEntity> accounts = dao.getAllAccounts();
            for (AccountEntity account : accounts) {
                Log.d(TAG, "Account: " + account.getEmailRP());
            }
        }).start();
    }
}
