package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbSysconfig entity. @author MyEclipse Persistence Tools
 */

public class TbSysconfig implements java.io.Serializable {

	// Fields

	private Integer sysconfigId;
	private String versionIos;
	private String versionAndroid;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbSysconfig() {
	}

	/** full constructor */
	public TbSysconfig(String versionIos, String versionAndroid,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.versionIos = versionIos;
		this.versionAndroid = versionAndroid;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getSysconfigId() {
		return this.sysconfigId;
	}

	public void setSysconfigId(Integer sysconfigId) {
		this.sysconfigId = sysconfigId;
	}

	public String getVersionIos() {
		return this.versionIos;
	}

	public void setVersionIos(String versionIos) {
		this.versionIos = versionIos;
	}

	public String getVersionAndroid() {
		return this.versionAndroid;
	}

	public void setVersionAndroid(String versionAndroid) {
		this.versionAndroid = versionAndroid;
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