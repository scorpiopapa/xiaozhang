package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbPushWeb;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

/**
 * TbPush entity. @author MyEclipse Persistence Tools
 */

public class TbPush implements java.io.Serializable {

	// Fields

	private Integer puchId;
	private TbSchool tbSchool;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private String pushType;
	private String pushToken;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbPush() {
	}

	/** full constructor */
	public TbPush(TbSchool tbSchool, TbUserinfo tbUserinfo,
			TbBranchschool tbBranchschool, String pushType, String pushToken,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.tbSchool = tbSchool;
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.pushType = pushType;
		this.pushToken = pushToken;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbPushWeb toPageWeb(){
		TWObject twObject=new TWObject();
		TbSchool tbSchool1 = twObject.isNullTbSchool(this.tbSchool);
		TbUserinfo tbUserinfo1 = twObject.isNullTbUserinfo(this.tbUserinfo);
		return new TbPushWeb(this.puchId, tbSchool1.getSchoolId(), tbSchool1.getSchoolName(), tbUserinfo1.getUserInfoId(), tbUserinfo1.getUserInfoName(), null, null, pushType, pushToken, this.isValid, TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getPuchId() {
		return this.puchId;
	}

	public void setPuchId(Integer puchId) {
		this.puchId = puchId;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
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

	public String getPushType() {
		return this.pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getPushToken() {
		return this.pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
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