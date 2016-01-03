package com.xjtu.sglab.shems.service;

import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.UserAddress;
import com.xjtu.sglab.shems.model.baidumap.ResultWithKey;

public interface IOperateGpsService {
	public void saveGpsInfo(GpsInfo gpsInfo) throws Exception;
	public UserAddress saveUserAddress(String address) throws Exception;
	public ResultWithKey searchLocation(String location) throws Exception;
	
	public GpsInfo queryGpsInfo() throws Exception;
}
