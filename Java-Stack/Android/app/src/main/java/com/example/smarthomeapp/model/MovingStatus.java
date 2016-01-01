package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class MovingStatus implements java.io.Serializable {

	// Fields

	private Integer movingTypeId;
	private String movingType;
	private Set<WearableDeviceInfo> wearableDeviceInfos = new HashSet<WearableDeviceInfo>(
			0);
	private Set<WearableInfoToDemand> wearableInfoToDemands = new HashSet<WearableInfoToDemand>(
			0);

	// Constructors

	/** default constructor */
	public MovingStatus() {
	}

	/** minimal constructor */
	public MovingStatus(String movingType) {
		this.movingType = movingType;
	}

	/** full constructor */
	public MovingStatus(String movingType,
			Set<WearableDeviceInfo> wearableDeviceInfos,
			Set<WearableInfoToDemand> wearableInfoToDemands) {
		this.movingType = movingType;
		this.wearableDeviceInfos = wearableDeviceInfos;
		this.wearableInfoToDemands = wearableInfoToDemands;
	}

	// Property accessors
	public Integer getMovingTypeId() {
		return this.movingTypeId;
	}

	public void setMovingTypeId(Integer movingTypeId) {
		this.movingTypeId = movingTypeId;
	}

	public String getMovingType() {
		return this.movingType;
	}

	public void setMovingType(String movingType) {
		this.movingType = movingType;
	}

	public Set<WearableDeviceInfo> getWearableDeviceInfos() {
		return this.wearableDeviceInfos;
	}

	public void setWearableDeviceInfos(
			Set<WearableDeviceInfo> wearableDeviceInfos) {
		this.wearableDeviceInfos = wearableDeviceInfos;
	}

	public Set<WearableInfoToDemand> getWearableInfoToDemands() {
		return this.wearableInfoToDemands;
	}

	public void setWearableInfoToDemands(
			Set<WearableInfoToDemand> wearableInfoToDemands) {
		this.wearableInfoToDemands = wearableInfoToDemands;
	}

}
