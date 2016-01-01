package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class CurtainStatus implements java.io.Serializable {

	// Fields

	private Integer curtainStatusId;
	private Curtain curtain;
	private Float curtainStatus;
	private Boolean isControlledByUser;
	private Timestamp curtainStatusRecordTime;

	// Constructors

	/** default constructor */
	public CurtainStatus() {
	}

	/** minimal constructor */
	public CurtainStatus(Float curtainStatus, Boolean isControlledByUser,
			Timestamp curtainStatusRecordTime) {
		this.curtainStatus = curtainStatus;
		this.isControlledByUser = isControlledByUser;
		this.curtainStatusRecordTime = curtainStatusRecordTime;
	}

	/** full constructor */
	public CurtainStatus(Curtain curtain, Float curtainStatus,
			Boolean isControlledByUser, Timestamp curtainStatusRecordTime) {
		this.curtain = curtain;
		this.curtainStatus = curtainStatus;
		this.isControlledByUser = isControlledByUser;
		this.curtainStatusRecordTime = curtainStatusRecordTime;
	}

	// Property accessors
	public Integer getCurtainStatusId() {
		return this.curtainStatusId;
	}

	public void setCurtainStatusId(Integer curtainStatusId) {
		this.curtainStatusId = curtainStatusId;
	}

	public Curtain getCurtain() {
		return this.curtain;
	}

	public void setCurtain(Curtain curtain) {
		this.curtain = curtain;
	}

	public Float getCurtainStatus() {
		return this.curtainStatus;
	}

	public void setCurtainStatus(Float curtainStatus) {
		this.curtainStatus = curtainStatus;
	}

	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	public Timestamp getCurtainStatusRecordTime() {
		return this.curtainStatusRecordTime;
	}

	public void setCurtainStatusRecordTime(Timestamp curtainStatusRecordTime) {
		this.curtainStatusRecordTime = curtainStatusRecordTime;
	}

}
