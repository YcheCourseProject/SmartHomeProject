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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ElectricityType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "electricity_type", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "electricity_type_id", unique = true, nullable = false)
	public Integer getElectricityTypeId() {
		return this.electricityTypeId;
	}

	public void setElectricityTypeId(Integer electricityTypeId) {
		this.electricityTypeId = electricityTypeId;
	}

	@Column(name = "electricity_type", nullable = false, length = 65535)
	public String getElectricityType() {
		return this.electricityType;
	}

	public void setElectricityType(String electricityType) {
		this.electricityType = electricityType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electricityType")
	public Set<ElectricityMeter> getElectricityMeters() {
		return this.electricityMeters;
	}

	public void setElectricityMeters(Set<ElectricityMeter> electricityMeters) {
		this.electricityMeters = electricityMeters;
	}

}