package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbGrade entity. @author MyEclipse Persistence Tools
 */

public class TbGrade implements java.io.Serializable {

	// Fields

	private Integer gradeId;
	private TbSchool tbSchool;
	private TbBranchschool tbBranchschool;
	private String gradeName;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbCurriculums = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbGrade() {
	}

	/** full constructor */
	public TbGrade(TbSchool tbSchool, TbBranchschool tbBranchschool,
			String gradeName, Integer isValid, Timestamp time,
			Timestamp alterTime, Set tbCurriculums) {
		this.tbSchool = tbSchool;
		this.tbBranchschool = tbBranchschool;
		this.gradeName = gradeName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbCurriculums = tbCurriculums;
	}

	// Property accessors

	public Integer getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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