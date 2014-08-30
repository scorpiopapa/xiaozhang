package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbChargeWeb;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

/**
 * TbCharge entity. @author MyEclipse Persistence Tools
 */

public class TbCharge implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbBranchschool tbBranchschool;
	private String chargeName;
	private Double vipCharge;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbCharge() {
	}

	/** full constructor */
	public TbCharge(TbBranchschool tbBranchschool, String chargeName,
			Double vipCharge, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.tbBranchschool = tbBranchschool;
		this.chargeName = chargeName;
		this.vipCharge = vipCharge;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbChargeWeb toPageWeb(){
		TWObject twObject = new TWObject();
		TbBranchschool tbBranchschool = twObject.isNullTbBranchschool(this.tbBranchschool);
		return new TbChargeWeb(this.id, tbBranchschool.getBranchSchoolId(), tbBranchschool.getBraschName(), this.chargeName, this.vipCharge, this.isValid, TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public String getChargeName() {
		return this.chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Double getVipCharge() {
		return this.vipCharge;
	}

	public void setVipCharge(Double vipCharge) {
		this.vipCharge = vipCharge;
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