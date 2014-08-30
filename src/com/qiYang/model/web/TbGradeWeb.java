package com.qiYang.model.web;

public class TbGradeWeb {
	private Integer gradeId;
	private Integer schoolId;
	private String schoolName;
	private Integer branchschoolId;
	private String branchschoolName;
	private String gradeName;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbGradeWeb(Integer gradeId, Integer schoolId, String schoolName,
			Integer branchschoolId, String branchschoolName, String gradeName,
			Integer isValid, String time, String alterTime) {
		super();
		this.gradeId = gradeId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.gradeName = gradeName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbGradeWeb() {
		super();
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getBranchschoolId() {
		return branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public String getBranchschoolName() {
		return branchschoolName;
	}

	public void setBranchschoolName(String branchschoolName) {
		this.branchschoolName = branchschoolName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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
}
