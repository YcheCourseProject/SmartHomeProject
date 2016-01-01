package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class Lamp implements java.io.Serializable {

	// Fields

	private Integer lampId;
	private Box box;
	private Integer lampType;
	private Float lampRatedPower;
	private Integer lampLocation;
	private Set<LampStatus> lampStatuses = new HashSet<LampStatus>(0);

	// Constructors

	/** default constructor */
	public Lamp() {
	}

	/** full constructor */
	public Lamp(Box box, Integer lampType, Float lampRatedPower,
			Integer lampLocation, Set<LampStatus> lampStatuses) {
		this.box = box;
		this.lampType = lampType;
		this.lampRatedPower = lampRatedPower;
		this.lampLocation = lampLocation;
		this.lampStatuses = lampStatuses;
	}

	// Property accessors
	public Integer getLampId() {
		return this.lampId;
	}

	public void setLampId(Integer lampId) {
		this.lampId = lampId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Integer getLampType() {
		return this.lampType;
	}

	public void setLampType(Integer lampType) {
		this.lampType = lampType;
	}

	public Float getLampRatedPower() {
		return this.lampRatedPower;
	}

	public void setLampRatedPower(Float lampRatedPower) {
		this.lampRatedPower = lampRatedPower;
	}

	public Integer getLampLocation() {
		return this.lampLocation;
	}

	public void setLampLocation(Integer lampLocation) {
		this.lampLocation = lampLocation;
	}

	public Set<LampStatus> getLampStatuses() {
		return this.lampStatuses;
	}

	public void setLampStatuses(Set<LampStatus> lampStatuses) {
		this.lampStatuses = lampStatuses;
	}

}
