package com.qiYang.model.web;

public class TbComplainWeb {
	private Integer complainId;
	private Integer schoolId;
	private String schoolName;
	private Integer parentId;
	private String parentName;
	private String studentName;
	private String courseName;
	private Integer branchschoolId;
	private String branchschoolName;
	private Integer teacherId;
	private String teacherName;
	private String complainContent;
	private Integer isValid;
	private Integer isSatisfie;
	private String complainStatus;
	private String time;
	private String alterTime;
	
	public TbComplainWeb(Integer complainId, Integer schoolId,
			String schoolName, Integer parentId, String parentName,String studentName,String courseName,
			Integer branchschoolId, String branchschoolName, Integer teacherId,
			String teacherName, String complainContent, Integer isValid,
			Integer isSatisfie, String complainStatus, String time,
			String alterTime) {
		super();
		this.complainId = complainId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.parentId = parentId;
		this.parentName = parentName;
		this.studentName = studentName;
		this.courseName = courseName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.complainContent = complainContent;
		this.isValid = isValid;
		this.isSatisfie = isSatisfie;
		this.complainStatus = complainStatus;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbComplainWeb() {
		super();
	}
	
	public Integer getIsSatisfie() {
		return isSatisfie;
	}

	public void setIsSatisfie(Integer isSatisfie) {
		this.isSatisfie = isSatisfie;
	}

	public Integer getComplainId() {
		return complainId;
	}

	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getComplainContent() {
		return complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getComplainStatus() {
		return complainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
