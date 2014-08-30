package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.web.TbBranchschoolWeb;
import com.qiYang.util.LalDistance;
import com.qiYang.util.TWObjectUtil;
import com.qiYang.util.TWPictureUtil;

/**
 * TbBranchschool entity. @author MyEclipse Persistence Tools
 */

public class TbBranchschool implements java.io.Serializable {

	// Fields

	private Integer branchSchoolId;
	private TbTown tbTown;
	private TbSchool tbSchool;
	private String braschName;
	private String braschMinName;
	private String braschAddress;
	private Double longitude;
	private Double latitude;
	private String braschPhone;
	private String braschBusWay;
	private String braschStopLocation;
	private String braschPictureUrl;
	private String braschIntroduce;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbAdmins = new HashSet(0);
	private Set tbCurriculums = new HashSet(0);
	private Set tbSubjectinbranchschools = new HashSet(0);
	private Set tbTests = new HashSet(0);
	private Set tbPushs = new HashSet(0);
	private Set tbBillses = new HashSet(0);
	private Set tbClassnotices = new HashSet(0);
	private Set tbComplains = new HashSet(0);
	private Set tbNotices = new HashSet(0);
	private Set tbCourses = new HashSet(0);
	private Set tbUserinfoclasses = new HashSet(0);
	private Set tbAttendances = new HashSet(0);
	private Set tbChats = new HashSet(0);
	private Set tbSchoolnotices = new HashSet(0);
	private Set tbLessons = new HashSet(0);
	private Set tbUserphotos = new HashSet(0);
	private Set tbViptimes = new HashSet(0);
	private Set tbCharges = new HashSet(0);
	private Set tbGrades = new HashSet(0);
	private Set tbUserinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbBranchschool() {
	}

	/** full constructor */
	public TbBranchschool(TbTown tbTown, TbSchool tbSchool, String braschName,
			String braschMinName, String braschAddress, Double longitude,Double latitude,String braschPhone,
			String braschBusWay, String braschStopLocation,
			String braschPictureUrl, String braschIntroduce, Integer isValid,
			Timestamp time, Timestamp alterTime, Set tbAdmins,
			Set tbCurriculums, Set tbSubjectinbranchschools, Set tbTests,
			Set tbPushs, Set tbBillses, Set tbClassnotices, Set tbComplains,
			Set tbNotices, Set tbCourses, Set tbUserinfoclasses,
			Set tbAttendances, Set tbChats, Set tbSchoolnotices, Set tbLessons,
			Set tbUserphotos, Set tbViptimes, Set tbCharges, Set tbGrades,
			Set tbUserinfos) {
		this.tbTown = tbTown;
		this.tbSchool = tbSchool;
		this.braschName = braschName;
		this.braschMinName = braschMinName;
		this.braschAddress = braschAddress;
		this.longitude = longitude;
		this.latitude = latitude;
		this.braschPhone = braschPhone;
		this.braschBusWay = braschBusWay;
		this.braschStopLocation = braschStopLocation;
		this.braschPictureUrl = braschPictureUrl;
		this.braschIntroduce = braschIntroduce;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbAdmins = tbAdmins;
		this.tbCurriculums = tbCurriculums;
		this.tbSubjectinbranchschools = tbSubjectinbranchschools;
		this.tbTests = tbTests;
		this.tbPushs = tbPushs;
		this.tbBillses = tbBillses;
		this.tbClassnotices = tbClassnotices;
		this.tbComplains = tbComplains;
		this.tbNotices = tbNotices;
		this.tbCourses = tbCourses;
		this.tbUserinfoclasses = tbUserinfoclasses;
		this.tbAttendances = tbAttendances;
		this.tbChats = tbChats;
		this.tbSchoolnotices = tbSchoolnotices;
		this.tbLessons = tbLessons;
		this.tbUserphotos = tbUserphotos;
		this.tbViptimes = tbViptimes;
		this.tbCharges = tbCharges;
		this.tbGrades = tbGrades;
		this.tbUserinfos = tbUserinfos;
	}
	public TbBranchschoolWeb toPartWeb() {
		String braschPictureUrl = this.braschPictureUrl == null ? ""
				: TWPictureUtil.getNomalPicPath(this.braschPictureUrl);
		TbBranchschool branchschool=new TbBranchschool();
		branchschool.setBranchSchoolId(this.branchSchoolId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userInfoRoot", 0);
		map.put("tbBranchschool", branchschool);
		map.put("isValid", 0);
		DataBaseDaoImpl daoImpl = new DataBaseDaoImpl();
		List<TbUserinfo> list = daoImpl.getObjects(TbUserinfo.class, map);
		
		if(list==null||list.isEmpty()||list.get(0)==null)
			return new TbBranchschoolWeb(this.branchSchoolId, this.braschName,
					this.braschMinName, braschPictureUrl,0,"");
		else{
			Map<String, Object> map1=new HashMap<String, Object>();
			map1.put("tbUserinfo", list.get(0));
			TbUser tbUser = daoImpl.getObject(TbUser.class, map1);
		return new TbBranchschoolWeb(this.branchSchoolId, this.braschName,
				this.braschMinName, braschPictureUrl,TWObjectUtil.getInteger(list.get(0).getUserInfoId()),TWObjectUtil.getString(tbUser.getUserName()));
		}
	}
	public TbBranchschoolWeb toJson() {
		TbCity city = TWObjectUtil.getTbCity(this.tbTown);
		String[] strs = LalDistance.getLal(this.braschAddress, city.getCityName());
		strs[0]=strs[0]==null?"":strs[0];
		strs[1]=strs[1]==null?"":strs[1];
		return new TbBranchschoolWeb(this.branchSchoolId, this.braschName,this.braschMinName,strs[0] ,strs[1]);
	}
	public TbBranchschoolWeb toDetailJson() {
		TbBranchschoolWeb tbBranchschoolWeb = new TbBranchschoolWeb(this.branchSchoolId, TWObjectUtil.getString(this.braschName), TWObjectUtil.getString(this.braschMinName), TWObjectUtil.getString(this.braschAddress), TWObjectUtil.getString(this.braschPhone), TWObjectUtil.getString(this.braschBusWay), TWObjectUtil.getString(this.braschStopLocation), TWObjectUtil.getNomalPicPath(this.braschPictureUrl),TWObjectUtil.getString(this.braschIntroduce));
		tbBranchschoolWeb.setLatitude(this.latitude==null?0.0:this.latitude);
		tbBranchschoolWeb.setLongitude(this.longitude==null?0.0:this.longitude);
		return tbBranchschoolWeb;
	}
	// Property accessors

	public Integer getBranchSchoolId() {
		return this.branchSchoolId;
	}

	public void setBranchSchoolId(Integer branchSchoolId) {
		this.branchSchoolId = branchSchoolId;
	}

	public TbTown getTbTown() {
		return this.tbTown;
	}

	public void setTbTown(TbTown tbTown) {
		this.tbTown = tbTown;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public String getBraschName() {
		return this.braschName;
	}

	public void setBraschName(String braschName) {
		this.braschName = braschName;
	}

	public String getBraschMinName() {
		return this.braschMinName;
	}

	public void setBraschMinName(String braschMinName) {
		this.braschMinName = braschMinName;
	}

	public String getBraschAddress() {
		return this.braschAddress;
	}

	public void setBraschAddress(String braschAddress) {
		this.braschAddress = braschAddress;
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

	public String getBraschPhone() {
		return this.braschPhone;
	}

	public void setBraschPhone(String braschPhone) {
		this.braschPhone = braschPhone;
	}

	public String getBraschBusWay() {
		return this.braschBusWay;
	}

	public void setBraschBusWay(String braschBusWay) {
		this.braschBusWay = braschBusWay;
	}

	public String getBraschStopLocation() {
		return this.braschStopLocation;
	}

	public void setBraschStopLocation(String braschStopLocation) {
		this.braschStopLocation = braschStopLocation;
	}

	public String getBraschPictureUrl() {
		return this.braschPictureUrl;
	}

	public void setBraschPictureUrl(String braschPictureUrl) {
		this.braschPictureUrl = braschPictureUrl;
	}

	public String getBraschIntroduce() {
		return this.braschIntroduce;
	}

	public void setBraschIntroduce(String braschIntroduce) {
		this.braschIntroduce = braschIntroduce;
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

	public Set getTbAdmins() {
		return this.tbAdmins;
	}

	public void setTbAdmins(Set tbAdmins) {
		this.tbAdmins = tbAdmins;
	}

	public Set getTbCurriculums() {
		return this.tbCurriculums;
	}

	public void setTbCurriculums(Set tbCurriculums) {
		this.tbCurriculums = tbCurriculums;
	}

	public Set getTbSubjectinbranchschools() {
		return this.tbSubjectinbranchschools;
	}

	public void setTbSubjectinbranchschools(Set tbSubjectinbranchschools) {
		this.tbSubjectinbranchschools = tbSubjectinbranchschools;
	}

	public Set getTbTests() {
		return this.tbTests;
	}

	public void setTbTests(Set tbTests) {
		this.tbTests = tbTests;
	}

	public Set getTbPushs() {
		return this.tbPushs;
	}

	public void setTbPushs(Set tbPushs) {
		this.tbPushs = tbPushs;
	}

	public Set getTbBillses() {
		return this.tbBillses;
	}

	public void setTbBillses(Set tbBillses) {
		this.tbBillses = tbBillses;
	}

	public Set getTbClassnotices() {
		return this.tbClassnotices;
	}

	public void setTbClassnotices(Set tbClassnotices) {
		this.tbClassnotices = tbClassnotices;
	}

	public Set getTbComplains() {
		return this.tbComplains;
	}

	public void setTbComplains(Set tbComplains) {
		this.tbComplains = tbComplains;
	}

	public Set getTbNotices() {
		return this.tbNotices;
	}

	public void setTbNotices(Set tbNotices) {
		this.tbNotices = tbNotices;
	}

	public Set getTbCourses() {
		return this.tbCourses;
	}

	public void setTbCourses(Set tbCourses) {
		this.tbCourses = tbCourses;
	}

	public Set getTbUserinfoclasses() {
		return this.tbUserinfoclasses;
	}

	public void setTbUserinfoclasses(Set tbUserinfoclasses) {
		this.tbUserinfoclasses = tbUserinfoclasses;
	}

	public Set getTbAttendances() {
		return this.tbAttendances;
	}

	public void setTbAttendances(Set tbAttendances) {
		this.tbAttendances = tbAttendances;
	}

	public Set getTbChats() {
		return this.tbChats;
	}

	public void setTbChats(Set tbChats) {
		this.tbChats = tbChats;
	}

	public Set getTbSchoolnotices() {
		return this.tbSchoolnotices;
	}

	public void setTbSchoolnotices(Set tbSchoolnotices) {
		this.tbSchoolnotices = tbSchoolnotices;
	}

	public Set getTbLessons() {
		return this.tbLessons;
	}

	public void setTbLessons(Set tbLessons) {
		this.tbLessons = tbLessons;
	}

	public Set getTbUserphotos() {
		return this.tbUserphotos;
	}

	public void setTbUserphotos(Set tbUserphotos) {
		this.tbUserphotos = tbUserphotos;
	}

	public Set getTbViptimes() {
		return this.tbViptimes;
	}

	public void setTbViptimes(Set tbViptimes) {
		this.tbViptimes = tbViptimes;
	}

	public Set getTbCharges() {
		return this.tbCharges;
	}

	public void setTbCharges(Set tbCharges) {
		this.tbCharges = tbCharges;
	}

	public Set getTbGrades() {
		return this.tbGrades;
	}

	public void setTbGrades(Set tbGrades) {
		this.tbGrades = tbGrades;
	}

	public Set getTbUserinfos() {
		return this.tbUserinfos;
	}

	public void setTbUserinfos(Set tbUserinfos) {
		this.tbUserinfos = tbUserinfos;
	}

}