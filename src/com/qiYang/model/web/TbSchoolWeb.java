package com.qiYang.model.web;

public class TbSchoolWeb {
	private Integer schoolId;
	private Integer townId;
	private String townName;
	private String schoolName;
	private String schoolMinName;
	private String schoolUrl;
	private String schoolAddress;
	private String schoolPhone;
	private String schoolCourse;
	private Integer studentNum;
	private String linemanPhone;
	private String linemanEmail;
	private String linemanQq;
	private String linemanName;
	private String rectorEmail;
	private String rectorPhone;
	private String rectorQq;
	private String rectorName;
	private Integer subSchoolNum;
	private String schoolCertificate;
	private String schoolUnit;
	private String schoolLogo;
	private String schoolBusWay;
	private String schoolStopLocation;
	private String schoolPicture;
	private String schoolIntroduce;
	private String schoolApplyDate;
	private String schoolLatitude;
	private String schoolLongitude;
	private Integer isValid;
	private String time;
	private String alterTime;
	private String cityName;
	
	public TbSchoolWeb(Integer schoolId, Integer townId, String townName,
			String schoolName, String schoolMinName, String schoolUrl,
			String schoolAddress, String schoolPhone, String schoolCourse,
			Integer studentNum, String linemanPhone, String linemanEmail,
			String linemanQq, String linemanName, String rectorEmail,
			String rectorPhone, String rectorQq, String rectorName,
			Integer subSchoolNum, String schoolCertificate, String schoolUnit,
			String schoolLogo, String schoolBusWay, String schoolStopLocation,
			String schoolPicture, String schoolIntroduce,
			String schoolApplyDate, String schoolLatitude,
			String schoolLongitude, Integer isValid, String time,
			String alterTime) {
		super();
		this.schoolId = schoolId;
		this.townId = townId;
		this.townName = townName;
		this.schoolName = schoolName;
		this.schoolMinName = schoolMinName;
		this.schoolUrl = schoolUrl;
		this.schoolAddress = schoolAddress;
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
		this.schoolLatitude = schoolLatitude;
		this.schoolLongitude = schoolLongitude;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	
	public TbSchoolWeb(Integer schoolId, String schoolName,
			String schoolMinName, String schoolAddress, String schoolPhone,
			String schoolBusWay, String schoolStopLocation,
			String schoolPicture, String schoolIntroduce) {
		super();
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.schoolMinName = schoolMinName;
		this.schoolAddress = schoolAddress;
		this.schoolPhone = schoolPhone;
		this.schoolBusWay = schoolBusWay;
		this.schoolStopLocation = schoolStopLocation;
		this.schoolPicture = schoolPicture;
		this.schoolIntroduce = schoolIntroduce;
	}

	public TbSchoolWeb(Integer schoolId, String schoolName) {
		super();
		this.schoolId = schoolId;
		this.schoolName = schoolName;
	}
	
	public TbSchoolWeb(Integer schoolId, String schoolName,
			String schoolMinName,  String schoolLongitude,String schoolLatitude) {
		super();
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.schoolMinName = schoolMinName;
		this.schoolLatitude = schoolLatitude;
		this.schoolLongitude = schoolLongitude;
	}
	
	public TbSchoolWeb() {
		super();
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolMinName() {
		return schoolMinName;
	}

	public void setSchoolMinName(String schoolMinName) {
		this.schoolMinName = schoolMinName;
	}

	public String getSchoolUrl() {
		return schoolUrl;
	}

	public void setSchoolUrl(String schoolUrl) {
		this.schoolUrl = schoolUrl;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getSchoolPhone() {
		return schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public String getSchoolCourse() {
		return schoolCourse;
	}

	public void setSchoolCourse(String schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public String getLinemanPhone() {
		return linemanPhone;
	}

	public void setLinemanPhone(String linemanPhone) {
		this.linemanPhone = linemanPhone;
	}

	public String getLinemanEmail() {
		return linemanEmail;
	}

	public void setLinemanEmail(String linemanEmail) {
		this.linemanEmail = linemanEmail;
	}

	public String getLinemanQq() {
		return linemanQq;
	}

	public void setLinemanQq(String linemanQq) {
		this.linemanQq = linemanQq;
	}

	public String getLinemanName() {
		return linemanName;
	}

	public void setLinemanName(String linemanName) {
		this.linemanName = linemanName;
	}

	public String getRectorEmail() {
		return rectorEmail;
	}

	public void setRectorEmail(String rectorEmail) {
		this.rectorEmail = rectorEmail;
	}

	public String getRectorPhone() {
		return rectorPhone;
	}

	public void setRectorPhone(String rectorPhone) {
		this.rectorPhone = rectorPhone;
	}

	public String getRectorQq() {
		return rectorQq;
	}

	public void setRectorQq(String rectorQq) {
		this.rectorQq = rectorQq;
	}

	public String getRectorName() {
		return rectorName;
	}

	public void setRectorName(String rectorName) {
		this.rectorName = rectorName;
	}

	public Integer getSubSchoolNum() {
		return subSchoolNum;
	}

	public void setSubSchoolNum(Integer subSchoolNum) {
		this.subSchoolNum = subSchoolNum;
	}

	public String getSchoolCertificate() {
		return schoolCertificate;
	}

	public void setSchoolCertificate(String schoolCertificate) {
		this.schoolCertificate = schoolCertificate;
	}

	public String getSchoolUnit() {
		return schoolUnit;
	}

	public void setSchoolUnit(String schoolUnit) {
		this.schoolUnit = schoolUnit;
	}

	public String getSchoolLogo() {
		return schoolLogo;
	}

	public void setSchoolLogo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}

	public String getSchoolBusWay() {
		return schoolBusWay;
	}

	public void setSchoolBusWay(String schoolBusWay) {
		this.schoolBusWay = schoolBusWay;
	}

	public String getSchoolStopLocation() {
		return schoolStopLocation;
	}

	public void setSchoolStopLocation(String schoolStopLocation) {
		this.schoolStopLocation = schoolStopLocation;
	}

	public String getSchoolPicture() {
		return schoolPicture;
	}

	public void setSchoolPicture(String schoolPicture) {
		this.schoolPicture = schoolPicture;
	}

	public String getSchoolIntroduce() {
		return schoolIntroduce;
	}

	public void setSchoolIntroduce(String schoolIntroduce) {
		this.schoolIntroduce = schoolIntroduce;
	}

	public String getSchoolApplyDate() {
		return schoolApplyDate;
	}

	public void setSchoolApplyDate(String schoolApplyDate) {
		this.schoolApplyDate = schoolApplyDate;
	}

	public String getSchoolLatitude() {
		return schoolLatitude;
	}

	public void setSchoolLatitude(String schoolLatitude) {
		this.schoolLatitude = schoolLatitude;
	}

	public String getSchoolLongitude() {
		return schoolLongitude;
	}

	public void setSchoolLongitude(String schoolLongitude) {
		this.schoolLongitude = schoolLongitude;
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
