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
 * RealTimeDecision entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "real_time_decision", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "real_time_decision_id", unique = true, nullable = false)
	public Integer getRealTimeDecisionId() {
		return this.realTimeDecisionId;
	}

	public void setRealTimeDecisionId(Integer realTimeDecisionId) {
		this.realTimeDecisionId = realTimeDecisionId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "real_time_demand_id")
	public RealTimeDemand getRealTimeDemand() {
		return this.realTimeDemand;
	}

	public void setRealTimeDemand(RealTimeDemand realTimeDemand) {
		this.realTimeDemand = realTimeDemand;
	}

	@Column(name = "real_time_decision_record_time", nullable = false, length = 0)
	public Timestamp getRealTimeDecisionRecordTime() {
		return this.realTimeDecisionRecordTime;
	}

	public void setRealTimeDecisionRecordTime(
			Timestamp realTimeDecisionRecordTime) {
		this.realTimeDecisionRecordTime = realTimeDecisionRecordTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "realTimeDecision")
	public Set<AirConditionRealTimeDecision> getAirConditionRealTimeDecisions() {
		return this.airConditionRealTimeDecisions;
	}

	public void setAirConditionRealTimeDecisions(
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions) {
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "realTimeDecision")
	public Set<WaterHeaterRealTimeDecision> getWaterHeaterRealTimeDecisions() {
		return this.waterHeaterRealTimeDecisions;
	}

	public void setWaterHeaterRealTimeDecisions(
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

}