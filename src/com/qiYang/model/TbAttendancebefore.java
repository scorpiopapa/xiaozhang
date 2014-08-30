package com.qiYang.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TbAttendancebefore entity. @author MyEclipse Persistence Tools
 */

public class TbAttendancebefore implements java.io.Serializable {

	// Fields

	private Integer attbefId;
	private TbCourse tbCourse;
	private TbSchool tbSchool;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private Integer isLate;
	private Integer isValid;
	private Date attbefDate;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbAttendancebefore() {
	}

	/** full constructor */
	public TbAttendancebefore(TbCourse tbCourse, TbSchool tbSchool,
			TbUserinfo tbUserinfo, TbBranchschool tbBranchschool,
			Integer isLate, Integer isValid, Date attbefDate, Timestamp time,
			Timestamp alterTime) {
		this.tbCourse = tbCourse;
		this.tbSchool = tbSchool;
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.isLate = isLate;
		this.isValid = isValid;
		this.attbefDate = attbefDate;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getAttbefId() {
		return this.attbefId;
	}

	public void setAttbefId(Integer attbefId) {
		this.attbefId = attbefId;
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

	public Integer getIsLate() {
		return this.isLate;
	}

	public void setIsLate(Integer isLate) {
		this.isLate = isLate;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getAttbefDate() {
		return this.attbefDate;
	}

	public void setAttbefDate(Date attbefDate) {
		this.attbefDate = attbefDate;
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