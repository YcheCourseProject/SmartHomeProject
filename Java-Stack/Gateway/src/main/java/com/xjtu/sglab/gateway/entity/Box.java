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
 * Box entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "box", catalog = "smarthome")
public class Box implements java.io.Serializable {

	// Fields

	private Integer boxId;
	private Room room;
	private String developmentBoardIp;
	private String controlModelIp;
	private Set<Lamp> lamps = new HashSet<Lamp>(0);
	private Set<LightSensor> lightSensors = new HashSet<LightSensor>(0);
	private Set<FlameSensor> flameSensors = new HashSet<FlameSensor>(0);
	private Set<GasSensor> gasSensors = new HashSet<GasSensor>(0);
	private Set<TemperatureSensor> temperatureSensors = new HashSet<TemperatureSensor>(
			0);
	private Set<Curtain> curtains = new HashSet<Curtain>(0);
	private Set<WaterHeater> waterHeaters = new HashSet<WaterHeater>(0);
	private Set<AirCondition> airConditions = new HashSet<AirCondition>(0);
	private Set<PlrSensor> plrSensors = new HashSet<PlrSensor>(0);

	// Constructors

	/** default constructor */
	public Box() {
	}

	/** minimal constructor */
	public Box(String developmentBoardIp, String controlModelIp) {
		this.developmentBoardIp = developmentBoardIp;
		this.controlModelIp = controlModelIp;
	}

	/** full constructor */
	public Box(Room room, String developmentBoardIp, String controlModelIp,
			Set<Lamp> lamps, Set<LightSensor> lightSensors,
			Set<FlameSensor> flameSensors, Set<GasSensor> gasSensors,
			Set<TemperatureSensor> temperatureSensors, Set<Curtain> curtains,
			Set<WaterHeater> waterHeaters, Set<AirCondition> airConditions,
			Set<PlrSensor> plrSensors) {
		this.room = room;
		this.developmentBoardIp = developmentBoardIp;
		this.controlModelIp = controlModelIp;
		this.lamps = lamps;
		this.lightSensors = lightSensors;
		this.flameSensors = flameSensors;
		this.gasSensors = gasSensors;
		this.temperatureSensors = temperatureSensors;
		this.curtains = curtains;
		this.waterHeaters = waterHeaters;
		this.airConditions = airConditions;
		this.plrSensors = plrSensors;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "box_id", unique = true, nullable = false)
	public Integer getBoxId() {
		return this.boxId;
	}

	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "development_board_ip", nullable = false, length = 65535)
	public String getDevelopmentBoardIp() {
		return this.developmentBoardIp;
	}

	public void setDevelopmentBoardIp(String developmentBoardIp) {
		this.developmentBoardIp = developmentBoardIp;
	}

	@Column(name = "control_model_ip", nullable = false, length = 65535)
	public String getControlModelIp() {
		return this.controlModelIp;
	}

	public void setControlModelIp(String controlModelIp) {
		this.controlModelIp = controlModelIp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<Lamp> getLamps() {
		return this.lamps;
	}

	public void setLamps(Set<Lamp> lamps) {
		this.lamps = lamps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<LightSensor> getLightSensors() {
		return this.lightSensors;
	}

	public void setLightSensors(Set<LightSensor> lightSensors) {
		this.lightSensors = lightSensors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<FlameSensor> getFlameSensors() {
		return this.flameSensors;
	}

	public void setFlameSensors(Set<FlameSensor> flameSensors) {
		this.flameSensors = flameSensors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<GasSensor> getGasSensors() {
		return this.gasSensors;
	}

	public void setGasSensors(Set<GasSensor> gasSensors) {
		this.gasSensors = gasSensors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<TemperatureSensor> getTemperatureSensors() {
		return this.temperatureSensors;
	}

	public void setTemperatureSensors(Set<TemperatureSensor> temperatureSensors) {
		this.temperatureSensors = temperatureSensors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<Curtain> getCurtains() {
		return this.curtains;
	}

	public void setCurtains(Set<Curtain> curtains) {
		this.curtains = curtains;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<WaterHeater> getWaterHeaters() {
		return this.waterHeaters;
	}

	public void setWaterHeaters(Set<WaterHeater> waterHeaters) {
		this.waterHeaters = waterHeaters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<AirCondition> getAirConditions() {
		return this.airConditions;
	}

	public void setAirConditions(Set<AirCondition> airConditions) {
		this.airConditions = airConditions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "box")
	public Set<PlrSensor> getPlrSensors() {
		return this.plrSensors;
	}

	public void setPlrSensors(Set<PlrSensor> plrSensors) {
		this.plrSensors = plrSensors;
	}

}