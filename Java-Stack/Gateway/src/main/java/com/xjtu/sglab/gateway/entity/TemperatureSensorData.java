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
 * TemperatureSensorData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "temperature_sensor_data", catalog = "smarthome")
public class TemperatureSensorData implements java.io.Serializable {

	// Fields

	private Integer temperatureDataId;
	private TemperatureSensor temperatureSensor;
	private Float temperatureData;
	private Timestamp temperatureDataCollectTime;

	// Constructors

	/** default constructor */
	public TemperatureSensorData() {
	}

	/** minimal constructor */
	public TemperatureSensorData(Float temperatureData,
			Timestamp temperatureDataCollectTime) {
		this.temperatureData = temperatureData;
		this.temperatureDataCollectTime = temperatureDataCollectTime;
	}

	/** full constructor */
	public TemperatureSensorData(TemperatureSensor temperatureSensor,
			Float temperatureData, Timestamp temperatureDataCollectTime) {
		this.temperatureSensor = temperatureSensor;
		this.temperatureData = temperatureData;
		this.temperatureDataCollectTime = temperatureDataCollectTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "temperature_data_id", unique = true, nullable = false)
	public Integer getTemperatureDataId() {
		return this.temperatureDataId;
	}

	public void setTemperatureDataId(Integer temperatureDataId) {
		this.temperatureDataId = temperatureDataId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "temperature_sensor_id")
	public TemperatureSensor getTemperatureSensor() {
		return this.temperatureSensor;
	}

	public void setTemperatureSensor(TemperatureSensor temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	@Column(name = "temperature_data", nullable = false, precision = 12, scale = 0)
	public Float getTemperatureData() {
		return this.temperatureData;
	}

	public void setTemperatureData(Float temperatureData) {
		this.temperatureData = temperatureData;
	}

	@Column(name = "temperature_data_collect_time", nullable = false, length = 0)
	public Timestamp getTemperatureDataCollectTime() {
		return this.temperatureDataCollectTime;
	}

	public void setTemperatureDataCollectTime(
			Timestamp temperatureDataCollectTime) {
		this.temperatureDataCollectTime = temperatureDataCollectTime;
	}

}