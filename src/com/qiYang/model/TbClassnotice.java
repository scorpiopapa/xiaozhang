package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbClassnoticeWeb;
import com.qiYang.util.TWObjectUtil;

/**
 * TbClassnotice entity. @author MyEclipse Persistence Tools
 */

public class TbClassnotice implements java.io.Serializable {

	// Fields

	private Integer classNoticeId;
	private TbCourse tbCourse;
	private TbSchool tbSchool;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private String clanotTitle;
	private String clanotAddTime;
	private String clanotSige;
	private Integer clanotSelect;
	private String clanotContent;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbClassnotice() {
	}

	/** full constructor */
	public TbClassnotice(TbCourse tbCourse, TbSchool tbSchool,
			TbUserinfo tbUserinfo, TbBranchschool tbBranchschool,
			String clanotTitle, String clanotAddTime, String clanotSige,
			Integer clanotSelect, String clanotContent, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.tbCourse = tbCourse;
		this.tbSchool = tbSchool;
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.clanotTitle = clanotTitle;
		this.clanotAddTime = clanotAddTime;
		this.clanotSige = clanotSige;
		this.clanotSelect = clanotSelect;
		this.clanotContent = clanotContent;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbClassnoticeWeb toListWeb() {
		TbUserinfo tbUserinfo = TWObjectUtil.isNullTbUserinfo(this.tbUserinfo);
		return new TbClassnoticeWeb(this.classNoticeId,
				tbUserinfo.getUserInfoName(), this.clanotTitle,
				this.clanotAddTime);
	}
	
	public TbClassnotice(Integer classNoticeId, String clanotTitle,
			String clanotContent) {
		super();
		this.classNoticeId = classNoticeId;
		this.clanotTitle = clanotTitle;
		this.clanotContent = clanotContent;
	}
	// Property accessors

	public Integer getClassNoticeId() {
		return this.classNoticeId;
	}

	public void setClassNoticeId(Integer classNoticeId) {
		this.classNoticeId = classNoticeId;
	}

	public TbCourse getTbCourse() {
		return this.tbCourse;
	}

	public void setTbCourse(TbCourse tbCourse) {
		this.tbCourse = tbCourse;
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

	public String getClanotTitle() {
		return this.clanotTitle;
	}

	public void setClanotTitle(String clanotTitle) {
		this.clanotTitle = clanotTitle;
	}

	public String getClanotAddTime() {
		return this.clanotAddTime;
	}

	public void setClanotAddTime(String clanotAddTime) {
		this.clanotAddTime = clanotAddTime;
	}

	public String getClanotSige() {
		return this.clanotSige;
	}

	public void setClanotSige(String clanotSige) {
		this.clanotSige = clanotSige;
	}

	public Integer getClanotSelect() {
		return this.clanotSelect;
	}

	public void setClanotSelect(Integer clanotSelect) {
		this.clanotSelect = clanotSelect;
	}

	public String getClanotContent() {
		return this.clanotContent;
	}

	public void setClanotContent(String clanotContent) {
		this.clanotContent = clanotContent;
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