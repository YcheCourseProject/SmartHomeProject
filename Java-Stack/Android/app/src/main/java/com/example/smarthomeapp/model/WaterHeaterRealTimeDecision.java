package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class WaterHeaterRealTimeDecision implements java.io.Serializable {

	// Fields

	private Integer waterHeaterRealTimeDecisionId;
	private RealTimeDecision realTimeDecision;
	private WaterHeaterControlDetail waterHeaterControlDetail;
	private WaterHeater waterHeater;
	private Timestamp waterHeaterRealStartTime;
	private Timestamp waterHeaterRealEndTime;
	private Float waterHeaterConsumeAverageEnergy;
	private Timestamp waterHeaterDecisionRecordTime;
	private Set<WaterHeaterControlDetail> waterHeaterControlDetails = new HashSet<WaterHeaterControlDetail>(
			0);

	// Constructors

	/** default constructor */
	public WaterHeaterRealTimeDecision() {
	}

	/** minimal constructor */
	public WaterHeaterRealTimeDecision(Timestamp waterHeaterRealStartTime,
			Timestamp waterHeaterRealEndTime,
			Timestamp waterHeaterDecisionRecordTime) {
		this.waterHeaterRealStartTime = waterHeaterRealStartTime;
		this.waterHeaterRealEndTime = waterHeaterRealEndTime;
		this.waterHeaterDecisionRecordTime = waterHeaterDecisionRecordTime;
	}

	/** full constructor */
	public WaterHeaterRealTimeDecision(RealTimeDecision realTimeDecision,
			WaterHeaterControlDetail waterHeaterControlDetail,
			WaterHeater waterHeater, Timestamp waterHeaterRealStartTime,
			Timestamp waterHeaterRealEndTime,
			Float waterHeaterConsumeAverageEnergy,
			Timestamp waterHeaterDecisionRecordTime,
			Set<WaterHeaterControlDetail> waterHeaterControlDetails) {
		this.realTimeDecision = realTimeDecision;
		this.waterHeaterControlDetail = waterHeaterControlDetail;
		this.waterHeater = waterHeater;
		this.waterHeaterRealStartTime = waterHeaterRealStartTime;
		this.waterHeaterRealEndTime = waterHeaterRealEndTime;
		this.waterHeaterConsumeAverageEnergy = waterHeaterConsumeAverageEnergy;
		this.waterHeaterDecisionRecordTime = waterHeaterDecisionRecordTime;
		this.waterHeaterControlDetails = waterHeaterControlDetails;
	}

	// Property accessors
	public Integer getWaterHeaterRealTimeDecisionId() {
		return this.waterHeaterRealTimeDecisionId;
	}

	public void setWaterHeaterRealTimeDecisionId(
			Integer waterHeaterRealTimeDecisionId) {
		this.waterHeaterRealTimeDecisionId = waterHeaterRealTimeDecisionId;
	}

	public RealTimeDecision getRealTimeDecision() {
		return this.realTimeDecision;
	}

	public void setRealTimeDecision(RealTimeDecision realTimeDecision) {
		this.realTimeDecision = realTimeDecision;
	}

	public WaterHeaterControlDetail getWaterHeaterControlDetail() {
		return this.waterHeaterControlDetail;
	}

	public void setWaterHeaterControlDetail(
			WaterHeaterControlDetail waterHeaterControlDetail) {
		this.waterHeaterControlDetail = waterHeaterControlDetail;
	}

	public WaterHeater getWaterHeater() {
		return this.waterHeater;
	}

	public void setWaterHeater(WaterHeater waterHeater) {
		this.waterHeater = waterHeater;
	}

	public Timestamp getWaterHeaterRealStartTime() {
		return this.waterHeaterRealStartTime;
	}

	public void setWaterHeaterRealStartTime(Timestamp waterHeaterRealStartTime) {
		this.waterHeaterRealStartTime = waterHeaterRealStartTime;
	}

	public Timestamp getWaterHeaterRealEndTime() {
		return this.waterHeaterRealEndTime;
	}

	public void setWaterHeaterRealEndTime(Timestamp waterHeaterRealEndTime) {
		this.waterHeaterRealEndTime = waterHeaterRealEndTime;
	}

	public Float getWaterHeaterConsumeAverageEnergy() {
		return this.waterHeaterConsumeAverageEnergy;
	}

	public void setWaterHeaterConsumeAverageEnergy(
			Float waterHeaterConsumeAverageEnergy) {
		this.waterHeaterConsumeAverageEnergy = waterHeaterConsumeAverageEnergy;
	}

	public Timestamp getWaterHeaterDecisionRecordTime() {
		return this.waterHeaterDecisionRecordTime;
	}

	public void setWaterHeaterDecisionRecordTime(
			Timestamp waterHeaterDecisionRecordTime) {
		this.waterHeaterDecisionRecordTime = waterHeaterDecisionRecordTime;
	}

	public Set<WaterHeaterControlDetail> getWaterHeaterControlDetails() {
		return this.waterHeaterControlDetails;
	}

	public void setWaterHeaterControlDetails(
			Set<WaterHeaterControlDetail> waterHeaterControlDetails) {
		this.waterHeaterControlDetails = waterHeaterControlDetails;
	}

}
