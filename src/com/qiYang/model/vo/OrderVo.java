/**
 * 
 */
package com.qiYang.model.vo;

/**
 * @author coolgo
 * 
 *         winerdaxian@163.com
 */
public class OrderVo {
	private Integer billsId;
	private String createrName;
	private String branchschool;
	private Integer tradeStatus; // 订单状态
	private Double totalFee;
	private String goodsName;
	private String goodsDescription;
	private String outTradeNo;
	private Long time;
	private Long alterTime;

	/**
	 * @param billsId
	 * @param createrName
	 * @param branchschool
	 * @param tradeStatus
	 * @param totalFee
	 * @param goodsName
	 * @param goodsDescription
	 * @param outTradeNo
	 * @param time
	 * @param alterTime
	 */
	public OrderVo(Integer billsId, String createrName, String branchschool,
			Integer tradeStatus, Double totalFee, String goodsName,
			String goodsDescription, String outTradeNo, Long time,
			Long alterTime) {
		super();
		this.billsId = billsId;
		this.createrName = createrName;
		this.branchschool = branchschool;
		this.tradeStatus = tradeStatus;
		this.totalFee = totalFee;
		this.goodsName = goodsName;
		this.goodsDescription = goodsDescription;
		this.outTradeNo = outTradeNo;
		this.time = time;
		this.alterTime = alterTime;
	}

	/**
	 * @return the billsId
	 */
	public Integer getBillsId() {
		return billsId;
	}

	/**
	 * @param billsId
	 *            the billsId to set
	 */
	public void setBillsId(Integer billsId) {
		this.billsId = billsId;
	}

	/**
	 * @return the createrName
	 */
	public String getCreaterName() {
		return createrName;
	}

	/**
	 * @param createrName
	 *            the createrName to set
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	/**
	 * @return the branchschool
	 */
	public String getBranchschool() {
		return branchschool;
	}

	/**
	 * @param branchschool
	 *            the branchschool to set
	 */
	public void setBranchschool(String branchschool) {
		this.branchschool = branchschool;
	}

	/**
	 * @return the tradeStatus
	 */
	public Integer getTradeStatus() {
		return tradeStatus;
	}

	/**
	 * @param tradeStatus
	 *            the tradeStatus to set
	 */
	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	/**
	 * @return the totalFee
	 */
	public Double getTotalFee() {
		return totalFee;
	}

	/**
	 * @param totalFee
	 *            the totalFee to set
	 */
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return the goodsDescription
	 */
	public String getGoodsDescription() {
		return goodsDescription;
	}

	/**
	 * @param goodsDescription
	 *            the goodsDescription to set
	 */
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	/**
	 * @return the outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * @param outTradeNo
	 *            the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}

	/**
	 * @return the alterTime
	 */
	public Long getAlterTime() {
		return alterTime;
	}

	/**
	 * @param alterTime
	 *            the alterTime to set
	 */
	public void setAlterTime(Long alterTime) {
		this.alterTime = alterTime;
	}

}
