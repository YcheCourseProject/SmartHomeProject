package com.example.smarthomeapp.model;


/**
 */
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
	public Integer getUserAddressId() {
		return this.userAddressId;
	}

	public void setUserAddressId(Integer userAddressId) {
		this.userAddressId = userAddressId;
	}

	public Float getUserAddressLatitude() {
		return this.userAddressLatitude;
	}

	public void setUserAddressLatitude(Float userAddressLatitude) {
		this.userAddressLatitude = userAddressLatitude;
	}

	public Float getUserAddressLongitude() {
		return this.userAddressLongitude;
	}

	public void setUserAddressLongitude(Float userAddressLongitude) {
		this.userAddressLongitude = userAddressLongitude;
	}

	public String getUserAddressDetail() {
		return this.userAddressDetail;
	}

	public void setUserAddressDetail(String userAddressDetail) {
		this.userAddressDetail = userAddressDetail;
	}

}
