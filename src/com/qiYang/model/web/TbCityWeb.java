package com.qiYang.model.web;

public class TbCityWeb {
	private Integer cityId;
	private String cityName;
	private Integer isValid;
	private String time;
	private String alterTime;
	private String pinyin;

	public TbCityWeb(Integer cityId, String cityName, Integer isValid,
			String time, String alterTime, String pinyin) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.pinyin = pinyin;
	}

	public TbCityWeb() {
		super();
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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}
