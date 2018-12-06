package com.vltavasoft.coasters.database;

import android.app.Application;
import android.arch.persistence.room.Room;

public class AppDelegate extends Application {

    public static AppDelegate instance;
    private CoasterDatabase mCoasterDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mCoasterDatabase = Room.databaseBuilder(this, CoasterDatabase.class,
                "coaster_database")
                .allowMainThreadQueries() //выключить дальше и перейти в параллельный поток
                .build();
    }

    public static AppDelegate getInstance() {
        return instance;
    }

    public CoasterDatabase getDatabase() {
        return mCoasterDatabase;
    }

}
