package com.vltavasoft.coasters.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Coaster {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    public int mId;

    @ColumnInfo(name = "name")
    protected String mName;

    @ColumnInfo(name = "shape")
    protected String mShape;

    @ColumnInfo(name = "country")
    protected String mCountry;

    @ColumnInfo(name = "city")
    protected String mCity;

    @ColumnInfo(name = "imgFront")
    protected String mImgFrontUrl;

    @ColumnInfo(name = "imgBack")
    protected String mImgBackUrl;

    public Coaster(int mId, String mName, String mShape, String mCountry, String mCity, String mImgFrontUrl, String mImgBackUrl) {
        this.mId = mId;
        this.mName = mName;
        this.mShape = mShape;
        this.mCountry = mCountry;
        this.mCity = mCity;
        this.mImgFrontUrl = mImgFrontUrl;
        this.mImgBackUrl = mImgBackUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getShape() {
        return mShape;
    }

    public void setShape(String mShape) {
        this.mShape = mShape;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getImgFrontUrl() {
        return mImgFrontUrl;
    }

    public void setmImgFrontUrl(String mImgFrontUrl) {
        this.mImgFrontUrl = mImgFrontUrl;
    }

    public String getImgBackUrl() {
        return mImgBackUrl;
    }

    public void setImgBackUrl(String mImgBackUrl) {
        this.mImgBackUrl = mImgBackUrl;
    }

    @Override
    public String toString() {
        return mId + " " + mName + mCity + mShape + mImgBackUrl + mImgFrontUrl;
    }
}
