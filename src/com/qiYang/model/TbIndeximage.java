package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbIndeximage entity. @author MyEclipse Persistence Tools
 */

public class TbIndeximage implements java.io.Serializable {

	// Fields

	private Integer imageid;
	private String imagepath;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public TbIndeximage() {
	}

	/** full constructor */
	public TbIndeximage(String imagepath, Timestamp addtime) {
		this.imagepath = imagepath;
		this.addtime = addtime;
	}

	// Property accessors

	public Integer getImageid() {
		return this.imageid;
	}

	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}

	public String getImagepath() {
		return this.imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}