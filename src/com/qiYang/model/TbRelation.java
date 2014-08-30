package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbRelation entity. @author MyEclipse Persistence Tools
 */

public class TbRelation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer relationId;
	private TbUserinfo tbUserinfoByUserInfoId;//儿子
	private TbUserinfo tbUserinfoByTbUserInfoId;//父亲
	private Integer isValid;
	private Double childLongitude;
	private Double childLatitude;
	private String phoneIMEI;
	private Integer locationRate;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbRelation() {
	}

	/** full constructor */
	public TbRelation(TbUserinfo tbUserinfoByUserInfoId,
			TbUserinfo tbUserinfoByTbUserInfoId, Integer isValid,
			Double childLongitude, Double childLatitude, Timestamp time,
			Timestamp alterTime) {
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
		this.isValid = isValid;
		this.childLongitude = childLongitude;
		this.childLatitude = childLatitude;
		this.time = time;
		this.alterTime = alterTime;
	}
	
	// Property accessors

	public Integer getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public TbUserinfo getTbUserinfoByUserInfoId() {
		return this.tbUserinfoByUserInfoId;
	}

	public void setTbUserinfoByUserInfoId(TbUserinfo tbUserinfoByUserInfoId) {
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
	}

	public TbUserinfo getTbUserinfoByTbUserInfoId() {
		return this.tbUserinfoByTbUserInfoId;
	}

	public void setTbUserinfoByTbUserInfoId(TbUserinfo tbUserinfoByTbUserInfoId) {
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
	}
	
	public String getPhoneIMEI() {
		return phoneIMEI;
	}

	public void setPhoneIMEI(String phoneIMEI) {
		this.phoneIMEI = phoneIMEI;
	}

	public Integer getLocationRate() {
		return locationRate;
	}

	public void setLocationRate(Integer locationRate) {
		this.locationRate = locationRate;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Double getChildLongitude() {
		return this.childLongitude;
	}

	public void setChildLongitude(Double childLongitude) {
		this.childLongitude = childLongitude;
	}

	public Double getChildLatitude() {
		return this.childLatitude;
	}

	public void setChildLatitude(Double childLatitude) {
		this.childLatitude = childLatitude;
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