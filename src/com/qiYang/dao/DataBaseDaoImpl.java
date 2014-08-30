package com.qiYang.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.qiYang.model.TbAttendance;
import com.qiYang.model.TbBills;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbClassnotice;
import com.qiYang.model.TbComplain;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbSchoolnotice;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;
import com.qiYang.util.HibernateSessionFactory;
import com.qiYang.util.Page;

public class DataBaseDaoImpl implements DataBaseDao{

	Logger logger = Logger.getLogger(DataBaseDaoImpl.class);

	// className为model包里面的类名，map存条件就是sql语句中的where key=value;
	public <T> List<T> getObjects( Class<T> clazz,
			Map<String, Object> map) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		criteria = confirmCriteriaClass(session, clazz, map,null,null, null);
		  List<T> list = criteria.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}
	public <T> Integer checkObjects( Class<T> clazz,
			Map<String, Object> map) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		criteria = confirmCriteriaClass(session, clazz, map,null,null, null);
		List list = criteria.list();
		if(list==null||list.isEmpty())
			return 0;
		if (null != session && session.isOpen())
			session.close();
		return list.size();
	}

	// className为model包里面的类名，map存条件就是sql语句中的where key=value;maple是where
	// key<=value; mapge等同where key>=value；
	public <T> List<T> getObjects(Class<T> clazz,
			Map<String, Object> map,Map<String, Object> mapne, Map<String, Object> maple,
			Map<String, Object> mapge) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		criteria = confirmCriteriaClass( session, clazz, map,mapne,
				maple, mapge);
		List<T> list = criteria.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}
	

	// className为model包里面的类名，通过ID找对象，跟方法getObjects类似
	public <T> T getObject(Class<T> clazz, Map<String, Object> mapId) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		criteria = confirmCriteriaClass(session, clazz, mapId,null,
				null, null);
		Object obj = criteria.uniqueResult();
		if (null != session && session.isOpen())
			session.close();
		if(obj==null){
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return (T) obj;
	}
	public <T> Long getCount(Class<T> clazz,
			Map<String, Object> map,Map<String, Object> mapne, Map<String, Object> maple,
			Map<String, Object> mapge,String distinct) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		criteria = confirmCriteriaClass(session, clazz, map,mapne,
				maple, mapge);
		Criteria setProjection=null;
		if(StringUtils.isBlank(distinct)){
		setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		}else{
			setProjection = criteria.setProjection(Projections.projectionList().add(Projections.countDistinct(distinct)));
		}
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		if (null != session && session.isOpen())
			session.close();
		return allcount;
	}
	public <T> Long getCount(Class<T> clazz,
			Map<String, Object> map) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		criteria = confirmCriteriaClass(session, clazz, map,null,
				null, null);
		Criteria setProjection=null;
		setProjection = criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		if (null != session && session.isOpen())
			session.close();
		return allcount;
	}
	// className为model包里面的类名，map存条件就是sql语句中的where key=value;page就是util的分页类
	public <T> Page getObjectPage(Page page, Class<T> clazz,
			Map<String, Object> map) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		Criteria pageList = null;
		criteria = confirmCriteriaClass(session, clazz, map,null,
				null, null);
		pageList = confirmCriteriaClass(session, clazz, map,null,
				null, null);
		Criteria setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		Object obj = setProjection.uniqueResult();
		if(obj==null)
			obj=new Object();
		Long allcount = ((Integer)obj).longValue();
		pageList.setFirstResult((page.getCurrentPage() - 1)
				* page.getCountPerPage());
		pageList.setMaxResults(page.getCountPerPage());
		List<T> currentlist = pageList.list();
		Long allpages = 0l;
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
		page.setAllCount(allcount.intValue());
		page.setAllPages(allpages.intValue());
		page.setCurrentList(currentlist);
		if (null != session && session.isOpen())
			session.close();
		return page;
	}

	// className为model包里面的类名，map存条件就是sql语句中的where key=value;maple是where
	// key<=value; mapge等同where key>=value；page就是util的分页类
	public <T> Page getObjectPage(Page page, Class<T> clazz,
			Map<String, Object> map,Map<String, Object> mapne, Map<String, Object> maple,
			Map<String, Object> mapge) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		Criteria pageList = null;
		criteria = confirmCriteriaClass(session, clazz, map,mapne,
				maple, mapge);
		pageList = confirmCriteriaClass(session, clazz, map,mapne,
				maple, mapge);
		Criteria setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		pageList.setFirstResult((page.getCurrentPage() - 1)
				* page.getCountPerPage());
		pageList.setMaxResults(page.getCountPerPage());
		List<T> currentlist = pageList.list();
		Long allpages = 0l;
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
		page.setAllCount(allcount.intValue());
		page.setAllPages(allpages.intValue());
		page.setCurrentList(currentlist);
		if (null != session && session.isOpen())
			session.close();
		return page;
	}
	// className为model包里面的类名，map存条件就是sql语句中的where key=value;maple是where
	// key<=value; mapge等同where key>=value；page就是util的分页类
	public <T> Page getObjectPage(Page page, Class<T> clazz,
			Map<String, Object> map,Map<String, Object> mapne, Map<String, Object> maple,
			Map<String, Object> mapge,Map<String, String> maplike) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		Criteria pageList = null;
		criteria = confirmCriteriaClass(session, clazz, map,mapne,
				maple, mapge,maplike);
		pageList = confirmCriteriaClass(session, clazz, map,mapne,
				maple, mapge,maplike);
		Criteria setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		pageList.setFirstResult((page.getCurrentPage() - 1)
				* page.getCountPerPage());
		pageList.setMaxResults(page.getCountPerPage());
		List<T> currentlist = pageList.list();
		Long allpages = 0l;
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
				page.setAllCount(allcount.intValue());
				page.setAllPages(allpages.intValue());
				page.setCurrentList(currentlist);
				if (null != session && session.isOpen())
					session.close();
				return page;
	}

	// 获得该表格全部的记录
	public <T> List<T> getAllTable(Class<T> clazz, String orderRowName) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = null;
		criteria = confirmCriteriaClass(session, clazz, null,null,
				null, null);
		if (orderRowName != null)
			criteria.addOrder(Order.asc(orderRowName));
		List<T> list = criteria.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}

	// 给criteria添加条件
	private <T> Criteria confirmCriteriaClass(Session session,
			Class<T> clazz, Map<String, Object> map,Map<String, Object> mapne,
			Map<String, Object> maple, Map<String, Object> mapge) {
		Criteria criteria = session.createCriteria(clazz);
//		System.out.println("distinctColumn:----->"+distinctColumn);
//		if(distinctColumn!=null)
//		criteria = criteria.setProjection(Projections.distinct(Projections.property(distinctColumn)));
		if (map != null) {
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.eq(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					if ("正".equals(key))
						criteria.addOrder(Order.asc(objectStr));
					else if ("倒".equals(key))
						criteria.addOrder(Order.desc(objectStr));
					else{
						if(key.startsWith("FetchMode"))
							criteria.setFetchMode(objectStr, FetchMode.JOIN);
						else
						criteria.add(Restrictions.eq(key, objectStr));
					}
				} else if (val instanceof Date) {
					Date objectDate = (Date) val;
					criteria.add(Restrictions.le(key, objectDate));
					criteria.add(Restrictions.ge(key, objectDate));
				} else if (val instanceof Timestamp) {
					Timestamp objectTimestamp = (Timestamp) val;
					criteria.add(Restrictions.le(key, objectTimestamp));
					criteria.add(Restrictions.ge(key, objectTimestamp));
				} else {
					criteria.add(Restrictions.eq(key, val));
				}
			}
		}
		if (mapne != null) {
			Iterator iter = mapne.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.ne(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					criteria.add(Restrictions.ne(key, objectStr));
				}else {
					criteria.add(Restrictions.ne(key, val));
				}
			}
		}
		if (mapge != null) {
			Iterator iter = mapge.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.ge(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					criteria.add(Restrictions.ge(key, objectStr));
				} else if (val instanceof Date) {
					Date objectDate = (Date) val;
					criteria.add(Restrictions.ge(key, objectDate));
				} else if (val instanceof Timestamp) {
					Timestamp objectTimestamp = (Timestamp) val;
					criteria.add(Restrictions.ge(key, objectTimestamp));
				} else {
					criteria.add(Restrictions.ge(key, val));
				}
			}
		}
		if (maple != null) {
			Iterator iter = maple.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.le(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					criteria.add(Restrictions.le(key, objectStr));
				} else if (val instanceof Date) {
					Date objectDate = (Date) val;
					criteria.add(Restrictions.le(key, objectDate));
				} else if (val instanceof Timestamp) {
					Timestamp objectTimestamp = (Timestamp) val;
					criteria.add(Restrictions.le(key, objectTimestamp));
				} else {
					criteria.add(Restrictions.le(key, val));
				}
			}
		}
		return criteria;
	}
	// 给criteria添加条件
	private <T> Criteria confirmCriteriaClass(Session session,
			Class<T> clazz, Map<String, Object> map,Map<String, Object> mapne,
			Map<String, Object> maple, Map<String, Object> mapge,Map<String, String> maplike) {
		Criteria criteria = session.createCriteria(clazz);
//		System.out.println("distinctColumn:----->"+distinctColumn);
//		if(distinctColumn!=null)
//		criteria = criteria.setProjection(Projections.distinct(Projections.property(distinctColumn)));
		if (map != null) {
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.eq(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					if ("正".equals(key))
						criteria.addOrder(Order.asc(objectStr));
					else if ("倒".equals(key))
						criteria.addOrder(Order.desc(objectStr));
					else{
						if(key.startsWith("FetchMode"))
							criteria.setFetchMode(objectStr, FetchMode.JOIN);
						else
							criteria.add(Restrictions.eq(key, objectStr));
					}
				} else if (val instanceof Date) {
					Date objectDate = (Date) val;
					criteria.add(Restrictions.le(key, objectDate));
					criteria.add(Restrictions.ge(key, objectDate));
				} else if (val instanceof Timestamp) {
					Timestamp objectTimestamp = (Timestamp) val;
					criteria.add(Restrictions.le(key, objectTimestamp));
					criteria.add(Restrictions.ge(key, objectTimestamp));
				} else {
					criteria.add(Restrictions.eq(key, val));
				}
			}
		}
		if (mapne != null) {
			Iterator iter = mapne.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.ne(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					criteria.add(Restrictions.ne(key, objectStr));
				}else {
					criteria.add(Restrictions.ne(key, val));
				}
			}
		}
		if (mapge != null) {
			Iterator iter = mapge.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.ge(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					criteria.add(Restrictions.ge(key, objectStr));
				} else if (val instanceof Date) {
					Date objectDate = (Date) val;
					criteria.add(Restrictions.ge(key, objectDate));
				} else if (val instanceof Timestamp) {
					Timestamp objectTimestamp = (Timestamp) val;
					criteria.add(Restrictions.ge(key, objectTimestamp));
				} else {
					criteria.add(Restrictions.ge(key, val));
				}
			}
		}
		if (maple != null) {
			Iterator iter = maple.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					Integer objectInt = (Integer) val;
					criteria.add(Restrictions.le(key, objectInt));
				} else if (val instanceof String) {
					String objectStr = (String) val;
					criteria.add(Restrictions.le(key, objectStr));
				} else if (val instanceof Date) {
					Date objectDate = (Date) val;
					criteria.add(Restrictions.le(key, objectDate));
				} else if (val instanceof Timestamp) {
					Timestamp objectTimestamp = (Timestamp) val;
					criteria.add(Restrictions.le(key, objectTimestamp));
				} else {
					criteria.add(Restrictions.le(key, val));
				}
			}
		}
		if (maplike != null) {
			Iterator iter = maplike.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				criteria.add(Restrictions.like(key, "%"+val+"%"));
			}
		}
		return criteria;
	}

	// 群删事务处理
	public <T> String deleteObjects(Class<T> clazz, Integer... ids) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (Integer id : ids) {
				session.delete(session.get(clazz, id));
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e);
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}


	// 添加方法
	public String addObject(Object...objs) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (Object object : objs) {
				if(object==null)
					continue;
				session.save(object);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e);
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	// 添加方法
	public String addObjects(Object obj2,Object...objs) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			if(obj2!=null){
			session.save(obj2);
			}else{
				return "fail";
			}
			for (Object object : objs) {
				if(object==null)
					continue;
				session.save(object);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e);
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	public String addObjectList(List list) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (Object object : list) {
				if(object==null)
					continue;
				session.save(object);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e);
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	//部分修改方法
	public <T> String updateObjects(Map<String, Object> mapSet,Map<String, Object> mapWhere,Class<T>...clazz) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Timestamp time = new Timestamp(new Date().getTime());
		try {for (Class<T> class1 : clazz) {
			String hql = "update "+class1.getSimpleName()+" t set t.alterTime= '" + time + "'";
			hql=updateHql(hql, mapSet, mapWhere);
			session.createQuery(hql).executeUpdate();
		}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e);
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}

	// 修改方法
	public <T> String updateObject(T... t) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (T t2 : t) {
				updateBefore(session, t2);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e);
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	// 修改条件判断
	public String updateHql(String hql,Map<String, Object> mapSet,Map<String, Object> mapWhere) {
		if (mapSet != null) {
			Iterator iter = mapSet.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					if(val!=null){
					Integer objectInt = (Integer) val;
					hql+=", t."+key+"="+objectInt;
					}
				} else {
					if(val!=null){
						hql+=", t."+key+"='"+val+"'";
					}
				} 
			}
		}
		if(mapWhere!=null){
			hql+=" where 1=1";
			Iterator iter = mapWhere.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Integer) {
					if(val!=null){
					Integer objectInt = (Integer) val;
					hql+=" and t."+key+"="+objectInt;
					}
				} else {
					if(val!=null){
						hql+=" and t."+key+"='"+val+"'";
					}
				} 
			}
		}
		return hql;
	}
	// 修改条件判断
	public <T> void updateBefore(Session session, T t) {
		if (t == null)
			return ;
		String hql = "";
		Timestamp time = new Timestamp(new Date().getTime());
		if (t instanceof TbSchoolnotice) {
			TbSchoolnotice schoolnotice = (TbSchoolnotice) t;
			hql = "update "+t.getClass().getSimpleName()+" t set t.alterTime= '" + time + "'";
			if(StringUtils.isNotBlank(schoolnotice.getSchnotTitle()))
				hql+=", t.schnotTitle ='"+schoolnotice.getSchnotTitle()+"'";
			if(StringUtils.isNotBlank(schoolnotice.getSchnotContent()))
				hql+=", t.schnotContent ='"+schoolnotice.getSchnotContent()+"'";
			if(schoolnotice.getTbUserinfo()!=null&&schoolnotice.getTbUserinfo().getUserInfoId()!=null)
				hql+=", t.tbUserinfo ='"+schoolnotice.getTbUserinfo().getUserInfoId()+"'";
			hql+="where t.schoolNoticeId ="+schoolnotice.getSchoolNoticeId();
			session.createQuery(hql).executeUpdate();
		} else if (t instanceof TbAttendance) {
			TbAttendance attendance = (TbAttendance) t;
			if (attendance.getIsLate() != null
					&& attendance.getAttendanceId() != null)
				hql = "update TbAttendance t set t.isLate = '"
						+ attendance.getIsLate() + "',t.alterTime = '" + time
						+ "' where t.attendanceId = "
						+ attendance.getAttendanceId();
			session.createQuery(hql).executeUpdate();
		} else if (t instanceof TbBranchschool) {
			TbBranchschool branchschool = (TbBranchschool) t;
			if (branchschool.getBranchSchoolId() == null)
				return;
			TbBranchschool tbBranchschool = (TbBranchschool) session.get(
					TbBranchschool.class, branchschool.getBranchSchoolId());
			if (branchschool.getTbTown() != null
					&& branchschool.getTbTown().getTownId() != null)
				tbBranchschool.setTbTown(branchschool.getTbTown());
			if (branchschool.getTbSchool() != null
					&& branchschool.getTbSchool().getSchoolId() != null)
				tbBranchschool.setTbSchool(branchschool.getTbSchool());
			if (StringUtils.isNotBlank(branchschool.getBraschName()))
				tbBranchschool.setBraschName(branchschool.getBraschName());
			if (StringUtils.isNotBlank(branchschool.getBraschMinName()))
				tbBranchschool
						.setBraschMinName(branchschool.getBraschMinName());
			if (StringUtils.isNotBlank(branchschool.getBraschAddress()))
				tbBranchschool
						.setBraschAddress(branchschool.getBraschAddress());
			if (StringUtils.isNotBlank(branchschool.getBraschPhone()))
				tbBranchschool.setBraschPhone(branchschool.getBraschPhone());
			if (StringUtils.isNotBlank(branchschool.getBraschBusWay()))
				tbBranchschool.setBraschBusWay(branchschool.getBraschBusWay());
			if (branchschool.getIsValid() != null)
				tbBranchschool.setIsValid(branchschool.getIsValid());
			tbBranchschool.setAlterTime(time);
		} else if (t instanceof TbBills) {
			TbBills bills = (TbBills) t;
			hql = "update TbBills t set t.alterTime= '" + time + "'";
			if (bills.getTbUserinfo() != null
					&& bills.getTbUserinfo().getUserInfoId() != null)
				hql += ",t.tbUserinfo = '"
						+ bills.getTbUserinfo().getUserInfoId() + "'";
			if (bills.getTbBranchschool() != null
					&& bills.getTbBranchschool().getBranchSchoolId() != null)
				hql += ",t.tbBranchschool = '"
						+ bills.getTbBranchschool().getBranchSchoolId() + "'";
			if (bills.getTotalMonth() != null)
				hql += ",t.totalMonth = '" + bills.getTotalMonth() + "'";
			if (bills.getTradeStatus() != null)
				hql += " , t.tradeStatus = '" + bills.getTradeStatus() + "'";
			if (bills.getTotalFee() != null)
				hql += " , t.totalFee = '" + bills.getTotalFee() + "'";
			if (StringUtils.isNotBlank(bills.getGoodsName()))
				hql += ", t.goodsName = '" + bills.getGoodsName() + "'";
			if (StringUtils.isNotBlank(bills.getGoodsDescription()))
				hql += ", t.goodsDescription = '" + bills.getGoodsDescription()
						+ "'";
			if (StringUtils.isNotBlank(bills.getOutTradeNo()))
				hql += ", t.outTradeNo = '" + bills.getOutTradeNo() + "'";
			if (StringUtils.isNotBlank(bills.getTradeNo()))
				hql += ", t.tradeNo = '" + bills.getTradeNo() + "'";
			if (bills.getSuccessTime() != null)
				hql += " , t.successTime = '" + bills.getSuccessTime() + "'";
			if (bills.getBillsId() != null)
				hql += " where t.billsId = " + bills.getBillsId();
			session.createQuery(hql).executeUpdate();
		} else if (t instanceof TbCity) {
			TbCity city = (TbCity) t;
			if (city.getCityId() == null)
				return;
			TbCity tbCity = (TbCity) session
					.get(TbCity.class, city.getCityId());
			if (city.getIsValid() != null)
				tbCity.setIsValid(city.getIsValid());
			if (StringUtils.isNotBlank(city.getCityName()))
				tbCity.setCityName(city.getCityName());
			tbCity.setAlterTime(time);
		} else if (t instanceof TbClassnotice) {
			TbClassnotice classnotice = (TbClassnotice) t;
			hql = "update TbClassnotice t set t.alterTime= '" + time + "'";
			if (classnotice.getClanotTitle() != null)
				hql += ",t.clanotTitle = '" + classnotice.getClanotTitle()
						+ "'";
			if (classnotice.getClanotContent() != null)
				hql += ",t.clanotContent = '" + classnotice.getClanotContent()
						+ "'";
			if (classnotice.getClassNoticeId() != null) {
				hql += " where t.classNoticeId = "
						+ classnotice.getClassNoticeId();
			}
			session.createQuery(hql).executeUpdate();
		} else if (t instanceof TbUserinfo) {
			TbUserinfo tbUserinfo = (TbUserinfo) t;
			if (StringUtils.isNotBlank(tbUserinfo.getUserInfoAvatar())
					&& tbUserinfo.getUserInfoId() != null)
				hql += "update TbUserinfo t set t.userInfoAvatar = '"
						+ tbUserinfo.getUserInfoAvatar() + "',t.alterTime = '"
						+ time + "'  where t.userInfoId = '"
						+ tbUserinfo.getUserInfoId() + "'";
			session.createQuery(hql).executeUpdate();
		} else if (t instanceof TbComplain) {
			TbComplain complain = (TbComplain) t;
			if (complain.getComplainId() == null)
				return;
			TbComplain tbComplain = (TbComplain) session.get(TbComplain.class,
					complain.getComplainId());
			if (complain.getTbSchool() != null
					&& complain.getTbSchool().getSchoolId() != null)
				tbComplain.setTbSchool(complain.getTbSchool());
			if (complain.getTbUserinfoByUserInfoId() != null
					&& complain.getTbUserinfoByUserInfoId().getUserInfoId() != null)
				tbComplain.setTbUserinfoByUserInfoId(complain
						.getTbUserinfoByUserInfoId());
			if (complain.getTbUserinfoByTbUserInfoId() != null
					&& complain.getTbUserinfoByTbUserInfoId().getUserInfoId() != null)
				tbComplain.setTbUserinfoByTbUserInfoId(complain
						.getTbUserinfoByTbUserInfoId());
			if (complain.getTbBranchschool() != null
					&& complain.getTbBranchschool().getBranchSchoolId() != null)
				tbComplain.setTbBranchschool(complain.getTbBranchschool());
			if (StringUtils.isNotBlank(complain.getComplainContent()))
				tbComplain.setComplainContent(complain.getComplainContent());
			if (StringUtils.isNotBlank(complain.getComplainStatus()))
				tbComplain.setComplainStatus(complain.getComplainStatus());
			if (complain.getIsValid() != null)
				tbComplain.setIsValid(complain.getIsValid());
			tbComplain.setAlterTime(time);
		}

		else if (t instanceof TbRelation) {
			TbRelation relation = (TbRelation) t;
			if (relation.getRelationId() == null)
				return;
			TbRelation tbRelation = (TbRelation) session.get(TbRelation.class,
					relation.getRelationId());
			if (relation.getTbUserinfoByUserInfoId() != null
					&& relation.getTbUserinfoByUserInfoId().getUserInfoId() != null)
				tbRelation.setTbUserinfoByUserInfoId(relation
						.getTbUserinfoByUserInfoId());
			if (relation.getTbUserinfoByTbUserInfoId() != null
					&& relation.getTbUserinfoByTbUserInfoId().getUserInfoId() != null)
				tbRelation.setTbUserinfoByTbUserInfoId(relation
						.getTbUserinfoByTbUserInfoId());
			if (relation.getIsValid() != null)
				tbRelation.setIsValid(relation.getIsValid());
			if (relation.getChildLatitude() != null)
				tbRelation.setChildLatitude(relation.getChildLatitude());
			if (relation.getChildLongitude() != null)
				tbRelation.setChildLongitude(relation.getChildLongitude());
			tbRelation.setAlterTime(time);
			session.update(tbRelation);
		} else if (t instanceof TbCourse) {
			TbCourse course = (TbCourse) t;
			if (course.getCourseId() == null)
				return;
			TbCourse tbCourse = (TbCourse) session.get(TbCourse.class,
					course.getCourseId());
			if (course.getTbBranchschool() != null
					&& course.getTbBranchschool().getBranchSchoolId() != null)
				tbCourse.setTbBranchschool(course.getTbBranchschool());
			if (StringUtils.isNotBlank(course.getCourseName()))
				tbCourse.setCourseName(course.getCourseName());
			if (course.getIsValid() != null)
			tbCourse.setAlterTime(time);
		}

		else if (t instanceof TbGrade) {
			TbGrade grade = (TbGrade) t;
			if (grade.getGradeId() == null)
				return;
			TbGrade tbGrade = (TbGrade) session.get(TbGrade.class,
					grade.getGradeId());
			if (grade.getTbSchool() != null
					&& grade.getTbSchool().getSchoolId() != null)
				tbGrade.setTbSchool(grade.getTbSchool());
			if (grade.getTbBranchschool() != null
					&& grade.getTbBranchschool().getBranchSchoolId() != null)
				tbGrade.setTbBranchschool(grade.getTbBranchschool());
			if (StringUtils.isNotBlank(grade.getGradeName()))
				tbGrade.setGradeName(grade.getGradeName());
			if (grade.getIsValid() != null)
				tbGrade.setIsValid(grade.getIsValid());
			tbGrade.setAlterTime(time);
		}
	}
	public Page webObjectsPage(Page page, DetachedCriteria detchedCriteria,DetachedCriteria detchedCriteria1) {
		Session session = HibernateSessionFactory.getSession();
		Criteria pageList = detchedCriteria1.getExecutableCriteria(session);
		pageList.setFirstResult((page.getCurrentPage() - 1)
				* page.getCountPerPage());
		pageList.setMaxResults(page.getCountPerPage());
		List currentlist = pageList.list();
		Long allpages = 0l;
		Criteria criteria = detchedCriteria.getExecutableCriteria(session);
		Criteria setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
		page.setAllCount(allcount.intValue());
		page.setAllPages(allpages.intValue());
		page.setCurrentList(currentlist);
		if (null != session && session.isOpen())
			session.close();
		return page;
	}
	public <T> String updateDeleteObject(Class<T> clazz, Integer... ids) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			for (Integer id : ids) {
				String hql = "update "+clazz.getSimpleName() +" t set t.isValid = 0,t.alterTime = '"+ time + "'  where t.photoId = " + id;
				session.createQuery(hql).executeUpdate();
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e);
			logger.info("HibernateException:" + e.getLocalizedMessage());
			return "fail";
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return "success";
	}
	public ArrayList<TbBranchschool> getTbBranchschoolList(TbUserinfo tbUserinfo) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(TbUserinfoclass.class).setProjection(Projections.distinct(Projections.property("tbBranchschool")));
		criteria.add(Restrictions.eq("tbUserinfo", tbUserinfo));	
		ArrayList<TbBranchschool> branchschools = (ArrayList<TbBranchschool>) criteria.list();
		if(null!=session && session.isOpen())
		session.close();
		return branchschools;
	}
	public List getSQLList(String sql) {
		Session session = HibernateSessionFactory.getSession();
		List list = session.createSQLQuery(sql).list();
		if(null!=session && session.isOpen())
			session.close();
		return list;
	}
	
	public <T> T webObject(Class<T> clazz,Integer id){
		Session session = HibernateSessionFactory.getSession();
		Object t = session.get(clazz, id);
		T t1=null;
		if(t==null)
			try {
				t1=clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		else
			t1=(T) t;
		if(null!=session && session.isOpen())
			session.close();
		return  t1;
	}
	public Integer webObjectsNumber(DetachedCriteria detchedCriteria){
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = detchedCriteria.getExecutableCriteria(session);
		Criteria setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		if(null!=session && session.isOpen())
			session.close();
		return allcount.intValue();
	}
	public ArrayList<Object> webObjects(DetachedCriteria detchedCriteria){
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = detchedCriteria.getExecutableCriteria(session);
		ArrayList<Object> list = (ArrayList<Object>) criteria.list();
		if(null!=session && session.isOpen())
			session.close();
		return list;
	}
	public <T> ArrayList<T> webObjects(Class<T> clazz,DetachedCriteria detchedCriteria){
		Session session = HibernateSessionFactory.getSession();
		List list=null;
		if(null!=session && session.isOpen()){
			Criteria criteria = detchedCriteria.getExecutableCriteria(session);
			list =  criteria.list();
			session.close();
			}
		return list==null?new ArrayList<T>():(ArrayList<T>) list;
	}
	private Page setPageList(Page page,final DetachedCriteria detchedCriteria){
		Session session = HibernateSessionFactory.getSession();
		Criteria pageList = detchedCriteria.getExecutableCriteria(session);
		pageList.setFirstResult((page.getCurrentPage() - 1)
				* page.getCountPerPage());
		pageList.setMaxResults(page.getCountPerPage());
		List currentlist = pageList.list();
		page.setCurrentList(currentlist);
		pageList.setFirstResult(0);
		if (null != session && session.isOpen())
			session.close();
		return page;
	}
	private Page setPageInfo(Page page,final DetachedCriteria detchedCriteria){
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = detchedCriteria.getExecutableCriteria(session);
		Criteria setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		
		Long allpages = 0l;
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
		page.setAllCount(allcount.intValue());
		page.setAllPages(allpages.intValue());
		if (null != session && session.isOpen())
			session.close();
		return page;
	}
	public Page webObjectsPage(Page page,DetachedCriteria detchedCriteria) {
		page=setPageList(page, detchedCriteria);
		page=setPageInfo(page, detchedCriteria);
		return page;
	}

}
