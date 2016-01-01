package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class LocationType implements java.io.Serializable {

	// Fields

	private Integer locTypeId;
	private String locType;
	private Set<GpsInfoToDemand> gpsInfoToDemands = new HashSet<GpsInfoToDemand>(
			0);
	private Set<GpsInfo> gpsInfos = new HashSet<GpsInfo>(0);

	// Constructors

	/** default constructor */
	public LocationType() {
	}

	/** minimal constructor */
	public LocationType(String locType) {
		this.locType = locType;
	}

	/** full constructor */
	public LocationType(String locType, Set<GpsInfoToDemand> gpsInfoToDemands,
			Set<GpsInfo> gpsInfos) {
		this.locType = locType;
		this.gpsInfoToDemands = gpsInfoToDemands;
		this.gpsInfos = gpsInfos;
	}

	// Property accessors
	public Integer getLocTypeId() {
		return this.locTypeId;
	}

	public void setLocTypeId(Integer locTypeId) {
		this.locTypeId = locTypeId;
	}

	public String getLocType() {
		return this.locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	public Set<GpsInfoToDemand> getGpsInfoToDemands() {
		return this.gpsInfoToDemands;
	}

	public void setGpsInfoToDemands(Set<GpsInfoToDemand> gpsInfoToDemands) {
		this.gpsInfoToDemands = gpsInfoToDemands;
	}

	public Set<GpsInfo> getGpsInfos() {
		return this.gpsInfos;
	}

	public void setGpsInfos(Set<GpsInfo> gpsInfos) {
		this.gpsInfos = gpsInfos;
	}

}
