package com.vltavasoft.coasters.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Coaster.class}, version = 1)
public abstract class CoasterDatabase extends RoomDatabase {

        public abstract CoasterDAO getCoasterDAO();
}
