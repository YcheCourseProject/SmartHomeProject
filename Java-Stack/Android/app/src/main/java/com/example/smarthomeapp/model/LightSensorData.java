package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class LightSensorData implements java.io.Serializable {

	// Fields

	private Integer lightDataId;
	private LightSensor lightSensor;
	private Float lightData;
	private Timestamp lightDataCollectTime;

	// Constructors

	/** default constructor */
	public LightSensorData() {
	}

	/** minimal constructor */
	public LightSensorData(Float lightData, Timestamp lightDataCollectTime) {
		this.lightData = lightData;
		this.lightDataCollectTime = lightDataCollectTime;
	}

	/** full constructor */
	public LightSensorData(LightSensor lightSensor, Float lightData,
			Timestamp lightDataCollectTime) {
		this.lightSensor = lightSensor;
		this.lightData = lightData;
		this.lightDataCollectTime = lightDataCollectTime;
	}

	// Property accessors
	public Integer getLightDataId() {
		return this.lightDataId;
	}

	public void setLightDataId(Integer lightDataId) {
		this.lightDataId = lightDataId;
	}

	public LightSensor getLightSensor() {
		return this.lightSensor;
	}

	public void setLightSensor(LightSensor lightSensor) {
		this.lightSensor = lightSensor;
	}

	public Float getLightData() {
		return this.lightData;
	}

	public void setLightData(Float lightData) {
		this.lightData = lightData;
	}

	public Timestamp getLightDataCollectTime() {
		return this.lightDataCollectTime;
	}

	public void setLightDataCollectTime(Timestamp lightDataCollectTime) {
		this.lightDataCollectTime = lightDataCollectTime;
	}

}
