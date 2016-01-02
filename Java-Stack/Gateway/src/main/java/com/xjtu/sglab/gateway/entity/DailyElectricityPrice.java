package com.xjtu.sglab.gateway.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DailyElectricityPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "daily_electricity_price", catalog = "smarthome")
public class DailyElectricityPrice implements java.io.Serializable {

	// Fields

	private Integer dailyElectricityPriceId;
	private Date date;
	private Float pricePeriod0;
	private Float pricePeriod1;
	private Float pricePeriod2;
	private Float pricePeriod3;
	private Float pricePeriod4;
	private Float pricePeriod5;
	private Float pricePeriod6;
	private Float pricePeriod7;
	private Float pricePeriod8;
	private Float pricePeriod9;
	private Float pricePeriod10;
	private Float pricePeriod11;
	private Float pricePeriod12;
	private Float pricePeriod13;
	private Float pircePeriod14;
	private Float pricePeriod15;
	private Float pricePeriod16;
	private Float pricePeriod17;
	private Float pricePeriod18;
	private Float pricePeriod19;
	private Float pricePeriod20;
	private Float pricePeriod21;
	private Float pricePeriod22;
	private Float pricePeriod23;

	// Constructors

	/** default constructor */
	public DailyElectricityPrice() {
	}

	/** full constructor */
	public DailyElectricityPrice(Date date, Float pricePeriod0,
			Float pricePeriod1, Float pricePeriod2, Float pricePeriod3,
			Float pricePeriod4, Float pricePeriod5, Float pricePeriod6,
			Float pricePeriod7, Float pricePeriod8, Float pricePeriod9,
			Float pricePeriod10, Float pricePeriod11, Float pricePeriod12,
			Float pricePeriod13, Float pircePeriod14, Float pricePeriod15,
			Float pricePeriod16, Float pricePeriod17, Float pricePeriod18,
			Float pricePeriod19, Float pricePeriod20, Float pricePeriod21,
			Float pricePeriod22, Float pricePeriod23) {
		this.date = date;
		this.pricePeriod0 = pricePeriod0;
		this.pricePeriod1 = pricePeriod1;
		this.pricePeriod2 = pricePeriod2;
		this.pricePeriod3 = pricePeriod3;
		this.pricePeriod4 = pricePeriod4;
		this.pricePeriod5 = pricePeriod5;
		this.pricePeriod6 = pricePeriod6;
		this.pricePeriod7 = pricePeriod7;
		this.pricePeriod8 = pricePeriod8;
		this.pricePeriod9 = pricePeriod9;
		this.pricePeriod10 = pricePeriod10;
		this.pricePeriod11 = pricePeriod11;
		this.pricePeriod12 = pricePeriod12;
		this.pricePeriod13 = pricePeriod13;
		this.pircePeriod14 = pircePeriod14;
		this.pricePeriod15 = pricePeriod15;
		this.pricePeriod16 = pricePeriod16;
		this.pricePeriod17 = pricePeriod17;
		this.pricePeriod18 = pricePeriod18;
		this.pricePeriod19 = pricePeriod19;
		this.pricePeriod20 = pricePeriod20;
		this.pricePeriod21 = pricePeriod21;
		this.pricePeriod22 = pricePeriod22;
		this.pricePeriod23 = pricePeriod23;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "daily_electricity_price_id", unique = true, nullable = false)
	public Integer getDailyElectricityPriceId() {
		return this.dailyElectricityPriceId;
	}

	public void setDailyElectricityPriceId(Integer dailyElectricityPriceId) {
		this.dailyElectricityPriceId = dailyElectricityPriceId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 0)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "price_period_0", precision = 12, scale = 0)
	public Float getPricePeriod0() {
		return this.pricePeriod0;
	}

	public void setPricePeriod0(Float pricePeriod0) {
		this.pricePeriod0 = pricePeriod0;
	}

	@Column(name = "price_period_1", precision = 12, scale = 0)
	public Float getPricePeriod1() {
		return this.pricePeriod1;
	}

	public void setPricePeriod1(Float pricePeriod1) {
		this.pricePeriod1 = pricePeriod1;
	}

	@Column(name = "price_period_2", precision = 12, scale = 0)
	public Float getPricePeriod2() {
		return this.pricePeriod2;
	}

	public void setPricePeriod2(Float pricePeriod2) {
		this.pricePeriod2 = pricePeriod2;
	}

	@Column(name = "price_period_3", precision = 12, scale = 0)
	public Float getPricePeriod3() {
		return this.pricePeriod3;
	}

	public void setPricePeriod3(Float pricePeriod3) {
		this.pricePeriod3 = pricePeriod3;
	}

	@Column(name = "price_period_4", precision = 12, scale = 0)
	public Float getPricePeriod4() {
		return this.pricePeriod4;
	}

	public void setPricePeriod4(Float pricePeriod4) {
		this.pricePeriod4 = pricePeriod4;
	}

	@Column(name = "price_period_5", precision = 12, scale = 0)
	public Float getPricePeriod5() {
		return this.pricePeriod5;
	}

	public void setPricePeriod5(Float pricePeriod5) {
		this.pricePeriod5 = pricePeriod5;
	}

	@Column(name = "price_period_6", precision = 12, scale = 0)
	public Float getPricePeriod6() {
		return this.pricePeriod6;
	}

	public void setPricePeriod6(Float pricePeriod6) {
		this.pricePeriod6 = pricePeriod6;
	}

	@Column(name = "price_period_7", precision = 12, scale = 0)
	public Float getPricePeriod7() {
		return this.pricePeriod7;
	}

	public void setPricePeriod7(Float pricePeriod7) {
		this.pricePeriod7 = pricePeriod7;
	}

	@Column(name = "price_period_8", precision = 12, scale = 0)
	public Float getPricePeriod8() {
		return this.pricePeriod8;
	}

	public void setPricePeriod8(Float pricePeriod8) {
		this.pricePeriod8 = pricePeriod8;
	}

	@Column(name = "price_period_9", precision = 12, scale = 0)
	public Float getPricePeriod9() {
		return this.pricePeriod9;
	}

	public void setPricePeriod9(Float pricePeriod9) {
		this.pricePeriod9 = pricePeriod9;
	}

	@Column(name = "price_period_10", precision = 12, scale = 0)
	public Float getPricePeriod10() {
		return this.pricePeriod10;
	}

	public void setPricePeriod10(Float pricePeriod10) {
		this.pricePeriod10 = pricePeriod10;
	}

	@Column(name = "price_period_11", precision = 12, scale = 0)
	public Float getPricePeriod11() {
		return this.pricePeriod11;
	}

	public void setPricePeriod11(Float pricePeriod11) {
		this.pricePeriod11 = pricePeriod11;
	}

	@Column(name = "price_period_12", precision = 12, scale = 0)
	public Float getPricePeriod12() {
		return this.pricePeriod12;
	}

	public void setPricePeriod12(Float pricePeriod12) {
		this.pricePeriod12 = pricePeriod12;
	}

	@Column(name = "price_period_13", precision = 12, scale = 0)
	public Float getPricePeriod13() {
		return this.pricePeriod13;
	}

	public void setPricePeriod13(Float pricePeriod13) {
		this.pricePeriod13 = pricePeriod13;
	}

	@Column(name = "pirce_period_14", precision = 12, scale = 0)
	public Float getPircePeriod14() {
		return this.pircePeriod14;
	}

	public void setPircePeriod14(Float pircePeriod14) {
		this.pircePeriod14 = pircePeriod14;
	}

	@Column(name = "price_period_15", precision = 12, scale = 0)
	public Float getPricePeriod15() {
		return this.pricePeriod15;
	}

	public void setPricePeriod15(Float pricePeriod15) {
		this.pricePeriod15 = pricePeriod15;
	}

	@Column(name = "price_period_16", precision = 12, scale = 0)
	public Float getPricePeriod16() {
		return this.pricePeriod16;
	}

	public void setPricePeriod16(Float pricePeriod16) {
		this.pricePeriod16 = pricePeriod16;
	}

	@Column(name = "price_period_17", precision = 12, scale = 0)
	public Float getPricePeriod17() {
		return this.pricePeriod17;
	}

	public void setPricePeriod17(Float pricePeriod17) {
		this.pricePeriod17 = pricePeriod17;
	}

	@Column(name = "price_period_18", precision = 12, scale = 0)
	public Float getPricePeriod18() {
		return this.pricePeriod18;
	}

	public void setPricePeriod18(Float pricePeriod18) {
		this.pricePeriod18 = pricePeriod18;
	}

	@Column(name = "price_period_19", precision = 12, scale = 0)
	public Float getPricePeriod19() {
		return this.pricePeriod19;
	}

	public void setPricePeriod19(Float pricePeriod19) {
		this.pricePeriod19 = pricePeriod19;
	}

	@Column(name = "price_period_20", precision = 12, scale = 0)
	public Float getPricePeriod20() {
		return this.pricePeriod20;
	}

	public void setPricePeriod20(Float pricePeriod20) {
		this.pricePeriod20 = pricePeriod20;
	}

	@Column(name = "price_period_21", precision = 12, scale = 0)
	public Float getPricePeriod21() {
		return this.pricePeriod21;
	}

	public void setPricePeriod21(Float pricePeriod21) {
		this.pricePeriod21 = pricePeriod21;
	}

	@Column(name = "price_period_22", precision = 12, scale = 0)
	public Float getPricePeriod22() {
		return this.pricePeriod22;
	}

	public void setPricePeriod22(Float pricePeriod22) {
		this.pricePeriod22 = pricePeriod22;
	}

	@Column(name = "price_period_23", precision = 12, scale = 0)
	public Float getPricePeriod23() {
		return this.pricePeriod23;
	}

	public void setPricePeriod23(Float pricePeriod23) {
		this.pricePeriod23 = pricePeriod23;
	}

}