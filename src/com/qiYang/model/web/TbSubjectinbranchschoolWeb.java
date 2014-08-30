package com.qiYang.model.web;

public class TbSubjectinbranchschoolWeb {
	private Integer id;
	private Integer subjectId;
	private String subjectName;
	private Integer branchschoolId;
	private String branchschoolName;
	private Integer isValid;
	private String time;
	private String alterTime;
	public TbSubjectinbranchschoolWeb() {
		super();
	}
	public TbSubjectinbranchschoolWeb(Integer id, Integer subjectId,
			String subjectName, Integer branchschoolId,
			String branchschoolName, Integer isValid, String time,
			String alterTime) {
		super();
		this.id = id;
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
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
	public String getBranchschoolName() {
		return branchschoolName;
	}
	public void setBranchschoolName(String branchschoolName) {
		this.branchschoolName = branchschoolName;
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
