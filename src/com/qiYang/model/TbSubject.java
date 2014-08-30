package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbSubject entity. @author MyEclipse Persistence Tools
 */

public class TbSubject implements java.io.Serializable {

	// Fields

	private Integer subjectId;
	private String subjectName;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbCurriculums = new HashSet(0);
	private Set tbSubjectinbranchschools = new HashSet(0);
	private Set tbLessons = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbSubject() {
	}

	/** full constructor */
	public TbSubject(String subjectName, Integer isValid, Timestamp time,
			Timestamp alterTime, Set tbCurriculums,
			Set tbSubjectinbranchschools, Set tbLessons) {
		this.subjectName = subjectName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbCurriculums = tbCurriculums;
		this.tbSubjectinbranchschools = tbSubjectinbranchschools;
		this.tbLessons = tbLessons;
	}
	
	// Property accessors

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public Set getTbCurriculums() {
		return this.tbCurriculums;
	}

	public void setTbCurriculums(Set tbCurriculums) {
		this.tbCurriculums = tbCurriculums;
	}

	public Set getTbSubjectinbranchschools() {
		return this.tbSubjectinbranchschools;
	}

	public void setTbSubjectinbranchschools(Set tbSubjectinbranchschools) {
		this.tbSubjectinbranchschools = tbSubjectinbranchschools;
	}

	public Set getTbLessons() {
		return this.tbLessons;
	}

	public void setTbLessons(Set tbLessons) {
		this.tbLessons = tbLessons;
	}

}