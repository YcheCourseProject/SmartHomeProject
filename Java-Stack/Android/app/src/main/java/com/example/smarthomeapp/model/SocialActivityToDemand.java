package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
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
	public Integer getSocialActivityToDemandId() {
		return this.socialActivityToDemandId;
	}

	public void setSocialActivityToDemandId(Integer socialActivityToDemandId) {
		this.socialActivityToDemandId = socialActivityToDemandId;
	}

	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public String getSocialActivityBeginTime() {
		return this.socialActivityBeginTime;
	}

	public void setSocialActivityBeginTime(String socialActivityBeginTime) {
		this.socialActivityBeginTime = socialActivityBeginTime;
	}

	public Set<AirConditionDemand> getAirConditionDemands() {
		return this.airConditionDemands;
	}

	public void setAirConditionDemands(
			Set<AirConditionDemand> airConditionDemands) {
		this.airConditionDemands = airConditionDemands;
	}

	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

}
