package com.xjtu.sglab.gateway.entity;

import java.sql.Time;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AirConditionDemand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "air_condition_demand", catalog = "smarthome")
public class AirConditionDemand implements java.io.Serializable {

	// Fields

	private Integer airConditionDemandId;
	private SocialActivityToDemand socialActivityToDemand;
	private GpsInfoToDemand gpsInfoToDemand;
	private Integer wearableInfoToDemandId;
	private Time airConditionRunTime;
	private Time airCondtionStopTime;
	private Float airConditionTemperature;
	private Float airConditionTemperatureDelta;
	private Float airConditionDemandInternal;
	private Set<RealTimeDemand> realTimeDemands = new HashSet<RealTimeDemand>(0);

	// Constructors

	/** default constructor */
	public AirConditionDemand() {
	}

	/** minimal constructor */
	public AirConditionDemand(Float airConditionTemperature) {
		this.airConditionTemperature = airConditionTemperature;
	}

	/** full constructor */
	public AirConditionDemand(SocialActivityToDemand socialActivityToDemand,
			GpsInfoToDemand gpsInfoToDemand, Integer wearableInfoToDemandId,
			Time airConditionRunTime, Time airCondtionStopTime,
			Float airConditionTemperature, Float airConditionTemperatureDelta,
			Float airConditionDemandInternal,
			Set<RealTimeDemand> realTimeDemands) {
		this.socialActivityToDemand = socialActivityToDemand;
		this.gpsInfoToDemand = gpsInfoToDemand;
		this.wearableInfoToDemandId = wearableInfoToDemandId;
		this.airConditionRunTime = airConditionRunTime;
		this.airCondtionStopTime = airCondtionStopTime;
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionTemperatureDelta = airConditionTemperatureDelta;
		this.airConditionDemandInternal = airConditionDemandInternal;
		this.realTimeDemands = realTimeDemands;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "air_condition_demand_id", unique = true, nullable = false)
	public Integer getAirConditionDemandId() {
		return this.airConditionDemandId;
	}

	public void setAirConditionDemandId(Integer airConditionDemandId) {
		this.airConditionDemandId = airConditionDemandId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "social_activity_to_demand_id")
	public SocialActivityToDemand getSocialActivityToDemand() {
		return this.socialActivityToDemand;
	}

	public void setSocialActivityToDemand(
			SocialActivityToDemand socialActivityToDemand) {
		this.socialActivityToDemand = socialActivityToDemand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gps_info_to_demand_id")
	public GpsInfoToDemand getGpsInfoToDemand() {
		return this.gpsInfoToDemand;
	}

	public void setGpsInfoToDemand(GpsInfoToDemand gpsInfoToDemand) {
		this.gpsInfoToDemand = gpsInfoToDemand;
	}

	@Column(name = "wearable_info_to_demand_id")
	public Integer getWearableInfoToDemandId() {
		return this.wearableInfoToDemandId;
	}

	public void setWearableInfoToDemandId(Integer wearableInfoToDemandId) {
		this.wearableInfoToDemandId = wearableInfoToDemandId;
	}

	@Column(name = "air_condition_run_time", length = 0)
	public Time getAirConditionRunTime() {
		return this.airConditionRunTime;
	}

	public void setAirConditionRunTime(Time airConditionRunTime) {
		this.airConditionRunTime = airConditionRunTime;
	}

	@Column(name = "air_condtion_stop_time", length = 0)
	public Time getAirCondtionStopTime() {
		return this.airCondtionStopTime;
	}

	public void setAirCondtionStopTime(Time airCondtionStopTime) {
		this.airCondtionStopTime = airCondtionStopTime;
	}

	@Column(name = "air_condition_temperature", nullable = false, precision = 12, scale = 0)
	public Float getAirConditionTemperature() {
		return this.airConditionTemperature;
	}

	public void setAirConditionTemperature(Float airConditionTemperature) {
		this.airConditionTemperature = airConditionTemperature;
	}

	@Column(name = "air_condition_temperature_delta", precision = 12, scale = 0)
	public Float getAirConditionTemperatureDelta() {
		return this.airConditionTemperatureDelta;
	}

	public void setAirConditionTemperatureDelta(
			Float airConditionTemperatureDelta) {
		this.airConditionTemperatureDelta = airConditionTemperatureDelta;
	}

	@Column(name = "air_condition_demand_internal", precision = 12, scale = 0)
	public Float getAirConditionDemandInternal() {
		return this.airConditionDemandInternal;
	}

	public void setAirConditionDemandInternal(Float airConditionDemandInternal) {
		this.airConditionDemandInternal = airConditionDemandInternal;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "airConditionDemands")
	public Set<RealTimeDemand> getRealTimeDemands() {
		return this.realTimeDemands;
	}

	public void setRealTimeDemands(Set<RealTimeDemand> realTimeDemands) {
		this.realTimeDemands = realTimeDemands;
	}

}