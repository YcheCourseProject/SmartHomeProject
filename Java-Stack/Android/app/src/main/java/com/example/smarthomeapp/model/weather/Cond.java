package com.example.smarthomeapp.model.weather;

import com.google.gson.annotations.SerializedName;


public class Cond{

    private static final String FIELD_CODE = "code";
    private static final String FIELD_CODE_N = "code_n";
    private static final String FIELD_TXT_D = "txt_d";
    private static final String FIELD_TXT = "txt";
    private static final String FIELD_CODE_D = "code_d";
    private static final String FIELD_TXT_N = "txt_n";


    @SerializedName(FIELD_CODE)
    private int mCode;
    @SerializedName(FIELD_CODE_N)
    private int mCodeN;
    @SerializedName(FIELD_TXT_D)
    private String mTxtD;
    @SerializedName(FIELD_TXT)
    private String mTxt;
    @SerializedName(FIELD_CODE_D)
    private int mCodeD;
    @SerializedName(FIELD_TXT_N)
    private String mTxtN;


    public Cond(){

    }

    public void setCode(int code) {
        mCode = code;
    }

    public int getCode() {
        return mCode;
    }

    public void setCodeN(int codeN) {
        mCodeN = codeN;
    }

    public int getCodeN() {
        return mCodeN;
    }

    public void setTxtD(String txtD) {
        mTxtD = txtD;
    }

    public String getTxtD() {
        return mTxtD;
    }

    public void setTxt(String txt) {
        mTxt = txt;
    }

    public String getTxt() {
        return mTxt;
    }

    public void setCodeD(int codeD) {
        mCodeD = codeD;
    }

    public int getCodeD() {
        return mCodeD;
    }

    public void setTxtN(String txtN) {
        mTxtN = txtN;
    }

    public String getTxtN() {
        return mTxtN;
    }

    @Override
    public String toString(){
        return "code = " + mCode + ", codeN = " + mCodeN + ", txtD = " + mTxtD + ", txt = " + mTxt + ", codeD = " + mCodeD + ", txtN = " + mTxtN;
    }


}