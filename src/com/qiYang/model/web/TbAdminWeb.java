package com.qiYang.model.web;

public class TbAdminWeb implements TransitionModel{
	private Integer adminId;
	private Integer schoolId;
	private String schoolName;
	private String schoolMinName;
	private String adminName;
	private String adminPassword;
	private Integer adminRoot;
	private String time;
	private String alterTime;

	public TbAdminWeb(Integer adminId, Integer schoolId, String schoolName,
			String schoolMinName, String adminName, String adminPassword,
			Integer adminRoot, String time, String alterTime) {
		super();
		this.adminId = adminId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.schoolMinName = schoolMinName;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminRoot = adminRoot;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbAdminWeb() {
		super();
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Integer getAdminRoot() {
		return adminRoot;
	}

	public void setAdminRoot(Integer adminRoot) {
		this.adminRoot = adminRoot;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}

	public <T> T toModel(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
