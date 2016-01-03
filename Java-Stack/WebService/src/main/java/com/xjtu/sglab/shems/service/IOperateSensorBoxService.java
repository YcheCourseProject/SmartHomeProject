package com.xjtu.sglab.shems.service;

import java.util.Set;

import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.FlameSensorData;
import com.xjtu.sglab.shems.entity.GasSensorData;
import com.xjtu.sglab.shems.entity.HumidityData;
import com.xjtu.sglab.shems.entity.LightSensorData;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.entity.TemperatureSensorData;

public interface IOperateSensorBoxService {
	 
	public void saveSensorBoxInfo(Box box) throws Exception;
	public void saveFlameSensorDataInfo(FlameSensorData flameSensorData) throws Exception;
	public void saveLightSensorDataInfo(LightSensorData lightSensorData) throws Exception;
	public void saveGasSensorDataInfo(GasSensorData gasSensorData) throws Exception;
	public void savePlrSensorDataInfo(PlrSensorData plrSensorData) throws Exception;
	public void saveTemperatureSensorDataInfo(TemperatureSensorData temperatureSensorData) throws Exception;
	public void saveHumidityDataInfo(HumidityData humidityData) throws Exception;
	
	public LightSensorData queryLightSensorData(int LightSensorID) throws Exception;
	public FlameSensorData queryFlameSensorData(int FlameSensorID) throws Exception;
	public GasSensorData queryGasSensorData(int GasSensorID) throws Exception;
	public PlrSensorData queryPlrSensorData(int PlrSensorID) throws Exception;
	public TemperatureSensorData queryTemperatureSensorData(int TemperatureSensorID) throws Exception;
	public HumidityData queryHumidityData(int TemperatureSensorID) throws Exception;
	
	public LightSensorData[] queryLightSensorDataSet(Set<Integer> lightSensorIDSet) throws Exception;
	public FlameSensorData[] queryFlameSensorDataSet(Set<Integer> flameSensorIDSet) throws Exception;
	public GasSensorData[] queryGasSensorDataSet(Set<Integer> gasSensorIDSet) throws Exception;
	public PlrSensorData[] queryPlrSensorDataSet(Set<Integer> plrSensorIDSet) throws Exception;
	public TemperatureSensorData[] queryTemperatureSensorDataSet(Set<Integer> temperatureSensorIDSet) throws Exception;
	public HumidityData[] queryHumidityDataSet(Set<Integer> temperatureSensorIDSet) throws Exception;
	
	public LightSensorData[] queryLightEveryTwentyMinutes() throws Exception;
	public PlrSensorData[] queryPlrEveryTwentyMinutes() throws Exception;
	public TemperatureSensorData[] queryTemperatureEveryTwentyMinutes() throws Exception;
	
	
}
