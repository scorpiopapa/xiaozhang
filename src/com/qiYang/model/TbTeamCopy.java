package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbTeamCopy entity. @author MyEclipse Persistence Tools
 */

public class TbTeamCopy implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer schoolId;
	private Integer adminid;
	private String schoolName;
	private String title;
	private String remark;
	private Double price;
	private Integer quantity;
	private Double teamPrice;
	private Integer nowNumber;
	private String detail;
	private String summary;
	private Timestamp startTime;
	private Timestamp expireTime;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Integer isUseRefund;
	private Integer isLateRefund;
	private Integer isUseUnreadRefund;
	private Integer isReadRefund;
	private String imgurl;
	private Integer type;
	private Integer status;
	private Integer inventory;
	private Long sortOrder;
	private Long sortOrder2;
	private Integer isValid;
	private Integer teamType;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbTeamCopy() {
	}

	/** full constructor */
	public TbTeamCopy(Integer schoolId, Integer adminid, String title,
			String remark, Double price, Integer quantity, Double teamPrice,
			Integer nowNumber, String detail, String summary,
			Timestamp startTime, Timestamp expireTime, Timestamp beginTime,
			Timestamp endTime, Integer isUseRefund, Integer isLateRefund,
			Integer isUseUnreadRefund, Integer isReadRefund, String imgurl,
			Integer type, Integer status, Integer inventory, Long sortOrder,
			Long sortOrder2, Integer isValid, Integer teamType, Timestamp time,
			Timestamp alterTime) {
		this.schoolId = schoolId;
		this.adminid = adminid;
		this.title = title;
		this.remark = remark;
		this.price = price;
		this.quantity = quantity;
		this.teamPrice = teamPrice;
		this.nowNumber = nowNumber;
		this.detail = detail;
		this.summary = summary;
		this.startTime = startTime;
		this.expireTime = expireTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.isUseRefund = isUseRefund;
		this.isLateRefund = isLateRefund;
		this.isUseUnreadRefund = isUseUnreadRefund;
		this.isReadRefund = isReadRefund;
		this.imgurl = imgurl;
		this.type = type;
		this.status = status;
		this.inventory = inventory;
		this.sortOrder = sortOrder;
		this.sortOrder2 = sortOrder2;
		this.isValid = isValid;
		this.teamType = teamType;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbTeamCopy(TbTeam tbTeam) {
		if(tbTeam!=null){
		this.schoolId = tbTeam.getSchoolId();
		this.adminid = tbTeam.getAdminid();
		this.title = tbTeam.getTitle();
		this.remark = tbTeam.getRemark();
		this.price = tbTeam.getPrice();
		this.quantity = tbTeam.getQuantity();
		this.teamPrice = tbTeam.getTeamPrice();
		this.nowNumber = tbTeam.getNowNumber();
		this.detail = tbTeam.getDetail();
		this.summary = tbTeam.getSummary();
		this.startTime = tbTeam.getStartTime();
		this.expireTime = tbTeam.getExpireTime();
		this.beginTime = tbTeam.getBeginTime();
		this.endTime = tbTeam.getEndTime();
		this.isUseRefund = tbTeam.getIsUseRefund();
		this.isLateRefund = tbTeam.getIsLateRefund();
		this.isUseUnreadRefund = tbTeam.getIsUseUnreadRefund();
		this.isReadRefund = tbTeam.getIsReadRefund();
		this.imgurl = tbTeam.getImgurl();
		this.type = tbTeam.getType();
		this.status = tbTeam.getStatus();
		this.inventory = tbTeam.getInventory();
		this.sortOrder = tbTeam.getSortOrder();
		this.sortOrder2 = tbTeam.getSortOrder2();
		this.isValid = tbTeam.getIsValid();
		this.teamType = tbTeam.getTeamType();
		this.time = tbTeam.getTime();
		this.alterTime = tbTeam.getAlterTime();
		}
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getAdminid() {
		return this.adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTeamPrice() {
		return this.teamPrice;
	}

	public void setTeamPrice(Double teamPrice) {
		this.teamPrice = teamPrice;
	}

	public Integer getNowNumber() {
		return this.nowNumber;
	}

	public void setNowNumber(Integer nowNumber) {
		this.nowNumber = nowNumber;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getIsUseRefund() {
		return this.isUseRefund;
	}

	public void setIsUseRefund(Integer isUseRefund) {
		this.isUseRefund = isUseRefund;
	}

	public Integer getIsLateRefund() {
		return this.isLateRefund;
	}

	public void setIsLateRefund(Integer isLateRefund) {
		this.isLateRefund = isLateRefund;
	}

	public Integer getIsUseUnreadRefund() {
		return this.isUseUnreadRefund;
	}

	public void setIsUseUnreadRefund(Integer isUseUnreadRefund) {
		this.isUseUnreadRefund = isUseUnreadRefund;
	}

	public Integer getIsReadRefund() {
		return this.isReadRefund;
	}

	public void setIsReadRefund(Integer isReadRefund) {
		this.isReadRefund = isReadRefund;
	}

	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getInventory() {
		return this.inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Long getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getSortOrder2() {
		return this.sortOrder2;
	}

	public void setSortOrder2(Long sortOrder2) {
		this.sortOrder2 = sortOrder2;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getTeamType() {
		return this.teamType;
	}

	public void setTeamType(Integer teamType) {
		this.teamType = teamType;
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