package com.example.artifactreliquary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={User.class, Set.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO getUserDAO();
    public abstract SetDAO getSetDAO();

    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "Database").build();
                }
            }
        }
        return instance;
    }
}
