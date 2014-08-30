package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.qiYang.model.web.TbCourseWeb;
import com.qiYang.util.TWObjectUtil;

/**
 * TbCourse entity. @author MyEclipse Persistence Tools
 */

public class TbCourse implements java.io.Serializable {

	// Fields

	private Integer courseId;
	private TbCurriculum tbCurriculum;
	private TbBranchschool tbBranchschool;
	private String classNumber;
	private String courseName;
	private String address;
	private String studyTime;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbUserinfoclasses = new HashSet(0);
	private Set tbComplains = new HashSet(0);
	private Set tbTests = new HashSet(0);
	private Set tbReviews = new HashSet(0);
	private Set tbAttendances = new HashSet(0);
	private Set tbClassnotices = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbCourse() {
	}

	/** full constructor */
	public TbCourse(TbCurriculum tbCurriculum, TbBranchschool tbBranchschool,
			String classNumber, String courseName, String address,
			String studyTime, Integer isValid, Timestamp time,
			Timestamp alterTime, Set tbUserinfoclasses, Set tbComplains,
			Set tbTests, Set tbReviews, Set tbAttendances, Set tbClassnotices) {
		this.tbCurriculum = tbCurriculum;
		this.tbBranchschool = tbBranchschool;
		this.classNumber = classNumber;
		this.courseName = courseName;
		this.address = address;
		this.studyTime = studyTime;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbUserinfoclasses = tbUserinfoclasses;
		this.tbComplains = tbComplains;
		this.tbTests = tbTests;
		this.tbReviews = tbReviews;
		this.tbAttendances = tbAttendances;
		this.tbClassnotices = tbClassnotices;
	}
	public TbCourseWeb toWeb() {
		return new TbCourseWeb(TWObjectUtil.getInteger(this.courseId), TWObjectUtil.getString(this.courseName));
	}
	// Property accessors

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public TbCurriculum getTbCurriculum() {
		return this.tbCurriculum;
	}

	public void setTbCurriculum(TbCurriculum tbCurriculum) {
		this.tbCurriculum = tbCurriculum;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public String getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStudyTime() {
		return this.studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
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

	public Set getTbUserinfoclasses() {
		return this.tbUserinfoclasses;
	}

	public void setTbUserinfoclasses(Set tbUserinfoclasses) {
		this.tbUserinfoclasses = tbUserinfoclasses;
	}

	public Set getTbComplains() {
		return this.tbComplains;
	}

	public void setTbComplains(Set tbComplains) {
		this.tbComplains = tbComplains;
	}

	public Set getTbTests() {
		return this.tbTests;
	}

	public void setTbTests(Set tbTests) {
		this.tbTests = tbTests;
	}

	public Set getTbReviews() {
		return this.tbReviews;
	}

	public void setTbReviews(Set tbReviews) {
		this.tbReviews = tbReviews;
	}

	public Set getTbAttendances() {
		return this.tbAttendances;
	}

	public void setTbAttendances(Set tbAttendances) {
		this.tbAttendances = tbAttendances;
	}

	public Set getTbClassnotices() {
		return this.tbClassnotices;
	}

	public void setTbClassnotices(Set tbClassnotices) {
		this.tbClassnotices = tbClassnotices;
	}

}