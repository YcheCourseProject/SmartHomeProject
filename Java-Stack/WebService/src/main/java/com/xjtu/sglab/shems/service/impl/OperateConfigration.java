package com.xjtu.sglab.shems.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjtu.sglab.shems.dao.IAirConditionDAO;
import com.xjtu.sglab.shems.dao.IBoxDAO;
import com.xjtu.sglab.shems.dao.ICircuitLineDAO;
import com.xjtu.sglab.shems.dao.ICurtainDAO;
import com.xjtu.sglab.shems.dao.IElectricityMeterDAO;
import com.xjtu.sglab.shems.dao.IFlameSensorDAO;
import com.xjtu.sglab.shems.dao.IGasSensorDAO;
import com.xjtu.sglab.shems.dao.ILampDAO;
import com.xjtu.sglab.shems.dao.ILightSensorDAO;
import com.xjtu.sglab.shems.dao.IPlrSensorDAO;
import com.xjtu.sglab.shems.dao.IRoomDAO;
import com.xjtu.sglab.shems.dao.ITemperatureSensorDAO;
import com.xjtu.sglab.shems.dao.IWaterHeaterDAO;
import com.xjtu.sglab.shems.dao.impl.CircuitLineDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.AirCondition;
import com.xjtu.sglab.shems.entity.Box;
import com.xjtu.sglab.shems.entity.CircuitLine;
import com.xjtu.sglab.shems.entity.Curtain;
import com.xjtu.sglab.shems.entity.ElectricityMeter;
import com.xjtu.sglab.shems.entity.FlameSensor;
import com.xjtu.sglab.shems.entity.GasSensor;
import com.xjtu.sglab.shems.entity.Lamp;
import com.xjtu.sglab.shems.entity.LightSensor;
import com.xjtu.sglab.shems.entity.PlrSensor;
import com.xjtu.sglab.shems.entity.PlrSensorData;
import com.xjtu.sglab.shems.entity.Room;
import com.xjtu.sglab.shems.entity.TemperatureSensor;
import com.xjtu.sglab.shems.entity.WaterHeater;
import com.xjtu.sglab.shems.service.IOperateConfigrationService;
import com.xjtu.sglab.shems.service.IOperateSocialService;

@Service
public class OperateConfigration implements IOperateConfigrationService {
	@Resource
	EntityManagerHelper emh;
	@Resource
	IRoomDAO RoomDAO;
	@Resource
	IBoxDAO boxDAO;
	@Resource
	ICurtainDAO curtainDAO;
	@Resource
	ILampDAO lampDAO;
	@Resource
	IAirConditionDAO airConditionDAO;
	@Resource
	IWaterHeaterDAO waterHeaterDAO;
	@Resource
	ILightSensorDAO lightSensorDAO;
	@Resource
	IFlameSensorDAO flameSensorDAO;
	@Resource
	ITemperatureSensorDAO temperatureSensorDAO;
	@Resource
	IPlrSensorDAO plrSensorDAO;
	@Resource
	IGasSensorDAO gasSensorDAO;
	@Resource
	ICircuitLineDAO circuitLineDAO;
	@Resource
	IElectricityMeterDAO electricityMeterDAO;

	@Override
	public void saveConfigration(Room room) throws Exception {
		emh.beginTransaction();
		RoomDAO.save(room);
		Set<Box> boxs=room.getBoxes();
		Set<CircuitLine> circuitLines=room.getCircuitLines();
		room.setBoxes(null);
		room.setCircuitLines(null);
		for(Box box : boxs){
			Set<Curtain> curtains=box.getCurtains();
			Set<Lamp> lamps=box.getLamps();
			Set<AirCondition> airConditions=box.getAirConditions();
			Set<WaterHeater> waterHeaters=box.getWaterHeaters();
			Set<LightSensor> lightSensors=box.getLightSensors();
			Set<FlameSensor> flameSensors=box.getFlameSensors();
			Set<TemperatureSensor> temperatureSensors=box.getTemperatureSensors();
			Set<PlrSensor> plrSensors=box.getPlrSensors();
			Set<GasSensor> gasSensors=box.getGasSensors();
			box.setCurtains(null);
			box.setLamps(null);
			box.setAirConditions(null);
			box.setWaterHeaters(null);
			box.setLightSensors(null);
			box.setFlameSensors(null);
			box.setTemperatureSensors(null);
			box.setPlrSensors(null);
			box.setGasSensors(null);
			box.setRoom(room);
			boxDAO.update(box);
			for(Curtain curtain:curtains){
				curtain.setCurtainStatuses(null);
				curtain.setBox(box);
				curtainDAO.update(curtain);
			}
			for(Lamp lamp:lamps){
				lamp.setLampStatuses(null);
				lamp.setBox(box);
				lampDAO.update(lamp);
			}
			for(AirCondition airCondition:airConditions){
				airCondition.setAirConditionControlDetails(null);
				airCondition.setAirConditionRealTimeDecisions(null);
				airCondition.setAirConditionStatuses(null);
				airCondition.setBox(box);
				airConditionDAO.update(airCondition);
			}
			for(WaterHeater waterHeater:waterHeaters){
				waterHeater.setWaterHeaterControlDetails(null);
				waterHeater.setWaterHeaterRealTimeDecisions(null);
				waterHeater.setWaterHeaterStatuses(null);
				waterHeater.setBox(box);
				waterHeaterDAO.update(waterHeater);
			}
			for(LightSensor lightSensor:lightSensors){
				lightSensor.setLightSensorDatas(null);
				lightSensor.setBox(box);
				lightSensorDAO.update(lightSensor);
			}
			for(FlameSensor flameSensor:flameSensors){
				flameSensor.setFlameSensorDatas(null);
				flameSensor.setBox(box);
				flameSensorDAO.update(flameSensor);
			}
			for(TemperatureSensor temperatureSensor:temperatureSensors){
				temperatureSensor.setTemperatureSensorDatas(null);
				temperatureSensor.setBox(box);
				temperatureSensorDAO.update(temperatureSensor);
			}
			for(PlrSensor plrSensor:plrSensors){
				plrSensor.setPlrSensorDatas(null);
				plrSensor.setBox(box);
				plrSensorDAO.update(plrSensor);
			}
			for(GasSensor gasSensor:gasSensors){
				gasSensor.setGasSensorDatas(null);
				gasSensor.setBox(box);
				gasSensorDAO.update(gasSensor);
			}
		}
		for(CircuitLine circuitLine:circuitLines){
			circuitLine.setElectricityMeters(null);
			circuitLine.setCircuitLines(null);
			circuitLine.setRoom(room);
			ElectricityMeter electricityMeter =circuitLine.getElectricityMeter();
			electricityMeter.setCircuitLine(circuitLine);
			circuitLineDAO.save(circuitLine);
			electricityMeterDAO.save(electricityMeter);
		}
		emh.commit();
	}

	@Override
	public Room queryConfiguration(int roomID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room[] queryConfigurationArray(Set<Integer> roomIDSet)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
