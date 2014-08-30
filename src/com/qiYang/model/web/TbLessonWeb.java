package com.qiYang.model.web;

import java.sql.Timestamp;

/**
 * TbLesson entity. @author MyEclipse Persistence Tools
 */

public class TbLessonWeb implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer subjectId;
	private String subjectName;
	private Integer branchschoolId;
	private String branchName;
	private String schoolName;
	private Long defineSort2;
	private Long defineSort;
	private String lessonName;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbLessonWeb() {
	}

	/** full constructor */
	public TbLessonWeb(String subjectName, String branchName,
			Long defineSort2, Long defineSort, String lessonName,
			Integer isValid, Timestamp time, Timestamp alterTime) {
		this.subjectName = subjectName;
		this.branchName = branchName;
		this.defineSort2 = defineSort2;
		this.defineSort = defineSort;
		this.lessonName = lessonName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getBranchschoolId() {
		return branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Long getDefineSort2() {
		return defineSort2;
	}

	public void setDefineSort2(Long defineSort2) {
		this.defineSort2 = defineSort2;
	}

	public Long getDefineSort() {
		return defineSort;
	}

	public void setDefineSort(Long defineSort) {
		this.defineSort = defineSort;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

}