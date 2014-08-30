package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbOrder entity. @author MyEclipse Persistence Tools
 */

public class TbOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer service;
	private String status;
	private Integer userinfoId;
	private Integer adminId;
	private Integer townId;
	private Integer schoolId;
	private Integer branchschoolId;
	private String outTradeNo;
	private String tradeNo;
	private Integer curriculumId;
	private Integer quantity;
	private Integer teamId;
	private Double price;
	private Double origin;
	private Integer type;
	private Timestamp payTime;
	private Timestamp refundTime;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbOrder() {
	}

	/** full constructor */
	public TbOrder(Integer service, String status, Integer userinfoId,
			Integer adminId, Integer townId, Integer schoolId,
			Integer branchschoolId, String outTradeNo, String tradeNo,
			Integer curriculumId, Integer quantity, Integer teamId,
			Double price, Double origin, Integer type, Timestamp payTime,
			Timestamp refundTime, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.service = service;
		this.status = status;
		this.userinfoId = userinfoId;
		this.adminId = adminId;
		this.townId = townId;
		this.schoolId = schoolId;
		this.branchschoolId = branchschoolId;
		this.outTradeNo = outTradeNo;
		this.tradeNo = tradeNo;
		this.curriculumId = curriculumId;
		this.quantity = quantity;
		this.teamId = teamId;
		this.price = price;
		this.origin = origin;
		this.type = type;
		this.payTime = payTime;
		this.refundTime = refundTime;
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

	public Integer getService() {
		return this.service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUserinfoId() {
		return this.userinfoId;
	}

	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getTownId() {
		return this.townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getBranchschoolId() {
		return this.branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public Integer getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOrigin() {
		return this.origin;
	}

	public void setOrigin(Double origin) {
		this.origin = origin;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public Timestamp getRefundTime() {
		return this.refundTime;
	}

	public void setRefundTime(Timestamp refundTime) {
		this.refundTime = refundTime;
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