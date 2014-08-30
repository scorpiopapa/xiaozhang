package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbTestfinishWeb;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

/**
 * TbTestfinish entity. @author MyEclipse Persistence Tools
 */

public class TbTestfinish implements java.io.Serializable {

	// Fields

	private Integer testfinishId;
	private TbUserinfo tbUserinfo;
	private TbTest tbTest;
	private String addDate;
	private String rightPercentage;
	private Integer isfinish;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbTestfinish() {
	}

	/** full constructor */
	public TbTestfinish(TbUserinfo tbUserinfo, TbTest tbTest, String addDate,
			String rightPercentage, Integer isfinish, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.tbUserinfo = tbUserinfo;
		this.tbTest = tbTest;
		this.addDate = addDate;
		this.rightPercentage = rightPercentage;
		this.isfinish = isfinish;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbTestfinishWeb toPageWeb() {
		TWObject twObject = new TWObject();
		TbUserinfo tbUserinfo1 = twObject.isNullTbUserinfo(this.tbUserinfo);
		TbTest tbTest1 = twObject.isNullTbTest(this.tbTest);
		return new TbTestfinishWeb(this.testfinishId, tbUserinfo1.getUserInfoId(), tbUserinfo1.getUserInfoName(), tbTest1.getTestId(), tbTest1.getTestName(), TWObjectUtil.getString(this.addDate), this.rightPercentage, this.isfinish, this.isValid, TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getTestfinishId() {
		return this.testfinishId;
	}

	public void setTestfinishId(Integer testfinishId) {
		this.testfinishId = testfinishId;
	}

	public TbUserinfo getTbUserinfo() {
		return this.tbUserinfo;
	}

	public void setTbUserinfo(TbUserinfo tbUserinfo) {
		this.tbUserinfo = tbUserinfo;
	}

	public TbTest getTbTest() {
		return this.tbTest;
	}

	public void setTbTest(TbTest tbTest) {
		this.tbTest = tbTest;
	}

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getRightPercentage() {
		return this.rightPercentage;
	}

	public void setRightPercentage(String rightPercentage) {
		this.rightPercentage = rightPercentage;
	}

	public Integer getIsfinish() {
		return this.isfinish;
	}

	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
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