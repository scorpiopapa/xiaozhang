package com.qiYang.model.web;

public class TbTownWeb {
	private Integer townId;
	private Integer cityId;
	private String cityName;
	private String townName;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbTownWeb(Integer townId, Integer cityId, String cityName,
			String townName, Integer isValid, String time, String alterTime) {
		super();
		this.townId = townId;
		this.cityId = cityId;
		this.cityName = cityName;
		this.townName = townName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbTownWeb() {
		super();
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
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
