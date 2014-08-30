package com.qiYang.model.web;

public class TbUserWeb {
	private Integer userId;
	private Integer userinfoId;
	private String userinfoName;
	private String userName;
	private String userPassword;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbUserWeb(Integer userId, Integer userinfoId, String userinfoName,
			String userName, String userPassword, Integer isValid, String time,
			String alterTime) {
		super();
		this.userId = userId;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.userName = userName;
		this.userPassword = userPassword;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbUserWeb() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
