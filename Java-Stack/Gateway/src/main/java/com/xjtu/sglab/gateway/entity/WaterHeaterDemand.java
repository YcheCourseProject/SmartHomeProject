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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * WaterHeaterDemand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "water_heater_demand", catalog = "smarthome")
public class WaterHeaterDemand implements java.io.Serializable {

	// Fields

	private Integer waterHeaterDemandId;
	private WearableInfoToDemand wearableInfoToDemand;
	private SocialActivityToDemand socialActivityToDemand;
	private GpsInfoToDemand gpsInfoToDemand;
	private Time waterHeaterRunTime;
	private Time waterHeaterStopTime;
	private Float waterHeaterTemperature;
	private Float waterHeaterTemperatureDelta;
	private Float waterHeaterDemandInternal;
	private Set<RealTimeDemand> realTimeDemands = new HashSet<RealTimeDemand>(0);

	// Constructors

	/** default constructor */
	public WaterHeaterDemand() {
	}

	/** full constructor */
	public WaterHeaterDemand(WearableInfoToDemand wearableInfoToDemand,
			SocialActivityToDemand socialActivityToDemand,
			GpsInfoToDemand gpsInfoToDemand, Time waterHeaterRunTime,
			Time waterHeaterStopTime, Float waterHeaterTemperature,
			Float waterHeaterTemperatureDelta, Float waterHeaterDemandInternal,
			Set<RealTimeDemand> realTimeDemands) {
		this.wearableInfoToDemand = wearableInfoToDemand;
		this.socialActivityToDemand = socialActivityToDemand;
		this.gpsInfoToDemand = gpsInfoToDemand;
		this.waterHeaterRunTime = waterHeaterRunTime;
		this.waterHeaterStopTime = waterHeaterStopTime;
		this.waterHeaterTemperature = waterHeaterTemperature;
		this.waterHeaterTemperatureDelta = waterHeaterTemperatureDelta;
		this.waterHeaterDemandInternal = waterHeaterDemandInternal;
		this.realTimeDemands = realTimeDemands;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "water_heater_demand_id", unique = true, nullable = false)
	public Integer getWaterHeaterDemandId() {
		return this.waterHeaterDemandId;
	}

	public void setWaterHeaterDemandId(Integer waterHeaterDemandId) {
		this.waterHeaterDemandId = waterHeaterDemandId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wearable_info_to_demand_id")
	public WearableInfoToDemand getWearableInfoToDemand() {
		return this.wearableInfoToDemand;
	}

	public void setWearableInfoToDemand(
			WearableInfoToDemand wearableInfoToDemand) {
		this.wearableInfoToDemand = wearableInfoToDemand;
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

	@Column(name = "water_heater_run_time", length = 0)
	public Time getWaterHeaterRunTime() {
		return this.waterHeaterRunTime;
	}

	public void setWaterHeaterRunTime(Time waterHeaterRunTime) {
		this.waterHeaterRunTime = waterHeaterRunTime;
	}

	@Column(name = "water_heater_stop_time", length = 0)
	public Time getWaterHeaterStopTime() {
		return this.waterHeaterStopTime;
	}

	public void setWaterHeaterStopTime(Time waterHeaterStopTime) {
		this.waterHeaterStopTime = waterHeaterStopTime;
	}

	@Column(name = "water_heater_temperature", precision = 12, scale = 0)
	public Float getWaterHeaterTemperature() {
		return this.waterHeaterTemperature;
	}

	public void setWaterHeaterTemperature(Float waterHeaterTemperature) {
		this.waterHeaterTemperature = waterHeaterTemperature;
	}

	@Column(name = "water_heater_temperature_delta", precision = 12, scale = 0)
	public Float getWaterHeaterTemperatureDelta() {
		return this.waterHeaterTemperatureDelta;
	}

	public void setWaterHeaterTemperatureDelta(Float waterHeaterTemperatureDelta) {
		this.waterHeaterTemperatureDelta = waterHeaterTemperatureDelta;
	}

	@Column(name = "water_heater_demand_internal", precision = 12, scale = 0)
	public Float getWaterHeaterDemandInternal() {
		return this.waterHeaterDemandInternal;
	}

	public void setWaterHeaterDemandInternal(Float waterHeaterDemandInternal) {
		this.waterHeaterDemandInternal = waterHeaterDemandInternal;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "has_water_heater_demand", catalog = "smarthome", joinColumns = { @JoinColumn(name = "water_heater_demand_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "real_time_demand_id", nullable = false, updatable = false) })
	public Set<RealTimeDemand> getRealTimeDemands() {
		return this.realTimeDemands;
	}

	public void setRealTimeDemands(Set<RealTimeDemand> realTimeDemands) {
		this.realTimeDemands = realTimeDemands;
	}

}