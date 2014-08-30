package com.qiYang.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbPracticetitle;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUserinfo;
import com.qiYang.service.GogoServiceImpl;

public class TWObject {
	private GogoServiceImpl gogoServiceImpl=new GogoServiceImpl();
	// 空值判断控制，并强转obj成T
		public <T> T getT(Class<T> clazz,Object obj) {
			if (obj != null) {
					return (T) obj;
			}
			try {
				obj= clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return (T) obj;
		}
		public TbUserinfo isNullTbUserinfo(TbUserinfo tbUserinfo){
			TbUserinfo tbUserinfo1=null;
			if(tbUserinfo!=null&&tbUserinfo.getUserInfoId()!=null)
				tbUserinfo1=gogoServiceImpl.getObjectByClazz(TbUserinfo.class, tbUserinfo.getUserInfoId());
			if(tbUserinfo1==null)
				tbUserinfo1=new TbUserinfo();
			return tbUserinfo1;
		}
		public TbTest isNullTbTest(TbTest obj){
			TbTest tbTest1=null;
			if(obj!=null&&obj.getTestId()!=null)
				tbTest1=gogoServiceImpl.getObjectByClazz(TbTest.class,obj.getTestId());
			if(tbTest1==null)
				tbTest1=new TbTest();
			return tbTest1;
		}
		public TbCourse isNullTbCourse(TbCourse obj){
			TbCourse obj1=null;
			if(obj!=null&&obj.getCourseId()!=null)
				obj1=gogoServiceImpl.getObjectByClazz(TbCourse.class,obj.getCourseId());
			if(obj1==null)
				obj1=new TbCourse();
			return obj1;
		}
		public TbSchool isNullTbSchool(TbSchool obj){
			TbSchool obj1=null;
			if(obj!=null&&obj.getSchoolId()!=null)
				obj1=gogoServiceImpl.getObjectByClazz(TbSchool.class,obj.getSchoolId());
			if(obj1==null)
				obj1=new TbSchool();
			return obj1;
		}
		public TbTown isNullTbTown(TbTown obj){
			TbTown obj1=null;
			if(obj!=null&&obj.getTownId()!=null)
				obj1=gogoServiceImpl.getObjectByClazz(TbTown.class,obj.getTownId());
			if(obj1==null)
				obj1=new TbTown();
			return obj1;
		}
		public TbBranchschool isNullTbBranchschool(TbBranchschool obj){
			TbBranchschool obj1=null;
			if(obj!=null&&obj.getBranchSchoolId()!=null)
				obj1=gogoServiceImpl.getObjectByClazz(TbBranchschool.class,obj.getBranchSchoolId());
			if(obj1==null)
				obj1=new TbBranchschool();
			return obj1;
		}
		public TbPracticetitle isNullTbPracticetitle(TbPracticetitle obj){
			TbPracticetitle tbPracticetitle1=null;
			if(obj!=null&&obj.getPracticeId()!=null)
				tbPracticetitle1=gogoServiceImpl.getObjectByClazz(TbPracticetitle.class,obj.getPracticeId());
			if(tbPracticetitle1==null)
				tbPracticetitle1=new TbPracticetitle();
			return tbPracticetitle1;
		}
		//把对象属性折成Map<String, Object>
		  public static Map<String, Object> getFieldVlaue(Object obj) throws Exception {
		        Map<String, Object> mapValue = new HashMap<String, Object>();
		        Class<?> cls = obj.getClass();
		        Field[] fields = cls.getDeclaredFields();
		        for (Field field : fields) {
		            String name = field.getName();
		            String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
		            Method methodGet = cls.getDeclaredMethod(strGet);
		            Object object = methodGet.invoke(obj);
		            if(object==null||object instanceof HashSet)
		            	continue;
		            mapValue.put(name, object);
		        }
		        return mapValue;
		    }
		  public static Boolean checkTest(TbCourse tbCourse,String testName){
			  Map<String, Object> map=new HashMap<String, Object>();
			  map.put("testName", testName);
			  map.put("tbCourse", tbCourse);
			Long count = new DataBaseDaoImpl().getCount(TbTest.class, map);
			return count>0?false:true;
			  
		  }

}
