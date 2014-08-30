package com.qiYang.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.TbAttendance;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbClassnotice;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbHistoryquestion;
import com.qiYang.model.TbInfonotice;
import com.qiYang.model.TbNotice;
import com.qiYang.model.TbPracticeoption;
import com.qiYang.model.TbPracticetitle;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSubjectinbranchschool;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;
import com.qiYang.model.web.TbBranchschoolWeb;
import com.qiYang.model.web.TbClassnoticeWeb;
import com.qiYang.model.web.TbCourseWeb;
import com.qiYang.model.web.TbHistoryquestionWeb;
import com.qiYang.model.web.TbInfonoticeWeb;
import com.qiYang.model.web.TbNoticeWeb;
import com.qiYang.model.web.TbPracticeoptionWeb;
import com.qiYang.model.web.TbPracticetitleWeb;
import com.qiYang.model.web.TbSchoolWeb;
import com.qiYang.model.web.TbSubjectinbranchschoolWeb;
import com.qiYang.model.web.TbTestWeb;
import com.qiYang.model.web.TbTestfinishWeb;
import com.qiYang.model.web.TbUserinfoWeb;
import com.qiYang.model.web.TbUserinfoclassWeb;
import com.qiYang.service.GogoServiceImpl;

public class TWObjectUtil {
	// list转化 list 进来需要改格式的方法 T是model层的类 strGet是方法名
	public static <T> List getList(List listIn, T t, String strGet) {
		if (listIn == null) {
			System.out.println("NewPagelist:---> NULL--->");
			return new ArrayList();
		}
		if (listIn.isEmpty()) {
			System.out.println("NewPagelist:---> 空--->");
			return new ArrayList();
		}
		Class<T> clazz = (Class<T>) t.getClass();
		Method methodGet = null;
		try {
			methodGet = clazz.getDeclaredMethod(strGet);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		List listOut = new ArrayList();
		for (Object object : listIn) {
			T obj = new TWObject().getT(clazz, object);
			try {
				listOut.add(methodGet.invoke(obj));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return listOut;
	}

	// 空值判断控制，并强转obj成T
	private <T> T getT(Class<T> clazz, Object obj) {
		if (obj != null) {
			return (T) obj;
		}
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return (T) obj;
	}

	// 转newPage的CurrentList
	public static List getListWeb(List listIn, String classWeb) {
		if (listIn == null) {
			// System.out.println("NewPagelist:---> NULL--->" + classWeb);
			return new ArrayList();
		}
		if (listIn.isEmpty()) {
			// System.out.println("NewPagelist:---> 空--->" + classWeb);
			return new ArrayList();
		}
		List listOut = null;

		if ("TbUserinfoWeb".equals(classWeb)) {
			listOut = new ArrayList<TbUserinfoWeb>();
			for (Object object : listIn) {
				TbUserinfo userinfo = new TWObject().getT(TbUserinfo.class,
						object);
				listOut.add(userinfo.toPageVo());
			}
		} else if ("TbUserinfoclassWeb".equals(classWeb)) {
			listOut = new ArrayList<TbUserinfoclassWeb>();
			for (Object object : listIn) {
				TbUserinfoclass userinfoclass = new TWObject().getT(
						TbUserinfoclass.class, object);
				listOut.add(userinfoclass.toCourseWeb());
			}
		} else if ("studentClassWeb".equals(classWeb)) {
			listOut = new ArrayList<TbUserinfoclassWeb>();
			for (Object object : listIn) {
				TbUserinfoclass userinfoclass = new TWObject().getT(
						TbUserinfoclass.class, object);
				listOut.add(userinfoclass.toStudentWeb());
			}
		} else if ("courseToStudent".equals(classWeb)) {
			listOut = new ArrayList<TbUserinfoclassWeb>();
			for (Object object : listIn) {
				TbUserinfoclass userinfoclass = new TWObject().getT(
						TbUserinfoclass.class, object);
				listOut.add(userinfoclass.courseToStudent());
			}
		} else if ("TbUserinfoclassWebTOPage".equals(classWeb)) {
			listOut = new ArrayList<TbUserinfoclassWeb>();
			for (Object object : listIn) {
				TbUserinfoclass userinfoclass = new TWObject().getT(
						TbUserinfoclass.class, object);
				listOut.add(userinfoclass.toPageWeb());
			}
		} else if ("tbAttendanceStudent".equals(classWeb)) {
			listOut = new ArrayList<TbUserinfoclassWeb>();
			for (Object object : listIn) {
				TbUserinfoclass userinfoclass = new TWObject().getT(
						TbUserinfoclass.class, object);
				listOut.add(userinfoclass.toAttendanceWeb());
			}
		} else if ("TbClassnoticeWeb".equals(classWeb)) {
			listOut = new ArrayList<TbClassnoticeWeb>();
			for (Object object : listIn) {
				TbClassnotice tbClassnotice = new TWObject().getT(
						TbClassnotice.class, object);
				listOut.add(tbClassnotice.toListWeb());
			}
		} else if ("noticeWeb".equals(classWeb)) {
			listOut = new ArrayList<TbNoticeWeb>();
			for (Object object : listIn) {
				TbNotice notice = new TWObject().getT(TbNotice.class, object);
				listOut.add(notice.toPageWeb());
			}
		} else if ("TbInfonoticeWeb".equals(classWeb)) {
			listOut = new ArrayList<TbInfonoticeWeb>();
			for (Object object : listIn) {
				TbInfonotice tbInfonotice = new TWObject().getT(
						TbInfonotice.class, object);
				listOut.add(tbInfonotice.toJson());
			}
		} else if ("TbSubjectinbranchschoolWeb".equals(classWeb)) {
			listOut = new ArrayList<TbSubjectinbranchschoolWeb>();
			for (Object object : listIn) {
				TbSubjectinbranchschool tbSubjectinbranchschool = new TWObject()
						.getT(TbSubjectinbranchschool.class, object);
				listOut.add(tbSubjectinbranchschool.toWeb());
			}
		} else if ("TbCourseWeb".equals(classWeb)) {
			listOut = new ArrayList<TbCourseWeb>();
			for (Object object : listIn) {
				TbCurriculum tbCourse = new TWObject().getT(TbCurriculum.class,
						object);
				listOut.add(tbCourse.toWeb());
			}
		} else if ("classWeb".equals(classWeb)) {
			listOut = new ArrayList<TbCourseWeb>();
			for (Object object : listIn) {
				TbCourse tbCourse = new TWObject().getT(TbCourse.class, object);
				listOut.add(tbCourse.toWeb());
			}
		} else if ("TbSchoolWeb".equals(classWeb)) {
			listOut = new ArrayList<TbSchoolWeb>();
			for (Object object : listIn) {
				TbSchool tbSchool = new TWObject().getT(TbSchool.class, object);
				listOut.add(tbSchool.toPageWeb());
			}
		} else if ("TbBranchschoolWeb".equals(classWeb)) {
			listOut = new ArrayList<TbBranchschoolWeb>();
			for (Object object : listIn) {
				TbBranchschool obj = new TWObject().getT(TbBranchschool.class,
						object);
				listOut.add(obj.toPartWeb());
			}
		} else if ("TbBranchschooltoJson".equals(classWeb)) {
			listOut = new ArrayList<TbBranchschoolWeb>();
			for (Object object : listIn) {
				TbBranchschool obj = new TWObject().getT(TbBranchschool.class,
						object);
				listOut.add(obj.toJson());
			}
		} else if ("TbPracticetitleWeb".equals(classWeb)) {
			listOut = new ArrayList<TbPracticetitleWeb>();
			for (Object object : listIn) {
				TbPracticetitle tbPracticetitle = new TWObject().getT(
						TbPracticetitle.class, object);
				listOut.add(tbPracticetitle.toWeb());
			}
		} else if ("TbPracticetitleWebTOPage".equals(classWeb)) {
			listOut = new ArrayList<TbPracticetitleWeb>();
			for (Object object : listIn) {
				TbPracticetitle tbPracticetitle = new TWObject().getT(
						TbPracticetitle.class, object);
				listOut.add(tbPracticetitle.toPageWeb());
			}
		} else if ("TbPracticeoptionWeb".equals(classWeb)) {
			listOut = new ArrayList<TbPracticeoptionWeb>();
			for (Object object : listIn) {
				TbPracticeoption tbPracticeoption = new TWObject().getT(
						TbPracticeoption.class, object);
				listOut.add(tbPracticeoption.toWeb());
			}
		} else if ("TbPracticeoptionWebTOPage".equals(classWeb)) {
			listOut = new ArrayList<TbPracticeoptionWeb>();
			for (Object object : listIn) {
				TbPracticeoption tbPracticeoption = new TWObject().getT(
						TbPracticeoption.class, object);
				listOut.add(tbPracticeoption.toPageWeb());
			}
		} else if ("TbHistoryquestionWeb".equals(classWeb)) {
			listOut = new ArrayList<TbHistoryquestionWeb>();
			for (Object object : listIn) {
				TbHistoryquestion tbHistoryquestion = new TWObject().getT(
						TbHistoryquestion.class, object);
				// listOut.add(tbHistoryquestion.toWeb());
			}
		} else if ("TbTestWeb".equals(classWeb)) {
			listOut = new ArrayList<TbTestWeb>();
			for (Object object : listIn) {
				TbTest tbTest = new TWObject().getT(TbTest.class, object);
				listOut.add(tbTest.toPageWeb());
			}
		} else if ("TbTestfinishWeb".equals(classWeb)) {
			listOut = new ArrayList<TbTestfinishWeb>();
			for (Object object : listIn) {
				// TbTestfinish tbTestfinish = new
				// TWObject().getT(TbTestfinish.class,object);
				// listOut.add(tbTestfinish.toWeb());
			}
		}
		return listOut;
	}

	// new TbUserinfo() 再set id为userinfoId，当查询条件
	public static TbUserinfo getUserinfoSetId(Integer userinfoId) {
		if (userinfoId == null)
			return new TbUserinfo();
		TbUserinfo userinfo = new TbUserinfo();
		userinfo.setUserInfoId(userinfoId);
		return userinfo;
	}

	// 通过userinfoId获取userName
	public static String getUserName(Integer userinfoId) {
		if (userinfoId == null)
			return "";
		TbUserinfo userinfo = getUserinfoSetId(userinfoId);
		Map<String, Object> mapId = new HashMap<String, Object>();
		mapId.put("tbUserinfo", userinfo);
		TbUser tbUser = new DataBaseDaoImpl().getObject(TbUser.class, mapId);
		return getString(tbUser.getUserName());
	}

	// 通过userinfoId获取userName
	public static String getUserName(TbUserinfo userinfo) {
		if (userinfo == null)
			return "";
		if (userinfo.getUserInfoId() == null)
			return "";
		Map<String, Object> mapId = new HashMap<String, Object>();
		mapId.put("tbUserinfo", userinfo);
		TbUser tbUser = new DataBaseDaoImpl().getObject(TbUser.class, mapId);
		return getString(tbUser.getUserName());
	}

	// new TbBranchschool() 再set id为branchschoolId，当查询条件
	public static TbBranchschool getBranchschoolSetId(Integer branchschoolId) {
		if (branchschoolId == null)
			return new TbBranchschool();
		TbBranchschool branchschool = new TbBranchschool();
		branchschool.setBranchSchoolId(branchschoolId);
		return branchschool;
	}

	// new TbCourse() 再set id为courselId，当查询条件
	public static TbCourse getCoureSetId(Integer courseId) {
		if (courseId == null)
			return new TbCourse();
		TbCourse course = new TbCourse();
		course.setCourseId(courseId);
		return course;
	}

	// new TbSchool() 再set id为schoolId，当查询条件
	public static TbSchool getSchoolSetId(Integer schoolId) {
		if (schoolId == null)
			return new TbSchool();
		TbSchool school = new TbSchool();
		school.setSchoolId(schoolId);
		return school;
	}

	// new TbSchool() 再set id为schoolId，当查询条件
	public static TbSchool getSchoolByBranchschool(Integer branchschoolId) {
		if (branchschoolId == null)
			return new TbSchool();
		GogoServiceImpl gogoServiceImpl = new GogoServiceImpl();
		TbBranchschool branchschool = gogoServiceImpl.getObjectByClazz(
				TbBranchschool.class, branchschoolId);
		TbSchool tbSchool = branchschool.getTbSchool() == null ? new TbSchool()
				: branchschool.getTbSchool();
		tbSchool = gogoServiceImpl.getObjectByClazz(TbSchool.class,
				tbSchool.getSchoolId());
		return tbSchool;
	}

	// 通过TbUserinfo的Id，获取userinfo；
	public static TbUserinfo getTbUserinfo(Integer id) {
		Object userinfo = new GogoServiceImpl().getObjectByClazz(
				TbUserinfo.class, id);
		return new TWObject().getT(TbUserinfo.class, userinfo);
	}

	// 通过TbUserinfo获取学校
	public static TbSchool getTbSchool(TbUserinfo tbUserinfo) {
		TbUserinfo tbUserinfo2 = null;
		if (tbUserinfo.getUserInfoId() != null)
			tbUserinfo2 = new GogoServiceImpl().getObjectByClazz(
					TbUserinfo.class, tbUserinfo.getUserInfoId());
		if (tbUserinfo2 == null)
			tbUserinfo2 = new TbUserinfo();
		return new TWObject().getT(TbSchool.class, tbUserinfo2.getTbSchool());
	}

	/*
	 * //通过TbUserinfo获取学校 public static TbSchool getTbSchool(TbBranchschool
	 * tbBranchschool){ TbUserinfo tbUserinfo2 = null;
	 * if(tbUserinfo.getUserInfoId()!=null) tbUserinfo2=new
	 * GogoServiceImpl().getObjectByClazz(TbUserinfo.class,
	 * tbUserinfo.getUserInfoId()); if(tbUserinfo2==null) tbUserinfo2=new
	 * TbUserinfo(); return new
	 * TWObject().getT(TbSchool.class,tbUserinfo2.getTbSchool()); }
	 */
	// 通过TbUserinfo获取分校，前提是仅有老师，校长可用
	public static TbBranchschool getTbBranchschool(TbUserinfo tbUserinfo) {
		Object branchschool = null;
		if (tbUserinfo.getTbBranchschool() != null
				&& tbUserinfo.getTbBranchschool().getBranchSchoolId() != null)
			branchschool = new GogoServiceImpl().getObjectByClazz(
					TbBranchschool.class, tbUserinfo.getTbBranchschool()
							.getBranchSchoolId());
		return new TWObject().getT(TbBranchschool.class, branchschool);
	}

	// 通过孩子获得家长对象
	public static TbUserinfo getParentByChild(TbUserinfo userinfo) {
		Map<String, Object> mapId = new HashMap<String, Object>();
		mapId.put("tbUserinfoByUserInfoId", userinfo);
		mapId.put("isValid", 1);
		TbRelation tbRelation = new TbRelation();
		Object relation = new DataBaseDaoImpl().getObject(TbRelation.class,
				mapId);
		tbRelation = new TWObject().getT(TbRelation.class, relation);
		TbUserinfo parent = null;
		if (tbRelation.getTbUserinfoByTbUserInfoId() != null
				&& tbRelation.getTbUserinfoByTbUserInfoId().getUserInfoId() != null)
			parent = getTbUserinfo(tbRelation.getTbUserinfoByTbUserInfoId()
					.getUserInfoId());
		return parent == null ? new TbUserinfo() : parent;
	}

	// 通过家长获得孩子对象
	public static TbUserinfo getChildByParent(TbUserinfo userinfo) {
		Map<String, Object> mapId = new HashMap<String, Object>();
		mapId.put("tbUserinfoByTbUserInfoId", userinfo);
		mapId.put("isValid", 1);
		TbRelation tbRelation = new TbRelation();
		Object relation = new DataBaseDaoImpl().getObject(TbRelation.class,
				mapId);
		tbRelation = new TWObject().getT(TbRelation.class, relation);
		TbUserinfo child = null;
		if (tbRelation.getTbUserinfoByUserInfoId() != null
				&& tbRelation.getTbUserinfoByUserInfoId().getUserInfoId() != null)
			child = getTbUserinfo(tbRelation.getTbUserinfoByUserInfoId()
					.getUserInfoId());
		return child == null ? new TbUserinfo() : child;
	}

	// data.put进去值，准备发出。
	public static ResultJson dataPut(Map<String, Object> data, Page newPage) {
		if (null != newPage) {
			data.put("currentPage", newPage.getCurrentPage());
			data.put("allPages", newPage.getAllPages());
			data.put("countPerPage", newPage.getCountPerPage());
			data.put("currentList", newPage.getCurrentList());
			return ResultJson.crateSuccJson(data);
		} else
			return ResultJson.createFailJson(-1, "数据获取失败");
	}

	// 普通图片路径判断是否为空获取
	public static String getNomalPicPath(String avatar) {
		return avatar == null ? "" : TWPictureUtil.getNomalPicPath(avatar);
	}

	// 字符串判断获取
	public static String getString(String string) {
		return string!=null ? string : "";
	}

	// 整型判断获取
	public static Integer getInteger(Integer integer) {
		return integer == null ? null : integer;
	}

	// 整型判断获取
	public static Integer integerIsZero(Integer integer) {
		return integer == null ? 0 : integer;
	}

	// 日历"yyyy-MM-dd"格式判断获取
	public static String getDate(Date date) {
		return date == null ? "" : TWDataUtil.dateFormat(date);
	}

	// 日历"yyyyMMdd"格式判断获取
	public static String getDateyyyyMMdd(Date date) {
		return date == null ? "" : TWDataUtil.dateDateyyyyMMdd(date);
	}

	// 日历"yyyy-MM-dd HH:mm"格式判断获取
	public static String getTimestamp(Timestamp timestamp) {
		return timestamp == null ? "" : TWDataUtil.TimestampFormat(timestamp);
	}

	// 通过Userinfo查询表中信息tbUserinfo
	public static TbUserinfo isNullTbUserinfo(TbUserinfo userinfo) {
		TbUserinfo userinfo1 = userinfo == null ? new TbUserinfo() : userinfo;
		Integer id = userinfo1.getUserInfoId();
		if (id == null)
			return new TbUserinfo();
		TbUserinfo tbUserinfo1 = getTbUserinfo(id);
		if (tbUserinfo1 == null)
			tbUserinfo1 = new TbUserinfo();
		return tbUserinfo1;
	}

	// 通过tbUserinfo查询TbAttendance
	public static TbAttendance getTbAttendance(TbUserinfo tbUserinfo) {
		Map<String, Object> mapId = new HashMap<String, Object>();
		mapId.put("tbUserinfo", tbUserinfo);
		mapId.put("isValid", 1);
		TbAttendance tbAttendance = new TbAttendance();
		Object attendance = new DataBaseDaoImpl().getObject(TbAttendance.class,
				mapId);
		tbAttendance = new TWObject().getT(TbAttendance.class, attendance);
		return tbAttendance == null ? new TbAttendance() : tbAttendance;
	}

	public static Integer getUserinfoId(String userName) {
		Map<String, Object> mapId = new HashMap<String, Object>();
		mapId.put("userName", userName);
		TbUser tbUser = new DataBaseDaoImpl().getObject(TbUser.class, mapId);
		return tbUser.getTbUserinfo().getUserInfoId();

	}

	public static TbCity getTbCity(TbTown tbTown) {
		TbTown tbTown1 = new TWObject().isNullTbTown(tbTown);
		TbCity city = tbTown1.getTbCity();
		if (city == null || city.getCityId() == null)
			return new TbCity();
		else
			return new GogoServiceImpl().getObjectByClazz(TbCity.class,
					city.getCityId());
	}
}
