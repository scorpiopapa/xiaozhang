package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbCurriculum02 entity. @author MyEclipse Persistence Tools
 */

public class TbCurriculum02 implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer curriculumId;
	private Integer quantity;
	private String teamPrice;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbCurriculum02() {
	}

	/** full constructor */
	public TbCurriculum02(Integer curriculumId, Integer quantity,String teamPrice,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.curriculumId = curriculumId;
		this.quantity = quantity;
		this.teamPrice=teamPrice;
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
	
	public String getTeamPrice() {
		return teamPrice;
	}

	public void setTeamPrice(String teamPrice) {
		this.teamPrice = teamPrice;
	}

	public Integer getCurriculumId() {
		return this.curriculumId;
	}

	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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