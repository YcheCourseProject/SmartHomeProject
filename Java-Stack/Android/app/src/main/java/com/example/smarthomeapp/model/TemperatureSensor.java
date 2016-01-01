package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class TemperatureSensor implements java.io.Serializable {

	// Fields

	private Integer temperatureSensorId;
	private Box box;
	private Set<TemperatureSensorData> temperatureSensorDatas = new HashSet<TemperatureSensorData>(
			0);

	// Constructors

	/** default constructor */
	public TemperatureSensor() {
	}

	/** full constructor */
	public TemperatureSensor(Box box,
			Set<TemperatureSensorData> temperatureSensorDatas) {
		this.box = box;
		this.temperatureSensorDatas = temperatureSensorDatas;
	}

	// Property accessors
	public Integer getTemperatureSensorId() {
		return this.temperatureSensorId;
	}

	public void setTemperatureSensorId(Integer temperatureSensorId) {
		this.temperatureSensorId = temperatureSensorId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Set<TemperatureSensorData> getTemperatureSensorDatas() {
		return this.temperatureSensorDatas;
	}

	public void setTemperatureSensorDatas(
			Set<TemperatureSensorData> temperatureSensorDatas) {
		this.temperatureSensorDatas = temperatureSensorDatas;
	}

}
