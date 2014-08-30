package com.qiYang.model.web;

public class TbUserinfoclassWeb {
	private Integer useclaId;
	private Integer courseId;
	private Integer schoolId;
	private Integer userinfoId;
	private Integer branchschoolId;
	private String userName;
	private String studentUserName;
	private String parentUserName;
	private String courseName;
	private String schoolName;
	private String schoolMinName;
	private String userinfoName;
	private String userinfoAvatar;
	private String userinfoSign;
	private Integer studentAttendanceId;
	private Integer studentIsLate;
	private String parentName;
	private String parentAvatar;
	private String branchschoolName;
	private String branchschoolMinName;
	private String branchschoolPictureUrl;
	private Integer isHeadTeacher;
	private Integer userRoot;
	private Integer isValid;
	private String time;
	private String alterTime;
	
	public String getParentAvatar() {
		return parentAvatar;
	}

	public void setParentAvatar(String parentAvatar) {
		this.parentAvatar = parentAvatar;
	}

	public String getUserName() {
		return userName;
	}

	public String getStudentUserName() {
		return studentUserName;
	}

	public void setStudentUserName(String studentUserName) {
		this.studentUserName = studentUserName;
	}

	public String getParentUserName() {
		return parentUserName;
	}

	public void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public TbUserinfoclassWeb() {
		super();
	}
	
	public Integer getStudentAttendanceId() {
		return studentAttendanceId;
	}

	public void setStudentAttendanceId(Integer studentAttendanceId) {
		this.studentAttendanceId = studentAttendanceId;
	}

	public Integer getIsHeadTeacher() {
		return isHeadTeacher;
	}

	public void setIsHeadTeacher(Integer isHeadTeacher) {
		this.isHeadTeacher = isHeadTeacher;
	}

	public TbUserinfoclassWeb(Integer useclaId, String courseName,
			String schoolName, String userinfoName, String branchschoolName,
			Integer userRoot,Integer isHeadTeacher, Integer isValid, String time,
			String alterTime) {
		super();
		this.useclaId = useclaId;
		this.courseName = courseName;
		this.schoolName = schoolName;
		this.userinfoName = userinfoName;
		this.branchschoolName = branchschoolName;
		this.userRoot = userRoot;
		this.isValid = isValid;
		this.isHeadTeacher = isHeadTeacher;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbUserinfoclassWeb(Integer useclaId, Integer courseId,
			Integer schoolId, Integer userinfoId, Integer branchschoolId,
			String courseName, String schoolName, String schoolMinName,
			String userinfoName, String branchschoolName,
			String branchschoolMinName, Integer userRoot, Integer isValid,
			String time, String alterTime) {
		super();
		this.useclaId = useclaId;
		this.courseId = courseId;
		this.schoolId = schoolId;
		this.userinfoId = userinfoId;
		this.branchschoolId = branchschoolId;
		this.courseName = courseName;
		this.schoolName = schoolName;
		this.schoolMinName = schoolMinName;
		this.userinfoName = userinfoName;
		this.branchschoolName = branchschoolName;
		this.branchschoolMinName = branchschoolMinName;
		this.userRoot = userRoot;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public Integer getStudentIsLate() {
		return studentIsLate;
	}

	public void setStudentIsLate(Integer studentIsLate) {
		this.studentIsLate = studentIsLate;
	}
	
	public TbUserinfoclassWeb(Integer userinfoId, String userinfoName,
			Integer studentAttendanceId, Integer studentIsLate) {
		super();
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.studentAttendanceId = studentAttendanceId;
		this.studentIsLate = studentIsLate;
	}

	public TbUserinfoclassWeb(Integer courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public TbUserinfoclassWeb(Integer branchschoolId, String branchschoolName,
			String branchschoolMinName, String branchschoolPictureUrl) {
		super();
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.branchschoolMinName = branchschoolMinName;
		this.branchschoolPictureUrl = branchschoolPictureUrl;
	}

	public TbUserinfoclassWeb(Integer userinfoId, String userinfoName,
			String userinfoAvatar, String studentUserName, String parentUserName, String userinfoSign, String parentName,String parentAvatar) {
		super();
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.userinfoAvatar = userinfoAvatar;
		this.parentAvatar = parentAvatar;
		this.studentUserName = studentUserName;
		this.parentUserName = parentUserName;
		this.userinfoSign = userinfoSign;
		this.parentName = parentName;
	}
	
	public TbUserinfoclassWeb(Integer userinfoId, String userinfoName,
			Integer userRoot) {
		super();
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.userRoot = userRoot;
	}

	public String getUserinfoSign() {
		return userinfoSign;
	}

	public void setUserinfoSign(String userinfoSign) {
		this.userinfoSign = userinfoSign;
	}

	public String getUserinfoAvatar() {
		return userinfoAvatar;
	}

	public void setUserinfoAvatar(String userinfoAvatar) {
		this.userinfoAvatar = userinfoAvatar;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getBranchschoolPictureUrl() {
		return branchschoolPictureUrl;
	}

	public void setBranchschoolPictureUrl(String branchschoolPictureUrl) {
		this.branchschoolPictureUrl = branchschoolPictureUrl;
	}

	public Integer getUseclaId() {
		return useclaId;
	}

	public void setUseclaId(Integer useclaId) {
		this.useclaId = useclaId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getUserinfoId() {
		return userinfoId;
	}

	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}

	public Integer getBranchschoolId() {
		return branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolMinName() {
		return schoolMinName;
	}

	public void setSchoolMinName(String schoolMinName) {
		this.schoolMinName = schoolMinName;
	}

	public String getUserinfoName() {
		return userinfoName;
	}

	public void setUserinfoName(String userinfoName) {
		this.userinfoName = userinfoName;
	}

	public String getBranchschoolName() {
		return branchschoolName;
	}

	public void setBranchschoolName(String branchschoolName) {
		this.branchschoolName = branchschoolName;
	}

	public String getBranchschoolMinName() {
		return branchschoolMinName;
	}

	public void setBranchschoolMinName(String branchschoolMinName) {
		this.branchschoolMinName = branchschoolMinName;
	}

	public Integer getUserRoot() {
		return userRoot;
	}

	public void setUserRoot(Integer userRoot) {
		this.userRoot = userRoot;
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
