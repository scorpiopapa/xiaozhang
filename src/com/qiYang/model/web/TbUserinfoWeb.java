package com.qiYang.model.web;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbUserinfo;
import com.qiYang.service.GogoServiceImpl;

public class TbUserinfoWeb {
	private Integer userinfoId;
	private Integer courseId;
	private String courseName;
	private Integer schoolId;
	private String schoolName;
	private Integer branchschoolId;
	private String branchschoolName;
	private String studentClass;
	private String studentSchool;
	private String userinfoName;
	private String userinfoSex;
	private String userinfoQQ;
	private String userinfoBirthday;
	private String userinfoPhone;
	private String userinfoAvatar;
	private String userinfoSign;
	private String userinfoVip;
	private String graduateSchool;
	private String userinfoEmail;
	private String userName;
	private Integer workYear;
	private String parentName;
	private String userinfoCourse;
	private Integer userinfoRoot;
	private Integer isValid;
	private String time;
	private String alterTime;
	private String pushToken;
	private String pushType;
	private Double distance; //距离

	public TbUserinfoWeb(Integer userinfoId, String userinfoName,
			String userinfoAvatar, String userinfoSign, String userName,
			String parentName) {
		super();
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.userinfoAvatar = userinfoAvatar;
		this.userinfoSign = userinfoSign;
		this.userName = userName;
		this.parentName = parentName;
	}

	public TbUserinfoWeb(Integer userinfoId, Integer courseId,
			String courseName, Integer schoolId, String schoolName,
			Integer branchschoolId, String branchschoolName,
			String studentClass, String studentSchool, String userinfoName,
			String userinfoSex, String userinfoBirthday, String userinfoPhone,
			String userinfoAvatar, String userinfoSign, String userinfoVip,
			String graduateSchool, String userinfoEmail, String userName,
			Integer workYear, String userinfoCourse, Integer userinfoRoot,
			Integer isValid, String time, String alterTime) {
		super();
		this.userinfoId = userinfoId;
		this.courseId = courseId;
		this.courseName = courseName;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.studentClass = studentClass;
		this.studentSchool = studentSchool;
		this.userinfoName = userinfoName;
		this.userinfoSex = userinfoSex;
		this.userinfoBirthday = userinfoBirthday;
		this.userinfoPhone = userinfoPhone;
		this.userinfoAvatar = userinfoAvatar;
		this.userinfoSign = userinfoSign;
		this.userinfoVip = userinfoVip;
		this.graduateSchool = graduateSchool;
		this.userinfoEmail = userinfoEmail;
		this.userName = userName;
		this.workYear = workYear;
		this.userinfoCourse = userinfoCourse;
		this.userinfoRoot = userinfoRoot;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	
	public String getUserinfoQQ() {
		return userinfoQQ;
	}

	public void setUserinfoQQ(String userinfoQQ) {
		this.userinfoQQ = userinfoQQ;
	}

	public String getUserName() {
		return userName;
	}
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getUserinfoSex() {
		return userinfoSex;
	}

	public void setUserinfoSex(String userinfoSex) {
		this.userinfoSex = userinfoSex;
	}

	public String getUserinfoBirthday() {
		return userinfoBirthday;
	}

	public void setUserinfoBirthday(String userinfoBirthday) {
		this.userinfoBirthday = userinfoBirthday;
	}

	public String getUserinfoPhone() {
		return userinfoPhone;
	}

	public void setUserinfoPhone(String userinfoPhone) {
		this.userinfoPhone = userinfoPhone;
	}

	public String getUserinfoAvatar() {
		return userinfoAvatar;
	}

	public void setUserinfoAvatar(String userinfoAvatar) {
		this.userinfoAvatar = userinfoAvatar;
	}

	public String getUserinfoSign() {
		return userinfoSign;
	}

	public void setUserinfoSign(String userinfoSign) {
		this.userinfoSign = userinfoSign;
	}

	public String getUserinfoVip() {
		return userinfoVip;
	}

	public void setUserinfoVip(String userinfoVip) {
		this.userinfoVip = userinfoVip;
	}

	public String getUserinfoEmail() {
		return userinfoEmail;
	}

	public void setUserinfoEmail(String userinfoEmail) {
		this.userinfoEmail = userinfoEmail;
	}

	public String getUserinfoCourse() {
		return userinfoCourse;
	}

	public void setUserinfoCourse(String userinfoCourse) {
		this.userinfoCourse = userinfoCourse;
	}

	public Integer getUserinfoRoot() {
		return userinfoRoot;
	}

	public void setUserinfoRoot(Integer userinfoRoot) {
		this.userinfoRoot = userinfoRoot;
	}

/*	public TbUserinfoWeb(Integer userinfoId, String userinfoName,
			String userinfoAvatar, String userinfoSign) {
		super();
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.userinfoAvatar = userinfoAvatar;
		this.userinfoSign = userinfoSign;
	}*/
	

	
	
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public TbUserinfoWeb() {
		super();
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

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentSchool() {
		return studentSchool;
	}

	public void setStudentSchool(String studentSchool) {
		this.studentSchool = studentSchool;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public Integer getWorkYear() {
		return workYear;
	}

	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
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

	public TbUserinfo toModel(TbCourse tbCourse) {
		GogoServiceImpl gogoService=new GogoServiceImpl();
		TbBranchschool tbBranchschool = gogoService.getObjectByClazz(TbBranchschool.class, tbCourse.getTbBranchschool().getBranchSchoolId());
		Date birthday = null;
		try {
			birthday = DateUtils.parseDate(this.userinfoBirthday, "yyyy-MM-dd");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp nowTime=new Timestamp(System.currentTimeMillis());
		return new TbUserinfo(tbBranchschool.getTbSchool(), tbBranchschool,this.userinfoQQ, this.studentClass, this.studentSchool, this.userinfoName, this.userinfoSex, birthday, this.userinfoPhone, "default.png", "", null, null, this.userinfoEmail, null, null, this.userinfoRoot, 1, nowTime, nowTime);
	}

}
