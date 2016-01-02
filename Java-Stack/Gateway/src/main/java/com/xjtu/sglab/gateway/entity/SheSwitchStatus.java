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
 * SheSwitchStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "she_switch_status", catalog = "smarthome")
public class SheSwitchStatus implements java.io.Serializable {

	// Fields

	private Integer sheSwitchStatusId;
	private SheSwitch sheSwitch;
	private Boolean sheSwitchStatus;
	private Boolean isControlledByUser;
	private Timestamp sheSwitchStatusRecordTime;
	private Boolean isAlreadyControlled;

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
			Boolean isControlledByUser, Timestamp sheSwitchStatusRecordTime,
			Boolean isAlreadyControlled) {
		this.sheSwitch = sheSwitch;
		this.sheSwitchStatus = sheSwitchStatus;
		this.isControlledByUser = isControlledByUser;
		this.sheSwitchStatusRecordTime = sheSwitchStatusRecordTime;
		this.isAlreadyControlled = isAlreadyControlled;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "she_switch_status_id", unique = true, nullable = false)
	public Integer getSheSwitchStatusId() {
		return this.sheSwitchStatusId;
	}

	public void setSheSwitchStatusId(Integer sheSwitchStatusId) {
		this.sheSwitchStatusId = sheSwitchStatusId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "she_switch_id")
	public SheSwitch getSheSwitch() {
		return this.sheSwitch;
	}

	public void setSheSwitch(SheSwitch sheSwitch) {
		this.sheSwitch = sheSwitch;
	}

	@Column(name = "she_switch_status", nullable = false)
	public Boolean getSheSwitchStatus() {
		return this.sheSwitchStatus;
	}

	public void setSheSwitchStatus(Boolean sheSwitchStatus) {
		this.sheSwitchStatus = sheSwitchStatus;
	}

	@Column(name = "is_controlled_by_user")
	public Boolean getIsControlledByUser() {
		return this.isControlledByUser;
	}

	public void setIsControlledByUser(Boolean isControlledByUser) {
		this.isControlledByUser = isControlledByUser;
	}

	@Column(name = "she_switch_status_record_time", nullable = false, length = 0)
	public Timestamp getSheSwitchStatusRecordTime() {
		return this.sheSwitchStatusRecordTime;
	}

	public void setSheSwitchStatusRecordTime(Timestamp sheSwitchStatusRecordTime) {
		this.sheSwitchStatusRecordTime = sheSwitchStatusRecordTime;
	}

	@Column(name = "is_already_controlled")
	public Boolean getIsAlreadyControlled() {
		return this.isAlreadyControlled;
	}

	public void setIsAlreadyControlled(Boolean isAlreadyControlled) {
		this.isAlreadyControlled = isAlreadyControlled;
	}

}