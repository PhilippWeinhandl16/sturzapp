package com.example.sturzapp.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sturzapp.database.dao.AccountDao;
import com.example.sturzapp.database.entity.AccountEntity;

/**
 * Die Klasse {@code SturzappDatabase} repräsentiert die Room-Datenbank der Sturzmart-App
 * Sie enthält die Definition der Datenbank und die Zugriffsmethoden auf die DAOs
 */
@Database(entities = {AccountEntity.class}, version = 1)
public abstract class SturzappDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "sturzapp_db";

    private static SturzappDatabase instance;

    /**
     * Gibt das {@link AccountDao} zurück,
     * das für den Zugriff auf die Account-Entitäten in der Datenbank verwendet wird
     * @return das AccountDao-Objekt
     */
    public abstract AccountDao accountDao();

    /**
     * Gibt die einzige Instanz der Sturzapp-Datenbank zurück.
     * Wenn noch keine Instanz vorhanden ist, wird eine neue Instanz erstellt
     * @param context der Kontext der Anwendung
     * @return die Sturzapp-Datenbankinstanz
     */
    public static synchronized SturzappDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            SturzappDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
