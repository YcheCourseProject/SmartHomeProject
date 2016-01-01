package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class AirConditionControlDetail implements java.io.Serializable {

	// Fields

	private Integer ariConditionControlId;
	private AirConditionRealTimeDecision airConditionRealTimeDecision;
	private AirCondition airCondition;
	private Timestamp airConditionControlStartTime;
	private Timestamp airConditionControlEndTime;
	private Float airConditionTemperature;
	private Integer airConditionMode;
	private Timestamp airConditionControlRecordTime;
	private Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions = new HashSet<AirConditionRealTimeDecision>(
			0);

	// Constructors

	/** default constructor */
	public AirConditionControlDetail() {
	}

	/** minimal constructor */
	public AirConditionControlDetail(Timestamp airConditionControlStartTime,
			Timestamp airConditionControlEndTime,
			Float airConditionTemperature, Integer airConditionMode,
			Timestamp airConditionControlRecordTime) {
		this.airConditionControlStartTime = airConditionControlStartTime;
		this.airConditionControlEndTime = airConditionControlEndTime;
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionMode = airConditionMode;
		this.airConditionControlRecordTime = airConditionControlRecordTime;
	}

	/** full constructor */
	public AirConditionControlDetail(
			AirConditionRealTimeDecision airConditionRealTimeDecision,
			AirCondition airCondition, Timestamp airConditionControlStartTime,
			Timestamp airConditionControlEndTime,
			Float airConditionTemperature, Integer airConditionMode,
			Timestamp airConditionControlRecordTime,
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions) {
		this.airConditionRealTimeDecision = airConditionRealTimeDecision;
		this.airCondition = airCondition;
		this.airConditionControlStartTime = airConditionControlStartTime;
		this.airConditionControlEndTime = airConditionControlEndTime;
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionMode = airConditionMode;
		this.airConditionControlRecordTime = airConditionControlRecordTime;
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
	}

	// Property accessors
	public Integer getAriConditionControlId() {
		return this.ariConditionControlId;
	}

	public void setAriConditionControlId(Integer ariConditionControlId) {
		this.ariConditionControlId = ariConditionControlId;
	}

	public AirConditionRealTimeDecision getAirConditionRealTimeDecision() {
		return this.airConditionRealTimeDecision;
	}

	public void setAirConditionRealTimeDecision(
			AirConditionRealTimeDecision airConditionRealTimeDecision) {
		this.airConditionRealTimeDecision = airConditionRealTimeDecision;
	}

	public AirCondition getAirCondition() {
		return this.airCondition;
	}

	public void setAirCondition(AirCondition airCondition) {
		this.airCondition = airCondition;
	}

	public Timestamp getAirConditionControlStartTime() {
		return this.airConditionControlStartTime;
	}

	public void setAirConditionControlStartTime(
			Timestamp airConditionControlStartTime) {
		this.airConditionControlStartTime = airConditionControlStartTime;
	}

	public Timestamp getAirConditionControlEndTime() {
		return this.airConditionControlEndTime;
	}

	public void setAirConditionControlEndTime(
			Timestamp airConditionControlEndTime) {
		this.airConditionControlEndTime = airConditionControlEndTime;
	}

	public Float getAirConditionTemperature() {
		return this.airConditionTemperature;
	}

	public void setAirConditionTemperature(Float airConditionTemperature) {
		this.airConditionTemperature = airConditionTemperature;
	}

	public Integer getAirConditionMode() {
		return this.airConditionMode;
	}

	public void setAirConditionMode(Integer airConditionMode) {
		this.airConditionMode = airConditionMode;
	}

	public Timestamp getAirConditionControlRecordTime() {
		return this.airConditionControlRecordTime;
	}

	public void setAirConditionControlRecordTime(
			Timestamp airConditionControlRecordTime) {
		this.airConditionControlRecordTime = airConditionControlRecordTime;
	}

	public Set<AirConditionRealTimeDecision> getAirConditionRealTimeDecisions() {
		return this.airConditionRealTimeDecisions;
	}

	public void setAirConditionRealTimeDecisions(
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions) {
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
	}

}
