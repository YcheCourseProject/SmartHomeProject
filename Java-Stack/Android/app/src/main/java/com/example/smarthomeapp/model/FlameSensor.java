package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class FlameSensor implements java.io.Serializable {

	// Fields

	private Integer flameSensorId;
	private Box box;
	private Set<FlameSensorData> flameSensorDatas = new HashSet<FlameSensorData>(
			0);

	// Constructors

	/** default constructor */
	public FlameSensor() {
	}

	/** full constructor */
	public FlameSensor(Box box, Set<FlameSensorData> flameSensorDatas) {
		this.box = box;
		this.flameSensorDatas = flameSensorDatas;
	}

	// Property accessors
	public Integer getFlameSensorId() {
		return this.flameSensorId;
	}

	public void setFlameSensorId(Integer flameSensorId) {
		this.flameSensorId = flameSensorId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Set<FlameSensorData> getFlameSensorDatas() {
		return this.flameSensorDatas;
	}

	public void setFlameSensorDatas(Set<FlameSensorData> flameSensorDatas) {
		this.flameSensorDatas = flameSensorDatas;
	}

}
