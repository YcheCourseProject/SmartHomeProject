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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SocialActivityToDemand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "social_activity_to_demand", catalog = "smarthome")
public class SocialActivityToDemand implements java.io.Serializable {

	// Fields

	private Integer socialActivityToDemandId;
	private ActivityType activityType;
	private String socialActivityBeginTime;
	private Set<AirConditionDemand> airConditionDemands = new HashSet<AirConditionDemand>(
			0);
	private Set<WaterHeaterDemand> waterHeaterDemands = new HashSet<WaterHeaterDemand>(
			0);

	// Constructors

	/** default constructor */
	public SocialActivityToDemand() {
	}

	/** full constructor */
	public SocialActivityToDemand(ActivityType activityType,
			String socialActivityBeginTime,
			Set<AirConditionDemand> airConditionDemands,
			Set<WaterHeaterDemand> waterHeaterDemands) {
		this.activityType = activityType;
		this.socialActivityBeginTime = socialActivityBeginTime;
		this.airConditionDemands = airConditionDemands;
		this.waterHeaterDemands = waterHeaterDemands;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "social_activity_to_demand_id", unique = true, nullable = false)
	public Integer getSocialActivityToDemandId() {
		return this.socialActivityToDemandId;
	}

	public void setSocialActivityToDemandId(Integer socialActivityToDemandId) {
		this.socialActivityToDemandId = socialActivityToDemandId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_type_id")
	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	@Column(name = "social_activity_begin_time", length = 20)
	public String getSocialActivityBeginTime() {
		return this.socialActivityBeginTime;
	}

	public void setSocialActivityBeginTime(String socialActivityBeginTime) {
		this.socialActivityBeginTime = socialActivityBeginTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "socialActivityToDemand")
	public Set<AirConditionDemand> getAirConditionDemands() {
		return this.airConditionDemands;
	}

	public void setAirConditionDemands(
			Set<AirConditionDemand> airConditionDemands) {
		this.airConditionDemands = airConditionDemands;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "socialActivityToDemand")
	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

}