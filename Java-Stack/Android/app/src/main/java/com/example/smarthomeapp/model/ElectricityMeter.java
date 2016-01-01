package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class ElectricityMeter implements java.io.Serializable {

	// Fields

	private Integer electricityMeterId;
	private CircuitLine circuitLine;
	private ElectricityType electricityType;
	private String electricityMeterIp;
	private Set<CircuitLine> circuitLines = new HashSet<CircuitLine>(0);
	private Set<ElectricityInfo> electricityInfos = new HashSet<ElectricityInfo>(
			0);

	// Constructors

	/** default constructor */
	public ElectricityMeter() {
	}

	/** full constructor */
	public ElectricityMeter(CircuitLine circuitLine,
			ElectricityType electricityType, String electricityMeterIp,
			Set<CircuitLine> circuitLines, Set<ElectricityInfo> electricityInfos) {
		this.circuitLine = circuitLine;
		this.electricityType = electricityType;
		this.electricityMeterIp = electricityMeterIp;
		this.circuitLines = circuitLines;
		this.electricityInfos = electricityInfos;
	}

	// Property accessors
	public Integer getElectricityMeterId() {
		return this.electricityMeterId;
	}

	public void setElectricityMeterId(Integer electricityMeterId) {
		this.electricityMeterId = electricityMeterId;
	}

	public CircuitLine getCircuitLine() {
		return this.circuitLine;
	}

	public void setCircuitLine(CircuitLine circuitLine) {
		this.circuitLine = circuitLine;
	}

	public ElectricityType getElectricityType() {
		return this.electricityType;
	}

	public void setElectricityType(ElectricityType electricityType) {
		this.electricityType = electricityType;
	}

	public String getElectricityMeterIp() {
		return this.electricityMeterIp;
	}

	public void setElectricityMeterIp(String electricityMeterIp) {
		this.electricityMeterIp = electricityMeterIp;
	}

	public Set<CircuitLine> getCircuitLines() {
		return this.circuitLines;
	}

	public void setCircuitLines(Set<CircuitLine> circuitLines) {
		this.circuitLines = circuitLines;
	}

	public Set<ElectricityInfo> getElectricityInfos() {
		return this.electricityInfos;
	}

	public void setElectricityInfos(Set<ElectricityInfo> electricityInfos) {
		this.electricityInfos = electricityInfos;
	}

}
