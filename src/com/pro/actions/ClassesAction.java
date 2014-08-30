package com.pro.actions;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.CourseManager;
import com.pro.manager.GradeManager;
import com.pro.manager.KechengManager;
import com.pro.manager.LessonManager;
import com.pro.manager.OfuserManager;
import com.pro.manager.RelationManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.SubBranchManager;
import com.pro.manager.SubjectManager;
import com.pro.manager.UserInfoClassManager;
import com.pro.manager.UserInfoManager;
import com.pro.manager.UserManager;
import com.pro.util.Page;
import com.qiYang.dao.FindUserDao;
import com.qiYang.model.Ofuser;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;
import com.qiYang.util.Separator;
import com.qiYang.util.StrutsUpload;

public class ClassesAction extends BaseAction {
	
	private int schoolId;
	private int branchId;
	private int subjectId;
	private int gradeId;
	private int kechengid;
	private String courseName;
	private int teachertype;
	private File img;
	private String imgContentType;
	private String imgFileName;
	
	private int[] delid;
	private int[] grades;
	private TbUser user;
	private TbUserinfo userInfo;
	private TbUserinfoclass userClass;
	private TbSchool school;
	private TbBranchschool branch;
	private TbCurriculum kecheng;
	private TbCourse course;
	private TbAdmin admin;
	private TbLesson lesson;
	private TbSubject subject;
	private String lessonName;
	
	
	public String getLessonName() {
		return lessonName;
	}


	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}


	public TbSubject getSubject() {
		return subject;
	}


	public void setSubject(TbSubject subject) {
		this.subject = subject;
	}
	private UserManager userManager;
	private AdminManager adminManager;
	private UserInfoManager userInfoManager;
	private UserInfoClassManager userclassManager;
	private SchoolManager schoolManager;
	private BranchSchoolManager branchManager;
	private CourseManager courseManager;
	private KechengManager kechengManager;
	private SubjectManager subjectManager;
	private GradeManager gradeManager;
	private SubBranchManager subBranchManager;
	private LessonManager lessonManager;
	private RelationManager relationManager;
	private OfuserManager ofuserManager;
	private int[] sbranchId;
	
	public int[] getSbranchId() {
		return sbranchId;
	}


	public void setSbranchId(int[] sbranchId) {
		this.sbranchId = sbranchId;
	}


	public void setOfuserManager(OfuserManager ofuserManager) {
		this.ofuserManager = ofuserManager;
	}


	public void setGradeManager(GradeManager gradeManager) {
		this.gradeManager = gradeManager;
	}


	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}


	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
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


	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}


	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}


	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}


	public void setKechengManager(KechengManager kechengManager) {
		this.kechengManager = kechengManager;
	}


	public void setSubBranchManager(SubBranchManager subBranchManager) {
		this.subBranchManager = subBranchManager;
	}


	public LessonManager getLessonManager() {
		return lessonManager;
	}


	public void setLessonManager(LessonManager lessonManager) {
		this.lessonManager = lessonManager;
	}


	public void setRelationManager(RelationManager relationManager) {
		this.relationManager = relationManager;
	}


	public TbUserinfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(TbUserinfo userInfo) {
		this.userInfo = userInfo;
	}


	public int[] getDelid() {
		return delid;
	}


	public void setDelid(int[] delid) {
		this.delid = delid;
	}


	public int[] getGrades() {
		return grades;
	}


	public void setGrades(int[] grades) {
		this.grades = grades;
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


	public TbUser getUser() {
		return user;
	}


	public void setUser(TbUser user) {
		this.user = user;
	}


	public TbUserinfoclass getUserClass() {
		return userClass;
	}


	public void setUserClass(TbUserinfoclass userClass) {
		this.userClass = userClass;
	}


	public TbSchool getSchool() {
		return school;
	}


	public void setSchool(TbSchool school) {
		this.school = school;
	}


	public TbBranchschool getBranch() {
		return branch;
	}


	public void setBranch(TbBranchschool branch) {
		this.branch = branch;
	}


	public TbCurriculum getKecheng() {
		return kecheng;
	}


	public void setKecheng(TbCurriculum kecheng) {
		this.kecheng = kecheng;
	}


	public TbCourse getCourse() {
		return course;
	}


	public void setCourse(TbCourse course) {
		this.course = course;
	}


	public TbAdmin getAdmin() {
		return admin;
	}


	public void setAdmin(TbAdmin admin) {
		this.admin = admin;
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


	public int getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}


	public int getGradeId() {
		return gradeId;
	}


	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}


	public int getKechengid() {
		return kechengid;
	}


	public void setKechengid(int kechengid) {
		this.kechengid = kechengid;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public int getTeachertype() {
		return teachertype;
	}


	public void setTeachertype(int teachertype) {
		this.teachertype = teachertype;
	}


	public TbLesson getLesson() {
		return lesson;
	}


	public void setLesson(TbLesson lesson) {
		this.lesson = lesson;
	}


	//查看班级
	public String viewClass(){
		try{
			
			
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbUserinfoclass.class);
			dc2.createAlias("tbUserinfo", "uc");
			dc2.setFetchMode("tbCourse", FetchMode.JOIN);
			dc2.add(Restrictions.eq("uc.userInfoId", this.loginAdmin().getIsValid()));
			List<TbUserinfoclass> userinfoclasslist1 = userclassManager.findByCriteria(dc2);
			
			List<Object> courseid = new ArrayList<Object>();
			for (TbUserinfoclass cours : userinfoclasslist1) {
				courseid.add(cours.getTbCourse().getCourseId());
			}
			if(courseid.size()>0){
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbCourse.class);
				
				dc1.setFetchMode("tbCurriculum", FetchMode.JOIN);
				dc1.setFetchMode("tbBranchschool", FetchMode.JOIN);
				dc1.add(Restrictions.in("courseId", courseid));
				Page.getResult(request, courseManager, dc1, "classlist", 20);
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String addClass(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbUserinfo.class);
			dc.add(Restrictions.eq("userInfoId", login.getIsValid()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbUserinfo> userlist1 = userInfoManager.findByCriteria(dc);
			if(userlist1.size()<=0)
				return "fail";
			 TbUserinfo user1 = userlist1.get(0);
			
			DetachedCriteria dc1sub = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sss = subjectManager.findByCriteria(dc1sub);

			request.setAttribute("subject", sss);
			
			if(adminroot==1){
				
			}
			if(adminroot==3){
				request.setAttribute("schools", user1.getTbSchool());
				request.setAttribute("branchs", user1.getTbBranchschool());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String addClassDo(){
		try{
			TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branchId);
			TbCurriculum kecheng1 = (TbCurriculum)kechengManager.findById(kechengid);
			TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
			//TbSubject subject1 = (TbSubject)subjectManager.findById(subjectId);
			//TbLesson lesson1 = (TbLesson)lessonManager.findById(lesson.getId());
			
			TbCourse course1 = new TbCourse();
			int adminroot = this.loginAdmin().getAdminRoot();
			
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			course1.setTime(nowdate);
			course1.setAlterTime(nowdate);
			course1.setIsValid(1);
			course1.setTbBranchschool(branch1);
			course1.setTbCurriculum(kecheng1);
			course1.setCourseName(course.getCourseName());
			course1.setAddress(course.getAddress());
			course1.setClassNumber(course.getClassNumber());
			course1.setStudyTime(course.getStudyTime());
			
			courseManager.save(course1);
			
			TbUserinfoclass tbuseclass = new TbUserinfoclass();
			tbuseclass.setTime(nowdate);
			tbuseclass.setAlterTime(nowdate);
			tbuseclass.setIsValid(1);
			tbuseclass.setTbBranchschool(branch1);
			tbuseclass.setTbCurriculum(kecheng1);
			tbuseclass.setTbCourse(course1);
			tbuseclass.setTbSchool(school1);
			tbuseclass.setUserRoot(1);
			if(adminroot==3){
				TbUserinfo userinfo1 = (TbUserinfo)userInfoManager.findById(this.loginAdmin().getIsValid());
				tbuseclass.setTbUserinfo(userinfo1);
			}
			else{
				
			}
			userclassManager.save(tbuseclass);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String editClass(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbUserinfo.class);
			dc.add(Restrictions.eq("userInfoId", login.getIsValid()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbUserinfo> userlist1 = userInfoManager.findByCriteria(dc);
			if(userlist1.size()<=0)
				return "fail";
			TbUserinfo user1 = userlist1.get(0);
			
			//DetachedCriteria dc1 = DetachedCriteria.forClass(TbSubject.class);
			/*DetachedCriteria dc1sub = DetachedCriteria.forClass(TbSubjectinbranchschool.class);
			dc1sub.setFetchMode("tbSubject", FetchMode.JOIN);
			dc1sub.createAlias("tbBranchschool", "branch");
			dc1sub.add(Restrictions.eq("branch.branchSchoolId", user1.getTbBranchschool().getBranchSchoolId()));
			List<TbSubjectinbranchschool> branchsub1 = subBranchManager.findByCriteria(dc1sub);
			List<TbSubject> sss = new ArrayList<TbSubject>();
			for (TbSubjectinbranchschool tbsss : branchsub1) {
				sss.add(tbsss.getTbSubject());
			}*/
			//List<TbSubject> subjiectlist1 = subjectManager.findByCriteria(dc1);
			
			DetachedCriteria dcdc = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sss = subjectManager.findByCriteria(dcdc);
			
			request.setAttribute("subject", sss);

			
			if(adminroot==1){
				
			}
			if(adminroot==3){
				request.setAttribute("schools", user1.getTbSchool());
				request.setAttribute("branchs", user1.getTbBranchschool());
			}
			TbCourse course11 = (TbCourse)courseManager.findById(course.getCourseId());
			request.setAttribute("course", course11);
			
			DetachedCriteria dc4 = DetachedCriteria.forClass(TbUserinfoclass.class);
			dc4.createAlias("tbUserinfo", "ui");
			dc4.add(Restrictions.eq("ui.userInfoId", this.loginAdmin().getIsValid()));
			dc4.createAlias("tbCourse", "ci");
			dc4.add(Restrictions.eq("ci.courseId", course.getCourseId()));
			dc4.setFetchMode("tbCurriculum", FetchMode.JOIN);
			List<TbUserinfoclass> userinfoclasslist1 = userclassManager.findByCriteria(dc4);
			if(userinfoclasslist1.size()<=0)
				return SUCCESS;
			
			TbUserinfoclass userclass1 = userinfoclasslist1.get(0);
			request.setAttribute("userclass", userclass1);
			
			int kechengid1 = userclass1.getTbCurriculum().getCourseId();
			
			DetachedCriteria dckc = DetachedCriteria.forClass(TbCurriculum.class);
			dckc.add(Restrictions.eq("courseId", kechengid1));
			dckc.setFetchMode("tbSubject", FetchMode.JOIN);
			dckc.setFetchMode("tbLesson", FetchMode.JOIN);
			List<TbCurriculum> kechenglist1 = kechengManager.findByCriteria(dckc);
			TbCurriculum kecheng11 = kechenglist1.get(0);
			
			TbSubject subs1 = kecheng11.getTbSubject();
			request.setAttribute("subjets", subs1);
			request.setAttribute("lessons", kecheng11.getTbLesson());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String editClassDo(){
		try{
			TbCurriculum kecheng1 = (TbCurriculum)kechengManager.findById(kechengid);
			TbCourse course1 = (TbCourse)courseManager.findById(course.getCourseId());
			int adminroot = this.loginAdmin().getAdminRoot();
			
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			course1.setAlterTime(nowdate);
			course1.setIsValid(course.getIsValid());
			course1.setTbCurriculum(kecheng1);
			course1.setCourseName(course.getCourseName());
			course1.setAddress(course.getAddress());
			course1.setClassNumber(course.getClassNumber());
			course1.setStudyTime(course.getStudyTime());
			
			courseManager.update(course1);
			
			TbUserinfoclass tbuseclass = (TbUserinfoclass)userclassManager.findById(userClass.getUseclaId());
			tbuseclass.setAlterTime(nowdate);
			tbuseclass.setIsValid(course.getIsValid());
			tbuseclass.setTbCurriculum(kecheng1);
			tbuseclass.setTbCourse(course1);
			userclassManager.update(tbuseclass);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String delClassDo(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbCourse course1 = (TbCourse)courseManager.findById(delid[i]);
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfoclass.class);
				dc1.createAlias("tbCourse", "course");
				dc1.add(Restrictions.eq("course.courseId", delid[i]));
				List<TbUserinfoclass> userinfocls1list = userclassManager.findByCriteria(dc1);
				if(userinfocls1list.size()>1)
					request.setAttribute("delTip", 1);
				else
					courseManager.delete(course1);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String addStudenttoClass(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbUserinfo.class);
			dc.add(Restrictions.eq("userInfoId", login.getIsValid()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbUserinfo> userlist1 = userInfoManager.findByCriteria(dc);
			TbUserinfo user1 = userlist1.get(0);
			
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> subjiectlist1 = subjectManager.findByCriteria(dc1);
			request.setAttribute("subject", subjiectlist1);
		
			if(adminroot==1){
				
			}
			if(adminroot==3){
				request.setAttribute("schools", user1.getTbSchool());
				request.setAttribute("branchs", user1.getTbBranchschool());
			}
			DetachedCriteria dcm = DetachedCriteria.forClass(TbUserinfoclass.class);
			dcm.setFetchMode("tbUserinfo", FetchMode.JOIN);
			dcm.createAlias("tbCourse", "bj");
			dcm.add(Restrictions.eq("bj.courseId", course.getCourseId()));
			dcm.add(Restrictions.eq("userRoot", 3));
			List<TbUserinfoclass> userinfoclasslist1 = userclassManager.findByCriteria(dcm);
			
			DetachedCriteria dcn = DetachedCriteria.forClass(TbUserinfo.class);
			dcn.add(Restrictions.eq("userInfoRoot", 3));
			List<TbUserinfo> userlist11 = userInfoManager.findByCriteria(dcn);
			List<TbUserinfo> userinfotemp1 = new ArrayList<TbUserinfo>();
			
			for (TbUserinfo userinfo1 : userlist11) {
				boolean f = true;
				for (TbUserinfoclass tfc : userinfoclasslist1) {
					if(userinfo1.getUserInfoId().equals(tfc.getTbUserinfo().getUserInfoId())){
						f=false;
						break;
					}
				}
				if(f)
					userinfotemp1.add(userinfo1);
			}
			TbCourse coursess = (TbCourse)courseManager.findById(course.getCourseId());
			
			request.setAttribute("courses", coursess);
			request.setAttribute("userinfolist", userinfotemp1);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String addstudentclassDo(){
		try{
			TbUserinfo userinfo12 = (TbUserinfo)userInfoManager.findById(userInfo.getUserInfoId());
			DetachedCriteria dc4 = DetachedCriteria.forClass(TbUserinfoclass.class);
			dc4.createAlias("tbUserinfo", "ui");
			dc4.add(Restrictions.eq("ui.userInfoId", this.loginAdmin().getIsValid()));
			dc4.createAlias("tbCourse", "ci");
			dc4.add(Restrictions.eq("ci.courseId", course.getCourseId()));
			dc4.setFetchMode("tbCurriculum", FetchMode.JOIN);
			List<TbUserinfoclass> userinfoclasslist1 = userclassManager.findByCriteria(dc4);
			TbUserinfoclass userclass1 = userinfoclasslist1.get(0);
			userclass1.setTbUserinfo(userinfo12);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			userclass1.setTime(nowdate);
			userclass1.setAlterTime(nowdate);
			userclass1.setIsValid(course.getIsValid());
			userclass1.setUserRoot(3);
			userclassManager.save(userclass1);
			
			request.setAttribute("userclass", userclass1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//删除老师
	public String delTeacherDo(){
		try{
			for (int i = 0; i < delid.length; i++) {
				DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
				dc.add(Restrictions.eq("userId", delid[i]));
				dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
				List<TbUser> userlist1 = userManager.findByCriteria(dc);
				
				DetachedCriteria dc2 = DetachedCriteria.forClass(TbUserinfoclass.class);
				dc2.add(Restrictions.eq("tbUserinfo", userlist1.get(0).getTbUserinfo()));
				dc2.setFetchMode("tbCourse", FetchMode.JOIN);
				List<TbUserinfoclass> uiclist1 = userclassManager.findByCriteria(dc2);
				
				
				Property p4 = Property.forName("username");
				
				DetachedCriteria dc4 = DetachedCriteria.forClass(Ofuser.class);
				dc4.add(p4.eq(userlist1.get(0).getUserName()));
				List<Ofuser> ofuserlist1 = ofuserManager.findByCriteria(dc4);
				if(uiclist1.size()>0){
					request.setAttribute("delTip", 1);
				}else{
					
					if(ofuserlist1.size()>0)
						ofuserManager.delete(ofuserlist1.get(0));
					
					userInfoManager.delete(userlist1.get(0).getTbUserinfo());
	//				userManager.delete(userlist1.get(0).getTbUserinfo());
				}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//
	public String viewclassstudent(){
		try{
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfoclass.class);
			dc1.createAlias("tbCourse", "ci");
			dc1.add(Restrictions.eq("ci.courseId", course.getCourseId()));
			dc1.add(Restrictions.eq("userRoot", 3));
			dc1.setFetchMode("tbSchool", FetchMode.JOIN);
			dc1.setFetchMode("tbUserinfo", FetchMode.JOIN);
			dc1.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc1.setFetchMode("tbCourse", FetchMode.JOIN);
			dc1.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc1.setFetchMode("tbCurriculum", FetchMode.JOIN);
			Page.getResult(request, userclassManager, dc1, "studentlist", 20);
			request.setAttribute("courseid", course.getCourseId());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String importStudenttoClass(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbUserinfo.class);
			dc.add(Restrictions.eq("userInfoId", login.getIsValid()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbUserinfo> userlist1 = userInfoManager.findByCriteria(dc);
			TbUserinfo user1 = userlist1.get(0);
			
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> subjiectlist1 = subjectManager.findByCriteria(dc1);
			request.setAttribute("subject", subjiectlist1);
		
			if(adminroot==1){
				
			}
			if(adminroot==3){
				request.setAttribute("schools", user1.getTbSchool());
				request.setAttribute("branchs", user1.getTbBranchschool());
			}
			TbCourse coursess = (TbCourse)courseManager.findById(course.getCourseId());
			
			request.setAttribute("courses", coursess);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String showBanInfo(){
		try{
			TbAdmin onlineadmin = this.loginAdmin();
			int adminroot = onlineadmin.getAdminRoot();
			
			
			if(adminroot==1){
				//根据登录信息获取总校
				DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
				dc.add(Restrictions.eq("adminId", onlineadmin.getAdminId()));
				dc.setFetchMode("tbSchool", FetchMode.JOIN);
				dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
				List<TbAdmin> adminlist1 = adminManager.findByCriteria(dc);
				TbAdmin admin1 = null;
				if(adminlist1.size()>0)
					admin1 = adminlist1.get(0);
				else 
					return "fail";
				
				//根据总校获取分校
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbBranchschool.class);
				dc1.createAlias("tbSchool", "school");
				dc1.add(Restrictions.eq("school.schoolId", onlineadmin.getTbSchool().getSchoolId()));
				List<TbBranchschool> branchlist1 = branchManager.findByCriteria(dc1);
				
				TbBranchschool branch1 = null;
				if(branchlist1.size()>0)
					branch1 = branchlist1.get(0);
				else 
					return SUCCESS;
				
				List<Object> branchid = new ArrayList<Object>();
				for (TbBranchschool branch11 : branchlist1) {
					branchid.add(branch11.getBranchSchoolId());
				}
				
				//根据分校获取班
				DetachedCriteria dc2 = DetachedCriteria.forClass(TbCurriculum.class);
				dc2.addOrder(Property.forName("courseId").desc());
				dc2.createAlias("tbBranchschool", "branch");
				dc2.add(Restrictions.in("branch.branchSchoolId", branchid));
				dc2.setFetchMode("tbLesson", FetchMode.JOIN);
				
				if(courseName!=null && !courseName.equals("")){
					//courseName =new String(courseName.getBytes("ISO-8859-1"),"utf-8");
					request.setCharacterEncoding("utf-8");
					dc2.add(Restrictions.like("courseName", courseName,MatchMode.ANYWHERE));
					request.setAttribute("courseName", courseName);
				}
				Page.getResult(request, kechengManager, dc2, "banlist", 20);
				
				
			}else {
				return "fail";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String addBanInfo(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminId", login.getAdminId()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbAdmin> userlist1 = adminManager.findByCriteria(dc);
			if(userlist1.size()<=0)
				return "fail";
			TbAdmin user1 = userlist1.get(0);
			 request.setAttribute("schools", user1.getTbSchool());
			 request.setAttribute("branchs", user1.getTbBranchschool());
			
			DetachedCriteria dc1sub = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sss = subjectManager.findByCriteria(dc1sub);

			request.setAttribute("subject", sss);
			
//			DetachedCriteria dcbranch = DetachedCriteria.forClass(TbBranchschool.class);
//			dcbranch.createAlias("tbSchool", "school");
//			dcbranch.add(Restrictions.eq("school.schoolId", user1.getTbSchool().getSchoolId()));
//			List<TbBranchschool> branchlist1 = branchManager.findByCriteria(dcbranch);
			List<TbBranchschool> branchlist1 = new FindUserDao().selectbranch(user1.getTbSchool());
			request.setAttribute("branchlist", branchlist1);
			
			DetachedCriteria dcgra = DetachedCriteria.forClass(TbGrade.class);
			//dcgra.createAlias("tbSchool", "school");
			//dcgra.add(Restrictions.eq("school.schoolId", user1.getTbSchool().getSchoolId()));
			List<TbGrade> gradelist1 = gradeManager.findByCriteria(dcgra);
			request.setAttribute("gradelist", gradelist1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String editBanInfoDo(){
		try{
			TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
			//TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branchId);
			TbSubject subject1 = (TbSubject)subjectManager.findById(subjectId);
			TbLesson lesson1 = (TbLesson)lessonManager.findById(lesson.getId());
			//TbGrade grade1 = (TbGrade)gradeManager.findById(gradeId);
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbBranchschool.class);
			dc.setFetchMode("tbTown", FetchMode.JOIN);
			dc.add(Restrictions.eq("branchSchoolId", branch.getBranchSchoolId()));
			List<TbBranchschool> branchlist1 = branchManager.findByCriteria(dc);
			if(branchlist1.size()==0)
				return "fail";
			TbBranchschool branch1 = branchlist1.get(0);
			
			TbCurriculum kech = (TbCurriculum)kechengManager.findById(kecheng.getCourseId());
			//200KB
			  if(img!=null){
				if(img.length()>IMAGE_SIZE)
					return "fail";
				imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
				kecheng.setCourseUrl(imgFileName);
			  }else {
				  kecheng.setCourseUrl(kech.getCourseUrl());
			  }
			
			kecheng.setTbTown(branch1.getTbTown());
			kecheng.setTbSchool(school1);
			kecheng.setTbBranchschool(branch1);
			kecheng.setTbSubject(subject1);
			kecheng.setTbLesson(lesson1);
			//kecheng.setTbGrade(grade1);
			if(grades!=null){
				if(grades.length>0)
					kecheng.setGrades(Separator.combiStr(grades, Separator.SYMBOL));
			}
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			kecheng.setTime(kech.getTime());
			kecheng.setAlterTime(nowdate);
			kecheng.setDefineSort(kech.getDefineSort());
			kecheng.setDefineSort2(kech.getDefineSort2());
			kechengManager.update(kecheng);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String editBanInfo(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminId", login.getAdminId()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbAdmin> userlist1 = adminManager.findByCriteria(dc);
			if(userlist1.size()<=0)
				return "fail";
			TbAdmin user1 = userlist1.get(0);
			 request.setAttribute("schools", user1.getTbSchool());
			 request.setAttribute("branchs", user1.getTbBranchschool());
			
			DetachedCriteria dc1sub = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sss = subjectManager.findByCriteria(dc1sub);

			request.setAttribute("subject", sss);
			
			DetachedCriteria dcbranch = DetachedCriteria.forClass(TbBranchschool.class);
			dcbranch.createAlias("tbSchool", "school");
			dcbranch.add(Restrictions.eq("school.schoolId", user1.getTbSchool().getSchoolId()));
			List<TbBranchschool> branchlist1 = branchManager.findByCriteria(dcbranch);
			request.setAttribute("branchlist", branchlist1);
			
			DetachedCriteria dcgra = DetachedCriteria.forClass(TbGrade.class);
			//dcgra.createAlias("tbSchool", "school");
			//dcgra.add(Restrictions.eq("school.schoolId", user1.getTbSchool().getSchoolId()));
			List<TbGrade> gradelist1 = gradeManager.findByCriteria(dcgra);
			request.setAttribute("gradelist", gradelist1);
			
			//获取当前id详情
			DetachedCriteria dccu = DetachedCriteria.forClass(TbCurriculum.class);
			dccu.add(Restrictions.eq("courseId", kecheng.getCourseId()));
			dccu.setFetchMode("tbLesson", FetchMode.JOIN);
			dccu.setFetchMode("tbSubject", FetchMode.JOIN);
			dccu.setFetchMode("tbGrade", FetchMode.JOIN);
			dccu.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbCurriculum> kechenglist1 = kechengManager.findByCriteria(dccu);
			/*if(kechenglist1.size()==0);
				return "fail";*/
			
			TbCurriculum kecheng1 = kechenglist1.get(0);
			request.setAttribute("baninfo", kecheng1);
			if(kecheng1.getGrades()!=null)
				request.setAttribute("mygrades", Separator.combiList(kecheng1.getGrades(), Separator.SYMBOL));
			else
				request.setAttribute("mygrades", null);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String addBanInfoDo(){
		try{
			TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
			//TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branchId);
			TbSubject subject1 = (TbSubject)subjectManager.findById(subjectId);
			TbLesson lesson1 = (TbLesson)lessonManager.findById(lesson.getId());
			//TbGrade grade1 = (TbGrade)gradeManager.findById(gradeId);
			
//			DetachedCriteria dc = DetachedCriteria.forClass(TbBranchschool.class);
//			dc.setFetchMode("tbTown", FetchMode.JOIN);
//			dc.add(Restrictions.eq("branchSchoolId", branch.getBranchSchoolId()));
//			List<TbBranchschool> branchlist1 = branchManager.findByCriteria(dc);
//			if(branchlist1.size()==0)
//				return "fail";
//			TbBranchschool branch1 = branchlist1.get(0);
			//200KB
		  if(img!=null){
			if(img.length()>IMAGE_SIZE)
				return "fail";
			imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
			kecheng.setCourseUrl(imgFileName);
		  }else{
			  kecheng.setCourseUrl("root2.png");
		  }
			kecheng.setTbSchool(school1);
			kecheng.setTbSubject(subject1);
			kecheng.setTbLesson(lesson1);
			//kecheng.setTbGrade(grade1);
			if(grades!=null)
				if(grades.length>0)
					kecheng.setGrades(Separator.combiStr(grades, Separator.SYMBOL));
			else 
				kecheng.setGrades(null);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			kecheng.setTime(nowdate);
			kecheng.setAlterTime(nowdate);
			kecheng.setIsValid(1);
			kecheng.setDefineSort(0L);
			kecheng.setDefineSort2(0L);
			
			for (int i = 0; i < sbranchId.length; i++) {
				TbBranchschool sbh = new FindUserDao().findByBranchschool(sbranchId[i]);
				kecheng.setTbBranchschool(sbh);
				kecheng.setTbTown(sbh.getTbTown());
				kechengManager.save(kecheng);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String delBanInfoDo(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbCurriculum ban1 = (TbCurriculum)kechengManager.findById(delid[i]);
				DetachedCriteria dc = DetachedCriteria.forClass(TbUserinfoclass.class);
				dc.add(Restrictions.eq("tbCurriculum", ban1));
				List<TbUserinfoclass> userclasslist1 = userclassManager.findByCriteria(dc);
				if(userclasslist1.size()>0)
					request.setAttribute("delTip", 1);
				else
					kechengManager.delete(ban1);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String addLesson(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminId", login.getAdminId()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbAdmin> userlist1 = adminManager.findByCriteria(dc);
			if(userlist1.size()<=0)
				return "fail";
			TbAdmin user1 = userlist1.get(0);
			 request.setAttribute("schools", user1.getTbSchool());
			 //request.setAttribute("branchs", user1.getTbBranchschool());
			
			DetachedCriteria dc1sub = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sss = subjectManager.findByCriteria(dc1sub);

			request.setAttribute("subject", sss);
			
			DetachedCriteria dcbranch = DetachedCriteria.forClass(TbBranchschool.class);
			dcbranch.createAlias("tbSchool", "school");
			dcbranch.add(Restrictions.eq("school.schoolId", user1.getTbSchool().getSchoolId()));
			List<TbBranchschool> branchlist1 = branchManager.findByCriteria(dcbranch);
			request.setAttribute("branchlist", branchlist1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String addLessonDo(){
		try{
			TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
			//TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branchId);
			TbSubject subject1 = (TbSubject)subjectManager.findById(subjectId);
			
			lesson.setTbSchool(school1);
			//lesson.setTbBranchschool(branch1);
			lesson.setTbSubject(subject1);
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			lesson.setTime(nowdate);
			lesson.setAlterTime(nowdate);
			lesson.setIsValid(1);
			lesson.setDefineSort(0l);
			lesson.setDefineSort2(0l);
			
			lessonManager.save(lesson);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String showlesson(){
		try{
			TbAdmin onlineadmin = this.loginAdmin();
			int adminroot = onlineadmin.getAdminRoot();
			
			
			if(adminroot==1){
				//根据登录信息获取总校
				DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
				dc.add(Restrictions.eq("adminId", onlineadmin.getAdminId()));
				dc.setFetchMode("tbSchool", FetchMode.JOIN);
				dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
				List<TbAdmin> adminlist1 = adminManager.findByCriteria(dc);
				TbAdmin admin1 = null;
				if(adminlist1.size()>0)
					admin1 = adminlist1.get(0);
				else 
					return "fail";
				
				//根据总校获取课程
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbLesson.class);
				
				dc1.addOrder(Order.desc("id"));
				dc1.createAlias("tbSchool", "school");
				dc1.add(Restrictions.eq("school.schoolId", admin1.getTbSchool().getSchoolId()));
				dc1.setFetchMode("tbSchool", FetchMode.JOIN);
				dc1.setFetchMode("tbSubject", FetchMode.JOIN);
				dc1.setFetchMode("tbBranchschool", FetchMode.JOIN);
				if(lessonName!=null && !lessonName.equals("")){
					lessonName =new String(lessonName.getBytes("ISO-8859-1"),"utf-8");
					dc1.add(Restrictions.like("lessonName", lessonName,MatchMode.ANYWHERE));
					request.setAttribute("lessonName", lessonName);
				}
				Page.getResult(request, lessonManager, dc1, "lessonlist", 20);
				
			}else {
				return "fail";
			}	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	public String editLesson(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminId", login.getAdminId()));
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbAdmin> userlist1 = adminManager.findByCriteria(dc);
			if(userlist1.size()<=0)
				return "fail";
			TbAdmin user1 = userlist1.get(0);
			 request.setAttribute("schools", user1.getTbSchool());
			 request.setAttribute("branchs", user1.getTbBranchschool());
			
			DetachedCriteria dc1sub = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sss = subjectManager.findByCriteria(dc1sub);

			request.setAttribute("subject", sss);
			
			DetachedCriteria dcbranch = DetachedCriteria.forClass(TbBranchschool.class);
			dcbranch.createAlias("tbSchool", "school");
			dcbranch.add(Restrictions.eq("school.schoolId", user1.getTbSchool().getSchoolId()));
			List<TbBranchschool> branchlist1 = branchManager.findByCriteria(dcbranch);
			//request.setAttribute("branchlist", branchlist1);
			
			//当前编辑信息
			DetachedCriteria dcl1 = DetachedCriteria.forClass(TbLesson.class);
			dcl1.add(Restrictions.eq("id", lesson.getId()));
			dcl1.setFetchMode("tbSubject", FetchMode.JOIN);
			dcl1.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbLesson> lessonlist1 = lessonManager.findByCriteria(dcl1);
			if(lessonlist1.size()<=0)
				return "fail";
			
			request.setAttribute("lesson", lessonlist1.get(0));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String editLessonDo(){
		try{
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbLesson.class);
			//TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branchId);
			TbSubject subject1 = (TbSubject)subjectManager.findById(subjectId);
			dc1.add(Restrictions.eq("id", lesson.getId()));
			List<TbLesson> lessonlist1 = lessonManager.findByCriteria(dc1);
			
			if(lessonlist1.size()<=0)
				return "fail";
			
			TbLesson lesson1 = lessonlist1.get(0);
			//lesson1.setTbBranchschool(branch1);
			lesson1.setTbSubject(subject1);
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			lesson1.setAlterTime(nowdate);
			lesson1.setIsValid(lesson.getIsValid());
			
			lesson1.setLessonName(lesson.getLessonName());
			lesson1.setDefineSort(lesson.getDefineSort());
			lesson1.setDefineSort2(lesson1.getDefineSort2());
			lesson.setDefineSort(lesson1.getDefineSort());
			lesson.setDefineSort2(lesson1.getDefineSort2());
			
			lessonManager.update(lesson1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String delLesson(){
		try{
			
			for (int i = 0; i < delid.length; i++) {
				TbLesson lesson1 = (TbLesson)lessonManager.findById(delid[i]);
				DetachedCriteria dc = DetachedCriteria.forClass(TbCurriculum.class);
				dc.add(Restrictions.eq("tbLesson", lesson1));
				List<TbCurriculum> courselist1 = kechengManager.findByCriteria(dc);
				if(courselist1.size()>0)
					request.setAttribute("delTip", 1);
				else
					lessonManager.delete(lesson1);
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 返回指定班级的学生家长
	 * 
	 * **/
	public String viewstuparent(){
		try{
			//获取学生id
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfoclass.class);
			dc1.createAlias("tbCourse", "course");
			dc1.add(Restrictions.eq("course.courseId", course.getCourseId()));
			dc1.setFetchMode("tbUserinfo", FetchMode.JOIN);
			List<TbUserinfoclass> userclasslist1 = userclassManager.findByCriteria(dc1);
			if(userclasslist1.size()==1)
				return SUCCESS;
			List<Object> stuid = new ArrayList<Object>();
			for (TbUserinfoclass ucls : userclasslist1) {
				stuid.add(ucls.getTbUserinfo().getUserInfoId());
			}
			//获取家长id
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbRelation.class);
			dc2.createAlias("tbUserinfoByUserInfoId", "stu");
			dc2.add(Restrictions.in("stu.userInfoId", stuid));
			dc2.setFetchMode("tbUserinfoByTbUserInfoId", FetchMode.JOIN);
			List<TbRelation> relationlist1 = relationManager.findByCriteria(dc2);
			
			List<Object> parentid = new ArrayList<Object>();
			for (TbRelation rel1 : relationlist1) {
				parentid.add(rel1.getTbUserinfoByTbUserInfoId().getUserInfoId());
			}
			
			//查询家长信息
			DetachedCriteria dc3 = DetachedCriteria.forClass(TbUser.class);
			dc3.createAlias("tbUserinfo", "parent");
			dc3.add(Restrictions.in("parent.userInfoId", parentid));
			
			request.setAttribute("courseid", course.getCourseId());
			Page.getResult(request, userManager, dc3, "parentlist", 20);

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String editStudentInfo(){
		
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbUserinfo.class);
			dc2.add(Restrictions.eq("userInfoId", login.getIsValid()));
			dc2.setFetchMode("tbSchool", FetchMode.JOIN);
			dc2.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbUserinfo> userlist11 = userInfoManager.findByCriteria(dc2);
			if(userlist11.size()<=0)
				return "fail";
			TbUserinfo user11 = userlist11.get(0);
			 request.setAttribute("schools", user11.getTbSchool());
			 request.setAttribute("branchs", user11.getTbBranchschool());
			//查询学生信息
			DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
			dc.createAlias("tbUserinfo", "ui");
			dc.add(Restrictions.eq("ui.userInfoId", userInfo.getUserInfoId()));
			List<TbUser> userlist1 = userManager.findByCriteria(dc);
			TbUser user1 = userlist1.get(0);
			request.setAttribute("info", user1);
			
			//查询学生班级
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfoclass.class);
			dc1.createAlias("tbUserinfo", "ui");
			dc1.add(Restrictions.eq("ui.userInfoId", userInfo.getUserInfoId()));
			dc1.setFetchMode("tbCourse", FetchMode.JOIN);
			List<TbUserinfoclass> userclslist1 = userclassManager.findByCriteria(dc1);
			TbUserinfoclass usercls1 = userclslist1.get(0);
			request.setAttribute("cls", usercls1);
			 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String editStudentInfoDo(){
		try{
			TbUser user1 = (TbUser)userManager.findById(user.getUserId());

			TbUserinfo userinfo1 = (TbUserinfo)userInfoManager.findById(userInfo.getUserInfoId());
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			user1.setAlterTime(nowdate);
			user1.setIsValid(userInfo.getIsValid());
			userinfo1.setAlterTime(nowdate);
			userinfo1.setIsValid(userInfo.getIsValid());
			userinfo1.setUserInfoName(userInfo.getUserInfoName());
			userinfo1.setUserInfoSex(userInfo.getUserInfoSex());
			userinfo1.setUserInfoBirthday(userInfo.getUserInfoBirthday());
			userinfo1.setUserinfoQq(userInfo.getUserinfoQq());
			userinfo1.setUserInfoPhone(userInfo.getUserInfoPhone());
			userinfo1.setStudentSchool(userInfo.getStudentSchool());
			userinfo1.setStudentClass(userInfo.getStudentClass());
			
			userManager.update(user1);
			userInfoManager.update(userinfo1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String editParentInfo(){
		try{
			TbAdmin login = this.loginAdmin();
			int adminroot = login.getAdminRoot();
			
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbUserinfo.class);
			dc2.add(Restrictions.eq("userInfoId", login.getIsValid()));
			dc2.setFetchMode("tbSchool", FetchMode.JOIN);
			dc2.setFetchMode("tbBranchschool", FetchMode.JOIN);
			List<TbUserinfo> userlist11 = userInfoManager.findByCriteria(dc2);
			if(userlist11.size()<=0)
				return "fail";
			TbUserinfo user11 = userlist11.get(0);
			 request.setAttribute("schools", user11.getTbSchool());
			 request.setAttribute("branchs", user11.getTbBranchschool());
			//查询家长信息
			DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
			dc.add(Restrictions.eq("userId", user.getUserId()));
			dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
			List<TbUser> userlist1 = userManager.findByCriteria(dc);
			TbUser user1 = userlist1.get(0);
			request.setAttribute("parent", user1);
			request.setAttribute("courseid", course.getCourseId());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String editParentInfoDo(){
		try{
			TbUser user1 = (TbUser)userManager.findById(user.getUserId());

			TbUserinfo userinfo1 = (TbUserinfo)userInfoManager.findById(userInfo.getUserInfoId());
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			user1.setAlterTime(nowdate);
			user1.setIsValid(userInfo.getIsValid());
			userinfo1.setAlterTime(nowdate);
			userinfo1.setIsValid(userInfo.getIsValid());
			userinfo1.setUserInfoName(userInfo.getUserInfoName());
			userinfo1.setUserInfoSex(userInfo.getUserInfoSex());
			userinfo1.setUserInfoBirthday(userInfo.getUserInfoBirthday());
			userinfo1.setUserInfoEmail(userInfo.getUserInfoEmail());
			userinfo1.setUserInfoPhone(userInfo.getUserInfoPhone());
			userManager.update(user1);
			userInfoManager.update(userinfo1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String viewRelation(){
		try{
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbRelation.class);
			dc1.createAlias("tbUserinfoByUserInfoId", "ui");
			dc1.add(Restrictions.eq("ui.userInfoId", userInfo.getUserInfoId()));
			dc1.setFetchMode("tbUserinfoByTbUserInfoId", FetchMode.JOIN);
			
			List<TbRelation> relationlist1 = relationManager.findByCriteria(dc1);
			TbRelation  relation1 = relationlist1.get(0);
			//学生
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbUser.class);
			dc2.createAlias("tbUserinfo", "ui");
			dc2.add(Restrictions.eq("ui.userInfoId", relation1.getTbUserinfoByUserInfoId().getUserInfoId()));
			List<TbUser> user1list = userManager.findByCriteria(dc2);
			TbUser stu = user1list.get(0);
			//家长
			DetachedCriteria dc3 = DetachedCriteria.forClass(TbUser.class);
			dc3.createAlias("tbUserinfo", "ui");
			dc3.add(Restrictions.eq("ui.userInfoId", relation1.getTbUserinfoByTbUserInfoId().getUserInfoId()));
			List<TbUser> user2list = userManager.findByCriteria(dc3);
			TbUser parent = user2list.get(0);
			
			request.setAttribute("parent", parent);
			request.setAttribute("stu", stu);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String delstuparInfoDo(){
		try{
			for (int i = 0; i < delid.length; i++) {
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfoclass.class);
				dc1.add(Restrictions.eq("useclaId", delid[i]));
				dc1.setFetchMode("tbUserinfo", FetchMode.JOIN);
				List<TbUserinfoclass> uiclist1 = userclassManager.findByCriteria(dc1);
				TbUserinfoclass uic1 = uiclist1.get(0);
				
				 //DetachedCriteria dc2 = DetachedCriteria.forClass(TbUser.class);
				 //dc2.add(Restrictions.eq("tbUserinfo", uic1.getTbUserinfo()));
				 //List<TbUser> ulist1 = userManager.findByCriteria(dc2);
				 //TbUser u1 = ulist1.get(0);
				 //删除关系
				 //DetachedCriteria dc = DetachedCriteria.forClass(TbRelation.class);
				 //dc.setFetchMode("tbUserinfoByTbUserInfoId", FetchMode.JOIN);
				 //dc.setFetchMode("tbUserinfoByUserInfoId", FetchMode.JOIN);
				 //dc.add(Restrictions.eq("tbUserinfoByUserInfoId", u1.getTbUserinfo()));
				 //List<TbRelation> relationlist1 = relationManager.findByCriteria(dc);
				// TbRelation relation1 = relationlist1.get(0);
				 
				//删除ofuser表的家长
				 //DetachedCriteria dc5 = DetachedCriteria.forClass(TbUser.class);
				 //dc5.add(Restrictions.eq("tbUserinfo", relation1.getTbUserinfoByTbUserInfoId()));
				 //List<TbUser> userlist2 = userManager.findByCriteria(dc5);
				 //TbUser user2 = userlist2.get(0);
				 
				 //DetachedCriteria dc4 = DetachedCriteria.forClass(Ofuser.class);
				 //dc4.add(Restrictions.eq("username", user2.getUserName()));
				 //List<Ofuser> olist2 = ofuserManager.findByCriteria(dc4);
				 //Ofuser o2 = olist2.get(0);
				 
				 
				//删除ofuser表的学生
				 //DetachedCriteria dc3 = DetachedCriteria.forClass(Ofuser.class);
				 //dc3.add(Restrictions.eq("username", u1.getUserName()));
				 //dc3.add(Restrictions.eq("plainPassword", u1.getUserPassword()));
				 //List<Ofuser> olist1= ofuserManager.findByCriteria(dc3);

				 //TbUserinfo stu1 = (TbUserinfo)userInfoManager.findById(uic1.getTbUserinfo().getUserInfoId());
				 //TbUserinfo parent1 = (TbUserinfo)userInfoManager.findById(relation1.getTbUserinfoByTbUserInfoId().getUserInfoId());
				 
				 //Ofuser o1 = olist1.get(0);

				 //relationManager.delete(relation1);
				 
				// userInfoManager.delete(stu1);
				 
				// userInfoManager.delete(parent1);
				 
				 //ofuserManager.delete(o1);
				 
				 //ofuserManager.delete(o2);
				 
				 userclassManager.delete(uic1);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String showlessonrank(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbLesson.class);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			//dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			
			if(subject!=null){
				if(subject.getSubjectId()!=null&&subject.getSubjectId()!=0){
					dc.createAlias("tbSubject", "subject");
					dc.add(Restrictions.eq("subject.subjectId", subject.getSubjectId()));
					request.setAttribute("subjectId", subject.getSubjectId());
				}
			}
			dc.addOrder(Order.desc("defineSort"));
			dc.addOrder(Order.desc("defineSort2"));
			dc.setFetchMode("tbSubject", FetchMode.JOIN);
			Page.getResult(request, lessonManager, dc, "lessonranklist", 20);
			
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sublist1 = subjectManager.findByCriteria(dc1);
			request.setAttribute("subjects", sublist1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editlessonrank(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbLesson.class);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			//dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc.setFetchMode("tbSubject", FetchMode.JOIN);
			dc.add(Restrictions.eq("id", lesson.getId()));
			List<TbLesson> lessonlist1 = lessonManager.findByCriteria(dc);
			TbLesson lesson1 = lessonlist1.get(0);
			
			request.setAttribute("lessonrank", lesson1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editlessonrankDo(){
		try{
			TbLesson lesson1 = (TbLesson)lessonManager.findById(lesson.getId());
			lesson1.setDefineSort(lesson.getDefineSort());
			if(lesson.getDefineSort2()!=null){
				lesson1.setDefineSort2(lesson.getDefineSort2());
			}
			lessonManager.update(lesson1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String showbanrank(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbCurriculum.class);
			if(subject!=null){
				if(subject.getSubjectId()!=null&&subject.getSubjectId()!=0){
					dc.createAlias("tbSubject", "subjects");
					dc.add(Restrictions.eq("subjects.subjectId", subject.getSubjectId()));
					request.setAttribute("subjectId", subject.getSubjectId());
				}
			}
			if(lesson!=null){
				if(lesson.getId()!=null&&lesson.getId()!=0){
					dc.createAlias("tbLesson", "lesson");
					dc.add(Restrictions.eq("lesson.id", lesson.getId()));
					request.setAttribute("lessonid", lesson.getId());
				}
			}
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc.setFetchMode("tbSubject", FetchMode.JOIN);
			dc.setFetchMode("tbLesson", FetchMode.JOIN);
			dc.addOrder(Order.desc("defineSort"));
			dc.addOrder(Order.desc("defineSort2"));
			Page.getResult(request, kechengManager, dc, "banranklist", 20);
			
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> sublist1 = subjectManager.findByCriteria(dc1);
			request.setAttribute("subjects", sublist1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editbanrank(){
		try{

			DetachedCriteria dc = DetachedCriteria.forClass(TbCurriculum.class);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc.setFetchMode("tbSubject", FetchMode.JOIN);
			dc.setFetchMode("tbLesson", FetchMode.JOIN);
			dc.add(Restrictions.eq("id", course.getCourseId()));
			List<TbCurriculum> banlist1 = kechengManager.findByCriteria(dc);
			TbCurriculum ban1 = banlist1.get(0);
			
			request.setAttribute("banrank", ban1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editbanrankDo(){
		try{
			TbCurriculum ban1 = (TbCurriculum)kechengManager.findById(kecheng.getCourseId());
			ban1.setDefineSort(kecheng.getDefineSort());
			if(kecheng.getDefineSort2()!=null){
				ban1.setDefineSort2(kecheng.getDefineSort2());
			}
			kechengManager.update(ban1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
