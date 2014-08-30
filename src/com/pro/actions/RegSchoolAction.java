package com.pro.actions;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.CityManager;
import com.pro.manager.RegSchoolManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.TownManager;
import com.pro.util.Page;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbRegschool;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbTown;

public class RegSchoolAction extends BaseAction {
	private TbRegschool regschool;
	private int[] delid;
	private File img;
	private String imgContentType;
	private String imgFileName;
	private RegSchoolManager regSchoolManager;
	private SchoolManager schoolManager;
	private BranchSchoolManager branchManager;
	private TownManager townManager;
	private CityManager cityManager;
	private AdminManager adminManger;
	private String schoolName;
	
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}
	public void setTownManager(TownManager townManager) {
		this.townManager = townManager;
	}
	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}
	public void setAdminManger(AdminManager adminManger) {
		this.adminManger = adminManger;
	}
	public TbRegschool getRegschool() {
		return regschool;
	}
	public void setRegschool(TbRegschool regschool) {
		this.regschool = regschool;
	}
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getImgContentType() {
		return imgContentType;
	}
	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public void setRegSchoolManager(RegSchoolManager regSchoolManager) {
		this.regSchoolManager = regSchoolManager;
	}
	public String viewRegSchool(){
		DetachedCriteria dc1 = DetachedCriteria.forClass(TbRegschool.class);
		try{
			if(schoolName!=null && !schoolName.equals("")){
				schoolName =new String(schoolName.getBytes("ISO-8859-1"),"utf-8");
				dc1.add(Restrictions.like("schoolName", schoolName,MatchMode.ANYWHERE ));
				request.setAttribute("schoolName", schoolName);
			}
			Page.getResult(request, regSchoolManager, dc1, "schoolList", 15);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String RegSchoolDetail(){
		DetachedCriteria dc1 = DetachedCriteria.forClass(TbRegschool.class);
		DetachedCriteria dc2 = DetachedCriteria.forClass(TbCity.class);
		DetachedCriteria dc3 = DetachedCriteria.forClass(TbTown.class);
	try{
		//获取学校信息
		dc1.setFetchMode("tbTown", FetchMode.JOIN);
		dc1.add(Restrictions.eq("schoolId", regschool.getSchoolId()));
		TbRegschool shcool1 = (TbRegschool)regSchoolManager.findByCriteria(dc1).get(0);
		//获取城市列表regschool
		List<TbCity> cityList = cityManager.findByCriteria(dc2);
		
		//获取对应的城市id
		dc3.setFetchMode("tbCity", FetchMode.JOIN);
		dc3.add(Restrictions.eq("townId", shcool1.getTbTown().getTownId()));
		List<TbTown> townList = townManager.findByCriteria(dc3);
		int cityid = townList.get(0).getTbCity().getCityId();
		
		//根据城市id获取地区列表
		dc3.createAlias("tbCity", "city");
		dc3.add(Restrictions.eq("city.cityId", cityid));
		request.setAttribute("cityid", cityid);
		request.setAttribute("cityList", cityList);
		request.setAttribute("townList", townList);
		request.setAttribute("school", shcool1);
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
	}
	
	return SUCCESS;
}
	public String delRegSchoolDo(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbRegschool school2 = new TbRegschool();
				school2.setSchoolId(delid[i]);
				regSchoolManager.delete(school2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	public String checkRegSchool(){
		try{
			TbRegschool regschool1 = (TbRegschool)regSchoolManager.findById(regschool.getSchoolId());
			if(regschool.getStatus()==1){
				TbSchool school1 = new TbSchool();
				school1.setSchoolName(regschool1.getSchoolName());
				school1.setSchoolUrl(regschool1.getSchoolUrl());
				school1.setTbTown(regschool1.getTbTown());
				school1.setSchoolAddress(regschool1.getSchoolAddress());
				school1.setSchoolPhone(regschool1.getSchoolPhone());
				school1.setSubSchoolNum(regschool1.getSubSchoolNum());
				school1.setRectorName(regschool1.getRectorName());
				school1.setRectorQq(regschool1.getRectorQq());
				school1.setRectorPhone(regschool1.getRectorPhone());
				school1.setRectorEmail(regschool1.getRectorEmail());
				school1.setLinemanName(regschool1.getLinemanName());
				//school1.setLinemanEmail(regschool1.getLinemanEmail());
				school1.setLinemanPhone(regschool1.getLinemanPhone());
				school1.setLinemanQq(regschool1.getLinemanQq());
				school1.setStudentNum(regschool1.getStudentNum());
				school1.setSchoolCourse(regschool1.getSchoolCourse());
				school1.setSchoolCertificate(regschool1.getSchoolCertificate());
				school1.setSchoolUnit(regschool1.getSchoolUnit());
				school1.setIsValid(1);
				school1.setAlterTime(Timestamp.valueOf(this.getSystemDate(0)));
				school1.setTime(Timestamp.valueOf(this.getSystemDate(0)));
				schoolManager.save(school1);
			}
			regschool1.setStatus(regschool.getStatus());
			regSchoolManager.update(regschool1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
}
