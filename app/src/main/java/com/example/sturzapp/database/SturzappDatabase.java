package com.example.sturzapp.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sturzapp.database.dao.AccountDao;
import com.example.sturzapp.database.entity.AccountEntity;

@Database(entities = {AccountEntity.class}, version = 1)
public abstract class SturzappDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "sturzapp_db";

    private static SturzappDatabase instance;

    public abstract AccountDao accountDao();

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
