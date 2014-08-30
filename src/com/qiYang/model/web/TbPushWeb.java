package com.qiYang.model.web;

public class TbPushWeb {
	private Integer puchId;
	private Integer schoolId;
	private String schoolName;
	private Integer userinfoId;
	private String userinfoName;
	private Integer branchschoolId;
	private String branchschoolName;
	private String pushType;
	private String pushToken;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbPushWeb(Integer puchId, Integer schoolId, String schoolName,
			Integer userinfoId, String userinfoName, Integer branchschoolId,
			String branchschoolName, String pushType, String pushToken,
			Integer isValid, String time, String alterTime) {
		super();
		this.puchId = puchId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.pushType = pushType;
		this.pushToken = pushToken;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbPushWeb() {
		super();
	}

	public Integer getPuchId() {
		return puchId;
	}

	public void setPuchId(Integer puchId) {
		this.puchId = puchId;
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

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
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
