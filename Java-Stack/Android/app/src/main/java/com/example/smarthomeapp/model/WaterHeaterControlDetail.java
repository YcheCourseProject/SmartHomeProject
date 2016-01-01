package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
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
	public Integer getWaterHeaterControlId() {
		return this.waterHeaterControlId;
	}

	public void setWaterHeaterControlId(Integer waterHeaterControlId) {
		this.waterHeaterControlId = waterHeaterControlId;
	}

	public WaterHeaterRealTimeDecision getWaterHeaterRealTimeDecision() {
		return this.waterHeaterRealTimeDecision;
	}

	public void setWaterHeaterRealTimeDecision(
			WaterHeaterRealTimeDecision waterHeaterRealTimeDecision) {
		this.waterHeaterRealTimeDecision = waterHeaterRealTimeDecision;
	}

	public WaterHeater getWaterHeater() {
		return this.waterHeater;
	}

	public void setWaterHeater(WaterHeater waterHeater) {
		this.waterHeater = waterHeater;
	}

	public Timestamp getWaterHeaterStartTime() {
		return this.waterHeaterStartTime;
	}

	public void setWaterHeaterStartTime(Timestamp waterHeaterStartTime) {
		this.waterHeaterStartTime = waterHeaterStartTime;
	}

	public Timestamp getWaterHeaterEndTime() {
		return this.waterHeaterEndTime;
	}

	public void setWaterHeaterEndTime(Timestamp waterHeaterEndTime) {
		this.waterHeaterEndTime = waterHeaterEndTime;
	}

	public Float getWaterTemperature() {
		return this.waterTemperature;
	}

	public void setWaterTemperature(Float waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	public Timestamp getWaterTemperatureDecisionTime() {
		return this.waterTemperatureDecisionTime;
	}

	public void setWaterTemperatureDecisionTime(
			Timestamp waterTemperatureDecisionTime) {
		this.waterTemperatureDecisionTime = waterTemperatureDecisionTime;
	}

	public Set<WaterHeaterRealTimeDecision> getWaterHeaterRealTimeDecisions() {
		return this.waterHeaterRealTimeDecisions;
	}

	public void setWaterHeaterRealTimeDecisions(
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

}
