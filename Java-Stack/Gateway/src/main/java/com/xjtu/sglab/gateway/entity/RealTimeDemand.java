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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RealTimeDemand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "real_time_demand", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "real_time_demand_id", unique = true, nullable = false)
	public Integer getRealTimeDemandId() {
		return this.realTimeDemandId;
	}

	public void setRealTimeDemandId(Integer realTimeDemandId) {
		this.realTimeDemandId = realTimeDemandId;
	}

	@Column(name = "real_time_demand_record_time", nullable = false, length = 0)
	public Timestamp getRealTimeDemandRecordTime() {
		return this.realTimeDemandRecordTime;
	}

	public void setRealTimeDemandRecordTime(Timestamp realTimeDemandRecordTime) {
		this.realTimeDemandRecordTime = realTimeDemandRecordTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "realTimeDemand")
	public Set<RealTimeDecision> getRealTimeDecisions() {
		return this.realTimeDecisions;
	}

	public void setRealTimeDecisions(Set<RealTimeDecision> realTimeDecisions) {
		this.realTimeDecisions = realTimeDecisions;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "has_air_condition_demand", catalog = "smarthome", joinColumns = { @JoinColumn(name = "real_time_demand_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "air_condition_demand_id", nullable = false, updatable = false) })
	public Set<AirConditionDemand> getAirConditionDemands() {
		return this.airConditionDemands;
	}

	public void setAirConditionDemands(
			Set<AirConditionDemand> airConditionDemands) {
		this.airConditionDemands = airConditionDemands;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "realTimeDemands")
	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

}