package com.xjtu.sglab.shems.model.baidumap;

import com.google.gson.annotations.SerializedName;


public class MapResult{

    private static final String FIELD_UID = "uid";
    private static final String FIELD_DETAIL = "detail";
    private static final String FIELD_TELEPHONE = "telephone";
    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_DETAIL_INFO = "detail_info";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_STREET_ID = "street_id";


    @SerializedName(FIELD_UID)
    private String mUid;
    @SerializedName(FIELD_DETAIL)
    private int mDetail;
    @SerializedName(FIELD_TELEPHONE)
    private String mTelephone;
    @SerializedName(FIELD_LOCATION)
    private MapLocation mLocation;
    @SerializedName(FIELD_DETAIL_INFO)
    private DetailInfo mDetailInfo;
    @SerializedName(FIELD_NAME)
    private String mName;
    @SerializedName(FIELD_ADDRESS)
    private String mAddress;
    @SerializedName(FIELD_STREET_ID)
    private String mStreetId;


    public MapResult(){

    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public String getUid() {
        return mUid;
    }

    public void setDetail(int detail) {
        mDetail = detail;
    }

    public int getDetail() {
        return mDetail;
    }

    public void setTelephone(String telephone) {
        mTelephone = telephone;
    }

    public String getTelephone() {
        return mTelephone;
    }

    public void setLocation(MapLocation location) {
        mLocation = location;
    }

    public MapLocation getLocation() {
        return mLocation;
    }

    public void setDetailInfo(DetailInfo detailInfo) {
        mDetailInfo = detailInfo;
    }

    public DetailInfo getDetailInfo() {
        return mDetailInfo;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setStreetId(String streetId) {
        mStreetId = streetId;
    }

    public String getStreetId() {
        return mStreetId;
    }

    @Override
    public String toString(){
        return "uid = " + mUid + ", detail = " + mDetail + ", telephone = " + mTelephone + ", location = " + mLocation + ", detailInfo = " + mDetailInfo + ", name = " + mName + ", address = " + mAddress + ", streetId = " + mStreetId;
    }


}