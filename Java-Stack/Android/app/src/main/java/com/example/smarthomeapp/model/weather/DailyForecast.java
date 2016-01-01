package com.example.smarthomeapp.model.weather;

import com.google.gson.annotations.SerializedName;


public class DailyForecast{

    private static final String FIELD_WIND = "wind";
    private static final String FIELD_VIS = "vis";
    private static final String FIELD_PCPN = "pcpn";
    private static final String FIELD_COND = "cond";
    private static final String FIELD_POP = "pop";
    private static final String FIELD_HUM = "hum";
    private static final String FIELD_TMP = "tmp";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_ASTRO = "astro";
    private static final String FIELD_PRES = "pres";


    @SerializedName(FIELD_WIND)
    private Wind mWind;
    @SerializedName(FIELD_VIS)
    private int mVi;
    @SerializedName(FIELD_PCPN)
    private double mPcpn;
    @SerializedName(FIELD_COND)
    private Cond mCond;
    @SerializedName(FIELD_POP)
    private int mPop;
    @SerializedName(FIELD_HUM)
    private int mHum;
    @SerializedName(FIELD_TMP)
    private Tmp mTmp;
    @SerializedName(FIELD_DATE)
    private String mDate;
    @SerializedName(FIELD_ASTRO)
    private Astro mAstro;
    @SerializedName(FIELD_PRES)
    private int mPre;


    public DailyForecast(){

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

    public void setPcpn(double pcpn) {
        mPcpn = pcpn;
    }

    public double getPcpn() {
        return mPcpn;
    }

    public void setCond(Cond cond) {
        mCond = cond;
    }

    public Cond getCond() {
        return mCond;
    }

    public void setPop(int pop) {
        mPop = pop;
    }

    public int getPop() {
        return mPop;
    }

    public void setHum(int hum) {
        mHum = hum;
    }

    public int getHum() {
        return mHum;
    }

    public void setTmp(Tmp tmp) {
        mTmp = tmp;
    }

    public Tmp getTmp() {
        return mTmp;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDate() {
        return mDate;
    }

    public void setAstro(Astro astro) {
        mAstro = astro;
    }

    public Astro getAstro() {
        return mAstro;
    }

    public void setPre(int pre) {
        mPre = pre;
    }

    public int getPre() {
        return mPre;
    }

    @Override
    public String toString(){
        return "wind = " + mWind + ", vi = " + mVi + ", pcpn = " + mPcpn + ", cond = " + mCond + ", pop = " + mPop + ", hum = " + mHum + ", tmp = " + mTmp + ", date = " + mDate + ", astro = " + mAstro + ", pre = " + mPre;
    }


}