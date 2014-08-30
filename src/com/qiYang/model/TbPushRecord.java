package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbPushRecord entity. @author MyEclipse Persistence Tools
 */

public class TbPushRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sendId;
	private Integer acceptId;
	private String name;
	private String title;
	private String content;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbPushRecord() {
	}

	/** full constructor */
	public TbPushRecord(Integer sendId, Integer acceptId, String title,
			String content, Integer isValid, Timestamp time, Timestamp alterTime) {
		this.sendId = sendId;
		this.acceptId = acceptId;
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

	public Integer getSendId() {
		return this.sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	public Integer getAcceptId() {
		return this.acceptId;
	}

	public void setAcceptId(Integer acceptId) {
		this.acceptId = acceptId;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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