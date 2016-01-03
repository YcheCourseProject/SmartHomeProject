package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class Suggestion{

    private static final String FIELD_FLU = "flu";
    private static final String FIELD_CW = "cw";
    private static final String FIELD_DRSG = "drsg";
    private static final String FIELD_COMF = "comf";
    private static final String FIELD_UV = "uv";
    private static final String FIELD_SPORT = "sport";
    private static final String FIELD_TRAV = "trav";


    @SerializedName(FIELD_FLU)
    private Flu mFlu;
    @SerializedName(FIELD_CW)
    private Cw mCw;
    @SerializedName(FIELD_DRSG)
    private Drsg mDrsg;
    @SerializedName(FIELD_COMF)
    private Comf mComf;
    @SerializedName(FIELD_UV)
    private Uv mUv;
    @SerializedName(FIELD_SPORT)
    private Sport mSport;
    @SerializedName(FIELD_TRAV)
    private Trav mTrav;


    public Suggestion(){

    }

    public void setFlu(Flu flu) {
        mFlu = flu;
    }

    public Flu getFlu() {
        return mFlu;
    }

    public void setCw(Cw cw) {
        mCw = cw;
    }

    public Cw getCw() {
        return mCw;
    }

    public void setDrsg(Drsg drsg) {
        mDrsg = drsg;
    }

    public Drsg getDrsg() {
        return mDrsg;
    }

    public void setComf(Comf comf) {
        mComf = comf;
    }

    public Comf getComf() {
        return mComf;
    }

    public void setUv(Uv uv) {
        mUv = uv;
    }

    public Uv getUv() {
        return mUv;
    }

    public void setSport(Sport sport) {
        mSport = sport;
    }

    public Sport getSport() {
        return mSport;
    }

    public void setTrav(Trav trav) {
        mTrav = trav;
    }

    public Trav getTrav() {
        return mTrav;
    }

    @Override
    public String toString(){
        return "flu = " + mFlu + ", cw = " + mCw + ", drsg = " + mDrsg + ", comf = " + mComf + ", uv = " + mUv + ", sport = " + mSport + ", trav = " + mTrav;
    }


}