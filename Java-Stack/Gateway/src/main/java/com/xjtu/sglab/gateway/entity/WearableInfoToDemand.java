package com.xjtu.sglab.gateway.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * WearableInfoToDemand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wearable_info_to_demand", catalog = "smarthome")
public class WearableInfoToDemand implements java.io.Serializable {

	// Fields

	private Integer wearableInfoToDemandId;
	private MovingStatus movingStatus;
	private Float heartRate;
	private Float bodyTemperature;
	private String wearableInfoBeginTime;
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
			Float bodyTemperature, String wearableInfoBeginTime,
			Set<WaterHeaterDemand> waterHeaterDemands) {
		this.movingStatus = movingStatus;
		this.heartRate = heartRate;
		this.bodyTemperature = bodyTemperature;
		this.wearableInfoBeginTime = wearableInfoBeginTime;
		this.waterHeaterDemands = waterHeaterDemands;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wearable_info_to_demand_id", unique = true, nullable = false)
	public Integer getWearableInfoToDemandId() {
		return this.wearableInfoToDemandId;
	}

	public void setWearableInfoToDemandId(Integer wearableInfoToDemandId) {
		this.wearableInfoToDemandId = wearableInfoToDemandId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moving_type_id")
	public MovingStatus getMovingStatus() {
		return this.movingStatus;
	}

	public void setMovingStatus(MovingStatus movingStatus) {
		this.movingStatus = movingStatus;
	}

	@Column(name = "heart_rate", nullable = false, precision = 12, scale = 0)
	public Float getHeartRate() {
		return this.heartRate;
	}

	public void setHeartRate(Float heartRate) {
		this.heartRate = heartRate;
	}

	@Column(name = "body_temperature", nullable = false, precision = 12, scale = 0)
	public Float getBodyTemperature() {
		return this.bodyTemperature;
	}

	public void setBodyTemperature(Float bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	@Column(name = "wearable_info_begin_time", length = 30)
	public String getWearableInfoBeginTime() {
		return this.wearableInfoBeginTime;
	}

	public void setWearableInfoBeginTime(String wearableInfoBeginTime) {
		this.wearableInfoBeginTime = wearableInfoBeginTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "wearableInfoToDemand")
	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

}