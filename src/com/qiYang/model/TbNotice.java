package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbNoticeWeb;
import com.qiYang.util.TWObjectUtil;

/**
 * TbNotice entity. @author MyEclipse Persistence Tools
 */

public class TbNotice implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUserinfo tbUserinfoByParentId;
	private TbUserinfo tbUserinfoByIssuerId;
	private TbBranchschool tbBranchschool;
	private String noticeContent;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbNotice() {
	}

	/** full constructor */
	public TbNotice(TbUserinfo tbUserinfoByParentId,
			TbUserinfo tbUserinfoByIssuerId, TbBranchschool tbBranchschool,
			String noticeContent, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.tbUserinfoByParentId = tbUserinfoByParentId;
		this.tbUserinfoByIssuerId = tbUserinfoByIssuerId;
		this.tbBranchschool = tbBranchschool;
		this.noticeContent = noticeContent;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbNoticeWeb toPageWeb(){
		TbUserinfo parent = TWObjectUtil.isNullTbUserinfo(tbUserinfoByParentId);
		TbUserinfo issuer = TWObjectUtil.isNullTbUserinfo(tbUserinfoByIssuerId);
		Integer parentId=TWObjectUtil.getInteger(parent.getUserInfoId());
		String parentName=TWObjectUtil.getString(parent.getUserInfoName());
		Integer issuerId=TWObjectUtil.getInteger(issuer.getUserInfoId());
		String issuerName=TWObjectUtil.getString(issuer.getUserInfoName());
		return new TbNoticeWeb(this.id, parentId, parentName, issuerId, issuerName, TWObjectUtil.getString(this.noticeContent), TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbUserinfo getTbUserinfoByParentId() {
		return this.tbUserinfoByParentId;
	}

	public void setTbUserinfoByParentId(TbUserinfo tbUserinfoByParentId) {
		this.tbUserinfoByParentId = tbUserinfoByParentId;
	}

	public TbUserinfo getTbUserinfoByIssuerId() {
		return this.tbUserinfoByIssuerId;
	}

	public void setTbUserinfoByIssuerId(TbUserinfo tbUserinfoByIssuerId) {
		this.tbUserinfoByIssuerId = tbUserinfoByIssuerId;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
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