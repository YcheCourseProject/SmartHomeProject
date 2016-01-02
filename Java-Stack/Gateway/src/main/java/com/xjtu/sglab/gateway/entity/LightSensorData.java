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
 * LightSensorData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "light_sensor_data", catalog = "smarthome")
public class LightSensorData implements java.io.Serializable {

	// Fields

	private Integer lightDataId;
	private LightSensor lightSensor;
	private Float lightData;
	private Timestamp lightDataCollectTime;

	// Constructors

	/** default constructor */
	public LightSensorData() {
	}

	/** minimal constructor */
	public LightSensorData(Float lightData, Timestamp lightDataCollectTime) {
		this.lightData = lightData;
		this.lightDataCollectTime = lightDataCollectTime;
	}

	/** full constructor */
	public LightSensorData(LightSensor lightSensor, Float lightData,
			Timestamp lightDataCollectTime) {
		this.lightSensor = lightSensor;
		this.lightData = lightData;
		this.lightDataCollectTime = lightDataCollectTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "light_data_id", unique = true, nullable = false)
	public Integer getLightDataId() {
		return this.lightDataId;
	}

	public void setLightDataId(Integer lightDataId) {
		this.lightDataId = lightDataId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "light_sensor_id")
	public LightSensor getLightSensor() {
		return this.lightSensor;
	}

	public void setLightSensor(LightSensor lightSensor) {
		this.lightSensor = lightSensor;
	}

	@Column(name = "light_data", nullable = false, precision = 12, scale = 0)
	public Float getLightData() {
		return this.lightData;
	}

	public void setLightData(Float lightData) {
		this.lightData = lightData;
	}

	@Column(name = "light_data_collect_time", nullable = false, length = 0)
	public Timestamp getLightDataCollectTime() {
		return this.lightDataCollectTime;
	}

	public void setLightDataCollectTime(Timestamp lightDataCollectTime) {
		this.lightDataCollectTime = lightDataCollectTime;
	}

}