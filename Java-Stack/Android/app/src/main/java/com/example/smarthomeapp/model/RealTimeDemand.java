package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class RealTimeDemand implements java.io.Serializable {

	// Fields

	private Integer realTimeDemandId;
	private Timestamp realTimeDemandRecordTime;
	private Set<RealTimeDecision> realTimeDecisions = new HashSet<RealTimeDecision>(
			0);
	private Set<AirConditionDemand> airConditionDemands = new HashSet<AirConditionDemand>(
			0);
	private Set<WaterHeaterDemand> waterHeaterDemands = new HashSet<WaterHeaterDemand>(
			0);

	// Constructors

	/** default constructor */
	public RealTimeDemand() {
	}

	/** minimal constructor */
	public RealTimeDemand(Timestamp realTimeDemandRecordTime) {
		this.realTimeDemandRecordTime = realTimeDemandRecordTime;
	}

	/** full constructor */
	public RealTimeDemand(Timestamp realTimeDemandRecordTime,
			Set<RealTimeDecision> realTimeDecisions,
			Set<AirConditionDemand> airConditionDemands,
			Set<WaterHeaterDemand> waterHeaterDemands) {
		this.realTimeDemandRecordTime = realTimeDemandRecordTime;
		this.realTimeDecisions = realTimeDecisions;
		this.airConditionDemands = airConditionDemands;
		this.waterHeaterDemands = waterHeaterDemands;
	}

	// Property accessors
	public Integer getRealTimeDemandId() {
		return this.realTimeDemandId;
	}

	public void setRealTimeDemandId(Integer realTimeDemandId) {
		this.realTimeDemandId = realTimeDemandId;
	}

	public Timestamp getRealTimeDemandRecordTime() {
		return this.realTimeDemandRecordTime;
	}

	public void setRealTimeDemandRecordTime(Timestamp realTimeDemandRecordTime) {
		this.realTimeDemandRecordTime = realTimeDemandRecordTime;
	}

	public Set<RealTimeDecision> getRealTimeDecisions() {
		return this.realTimeDecisions;
	}

	public void setRealTimeDecisions(Set<RealTimeDecision> realTimeDecisions) {
		this.realTimeDecisions = realTimeDecisions;
	}

	public Set<AirConditionDemand> getAirConditionDemands() {
		return this.airConditionDemands;
	}

	public void setAirConditionDemands(
			Set<AirConditionDemand> airConditionDemands) {
		this.airConditionDemands = airConditionDemands;
	}

	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

}
