package com.vltavasoft.coasters.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface CoasterDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAllCoasters(List<Coaster> coaster);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertCoaster(Coaster coaster);

    @Query("select * from coaster")
    List<Coaster> getCoasters();

    @Delete
    void deleteCoaster(Coaster coaster);

    @Query("delete from coaster where id = :id")
    void deleteCoasterById (int id);

    @Query("select * from coaster where id = :coasterId")
    Coaster getCoasterById(int coasterId);

    @Query("select * from coaster where name = :nameSearch")
    List<Coaster> getCoastersByName(String nameSearch);

}
