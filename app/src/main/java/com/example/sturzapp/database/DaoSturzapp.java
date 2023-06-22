package com.example.sturzapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import java.util.List;

@Dao
public interface DaoSturzapp {
    @Insert
    void insertAccount(AccountEntity account);

    @Delete
    void deleteAccount(AccountEntity account);

    @Query("SELECT * FROM accounts")
    List<AccountEntity> getAllAccounts();
}
