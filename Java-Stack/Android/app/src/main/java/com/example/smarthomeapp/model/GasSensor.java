package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class GasSensor implements java.io.Serializable {

	// Fields

	private Integer gasSensorId;
	private Box box;
	private Set<GasSensorData> gasSensorDatas = new HashSet<GasSensorData>(0);

	// Constructors

	/** default constructor */
	public GasSensor() {
	}

	/** full constructor */
	public GasSensor(Box box, Set<GasSensorData> gasSensorDatas) {
		this.box = box;
		this.gasSensorDatas = gasSensorDatas;
	}

	// Property accessors
	public Integer getGasSensorId() {
		return this.gasSensorId;
	}

	public void setGasSensorId(Integer gasSensorId) {
		this.gasSensorId = gasSensorId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Set<GasSensorData> getGasSensorDatas() {
		return this.gasSensorDatas;
	}

	public void setGasSensorDatas(Set<GasSensorData> gasSensorDatas) {
		this.gasSensorDatas = gasSensorDatas;
	}

}
