package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
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
	public Integer getElectricityInfoId() {
		return this.electricityInfoId;
	}

	public void setElectricityInfoId(Integer electricityInfoId) {
		this.electricityInfoId = electricityInfoId;
	}

	public ElectricityMeter getElectricityMeter() {
		return this.electricityMeter;
	}

	public void setElectricityMeter(ElectricityMeter electricityMeter) {
		this.electricityMeter = electricityMeter;
	}

	public Float getActivePower() {
		return this.activePower;
	}

	public void setActivePower(Float activePower) {
		this.activePower = activePower;
	}

	public Float getReactivePower() {
		return this.reactivePower;
	}

	public void setReactivePower(Float reactivePower) {
		this.reactivePower = reactivePower;
	}

	public Float getTotalConsumeEnergy() {
		return this.totalConsumeEnergy;
	}

	public void setTotalConsumeEnergy(Float totalConsumeEnergy) {
		this.totalConsumeEnergy = totalConsumeEnergy;
	}

	public Timestamp getElectricityInfoCollectTime() {
		return this.electricityInfoCollectTime;
	}

	public void setElectricityInfoCollectTime(
			Timestamp electricityInfoCollectTime) {
		this.electricityInfoCollectTime = electricityInfoCollectTime;
	}

}
