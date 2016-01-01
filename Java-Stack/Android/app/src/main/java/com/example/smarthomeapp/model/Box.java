package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
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
	public Integer getBoxId() {
		return this.boxId;
	}

	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getDevelopmentBoardIp() {
		return this.developmentBoardIp;
	}

	public void setDevelopmentBoardIp(String developmentBoardIp) {
		this.developmentBoardIp = developmentBoardIp;
	}

	public String getControlModelIp() {
		return this.controlModelIp;
	}

	public void setControlModelIp(String controlModelIp) {
		this.controlModelIp = controlModelIp;
	}

	public Set<Lamp> getLamps() {
		return this.lamps;
	}

	public void setLamps(Set<Lamp> lamps) {
		this.lamps = lamps;
	}

	public Set<LightSensor> getLightSensors() {
		return this.lightSensors;
	}

	public void setLightSensors(Set<LightSensor> lightSensors) {
		this.lightSensors = lightSensors;
	}

	public Set<FlameSensor> getFlameSensors() {
		return this.flameSensors;
	}

	public void setFlameSensors(Set<FlameSensor> flameSensors) {
		this.flameSensors = flameSensors;
	}

	public Set<GasSensor> getGasSensors() {
		return this.gasSensors;
	}

	public void setGasSensors(Set<GasSensor> gasSensors) {
		this.gasSensors = gasSensors;
	}

	public Set<TemperatureSensor> getTemperatureSensors() {
		return this.temperatureSensors;
	}

	public void setTemperatureSensors(Set<TemperatureSensor> temperatureSensors) {
		this.temperatureSensors = temperatureSensors;
	}

	public Set<Curtain> getCurtains() {
		return this.curtains;
	}

	public void setCurtains(Set<Curtain> curtains) {
		this.curtains = curtains;
	}

	public Set<WaterHeater> getWaterHeaters() {
		return this.waterHeaters;
	}

	public void setWaterHeaters(Set<WaterHeater> waterHeaters) {
		this.waterHeaters = waterHeaters;
	}

	public Set<AirCondition> getAirConditions() {
		return this.airConditions;
	}

	public void setAirConditions(Set<AirCondition> airConditions) {
		this.airConditions = airConditions;
	}

	public Set<PlrSensor> getPlrSensors() {
		return this.plrSensors;
	}

	public void setPlrSensors(Set<PlrSensor> plrSensors) {
		this.plrSensors = plrSensors;
	}

}
