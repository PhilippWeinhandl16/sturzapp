package com.example.sturzapp.database;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sturzapp.database.AccountEntity;
import com.example.sturzapp.database.DaoSturzapp;
import com.example.sturzapp.R;

import java.util.List;

public class AccountsShow extends AppCompatActivity {
    private TextView accounts;  // TextView for account entries
    private DaoSturzapp daoSturzapp;  // DAO for database operations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikopatient_erstellen); // Set the content view using the R.layout.accountsshow reference

        accounts = findViewById(R.id.buttonCreateAccount);

        // Initialize your DaoSturzapp
        DatabaseSturzapp databaseSturzapp = DatabaseSturzapp.getInstance(getApplicationContext());
        daoSturzapp = databaseSturzapp.daoSturzapp();

        // Fetch account entries from the database and display them
        new GetAllAccountsAsyncTask().execute();
    }

    private class GetAllAccountsAsyncTask extends AsyncTask<Void, Void, List<AccountEntity>> {
        @Override
        protected List<AccountEntity> doInBackground(Void... voids) {
            return daoSturzapp.getAllAccounts();
        }

        @Override
        protected void onPostExecute(List<AccountEntity> accountsList) {
            super.onPostExecute(accountsList);

            StringBuilder accountsSb = new StringBuilder();
            for (AccountEntity account : accountsList) {
                accountsSb.append(account.toString()).append("\n");
            }
            accounts.setText(accountsSb.toString());
        }
    }
}
