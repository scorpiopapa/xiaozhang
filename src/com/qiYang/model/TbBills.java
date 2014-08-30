package com.qiYang.model;

import java.sql.Timestamp;
import java.util.Date;
import com.qiYang.model.web.TbBillsWeb;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

/**
 * TbBills entity. @author MyEclipse Persistence Tools
 */

public class TbBills implements java.io.Serializable {
	public static final int TRADE_FINISHED = 1;
	public static final int WAIT_BUYER_PAY = 2;

	// Fields

	private Integer billsId;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	// TRADE_FINISHED 付款完成 1； WAIT_BUYER_PAY 等待付款 2
	private Integer tradeStatus; // 0 未付款，1付款
	// Fields
	// Fields

	private Integer totalMonth;
	private Double totalFee;
	private String goodsName;
	private String goodsDescription;
	private String outTradeNo;
	private String tradeNo;
	private Date successTime;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbBills() {
	}

	/** full constructor */
	public TbBills(TbUserinfo tbUserinfo, TbBranchschool tbBranchschool,
			Integer totalMonth, Integer tradeStatus, Double totalFee,
			String goodsName, String goodsDescription, String outTradeNo,
			String tradeNo, Date successTime, Timestamp time,
			Timestamp alterTime) {
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.totalMonth = totalMonth;
		this.tradeStatus = tradeStatus;
		this.totalFee = totalFee;
		this.goodsName = goodsName;
		this.goodsDescription = goodsDescription;
		this.outTradeNo = outTradeNo;
		this.tradeNo = tradeNo;
		this.successTime = successTime;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbBills(TbUserinfo tbUserinfo, TbBranchschool tbBranchschool,
			Double totalFee, String goodsName, String goodsDescription,
			String outTradeNo) {
		this(tbUserinfo, tbBranchschool, 0, 0, totalFee, goodsName,
				goodsDescription, outTradeNo, null, null, null, null);
		Timestamp currentStamp = new Timestamp(System.currentTimeMillis());
		this.successTime=new Date(System.currentTimeMillis());
		this.time = currentStamp;
		this.alterTime = currentStamp;
	}
	
	public TbBills(TbUserinfo tbUserinfo, Integer totalMonth, Date successTime) {
		super();
		this.tbUserinfo = tbUserinfo;
		this.totalMonth = totalMonth;
		this.successTime = successTime;
	}

	public TbBillsWeb toPageWeb(){
		TWObject twObject = new TWObject();
		TbUserinfo tbUserinfo = twObject.isNullTbUserinfo(this.tbUserinfo);
		TbBranchschool tbBranchschool = twObject.isNullTbBranchschool(this.tbBranchschool);
		return new TbBillsWeb(this.billsId, tbUserinfo.getUserInfoId(), tbUserinfo.getUserInfoName(), tbBranchschool.getBranchSchoolId(), tbBranchschool.getBraschName(), this.tradeStatus, this.totalMonth, this.totalFee, this.goodsName, this.goodsDescription, this.outTradeNo, this.tradeNo, TWObjectUtil.getDate(this.successTime), TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	// Property accessors
	}
	
	public Integer getBillsId() {
		return this.billsId;
	}

	public void setBillsId(Integer billsId) {
		this.billsId = billsId;
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

	public Integer getTotalMonth() {
		return this.totalMonth;
	}

	public void setTotalMonth(Integer totalMonth) {
		this.totalMonth = totalMonth;
	}

	public Integer getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDescription() {
		return this.goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
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

	public Date getSuccessTime() {
		return this.successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
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