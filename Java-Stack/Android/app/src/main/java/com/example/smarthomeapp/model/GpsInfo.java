package com.example.smarthomeapp.model;

import java.sql.Timestamp;

/**
 */
public class GpsInfo implements java.io.Serializable {

	// Fields

	private Integer gpsId;
	private LocationType locationType;
	private Double gpsLongitude;
	private Double gpsLatitude;
	private Float distanceFromHome;
	private Timestamp gpsRecordTime;

	// Constructors

	/** default constructor */
	public GpsInfo() {
	}

	/** minimal constructor */
	public GpsInfo(Double gpsLongitude, Double gpsLatitude,
			Float distanceFromHome, Timestamp gpsRecordTime) {
		this.gpsLongitude = gpsLongitude;
		this.gpsLatitude = gpsLatitude;
		this.distanceFromHome = distanceFromHome;
		this.gpsRecordTime = gpsRecordTime;
	}

	/** full constructor */
	public GpsInfo(LocationType locationType, Double gpsLongitude,
			Double gpsLatitude, Float distanceFromHome, Timestamp gpsRecordTime) {
		this.locationType = locationType;
		this.gpsLongitude = gpsLongitude;
		this.gpsLatitude = gpsLatitude;
		this.distanceFromHome = distanceFromHome;
		this.gpsRecordTime = gpsRecordTime;
	}

	// Property accessors
	public Integer getGpsId() {
		return this.gpsId;
	}

	public void setGpsId(Integer gpsId) {
		this.gpsId = gpsId;
	}

	public LocationType getLocationType() {
		return this.locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public Double getGpsLongitude() {
		return this.gpsLongitude;
	}

	public void setGpsLongitude(Double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public Double getGpsLatitude() {
		return this.gpsLatitude;
	}

	public void setGpsLatitude(Double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public Float getDistanceFromHome() {
		return this.distanceFromHome;
	}

	public void setDistanceFromHome(Float distanceFromHome) {
		this.distanceFromHome = distanceFromHome;
	}

	public Timestamp getGpsRecordTime() {
		return this.gpsRecordTime;
	}

	public void setGpsRecordTime(Timestamp gpsRecordTime) {
		this.gpsRecordTime = gpsRecordTime;
	}

}
