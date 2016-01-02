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
 * HumidityData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "humidity_data", catalog = "smarthome")
public class HumidityData implements java.io.Serializable {

	// Fields

	private Integer humidityDataId;
	private TemperatureSensor temperatureSensor;
	private Float humidityData;
	private Timestamp humidityDataRecordTime;

	// Constructors

	/** default constructor */
	public HumidityData() {
	}

	/** minimal constructor */
	public HumidityData(Float humidityData, Timestamp humidityDataRecordTime) {
		this.humidityData = humidityData;
		this.humidityDataRecordTime = humidityDataRecordTime;
	}

	/** full constructor */
	public HumidityData(TemperatureSensor temperatureSensor,
			Float humidityData, Timestamp humidityDataRecordTime) {
		this.temperatureSensor = temperatureSensor;
		this.humidityData = humidityData;
		this.humidityDataRecordTime = humidityDataRecordTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "humidity_data_id", unique = true, nullable = false)
	public Integer getHumidityDataId() {
		return this.humidityDataId;
	}

	public void setHumidityDataId(Integer humidityDataId) {
		this.humidityDataId = humidityDataId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "temperature_sensor_id")
	public TemperatureSensor getTemperatureSensor() {
		return this.temperatureSensor;
	}

	public void setTemperatureSensor(TemperatureSensor temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	@Column(name = "humidity_data", nullable = false, precision = 12, scale = 0)
	public Float getHumidityData() {
		return this.humidityData;
	}

	public void setHumidityData(Float humidityData) {
		this.humidityData = humidityData;
	}

	@Column(name = "humidity_data_record_time", nullable = false, length = 0)
	public Timestamp getHumidityDataRecordTime() {
		return this.humidityDataRecordTime;
	}

	public void setHumidityDataRecordTime(Timestamp humidityDataRecordTime) {
		this.humidityDataRecordTime = humidityDataRecordTime;
	}

}