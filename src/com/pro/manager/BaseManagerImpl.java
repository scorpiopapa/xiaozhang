package com.pro.manager;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.pro.dao.BaseDao;
/**
 *
 * @author Administrator
 *
 */

public class BaseManagerImpl implements BaseManager {
	@SuppressWarnings("unchecked")
	public BaseDao baseDao;
	
	@SuppressWarnings("unchecked")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	/**
	 *  J2SE 提供的批注 @SuppressWarnings。
	 *  批注的作用是给编译器一条指令，告诉它对被批注的代码元素内部的某些警告保持静默。
	 */
	@SuppressWarnings("unchecked")
	public void deleteByParam(Serializable[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			baseDao.delete(args[i]);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveByParam(Serializable[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			baseDao.save(args[i]);			
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void save(Serializable entity) throws Exception {
		baseDao.save(entity);
	}

	@SuppressWarnings("unchecked")
	public void update(Serializable entity) throws Exception {
		baseDao.update(entity);
	}

	@SuppressWarnings("unchecked")
	public Object findById(Serializable id) throws Exception {
		return baseDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List findByCriteria(DetachedCriteria dc) throws Exception {
		return baseDao.findByCriteria(dc);
	}

	@SuppressWarnings("unchecked")
	public List findByCriteria(int firstResult, int maxResults,DetachedCriteria dc) throws Exception {
		return baseDao.findByCriteria(dc, (firstResult - 1) * maxResults,maxResults);
	}

	
	public int getRowCount(DetachedCriteria dc) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.getRowCount(dc);
	}
	
	public void delete(Serializable entity) throws Exception {
		baseDao.delete(entity);
		
	}
	@SuppressWarnings("unchecked")
	public List find(String hql) {
		return baseDao.find(hql);
	}



}
