package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbCurriculumPopularity entity. @author MyEclipse Persistence Tools
 */

public class TbCurriculumPopularity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer curriculumId;
	private Integer userInfoId;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbCurriculumPopularity() {
	}

	/** full constructor */
	public TbCurriculumPopularity(Integer curriculumId, Integer userInfoId,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.curriculumId = curriculumId;
		this.userInfoId = userInfoId;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurriculumId() {
		return this.curriculumId;
	}

	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}

	public Integer getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getAlterTime() {
		return this.alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

}