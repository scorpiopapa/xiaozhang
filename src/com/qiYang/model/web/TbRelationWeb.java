package com.qiYang.model.web;

public class TbRelationWeb {
	private Integer relationId;
	private Integer studentId;
	private String studentName;
	private Integer parentId;
	private String parentName;
	private Integer isValid;
	private String childLongitude;
	private String childLatitude;
	private String time;
	private String alterTime;

	public TbRelationWeb() {
		super();
	}

	public TbRelationWeb(Integer relationId, Integer studentId,
			String studentName, Integer parentId, String parentName,
			Integer isValid, String childLongitude, String childLatitude,
			String time, String alterTime) {
		super();
		this.relationId = relationId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.parentId = parentId;
		this.parentName = parentName;
		this.isValid = isValid;
		this.childLongitude = childLongitude;
		this.childLatitude = childLatitude;
		this.time = time;
		this.alterTime = alterTime;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getChildLongitude() {
		return childLongitude;
	}

	public void setChildLongitude(String childLongitude) {
		this.childLongitude = childLongitude;
	}

	public String getChildLatitude() {
		return childLatitude;
	}

	public void setChildLatitude(String childLatitude) {
		this.childLatitude = childLatitude;
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
