package com.example.sturzapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sturzapp.database.entity.AccountEntity;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    long insert(AccountEntity account);

    @Update
    void update(AccountEntity account);

    @Delete
    void delete(AccountEntity account);

    @Query("SELECT * FROM accounts")
    List<AccountEntity> getAllAccounts();

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    AccountEntity getAccountById(int accountId);

    @Query("SELECT * FROM accounts WHERE emailRP = :email AND passwordRP = :password")
    AccountEntity getAccountByEmailAndPassword(String email, String password);

}


