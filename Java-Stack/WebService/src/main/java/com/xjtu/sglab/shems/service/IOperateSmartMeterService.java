package com.xjtu.sglab.shems.service;

import java.util.Set;

import com.xjtu.sglab.shems.entity.ElectricityInfo;
import com.xjtu.sglab.shems.entity.ElectricityMeter;
import com.xjtu.sglab.shems.entity.LightSensorData;


public interface IOperateSmartMeterService {
	public void saveSmartMeterInfo(ElectricityInfo electricityInfo) throws Exception;
	
	public ElectricityInfo queryElectricityInfo(int ElectricityMeterID) throws Exception;
	public ElectricityInfo[] queryElectricityInfoEveryHour() throws Exception;
			
	public ElectricityInfo[] queryElectricityInfoArray(Set<Integer> electricityMeterIDSet) throws Exception;
	
}
