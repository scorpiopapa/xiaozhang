package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.qiYang.model.web.TbBranchschoolWeb;
import com.qiYang.model.web.TbSchoolWeb;
import com.qiYang.util.LalDistance;
import com.qiYang.util.TWObjectUtil;

/**
 * TbSchool entity. @author MyEclipse Persistence Tools
 */

public class TbSchool implements java.io.Serializable {

	// Fields

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
	private Integer isSchool;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbLessons = new HashSet(0);
	private Set tbUserinfoclasses = new HashSet(0);
	private Set tbAdmins = new HashSet(0);
	private Set tbComplains = new HashSet(0);
	private Set tbAttendances = new HashSet(0);
	private Set tbSchoolnotices = new HashSet(0);
	private Set tbPushs = new HashSet(0);
	private Set tbCurriculums = new HashSet(0);
	private Set tbUserinfos = new HashSet(0);
	private Set tbClassnotices = new HashSet(0);
	private Set tbGrades = new HashSet(0);
	private Set tbBranchschools = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbSchool() {
	}

	public TbSchool(TbTown tbTown, String schoolName, String schoolMinName,
			String schoolUrl, String schoolAddress, Double longitude,
			Double latitude, String schoolPhone, String schoolCourse,
			String studentNum, String linemanPhone, String linemanEmail,
			String linemanQq, String linemanName, String rectorEmail,
			String rectorPhone, String rectorQq, String rectorName,
			String subSchoolNum, String schoolCertificate, String schoolUnit,
			String schoolLogo, String schoolBusWay, String schoolStopLocation,
			String schoolPicture, String schoolIntroduce,
			String schoolApplyDate, Integer isValid, Timestamp time,
			Timestamp alterTime) {
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
		this.alterTime = alterTime;
	}

	/** full constructor */
	public TbSchool(TbTown tbTown, String schoolName, String schoolMinName,
			String schoolUrl, String schoolAddress, Double longitude,
			Double latitude, String schoolPhone, String schoolCourse,
			String studentNum, String linemanPhone, String linemanEmail,
			String linemanQq, String linemanName, String rectorEmail,
			String rectorPhone, String rectorQq, String rectorName,
			String subSchoolNum, String schoolCertificate, String schoolUnit,
			String schoolLogo, String schoolBusWay, String schoolStopLocation,
			String schoolPicture, String schoolIntroduce,
			String schoolApplyDate, Integer isValid, Timestamp time,
			Timestamp alterTime, Set tbLessons, Set tbUserinfoclasses,
			Set tbAdmins, Set tbComplains, Set tbAttendances,
			Set tbSchoolnotices, Set tbPushs, Set tbCurriculums,
			Set tbUserinfos, Set tbClassnotices, Set tbGrades,
			Set tbBranchschools) {
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
		this.alterTime = alterTime;
		this.tbLessons = tbLessons;
		this.tbUserinfoclasses = tbUserinfoclasses;
		this.tbAdmins = tbAdmins;
		this.tbComplains = tbComplains;
		this.tbAttendances = tbAttendances;
		this.tbSchoolnotices = tbSchoolnotices;
		this.tbPushs = tbPushs;
		this.tbCurriculums = tbCurriculums;
		this.tbUserinfos = tbUserinfos;
		this.tbClassnotices = tbClassnotices;
		this.tbGrades = tbGrades;
		this.tbBranchschools = tbBranchschools;
	}

	public TbSchoolWeb toPageWeb() {
		return new TbSchoolWeb(this.schoolId, this.schoolName);
	}

	public TbBranchschoolWeb toDetailJson() {
		TbBranchschoolWeb tbBranchschoolWeb = new TbBranchschoolWeb(
				this.schoolId, TWObjectUtil.getString(this.schoolName),
				TWObjectUtil.getString(this.schoolMinName),
				TWObjectUtil.getString(this.schoolAddress),
				TWObjectUtil.getString(this.schoolPhone),
				TWObjectUtil.getString(this.schoolBusWay),
				TWObjectUtil.getString(this.schoolStopLocation),
				TWObjectUtil.getNomalPicPath(this.schoolPicture),
				TWObjectUtil.getString(this.schoolIntroduce));
		tbBranchschoolWeb.setLatitude(this.latitude == null ? 0.0
				: this.latitude);
		tbBranchschoolWeb.setLongitude(this.longitude == null ? 0.0
				: this.longitude);
		return tbBranchschoolWeb;
	}

	public TbSchoolWeb toJson() {
		TbCity city = TWObjectUtil.getTbCity(this.tbTown);
		String[] strs = LalDistance.getLal(this.schoolAddress,
				city.getCityName());
		strs[0] = strs[0] == null ? "" : strs[0];
		strs[1] = strs[1] == null ? "" : strs[1];
		return new TbSchoolWeb(this.schoolId, this.schoolName,
				this.schoolMinName, strs[0], strs[1]);
	}

	// Property accessors

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
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
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

	public Timestamp getAlterTime() {
		return this.alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

	public Set getTbLessons() {
		return this.tbLessons;
	}

	public void setTbLessons(Set tbLessons) {
		this.tbLessons = tbLessons;
	}

	public Set getTbUserinfoclasses() {
		return this.tbUserinfoclasses;
	}

	public void setTbUserinfoclasses(Set tbUserinfoclasses) {
		this.tbUserinfoclasses = tbUserinfoclasses;
	}

	public Set getTbAdmins() {
		return this.tbAdmins;
	}

	public void setTbAdmins(Set tbAdmins) {
		this.tbAdmins = tbAdmins;
	}

	public Set getTbComplains() {
		return this.tbComplains;
	}

	public void setTbComplains(Set tbComplains) {
		this.tbComplains = tbComplains;
	}

	public Set getTbAttendances() {
		return this.tbAttendances;
	}

	public void setTbAttendances(Set tbAttendances) {
		this.tbAttendances = tbAttendances;
	}

	public Set getTbSchoolnotices() {
		return this.tbSchoolnotices;
	}

	public void setTbSchoolnotices(Set tbSchoolnotices) {
		this.tbSchoolnotices = tbSchoolnotices;
	}

	public Set getTbPushs() {
		return this.tbPushs;
	}

	public void setTbPushs(Set tbPushs) {
		this.tbPushs = tbPushs;
	}

	public Set getTbCurriculums() {
		return this.tbCurriculums;
	}

	public void setTbCurriculums(Set tbCurriculums) {
		this.tbCurriculums = tbCurriculums;
	}

	public Set getTbUserinfos() {
		return this.tbUserinfos;
	}

	public void setTbUserinfos(Set tbUserinfos) {
		this.tbUserinfos = tbUserinfos;
	}

	public Set getTbClassnotices() {
		return this.tbClassnotices;
	}

	public void setTbClassnotices(Set tbClassnotices) {
		this.tbClassnotices = tbClassnotices;
	}

	public Set getTbGrades() {
		return this.tbGrades;
	}

	public void setTbGrades(Set tbGrades) {
		this.tbGrades = tbGrades;
	}

	public Set getTbBranchschools() {
		return this.tbBranchschools;
	}

	public void setTbBranchschools(Set tbBranchschools) {
		this.tbBranchschools = tbBranchschools;
	}

}