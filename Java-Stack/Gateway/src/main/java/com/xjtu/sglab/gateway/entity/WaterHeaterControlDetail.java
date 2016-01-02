package com.xjtu.sglab.gateway.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * WaterHeaterControlDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "water_heater_control_detail", catalog = "smarthome")
public class WaterHeaterControlDetail implements java.io.Serializable {

	// Fields

	private Integer waterHeaterControlId;
	private WaterHeaterRealTimeDecision waterHeaterRealTimeDecision;
	private WaterHeater waterHeater;
	private Timestamp waterHeaterStartTime;
	private Timestamp waterHeaterEndTime;
	private Float waterTemperature;
	private Timestamp waterTemperatureDecisionTime;
	private Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions = new HashSet<WaterHeaterRealTimeDecision>(
			0);

	// Constructors

	/** default constructor */
	public WaterHeaterControlDetail() {
	}

	/** minimal constructor */
	public WaterHeaterControlDetail(Timestamp waterHeaterStartTime,
			Timestamp waterHeaterEndTime, Float waterTemperature,
			Timestamp waterTemperatureDecisionTime) {
		this.waterHeaterStartTime = waterHeaterStartTime;
		this.waterHeaterEndTime = waterHeaterEndTime;
		this.waterTemperature = waterTemperature;
		this.waterTemperatureDecisionTime = waterTemperatureDecisionTime;
	}

	/** full constructor */
	public WaterHeaterControlDetail(
			WaterHeaterRealTimeDecision waterHeaterRealTimeDecision,
			WaterHeater waterHeater, Timestamp waterHeaterStartTime,
			Timestamp waterHeaterEndTime, Float waterTemperature,
			Timestamp waterTemperatureDecisionTime,
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.waterHeaterRealTimeDecision = waterHeaterRealTimeDecision;
		this.waterHeater = waterHeater;
		this.waterHeaterStartTime = waterHeaterStartTime;
		this.waterHeaterEndTime = waterHeaterEndTime;
		this.waterTemperature = waterTemperature;
		this.waterTemperatureDecisionTime = waterTemperatureDecisionTime;
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "water_heater_control_id", unique = true, nullable = false)
	public Integer getWaterHeaterControlId() {
		return this.waterHeaterControlId;
	}

	public void setWaterHeaterControlId(Integer waterHeaterControlId) {
		this.waterHeaterControlId = waterHeaterControlId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "water_heater_real_time_decision_id")
	public WaterHeaterRealTimeDecision getWaterHeaterRealTimeDecision() {
		return this.waterHeaterRealTimeDecision;
	}

	public void setWaterHeaterRealTimeDecision(
			WaterHeaterRealTimeDecision waterHeaterRealTimeDecision) {
		this.waterHeaterRealTimeDecision = waterHeaterRealTimeDecision;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "water_heater_id")
	public WaterHeater getWaterHeater() {
		return this.waterHeater;
	}

	public void setWaterHeater(WaterHeater waterHeater) {
		this.waterHeater = waterHeater;
	}

	@Column(name = "water_heater_start_time", nullable = false, length = 0)
	public Timestamp getWaterHeaterStartTime() {
		return this.waterHeaterStartTime;
	}

	public void setWaterHeaterStartTime(Timestamp waterHeaterStartTime) {
		this.waterHeaterStartTime = waterHeaterStartTime;
	}

	@Column(name = "water_heater_end_time", nullable = false, length = 0)
	public Timestamp getWaterHeaterEndTime() {
		return this.waterHeaterEndTime;
	}

	public void setWaterHeaterEndTime(Timestamp waterHeaterEndTime) {
		this.waterHeaterEndTime = waterHeaterEndTime;
	}

	@Column(name = "water_temperature", nullable = false, precision = 12, scale = 0)
	public Float getWaterTemperature() {
		return this.waterTemperature;
	}

	public void setWaterTemperature(Float waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	@Column(name = "water_temperature_decision_time", nullable = false, length = 0)
	public Timestamp getWaterTemperatureDecisionTime() {
		return this.waterTemperatureDecisionTime;
	}

	public void setWaterTemperatureDecisionTime(
			Timestamp waterTemperatureDecisionTime) {
		this.waterTemperatureDecisionTime = waterTemperatureDecisionTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waterHeaterControlDetail")
	public Set<WaterHeaterRealTimeDecision> getWaterHeaterRealTimeDecisions() {
		return this.waterHeaterRealTimeDecisions;
	}

	public void setWaterHeaterRealTimeDecisions(
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

}