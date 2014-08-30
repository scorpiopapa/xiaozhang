package com.qiYang.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.cyh.util.HibernateByDCPageUtil;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbClassnotice;
import com.qiYang.model.TbComplain;
import com.qiYang.model.TbComplaindetails;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbPracticeoption;
import com.qiYang.model.TbPracticetitle;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbReview;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchoolnotice;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbSubjectinbranchschool;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbTestfinish;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;
import com.qiYang.model.TbUserphoto;
import com.qiYang.util.HibernateSessionFactory;

public class FindUserDao extends HibernateByDCPageUtil {

	//
	public TbUser getTbUser(TbUserinfo tbuserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUser.class);
		c.add(Restrictions.eq("tbUserinfo", tbuserinfo));
		c.add(Restrictions.eq("isValid", 1));
		TbUser tbUser = (TbUser) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUser;
	}

	public TbUser getTbUser(String userName) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUser.class);
		c.add(Restrictions.eq("userName", userName));
		TbUser tbUser = (TbUser) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUser;
	}

	public TbUserinfo getTbUserinfo(TbUserinfo tbuserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("tbUserinfo", tbuserinfo));
		c.add(Restrictions.eq("isValid", 1));
		TbUserinfo tbUserinfo = (TbUserinfo) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUserinfo;
	}

	// 修改密码
	public TbUser getTbUser(Integer userinfoId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUser.class);
		c.add(Restrictions.eq("userInfoId", userinfoId));
		c.add(Restrictions.eq("isValid", 1));
		TbUser tbUser = (TbUser) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUser;
	}

	// 用户表
	public TbUserinfo getTbUserinfo(Integer userInfoId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("userInfoId", userInfoId));
		TbUserinfo tbUserinfo = (TbUserinfo) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUserinfo;
	}

	public TbUserinfo selectTbUserinfo(TbCourse tbcourse) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("tbCourse", tbcourse));
		TbUserinfo tbUserinfo = (TbUserinfo) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUserinfo;
	}

	// 根据ID查询用户信息
	public TbUserinfo findByUserId(Integer userInfoId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("userInfoId", userInfoId));
		c.add(Restrictions.eq("isValid", 1));
		TbUserinfo tbUserinfo = (TbUserinfo) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUserinfo;
	}

	// 学生所在分校
	public TbBranchschool findByBranchschool(Integer branchschoolId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbBranchschool.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("id", branchschoolId));
		TbBranchschool tbBranchschool = (TbBranchschool) c.uniqueResult();
		if (null != session && session.isOpen())
			session.close();
		return tbBranchschool;
	}

	// 所属学校
	public TbSchool findBySchool(Integer schoolId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSchool.class);
		c.add(Restrictions.eq("schoolId", schoolId));
		TbSchool tbschool = (TbSchool) c.uniqueResult();
		if (null != session && session.isOpen())
			session.close();
		return tbschool;
	}

	// 查询用户相册
	public ArrayList<TbUserphoto> findbyPhotoAll(TbUserinfo tbUserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserphoto.class);
		c.add(Restrictions.eq("tbUserinfo", tbUserinfo));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		ArrayList<TbUserphoto> currentlist = (ArrayList<TbUserphoto>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return currentlist;
	}

	// 学校通知公告
	public ArrayList<TbSchoolnotice> listSchoolNotice(
			TbBranchschool tbBranchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSchoolnotice.class);
		c.add(Restrictions.eq("tbBranchschool", tbBranchschool));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		ArrayList<TbSchoolnotice> schoolnoticelist = (ArrayList<TbSchoolnotice>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return schoolnoticelist;
	}

	// 通告详情
	public TbSchoolnotice findByTbSchoolnotice(Integer schoolnoticeId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSchoolnotice.class);
		c.setFetchMode("tbSchool", FetchMode.JOIN);
		c.setFetchMode("tbBranchschool", FetchMode.JOIN);
		c.add(Restrictions.eq("schoolNoticeId", schoolnoticeId));
		TbSchoolnotice tbschool = (TbSchoolnotice) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbschool;
	}

	// 查看评语
	public ArrayList<TbReview> listTbreview(TbUserinfo tbuserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbReview.class);
		c.add(Restrictions.eq("tbUserinfoByUserInfoId", tbuserinfo));
		c.addOrder(Order.desc("alterTime"));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbReview> review = (ArrayList<TbReview>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return review;
	}

	public TbReview getReview(Integer reviewId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbReview.class);
		c.add(Restrictions.eq("reviewId", reviewId));
		c.add(Restrictions.eq("isValid", 1));
		TbReview tbReview = (TbReview) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbReview;
	}

	// 投诉列表
	public ArrayList<TbComplain> listComplain(TbUserinfo tbuserinfo,
			TbCourse tbcourse, TbBranchschool tbbranchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbComplain.class);
		c.add(Restrictions.eq("tbUserinfoByUserInfoId", tbuserinfo));
		c.add(Restrictions.eq("tbBranchschool", tbbranchschool));
		c.add(Restrictions.eq("tbCourse", tbcourse));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		ArrayList<TbComplain> complain = (ArrayList<TbComplain>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return complain;
	}

	// 查看投诉
	public TbComplain getComplain(Integer complainId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbComplain.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("complainId", complainId));
		c.addOrder(Order.desc("time"));
		TbComplain tbComplain = (TbComplain) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbComplain;
	}

	// 校长端 查询所有投诉
	public ArrayList<TbComplain> listComplain(TbBranchschool tbbranchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbComplain.class);
		c.add(Restrictions.eq("tbBranchschool", tbbranchschool));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		ArrayList<TbComplain> listtbComplain = (ArrayList<TbComplain>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listtbComplain;
	}

	public TbSchool findSchool(Integer schoolId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSchool.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("schoolId", schoolId));
		TbSchool tbschool = (TbSchool) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbschool;
	}

	public TbSchool getSchool(Integer tbbranchschoolId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSchool.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("branchSchoolId", tbbranchschoolId));
		TbSchool tbschool = (TbSchool) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbschool;
	}

	public ArrayList<TbComplaindetails> getComplaindetails(TbComplain tbcomplain) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbComplaindetails.class);
		c.add(Restrictions.eq("tbComplain", tbcomplain));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbComplaindetails> tbComplaindetails = (ArrayList<TbComplaindetails>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbComplaindetails;
	}

	// 班级
	public TbCourse findByCourse(TbComplain tbcomplain) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("tbComplain", tbcomplain));
		TbCourse tbCourse = (TbCourse) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbCourse;
	}

	public TbCourse findByCourse(Integer courseId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("courseId", courseId));
		TbCourse tbCourse = (TbCourse) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbCourse;
	}

	// 老师班级
	public ArrayList<TbUserinfoclass> listuserinfoclass(Integer tbcourseId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		c.add(Restrictions.eq("courseId", tbcourseId));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfoclass> tbuserinfoclass = (ArrayList<TbUserinfoclass>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbuserinfoclass;
	}

	public ArrayList<TbUserinfoclass> listuserclass(TbUserinfo tbUserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfoclass.class);
		c.setFetchMode("tbCourse", FetchMode.JOIN);
		c.add(Restrictions.eq("tbUserinfo", tbUserinfo));
		c.add(Restrictions.eq("userRoot", 1));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfoclass> tbuserinfoclass = (ArrayList<TbUserinfoclass>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbuserinfoclass;
	}

	// 年段
	public TbLesson findBylesson(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbLesson.class);
		c.add(Restrictions.eq("id", id));
		c.add(Restrictions.eq("isValid", 1));
		TbLesson lesson = (TbLesson) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return lesson;
	}

	public TbGrade findByGrade(Integer gradeId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbGrade.class);
		c.add(Restrictions.eq("gradeId", gradeId));
		c.add(Restrictions.eq("isValid", 1));
		TbGrade grade = (TbGrade) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return grade;
	}

	public ArrayList<TbUserinfo> getTbUserinfoList(TbCourse tbcourse) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfoclass.class);
		c.add(Restrictions.eq("tbCourse", tbcourse));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfo> tbUserinfo = (ArrayList<TbUserinfo>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUserinfo;
	}

	public ArrayList<TbUserinfo> UserinfoList(TbCourse tbcourse) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("tbCourse", tbcourse));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfo> tbUserinfo = (ArrayList<TbUserinfo>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUserinfo;
	}

	// ---------------------------------家长端----------------------------------------
	// 只能查看在线练习
	public ArrayList<TbTestfinish> listTestfinish(TbUserinfo tbuserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTestfinish.class);
		c.add(Restrictions.eq("tbUserinfo", tbuserinfo));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		ArrayList<TbTestfinish> listtestfinish = (ArrayList<TbTestfinish>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listtestfinish;
	}

	// 练习
	public TbTest gettest(Integer testId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTest.class);
		c.add(Restrictions.eq("testId", testId));
		c.add(Restrictions.eq("isValid", 1));
		TbTest tbtest = (TbTest) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbtest;
	}

	// 学生在线练习
	public ArrayList<TbPracticetitle> listPracticetitle(TbTest tbtest) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbPracticetitle.class);
		c.add(Restrictions.eq("tbTest", tbtest));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbPracticetitle> listtbPracticetitle = (ArrayList<TbPracticetitle>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listtbPracticetitle;
	}

	public ArrayList<TbPracticeoption> listPracticeoption(
			TbPracticetitle tbPracticetitle) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbPracticeoption.class);
		c.add(Restrictions.eq("tbPracticetitle", tbPracticetitle));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbPracticeoption> listtbPracticetitle = (ArrayList<TbPracticeoption>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listtbPracticetitle;
	}

	public TbPracticetitle findPracticetitle(TbTest tbTest) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbPracticetitle.class);
		c.add(Restrictions.eq("tbTest", tbTest));
		c.add(Restrictions.eq("isValid", 1));
		TbPracticetitle tbtest1 = (TbPracticetitle) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbtest1;
	}

	// 关系表
	public TbRelation gettbrelation(TbUserinfo userinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbRelation.class);
		c.add(Restrictions.eq("tbUserinfoByTbUserInfoId", userinfo));
		c.add(Restrictions.eq("isValid", 1));
		TbRelation tbRelation = (TbRelation) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbRelation;
	}

	public TbTestfinish getTbtestfinish(TbUserinfo tbuserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTestfinish.class);
		c.add(Restrictions.eq("tbUserinfo", tbuserinfo));
		c.add(Restrictions.eq("isValid", 1));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		TbTestfinish tbtestfinish = (TbTestfinish) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbtestfinish;
	}

	public TbUserinfoclass gettbuserinfoclass(TbUserinfo tbuserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfoclass.class);
		c.add(Restrictions.eq("tbUserinfo", tbuserinfo));
		c.add(Restrictions.eq("isValid", 1));
		TbUserinfoclass tbuserinfoclass = (TbUserinfoclass) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbuserinfoclass;
	}

	public TbSubject findBysubject(Integer subjectId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSubject.class);
		c.add(Restrictions.eq("subjectId", subjectId));
		c.addOrder(Order.desc("subjectId"));
		c.add(Restrictions.eq("isValid", 1));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		TbSubject tbsubject = (TbSubject) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbsubject;
	}

	// 所有课程
	public ArrayList<TbSubject> listsubject() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSubject.class);
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		ArrayList<TbSubject> list = (ArrayList<TbSubject>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> list(TbTown tbTown) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("tbTown", tbTown));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbSubject> listsubject(Integer branchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSubject.class);
		c.add(Restrictions.eq("isValid", 1));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		ArrayList<TbSubject> tbsubject = (ArrayList<TbSubject>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbsubject;
	}

	public List selectsubject() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbSubject.class);
		dc.add(Restrictions.eq("isValid", 1));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	public ArrayList<TbCourse> findBycourse() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		// c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCourse> tbcourse = (ArrayList<TbCourse>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbcourse;
	}

	public ArrayList<TbGrade> findBygrade(TbSubject tbsubject) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCourse> tbcourse = (ArrayList<TbCourse>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return null;
	}

	public ArrayList<TbCurriculum> findBycourse(TbSubject tbsubject) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> tbcurriculum = (ArrayList<TbCurriculum>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbcurriculum;
	}

	public ArrayList<TbLesson> findBylesson(TbSubject tbsubject,
			TbSchool tbschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbLesson.class);
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbLesson> lesson = (ArrayList<TbLesson>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return lesson;
	}

	public ArrayList<TbLesson> tofindBylesson(TbSubject tbsubject) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbLesson.class);
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbLesson> lesson = (ArrayList<TbLesson>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return lesson;
	}

	public ArrayList<TbCourse> getCourse(TbBranchschool branchSchool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		c.add(Restrictions.eq("tbBranchschool", branchSchool));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		ArrayList<TbCourse> currentlist = (ArrayList<TbCourse>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return currentlist;
	}

	// 课程表
	public ArrayList<TbCurriculum> listCurriculum() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> selectCurriculum(TbSchool tbschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public TbCurriculum findByCurriculum(Integer courseId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("courseId", courseId));
		c.addOrder(Order.desc("time"));
		TbCurriculum currentlist = (TbCurriculum) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return currentlist;
	}

	public TbCurriculum findByCurriculum(String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("courseScore", courseScore));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		TbCurriculum currentlist = (TbCurriculum) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return currentlist;
	}

	public TbCurriculum findByCurr(String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("courseScore", courseScore));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("time"));
		TbCurriculum currentlist = (TbCurriculum) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return currentlist;
	}

	// -----------------------------------------------------------------
	public TbBranchschool getbranchSchool(Integer branchSchoolId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbBranchschool.class);
		c.add(Restrictions.eq("branchSchoolId", branchSchoolId));
		TbBranchschool branchSchool = (TbBranchschool) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return branchSchool;
	}

	// 班主任
	public ArrayList<TbUserinfoclass> finduserinfoclass(TbCourse tbcourse) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfoclass.class);
		c.add(Restrictions.eq("tbCourse", tbcourse));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfoclass> userinfoclass = (ArrayList<TbUserinfoclass>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return userinfoclass;
	}

	public ArrayList<TbUserinfoclass> findinfoclass(TbCourse course) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfoclass.class);
		c.add(Restrictions.eq("tbCourse", course));
		c.add(Restrictions.eq("userRoot", 1));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfoclass> userinfoclass = (ArrayList<TbUserinfoclass>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return userinfoclass;
	}

	// 学生详细信息
	public TbUserinfo findStudentinfo(Integer stuId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("stuId", stuId));
		c.add(Restrictions.eq("isValid", 1));
		TbUserinfo tbstudentinfo = (TbUserinfo) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbstudentinfo;
	}

	// 市区
	public TbCity findTbCity(Integer cityId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCity.class);
		c.add(Restrictions.eq("cityId", cityId));
		c.add(Restrictions.eq("isValid", 1));
		TbCity tbcity = (TbCity) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbcity;
	}

	public List<TbTown> findTbtown(TbCity tbCity) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTown.class);
		c.add(Restrictions.eq("tbCity", tbCity));
		List<TbTown> tbcity = c.list();
		if (null != session && session.isOpen())
			session.close();
		return tbcity;
	}

	public List<TbCurriculum> listTbCurr(List<TbTown> tbTowns) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		List<TbCurriculum> list = c.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}

	public TbCity findTbCity(String cityName) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCity.class);
		c.add(Restrictions.like("cityName", cityName, MatchMode.START));
		c.add(Restrictions.eq("isValid", 1));
		TbCity tbcity = (TbCity) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbcity;
	}

	public ArrayList<TbCity> listCity() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCity.class);
		c.addOrder(Order.asc("cityName"));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCity> listtbcity = (ArrayList<TbCity>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listtbcity;
	}

	// 城区
	public ArrayList<TbTown> listTown(TbCity tbCity) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTown.class);
		c.add(Restrictions.eq("tbCity", tbCity));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbTown> listtown = (ArrayList<TbTown>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listtown;
	}

	public ArrayList<TbTown> listtbTown() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTown.class);
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbTown> listtown = (ArrayList<TbTown>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listtown;
	}

	public TbTown findTown(Integer townId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTown.class);
		c.add(Restrictions.eq("townId", townId));
		c.add(Restrictions.eq("isValid", 1));
		TbTown town = (TbTown) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return town;
	}

	public ArrayList<TbCurriculum> listcurriculum(TbTown tbTown,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> listcourse = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listcourse;
	}

	public ArrayList<TbCurriculum> findcurriculum(Integer townId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("townId", townId));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> listcourse = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listcourse;
	}

	// 所有成绩
	public ArrayList<TbCurriculum> listcourse() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	// 所有课程
	public List listtbcourses() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbCurriculum.class);
		dc.add(Restrictions.eq("isValid", 1));
		dc.setFetchMode("tbTown", FetchMode.JOIN);
		dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
		dc.setFetchMode("tbSubject", FetchMode.JOIN);
		dc.setFetchMode("tbGrade", FetchMode.JOIN);
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("defineSort"));
		dc.addOrder(Order.desc("defineSort2"));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	public ArrayList<TbCurriculum> listtbcourse(String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("courseScore", courseScore));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listtbcourse() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listtbc(List<TbTown> tbTowns) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.in("tbTown", tbTowns));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}

	// 课程下的班级
	public ArrayList<TbCourse> listcourse(TbCurriculum tbcurriculum) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		c.setFetchMode("tbCurriculum", FetchMode.JOIN);
		c.add(Restrictions.eq("tbCurriculum", tbcurriculum));
		c.add(Restrictions.eq("isGood", 0));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCourse> list = (ArrayList<TbCourse>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbUserinfoclass> selectcourse(Integer userinfoId) {

		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfoclass.class);
		c.add(Restrictions.eq("userinfoId", userinfoId));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfoclass> list = (ArrayList<TbUserinfoclass>) c.list();

		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public List selecttbcourse() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbCourse.class);
		dc.setFetchMode("tbTown", FetchMode.JOIN);
		dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
		dc.setFetchMode("tbSubject", FetchMode.JOIN);
		dc.setFetchMode("tbGrade", FetchMode.JOIN);
		dc.add(Restrictions.eq("isValid", 1));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	public ArrayList<TbCurriculum> listCourse(TbGrade tbGrade) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbGrade", tbGrade));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	// 推荐课程
	public ArrayList<TbCurriculum> listCurriculumtuijian(
			TbBranchschool tbBranchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbBranchschool", tbBranchschool));
		c.add(Restrictions.eq("isGood", 0));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listtuijian() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("isGood", 0));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	// --------------------------------------------------------

	// 根据科目查找
	public ArrayList<TbSubject> listSubjectName(Integer subjectId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSubject.class);
		c.add(Restrictions.eq("subjectId", subjectId));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbSubject> listsubject = (ArrayList<TbSubject>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listsubject;
	}

	public ArrayList<TbCurriculum> listCourseSubject(TbSubject tbsubject) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listCourseSubject(TbSubject tbsubject,
			TbGrade tbGrade) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("tbGrade", tbGrade));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listCourseSubject(TbSchool tbschool,
			TbSubject tbsubject, TbGrade tbGrade) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("tbGrade", tbGrade));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listCourseName(String courseName) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("courseName", courseName));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listSubject(TbTown tbTown) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbGrade> listGrade() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbGrade.class);
		c.setFetchMode("tbSchool", FetchMode.JOIN);
		c.setFetchMode("tbBranchschool", FetchMode.JOIN);
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbGrade> listgrade = (ArrayList<TbGrade>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return listgrade;
	}

	public List listGrades() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbGrade.class);
		dc.setFetchMode("tbSchool", FetchMode.JOIN);
		dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("time"));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	public ArrayList<TbCurriculum> tijiaocourse(TbSchool tbSchool,
			List<TbTown> tbTowns, TbTown tbTown, String grades,
			TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiao1(TbSchool tbSchool, TbTown tbTown,
			String grades, TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse(TbSchool tbSchool,
			List<TbTown> tbTowns, TbTown tbTown, String grades,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbSchool", tbSchool));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiao2(TbSchool tbSchool, TbTown tbTown,
			String grades, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSchool", tbSchool));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse1(TbTown tbTown, TbGrade tbGrade) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbGrade", tbGrade));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse1(TbTown tbTown,
			TbSubject tbsubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("courseScore", courseScore));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse1(TbSchool tbschool,
			TbSubject tbsubject) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse2(List<TbTown> tbTowns,
			TbSchool tbschool, TbTown tbTown, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse6(TbSchool tbschool,
			TbTown tbTown, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse2(List<TbTown> tbTowns,
			TbTown tbTown, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		// c.setResultTransformer(c.DISTINCT_ROOT_ENTITY);
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse7(TbTown tbTown,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		// c.setResultTransformer(c.DISTINCT_ROOT_ENTITY);
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse2(List<TbTown> tbTowns,
			TbTown tbTown, TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse8(TbTown tbTown,
			TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse2(List<TbTown> tbTowns,
			TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse9(TbSubject tbSubject,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse2(List<TbTown> tbTowns,
			TbSchool tbschool, TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse11(TbSchool tbschool,
			TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse2(List<TbTown> tbTowns,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse3(String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse2(List<TbTown> tbTowns,
			TbSchool tbSchool, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse4(TbSchool tbSchool,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse3(List<TbTown> tbTowns,
			String grades, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		// c.add(Restrictions.or(Restrictions.or(Restrictions.like("grades",
		// grades+"@%",MatchMode.START), Restrictions.like("grades",
		// "%@"+grades,MatchMode.START)),Restrictions.or(Restrictions.like("grades",
		// "%@"+grades+"@%",MatchMode.START),Restrictions.eq("grades",
		// grades))));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse12(String grades,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		// c.add(Restrictions.or(Restrictions.or(Restrictions.like("grades",
		// grades+"@%",MatchMode.START), Restrictions.like("grades",
		// "%@"+grades,MatchMode.START)),Restrictions.or(Restrictions.like("grades",
		// "%@"+grades+"@%",MatchMode.START),Restrictions.eq("grades",
		// grades))));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse3(List<TbTown> tbTowns,
			TbTown tbTown, String grades, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbTown", tbTown));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse13(TbTown tbTown, String grades,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse3(List<TbTown> tbTowns,
			TbSchool tbSchool, TbTown tbTown, TbSubject tbSubject,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourse14(TbSchool tbSchool,
			TbTown tbTown, TbSubject tbSubject, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	public ArrayList<TbCurriculum> tijiaocourseScore(String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("courseScore", courseScore));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> tijiao = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tijiao;
	}

	// 选课报班
	public ArrayList<TbCurriculum> listGradeCourse(TbSubject tbsubject,
			TbLesson lesson) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("tbLesson", lesson));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("courseId"));//选课报班详细内容
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen()) {
			session.flush();
			session.close();
		}
		return list;
	}

	public ArrayList<TbCurriculum> listGradeCourse(TbSchool tbSchool,
			TbSubject tbsubject, TbLesson tblesson) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbSubject", tbsubject));
		c.add(Restrictions.eq("tbLesson", tblesson));
		c.add(Restrictions.eq("isValid", 1));
		c.addOrder(Order.desc("defineSort"));
		c.addOrder(Order.desc("defineSort2"));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public TbUserinfo selecttbuser(Integer userinfoId, String userInfoEmail) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("userInfoId", userinfoId));
		c.add(Restrictions.eq("userInfoEmail", userInfoEmail));
		c.add(Restrictions.eq("isValid", 1));
		TbUserinfo userinfo = (TbUserinfo) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return userinfo;
	}

	public TbUserinfo selecttbuser01(Integer userinfoId, String userInfoEmail) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.add(Restrictions.eq("userInfoId", userinfoId));
		c.add(Restrictions.eq("userInfoEmail", userInfoEmail));
		TbUserinfo userinfo = (TbUserinfo) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return userinfo;
	}

	public TbUser selectUserinfoName(String userName) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUser.class);
		c.add(Restrictions.eq("userName", userName));
		c.add(Restrictions.eq("isValid", 1));
		TbUser tbUser = (TbUser) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUser;
	}

	public TbUser selectUserinfoName01(String userName) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUser.class);
		c.add(Restrictions.eq("userName", userName));
		TbUser tbUser = (TbUser) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbUser;
	}

	// 修改密码
	public String updatePassword(Integer userInfoId, String num) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbUser tbuser = (TbUser) session.get(TbUser.class, userInfoId);
			tbuser.setUserPassword(num);
			session.update(tbuser);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.flush();
			session.close();
		}
		return "success";
	}

	public List<TbUserinfo> selectUserinfo() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.setFetchMode("tbSchool", FetchMode.JOIN);
		c.setFetchMode("tbBranchschool", FetchMode.JOIN);
		c.setFetchMode("tbCourse", FetchMode.JOIN);
		c.add(Restrictions.eq("isValid", 1));
		c.add(Restrictions.eq("userInfoRoot", 2));
		ArrayList<TbUserinfo> tbuserinfo = (ArrayList<TbUserinfo>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbuserinfo;
	}

	public List<TbUserinfo> listuserinfo() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbUserinfo.class);
		c.setFetchMode("tbSchool", FetchMode.JOIN);
		c.setFetchMode("tbBranchschool", FetchMode.JOIN);
		c.setFetchMode("tbCourse", FetchMode.JOIN);
		c.add(Restrictions.eq("userInfoRoot", 3));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbUserinfo> tbuserinfo = (ArrayList<TbUserinfo>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbuserinfo;
	}

	public List<TbUserinfo> listuserinfo(TbSchool tbSchool) {
		DetachedCriteria dc = DetachedCriteria.forClass(TbUserinfo.class);

		dc.setFetchMode("tbSchool", FetchMode.JOIN);
		dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
		dc.add(Restrictions.eq("tbSchool", tbSchool));
		dc.add(Restrictions.eq("userInfoRoot", 0));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	public List<TbUser> listuser(List<Object> ob) {
		DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
		dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfo.tbSchool", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfo.tbBranchschool", FetchMode.JOIN);
		// dc.add(Restrictions.eq("tbUserinfo.userInfoRoot", 0));
		dc.add(Restrictions.in("tbUserinfo", ob));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 查询所有学校
	public List selectSchool() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbSchool.class);
		dc.add(Restrictions.eq("isValid", 1));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 分校
	public List selectbranchschool() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbBranchschool.class);
		dc.add(Restrictions.eq("isValid", 1));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	public ArrayList<TbBranchschool> selectbranch(TbSchool tbschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbBranchschool.class);
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbBranchschool> tbBranchschool = (ArrayList<TbBranchschool>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbBranchschool;
	}

	public List<TbTown> selecttown(TbCity tbcity) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbTown.class);
		c.add(Restrictions.eq("tbCity", tbcity));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbTown> town = (ArrayList<TbTown>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return town;
	}

	public List<TbBranchschool> selectb(TbTown tbtown) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbBranchschool.class);
		c.add(Restrictions.eq("tbTown", tbtown));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbBranchschool> tbBranchschool = (ArrayList<TbBranchschool>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbBranchschool;
	}

	public ArrayList<TbLesson> listLesson(TbSchool tbSchool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbLesson.class);
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.setFetchMode("tbSubject", FetchMode.JOIN);
		c.add(Restrictions.eq("isValid", 1));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		ArrayList<TbLesson> list = (ArrayList<TbLesson>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listcc(TbSchool tbSchool, TbTown tbTown) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listcc(TbSchool tbSchool, TbTown tbTown,
			TbSubject tbSubject) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listccc(TbSchool tbSchool, TbTown tbTown,
			TbGrade tbGrade) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("tbGrade", tbGrade));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listtsg(TbTown tbTown, TbSubject tbSubject,
			String grades) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listtsg(TbSchool tbschool,
			List<TbTown> tbTowns, TbSubject tbSubject, String grades,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> tijiao4(TbSchool tbschool,
			TbSubject tbSubject, String grades, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbschool));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listtsg(List<TbTown> tbTowns,
			TbSubject tbSubject, String grades, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> tijiao3(TbSubject tbSubject, String grades,
			String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listtsg(List<TbTown> tbTowns, TbTown tbTown,
			TbSubject tbSubject, String grades, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.in("tbTown", tbTowns));
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> tijiao5(TbTown tbTown, TbSubject tbSubject,
			String grades, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbTown", tbTown));
		c.add(Restrictions.eq("tbSubject", tbSubject));
		Property pf = Property.forName("grades");
		c.add(Restrictions.disjunction().add(pf.like(grades + "@%"))
				.add(pf.like("%@" + grades)).add(pf.like("%@" + grades + "@%"))
				.add(pf.eq(grades)));
		c.add(Restrictions.or(Restrictions.eq("courseScore", "0"),
				Restrictions.eq("courseScore", courseScore)));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listc(TbSchool tbSchool, String courseScore) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbSchool", tbSchool));
		c.add(Restrictions.eq("courseScore", courseScore));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCurriculum> listc(TbBranchschool tbBranchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCurriculum.class);
		c.add(Restrictions.eq("tbBranchschool", tbBranchschool));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbCurriculum> list = (ArrayList<TbCurriculum>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbSubject> listTbSubject(TbBranchschool tbbranchSchool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSubject.class);
		c.add(Restrictions.eq("tbBranchschool", tbbranchSchool));
		c.add(Restrictions.eq("isValid", 1));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		ArrayList<TbSubject> list = (ArrayList<TbSubject>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public ArrayList<TbCourse> listcoures(TbCurriculum tbcurriculum) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbCourse.class);
		c.add(Restrictions.eq("tbCurriculum", tbcurriculum));
		c.add(Restrictions.eq("isValid", 1));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		ArrayList<TbCourse> list = (ArrayList<TbCourse>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return list;
	}

	public List<TbSchool> selectschooltown(TbTown tbtown) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSchool.class);
		c.add(Restrictions.eq("tbTown", tbtown));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbSchool> school = (ArrayList<TbSchool>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return school;
	}

	public List<TbBranchschool> selectbranchschooltown(TbTown tbtown) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbBranchschool.class);
		c.add(Restrictions.eq("tbTown", tbtown));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbBranchschool> tbBranchschool = (ArrayList<TbBranchschool>) c
				.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return tbBranchschool;
	}

	public List<TbGrade> selectgrade(TbBranchschool tbbranchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbGrade.class);
		c.add(Restrictions.eq("tbBranchschool", tbbranchschool));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbGrade> grade = (ArrayList<TbGrade>) c.list();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return grade;
	}

	// 去修改科目
	public TbSubject selectSubject(Integer subjectId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSubject.class);
		c.add(Restrictions.eq("subjectId", subjectId));
		c.add(Restrictions.eq("isValid", 1));
		TbSubject subject = (TbSubject) c.uniqueResult();
		if (null != session && session.isOpen())
			session.flush();
		session.close();
		return subject;
	}

	// 用户
	public List selectUser() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
		dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
		dc.add(Restrictions.eq("isValid", 1));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 查看分校下面的老师
	public List selectTeacher(TbBranchschool tbBranchschool) {
		DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
		dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
		dc.add(Restrictions.eq("tbBranchschool", tbBranchschool));
		dc.add(Restrictions.eq("isValid", 1));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 家长关联用户
	public List selectRelation() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbRelation.class);
		dc.setFetchMode("tbUserinfoByUserInfoId", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfoByTbUserInfoId", FetchMode.JOIN);
		dc.add(Restrictions.eq("isValid", 1));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 学校通知公告
	public List listSchoolNotice1(TbSchool tbSchool) {
		DetachedCriteria dc = DetachedCriteria.forClass(TbSchoolnotice.class);
		dc.setFetchMode("tbSchool", FetchMode.JOIN);
		dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
		dc.add(Restrictions.eq("tbSchool", tbSchool));
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("time"));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 班级通知
	public List listClassnotice(Integer userInfoId) {
		DetachedCriteria dc = DetachedCriteria.forClass(TbClassnotice.class);
		dc.setFetchMode("tbSchool", FetchMode.JOIN);
		dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
		dc.setFetchMode("tbCourse", FetchMode.JOIN);
		dc.createAlias("tbUserinfo", "ui");
		dc.add(Restrictions.eq("ui.userInfoId", userInfoId));
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("time"));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 投诉管理
	public List listComplain1(TbBranchschool tbbranchschool) {
		DetachedCriteria dc = DetachedCriteria.forClass(TbComplain.class);
		dc.setFetchMode("tbSchool", FetchMode.JOIN);
		dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfoByUserInfoId", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfoByTbUserInfoId", FetchMode.JOIN);
		dc.add(Restrictions.eq("tbBranchschool", tbbranchschool));
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("time"));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	// 评语
	public List listreview() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbReview.class);
		dc.setFetchMode("tbUserinfoByUserInfoId", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfoByTbUserInfoId", FetchMode.JOIN);
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("time"));
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());

	}

	public ArrayList<TbSubjectinbranchschool> listsubjectinbranchschool(
			TbBranchschool tbBranchschool) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbSubjectinbranchschool.class);
		c.add(Restrictions.eq("tbBranchschool", tbBranchschool));
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbSubjectinbranchschool> list = (ArrayList<TbSubjectinbranchschool>) c
				.list();
		return list;
	}

	public List listlesson() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbLesson.class);
		dc.add(Restrictions.eq("isValid", 1));
		dc.setFetchMode("tbSubject", FetchMode.JOIN);
		return findPageByDcQuery(dc, 10, ServletActionContext.getRequest());
	}

	public TbLesson gettblesson(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbLesson.class);
		c.add(Restrictions.eq("id", id));
		TbLesson lesson = (TbLesson) c.uniqueResult();
		return lesson;
	}

}
