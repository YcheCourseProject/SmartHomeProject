package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class WaterHeaterStatus implements java.io.Serializable {

	// Fields

	private Integer waterHeaterStautsId;
	private WaterHeater waterHeater;
	private Float waterHeaterTemperature;
	private Boolean isControlledByUser;
	private Timestamp waterHeaterStatusRecordTime;

	// Constructors

	/** default constructor */
	public WaterHeaterStatus() {
	}

	/** minimal constructor */
	public WaterHeaterStatus(Float waterHeaterTemperature,
			Timestamp waterHeaterStatusRecordTime) {
		this.waterHeaterTemperature = waterHeaterTemperature;
		this.waterHeaterStatusRecordTime = waterHeaterStatusRecordTime;
	}

	/** full constructor */
	public WaterHeaterStatus(WaterHeater waterHeater,
			Float waterHeaterTemperature, Boolean isControlledByUser,
			Timestamp waterHeaterStatusRecordTime) {
		this.waterHeater = waterHeater;
		this.waterHeaterTemperature = waterHeaterTemperature;
		this.isControlledByUser = isControlledByUser;
		this.waterHeaterStatusRecordTime = waterHeaterStatusRecordTime;
	}

	// Property accessors
	public Integer getWaterHeaterStautsId() {
		return this.waterHeaterStautsId;
	}

	public void setWaterHeaterStautsId(Integer waterHeaterStautsId) {
		this.waterHeaterStautsId = waterHeaterStautsId;
	}

	public WaterHeater getWaterHeater() {
		return this.waterHeater;
	}

	public void setWaterHeater(WaterHeater waterHeater) {
		this.waterHeater = waterHeater;
	}

	public Float getWaterHeaterTemperature() {
		return this.waterHeaterTemperature;
	}

	public void setWaterHeaterTemperature(Float waterHeaterTemperature) {
		this.waterHeaterTemperature = waterHeaterTemperature;
	}

	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	public Timestamp getWaterHeaterStatusRecordTime() {
		return this.waterHeaterStatusRecordTime;
	}

	public void setWaterHeaterStatusRecordTime(
			Timestamp waterHeaterStatusRecordTime) {
		this.waterHeaterStatusRecordTime = waterHeaterStatusRecordTime;
	}

}
