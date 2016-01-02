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
 * LampStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lamp_status", catalog = "smarthome")
public class LampStatus implements java.io.Serializable {

	// Fields

	private Integer lampStatusId;
	private Lamp lamp;
	private Integer lampStatus;
	private Boolean isControlledByUser;
	private Timestamp lampStatusRecordTime;
	private Boolean isAlreadyControlled;

	// Constructors

	/** default constructor */
	public LampStatus() {
	}

	/** minimal constructor */
	public LampStatus(Integer lampStatus, Boolean isControlledByUser,
			Timestamp lampStatusRecordTime, Boolean isAlreadyControlled) {
		this.lampStatus = lampStatus;
		this.isControlledByUser = isControlledByUser;
		this.lampStatusRecordTime = lampStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	/** full constructor */
	public LampStatus(Lamp lamp, Integer lampStatus,
			Boolean isControlledByUser, Timestamp lampStatusRecordTime,
			Boolean isAlreadyControlled) {
		this.lamp = lamp;
		this.lampStatus = lampStatus;
		this.isControlledByUser = isControlledByUser;
		this.lampStatusRecordTime = lampStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "lamp_status_id", unique = true, nullable = false)
	public Integer getLampStatusId() {
		return this.lampStatusId;
	}

	public void setLampStatusId(Integer lampStatusId) {
		this.lampStatusId = lampStatusId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lamp_id")
	public Lamp getLamp() {
		return this.lamp;
	}

	public void setLamp(Lamp lamp) {
		this.lamp = lamp;
	}

	@Column(name = "lamp_status", nullable = false)
	public Integer getLampStatus() {
		return this.lampStatus;
	}

	public void setLampStatus(Integer lampStatus) {
		this.lampStatus = lampStatus;
	}

	@Column(name = "is_controlled_by_user", nullable = false)
	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	@Column(name = "lamp_status_record_time", nullable = false, length = 0)
	public Timestamp getLampStatusRecordTime() {
		return this.lampStatusRecordTime;
	}

	public void setLampStatusRecordTime(Timestamp lampStatusRecordTime) {
		this.lampStatusRecordTime = lampStatusRecordTime;
	}

	@Column(name = "is_already_controlled", nullable = false)
	public Boolean getIsAlreadyControlled() {
		return this.isAlreadyControlled;
	}

	public void setIsAlreadyControlled(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

}