package com.xjtu.sglab.shems.service;

import java.util.Set;

import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.AirConditionStatus;
import com.xjtu.sglab.shems.entity.Curtain;
import com.xjtu.sglab.shems.entity.CurtainStatus;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LampStatus;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.SheSwitch;
import com.xjtu.sglab.shems.entity.SheSwitchStatus;
import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.entity.WaterHeaterStatus;

public interface IOperateApplianceService {
	public void saveLampInfo(LampStatus lampStatus) throws Exception;
	public void saveCurtainInfo(CurtainStatus curtainStatus) throws Exception;
	public void saveSheSwitchInfo(SheSwitchStatus sheSwitchStatus) throws Exception;
	public void saveAirConditionInfo(AirConditionStatus airConditionStatus) throws Exception;
	public void saveWaterHeaterInfo(WaterHeaterStatus waterHeaterStatus) throws Exception;
	
	public LampStatus queryLampStatus(int LampID) throws Exception;
	public CurtainStatus queryCurtainStatus(int CurtainID) throws Exception;
	public SheSwitchStatus querySheSwitchStatus(int SheSwitchID) throws Exception;
	public AirConditionStatus queryAirConditionStatus(int AirConditionID) throws Exception;
	public WaterHeaterStatus queryWaterHeaterStatus(int WaterHeaterID) throws Exception;
	
	public LampStatus[] queryLampStatusArray(Set<Integer> LampIDSet) throws Exception;
	public CurtainStatus[] queryCurtainStatusArray(Set<Integer>  CurtainIDSet) throws Exception;
	public SheSwitchStatus[] querySheSwitchStatusArray(Set<Integer>  SheSwitchIDSet) throws Exception;
	public AirConditionStatus[] queryAirConditionStatusArray(Set<Integer>  AirConditionIDSet) throws Exception;
	public WaterHeaterStatus[] queryWaterHeaterStatusArray(Set<Integer>  WaterHeaterIDSet) throws Exception;
	

}
