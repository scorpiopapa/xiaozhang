package com.qiYang.model.web;

public class TbAttendanceWeb {
	private Integer attendanceId;
	private Integer courseId;
	private String courseName;
	private Integer schoolId;
	private String schoolName;
	private Integer userinfoId;
	private String userinfoName;
	private Integer branchschoolId;
	private String branchschoolName;
	private Integer isLate;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbAttendanceWeb(Integer attendanceId, Integer courseId,
			String courseName, Integer schoolId, String schoolName,
			Integer userinfoId, String userinfoName, Integer branchschoolId,
			String branchschoolName, Integer isLate, Integer isValid,
			String time, String alterTime) {
		super();
		this.attendanceId = attendanceId;
		this.courseId = courseId;
		this.courseName = courseName;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.isLate = isLate;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbAttendanceWeb() {
		super();
	}

	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public Integer getUserinfoId() {
		return userinfoId;
	}

	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}

	public String getUserinfoName() {
		return userinfoName;
	}

	public void setUserinfoName(String userinfoName) {
		this.userinfoName = userinfoName;
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

	public Integer getIsLate() {
		return isLate;
	}

	public void setIsLate(Integer isLate) {
		this.isLate = isLate;
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
