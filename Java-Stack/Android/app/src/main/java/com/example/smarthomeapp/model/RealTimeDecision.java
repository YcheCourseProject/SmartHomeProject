package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class RealTimeDecision implements java.io.Serializable {

	// Fields

	private Integer realTimeDecisionId;
	private RealTimeDemand realTimeDemand;
	private Timestamp realTimeDecisionRecordTime;
	private Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions = new HashSet<AirConditionRealTimeDecision>(
			0);
	private Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions = new HashSet<WaterHeaterRealTimeDecision>(
			0);

	// Constructors

	/** default constructor */
	public RealTimeDecision() {
	}

	/** minimal constructor */
	public RealTimeDecision(Timestamp realTimeDecisionRecordTime) {
		this.realTimeDecisionRecordTime = realTimeDecisionRecordTime;
	}

	/** full constructor */
	public RealTimeDecision(RealTimeDemand realTimeDemand,
			Timestamp realTimeDecisionRecordTime,
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions,
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.realTimeDemand = realTimeDemand;
		this.realTimeDecisionRecordTime = realTimeDecisionRecordTime;
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

	// Property accessors
	public Integer getRealTimeDecisionId() {
		return this.realTimeDecisionId;
	}

	public void setRealTimeDecisionId(Integer realTimeDecisionId) {
		this.realTimeDecisionId = realTimeDecisionId;
	}

	public RealTimeDemand getRealTimeDemand() {
		return this.realTimeDemand;
	}

	public void setRealTimeDemand(RealTimeDemand realTimeDemand) {
		this.realTimeDemand = realTimeDemand;
	}

	public Timestamp getRealTimeDecisionRecordTime() {
		return this.realTimeDecisionRecordTime;
	}

	public void setRealTimeDecisionRecordTime(
			Timestamp realTimeDecisionRecordTime) {
		this.realTimeDecisionRecordTime = realTimeDecisionRecordTime;
	}

	public Set<AirConditionRealTimeDecision> getAirConditionRealTimeDecisions() {
		return this.airConditionRealTimeDecisions;
	}

	public void setAirConditionRealTimeDecisions(
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions) {
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
	}

	public Set<WaterHeaterRealTimeDecision> getWaterHeaterRealTimeDecisions() {
		return this.waterHeaterRealTimeDecisions;
	}

	public void setWaterHeaterRealTimeDecisions(
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

}
