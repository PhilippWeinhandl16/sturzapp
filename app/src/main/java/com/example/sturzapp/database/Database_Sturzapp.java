package com.example.sturzapp.database;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Account_Entity.class}, version = 1)
public abstract class Database_Sturzapp extends RoomDatabase {



    public abstract DAO_Sturzapp Dao();
}

