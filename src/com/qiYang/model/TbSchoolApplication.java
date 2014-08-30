package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbSchoolApplication entity. @author MyEclipse Persistence Tools
 */

public class TbSchoolApplication implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cityName;
	private String schoolName;
	private String address;
	private String phone;
	private String name;
	private String userName;
	private String password;
	private String email;
	private String pusher;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbSchoolApplication() {
	}

	/** full constructor */
	public TbSchoolApplication(String cityName,String schoolName, String address, String phone,
			String name, String userName, String password, String email,
			String pusher, Integer isValid, Timestamp time, Timestamp alterTime) {
		this.cityName=cityName;
		this.schoolName = schoolName;
		this.address = address;
		this.phone = phone;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.pusher = pusher;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPusher() {
		return this.pusher;
	}

	public void setPusher(String pusher) {
		this.pusher = pusher;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getAlterTime() {
		return this.alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

}