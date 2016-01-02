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
 * AirConditionRealTimeDecision entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "air_condition_real_time_decision", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "air_condition_real_time_decision_id", unique = true, nullable = false)
	public Integer getAirConditionRealTimeDecisionId() {
		return this.airConditionRealTimeDecisionId;
	}

	public void setAirConditionRealTimeDecisionId(
			Integer airConditionRealTimeDecisionId) {
		this.airConditionRealTimeDecisionId = airConditionRealTimeDecisionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "real_time_decision_id")
	public RealTimeDecision getRealTimeDecision() {
		return this.realTimeDecision;
	}

	public void setRealTimeDecision(RealTimeDecision realTimeDecision) {
		this.realTimeDecision = realTimeDecision;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_condition_id")
	public AirCondition getAirCondition() {
		return this.airCondition;
	}

	public void setAirCondition(AirCondition airCondition) {
		this.airCondition = airCondition;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ari_condition_control_id")
	public AirConditionControlDetail getAirConditionControlDetail() {
		return this.airConditionControlDetail;
	}

	public void setAirConditionControlDetail(
			AirConditionControlDetail airConditionControlDetail) {
		this.airConditionControlDetail = airConditionControlDetail;
	}

	@Column(name = "air_condition_decide_start_time", nullable = false, length = 0)
	public Timestamp getAirConditionDecideStartTime() {
		return this.airConditionDecideStartTime;
	}

	public void setAirConditionDecideStartTime(
			Timestamp airConditionDecideStartTime) {
		this.airConditionDecideStartTime = airConditionDecideStartTime;
	}

	@Column(name = "air_condition_decide_end_time", nullable = false, length = 0)
	public Timestamp getAirConditionDecideEndTime() {
		return this.airConditionDecideEndTime;
	}

	public void setAirConditionDecideEndTime(Timestamp airConditionDecideEndTime) {
		this.airConditionDecideEndTime = airConditionDecideEndTime;
	}

	@Column(name = "air_condition_decide_average_energy", nullable = false, precision = 12, scale = 0)
	public Float getAirConditionDecideAverageEnergy() {
		return this.airConditionDecideAverageEnergy;
	}

	public void setAirConditionDecideAverageEnergy(
			Float airConditionDecideAverageEnergy) {
		this.airConditionDecideAverageEnergy = airConditionDecideAverageEnergy;
	}

	@Column(name = "air_condition_decision_record_time", nullable = false, length = 0)
	public Timestamp getAirConditionDecisionRecordTime() {
		return this.airConditionDecisionRecordTime;
	}

	public void setAirConditionDecisionRecordTime(
			Timestamp airConditionDecisionRecordTime) {
		this.airConditionDecisionRecordTime = airConditionDecisionRecordTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "airConditionRealTimeDecision")
	public Set<AirConditionControlDetail> getAirConditionControlDetails() {
		return this.airConditionControlDetails;
	}

	public void setAirConditionControlDetails(
			Set<AirConditionControlDetail> airConditionControlDetails) {
		this.airConditionControlDetails = airConditionControlDetails;
	}

}