package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbAttendance entity. @author MyEclipse Persistence Tools
 */

public class TbAttendance implements java.io.Serializable {

	// Fields

	private Integer attendanceId;
	private TbCourse tbCourse;
	private TbSchool tbSchool;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private Integer isLate;//1到校，0是未到校。1是未离校，0是已离校
	private Integer isValid;
	private String addDate;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbAttendance() {
	}

	/** full constructor */
	public TbAttendance(TbCourse tbCourse, TbSchool tbSchool,
			TbUserinfo tbUserinfo, TbBranchschool tbBranchschool,
			Integer isLate, Integer isValid, String addDate, Timestamp time,
			Timestamp alterTime) {
		this.tbCourse = tbCourse;
		this.tbSchool = tbSchool;
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.isLate = isLate;
		this.isValid = isValid;
		this.addDate = addDate;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getAttendanceId() {
		return this.attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
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

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
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