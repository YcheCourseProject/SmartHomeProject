package com.xjtu.sglab.gateway.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ActivityType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "activity_type", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "activity_type_id", unique = true, nullable = false)
	public Integer getActivityTypeId() {
		return this.activityTypeId;
	}

	public void setActivityTypeId(Integer activityTypeId) {
		this.activityTypeId = activityTypeId;
	}

	@Column(name = "activity_type", nullable = false, length = 15)
	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activityType")
	public Set<SocialActivityToDemand> getSocialActivityToDemands() {
		return this.socialActivityToDemands;
	}

	public void setSocialActivityToDemands(
			Set<SocialActivityToDemand> socialActivityToDemands) {
		this.socialActivityToDemands = socialActivityToDemands;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activityType")
	public Set<SocialInfo> getSocialInfos() {
		return this.socialInfos;
	}

	public void setSocialInfos(Set<SocialInfo> socialInfos) {
		this.socialInfos = socialInfos;
	}

}