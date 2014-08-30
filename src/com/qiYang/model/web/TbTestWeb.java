package com.qiYang.model.web;

import java.sql.Timestamp;
import java.util.Date;

import com.qiYang.model.TbCourse;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbUserinfo;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

public class TbTestWeb implements TransitionModel{
	private Integer testId;
	private Integer courseId;
	private String courseName;
	private Integer userinfoId;
	private String userinfoName;
	private String testName;
	private String testDate;
	private String rightPercentage;
	private Integer isfinish;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbTestWeb(Integer testId, String testName, String testDate,
			String rightPercentage,Integer isfinish) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.testDate = testDate;
		this.rightPercentage = rightPercentage;
		this.isfinish = isfinish;
	}
	
	public Integer getIsfinish() {
		return isfinish;
	}

	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
	}

	public String getRightPercentage() {
		return rightPercentage;
	}
	public void setRightPercentage(String rightPercentage) {
		this.rightPercentage = rightPercentage;
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
	public TbTestWeb(Integer testId, Integer courseId, String courseName,
			String testName, String testDate, Integer isValid, String time,
			String alterTime) {
		super();
		this.testId = testId;
		this.courseId = courseId;
		this.courseName = courseName;
		this.testName = testName;
		this.testDate = testDate;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public <T> T toModel(Class<T> clazz) {
		TbCourse tbCourse = new GogoServiceImpl().getObjectByClazz(TbCourse.class, courseId);
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(this.userinfoId);
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		Boolean boo = TWObject.checkTest(tbCourse, testName);
		if(!boo)
			return null;
		return (T) new TbTest(tbCourse, userinfo,tbCourse.getTbBranchschool(), this.testName, TWDataUtil.dateDateyyyyMMdd(date), 1, timestamp, timestamp, null, null,null);
	}
	public TbTestWeb() {
		super();
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
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

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
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
