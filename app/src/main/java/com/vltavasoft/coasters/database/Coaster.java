package com.vltavasoft.coasters.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Coaster {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int mId;

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

    public Coaster(String name, String shape, String country, String city, String imgFrontUrl, String imgBackUrl) {
        this.mName = name;
        this.mShape = shape;
        this.mCountry = country;
        this.mCity = city;
        this.mImgFrontUrl = imgFrontUrl;
        this.mImgBackUrl = imgBackUrl;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getmShape() {
        return mShape;
    }

    public void setmShape(String mShape) {
        this.mShape = mShape;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmImgFrontUrl() {
        return mImgFrontUrl;
    }

    public void setmImgFrontUrl(String mImgFrontUrl) {
        this.mImgFrontUrl = mImgFrontUrl;
    }

    public String getmImgBackUrl() {
        return mImgBackUrl;
    }

    public void setmImgBackUrl(String mImgBackUrl) {
        this.mImgBackUrl = mImgBackUrl;
    }

    @Override
    public String toString() {
        return mId + " " + mName + mCity + mShape + mImgBackUrl + mImgFrontUrl;
    }
}
