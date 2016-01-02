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
 * Lamp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lamp", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "lamp_id", unique = true, nullable = false)
	public Integer getLampId() {
		return this.lampId;
	}

	public void setLampId(Integer lampId) {
		this.lampId = lampId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@Column(name = "lamp_type")
	public Integer getLampType() {
		return this.lampType;
	}

	public void setLampType(Integer lampType) {
		this.lampType = lampType;
	}

	@Column(name = "lamp_rated_power", precision = 12, scale = 0)
	public Float getLampRatedPower() {
		return this.lampRatedPower;
	}

	public void setLampRatedPower(Float lampRatedPower) {
		this.lampRatedPower = lampRatedPower;
	}

	@Column(name = "lamp_location")
	public Integer getLampLocation() {
		return this.lampLocation;
	}

	public void setLampLocation(Integer lampLocation) {
		this.lampLocation = lampLocation;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lamp")
	public Set<LampStatus> getLampStatuses() {
		return this.lampStatuses;
	}

	public void setLampStatuses(Set<LampStatus> lampStatuses) {
		this.lampStatuses = lampStatuses;
	}

}