package com.qiYang.model;

import java.sql.Timestamp;
import java.util.Date;

import com.qiYang.model.web.TbViptimeWeb;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

/**
 * TbViptime entity. @author MyEclipse Persistence Tools
 */

public class TbViptime implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private Date startTime;
	private Date endTime;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbViptime() {
	}

	/** full constructor */
	public TbViptime(TbUserinfo tbUserinfo, TbBranchschool tbBranchschool,
			Date startTime, Date endTime, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbViptimeWeb toPageWeb(){
		TWObject twObject = new TWObject();
		TbUserinfo tbUserinfo = twObject.isNullTbUserinfo(this.tbUserinfo);
		TbBranchschool tbBranchschool = twObject.isNullTbBranchschool(this.tbBranchschool);
		return new TbViptimeWeb(this.id, tbUserinfo.getUserInfoId(), tbUserinfo.getUserInfoName(), tbBranchschool.getBranchSchoolId(), tbBranchschool.getBraschName(), TWObjectUtil.getDate(this.startTime), TWObjectUtil.getDate(this.endTime), this.isValid, TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbUserinfo getTbUserinfo() {
		return this.tbUserinfo;
	}

	public void setTbUserinfo(TbUserinfo tbUserinfo) {
		this.tbUserinfo = tbUserinfo;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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