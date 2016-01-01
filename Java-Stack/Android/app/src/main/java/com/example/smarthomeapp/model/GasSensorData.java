package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class GasSensorData implements java.io.Serializable {

	// Fields

	private Integer gasDataId;
	private GasSensor gasSensor;
	private Float gasData;
	private Timestamp gasDataCollectTime;

	// Constructors

	/** default constructor */
	public GasSensorData() {
	}

	/** minimal constructor */
	public GasSensorData(Float gasData, Timestamp gasDataCollectTime) {
		this.gasData = gasData;
		this.gasDataCollectTime = gasDataCollectTime;
	}

	/** full constructor */
	public GasSensorData(GasSensor gasSensor, Float gasData,
			Timestamp gasDataCollectTime) {
		this.gasSensor = gasSensor;
		this.gasData = gasData;
		this.gasDataCollectTime = gasDataCollectTime;
	}

	// Property accessors
	public Integer getGasDataId() {
		return this.gasDataId;
	}

	public void setGasDataId(Integer gasDataId) {
		this.gasDataId = gasDataId;
	}

	public GasSensor getGasSensor() {
		return this.gasSensor;
	}

	public void setGasSensor(GasSensor gasSensor) {
		this.gasSensor = gasSensor;
	}

	public Float getGasData() {
		return this.gasData;
	}

	public void setGasData(Float gasData) {
		this.gasData = gasData;
	}

	public Timestamp getGasDataCollectTime() {
		return this.gasDataCollectTime;
	}

	public void setGasDataCollectTime(Timestamp gasDataCollectTime) {
		this.gasDataCollectTime = gasDataCollectTime;
	}

}
