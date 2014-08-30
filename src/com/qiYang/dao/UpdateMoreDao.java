package com.qiYang.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.qiYang.model.TbMore;
import com.qiYang.util.HibernateSessionFactory;

public class UpdateMoreDao {
	
	public ArrayList<TbMore> selectMore(){
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbMore.class);
		c.add(Restrictions.eq("isValid", 1));
		ArrayList<TbMore> more = (ArrayList<TbMore>) c.list();
		if (null != session && session.isOpen())
			session.close();
		return more;
	}
	
	 // 去修改
	public TbMore toupdateMore(Integer moreId){
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(TbMore.class);
		c.add(Restrictions.eq("id", moreId));
		c.add(Restrictions.eq("isValid", 1));
		TbMore more = (TbMore) c.uniqueResult();
		if (null != session && session.isOpen())
			session.close();
		return more;
	}
	
	// 修改
	public TbMore updateMore(TbMore tbmore) {
		Session session = HibernateSessionFactory.getSession();
		Transaction t = session.beginTransaction();
		try {
			tbmore.setTitle(tbmore.getTitle());
			tbmore.setContent(tbmore.getContent());
			tbmore.setIsValid(1);
			Timestamp time = new Timestamp(new Date().getTime());
			tbmore.setTime(time);
			tbmore.setAlterTime(time);
			session.update(tbmore);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (null != session && session.isOpen())
				session.close();
		}
		return tbmore;
	}
}
