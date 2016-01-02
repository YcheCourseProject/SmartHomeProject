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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SocialSource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "social_source", catalog = "smarthome")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "source_type_id", unique = true, nullable = false)
	public Integer getSourceTypeId() {
		return this.sourceTypeId;
	}

	public void setSourceTypeId(Integer sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}

	@Column(name = "source_type", nullable = false, length = 65535)
	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "socialSource")
	public Set<SocialInfo> getSocialInfos() {
		return this.socialInfos;
	}

	public void setSocialInfos(Set<SocialInfo> socialInfos) {
		this.socialInfos = socialInfos;
	}

}