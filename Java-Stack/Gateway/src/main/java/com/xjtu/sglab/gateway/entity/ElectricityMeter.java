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
 * ElectricityMeter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "electricity_meter", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "electricity_meter_id", unique = true, nullable = false)
	public Integer getElectricityMeterId() {
		return this.electricityMeterId;
	}

	public void setElectricityMeterId(Integer electricityMeterId) {
		this.electricityMeterId = electricityMeterId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "circuit_line_id")
	public CircuitLine getCircuitLine() {
		return this.circuitLine;
	}

	public void setCircuitLine(CircuitLine circuitLine) {
		this.circuitLine = circuitLine;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "electricity_type_id")
	public ElectricityType getElectricityType() {
		return this.electricityType;
	}

	public void setElectricityType(ElectricityType electricityType) {
		this.electricityType = electricityType;
	}

	@Column(name = "electricity_meter_ip", length = 15)
	public String getElectricityMeterIp() {
		return this.electricityMeterIp;
	}

	public void setElectricityMeterIp(String electricityMeterIp) {
		this.electricityMeterIp = electricityMeterIp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electricityMeter")
	public Set<CircuitLine> getCircuitLines() {
		return this.circuitLines;
	}

	public void setCircuitLines(Set<CircuitLine> circuitLines) {
		this.circuitLines = circuitLines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electricityMeter")
	public Set<ElectricityInfo> getElectricityInfos() {
		return this.electricityInfos;
	}

	public void setElectricityInfos(Set<ElectricityInfo> electricityInfos) {
		this.electricityInfos = electricityInfos;
	}

}