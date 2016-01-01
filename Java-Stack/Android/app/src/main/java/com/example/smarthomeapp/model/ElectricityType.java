package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class ElectricityType implements java.io.Serializable {

	// Fields

	private Integer electricityTypeId;
	private String electricityType;
	private Set<ElectricityMeter> electricityMeters = new HashSet<ElectricityMeter>(
			0);

	// Constructors

	/** default constructor */
	public ElectricityType() {
	}

	/** minimal constructor */
	public ElectricityType(String electricityType) {
		this.electricityType = electricityType;
	}

	/** full constructor */
	public ElectricityType(String electricityType,
			Set<ElectricityMeter> electricityMeters) {
		this.electricityType = electricityType;
		this.electricityMeters = electricityMeters;
	}

	// Property accessors
	public Integer getElectricityTypeId() {
		return this.electricityTypeId;
	}

	public void setElectricityTypeId(Integer electricityTypeId) {
		this.electricityTypeId = electricityTypeId;
	}

	public String getElectricityType() {
		return this.electricityType;
	}

	public void setElectricityType(String electricityType) {
		this.electricityType = electricityType;
	}

	public Set<ElectricityMeter> getElectricityMeters() {
		return this.electricityMeters;
	}

	public void setElectricityMeters(Set<ElectricityMeter> electricityMeters) {
		this.electricityMeters = electricityMeters;
	}

}
