package com.xjtu.sglab.gateway.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_address", catalog = "smarthome")
public class UserAddress implements java.io.Serializable {

	// Fields

	private Integer userAddressId;
	private Float userAddressLatitude;
	private Float userAddressLongitude;
	private String userAddressDetail;

	// Constructors

	/** default constructor */
	public UserAddress() {
	}

	/** full constructor */
	public UserAddress(Float userAddressLatitude, Float userAddressLongitude,
			String userAddressDetail) {
		this.userAddressLatitude = userAddressLatitude;
		this.userAddressLongitude = userAddressLongitude;
		this.userAddressDetail = userAddressDetail;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_address_id", unique = true, nullable = false)
	public Integer getUserAddressId() {
		return this.userAddressId;
	}

	public void setUserAddressId(Integer userAddressId) {
		this.userAddressId = userAddressId;
	}

	@Column(name = "user_address_latitude", nullable = false, precision = 12, scale = 0)
	public Float getUserAddressLatitude() {
		return this.userAddressLatitude;
	}

	public void setUserAddressLatitude(Float userAddressLatitude) {
		this.userAddressLatitude = userAddressLatitude;
	}

	@Column(name = "user_address_longitude", nullable = false, precision = 12, scale = 0)
	public Float getUserAddressLongitude() {
		return this.userAddressLongitude;
	}

	public void setUserAddressLongitude(Float userAddressLongitude) {
		this.userAddressLongitude = userAddressLongitude;
	}

	@Column(name = "user_address_detail", nullable = false, length = 65535)
	public String getUserAddressDetail() {
		return this.userAddressDetail;
	}

	public void setUserAddressDetail(String userAddressDetail) {
		this.userAddressDetail = userAddressDetail;
	}

}