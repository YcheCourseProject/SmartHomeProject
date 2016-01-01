package com.example.smarthomeapp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class AirConditionDemand implements java.io.Serializable {

	// Fields

	private Integer airConditionDemandId;
	private WearableInfoToDemand wearableInfoToDemand;
	private SocialActivityToDemand socialActivityToDemand;
	private GpsInfoToDemand gpsInfoToDemand;
	private Timestamp airConditionRunTime;
	private Timestamp airCondtionStopTime;
	private Float airConditionTemperature;
	private Float airConditionTemperatureDelta;
	private Float airConditionDemandInternal;
	private Set<RealTimeDemand> realTimeDemands = new HashSet<RealTimeDemand>(0);

	// Constructors

	/** default constructor */
	public AirConditionDemand() {
	}

	/** minimal constructor */
	public AirConditionDemand(Timestamp airConditionRunTime,
			Timestamp airCondtionStopTime, Float airConditionTemperature) {
		this.airConditionRunTime = airConditionRunTime;
		this.airCondtionStopTime = airCondtionStopTime;
		this.airConditionTemperature = airConditionTemperature;
	}

	/** full constructor */
	public AirConditionDemand(WearableInfoToDemand wearableInfoToDemand,
			SocialActivityToDemand socialActivityToDemand,
			GpsInfoToDemand gpsInfoToDemand, Timestamp airConditionRunTime,
			Timestamp airCondtionStopTime, Float airConditionTemperature,
			Float airConditionTemperatureDelta,
			Float airConditionDemandInternal,
			Set<RealTimeDemand> realTimeDemands) {
		this.wearableInfoToDemand = wearableInfoToDemand;
		this.socialActivityToDemand = socialActivityToDemand;
		this.gpsInfoToDemand = gpsInfoToDemand;
		this.airConditionRunTime = airConditionRunTime;
		this.airCondtionStopTime = airCondtionStopTime;
		this.airConditionTemperature = airConditionTemperature;
		this.airConditionTemperatureDelta = airConditionTemperatureDelta;
		this.airConditionDemandInternal = airConditionDemandInternal;
		this.realTimeDemands = realTimeDemands;
	}

	// Property accessors
	public Integer getAirConditionDemandId() {
		return this.airConditionDemandId;
	}

	public void setAirConditionDemandId(Integer airConditionDemandId) {
		this.airConditionDemandId = airConditionDemandId;
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

	public Timestamp getAirConditionRunTime() {
		return this.airConditionRunTime;
	}

	public void setAirConditionRunTime(Timestamp airConditionRunTime) {
		this.airConditionRunTime = airConditionRunTime;
	}

	public Timestamp getAirCondtionStopTime() {
		return this.airCondtionStopTime;
	}

	public void setAirCondtionStopTime(Timestamp airCondtionStopTime) {
		this.airCondtionStopTime = airCondtionStopTime;
	}

	public Float getAirConditionTemperature() {
		return this.airConditionTemperature;
	}

	public void setAirConditionTemperature(Float airConditionTemperature) {
		this.airConditionTemperature = airConditionTemperature;
	}

	public Float getAirConditionTemperatureDelta() {
		return this.airConditionTemperatureDelta;
	}

	public void setAirConditionTemperatureDelta(
			Float airConditionTemperatureDelta) {
		this.airConditionTemperatureDelta = airConditionTemperatureDelta;
	}

	public Float getAirConditionDemandInternal() {
		return this.airConditionDemandInternal;
	}

	public void setAirConditionDemandInternal(Float airConditionDemandInternal) {
		this.airConditionDemandInternal = airConditionDemandInternal;
	}

	public Set<RealTimeDemand> getRealTimeDemands() {
		return this.realTimeDemands;
	}

	public void setRealTimeDemands(Set<RealTimeDemand> realTimeDemands) {
		this.realTimeDemands = realTimeDemands;
	}

}
