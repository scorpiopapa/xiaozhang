package com.qiYang.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import com.pro.actions.BaseAction;
import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.dao.FindUserDao;
import com.qiYang.dao.UpdateDao;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbClassnotice;
import com.qiYang.model.TbComplain;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbReview;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchoolnotice;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;
import com.qiYang.util.JpushThread;

public class SelectAction extends BaseAction{

	private FindUserDao findDao;
	private UpdateDao updatedao;
	private List listSubject;
	private List listCourse;
	private List listUserinfo;
	private List listschool;
	private List listbranchschool;
	private List listtown;
	private List listgrade;
	private List listuser;
	private List listrelation;
	private List listschoolnotice;
	private List listclassnotice;
	private List listcomplain;
	private List listinfoclass;
	private List listcity;
	private List listreview;
	private List listtbcourse;
	private List listteacherinfo;
	private List listsubjectinbranchschool;
	private List listlesson;
	private List listuserinfoclass;
	private TbTown tbtown;
	private Integer townId;
	private TbSubject tbsubject;
	private TbSchool tbschool;
	private String str;
	private TbCurriculum tbCourse;
	private Integer userInfoId;
	private TbBranchschool tbbranchschool;
	private TbUserinfo tbUserinfo;
	private TbUser user;
	private Integer schoolId;
	private Integer courseId;
	private Integer branchSchoolId;
	private TbUserinfo tbuser;
	private String userName;
	private String userPassword;
	private String imgFileName;
	private File img;
	private List complaindetails;
	private TbSchoolnotice tbschoolnotice;
	private Integer schoolNoticeId;
	private TbClassnotice tbclassnotice;
	private TbGrade tbgrade;
	private TbUserinfoclass infoclass;
	private Integer subjectId;
	private TbCity tbcity;
	private TbCurriculum tbcurriculum;
	private Integer curriculumId;
	private Integer branchschoolId;
	private String info;
	private Integer gradeId;
	private TbComplain tbcomplain;
	private TbLesson tblesson;
	private Integer id;
	private List<TbUser> userlist1;
	private List<TbUserinfo> listUserinfo1;
	private Integer tips;
	
	public Integer getTips() {
		return tips;
	}
	public void setTips(Integer tips) {
		this.tips = tips;
	}
	//  城市下面的城区
	public void selectcity(){
		listtown = findDao.selecttown(tbcity);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbCity","isValid","time",
				 "alterTime","tbCurriculums","tbSchools","tbBranchschools"});
		json = JSONArray.fromObject(listtown,config);
		String str = json.toString();
		print(str);
	}
	// 城区下面的总校
	public void selectschool(){
		listschool = findDao.selectschooltown(tbtown);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbTown","schoolMinName","schoolUrl",
				 "schoolAddress","schoolPhone","schoolCourse","studentNum","linemanPhone","linemanEmail"
				 ,"linemanQq","linemanName","rectorEmail","rectorPhone","rectorQq","rectorName","subSchoolNum","schoolCertificate","schoolUnit"
				 ,"schoolLogo","schoolBusWay","schoolStopLocation","schoolPicture","schoolIntroduce","schoolApplyDate","schoolLatitude","schoolLongitude","isValid","time"
				 ,"alterTime","tbUserinfoclasses","tbAdmins","tbBranchschools","tbUserinfos","tbGrades","tbAttendances","tbClassnotices","tbPushs","tbComplains","tbSchoolnotices"});
		json = JSONArray.fromObject(listschool,config);
		String str = json.toString();
		print(str);
	}
	// 城区下面的分校
	public void selectb(){
		listbranchschool = findDao.selectb(tbtown);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbTown","tbSchool","braschMinName",
				 "braschAddress","braschPhone","braschBusWay","braschStopLocation",
				 	"braschPictureUrl","braschLongitude","braschLatitude","braschIntroduce","isValid","time","alterTime",
				 		"tbAdmins","tbCurriculums","tbSubjectinbranchschools","tbTests","tbPushs","tbBillses","tbClassnotices",
				 			"tbComplains","tbNotices","tbCourses","tbUserinfoclasses","tbAttendances","tbChats","tbSchoolnotices","tbUserphotos",
				 				"tbViptimes","tbCharges","tbGrades","tbUserinfos"});
		json = JSONArray.fromObject(listbranchschool,config);
		String str = json.toString();
		print(str);
	}
	// 分校下面的年段
	public void selectg(){
		listgrade = findDao.selectgrade(tbbranchschool);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbSchool","tbBranchschool","isValid",
				 "time","alterTime","tbCurriculums"});
		json = JSONArray.fromObject(listgrade,config);
		String str = json.toString();
		print(str);
	}
	// 总校下的分校
	public void select(){
		listbranchschool = findDao.selectbranch(tbschool);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbTown","tbSchool","braschMinName",
				 "braschAddress","braschPhone","braschBusWay","braschStopLocation",
				 	"braschPictureUrl","braschLongitude","braschLatitude","braschIntroduce","isValid","time","alterTime",
				 		"tbAdmins","tbCurriculums","tbSubjectinbranchschools","tbTests","tbPushs","tbBillses","tbClassnotices",
				 			"tbComplains","tbNotices","tbCourses","tbUserinfoclasses","tbAttendances","tbChats","tbSchoolnotices","tbUserphotos",
				 			  "tbViptimes","tbCharges","tbGrades","tbUserinfos"});
		json = JSONArray.fromObject(listbranchschool,config);
		String str = json.toString();
		print(str);
	}
	// 分校下面的课程
	public void listCourse(){
		tbCourse = findDao.findByCurriculum(courseId);
		listCourse = findDao.listc(tbbranchschool);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbTown","tbSubject","tbGrade","tbBranchschool",
				 "courseScore","isGood","courseUrl","courseIntroduce","courseGoodness","enterNeed",
				 	"isValid","studyGoal","courseBook","startTerm","endTerm","studyTime","totalStudyTime"
				 	,"studyCosts","textbookCost","direction","phone","time","alterTime","tbUserinfoclasses","tbCourses"});
		json = JSONArray.fromObject(listCourse,config);
		
		String str = json.toString();
		print(str);
	}
	// 分校下面的科目
	public void listCurriculum(){
		listsubjectinbranchschool = findDao.listsubjectinbranchschool(tbbranchschool);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbBranchschool","isValid","time","alterTime"});
		json = JSONArray.fromObject(listsubjectinbranchschool,config);
		String str = json.toString();
		print(str);
	}
	// 课程下面的班级
	public void selectCoures(){
	
		listtbcourse = findDao.listcoures(tbcurriculum);
		JSONArray json = new JSONArray();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"tbCurriculum","tbBranchschool","isValid",
				 "time","alterTime","tbUserinfoclasses","tbComplains","tbTests","tbReviews",
				 	"tbAttendances","tbClassnotices"});
		json = JSONArray.fromObject(listtbcourse,config);
		String str = json.toString();
		print(str);
	}
	// 查看总校下的所有分校校长信息
	public String selectprincipal(){
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		 tbschool = findDao.findBySchool(tbadmin.getTbSchool().getSchoolId());
		listUserinfo1 = findDao.listuserinfo(tbschool);
		if(listUserinfo1.size()!=0){
			List<Object> user1 = new ArrayList<Object>();
			for(TbUserinfo userinfo1:listUserinfo1){
				user1.add(userinfo1);
			}
			userlist1 = findDao.listuser(user1);
		}
		request.setAttribute("tips", tips);
		return "selectprincipal";
	}
	// 去添加分校校长
	public String toaddprincipal(){
		 TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		 tbschool = findDao.findBySchool(tbadmin.getTbSchool().getSchoolId());
		 listbranchschool = findDao.selectbranch(tbschool);
		 return "toaddprincipal";
	}
	
	// 添加分校校长
	public String addprincipal(){
		if(null == updatedao.addPrincipal(tbUserinfo, user))
			tips = 1;
		
		return "addprincipal";
	}
	// 去修改分校校长
	public String toupdateprincipal(){
		 TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		 tbschool = findDao.findBySchool(tbadmin.getTbSchool().getSchoolId());
		 listbranchschool = findDao.selectbranch(tbschool);
		 tbUserinfo = findDao.findByUserId(userInfoId);
		 return "toupdateprincipal";
	}
	// 修改分校校长
	public String updateprincipal(){
		tbUserinfo = updatedao.updatePrincipal(tbUserinfo);
		return "addprincipal";
	}
	// 删除分校校长
	public void deleteprincipal(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
		 aa[i]=Integer.parseInt(s[i]);
		 TbUserinfo sc=new TbUserinfo();
		 sc.setUserInfoId(aa[i]);
		 updatedao.deleteprincipal(sc);
		} 
	}
	
	// 去导入老师
	public String toteacherinfo(){
		
		return "toteacherinfo";
	}
	
	// 查看科目
	public String selectSubject(){
		listSubject = findDao.selectsubject();
		return "listsubject";
	}
	public String toaddsubject(){
		return "tosubject";
	}
	// 添加科目
	public String addSubject(){
		updatedao.addSubject(tbsubject);
		 return "addsubject";
	 }
	// 去修改科目
	public String toupdateSubject(){
		tbsubject = findDao.selectSubject(subjectId);
		return "toupdatesubject";
	}
	// 修改科目
	public String updateSubject(){
		updatedao.updateSubject(tbsubject);
		return "addsubject";
	}
	
	// 科目删除
	public void deleteSubject(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
		 aa[i]=Integer.parseInt(s[i]);
		 TbSubject sc=new TbSubject();
		 sc.setSubjectId(aa[i]);
		 updatedao.deleteSubject(sc);
		}
	}
	// 删除年段
	public void deleteGrade(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
		 aa[i]=Integer.parseInt(s[i]);
		 TbGrade sc=new TbGrade();
		 sc.setGradeId(aa[i]);
		 updatedao.deleteGrade(sc);
		}
	}
	
	// 查看课程
	public String selectLesson(){
		listlesson = findDao.listlesson();
		return "selectlesson";
	}
	//去添加课程
	public String toaddLesson(){
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		listbranchschool = findDao.selectbranch(tbadmin.getTbSchool());
		listSubject = findDao.selectsubject();
		return "toaddlesson";
	}
	//添加课程数据
	public String addLesson(){
		tblesson = updatedao.addLesson(tblesson);
		return "addlesson";
	}
	//去修改
	public String toupdateLesson(){
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		listSubject = findDao.selectsubject();
		tblesson = findDao.gettblesson(id);
		return "toupdatelesson";
	}
	// 修改课程数据
	public String updateLesson(){
		updatedao.updateTbLesson(tblesson);
		return "addlesson";
	}
	// 删除班
	public void deleteLesson(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
		 aa[i]=Integer.parseInt(s[i]);
		 TbLesson sc=new TbLesson();
		 sc.setId(aa[i]);
		 updatedao.deleteLesson(sc);
		}
	}
	// 删除班
	public void deleteCourse(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
		 aa[i]=Integer.parseInt(s[i]);
		 TbCurriculum sc=new TbCurriculum();
		 sc.setCourseId(aa[i]);
		 updatedao.deleteCourse(sc);
		}
	}
	// 查看班
	public String selectCourse(){
		listCourse = findDao.listtbcourses();
		return "listcourse";
	}
	// 去添加班
	public String toaddCourse(){
		listtown = findDao.listtbTown();
	
		listbranchschool = findDao.selectbranchschooltown(tbtown);
		listgrade = findDao.selectgrade(tbbranchschool);
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		listsubjectinbranchschool = findDao.listsubjectinbranchschool(tbadmin.getTbBranchschool());
		listSubject = findDao.selectsubject();
		 return "toaddcourse";
	}
	
	// 添加班
	public String addCourse(){
		updatedao.addCourse(tbCourse);
		 return "addcourse";
	}
	
	// 去修改班
	public String toupdateCourse(){
		tbCourse = findDao.findByCurriculum(courseId);
		listtown = findDao.listtbTown();
		listbranchschool = findDao.selectbranchschooltown(tbCourse.getTbTown());
		listgrade = findDao.selectgrade(tbCourse.getTbBranchschool());
		
		
		listSubject = findDao.selectsubject();
		listSubject = findDao.listsubject();
		
		 return "toupdatecourse";
	}
	
	 // 修改
	 public String updateCourse(){
		 updatedao.updateCourse(tbCourse);
		 return "addcourse";
	 }
	
	// 查看用户信息
	public String selectUserinfo(){
		listUserinfo = findDao.selectUserinfo();
		return "selectuserinfo";
	}
	
	// 用户
	public String selectUser(){
		listuser = findDao.selectUser();
		return "selectuser";
	}
	// 用户的详细信息
	public String findByUserinfo(){
		tbUserinfo = findDao.findByUserId(tbUserinfo.getUserInfoId());
		return "findbyuserinfo";
	}
	
	// 家长关联学生
	public String selectRelation(){
		listrelation = findDao.selectRelation();
		return "selectrelation";
	}
	
	// 学校通知
	public String selectSchoolnotice(){
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
//		listbranchschool = findDao.selectbranch(tbadmin.getTbSchool());
		tbschool = findDao.findBySchool(tbadmin.getTbSchool().getSchoolId());
		listschoolnotice = findDao.listSchoolNotice1(tbschool);
		System.out.println(listschoolnotice.size());
		return "selectschoolnotice";
	}
	//去添加
	public String toaddSchoolnotice(){
		listschool = findDao.selectSchool();
		 return "toaddschoolnotice";
	}
	//添加学校通知
	public String addSchoolnotice(){
		tbschoolnotice = updatedao.addSchoolnotice(tbschoolnotice);
		return "addschoolnotice";
	}
	// 去修改
	public String toupdateSchoolnotice(){
		listschool = findDao.selectSchool();
		tbschoolnotice = findDao.findByTbSchoolnotice(schoolNoticeId);
		 return "toupdateschoolnotice";
	}
	// 修改学校通知
	public String updateSchoolnotice(){
		tbschoolnotice = updatedao.updateschoolNotice(tbschoolnotice);
		return "addschoolnotice";
	}
	
	// 删除通知
	public void deleteschoolNotice(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
			aa[i]=Integer.parseInt(s[i]);
			TbSchoolnotice uu=new TbSchoolnotice();
			uu.setSchoolNoticeId(aa[i]);
			updatedao.deleteschoolNotice(uu);
		}
	}
	
	// 班级通知
	public String selectClassnotice(){
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		listclassnotice = findDao.listClassnotice(tbadmin.getIsValid());
		return "selectclassnotice";
	}
	// 去添加
	public String toaddClassnotice(){
		listschool = findDao.selectSchool();
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		tbUserinfo = findDao.findByUserId(tbadmin.getIsValid());
		listuserinfoclass = findDao.listuserclass(tbUserinfo);
		return "toaddclassnotice";
	}
	
	// 添加班级通知
	public String addClassnotice(){
		String basePicUrl = request.getSession().getServletContext()
				.getRealPath("images/").replace("\\", "/");
		tbclassnotice = updatedao.addClassnotice(tbclassnotice);
			List<Integer> list = new DataBaseDaoImpl().getSQLList("select distinct c.userInfoId from tb_userinfoclass c where c.courseId= "+tbclassnotice.getTbCourse().getCourseId());
			new JpushThread(tbclassnotice.getTbUserinfo().getUserInfoId(), list, tbclassnotice.getClanotContent(),"班级通知",basePicUrl).start();
		return "addclassnotice";
	}
	
	// 删除班级通知
	public void deleteclassNotice(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
			aa[i]=Integer.parseInt(s[i]);
			TbClassnotice uu=new TbClassnotice();
			uu.setClassNoticeId(aa[i]);
			updatedao.deleteclassNotice(uu);
		}
	}
	
	// 投诉管理
	public String selectComplain(){
		TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
		listcomplain = findDao.listComplain1(tbadmin.getTbBranchschool());
		return "selectcomplain";
	}
	// 删除投诉
	public void deleteComplain(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
		 aa[i]=Integer.parseInt(s[i]);
		 TbComplain uu=new TbComplain();
		 uu.setComplainId(aa[i]);
		 updatedao.deleteComplain(uu);
		}
	}
	
	// 投诉明细
	public String selectComplaindetails(){
		complaindetails = findDao.getComplaindetails(tbcomplain);
		return "selectcomplaindetails";
	}
	
	// 评语
	public String selectReview(){
		listreview = findDao.listreview();
		return "selectreview";
	}
	// 删除评语
	public void deleteReview(){
		String[] s=str.split(",");
		Integer[] aa=new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
			aa[i]=Integer.parseInt(s[i]);
			TbReview uu=new TbReview();
			uu.setReviewId(aa[i]);
			updatedao.deleteReview(uu);
		}
	}
	
	// 年段
	public String selectGrade(){
		listgrade = findDao.listGrades();
		return "selectgrade";
	}
	
	//去添加
	public String toaddgrade(){
		
		return "toaddgrade";
	}
	// 添加
	public String addgrade(){
		tbgrade = updatedao.addGrade(tbgrade);
		return "addgrade";
	}
	
	// 去修改
	public String toupdategrade(){
		tbgrade = findDao.findByGrade(gradeId);
		return "toupdategrade";
	}
	// 修改
	public String updategrade(){
		updatedao.updateGrade(tbgrade);
		return "addgrade";
	}

	// 去添加
	public String toCourse(){
		listschool = findDao.selectSchool();
		listCourse = findDao.selecttbcourse();
		listUserinfo = findDao.listuserinfo();
		return "toaddclassinfo";
	}
	public void print(String str){
		HttpServletResponse res=ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter pw=res.getWriter();
			pw.print(str);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List getListreview() {
		return listreview;
	}
	public void setListreview(List listreview) {
		this.listreview = listreview;
	}
	public List getComplaindetails() {
		return complaindetails;
	}
	public void setComplaindetails(List complaindetails) {
		this.complaindetails = complaindetails;
	}
	public TbComplain getTbcomplain() {
		return tbcomplain;
	}
	public void setTbcomplain(TbComplain tbcomplain) {
		this.tbcomplain = tbcomplain;
	}
	public List getListcomplain() {
		return listcomplain;
	}
	public void setListcomplain(List listcomplain) {
		this.listcomplain = listcomplain;
	}
	public FindUserDao getFindDao() {
		return findDao;
	}
	public void setFindDao(FindUserDao findDao) {
		this.findDao = findDao;
	}
	public UpdateDao getUpdatedao() {
		return updatedao;
	}
	public void setUpdatedao(UpdateDao updatedao) {
		this.updatedao = updatedao;
	}
	public List getListSubject() {
		return listSubject;
	}
	public void setListSubject(List listSubject) {
		this.listSubject = listSubject;
	}
	public List getListCourse() {
		return listCourse;
	}
	public void setListCourse(List listCourse) {
		this.listCourse = listCourse;
	}
	public List getListUserinfo() {
		return listUserinfo;
	}
	public void setListUserinfo(List listUserinfo) {
		this.listUserinfo = listUserinfo;
	}
	public List getListschool() {
		return listschool;
	}
	public void setListschool(List listschool) {
		this.listschool = listschool;
	}
	public List getListbranchschool() {
		return listbranchschool;
	}
	public void setListbranchschool(List listbranchschool) {
		this.listbranchschool = listbranchschool;
	}
	public TbTown getTbtown() {
		return tbtown;
	}
	public void setTbtown(TbTown tbtown) {
		this.tbtown = tbtown;
	}
	public Integer getTownId() {
		return townId;
	}
	public void setTownId(Integer townId) {
		this.townId = townId;
	}
	public TbSubject getTbsubject() {
		return tbsubject;
	}
	public void setTbsubject(TbSubject tbsubject) {
		this.tbsubject = tbsubject;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public TbCurriculum getTbCourse() {
		return tbCourse;
	}
	public void setTbCourse(TbCurriculum tbCourse) {
		this.tbCourse = tbCourse;
	}
	public Integer getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}
	public TbBranchschool getTbbranchschool() {
		return tbbranchschool;
	}
	public void setTbbranchschool(TbBranchschool tbbranchschool) {
		this.tbbranchschool = tbbranchschool;
	}
	public TbUserinfo getTbUserinfo() {
		return tbUserinfo;
	}
	public void setTbUserinfo(TbUserinfo tbUserinfo) {
		this.tbUserinfo = tbUserinfo;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public List getListtown() {
		return listtown;
	}
	public void setListtown(List listtown) {
		this.listtown = listtown;
	}
	public List getListgrade() {
		return listgrade;
	}
	public void setListgrade(List listgrade) {
		this.listgrade = listgrade;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public List getListuser() {
		return listuser;
	}
	public void setListuser(List listuser) {
		this.listuser = listuser;
	}
	public TbUserinfo getTbuser() {
		return tbuser;
	}
	public void setTbuser(TbUserinfo tbuser) {
		this.tbuser = tbuser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public List getListrelation() {
		return listrelation;
	}
	public void setListrelation(List listrelation) {
		this.listrelation = listrelation;
	}
	public Integer getBranchSchoolId() {
		return branchSchoolId;
	}
	public void setBranchSchoolId(Integer branchSchoolId) {
		this.branchSchoolId = branchSchoolId;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public List getListschoolnotice() {
		return listschoolnotice;
	}
	public void setListschoolnotice(List listschoolnotice) {
		this.listschoolnotice = listschoolnotice;
	}
	public TbSchool getTbschool() {
		return tbschool;
	}
	public void setTbschool(TbSchool tbschool) {
		this.tbschool = tbschool;
	}
	public List getListclassnotice() {
		return listclassnotice;
	}
	public void setListclassnotice(List listclassnotice) {
		this.listclassnotice = listclassnotice;
	}
	public TbSchoolnotice getTbschoolnotice() {
		return tbschoolnotice;
	}
	public void setTbschoolnotice(TbSchoolnotice tbschoolnotice) {
		this.tbschoolnotice = tbschoolnotice;
	}
	public Integer getSchoolNoticeId() {
		return schoolNoticeId;
	}
	public void setSchoolNoticeId(Integer schoolNoticeId) {
		this.schoolNoticeId = schoolNoticeId;
	}
	public TbClassnotice getTbclassnotice() {
		return tbclassnotice;
	}
	public void setTbclassnotice(TbClassnotice tbclassnotice) {
		this.tbclassnotice = tbclassnotice;
	}
	public TbGrade getTbgrade() {
		return tbgrade;
	}
	public void setTbgrade(TbGrade tbgrade) {
		this.tbgrade = tbgrade;
	}
	public List getListinfoclass() {
		return listinfoclass;
	}
	public void setListinfoclass(List listinfoclass) {
		this.listinfoclass = listinfoclass;
	}
	public TbUserinfoclass getInfoclass() {
		return infoclass;
	}
	public void setInfoclass(TbUserinfoclass infoclass) {
		this.infoclass = infoclass;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public List getListcity() {
		return listcity;
	}
	public void setListcity(List listcity) {
		this.listcity = listcity;
	}
	public TbCity getTbcity() {
		return tbcity;
	}
	public void setTbcity(TbCity tbcity) {
		this.tbcity = tbcity;
	}
	public List getListtbcourse() {
		return listtbcourse;
	}
	public void setListtbcourse(List listtbcourse) {
		this.listtbcourse = listtbcourse;
	}
	public TbCurriculum getTbcurriculum() {
		return tbcurriculum;
	}
	public void setTbcurriculum(TbCurriculum tbcurriculum) {
		this.tbcurriculum = tbcurriculum;
	}
	public Integer getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}
	public List getListteacherinfo() {
		return listteacherinfo;
	}
	public void setListteacherinfo(List listteacherinfo) {
		this.listteacherinfo = listteacherinfo;
	}
	public Integer getBranchschoolId() {
		return branchschoolId;
	}
	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public List getListsubjectinbranchschool() {
		return listsubjectinbranchschool;
	}
	public void setListsubjectinbranchschool(List listsubjectinbranchschool) {
		this.listsubjectinbranchschool = listsubjectinbranchschool;
	}
	public List getListlesson() {
		return listlesson;
	}
	public void setListlesson(List listlesson) {
		this.listlesson = listlesson;
	}
	public TbLesson getTblesson() {
		return tblesson;
	}
	public void setTblesson(TbLesson tblesson) {
		this.tblesson = tblesson;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TbUser getUser() {
		return user;
	}
	public void setUser(TbUser user) {
		this.user = user;
	}
	public List getListuserinfoclass() {
		return listuserinfoclass;
	}
	public void setListuserinfoclass(List listuserinfoclass) {
		this.listuserinfoclass = listuserinfoclass;
	}
	public List<TbUser> getUserlist1() {
		return userlist1;
	}
	public void setUserlist1(List<TbUser> userlist1) {
		this.userlist1 = userlist1;
	}
	public List<TbUserinfo> getListUserinfo1() {
		return listUserinfo1;
	}
	public void setListUserinfo1(List<TbUserinfo> listUserinfo1) {
		this.listUserinfo1 = listUserinfo1;
	}
}
