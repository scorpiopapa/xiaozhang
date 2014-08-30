package com.pro.actions;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.OfuserManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.UserInfoClassManager;
import com.pro.manager.UserInfoManager;
import com.pro.manager.UserManager;
import com.pro.util.Page;
import com.qiYang.model.Ofuser;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;

public class UserAction extends BaseAction {
	
	private TbUser user;
	private TbUserinfo usrInfo;
	private TbAdmin admin;
	private UserManager userManager;
	private AdminManager adminManager;
	private UserInfoManager userInfoManager;
	private UserInfoClassManager userclassManager;
	private SchoolManager schoolManager;
	private BranchSchoolManager branchManager;
	private OfuserManager ofuserManager;
	private int usertype;
	private int schoolId;
	private int branchId;
	private File img;
	private String imgContentType;
	private String imgFileName;
	
	public TbUserinfo getUsrInfo() {
		return usrInfo;
	}


	public void setUsrInfo(TbUserinfo usrInfo) {
		this.usrInfo = usrInfo;
	}


	public TbAdmin getAdmin() {
		return admin;
	}


	public void setAdmin(TbAdmin admin) {
		this.admin = admin;
	}


	public TbUser getUser() {
		return user;
	}


	public void setUser(TbUser user) {
		this.user = user;
	}


	public int getUsertype() {
		return usertype;
	}


	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}


	public int getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}


	public int getBranchId() {
		return branchId;
	}


	public void setBranchId(int branchId) {
		this.branchId = branchId;
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


	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}
	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}
	
	public void setUserclassManager(UserInfoClassManager userclassManager) {
		this.userclassManager = userclassManager;
	}


	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}


	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}


	public void setOfuserManager(OfuserManager ofuserManager) {
		this.ofuserManager = ofuserManager;
	}


	//老师登录
	@SuppressWarnings("unused")
	public String teacherLogin(){
		String path = "";
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
			dc.createAlias("tbUserinfo", "ui");
			dc.add(Restrictions.eq("ui.userInfoRoot", 1));
			dc.add(Restrictions.eq("userName", admin.getAdminName()));
			dc.add(Restrictions.eq("userPassword", admin.getAdminPassword()));
			List<TbUser> userlist = userManager.findByCriteria(dc);
			
			if(userlist.size()>0){
				HttpSession session = request.getSession();
				TbUser user1 = userlist.get(0);
				int adminroot = user1.getTbUserinfo().getUserInfoRoot();
				TbAdmin aadd = new TbAdmin();
				
				aadd.setAdminId(user1.getUserId());
				aadd.setAdminName(user1.getTbUserinfo().getUserInfoName());
				aadd.setAdminPassword(user1.getUserPassword());
				aadd.setAdminRoot(3);
				//存放userinfoid
				aadd.setIsValid(user1.getTbUserinfo().getUserInfoId());
				session.setAttribute("loginAdmin", aadd);
				path = SUCCESS;
			}else{
				request.setAttribute("loginFail", 1);
				path = "fail";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return path;
	}
	//查看user及userinfoclass表
	public String viewUsers(){
		String path ="";
		try{
			
			TbAdmin admin1 = this.loginAdmin();
			int ar = admin1.getAdminRoot();
			int userroot = 0;
			switch(usertype){
				case 0:path="master";break;
				case 1:path="teacher";break;
				case 2:path="parent";break;
				case 3:path="student";break;
			}
				switch (ar) {
				//超级管理员
				case 0:
					  break;
				//总校管理员
				case 1:
					//获取总校id
					  DetachedCriteria dcc = DetachedCriteria.forClass(TbAdmin.class); 
					 
					  dcc.add(Restrictions.eq("adminId", admin1.getAdminId()));
					  dcc.setFetchMode("tbBranchschool", FetchMode.JOIN);
					  List<TbAdmin> userlistc = adminManager.findByCriteria(dcc);
					  TbAdmin userc = userlistc.get(0);
					  int schoolid = userc.getTbSchool().getSchoolId();
					  //获取指定总校下分校的校长信息
					  DetachedCriteria dcd = DetachedCriteria.forClass(TbUserinfo.class);
					  dcd.createAlias("tbSchool", "school");
					  dcd.add(Restrictions.eq("school.schoolId", schoolid));
					  List<TbUserinfo> userinfolist279 = userInfoManager.findByCriteria(dcd);
					 
					  List<Object> infoid = new ArrayList<Object>();
					  for (TbUserinfo tbUserinfo : userinfolist279) {
							infoid.add(tbUserinfo.getUserInfoId());
						}
					if(infoid.size()>0){
						//获取用户的
						  DetachedCriteria dc3 = DetachedCriteria.forClass(TbUser.class);
						  
						  switch (usertype) {

							//查看分校校长
							case 0:userroot = 0; break;
							//查看老师
							case 1:userroot = 1; break;
							//查看家长
							case 2:userroot = 2; break;
							//查看学生
							case 3:userroot = 3; break;
						}
						//根据类型查找用户表
						  dc3.createAlias("tbUserinfo", "ui");
						  dc3.add(Restrictions.in("ui.userInfoId", infoid));
						  dc3.add(Restrictions.eq("ui.userInfoRoot", userroot));
						  Page.getResult(request, userManager, dc3, "userList", 20);
					}
					  break;
				//分校管理员
				case 2:
					//获取分校id
					  DetachedCriteria dcb = DetachedCriteria.forClass(TbAdmin.class); 
					 
					  dcb.add(Restrictions.eq("adminId", admin1.getAdminId()));
					  dcb.setFetchMode("tbBranchschool", FetchMode.JOIN);
					  List<TbAdmin> userlistb = adminManager.findByCriteria(dcb);
					  if(userlistb.size()<=0)
						  return path;
					  TbAdmin userb = userlistb.get(0);
					  int branchid = userb.getTbBranchschool().getBranchSchoolId();
					//获取用户的
					  DetachedCriteria dc2 = DetachedCriteria.forClass(TbUserinfo.class); 
					  dc2.createAlias("tbBranchschool", "branch");
					  dc2.add(Restrictions.eq("branch.branchSchoolId", branchid));
					  List<Object> infoid1 = new ArrayList<Object>();
					  List<TbUserinfo> userinfolist121 = userInfoManager.findByCriteria(dc2);
					  for (TbUserinfo usf121 : userinfolist121) {
						  infoid1.add(usf121.getUserInfoId());
					  }
					  if(infoid1.size()>0){
						  DetachedCriteria dc4 = DetachedCriteria.forClass(TbUser.class);
						  switch (usertype) {
						  //查看老师
							case 1:userroot = 1; break;
							//查看家长
							case 2:userroot = 2; break;
							//查看学生
							case 3:userroot = 3; break;
						 }
						//根据类型查找用户表
						  dc4.createAlias("tbUserinfo", "ui");
						  dc4.add(Restrictions.in("ui.userInfoId", infoid1));
						  dc4.add(Restrictions.eq("ui.userInfoRoot", userroot));
						  Page.getResult(request, userManager, dc4, "userList", 20);
					  }  
					  break;
				//老师
				case 3:
					/*获取userInfoid
					  DetachedCriteria dca = DetachedCriteria.forClass(TbUser.class); 
					  
					  dca.add(Restrictions.eq("userId", admin1.getAdminId()));
					  dca.setFetchMode("tbUserinfo", FetchMode.JOIN);
					  List<TbUser> userlista = userManager.findByCriteria(dca);
					  TbUser usera = userlista.get(0);*/
					  int userinfoid = this.loginAdmin().getIsValid();
					//获取老师所有的班级
					  List<Object> infoid11 = new ArrayList<Object>();
					  if(infoid11.size()>0){
					  switch (usertype) {
					  //查看家长
						case 2:userroot = 2; break;
						//查看学生
						case 3:userroot = 3; break;
					  }
					  
					//根据类型查找用户表
					  DetachedCriteria dc6 = DetachedCriteria.forClass(TbUserinfoclass.class); 
					  dc6.createAlias("tbUserinfo", "ui");
					  dc6.add(Restrictions.eq("ui.userInfoId", userinfoid));
					  dc6.setFetchMode("", FetchMode.JOIN);
					  List<TbUserinfoclass> uiclist1 = userclassManager.findByCriteria(dc6);
					  
					  
					  }
					  break;
				}
			
			
			request.setAttribute("usertype", usertype);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return path;
	}
	
	//添加老师
	public String addTeacher(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminId", login.getAdminId()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbAdmin> adminlist1 = adminManager.findByCriteria(dc);
			TbAdmin admin1 = adminlist1.get(0);
			
			if(adminroot==1){
				
			}
			if(adminroot==2){
				request.setAttribute("schools", admin1.getTbSchool());
				request.setAttribute("branchs", admin1.getTbBranchschool());
			}
			
			request.setAttribute("usertype", usertype);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//保存添加老师
	public String addTeacherDo(){
		try{
			request.setAttribute("usertype", usertype);
			
			int size = 0;
			DetachedCriteria dcu = DetachedCriteria.forClass(TbUser.class);
			dcu.add(Restrictions.eq("userName", user.getUserName()));
			List<TbUser> userlist = userManager.findByCriteria(dcu);
			
			DetachedCriteria dco = DetachedCriteria.forClass(Ofuser.class);
			Property p1 = Property.forName("username");
			dco.add(p1.eq(user.getUserName()));
			List<Ofuser> ofuserlist = ofuserManager.findByCriteria(dco);
			
			size = userlist.size()+ofuserlist.size();
			
			if(size<=0){
				TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
				TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branchId);
				Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
				DateFormat nowdate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//50KB
			  /*if(img!=null){
				if(img.length()>5120000)
					return "fail";
				imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName, "/uploadimg");
				usrInfo.setUserInfoAvatar("/uploadimg/"+imgFileName);
			  }*/
				
				Ofuser ofuser = new Ofuser();
				ofuser.setUsername(user.getUserName());
				ofuser.setPlainPassword("111111");
				ofuser.setName(usrInfo.getUserInfoName());
				ofuser.setEmail(usrInfo.getUserInfoEmail());
				ofuser.setModificationDate("0");
				ofuser.setCreationDate(Long.toString(new Date().getTime()));
				ofuserManager.save(ofuser);
				
				usrInfo.setTbSchool(school1);
				usrInfo.setTbBranchschool(branch1);
				usrInfo.setTime(nowdate);
				usrInfo.setAlterTime(nowdate);
				usrInfo.setUserInfoRoot(1);
				usrInfo.setUserInfoAvatar("default.png");
				usrInfo.setIsValid(1);
				branchManager.save(usrInfo);
				
				user.setTbUserinfo(usrInfo);
				user.setUserPassword("000000");
				user.setIsValid(1);
				user.setTime(nowdate);
				user.setAlterTime(nowdate);
				userManager.save(user);
			}else{
				request.setAttribute("addTip", 1);
				return "fail";
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//添加老师
	public String addTeacherToclass(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminId", login.getAdminId()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbAdmin> adminlist1 = adminManager.findByCriteria(dc);
			TbAdmin admin1 = adminlist1.get(0);
			
			if(adminroot==1){
				
			}
			if(adminroot==2){
				request.setAttribute("schools", admin1.getTbSchool());
				request.setAttribute("branchs", admin1.getTbBranchschool());
			}
			
			request.setAttribute("usertype", usertype);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//已存在老师加入分校
		public String addTeacherToclassDo(){
			try{
				request.setAttribute("usertype", usertype);
				
				int size = 0;
				DetachedCriteria dcu = DetachedCriteria.forClass(TbUser.class);
				dcu.add(Restrictions.eq("userName", user.getUserName()));
				List<TbUser> userlist = userManager.findByCriteria(dcu);
				
				DetachedCriteria dco = DetachedCriteria.forClass(Ofuser.class);
				Property p1 = Property.forName("username");
				dco.add(p1.eq(user.getUserName()));
				List<Ofuser> ofuserlist = ofuserManager.findByCriteria(dco);
				
				size = userlist.size()+ofuserlist.size();
				
				if(size<=0){
					TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
					TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branchId);
					Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
					DateFormat nowdate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//50KB
				  /*if(img!=null){
					if(img.length()>5120000)
						return "fail";
					imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName, "/uploadimg");
					usrInfo.setUserInfoAvatar("/uploadimg/"+imgFileName);
				  }*/
					
					usrInfo.setTbSchool(school1);
					usrInfo.setTbBranchschool(branch1);
					usrInfo.setTime(nowdate);
					usrInfo.setAlterTime(nowdate);
					usrInfo.setUserInfoRoot(1);
					usrInfo.setUserInfoAvatar("default.png");
					usrInfo.setIsValid(1);
					branchManager.save(usrInfo);
					
					user.setTbUserinfo(usrInfo);
					user.setUserPassword("000000");
					user.setIsValid(1);
					user.setTime(nowdate);
					user.setAlterTime(nowdate);
					userManager.save(user);
				}else{
					request.setAttribute("addTip", 1);
					return "fail";
				}
				
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return SUCCESS;
		}
	//编辑老师
	public String editTeacher(){
		try{
			request.setAttribute("usertype", usertype);
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbUser.class);
			dc1.add(Restrictions.eq("userId", user.getUserId()));
			dc1.setFetchMode("tbUserinfo", FetchMode.JOIN);
			List<TbUser> userlist1 = userManager.findByCriteria(dc1);
			TbUser user1 = userlist1.get(0);
			request.setAttribute("user", user1);
			
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbUserinfo.class);
			dc2.add(Restrictions.eq("userInfoId", user1.getTbUserinfo().getUserInfoId()));
			dc2.setFetchMode("tbSchool", FetchMode.JOIN);
			dc2.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbUserinfo> userinfolist1 = userInfoManager.findByCriteria(dc2);
			TbUserinfo userinfo1 = userinfolist1.get(0);
			request.setAttribute("school", userinfo1.getTbSchool());
			request.setAttribute("branch", userinfo1.getTbBranchschool());
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//保存编辑的老师
	public String editTeacherDo(){
		try{
			request.setAttribute("usertype", usertype);
			TbUser user1 = (TbUser)userManager.findById(user.getUserId());
				   
			TbUserinfo userinfo1 = (TbUserinfo)userInfoManager.findById(usrInfo.getUserInfoId());
			user.setUserPassword(user1.getUserPassword());

		  usrInfo.setUserInfoRoot(userinfo1.getUserInfoRoot());
		  Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));//
		  userinfo1.setUserInfoName(usrInfo.getUserInfoName());
		  userinfo1.setUserInfoSex(usrInfo.getUserInfoSex());
		  userinfo1.setUserInfoCourse(usrInfo.getUserInfoCourse());
		  userinfo1.setUserInfoPhone(usrInfo.getUserInfoPhone());
		  userinfo1.setUserInfoEmail(usrInfo.getUserInfoEmail());
		  userinfo1.setWorkYear(usrInfo.getWorkYear());
		  userinfo1.setIsValid(user.getIsValid());
		  userinfo1.setStudentSchool(usrInfo.getStudentSchool());
		  userinfo1.setAlterTime(nowdate);
		  user1.setAlterTime(nowdate);
		  user1.setIsValid(user.getIsValid());
		  
		  userManager.update(user1);
		  userInfoManager.update(userinfo1);
		  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//导入老师
	public String importTeachers(){
		try{
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String editonestudent(){
		try{
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfoclass.class);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
