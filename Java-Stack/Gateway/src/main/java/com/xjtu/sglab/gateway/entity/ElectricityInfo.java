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
 * ElectricityInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "electricity_info", catalog = "smarthome")
public class ElectricityInfo implements java.io.Serializable {

	// Fields

	private Integer electricityInfoId;
	private ElectricityMeter electricityMeter;
	private Float activePower;
	private Float reactivePower;
	private Float totalConsumeEnergy;
	private Timestamp electricityInfoCollectTime;

	// Constructors

	/** default constructor */
	public ElectricityInfo() {
	}

	/** minimal constructor */
	public ElectricityInfo(Float activePower, Float reactivePower,
			Float totalConsumeEnergy, Timestamp electricityInfoCollectTime) {
		this.activePower = activePower;
		this.reactivePower = reactivePower;
		this.totalConsumeEnergy = totalConsumeEnergy;
		this.electricityInfoCollectTime = electricityInfoCollectTime;
	}

	/** full constructor */
	public ElectricityInfo(ElectricityMeter electricityMeter,
			Float activePower, Float reactivePower, Float totalConsumeEnergy,
			Timestamp electricityInfoCollectTime) {
		this.electricityMeter = electricityMeter;
		this.activePower = activePower;
		this.reactivePower = reactivePower;
		this.totalConsumeEnergy = totalConsumeEnergy;
		this.electricityInfoCollectTime = electricityInfoCollectTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "electricity_info_id", unique = true, nullable = false)
	public Integer getElectricityInfoId() {
		return this.electricityInfoId;
	}

	public void setElectricityInfoId(Integer electricityInfoId) {
		this.electricityInfoId = electricityInfoId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "electricity_meter_id")
	public ElectricityMeter getElectricityMeter() {
		return this.electricityMeter;
	}

	public void setElectricityMeter(ElectricityMeter electricityMeter) {
		this.electricityMeter = electricityMeter;
	}

	@Column(name = "active_power", nullable = false, precision = 12, scale = 0)
	public Float getActivePower() {
		return this.activePower;
	}

	public void setActivePower(Float activePower) {
		this.activePower = activePower;
	}

	@Column(name = "reactive_power", nullable = false, precision = 12, scale = 0)
	public Float getReactivePower() {
		return this.reactivePower;
	}

	public void setReactivePower(Float reactivePower) {
		this.reactivePower = reactivePower;
	}

	@Column(name = "total_consume_energy", nullable = false, precision = 12, scale = 0)
	public Float getTotalConsumeEnergy() {
		return this.totalConsumeEnergy;
	}

	public void setTotalConsumeEnergy(Float totalConsumeEnergy) {
		this.totalConsumeEnergy = totalConsumeEnergy;
	}

	@Column(name = "electricity_info_collect_time", nullable = false, length = 0)
	public Timestamp getElectricityInfoCollectTime() {
		return this.electricityInfoCollectTime;
	}

	public void setElectricityInfoCollectTime(
			Timestamp electricityInfoCollectTime) {
		this.electricityInfoCollectTime = electricityInfoCollectTime;
	}

}