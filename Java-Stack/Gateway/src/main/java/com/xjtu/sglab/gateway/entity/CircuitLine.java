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
 * CircuitLine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "circuit_line", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "circuit_line_id", unique = true, nullable = false)
	public Integer getCircuitLineId() {
		return this.circuitLineId;
	}

	public void setCircuitLineId(Integer circuitLineId) {
		this.circuitLineId = circuitLineId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cir_circuit_line_id")
	public CircuitLine getCircuitLine() {
		return this.circuitLine;
	}

	public void setCircuitLine(CircuitLine circuitLine) {
		this.circuitLine = circuitLine;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "electricity_meter_id")
	public ElectricityMeter getElectricityMeter() {
		return this.electricityMeter;
	}

	public void setElectricityMeter(ElectricityMeter electricityMeter) {
		this.electricityMeter = electricityMeter;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "circuit_line_description", length = 65535)
	public String getCircuitLineDescription() {
		return this.circuitLineDescription;
	}

	public void setCircuitLineDescription(String circuitLineDescription) {
		this.circuitLineDescription = circuitLineDescription;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "circuitLine")
	public Set<ElectricityMeter> getElectricityMeters() {
		return this.electricityMeters;
	}

	public void setElectricityMeters(Set<ElectricityMeter> electricityMeters) {
		this.electricityMeters = electricityMeters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "circuitLine")
	public Set<CircuitLine> getCircuitLines() {
		return this.circuitLines;
	}

	public void setCircuitLines(Set<CircuitLine> circuitLines) {
		this.circuitLines = circuitLines;
	}

}