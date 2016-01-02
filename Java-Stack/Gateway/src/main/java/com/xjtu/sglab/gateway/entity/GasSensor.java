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
 * GasSensor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gas_sensor", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gas_sensor_id", unique = true, nullable = false)
	public Integer getGasSensorId() {
		return this.gasSensorId;
	}

	public void setGasSensorId(Integer gasSensorId) {
		this.gasSensorId = gasSensorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gasSensor")
	public Set<GasSensorData> getGasSensorDatas() {
		return this.gasSensorDatas;
	}

	public void setGasSensorDatas(Set<GasSensorData> gasSensorDatas) {
		this.gasSensorDatas = gasSensorDatas;
	}

}