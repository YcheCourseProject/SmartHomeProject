package com.xjtu.sglab.exp;

import java.util.Date;

import com.google.gson.Gson;
import com.xjtu.sglab.gateway.entity.AirConditionStatus;
import com.xjtu.sglab.gateway.util.GsonUtil;
import com.xjtu.sglab.gateway.withserver.ApplianceComm;

public class Exp {

	public static void main(String[]args){
		Gson gson=GsonUtil.create();
		AirConditionStatus[] airConditionStatus=ApplianceComm.getInstance().queryAirConditionStatus(1,2);
		System.out.println(airConditionStatus[0].getAirConditionStatusRecordTime().getTime());
		System.out.println(System.currentTimeMillis());
		
	}
}
