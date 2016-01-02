package com.xjtu.sglab.gateway.entity;

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
 * AirCondition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "air_condition", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "air_condition_id", unique = true, nullable = false)
	public Integer getAirConditionId() {
		return this.airConditionId;
	}

	public void setAirConditionId(Integer airConditionId) {
		this.airConditionId = airConditionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@Column(name = "air_condition_ip", length = 15)
	public String getAirConditionIp() {
		return this.airConditionIp;
	}

	public void setAirConditionIp(String airConditionIp) {
		this.airConditionIp = airConditionIp;
	}

	@Column(name = "air_condition_rated_power", precision = 12, scale = 0)
	public Float getAirConditionRatedPower() {
		return this.airConditionRatedPower;
	}

	public void setAirConditionRatedPower(Float airConditionRatedPower) {
		this.airConditionRatedPower = airConditionRatedPower;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "airCondition")
	public Set<AirConditionControlDetail> getAirConditionControlDetails() {
		return this.airConditionControlDetails;
	}

	public void setAirConditionControlDetails(
			Set<AirConditionControlDetail> airConditionControlDetails) {
		this.airConditionControlDetails = airConditionControlDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "airCondition")
	public Set<AirConditionRealTimeDecision> getAirConditionRealTimeDecisions() {
		return this.airConditionRealTimeDecisions;
	}

	public void setAirConditionRealTimeDecisions(
			Set<AirConditionRealTimeDecision> airConditionRealTimeDecisions) {
		this.airConditionRealTimeDecisions = airConditionRealTimeDecisions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "airCondition")
	public Set<AirConditionStatus> getAirConditionStatuses() {
		return this.airConditionStatuses;
	}

	public void setAirConditionStatuses(
			Set<AirConditionStatus> airConditionStatuses) {
		this.airConditionStatuses = airConditionStatuses;
	}

}