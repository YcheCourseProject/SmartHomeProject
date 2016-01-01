package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class ActivityType implements java.io.Serializable {

	// Fields

	private Integer activityTypeId;
	private String activityType;
	private Set<SocialActivityToDemand> socialActivityToDemands = new HashSet<SocialActivityToDemand>(
			0);
	private Set<SocialInfo> socialInfos = new HashSet<SocialInfo>(0);

	// Constructors

	/** default constructor */
	public ActivityType() {
	}

	/** minimal constructor */
	public ActivityType(String activityType) {
		this.activityType = activityType;
	}

	/** full constructor */
	public ActivityType(String activityType,
			Set<SocialActivityToDemand> socialActivityToDemands,
			Set<SocialInfo> socialInfos) {
		this.activityType = activityType;
		this.socialActivityToDemands = socialActivityToDemands;
		this.socialInfos = socialInfos;
	}

	// Property accessors
	public Integer getActivityTypeId() {
		return this.activityTypeId;
	}

	public void setActivityTypeId(Integer activityTypeId) {
		this.activityTypeId = activityTypeId;
	}

	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Set<SocialActivityToDemand> getSocialActivityToDemands() {
		return this.socialActivityToDemands;
	}

	public void setSocialActivityToDemands(
			Set<SocialActivityToDemand> socialActivityToDemands) {
		this.socialActivityToDemands = socialActivityToDemands;
	}

	public Set<SocialInfo> getSocialInfos() {
		return this.socialInfos;
	}

	public void setSocialInfos(Set<SocialInfo> socialInfos) {
		this.socialInfos = socialInfos;
	}

}
