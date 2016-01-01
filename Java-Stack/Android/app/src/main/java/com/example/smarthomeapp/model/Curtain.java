package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class Curtain implements java.io.Serializable {

	// Fields

	private Integer curtainId;
	private Box box;
	private String curtainIp;
	private Float curtainSize;
	private Float curtainRatedPower;
	private Set<CurtainStatus> curtainStatuses = new HashSet<CurtainStatus>(0);

	// Constructors

	/** default constructor */
	public Curtain() {
	}

	/** full constructor */
	public Curtain(Box box, String curtainIp, Float curtainSize,
			Float curtainRatedPower, Set<CurtainStatus> curtainStatuses) {
		this.box = box;
		this.curtainIp = curtainIp;
		this.curtainSize = curtainSize;
		this.curtainRatedPower = curtainRatedPower;
		this.curtainStatuses = curtainStatuses;
	}

	// Property accessors
	public Integer getCurtainId() {
		return this.curtainId;
	}

	public void setCurtainId(Integer curtainId) {
		this.curtainId = curtainId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public String getCurtainIp() {
		return this.curtainIp;
	}

	public void setCurtainIp(String curtainIp) {
		this.curtainIp = curtainIp;
	}

	public Float getCurtainSize() {
		return this.curtainSize;
	}

	public void setCurtainSize(Float curtainSize) {
		this.curtainSize = curtainSize;
	}

	public Float getCurtainRatedPower() {
		return this.curtainRatedPower;
	}

	public void setCurtainRatedPower(Float curtainRatedPower) {
		this.curtainRatedPower = curtainRatedPower;
	}

	public Set<CurtainStatus> getCurtainStatuses() {
		return this.curtainStatuses;
	}

	public void setCurtainStatuses(Set<CurtainStatus> curtainStatuses) {
		this.curtainStatuses = curtainStatuses;
	}

}
