package com.xjtu.sglab.shems.service;

import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;

public interface IOperateWearableService {
	public void saveWearableInfo(WearableDeviceInfo werableDeviceInfo) throws Exception;
	
	public WearableDeviceInfo queryWearableInfo() throws Exception;
}
