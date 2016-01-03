package com.xjtu.sglab.shems.integration.resource.shi;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.WearableDeviceInfo;
import com.xjtu.sglab.shems.service.impl.OperateGpsService;
import com.xjtu.sglab.shems.service.impl.OperateWearableService;
import com.xjtu.sglab.shems.util.GsonUtil;

public class TestQueryGpsWearable {

	@Test
	@Ignore
	public void testGpsInfo() throws Exception{
		OperateGpsService operateGpsService = new OperateGpsService();
		GpsInfo gpsInfo = operateGpsService.queryGpsInfo();
		gpsInfo.setLocationType(null);
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(gpsInfo, GpsInfo.class);
		System.out.println(tmpstr);	
	}
	@Test
	public void testWearableInfo() throws Exception{
		OperateWearableService operateWearableService = new OperateWearableService();
		WearableDeviceInfo wearableDeviceInfo = operateWearableService.queryWearableInfo();
		wearableDeviceInfo.setMovingStatus(null);
		Gson gson = GsonUtil.create();
		String tmpstr = gson.toJson(wearableDeviceInfo, WearableDeviceInfo.class);
		System.out.println(tmpstr);	
	}
	
	
}
