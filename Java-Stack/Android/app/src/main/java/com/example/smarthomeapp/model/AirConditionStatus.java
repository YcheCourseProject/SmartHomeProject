package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class AirConditionStatus implements java.io.Serializable {

	// Fields

	private Integer airConditionStatusId;
	private AirCondition airCondition;
	private Float airConditionTemperature;
	private Integer airConditionMode;
	private Boolean isControlledByUser;
	private Timestamp airConditionStatusRecordTime;

	// Constructors

	/** default constructor */
	public AirConditionStatus() {
	}

	/** minimal constructor */
	public AirConditionStatus(Float airConditionTemperature,
			Integer airConditionMode, Boolean isControlledByUser,
			Timestamp airConditionStatusRecordTime) {
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionMode = airConditionMode;
		this.isControlledByUser = isControlledByUser;
		this.airConditionStatusRecordTime = airConditionStatusRecordTime;
	}

	/** full constructor */
	public AirConditionStatus(AirCondition airCondition,
			Float airConditionTemperature, Integer airConditionMode,
			Boolean isControlledByUser, Timestamp airConditionStatusRecordTime) {
		this.airCondition = airCondition;
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionMode = airConditionMode;
		this.isControlledByUser = isControlledByUser;
		this.airConditionStatusRecordTime = airConditionStatusRecordTime;
	}

	// Property accessors
	public Integer getAirConditionStatusId() {
		return this.airConditionStatusId;
	}

	public void setAirConditionStatusId(Integer airConditionStatusId) {
		this.airConditionStatusId = airConditionStatusId;
	}

	public AirCondition getAirCondition() {
		return this.airCondition;
	}

	public void setAirCondition(AirCondition airCondition) {
		this.airCondition = airCondition;
	}

	public Float getAirConditionTemperature() {
		return this.airConditionTemperature;
	}

	public void setAirConditionTemperature(Float airConditionTemperature) {
		this.airConditionTemperature = airConditionTemperature;
	}

	public Integer getAirConditionMode() {
		return this.airConditionMode;
	}

	public void setAirConditionMode(Integer airConditionMode) {
		this.airConditionMode = airConditionMode;
	}

	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	public Timestamp getAirConditionStatusRecordTime() {
		return this.airConditionStatusRecordTime;
	}

	public void setAirConditionStatusRecordTime(
			Timestamp airConditionStatusRecordTime) {
		this.airConditionStatusRecordTime = airConditionStatusRecordTime;
	}

}
