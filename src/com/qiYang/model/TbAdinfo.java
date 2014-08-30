package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbAdinfo entity. @author MyEclipse Persistence Tools
 */

public class TbAdinfo implements java.io.Serializable {

	// Fields

	private Integer adid;
	private String adimage;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public TbAdinfo() {
	}

	/** full constructor */
	public TbAdinfo(String adimage, Timestamp addtime) {
		this.adimage = adimage;
		this.addtime = addtime;
	}

	// Property accessors

	public Integer getAdid() {
		return this.adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	public String getAdimage() {
		return this.adimage;
	}

	public void setAdimage(String adimage) {
		this.adimage = adimage;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}