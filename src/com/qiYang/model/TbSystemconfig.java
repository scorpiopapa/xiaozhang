package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbSystemconfig entity. @author MyEclipse Persistence Tools
 */

public class TbSystemconfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String systemType;
	private String minVersion;
	private String maxVersion;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbSystemconfig() {
	}

	/** full constructor */
	public TbSystemconfig(String systemType, String minVersion,
			String maxVersion, Timestamp time, Timestamp alterTime) {
		this.systemType = systemType;
		this.minVersion = minVersion;
		this.maxVersion = maxVersion;
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

	public String getSystemType() {
		return this.systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getMinVersion() {
		return this.minVersion;
	}

	public void setMinVersion(String minVersion) {
		this.minVersion = minVersion;
	}

	public String getMaxVersion() {
		return this.maxVersion;
	}

	public void setMaxVersion(String maxVersion) {
		this.maxVersion = maxVersion;
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