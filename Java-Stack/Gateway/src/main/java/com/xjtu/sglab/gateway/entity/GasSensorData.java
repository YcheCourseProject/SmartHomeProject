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
 * GasSensorData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gas_sensor_data", catalog = "smarthome")
public class GasSensorData implements java.io.Serializable {

	// Fields

	private Integer gasDataId;
	private GasSensor gasSensor;
	private Float gasData;
	private Timestamp gasDataCollectTime;

	// Constructors

	/** default constructor */
	public GasSensorData() {
	}

	/** minimal constructor */
	public GasSensorData(Float gasData, Timestamp gasDataCollectTime) {
		this.gasData = gasData;
		this.gasDataCollectTime = gasDataCollectTime;
	}

	/** full constructor */
	public GasSensorData(GasSensor gasSensor, Float gasData,
			Timestamp gasDataCollectTime) {
		this.gasSensor = gasSensor;
		this.gasData = gasData;
		this.gasDataCollectTime = gasDataCollectTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gas_data_id", unique = true, nullable = false)
	public Integer getGasDataId() {
		return this.gasDataId;
	}

	public void setGasDataId(Integer gasDataId) {
		this.gasDataId = gasDataId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gas_sensor_id")
	public GasSensor getGasSensor() {
		return this.gasSensor;
	}

	public void setGasSensor(GasSensor gasSensor) {
		this.gasSensor = gasSensor;
	}

	@Column(name = "gas_data", nullable = false, precision = 12, scale = 0)
	public Float getGasData() {
		return this.gasData;
	}

	public void setGasData(Float gasData) {
		this.gasData = gasData;
	}

	@Column(name = "gas_data_collect_time", nullable = false, length = 0)
	public Timestamp getGasDataCollectTime() {
		return this.gasDataCollectTime;
	}

	public void setGasDataCollectTime(Timestamp gasDataCollectTime) {
		this.gasDataCollectTime = gasDataCollectTime;
	}

}