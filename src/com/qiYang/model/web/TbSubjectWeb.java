package com.qiYang.model.web;

public class TbSubjectWeb {
	private Integer subjectId;
	private String subjectName;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbSubjectWeb(Integer subjectId, String subjectName, Integer isValid,
			String time, String alterTime) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbSubjectWeb() {
		super();
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
