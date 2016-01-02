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
 * GpsInfoToDemand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gps_info_to_demand", catalog = "smarthome")
public class GpsInfoToDemand implements java.io.Serializable {

	// Fields

	private Integer gpsInfoToDemandId;
	private LocationType locationType;
	private Float distanceFromHome;
	private String gpsInfoBeginTime;
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
			String gpsInfoBeginTime, Set<WaterHeaterDemand> waterHeaterDemands,
			Set<AirConditionDemand> airConditionDemands) {
		this.locationType = locationType;
		this.distanceFromHome = distanceFromHome;
		this.gpsInfoBeginTime = gpsInfoBeginTime;
		this.waterHeaterDemands = waterHeaterDemands;
		this.airConditionDemands = airConditionDemands;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gps_info_to_demand_id", unique = true, nullable = false)
	public Integer getGpsInfoToDemandId() {
		return this.gpsInfoToDemandId;
	}

	public void setGpsInfoToDemandId(Integer gpsInfoToDemandId) {
		this.gpsInfoToDemandId = gpsInfoToDemandId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loc_type_id")
	public LocationType getLocationType() {
		return this.locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	@Column(name = "distance_from_home", precision = 12, scale = 0)
	public Float getDistanceFromHome() {
		return this.distanceFromHome;
	}

	public void setDistanceFromHome(Float distanceFromHome) {
		this.distanceFromHome = distanceFromHome;
	}

	@Column(name = "gps_info_begin_time", length = 30)
	public String getGpsInfoBeginTime() {
		return this.gpsInfoBeginTime;
	}

	public void setGpsInfoBeginTime(String gpsInfoBeginTime) {
		this.gpsInfoBeginTime = gpsInfoBeginTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gpsInfoToDemand")
	public Set<WaterHeaterDemand> getWaterHeaterDemands() {
		return this.waterHeaterDemands;
	}

	public void setWaterHeaterDemands(Set<WaterHeaterDemand> waterHeaterDemands) {
		this.waterHeaterDemands = waterHeaterDemands;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gpsInfoToDemand")
	public Set<AirConditionDemand> getAirConditionDemands() {
		return this.airConditionDemands;
	}

	public void setAirConditionDemands(
			Set<AirConditionDemand> airConditionDemands) {
		this.airConditionDemands = airConditionDemands;
	}

}