package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbMore entity. @author MyEclipse Persistence Tools
 */

public class TbMore implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbMore() {
	}

	/** full constructor */
	public TbMore(String title, String content, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.title = title;
		this.content = content;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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