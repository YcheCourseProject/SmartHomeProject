package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class LampStatus implements java.io.Serializable {

	// Fields

	private Integer lampStatusId;
	private Lamp lamp;
	private Integer lampStatus;
	private Boolean isControlledByUser;
	private Timestamp lampStatusRecordTime;

	// Constructors

	/** default constructor */
	public LampStatus() {
	}

	/** minimal constructor */
	public LampStatus(Integer lampStatus, Boolean isControlledByUser,
			Timestamp lampStatusRecordTime) {
		this.lampStatus = lampStatus;
		this.isControlledByUser = isControlledByUser;
		this.lampStatusRecordTime = lampStatusRecordTime;
	}

	/** full constructor */
	public LampStatus(Lamp lamp, Integer lampStatus,
			Boolean isControlledByUser, Timestamp lampStatusRecordTime) {
		this.lamp = lamp;
		this.lampStatus = lampStatus;
		this.isControlledByUser = isControlledByUser;
		this.lampStatusRecordTime = lampStatusRecordTime;
	}

	// Property accessors
	public Integer getLampStatusId() {
		return this.lampStatusId;
	}

	public void setLampStatusId(Integer lampStatusId) {
		this.lampStatusId = lampStatusId;
	}

	public Lamp getLamp() {
		return this.lamp;
	}

	public void setLamp(Lamp lamp) {
		this.lamp = lamp;
	}

	public Integer getLampStatus() {
		return this.lampStatus;
	}

	public void setLampStatus(Integer lampStatus) {
		this.lampStatus = lampStatus;
	}

	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	public Timestamp getLampStatusRecordTime() {
		return this.lampStatusRecordTime;
	}

	public void setLampStatusRecordTime(Timestamp lampStatusRecordTime) {
		this.lampStatusRecordTime = lampStatusRecordTime;
	}

}
