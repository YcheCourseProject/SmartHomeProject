package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
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
	public Integer getWaterHeaterId() {
		return this.waterHeaterId;
	}

	public void setWaterHeaterId(Integer waterHeaterId) {
		this.waterHeaterId = waterHeaterId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Float getWaterHeaterRatedPower() {
		return this.waterHeaterRatedPower;
	}

	public void setWaterHeaterRatedPower(Float waterHeaterRatedPower) {
		this.waterHeaterRatedPower = waterHeaterRatedPower;
	}

	public Set<WaterHeaterControlDetail> getWaterHeaterControlDetails() {
		return this.waterHeaterControlDetails;
	}

	public void setWaterHeaterControlDetails(
			Set<WaterHeaterControlDetail> waterHeaterControlDetails) {
		this.waterHeaterControlDetails = waterHeaterControlDetails;
	}

	public Set<WaterHeaterStatus> getWaterHeaterStatuses() {
		return this.waterHeaterStatuses;
	}

	public void setWaterHeaterStatuses(
			Set<WaterHeaterStatus> waterHeaterStatuses) {
		this.waterHeaterStatuses = waterHeaterStatuses;
	}

	public Set<WaterHeaterRealTimeDecision> getWaterHeaterRealTimeDecisions() {
		return this.waterHeaterRealTimeDecisions;
	}

	public void setWaterHeaterRealTimeDecisions(
			Set<WaterHeaterRealTimeDecision> waterHeaterRealTimeDecisions) {
		this.waterHeaterRealTimeDecisions = waterHeaterRealTimeDecisions;
	}

}
