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
 * FlameSensor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "flame_sensor", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "flame_sensor_id", unique = true, nullable = false)
	public Integer getFlameSensorId() {
		return this.flameSensorId;
	}

	public void setFlameSensorId(Integer flameSensorId) {
		this.flameSensorId = flameSensorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flameSensor")
	public Set<FlameSensorData> getFlameSensorDatas() {
		return this.flameSensorDatas;
	}

	public void setFlameSensorDatas(Set<FlameSensorData> flameSensorDatas) {
		this.flameSensorDatas = flameSensorDatas;
	}

}