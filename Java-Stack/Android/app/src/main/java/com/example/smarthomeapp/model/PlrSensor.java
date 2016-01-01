package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class PlrSensor implements java.io.Serializable {

	// Fields

	private Integer plrSensorId;
	private Box box;
	private Set<PlrSensorData> plrSensorDatas = new HashSet<PlrSensorData>(0);

	// Constructors

	/** default constructor */
	public PlrSensor() {
	}

	/** full constructor */
	public PlrSensor(Box box, Set<PlrSensorData> plrSensorDatas) {
		this.box = box;
		this.plrSensorDatas = plrSensorDatas;
	}

	// Property accessors
	public Integer getPlrSensorId() {
		return this.plrSensorId;
	}

	public void setPlrSensorId(Integer plrSensorId) {
		this.plrSensorId = plrSensorId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Set<PlrSensorData> getPlrSensorDatas() {
		return this.plrSensorDatas;
	}

	public void setPlrSensorDatas(Set<PlrSensorData> plrSensorDatas) {
		this.plrSensorDatas = plrSensorDatas;
	}

}
