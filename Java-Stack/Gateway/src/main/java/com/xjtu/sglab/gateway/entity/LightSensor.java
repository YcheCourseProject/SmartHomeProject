package com.xjtu.sglab.gateway.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * LightSensor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "light_sensor", catalog = "smarthome")
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

	/** minimal constructor */
	public LightSensor(Integer lightSensorId) {
		this.lightSensorId = lightSensorId;
	}

	/** full constructor */
	public LightSensor(Integer lightSensorId, Box box,
			Set<LightSensorData> lightSensorDatas) {
		this.lightSensorId = lightSensorId;
		this.box = box;
		this.lightSensorDatas = lightSensorDatas;
	}

	// Property accessors
	@Id
	@Column(name = "light_sensor_id", unique = true, nullable = false)
	public Integer getLightSensorId() {
		return this.lightSensorId;
	}

	public void setLightSensorId(Integer lightSensorId) {
		this.lightSensorId = lightSensorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lightSensor")
	public Set<LightSensorData> getLightSensorDatas() {
		return this.lightSensorDatas;
	}

	public void setLightSensorDatas(Set<LightSensorData> lightSensorDatas) {
		this.lightSensorDatas = lightSensorDatas;
	}

}