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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MovingStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "moving_status", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "moving_type_id", unique = true, nullable = false)
	public Integer getMovingTypeId() {
		return this.movingTypeId;
	}

	public void setMovingTypeId(Integer movingTypeId) {
		this.movingTypeId = movingTypeId;
	}

	@Column(name = "moving_type", nullable = false, length = 15)
	public String getMovingType() {
		return this.movingType;
	}

	public void setMovingType(String movingType) {
		this.movingType = movingType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movingStatus")
	public Set<WearableDeviceInfo> getWearableDeviceInfos() {
		return this.wearableDeviceInfos;
	}

	public void setWearableDeviceInfos(
			Set<WearableDeviceInfo> wearableDeviceInfos) {
		this.wearableDeviceInfos = wearableDeviceInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movingStatus")
	public Set<WearableInfoToDemand> getWearableInfoToDemands() {
		return this.wearableInfoToDemands;
	}

	public void setWearableInfoToDemands(
			Set<WearableInfoToDemand> wearableInfoToDemands) {
		this.wearableInfoToDemands = wearableInfoToDemands;
	}

}