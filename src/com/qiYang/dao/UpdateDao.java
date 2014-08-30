package com.qiYang.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cyh.util.HibernateByDCPageUtil;
import com.qiYang.model.Ofuser;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbClassnotice;
import com.qiYang.model.TbComplain;
import com.qiYang.model.TbComplaindetails;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbReview;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchoolnotice;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.util.HibernateSessionFactory;
import com.qiYang.util.TWDataUtil;

public class UpdateDao extends HibernateByDCPageUtil{

	// 更换手机号
	public String updateUserinfophone(Integer id, Object object,
			Timestamp alertTime, String str) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbUserinfo tbUserinfo = (TbUserinfo) session.get(TbUserinfo.class,id);
			String phone = (String) object;
			tbUserinfo.setUserInfoPhone(phone);
			tbUserinfo.setAlterTime(alertTime);
			session.update(tbUserinfo);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}

	// 修改签名
	public String updateUserinfoSign(Integer id, Object object,
			Timestamp alertTime, String str) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbUserinfo tbUserinfo = (TbUserinfo) session.get(TbUserinfo.class,
					id);
			String sign = (String) object;
			tbUserinfo.setUserInfoSign(sign);
			tbUserinfo.setAlterTime(alertTime);
			session.update(tbUserinfo);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}

	// 修改密码
	public TbUser checkUserPassword(Integer userId, String password) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(TbUser.class);
		criteria.add(Restrictions.eq("id", userId));
		TbUser tbUser = (TbUser) criteria.uniqueResult();
		if (null != session && session.isOpen())
			session.close();
		return tbUser;
	}

	public String upUserPassword(Integer id, Object object,
			Timestamp alertTime, String password) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbUser tbUser = (TbUser) session.get(TbUser.class, id);
			String userpassword = (String) object;
			tbUser.setUserPassword(userpassword);
			tbUser.setAlterTime(alertTime);
			session.update(tbUser);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}

	// 添加评语
	public String addReview(Integer studentId, Integer teacherId,Integer classId,
			String reviewContent) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbReview review = new TbReview();
			TbUserinfo tbstudent = new TbUserinfo();
			tbstudent.setUserInfoId(studentId);
			TbUserinfo tbteacher = new TbUserinfo();
			tbteacher.setUserInfoId(teacherId);
			TbCourse tbcourse = new TbCourse();
			tbcourse.setCourseId(classId);
			Timestamp time = new Timestamp(new Date().getTime());
			review.setTbUserinfoByUserInfoId(tbstudent);
			review.setTbUserinfoByTbUserInfoId(tbteacher);
			review.setTbCourse(tbcourse);

			review.setAddDate(TWDataUtil.dateFormat(new Date()));

			review.setReviewContent(reviewContent);
			review.setIsValid(1);
			review.setTime(time);
			review.setAlterTime(time);
			session.save(review);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}

	// 投诉建议
	public String addtbComplain(Integer userinfoId,Integer classId,Integer branchschoolId,String complainContent) {
		TbSchool tbschool = new FindUserDao().findSchool(branchschoolId);
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbComplain tbcomplain = new TbComplain();
			TbUserinfo tbperson = new TbUserinfo();
			tbperson.setUserInfoId(userinfoId);

//			TbSchool tbschool = new TbSchool();
			
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(branchschoolId);
			TbCourse tbcourse = new TbCourse();
			tbcourse.setCourseId(classId);
			tbcomplain.setTbUserinfoByUserInfoId(tbperson);
			
			tbcomplain.setTbSchool(tbschool);
			tbcomplain.setTbCourse(tbcourse);
			tbcomplain.setTbBranchschool(tbbranchschool);
			tbcomplain.setComplainContent(complainContent);
			tbcomplain.setComplainStatus("1");
			tbcomplain.setIsValid(1);

			Timestamp time = new Timestamp(new Date().getTime());
			tbcomplain.setTime(time);
			tbcomplain.setAlterTime(time);
			session.save(tbcomplain);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	// 校长端回复投诉
	public String addcomplainDetails(Integer complainId,Integer userInfoId,String comdetContent) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbComplaindetails tbcomplaindatails = new TbComplaindetails();
			TbComplain tbcomplain = new TbComplain();
			tbcomplain.setComplainId(complainId);
			TbUserinfo tbperson = new TbUserinfo();
			tbperson.setUserInfoId(userInfoId);
			
			tbcomplaindatails.setTbComplain(tbcomplain);
			tbcomplaindatails.setTbUserinfo(tbperson);
			tbcomplaindatails.setComdetContent(comdetContent);
			tbcomplaindatails.setIsValid(1);
			
			Timestamp time = new Timestamp(new Date().getTime());
			tbcomplaindatails.setTime(time);
			tbcomplaindatails.setAlterTime(time);
			
			TbComplain complain = (TbComplain)session.get(TbComplain.class, tbcomplaindatails.getTbComplain().getComplainId());
			complain.setComplainStatus("0");
			complain.setIsSatisfie(2);
			complain.setTbUserinfoByTbUserInfoId(tbperson);
			
			session.save(tbcomplaindatails);
			session.update(complain);
			
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}

	// 家长回复满意，不满意
	public String updatecomplain(Integer id, Object object, Timestamp alertTime) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbComplain tbcomplain = (TbComplain) session.get(TbComplain.class,
					id);
			Integer isSatisfie = (Integer) object;
			tbcomplain.setIsSatisfie(isSatisfie);
			tbcomplain.setAlterTime(alertTime);
			session.update(tbcomplain);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	// 修改学校 
	public String updateSchool(Integer id, Object object,
			Timestamp alertTime, String str) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbUserinfo tbUserinfo = (TbUserinfo) session.get(TbUserinfo.class,
					id);
			String studentSchool = (String) object;
			tbUserinfo.setStudentSchool(studentSchool);
			tbUserinfo.setAlterTime(alertTime);
			session.update(tbUserinfo);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	// 修改班级 
	public String updateClass(Integer id, Object object,
			Timestamp alertTime, String str) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbUserinfo tbUserinfo = (TbUserinfo) session.get(TbUserinfo.class,id);
			String studentClass = (String) object;
			tbUserinfo.setStudentClass(studentClass);
			tbUserinfo.setAlterTime(alertTime);
			session.update(tbUserinfo);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	
	// 添加科目
	public String addSubject(TbSubject tbsubject) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			tbsubject.setIsValid(1);
			tbsubject.setSubjectName(tbsubject.getSubjectName());
			tbsubject.setTime(time);
			tbsubject.setAlterTime(time);
			session.save(tbsubject);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	// 修改科目
	public String updateSubject(TbSubject tbsubject) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			tbsubject.setIsValid(1);
			tbsubject.setSubjectName(tbsubject.getSubjectName());
			tbsubject.setTime(time);
			tbsubject.setAlterTime(time);
			session.update(tbsubject);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	// 删除分校校长
	public String deleteprincipal(TbUserinfo tbuserinfo){
		System.out.println(tbuserinfo.getUserInfoId());
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(session.get(TbUserinfo.class, tbuserinfo.getUserInfoId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除科目
	public String deleteSubject(TbSubject tbsubject){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(session.get(TbSubject.class, tbsubject.getSubjectId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除年段
	public String deleteGrade(TbGrade tbgrade){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(session.get(TbGrade.class,tbgrade.getGradeId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除课程
	public String deleteCourse(TbCurriculum tbcurriculum){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(session.get(TbCurriculum.class, tbcurriculum.getCourseId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除课程
	public String deleteUserinfo(TbUserinfo tbuserinfo){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.get(TbUserinfo.class, tbuserinfo.getUserInfoId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除投诉
	public String deleteComplain(TbComplain tbcomplain){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.get(TbComplain.class, tbcomplain.getComplainId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除通告
	public String deleteschoolNotice(TbSchoolnotice tbschoolnotice){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.get(TbSchoolnotice.class, tbschoolnotice.getSchoolNoticeId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除班级通告
	public String deleteclassNotice(TbClassnotice tbclassnotice){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.get(TbClassnotice.class, tbclassnotice.getClassNoticeId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除评语
	public String deleteReview(TbReview tbreview){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.get(TbReview.class, tbreview.getReviewId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 删除课程数据信息
	public String deleteLesson(TbLesson tblesson){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.get(TbLesson.class, tblesson.getId()));
			t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
				return "fail";
			} finally {
				if(null!=session && session.isOpen())
				session.close();
			}
			return "success";
	}
	// 修改通告
	public TbSchoolnotice updateschoolNotice(TbSchoolnotice tbschoolnotice) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			TbSchool tbschool = new TbSchool();
//			tbschool.setSchoolId(tbschoolnotice.getTbSchool().getSchoolId());
			tbschool.setSchoolId(1);
			TbBranchschool tbbranchschool = new TbBranchschool();
//			tbbranchschool.setBranchSchoolId(tbschoolnotice.getTbBranchschool().getBranchSchoolId());
			tbbranchschool.setBranchSchoolId(1);
			TbUserinfo tbuserinfo = new TbUserinfo();
			tbuserinfo.setUserInfoId(1);
			
			tbschoolnotice.setTbSchool(tbschool);
			tbschoolnotice.setTbBranchschool(tbbranchschool);
			tbschoolnotice.setTbUserinfo(tbuserinfo);
			tbschoolnotice.setSchnotTitle(tbschoolnotice.getSchnotTitle());
			tbschoolnotice.setSchnotContent(tbschoolnotice.getSchnotContent());
			tbschoolnotice.setAddTime(TWDataUtil.date(time));
			tbschoolnotice.setIsAllSchool(0);
			tbschoolnotice.setIsValid(1);
			tbschoolnotice.setTime(time);
			tbschoolnotice.setAlterTime(time);
			session.update(tbschoolnotice);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbschoolnotice;
	}
	
	// 添加课程
	public TbCurriculum addCourse(TbCurriculum tbcurriculum) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbTown tbtown = new TbTown();
			tbtown.setTownId(tbcurriculum.getTbTown().getTownId());
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(tbcurriculum.getTbBranchschool().getBranchSchoolId());
			TbGrade tbgrade = new TbGrade();
			tbgrade.setGradeId(tbcurriculum.getTbGrade().getGradeId());
			TbSubject tbsubject = new TbSubject();
			tbsubject.setSubjectId(tbcurriculum.getTbSubject().getSubjectId());
			
			tbcurriculum.setTbTown(tbtown);
			tbcurriculum.setTbBranchschool(tbbranchschool);
			tbcurriculum.setTbGrade(tbgrade);
			tbcurriculum.setTbSubject(tbsubject);
			tbcurriculum.setCourseName(tbcurriculum.getCourseName());
			tbcurriculum.setCourseScore(tbcurriculum.getCourseScore());
			tbcurriculum.setIsGood(tbcurriculum.getIsGood());
			tbcurriculum.setCourseIntroduce(tbcurriculum.getCourseIntroduce());
			tbcurriculum.setCourseGoodness(tbcurriculum.getCourseGoodness());
			tbcurriculum.setEnterNeed(tbcurriculum.getEnterNeed());
			tbcurriculum.setIsValid(1);
			tbcurriculum.setStudyGoal(tbcurriculum.getStudyGoal());
			tbcurriculum.setCourseBook(tbcurriculum.getCourseBook());
			
			tbcurriculum.setStartTerm(tbcurriculum.getStartTerm());
			tbcurriculum.setEndTerm(tbcurriculum.getEndTerm());
			
			tbcurriculum.setStudyTime(tbcurriculum.getStudyTime());
			tbcurriculum.setTotalStudyTime(tbcurriculum.getTotalStudyTime());
			tbcurriculum.setStudyCosts(tbcurriculum.getStudyCosts());
			tbcurriculum.setTextbookCost(tbcurriculum.getTextbookCost());
			tbcurriculum.setDirection(tbcurriculum.getDirection());
			tbcurriculum.setPhone(tbcurriculum.getPhone());
			Timestamp time = new Timestamp(new Date().getTime());
			tbcurriculum.setTime(time);
			tbcurriculum.setAlterTime(time);
			
			session.save(tbcurriculum);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbcurriculum;
	}
	// 修改课程
	public TbCurriculum updateCourse(TbCurriculum tbcurriculum) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbTown tbtown = new TbTown();
			tbtown.setTownId(tbcurriculum.getTbTown().getTownId());
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(tbcurriculum.getTbBranchschool().getBranchSchoolId());
			TbGrade tbgrade = new TbGrade();
			tbgrade.setGradeId(tbcurriculum.getTbGrade().getGradeId());
			TbSubject tbsubject = new TbSubject();
			tbsubject.setSubjectId(tbcurriculum.getTbSubject().getSubjectId());
			
			tbcurriculum.setTbTown(tbtown);
			tbcurriculum.setTbBranchschool(tbbranchschool);
			tbcurriculum.setTbGrade(tbgrade);
			tbcurriculum.setTbSubject(tbsubject);
			tbcurriculum.setCourseName(tbcurriculum.getCourseName());
			tbcurriculum.setCourseScore(tbcurriculum.getCourseScore());
			tbcurriculum.setIsGood(tbcurriculum.getIsGood());
			tbcurriculum.setCourseIntroduce(tbcurriculum.getCourseIntroduce());
			tbcurriculum.setCourseGoodness(tbcurriculum.getCourseGoodness());
			tbcurriculum.setEnterNeed(tbcurriculum.getEnterNeed());
			tbcurriculum.setIsValid(1);
			tbcurriculum.setStudyGoal(tbcurriculum.getStudyGoal());
			tbcurriculum.setCourseBook(tbcurriculum.getCourseBook());
			tbcurriculum.setStartTerm(tbcurriculum.getStartTerm());
			tbcurriculum.setEndTerm(tbcurriculum.getEndTerm());
			tbcurriculum.setStudyTime(tbcurriculum.getStudyTime());
			tbcurriculum.setTotalStudyTime(tbcurriculum.getTotalStudyTime());
			tbcurriculum.setStudyCosts(tbcurriculum.getStudyCosts());
			tbcurriculum.setTextbookCost(tbcurriculum.getTextbookCost());
			tbcurriculum.setDirection(tbcurriculum.getDirection());
			tbcurriculum.setPhone(tbcurriculum.getPhone());
			Timestamp time = new Timestamp(new Date().getTime());
			tbcurriculum.setTime(time);
			tbcurriculum.setAlterTime(time);
			session.update(tbcurriculum);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbcurriculum;
	}
	// 添加老师信息
	public TbUserinfo addTeacherinfo(TbUserinfo tbuser,TbUser user) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbSchool tbschool = new TbSchool();
			tbschool.setSchoolId(tbuser.getTbSchool().getSchoolId());
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(tbuser.getTbBranchschool().getBranchSchoolId());
			
			Timestamp time = new Timestamp(new Date().getTime());
			
			tbuser.setTbSchool(tbschool);
			tbuser.setTbBranchschool(tbbranchschool);
			tbuser.setStudentClass(null);
			tbuser.setStudentSchool(null);
			tbuser.setUserInfoName(tbuser.getUserInfoName());
			tbuser.setUserInfoSex(tbuser.getUserInfoSex());
			tbuser.setUserInfoBirthday(tbuser.getUserInfoBirthday());
			tbuser.setUserInfoPhone(tbuser.getUserInfoPhone());
			tbuser.setUserInfoAvatar(tbuser.getUserInfoAvatar());
			tbuser.setUserInfoSign(tbuser.getUserInfoSign());
			tbuser.setUserInfoVip(tbuser.getUserInfoVip());
			tbuser.setGraduateSchool(tbuser.getGraduateSchool());
			tbuser.setUserInfoEmail(tbuser.getUserInfoEmail());
			tbuser.setWorkYear(tbuser.getWorkYear());
			tbuser.setUserInfoCourse(tbuser.getUserInfoCourse());
			tbuser.setUserInfoRoot(tbuser.getUserInfoRoot());
			tbuser.setIsValid(1);
			tbuser.setTime(time);
			tbuser.setAlterTime(time);

			user.setUserId(tbuser.getUserInfoId());
			user.setTbUserinfo(tbuser);
			user.setUserName(user.getUserName());
			user.setUserPassword("000000");
			user.setIsValid(1);
			user.setTime(time);
			user.setAlterTime(time);
			
			Ofuser ofuser = new Ofuser();
			ofuser.setUsername(user.getUserName());
			ofuser.setPlainPassword("111111");
			ofuser.setName(tbuser.getUserInfoName());
			ofuser.setEmail(tbuser.getUserInfoEmail());
			ofuser.setCreationDate(Long.toString(new Date().getTime()));
			ofuser.setModificationDate("0");
			
			
			session.save(tbuser);
			session.save(user);
			session.save(ofuser);
			
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbuser;
	}
	
	// 添加分校校长
	public TbUserinfo addPrincipal(TbUserinfo tbUserinfo,TbUser user) {
			FindUserDao fud = new FindUserDao();
			if(null == fud.getTbUser(user.getUserName())){
			Session session = HibernateSessionFactory.getSession();
			Transaction t = session.beginTransaction();
		try {
				TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
				TbSchool tbschool = new TbSchool();
				tbschool.setSchoolId(tbadmin.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = new TbBranchschool();
				tbbranchschool.setBranchSchoolId(tbUserinfo.getTbBranchschool().getBranchSchoolId());
				Timestamp time = new Timestamp(new Date().getTime());
			
				tbUserinfo.setTbSchool(tbschool);
				tbUserinfo.setTbBranchschool(tbbranchschool);
				tbUserinfo.setStudentClass(null);
				tbUserinfo.setStudentSchool(null);
				tbUserinfo.setUserInfoId(tbUserinfo.getUserInfoId());
				tbUserinfo.setUserInfoName(tbUserinfo.getUserInfoName());
				tbUserinfo.setUserInfoSex(tbUserinfo.getUserInfoSex());
				tbUserinfo.setUserInfoBirthday(tbUserinfo.getUserInfoBirthday());
				tbUserinfo.setUserInfoPhone(tbUserinfo.getUserInfoPhone());
				tbUserinfo.setUserInfoAvatar(tbUserinfo.getUserInfoAvatar());
				tbUserinfo.setUserInfoSign(tbUserinfo.getUserInfoSign());
				tbUserinfo.setUserInfoVip(tbUserinfo.getUserInfoVip());
				tbUserinfo.setGraduateSchool(tbUserinfo.getGraduateSchool());
				tbUserinfo.setUserInfoEmail(tbUserinfo.getUserInfoEmail());
				tbUserinfo.setWorkYear(tbUserinfo.getWorkYear());
				tbUserinfo.setUserInfoCourse(tbUserinfo.getUserInfoCourse());
				tbUserinfo.setUserInfoRoot(0);
				tbUserinfo.setIsValid(1);
				tbUserinfo.setTime(time);
				tbUserinfo.setAlterTime(time);
				
	
				TbUserinfo tbuserinfo2 = new TbUserinfo();
				tbuserinfo2.setTbSchool(tbschool);
				tbuserinfo2.setTbBranchschool(tbbranchschool);
				tbuserinfo2.setStudentClass(null);
				tbuserinfo2.setStudentSchool(null);
				tbuserinfo2.setUserInfoId(tbUserinfo.getUserInfoId());
				tbuserinfo2.setUserInfoName(tbUserinfo.getUserInfoName());
				tbuserinfo2.setUserInfoSex(tbUserinfo.getUserInfoSex());
				tbuserinfo2.setUserInfoBirthday(tbUserinfo.getUserInfoBirthday());
				tbuserinfo2.setUserInfoPhone(tbUserinfo.getUserInfoPhone());
				tbuserinfo2.setUserInfoAvatar(tbUserinfo.getUserInfoAvatar());
				tbuserinfo2.setUserInfoSign(tbUserinfo.getUserInfoSign());
				tbuserinfo2.setUserInfoVip(tbUserinfo.getUserInfoVip());
				tbuserinfo2.setGraduateSchool(tbUserinfo.getGraduateSchool());
				tbuserinfo2.setUserInfoEmail(tbUserinfo.getUserInfoEmail());
				tbuserinfo2.setWorkYear(tbUserinfo.getWorkYear());
				tbuserinfo2.setUserInfoCourse(tbUserinfo.getUserInfoCourse());
				tbuserinfo2.setUserInfoRoot(0);
				tbuserinfo2.setIsValid(0);
				tbuserinfo2.setTime(time);
				tbuserinfo2.setAlterTime(time);
				
				user.setUserId(tbUserinfo.getUserInfoId());
				user.setTbUserinfo(tbUserinfo);
				user.setUserName(user.getUserName());
				user.setUserPassword("000000");
				user.setIsValid(1);
				user.setTime(time);
				user.setAlterTime(time);
				
				TbUser tbuser2 = new TbUser();
				tbuser2.setUserId(tbuserinfo2.getUserInfoId());
				tbuser2.setTbUserinfo(tbuserinfo2);
				tbuser2.setUserName(user.getUserName()+"0");
				tbuser2.setUserPassword("000000");
				tbuser2.setIsValid(1);
				tbuser2.setTime(time);
				tbuser2.setAlterTime(time);
				
				
				Ofuser ofuser = new Ofuser();
				ofuser.setUsername(user.getUserName());
				ofuser.setPlainPassword("111111");
				ofuser.setName(tbUserinfo.getUserInfoName());
				ofuser.setEmail(tbUserinfo.getUserInfoEmail());
				ofuser.setCreationDate(Long.toString(new Date().getTime()));
				ofuser.setModificationDate("0");
				
				Ofuser ofuser2 = new Ofuser();
				ofuser2.setUsername(tbuser2.getUserName());
				ofuser2.setPlainPassword("111111");
				ofuser2.setName(tbuserinfo2.getUserInfoName());
				ofuser2.setEmail(tbuserinfo2.getUserInfoEmail());
				ofuser2.setCreationDate(Long.toString(new Date().getTime()));
				ofuser2.setModificationDate("0");
				
				session.save(tbUserinfo);
				//session.save(tbuserinfo2);
				session.save(user);
				//session.save(tbuser2);
				session.save(ofuser);
				//session.save(ofuser2);
				
				t.commit();
			
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		}else{
			tbUserinfo = null;
		}
		return tbUserinfo;
	}
	
	// 修改分校校长
	public TbUserinfo updatePrincipal(TbUserinfo tbUserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			 TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
			TbSchool tbschool = new TbSchool();
			tbschool.setSchoolId(tbadmin.getTbSchool().getSchoolId());
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(tbUserinfo.getTbBranchschool().getBranchSchoolId());
			Timestamp time = new Timestamp(new Date().getTime());
		
			tbUserinfo.setTbSchool(tbschool);
			tbUserinfo.setTbBranchschool(tbbranchschool);
			tbUserinfo.setStudentClass(null);
			tbUserinfo.setStudentSchool(null);
			tbUserinfo.setUserInfoId(tbUserinfo.getUserInfoId());
			tbUserinfo.setUserInfoName(tbUserinfo.getUserInfoName());
			tbUserinfo.setUserInfoSex(tbUserinfo.getUserInfoSex());
			tbUserinfo.setUserInfoBirthday(tbUserinfo.getUserInfoBirthday());
			tbUserinfo.setUserInfoPhone(tbUserinfo.getUserInfoPhone());
			tbUserinfo.setUserInfoAvatar(tbUserinfo.getUserInfoAvatar());
			tbUserinfo.setUserInfoSign(tbUserinfo.getUserInfoSign());
			tbUserinfo.setUserInfoVip(tbUserinfo.getUserInfoVip());
			tbUserinfo.setGraduateSchool(tbUserinfo.getGraduateSchool());
			tbUserinfo.setUserInfoEmail(tbUserinfo.getUserInfoEmail());
			tbUserinfo.setWorkYear(tbUserinfo.getWorkYear());
			tbUserinfo.setUserInfoCourse(tbUserinfo.getUserInfoCourse());
			tbUserinfo.setUserInfoRoot(0);
			tbUserinfo.setIsValid(1);
			tbUserinfo.setTime(time);
			tbUserinfo.setAlterTime(time);

			session.update(tbUserinfo);
			
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbUserinfo;
	}
	
	// 添加学校通告
	public TbSchoolnotice addSchoolnotice(TbSchoolnotice tbschoolnotice) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			TbSchool tbschool = new TbSchool();
			tbschool.setSchoolId(tbschoolnotice.getTbSchool().getSchoolId());
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(tbschoolnotice.getTbBranchschool().getBranchSchoolId());
			TbUserinfo tbuserinfo = new TbUserinfo();
			tbuserinfo.setUserInfoId(1);
			
			tbschoolnotice.setTbSchool(tbschool);
			tbschoolnotice.setTbBranchschool(tbbranchschool);
			tbschoolnotice.setTbUserinfo(tbuserinfo);
			tbschoolnotice.setSchnotTitle(tbschoolnotice.getSchnotTitle());
			tbschoolnotice.setSchnotContent(tbschoolnotice.getSchnotContent());
			tbschoolnotice.setAddTime(TWDataUtil.date(time));
			tbschoolnotice.setIsAllSchool(0);
			tbschoolnotice.setIsValid(1);
			
			tbschoolnotice.setTime(time);
			tbschoolnotice.setAlterTime(time);
			
			session.save(tbschoolnotice);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbschoolnotice;
	}
	
	// 添加班级通知
	public TbClassnotice addClassnotice(TbClassnotice tbclassnotice) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
//			TbUserinfo tbUserinfo = new FindUserDao().findByUserId(tbadmin.getIsValid());
			Criteria cc = session.createCriteria(TbUserinfo.class);
			cc.add(Restrictions.eq("userInfoId", tbadmin.getIsValid()));
			cc.setFetchMode("tbSchool", FetchMode.JOIN);
			cc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			TbUserinfo tbUserinfo = (TbUserinfo) cc.uniqueResult();
			TbSchool tbschool = new TbSchool();
			tbschool.setSchoolId(tbUserinfo.getTbSchool().getSchoolId());
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(tbUserinfo.getTbBranchschool().getBranchSchoolId());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String time = df.format(new Date());
//			Timestamp ts = Timestamp.valueOf(time);
			Timestamp ts = new Timestamp(new Date().getTime());
			TbCourse tbcourse = new TbCourse();
			tbcourse.setCourseId(tbclassnotice.getTbCourse().getCourseId());
			TbUserinfo tbuserinfo = new TbUserinfo();
			tbuserinfo.setUserInfoId(tbUserinfo.getUserInfoId());
			
			tbclassnotice.setTbSchool(tbschool);
			tbclassnotice.setTbBranchschool(tbbranchschool);
			tbclassnotice.setTbCourse(tbcourse);
			tbclassnotice.setTbUserinfo(tbuserinfo);
			tbclassnotice.setClanotTitle(tbclassnotice.getClanotTitle());
			tbclassnotice.setClanotAddTime(time);
			tbclassnotice.setClanotSige("");
			tbclassnotice.setClanotSelect(0);
			tbclassnotice.setClanotContent(tbclassnotice.getClanotContent());
			tbclassnotice.setIsValid(1);
			tbclassnotice.setTime(ts);
			tbclassnotice.setAlterTime(ts);
			
			session.save(tbclassnotice);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} 
		finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbclassnotice;
	}
	// 添加年段
	public TbGrade addGrade(TbGrade tbgrade) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			
			tbgrade.setTbSchool(null);
			tbgrade.setTbBranchschool(null);
			tbgrade.setGradeName(tbgrade.getGradeName());
			
			tbgrade.setIsValid(1);
			tbgrade.setTime(time);
			tbgrade.setAlterTime(time);
			
			session.save(tbgrade);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbgrade;
	}
	
	// 修改年段
	public TbGrade updateGrade(TbGrade tbgrade) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			
			tbgrade.setTbSchool(null);
			tbgrade.setTbBranchschool(null);
			tbgrade.setGradeName(tbgrade.getGradeName());
			
			tbgrade.setIsValid(1);
			tbgrade.setTime(time);
			tbgrade.setAlterTime(time);
			
			session.update(tbgrade);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbgrade;
	}
	
	
	
	// 录入信息
	public TbUserinfo addinfo(TbUserinfo tbuser,TbUserinfo tbuserinfo){
			Session session = HibernateSessionFactory.getSession();
			Transaction t = session.beginTransaction();
			try {
				TbSchool tbschool = new TbSchool();
				tbschool.setSchoolId(tbuser.getTbSchool().getSchoolId());
//				tbschool.setSchoolId(1);
				TbBranchschool tbbranchschool = new TbBranchschool();
				tbbranchschool.setBranchSchoolId(tbuser.getTbBranchschool().getBranchSchoolId());
//				tbbranchschool.setBranchSchoolId(1);
				tbuserinfo.setTbSchool(tbschool);
				tbuserinfo.setTbBranchschool(tbbranchschool);
				Timestamp time = new Timestamp(new Date().getTime());
				new TbUserinfo(tbschool, tbbranchschool, tbuser.getStudentClass(), 
						tbuser.getStudentSchool(), tbuser.getUserInfoName(), tbuser.getUserInfoSex(),
							tbuser.getUserInfoBirthday(), tbuser.getUserInfoPhone(), tbuser.getUserInfoAvatar(), 
							tbuser.getUserInfoSign(), tbuser.getUserInfoVip(), tbuser.getGraduateSchool(), tbuser.getUserInfoEmail(), 
							tbuser.getWorkYear(), tbuser.getUserInfoCourse(), tbuser.getUserInfoRoot(), 1, time, time);
				
				new TbUserinfo(tbschool, tbbranchschool, tbuserinfo.getStudentClass(), 
						tbuserinfo.getStudentSchool(), tbuserinfo.getUserInfoName(), tbuserinfo.getUserInfoSex(),
						tbuserinfo.getUserInfoBirthday(), tbuserinfo.getUserInfoPhone(), tbuserinfo.getUserInfoAvatar(), 
						tbuserinfo.getUserInfoSign(), tbuserinfo.getUserInfoVip(), tbuserinfo.getGraduateSchool(), tbuserinfo.getUserInfoEmail(), 
						tbuserinfo.getWorkYear(), tbuserinfo.getUserInfoCourse(), tbuserinfo.getUserInfoRoot(), 1,time, time);
				
				TbUser user = new TbUser();
				user.setUserId(tbuser.getUserInfoId());
				user.setTbUserinfo(tbuser);
				user.setUserName(tbuser.getUserInfoPhone());
				user.setUserPassword("000000");
				user.setIsValid(1);
				user.setTime(time);
				user.setAlterTime(time);
				
				TbUser users = new TbUser();
				users.setUserId(tbuserinfo.getUserInfoId());
				users.setTbUserinfo(tbuserinfo);
				users.setUserName(tbuserinfo.getUserInfoEmail());
				users.setUserPassword("000000");
				users.setIsValid(1);
				users.setTime(time);
				users.setAlterTime(time);
				
				TbRelation tbrelation = new TbRelation();
				tbrelation.setTbUserinfoByTbUserInfoId(tbuser);
				tbrelation.setTbUserinfoByUserInfoId(tbuserinfo);
				tbrelation.setIsValid(1);
				tbrelation.setTime(time);
				tbrelation.setAlterTime(time);
				
				Ofuser ofuser = new Ofuser();
				ofuser.setUsername("0");
				ofuser.setPlainPassword("111111");
				ofuser.setName("0");
				ofuser.setEmail("0");
				ofuser.setCreationDate(Long.toString(new Date().getTime()));
				ofuser.setModificationDate("0");
				
				Ofuser ofuser1 = new Ofuser();
				ofuser1.setUsername("1");
				ofuser1.setPlainPassword("111111");
				ofuser1.setName("1");
				ofuser1.setEmail("1");
				ofuser1.setCreationDate(Long.toString(new Date().getTime()));
				ofuser1.setModificationDate("0");
				
				session.save(tbuser);
				session.save(tbuserinfo);
				session.save(user);
				session.save(users);
				session.save(tbrelation);
				session.save(ofuser);
				session.save(ofuser1);
				
				t.commit();
			} catch (HibernateException e) {
				t.rollback();
				e.printStackTrace();
			} finally {
				if (null != session && session.isOpen())
					session.close();
			}
			return tbuser;
		}
	
	// 添加课程数据
	public TbLesson addLesson(TbLesson tblesson) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			TbBranchschool tbbranchschool = new TbBranchschool();
			tbbranchschool.setBranchSchoolId(tblesson.getTbBranchschool().getBranchSchoolId());
			TbSubject tbsubject = new TbSubject();
			tbsubject.setSubjectId(tblesson.getTbSubject().getSubjectId());
			tblesson.setTbSubject(tbsubject);
			tblesson.setDefineSort(tblesson.getDefineSort());
			tblesson.setDefineSort2(tblesson.getDefineSort2());
			tblesson.setLessonName(tblesson.getLessonName());
			tblesson.setIsValid(1);
			tblesson.setTime(time);
			tblesson.setAlterTime(time);
			
			session.save(tblesson);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tblesson;
	}
	// 修改课程数据
	public TbLesson updateTbLesson(TbLesson tblesson) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Timestamp time = new Timestamp(new Date().getTime());
			TbSubject tbsubject = new TbSubject();
			tbsubject.setSubjectId(tblesson.getTbSubject().getSubjectId());
			
			tblesson.setTbSubject(tbsubject);
			tblesson.setDefineSort(tblesson.getDefineSort());
			tblesson.setDefineSort2(tblesson.getDefineSort2());
			tblesson.setLessonName(tblesson.getLessonName());
			
			tblesson.setIsValid(1);
			tblesson.setTime(time);
			tblesson.setAlterTime(time);
			
			session.update(tblesson);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tblesson;
	}
}
