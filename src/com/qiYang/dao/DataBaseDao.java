package com.qiYang.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbUserinfo;
import com.qiYang.util.Page;

public interface DataBaseDao {
	// className为model包里面的类名，map存条件就是sql语句中的where key=value;
	public <T> List<T> getObjects(Class<T> clazz, Map<String, Object> map);
	public <T> Integer checkObjects( Class<T> clazz,
			Map<String, Object> map);
	// className为model包里面的类名，map存条件就是sql语句中的where key=value;maple是where
	// key<=value; mapge等同where key>=value；
	public <T> List getObjects(Class<T> clazz, Map<String, Object> map,Map<String, Object> mapne,
			Map<String, Object> maple, Map<String, Object> mapge);
	public <T> Page getObjectPage(Page page, Class<T> clazz,
			Map<String, Object> map,Map<String, Object> mapne, Map<String, Object> maple,
			Map<String, Object> mapge,Map<String, String> maplike);
	//统计数量className为model包里面的类名，map存条件就是sql语句中的where key=value;maple是where key<=value; mapge等同where key>=value；page就是util的分页类
	public <T> Long getCount(Class<T> clazz,
			Map<String, Object> map,Map<String, Object> mapne, Map<String, Object> maple,
			Map<String, Object> mapge,String distinct) ;
	public <T> Long getCount(Class<T> clazz,Map<String, Object> map);
	// className为model包里面的类名，通过ID找对象，跟方法getObjects类似
	public <T> T getObject(Class<T> clazz, Map<String, Object> mapId);

	// className为model包里面的类名，map存条件就是sql语句中的where key=value;page就是util的分页类
	public <T> Page getObjectPage(Page page, Class<T> clazz,Map<String, Object> map);

	// className为model包里面的类名，map存条件就是sql语句中的where key=value;maple是where
	// key<=value; mapge等同where key>=value；page就是util的分页类
	public <T> Page getObjectPage(Page page, Class<T> clazz,
			Map<String, Object> map,Map<String, Object> mapne, Map<String, Object> maple,
			Map<String, Object> mapge);

	// 获得该表格全部的记录
	public <T> List getAllTable(Class<T> clazz, String orderRowName);

	// 群删事务处理
	public <T> String deleteObjects(Class<T> clazz, Integer... ids);

	// 添加方法
	public String addObject(Object...objs);
	//部分修改，mapset修改项key是属性 val是值， mapWhere条件跟查询一样
	public <T> String updateObjects(Map<String, Object> mapSet,Map<String, Object> mapWhere,Class<T>...clazz);
	// 修改方法
	public <T> String updateObject(T... t);

	public <T> String updateDeleteObject(Class<T> clazz, Integer[] ids);
	public ArrayList<TbBranchschool> getTbBranchschoolList(TbUserinfo tbUserinfo);
	public List getSQLList(String sql);
	public Page webObjectsPage(Page page, DetachedCriteria detchedCriteria,DetachedCriteria detchedCriteria1);
	public ArrayList<Object> webObjects(DetachedCriteria dc);
	public Page webObjectsPage(Page page, DetachedCriteria dc);
	public <T> ArrayList<T> webObjects(Class<T> clazz,DetachedCriteria dc);
	public Integer webObjectsNumber(DetachedCriteria detchedCriteria);
	public <T> T webObject(Class<T> clazz,Integer id);
}
