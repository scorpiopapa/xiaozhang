package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbAdmin entity. @author MyEclipse Persistence Tools
 */

public class TbAdmin implements java.io.Serializable {

	// Fields

	private Integer adminId;
	private TbSchool tbSchool;
	private TbBranchschool tbBranchschool;
	private String adminName;
	private String adminPassword;
	private Integer adminRoot;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbInfonotices = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbAdmin() {
	}

	/** full constructor */
	public TbAdmin(TbSchool tbSchool, TbBranchschool tbBranchschool,
			String adminName, String adminPassword, Integer adminRoot,
			Integer isValid, Timestamp time, Timestamp alterTime,
			Set tbInfonotices) {
		this.tbSchool = tbSchool;
		this.tbBranchschool = tbBranchschool;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminRoot = adminRoot;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbInfonotices = tbInfonotices;
	}

	// Property accessors

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Integer getAdminRoot() {
		return this.adminRoot;
	}

	public void setAdminRoot(Integer adminRoot) {
		this.adminRoot = adminRoot;
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

	public Set getTbInfonotices() {
		return this.tbInfonotices;
	}

	public void setTbInfonotices(Set tbInfonotices) {
		this.tbInfonotices = tbInfonotices;
	}

}