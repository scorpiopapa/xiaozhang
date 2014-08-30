package com.qiYang.model.web;

public class TbChargeWeb {
	private Integer id;
	private Integer branchschoolId;
	private String branchschoolName;
	private String chargeName;
	private Double vipCharge;
	private Integer isValid;
	private String time;
	private String alterTime;
	
	public TbChargeWeb(Integer id, Integer branchschoolId,
			String branchschoolName, String chargeName, Double vipCharge,
			Integer isValid, String time, String alterTime) {
		super();
		this.id = id;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.chargeName = chargeName;
		this.vipCharge = vipCharge;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBranchschoolId() {
		return branchschoolId;
	}
	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}
	public String getBranchschoolName() {
		return branchschoolName;
	}
	public void setBranchschoolName(String branchschoolName) {
		this.branchschoolName = branchschoolName;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	
	public Double getVipCharge() {
		return vipCharge;
	}
	public void setVipCharge(Double vipCharge) {
		this.vipCharge = vipCharge;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAlterTime() {
		return alterTime;
	}
	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}
	
}
