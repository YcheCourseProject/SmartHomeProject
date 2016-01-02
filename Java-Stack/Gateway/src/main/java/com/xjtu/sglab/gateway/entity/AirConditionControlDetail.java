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
 * AirConditionControlDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "air_condition_control_detail", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ari_condition_control_id", unique = true, nullable = false)
	public Integer getAriConditionControlId() {
		return this.ariConditionControlId;
	}

	public void setAriConditionControlId(Integer ariConditionControlId) {
		this.ariConditionControlId = ariConditionControlId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "air_condition_real_time_decision_id")
	public AirConditionRealTimeDecision getAirConditionRealTimeDecision() {
		return this.airConditionRealTimeDecision;
	}

	public void setAirConditionRealTimeDecision(
			AirConditionRealTimeDecision airConditionRealTimeDecision) {
		this.airConditionRealTimeDecision = airConditionRealTimeDecision;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_condition_id")
	public AirCondition getAirCondition() {
		return this.airCondition;
	}

	public void setAirCondition(AirCondition airCondition) {
		this.airCondition = airCondition;
	}

	@Column(name = "air_condition_control_start_time", nullable = false, length = 0)
	public Timestamp getAirConditionControlStartTime() {
		return this.airConditionControlStartTime;
	}

	public void setAirConditionControlStartTime(
			Timestamp airConditionControlStartTime) {
		this.airConditionControlStartTime = airConditionControlStartTime;
	}

	@Column(name = "air_condition_control_end_time", nullable = false, length = 0)
	public Timestamp getAirConditionControlEndTime() {
		return this.airConditionControlEndTime;
	}

	public void setAirConditionControlEndTime(
			Timestamp airConditionControlEndTime) {
		this.airConditionControlEndTime = airConditionControlEndTime;
	}

	@Column(name = "air_condition_temperature", nullable = false, precision = 12, scale = 0)
	public Float getAirConditionTemperature() {
		return this.airConditionTemperature;
	}

	public void setAirConditionTemperature(Float airConditionTemperature) {
		this.airConditionTemperature = airConditionTemperature;
	}

	@Column(name = "air_condition_mode", nullable = false)
	public Integer getAirConditionMode() {
		return this.airConditionMode;
	}

	public void setAirConditionMode(Integer airConditionMode) {
		this.airConditionMode = airConditionMode;
	}

	@Column(name = "air_condition_control_record_time", nullable = false, length = 0)
	public Timestamp getAirConditionControlRecordTime() {
		return this.airConditionControlRecordTime;
	}

	public void setAirConditionControlRecordTime(
			Timestamp airConditionControlRecordTime) {
		this.airConditionControlRecordTime = airConditionControlRecordTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "airConditionControlDetail")
	public Set<AirConditionRealTimeDecision> getAirConditionRealTimeDecisions() {
		return this.airConditionRealTimeDecisions;
	}

	public void setAirConditionRealTimeDecisions(
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions) {
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
	}

}