package com.qiYang.model.web;

public class TbTestfinishWeb {
	private Integer testfinishId;
	private Integer userinfoId;
	private String userinfoName;
	private Integer testId;
	private String testName;
	private String testfinishDate;
	private String rightPercentage;
	private Integer isfinish;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbTestfinishWeb(Integer testfinishId, Integer userinfoId,
			String userinfoName, Integer testId, String testName,
			String testfinishDate, String rightPercentage, Integer isfinish,
			Integer isValid, String time, String alterTime) {
		super();
		this.testfinishId = testfinishId;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.testId = testId;
		this.testName = testName;
		this.testfinishDate = testfinishDate;
		this.rightPercentage = rightPercentage;
		this.isfinish = isfinish;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbTestfinishWeb() {
		super();
	}

	public Integer getTestfinishId() {
		return testfinishId;
	}

	public void setTestfinishId(Integer testfinishId) {
		this.testfinishId = testfinishId;
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

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestfinishDate() {
		return testfinishDate;
	}

	public void setTestfinishDate(String testfinishDate) {
		this.testfinishDate = testfinishDate;
	}

	public String getRightPercentage() {
		return rightPercentage;
	}

	public void setRightPercentage(String rightPercentage) {
		this.rightPercentage = rightPercentage;
	}

	public Integer getIsfinish() {
		return isfinish;
	}

	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
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
