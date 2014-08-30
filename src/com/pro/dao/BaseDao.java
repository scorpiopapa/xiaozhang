/*
 * 
 * @author zyong
 *
 * @date 2009-9-19 下午11:02:34
 *
 * @action 一个简单公共的hibernate通用dao实现类
 */
package com.pro.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 
 * @author zyong
 * 
 * @action 一个通用的Hibernate的dao接口<br>
 *         数据访问层里面的每一个接口都应该继承这个接口<br>
 *         而不用重写里面的方法
 */
public interface BaseDao<E extends Serializable, PK extends Serializable> {

	/**
	 * 
	 * 该枚举类型用于,getStackValue方法中的Stack枚举
	 * 
	 */
	enum Stack {
		MAX, MIN, AVG, SUM;
	}

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @return 一个实体对象
	 */
	E get(PK id);

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	E get(PK id, LockMode lock);

	

	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param criteria
	 *            一个DetachedCriteria对象
	 * @return 记录数
	 */
	Integer getRowCount(DetachedCriteria criteria);

	/**
	 * 
	 * @param id
	 *            根据主键加裁一个实体对象
	 * @return 一个实体对象
	 */
	E load(PK id);

	/**
	 * 
	 * @param id
	 *            根据主键加裁实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	E load(PK id, LockMode lock);

	/**
	 * 
	 * @return 加裁所有对象
	 */
	List<E> loadAll();

	/**
	 * 
	 * @param entity
	 *            保存一个实体
	 * @throws HibernateException
	 *             抛出Exception异常
	 */
	void save(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void delete(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @param lock
	 *            加锁实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void delete(E entity, LockMode lock) throws HibernateException;

	/**
	 * 
	 * @param entitys
	 *            删除多个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void delete(Collection<E> entitys) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void update(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @param lock
	 *            加锁实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void update(E entity, LockMode lock) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void saveOrUpdate(E entity) throws HibernateException;

	/**
	 * 
	 * @param entitys
	 *            保存多个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void saveOrUpdate(Collection<E> entitys) throws HibernateException;

	/*---------------------------利用hql,sql对数据库进行操作--------------------------------*/

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql);

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @param params
	 *            hql语句参数
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql, Object... values);

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list集合
	 */
	List<E> find(String hql);

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @param params
	 *            hql语句参数
	 * @return 一个list集合
	 */
	List<E> find(String hql, Object... values);

	/**
	 * 
	 * @param queryName
	 *            使用命名的hql语句进行查询
	 * @return 一个list集合
	 */
	List<E> findByNamedQuery(String queryName);

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param values
	 *            参数集合
	 * @return 一个list集合
	 */
	List<E> findByNamedQuery(String queryName, Object... values);

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param params
	 *            参数集合<br>
	 *            Map的键为参数名称即paramName<br>
	 *            Map的值则为values
	 * @return 一个list集合
	 */
	List<E> findByNamedParam(String queryName, Map<String, Object> params);

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param params
	 *            参数集合<br>
	 *            Map的键为参数名称即paramName<br>
	 *            Map的值则为values
	 * @return 一个list集合
	 */
	List<E> findByNamedQueryAndNamedParam(String queryName,
			Map<String, Object> params);

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来检索数
	 * @return 一个list集合
	 */
	List<E> findByCriteria(DetachedCriteria criteria);

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来分页检索数据
	 * @param firstResult
	 *            开始条数
	 * @param maxResults
	 *            返回记录数
	 * @return 一个list集合
	 */
	List<E> findByCriteria(DetachedCriteria criteria, Integer firstResult,
			Integer maxResults);

	/**
	 * 加锁指定的实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * @param lock
	 *            加锁
	 */
	void lock(E entity, LockMode lock) throws HibernateException;

	/**
	 * 强制立即更新到数据库,否则需要事务提交后,才会提交到数据库
	 */
	void flush() throws HibernateException;

	/**
	 * 
	 * @return 根据SimpleDao泛型类型,创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria();

	/**
	 * 
	 * @param c
	 *            为一个实体类型
	 * @return 根据指定的类型创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c);

	/**
	 * 
	 * @return 创建与会话绑定的检索标准对象
	 */
	Criteria createCriteria();

}
