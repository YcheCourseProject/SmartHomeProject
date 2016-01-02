package com.xjtu.sglab.gateway.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GpsInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gps_info", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gps_id", unique = true, nullable = false)
	public Integer getGpsId() {
		return this.gpsId;
	}

	public void setGpsId(Integer gpsId) {
		this.gpsId = gpsId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "loc_type_id")
	public LocationType getLocationType() {
		return this.locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	@Column(name = "gps_longitude", nullable = false, precision = 22, scale = 0)
	public Double getGpsLongitude() {
		return this.gpsLongitude;
	}

	public void setGpsLongitude(Double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	@Column(name = "gps_latitude", nullable = false, precision = 22, scale = 0)
	public Double getGpsLatitude() {
		return this.gpsLatitude;
	}

	public void setGpsLatitude(Double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	@Column(name = "distance_from_home", nullable = false, precision = 12, scale = 0)
	public Float getDistanceFromHome() {
		return this.distanceFromHome;
	}

	public void setDistanceFromHome(Float distanceFromHome) {
		this.distanceFromHome = distanceFromHome;
	}

	@Column(name = "gps_record_time", nullable = false, length = 0)
	public Timestamp getGpsRecordTime() {
		return this.gpsRecordTime;
	}

	public void setGpsRecordTime(Timestamp gpsRecordTime) {
		this.gpsRecordTime = gpsRecordTime;
	}

}