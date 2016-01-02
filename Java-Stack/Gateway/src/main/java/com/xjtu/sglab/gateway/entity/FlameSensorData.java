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
 * FlameSensorData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "flame_sensor_data", catalog = "smarthome")
public class FlameSensorData implements java.io.Serializable {

	// Fields

	private Integer flameDataId;
	private FlameSensor flameSensor;
	private Float flameData;
	private Timestamp flameDataCollectTime;

	// Constructors

	/** default constructor */
	public FlameSensorData() {
	}

	/** minimal constructor */
	public FlameSensorData(Float flameData, Timestamp flameDataCollectTime) {
		this.flameData = flameData;
		this.flameDataCollectTime = flameDataCollectTime;
	}

	/** full constructor */
	public FlameSensorData(FlameSensor flameSensor, Float flameData,
			Timestamp flameDataCollectTime) {
		this.flameSensor = flameSensor;
		this.flameData = flameData;
		this.flameDataCollectTime = flameDataCollectTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "flame_data_id", unique = true, nullable = false)
	public Integer getFlameDataId() {
		return this.flameDataId;
	}

	public void setFlameDataId(Integer flameDataId) {
		this.flameDataId = flameDataId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flame_sensor_id")
	public FlameSensor getFlameSensor() {
		return this.flameSensor;
	}

	public void setFlameSensor(FlameSensor flameSensor) {
		this.flameSensor = flameSensor;
	}

	@Column(name = "flame_data", nullable = false, precision = 12, scale = 0)
	public Float getFlameData() {
		return this.flameData;
	}

	public void setFlameData(Float flameData) {
		this.flameData = flameData;
	}

	@Column(name = "flame_data_collect_time", nullable = false, length = 0)
	public Timestamp getFlameDataCollectTime() {
		return this.flameDataCollectTime;
	}

	public void setFlameDataCollectTime(Timestamp flameDataCollectTime) {
		this.flameDataCollectTime = flameDataCollectTime;
	}

}