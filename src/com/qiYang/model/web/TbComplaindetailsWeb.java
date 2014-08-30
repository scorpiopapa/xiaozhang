package com.qiYang.model.web;

public class TbComplaindetailsWeb {
	private Integer comdetId;
	private Integer parentId;
	private String parentName;
	private Integer teacherId;
	private String teacherName;
	private String comdetContent;
	private Integer isSatisfie;
	private Integer isValid;
	private String time;
	private String alterTime;
	
	

	public Integer getIsSatisfie() {
		return isSatisfie;
	}

	public void setIsSatisfie(Integer isSatisfie) {
		this.isSatisfie = isSatisfie;
	}

	public TbComplaindetailsWeb(Integer comdetId, Integer parentId,
			String parentName, Integer teacherId, String teacherName,
			String comdetContent, Integer isSatisfie, Integer isValid,
			String time, String alterTime) {
		super();
		this.comdetId = comdetId;
		this.parentId = parentId;
		this.parentName = parentName;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.comdetContent = comdetContent;
		this.isSatisfie = isSatisfie;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbComplaindetailsWeb() {
		super();
	}

	public Integer getComdetId() {
		return comdetId;
	}

	public void setComdetId(Integer comdetId) {
		this.comdetId = comdetId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getComdetContent() {
		return comdetContent;
	}

	public void setComdetContent(String comdetContent) {
		this.comdetContent = comdetContent;
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
