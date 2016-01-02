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
 * WearableDeviceInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wearable_device_info", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wearable_info_id", unique = true, nullable = false)
	public Integer getWearableInfoId() {
		return this.wearableInfoId;
	}

	public void setWearableInfoId(Integer wearableInfoId) {
		this.wearableInfoId = wearableInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moving_type_id")
	public MovingStatus getMovingStatus() {
		return this.movingStatus;
	}

	public void setMovingStatus(MovingStatus movingStatus) {
		this.movingStatus = movingStatus;
	}

	@Column(name = "accelerated_speed_x", precision = 12, scale = 0)
	public Float getAcceleratedSpeedX() {
		return this.acceleratedSpeedX;
	}

	public void setAcceleratedSpeedX(Float acceleratedSpeedX) {
		this.acceleratedSpeedX = acceleratedSpeedX;
	}

	@Column(name = "accelerated_speed_y", precision = 12, scale = 0)
	public Float getAcceleratedSpeedY() {
		return this.acceleratedSpeedY;
	}

	public void setAcceleratedSpeedY(Float acceleratedSpeedY) {
		this.acceleratedSpeedY = acceleratedSpeedY;
	}

	@Column(name = "accelerated_speed_z", precision = 12, scale = 0)
	public Float getAcceleratedSpeedZ() {
		return this.acceleratedSpeedZ;
	}

	public void setAcceleratedSpeedZ(Float acceleratedSpeedZ) {
		this.acceleratedSpeedZ = acceleratedSpeedZ;
	}

	@Column(name = "gyroscope_x", precision = 12, scale = 0)
	public Float getGyroscopeX() {
		return this.gyroscopeX;
	}

	public void setGyroscopeX(Float gyroscopeX) {
		this.gyroscopeX = gyroscopeX;
	}

	@Column(name = "gyroscope_y", precision = 12, scale = 0)
	public Float getGyroscopeY() {
		return this.gyroscopeY;
	}

	public void setGyroscopeY(Float gyroscopeY) {
		this.gyroscopeY = gyroscopeY;
	}

	@Column(name = "gyroscope_z", precision = 12, scale = 0)
	public Float getGyroscopeZ() {
		return this.gyroscopeZ;
	}

	public void setGyroscopeZ(Float gyroscopeZ) {
		this.gyroscopeZ = gyroscopeZ;
	}

	@Column(name = "body_temperature", nullable = false, precision = 12, scale = 0)
	public Float getBodyTemperature() {
		return this.bodyTemperature;
	}

	public void setBodyTemperature(Float bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	@Column(name = "heart_rate", precision = 12, scale = 0)
	public Float getHeartRate() {
		return this.heartRate;
	}

	public void setHeartRate(Float heartRate) {
		this.heartRate = heartRate;
	}

	@Column(name = "speed", precision = 12, scale = 0)
	public Float getSpeed() {
		return this.speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	@Column(name = "wearable_info_record_time", nullable = false, length = 0)
	public Timestamp getWearableInfoRecordTime() {
		return this.wearableInfoRecordTime;
	}

	public void setWearableInfoRecordTime(Timestamp wearableInfoRecordTime) {
		this.wearableInfoRecordTime = wearableInfoRecordTime;
	}

}