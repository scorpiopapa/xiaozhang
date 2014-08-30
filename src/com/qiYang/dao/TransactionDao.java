package com.qiYang.dao;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qiYang.model.Ofuser;
import com.qiYang.model.TbBranchschool02;
import com.qiYang.model.TbBranchschoolPopularity;
import com.qiYang.model.TbCoupon;
import com.qiYang.model.TbCurriculum02;
import com.qiYang.model.TbCurriculumCopy;
import com.qiYang.model.TbCurriculumPopularity;
import com.qiYang.model.TbOrder;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchool02;
import com.qiYang.model.TbSchoolPhoto;
import com.qiYang.model.TbSchoolPopularity;
import com.qiYang.model.TbTeam;
import com.qiYang.model.TbTeamCopy;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfo2;
import com.qiYang.util.HibernateSessionFactory;

public class TransactionDao {
	private static Logger log = Logger.getLogger(TransactionDao.class);

	public Integer register(String phone, String password, String email,
			Integer gradeId, Integer cityId) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			long longTime = System.currentTimeMillis();
			Timestamp nowTime = new Timestamp(longTime);
			TbUserinfo tbUserinfo = new TbUserinfo(null, null, "", "", "", "",
					"男", null, phone, "default.png", "", null, "", email, null,
					"", 2, 1, nowTime, nowTime);
			TbUser tbUser = new TbUser(tbUserinfo, phone, password, 0, nowTime,
					nowTime);
			Ofuser ofuser = new Ofuser(phone, "111111", null, phone, email,
					Long.toString(longTime), "0");
			Integer userinfoId = (Integer) session.save(tbUserinfo);
			session.save(ofuser);
			session.save(tbUser);
			TbUserinfo2 tbUserinfo2 = new TbUserinfo2(null, userinfoId,
					gradeId, cityId, 1, nowTime, nowTime, nowTime, null, null);
			session.save(tbUserinfo2);
			t.commit();
			return userinfoId;
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			return 0;
		}

	}

	public void addCurriculumPopularity(TbCurriculumPopularity popularity,
			TbCurriculum02 curriculum02) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(popularity);
			Integer currId = curriculum02.getId();
			TbCurriculum02 tbCurriculum02 = (TbCurriculum02) session.get(
					TbCurriculum02.class, currId);
			tbCurriculum02.setQuantity(curriculum02.getQuantity());
			session.update(tbCurriculum02);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
		} catch (Exception e) {
			t.rollback();
			log.error(e);
			;
		}
	}

	public void addTbBranchschoolPopularity(
			TbBranchschoolPopularity popularity, TbBranchschool02 obj02) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(popularity);
			Integer id = obj02.getId();
			TbBranchschool02 obj = (TbBranchschool02) session.get(
					TbBranchschool02.class, id);
			obj.setQuantity(obj02.getQuantity());
			session.update(obj);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
		} catch (Exception e) {
			t.rollback();
			log.error(e);
			;
		}
	}

	public void addTbSchoolPopularity(TbSchoolPopularity popularity,
			TbSchool02 obj02) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(popularity);
			Integer id = obj02.getId();
			TbSchool02 obj = (TbSchool02) session.get(TbSchool02.class, id);
			obj.setQuantity(obj02.getQuantity());
			session.update(obj);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
		} catch (Exception e) {
			t.rollback();
			log.error(e);
			;
		}
	}

	public Boolean registerChild(String phone, String password, String email,
			Integer gradeId, String sex, Integer parentInt) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			long longTime = System.currentTimeMillis();
			Timestamp nowTime = new Timestamp(longTime);
			TbUserinfo tbUserinfo = new TbUserinfo(null, null, "", "", "", "",
					sex, null, phone, "default.png", "", null, "", email, null,
					"", 3, 1, nowTime, nowTime);
			Ofuser ofuser = new Ofuser(phone, "111111", null, phone, email,
					Long.toString(longTime), "0");
			Integer userinfoId = (Integer) session.save(tbUserinfo);
			tbUserinfo.setUserInfoId(userinfoId);
			TbUser tbUser = new TbUser(tbUserinfo, phone, password, 0, nowTime,
					nowTime);
			session.save(tbUser);
			session.save(ofuser);
			Query query = session
					.createQuery("update TbUserinfo2 set gradeId=?,studentId=? where userinfoId=?");
			query.setParameter(0, gradeId);
			query.setParameter(1, userinfoId);
			query.setParameter(2, parentInt);
			query.executeUpdate();
			TbUserinfo parent = new TbUserinfo();
			parent.setUserInfoId(parentInt);
			TbRelation tbRelation = new TbRelation(tbUserinfo, parent, 1, null,
					null, nowTime, nowTime);
			session.save(tbRelation);
			t.commit();
			return true;
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
			return false;
		}
	}

	public Boolean addOrder(TbOrder tbOrder, TbCoupon tbCoupon,
			TbCurriculumCopy tbCurriculumCopy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Integer curCopyId = null;
			if (tbCurriculumCopy != null
					&& tbCurriculumCopy.getIsValid() != null) {
				curCopyId = (Integer) session.save(tbCurriculumCopy);
			} else {
				return false;
			}
			tbOrder.setCurriculumId(curCopyId);
			Integer orderId = (Integer) session.save(tbOrder);
			if (tbCoupon != null) {
				tbCoupon.setOrderId(orderId);
				tbCoupon.setCurriculumId(curCopyId);
				session.save(tbCoupon);
			}
			t.commit();
			return true;
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
			return false;
		}
	}

	public Boolean addOrder(TbOrder tbOrder, TbCurriculumCopy tbCurriculumCopy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Integer curCopyId = null;
			if (tbCurriculumCopy != null
					&& tbCurriculumCopy.getIsValid() != null) {
				curCopyId = (Integer) session.save(tbCurriculumCopy);
			} else {
				return false;
			}
			tbOrder.setCurriculumId(curCopyId);
			session.save(tbOrder);
			t.commit();
			return true;
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
			return false;
		}
	}

	public Boolean addOrder(TbOrder tbOrder, TbCoupon tbCoupon,
			TbTeamCopy tbTeamCopy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Integer teamCopyId = null;
			if (tbTeamCopy != null && tbTeamCopy.getIsValid() != null) {
				teamCopyId = (Integer) session.save(tbTeamCopy);
			} else {
				return false;
			}
			tbOrder.setTeamId(teamCopyId);
			Integer orderId = (Integer) session.save(tbOrder);
			if (tbCoupon != null) {
				tbCoupon.setOrderId(orderId);
				tbCoupon.setTeamId(teamCopyId);
				session.save(tbCoupon);
			}
			t.commit();
			return true;
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
			return false;
		}

	}

	public Boolean addOrder(TbOrder tbOrder, TbTeamCopy tbTeamCopy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Integer teamCopyId = null;
			if (tbTeamCopy != null && tbTeamCopy.getIsValid() != null) {
				teamCopyId = (Integer) session.save(tbTeamCopy);
			} else {
				return false;
			}
			tbOrder.setTeamId(teamCopyId);
			session.save(tbOrder);
			t.commit();
			return true;
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
			return false;
		}

	}

	public Boolean saveRegisterTbSchool(TbSchool tbSchool,
			TbSchool02 tbSchool02, TbUserinfo tbUserinfo, TbUser tbUser,
			Ofuser ofuser, TbSchoolPhoto[] tbSchoolPhotos) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			Integer schoolId = (Integer) session.save(tbSchool);
			if (tbSchoolPhotos != null && tbSchoolPhotos.length != 0) {
				for (TbSchoolPhoto tbSchoolPhoto : tbSchoolPhotos) {
					tbSchoolPhoto.setSchoolId(schoolId);
					session.save(tbSchoolPhoto);
				}
			}
			tbSchool02.setSchoolId(schoolId);
			session.save(tbSchool02);
			session.save(tbUserinfo);
			session.save(tbUser);
			session.save(ofuser);
			t.commit();
			return true;
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}

	}

	public Boolean addTbCoupon(TbCoupon tbCoupon, TbOrder tbOrder, TbTeam tbTeam) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tbCoupon);
			Object obj = session.get(tbOrder.getClass(), tbOrder.getId());
			obj = new BaseDao().getFieldVlaue(obj, tbOrder);
			session.update(obj);
			if (tbTeam != null) {
				Object obj1 = session.get(tbTeam.getClass(), tbTeam.getId());
				obj1 = new BaseDao().getFieldVlaue(obj1, tbTeam);
				session.update(obj1);
			}
			t.commit();
			return true;
		} catch (HibernateException e) {
			t.rollback();
			log.error(e);
			;
			return false;
		}

	}
}
