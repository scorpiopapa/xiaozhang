package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbUserinfo2 entity. @author MyEclipse Persistence Tools
 */

public class TbUserinfo2 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -504970663257513754L;
	private Integer id;
	private Integer userinfoId;//家长
	private Integer studentId;//孩子
	private Integer gradeId;
	private Integer cityId;
	private Integer isValid;
	private Double latitude;
	private Double longitude;
	private Timestamp gradeUpdateTime;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbUserinfo2() {
	}

	/** full constructor */
	public TbUserinfo2(Integer studentId,Integer userinfoId, Integer gradeId, Integer cityId,Integer isValid,Timestamp gradeUpdateTime,
			Timestamp time, Timestamp alterTime,Double latitude,Double longitude) {
		this.userinfoId = userinfoId;
		this.gradeId = gradeId;
		this.studentId = studentId;
		this.cityId = cityId;
		this.isValid=isValid;
		this.gradeUpdateTime=gradeUpdateTime;
		this.latitude=latitude;
		this.longitude=longitude;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Timestamp getGradeUpdateTime() {
		return gradeUpdateTime;
	}

	public void setGradeUpdateTime(Timestamp gradeUpdateTime) {
		this.gradeUpdateTime = gradeUpdateTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserinfoId() {
		return this.userinfoId;
	}

	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}

	public Integer getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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