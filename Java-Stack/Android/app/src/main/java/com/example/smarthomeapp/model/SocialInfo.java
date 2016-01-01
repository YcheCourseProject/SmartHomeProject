package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class SocialInfo implements java.io.Serializable {

	// Fields

	private Integer socialInfoId;
	private ActivityType activityType;
	private SocialSource socialSource;
	private String startTime;
	private String endTime;
	private String location;
	private Timestamp activitySentTime;
	private Timestamp activityRecordTime;

	// Constructors

	/** default constructor */
	public SocialInfo() {
	}

	/** minimal constructor */
	public SocialInfo(Timestamp activitySentTime, Timestamp activityRecordTime) {
		this.activitySentTime = activitySentTime;
		this.activityRecordTime = activityRecordTime;
	}

	/** full constructor */
	public SocialInfo(ActivityType activityType, SocialSource socialSource,
			String startTime, String endTime, String location,
			Timestamp activitySentTime, Timestamp activityRecordTime) {
		this.activityType = activityType;
		this.socialSource = socialSource;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.activitySentTime = activitySentTime;
		this.activityRecordTime = activityRecordTime;
	}

	// Property accessors
	public Integer getSocialInfoId() {
		return this.socialInfoId;
	}

	public void setSocialInfoId(Integer socialInfoId) {
		this.socialInfoId = socialInfoId;
	}

	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public SocialSource getSocialSource() {
		return this.socialSource;
	}

	public void setSocialSource(SocialSource socialSource) {
		this.socialSource = socialSource;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getActivitySentTime() {
		return this.activitySentTime;
	}

	public void setActivitySentTime(Timestamp activitySentTime) {
		this.activitySentTime = activitySentTime;
	}

	public Timestamp getActivityRecordTime() {
		return this.activityRecordTime;
	}

	public void setActivityRecordTime(Timestamp activityRecordTime) {
		this.activityRecordTime = activityRecordTime;
	}

}
