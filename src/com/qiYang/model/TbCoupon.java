package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbCoupon entity. @author MyEclipse Persistence Tools
 */

public class TbCoupon implements java.io.Serializable {

	// Fields

	private String id;
	private Integer userinfoId;
	private Integer schoolId;
	private Integer adminId;
	private Integer teamId;
	private Integer curriculumId;
	private Integer orderId;
	private Integer type;
	private String secret;
	private Integer consume;
	private Timestamp expireTime;
	private Timestamp consumeTime;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbCoupon() {
	}

	/** full constructor */
	public TbCoupon(Integer userinfoId, Integer schoolId,Integer adminId, Integer teamId,
			Integer curriculumId, Integer orderId, Integer type, String secret,
			Integer consume, Timestamp expireTime, Timestamp consumeTime,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.userinfoId = userinfoId;
		this.schoolId = schoolId;
		this.adminId=adminId;
		this.teamId = teamId;
		this.curriculumId = curriculumId;
		this.orderId = orderId;
		this.type = type;
		this.secret = secret;
		this.consume = consume;
		this.expireTime = expireTime;
		this.consumeTime = consumeTime;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserinfoId() {
		return this.userinfoId;
	}

	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getCurriculumId() {
		return this.curriculumId;
	}

	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSecret() {
		return this.secret;
	}
	
	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Integer getConsume() {
		return this.consume;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	public Timestamp getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}

	public Timestamp getConsumeTime() {
		return this.consumeTime;
	}

	public void setConsumeTime(Timestamp consumeTime) {
		this.consumeTime = consumeTime;
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