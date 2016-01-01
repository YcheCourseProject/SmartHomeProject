package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class SheSwitchStatus implements java.io.Serializable {

	// Fields

	private Integer sheSwitchStatusId;
	private SheSwitch sheSwitch;
	private Boolean sheSwitchStatus;
	private Boolean isControlledByUser;
	private Timestamp sheSwitchStatusRecordTime;

	// Constructors

	/** default constructor */
	public SheSwitchStatus() {
	}

	/** minimal constructor */
	public SheSwitchStatus(Boolean sheSwitchStatus,
			Timestamp sheSwitchStatusRecordTime) {
		this.sheSwitchStatus = sheSwitchStatus;
		this.sheSwitchStatusRecordTime = sheSwitchStatusRecordTime;
	}

	/** full constructor */
	public SheSwitchStatus(SheSwitch sheSwitch, Boolean sheSwitchStatus,
			Boolean isControlledByUser, Timestamp sheSwitchStatusRecordTime) {
		this.sheSwitch = sheSwitch;
		this.sheSwitchStatus = sheSwitchStatus;
		this.isControlledByUser = isControlledByUser;
		this.sheSwitchStatusRecordTime = sheSwitchStatusRecordTime;
	}

	// Property accessors
	public Integer getSheSwitchStatusId() {
		return this.sheSwitchStatusId;
	}

	public void setSheSwitchStatusId(Integer sheSwitchStatusId) {
		this.sheSwitchStatusId = sheSwitchStatusId;
	}

	public SheSwitch getSheSwitch() {
		return this.sheSwitch;
	}

	public void setSheSwitch(SheSwitch sheSwitch) {
		this.sheSwitch = sheSwitch;
	}

	public Boolean getSheSwitchStatus() {
		return this.sheSwitchStatus;
	}

	public void setSheSwitchStatus(Boolean sheSwitchStatus) {
		this.sheSwitchStatus = sheSwitchStatus;
	}

	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	public Timestamp getSheSwitchStatusRecordTime() {
		return this.sheSwitchStatusRecordTime;
	}

	public void setSheSwitchStatusRecordTime(Timestamp sheSwitchStatusRecordTime) {
		this.sheSwitchStatusRecordTime = sheSwitchStatusRecordTime;
	}

}
