package com.qiYang.model;

import java.sql.Timestamp;



public class TbRegschool implements java.io.Serializable {

	private Integer schoolId;
	private TbTown tbTown;
	private String schoolName;
	private String schoolMinName;
	private String schoolUrl;
	private String schoolAddress;
	private Double longitude;
	private Double latitude;
	private String schoolPhone;
	private String schoolCourse;
	private String studentNum;
	private String linemanPhone;
	private String linemanEmail;
	private String linemanQq;
	private String linemanName;
	private String rectorEmail;
	private String rectorPhone;
	private String rectorQq;
	private String rectorName;
	private String subSchoolNum;
	private String schoolCertificate;
	private String schoolUnit;
	private String schoolLogo;
	private String schoolBusWay;
	private String schoolStopLocation;
	private String schoolPicture;
	private String schoolIntroduce;
	private String schoolApplyDate;
	private Integer isValid;
	private Timestamp time;
	private Integer status;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbRegschool() {
	}

	/** full constructor */
	public TbRegschool(TbTown tbTown, String schoolName, String schoolMinName,
			String schoolUrl, String schoolAddress, Double longitude,
			Double latitude, String schoolPhone, String schoolCourse,
			String studentNum, String linemanPhone, String linemanEmail,
			String linemanQq, String linemanName, String rectorEmail,
			String rectorPhone, String rectorQq, String rectorName,
			String subSchoolNum, String schoolCertificate, String schoolUnit,
			String schoolLogo, String schoolBusWay, String schoolStopLocation,
			String schoolPicture, String schoolIntroduce,
			String schoolApplyDate, Integer isValid, Timestamp time,
			Integer status, Timestamp alterTime) {
		this.tbTown = tbTown;
		this.schoolName = schoolName;
		this.schoolMinName = schoolMinName;
		this.schoolUrl = schoolUrl;
		this.schoolAddress = schoolAddress;
		this.longitude = longitude;
		this.latitude = latitude;
		this.schoolPhone = schoolPhone;
		this.schoolCourse = schoolCourse;
		this.studentNum = studentNum;
		this.linemanPhone = linemanPhone;
		this.linemanEmail = linemanEmail;
		this.linemanQq = linemanQq;
		this.linemanName = linemanName;
		this.rectorEmail = rectorEmail;
		this.rectorPhone = rectorPhone;
		this.rectorQq = rectorQq;
		this.rectorName = rectorName;
		this.subSchoolNum = subSchoolNum;
		this.schoolCertificate = schoolCertificate;
		this.schoolUnit = schoolUnit;
		this.schoolLogo = schoolLogo;
		this.schoolBusWay = schoolBusWay;
		this.schoolStopLocation = schoolStopLocation;
		this.schoolPicture = schoolPicture;
		this.schoolIntroduce = schoolIntroduce;
		this.schoolApplyDate = schoolApplyDate;
		this.isValid = isValid;
		this.time = time;
		this.status = status;
		this.alterTime = alterTime;
	}


	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public TbTown getTbTown() {
		return this.tbTown;
	}

	public void setTbTown(TbTown tbTown) {
		this.tbTown = tbTown;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolMinName() {
		return this.schoolMinName;
	}

	public void setSchoolMinName(String schoolMinName) {
		this.schoolMinName = schoolMinName;
	}

	public String getSchoolUrl() {
		return this.schoolUrl;
	}

	public void setSchoolUrl(String schoolUrl) {
		this.schoolUrl = schoolUrl;
	}

	public String getSchoolAddress() {
		return this.schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getSchoolPhone() {
		return this.schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public String getSchoolCourse() {
		return this.schoolCourse;
	}

	public void setSchoolCourse(String schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	public String getStudentNum() {
		return this.studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getLinemanPhone() {
		return this.linemanPhone;
	}

	public void setLinemanPhone(String linemanPhone) {
		this.linemanPhone = linemanPhone;
	}

	public String getLinemanEmail() {
		return this.linemanEmail;
	}

	public void setLinemanEmail(String linemanEmail) {
		this.linemanEmail = linemanEmail;
	}

	public String getLinemanQq() {
		return this.linemanQq;
	}

	public void setLinemanQq(String linemanQq) {
		this.linemanQq = linemanQq;
	}

	public String getLinemanName() {
		return this.linemanName;
	}

	public void setLinemanName(String linemanName) {
		this.linemanName = linemanName;
	}

	public String getRectorEmail() {
		return this.rectorEmail;
	}

	public void setRectorEmail(String rectorEmail) {
		this.rectorEmail = rectorEmail;
	}

	public String getRectorPhone() {
		return this.rectorPhone;
	}

	public void setRectorPhone(String rectorPhone) {
		this.rectorPhone = rectorPhone;
	}

	public String getRectorQq() {
		return this.rectorQq;
	}

	public void setRectorQq(String rectorQq) {
		this.rectorQq = rectorQq;
	}

	public String getRectorName() {
		return this.rectorName;
	}

	public void setRectorName(String rectorName) {
		this.rectorName = rectorName;
	}

	public String getSubSchoolNum() {
		return this.subSchoolNum;
	}

	public void setSubSchoolNum(String subSchoolNum) {
		this.subSchoolNum = subSchoolNum;
	}

	public String getSchoolCertificate() {
		return this.schoolCertificate;
	}

	public void setSchoolCertificate(String schoolCertificate) {
		this.schoolCertificate = schoolCertificate;
	}

	public String getSchoolUnit() {
		return this.schoolUnit;
	}

	public void setSchoolUnit(String schoolUnit) {
		this.schoolUnit = schoolUnit;
	}

	public String getSchoolLogo() {
		return this.schoolLogo;
	}

	public void setSchoolLogo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}

	public String getSchoolBusWay() {
		return this.schoolBusWay;
	}

	public void setSchoolBusWay(String schoolBusWay) {
		this.schoolBusWay = schoolBusWay;
	}

	public String getSchoolStopLocation() {
		return this.schoolStopLocation;
	}

	public void setSchoolStopLocation(String schoolStopLocation) {
		this.schoolStopLocation = schoolStopLocation;
	}

	public String getSchoolPicture() {
		return this.schoolPicture;
	}

	public void setSchoolPicture(String schoolPicture) {
		this.schoolPicture = schoolPicture;
	}

	public String getSchoolIntroduce() {
		return this.schoolIntroduce;
	}

	public void setSchoolIntroduce(String schoolIntroduce) {
		this.schoolIntroduce = schoolIntroduce;
	}

	public String getSchoolApplyDate() {
		return this.schoolApplyDate;
	}

	public void setSchoolApplyDate(String schoolApplyDate) {
		this.schoolApplyDate = schoolApplyDate;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getAlterTime() {
		return this.alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

}