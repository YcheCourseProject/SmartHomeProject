package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class SheSwitch implements java.io.Serializable {

	// Fields

	private Integer sheSwitchId;
	private Set<SheSwitchStatus> sheSwitchStatuses = new HashSet<SheSwitchStatus>(
			0);

	// Constructors

	/** default constructor */
	public SheSwitch() {
	}

	/** full constructor */
	public SheSwitch(Set<SheSwitchStatus> sheSwitchStatuses) {
		this.sheSwitchStatuses = sheSwitchStatuses;
	}

	// Property accessors
	public Integer getSheSwitchId() {
		return this.sheSwitchId;
	}

	public void setSheSwitchId(Integer sheSwitchId) {
		this.sheSwitchId = sheSwitchId;
	}

	public Set<SheSwitchStatus> getSheSwitchStatuses() {
		return this.sheSwitchStatuses;
	}

	public void setSheSwitchStatuses(Set<SheSwitchStatus> sheSwitchStatuses) {
		this.sheSwitchStatuses = sheSwitchStatuses;
	}

}
