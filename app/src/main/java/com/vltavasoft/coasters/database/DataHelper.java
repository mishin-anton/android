package com.vltavasoft.coasters.database;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    private CoasterDatabase db;
    private CoasterDAO mCoasterDAO;

    public DataHelper() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db = AppDelegate.getInstance().getDatabase();
                mCoasterDAO = db.getCoasterDAO();
            }
        }).run();

    }

    public List<Coaster> getAllCoasters() {

        List<Coaster> coasters = new ArrayList<>();
        coasters = mCoasterDAO.getCoasters();

        return coasters;
    }

    public void insertNewCoaster(final Coaster coaster) {

        mCoasterDAO.insertCoaster(coaster);
    }

    public void deleteCoaster(Coaster coaster) {
        mCoasterDAO.deleteCoaster(coaster);
    }

    public void deleteCoasterById(int id) {

        mCoasterDAO.deleteCoasterById(id);
    }

}
