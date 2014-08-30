package com.pro.actions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.KechengManager;
import com.pro.manager.LessonManager;
import com.pro.manager.OfuserManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.TownManager;
import com.pro.manager.UserInfoManager;
import com.pro.manager.UserManager;
import com.qiYang.model.Ofuser;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;

public class JsonAction extends BaseAction {
	private static final long serialVersionUID = 1359090410097337654L;
	private List<TbBranchschool> branch = new ArrayList<TbBranchschool>();
	private List<TbUserinfo> userInfo = new ArrayList<TbUserinfo>();
	private List<TbLesson> kecheng = new ArrayList<TbLesson>();
	private List<TbCurriculum> ban = new ArrayList<TbCurriculum>();
	private List<TbTown> town = new ArrayList<TbTown>();
	private BranchSchoolManager branchManager;
	private SchoolManager schoolManager; 
	private UserInfoManager userInfoManager;
	private KechengManager kechengManager;
	private TownManager townManager;
	private AdminManager adminManager;
	private LessonManager lessonManager;
	private OfuserManager ofuserManager;
	private UserManager userManager;
	private int schoolid;
	private int branchid;
	private int subjectid;
	private int cityId;
	private int lessonid;
	private int size;
	private int teachersize;
	private int userId;
	private String regname;
	
	public List<TbUserinfo> getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(List<TbUserinfo> userInfo) {
		this.userInfo = userInfo;
	}


	public List<TbBranchschool> getBranch() {
		return branch;
	}


	public void setBranch(List<TbBranchschool> branch) {
		this.branch = branch;
	}
	




	public List<TbLesson> getKecheng() {
		return kecheng;
	}


	public void setKecheng(List<TbLesson> kecheng) {
		this.kecheng = kecheng;
	}


	public List<TbCurriculum> getBan() {
		return ban;
	}


	public void setBan(List<TbCurriculum> ban) {
		this.ban = ban;
	}


	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}


	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}


	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
	

	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}


	public void setKechengManager(KechengManager kechengManager) {
		this.kechengManager = kechengManager;
	}


	public void setLessonManager(LessonManager lessonManager) {
		this.lessonManager = lessonManager;
	}


	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


	public int getSchoolid() {
		return schoolid;
	}


	public void setTownManager(TownManager townManager) {
		this.townManager = townManager;
	}


	public void setOfuserManager(OfuserManager ofuserManager) {
		this.ofuserManager = ofuserManager;
	}


	public void setSchoolid(int schoolid) {
		this.schoolid = schoolid;
	}
	

	public int getBranchid() {
		return branchid;
	}
	
	public int getSize() {
		return size;
	}


	public int getLessonid() {
		return lessonid;
	}
	

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}


	public String getRegname() {
		return regname;
	}


	public void setRegname(String regname) {
		this.regname = regname;
	}


	public List<TbTown> getTown() {
		return town;
	}


	public void setTown(List<TbTown> town) {
		this.town = town;
	}


	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}


	public int getSubjectid() {
		return subjectid;
	}


	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}


	public int getCityId() {
		return cityId;
	}


	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getUserId() {
		return userId;
	}


	public int getTeachersize() {
		return teachersize;
	}


	@SuppressWarnings("unchecked")
	public String allBranch(){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(TbBranchschool.class);
			dc.createAlias("tbSchool", "school");
			dc.add(Restrictions.eq("school.schoolId", schoolid));
			branch = branchManager.findByCriteria(dc);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String allUserInfo(){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(TbUserinfo.class);
			dc.createAlias("tbBranchschool", "branch");
			dc.add(Restrictions.eq("branch.branchSchoolId", branchid));
			userInfo = userInfoManager.findByCriteria(dc);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String allKecheng(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbLesson.class);
			TbAdmin onlineadmin = this.loginAdmin();
			if(onlineadmin!=null){
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbAdmin.class);
				dc1.add(Restrictions.eq("adminId", onlineadmin.getAdminId()));
				dc1.setFetchMode("tbSchool", FetchMode.JOIN);
				List<TbAdmin> adminlist1 = adminManager.findByCriteria(dc1);
				if(adminlist1.size()>0){
					TbAdmin admin1 = adminlist1.get(0);
					
					dc.createAlias("tbSchool", "school");
					if(admin1.getAdminRoot()!=0)
						dc.add(Restrictions.eq("school.schoolId", admin1.getTbSchool().getSchoolId()));
					dc.createAlias("tbSubject", "subs");
					dc.add(Restrictions.eq("subs.subjectId", subjectid));
					kecheng = lessonManager.findByCriteria(dc);
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String allTown(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbTown.class);
			dc.createAlias("tbCity", "city");
			dc.add(Restrictions.eq("city.cityId", cityId));
			town = townManager.findByCriteria(dc);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//验证重名
	public String checkName(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminName", regname));
			List<TbAdmin> adminlist = adminManager.findByCriteria(dc);
			size = adminlist.size();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//老师登录调用
	public String allKecheng2(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbLesson.class);
			TbAdmin onlineadmin = this.loginAdmin();
			if(onlineadmin!=null){
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfo.class);
				dc1.setFetchMode("tbSchool", FetchMode.JOIN);
				dc1.add(Restrictions.eq("userInfoId", onlineadmin.getIsValid()));
				List<TbUserinfo> adminlist1 = userInfoManager.findByCriteria(dc1);
				if(adminlist1.size()>0){
					TbUserinfo admin1 = adminlist1.get(0);
					
					dc.createAlias("tbSchool", "school");
					dc.add(Restrictions.eq("school.schoolId", admin1.getTbSchool().getSchoolId()));
					dc.createAlias("tbSubject", "subs");
					dc.add(Restrictions.eq("subs.subjectId", subjectid));
					kecheng = lessonManager.findByCriteria(dc);
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String allBanInfo(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbCurriculum.class);
			dc.createAlias("tbLesson", "ban");
			dc.add(Restrictions.eq("ban.id", lessonid));
			
			ban = kechengManager.findByCriteria(dc);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//验证重名
		public String checkLoginName(){
			try{
				DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
				dc.add(Restrictions.eq("userName", regname));
				List<TbUser> userlist = userManager.findByCriteria(dc);
				if(userlist.size()>0){
					DetachedCriteria dc1 = DetachedCriteria.forClass(Ofuser.class);
					Property p1 = Property.forName("username");
					dc1.add(p1.eq(regname));
					List<Ofuser> ofuserlist = ofuserManager.findByCriteria(dc1);
					
					size = userlist.size()+ofuserlist.size();
					
					dc.createAlias("tbUserinfo", "ui");
					dc.add(Restrictions.eq("ui.userInfoRoot", 1));
					List<TbUser> userlist1 = userManager.findByCriteria(dc);
					teachersize = userlist1.size();
					
					if(teachersize>0){
						userId = userlist1.get(0).getUserId();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return SUCCESS;
		}
}
