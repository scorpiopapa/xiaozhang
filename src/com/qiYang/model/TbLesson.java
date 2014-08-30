package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbLesson entity. @author MyEclipse Persistence Tools
 */

public class TbLesson implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbSchool tbSchool;
	private TbSubject tbSubject;
	private TbBranchschool tbBranchschool;
	private Long defineSort2;
	private Long defineSort;
	private String lessonName;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbCurriculums = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbLesson() {
	}

	/** full constructor */
	public TbLesson(TbSchool tbSchool, TbSubject tbSubject,
			TbBranchschool tbBranchschool, Long defineSort2, Long defineSort,
			String lessonName, Integer isValid, Timestamp time,
			Timestamp alterTime, Set tbCurriculums) {
		this.tbSchool = tbSchool;
		this.tbSubject = tbSubject;
		this.tbBranchschool = tbBranchschool;
		this.defineSort2 = defineSort2;
		this.defineSort = defineSort;
		this.lessonName = lessonName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbCurriculums = tbCurriculums;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public TbLesson(Long defineSort2, Long defineSort) {
		super();
		this.defineSort2 = defineSort2;
		this.defineSort = defineSort;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public TbSubject getTbSubject() {
		return this.tbSubject;
	}

	public void setTbSubject(TbSubject tbSubject) {
		this.tbSubject = tbSubject;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public Long getDefineSort2() {
		return this.defineSort2;
	}

	public void setDefineSort2(Long defineSort2) {
		this.defineSort2 = defineSort2;
	}

	public Long getDefineSort() {
		return this.defineSort;
	}

	public void setDefineSort(Long defineSort) {
		this.defineSort = defineSort;
	}

	public String getLessonName() {
		return this.lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
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

}