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
 * SocialInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "social_info", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "social_info_id", unique = true, nullable = false)
	public Integer getSocialInfoId() {
		return this.socialInfoId;
	}

	public void setSocialInfoId(Integer socialInfoId) {
		this.socialInfoId = socialInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_type_id")
	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_type_id")
	public SocialSource getSocialSource() {
		return this.socialSource;
	}

	public void setSocialSource(SocialSource socialSource) {
		this.socialSource = socialSource;
	}

	@Column(name = "start_time", length = 6)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 6)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "location", length = 10)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "activity_sent_time", nullable = false, length = 0)
	public Timestamp getActivitySentTime() {
		return this.activitySentTime;
	}

	public void setActivitySentTime(Timestamp activitySentTime) {
		this.activitySentTime = activitySentTime;
	}

	@Column(name = "activity_record_time", nullable = false, length = 0)
	public Timestamp getActivityRecordTime() {
		return this.activityRecordTime;
	}

	public void setActivityRecordTime(Timestamp activityRecordTime) {
		this.activityRecordTime = activityRecordTime;
	}

}