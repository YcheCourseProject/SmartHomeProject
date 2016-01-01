package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class FlameSensorData implements java.io.Serializable {

	// Fields

	private Integer flameDataId;
	private FlameSensor flameSensor;
	private Float flameData;
	private Timestamp flameDataCollectTime;

	// Constructors

	/** default constructor */
	public FlameSensorData() {
	}

	/** minimal constructor */
	public FlameSensorData(Float flameData, Timestamp flameDataCollectTime) {
		this.flameData = flameData;
		this.flameDataCollectTime = flameDataCollectTime;
	}

	/** full constructor */
	public FlameSensorData(FlameSensor flameSensor, Float flameData,
			Timestamp flameDataCollectTime) {
		this.flameSensor = flameSensor;
		this.flameData = flameData;
		this.flameDataCollectTime = flameDataCollectTime;
	}

	// Property accessors
	public Integer getFlameDataId() {
		return this.flameDataId;
	}

	public void setFlameDataId(Integer flameDataId) {
		this.flameDataId = flameDataId;
	}

	public FlameSensor getFlameSensor() {
		return this.flameSensor;
	}

	public void setFlameSensor(FlameSensor flameSensor) {
		this.flameSensor = flameSensor;
	}

	public Float getFlameData() {
		return this.flameData;
	}

	public void setFlameData(Float flameData) {
		this.flameData = flameData;
	}

	public Timestamp getFlameDataCollectTime() {
		return this.flameDataCollectTime;
	}

	public void setFlameDataCollectTime(Timestamp flameDataCollectTime) {
		this.flameDataCollectTime = flameDataCollectTime;
	}

}
