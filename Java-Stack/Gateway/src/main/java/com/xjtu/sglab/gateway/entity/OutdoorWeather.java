package com.xjtu.sglab.gateway.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OutdoorWeather entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "outdoor_weather", catalog = "smarthome")
public class OutdoorWeather implements java.io.Serializable {

	// Fields

	private Integer outdoorWeatherId;
	private Float outdoorWeatherTem;
	private Float outdoorWeatherHum;
	private Float outdoorWeatherPres;
	private Timestamp outdoorWeatherDate;

	// Constructors

	/** default constructor */
	public OutdoorWeather() {
	}

	/** minimal constructor */
	public OutdoorWeather(Timestamp outdoorWeatherDate) {
		this.outdoorWeatherDate = outdoorWeatherDate;
	}

	/** full constructor */
	public OutdoorWeather(Float outdoorWeatherTem, Float outdoorWeatherHum,
			Float outdoorWeatherPres, Timestamp outdoorWeatherDate) {
		this.outdoorWeatherTem = outdoorWeatherTem;
		this.outdoorWeatherHum = outdoorWeatherHum;
		this.outdoorWeatherPres = outdoorWeatherPres;
		this.outdoorWeatherDate = outdoorWeatherDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "outdoor_weather_id", unique = true, nullable = false)
	public Integer getOutdoorWeatherId() {
		return this.outdoorWeatherId;
	}

	public void setOutdoorWeatherId(Integer outdoorWeatherId) {
		this.outdoorWeatherId = outdoorWeatherId;
	}

	@Column(name = "outdoor_weather_tem", precision = 12, scale = 0)
	public Float getOutdoorWeatherTem() {
		return this.outdoorWeatherTem;
	}

	public void setOutdoorWeatherTem(Float outdoorWeatherTem) {
		this.outdoorWeatherTem = outdoorWeatherTem;
	}

	@Column(name = "outdoor_weather_hum", precision = 12, scale = 0)
	public Float getOutdoorWeatherHum() {
		return this.outdoorWeatherHum;
	}

	public void setOutdoorWeatherHum(Float outdoorWeatherHum) {
		this.outdoorWeatherHum = outdoorWeatherHum;
	}

	@Column(name = "outdoor_weather_pres", precision = 12, scale = 0)
	public Float getOutdoorWeatherPres() {
		return this.outdoorWeatherPres;
	}

	public void setOutdoorWeatherPres(Float outdoorWeatherPres) {
		this.outdoorWeatherPres = outdoorWeatherPres;
	}

	@Column(name = "outdoor_weather_date", nullable = false, length = 0)
	public Timestamp getOutdoorWeatherDate() {
		return this.outdoorWeatherDate;
	}

	public void setOutdoorWeatherDate(Timestamp outdoorWeatherDate) {
		this.outdoorWeatherDate = outdoorWeatherDate;
	}

}