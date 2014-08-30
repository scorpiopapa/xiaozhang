package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbBranchschoolPopularity entity. @author MyEclipse Persistence Tools
 */

public class TbBranchschoolPopularity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer branchschoolId;
	private Integer userInfoId;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbBranchschoolPopularity() {
	}

	/** full constructor */
	public TbBranchschoolPopularity(Integer branchschoolId, Integer userInfoId,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.branchschoolId = branchschoolId;
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

	public Integer getBranchschoolId() {
		return this.branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
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