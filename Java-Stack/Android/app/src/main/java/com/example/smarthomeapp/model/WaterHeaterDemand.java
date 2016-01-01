package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class WaterHeaterDemand implements java.io.Serializable {

	// Fields

	private Integer waterHeaterDemandId;
	private WearableInfoToDemand wearableInfoToDemand;
	private SocialActivityToDemand socialActivityToDemand;
	private GpsInfoToDemand gpsInfoToDemand;
	private Timestamp waterHeaterRunTime;
	private Timestamp waterHeaterStopTime;
	private Float waterHeaterTemperature;
	private Float waterHeaterTemperatureDelta;
	private Float waterHeaterDemandInternal;
	private Set<RealTimeDemand> realTimeDemands = new HashSet<RealTimeDemand>(0);

	// Constructors

	/** default constructor */
	public WaterHeaterDemand() {
	}

	/** minimal constructor */
	public WaterHeaterDemand(Timestamp waterHeaterRunTime,
			Timestamp waterHeaterStopTime) {
		this.waterHeaterRunTime = waterHeaterRunTime;
		this.waterHeaterStopTime = waterHeaterStopTime;
	}

	/** full constructor */
	public WaterHeaterDemand(WearableInfoToDemand wearableInfoToDemand,
			SocialActivityToDemand socialActivityToDemand,
			GpsInfoToDemand gpsInfoToDemand, Timestamp waterHeaterRunTime,
			Timestamp waterHeaterStopTime, Float waterHeaterTemperature,
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
	public Integer getWaterHeaterDemandId() {
		return this.waterHeaterDemandId;
	}

	public void setWaterHeaterDemandId(Integer waterHeaterDemandId) {
		this.waterHeaterDemandId = waterHeaterDemandId;
	}

	public WearableInfoToDemand getWearableInfoToDemand() {
		return this.wearableInfoToDemand;
	}

	public void setWearableInfoToDemand(
			WearableInfoToDemand wearableInfoToDemand) {
		this.wearableInfoToDemand = wearableInfoToDemand;
	}

	public SocialActivityToDemand getSocialActivityToDemand() {
		return this.socialActivityToDemand;
	}

	public void setSocialActivityToDemand(
			SocialActivityToDemand socialActivityToDemand) {
		this.socialActivityToDemand = socialActivityToDemand;
	}

	public GpsInfoToDemand getGpsInfoToDemand() {
		return this.gpsInfoToDemand;
	}

	public void setGpsInfoToDemand(GpsInfoToDemand gpsInfoToDemand) {
		this.gpsInfoToDemand = gpsInfoToDemand;
	}

	public Timestamp getWaterHeaterRunTime() {
		return this.waterHeaterRunTime;
	}

	public void setWaterHeaterRunTime(Timestamp waterHeaterRunTime) {
		this.waterHeaterRunTime = waterHeaterRunTime;
	}

	public Timestamp getWaterHeaterStopTime() {
		return this.waterHeaterStopTime;
	}

	public void setWaterHeaterStopTime(Timestamp waterHeaterStopTime) {
		this.waterHeaterStopTime = waterHeaterStopTime;
	}

	public Float getWaterHeaterTemperature() {
		return this.waterHeaterTemperature;
	}

	public void setWaterHeaterTemperature(Float waterHeaterTemperature) {
		this.waterHeaterTemperature = waterHeaterTemperature;
	}

	public Float getWaterHeaterTemperatureDelta() {
		return this.waterHeaterTemperatureDelta;
	}

	public void setWaterHeaterTemperatureDelta(Float waterHeaterTemperatureDelta) {
		this.waterHeaterTemperatureDelta = waterHeaterTemperatureDelta;
	}

	public Float getWaterHeaterDemandInternal() {
		return this.waterHeaterDemandInternal;
	}

	public void setWaterHeaterDemandInternal(Float waterHeaterDemandInternal) {
		this.waterHeaterDemandInternal = waterHeaterDemandInternal;
	}

	public Set<RealTimeDemand> getRealTimeDemands() {
		return this.realTimeDemands;
	}

	public void setRealTimeDemands(Set<RealTimeDemand> realTimeDemands) {
		this.realTimeDemands = realTimeDemands;
	}

}
