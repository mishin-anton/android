package com.vltavasoft.coasters.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Coaster {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "shape")
    private String shape;

    @ColumnInfo(name = "country")
    private String mCountry;

    @ColumnInfo(name = "city")
    private String mCity;

    @ColumnInfo(name = "imgFront")
    private String mImgFrontUrl;

    @ColumnInfo(name = "imgBack")
    private String mImgBackUrl;

    public Coaster(int id, String name, String shape, String country, String city, String imgFrontUrl, String imgBackUrl) {
        this.mId = id;
        this.name = name;
        this.shape = shape;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getmShape() {
        return shape;
    }

    public void setmShape(String mShape) {
        this.shape = mShape;
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
}
