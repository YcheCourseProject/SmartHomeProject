package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class Now{

    private static final String FIELD_WIND = "wind";
    private static final String FIELD_VIS = "vis";
    private static final String FIELD_PCPN = "pcpn";
    private static final String FIELD_COND = "cond";
    private static final String FIELD_HUM = "hum";
    private static final String FIELD_TMP = "tmp";
    private static final String FIELD_FL = "fl";
    private static final String FIELD_PRES = "pres";


    @SerializedName(FIELD_WIND)
    private Wind mWind;
    @SerializedName(FIELD_VIS)
    private int mVi;
    @SerializedName(FIELD_PCPN)
    private int mPcpn;
    @SerializedName(FIELD_COND)
    private Cond mCond;
    @SerializedName(FIELD_HUM)
    private int mHum;
    @SerializedName(FIELD_TMP)
    private int mTmp;
    @SerializedName(FIELD_FL)
    private int mFl;
    @SerializedName(FIELD_PRES)
    private int mPre;


    public Now(){

    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setVi(int vi) {
        mVi = vi;
    }

    public int getVi() {
        return mVi;
    }

    public void setPcpn(int pcpn) {
        mPcpn = pcpn;
    }

    public int getPcpn() {
        return mPcpn;
    }

    public void setCond(Cond cond) {
        mCond = cond;
    }

    public Cond getCond() {
        return mCond;
    }

    public void setHum(int hum) {
        mHum = hum;
    }

    public int getHum() {
        return mHum;
    }

    public void setTmp(int tmp) {
        mTmp = tmp;
    }

    public int getTmp() {
        return mTmp;
    }

    public void setFl(int fl) {
        mFl = fl;
    }

    public int getFl() {
        return mFl;
    }

    public void setPre(int pre) {
        mPre = pre;
    }

    public int getPre() {
        return mPre;
    }

    @Override
    public String toString(){
        return "wind = " + mWind + ", vi = " + mVi + ", pcpn = " + mPcpn + ", cond = " + mCond + ", hum = " + mHum + ", tmp = " + mTmp + ", fl = " + mFl + ", pre = " + mPre;
    }


}