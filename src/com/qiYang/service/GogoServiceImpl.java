package com.qiYang.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;

import com.qiYang.dao.DataBaseDao;
import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.Ofproperty;
import com.qiYang.model.Ofuser;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbAttendance;
import com.qiYang.model.TbBills;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCharge;
import com.qiYang.model.TbChat;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbClassnotice;
import com.qiYang.model.TbComplain;
import com.qiYang.model.TbComplaindetails;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbHistoryquestion;
import com.qiYang.model.TbInfonotice;
import com.qiYang.model.TbMore;
import com.qiYang.model.TbNotice;
import com.qiYang.model.TbPracticeoption;
import com.qiYang.model.TbPracticetitle;
import com.qiYang.model.TbPush;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbReview;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchoolnotice;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbSubjectinbranchschool;
import com.qiYang.model.TbSysconfig;
import com.qiYang.model.TbSystemconfig;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbTestfinish;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;
import com.qiYang.model.TbUserphoto;
import com.qiYang.model.TbViptime;
import com.qiYang.model.web.TbBranchschoolWeb;
import com.qiYang.model.web.TbComplainWeb;
import com.qiYang.model.web.TbComplaindetailsWeb;
import com.qiYang.model.web.TbCourseWeb;
import com.qiYang.model.web.TbHistoryquestionWeb;
import com.qiYang.model.web.TbPracticeoptionWeb;
import com.qiYang.model.web.TbPracticetitleWeb;
import com.qiYang.model.web.TbSchoolnoticeWeb;
import com.qiYang.model.web.TbSysconfigWeb;
import com.qiYang.model.web.TbTestWeb;
import com.qiYang.model.web.TbUserinfoWeb;
import com.qiYang.model.web.TbUserinfoclassWeb;
import com.qiYang.model.web.TransitionModel;
import com.qiYang.util.JpushThread;
import com.qiYang.util.LalDistance;
import com.qiYang.util.Page;
import com.qiYang.util.ResultJson;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;
import com.qiYang.util.TWPictureUtil;

public class GogoServiceImpl implements GogoService {
	final static  Logger log = Logger.getLogger(GogoServiceImpl.class);
	private DataBaseDao database = new DataBaseDaoImpl();

	private static Map<Class, String> clazzIdMap = new HashMap<Class, String>();
	// ID获取对象 start-----------------------------------------------------------------------------------------

		static {
			clazzIdMap.put(TbAdmin.class, "adminId");
			clazzIdMap.put(TbAttendance.class, "attendanceId");
			clazzIdMap.put(TbBranchschool.class, "branchSchoolId");
			clazzIdMap.put(TbBills.class, "billsId");
			clazzIdMap.put(TbCharge.class, "id");
			clazzIdMap.put(TbChat.class, "chatId");
			clazzIdMap.put(TbCity.class, "cityId");
			clazzIdMap.put(TbClassnotice.class, "classNoticeId");
			clazzIdMap.put(TbComplain.class, "complainId");
			clazzIdMap.put(TbComplaindetails.class, "comdetId");
			clazzIdMap.put(TbCourse.class, "courseId");
			clazzIdMap.put(TbCurriculum.class, "courseId");
			clazzIdMap.put(TbGrade.class, "gradeId");
			clazzIdMap.put(TbMore.class, "id");
			clazzIdMap.put(TbNotice.class, "id");
			clazzIdMap.put(TbPracticeoption.class, "praoptId");
			clazzIdMap.put(TbPracticetitle.class, "practiceId");
			clazzIdMap.put(TbPush.class, "puchId");
			clazzIdMap.put(TbRelation.class, "relationId");
			clazzIdMap.put(TbReview.class, "reviewId");
			clazzIdMap.put(TbSchool.class, "schoolId");
			clazzIdMap.put(TbSchoolnotice.class, "schoolNoticeId");
			clazzIdMap.put(TbSubject.class, "subjectId");
			clazzIdMap.put(TbSysconfig.class, "sysconfigId");
			clazzIdMap.put(TbTest.class, "testId");
			clazzIdMap.put(TbTestfinish.class, "testfinishId");
			clazzIdMap.put(TbTown.class, "townId");
			clazzIdMap.put(TbUser.class, "userId");
			clazzIdMap.put(TbUserinfo.class, "userInfoId");
			clazzIdMap.put(TbUserinfoclass.class, "useclaId");
			clazzIdMap.put(TbUserphoto.class, "photoId");
			clazzIdMap.put(TbViptime.class, "id");
			clazzIdMap.put(TbHistoryquestion.class, "wroqueId");
			clazzIdMap.put(TbInfonotice.class, "id");
			clazzIdMap.put(TbSystemconfig.class, "id");
		}
		public <T> T findObject(String str,Integer id){
			 Class<T> clazz = null;
			try {
				clazz = (Class<T>) Class.forName("com.qiYang.model."+str);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			T t = getObjectByClazz(clazz, id);
		     Method methodGet = null;
		     Object obj=null;
			try {
				methodGet = clazz.getDeclaredMethod("toPageWeb");
				obj = methodGet.invoke(t);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
//			System.out.println("(T) obj----------------->"+(T) obj);
			return  (T) obj;
		}
		public <T> T getObjectByClazz(Class<T> clazz, Integer id) {
//			System.out.println(clazz);
			Map<String, Object> map = new HashMap<String, Object>();
			if(clazz==TbSystemconfig.class||clazz==TbBills.class)
				map.remove("isValid");
			String idName = clazzIdMap.get(clazz);
			if (StringUtils.isBlank(idName)) {
				return null;
			}
			if (null == id) {
				return null;
			}
			map.put(idName, id);
			Object object = database.getObject(clazz, map);
			if (object == null) {
				try {
					object = clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return (T) object;
		}
		// ID获取对象 End------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		//新增util---------------------------------------------------------------------------------------
		public String addObject(String str,TransitionModel model){
			 Class clazz = null;
			try {
				clazz = Class.forName("com.qiYang.model."+str);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Object obj = model.toModel(clazz);
			return addObject(clazz,obj );
		}
		public  <T> String addObject(Class<T> clazz, Object obj){
			if(obj==null)
				return "添加失败：此记录可能已经存在！";
			String result = database.addObject(obj);
			return "success".equals(result)?"添加成功":"添加失败";
		}
		//新增util---------------------------------------------------------------------------------------
		
		//修改util start---------------------------------------------------------------------------------------------
		public <T> String updateObject(String str, T t){
			Map<String, Object> mapSet=new HashMap<String, Object>();
			Map<String, Object> mapWhere=new HashMap<String, Object>();
			try {
				mapSet = TWObject.getFieldVlaue(t);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			 Class clazz = null;
			try {
				clazz = Class.forName("com.qiYang.model."+str);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String idName = clazzIdMap.get(clazz);
			Object num = mapSet.get(idName);
			mapSet.remove(idName);
			mapWhere.put(idName,num);
			return updateObject(clazz,mapSet, mapWhere);
		}
		public  <T> String updateObject(Class<T> clazz, Map mapSet,Map mapWhere){
			String result = database.updateObjects(mapSet, mapWhere, clazz);
			return "success".equals(result)?"修改成功":"修改失败";
		}
		//修改 uitl end---------------------------------------------------------------------------------------------
	public ResultJson phoneLogin(String userName, String userPassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("userPassword", userPassword);
		map.put("isValid", 1);
		Map<String, Object> data = new HashMap<String, Object>();
		 List objects = database.getObjects(TbUser.class, map);
		Map<String, Object> mapId=new HashMap<String, Object>();
		mapId.put("name", "xmpp.domain");
		Ofproperty ofproperty = database.getObject(Ofproperty.class, mapId);
		data.put("propValue", ofproperty.getPropValue());
		if (objects==null||objects.isEmpty())
			return ResultJson.createFailJson(-1, "帐户或密码错误");
		else {
			if (objects.get(0) != null) {
				if (objects.get(0) instanceof TbUser) {
					TbUser user = (TbUser) objects.get(0);
					Object obj = null;
					if (user.getTbUserinfo() != null
							&& user.getTbUserinfo().getUserInfoId() != null)
						obj = getObjectByClazz(TbUserinfo.class, user.getTbUserinfo()
								.getUserInfoId());
					if (obj != null) {
						if (obj instanceof TbUserinfo) {
							TbUserinfo tbUserinfo = (TbUserinfo) obj;
							String name = tbUserinfo == null ? "" : tbUserinfo
									.getUserInfoName();
							data.put("userinfoName", name);
							Integer root = tbUserinfo.getUserInfoRoot();
							if(tbUserinfo.getIsValid()==0)
								return ResultJson.crateNullJson("此用户无法正常登录！");
							data.put("userName", userName);
							data.put("userinfoRoot", root);
							data.put("userinfoId", tbUserinfo.getUserInfoId());
							data.put("userInfoAvatar", TWObjectUtil.getNomalPicPath(tbUserinfo.getUserInfoAvatar()));
							if (3== root) {
								ArrayList<TbBranchschool> branchschools = database.getTbBranchschoolList(tbUserinfo);
								List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
								for (TbBranchschool tbBranchschool : branchschools) {
									TbBranchschool tbBranchschool2 = getObjectByClazz(TbBranchschool.class, tbBranchschool.getBranchSchoolId());
									list.add(tbBranchschool2.toPartWeb());
								}
								data.put("branchschools", list);
								data.put("isStudent", true);
								data.put("isLate", "");
								data.put("studentName", "");
							} else if (root == 2) {
								Map<String, Object> map1 = new HashMap<String, Object>();
								TbUserinfo userinfo2 = TWObjectUtil.getChildByParent(tbUserinfo);
								Map<String, Object> mapAtt = new HashMap<String, Object>();
								mapAtt.put("tbUserinfo", userinfo2);
								mapAtt.put("addDate", TWDataUtil.dateFormat(new Date()));
								mapAtt.put("isValid", 1);
								List<TbAttendance> list1 = database.getObjects(
										TbAttendance.class, mapAtt);
								if(list1==null||list1.isEmpty())
									data.put("isLate", 0);
								else{
								TbAttendance tbAttendance = new TWObject().getT(TbAttendance.class, list1.get(0));
								String isLate = tbAttendance.getIsLate() == null ? ""
										: String.valueOf(tbAttendance
												.getIsLate());
								data.put("isLate", isLate);}
								ArrayList<TbBranchschool> branchschools = database.getTbBranchschoolList(userinfo2);
								List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
								for (TbBranchschool tbBranchschool : branchschools) {
									TbBranchschool tbBranchschool2 = getObjectByClazz(TbBranchschool.class, tbBranchschool.getBranchSchoolId());
									list.add(tbBranchschool2.toPartWeb());
								}
								TbUserinfo tbUserinfo2 = new TWObject().isNullTbUserinfo(userinfo2);
								data.put("studentName", tbUserinfo2.getUserInfoName());
								data.put("branchschools", list);
								data.put("isStudent", false);
							} else if(root<=1){
								TbBranchschool tbBranchschool = TWObjectUtil
										.getTbBranchschool(tbUserinfo);
								List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
								list.add(tbBranchschool.toPartWeb());
								data.put("branchschools", list);
								data.put("isLate", "");
								data.put("isStudent", false);
								data.put("studentName", "");
							}
						 else if(root==4){//总校长
							 TbSchool school = tbUserinfo.getTbSchool();
							 Map<String, Object> map1=new HashMap<String, Object>();
							 map1.put("tbSchool", school);
							 map1.put("isValid", 1);
							List<TbBranchschool> listbs = database.getObjects(TbBranchschool.class, map1);
							List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
							for (TbBranchschool tbBranchschool2 : listbs) {
								list.add(tbBranchschool2.toPartWeb());
							}
							data.put("branchschools", list);
							data.put("isLate", "");
							data.put("isStudent", false);
							data.put("studentName", "");
						}
						}
					}
				}
			}
			return ResultJson.crateSuccJson(data);
		}
	}
	public ResultJson phoneLogin01(String userName, String userPassword,String iMEI) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("userPassword", userPassword);
		map.put("isValid", 1);
		Map<String, Object> data = new HashMap<String, Object>();
		List objects = database.getObjects(TbUser.class, map);
		Map<String, Object> mapId=new HashMap<String, Object>();
		mapId.put("name", "xmpp.domain");
		Ofproperty ofproperty = database.getObject(Ofproperty.class, mapId);
		data.put("propValue", ofproperty.getPropValue());
		if (objects==null||objects.isEmpty())
			return ResultJson.createFailJson(-1, "帐户或密码错误");
		else {
			if (objects.get(0) != null) {
				if (objects.get(0) instanceof TbUser) {
					TbUser user = (TbUser) objects.get(0);
					Object obj = null;
					if (user.getTbUserinfo() != null
							&& user.getTbUserinfo().getUserInfoId() != null)
						obj = getObjectByClazz(TbUserinfo.class, user.getTbUserinfo()
								.getUserInfoId());
					if (obj != null) {
						if (obj instanceof TbUserinfo) {
							TbUserinfo tbUserinfo = (TbUserinfo) obj;
							String name = tbUserinfo == null ? "" : tbUserinfo
									.getUserInfoName();
							data.put("userinfoName", name);
							Integer root = tbUserinfo.getUserInfoRoot();
							if(tbUserinfo.getIsValid()==0)
								return ResultJson.crateNullJson("此用户无法正常登录！");
							data.put("userName", userName);
							data.put("userinfoRoot", root);
							data.put("userinfoId", tbUserinfo.getUserInfoId());
							data.put("userInfoAvatar", TWObjectUtil.getNomalPicPath(tbUserinfo.getUserInfoAvatar()));
							if (3== root) {
								ArrayList<TbBranchschool> branchschools = database.getTbBranchschoolList(tbUserinfo);
								List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
								for (TbBranchschool tbBranchschool : branchschools) {
									TbBranchschool tbBranchschool2 = getObjectByClazz(TbBranchschool.class, tbBranchschool.getBranchSchoolId());
									list.add(tbBranchschool2.toPartWeb());
								}
								Map<String, Object> mapOne=new HashMap<String, Object>();
								mapOne.put("tbUserinfoByUserInfoId", tbUserinfo);
								Long num = database.getCount(TbRelation.class, mapOne);
								if(num==1){
									TbRelation tbRelation = database.getObject(TbRelation.class, mapOne);
									Map<String, Object> mapSet=new HashMap<String, Object>();
									mapSet.put("phoneIMEI", iMEI);
									if(tbRelation.getLocationRate()==null)
										mapSet.put("locationRate", 300000);
									Map<String, Object> mapWhere=new HashMap<String, Object>();
									mapWhere.put("relationId", tbRelation.getRelationId());
									database.updateObjects(mapSet, mapWhere, TbRelation.class);
								}
								data.put("branchschools", list);
								data.put("isStudent", true);
								data.put("isLate", "");
								data.put("studentName", "");
							} else if (root == 2) {
								Map<String, Object> map1 = new HashMap<String, Object>();
								TbUserinfo userinfo2 = TWObjectUtil.getChildByParent(tbUserinfo);
								Map<String, Object> mapAtt = new HashMap<String, Object>();
								mapAtt.put("tbUserinfo", userinfo2);
								mapAtt.put("addDate", TWDataUtil.dateFormat(new Date()));
								mapAtt.put("isValid", 1);
								List<TbAttendance> list1 = database.getObjects(
										TbAttendance.class, mapAtt);
								if(list1==null||list1.isEmpty())
									data.put("isLate", 0);
								else{
									TbAttendance tbAttendance = new TWObject().getT(TbAttendance.class, list1.get(0));
									String isLate = tbAttendance.getIsLate() == null ? ""
											: String.valueOf(tbAttendance
													.getIsLate());
									data.put("isLate", isLate);}
								ArrayList<TbBranchschool> branchschools = database.getTbBranchschoolList(userinfo2);
								List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
								for (TbBranchschool tbBranchschool : branchschools) {
									TbBranchschool tbBranchschool2 = getObjectByClazz(TbBranchschool.class, tbBranchschool.getBranchSchoolId());
									list.add(tbBranchschool2.toPartWeb());
								}
								TbUserinfo tbUserinfo2 = new TWObject().isNullTbUserinfo(userinfo2);
								data.put("studentName", tbUserinfo2.getUserInfoName());
								data.put("branchschools", list);
								data.put("isStudent", false);
							} else if(root<=1){
								TbBranchschool tbBranchschool = TWObjectUtil
										.getTbBranchschool(tbUserinfo);
								List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
								list.add(tbBranchschool.toPartWeb());
								data.put("branchschools", list);
								data.put("isLate", "");
								data.put("isStudent", false);
								data.put("studentName", "");
							}
							else if(root==4){
								TbSchool school = tbUserinfo.getTbSchool();
								Map<String, Object> map1=new HashMap<String, Object>();
								map1.put("tbSchool", school);
								map1.put("isValid", 1);
								List<TbBranchschool> listbs = database.getObjects(TbBranchschool.class, map1);
								List<TbBranchschoolWeb> list = new ArrayList<TbBranchschoolWeb>();
								for (TbBranchschool tbBranchschool2 : listbs) {
									list.add(tbBranchschool2.toPartWeb());
								}
								data.put("branchschools", list);
								data.put("isLate", "");
								data.put("isStudent", false);
								data.put("studentName", "");
							}
						}
					}
				}
			}
			return ResultJson.crateSuccJson(data);
		}
	}


	

	// 通过外键获得资料
	public List getObjects(String methodsName, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isValid", 1);
		List objects = null;
		if (obj == null)
			return null;
		if ("TbUserinfoclass".equals(methodsName)) {
			if (obj instanceof TbUserinfo)
				map.put("tbUserinfo", obj);
			objects = database.getObjects(TbUserinfoclass.class, map);
		} else if ("studentClass".equals(methodsName)) {
			if (obj instanceof TbBranchschool)
				map.put("tbCourse", obj);
			else if (obj instanceof TbCourse)
				map.put("tbCourse", obj);
			map.put("userRoot", 3);
			objects = database.getObjects(TbUserinfoclass.class, map);
		} else if ("TbClassnotice".equals(methodsName)) {
			if (obj instanceof TbClassnotice) {
				map.put("clanotSige", obj);
				objects = database.getObjects(TbClassnotice.class, map);
			}
		}else if ("brandschoolAllSubject".equals(methodsName)) {
			if (obj instanceof TbBranchschool) {
				map.put("tbBranchschool", obj);
				objects = database.getObjects(TbSubjectinbranchschool.class, map);
			} 
		}
		else if ("brandschoolAllCourse".equals(methodsName)) {
			if (obj instanceof TbBranchschool) {
				map.put("tbBranchschool", obj);
				objects = database.getObjects(TbCourse.class, map);
			} 
		}else if ("subjectAllCourse".equals(methodsName)) {
				if (obj instanceof TbSubject) {
					map.put("tbSubject", obj);
					objects = database.getObjects(TbCurriculum.class, map);
				} 
		}
//			System.out.println("className:没有对应的model类" + methodsName);
		return objects;

	}
	
	public ResultJson childLocation(Integer userinfoId, Double childLongitude,
			Double childLatitude) {
		/*System.out.println("userinfoId:-->" + userinfoId
				+ ";childLongitude:-->" + childLongitude + ";childLatitude:-->"
				+ childLatitude);*/
		TbUserinfo userinfo = getObjectByClazz(TbUserinfo.class, userinfoId);
		TbUserinfo tbUserinfo = new TWObject().getT(TbUserinfo.class, userinfo);
		Map<String, Object> mapId = new HashMap<String, Object>();
		if(tbUserinfo.getUserInfoRoot() == 3)
		mapId.put("tbUserinfoByUserInfoId", tbUserinfo);
		else if(tbUserinfo.getUserInfoRoot() == 2)
		mapId.put("tbUserinfoByTbUserInfoId", tbUserinfo);
		mapId.put("isValid", 1);
		Object relation = database.getObject(TbRelation.class, mapId);
		TbRelation tbRelation = new TWObject().getT(TbRelation.class, relation);
		if (tbUserinfo.getUserInfoRoot() == 3) {
			if (childLongitude != null && childLatitude != null) {
				Map<String, Object> mapWhere=new HashMap<String, Object>();
				mapWhere.put("relationId", tbRelation.getRelationId());
				Map<String, Object> mapSet=new HashMap<String, Object>();
				mapSet.put("childLongitude", childLongitude);
				mapSet.put("childLatitude", childLatitude);
				String result =database.updateObjects(mapSet, mapWhere, TbRelation.class);
				return "success".equals(result) ? ResultJson
						.crateSuccJson(new HashMap<String, Object>())
						: ResultJson.createFailJson(-1, "修改失败");
			} else
				return ResultJson.createFailJson(-1, "修改失败");
		} else if (tbUserinfo.getUserInfoRoot() == 2) {
			Map<String, Object> data = new HashMap<String, Object>();
			Double latitude = tbRelation.getChildLatitude() == null ? 0
					: tbRelation.getChildLatitude();
			Double longitude = tbRelation.getChildLongitude() == null ? 0
					: tbRelation.getChildLongitude();
			data.put("childLatitude", latitude);
			data.put("childLongitude", longitude);
			return ResultJson.crateSuccJson(data);
		}
		return ResultJson.createFailJson(-1, "访问失败");
	}
	/**上传定位接口
	 * @param userinfoId	用户id
	 * @param childLongitude	经度
	 * @param childLatitude		纬度
	 * @param iMEI		手机唯一识别码,phoneLogin01接口登录时记录最后一次登录用户的手机
	 * @return
	 */
	public ResultJson childLocation01(Integer userinfoId, Double childLongitude,
			Double childLatitude,String iMEI) {
		TbUserinfo userinfo = getObjectByClazz(TbUserinfo.class, userinfoId);
		TbUserinfo tbUserinfo = new TWObject().getT(TbUserinfo.class, userinfo);
		Map<String, Object> mapId = new HashMap<String, Object>();
		if(tbUserinfo.getUserInfoRoot() == 3)
			mapId.put("tbUserinfoByUserInfoId", tbUserinfo);
		else if(tbUserinfo.getUserInfoRoot() == 2)
			mapId.put("tbUserinfoByTbUserInfoId", tbUserinfo);;;
		mapId.put("isValid", 1);
		Object relation = database.getObject(TbRelation.class, mapId);
		TbRelation tbRelation = new TWObject().getT(TbRelation.class, relation);
		if (tbUserinfo.getUserInfoRoot() == 3) {
			if (childLongitude != null && childLatitude != null) {
				if(StringUtils.isNotEmpty(iMEI)&&iMEI.equals(tbRelation.getPhoneIMEI())){
				Map<String, Object> data = new HashMap<String, Object>();
				Map<String, Object> mapWhere=new HashMap<String, Object>();
				mapWhere.put("relationId", tbRelation.getRelationId());
				Map<String, Object> mapSet=new HashMap<String, Object>();
				mapSet.put("childLongitude", childLongitude);
				mapSet.put("childLatitude", childLatitude);
				String result =database.updateObjects(mapSet, mapWhere, TbRelation.class);
				data.put("locationRate", TWObjectUtil.integerIsZero(tbRelation.getLocationRate()));
				return "success".equals(result) ? ResultJson
						.crateSuccJson(data)
						: ResultJson.createFailJson(-1, "修改失败");
				}
				} else
				return ResultJson.createFailJson(-1, "修改失败");
		} else if (tbUserinfo.getUserInfoRoot() == 2) {
			Map<String, Object> data = new HashMap<String, Object>();
			Double latitude = tbRelation.getChildLatitude() == null ? 0
					: tbRelation.getChildLatitude();
			Double longitude = tbRelation.getChildLongitude() == null ? 0
					: tbRelation.getChildLongitude();
			data.put("childLatitude", latitude);
			data.put("childLongitude", longitude);
			data.put("alterTime", TWDataUtil.TimestampFormat(tbRelation.getAlterTime()));
			return ResultJson.crateSuccJson(data);
		}
		return ResultJson.createFailJson(-1, "访问失败");
	}

	public ResultJson myWorkmate(Page page, Integer userinfoId) {
		if(userinfoId==null){
			log.info("com.qiYang.service.GogoServiceImpl.myWorkmate的userinfoId为null");
			return ResultJson.createFailJson(-1, "数据获取失败");
		}
		TbUserinfo tbUserinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		Integer root = tbUserinfo.getUserInfoRoot();
		Map<String, Object> data = new HashMap<String, Object>();
		Page newPage = new Page();
		if (root == null)
			return ResultJson.createFailJson(-1, "数据获取失败");
		if (root == 0 || root == 1) {
			TbBranchschool branchschool = tbUserinfo.getTbBranchschool();
			newPage = getObjectPage(page, TbUserinfo.class, branchschool,"myWorkmate",userinfoId);
			List listIn = newPage.getCurrentList();
			List list = TWObjectUtil.getListWeb(listIn, "TbUserinfoWeb");
			newPage.setCurrentList(list);
		}
		return TWObjectUtil.dataPut(data, newPage);
	}

	// 分页 start page分页参数，className查询大类，obj任意类
	public <T> Page getObjectPage(Page page, Class<T> clazz, Object obj,String methodsName,Object obj2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isValid", 1);
		map.put("倒", "time");
		if (obj == null)
			return new Page();
		else {
				if (obj instanceof TbBranchschool) {
					TbBranchschool branchschool = new TWObject().getT(TbBranchschool.class, obj);
					map.put("tbBranchschool", branchschool);
					if("myWorkmate".equals(methodsName)){
						Map<String, Object> maple = new HashMap<String, Object>();
						Map<String, Object> mapne=new HashMap<String, Object>();
						maple.put("userInfoRoot", 1);
						mapne.put("userInfoId", obj2);
						return database.getObjectPage(page, clazz, map,mapne, maple, null);
					}else if("branchschooltostudent".equals(methodsName))
						map.put("userRoot", 3);
					
			} else if (obj instanceof TbCourse) {
					TbCourse tbCourse = new TWObject().getT(TbCourse.class, obj);
					map.put("tbCourse", tbCourse);
					if("classToStudent".equals(methodsName)){
						map.put("userRoot", 3);
					}
			    }
			else if(obj instanceof TbUserinfo){
					if("parentNotice".equals(methodsName)){
						map.put("tbUserinfoByParentId", obj);
					}
			}
			else if(obj instanceof TbTest){
				if("TbPracticetitle".equals(methodsName)){
					map.put("tbTest", obj);
					map.remove("倒");
					map.put("倒", "alterTime");
				}
			}
		}
		return database.getObjectPage(page, clazz, map);
	}

	// 分页 end
	public ResultJson myClass(Integer userinfoId) {
		TbUserinfo tbUserinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(tbUserinfo.getUserInfoRoot()==2)
			tbUserinfo=TWObjectUtil.getChildByParent(tbUserinfo);
		map.put("tbUserinfo", tbUserinfo);
		map.put("isValid", 1);
		List list = database.getObjects(TbUserinfoclass.class, map);
		List listOut = TWObjectUtil.getListWeb(list, "TbUserinfoclassWeb");
		if (list.isEmpty())
			return ResultJson.createFailJson(-1, "数据读取失败");
		data.put("list", listOut);
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson classToStudent(Page page, Integer courseId,Integer branchschoolId) {
		Page pageUserinfoClass=new Page();
		List listIn=new ArrayList();
		List listOut=new ArrayList();
		if(branchschoolId==null){
		Object obj = getObjectByClazz(TbCourse.class, courseId);
		pageUserinfoClass = getObjectPage(page, TbUserinfoclass.class, obj,"classToStudent",null);
	    listIn = pageUserinfoClass.getCurrentList();
	    listOut = TWObjectUtil.getListWeb(listIn, "studentClassWeb");
		}else if(courseId==null){
			TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
			DetachedCriteria dc=DetachedCriteria.forClass(TbUserinfoclass.class);
			DetachedCriteria dc1=DetachedCriteria.forClass(TbUserinfoclass.class);
			dc.setProjection(Projections.projectionList().add(Projections.groupProperty("tbUserinfo"))).add(Restrictions.eq("userRoot", 3)).add(Restrictions.eq("isValid", 1)).add(Restrictions.eq("tbBranchschool", branchschool)).setFetchMode("tbUserinfo", FetchMode.JOIN);
			dc1.setProjection(Projections.projectionList().add(Projections.groupProperty("tbUserinfo"))).add(Restrictions.eq("userRoot", 3)).add(Restrictions.eq("isValid", 1)).add(Restrictions.eq("tbBranchschool", branchschool)).setFetchMode("tbUserinfo", FetchMode.JOIN);
			pageUserinfoClass = database.webObjectsPage(pageUserinfoClass, dc, dc1);
			List list = pageUserinfoClass.getCurrentList();
			for (Object obj : list) {
				TbUserinfo userinfo = (TbUserinfo)obj;
				listOut.add(toStudentWeb(userinfo));
			}
		/*TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
		pageUserinfoClass = getObjectPage(page, TbUserinfoclass.class, branchschool,"branchschooltostudent",null);
		listIn = pageUserinfoClass.getCurrentList();
		listOut = TWObjectUtil.getListWeb(listIn, "studentClassWeb");*/
		}
		pageUserinfoClass.setCurrentList(listOut);
		Map<String, Object> data = new HashMap<String, Object>();
		return TWObjectUtil.dataPut(data, pageUserinfoClass);
	}
	private TbUserinfoclassWeb toStudentWeb(TbUserinfo userinfo) {
		Integer childId = userinfo.getUserInfoId();
		if (childId == null)
			return null;
		TbUserinfo tbUserinfo1 = TWObjectUtil.getTbUserinfo(childId);
		TbUserinfo parent = TWObjectUtil.getParentByChild(tbUserinfo1);
		return new TbUserinfoclassWeb(tbUserinfo1.getUserInfoId(),
				tbUserinfo1.getUserInfoName(),
				TWObjectUtil.getNomalPicPath(tbUserinfo1.getUserInfoAvatar()),TWObjectUtil.getUserName(tbUserinfo1),TWObjectUtil.getUserName(parent),
				tbUserinfo1.getUserInfoSign(), TWObjectUtil.getString(parent
						.getUserInfoName()),TWObjectUtil.getNomalPicPath(parent.getUserInfoAvatar()));
	}
	public ResultJson tbAttendanceStudent(Integer courseId) {
		Object obj = getObjectByClazz(TbCourse.class, courseId);
		List listIn = getObjects("studentClass", obj);
		List list = TWObjectUtil.getListWeb(listIn, "tbAttendanceStudent");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		return list.isEmpty() ? ResultJson.createFailJson(-1, "数据读取失败")
				: ResultJson.crateSuccJson(data);
	}

	public ResultJson tbAttendanceUpdate(String attendanceIds,Integer courseId,Integer issuerId,String basePicUrl) {
		if (StringUtils.isBlank(attendanceIds))
			return ResultJson.createFailJson(-1, "服务器无法接受到数据请求");
		TbAttendance[] attendances = TWDataUtil.getAttendanceVoJsonArray(attendanceIds,courseId);
		String result = tbAttendanceUpdateM(attendances, courseId);
		if("success".equals(result)){
			List<Integer> list = database.getSQLList("select distinct c.userInfoId from tb_userinfoclass c where c.courseId= "+courseId);
			new JpushThread(issuerId,attendances,"到离校管理",basePicUrl).start();
		}
		return "success".equals(result) ? ResultJson
				.crateSuccJson(new HashMap<String, Object>()) : ResultJson
				.createFailJson(-1, "attendance修改失败");
	}
	public ResultJson outSchool(String attendanceIds,Integer courseId,Integer issuerId,String basePicUrl) {
		if (StringUtils.isBlank(attendanceIds))
			return ResultJson.createFailJson(-1, "服务器无法接受到数据请求");
		TbAttendance[] attendances = TWDataUtil.getAttendanceVoJsonArray(attendanceIds,courseId);
		String result = tbAttendanceUpdateM(attendances, courseId);
		if("success".equals(result)){
			List<Integer> list = database.getSQLList("select distinct c.userInfoId from tb_userinfoclass c where c.courseId= "+courseId);
			new JpushThread(issuerId,attendances,"到离校管理",basePicUrl,true).start();
		}
		return "success".equals(result) ? ResultJson
				.crateSuccJson(new HashMap<String, Object>()) : ResultJson
				.createFailJson(-1, "attendance修改失败");
	}
	private String tbAttendanceUpdateM(TbAttendance[] attendances,Integer courseId){
		Map<String, Object> map=new HashMap<String, Object>();
		TbCourse course = TWObjectUtil.getCoureSetId(courseId);
		map.put("tbCourse", course);
		map.put("isValid", 1);
		map.put("addDate", TWDataUtil.dateFormat(new Date()));
		List<TbAttendance> list1 = database.getObjects(TbAttendance.class, map);
		if(list1==null||list1.isEmpty()){
		}else{
		Integer[] ids=new Integer[list1.size()];
		for (int i = 0; i < list1.size(); i++) {
			ids[i]=list1.get(i).getAttendanceId();
		}
		String result1 = database.deleteObjects(TbAttendance.class, ids);
		}
		String result = database.addObject(attendances);
		return result;
	}
	public ResultJson classNoticeList(Page page, Integer courseId,Integer branchschoolId) {
		Map<String, Object> data = new HashMap<String, Object>();
		List list=new ArrayList();
		Page newPage=new Page();
		if(courseId!=null){
		Object object = getObjectByClazz(TbCourse.class, courseId);
		TbCourse course = new TWObject().getT(TbCourse.class, object);
		newPage = getObjectPage(page, TbClassnotice.class, course,null,null);
		}
		else if(branchschoolId!=null){
			TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
			newPage = getObjectPage(page, TbClassnotice.class, branchschool,null,null);
		}
		list = newPage.getCurrentList();
		List classnoticeWebs = TWObjectUtil.getListWeb(list, "TbClassnoticeWeb");
		
		if (list.isEmpty()){
			TWObjectUtil.dataPut(data, page);
			return ResultJson.crateSuccJson(data);
		}
		newPage.setCurrentList(classnoticeWebs);
		TWObjectUtil.dataPut(data, newPage);
			return ResultJson.crateSuccJson(data);
	}

	public ResultJson classNoticeDetail(Integer classnoticeId) {
		Object classnotice = getObjectByClazz(TbClassnotice.class, classnoticeId);
		TbClassnotice tbClassnotice = new TWObject().getT(TbClassnotice.class, classnotice);
		TbUserinfo userinfo = null;
		if(tbClassnotice==null)
			return ResultJson.crateNullJson();
		if (tbClassnotice.getTbUserinfo() != null
				&& tbClassnotice.getTbUserinfo().getUserInfoId() != null)
			userinfo = TWObjectUtil.getTbUserinfo(tbClassnotice.getTbUserinfo()
					.getUserInfoId());

		Map<String, Object> data = new HashMap<String, Object>();
		String time = tbClassnotice.getTime() == null ? "" : TWDataUtil
				.TimestampFormat(tbClassnotice.getTime());
		if(userinfo==null)
			userinfo=new TbUserinfo();
		data.put("userinfoName", userinfo.getUserInfoName());
		data.put("classNoticeTitle", tbClassnotice.getClanotTitle());
		data.put("classNoticeContent", tbClassnotice.getClanotContent());
		data.put("classNoticeAddTime", time);
		data.put("classNoticeId", tbClassnotice.getClassNoticeId());
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson addTbClassnotices(Integer courseId, Integer userinfoId,
			String title, String content, String basePicUrl) {
		TbUserinfo tbUserinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		TbSchool tbSchool = TWObjectUtil.getTbSchool(tbUserinfo);
		TbBranchschool tbBranchschool = TWObjectUtil
				.getTbBranchschool(tbUserinfo);
		Date today = new Date();
		long longDate = today.getTime();
		Timestamp timestamp = new Timestamp(longDate);
		TbCourse tbCourse = getObjectByClazz(TbCourse.class, courseId);
		TbClassnotice tbClassnotice = new TbClassnotice(tbCourse, tbSchool,
				tbUserinfo, tbBranchschool, title, TWDataUtil.dateDateyyyyMMdd(today),
				String.valueOf(longDate), null, content, 1, timestamp,
				timestamp);
		String result = database.addObject(tbClassnotice);
		if("success".equals(result)){
			List<Integer> list = database.getSQLList("select distinct c.userInfoId from tb_userinfoclass c where c.courseId= "+courseId);
			new JpushThread(userinfoId, list, content,"班级通知",basePicUrl).start();
		}
			
		// String result = beginTransactionDao.addTbClassnotices(tbSchool,
		// tbClasses, tbUserinformation, title, content);
		/*
		 * if("success".equals(result)){ for (TbClass tbClass2 : tbClasses) {
		 * JpushClientExample.pushPhone(userInformationId,tbClass2, picUrl,
		 * findDao, content,"班级通知"); } }
		 */
		return "success".equals(result) ? ResultJson
				.crateSuccJson(new HashMap<String, Object>()) : ResultJson
				.createFailJson(-1, "数据添加失败");
	}

	public ResultJson classNoticeDetailUpdate(Integer classNoticeId,
			Integer userinfoId, String title, String content,String basePicUrl) {
		TbClassnotice classnotice = new TbClassnotice();
		if (classNoticeId != null)
			classnotice.setClassNoticeId(classNoticeId);
		if (StringUtils.isNotBlank(title))
			classnotice.setClanotTitle(title);
		if (StringUtils.isNotBlank(content))
			classnotice.setClanotContent(content);
		String str = database.updateObject(classnotice);
		TbClassnotice tbClassnotice = getObjectByClazz(TbClassnotice.class, classNoticeId);
		Integer courseId = tbClassnotice.getTbCourse().getCourseId();
		if("success".equals(str)){
			List<Integer> list = database.getSQLList("select distinct c.userInfoId from tb_userinfoclass c where c.courseId= "+courseId);
			new JpushThread(userinfoId, list, content, "班级通知",basePicUrl).start();
		}
		return "success".equals(str) ? ResultJson
				.crateSuccJson(new HashMap<String, Object>()) : ResultJson
				.createFailJson(-1, "修改失败");
	}

	public ResultJson deleteUserphotoToJson(String deletePhoto) {
		JSONArray jsonArray = JSONArray.fromObject(deletePhoto);
		Integer[] ids = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			ids[i] = Integer.parseInt(jsonArray.getString(i));
		}
		String result = database.updateDeleteObject(TbUserphoto.class, ids);
		if ("success".equals(result))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "删除失败");
	}

	public ResultJson updateGravatarToJson(List<FileItem> items, Object object,
			Integer userinfoId, String basePicUrl) {
		TbUserinfo tbUserinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		Date date = new Date();
		String date1 = String.valueOf(date.getTime());
		String istrueResult = "";
		if (null != items)
			istrueResult = TWPictureUtil.getUploadedFile(basePicUrl, items,
					date1);
		if (!"fail".equals(istrueResult)) {
			tbUserinfo.setUserInfoAvatar(istrueResult);
			String result = database.updateObject(tbUserinfo);
			Map<String, Object> data = new HashMap<String, Object>();
			String avatar = TWObjectUtil.getNomalPicPath(istrueResult);
			data.put("userinfoAvatar", avatar);
			if ("success".equals(result))
				return ResultJson.crateSuccJson(data);
		}
		return ResultJson.createFailJson(-1, "发布失败");
	}

	public ResultJson userphotoAddToJson(List<FileItem> items, Object object,
			Integer userinfoId, String basePicUrl) {
		TbUserinfo tbUserinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String date1 = String.valueOf(date.getTime());
		String photoName = date1 + ".jpg";
		String istrueResult = "fail";
		if (null != items)
			istrueResult = TWPictureUtil.getUploadedFile(basePicUrl, items,
					date1);
		if (!"fail".equals(istrueResult)) {
			TbUserphoto tbUserphoto = new TbUserphoto(tbUserinfo,null, photoName,
					istrueResult, 1, timestamp, timestamp);
			String result = database.addObject(tbUserphoto);
			if ("success".equals(result))
				return ResultJson.crateSuccJson(new HashMap<String, Object>());
		}
		return ResultJson.createFailJson(-1, "发布失败");
	}

	public ResultJson parentNotice(Page page, Integer userinfoId) {
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		Page page1 = getObjectPage(page, TbNotice.class, userinfo, "parentNotice",null);
		List list = page1.getCurrentList();
		List classnoticeWebs = TWObjectUtil.getListWeb(list, "noticeWeb");
		page1.setCurrentList(classnoticeWebs);
		Map<String, Object> data=new HashMap<String, Object>();
		return TWObjectUtil.dataPut(data, page1);
	}

	public ResultJson deleteParentNotice(String noticeIds) {
		JSONArray jsonArray = JSONArray.fromObject(noticeIds);
		if(jsonArray.size()<=0)
			return ResultJson.createFailJson(-1, "无Id数据");
		int ids = Integer.parseInt(jsonArray.getString(0));
		TbNotice tbNotice = getObjectByClazz(TbNotice.class, ids);
		Map<String, Object> mapSet=new HashMap<String, Object>();
		Map<String, Object> mapWhere=new HashMap<String, Object>();
		mapSet.put("isValid", 0);
		mapWhere.put("tbUserinfoByParentId", tbNotice.getTbUserinfoByParentId().getUserInfoId());
		String result = database.updateObjects(mapSet, mapWhere, TbNotice.class);
		return "success".equals(result)?ResultJson.crateSuccJson(new HashMap<String, Object>()):ResultJson.createFailJson(-1, "删除失败");
	}

	public ResultJson myTeacher(Integer branchschoolId, Integer userinfoId) {
		TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
		TbUserinfo userinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		if(userinfo==null)
			userinfo=new TbUserinfo();
		if(userinfo.getUserInfoRoot()!=null&&userinfo.getUserInfoRoot()==2)
			userinfo=TWObjectUtil.getChildByParent(userinfo);
		List teachers = database.getSQLList("select * from (select c.userInfoId userinfoId,c.userInfoName userinfoName,c.userInfoAvatar userinfoAvatar,c.userInfoSign userinfoSign,e.userName userName from tb_user e,tb_userinfo c where c.userInfoId=e.userInfoId) f where f.userinfoId in(select distinct userinfoId from tb_userinfoclass where userRoot=1 and courseId in (select distinct courseId from tb_userinfoclass where isValid = 1 and userinfoId = "+userinfo.getUserInfoId()+" and branchSchoolId= "+branchschool.getBranchSchoolId()+" ))");
		List<TbUserinfoWeb> teachers1=new ArrayList<TbUserinfoWeb>();
		for (Iterator iterator = teachers.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			TbUserinfoWeb tbUserinfoWeb = new TbUserinfoWeb((Integer)object[0], TWObjectUtil.getString((String)object[1]), TWObjectUtil.getNomalPicPath((String)object[2]),
					TWObjectUtil.getString((String)object[3]),TWObjectUtil.getString((String)object[4]),"");
			teachers1.add(tbUserinfoWeb);
		}
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("teachers", teachers1);
		return ResultJson.crateSuccJson(data);
	}
	//班级选择页面（校长）
	public ResultJson classSelect(Integer branchschoolId) {
		if(branchschoolId==null)
			return ResultJson.createFailJson(-1, "branchschoolId为null");
		TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
		List<Integer> list1 = database.getSQLList("select distinct c.userInfoId from tb_userinfoclass c where c.userRoot=1 and c.branchSchoolId= "+branchschool.getBranchSchoolId());
		List list=new ArrayList<Object>();
		for (Integer userinfoId : list1) {
			TbUserinfo tbUserinfo = getObjectByClazz(TbUserinfo.class, userinfoId);
			list.add(tbUserinfo);
		}
		Map<String, Object> data=new HashMap<String, Object>();
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		for (Object object : list) {
			TbUserinfo tbUserinfo = new TWObject().getT(TbUserinfo.class, object);
			List listCourse = getObjects("TbUserinfoclass", tbUserinfo);
			if(listCourse.isEmpty()){
				map.put(tbUserinfo.getUserInfoId(), new ArrayList());
				 continue;
				 }
			listCourse=TWObjectUtil.getListWeb(listCourse, "TbUserinfoclassWeb");
			map.put(tbUserinfo.getUserInfoId(), listCourse);
		}
		list=TWObjectUtil.getListWeb(list, "TbUserinfoWeb");
		data.put("listSubject", list);
		data.put("listCourse", map);
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson workCollect(Integer userinfoId, String date) {
		if(userinfoId==null)
			return ResultJson.createFailJson(-1, "userinfoId为null");
		if(date==null)
			return ResultJson.createFailJson(-1, "date为null");
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		Map map=new HashMap();
		map.put("isValid", 1);
		map.put("addDate", date);
		map.put("tbUserinfo", userinfo);
		Long testNumber = database.getCount(TbTest.class, map);
		map.remove("addDate");
		map.put("clanotAddTime", date);
		Long classnoticeNumber = database.getCount(TbClassnotice.class, map);
		map.remove("clanotAddTime");
		map.remove("tbUserinfo");
		map.put("addDate", date);
		map.put("tbUserinfoByTbUserInfoId", userinfo);
		Long reviewNumber = database.getCount(TbReview.class, map);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("testNumber", testNumber);
		data.put("classnoticeNumber", classnoticeNumber);
		data.put("reviewNumber", reviewNumber);
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson statistics(Integer branchschoolId,Integer courseId, Date date) {
		if(branchschoolId==null&&courseId==null)
			return ResultJson.createFailJson(-1, "branchschoolId或courseId为null");
		Map map=new HashMap();
		map.put("isValid", 1);
		
		Long  schoolStudentNumber=null;
		Long  inSchoolNumber=null;
		Long  outSchoolNumber=null;
		Map<String, Object> data=new HashMap<String, Object>();
		if(branchschoolId!=null){
		TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
		map.put("tbBranchschool", branchschool);
		}
		else if(courseId!=null){
			TbCourse course=new TbCourse();
			course.setCourseId(courseId);
			map.put("tbCourse", course);
		}
		schoolStudentNumber= database.getCount(TbUserinfoclass.class, map,null, null, null,"tbUserinfo");
		map.put("addDate", TWDataUtil.dateFormat(new Date()));
		map.put("isLate", 1);
		inSchoolNumber= database.getCount(TbAttendance.class, map,null, null, null,"tbUserinfo");
		outSchoolNumber= schoolStudentNumber-inSchoolNumber;
		data.put("outSchoolNumber", outSchoolNumber);
		data.put("inSchoolNumber", inSchoolNumber);
		data.put("schoolStudentNumber", schoolStudentNumber);
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson addBranchschoolnotices(Integer userinfoId, String title,
			String content, String basePicUrl) {
		if(userinfoId==null)
			return ResultJson.createFailJson(-1, "userinfoId为null");
		TbUserinfo tbUserinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		TbBranchschool tbBranchschool = TWObjectUtil.getTbBranchschool(tbUserinfo);
		 TbSchool school = tbBranchschool.getTbSchool();
		 if(school==null||school.getSchoolId()==null)
			 return ResultJson.crateNullJson("此用户无总校所属");
		 Date today = new Date();
		Timestamp nowTimes=new Timestamp(today.getTime());
		TbSchoolnotice tbSchoolnotice=new TbSchoolnotice(school, tbUserinfo, tbBranchschool, title, content, TWDataUtil.dateDateyyyyMMdd(today), 0, 1, nowTimes, nowTimes);
		String result = database.addObject(tbSchoolnotice);
		if("success".equals(result)){
			Integer branchschoolId = tbUserinfo.getTbBranchschool().getBranchSchoolId();
			List<Integer> list = database.getSQLList("select distinct c.userInfoId from tb_userinfo c where c.branchSchoolId = "+branchschoolId+" and userInfoRoot <= 1");
			new JpushThread(userinfoId, list, content,"学校通知",basePicUrl).start();
		}
		Map<String, Object> data=new HashMap<String, Object>();
		return "success".equals(result)?ResultJson.crateSuccJson(data):ResultJson.createFailJson(-1, "添加失败");
	}

	public ResultJson updateBranchschoolnotices(Integer branchschoolNoticeId,
			Integer userinfoId, String basePicUrl, String title, String content) {
		if(branchschoolNoticeId==null||userinfoId==null)
			return ResultJson.createFailJson(-1, "branchschoolNoticeId或userinfoId为null");
		TbSchoolnotice tbSchoolnotice = getObjectByClazz(TbSchoolnotice.class, branchschoolNoticeId);
		TbUserinfo userinfo = getObjectByClazz(TbUserinfo.class, userinfoId);
		tbSchoolnotice.setTbUserinfo(userinfo);
		if(StringUtils.isNotBlank(title))
		tbSchoolnotice.setSchnotTitle(title);
		if(StringUtils.isNotBlank(content))
		tbSchoolnotice.setSchnotContent(content);
		String result = database.updateObject(tbSchoolnotice);
		if("success".equals(result)){
			  TbBranchschool branchschool = userinfo.getTbBranchschool();
			  List<Integer> list=null;
			if(branchschool!=null&&branchschool.getBranchSchoolId()!=null)
			list = database.getSQLList("select distinct c.userInfoId from tb_userinfo c where c.branchSchoolId = "+branchschool.getBranchSchoolId()+" and userInfoRoot <= 1");
			new JpushThread(userinfoId, list, content,"学校通知",basePicUrl).start();
		}
		Map<String, Object> data=new HashMap<String, Object>();
		return "success".equals(result)?ResultJson.crateSuccJson(data):ResultJson.createFailJson(-1, "修改失败");
	}

	public ResultJson infonoticeList(Page page) {
		Map map=new HashMap();
		map.put("倒", "time");
		map.put("isValid", 1);
		Page page1 = database.getObjectPage(page, TbInfonotice.class, map);
		List list = page1.getCurrentList();
		page1.setCurrentList(TWObjectUtil.getListWeb(list, "TbInfonoticeWeb"));
		Map<String, Object> data=new HashMap<String, Object>();
		return TWObjectUtil.dataPut(data, page1);
	}

	public ResultJson infonoticeDetail(Integer infonoticeId) {
		if(infonoticeId==null)
			return ResultJson.createFailJson(-1, "infonoticeId为null");
		TbInfonotice tbInfonotice = getObjectByClazz(TbInfonotice.class, infonoticeId);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("starttime", TWDataUtil.TimestampFormat(tbInfonotice.getTime()));
		data.put("headline", TWObjectUtil.getString(tbInfonotice.getHeadline()));
		data.put("content", TWObjectUtil.getString(tbInfonotice.getContent()));
		TbAdmin tbAdmin=tbInfonotice.getTbAdmin();
		if(tbAdmin!=null&&tbAdmin.getAdminId()!=null)
			tbAdmin=new GogoServiceImpl().getObjectByClazz(TbAdmin.class, tbAdmin.getAdminId());
		if(tbAdmin==null)
			tbAdmin=new TbAdmin();
		String adminName = TWObjectUtil.getString(tbAdmin.getAdminName());
		data.put("adminName", adminName);
		data.put("pictrueUrl", TWPictureUtil.getNomalPicPath(tbInfonotice.getPictrueUrl()));
		return ResultJson.crateSuccJson(data);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public ResultJson onLineTest(Page page, Integer testId, Integer userinfoId) {
		if(testId==null||userinfoId==null)
			return ResultJson.createFailJson(-1, "testId或userinfoId为null");
		Page page1 = onLinePage(page, testId);
		List list = page1.getCurrentList();
		if(list==null||list.isEmpty())
			return ResultJson.crateNullJson("查无记录");
		TbPracticetitleWeb tbPracticetitleWeb = new TWObject().getT(TbPracticetitleWeb.class, list.get(0));
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("url", TWDataUtil.practiceUrl()+"?currentPage="+page.getCurrentPage()+"&testId="+testId+"&userinfoId="+userinfoId);
		data.put("allCount", page1.getAllCount());
		return ResultJson.crateSuccJson(data);
	}
	public Page onLinePage(Page page, Integer testId){
		TbTest tbTest=new TbTest();
		tbTest.setTestId(testId);
		page.setCountPerPage(1);
		Page page1 = getObjectPage(page, TbPracticetitle.class, tbTest, "TbPracticetitle",null);
		List list = page1.getCurrentList();
		page1.setCurrentList(TWObjectUtil.getListWeb(list, "TbPracticetitleWeb"));
		return page1;
	}
	//新增对象start
	public String addTbHistoryquestion(String str,TbHistoryquestionWeb tbHistoryquestionWeb){
		TransitionModel model= tbHistoryquestionWeb;
		return addObject(str, model);
	}
	public String addTbSysconfig(String str,TbSysconfigWeb tbSysconfigWeb){
		TransitionModel model= tbSysconfigWeb;
		return addObject(str, model);
	}
	public String addTbTest(String str,TbTestWeb obj){
		TransitionModel model= obj;
		return addObject(str, model);
	}
	public String addTbPracticetitle(String str,TbPracticetitleWeb obj){
		TransitionModel model= obj;
		return addObject(str, model);
	}
	public String addTbPracticeoption(String str,TbPracticeoptionWeb obj){
		TransitionModel model= obj;
		return addObject(str, model);
	}
	//新增对象end
	public String getHistoryAnswer(TbPracticetitleWeb tbPracticetitleWeb, Integer userinfoId){
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		TbPracticetitle tbPracticetitle=new TbPracticetitle();
		tbPracticetitle.setPracticeId(tbPracticetitleWeb.getPracticeId());
		return getHistoryAnswer01(tbPracticetitle, userinfoId);
	}
	public String getHistoryAnswer01(TbPracticetitle tbPracticetitle, Integer userinfoId){
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		Map map=new HashMap();
		map.put("tbUserinfo", userinfo);
		map.put("tbPracticetitle",tbPracticetitle);
		map.put("isValid", 1);
		map.put("正", "hisqueId");
		Long count = database.getCount(TbHistoryquestion.class, map,null, null, null, null);
		if(count>0){
			List list = database.getObjects(TbHistoryquestion.class, map);
			TbHistoryquestion tbHistoryquestion = new TWObject().getT(TbHistoryquestion.class, list.get(0));
			return tbHistoryquestion.getAnswer();
		}
		return "";
	}
	
	public ResultJson mySchoolmate(Page page, Integer userinfoId, Integer courseId) {
		if(userinfoId==null||courseId==null)
			return ResultJson.createFailJson(-1, "userinfoId或courseId为null");		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		TbCourse course = TWObjectUtil.getCoureSetId(courseId);
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapne=new HashMap<String, Object>();
		map.put("tbCourse", course);
		map.put("isValid", 1);
		map.put("userRoot", 3);
		mapne.put("tbUserinfo", userinfo);
		Page page1 = database.getObjectPage(page, TbUserinfoclass.class, map, mapne, null, null);
		List list =page1.getCurrentList();
		if(list.isEmpty())
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		list=TWObjectUtil.getListWeb(list, "studentClassWeb");
		page1.setCurrentList(list);
		Map<String, Object> data=new HashMap<String, Object>();
		TWObjectUtil.dataPut(data, page1);
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson machineCheck(Integer userinfoId, String machine, String type) {
		if(userinfoId==null&&machine==null&&type==null)
			return ResultJson.createFailJson(-1, "userinfoId或machine或type为null");
		type = "0".equals(type) ? "Android" : "IOS";
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pushToken", machine);
		Long count = database.getCount(TbPush.class, map, null, null, null, null);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String result="";
		if(count>0){
			Map<String, Object> mapSet=new HashMap<String, Object>();
			Map<String, Object> mapWhere=new HashMap<String, Object>();
			mapSet.put("tbUserinfo", userinfo.getUserInfoId());
			mapSet.put("isValid", 1);
			mapWhere.put("pushToken", machine);
			result = database.updateObjects(mapSet, mapWhere, TbPush.class);
		}else{
				TbSchool tbSchool = TWObjectUtil.getTbSchool(userinfo);
				TbPush tbPush = new TbPush(tbSchool, userinfo, null, type, machine, 1, timestamp, timestamp);
				result=database.addObject(tbPush);
		}
		return "success".equals(result)?ResultJson.crateSuccJson(new HashMap<String, Object>()):ResultJson.createFailJson(-1, "失败");
		}

	public ResultJson machineDelete(Integer userinfoId, String machine) {
		if(userinfoId==null&&machine==null)
			return ResultJson.createFailJson(-1, "userinfoId或machine或type为null");
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pushToken", machine);
		map.put("isValid", 1);
		Long count = database.getCount(TbPush.class, map, null, null, null, null);
		String result="";
		if(count>0){
			Map<String, Object> mapSet=new HashMap<String, Object>();
			mapSet.put("isValid", 0);
			Map<String, Object> mapWhere=new HashMap<String, Object>();
			mapWhere.put("pushToken", machine);
			result = database.updateObjects(mapSet, mapWhere, TbPush.class);
		}
		return "success".equals(result)?ResultJson.crateSuccJson(new HashMap<String, Object>()):ResultJson.createFailJson(-1, "退出失败");
	}

	public ResultJson myClassStudent(Integer branchschoolId, Integer userinfoId) {
		if(userinfoId==null&&branchschoolId==null||branchschoolId==0)
			return ResultJson.createFailJson(-1, "userinfoId或branchschoolId或type为null");
		TbUserinfo tbUserinfo = TWObjectUtil.getTbUserinfo(userinfoId);
		TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
		Map<String, Object> map=new HashMap<String, Object>();
		if(tbUserinfo.getUserInfoRoot()==2){
			tbUserinfo = TWObjectUtil.getChildByParent(tbUserinfo);
		}
		String sql = "select distinct c.courseId,c.courseName from tb_userinfoclass u,tb_course c where u.courseId=c.courseId  and u.isValid=1 and c.isValid=1 and u.branchSchoolId= "+branchschoolId+" and u.userinfoId="+tbUserinfo.getUserInfoId();
			 List list = database.getSQLList(sql);
			 List<TbCourseWeb> listWeb =new ArrayList<TbCourseWeb>();
			 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] row = (Object[]) iterator.next();
				Integer id=(Integer) row[0];
				String name= (String) row[1];
				listWeb.add(new TbCourseWeb(id, name));
			}
			Map<String, Object> data=new HashMap<String, Object>();
			if(listWeb==null&&listWeb.isEmpty())
				return ResultJson.crateSuccJson(data);
			data.put("list", listWeb);
		return ResultJson.crateSuccJson(data);
	}
	public Map statisticsTbHistoryquestion(Integer userinfoId,Integer testId){
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		Map<String, Object> mapResult=new HashMap<String, Object>();
		TbTest test=new TbTest();
		test.setTestId(testId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbTest", test);
		Long count = database.getCount(TbPracticetitle.class, map, null, null, null, null);
		double allcount = Double.longBitsToDouble(count);
		map.put("tbUserinfo", userinfo);
		map.put("trueOrFalse", 1);
		Long countT = database.getCount(TbHistoryquestion.class, map, null, null, null, null);
		mapResult.put("allcount", count);
		mapResult.put("countTrue", countT);
		double countTrue = Double.longBitsToDouble(countT);
		DecimalFormat df=new DecimalFormat("0.00");
		String rightPercentage = df.format((countTrue/allcount)*100)+"%";
		mapResult.put("rightPercentage", rightPercentage);
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		TbTestfinish tbTestfinish=new TbTestfinish(userinfo, test, TWDataUtil.dateDateyyyyMMdd(date), rightPercentage, 1, 1, timestamp, timestamp);
		String result = database.addObject(tbTestfinish);
		return mapResult;
	}
	public Long getTestAllCount(TbTest tbTest){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbTest", tbTest);
		return  database.getCount(TbPracticetitle.class, map, null, null, null, null);
	}
	public Long getHistoryAllCount(TbTest tbTest,TbUserinfo userinfo){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbTest", tbTest);
		map.put("tbUserinfo", userinfo);
		return  database.getCount(TbHistoryquestion.class, map, null, null, null, null);
	}

	public ResultJson vipTime(Integer branchschoolId, Integer userinfoId) {
		if(branchschoolId==null||userinfoId==null)
			return ResultJson.createFailJson(-1, "数据传输失败");
		Map<String, Object> data=new HashMap<String, Object>();
		TbUserinfo tbUserinfo = getObjectByClazz(TbUserinfo.class, userinfoId);
		TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
		Map<String, Object> mapId=new HashMap<String, Object>();
		if(tbUserinfo.getUserInfoRoot()<2){
			data.put("isVIP", "");
			return ResultJson.crateSuccJson(data);
		}
		if(tbUserinfo.getUserInfoRoot()==3)
			tbUserinfo=TWObjectUtil.getParentByChild(tbUserinfo);
		mapId.put("tbUserinfo", tbUserinfo);
		mapId.put("tbBranchschool", branchschool);
		mapId.put("isValid", 1);
		TbViptime viptime = database.getObject(TbViptime.class, mapId);
		Date VIPDate = viptime.getEndTime();
		Date today = new Date();
		Integer isVIP = VIPDate != null && (VIPDate.before(today) || VIPDate
						.equals(today)) ? 0 : 1;
		data.put("isVIP", 1);//1-vip,0-非vip
		return ResultJson.crateSuccJson(data);
	}
	
	public List loadPage(Integer root,Integer id){
		if(root==0){
			return getAllSchool();
		}else if(root==1){
			List list=new ArrayList();
			list.add(getObjectByClazz(TbSchool.class, id));
			return TWObjectUtil.getListWeb(list, "TbSchoolWeb");
		}else {
			List list=new ArrayList();
			list.add(getObjectByClazz(TbBranchschool.class, id));
			return TWObjectUtil.getListWeb(list, "TbBranchschoolWeb");
		}
	} 
	//查询方法
	public <T> Page getPageObject2(String str,Integer branchschoolId,Page page){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		if(branchschoolId!=null){
			TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
			map.put("tbBranchschool", branchschool);
		}
		 Class<T> clazz = null;
			try {
				clazz = (Class<T>) Class.forName("com.qiYang.model."+str);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		Page page1 = database.getObjectPage(page, clazz, map);
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		List list = TWObjectUtil.getList(page1.getCurrentList(), t, "toPageWeb");
		if(list==null)
			list=new ArrayList();
		page1.setCurrentList(list);
		return page1;
	}
	public <T> Page getPageObject(String str,Page page){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		Class<T> clazz = null;
		try {
			clazz = (Class<T>) Class.forName("com.qiYang.model."+str);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Page page1 = database.getObjectPage(page, clazz, map);
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		List list = TWObjectUtil.getList(page1.getCurrentList(), t, "toPageWeb");
		if(list==null)
			list=new ArrayList();
		page1.setCurrentList(list);
		return page1;
	}
	public <T> Page getPageTbClass(String str,Page page,Integer classId){
		Map<String, Object> map=new HashMap<String, Object>();
		TbCourse course = TWObjectUtil.getCoureSetId(classId);
		map.put("tbCourse", course);
		map.put("倒", "alterTime");
		return getPageByMap(str, page, map);
	}
	public <T> Page getPracticetitleBytestId(String str,Page page,Integer testId){
		Map<String, Object> map=new HashMap<String, Object>();
		TbTest test=new TbTest();
		test.setTestId(testId);
		map.put("tbTest", test);
		map.put("倒", "alterTime");
		return getPageByMap(str, page, map);
	}
	public <T> Page getPageTbTestfinish(String str,Page page,Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		TbTest obj=new TbTest();
		obj.setTestId(id);
		map.put("tbTest", obj);
		map.put("倒", "alterTime");
		return getPageByMap(str, page, map);
	}
	public <T> Page getPageHistoryquestion(String str,Page page,Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		TbUserinfo obj=new TbUserinfo();
		obj.setUserInfoId(id);
		map.put("tbUserinfo", obj);
		map.put("倒", "alterTime");
		return getPageByMap(str, page, map);
	}
	public <T> Page getPageByBills(String str,Page page,String tradeNo){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, String> maplike=new HashMap<String, String>();
		if(StringUtils.isNotEmpty(tradeNo))
			maplike.put("tradeNo", tradeNo);
		map.put("倒", "alterTime");
		return getPageByMaplike(str, page, map,maplike);
	}
	public <T> List getTbPracticeoptionByPracticeId(String str,Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		TbPracticetitle obj=new TbPracticetitle();
		obj.setPracticeId(id);
		map.put("tbPracticetitle", obj);
		map.put("倒", "alterTime");
		return getListByMap(str, map);
	}
	public <T> Page getPageByMap(String str,Page page,Map<String, Object> map){
		Class<T> clazz = null;
		try {
			clazz = (Class<T>) Class.forName("com.qiYang.model."+str);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Page page1 = database.getObjectPage(page, clazz, map);
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		List list = TWObjectUtil.getList(page1.getCurrentList(), t, "toPageWeb");
		if(list==null)
			list=new ArrayList();
		page1.setCurrentList(list);
		return page1;
	}
	public <T> Page getPageByMaplike(String str,Page page,Map<String, Object> map,Map<String, String> maplike){
		Class<T> clazz = null;
		try {
			clazz = (Class<T>) Class.forName("com.qiYang.model."+str);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Page page1 = database.getObjectPage(page, clazz, map, null, null, null, maplike);
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		List list = TWObjectUtil.getList(page1.getCurrentList(), t, "toPageWeb");
		if(list==null)
			list=new ArrayList();
		page1.setCurrentList(list);
		return page1;
	}
	
	public <T> List getListByMap(String str,Map<String, Object> map){
		Class<T> clazz = null;
		try {
			clazz = (Class<T>) Class.forName("com.qiYang.model."+str);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		List<T> list1 = database.getObjects(clazz, map);
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		List list = TWObjectUtil.getList(list1, t, "toPageWeb");
		if(list==null)
			list=new ArrayList();
		return list;
	}
	public Page getPageTbUserinfoclass(Page page){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		Page page1 = database.getObjectPage(page, TbUserinfoclass.class, map);
		List list = TWObjectUtil.getListWeb(page1.getCurrentList(), "TbUserinfoclassWebTOPage");
		page1.setCurrentList(list);
		return page1;
	}
	public Page getPageTbSysconfig(Page page){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		Page page1 = database.getObjectPage(page, TbSystemconfig.class, map);
		return page1;
	}
	public String deleteObjects(String className,Integer[] ids){
		Class<?> clazz=null;
		try {
			clazz = Class.forName("com.qiYang.model."+className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String result = database.deleteObjects(clazz, ids);
		return result;
	}
	public List getAllSchool(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		List<TbSchool> list = database.getObjects(TbSchool.class, map);
		return TWObjectUtil.getListWeb(list, "TbSchoolWeb");
	}
	public List getAllTest(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		List<TbTest> list = database.getObjects(TbTest.class, map);
		return TWObjectUtil.getListWeb(list, "TbTestWeb");
	}
	public List getAllTitle(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		List<TbPracticetitle> list = database.getObjects(TbPracticetitle.class, map);
		return TWObjectUtil.getListWeb(list, "TbPracticetitleWebTOPage");
	}
	public List getTbSchoolTbGrad(TbSchool tbSchool){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		map.put("tbSchool", tbSchool);
		List<TbBranchschool> list = database.getObjects(TbBranchschool.class, map);
		return TWObjectUtil.getListWeb(list, "TbBranchschoolWeb");
	}
	public List getTbGradeTbClass(TbBranchschool tbBranchschool){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		map.put("tbBranchschool", tbBranchschool);
		List<TbCourse> list = database.getObjects(TbCourse.class, map);
		return TWObjectUtil.getListWeb(list, "classWeb");
	}
	public List courseToStudent(TbCourse tbCourse){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("倒", "alterTime");
		map.put("tbCourse", tbCourse);
		List<TbUserinfoclass> list = database.getObjects(TbUserinfoclass.class, map);
		return TWObjectUtil.getListWeb(list, "courseToStudent");
	}
	
	public TbSystemconfig getOneTbSysconfig(Integer sysconfigId){
		System.out.println(sysconfigId);
		return	getObjectByClazz(TbSystemconfig.class, sysconfigId);
	}
	public String updateTbSysconfig(String str,TbSystemconfig tbSysconfig){
		return updateObject(str, tbSysconfig);
	}
	public String updateTbTest(String str,TbTest tbTest){
		return updateObject(str, tbTest);
	}
	public String updateTbPracticetitle(String str,TbPracticetitle tbPracticetitle){
		return updateObject(str, tbPracticetitle);
	}
	public String updateTbPracticeoption(String str,TbPracticeoption obj){
		return updateObject(str, obj);
	}

	public ResultJson campusMapNew(Integer branchschoolId) {
		if(branchschoolId==null)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL");
		Map<String, Object> data=new HashMap<String, Object>();
		List<TbBranchschoolWeb> listSchool=new ArrayList<TbBranchschoolWeb>();
		Map<String, Object> map=new HashMap<String, Object>();
		if(branchschoolId!=0){
			TbSchool tbSchool = TWObjectUtil.getSchoolByBranchschool(branchschoolId);
			map.put("tbSchool", tbSchool);
			listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
		}
		else
		{
			List<TbSchool> list1 = database.getObjects(TbSchool.class, map);
			for (TbSchool tbSchool : list1) {
				listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
			}
		}
		List<TbBranchschool> tbBranchschools = database.getObjects(TbBranchschool.class, map);
		for (TbBranchschool tbBranchschool : tbBranchschools) {
			listSchool.add(getTbBranchschoolWebByTbBranchschool(tbBranchschool));
		}
			Set<String[]> set=new HashSet<String[]>();
		for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
			String[] strs=new String[2];
			strs[0]=tbBranchschoolWeb.getBranchschoolLongitude();
			strs[1]=tbBranchschoolWeb.getBranchschoolLatitude();
			Boolean boo=false;
			if(set.isEmpty()){
				if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1]))
				set.add(strs);
			}
			else{
			for (String[] str : set) {
//				System.out.println("str[0]:"+str[0]+"-->strs[0]:"+strs[0]+"-->str[1]:"+str[1]+"-->strs[1]:"+strs[1]);
//				System.out.println("str[0].equals(strs[0])&&str[1].equals(strs[1])--->"+(str[0].equals(strs[0])&&str[1].equals(strs[1])));
				if(str[0].equals(strs[0])&&str[1].equals(strs[1])){
					boo=true;
					}
			}
			if(!boo){
//				System.out.println("输入set:"+tbBranchschoolWeb.getBranchschoolAddress()+strs[0]+"   and  "+strs[1]);
			if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1]))
			set.add(strs);
			}
			}
		}
		List list=new ArrayList();
		for (String[] strs : set) {
			List<TbBranchschoolWeb> branchschoolWebs =new ArrayList<TbBranchschoolWeb>();
			for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
				if(strs[0].equals(tbBranchschoolWeb.getBranchschoolLongitude())&&strs[1].equals(tbBranchschoolWeb.getBranchschoolLatitude()))
					branchschoolWebs.add(tbBranchschoolWeb);
			}
			list.add(branchschoolWebs);
		}
		try {
			Thread.sleep (1000) ;
		} catch (InterruptedException e) {
		}
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson campusMap(Integer branchschoolId) {
		if(branchschoolId==null)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL");
		Map<String, Object> data=new HashMap<String, Object>();
		List<TbBranchschoolWeb> listSchool=new ArrayList<TbBranchschoolWeb>();
		Map<String, Object> map=new HashMap<String, Object>();
		if(branchschoolId!=0){
			TbSchool tbSchool = TWObjectUtil.getSchoolByBranchschool(branchschoolId);
			map.put("tbSchool", tbSchool);
			listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
		}
		else
		{
			List<TbSchool> list1 = database.getObjects(TbSchool.class, map);
			for (TbSchool tbSchool : list1) {
				listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
			}
		}
		List<TbBranchschool> tbBranchschools = database.getObjects(TbBranchschool.class, map);
		for (TbBranchschool tbBranchschool : tbBranchschools) {
			listSchool.add(getTbBranchschoolWebByTbBranchschool(tbBranchschool));
		}
		Set<String[]> set=new HashSet<String[]>();
		for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
			String[] strs=new String[2];
			strs[0]=tbBranchschoolWeb.getBranchschoolLongitude();
			strs[1]=tbBranchschoolWeb.getBranchschoolLatitude();
			Boolean boo=false;
			if(set.isEmpty()){
				if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1]))
				set.add(strs);
			}
			else{
				for (String[] str : set) {
//				System.out.println("str[0]:"+str[0]+"-->strs[0]:"+strs[0]+"-->str[1]:"+str[1]+"-->strs[1]:"+strs[1]);
//				System.out.println("str[0].equals(strs[0])&&str[1].equals(strs[1])--->"+(str[0].equals(strs[0])&&str[1].equals(strs[1])));
					if(str[0].equals(strs[0])&&str[1].equals(strs[1])){
						boo=true;
					}
				}
				if(!boo){
//				System.out.println("输入set:"+tbBranchschoolWeb.getBranchschoolAddress()+strs[0]+"   and  "+strs[1]);
					if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1]))
					set.add(strs);
				}
			}
		}
		List list=new ArrayList();
		for (String[] strs : set) {
			List<TbBranchschoolWeb> branchschoolWebs =new ArrayList<TbBranchschoolWeb>();
			for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
				if(strs[0].equals(tbBranchschoolWeb.getBranchschoolLongitude())&&strs[1].equals(tbBranchschoolWeb.getBranchschoolLatitude()))
					branchschoolWebs.add(tbBranchschoolWeb);
			}
			list.add(branchschoolWebs);
		}
		try { Thread.sleep ( 1000) ; 
		} catch (InterruptedException ie){}
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}
	
	public TbBranchschoolWeb getTbBranchschoolWebByTbschool(TbSchool tbSchool){
		String[] strs = new String[2];
		strs[0]=tbSchool.getLongitude()==null?"":tbSchool.getLongitude().toString();
		strs[1]=tbSchool.getLatitude()==null?"":tbSchool.getLatitude().toString();
		return new TbBranchschoolWeb(tbSchool.getSchoolId(), TWObjectUtil.getString(tbSchool.getSchoolName()), TWObjectUtil.getString(tbSchool.getSchoolMinName()), strs[0], strs[1], 1);
	}
	public static TbBranchschoolWeb getTbBranchschoolWebByTbBranchschool(TbBranchschool tbBranchschool){
		String[] strs = new String[2];
		strs[0]=tbBranchschool.getLongitude()==null?"":tbBranchschool.getLongitude().toString();
		strs[1]=tbBranchschool.getLatitude()==null?"":tbBranchschool.getLatitude().toString();
		return new TbBranchschoolWeb(tbBranchschool.getBranchSchoolId(), TWObjectUtil.getString(tbBranchschool.getBraschName()), TWObjectUtil.getString(tbBranchschool.getBraschMinName()), strs[0], strs[1], 0);
	}
	public static TbBranchschoolWeb getTbBranchschoolWebByTbBranchschoolOld(TbBranchschool tbBranchschool){
		String[] strs = new String[2];
		strs[0]=tbBranchschool.getLongitude()==null?"":tbBranchschool.getLongitude().toString();
		strs[1]=tbBranchschool.getLatitude()==null?"":tbBranchschool.getLatitude().toString();
		return new TbBranchschoolWeb(tbBranchschool.getBranchSchoolId(), TWObjectUtil.getString(tbBranchschool.getBraschName()), TWObjectUtil.getString(tbBranchschool.getBraschMinName()), strs[0], strs[1], null);
	}
	public ResultJson findCharge(Integer branchschoolId) {
		if(branchschoolId==null)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL");
		TbBranchschool branchschool = TWObjectUtil.getBranchschoolSetId(branchschoolId);
		  Map<String, Object> mapId=new HashMap<String, Object>();
		  mapId.put("tbBranchschool", branchschool);
		TbCharge tbCharge = database.getObject(TbCharge.class, mapId);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("id", tbCharge.getId());
		data.put("chargeName", tbCharge.getChargeName());
		data.put("vipCharge", tbCharge.getVipCharge());
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson branchschoolDetail(Integer branchschoolId) {
		if(branchschoolId==null||branchschoolId==0)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL或0");
		TbBranchschool tbBranchschool = getObjectByClazz(TbBranchschool.class, branchschoolId);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("tbBranchschool", tbBranchschool.toDetailJson());
		DetachedCriteria dc=DetachedCriteria.forClass(TbCurriculum.class);
		dc.add(Restrictions.eq("tbBranchschool", tbBranchschool));
		dc.addOrder(Order.desc("defineSort"));
		dc.addOrder(Order.desc("defineSort2"));
		dc.addOrder(Order.asc("courseName"));
		List<Object> list=database.webObjects(dc);
		list=TWObjectUtil.getListWeb(list, "TbCourseWeb");
		data.put("courses", list);
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson addChat(String messageContent, String messageFrom,
			String createDate, String messageTo) {
		if(StringUtils.isBlank(messageFrom)||StringUtils.isBlank(messageTo)||StringUtils.isBlank(createDate))
			return ResultJson.createFailJson(-1, "messageFrom或messageTo或createDate为NULL");
		Timestamp timetamp = Timestamp.valueOf(createDate);
		Integer fromId = TWObjectUtil.getUserinfoId(messageFrom);
		Integer toId = TWObjectUtil.getUserinfoId(messageTo);
		TbChat tbChat=new TbChat(TWObjectUtil.getUserinfoSetId(fromId), TWObjectUtil.getUserinfoSetId(toId), messageContent,1, timetamp, timetamp);
		String result = database.addObject(tbChat);
		return "success".equals(result)?ResultJson.crateNullJson("添加成功"):ResultJson.createFailJson(-1, "添加失败");
	}
	public Integer checkTbPracticetitle(TbPracticetitle tbPracticetitle) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbTest", tbPracticetitle.getTbTest());
		map.put("practiceTopic", tbPracticetitle.getPracticeTopic());
		map.put("answer", tbPracticetitle.getAnswer());
		 List<TbPracticetitle> list = database.getObjects(TbPracticetitle.class, map);
		 if(list==null||list.isEmpty())
			 return 0;
		return list.size();
	}
	public Integer checkTbPush(TbUserinfo tbUserinfo) {
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("tbUserinfo", tbUserinfo);
		 map.put("isValid", 1);
		 return  database.checkObjects(TbPush.class, map);
	}
	public List<TbPush> getTbPush(TbUserinfo tbUserinfo) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbUserinfo", tbUserinfo);
		List<TbPush> list = database.getObjects(TbPush.class, map);
		return list;
	}
	public ResultJson courseToClass(Integer curriculumId) {
		TbCurriculum curriculum=new TbCurriculum();
		curriculum.setCourseId(curriculumId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbCurriculum", curriculum);
		List<TbCourse> list = database.getObjects(TbCourse.class, map);
		list=TWObjectUtil.getListWeb(list, "classWeb");
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson theMore(String type) {
		Map<String, Object> mapId=new HashMap<String, Object>();
		
		String html="<!DOCTYPE html><html><head><title>";
		html+="</title><meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body>";
		
		Map<String, Object> data=new HashMap<String, Object>();
		
		if("1".equals(type)){
		TbMore tbMore = getObjectByClazz(TbMore.class, 1);
		html+=tbMore.getContent();
		tbMore = getObjectByClazz(TbMore.class, 2);
		html+=tbMore.getContent();
		}
		else if("2".equals(type)){
			String html2=html;
			TbMore tbMore = getObjectByClazz(TbMore.class, 1);
			html+=tbMore.getContent();
			tbMore = getObjectByClazz(TbMore.class, 2);
			html2+=tbMore.getContent();
			html2+="</body></html>";
			data.put("type2", html2);
		}
		else if("5".equals(type))
		{
			TbMore tbMore = getObjectByClazz(TbMore.class, 5);
			html+=tbMore.getContent();
		}
		else if("3".equals(type))
		{
			TbMore tbMore = getObjectByClazz(TbMore.class, 3);
			TbMore tbMore1 = getObjectByClazz(TbMore.class, 6);
			html+=tbMore.getContent();
			String note = tbMore1.getContent();
			int num = note.indexOf("<");
			note=num>0?note.substring(0,num):note;
			data.put("phone2", note);	
		}
		//
		else if("4".equals(type))
		{
			TbMore tbMore = getObjectByClazz(TbMore.class, 4);
			html+=tbMore.getContent();
			String note = tbMore.getContent();
			int num = note.indexOf("<");
			note=num>0?note.substring(0,num):note;
			data.put("type", note);	
			return ResultJson.crateSuccJson(data);
		}
		//6软件功能介绍
		else if("6".equals(type)){
			TbMore tbMore1 = getObjectByClazz(TbMore.class, 1);
			TbMore tbMore = getObjectByClazz(TbMore.class, 2);
			String content = Jsoup.parse(tbMore1.getContent()).text()+"\r\n"+Jsoup.parseBodyFragment(tbMore.getContent()).getAllElements().first().text();
//			System.out.println(content);
			data.put("type",content);	
			return ResultJson.crateSuccJson(data);
		}
		html+="</body></html>";
		data.put("type", html);
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson versions() {
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("倒", "time");
		List<TbSysconfig> list = database.getObjects(TbSysconfig.class, map);
		data.put("versions", list.get(0).getVersionAndroid());
		data.put("time", TWDataUtil.TimestampToDate(list.get(0).getTime()));
		return ResultJson.crateSuccJson(data);
	}
	public String addStudentAndParent(TbUserinfoWeb studentWeb,TbUserinfoWeb parentWeb,Integer courseId){
		 GogoServiceImpl gogo = new GogoServiceImpl();
		 DataBaseDaoImpl database=new DataBaseDaoImpl();
		 String result="";
		 TbCourse tbCourse = gogo.getObjectByClazz(TbCourse.class, courseId);
		 TbBranchschool tbBranchschool = gogo.getObjectByClazz(TbBranchschool.class, tbCourse.getTbBranchschool().getBranchSchoolId());
		 studentWeb.setUserinfoRoot(3);
		 parentWeb.setUserinfoRoot(2);
		 TbUserinfo student = studentWeb.toModel(tbCourse);
		 TbUserinfo parent = parentWeb.toModel(tbCourse);
		 Long num = gogo.checkStudentQQ(student.getUserinfoQq());
		 Date date = new Date();
		 long timeMillis = date.getTime();
		 Timestamp  nowTime=new Timestamp(timeMillis);
		 String str=null;
		 Long numParent = gogo.checkParentPhone(parent.getUserInfoPhone());
			if(num==0&&numParent>0){
				return parentWeb.getUserinfoName()+"的手机号已存在";
			}
			else if(num>0&&numParent>=0){
			 result+=studentWeb.getUserinfoName()+"的QQ已存在";
			if(numParent>0)
			result+="和"+ parentWeb.getUserinfoName()+"的手机号已存在";
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("userInfoPhone", student.getUserInfoPhone());
			 map.put("userInfoRoot", 3);
			 List<TbUserinfo> list = database.getObjects(TbUserinfo.class, map);
			 Map<String, Object> map1=new HashMap<String, Object>();
			 map1.put("tbUserinfo", list.get(0));
			 map1.put("tbCourse", tbCourse);
			 num=database.getCount(TbUserinfoclass.class, map1);
			 if(num==0){
			 TbUserinfoclass tbUserinfoclass=new TbUserinfoclass(tbCourse, tbCourse.getTbCurriculum(), tbBranchschool.getTbSchool(), list.get(0), tbBranchschool, 3, 1, nowTime, nowTime);
			 String strUserinfoclass = database.addObject(tbUserinfoclass);
			 result="success".equals(strUserinfoclass)?"班级关系添加成功":"班级关系添加失败";
			 }
		}
		 else{
			 TbUser tbUser = new TbUser(student, student.getUserinfoQq(), "000000", 1, nowTime, nowTime);
			 Ofuser ofuser = new Ofuser(student.getUserinfoQq(), "111111", null, student.getUserInfoName(), student.getUserInfoEmail(), Long.toString(timeMillis), "0");
			 TbUser tbUser1 = new TbUser(parent, parent.getUserInfoPhone(), "000000", 1, nowTime, nowTime);
			 Ofuser ofuser1 = new Ofuser(parent.getUserInfoPhone(), "111111", null, parent.getUserInfoName(), parent.getUserInfoEmail(), Long.toString(timeMillis), "0");
			 TbRelation tbRelation = new TbRelation(student, parent, 1, null, null, nowTime, nowTime);
			 TbUserinfoclass tbUserinfoclass=new TbUserinfoclass(tbCourse, tbCourse.getTbCurriculum(), tbBranchschool.getTbSchool(), student, tbBranchschool, 3, 1, nowTime, nowTime);
			 TbViptime viptime = new TbViptime(parent, tbBranchschool, date, TWDataUtil.addDayEndDate(date, 7), 1, nowTime, nowTime);
			 str=database.addObject(student,parent,tbUser,tbUser1,ofuser,ofuser1,tbRelation,tbUserinfoclass,viptime);
			 result="success".equals(str)?"信息添加成功":"信息添加失败";
		 }
		 return result;
	}
	public Long checkParentPhone(String phone){
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("userInfoPhone", phone);
		 return database.getCount(TbUserinfo.class, map);
	}
	public Long checkStudentQQ(String QQ){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userinfoQq", QQ);
		return database.getCount(TbUserinfo.class, map);
	}
	public List<TbCourseWeb> getTeatherClass(Integer userinfoId){
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbUserinfo", userinfo);
		List<TbUserinfoclass> list = database.getObjects(TbUserinfoclass.class, map);
		return TWObjectUtil.getListWeb(list, "TbUserinfoclassWeb");
	}
	public ResultJson curriculumDetail(Integer curriculumId) {
		TbCurriculum tbCurriculum = getObjectByClazz(TbCurriculum.class, curriculumId);
		Map<String, Object> data=new HashMap<String, Object>();
		if(tbCurriculum==null||tbCurriculum.getTbBranchschool()==null||tbCurriculum.getTbBranchschool().getBranchSchoolId()==null){
			data.put("branchSchoolName", "");
			data.put("schoolAddress", "");
		}else{
			TbBranchschool tbBranchschool = getObjectByClazz(TbBranchschool.class, tbCurriculum.getTbBranchschool().getBranchSchoolId());
			data.put("branchSchoolName", TWObjectUtil.getString(tbBranchschool.getBraschName()));
			data.put("schoolAddress", TWObjectUtil.getString(tbBranchschool.getBraschAddress()));
		}
		if(tbCurriculum==null)
			tbCurriculum=new TbCurriculum();
		data.put("phone", TWObjectUtil.getString(tbCurriculum.getPhone()));
		data.put("direction", TWObjectUtil.getString(tbCurriculum.getDirection()));
		data.put("startTerm", TWObjectUtil.getString(tbCurriculum.getStartTerm()));
		data.put("enterNeed", TWObjectUtil.getString(tbCurriculum.getEnterNeed()));
		data.put("coursename", TWObjectUtil.getString(tbCurriculum.getCourseName()));
		data.put("totalStudyTime", TWObjectUtil.getString(tbCurriculum.getTotalStudyTime()));
		data.put("courseUrl", TWObjectUtil.getNomalPicPath(tbCurriculum.getCourseUrl()));
		data.put("studyGoal", TWObjectUtil.getString(tbCurriculum.getStudyGoal()));
		data.put("endTerm", TWObjectUtil.getString(tbCurriculum.getEndTerm()));
		data.put("courseGoodness", TWObjectUtil.getString(tbCurriculum.getCourseGoodness()));
		data.put("studyCosts", tbCurriculum.getStudyCosts());
		data.put("courseIntroduce", TWObjectUtil.getString(tbCurriculum.getCourseIntroduce()));
		data.put("textbookCost", tbCurriculum.getTextbookCost());
		data.put("studyTime", TWObjectUtil.getString(tbCurriculum.getStudyTime()));
		data.put("courseBook", TWObjectUtil.getString(tbCurriculum.getCourseBook()));
		return ResultJson.crateSuccJson(data);
	}
	public List getTeacherClass(Integer id){
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbUserinfo", userinfo);
		List<TbUserinfoclass> list = database.getObjects(TbUserinfoclass.class, map);
		return TWObjectUtil.getListWeb(list, "TbUserinfoclassWeb");
	}
	public ResultJson schoolDetail(Integer schoolId) {
		if(schoolId==null||schoolId==0)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL或0");
		 TbSchool tbSchool = getObjectByClazz(TbSchool.class, schoolId);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("tbBranchschool", tbSchool.toDetailJson());
		DetachedCriteria dc=DetachedCriteria.forClass(TbCurriculum.class);
		dc.add(Restrictions.eq("tbSchool", tbSchool));
		dc.addOrder(Order.desc("defineSort"));
		dc.addOrder(Order.desc("defineSort2"));
		dc.addOrder(Order.asc("courseName"));
		List<Object> list=database.webObjects(dc);
		list=TWObjectUtil.getListWeb(list, "TbCourseWeb");
		data.put("courses", list);
		return ResultJson.crateSuccJson(data);
	}
	/*public ResultJson campusMap(Integer branchschoolId) {
		if(branchschoolId==null)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL");
		Map<String, Object> data=new HashMap<String, Object>();
		List<TbSchoolWeb> listSchool=new ArrayList<TbSchoolWeb>();
		List<TbBranchschoolWeb> list=new ArrayList<TbBranchschoolWeb>();
		Map<String, Object> map=new HashMap<String, Object>();
		if(branchschoolId!=0){
			TbSchool tbSchool = TWObjectUtil.getSchoolByBranchschool(branchschoolId);
			map.put("tbSchool", tbSchool);
			listSchool.add(tbSchool.toJson());
		}
		else
		{
			List<TbSchool> list1 = database.getObjects(TbSchool.class, map);
			for (TbSchool tbSchool : list1) {
				listSchool.add(tbSchool.toJson());
			}
		}
		List<TbBranchschool> tbBranchschools = database.getObjects(TbBranchschool.class, map);
		for (TbBranchschool tbBranchschool : tbBranchschools) {
			list.add(getTbBranchschoolWebByTbBranchschoolOld(tbBranchschool));
		}
		data.put("list", list);
		data.put("listSchool", listSchool);
		return ResultJson.crateSuccJson(data);
	}*/
	public ResultJson updateVersions(String type, String versions, String basePicUrl) {
		TbSystemconfig syscon;
		Map<String, Object> data=new HashMap<String, Object>();
		if("Android".equals(type)){
		syscon=database.webObject(TbSystemconfig.class, 1);
		if(syscon.getMinVersion().compareTo(versions)>0){
			data.put("isMust", 0);//0-必须更新，1-非必须更新,2-最新版本
		}else{
			if(syscon.getMaxVersion().compareTo(versions)<=0)
				data.put("isMust", 2);
			else
			data.put("isMust", 1);//1-必须更新，0-非必须更新
		}
		data.put("url", TWDataUtil.getAndroidAppUrl());
//		data.put("url", "http://192.168.0.129:8080/qiyangs/images/nomal/Master.apk");
		}
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson onLineTestPC(Page page, Integer testId, Integer userinfoId) {
		if(testId==null||userinfoId==null)
			return ResultJson.createFailJson(-1, "testId或userinfoId为null");
		TbTest tbTest=new TbTest();
		tbTest.setTestId(testId);
		Map<String, Object> data=new HashMap<String, Object>();
		Page page1 = getObjectPage(page, TbPracticetitle.class, tbTest, "TbPracticetitle",null);
		List list = page1.getCurrentList();
		List listOut = new ArrayList<TbPracticetitleWeb>();
		if(list==null||list.isEmpty())
			return ResultJson.crateNullJson("查无记录");
		for (Object obj : list) {
			DetachedCriteria dc=DetachedCriteria.forClass(TbPracticeoption.class);
			TbPracticetitle tbPracticetitle = new TWObject().getT(TbPracticetitle.class, obj);
			dc.add(Restrictions.eq("tbPracticetitle", tbPracticetitle));
			ArrayList<Object> list1 = database.webObjects(dc);
			List list2 = TWObjectUtil.getListWeb(list1, "TbPracticeoptionWeb");
			data.put(tbPracticetitle.getPracticeId().toString(), list2);
			String historyAnswer = getHistoryAnswer01(tbPracticetitle, userinfoId);
			TbPracticetitleWeb tbPracticetitleWeb = tbPracticetitle.toWeb();
			tbPracticetitleWeb.setHistoryAnswer(TWObjectUtil.getString(historyAnswer));
			listOut.add(tbPracticetitleWeb);
		}
		page1.setCurrentList(listOut);
		data.put("page", page1);
		return ResultJson.crateSuccJson(data);
	}
	private Integer checkExercise(Integer practiceId,Integer userinfoId){
		DetachedCriteria dc=DetachedCriteria.forClass(TbHistoryquestion.class);
		TbPracticetitle practicetitle=new TbPracticetitle();
		practicetitle.setPracticeId(practiceId);
		TbUserinfo userinfo=new TbUserinfo();
		userinfo.setUserInfoId(userinfoId);
		dc.add(Restrictions.eq("tbPracticetitle", practicetitle));
		dc.add(Restrictions.eq("tbUserinfo", userinfo));
		Integer num = database.webObjectsNumber(dc);
		return num;
		
	}
	public ResultJson addTbHistoryquestionPC(String answer, Integer practiceId,
			Integer testId,Integer userinfoId) {
		if(StringUtils.isEmpty(answer))
		 return	ResultJson.createFailJson(-1, "先答题再提交");
		else if(practiceId==null)
			return	ResultJson.createFailJson(-1, "服务器无法获取题目参数");
		else if(testId==null)
			return ResultJson.createFailJson(-1, "服务器无法获取习题参数");
		else if(userinfoId==null)
			return ResultJson.createFailJson(-1, "服务器无法获取个人信息");
		if(checkExercise(practiceId,userinfoId)>0)
			return ResultJson.crateNullJson("此题已答，请勿重复提交！");
		TbPracticetitle tbPracticetitle = database.webObject(TbPracticetitle.class, practiceId);
		Integer trueOrFalse=answer.equals(tbPracticetitle.getAnswer())?0:1;
		TbHistoryquestionWeb tbHistoryquestionWeb=new TbHistoryquestionWeb(null, practiceId, null, testId, null, userinfoId, answer, null, 1, trueOrFalse, null, null);
		String result = addTbHistoryquestion("TbHistoryquestion", tbHistoryquestionWeb);
		TbTest tbTest=new TbTest();
		tbTest.setTestId(testId);
		DetachedCriteria dc=DetachedCriteria.forClass(TbPracticetitle.class);
		dc.add(Restrictions.eq("tbTest", tbTest));
		Integer allcount = database.webObjectsNumber(dc);
		TbUserinfo userinfo=new TbUserinfo();
		userinfo.setUserInfoId(userinfoId);
		Long countT = getHistoryAllCount(tbTest, userinfo);
		if(countT+1==allcount){
			Map map = statisticsTbHistoryquestion(userinfoId, testId);
			return ResultJson.crateSuccJson(map);}
		return "添加成功".equals(result)?ResultJson.crateSuccJson(new HashMap<String, Object>()):ResultJson.createFailJson(-1, "添加失败");
	}
	public ResultJson recentLinkman(String userNames) {
		JSONArray jsonArray = JSONArray.fromObject(userNames);
		String[] strPath = new String[jsonArray.size()];
		List list=new ArrayList<TbUserinfoWeb>();
		for (int i = 0; i < strPath.length; i++) {
			strPath[i] = jsonArray.getString(i);
			DetachedCriteria dc=DetachedCriteria.forClass(TbUser.class);
			dc.add(Restrictions.eq("userName", strPath[i]));
			dc.createAlias("tbUserinfo","c"); 
			dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
			dc.add(Restrictions.eq("c.userInfoRoot", 1));
			ArrayList<TbUser> list1 = database.webObjects(TbUser.class,dc);
			if(list1==null||list1.isEmpty())
				continue;
			TbUser tbUser = list1.get(0);
			TbUserinfoWeb tbUserinfoWeb=new TbUserinfoWeb();
			tbUserinfoWeb.setUserinfoAvatar(TWPictureUtil.getNomalPicPath(tbUser.getTbUserinfo().getUserInfoAvatar()));
			tbUserinfoWeb.setUserName(TWObjectUtil.getString(tbUser.getUserName()));
			tbUserinfoWeb.setUserinfoId(tbUser.getTbUserinfo().getUserInfoId());
			tbUserinfoWeb.setUserinfoName(TWObjectUtil.getString(tbUser.getTbUserinfo().getUserInfoName()));
			list.add(tbUserinfoWeb);
		}
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson schoolNoticeList(Page page, Integer branchschoolId) {
		TbBranchschool branchschool = database.webObject(TbBranchschool.class, branchschoolId);
		Map<String, Object> data = new HashMap<String, Object>();
		if(null!=branchschoolId && 0!=branchschoolId){
			DetachedCriteria dc=DetachedCriteria.forClass(TbSchoolnotice.class);
			dc.add(Restrictions.eq("tbBranchschool", branchschool));
			dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
			dc.addOrder(Order.desc("schoolNoticeId"));
			 page= database.webObjectsPage(page, dc);
			 List list= page.getCurrentList();
			ArrayList<Object> strList = new ArrayList<Object>();
			for (Object obj : list) {
				if(obj instanceof TbSchoolnotice){
				TbSchoolnotice	tbSchoolnotice=(TbSchoolnotice)obj;
				TbSchoolnoticeWeb tbschoolnoticeweb = new TbSchoolnoticeWeb();
				tbschoolnoticeweb.setSchoolNoticeId(TWObjectUtil.getInteger(tbSchoolnotice.getSchoolNoticeId()));
				tbschoolnoticeweb.setSchoolnoticeTitle(TWObjectUtil.getString(tbSchoolnotice.getSchnotTitle()));
				tbschoolnoticeweb.setUserinfoName(TWObjectUtil.getString(tbSchoolnotice.getTbUserinfo().getUserInfoName()));
				tbschoolnoticeweb.setSchnotAddTime(TWDataUtil.TimestampFormat(tbSchoolnotice.getTime()));
				strList.add(tbschoolnoticeweb);
				}
			}
			page.setCurrentList(strList);
			data.put("page", page);
		}
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson complainPage(Page page, Integer branchschoolId,
			Integer courseId, Integer userinfoId) {
		Map<String, Object> data=new HashMap<String, Object>();
		TbUserinfo tbUserinfo=null;
		if(userinfoId!=null){
			tbUserinfo = new TbUserinfo();
			tbUserinfo.setUserInfoId(userinfoId);
			}
		TbCourse tbCourse=null;
		if(courseId!=null){
			tbCourse=new TbCourse();
			tbCourse.setCourseId(courseId);
		}
		TbBranchschool tbBranchschool=null;
		if(branchschoolId!=null){
			tbBranchschool=new TbBranchschool();
			tbBranchschool.setBranchSchoolId(branchschoolId);
		}
		
		DetachedCriteria dc=DetachedCriteria.forClass(TbComplain.class);
		if(tbBranchschool!=null)
		dc.add(Restrictions.eq("tbBranchschool", tbBranchschool));
		if(tbUserinfo!=null)
		dc.add(Restrictions.eq("tbUserinfoByUserInfoId", tbUserinfo));
		if(tbCourse!=null)
		dc.add(Restrictions.eq("tbCourse", tbCourse));
//		dc.setFetchMode("tbUserinfoByUserInfoId", FetchMode.JOIN);
		dc.setFetchMode("tbUserinfoByTbUserInfoId", FetchMode.JOIN);
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("complainId"));
		Page page1 = database.webObjectsPage(page, dc);
		List list = page1.getCurrentList();
		if(list==null||list.isEmpty())
			return ResultJson.crateNullJson("查无记录");
		Map<Integer, Object> dataMin=new HashMap<Integer, Object>();
		ArrayList<Object> strList = new ArrayList<Object>();
		for (Object obj : list) {
			if(obj instanceof TbComplain){
				TbComplain tbComplain = (TbComplain)obj;
				TbUserinfo userinfo = TWObjectUtil.getChildByParent(tbComplain.getTbUserinfoByUserInfoId());
				TbComplainWeb tbcomplainweb = new TbComplainWeb();
				tbcomplainweb.setComplainId(tbComplain.getComplainId());
				tbcomplainweb.setStudentName(TWObjectUtil.getString(userinfo.getUserInfoName()));
				String time = TWDataUtil.date(tbComplain.getTime());
				tbcomplainweb.setTime(time);
				tbcomplainweb.setComplainContent(TWObjectUtil.getString(tbComplain.getComplainContent()));
				tbcomplainweb.setComplainStatus(TWObjectUtil.getString(tbComplain.getComplainStatus()));
				strList.add(tbcomplainweb);
				DetachedCriteria dc2=DetachedCriteria.forClass(TbComplaindetails.class);
				dc2.add(Restrictions.eq("isValid", 1));
				dc2.setFetchMode("tbUserinfo", FetchMode.JOIN);
				dc2.add(Restrictions.eq("tbComplain", tbComplain));
				dc2.addOrder(Order.desc("comdetId"));
				ArrayList<TbComplaindetails> list2 = database.webObjects(TbComplaindetails.class, dc2);
				if(list2.isEmpty()){
					dataMin.put(tbComplain.getComplainId(),new TbComplaindetailsWeb());
					continue;
					}
				if(list2.get(0) instanceof TbComplaindetails){
					TbComplaindetails tbComplaindetails=list2.get(0);
						TbUserinfo tbUserinfo1 = tbComplaindetails.getTbUserinfo();
						TbComplaindetailsWeb tbcomplaindetailsweb = new TbComplaindetailsWeb();
						tbcomplaindetailsweb.setTeacherName(TWObjectUtil.getString(tbUserinfo1.getUserInfoName()));
						tbcomplaindetailsweb.setComdetContent(TWObjectUtil.getString(tbComplaindetails.getComdetContent()));
						String dateTime = TWDataUtil.date(tbComplaindetails.getTime());
						tbcomplaindetailsweb.setTime(dateTime);
						if(tbComplain.getIsSatisfie()!=null){
							tbcomplaindetailsweb.setIsSatisfie(TWObjectUtil.getInteger(tbComplain.getIsSatisfie()));
							String altertime = TWDataUtil.date(tbComplain.getAlterTime());
							tbcomplaindetailsweb.setAlterTime(altertime);
						}
				dataMin.put(tbComplain.getComplainId(),tbcomplaindetailsweb);
				}
			}
		}
		page1.setCurrentList(strList);
		data.put("tbcomplaindetails", dataMin);
		data.put("tbcomplain", page1);
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson campusMap02(Integer branchschoolId, Double childLongitude, Double childLatitude) {
		
		if(branchschoolId==null)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL");
		Map<String, Object> data=new HashMap<String, Object>();
		List<TbBranchschoolWeb> listSchool=new ArrayList<TbBranchschoolWeb>();
		Map<String, Object> map=new HashMap<String, Object>();
		if(branchschoolId!=0){
			TbSchool tbSchool = TWObjectUtil.getSchoolByBranchschool(branchschoolId);
			map.put("tbSchool", tbSchool);
			listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
		}
		else
		{
			List<TbSchool> list1 = database.getObjects(TbSchool.class, map);
			for (TbSchool tbSchool : list1) {
				listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
			}
		}
		List<TbBranchschool> tbBranchschools = database.getObjects(TbBranchschool.class, map);
		for (TbBranchschool tbBranchschool : tbBranchschools) {
			listSchool.add(getTbBranchschoolWebByTbBranchschool(tbBranchschool));
		}
			Set<String[]> set=new HashSet<String[]>();
		for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
			String[] strs=new String[2];
			strs[0]=tbBranchschoolWeb.getBranchschoolLongitude();
			strs[1]=tbBranchschoolWeb.getBranchschoolLatitude();
			Boolean boo=false;
			if(set.isEmpty()){
				if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1])&&specificRange(childLongitude, childLatitude, Double.valueOf(strs[0]), Double.valueOf(strs[1])))
				set.add(strs);
			}
			else{
			for (String[] str : set) {
				if(str[0].equals(strs[0])&&str[1].equals(strs[1])){
					boo=true;
					}
			}
			if(!boo){
			if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1])&&specificRange(childLongitude, childLatitude, Double.valueOf(strs[0]), Double.valueOf(strs[1])))
			set.add(strs);
			}
			}
		}
		List list=new ArrayList();
		for (String[] strs : set) {
			List<TbBranchschoolWeb> branchschoolWebs =new ArrayList<TbBranchschoolWeb>();
			for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
				if(strs[0].equals(tbBranchschoolWeb.getBranchschoolLongitude())&&strs[1].equals(tbBranchschoolWeb.getBranchschoolLatitude()))
					branchschoolWebs.add(tbBranchschoolWeb);
			}
			list.add(branchschoolWebs);
		}
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}
	private Boolean specificRange(Double lon1, Double lat1, Double lon2,
			Double lat2){
		return LalDistance.getShortDistance(lon1, lat1, lon2, lat2)>5000?false:true;
	}
	private Boolean specificRange01(Double lon1, Double lat1, Double lon2,
			Double lat2,Double range){
		range=range>=1500.0?range:1500.0;
		return LalDistance.getShortDistance(lon1, lat1, lon2, lat2)>=range?false:true;
	}
	public ResultJson campusMap03(Integer branchschoolId, Double childLongitude,
			Double childLatitude, String range) {
		if(branchschoolId==null)
			return ResultJson.createFailJson(-1, "branchschoolId为NULL");
		Map<String, Object> data=new HashMap<String, Object>();
		List<TbBranchschoolWeb> listSchool=new ArrayList<TbBranchschoolWeb>();
		Map<String, Object> map=new HashMap<String, Object>();
		if(branchschoolId!=0){
			TbSchool tbSchool = TWObjectUtil.getSchoolByBranchschool(branchschoolId);
			map.put("tbSchool", tbSchool);
			listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
		}
		else
		{
			List<TbSchool> list1 = database.getObjects(TbSchool.class, map);
			for (TbSchool tbSchool : list1) {
				listSchool.add(getTbBranchschoolWebByTbschool(tbSchool));
			}
		}
		List<TbBranchschool> tbBranchschools = database.getObjects(TbBranchschool.class, map);
		for (TbBranchschool tbBranchschool : tbBranchschools) {
			listSchool.add(getTbBranchschoolWebByTbBranchschool(tbBranchschool));
		}
			Set<String[]> set=new HashSet<String[]>();
		for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
			String[] strs=new String[2];
			strs[0]=tbBranchschoolWeb.getBranchschoolLongitude();
			strs[1]=tbBranchschoolWeb.getBranchschoolLatitude();
			Boolean boo=false;
			if(set.isEmpty()){
				if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1])&&specificRange01(childLongitude, childLatitude, Double.valueOf(strs[0]), Double.valueOf(strs[1]),Double.valueOf(range)))
				set.add(strs);
			}
			else{
			for (String[] str : set) {
				if(str[0].equals(strs[0])&&str[1].equals(strs[1])){
					boo=true;
					}
			}
			if(!boo){
			if(StringUtils.isNotEmpty(strs[0])&&StringUtils.isNotEmpty(strs[1])&&specificRange01(childLongitude, childLatitude, Double.valueOf(strs[0]), Double.valueOf(strs[1]),Double.valueOf(range)))
			set.add(strs);
			}
			}
		}
		List list=new ArrayList();
		for (String[] strs : set) {
			List<TbBranchschoolWeb> branchschoolWebs =new ArrayList<TbBranchschoolWeb>();
			for (TbBranchschoolWeb tbBranchschoolWeb : listSchool) {
				if(strs[0].equals(tbBranchschoolWeb.getBranchschoolLongitude())&&strs[1].equals(tbBranchschoolWeb.getBranchschoolLatitude()))
					branchschoolWebs.add(tbBranchschoolWeb);
			}
			list.add(branchschoolWebs);
		}
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}
}
