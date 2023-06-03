package com.example.sturzapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO_Sturzapp {
    @Insert
    void insertAccount(Account_Entity account);

    @Delete
    void deleteAccount(Account_Entity account);

    @Query("SELECT * FROM accounts")
    List<Account_Entity> getAllAccounts();
}

