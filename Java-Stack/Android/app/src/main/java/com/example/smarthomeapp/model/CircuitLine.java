package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class CircuitLine implements java.io.Serializable {

	// Fields

	private Integer circuitLineId;
	private CircuitLine circuitLine;
	private ElectricityMeter electricityMeter;
	private Room room;
	private String circuitLineDescription;
	private Set<ElectricityMeter> electricityMeters = new HashSet<ElectricityMeter>(
			0);
	private Set<CircuitLine> circuitLines = new HashSet<CircuitLine>(0);

	// Constructors

	/** default constructor */
	public CircuitLine() {
	}

	/** full constructor */
	public CircuitLine(CircuitLine circuitLine,
			ElectricityMeter electricityMeter, Room room,
			String circuitLineDescription,
			Set<ElectricityMeter> electricityMeters,
			Set<CircuitLine> circuitLines) {
		this.circuitLine = circuitLine;
		this.electricityMeter = electricityMeter;
		this.room = room;
		this.circuitLineDescription = circuitLineDescription;
		this.electricityMeters = electricityMeters;
		this.circuitLines = circuitLines;
	}

	// Property accessors
	public Integer getCircuitLineId() {
		return this.circuitLineId;
	}

	public void setCircuitLineId(Integer circuitLineId) {
		this.circuitLineId = circuitLineId;
	}

	public CircuitLine getCircuitLine() {
		return this.circuitLine;
	}

	public void setCircuitLine(CircuitLine circuitLine) {
		this.circuitLine = circuitLine;
	}

	public ElectricityMeter getElectricityMeter() {
		return this.electricityMeter;
	}

	public void setElectricityMeter(ElectricityMeter electricityMeter) {
		this.electricityMeter = electricityMeter;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getCircuitLineDescription() {
		return this.circuitLineDescription;
	}

	public void setCircuitLineDescription(String circuitLineDescription) {
		this.circuitLineDescription = circuitLineDescription;
	}

	public Set<ElectricityMeter> getElectricityMeters() {
		return this.electricityMeters;
	}

	public void setElectricityMeters(Set<ElectricityMeter> electricityMeters) {
		this.electricityMeters = electricityMeters;
	}

	public Set<CircuitLine> getCircuitLines() {
		return this.circuitLines;
	}

	public void setCircuitLines(Set<CircuitLine> circuitLines) {
		this.circuitLines = circuitLines;
	}

}
