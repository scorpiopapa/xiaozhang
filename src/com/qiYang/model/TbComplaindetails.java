package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbComplaindetails entity. @author MyEclipse Persistence Tools
 */

public class TbComplaindetails implements java.io.Serializable {

	// Fields

	private Integer comdetId;
	private TbUserinfo tbUserinfo;
	private TbComplain tbComplain;
	private String comdetContent;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbComplaindetails() {
	}

	/** full constructor */
	public TbComplaindetails(TbUserinfo tbUserinfo, TbComplain tbComplain,
			String comdetContent, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.tbUserinfo = tbUserinfo;
		this.tbComplain = tbComplain;
		this.comdetContent = comdetContent;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getComdetId() {
		return this.comdetId;
	}

	public void setComdetId(Integer comdetId) {
		this.comdetId = comdetId;
	}

	public TbUserinfo getTbUserinfo() {
		return this.tbUserinfo;
	}

	public void setTbUserinfo(TbUserinfo tbUserinfo) {
		this.tbUserinfo = tbUserinfo;
	}

	public TbComplain getTbComplain() {
		return this.tbComplain;
	}

	public void setTbComplain(TbComplain tbComplain) {
		this.tbComplain = tbComplain;
	}

	public String getComdetContent() {
		return this.comdetContent;
	}

	public void setComdetContent(String comdetContent) {
		this.comdetContent = comdetContent;
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