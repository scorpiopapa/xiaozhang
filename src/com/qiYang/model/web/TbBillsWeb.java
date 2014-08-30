package com.qiYang.model.web;

public class TbBillsWeb {
	private Integer billsId;
	private Integer userinfoId;
	private String  userinfoName;
	private Integer branchschoolId;
	private String branchschoolName;
	// TRADE_FINISHED 付款完成 1； WAIT_BUYER_PAY 等待付款 2
	private Integer tradeStatus; // 0 未付款，1付款
	// Fields
	private Integer totalMonth;
	private Double totalFee;
	private String goodsName;
	private String goodsDescription;
	private String outTradeNo;
	private String tradeNo;
	private String successTime;
	private String time;
	private String alterTime;
	public TbBillsWeb(Integer billsId, Integer userinfoId, String userinfoName,
			Integer branchschoolId, String branchschoolName,
			Integer tradeStatus, Integer totalMonth, Double totalFee,
			String goodsName, String goodsDescription, String outTradeNo,
			String tradeNo, String successTime, String time, String alterTime) {
		super();
		this.billsId = billsId;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.tradeStatus = tradeStatus;
		this.totalMonth = totalMonth;
		this.totalFee = totalFee;
		this.goodsName = goodsName;
		this.goodsDescription = goodsDescription;
		this.outTradeNo = outTradeNo;
		this.tradeNo = tradeNo;
		this.successTime = successTime;
		this.time = time;
		this.alterTime = alterTime;
	}
	public Integer getBillsId() {
		return billsId;
	}
	public void setBillsId(Integer billsId) {
		this.billsId = billsId;
	}
	public Integer getUserinfoId() {
		return userinfoId;
	}
	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}
	public String getUserinfoName() {
		return userinfoName;
	}
	public void setUserinfoName(String userinfoName) {
		this.userinfoName = userinfoName;
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
	public Integer getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public Integer getTotalMonth() {
		return totalMonth;
	}
	public void setTotalMonth(Integer totalMonth) {
		this.totalMonth = totalMonth;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDescription() {
		return goodsDescription;
	}
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
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
