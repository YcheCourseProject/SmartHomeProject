package com.xjtu.sglab.gateway.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "smarthome")
public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userSalt;
	private String userHash;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Integer userId, String userName, String userSalt,
			String userHash) {
		this.userId = userId;
		this.userName = userName;
		this.userSalt = userSalt;
		this.userHash = userHash;
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", nullable = false, length = 30)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_salt", nullable = false, length = 30)
	public String getUserSalt() {
		return this.userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	@Column(name = "user_hash", nullable = false, length = 30)
	public String getUserHash() {
		return this.userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

}