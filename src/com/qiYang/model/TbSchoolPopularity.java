package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbSchoolPopularity entity. @author MyEclipse Persistence Tools
 */

public class TbSchoolPopularity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer schoolId;
	private Integer userInfoId;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbSchoolPopularity() {
	}

	/** full constructor */
	public TbSchoolPopularity(Integer schoolId, Integer userInfoId,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.schoolId = schoolId;
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

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
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