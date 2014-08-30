package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbSchoolunion entity. @author MyEclipse Persistence Tools
 */

public class TbSchoolunion implements java.io.Serializable {

	// Fields

	private Integer schoolid;
	private String schoolname;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public TbSchoolunion() {
	}

	/** full constructor */
	public TbSchoolunion(String schoolname, Timestamp addtime) {
		this.schoolname = schoolname;
		this.addtime = addtime;
	}

	// Property accessors

	public Integer getSchoolid() {
		return this.schoolid;
	}

	public void setSchoolid(Integer schoolid) {
		this.schoolid = schoolid;
	}

	public String getSchoolname() {
		return this.schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}