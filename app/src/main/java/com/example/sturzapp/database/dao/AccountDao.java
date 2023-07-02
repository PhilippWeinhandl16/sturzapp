package com.example.sturzapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sturzapp.database.entity.AccountEntity;

import java.util.List;

/**
 * Das DAO  {@code AccountDao} bietet Methoden zum Zugriff auf die Account-Entitäten in der Datenbank an.
 * Es definiert die erforderlichen Operationen für das
 * Einfügen, Aktualisieren, Löschen und Abfragen von Account-Entitäten.
 */
@Dao
public interface AccountDao {

    /**
     * Fügt einene neuen Account in die {@link com.example.sturzapp.database.SturzappDatabase} ein
     * @param account ist der einzufügende Account
     * @return die ID des eingefügten Accounts
     */
    @Insert
    long insert(AccountEntity account);

    /**
     * Aktualisiert einen bestehenden Account aus der {@link com.example.sturzapp.database.SturzappDatabase}
     * @param account ist der zu aktualisierende Account
     */
    @Update
    void update(AccountEntity account);


    /**
     * Löscht einen bestehenden Account aus der {@link com.example.sturzapp.database.SturzappDatabase}
     * @param account  ist der zu löschende Account
     */
    @Delete
    void delete(AccountEntity account);


    /**
     * Ruft alle Accounts aus der Datenbank ab
     * @return liefert eine Liste aller Accounts zurück
     */
    @Query("SELECT * FROM accounts")
    List<AccountEntity> getAllAccounts();

    /**
     * Ruft einen Account anhand seiner ID aus der {@link com.example.sturzapp.database.SturzappDatabase} ab
     * @param accountId  ist die ID des gesuchten Accounts
     * @return der gesuchte Account oder null wenn kein Account gefunden wurde
     */
    @Query("SELECT * FROM accounts WHERE id = :accountId")
    AccountEntity getAccountById(int accountId);

    /**
     * Ruft einen Account anhand seiner E-Mail-Adresse aus der
     * {@link com.example.sturzapp.database.SturzappDatabase} ab
     * @param email  ist die Email-Adresse des gesuchten Accounts
     * @return der gesuchte Account oder null wenn kein Account gefunden wurde
     */
    @Query("SELECT * FROM accounts WHERE emailRP = :email")
    AccountEntity getAccountByEmail(String email);


}


