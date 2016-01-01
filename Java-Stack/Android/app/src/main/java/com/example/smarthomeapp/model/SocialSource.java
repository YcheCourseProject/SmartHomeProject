package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class SocialSource implements java.io.Serializable {

	// Fields

	private Integer sourceTypeId;
	private String sourceType;
	private Set<SocialInfo> socialInfos = new HashSet<SocialInfo>(0);

	// Constructors

	/** default constructor */
	public SocialSource() {
	}

	/** minimal constructor */
	public SocialSource(String sourceType) {
		this.sourceType = sourceType;
	}

	/** full constructor */
	public SocialSource(String sourceType, Set<SocialInfo> socialInfos) {
		this.sourceType = sourceType;
		this.socialInfos = socialInfos;
	}

	// Property accessors
	public Integer getSourceTypeId() {
		return this.sourceTypeId;
	}

	public void setSourceTypeId(Integer sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}

	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Set<SocialInfo> getSocialInfos() {
		return this.socialInfos;
	}

	public void setSocialInfos(Set<SocialInfo> socialInfos) {
		this.socialInfos = socialInfos;
	}

}
