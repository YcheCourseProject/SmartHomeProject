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
 * WaterHeater entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "water_heater", catalog = "smarthome")
public class WaterHeater implements java.io.Serializable {

	// Fields

	private Integer waterHeaterId;
	private Box box;
	private Float waterHeaterRatedPower;
	private Set<WaterHeaterControlDetail> waterHeaterControlDetails = new HashSet<WaterHeaterControlDetail>(
			0);
	private Set<WaterHeaterStatus> waterHeaterStatuses = new HashSet<WaterHeaterStatus>(
			0);
	private Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions = new HashSet<WaterHeaterRealTimeDecision>(
			0);

	// Constructors

	/** default constructor */
	public WaterHeater() {
	}

	/** full constructor */
	public WaterHeater(Box box, Float waterHeaterRatedPower,
			Set<WaterHeaterControlDetail> waterHeaterControlDetails,
			Set<WaterHeaterStatus> waterHeaterStatuses,
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.box = box;
		this.waterHeaterRatedPower = waterHeaterRatedPower;
		this.waterHeaterControlDetails = waterHeaterControlDetails;
		this.waterHeaterStatuses = waterHeaterStatuses;
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "water_heater_id", unique = true, nullable = false)
	public Integer getWaterHeaterId() {
		return this.waterHeaterId;
	}

	public void setWaterHeaterId(Integer waterHeaterId) {
		this.waterHeaterId = waterHeaterId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@Column(name = "water_heater_rated_power", precision = 12, scale = 0)
	public Float getWaterHeaterRatedPower() {
		return this.waterHeaterRatedPower;
	}

	public void setWaterHeaterRatedPower(Float waterHeaterRatedPower) {
		this.waterHeaterRatedPower = waterHeaterRatedPower;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waterHeater")
	public Set<WaterHeaterControlDetail> getWaterHeaterControlDetails() {
		return this.waterHeaterControlDetails;
	}

	public void setWaterHeaterControlDetails(
			Set<WaterHeaterControlDetail> waterHeaterControlDetails) {
		this.waterHeaterControlDetails = waterHeaterControlDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waterHeater")
	public Set<WaterHeaterStatus> getWaterHeaterStatuses() {
		return this.waterHeaterStatuses;
	}

	public void setWaterHeaterStatuses(
			Set<WaterHeaterStatus> waterHeaterStatuses) {
		this.waterHeaterStatuses = waterHeaterStatuses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waterHeater")
	public Set<WaterHeaterRealTimeDecision> getWaterHeaterRealTimeDecisions() {
		return this.waterHeaterRealTimeDecisions;
	}

	public void setWaterHeaterRealTimeDecisions(
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

}