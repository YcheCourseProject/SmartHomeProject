package com.xjtu.sglab.gateway.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CurtainStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "curtain_status", catalog = "smarthome")
public class CurtainStatus implements java.io.Serializable {

	// Fields

	private Integer curtainStatusId;
	private Curtain curtain;
	private Float curtainStatus;
	private Boolean isControlledByUser;
	private Timestamp curtainStatusRecordTime;
	private Boolean isAlreadyControlled;

	// Constructors

	/** default constructor */
	public CurtainStatus() {
	}

	/** minimal constructor */
	public CurtainStatus(Float curtainStatus, Boolean isControlledByUser,
			Timestamp curtainStatusRecordTime, Boolean isAlreadyControlled) {
		this.curtainStatus = curtainStatus;
		this.isControlledByUser = isControlledByUser;
		this.curtainStatusRecordTime = curtainStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	/** full constructor */
	public CurtainStatus(Curtain curtain, Float curtainStatus,
			Boolean isControlledByUser, Timestamp curtainStatusRecordTime,
			Boolean isAlreadyControlled) {
		this.curtain = curtain;
		this.curtainStatus = curtainStatus;
		this.isControlledByUser = isControlledByUser;
		this.curtainStatusRecordTime = curtainStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "curtain_status_id", unique = true, nullable = false)
	public Integer getCurtainStatusId() {
		return this.curtainStatusId;
	}

	public void setCurtainStatusId(Integer curtainStatusId) {
		this.curtainStatusId = curtainStatusId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curtain_id")
	public Curtain getCurtain() {
		return this.curtain;
	}

	public void setCurtain(Curtain curtain) {
		this.curtain = curtain;
	}

	@Column(name = "curtain_status", nullable = false, precision = 12, scale = 0)
	public Float getCurtainStatus() {
		return this.curtainStatus;
	}

	public void setCurtainStatus(Float curtainStatus) {
		this.curtainStatus = curtainStatus;
	}

	@Column(name = "is_controlled_by_user", nullable = false)
	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	@Column(name = "curtain_status_record_time", nullable = false, length = 0)
	public Timestamp getCurtainStatusRecordTime() {
		return this.curtainStatusRecordTime;
	}

	public void setCurtainStatusRecordTime(Timestamp curtainStatusRecordTime) {
		this.curtainStatusRecordTime = curtainStatusRecordTime;
	}

	@Column(name = "is_already_controlled", nullable = false)
	public Boolean getIsAlreadyControlled() {
		return this.isAlreadyControlled;
	}

	public void setIsAlreadyControlled(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

}