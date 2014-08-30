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
import com.pro.manager.SchoolManager;
import com.pro.manager.TownManager;
import com.pro.util.Page;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbTown;
import com.qiYang.util.LalDistance;
import com.qiYang.util.StrutsUpload;

public class SchoolAction extends BaseAction {
	
	private int cityId;
	private int townId;
	private File logo;
	private String logoContentType;
	private String logoFileName;
	private File img;
	private String imgContentType;
	private String imgFileName;
	private TbSchool school;
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
	public int getTownId() {
		return townId;
	}
	public void setTownId(int townId) {
		this.townId = townId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public File getLogo() {
		return logo;
	}
	public void setLogo(File logo) {
		this.logo = logo;
	}
	public String getLogoContentType() {
		return logoContentType;
	}
	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}
	public String getLogoFileName() {
		return logoFileName;
	}
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
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

	private int[] delid;
	
	public TbSchool getSchool() {
		return school;
	}
	public void setSchool(TbSchool school) {
		this.school = school;
	}
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
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
	public String parentSchool(){
		DetachedCriteria dc1 = DetachedCriteria.forClass(TbSchool.class);
		
		try{
			if(schoolName!=null && !schoolName.equals("")){
				//schoolName =new String(schoolName.getBytes("ISO-8859-1"),"utf-8");
				schoolName = schoolName.replaceAll("[　\\s\\t\\n\\r\\f]", "");
				dc1.add(Restrictions.like("schoolName", schoolName,MatchMode.ANYWHERE ));
				request.setAttribute("schoolName", schoolName);
			}
			TbAdmin loginadmin = this.loginAdmin();
			if(loginadmin.getAdminRoot()==1){
				DetachedCriteria dc2 = DetachedCriteria.forClass(TbAdmin.class);
				dc2.setFetchMode("tbSchool", FetchMode.JOIN);
				dc2.add(Restrictions.eq("adminId", loginadmin.getAdminId()));
				List<TbAdmin> admintemp = adminManger.findByCriteria(dc2);
				TbAdmin aadd = admintemp.get(0);
				dc1.add(Restrictions.eq("schoolId", aadd.getTbSchool().getSchoolId()));
			}	
			Page.getResult(request, schoolManager, dc1, "schoolList", 15);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String addSchoolDo(){
		//DetachedCriteria dc1 = DetachedCriteria.forClass(TbTown.class);
		try{
			//200KB
		  if(img!=null){
			if(img.length()>IMAGE_SIZE)
				return "fail";
			imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
			school.setSchoolPicture(imgFileName);
		  }else{
			  school.setSchoolPicture("root2.png");
		  }
		  if(logo!=null){
				if(logo.length()>IMAGE_SIZE)
					return "fail";
				logoFileName = StrutsUpload.upLoadFile(logo, logoContentType, logoFileName);
				school.setSchoolLogo(logoFileName);
		  }else{
			  school.setSchoolLogo("root2.png");
		  }
		    TbTown town1 = (TbTown)townManager.findById(townId);
		    school.setTbTown(town1);
		    
		    TbCity city1 = (TbCity)cityManager.findById(cityId);
			
			String[] coordinate = LalDistance.getLal(school.getSchoolAddress(), city1.getCityName());
			if(coordinate[0]!=null&&coordinate[1]!=null){
				school.setLongitude(Double.parseDouble(coordinate[0]));
				school.setLatitude(Double.parseDouble(coordinate[1]));
			}
		    
		  	Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			school.setTime(nowdate);
			school.setAlterTime(nowdate);
			schoolManager.save(school);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String editSchool(){
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSchool.class);
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbCity.class);
			DetachedCriteria dc3 = DetachedCriteria.forClass(TbTown.class);
		try{
			//获取学校信息
			dc1.setFetchMode("tbTown", FetchMode.JOIN);
			TbSchool shcool1 = (TbSchool)schoolManager.findById(school.getSchoolId());
			//获取城市列表
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
	
	public String editSchoolDo(){
		DetachedCriteria dc1 = DetachedCriteria.forClass(TbSchool.class);
		
		try{
			TbSchool school1 = (TbSchool)schoolManager.findById(school.getSchoolId());
			//200KB
		  if(img!=null){
			if(img.length()>IMAGE_SIZE)
				return "fail";
			imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
			school.setSchoolPicture(imgFileName);
		  }else{
			  school.setSchoolPicture(school1.getSchoolPicture());
		  }
		  if(logo!=null){
				if(logo.length()>IMAGE_SIZE)
					return "fail";
				logoFileName = StrutsUpload.upLoadFile(logo, logoContentType, logoFileName);
				school.setSchoolLogo(logoFileName);
		  }else{
			  school.setSchoolLogo(school1.getSchoolLogo());
		  }
		  
		    TbTown town1 = (TbTown)townManager.findById(townId);
		    school.setTbTown(town1);
		    
		    TbCity city1 = (TbCity)cityManager.findById(cityId);
			
			String[] coordinate = LalDistance.getLal(school.getSchoolAddress(), city1.getCityName());
			if(coordinate[0]!=null&&coordinate[1]!=null){
				school.setLongitude(Double.parseDouble(coordinate[0]));
				school.setLatitude(Double.parseDouble(coordinate[1]));
			}
		    
		  	Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
		  	school.setTime(school1.getTime());
		  	school.setAlterTime(nowdate);
			schoolManager.update(school);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return SUCCESS;
	}
	public String delSchoolDo(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbSchool school2 = (TbSchool)schoolManager.findById(delid[i]);
				DetachedCriteria dc = DetachedCriteria.forClass(TbBranchschool.class);
				dc.createAlias("tbSchool", "sc");
				
				dc.add(Restrictions.eq("sc.schoolId", delid[i]));
				List<TbBranchschool> branchshool1	= branchManager.findByCriteria(dc);
				if(branchshool1.size()<=0)
					schoolManager.delete(school2);
				else 
					request.setAttribute("delTip", 1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
}
