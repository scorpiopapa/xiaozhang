package com.qiYang.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.qiYang.model.TbLesson;
import com.qiYang.util.HibernateSessionFactory;
import com.qiYang.util.Page;

public class BaseDao {
	private static Logger log = Logger.getLogger(BaseDao.class);

	/**
	 * 20140409 池长购 DetachedCriteria格式的分页主方法，page对象全数据。
	 * 
	 * @param page
	 *            分页数据封装对象，需要每页多少条，第几页
	 * @param dc
	 *            离线查询
	 * @return 分页对象全数据
	 */
	public Page dCPage(Page page, DetachedCriteria dc) {
		page = setPageList(page, dc);
		page = setPageInfo(page, dc);
		return page;
	}

	/**
	 * 20140409 池长购 DetachedCriteria格式的分页List信息
	 * 
	 * @param page
	 *            分页数据封装对象，需要每页多少条，第几页
	 * @param dc
	 *            离线查询
	 * @return 分页对象
	 */
	@SuppressWarnings("rawtypes")
	private Page setPageList(Page page, DetachedCriteria dc) {
		Session session = HibernateSessionFactory.getSession();
		Criteria pageList = dc.getExecutableCriteria(session);
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

	/**
	 * 20140409 池长购 DetachedCriteria格式的分页page对象的总条数，总页数。
	 * 
	 * @param page
	 *            分页数据封装对象，需要每页多少条，第几页
	 * @param dc
	 *            离线查询
	 * @return 分页对象
	 */
	private Page setPageInfo(Page page, DetachedCriteria dc) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = dc.getExecutableCriteria(session);
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

	/**
	 * 20140409 池长购 sql格式的分页主方法，page对象全数据。
	 * 
	 * @param page
	 *            分页数据封装对象，需要每页多少条，第几页
	 * @param dc
	 *            离线查询
	 * @return 分页对象全数据
	 */
	public Page sqlPage(Page page, String sql) {
		page = setPageInfo(page, sql);
		page = setPageList(page, sql);
		return page;
	}

	/**
	 * 20140409 池长购 sql格式的分页List信息
	 * 
	 * @param page
	 *            分页数据封装对象，需要每页多少条，第几页
	 * @param dc
	 *            离线查询
	 * @return 分页对象
	 */
	private Page setPageList(Page page, String sql) {
		Session session = HibernateSessionFactory.getSession();
		StringBuffer buffer = new StringBuffer(sql);
		buffer.append(" limit ");
		int num = (page.getCurrentPage() - 1) * page.getCountPerPage();
		buffer.append(num);
		buffer.append(",");
		buffer.append(page.getCountPerPage());
		buffer.append(";");
		List<?> currentlist = session.createSQLQuery(
				buffer.toString().toLowerCase()).list();
		page.setCurrentList(currentlist);
		if (null != session && session.isOpen())
			session.close();
		return page;
	}

	/**
	 * 20140409 池长购 sql格式的分页page对象的总条数，总页数。
	 * 
	 * @param page
	 *            分页数据封装对象，需要每页多少条，第几页
	 * @param dc
	 *            离线查询
	 * @return 分页对象
	 */
	private Page setPageInfo(Page page, String sql) {
		Session session = HibernateSessionFactory.getSession();
		Long allpages = 0l;
		StringBuffer buffer = new StringBuffer("select count(*) from ");
		buffer.append(StringUtils.substringAfter(sql.toLowerCase(), "from"));
		Long allcount = ((BigInteger) session.createSQLQuery(buffer.toString())
				.uniqueResult()).longValue();
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
		page.setAllCount(allcount.intValue());
		page.setAllPages(allpages.intValue());
		if (null != session && session.isOpen())
			session.close();
		return page;
	}

	/**
	 * 20140318 池长购 hql带占位符查询分页(属性为对象时，赋值也需要是以对象形式)
	 * 
	 * @param page
	 *            分页
	 * @param hql
	 *            带占位的hql
	 * @param paramList
	 *            严格按照hql占位符顺序的List赋值集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page hqlPage(Page page, String hql, List<Object> paramList) {
		Session session = HibernateSessionFactory.getSession();
		if (paramList == null) {
			page.setCurrentList(new ArrayList(0));
			return page;
		}
		Query query = session.createQuery(hql);
		StringBuffer buffer = new StringBuffer("select count(*) from ");
		buffer.append(StringUtils.substringAfter(hql, "from"));
		Query query01 = session.createQuery(buffer.toString());
		if (!paramList.isEmpty()) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
				query01.setParameter(i, paramList.get(i));
			}
		}
		int num = (page.getCurrentPage() - 1) * page.getCountPerPage();
		query.setFirstResult(num);
		query.setMaxResults(page.getCountPerPage());
		List<?> currentlist = query.list();
		page.setCurrentList(currentlist);
		Integer allpages = 0;
		Integer allcount = ((Long) query01.uniqueResult()).intValue();
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
		page.setAllCount(allcount);
		page.setAllPages(allpages);
		if (null != session && session.isOpen())
			session.close();
		return page;
	}

	/**
	 * 20140318 池长购 sql带占位符查询分页
	 * 
	 * @param page
	 *            分页
	 * @param sql
	 *            带占位的sql
	 * @param paramList
	 *            严格按照sql占位符顺序的List赋值集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page sqlPage(Page page, String sql, List<Object> paramList) {
		Session session = HibernateSessionFactory.getSession();
		if (paramList == null) {
			page.setCurrentList(new ArrayList(0));
			return page;
		}
		sql = sql.toLowerCase();
		Query query = session.createSQLQuery(sql);
		StringBuffer buffer = new StringBuffer("select count(*) from ");
		buffer.append(StringUtils.substringAfter(sql, "from"));
		Query query01 = session.createSQLQuery(buffer.toString());
		if (!paramList.isEmpty()) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
				query01.setParameter(i, paramList.get(i));
			}
		}
		int num = (page.getCurrentPage() - 1) * page.getCountPerPage();
		query.setFirstResult(num);
		query.setMaxResults(page.getCountPerPage());
		List<?> currentlist = query.list();
		page.setCurrentList(currentlist);
		Integer allpages = 0;
		Integer allcount = ((BigInteger) query01.uniqueResult()).intValue();
		allpages = allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1;
		page.setAllCount(allcount);
		page.setAllPages(allpages);
		if (null != session && session.isOpen())
			session.close();
		return page;
	}

	/**
	 * 20140409 池长购 通过id查询整个对象
	 * 
	 * @param clazz
	 *            对象的类型
	 * @param id
	 *            id主键
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Integer id) {
		Session session = HibernateSessionFactory.getSession();
		T t1 = null;
		Object t = null;
		if (null != session && session.isOpen()) {
			t = session.get(clazz, id);
			session.close();
		}
		if (t == null) {
			try {
				t1 = clazz.newInstance();
			} catch (InstantiationException e) {
				log.error(e);
			} catch (IllegalAccessException e) {
				log.error(e);
			}
		} else
			t1 = (T) t;
		return t1;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {
		Session session = HibernateSessionFactory.getSession();
		T t1 = null;
		Object t = null;
		if (null != session && session.isOpen()) {
			t = session.get(clazz, id);
			session.close();
		}
		if (t == null) {
			try {
				t1 = clazz.newInstance();
			} catch (InstantiationException e) {
				log.error(e);
			} catch (IllegalAccessException e) {
				log.error(e);
			}
		} else
			t1 = (T) t;
		return t1;
	}

	/**
	 * 20140409 池长购 DetachedCriteria格式的查询单个对象方法(强转对象)
	 * 
	 * @param clazz
	 *            对象类
	 * @param dc
	 *            离线查询
	 * @return list结果集
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> ArrayList<T> dCList(Class<T> clazz, DetachedCriteria dc) {
		Session session = HibernateSessionFactory.getSession();
		List list = null;
		if (null != session && session.isOpen()) {
			Criteria criteria = dc.getExecutableCriteria(session);
			list = criteria.list();
			session.close();
		}
		return list == null || list.isEmpty() ? new ArrayList<T>()
				: (ArrayList<T>) list;
	}

	/**
	 * 20140409 池长购 DetachedCriteria格式的查询单个对象方法
	 * 
	 * @param dc
	 *            离线查询
	 * @return list结果集
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object> dCList(DetachedCriteria dc) {
		Session session = HibernateSessionFactory.getSession();
		ArrayList<Object> list = null;
		if (null != session && session.isOpen()) {
			Criteria criteria = dc.getExecutableCriteria(session);
			list = (ArrayList<Object>) criteria.list();
			session.close();
		}
		return list;
	}

	/**
	 * 20140409 池长购 DetachedCriteria格式的查询单个对象方法
	 * 
	 * @param dc
	 *            离线查询
	 * @return 有多少条
	 */
	public Integer dCRowCount(DetachedCriteria dc) {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = dc.getExecutableCriteria(session);
		Criteria setProjection = criteria.setProjection(Projections
				.projectionList().add(Projections.rowCount()));
		Long allcount = ((Integer) setProjection.uniqueResult()).longValue();
		if (null != session && session.isOpen())
			session.close();
		return allcount.intValue();
	}

	/**
	 * 20140409 池长购 部分修改或删除方法，hql没有insert into因为它没where明显不是查询的
	 * 
	 * @param hql
	 * @param paramList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public <T> Integer hqlOperate(String hql, List paramList) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		if (!paramList.isEmpty()) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		Integer num = query.executeUpdate();
		t.commit();
		if (null != session && session.isOpen())
			session.close();
		return num;
	}

	/**
	 * 20140409 池长购 部分修改或删除方法，hql没有insert into因为它没where明显不是查询的
	 * 
	 * @param hql
	 * @param paramList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public <T> Integer sqlOperate(String sql, List paramList) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		Query query = session.createSQLQuery(sql);
		if (!paramList.isEmpty()) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		Integer num = query.executeUpdate();
		t.commit();
		if (null != session && session.isOpen())
			session.close();
		return num;
	}

	/**
	 * 20140409 池长购 群删事务处理
	 * 
	 * @param map
	 *            Map<Class,List<Serializable>> Class这个是对象的类，List里面直接装对象的id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> Boolean delete(Map<Class, List<Serializable>> map) {
		Session session = HibernateSessionFactory.getSession();
		if (null != session && session.isOpen()) {
			Transaction transaction = session.beginTransaction();
			try {
				for (Iterator iterator = map.entrySet().iterator(); iterator
						.hasNext();) {
					Entry<Class, List<Serializable>> type = (Entry<Class, List<Serializable>>) iterator
							.next();
					List<Serializable> ids = type.getValue();
					for (Serializable id : ids) {
						session.delete(session.get(type.getKey(), id));
					}
				}
				transaction.commit();
			} catch (HibernateException e) {
				log.error(e);
				transaction.rollback();
				return false;
			} finally {
				if (null != session && session.isOpen())
					session.close();
			}
		}
		return true;
	}

	/**
	 * 单类表的删除
	 * 
	 * @param clazz
	 * @param ids
	 * @return
	 */
	public <T> Boolean delete(Class<T> clazz, Serializable[] ids) {
		Session session = HibernateSessionFactory.getSession();
		if (null != session && session.isOpen()) {
			Transaction transaction = session.beginTransaction();
			try {
				for (Serializable id : ids) {
					session.delete(session.get(clazz, id));
				}
				transaction.commit();
			} catch (HibernateException e) {
				log.error(e);
				transaction.rollback();
				return false;
			} finally {
				if (null != session && session.isOpen())
					session.close();
			}
		}
		return true;
	}

	/**
	 * 单个删除
	 * 
	 * @param clazz
	 *            类名.class
	 * @param id
	 *            主键
	 * @return
	 */
	public <T> Boolean delete(Class<T> clazz, Serializable id) {
		Session session = HibernateSessionFactory.getSession();
		if (null != session && session.isOpen()) {
			Transaction transaction = session.beginTransaction();
			try {
				session.delete(session.get(clazz, id));
				transaction.commit();
			} catch (HibernateException e) {
				log.error(e);
				transaction.rollback();
				return false;
			} finally {
				if (null != session && session.isOpen())
					session.close();
			}
		}
		return true;
	}

	/**
	 * 20140409 池长购 批量添加，成功的话返回主键
	 * 
	 * @param objs
	 *            Map<Class,Object[]> Class对象类，对应的对象数组
	 * @return Map<Class,Serializable[]> Class对象类，对应的主键数组
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Class, Serializable[]> save(Map<Class, Object[]> map) {
		Session session = HibernateSessionFactory.getSession();
		if (map == null || map.size() == 0)
			return new HashMap<Class, Serializable[]>(0);
		Map<Class, Serializable[]> outMap = new HashMap<Class, Serializable[]>(
				map.size());
		if (null != session && session.isOpen()) {
			Transaction transaction = session.beginTransaction();
			try {
				for (Iterator iterator = map.entrySet().iterator(); iterator
						.hasNext();) {
					Entry<Class, Object[]> type = (Entry<Class, Object[]>) iterator
							.next();
					Object[] objs = null;
					objs = type.getValue();
					if (objs == null || objs.length == 0)
						continue;
					Serializable[] ints = new Serializable[objs.length];
					int i = 0;
					for (Object objects : objs) {
						ints[i] = session.save(objects);
						i++;
					}
					outMap.put(type.getKey(), ints);
				}
				transaction.commit();
			} catch (HibernateException e) {
				log.error(e);
				transaction.rollback();
				return new HashMap<Class, Serializable[]>(0);
			} finally {
				if (null != session && session.isOpen())
					session.close();
			}
		}
		return outMap;
	}

	/**
	 * 20140409 池长购 批量添加，成功的话返回主键
	 * 
	 * @param objs
	 *            Object[] 对应的对象数组
	 * @return 插入成功的主键返回
	 */
	public Serializable[] save(Object[] objs) {
		Session session = HibernateSessionFactory.getSession();
		if (objs == null || objs.length == 0)
			return new Serializable[0];
		Serializable[] ints = new Serializable[objs.length];
		if (null != session && session.isOpen()) {
			Transaction transaction = session.beginTransaction();
			try {
				ints = new Serializable[objs.length];
				int i = 0;
				for (Object objects : objs) {
					ints[i] = session.save(objects);
					i++;
				}
				transaction.commit();
			} catch (HibernateException e) {
				log.error(e);
				transaction.rollback();
				return new Serializable[0];
			} finally {
				if (null != session && session.isOpen())
					session.close();
			}
		}
		return ints;
	}

	/**
	 * 20140409 池长购 批量添加，成功的话返回主键
	 * 
	 * @param objs
	 *            Object[] 对应的对象数组
	 * @return 插入成功的主键返回
	 */
	public Serializable save(Object objs) {
		Session session = HibernateSessionFactory.getSession();
		if (objs == null)
			return null;
		if (null != session && session.isOpen()) {
			Transaction transaction = session.beginTransaction();
			try {
				Serializable id = session.save(objs);
				transaction.commit();
				return id;
			} catch (HibernateException e) {
				log.error(e);
				transaction.rollback();
				return null;
			} finally {
				if (null != session && session.isOpen())
					session.close();
			}
		}
		return null;
	}

	/**
	 * 20140318 池长购 hql占位符查询
	 * 
	 * @param hql
	 *            带占位符的hql
	 * @param paramList
	 *            严格按照占位符的List
	 * @return
	 */
	public List<?> hqlList(String hql, List<Object> paramList) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		if (!paramList.isEmpty()) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		List<?> list = query.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}

	/**
	 * 20140318 池长购 sql占位符查询
	 * 
	 * @param sql
	 *            带占位符的sql
	 * @param paramList
	 *            严格按照占位符的List
	 * @return
	 */
	public List<?> sqlList(String sql, List<Object> paramList) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createSQLQuery(sql.toLowerCase());
		if (!paramList.isEmpty()) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		List<?> list = query.list();
		if (null != session && session.isOpen())
			session.close();
		return list;
	}

	/**
	 * 20140409 池长购 修改单个对象部分属性的方法（注：model层xml配置文件一定要加dynamic-update="true"）
	 * 
	 * @param obj1
	 *            参数obj1为要修改对象
	 * @param id
	 *            id修改对象的主键
	 * @return
	 */
	public boolean updateT(Object obj1, Serializable id) {
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction t = session.beginTransaction();
			Object obj = session.get(obj1.getClass(), id);
			obj = getFieldVlaue(obj, obj1);
			session.update(obj);
			t.commit();
		} catch (HibernateException e) {
			log.error(e);
			return false;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		if (null != session && session.isOpen())
			session.close();
		return true;
	}

	// 部分修改封装方法，参数obj1为要修改对象，id修改对象的主键；（注：model层xml配置文件一定要加dynamic-update="true"）
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Boolean updateT(Map<Object[], Serializable[]> map) {
		if (map == null || map.isEmpty())
			return false;
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction t = session.beginTransaction();
			Object[] objs = null;
			Serializable[] ids = null;
			for (Iterator iterator = map.entrySet().iterator(); iterator
					.hasNext();) {
				Entry<Object[], Serializable[]> type = (Entry<Object[], Serializable[]>) iterator
						.next();
				objs = type.getKey();
				ids = type.getValue();
				if (objs == null || ids == null || objs.length == 0
						|| ids.length == 0)
					continue;
				for (int i = 0; i < ids.length; i++) {
					if (objs[i] == null || ids[i] == null)
						continue;
					Class clazz = objs[i].getClass();
					Object obj = session.get(clazz, ids[i]);
					obj = getFieldVlaue(obj, objs[i]);
					session.update(obj);
				}
			}
			t.commit();
		} catch (HibernateException e) {
			log.error(e);
			return false;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		if (null != session && session.isOpen())
			session.close();
		return true;
	}

	/**
	 * 修改多个不同对象时：要求对象跟Id一对一，多个对象对应多个ID
	 * 
	 * @param objs
	 *            对象数组
	 * @param ids
	 *            ID数组
	 * @return
	 */
	public Boolean updateT(Object[] objs, Serializable[] ids) {
		if (objs == null || objs.length == 0 || ids == null || ids.length == 0)
			return false;
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction t = session.beginTransaction();
			for (int i = 0; i < ids.length; i++) {
				if (objs[i] == null || ids[i] == null)
					continue;
				@SuppressWarnings("rawtypes")
				Class clazz = objs[i].getClass();
				Object obj = session.get(clazz, ids[i]);
				obj = getFieldVlaue(obj, objs[i]);
				session.update(obj);
			}
			t.commit();
		} catch (HibernateException e) {
			log.error(e);
			return false;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		if (null != session && session.isOpen())
			session.close();
		return true;
	}

	/**
	 * 20140409 池长购 把缓存对象通过映射实现现在对象的赋值
	 * 
	 * @param t
	 *            缓存对象，打算要修改的对象，即被修改对象
	 * @param t1
	 *            传入对象，要修改的成对象
	 * @return 修正后的对象
	 */
	public <T> T getFieldVlaue(T t, T t1) {
		Class<?> cls = t1.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			String strGet = "get" + name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
			String strSet = "set" + name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
			Method methodGet;
			try {
				methodGet = cls.getDeclaredMethod(strGet);
				Object object = methodGet.invoke(t1);
				if (object == null || object instanceof HashSet)
					continue;
				Class<?> parameterTypes = field.getType();
				Method methodSet = cls
						.getDeclaredMethod(strSet, parameterTypes);
				methodSet.invoke(t, object);
			} catch (SecurityException e) {
				log.error(e);
			} catch (NoSuchMethodException e) {
				log.error(e);
			} catch (IllegalArgumentException e) {
				log.error(e);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			}
		}
		return t;
	}
}
