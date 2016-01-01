package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class WearableInfoToDemand implements java.io.Serializable {

	// Fields

	private Integer wearableInfoToDemandId;
	private MovingStatus movingStatus;
	private Float heartRate;
	private Float bodyTemperature;
	private Set<AirConditionDemand> airConditionDemands = new HashSet<AirConditionDemand>(
			0);
	private Set<WaterHeaterDemand> waterHeaterDemands = new HashSet<WaterHeaterDemand>(
			0);

	// Constructors

	/** default constructor */
	public WearableInfoToDemand() {
	}

	/** minimal constructor */
	public WearableInfoToDemand(Float heartRate, Float bodyTemperature) {
		this.heartRate = heartRate;
		this.bodyTemperature = bodyTemperature;
	}

	/** full constructor */
	public WearableInfoToDemand(MovingStatus movingStatus, Float heartRate,
			Float bodyTemperature, Set<AirConditionDemand> airConditionDemands,
			Set<WaterHeaterDemand> waterHeaterDemands) {
		this.movingStatus = movingStatus;
		this.heartRate = heartRate;
		this.bodyTemperature = bodyTemperature;
		this.airConditionDemands = airConditionDemands;
		this.waterHeaterDemands = waterHeaterDemands;
	}

	// Property accessors
	public Integer getWearableInfoToDemandId() {
		return this.wearableInfoToDemandId;
	}

	public void setWearableInfoToDemandId(Integer wearableInfoToDemandId) {
		this.wearableInfoToDemandId = wearableInfoToDemandId;
	}

	public MovingStatus getMovingStatus() {
		return this.movingStatus;
	}

	public void setMovingStatus(MovingStatus movingStatus) {
		this.movingStatus = movingStatus;
	}

	public Float getHeartRate() {
		return this.heartRate;
	}

	public void setHeartRate(Float heartRate) {
		this.heartRate = heartRate;
	}

	public Float getBodyTemperature() {
		return this.bodyTemperature;
	}

	public void setBodyTemperature(Float bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public Set<AirConditionDemand> getAirConditionDemands() {
		return this.airConditionDemands;
	}

	public void setAirConditionDemands(
			Set<AirConditionDemand> airConditionDemands) {
		this.airConditionDemands = airConditionDemands;
	}

	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

}
