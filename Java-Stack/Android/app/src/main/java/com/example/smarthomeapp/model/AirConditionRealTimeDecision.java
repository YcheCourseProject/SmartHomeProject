package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class AirConditionRealTimeDecision implements java.io.Serializable {

	// Fields

	private Integer airConditionRealTimeDecisionId;
	private RealTimeDecision realTimeDecision;
	private AirCondition airCondition;
	private AirConditionControlDetail airConditionControlDetail;
	private Timestamp airConditionDecideStartTime;
	private Timestamp airConditionDecideEndTime;
	private Float airConditionDecideAverageEnergy;
	private Timestamp airConditionDecisionRecordTime;
	private Set<AirConditionControlDetail> airConditionControlDetails = new HashSet<AirConditionControlDetail>(
			0);

	// Constructors

	/** default constructor */
	public AirConditionRealTimeDecision() {
	}

	/** minimal constructor */
	public AirConditionRealTimeDecision(Timestamp airConditionDecideStartTime,
			Timestamp airConditionDecideEndTime,
			Float airConditionDecideAverageEnergy,
			Timestamp airConditionDecisionRecordTime) {
		this.airConditionDecideStartTime = airConditionDecideStartTime;
		this.airConditionDecideEndTime = airConditionDecideEndTime;
		this.airConditionDecideAverageEnergy = airConditionDecideAverageEnergy;
		this.airConditionDecisionRecordTime = airConditionDecisionRecordTime;
	}

	/** full constructor */
	public AirConditionRealTimeDecision(RealTimeDecision realTimeDecision,
			AirCondition airCondition,
			AirConditionControlDetail airConditionControlDetail,
			Timestamp airConditionDecideStartTime,
			Timestamp airConditionDecideEndTime,
			Float airConditionDecideAverageEnergy,
			Timestamp airConditionDecisionRecordTime,
			Set<AirConditionControlDetail> airConditionControlDetails) {
		this.realTimeDecision = realTimeDecision;
		this.airCondition = airCondition;
		this.airConditionControlDetail = airConditionControlDetail;
		this.airConditionDecideStartTime = airConditionDecideStartTime;
		this.airConditionDecideEndTime = airConditionDecideEndTime;
		this.airConditionDecideAverageEnergy = airConditionDecideAverageEnergy;
		this.airConditionDecisionRecordTime = airConditionDecisionRecordTime;
		this.airConditionControlDetails = airConditionControlDetails;
	}

	// Property accessors
	public Integer getAirConditionRealTimeDecisionId() {
		return this.airConditionRealTimeDecisionId;
	}

	public void setAirConditionRealTimeDecisionId(
			Integer airConditionRealTimeDecisionId) {
		this.airConditionRealTimeDecisionId = airConditionRealTimeDecisionId;
	}

	public RealTimeDecision getRealTimeDecision() {
		return this.realTimeDecision;
	}

	public void setRealTimeDecision(RealTimeDecision realTimeDecision) {
		this.realTimeDecision = realTimeDecision;
	}

	public AirCondition getAirCondition() {
		return this.airCondition;
	}

	public void setAirCondition(AirCondition airCondition) {
		this.airCondition = airCondition;
	}

	public AirConditionControlDetail getAirConditionControlDetail() {
		return this.airConditionControlDetail;
	}

	public void setAirConditionControlDetail(
			AirConditionControlDetail airConditionControlDetail) {
		this.airConditionControlDetail = airConditionControlDetail;
	}

	public Timestamp getAirConditionDecideStartTime() {
		return this.airConditionDecideStartTime;
	}

	public void setAirConditionDecideStartTime(
			Timestamp airConditionDecideStartTime) {
		this.airConditionDecideStartTime = airConditionDecideStartTime;
	}

	public Timestamp getAirConditionDecideEndTime() {
		return this.airConditionDecideEndTime;
	}

	public void setAirConditionDecideEndTime(Timestamp airConditionDecideEndTime) {
		this.airConditionDecideEndTime = airConditionDecideEndTime;
	}

	public Float getAirConditionDecideAverageEnergy() {
		return this.airConditionDecideAverageEnergy;
	}

	public void setAirConditionDecideAverageEnergy(
			Float airConditionDecideAverageEnergy) {
		this.airConditionDecideAverageEnergy = airConditionDecideAverageEnergy;
	}

	public Timestamp getAirConditionDecisionRecordTime() {
		return this.airConditionDecisionRecordTime;
	}

	public void setAirConditionDecisionRecordTime(
			Timestamp airConditionDecisionRecordTime) {
		this.airConditionDecisionRecordTime = airConditionDecisionRecordTime;
	}

	public Set<AirConditionControlDetail> getAirConditionControlDetails() {
		return this.airConditionControlDetails;
	}

	public void setAirConditionControlDetails(
			Set<AirConditionControlDetail> airConditionControlDetails) {
		this.airConditionControlDetails = airConditionControlDetails;
	}

}
