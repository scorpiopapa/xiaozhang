package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbBranchschool02 entity. @author MyEclipse Persistence Tools
 */

public class TbBranchschool02 implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer branchschoolId;
	private String overview;
	private Integer quantity;
	private String type;
	private Integer showType;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbBranchschool02() {
	}

	/** full constructor */
	public TbBranchschool02(Integer branchschoolId, String overview,
			Integer quantity, String type, Integer showType, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.branchschoolId = branchschoolId;
		this.overview = overview;
		this.quantity = quantity;
		this.type = type;
		this.showType = showType;
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

	public Integer getBranchschoolId() {
		return this.branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public String getOverview() {
		return this.overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getShowType() {
		return this.showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
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