package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class WearableDeviceInfo implements java.io.Serializable {

	// Fields

	private Integer wearableInfoId;
	private MovingStatus movingStatus;
	private Float acceleratedSpeedX;
	private Float acceleratedSpeedY;
	private Float acceleratedSpeedZ;
	private Float gyroscopeX;
	private Float gyroscopeY;
	private Float gyroscopeZ;
	private Float bodyTemperature;
	private Float heartRate;
	private Float speed;
	private Timestamp wearableInfoRecordTime;

	// Constructors

	/** default constructor */
	public WearableDeviceInfo() {
	}

	/** minimal constructor */
	public WearableDeviceInfo(Float bodyTemperature,
			Timestamp wearableInfoRecordTime) {
		this.bodyTemperature = bodyTemperature;
		this.wearableInfoRecordTime = wearableInfoRecordTime;
	}

	/** full constructor */
	public WearableDeviceInfo(MovingStatus movingStatus,
			Float acceleratedSpeedX, Float acceleratedSpeedY,
			Float acceleratedSpeedZ, Float gyroscopeX, Float gyroscopeY,
			Float gyroscopeZ, Float bodyTemperature, Float heartRate,
			Float speed, Timestamp wearableInfoRecordTime) {
		this.movingStatus = movingStatus;
		this.acceleratedSpeedX = acceleratedSpeedX;
		this.acceleratedSpeedY = acceleratedSpeedY;
		this.acceleratedSpeedZ = acceleratedSpeedZ;
		this.gyroscopeX = gyroscopeX;
		this.gyroscopeY = gyroscopeY;
		this.gyroscopeZ = gyroscopeZ;
		this.bodyTemperature = bodyTemperature;
		this.heartRate = heartRate;
		this.speed = speed;
		this.wearableInfoRecordTime = wearableInfoRecordTime;
	}

	// Property accessors
	public Integer getWearableInfoId() {
		return this.wearableInfoId;
	}

	public void setWearableInfoId(Integer wearableInfoId) {
		this.wearableInfoId = wearableInfoId;
	}

	public MovingStatus getMovingStatus() {
		return this.movingStatus;
	}

	public void setMovingStatus(MovingStatus movingStatus) {
		this.movingStatus = movingStatus;
	}

	public Float getAcceleratedSpeedX() {
		return this.acceleratedSpeedX;
	}

	public void setAcceleratedSpeedX(Float acceleratedSpeedX) {
		this.acceleratedSpeedX = acceleratedSpeedX;
	}

	public Float getAcceleratedSpeedY() {
		return this.acceleratedSpeedY;
	}

	public void setAcceleratedSpeedY(Float acceleratedSpeedY) {
		this.acceleratedSpeedY = acceleratedSpeedY;
	}

	public Float getAcceleratedSpeedZ() {
		return this.acceleratedSpeedZ;
	}

	public void setAcceleratedSpeedZ(Float acceleratedSpeedZ) {
		this.acceleratedSpeedZ = acceleratedSpeedZ;
	}

	public Float getGyroscopeX() {
		return this.gyroscopeX;
	}

	public void setGyroscopeX(Float gyroscopeX) {
		this.gyroscopeX = gyroscopeX;
	}

	public Float getGyroscopeY() {
		return this.gyroscopeY;
	}

	public void setGyroscopeY(Float gyroscopeY) {
		this.gyroscopeY = gyroscopeY;
	}

	public Float getGyroscopeZ() {
		return this.gyroscopeZ;
	}

	public void setGyroscopeZ(Float gyroscopeZ) {
		this.gyroscopeZ = gyroscopeZ;
	}

	public Float getBodyTemperature() {
		return this.bodyTemperature;
	}

	public void setBodyTemperature(Float bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public Float getHeartRate() {
		return this.heartRate;
	}

	public void setHeartRate(Float heartRate) {
		this.heartRate = heartRate;
	}

	public Float getSpeed() {
		return this.speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public Timestamp getWearableInfoRecordTime() {
		return this.wearableInfoRecordTime;
	}

	public void setWearableInfoRecordTime(Timestamp wearableInfoRecordTime) {
		this.wearableInfoRecordTime = wearableInfoRecordTime;
	}

}
