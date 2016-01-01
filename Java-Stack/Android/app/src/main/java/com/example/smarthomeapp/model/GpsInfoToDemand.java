package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class GpsInfoToDemand implements java.io.Serializable {

	// Fields

	private Integer gpsInfoToDemandId;
	private LocationType locationType;
	private Float distanceFromHome;
	private Set<WaterHeaterDemand> waterHeaterDemands = new HashSet<WaterHeaterDemand>(
			0);
	private Set<AirConditionDemand> airConditionDemands = new HashSet<AirConditionDemand>(
			0);

	// Constructors

	/** default constructor */
	public GpsInfoToDemand() {
	}

	/** full constructor */
	public GpsInfoToDemand(LocationType locationType, Float distanceFromHome,
			Set<WaterHeaterDemand> waterHeaterDemands,
			Set<AirConditionDemand> airConditionDemands) {
		this.locationType = locationType;
		this.distanceFromHome = distanceFromHome;
		this.waterHeaterDemands = waterHeaterDemands;
		this.airConditionDemands = airConditionDemands;
	}

	// Property accessors
	public Integer getGpsInfoToDemandId() {
		return this.gpsInfoToDemandId;
	}

	public void setGpsInfoToDemandId(Integer gpsInfoToDemandId) {
		this.gpsInfoToDemandId = gpsInfoToDemandId;
	}

	public LocationType getLocationType() {
		return this.locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public Float getDistanceFromHome() {
		return this.distanceFromHome;
	}

	public void setDistanceFromHome(Float distanceFromHome) {
		this.distanceFromHome = distanceFromHome;
	}

	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

	public Set<AirConditionDemand> getAirConditionDemands() {
		return this.airConditionDemands;
	}

	public void setAirConditionDemands(
			Set<AirConditionDemand> airConditionDemands) {
		this.airConditionDemands = airConditionDemands;
	}

}
