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
 * PlrSensor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "plr_sensor", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "plr_sensor_id", unique = true, nullable = false)
	public Integer getPlrSensorId() {
		return this.plrSensorId;
	}

	public void setPlrSensorId(Integer plrSensorId) {
		this.plrSensorId = plrSensorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plrSensor")
	public Set<PlrSensorData> getPlrSensorDatas() {
		return this.plrSensorDatas;
	}

	public void setPlrSensorDatas(Set<PlrSensorData> plrSensorDatas) {
		this.plrSensorDatas = plrSensorDatas;
	}

}