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
 * WaterHeaterStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "water_heater_status", catalog = "smarthome")
public class WaterHeaterStatus implements java.io.Serializable {

	// Fields

	private Integer waterHeaterStautsId;
	private WaterHeater waterHeater;
	private Float waterHeaterTemperature;
	private Boolean isControlledByUser;
	private Timestamp waterHeaterStatusRecordTime;
	private Boolean isAlreadyControlled;

	// Constructors

	/** default constructor */
	public WaterHeaterStatus() {
	}

	/** minimal constructor */
	public WaterHeaterStatus(Float waterHeaterTemperature,
			Timestamp waterHeaterStatusRecordTime, Boolean isAlreadyControlled) {
		this.waterHeaterTemperature = waterHeaterTemperature;
		this.waterHeaterStatusRecordTime = waterHeaterStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	/** full constructor */
	public WaterHeaterStatus(WaterHeater waterHeater,
			Float waterHeaterTemperature, Boolean isControlledByUser,
			Timestamp waterHeaterStatusRecordTime, Boolean isAlreadyControlled) {
		this.waterHeater = waterHeater;
		this.waterHeaterTemperature = waterHeaterTemperature;
		this.isControlledByUser = isControlledByUser;
		this.waterHeaterStatusRecordTime = waterHeaterStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "water_heater_stauts_id", unique = true, nullable = false)
	public Integer getWaterHeaterStautsId() {
		return this.waterHeaterStautsId;
	}

	public void setWaterHeaterStautsId(Integer waterHeaterStautsId) {
		this.waterHeaterStautsId = waterHeaterStautsId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "water_heater_id")
	public WaterHeater getWaterHeater() {
		return this.waterHeater;
	}

	public void setWaterHeater(WaterHeater waterHeater) {
		this.waterHeater = waterHeater;
	}

	@Column(name = "water_heater_temperature", nullable = false, precision = 12, scale = 0)
	public Float getWaterHeaterTemperature() {
		return this.waterHeaterTemperature;
	}

	public void setWaterHeaterTemperature(Float waterHeaterTemperature) {
		this.waterHeaterTemperature = waterHeaterTemperature;
	}

	@Column(name = "is_controlled_by_user")
	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	@Column(name = "water_heater_status_record_time", nullable = false, length = 0)
	public Timestamp getWaterHeaterStatusRecordTime() {
		return this.waterHeaterStatusRecordTime;
	}

	public void setWaterHeaterStatusRecordTime(
			Timestamp waterHeaterStatusRecordTime) {
		this.waterHeaterStatusRecordTime = waterHeaterStatusRecordTime;
	}

	@Column(name = "is_already_controlled", nullable = false)
	public Boolean getIsAlreadyControlled() {
		return this.isAlreadyControlled;
	}

	public void setIsAlreadyControlled(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

}