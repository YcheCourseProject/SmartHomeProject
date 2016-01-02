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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * LocationType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "location_type", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "loc_type_id", unique = true, nullable = false)
	public Integer getLocTypeId() {
		return this.locTypeId;
	}

	public void setLocTypeId(Integer locTypeId) {
		this.locTypeId = locTypeId;
	}

	@Column(name = "loc_type", nullable = false, length = 15)
	public String getLocType() {
		return this.locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "locationType")
	public Set<GpsInfoToDemand> getGpsInfoToDemands() {
		return this.gpsInfoToDemands;
	}

	public void setGpsInfoToDemands(Set<GpsInfoToDemand> gpsInfoToDemands) {
		this.gpsInfoToDemands = gpsInfoToDemands;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "locationType")
	public Set<GpsInfo> getGpsInfos() {
		return this.gpsInfos;
	}

	public void setGpsInfos(Set<GpsInfo> gpsInfos) {
		this.gpsInfos = gpsInfos;
	}

}