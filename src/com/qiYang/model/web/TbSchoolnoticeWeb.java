package com.qiYang.model.web;

public class TbSchoolnoticeWeb {
	private Integer schoolNoticeId;
	private Integer schoolId;
	private String schoolName;
	private Integer userinfoId;
	private String userinfoName;
	private Integer branchschoolId;
	private String branchschoolName;
	private String schoolnoticeTitle;
	private String schoolnoticeContent;
	private String schoolnoticeAddTime;
	private Integer isAllSchool;
	private Integer isValid;
	private String time;
	private String alterTime;
	private String schnotAddTime;


	public TbSchoolnoticeWeb(Integer schoolNoticeId, Integer schoolId,
			String schoolName, Integer userinfoId, String userinfoName,
			Integer branchschoolId, String branchschoolName,
			String schoolnoticeTitle, String schoolnoticeContent,
			String schoolnoticeAddTime, Integer isAllSchool, Integer isValid,
			String time, String alterTime) {
		super();
		this.schoolNoticeId = schoolNoticeId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.schoolnoticeTitle = schoolnoticeTitle;
		this.schoolnoticeContent = schoolnoticeContent;
		this.schoolnoticeAddTime = schoolnoticeAddTime;
		this.isAllSchool = isAllSchool;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbSchoolnoticeWeb() {
		super();
	}
	
	public String getSchnotAddTime() {
		return schnotAddTime;
	}

	public void setSchnotAddTime(String schnotAddTime) {
		this.schnotAddTime = schnotAddTime;
	}

	public Integer getSchoolNoticeId() {
		return schoolNoticeId;
	}

	public void setSchoolNoticeId(Integer schoolNoticeId) {
		this.schoolNoticeId = schoolNoticeId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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


	public Integer getIsAllSchool() {
		return isAllSchool;
	}

	public void setIsAllSchool(Integer isAllSchool) {
		this.isAllSchool = isAllSchool;
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

	public String getSchoolnoticeTitle() {
		return schoolnoticeTitle;
	}

	public void setSchoolnoticeTitle(String schoolnoticeTitle) {
		this.schoolnoticeTitle = schoolnoticeTitle;
	}

	public String getSchoolnoticeContent() {
		return schoolnoticeContent;
	}

	public void setSchoolnoticeContent(String schoolnoticeContent) {
		this.schoolnoticeContent = schoolnoticeContent;
	}

	public String getSchoolnoticeAddTime() {
		return schoolnoticeAddTime;
	}

	public void setSchoolnoticeAddTime(String schoolnoticeAddTime) {
		this.schoolnoticeAddTime = schoolnoticeAddTime;
	}

}
