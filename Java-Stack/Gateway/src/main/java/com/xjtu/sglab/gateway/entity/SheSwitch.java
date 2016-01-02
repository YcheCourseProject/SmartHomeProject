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
 * SheSwitch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "she_switch", catalog = "smarthome")
public class SheSwitch implements java.io.Serializable {

	// Fields

	private Integer sheSwitchId;
	private Boolean isAlreadyControlled;
	private Set<SheSwitchStatus> sheSwitchStatuses = new HashSet<SheSwitchStatus>(
			0);

	// Constructors

	/** default constructor */
	public SheSwitch() {
	}

	/** minimal constructor */
	public SheSwitch(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

	/** full constructor */
	public SheSwitch(Boolean isAlreadyControlled,
			Set<SheSwitchStatus> sheSwitchStatuses) {
		this.isAlreadyControlled = isAlreadyControlled;
		this.sheSwitchStatuses = sheSwitchStatuses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "she_switch_id", unique = true, nullable = false)
	public Integer getSheSwitchId() {
		return this.sheSwitchId;
	}

	public void setSheSwitchId(Integer sheSwitchId) {
		this.sheSwitchId = sheSwitchId;
	}

	@Column(name = "is_already_controlled", nullable = false)
	public Boolean getIsAlreadyControlled() {
		return this.isAlreadyControlled;
	}

	public void setIsAlreadyControlled(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sheSwitch")
	public Set<SheSwitchStatus> getSheSwitchStatuses() {
		return this.sheSwitchStatuses;
	}

	public void setSheSwitchStatuses(Set<SheSwitchStatus> sheSwitchStatuses) {
		this.sheSwitchStatuses = sheSwitchStatuses;
	}

}