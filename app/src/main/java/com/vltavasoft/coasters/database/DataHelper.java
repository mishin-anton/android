package com.vltavasoft.coasters.database;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    private CoasterDatabase db;
    private CoasterDAO mCoasterDAO;

    public DataHelper() {
        db = AppDelegate.getInstance().getDatabase();
        mCoasterDAO = db.getCoasterDAO();
    }

    public List<Coaster> getAllCoasters() {

        List<Coaster> coasters = new ArrayList<>();
        coasters = mCoasterDAO.getCoasters();

        return coasters;
    }

    public void insertNewCoaster(final Coaster coaster) {

        mCoasterDAO.insertCoaster(coaster);
    }

    public void deleteCoasterById(int id) {

        mCoasterDAO.deleteCoasterById(id);
    }


}
