package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class AirCondition implements java.io.Serializable {

	// Fields

	private Integer airConditionId;
	private Box box;
	private String airConditionIp;
	private Float airConditionRatedPower;
	private Set<AirConditionControlDetail> airConditionControlDetails = new HashSet<AirConditionControlDetail>(
			0);
	private Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions = new HashSet<AirConditionRealTimeDecision>(
			0);
	private Set<AirConditionStatus> airConditionStatuses = new HashSet<AirConditionStatus>(
			0);

	// Constructors

	/** default constructor */
	public AirCondition() {
	}

	/** full constructor */
	public AirCondition(Box box, String airConditionIp,
			Float airConditionRatedPower,
			Set<AirConditionControlDetail> airConditionControlDetails,
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions,
			Set<AirConditionStatus> airConditionStatuses) {
		this.box = box;
		this.airConditionIp = airConditionIp;
		this.airConditionRatedPower = airConditionRatedPower;
		this.airConditionControlDetails = airConditionControlDetails;
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
		this.airConditionStatuses = airConditionStatuses;
	}

	// Property accessors
	public Integer getAirConditionId() {
		return this.airConditionId;
	}

	public void setAirConditionId(Integer airConditionId) {
		this.airConditionId = airConditionId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public String getAirConditionIp() {
		return this.airConditionIp;
	}

	public void setAirConditionIp(String airConditionIp) {
		this.airConditionIp = airConditionIp;
	}

	public Float getAirConditionRatedPower() {
		return this.airConditionRatedPower;
	}

	public void setAirConditionRatedPower(Float airConditionRatedPower) {
		this.airConditionRatedPower = airConditionRatedPower;
	}

	public Set<AirConditionControlDetail> getAirConditionControlDetails() {
		return this.airConditionControlDetails;
	}

	public void setAirConditionControlDetails(
			Set<AirConditionControlDetail> airConditionControlDetails) {
		this.airConditionControlDetails = airConditionControlDetails;
	}

	public Set<AirConditionRealTimeDecision> getAirConditionRealTimeDecisions() {
		return this.airConditionRealTimeDecisions;
	}

	public void setAirConditionRealTimeDecisions(
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions) {
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
	}

	public Set<AirConditionStatus> getAirConditionStatuses() {
		return this.airConditionStatuses;
	}

	public void setAirConditionStatuses(
			Set<AirConditionStatus> airConditionStatuses) {
		this.airConditionStatuses = airConditionStatuses;
	}

}
