package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbRecruits entity. @author MyEclipse Persistence Tools
 */

public class TbRecruits implements java.io.Serializable {

	// Fields

	private Integer recruitid;
	private String position;
	private String require;
	private Timestamp issuetime;

	// Constructors

	/** default constructor */
	public TbRecruits() {
	}

	/** full constructor */
	public TbRecruits(String position, String require, Timestamp issuetime) {
		this.position = position;
		this.require = require;
		this.issuetime = issuetime;
	}

	// Property accessors

	public Integer getRecruitid() {
		return this.recruitid;
	}

	public void setRecruitid(Integer recruitid) {
		this.recruitid = recruitid;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRequire() {
		return this.require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public Timestamp getIssuetime() {
		return this.issuetime;
	}

	public void setIssuetime(Timestamp issuetime) {
		this.issuetime = issuetime;
	}

}