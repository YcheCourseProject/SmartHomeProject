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
 * WaterHeaterRealTimeDecision entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "water_heater_real_time_decision", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "water_heater_real_time_decision_id", unique = true, nullable = false)
	public Integer getWaterHeaterRealTimeDecisionId() {
		return this.waterHeaterRealTimeDecisionId;
	}

	public void setWaterHeaterRealTimeDecisionId(
			Integer waterHeaterRealTimeDecisionId) {
		this.waterHeaterRealTimeDecisionId = waterHeaterRealTimeDecisionId;
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
	@JoinColumn(name = "water_heater_control_id")
	public WaterHeaterControlDetail getWaterHeaterControlDetail() {
		return this.waterHeaterControlDetail;
	}

	public void setWaterHeaterControlDetail(
			WaterHeaterControlDetail waterHeaterControlDetail) {
		this.waterHeaterControlDetail = waterHeaterControlDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "water_heater_id")
	public WaterHeater getWaterHeater() {
		return this.waterHeater;
	}

	public void setWaterHeater(WaterHeater waterHeater) {
		this.waterHeater = waterHeater;
	}

	@Column(name = "water_heater_real_start_time", nullable = false, length = 0)
	public Timestamp getWaterHeaterRealStartTime() {
		return this.waterHeaterRealStartTime;
	}

	public void setWaterHeaterRealStartTime(Timestamp waterHeaterRealStartTime) {
		this.waterHeaterRealStartTime = waterHeaterRealStartTime;
	}

	@Column(name = "water_heater_real_end_time", nullable = false, length = 0)
	public Timestamp getWaterHeaterRealEndTime() {
		return this.waterHeaterRealEndTime;
	}

	public void setWaterHeaterRealEndTime(Timestamp waterHeaterRealEndTime) {
		this.waterHeaterRealEndTime = waterHeaterRealEndTime;
	}

	@Column(name = "water_heater_consume_average_energy", precision = 12, scale = 0)
	public Float getWaterHeaterConsumeAverageEnergy() {
		return this.waterHeaterConsumeAverageEnergy;
	}

	public void setWaterHeaterConsumeAverageEnergy(
			Float waterHeaterConsumeAverageEnergy) {
		this.waterHeaterConsumeAverageEnergy = waterHeaterConsumeAverageEnergy;
	}

	@Column(name = "water_heater_decision_record_time", nullable = false, length = 0)
	public Timestamp getWaterHeaterDecisionRecordTime() {
		return this.waterHeaterDecisionRecordTime;
	}

	public void setWaterHeaterDecisionRecordTime(
			Timestamp waterHeaterDecisionRecordTime) {
		this.waterHeaterDecisionRecordTime = waterHeaterDecisionRecordTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waterHeaterRealTimeDecision")
	public Set<WaterHeaterControlDetail> getWaterHeaterControlDetails() {
		return this.waterHeaterControlDetails;
	}

	public void setWaterHeaterControlDetails(
			Set<WaterHeaterControlDetail> waterHeaterControlDetails) {
		this.waterHeaterControlDetails = waterHeaterControlDetails;
	}

}