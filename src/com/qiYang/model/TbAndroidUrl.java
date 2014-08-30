package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbAndroidUrl entity. @author MyEclipse Persistence Tools
 */

public class TbAndroidUrl implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer marketSign;
	private String marketName;
	private String softUrl;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbAndroidUrl() {
	}

	/** full constructor */
	public TbAndroidUrl(Integer marketSign, String marketName, String softUrl,
			Timestamp time, Timestamp alterTime) {
		this.marketSign = marketSign;
		this.marketName = marketName;
		this.softUrl = softUrl;
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

	public Integer getMarketSign() {
		return this.marketSign;
	}

	public void setMarketSign(Integer marketSign) {
		this.marketSign = marketSign;
	}

	public String getMarketName() {
		return this.marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getSoftUrl() {
		return this.softUrl;
	}

	public void setSoftUrl(String softUrl) {
		this.softUrl = softUrl;
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