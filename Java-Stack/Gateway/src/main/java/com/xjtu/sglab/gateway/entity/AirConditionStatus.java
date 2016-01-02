package com.xjtu.sglab.gateway.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AirConditionStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "air_condition_status", catalog = "smarthome")
public class AirConditionStatus implements java.io.Serializable {

	// Fields

	private Integer airConditionStatusId;
	private AirCondition airCondition;
	private Float airConditionTemperature;
	private Integer airConditionMode;
	private Boolean isControlledByUser;
	private Timestamp airConditionStatusRecordTime;
	private Boolean isAlreadyControlled;

	// Constructors

	/** default constructor */
	public AirConditionStatus() {
	}

	/** minimal constructor */
	public AirConditionStatus(Float airConditionTemperature,
			Integer airConditionMode, Boolean isControlledByUser,
			Timestamp airConditionStatusRecordTime, Boolean isAlreadyControlled) {
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionMode = airConditionMode;
		this.isControlledByUser = isControlledByUser;
		this.airConditionStatusRecordTime = airConditionStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	/** full constructor */
	public AirConditionStatus(AirCondition airCondition,
			Float airConditionTemperature, Integer airConditionMode,
			Boolean isControlledByUser, Timestamp airConditionStatusRecordTime,
			Boolean isAlreadyControlled) {
		this.airCondition = airCondition;
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionMode = airConditionMode;
		this.isControlledByUser = isControlledByUser;
		this.airConditionStatusRecordTime = airConditionStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "air_condition_status_id", unique = true, nullable = false)
	public Integer getAirConditionStatusId() {
		return this.airConditionStatusId;
	}

	public void setAirConditionStatusId(Integer airConditionStatusId) {
		this.airConditionStatusId = airConditionStatusId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "air_condition_id")
	public AirCondition getAirCondition() {
		return this.airCondition;
	}

	public void setAirCondition(AirCondition airCondition) {
		this.airCondition = airCondition;
	}

	@Column(name = "air_condition_temperature", nullable = false, precision = 12, scale = 0)
	public Float getAirConditionTemperature() {
		return this.airConditionTemperature;
	}

	public void setAirConditionTemperature(Float airConditionTemperature) {
		this.airConditionTemperature = airConditionTemperature;
	}

	@Column(name = "air_condition_mode", nullable = false)
	public Integer getAirConditionMode() {
		return this.airConditionMode;
	}

	public void setAirConditionMode(Integer airConditionMode) {
		this.airConditionMode = airConditionMode;
	}

	@Column(name = "is_controlled_by_user", nullable = false)
	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	@Column(name = "air_condition_status_record_time", nullable = false, length = 0)
	public Timestamp getAirConditionStatusRecordTime() {
		return this.airConditionStatusRecordTime;
	}

	public void setAirConditionStatusRecordTime(
			Timestamp airConditionStatusRecordTime) {
		this.airConditionStatusRecordTime = airConditionStatusRecordTime;
	}

	@Column(name = "is_already_controlled", nullable = false)
	public Boolean getIsAlreadyControlled() {
		return this.isAlreadyControlled;
	}

	public void setIsAlreadyControlled(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

}