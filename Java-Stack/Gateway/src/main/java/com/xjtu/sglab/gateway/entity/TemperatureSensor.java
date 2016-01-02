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
 * TemperatureSensor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "temperature_sensor", catalog = "smarthome")
public class TemperatureSensor implements java.io.Serializable {

	// Fields

	private Integer temperatureSensorId;
	private Box box;
	private Set<HumidityData> humidityDatas = new HashSet<HumidityData>(0);
	private Set<TemperatureSensorData> temperatureSensorDatas = new HashSet<TemperatureSensorData>(
			0);

	// Constructors

	/** default constructor */
	public TemperatureSensor() {
	}

	/** full constructor */
	public TemperatureSensor(Box box, Set<HumidityData> humidityDatas,
			Set<TemperatureSensorData> temperatureSensorDatas) {
		this.box = box;
		this.humidityDatas = humidityDatas;
		this.temperatureSensorDatas = temperatureSensorDatas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "temperature_sensor_id", unique = true, nullable = false)
	public Integer getTemperatureSensorId() {
		return this.temperatureSensorId;
	}

	public void setTemperatureSensorId(Integer temperatureSensorId) {
		this.temperatureSensorId = temperatureSensorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "temperatureSensor")
	public Set<HumidityData> getHumidityDatas() {
		return this.humidityDatas;
	}

	public void setHumidityDatas(Set<HumidityData> humidityDatas) {
		this.humidityDatas = humidityDatas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "temperatureSensor")
	public Set<TemperatureSensorData> getTemperatureSensorDatas() {
		return this.temperatureSensorDatas;
	}

	public void setTemperatureSensorDatas(
			Set<TemperatureSensorData> temperatureSensorDatas) {
		this.temperatureSensorDatas = temperatureSensorDatas;
	}

}