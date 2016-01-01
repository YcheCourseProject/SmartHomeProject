package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class PlrSensorData implements java.io.Serializable {

	// Fields

	private Integer plrDataId;
	private PlrSensor plrSensor;
	private Boolean plrData;
	private Timestamp plrDataCollectTime;

	// Constructors

	/** default constructor */
	public PlrSensorData() {
	}

	/** minimal constructor */
	public PlrSensorData(Boolean plrData, Timestamp plrDataCollectTime) {
		this.plrData = plrData;
		this.plrDataCollectTime = plrDataCollectTime;
	}

	/** full constructor */
	public PlrSensorData(PlrSensor plrSensor, Boolean plrData,
			Timestamp plrDataCollectTime) {
		this.plrSensor = plrSensor;
		this.plrData = plrData;
		this.plrDataCollectTime = plrDataCollectTime;
	}

	// Property accessors
	public Integer getPlrDataId() {
		return this.plrDataId;
	}

	public void setPlrDataId(Integer plrDataId) {
		this.plrDataId = plrDataId;
	}

	public PlrSensor getPlrSensor() {
		return this.plrSensor;
	}

	public void setPlrSensor(PlrSensor plrSensor) {
		this.plrSensor = plrSensor;
	}

	public Boolean getPlrData() {
		return this.plrData;
	}

	public void setPlrData(Boolean plrData) {
		this.plrData = plrData;
	}

	public Timestamp getPlrDataCollectTime() {
		return this.plrDataCollectTime;
	}

	public void setPlrDataCollectTime(Timestamp plrDataCollectTime) {
		this.plrDataCollectTime = plrDataCollectTime;
	}

}
