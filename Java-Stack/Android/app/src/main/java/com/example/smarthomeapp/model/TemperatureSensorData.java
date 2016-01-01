package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class TemperatureSensorData implements java.io.Serializable {

	// Fields

	private Integer temperatureDataId;
	private TemperatureSensor temperatureSensor;
	private Float temperatureData;
	private Timestamp temperatureDataCollectTime;

	// Constructors

	/** default constructor */
	public TemperatureSensorData() {
	}

	/** minimal constructor */
	public TemperatureSensorData(Float temperatureData,
			Timestamp temperatureDataCollectTime) {
		this.temperatureData = temperatureData;
		this.temperatureDataCollectTime = temperatureDataCollectTime;
	}

	/** full constructor */
	public TemperatureSensorData(TemperatureSensor temperatureSensor,
			Float temperatureData, Timestamp temperatureDataCollectTime) {
		this.temperatureSensor = temperatureSensor;
		this.temperatureData = temperatureData;
		this.temperatureDataCollectTime = temperatureDataCollectTime;
	}

	// Property accessors
	public Integer getTemperatureDataId() {
		return this.temperatureDataId;
	}

	public void setTemperatureDataId(Integer temperatureDataId) {
		this.temperatureDataId = temperatureDataId;
	}

	public TemperatureSensor getTemperatureSensor() {
		return this.temperatureSensor;
	}

	public void setTemperatureSensor(TemperatureSensor temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	public Float getTemperatureData() {
		return this.temperatureData;
	}

	public void setTemperatureData(Float temperatureData) {
		this.temperatureData = temperatureData;
	}

	public Timestamp getTemperatureDataCollectTime() {
		return this.temperatureDataCollectTime;
	}

	public void setTemperatureDataCollectTime(
			Timestamp temperatureDataCollectTime) {
		this.temperatureDataCollectTime = temperatureDataCollectTime;
	}

}
