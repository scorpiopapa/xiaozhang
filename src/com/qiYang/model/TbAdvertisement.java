package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbAdvertisement entity. @author MyEclipse Persistence Tools
 */

public class TbAdvertisement implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private String hyperlink;
	private Integer type;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbAdvertisement() {
	}

	/** full constructor */
	public TbAdvertisement(String url, String hyperlink,Integer type, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.url = url;
		this.hyperlink = hyperlink;
		this.type=type;
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHyperlink() {
		return this.hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
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