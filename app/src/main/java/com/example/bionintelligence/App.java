package com.example.bionintelligence;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.bionintelligence.data.database.AppDatabase;

public class App extends Application {
    public static App instance;
    private AppDatabase database;
    public static final int DATABASE_VERSION = 2;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "appDatabase")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
