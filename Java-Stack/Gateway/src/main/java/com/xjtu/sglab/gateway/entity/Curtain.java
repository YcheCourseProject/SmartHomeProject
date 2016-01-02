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
 * Curtain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "curtain", catalog = "smarthome")
public class Curtain implements java.io.Serializable {

	// Fields

	private Integer curtainId;
	private Box box;
	private String curtainIp;
	private Float curtainSize;
	private Float curtainRatedPower;
	private Boolean isAlreadyControlled;
	private Set<CurtainStatus> curtainStatuses = new HashSet<CurtainStatus>(0);

	// Constructors

	/** default constructor */
	public Curtain() {
	}

	/** minimal constructor */
	public Curtain(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

	/** full constructor */
	public Curtain(Box box, String curtainIp, Float curtainSize,
			Float curtainRatedPower, Boolean isAlreadyControlled,
			Set<CurtainStatus> curtainStatuses) {
		this.box = box;
		this.curtainIp = curtainIp;
		this.curtainSize = curtainSize;
		this.curtainRatedPower = curtainRatedPower;
		this.isAlreadyControlled = isAlreadyControlled;
		this.curtainStatuses = curtainStatuses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "curtain_id", unique = true, nullable = false)
	public Integer getCurtainId() {
		return this.curtainId;
	}

	public void setCurtainId(Integer curtainId) {
		this.curtainId = curtainId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@Column(name = "curtain_ip", length = 15)
	public String getCurtainIp() {
		return this.curtainIp;
	}

	public void setCurtainIp(String curtainIp) {
		this.curtainIp = curtainIp;
	}

	@Column(name = "curtain_size", precision = 12, scale = 0)
	public Float getCurtainSize() {
		return this.curtainSize;
	}

	public void setCurtainSize(Float curtainSize) {
		this.curtainSize = curtainSize;
	}

	@Column(name = "curtain_rated_power", precision = 12, scale = 0)
	public Float getCurtainRatedPower() {
		return this.curtainRatedPower;
	}

	public void setCurtainRatedPower(Float curtainRatedPower) {
		this.curtainRatedPower = curtainRatedPower;
	}

	@Column(name = "is_already_controlled", nullable = false)
	public Boolean getIsAlreadyControlled() {
		return this.isAlreadyControlled;
	}

	public void setIsAlreadyControlled(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "curtain")
	public Set<CurtainStatus> getCurtainStatuses() {
		return this.curtainStatuses;
	}

	public void setCurtainStatuses(Set<CurtainStatus> curtainStatuses) {
		this.curtainStatuses = curtainStatuses;
	}

}