package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbSchoolPhoto entity. @author MyEclipse Persistence Tools
 */

public class TbSchoolPhoto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725349882040427419L;
	private Integer id;
	private Integer schoolId;
	private Integer branchschoolId;
	private String photoPath;
	private Integer adminId;
	private String pusherName;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbSchoolPhoto() {
	}

	/** full constructor */
	public TbSchoolPhoto(Integer schoolId, Integer branchschoolId,
			String photoPath, Integer adminId,String pusherName, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.schoolId = schoolId;
		this.branchschoolId = branchschoolId;
		this.photoPath = photoPath;
		this.adminId = adminId;
		this.pusherName=pusherName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getBranchschoolId() {
		return this.branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	
	public String getPusherName() {
		return pusherName;
	}

	public void setPusherName(String pusherName) {
		this.pusherName = pusherName;
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