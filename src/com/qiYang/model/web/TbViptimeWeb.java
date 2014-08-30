package com.qiYang.model.web;


public class TbViptimeWeb {
	private Integer id;
	private Integer userinfoId;
	private String  userinfoName;
	private Integer branchschoolId;
	private String branchschoolName;
	private String startTime;
	private String endTime;
	private Integer isValid;
	private String time;
	private String alterTime;
	public TbViptimeWeb(Integer id, Integer userinfoId, String userinfoName,
			Integer branchschoolId, String branchschoolName, String startTime,
			String endTime, Integer isValid, String time, String alterTime) {
		super();
		this.id = id;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.startTime = startTime;
		this.endTime = endTime;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
