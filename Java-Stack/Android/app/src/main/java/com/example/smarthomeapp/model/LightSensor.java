package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class LightSensor implements java.io.Serializable {

	// Fields

	private Integer lightSensorId;
	private Box box;
	private Set<LightSensorData> lightSensorDatas = new HashSet<LightSensorData>(
			0);

	// Constructors

	/** default constructor */
	public LightSensor() {
	}

	/** full constructor */
	public LightSensor(Box box, Set<LightSensorData> lightSensorDatas) {
		this.box = box;
		this.lightSensorDatas = lightSensorDatas;
	}

	// Property accessors
	public Integer getLightSensorId() {
		return this.lightSensorId;
	}

	public void setLightSensorId(Integer lightSensorId) {
		this.lightSensorId = lightSensorId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Set<LightSensorData> getLightSensorDatas() {
		return this.lightSensorDatas;
	}

	public void setLightSensorDatas(Set<LightSensorData> lightSensorDatas) {
		this.lightSensorDatas = lightSensorDatas;
	}

}
