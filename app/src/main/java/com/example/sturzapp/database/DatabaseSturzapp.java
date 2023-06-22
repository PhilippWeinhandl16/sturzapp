package com.example.sturzapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AccountEntity.class}, version = 1)
public abstract class DatabaseSturzapp extends RoomDatabase {
    public abstract DaoSturzapp daoSturzapp();
    private static volatile DatabaseSturzapp sturzDBInstance;


    public static DatabaseSturzapp getInstance(Context context){
        if(sturzDBInstance == null){
            synchronized (DatabaseSturzapp.class){
                if(sturzDBInstance == null){
                    sturzDBInstance = Room.databaseBuilder(context, DatabaseSturzapp.class, "sturzapp_db").build();
                }
            }
        }
        return (sturzDBInstance);
    }
}

