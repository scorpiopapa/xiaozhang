package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.util.TWDataUtil;

/**
 * TbTeam entity. @author MyEclipse Persistence Tools
 */

public class TbTeam implements java.io.Serializable {

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
	private String startTime1;
	private String expireTime1;
	private String beginTime1;
	private String endTime1;
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
	public TbTeam() {
	}

	/** full constructor */
	public TbTeam(Integer schoolId, Integer adminid, String title,
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
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
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

	public String getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
		this.startTime=TWDataUtil.getStartTimestamp(startTime1);
	}

	public String getExpireTime1() {
		return expireTime1;
	}

	public void setExpireTime1(String expireTime1) {
		this.expireTime1 = expireTime1;
		this.expireTime=TWDataUtil.getEndTimestamp(expireTime1);
	}

	public String getBeginTime1() {
		return beginTime1;
	}

	public void setBeginTime1(String beginTime1) {
		this.beginTime1 = beginTime1;
		this.beginTime=TWDataUtil.getStartTimestamp(beginTime1);
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
		this.endTime=TWDataUtil.getEndTimestamp(endTime1);
	}
	
}