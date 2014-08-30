package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbSchoolnotice entity. @author MyEclipse Persistence Tools
 */

public class TbSchoolnotice implements java.io.Serializable {

	// Fields

	private Integer schoolNoticeId;
	private TbSchool tbSchool;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private String schnotTitle;
	private String schnotContent;
	private String addTime;
	private Integer isAllSchool;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbSchoolnotice() {
	}

	/** full constructor */
	public TbSchoolnotice(TbSchool tbSchool, TbUserinfo tbUserinfo,
			TbBranchschool tbBranchschool, String schnotTitle,
			String schnotContent, String addTime, Integer isAllSchool,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.tbSchool = tbSchool;
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.schnotTitle = schnotTitle;
		this.schnotContent = schnotContent;
		this.addTime = addTime;
		this.isAllSchool = isAllSchool;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	
	// Property accessors

	public Integer getSchoolNoticeId() {
		return this.schoolNoticeId;
	}

	public void setSchoolNoticeId(Integer schoolNoticeId) {
		this.schoolNoticeId = schoolNoticeId;
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

	public String getSchnotTitle() {
		return this.schnotTitle;
	}

	public void setSchnotTitle(String schnotTitle) {
		this.schnotTitle = schnotTitle;
	}

	public String getSchnotContent() {
		return this.schnotContent;
	}

	public void setSchnotContent(String schnotContent) {
		this.schnotContent = schnotContent;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getIsAllSchool() {
		return this.isAllSchool;
	}

	public void setIsAllSchool(Integer isAllSchool) {
		this.isAllSchool = isAllSchool;
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