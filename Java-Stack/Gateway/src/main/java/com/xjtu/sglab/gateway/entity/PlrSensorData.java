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
 * PlrSensorData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "plr_sensor_data", catalog = "smarthome")
public class PlrSensorData implements java.io.Serializable {

	// Fields

	private Integer plrDataId;
	private PlrSensor plrSensor;
	private Boolean plrData;
	private Timestamp plrDataCollectTime;

	// Constructors

	/** default constructor */
	public PlrSensorData() {
	}

	/** minimal constructor */
	public PlrSensorData(Boolean plrData, Timestamp plrDataCollectTime) {
		this.plrData = plrData;
		this.plrDataCollectTime = plrDataCollectTime;
	}

	/** full constructor */
	public PlrSensorData(PlrSensor plrSensor, Boolean plrData,
			Timestamp plrDataCollectTime) {
		this.plrSensor = plrSensor;
		this.plrData = plrData;
		this.plrDataCollectTime = plrDataCollectTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "plr_data_id", unique = true, nullable = false)
	public Integer getPlrDataId() {
		return this.plrDataId;
	}

	public void setPlrDataId(Integer plrDataId) {
		this.plrDataId = plrDataId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plr_sensor_id")
	public PlrSensor getPlrSensor() {
		return this.plrSensor;
	}

	public void setPlrSensor(PlrSensor plrSensor) {
		this.plrSensor = plrSensor;
	}

	@Column(name = "plr_data", nullable = false)
	public Boolean getPlrData() {
		return this.plrData;
	}

	public void setPlrData(Boolean plrData) {
		this.plrData = plrData;
	}

	@Column(name = "plr_data_collect_time", nullable = false, length = 0)
	public Timestamp getPlrDataCollectTime() {
		return this.plrDataCollectTime;
	}

	public void setPlrDataCollectTime(Timestamp plrDataCollectTime) {
		this.plrDataCollectTime = plrDataCollectTime;
	}

}