package com.qiYang.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.alipay.config.AlipayConfig;
import com.alipay.sign.RSA;
import com.alipay.util.AlipayCore;
import com.alipay.util.UtilDate;
import com.qiYang.dao.BaseDao;
import com.qiYang.dao.TransactionDao;
import com.qiYang.model.Ofuser;
import com.qiYang.model.TbAdvertisement;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbBranchschool02;
import com.qiYang.model.TbBranchschoolPopularity;
import com.qiYang.model.TbCoupon;
import com.qiYang.model.TbCurriculumCopy;
import com.qiYang.model.TbMore;
import com.qiYang.model.TbOrder;
import com.qiYang.model.TbPusher;
import com.qiYang.model.TbSchool02;
import com.qiYang.model.TbSchoolPhotoCopy;
import com.qiYang.model.TbSchoolPopularity;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbCurriculum02;
import com.qiYang.model.TbCurriculumPopularity;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbPushRecord;
import com.qiYang.model.TbRelation;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchoolApplication;
import com.qiYang.model.TbSchoolPhoto;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbTeam;
import com.qiYang.model.TbTeamCopy;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfo2;
import com.qiYang.model.web.TbBranchschoolWeb;
import com.qiYang.util.LalDistance;
import com.qiYang.util.Page;
import com.qiYang.util.ResultJson;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObjectUtil;
import com.qiYang.util.TWPictureUtil;

public class SecondaryDevelopmen {
	private static Logger loger = Logger.getLogger(SecondaryDevelopmen.class);

	/**
	 * 注册页面获取城市跟年段
	 * 
	 * @return
	 */
	public ResultJson cityAndGrage() {
		DetachedCriteria dc = DetachedCriteria.forClass(TbCity.class);
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.asc("cityName"));
		BaseDao baseDao = new BaseDao();
		ArrayList<TbCity> citys = baseDao.dCList(TbCity.class, dc);
		Map<String, Object> data = new HashMap<String, Object>(2);
		if (citys != null && !citys.isEmpty()) {
			Map<String, String> citysMap = null;
			List<Object> citysList = new ArrayList<Object>(citys.size());
			for (TbCity tbCity : citys) {
				citysMap = new HashMap<String, String>(3);
				citysMap.put("cityId", tbCity.getCityId().toString());
				citysMap.put("cityName",
						TWObjectUtil.getString(tbCity.getCityName()));
				citysMap.put("spell", TWObjectUtil.getString(tbCity.getSpell()));
				citysList.add(citysMap);
			}
			data.put("citys", citysList);
		} else {
			data.put("citys", new ArrayList<Object>(0));
		}
		DetachedCriteria gradeDc = DetachedCriteria.forClass(TbGrade.class);
		gradeDc.add(Restrictions.eq("isValid", 1));
		gradeDc.addOrder(Order.asc("gradeId"));
		ArrayList<TbGrade> grades = baseDao.dCList(TbGrade.class, gradeDc);
		if (grades != null && !grades.isEmpty()) {
			Map<String, String> gradesMap = null;
			List<Object> gradesList = new ArrayList<Object>(grades.size());
			for (TbGrade tbGrade : grades) {
				gradesMap = new HashMap<String, String>(2);
				gradesMap.put("gradeId", tbGrade.getGradeId().toString());
				gradesMap.put("gradeName",
						TWObjectUtil.getString(tbGrade.getGradeName()));
				gradesList.add(gradesMap);
			}
			data.put("grades", gradesList);
		} else {
			data.put("grades", new ArrayList<Object>(0));
		}
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 家长注册接口
	 * 
	 * @param phone
	 *            手机
	 * @param password
	 *            密码
	 * @param email
	 *            邮件
	 * @param gradeId
	 *            年段ID
	 * @param cityId
	 *            城市ID
	 * @return
	 */
	public ResultJson register(String phone, String password, String email,
			String gradeId, String cityId) {
		Integer boo = null;
		BaseDao baseDao = new BaseDao();
		DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
		dc.add(Restrictions.eq("userName", phone));
		Integer resultNum = baseDao.dCRowCount(dc);
		StringBuffer buffer = new StringBuffer(phone);
		if (resultNum > 0) {
			buffer.append("的用户账户已经存在");
			return ResultJson.createFailJson(500, buffer.toString());
		}
		DetachedCriteria ofDc = DetachedCriteria.forClass(Ofuser.class);
		ofDc.add(Restrictions.eq("username", phone));
		Integer ofDcNum = baseDao.dCRowCount(ofDc);
		if (ofDcNum > 0) {
			buffer.append("的聊天账户已经存在");
			return ResultJson.createFailJson(500, buffer.toString());
		}
		boo = new TransactionDao().register(phone, password, email,
				Integer.parseInt(gradeId), Integer.parseInt(cityId));
		if (boo != 0) {
			HashMap<String, Object> map = new HashMap<String, Object>(1);
			map.put("userinfoId", boo);
			return ResultJson.crateSuccJson(map);
		} else
			return ResultJson.createFailJson(1004, "添加失败");
	}

	/**
	 * 家长帮孩子注册接口
	 * 
	 * @param phone
	 *            手机
	 * @param password
	 *            密码
	 * @param email
	 *            邮件
	 * @param gradeId
	 *            年段ID
	 * @param sex
	 *            性别 1-男或者0-女
	 * @param userId
	 *            家长ID
	 * @return
	 */
	public ResultJson registerChild(String phone, String password,
			String email, String gradeId, String sex, String userId) {
		Boolean boo = null;
		BaseDao baseDao = new BaseDao();
		DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
		dc.add(Restrictions.eq("userName", phone));
		Integer resultNum = baseDao.dCRowCount(dc);
		StringBuffer buffer = new StringBuffer(phone);
		if (resultNum > 0) {
			buffer.append("的用户账户已经存在");
			return ResultJson.createFailJson(500, buffer.toString());
		}
		DetachedCriteria ofDc = DetachedCriteria.forClass(Ofuser.class);
		ofDc.add(Restrictions.eq("username", phone));
		Integer ofDcNum = baseDao.dCRowCount(ofDc);
		if (ofDcNum > 0) {
			buffer.append("的聊天账户已经存在");
			return ResultJson.createFailJson(500, buffer.toString());
		}
		Integer parentInt = Integer.parseInt(userId);
		DetachedCriteria relaDc = DetachedCriteria.forClass(TbRelation.class);
		TbUserinfo parent = new TbUserinfo();
		parent.setUserInfoId(parentInt);
		relaDc.add(Restrictions.eq("tbUserinfoByTbUserInfoId", parent));
		Integer relaDcNum = baseDao.dCRowCount(relaDc);
		if (relaDcNum > 0) {
			buffer.append("的孩子账户已经存在");
			return ResultJson.createFailJson(500, buffer.toString());
		}
		if (StringUtils.isEmpty(password))
			password = "000000";
		boo = new TransactionDao().registerChild(phone, password, email,
				Integer.parseInt(gradeId), sex, parentInt);
		HashMap<String, Object> data = new HashMap<String, Object>(2);
		data.put("urlIos",
				new StringBuffer(TWDataUtil.getBaseUrl()).append("babyR1.html")
						.toString());
		data.put("urlAndroid", new StringBuffer(TWDataUtil.getBaseUrl())
				.append("babyR1.html").toString());
		return boo ? ResultJson.crateSuccJson(data) : ResultJson
				.createFailJson(1004, "添加失败");
	}

	/**
	 * 首次登陆接口
	 * 
	 * @param userName
	 *            用户账户
	 * @param userPassword
	 *            密码
	 * @return
	 */
	public ResultJson firstLogin(String userName, String userPassword) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
		dc.add(Restrictions.eq("userName", userName));
		dc.add(Restrictions.eq("userPassword", userPassword));
		ArrayList<TbUser> tbUsers = baseDao.dCList(TbUser.class, dc);
		if (tbUsers == null || tbUsers.isEmpty())
			return ResultJson.createFailJson(1002, "帐户或密码错误");
		else {
			Map<String, Object> data = new HashMap<String, Object>(3);
			TbUser tbUser = tbUsers.get(0);
			Integer id = tbUser.getTbUserinfo().getUserInfoId();
			TbUserinfo tbUserinfo = baseDao.get(TbUserinfo.class, id);
			DetachedCriteria relaDc = DetachedCriteria
					.forClass(TbRelation.class);
			relaDc.add(Restrictions.eq("tbUserinfoByTbUserInfoId", tbUserinfo));
			relaDc.add(Restrictions.eq("isValid", 1));
			Integer num = baseDao.dCRowCount(relaDc) > 0 ? 1 : 0;
			data.put("userinfoId", id);
			data.put("isParent", num);
			data.put("isValid", tbUser.getIsValid());
			data.put("root", tbUserinfo.getUserInfoRoot());
			return ResultJson.crateSuccJson(data);
		}
	}

	/**
	 * 学校注册申请
	 * 
	 * @param cityName
	 *            城市名称
	 * @param schoolName
	 *            学校名称
	 * @param address
	 *            学校地址
	 * @param phone
	 *            学校电话
	 * @param name
	 *            校长名称
	 * @param userName
	 *            校长手机
	 * @param password
	 *            账户密码
	 * @param email
	 *            邮箱
	 * @param pusher
	 *            推广人
	 * @return
	 */
	public ResultJson schoolLogin(String cityName, String schoolName,
			String address, String phone, String name, String userName,
			String password, String email, String pusher) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		BaseDao baseDao = new BaseDao();
		TbSchoolApplication tbApplication = new TbSchoolApplication(cityName,
				schoolName, address, phone, name, userName, password, email,
				pusher, 1, time, time);
		Integer id = (Integer) baseDao.save(tbApplication);
		TbPusher tbPusher = baseDao.get(TbPusher.class, pusher);
		HashMap<String, Object> data = new HashMap<String, Object>(1);
		if (tbPusher != null && StringUtils.isNotEmpty(tbPusher.getName())) {
			data.put("isPusher", 1);
		} else {
			data.put("isPusher", 0);
		}
		data.put("schoolId", id);
		data.put("pusherName", pusher);
		return id != null ? ResultJson.crateSuccJson(data) : ResultJson
				.createFailJson(1005, "添加失败");
	}

	/**
	 * 我的修改页面获得信息接口
	 * 
	 * @param userId
	 *            用户Id
	 * @param root
	 *            0,4-校长，2-家长，3-学生
	 * @return
	 */
	public ResultJson findMyInfo(String userId, String root) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		BaseDao baseDao = new BaseDao();
		if ("2".equals(root)) {
			String hql = "select u2.studentId,u2.gradeId,u2.cityId,u.userInfoEmail,us.userName,us.userPassword from tb_userinfo u inner join tb_user us on u.userInfoId=us.userInfoId left outer join tb_userinfo2 u2 on u.userInfoId=u2.userinfoId where u.userInfoId=?";
			List<Object> paramList = new ArrayList<Object>(1);
			Integer user_id = Integer.parseInt(userId);
			paramList.add(user_id);
			List<?> objs = baseDao.sqlList(hql, paramList);
			if (objs == null || objs.isEmpty())
				return ResultJson.crateNullJson();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = objs.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				String studentId = "";
				if (object[0] == null) {
					DetachedCriteria relDc = DetachedCriteria
							.forClass(TbRelation.class);
					TbUserinfo tbUserinfo = new TbUserinfo();
					tbUserinfo.setUserInfoId(user_id);
					relDc.add(Restrictions.eq("tbUserinfoByTbUserInfoId",
							tbUserinfo));
					ArrayList<TbRelation> relations = baseDao.dCList(
							TbRelation.class, relDc);
					if (relations != null && !relations.isEmpty()) {
						TbRelation tbRelation = relations.get(0);
						if (tbRelation.getTbUserinfoByUserInfoId() != null) {
							TbUserinfo2 tbUserinfo2 = new TbUserinfo2();
							tbUserinfo2.setId(user_id);
							studentId = tbRelation.getTbUserinfoByUserInfoId()
									.getUserInfoId().toString();
							tbUserinfo2.setStudentId(tbRelation
									.getTbUserinfoByUserInfoId()
									.getUserInfoId());
							baseDao.updateT(tbUserinfo2, user_id);
						}
					}
				} else {
					studentId = ((Integer) object[0]).toString();
				}
				String gradeId = object[1] == null ? "" : ((Integer) object[1])
						.toString();
				String cityId = object[2] == null ? "" : ((Integer) object[2])
						.toString();
				String userInfoEmail = object[3] == null ? ""
						: (String) object[3];
				String userName = object[4] == null ? "" : (String) object[4];
				String userPassword = object[5] == null ? ""
						: (String) object[5];
				outMap.put("studentId", studentId);
				outMap.put("gradeId", gradeId);
				outMap.put("cityId", cityId);
				Integer cityid = null;
				String cityName = null;
				String spell = null;
				if (StringUtils.isNotEmpty(cityId)) {
					cityid = Integer.parseInt(cityId);
				}
				DetachedCriteria citydc = DetachedCriteria
						.forClass(TbCity.class);
				citydc.addOrder(Order.asc("cityName"));
				ArrayList<TbCity> citys = baseDao.dCList(TbCity.class, citydc);
				TbCity city1 = null;
				for (TbCity tbCity : citys) {
					if (tbCity.getCityId().equals(cityid)) {
						cityName = tbCity.getCityName();
						spell = tbCity.getSpell();
						city1 = tbCity;
						continue;
					}
					tbCity.setAlterTime(null);
					tbCity.setIsValid(null);
					tbCity.setTbTowns(null);
					tbCity.setTime(null);

				}
				if (StringUtils.isNotBlank(cityName)) {
					TbCity tbCity = new TbCity();
					citys.remove(city1);
					tbCity.setCityId(cityid);
					tbCity.setCityName(cityName);
					tbCity.setSpell(spell);
					tbCity.setTbTowns(null);
					citys.add(0, tbCity);
					outMap.put("cityName", cityName);
				} else {
					outMap.put("cityName", "");
				}
				outMap.put("citys", citys);
				outMap.put("email", userInfoEmail);
				outMap.put("userName", userName);
				outMap.put("password", userPassword);
				if (StringUtils.isNotEmpty(studentId)) {
					return findStudentById(studentId, outMap, baseDao, gradeId);
				}
			}
		} else if ("3".equals(root)) {
			return findStudentById(userId, outMap, baseDao, null);
		} else {
			int userid = Integer.parseInt(userId);
			TbUserinfo tbUserinfo = baseDao.get(TbUserinfo.class, userid);
			outMap.put(
					"email",
					tbUserinfo.getUserInfoEmail() != null ? tbUserinfo
							.getUserInfoEmail() : "");
			outMap.put(
					"phone",
					tbUserinfo.getUserInfoPhone() != null ? tbUserinfo
							.getUserInfoPhone() : "");
			DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
			dc.add(Restrictions.eq("tbUserinfo", tbUserinfo));
			ArrayList<TbUser> tbUsers = baseDao.dCList(TbUser.class, dc);
			if (tbUsers != null && !tbUsers.isEmpty()) {
				outMap.put("userName", tbUsers.get(0).getUserName());
				outMap.put("password", tbUsers.get(0).getUserPassword());
			} else {
				outMap.put("userName", "");
				outMap.put("password", "");
			}
		}
		return ResultJson.crateSuccJson(outMap);
	}

	private ResultJson findStudentById(String studentId,
			Map<String, Object> outMap, BaseDao baseDao, String gradeId) {
		int userId = Integer.parseInt(studentId);
		Integer gradeid = null;
		String gradeName = null;
		if (StringUtils.isNotEmpty(gradeId)) {
			gradeid = Integer.parseInt(gradeId);
		} else {
			DetachedCriteria info2dc = DetachedCriteria
					.forClass(TbUserinfo2.class);
			info2dc.add(Restrictions.eq("studentId", userId));
			ArrayList<TbUserinfo2> userinfo2s = baseDao.dCList(
					TbUserinfo2.class, info2dc);
			if (userinfo2s != null && !userinfo2s.isEmpty())
				gradeid = userinfo2s.get(0).getGradeId();
		}
		DetachedCriteria dc = DetachedCriteria.forClass(TbGrade.class);
		dc.addOrder(Order.asc("gradeId"));
		ArrayList<TbGrade> grades = baseDao.dCList(TbGrade.class, dc);
		TbGrade grade1 = null;
		for (TbGrade tbGrade : grades) {
			if (tbGrade.getGradeId().equals(gradeid)) {
				gradeName = tbGrade.getGradeName();
				grade1 = tbGrade;
				continue;
			}
			tbGrade.setAlterTime(null);
			tbGrade.setIsValid(null);
			tbGrade.setTbBranchschool(null);
			tbGrade.setTbSchool(null);
			tbGrade.setTbCurriculums(null);
			tbGrade.setTime(null);
		}
		if (StringUtils.isNotBlank(gradeName)) {
			TbGrade grade = new TbGrade();
			grades.remove(grade1);
			grade.setTbCurriculums(null);
			grade.setGradeId(gradeid);
			grade.setGradeName(gradeName);
			grades.add(0, grade);
			outMap.put("gradeName", gradeName);
		} else {
			outMap.put("gradeName", "");
		}
		outMap.put("grades", grades);
		String hql1 = "select u.userInfoSex,u.userInfoEmail,us.userName,us.userPassword from TbUserinfo u,TbUser us where u.id=? and us.tbUserinfo=u.id";
		List<Object> paramlist2 = new ArrayList<Object>(1);
		paramlist2.add(userId);
		List<?> students = baseDao.hqlList(hql1, paramlist2);
		if (students == null || students.isEmpty())
			return ResultJson.crateSuccJson(outMap);
		for (@SuppressWarnings("rawtypes")
		Iterator iterator2 = students.iterator(); iterator2.hasNext();) {
			Object[] object2 = (Object[]) iterator2.next();
			String studentSex = object2[0] == null ? "" : (String) object2[0];
			String studentEmail = object2[1] == null ? "" : (String) object2[1];
			String studentUserName = object2[2] == null ? ""
					: (String) object2[2];
			String studentPassword = object2[3] == null ? ""
					: (String) object2[3];
			outMap.put("studentSex", studentSex);
			outMap.put("studentEmail", studentEmail);
			outMap.put("studentUserName", studentUserName);
			outMap.put("studentPassword", studentPassword);
		}
		return ResultJson.crateSuccJson(outMap);
	}

	/**
	 * 五广告
	 * 
	 * @return
	 */
	public ResultJson fiveTbAdvertisement() {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria dc = DetachedCriteria.forClass(TbAdvertisement.class);
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.asc("id"));
		ArrayList<TbAdvertisement> tbAdvertisements = baseDao.dCList(
				TbAdvertisement.class, dc);
		if (tbAdvertisements == null || tbAdvertisements.isEmpty())
			return ResultJson.crateNullJson();
		List<Object> list = new ArrayList<Object>(tbAdvertisements.size());
		for (TbAdvertisement tbAdvertisement : tbAdvertisements) {
			tbAdvertisement.setId(null);
			tbAdvertisement.setAlterTime(null);
			tbAdvertisement.setUrl(TWPictureUtil
					.getNomalPicPath(tbAdvertisement.getUrl()));
			tbAdvertisement.setType(tbAdvertisement.getType() == null ? 0
					: tbAdvertisement.getType());
			tbAdvertisement
					.setHyperlink(tbAdvertisement.getHyperlink() == null ? ""
							: tbAdvertisement.getHyperlink());
			tbAdvertisement.setIsValid(null);
			tbAdvertisement.setTime(null);
			list.add(tbAdvertisement);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}

	// 首页第一张图片
	public ResultJson TbAdvertisement() {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria dc = DetachedCriteria.forClass(TbAdvertisement.class);
		dc.add(Restrictions.eq("isValid", 1));
		dc.addOrder(Order.desc("id"));
		ArrayList<TbAdvertisement> tbAdvertisements = baseDao.dCList(
				TbAdvertisement.class, dc);
		if (tbAdvertisements == null || tbAdvertisements.isEmpty())
			return ResultJson.crateNullJson();

		List<Object> list = new ArrayList<Object>(tbAdvertisements.size());
		for (TbAdvertisement tbAdvertisement : tbAdvertisements) {
			tbAdvertisement.setId(null);
			tbAdvertisement.setAlterTime(null);
			tbAdvertisement.setUrl(TWPictureUtil
					.getNomalPicPath(tbAdvertisement.getUrl()));
			tbAdvertisement.setType(tbAdvertisement.getType() == null ? 0
					: tbAdvertisement.getType());
			tbAdvertisement
					.setHyperlink(tbAdvertisement.getHyperlink() == null ? ""
							: tbAdvertisement.getHyperlink());
			tbAdvertisement.setIsValid(null);
			tbAdvertisement.setTime(null);
			list.add(tbAdvertisement);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 个人推送分页
	 * 
	 * @param userinfoId
	 *            用户id
	 * @param currentPage
	 *            当前页
	 * @param countPerPage
	 *            每页多少条
	 * @return
	 */
	public ResultJson findTbPushRecord(String userinfoId, String currentPage,
			String countPerPage) {
		BaseDao baseDao = new BaseDao();
		Page page = new Page();
		Integer acceptId = Integer.parseInt(userinfoId);
		page.setCountPerPage(Integer.parseInt(countPerPage));
		page.setCurrentPage(Integer.parseInt(currentPage));
		DetachedCriteria dc = DetachedCriteria.forClass(TbPushRecord.class);
		dc.add(Restrictions.eq("acceptId", acceptId));
		dc.addOrder(Order.desc("id"));
		page = baseDao.dCPage(page, dc);
		@SuppressWarnings("rawtypes")
		List list = page.getCurrentList();
		List<Map<String, String>> outList = new ArrayList<Map<String, String>>();
		TbPushRecord tbPushRecord = null;
		Map<String, String> outMap = null;
		for (Object object : list) {
			if (object instanceof TbPushRecord) {
				tbPushRecord = (TbPushRecord) object;
				outMap = new HashMap<String, String>(3);
				outMap.put("title",
						TWObjectUtil.getString(tbPushRecord.getTitle()));
				outMap.put("content",
						TWObjectUtil.getString(tbPushRecord.getContent()));
				outMap.put("time",
						TWDataUtil.TimestampFormat(tbPushRecord.getTime()));
				outList.add(outMap);
			}
		}
		page.setCurrentList(outList);
		return ResultJson.crateSuccJson(page);
	}

	/**
	 * 修改家长我的信息
	 * 
	 * @param userinfoId
	 *            家长ID
	 * @param password
	 *            家长密码
	 * @param email
	 *            家长邮箱
	 * @param gradeId
	 *            年段ID
	 * @param cityId
	 *            城市ID
	 * @param studentSex
	 *            学生性别
	 * @param studentId
	 *            学生ID id为空时不修改学生信息
	 * @param studentPassword
	 *            学生密码
	 * @param studentEmail
	 *            学生邮箱
	 * @return
	 */
	public ResultJson updateMyInfoParentAndStudent(String userinfoId,
			String password, String email, String gradeId, String cityId,
			String studentSex, String studentId, String studentPassword,
			String studentEmail) {
		BaseDao baseDao = new BaseDao();
		TbUserinfo2 parent2 = null;
		TbUser parentUser = null;
		int parentid = Integer.parseInt(userinfoId);
		Integer student_id = null;
		Integer gradeid = null;
		Integer cityid = null;
		if (StringUtils.isNotEmpty(studentId))
			student_id = Integer.parseInt(studentId);
		TbUserinfo parent = new TbUserinfo();
		parent.setUserInfoId(parentid);
		if (StringUtils.isNotEmpty(password)) {
			DetachedCriteria userdc = DetachedCriteria.forClass(TbUser.class);
			userdc.add(Restrictions.eq("tbUserinfo", parent));
			ArrayList<TbUser> tbUsers = baseDao.dCList(TbUser.class, userdc);
			if (tbUsers == null || tbUsers.isEmpty())
				return ResultJson.crateNullJson();
			parentUser = tbUsers.get(0);
			parentUser.setUserPassword(password);
		}
		if (StringUtils.isNotEmpty(email)) {
			parent.setUserInfoEmail(email);
		}
		if (StringUtils.isNotEmpty(gradeId)) {
			gradeid = Integer.parseInt(gradeId);
		}
		if (StringUtils.isNotEmpty(cityId)) {
			cityid = Integer.parseInt(cityId);
		}
		if (StringUtils.isNotEmpty(cityId) || StringUtils.isNotEmpty(gradeId)) {
			DetachedCriteria userdc = DetachedCriteria
					.forClass(TbUserinfo2.class);
			userdc.add(Restrictions.eq("userinfoId", parentid));
			ArrayList<TbUserinfo2> tbUserinfo2s = baseDao.dCList(
					TbUserinfo2.class, userdc);
			if (tbUserinfo2s == null || tbUserinfo2s.isEmpty()) {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				parent2 = new TbUserinfo2(student_id, parentid, gradeid,
						cityid, 1, null, time, time, null, null);
				Integer user2id = (Integer) baseDao.save(parent2);
				parent2.setId(user2id);
			} else
				parent2 = tbUserinfo2s.get(0);
			parent2.setCityId(cityid);
			parent2.setGradeId(gradeid);
		}
		Object[] objects = new Object[5];
		Integer[] ids = new Integer[5];
		if (student_id != null) {
			TbUserinfo student = new TbUserinfo();
			student.setUserInfoId(student_id);
			TbUser studentUser = null;
			if (StringUtils.isNotEmpty(studentPassword)) {
				DetachedCriteria userdc = DetachedCriteria
						.forClass(TbUser.class);
				userdc.add(Restrictions.eq("tbUserinfo", student));
				ArrayList<TbUser> tbUsers = baseDao
						.dCList(TbUser.class, userdc);
				if (tbUsers == null || tbUsers.isEmpty())
					return ResultJson.crateNullJson();
				studentUser = tbUsers.get(0);
				studentUser.setUserPassword(studentPassword);
			}
			if (StringUtils.isNotEmpty(studentEmail)) {
				student.setUserInfoEmail(studentEmail);
			}
			if (StringUtils.isNotEmpty(studentSex)) {
				student.setUserInfoSex(studentSex);
			}
			objects[3] = student;
			ids[3] = student.getUserInfoId();
			if (studentUser != null) {
				objects[4] = studentUser;
				ids[4] = studentUser.getUserId();
			}
		}
		objects[0] = parentUser;
		ids[0] = parentUser.getUserId();
		objects[1] = parent;
		ids[1] = parent.getUserInfoId();
		objects[2] = parent2;
		ids[2] = parent2.getId();
		Boolean boo = baseDao.updateT(objects, ids);
		if (!boo)
			return ResultJson.createFailJson(1005, "修改失败");
		return ResultJson.crateSuccJson(new HashMap<String, Object>());
	}

	/**
	 * 学生我的信息修改接口
	 * 
	 * @param gradeId
	 *            年段id
	 * @param studentSex
	 *            学生性别
	 * @param studentId
	 *            学生ID
	 * @param studentPassword
	 *            学生密码
	 * @param studentEmail
	 *            学生邮箱
	 * @return
	 */
	public ResultJson updateMyInfoStudent(String gradeId, String studentSex,
			String studentId, String studentPassword, String studentEmail) {
		BaseDao baseDao = new BaseDao();
		TbUserinfo2 student2 = null;
		int studentid = Integer.parseInt(studentId);
		TbUserinfo student = new TbUserinfo();
		student.setUserInfoId(studentid);
		TbUser studentUser = null;
		if (StringUtils.isNotEmpty(studentPassword)) {
			DetachedCriteria userdc = DetachedCriteria.forClass(TbUser.class);
			userdc.add(Restrictions.eq("tbUserinfo", student));
			ArrayList<TbUser> tbUsers = baseDao.dCList(TbUser.class, userdc);
			if (tbUsers == null || tbUsers.isEmpty())
				return ResultJson.crateNullJson();
			studentUser = tbUsers.get(0);
			studentUser.setUserPassword(studentPassword);
		}
		if (StringUtils.isNotEmpty(gradeId)) {
			DetachedCriteria userdc = DetachedCriteria
					.forClass(TbUserinfo2.class);
			userdc.add(Restrictions.eq("studentId", studentid));
			ArrayList<TbUserinfo2> tbUsers = baseDao.dCList(TbUserinfo2.class,
					userdc);
			if (tbUsers == null || tbUsers.isEmpty())
				return ResultJson.crateNullJson();
			student2 = tbUsers.get(0);
			student2.setGradeId(Integer.parseInt(gradeId));
		}
		if (StringUtils.isNotEmpty(studentEmail)) {
			student.setUserInfoEmail(studentEmail);
		}
		if (StringUtils.isNotEmpty(studentSex)) {
			student.setUserInfoSex(studentSex);
		}
		Object[] objects = new Object[3];
		Integer[] ids = new Integer[3];
		objects[0] = studentUser;
		ids[0] = studentUser.getUserId();
		objects[1] = student;
		ids[1] = student.getUserInfoId();
		if (student2 != null) {
			objects[2] = student2;
			ids[2] = student2.getId();
		}
		Boolean boo = baseDao.updateT(objects, ids);
		if (!boo)
			return ResultJson.createFailJson(1005, "修改失败");
		return ResultJson.crateSuccJson(new HashMap<String, Object>());
	}

	/**
	 * 宝宝去哪里啦_下载界面
	 * 
	 * @param type
	 *            安卓可以不带参数，ios则参数为ios大小写不明感
	 * @return
	 */
	public ResultJson whereTheBabyTo(String type) {
		Map<String, Object> data = new HashMap<String, Object>(1);
		if (type != null && "IOS".equals(type.toUpperCase())) {
			data.put(
					"url",
					new StringBuffer(TWDataUtil.getBaseUrl()).append(
							"baby1.html").toString());
		} else {
			data.put(
					"url",
					new StringBuffer(TWDataUtil.getBaseUrl()).append(
							"baby1.html").toString());
		}
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 宝宝去哪里啦_获取经纬度及其下载url
	 * 
	 * @param userinfoId
	 *            用户id
	 * @param type
	 *            安卓可以不带参数，ios则参数为ios大小写不明感
	 * @return
	 */
	public ResultJson childLocation02(String userinfoId, String type) {
		BaseDao baseDao = new BaseDao();
		int userid = Integer.parseInt(userinfoId);
		TbUserinfo userinfo = baseDao.get(TbUserinfo.class, userid);
		DetachedCriteria reladc = DetachedCriteria.forClass(TbRelation.class);
		reladc.add(Restrictions.eq("tbUserinfoByTbUserInfoId", userinfo));
		reladc.add(Restrictions.eq("isValid", 1));
		ArrayList<TbRelation> relations = baseDao.dCList(TbRelation.class,
				reladc);
		if (relations == null || relations.isEmpty())
			return ResultJson.crateNullJson();
		TbRelation tbRelation = relations.get(0);
		Map<String, Object> data = new HashMap<String, Object>();
		Double latitude = tbRelation.getChildLatitude() == null ? 0
				: tbRelation.getChildLatitude();
		Double longitude = tbRelation.getChildLongitude() == null ? 0
				: tbRelation.getChildLongitude();
		data.put("childLatitude", latitude);
		data.put("childLongitude", longitude);
		if (type != null && "IOS".equals(type.toUpperCase())) {
			data.put(
					"url",
					new StringBuffer(TWDataUtil.getBaseUrl()).append(
							"baby1.html").toString());
		} else {
			data.put(
					"url",
					new StringBuffer(TWDataUtil.getBaseUrl()).append(
							"baby1.html").toString());
		}
		data.put("alterTime",
				TWDataUtil.TimestampFormat(tbRelation.getAlterTime()));
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 修改校长个人信息
	 * 
	 * @param email
	 *            邮箱
	 * @param phone
	 *            手机
	 * @param userinfoId
	 *            用户ID
	 * @param password
	 *            密码
	 * @return
	 */
	public ResultJson updateRectorInfo(String email, String phone,
			String userinfoId, String password) {
		Integer userinfoid = Integer.parseInt(userinfoId);
		Object[] objs = new Object[2];
		Serializable[] ids = new Integer[2];
		BaseDao baseDao = new BaseDao();
		TbUserinfo tbUserinfo = new TbUserinfo();
		tbUserinfo.setUserInfoId(userinfoid);
		if (StringUtils.isNotEmpty(email) || StringUtils.isNotEmpty(phone)) {
			if (StringUtils.isNotEmpty(email))
				tbUserinfo.setUserInfoEmail(email);
			if (StringUtils.isNotEmpty(phone))
				tbUserinfo.setUserInfoPhone(phone);
			objs[0] = tbUserinfo;
			ids[0] = tbUserinfo.getUserInfoId();
		}
		TbUser tbUser = null;
		if (StringUtils.isNotEmpty(password)) {
			DetachedCriteria userDc = DetachedCriteria.forClass(TbUser.class);
			userDc.add(Restrictions.eq("tbUserinfo", tbUserinfo));
			userDc.add(Restrictions.eq("isValid", 1));
			ArrayList<TbUser> tbUsers = baseDao.dCList(TbUser.class, userDc);
			if (tbUsers == null || tbUsers.isEmpty()) {
			} else {
				tbUser = tbUsers.get(0);
				tbUser.setUserPassword(password);
				objs[1] = tbUser;
				ids[1] = tbUser.getUserId();
			}
		}
		Boolean boo = baseDao.updateT(objs, ids);
		return boo ? ResultJson.crateSuccJson(new HashMap<String, Object>(0))
				: ResultJson.createFailJson(1005, "修改失败");
	}

	/**
	 * 获取用户总校信息接口
	 * 
	 * @param userinfoId
	 *            用户ID
	 * @return
	 */
	public ResultJson findSchoolInfo(String userinfoId) {
		Integer userId = Integer.parseInt(userinfoId);
		BaseDao baseDao = new BaseDao();
		TbUserinfo userinfo = baseDao.get(TbUserinfo.class, userId);
		Integer schoolId = userinfo.getTbSchool().getSchoolId();
		Map<String, Object> data = new HashMap<String, Object>();
		TbSchool tbshool = baseDao.get(TbSchool.class, schoolId);
		Integer townId = tbshool.getTbTown().getTownId();
		DetachedCriteria townDc = DetachedCriteria.forClass(TbTown.class);
		townDc.setFetchMode("tbCity", FetchMode.JOIN);
		townDc.add(Restrictions.eq("townId", townId));
		ArrayList<TbTown> tbTowns = baseDao.dCList(TbTown.class, townDc);
		if (tbTowns == null || tbTowns.isEmpty()) {
			data.put("cityId", 0);
			data.put("cityName", "");
			data.put("townId", 0);
			data.put("townName", "");
		} else {
			TbTown tbTown = tbTowns.get(0);
			data.put("cityId", tbTown.getTbCity().getCityId());
			data.put("cityName",
					TWObjectUtil.getString(tbTown.getTbCity().getCityName()));
			data.put("townId", tbTown.getTownId());
			data.put("townName", TWObjectUtil.getString(tbTown.getTownName()));
		}
		data.put("schoolId", tbshool.getSchoolId());
		data.put("schoolName", TWObjectUtil.getString(tbshool.getSchoolName()));
		data.put("schoolMinName",
				TWObjectUtil.getString(tbshool.getSchoolMinName()));
		data.put("schoolPhone",
				TWObjectUtil.getString(tbshool.getSchoolPhone()));
		data.put("schoolAddress",
				TWObjectUtil.getString(tbshool.getSchoolAddress()));
		data.put("schoolStopLocation",
				TWObjectUtil.getString(tbshool.getSchoolStopLocation()));
		data.put("schoolBusWay",
				TWObjectUtil.getString(tbshool.getSchoolBusWay()));
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 修改学校信息
	 * 
	 * @param schoolId
	 *            学校ID
	 * @param schoolName
	 *            学校名称
	 * @param schoolStopLocation
	 *            学校停车位
	 * @param schoolBusWay
	 *            学校公交路线
	 * @param schoolMinName
	 *            学校简称
	 * @param schoolPhone
	 *            学校电话
	 * @param townId
	 *            城区ID
	 * @param schoolAddress
	 *            学校地址
	 * @return
	 */
	public ResultJson updateSchoolInfo(String schoolId, String schoolName,
			String schoolStopLocation, String schoolBusWay,
			String schoolMinName, String schoolPhone, String townId,
			String schoolAddress) {
		Integer schId = Integer.parseInt(schoolId);
		TbSchool tbSchool = new TbSchool();
		tbSchool.setSchoolId(schId);
		if (StringUtils.isNotEmpty(schoolName))
			tbSchool.setSchoolName(schoolName);
		if (StringUtils.isNotEmpty(schoolMinName))
			tbSchool.setSchoolMinName(schoolMinName);
		if (StringUtils.isNotEmpty(schoolPhone))
			tbSchool.setSchoolPhone(schoolPhone);
		if (StringUtils.isNotEmpty(schoolAddress))
			tbSchool.setSchoolAddress(schoolAddress);
		if (StringUtils.isNotEmpty(schoolBusWay))
			tbSchool.setSchoolBusWay(schoolBusWay);
		if (StringUtils.isNotEmpty(schoolStopLocation))
			tbSchool.setSchoolStopLocation(schoolStopLocation);
		if (StringUtils.isNotEmpty(townId)) {
			TbTown tbTown = new TbTown();
			tbTown.setTownId(Integer.parseInt(townId));
			tbSchool.setTbTown(tbTown);
		}
		boolean boo = new BaseDao().updateT(tbSchool, schId);
		return boo ? ResultJson.crateSuccJson(new HashMap<String, Object>(0))
				: ResultJson.createFailJson(1005, "修改失败");
	}

	/**
	 * 找学校
	 * 
	 * @param schoolName学校名称
	 * @param cityName
	 *            城市名称
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @param orderType
	 *            0-距离，1-名称，2-人气，三排序
	 * @param countPerPage
	 *            每页多少条
	 * @param currentPage
	 *            当前多少页
	 * @param gradeId
	 *            年段ID
	 * @param subjectId
	 *            学科id
	 * @param courseName
	 *            班的名称
	 * @param townId
	 *            城区ID
	 * @return
	 */
	public static void main(String[] args) {
		System.out.println(new SecondaryDevelopmen().findSchools("明明明明饿清明哦",
				"杭州市", "120.101318359375", "30.28344345092773", "0", "1", "10",
				null, null, "", null, null));
	}

	@SuppressWarnings({ "rawtypes" })
	public ResultJson findSchools(String schoolName, String cityName,
			String longitude, String latitude, String orderType,
			String currentPage, String countPerPage, String gradeId,
			String subjectId, String courseName, String townId,
			String branchschoolId) {
		BaseDao baseDao = new BaseDao();
		ArrayList<Integer> town_ids = null;
		Integer branchschool_Id = null;
		TbBranchschool tbBranchschoolT = null;
		ArrayList<Integer> school_ids = new ArrayList<Integer>();
		ArrayList<Integer> tbBranchschool_ids = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(branchschoolId)) {
			branchschool_Id = Integer.parseInt(branchschoolId);
			tbBranchschoolT = baseDao
					.get(TbBranchschool.class, branchschool_Id);
			if (tbBranchschoolT != null
					&& tbBranchschoolT.getTbSchool() != null
					&& tbBranchschoolT.getTbSchool().getSchoolId() != null)
				school_ids.add(tbBranchschoolT.getTbSchool().getSchoolId());
			DetachedCriteria branDc = DetachedCriteria
					.forClass(TbBranchschool.class);
			branDc.add(Restrictions.eq("tbSchool",
					tbBranchschoolT.getTbSchool()));
			ArrayList<TbBranchschool> tbBranchschools = baseDao.dCList(
					TbBranchschool.class, branDc);
			for (TbBranchschool tbBranchschool : tbBranchschools) {
				if (tbBranchschool.getBranchSchoolId() != null)
					tbBranchschool_ids.add(tbBranchschool.getBranchSchoolId());
			}
		}
		// 通过条件获取或需要的总校ID跟分校ID
		if (StringUtils.isNotEmpty(cityName)) {
			DetachedCriteria cityDc = DetachedCriteria.forClass(TbCity.class);
			cityDc.add(Restrictions.ilike("cityName", cityName,
					MatchMode.ANYWHERE));
			ArrayList<TbCity> citys = baseDao.dCList(TbCity.class, cityDc);
			if (citys != null && !citys.isEmpty()) {
				TbCity city = citys.get(0);
				DetachedCriteria townDc = DetachedCriteria
						.forClass(TbTown.class);
				townDc.add(Restrictions.eq("tbCity", city));
				ArrayList<TbTown> towns = baseDao.dCList(TbTown.class, townDc);
				if (towns != null && !towns.isEmpty()) {
					town_ids = new ArrayList<Integer>(towns.size());
					for (TbTown town : towns) {
						town_ids.add(town.getTownId());
					}
				}
			}
		} else if (StringUtils.isNotEmpty(townId)) {
			int town_id = Integer.parseInt(townId);
			town_ids = new ArrayList<Integer>(1);
			town_ids.add(town_id);
		}

		if (StringUtils.isNotEmpty(schoolName)) {
			DetachedCriteria tbSchoolDc = DetachedCriteria
					.forClass(TbSchool.class);
			tbSchoolDc.add(Restrictions.ilike("schoolName", schoolName,
					MatchMode.ANYWHERE));
			if (tbBranchschoolT != null
					&& tbBranchschoolT.getTbSchool() != null
					&& tbBranchschoolT.getTbSchool().getSchoolId() != null)
				tbSchoolDc.add(Restrictions.eq("schoolId", tbBranchschoolT
						.getTbSchool().getSchoolId()));
			ArrayList<TbSchool> schools = baseDao.dCList(TbSchool.class,
					tbSchoolDc);
			if (schools != null && !schools.isEmpty()) {
				for (TbSchool tbSchool : schools) {
					school_ids.add(tbSchool.getSchoolId());
				}
			}
			DetachedCriteria tbBranchschoolDc = DetachedCriteria
					.forClass(TbBranchschool.class);
			tbBranchschoolDc.add(Restrictions.ilike("braschName", schoolName,
					MatchMode.ANYWHERE));
			if (tbBranchschoolT != null
					&& tbBranchschoolT.getTbSchool() != null
					&& tbBranchschoolT.getTbSchool().getSchoolId() != null)
				tbBranchschoolDc.add(Restrictions.eq("tbSchool",
						tbBranchschoolT.getTbSchool()));
			ArrayList<TbBranchschool> branchschools = baseDao.dCList(
					TbBranchschool.class, tbBranchschoolDc);
			if (branchschools != null && !branchschools.isEmpty()) {
				for (TbBranchschool branchschool : branchschools) {
					tbBranchschool_ids.add(branchschool.getBranchSchoolId());
				}
			}
		}
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer
				.append("select schoolId,branchSchoolId from tb_curriculum where 1=1 ");// and
																						// courseName
																						// like
																						// '%?%'
																						// and(grades
																						// like
																						// '?@%'
																						// or
																						// grades
																						// like
																						// '%@?'
																						// or
																						// grades
																						// like
																						// '%@?@%')");
		if (StringUtils.isNotEmpty(schoolName)
				&& (school_ids == null || school_ids.isEmpty())
				&& (tbBranchschool_ids == null || tbBranchschool_ids.isEmpty()))
			return ResultJson.crateSuccJson(new Page());
		if (school_ids != null && !school_ids.isEmpty()) {
			if (tbBranchschool_ids != null && !tbBranchschool_ids.isEmpty())
				hqlBuffer.append("and ( schoolId in(");
			else
				hqlBuffer.append("and schoolId in(");
			for (int i = 0; i < school_ids.size(); i++) {
				if (i == 0)
					hqlBuffer.append("?");
				else
					hqlBuffer.append(",?");
				paramList.add(school_ids.get(i));
			}
			if (tbBranchschool_ids != null && !tbBranchschool_ids.isEmpty())
				hqlBuffer.append(") or ");
			else
				hqlBuffer.append(") ");
		}
		if (tbBranchschool_ids != null && !tbBranchschool_ids.isEmpty()) {
			hqlBuffer.append("branchSchoolId in(");
			for (int i = 0; i < tbBranchschool_ids.size(); i++) {
				if (i == 0)
					hqlBuffer.append("?");
				else
					hqlBuffer.append(",?");
				paramList.add(tbBranchschool_ids.get(i));
			}
			if (school_ids != null && !school_ids.isEmpty())
				hqlBuffer.append(") ) ");
			else
				hqlBuffer.append(") ");
		}
		if (town_ids != null && !town_ids.isEmpty()) {
			// System.out.println("townsize--->"+town_ids.size());
			hqlBuffer.append("and townId in(");
			for (int i = 0; i < town_ids.size(); i++) {
				if (i == 0)
					hqlBuffer.append("?");
				else
					hqlBuffer.append(",?");
				paramList.add(town_ids.get(i));
			}
			hqlBuffer.append(") ");
		}
		if (StringUtils.isNotEmpty(subjectId)) {
			int subject_id = Integer.parseInt(subjectId);
			hqlBuffer.append("and subjectId=? ");
			paramList.add(subject_id);
		}
		if (StringUtils.isNotEmpty(courseName)) {
			hqlBuffer.append("and courseName like ? ");
			paramList.add(new StringBuffer("%").append(courseName).append("%")
					.toString());
		}
		if (StringUtils.isNotEmpty(gradeId)) {
			hqlBuffer
					.append("and (grades like ? or grades like ? or grades like ?) ");
			int grade_id = Integer.parseInt(gradeId);
			paramList.add(new StringBuffer(grade_id).append("@%").toString());
			paramList.add(new StringBuffer("@%").append(grade_id).toString());
			paramList.add(new StringBuffer("@%").append(grade_id).append("@%")
					.toString());
		}
		// System.out.println("hql--->"+hqlBuffer.toString()+"--->"+paramList.size());
		List<?> list = baseDao.sqlList(hqlBuffer.toString(), paramList);
		if (list == null || list.isEmpty())
			return ResultJson.crateNullJson();
		Set<Integer> schoolSet = new HashSet<Integer>();
		Set<Integer> branchschoolSet = new HashSet<Integer>();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			schoolSet.add((Integer) objects[0]);
			branchschoolSet.add((Integer) objects[1]);
		}
		// 用总校id,sql in查询其明细
		StringBuffer schoolSqlbuffer = new StringBuffer();
		schoolSqlbuffer
				.append("SELECT s.schoolId,s.schoolName,s.schoolAddress,s.longitude,s.latitude,s.schoolPhone,s.schoolLogo,s2.quantity from tb_school s left join tb_school02 s2 on s.schoolId=s2.schoolId where 1=1 ");// b.branchSchoolId
																																																						// in()
																																																						// and
																																																						// b.branchSchoolId=b2.branchschoolId
																																																						// and
																																																						// b.isValid=1
																																																						// and
																																																						// b2.isValid=1
		int i = 0;
		schoolSqlbuffer.append("and s.schoolId in(");
		List<Object> schoolparamList = new ArrayList<Object>();
		for (Iterator iterator = schoolSet.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			if (i == 0)
				schoolSqlbuffer.append("?");
			else
				schoolSqlbuffer.append(",?");
			schoolparamList.add(integer);
			i++;

		}
		schoolSqlbuffer.append(")  and s.isValid=?");
		schoolparamList.add(1);
		// System.out.println(schoolSqlbuffer.toString());
		List<?> schoollist = baseDao.sqlList(schoolSqlbuffer.toString(),
				schoolparamList);
		List<TbBranchschoolWeb> outList = new ArrayList<TbBranchschoolWeb>();
		TbBranchschoolWeb tbBranchschoolWeb = null;
		Map<Integer, String> logoMap = new HashMap<Integer, String>();
		if (schoollist != null && !schoollist.isEmpty()) {
			for (Iterator iterator = schoollist.iterator(); iterator.hasNext();) {
				tbBranchschoolWeb = new TbBranchschoolWeb();
				Object[] objects = (Object[]) iterator.next();
				tbBranchschoolWeb.setBranchschoolId((Integer) objects[0]);
				tbBranchschoolWeb
						.setBranchschoolName((String) (objects[1] == null ? ""
								: objects[1]));
				tbBranchschoolWeb
						.setBranchschoolAddress((String) (objects[2] == null ? ""
								: objects[2]));
				Double longitude_branch = (Double) (objects[3] == null ? 0.0
						: objects[3]);
				Double latitude_branch = (Double) (objects[4] == null ? 0.0
						: objects[4]);
				tbBranchschoolWeb.setBranchschoolLongitude(longitude_branch
						.toString());
				tbBranchschoolWeb.setBranchschoolLatitude(latitude_branch
						.toString());
				if (tbBranchschoolWeb == null || longitude == null
						|| latitude == null || longitude_branch == null
						|| latitude_branch == null)
					System.out.println("tbBranchschoolWeb-->"
							+ tbBranchschoolWeb + "--->longitude--->"
							+ longitude + "--->latitude--->" + latitude
							+ "--->longitude_branch--->" + longitude_branch
							+ "--->latitude_branch--->" + latitude_branch);
				tbBranchschoolWeb.setDistance(LalDistance.getShortDistance(
						Double.valueOf(longitude), Double.valueOf(latitude),
						longitude_branch, latitude_branch));
				tbBranchschoolWeb
						.setBranchschoolPhone((String) (objects[5] == null ? ""
								: objects[5]));
				tbBranchschoolWeb.setIsNot(1);
				String logo = TWPictureUtil
						.getNomalPicPath((String) (objects[6] == null ? ""
								: objects[6]));
				tbBranchschoolWeb.setBranchschoolPictureUrl(logo);
				tbBranchschoolWeb
						.setPopularity((Integer) (objects[7] == null ? 0
								: objects[7]));
				logoMap.put((Integer) objects[0], logo);
				outList.add(tbBranchschoolWeb);
			}
		}
		// 用分校id ,sql in查询其明细
		StringBuffer branchSqlbuffer = new StringBuffer();
		branchSqlbuffer
				.append("SELECT b.branchSchoolId,b.braschName,b.braschAddress,b.longitude,b.latitude,b.braschPhone,b2.quantity,b.schoolId from tb_branchschool b LEFT JOIN tb_branchschool02 b2 on b.branchSchoolId=b2.branchschoolId where 1=1 ");// b.branchSchoolId
																																																													// in()
																																																													// and
																																																													// b.branchSchoolId=b2.branchschoolId
																																																													// and
																																																													// b.isValid=1
																																																													// and
																																																													// b2.isValid=1
		i = 0;
		branchSqlbuffer.append("and b.branchSchoolId in(");
		List<Object> branchparamList = new ArrayList<Object>();
		for (Iterator iterator = branchschoolSet.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			if (i == 0)
				branchSqlbuffer.append("?");
			else
				branchSqlbuffer.append(",?");
			branchparamList.add(integer);
			i++;
		}
		branchSqlbuffer.append(") and b.isValid=? ");
		branchparamList.add(1);
		List<?> branchschoollist = baseDao.sqlList(branchSqlbuffer.toString(),
				branchparamList);
		if (branchschoollist != null && !branchparamList.isEmpty()) {
			for (Iterator iterator = branchschoollist.iterator(); iterator
					.hasNext();) {
				tbBranchschoolWeb = new TbBranchschoolWeb();
				Object[] objects = (Object[]) iterator.next();
				tbBranchschoolWeb.setBranchschoolId((Integer) objects[0]);
				tbBranchschoolWeb
						.setBranchschoolName((String) (objects[1] == null ? ""
								: objects[1]));
				tbBranchschoolWeb
						.setBranchschoolAddress((String) (objects[2] == null ? ""
								: objects[2]));
				Double longitude_branch = (Double) (objects[3] == null ? 0.0
						: objects[3]);
				Double latitude_branch = (Double) (objects[4] == null ? 0.0
						: objects[4]);
				tbBranchschoolWeb.setBranchschoolLongitude(longitude_branch
						.toString());
				tbBranchschoolWeb.setBranchschoolLatitude(latitude_branch
						.toString());
				tbBranchschoolWeb.setDistance(LalDistance.getShortDistance(
						Double.valueOf(longitude), Double.valueOf(latitude),
						longitude_branch, latitude_branch));
				tbBranchschoolWeb
						.setBranchschoolPhone((String) (objects[5] == null ? ""
								: objects[5]));
				tbBranchschoolWeb.setIsNot(0);
				tbBranchschoolWeb.setBranchschoolPictureUrl(logoMap
						.get(objects[7]));
				tbBranchschoolWeb
						.setPopularity((Integer) (objects[6] == null ? 0
								: objects[6]));
				outList.add(tbBranchschoolWeb);
			}
		}
		if (outList.isEmpty())
			return ResultJson.crateNullJson();
		if ("0".equals(orderType)) {
			Collections.sort(outList, new Comparator<TbBranchschoolWeb>() {
				public int compare(TbBranchschoolWeb arg0,
						TbBranchschoolWeb arg1) {
					return arg0.getDistance().compareTo(arg1.getDistance());
				}
			});
		} else if ("1".equals(orderType)) {
			Collections.sort(outList, new Comparator<TbBranchschoolWeb>() {
				public int compare(TbBranchschoolWeb arg0,
						TbBranchschoolWeb arg1) {
					return arg0.getBranchschoolName().compareTo(
							arg1.getBranchschoolName());
				}
			});
		} else {
			Collections.sort(outList, new Comparator<TbBranchschoolWeb>() {
				public int compare(TbBranchschoolWeb arg0,
						TbBranchschoolWeb arg1) {
					return arg1.getPopularity().compareTo(arg0.getPopularity());
				}
			});
		}
		Page page = getPage(currentPage, countPerPage, outList);
		List list02 = page.getCurrentList();
		for (Object object : list02) {
			if (object instanceof TbBranchschoolWeb) {
				TbBranchschoolWeb branchschoolWeb = (TbBranchschoolWeb) object;
				System.out.println(branchschoolWeb.getBranchschoolName());
			}
		}
		return ResultJson.crateSuccJson(page);
	}

	/**
	 * 输出list转page
	 * 
	 * @param currentPage
	 *            当前多少页
	 * @param countPerPage
	 *            每页多少条
	 * @param outList
	 *            输出list ,不能为null;
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	private Page getPage(String currentPage, String countPerPage, List outList) {
		Page page = getPage(currentPage, countPerPage);
		if (outList == null)
			return page;
		int num = (page.getCurrentPage() - 1) * page.getCountPerPage();
		int allcount = outList.size();
		int lastNum = num + page.getCountPerPage();
		List outL = null;
		if (lastNum < allcount)
			outL = outList.subList(num, num + page.getCountPerPage());
		else if (lastNum < num)
			outL = new ArrayList(page.getCountPerPage());
		else
			outL = outList.subList(num, allcount);
		page.setAllCount(allcount);
		page.setAllPages(allcount % page.getCountPerPage() == 0 ? allcount
				/ page.getCountPerPage() : allcount / page.getCountPerPage()
				+ 1);
		page.setCurrentList(outL);
		return page;

	}

	/**
	 * 学校详情接口及其人气累计
	 * 
	 * @param schoolId
	 *            学校ID
	 * @param type
	 *            1-分校，默认总校
	 * @param userinfoId
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public ResultJson schoolDetail(String schoolId, String type,
			String userinfoId) {
		if (StringUtils.isEmpty(schoolId) || StringUtils.isEmpty(userinfoId))
			return ResultJson.crate1001Json();
		BaseDao baseDao = new BaseDao();
		Integer school_id = Integer.parseInt(schoolId);
		Integer userinfo_id = Integer.parseInt(userinfoId);
		Map<String, Object> data = new HashMap<String, Object>(2);
		if ("1".equals(type)) {
			TbBranchschool tbBranchschool = baseDao.get(TbBranchschool.class,
					school_id);
			TbBranchschoolWeb branchschoolWeb = tbBranchschool.toDetailJson();
			DetachedCriteria currdc = DetachedCriteria
					.forClass(TbBranchschool02.class);
			currdc.add(Restrictions.eq("branchschoolId", school_id));
			ArrayList<TbBranchschool02> currs = baseDao.dCList(
					TbBranchschool02.class, currdc);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			if (currs == null || currs.isEmpty()) {
				TbBranchschool02 obj02 = new TbBranchschool02(school_id, null,
						1, "0", 0, 1, time, time);
				TbBranchschoolPopularity popularity = new TbBranchschoolPopularity(
						school_id, userinfo_id, 1, time, time);
				branchschoolWeb.setPopularity(1);
				Object[] objs = new Object[2];
				objs[0] = obj02;
				objs[1] = popularity;
				baseDao.save(objs);
			} else {
				TbBranchschool02 obj02 = currs.get(0);
				obj02.setQuantity(obj02.getQuantity() + 1);
				branchschoolWeb.setPopularity(obj02.getQuantity() + 1);
				TbBranchschoolPopularity popularity = new TbBranchschoolPopularity(
						school_id, userinfo_id, 1, time, time);
				new TransactionDao().addTbBranchschoolPopularity(popularity,
						obj02);
			}
			DetachedCriteria photoDc = DetachedCriteria
					.forClass(TbSchoolPhoto.class);
			photoDc.add(Restrictions.eq("branchschoolId", school_id));
			photoDc.add(Restrictions.eq("isValid", 1));
			Integer num = baseDao.dCRowCount(photoDc);
			branchschoolWeb.setImagesNumber(num);
			data.put("tbBranchschool", branchschoolWeb);
			DetachedCriteria dc = DetachedCriteria.forClass(TbCurriculum.class);
			dc.add(Restrictions.eq("tbBranchschool", tbBranchschool));
			dc.addOrder(Order.desc("defineSort"));
			dc.addOrder(Order.desc("defineSort2"));
			dc.addOrder(Order.asc("courseName"));
			List<Object> list = baseDao.dCList(dc);
			list = TWObjectUtil.getListWeb(list, "TbCourseWeb");
			data.put("courses", list);
		} else {
			TbSchool tbSchool = baseDao.get(TbSchool.class, school_id);
			TbBranchschoolWeb branchschoolWeb = tbSchool.toDetailJson();
			DetachedCriteria photoDc = DetachedCriteria
					.forClass(TbSchoolPhoto.class);
			photoDc.add(Restrictions.eq("schoolId", school_id));
			photoDc.add(Restrictions.eq("isValid", 1));
			Integer num = baseDao.dCRowCount(photoDc);
			branchschoolWeb.setImagesNumber(num);
			DetachedCriteria schooldc = DetachedCriteria
					.forClass(TbSchool02.class);
			schooldc.add(Restrictions.eq("schoolId", school_id));
			ArrayList<TbSchool02> school02s = baseDao.dCList(TbSchool02.class,
					schooldc);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			if (school02s == null || school02s.isEmpty()) {
				TbSchool02 obj02 = new TbSchool02(school_id, null, 1, "0", 0,
						1, time, time);
				TbSchoolPopularity popularity = new TbSchoolPopularity(
						school_id, userinfo_id, 1, time, time);
				branchschoolWeb.setPopularity(1);
				branchschoolWeb.setBranchschoolIntroduce("");
				Object[] objs = new Object[2];
				objs[0] = obj02;
				objs[1] = popularity;
				baseDao.save(objs);
			} else {
				TbSchool02 obj02 = school02s.get(0);
				obj02.setQuantity(obj02.getQuantity() + 1);
				branchschoolWeb.setPopularity(obj02.getQuantity() + 1);
				branchschoolWeb.setBranchschoolIntroduce(obj02.getOverview());
				TbSchoolPopularity popularity = new TbSchoolPopularity(
						school_id, userinfo_id, 1, time, time);
				new TransactionDao().addTbSchoolPopularity(popularity, obj02);
			}
			data.put("tbBranchschool", branchschoolWeb);
			DetachedCriteria dc = DetachedCriteria.forClass(TbCurriculum.class);
			dc.add(Restrictions.eq("tbSchool", tbSchool));
			dc.addOrder(Order.desc("defineSort"));
			dc.addOrder(Order.desc("defineSort2"));
			dc.addOrder(Order.asc("courseName"));
			List<Object> list = baseDao.dCList(dc);
			list = TWObjectUtil.getListWeb(list, "TbCourseWeb");
			data.put("courses", list);
		}
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 课程详情接口及其人气累计
	 * 
	 * @param curriculum_id
	 *            课程ID
	 * @param userinfo_id
	 *            用户ID
	 * @return
	 */
	public ResultJson curriculumDetail(String curriculum_id, String userinfo_id) {
		BaseDao baseDao = new BaseDao();
		if (StringUtils.isEmpty(curriculum_id)
				|| StringUtils.isEmpty(userinfo_id))
			return ResultJson.crate1001Json();
		Integer curriculumId = Integer.parseInt(curriculum_id);
		Integer userinfoId = Integer.parseInt(userinfo_id);
		TbCurriculum tbCurriculum = baseDao.get(TbCurriculum.class,
				curriculumId);
		DetachedCriteria currdc = DetachedCriteria
				.forClass(TbCurriculum02.class);
		currdc.add(Restrictions.eq("curriculumId", curriculumId));
		ArrayList<TbCurriculum02> currs = baseDao.dCList(TbCurriculum02.class,
				currdc);
		Map<String, Object> data = new HashMap<String, Object>();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (currs == null || currs.isEmpty()) {
			data.put("quantity", 1);
			TbCurriculum02 curriculum02 = new TbCurriculum02(curriculumId, 1,
					tbCurriculum.getStudyCosts(), 1, time, time);
			TbCurriculumPopularity popularity = new TbCurriculumPopularity(
					curriculumId, userinfoId, 1, time, time);
			Object[] objs = new Object[2];
			objs[0] = curriculum02;
			objs[1] = popularity;
			baseDao.save(objs);
			data.put("teamPrice", tbCurriculum.getStudyCosts());
		} else {
			TbCurriculum02 curriculum02 = currs.get(0);
			data.put("teamPrice", StringUtils.isEmpty(curriculum02
					.getTeamPrice()) ? tbCurriculum.getStudyCosts()
					: curriculum02.getTeamPrice());
			data.put("quantity", curriculum02.getQuantity() + 1);
			curriculum02.setQuantity(curriculum02.getQuantity() + 1);
			TbCurriculumPopularity popularity = new TbCurriculumPopularity(
					curriculumId, userinfoId, 1, time, time);
			new TransactionDao().addCurriculumPopularity(popularity,
					curriculum02);
		}
		if (tbCurriculum == null || tbCurriculum.getTbBranchschool() == null
				|| tbCurriculum.getTbBranchschool().getBranchSchoolId() == null) {
			data.put("branchSchoolName", "");
			data.put("branchschoolId", "");
			data.put("schoolAddress", "");
			data.put("latitude", 0.0);
			data.put("longitude", 0.0);
		} else {
			Integer branchschoolId = tbCurriculum.getTbBranchschool()
					.getBranchSchoolId();
			TbBranchschool tbBranchschool = baseDao.get(TbBranchschool.class,
					branchschoolId);
			data.put("branchSchoolName",
					TWObjectUtil.getString(tbBranchschool.getBraschName()));
			data.put("branchschoolId", branchschoolId);
			data.put("schoolAddress",
					TWObjectUtil.getString(tbBranchschool.getBraschAddress()));
			data.put("latitude", tbBranchschool.getLatitude() == null ? 0.0
					: tbBranchschool.getLatitude());
			data.put("longitude", tbBranchschool.getLongitude() == null ? 0.0
					: tbBranchschool.getLongitude());
		}
		TbMore tbMore = baseDao.get(TbMore.class, 7);
		data.put("hint", TWObjectUtil.getString(tbMore.getContent())
				.replaceAll("<br />", "\r\n"));
		data.put("isNot", 0);
		if (tbCurriculum == null)
			tbCurriculum = new TbCurriculum();
		data.put("phone", TWObjectUtil.getString(tbCurriculum.getPhone()));
		data.put("direction",
				TWObjectUtil.getString(tbCurriculum.getDirection()));
		data.put("startTerm",
				TWObjectUtil.getString(tbCurriculum.getStartTerm()));
		data.put("enterNeed",
				TWObjectUtil.getString(tbCurriculum.getEnterNeed()));
		data.put("coursename",
				TWObjectUtil.getString(tbCurriculum.getCourseName()));
		data.put("totalStudyTime",
				TWObjectUtil.getString(tbCurriculum.getTotalStudyTime()));
		data.put("courseUrl",
				TWObjectUtil.getNomalPicPath(tbCurriculum.getCourseUrl()));
		data.put("studyGoal",
				TWObjectUtil.getString(tbCurriculum.getStudyGoal()));
		data.put("endTerm", TWObjectUtil.getString(tbCurriculum.getEndTerm()));
		data.put("courseGoodness",
				TWObjectUtil.getString(tbCurriculum.getCourseGoodness()));
		data.put("studyCosts", tbCurriculum.getStudyCosts());
		data.put("courseIntroduce",
				TWObjectUtil.getString(tbCurriculum.getCourseIntroduce()));
		data.put("textbookCost", tbCurriculum.getTextbookCost());
		data.put("studyTime",
				TWObjectUtil.getString(tbCurriculum.getStudyTime()));
		data.put("courseBook",
				TWObjectUtil.getString(tbCurriculum.getCourseBook()));
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson curriculumDetail(TbOrder tbOrder, BaseDao baseDao,
			TbCoupon tbCoupon) {
		Integer curriculumId = tbOrder.getCurriculumId();
		TbCurriculumCopy tbCurriculum = baseDao.get(TbCurriculumCopy.class,
				curriculumId);
		Map<String, Object> data = new HashMap<String, Object>();
		if (tbCurriculum == null || tbCurriculum.getTbBranchschool() == null
				|| tbCurriculum.getTbBranchschool().getBranchSchoolId() == null) {
			data.put("branchSchoolName", "");
			data.put("branchschoolId", "");
			data.put("schoolAddress", "");
			data.put("latitude", 0.0);
			data.put("longitude", 0.0);
		} else {
			Integer branchschoolId = tbCurriculum.getTbBranchschool()
					.getBranchSchoolId();
			TbBranchschool tbBranchschool = baseDao.get(TbBranchschool.class,
					branchschoolId);
			data.put("branchSchoolName",
					TWObjectUtil.getString(tbBranchschool.getBraschName()));
			data.put("branchschoolId", branchschoolId);
			data.put("schoolAddress",
					TWObjectUtil.getString(tbBranchschool.getBraschAddress()));
			data.put("latitude", tbBranchschool.getLatitude() == null ? 0.0
					: tbBranchschool.getLatitude());
			data.put("longitude", tbBranchschool.getLongitude() == null ? 0.0
					: tbBranchschool.getLongitude());
		}
		TbMore tbMore = baseDao.get(TbMore.class, 7);
		data.put("hint", TWObjectUtil.getString(tbMore.getContent())
				.replaceAll("<br />", "\r\n"));
		if (tbCurriculum == null)
			tbCurriculum = new TbCurriculumCopy();
		data.put(
				"teamPrice",
				StringUtils.isEmpty(tbCurriculum.getTeamPrice()) ? tbCurriculum
						.getStudyCosts() : tbCurriculum.getTeamPrice());
		data.put("quantity", tbCurriculum.getQuantity() == null ? 1
				: tbCurriculum.getQuantity() + 1);
		data.put("isNot", 0);
		data.put("couponId", TWObjectUtil.getString(tbCoupon.getId()));
		data.put("phone", TWObjectUtil.getString(tbCurriculum.getPhone()));
		data.put("direction",
				TWObjectUtil.getString(tbCurriculum.getDirection()));
		data.put("startTerm",
				TWObjectUtil.getString(tbCurriculum.getStartTerm()));
		data.put("enterNeed",
				TWObjectUtil.getString(tbCurriculum.getEnterNeed()));
		data.put("coursename",
				TWObjectUtil.getString(tbCurriculum.getCourseName()));
		data.put("totalStudyTime",
				TWObjectUtil.getString(tbCurriculum.getTotalStudyTime()));
		data.put("courseUrl",
				TWObjectUtil.getNomalPicPath(tbCurriculum.getCourseUrl()));
		data.put("studyGoal",
				TWObjectUtil.getString(tbCurriculum.getStudyGoal()));
		data.put("endTerm", TWObjectUtil.getString(tbCurriculum.getEndTerm()));
		data.put("courseGoodness",
				TWObjectUtil.getString(tbCurriculum.getCourseGoodness()));
		data.put("studyCosts", tbCurriculum.getStudyCosts());
		data.put("courseIntroduce",
				TWObjectUtil.getString(tbCurriculum.getCourseIntroduce()));
		data.put("textbookCost", tbCurriculum.getTextbookCost());
		data.put("studyTime",
				TWObjectUtil.getString(tbCurriculum.getStudyTime()));
		data.put("courseBook",
				TWObjectUtil.getString(tbCurriculum.getCourseBook()));
		data.put("isUse",
				tbCoupon.getConsume() == null ? 0 : tbCoupon.getConsume());
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 选课报班添加城市条件
	 * 
	 * @param branchschool_id
	 *            分校Id 0-搜全部
	 * @param subject_id
	 *            科目Id
	 * @param lessonId
	 *            班Id
	 * @param cityId
	 *            城市Id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultJson findCurriculum(String branchschool_id, String cityId) {
		BaseDao baseDao = new BaseDao();
		Map<String, Object> data = new HashMap<String, Object>(2);
		Map<Integer, List<Map<String, Object>>> map = null;
		ArrayList<Map<String, Object>> strList = null;
		if (StringUtils.isNotEmpty(cityId)) {
			ArrayList<TbSchool> schools = null;
			Map<Integer, String> schoolmap = null;
			if (StringUtils.isEmpty(branchschool_id)
					|| "0".equals(branchschool_id)) {
				int city_id = Integer.parseInt(cityId);
				TbCity tbCity = new TbCity();
				tbCity.setCityId(city_id);
				DetachedCriteria townDc = DetachedCriteria
						.forClass(TbTown.class);
				townDc.add(Restrictions.eq("isValid", 1));
				townDc.add(Restrictions.eq("tbCity", tbCity));
				ArrayList<TbTown> towns = baseDao.dCList(TbTown.class, townDc);
				if (towns != null && !towns.isEmpty()) {
					DetachedCriteria schooldc = DetachedCriteria
							.forClass(TbSchool.class);
					schooldc.add(Restrictions.eq("isValid", 1));
					if (towns == null || towns.isEmpty())
						return ResultJson.crateNullJson();
					schooldc.add(Restrictions.in("tbTown", towns));
					schools = baseDao.dCList(TbSchool.class, schooldc);
					if (schools != null && !schools.isEmpty()) {
						schoolmap = new HashMap<Integer, String>();
						for (TbSchool tbSchool : schools) {
							schoolmap.put(tbSchool.getSchoolId(),
									tbSchool.getSchoolName());
						}
					}
				} else
					return ResultJson.crateNullJson();

			} else {
				int branchschoolId = Integer.parseInt(branchschool_id);
				schools = new ArrayList<TbSchool>(1);
				TbBranchschool tbBranchschool = baseDao.get(
						TbBranchschool.class, branchschoolId);
				TbSchool tbSchool = baseDao.get(TbSchool.class, tbBranchschool
						.getTbSchool().getSchoolId());
				schools.add(tbSchool);
				schoolmap = new HashMap<Integer, String>(1);
				schoolmap.put(tbSchool.getSchoolId(), tbSchool.getSchoolName());
			}
			if (schoolmap == null || schoolmap.isEmpty())
				return ResultJson.crateNullJson();
			DetachedCriteria lessdc = DetachedCriteria.forClass(TbLesson.class);
			lessdc.add(Restrictions.eq("isValid", 1));
			if (schools != null && !schools.isEmpty())
				lessdc.add(Restrictions.in("tbSchool", schools));
			lessdc.setFetchMode("tbSubject", FetchMode.JOIN);
			lessdc.addOrder(Order.desc("defineSort"));
			lessdc.addOrder(Order.desc("defineSort2"));
			ArrayList<TbLesson> tbLessons = baseDao.dCList(TbLesson.class,
					lessdc);
			if (tbLessons != null && !tbLessons.isEmpty()) {
				map = new HashMap<Integer, List<Map<String, Object>>>();
				Map<Integer, String> subjects = new HashMap<Integer, String>();
				Map<String, Object> less = null;
				Integer subjectId = null;
				List<Map<String, Object>> lessList = null;
				for (TbLesson tbLesson : tbLessons) {
					subjectId = tbLesson.getTbSubject().getSubjectId();
					subjects.put(subjectId, tbLesson.getTbSubject()
							.getSubjectName());
					lessList = map.get(subjectId);
					if (lessList == null) {
						lessList = new ArrayList<Map<String, Object>>();
					}
					less = new HashMap<String, Object>();
					less.put("schoolName", TWObjectUtil.getString(schoolmap
							.get(tbLesson.getTbSchool().getSchoolId())));
					less.put("id", tbLesson.getId());
					less.put("lessonName",
							TWObjectUtil.getString(tbLesson.getLessonName()));
					lessList.add(less);
					map.put(subjectId, lessList);
				}
				Map<String, Object> subject = null;
				strList = new ArrayList<Map<String, Object>>();
				for (Iterator iterator = subjects.entrySet().iterator(); iterator
						.hasNext();) {
					Entry<Integer, String> entry = (Entry<Integer, String>) iterator
							.next();
					subject = new HashMap<String, Object>(2);
					subject.put("subjectId", entry.getKey());
					subject.put("subjectName",
							TWObjectUtil.getString(entry.getValue()));
					strList.add(subject);
				}
			}
			if (strList != null && !strList.isEmpty()) {
				Collections.sort(strList,
						new Comparator<Map<String, Object>>() {
							public int compare(Map<String, Object> arg0,
									Map<String, Object> arg1) {
								return ((Integer) arg0.get("subjectId"))
										.compareTo((Integer) arg1
												.get("subjectId"));
							}
						});
			}
			data.put("tbsubject", strList);
			data.put("tblesson", map);
			return ResultJson.crateSuccJson(data);
		} else {
			Integer branchschoolId = 0;
			if (StringUtils.isNotEmpty(branchschool_id))
				branchschoolId = Integer.parseInt(branchschool_id);
			return new UserJsonService().listcourse(branchschoolId);
		}
	}

	/**
	 * 学校相册列表
	 * 
	 * @param schoolId
	 *            学校Id
	 * @param type
	 *            1-分校，默认总校
	 * @param countPerPage
	 *            每页多少条
	 * @param currentPage
	 *            当前多少页
	 * @return
	 */
	public ResultJson findSchoolPhoto(String schoolId, String type,
			String currentPage, String countPerPage) {
		Page page = getPage(currentPage, countPerPage);
		if (StringUtils.isNotEmpty(schoolId)) {
			int school_id = Integer.parseInt(schoolId);
			BaseDao baseDao = new BaseDao();
			DetachedCriteria schoolDc = DetachedCriteria
					.forClass(TbSchoolPhoto.class);
			if ("1".equals(type))
				schoolDc.add(Restrictions.eq("branchschoolId", school_id));
			else
				schoolDc.add(Restrictions.eq("schoolId", school_id));
			schoolDc.add(Restrictions.eq("isValid", 1));
			schoolDc.addOrder(Order.desc("id"));
			baseDao.dCPage(page, schoolDc);
			@SuppressWarnings("rawtypes")
			List outList = page.getCurrentList();
			if (outList == null || outList.isEmpty())
				return ResultJson.crateNullJson();
			List<Map<String, String>> maps = new ArrayList<Map<String, String>>(
					outList.size());
			Map<String, String> map = null;
			for (Object object : outList) {
				if (object instanceof TbSchoolPhoto) {
					TbSchoolPhoto photo = (TbSchoolPhoto) object;
					map = new HashMap<String, String>(2);
					map.put("photoPath",
							TWPictureUtil.getNomalPicPath(photo.getPhotoPath()));
					map.put("photoPathMini",
							TWPictureUtil.getMinPicPath(photo.getPhotoPath()));
					maps.add(map);
				}
			}
			page.setCurrentList(maps);
			return ResultJson.crateSuccJson(page);
		} else {
			return ResultJson.createFailJson(1001, "输入参数不合规格");
		}
	}

	private Page getPage(String currentPage, String countPerPage) {
		Page page = new Page();
		if (StringUtils.isNotEmpty(currentPage))
			page.setCurrentPage(Integer.parseInt(currentPage));
		if (StringUtils.isNotEmpty(countPerPage))
			page.setCountPerPage(Integer.parseInt(countPerPage));
		return page;
	}

	/**
	 * 优惠专区查询接口
	 * 
	 * @param title
	 *            题目
	 * @param cityId
	 *            城市ID
	 * @param townId
	 *            城区ID
	 * @param schoolId
	 *            学校Id
	 * @param longitude
	 *            经度(必须)
	 * @param latitude
	 *            纬度（必须）
	 * @param orderType
	 *            排序：默认人气，1-名称，2-价格，3-距离
	 * @param currentPage
	 *            当前页数，默认第1条
	 * @param countPerPage
	 *            默认6条，每页多少条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultJson teamfind(String title, String cityId, String townId,
			String schoolId, String longitude, String latitude,
			String orderType, String currentPage, String countPerPage,
			String cityName) {
		Map<Integer, Map<String, Object>> school_map = new HashMap<Integer, Map<String, Object>>();
		Set<Integer> school_ids = new HashSet<Integer>();
		BaseDao baseDao = new BaseDao();
		if (StringUtils.isNotEmpty(cityName) || StringUtils.isNotEmpty(cityId)
				|| StringUtils.isNotEmpty(townId)
				|| StringUtils.isNotEmpty(schoolId)) {
			if (StringUtils.isNotEmpty(cityName)
					|| StringUtils.isNotEmpty(cityId)
					|| StringUtils.isNotEmpty(townId)) {
				ArrayList<TbTown> tbTowns = new ArrayList<TbTown>();
				if (StringUtils.isNotEmpty(townId)) {
					int town_id = Integer.parseInt(townId);
					TbTown tbTown = new TbTown();
					tbTown.setTownId(town_id);
					tbTowns.add(tbTown);
				} else if (StringUtils.isNotEmpty(cityId)) {
					int city_id = Integer.parseInt(cityId);
					DetachedCriteria towndc = DetachedCriteria
							.forClass(TbTown.class);
					TbCity tbCity = new TbCity();
					tbCity.setCityId(city_id);
					towndc.add(Restrictions.eq("tbCity", tbCity));
					towndc.add(Restrictions.eq("isValid", 1));
					tbTowns = baseDao.dCList(TbTown.class, towndc);
				} else {
					DetachedCriteria cityDc = DetachedCriteria
							.forClass(TbCity.class);
					cityDc.add(Restrictions.ilike("cityName", cityName,
							MatchMode.ANYWHERE));
					ArrayList<TbCity> citys = baseDao.dCList(TbCity.class,
							cityDc);
					if (citys != null && !citys.isEmpty()) {
						TbCity city = citys.get(0);
						DetachedCriteria townDc = DetachedCriteria
								.forClass(TbTown.class);
						townDc.add(Restrictions.eq("tbCity", city));
						tbTowns = baseDao.dCList(TbTown.class, townDc);
					}
				}
				DetachedCriteria schoolDc = DetachedCriteria
						.forClass(TbSchool.class);
				if (tbTowns == null || tbTowns.isEmpty())
					return ResultJson.crateNullJson();
				schoolDc.add(Restrictions.in("tbTown", tbTowns));
				schoolDc.add(Restrictions.eq("isValid", 1));
				ArrayList<TbSchool> schools = baseDao.dCList(TbSchool.class,
						schoolDc);
				Map<String, Object> schoolmap = null;
				for (TbSchool tbSchool : schools) {
					if (!school_map.containsKey(tbSchool.getSchoolId())) {
						schoolmap = new HashMap<String, Object>(4);
						schoolmap.put("longitude",
								tbSchool.getLongitude() == null ? 0.0
										: tbSchool.getLongitude());
						schoolmap.put(
								"latitude",
								tbSchool.getLatitude() == null ? 0.0 : tbSchool
										.getLatitude());
						schoolmap.put("schoolLogo", TWPictureUtil
								.getNomalPicPath(tbSchool.getSchoolLogo()));
						schoolmap.put("schoolName", TWObjectUtil
								.getString(tbSchool.getSchoolName()));
						school_ids.add(tbSchool.getSchoolId());
						school_map.put(tbSchool.getSchoolId(), schoolmap);
					}
				}
			} else {
				int school_id = Integer.parseInt(schoolId);
				TbSchool tbSchool = baseDao.get(TbSchool.class, school_id);
				school_ids.add(tbSchool.getSchoolId());
				HashMap<String, Object> schoolmap = new HashMap<String, Object>(
						4);
				schoolmap.put(
						"longitude",
						tbSchool.getLongitude() == null ? 0.0 : tbSchool
								.getLongitude());
				schoolmap.put("latitude", tbSchool.getLatitude() == null ? 0.0
						: tbSchool.getLatitude());
				schoolmap
						.put("schoolLogo", TWPictureUtil
								.getNomalPicPath(tbSchool.getSchoolLogo()));
				schoolmap.put("schoolName",
						TWObjectUtil.getString(tbSchool.getSchoolName()));
				school_map.put(tbSchool.getSchoolId(), schoolmap);
			}
		} else {
			return ResultJson.crate1001Json();
		}
		if (school_ids.isEmpty())
			return ResultJson.crateNullJson();
		DetachedCriteria teamdc = DetachedCriteria.forClass(TbTeam.class);
		if (!school_ids.isEmpty())
			teamdc.add(Restrictions.in("schoolId", school_ids));
		if (StringUtils.isNotEmpty(title)) {
			teamdc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		}
		teamdc.add(Restrictions.eq("status", 2));
		teamdc.add(Restrictions.eq("isValid", 1));
		Page page = getPage(currentPage, countPerPage);
		List<TbTeam> tbTeams = null;
		if ("3".equals(orderType)) {
			tbTeams = baseDao.dCList(TbTeam.class, teamdc);
			teamdc.addOrder(Order.desc("nowNumber"));
		} else if ("2".equals(orderType)) {
			teamdc.addOrder(Order.asc("teamPrice"));
			page = baseDao.dCPage(page, teamdc);
			tbTeams = page.getCurrentList();
		} else if ("1".equals(orderType)) {
			teamdc.addOrder(Order.asc("title"));
			page = baseDao.dCPage(page, teamdc);
			tbTeams = page.getCurrentList();
		} else {
			teamdc.addOrder(Order.desc("nowNumber"));
			page = baseDao.dCPage(page, teamdc);
			tbTeams = page.getCurrentList();
		}
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
		Map<String, Object> teamMap = null;
		for (TbTeam tbTeam : tbTeams) {
			teamMap = new HashMap<String, Object>();
			Map<String, Object> values = school_map.get(tbTeam.getSchoolId());
			teamMap.put("schoolLogo", values.get("schoolLogo"));
			teamMap.put("schoolName", values.get("schoolName"));
			teamMap.put("teamId", tbTeam.getId());
			teamMap.put("title", TWObjectUtil.getString(tbTeam.getTitle()));
			teamMap.put("summary", TWObjectUtil.getString(tbTeam.getSummary()));
			teamMap.put("price", tbTeam.getPrice());
			teamMap.put("teamPrice", tbTeam.getTeamPrice());
			teamMap.put("nowNumber",
					tbTeam.getNowNumber() == null ? 0 : tbTeam.getNowNumber());
			teamMap.put("distance", LalDistance.getShortDistance(
					Double.valueOf(longitude), Double.valueOf(latitude),
					(Double) values.get("longitude"),
					(Double) values.get("latitude")));
			outList.add(teamMap);
		}
		if ("3".equals(orderType)) {
			Collections.sort(outList, new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> arg0,
						Map<String, Object> arg1) {
					return ((Double) arg0.get("distance"))
							.compareTo((Double) arg1.get("distance"));
				}
			});
			return ResultJson.crateSuccJson(getPage(currentPage, countPerPage,
					outList));
		}
		page.setCurrentList(outList);
		return ResultJson.crateSuccJson(page);
	}

	/**
	 * 优惠专区详情接口
	 * 
	 * @param teamId
	 * @return
	 */
	public ResultJson teamDetail(String teamId) {
		BaseDao baseDao = new BaseDao();
		if (StringUtils.isEmpty(teamId))
			return ResultJson.crate1001Json();
		Integer team_id = Integer.parseInt(teamId);
		TbTeam tbTeam = baseDao.get(TbTeam.class, team_id);
		Map<String, Object> data = new HashMap<String, Object>();
		Integer schoolId = tbTeam.getSchoolId();
		TbSchool tbSchool = baseDao.get(TbSchool.class, schoolId);
		TbMore tbMore = baseDao.get(TbMore.class, 7);
		data.put("title", TWObjectUtil.getString(tbTeam.getTitle()));
		data.put("hint", TWObjectUtil.getString(tbMore.getContent())
				.replaceAll("<br />", "\r\n"));
		data.put("schoolId", schoolId);
		data.put("imgurl",
				TWPictureUtil.getNomalPicPath(tbSchool.getSchoolLogo()));
		data.put("latitude",
				tbSchool.getLatitude() == null ? 0.0 : tbSchool.getLatitude());
		data.put("longitude",
				tbSchool.getLongitude() == null ? 0.0 : tbSchool.getLongitude());
		data.put("price", tbTeam.getPrice() == null ? 0.0 : tbTeam.getPrice());
		data.put("teamPrice",
				tbTeam.getTeamPrice() == null ? 0.0 : tbTeam.getTeamPrice());
		data.put("schoolName", TWObjectUtil.getString(tbSchool.getSchoolName()));
		data.put("schoolPhone",
				TWObjectUtil.getString(tbSchool.getSchoolPhone()));
		data.put("schoolAddress",
				TWObjectUtil.getString(tbSchool.getSchoolAddress()));
		data.put("summary", TWObjectUtil.getString(tbTeam.getSummary()));
		data.put("nowNumber",
				tbTeam.getNowNumber() == null ? 0 : tbTeam.getNowNumber());
		data.put("detail", TWObjectUtil.getString(tbTeam.getDetail()));
		data.put("startTime", TWDataUtil.TimestampToDate(tbTeam.getStartTime()));
		data.put("expireTime",
				TWDataUtil.TimestampToDate(tbTeam.getExpireTime()));
		data.put("isUseRefund",
				tbTeam.getIsUseRefund() == null ? 0 : tbTeam.getIsUseRefund());
		data.put("isLateRefund",
				tbTeam.getIsLateRefund() == null ? 0 : tbTeam.getIsLateRefund());
		data.put("isUseUnreadRefund", tbTeam.getIsUseUnreadRefund() == null ? 0
				: tbTeam.getIsUseUnreadRefund());
		data.put("isReadRefund",
				tbTeam.getIsReadRefund() == null ? 0 : tbTeam.getIsReadRefund());
		data.put("remark", TWObjectUtil.getString(tbTeam.getRemark()));
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson teamCopyDetail(String teamId, TbCoupon tbCoupon) {
		BaseDao baseDao = new BaseDao();
		if (StringUtils.isEmpty(teamId))
			return ResultJson.crate1001Json();
		Integer team_id = Integer.parseInt(teamId);
		TbTeamCopy tbTeam = baseDao.get(TbTeamCopy.class, team_id);
		Map<String, Object> data = new HashMap<String, Object>();
		Integer schoolId = tbTeam.getSchoolId();
		TbSchool tbSchool = baseDao.get(TbSchool.class, schoolId);
		TbMore tbMore = baseDao.get(TbMore.class, 7);
		data.put("title", TWObjectUtil.getString(tbTeam.getTitle()));
		data.put("hint", TWObjectUtil.getString(tbMore.getContent())
				.replaceAll("<br />", "\r\n"));
		data.put("isUse",
				tbCoupon.getConsume() == null ? 0 : tbCoupon.getConsume());
		data.put("couponId", tbCoupon.getId());
		data.put("schoolId", schoolId);
		data.put("imgurl",
				TWPictureUtil.getNomalPicPath(tbSchool.getSchoolLogo()));
		data.put("latitude",
				tbSchool.getLatitude() == null ? 0.0 : tbSchool.getLatitude());
		data.put("longitude",
				tbSchool.getLongitude() == null ? 0.0 : tbSchool.getLongitude());
		data.put("price", tbTeam.getPrice() == null ? 0.0 : tbTeam.getPrice());
		data.put("teamPrice",
				tbTeam.getTeamPrice() == null ? 0.0 : tbTeam.getTeamPrice());
		data.put("schoolName", TWObjectUtil.getString(tbSchool.getSchoolName()));
		data.put("schoolPhone",
				TWObjectUtil.getString(tbSchool.getSchoolPhone()));
		data.put("schoolAddress",
				TWObjectUtil.getString(tbSchool.getSchoolAddress()));
		data.put("summary", TWObjectUtil.getString(tbTeam.getSummary()));
		data.put("nowNumber",
				tbTeam.getNowNumber() == null ? 0 : tbTeam.getNowNumber());
		data.put("detail", TWObjectUtil.getString(tbTeam.getDetail()));
		data.put("startTime", TWDataUtil.TimestampToDate(tbTeam.getStartTime()));
		data.put("expireTime",
				TWDataUtil.TimestampToDate(tbTeam.getExpireTime()));
		data.put("isUseRefund",
				tbTeam.getIsUseRefund() == null ? 0 : tbTeam.getIsUseRefund());
		data.put("isLateRefund",
				tbTeam.getIsLateRefund() == null ? 0 : tbTeam.getIsLateRefund());
		data.put("isUseUnreadRefund", tbTeam.getIsUseUnreadRefund() == null ? 0
				: tbTeam.getIsUseUnreadRefund());
		data.put("isReadRefund",
				tbTeam.getIsReadRefund() == null ? 0 : tbTeam.getIsReadRefund());
		data.put("remark", TWObjectUtil.getString(tbTeam.getRemark()));
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 我的_优惠分页列表接口
	 * 
	 * @param userinfoId
	 * @param longitude
	 * @param latitude
	 * @param currentPage
	 * @param countPerPage
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultJson myTeam(String userinfoId, String longitude,
			String latitude, String currentPage, String countPerPage) {
		if (StringUtils.isEmpty(userinfoId) || StringUtils.isEmpty(latitude)
				|| StringUtils.isEmpty(longitude)) {
			return ResultJson.crate1001Json();
		}
		Page page = getPage(currentPage, countPerPage);
		BaseDao baseDao = new BaseDao();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Integer.parseInt(userinfoId));
		paramList.add(1);
		paramList.add("pay");
		page = baseDao
				.sqlPage(
						page,
						"select ord.schoolId,ord.teamId,cou.consume,ord.time,ord.origin,ord.id,ord.type,ord.curriculumId from tb_coupon cou  LEFT JOIN tb_order ord on cou.orderId=ord.id where ord.userinfoId=? and ord.isValid=? and ord.`status`=? ORDER BY ord.id DESC ",
						paramList);
		List outList = page.getCurrentList();
		if (outList == null || outList.isEmpty())
			return ResultJson.crateSuccJson(page);
		Set<Integer> schoolIds = new HashSet<Integer>();
		Set<Integer> teamIds = new HashSet<Integer>();
		Set<Integer> curriculumIds = new HashSet<Integer>();
		List<Map<String, Object>> outList2 = new ArrayList<Map<String, Object>>(
				outList.size());
		Map<String, Object> outMap = null;
		for (Iterator iterator = outList.iterator(); iterator.hasNext();) {
			Object[] objs = (Object[]) iterator.next();
			outMap = new HashMap<String, Object>();
			Integer schoolId = (Integer) objs[0];
			schoolIds.add(schoolId);
			outMap.put("schoolId", schoolId);
			int teamId = objs[1] == null ? 0 : (Integer) objs[1];
			teamIds.add(teamId);
			outMap.put("teamId", teamId);
			int curriculumId = objs[7] == null ? 0 : (Integer) objs[7];
			outMap.put("curriculumId", curriculumId);
			curriculumIds.add(curriculumId);
			int consume = objs[2] == null ? 0 : (Integer) objs[2];
			outMap.put("isUse", consume);
			String time = DateFormatUtils.format(
					((Timestamp) objs[3]).getTime(), "yyyy.MM.dd");
			outMap.put("time", time);
			outMap.put("origin", objs[4]);
			outMap.put("orderId", objs[5]);
			outMap.put("type", objs[6] == null ? 0 : (Integer) objs[6]);
			outList2.add(outMap);
		}
		DetachedCriteria schdc = DetachedCriteria.forClass(TbSchool.class);
		if (schoolIds != null && !schoolIds.isEmpty())
			schdc.add(Restrictions.in("schoolId", schoolIds));
		schdc.add(Restrictions.eq("isValid", 1));
		ArrayList<TbSchool> schools = baseDao.dCList(TbSchool.class, schdc);
		Map<Integer, Map<String, Object>> schoolmaps = new HashMap<Integer, Map<String, Object>>();
		Map<String, Object> school_map = null;
		for (TbSchool tbSchool : schools) {
			school_map = new HashMap<String, Object>(4);
			school_map.put("schoolLogo",
					TWPictureUtil.getNomalPicPath(tbSchool.getSchoolLogo()));
			school_map.put("schoolName",
					TWObjectUtil.getString(tbSchool.getSchoolName()));
			double lon = tbSchool.getLongitude() == null ? 0.0 : tbSchool
					.getLongitude();
			double lat = tbSchool.getLatitude() == null ? 0.0 : tbSchool
					.getLatitude();
			school_map.put("distance", LalDistance.getShortDistance(
					Double.valueOf(longitude), Double.valueOf(latitude), lon,
					lat));
			schoolmaps.put(tbSchool.getSchoolId(), school_map);
		}
		DetachedCriteria teamdc = DetachedCriteria.forClass(TbTeamCopy.class);
		if (teamIds != null && !teamIds.isEmpty())
			teamdc.add(Restrictions.in("id", teamIds));
		ArrayList<TbTeamCopy> tbTeams = baseDao
				.dCList(TbTeamCopy.class, teamdc);
		Map<Integer, String> teammaps = new HashMap<Integer, String>();
		for (TbTeamCopy tbTeam : tbTeams) {
			teammaps.put(tbTeam.getId(),
					TWObjectUtil.getString(tbTeam.getTitle()));
		}
		DetachedCriteria currdc = DetachedCriteria
				.forClass(TbCurriculumCopy.class);
		if (curriculumIds != null && !curriculumIds.isEmpty())
			currdc.add(Restrictions.in("courseId", curriculumIds));
		ArrayList<TbCurriculumCopy> curriculums = baseDao.dCList(
				TbCurriculumCopy.class, currdc);
		Map<Integer, String> currmaps = new HashMap<Integer, String>();
		for (TbCurriculumCopy tbCurriculum : curriculums) {
			currmaps.put(tbCurriculum.getCourseId(),
					TWObjectUtil.getString(tbCurriculum.getCourseName()));
		}
		for (Iterator iterator = outList2.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			Integer school_id = (Integer) map.get("schoolId");
			Map<String, Object> schoolmap = schoolmaps.get(school_id);
			map.remove("schoolId");
			map.putAll(schoolmap);
			Integer teamId = (Integer) map.get("teamId");
			Integer curriculumId = (Integer) map.get("curriculumId");
			Integer type = (Integer) map.get("type");
			map.remove("teamId");
			map.remove("curriculumId");
			map.put("isNot", 1);
			String title = null;
			if (type == 0)
				title = teammaps.get(teamId);
			else
				title = currmaps.get(curriculumId);
			map.put("title", TWObjectUtil.getString(title));
		}
		page.setCurrentList(outList2);
		return ResultJson.crateSuccJson(page);
	}

	/**
	 * 我的_报班统计接口
	 * 
	 * @param userinfoId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultJson myStatistics(String userinfoId, String startTime,
			String endTime) {
		if (StringUtils.isEmpty(userinfoId)) {
			return ResultJson.crate1001Json();
		}
		BaseDao baseDao = new BaseDao();
		int user_id = Integer.parseInt(userinfoId);
		TbUserinfo tbUserinfo = baseDao.get(TbUserinfo.class, user_id);
		Integer schoolId = tbUserinfo.getTbSchool().getSchoolId();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(schoolId);
		paramList.add(1);
		paramList.add(TWDataUtil.getStartTimestamp(startTime));
		paramList.add(TWDataUtil.getEndTimestamp(endTime));
		List<?> list = baseDao
				.sqlList(
						"SELECT o.origin,c.consume,o.quantity from tb_coupon c LEFT JOIN tb_order o on c.orderId=o.id where o.schoolId=? and o.isValid=? and c.time>? and c.time< ?  ",
						paramList);
		if (list == null || list.isEmpty())
			return ResultJson.crateNullJson();
		Integer curriculumNumber = 0;
		Double totalMoney = 0.0;
		Integer readCurriculumNumber = 0;
		Double readTotalMoney = 0.0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objs = (Object[]) iterator.next();
			Double origin = (Double) objs[0];
			Integer consume = (Integer) objs[1];
			Integer quantity = (Integer) objs[2];
			curriculumNumber += quantity;
			totalMoney += origin;
			if (consume == 1) {
				readCurriculumNumber += quantity;
				readTotalMoney += origin;
			}
		}
		Map<String, Object> data = new HashMap<String, Object>(4);
		data.put("curriculumNumber", curriculumNumber);
		data.put("totalMoney", totalMoney);
		data.put("readCurriculumNumber", readCurriculumNumber);
		data.put("readTotalMoney", readTotalMoney);
		return ResultJson.crateSuccJson(data);
	}

	/**
	 * 我的_报班下单或优惠详情接口
	 * 
	 * @param orderId
	 * @return
	 */
	public ResultJson myTeamOrCurriculum(String orderId) {
		if (StringUtils.isEmpty(orderId))
			return ResultJson.crate1001Json();
		BaseDao baseDao = new BaseDao();
		int order_id = Integer.parseInt(orderId);
		TbOrder tbOrder = baseDao.get(TbOrder.class, order_id);
		DetachedCriteria coudc = DetachedCriteria.forClass(TbCoupon.class);
		coudc.add(Restrictions.eq("orderId", order_id));
		ArrayList<TbCoupon> coupons = baseDao.dCList(TbCoupon.class, coudc);
		if (coupons == null || coupons.isEmpty())
			return ResultJson.crateNullJson();
		TbCoupon tbCoupon = coupons.get(0);
		if (tbCoupon.getId() != null) {
			StringBuffer buffer = new StringBuffer("消费号:");
			buffer.append(tbCoupon.getId());
			buffer.append(" 密码:");
			buffer.append(tbCoupon.getSecret());
			tbCoupon.setId(buffer.toString());
		}
		Integer type = tbOrder.getType();
		if (1 == type) {
			return curriculumDetail(tbOrder, baseDao, tbCoupon);
		} else {
			return teamCopyDetail(tbOrder.getTeamId().toString(), tbCoupon);
		}
	}

	/**
	 * 添加订单
	 * 
	 * @param userinfo_id
	 *            用户ID
	 * @param quantity_str
	 *            默认为1，购买数量
	 * @param curriculum_str
	 *            type为报班时，必须给值
	 * @param team_str
	 *            type为优惠时，必须给值
	 * @param type_str
	 *            0-优惠，1-报班，默认优惠
	 * @param origin_str
	 *            购买总额
	 * @return
	 */
	public ResultJson alipaySuccess(String userinfo_id, String quantity_str,
			String curriculum_str, String team_str, String type_str,
			String origin_str) {
		if (StringUtils.isEmpty(userinfo_id))
			return ResultJson.crate1001Json();
		Integer type = "1".equals(type_str) ? 1 : 0;
		long sysTime = System.currentTimeMillis();
		Timestamp time = new Timestamp(sysTime);
		Integer userinfoId = Integer.parseInt(userinfo_id);
		BaseDao baseDao = new BaseDao();
		Integer branchSchool_Id = null;
		Integer schoolId = null;
		// if(tbUserinfo.getTbBranchschool()!=null&&tbUserinfo.getTbSchool()!=null){
		// branchSchool_Id = tbUserinfo.getTbBranchschool().getBranchSchoolId();
		// schoolId = tbUserinfo.getTbSchool().getSchoolId();
		// }
		Integer quantity = 1;
		Integer curriculumId = null;
		Integer teamId = null;
		Double price = null;
		Double origin = null;
		TbTeam tbTeam = new TbTeam();
		TbTeamCopy tbTeamCopy = null;
		TbCurriculumCopy tbCurriculumCopy = null;
		String subject = null;
		if (type == 1) {
			if (StringUtils.isEmpty(curriculum_str))
				return ResultJson.crate1001Json();
			curriculumId = Integer.parseInt(curriculum_str);
			TbCurriculum tbCurriculum = baseDao.get(TbCurriculum.class,
					curriculumId);
			subject = tbCurriculum.getCourseName();
			tbCurriculumCopy = new TbCurriculumCopy(tbCurriculum);
			if (schoolId == null) {
				schoolId = tbCurriculum.getTbSchool().getSchoolId();
			}
		} else {
			if (StringUtils.isEmpty(team_str))
				return ResultJson.crate1001Json();
			teamId = Integer.parseInt(team_str);
			tbTeam = baseDao.get(TbTeam.class, teamId);
			subject = tbTeam.getTitle();
			tbTeamCopy = new TbTeamCopy(tbTeam);
			if (schoolId == null) {
				schoolId = tbTeam.getSchoolId();
			}
		}
		if (StringUtils.isNotEmpty(quantity_str)) {
			quantity = Integer.parseInt(quantity_str);
		}
		if (StringUtils.isNotEmpty(origin_str)) {
			origin = Double.valueOf(origin_str);
			price = origin / quantity;
		} else {
			return ResultJson.crate1001Json();
		}
		String out_trade_no = UtilDate.getOutTradeNo();
		TbOrder tbOrder = new TbOrder(1, "unpay", userinfoId, null, null,
				schoolId, branchSchool_Id, out_trade_no, null, curriculumId,
				quantity, teamId, price, origin, type, null, null, 1, time,
				time);
		Boolean boo = null;
		if (tbTeamCopy == null || tbTeamCopy.getIsValid() == null)
			boo = new TransactionDao().addOrder(tbOrder, tbCurriculumCopy);
		else
			boo = new TransactionDao().addOrder(tbOrder, tbTeamCopy);
		HashMap<String, Object> data = new HashMap<String, Object>(1);
		String signData = AlipayCore.createLinkString2(UtilDate.createMap(
				subject, origin_str, "校掌支付宝收款", out_trade_no));
		String sign = RSA.sign(signData, AlipayConfig.private_key,
				AlipayConfig._input_charset);
		String payInfo = new StringBuffer(signData).append("&sign=\"")
				.append(sign).append("\"").append("&sign_type=\"")
				.append(AlipayConfig.sign_type).append("\"").toString();
		try {
			data.put("payInfo", URLEncoder.encode(payInfo, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			loger.error(e);
		}
		return boo ? ResultJson.crateSuccJson(data) : ResultJson
				.createFailJson(1004, "添加失败");
	}

	public ResultJson alipaySuccess1(String userinfo_id, String quantity_str,
			String curriculum_str, String team_str, String type_str,
			String origin_str) {
		if (StringUtils.isEmpty(userinfo_id))
			return ResultJson.crate1001Json();
		Integer type = "1".equals(type_str) ? 1 : 0;
		long sysTime = System.currentTimeMillis();
		Timestamp time = new Timestamp(sysTime);
		Integer userinfoId = Integer.parseInt(userinfo_id);
		BaseDao baseDao = new BaseDao();
		Integer branchSchool_Id = null;
		Integer schoolId = null;
		// if(tbUserinfo.getTbBranchschool()!=null&&tbUserinfo.getTbSchool()!=null){
		// branchSchool_Id = tbUserinfo.getTbBranchschool().getBranchSchoolId();
		// schoolId = tbUserinfo.getTbSchool().getSchoolId();
		// }
		Integer quantity = 1;
		Integer curriculumId = null;
		Integer teamId = null;
		Double price = null;
		Double origin = null;
		TbTeam tbTeam = new TbTeam();
		TbTeamCopy tbTeamCopy = null;
		TbCurriculumCopy tbCurriculumCopy = null;
		String subject = null;
		if (type == 1) {
			if (StringUtils.isEmpty(curriculum_str))
				return ResultJson.crate1001Json();
			curriculumId = Integer.parseInt(curriculum_str);
			TbCurriculum tbCurriculum = baseDao.get(TbCurriculum.class,
					curriculumId);
			subject = tbCurriculum.getCourseName();
			tbCurriculumCopy = new TbCurriculumCopy(tbCurriculum);
			if (schoolId == null) {
				schoolId = tbCurriculum.getTbSchool().getSchoolId();
			}
		} else {
			if (StringUtils.isEmpty(team_str))
				return ResultJson.crate1001Json();
			teamId = Integer.parseInt(team_str);
			tbTeam = baseDao.get(TbTeam.class, teamId);
			subject = tbTeam.getTitle();
			tbTeamCopy = new TbTeamCopy(tbTeam);
			if (schoolId == null) {
				schoolId = tbTeam.getSchoolId();
			}
		}
		if (StringUtils.isNotEmpty(quantity_str)) {
			quantity = Integer.parseInt(quantity_str);
		}
		if (StringUtils.isNotEmpty(origin_str)) {
			origin = Double.valueOf(origin_str);
			price = origin / quantity;
		} else {
			return ResultJson.crate1001Json();
		}
		String out_trade_no = UtilDate.getOutTradeNo();
		TbOrder tbOrder = new TbOrder(1, "unpay", userinfoId, null, null,
				schoolId, branchSchool_Id, out_trade_no, null, curriculumId,
				quantity, teamId, price, origin, type, null, null, 1, time,
				time);
		Boolean boo = null;
		if (tbTeamCopy == null || tbTeamCopy.getIsValid() == null)
			boo = new TransactionDao().addOrder(tbOrder, tbCurriculumCopy);
		else
			boo = new TransactionDao().addOrder(tbOrder, tbTeamCopy);
		HashMap<String, Object> data = new HashMap<String, Object>(1);
		String signData = AlipayCore.createLinkString2(UtilDate.createMap(
				subject, origin_str, "校掌支付宝收款", out_trade_no));
		String sign = RSA.sign(signData, AlipayConfig.private_key,
				AlipayConfig._input_charset);
		// String payInfo=new
		// StringBuffer(signData).append("&sign=\"").append(sign).append("\"").append("&sign_type=\"").append(AlipayConfig.sign_type).append("\"").toString();
		try {
			data.put("signData", URLEncoder.encode(signData, "utf-8"));
			data.put("orderSign", URLEncoder.encode(sign, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			loger.error(e);
		}
		return boo ? ResultJson.crateSuccJson(data) : ResultJson
				.createFailJson(1004, "添加失败");
	}

	/**
	 * @param branchschoolId
	 *            分校ID
	 * @param childLongitude
	 * @param childLatitude
	 * @param range
	 * @param title
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultJson mapShow(String branchschoolId, String childLongitude,
			String childLatitude, String range, String title) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<TbBranchschoolWeb> listSchool = new ArrayList<TbBranchschoolWeb>();
		BaseDao baseDao = new BaseDao();
		Set<Integer> schoolIdsSet = new HashSet<Integer>();
		Set<Integer> branschschoolIdsSet = new HashSet<Integer>();
		Integer branschschool_id = null;
		TbBranchschool tbBranchschool01 = null;
		if (StringUtils.isNotEmpty(branchschoolId)
				&& !"0".equals(branchschoolId)) {
			branschschool_id = Integer.parseInt(branchschoolId);
			tbBranchschool01 = new TbBranchschool();
			tbBranchschool01.setBranchSchoolId(branschschool_id);
		}
		if (StringUtils.isNotEmpty(title)) {
			DetachedCriteria subdc = DetachedCriteria.forClass(TbSubject.class);
			subdc.add(Restrictions.like("subjectName", title,
					MatchMode.ANYWHERE));
			ArrayList<TbSubject> subjects = baseDao.dCList(TbSubject.class,
					subdc);
			DetachedCriteria curdc = DetachedCriteria
					.forClass(TbCurriculum.class);
			if (subjects != null && !subjects.isEmpty())
				curdc.add(Restrictions.or(Restrictions.like("courseName",
						title, MatchMode.ANYWHERE), Restrictions.in(
						"tbSubject", subjects)));
			else
				curdc.add(Restrictions.like("courseName", title,
						MatchMode.ANYWHERE));
			if (tbBranchschool01 != null) {
				curdc.add(Restrictions.eq("tbBranchschool", tbBranchschool01));
			}
			ArrayList<TbCurriculum> tbCurriculums = baseDao.dCList(
					TbCurriculum.class, curdc);
			if (tbCurriculums != null && !tbCurriculums.isEmpty()) {
				for (TbCurriculum tbCurriculum : tbCurriculums) {
					if (tbCurriculum.getTbSchool() != null
							&& tbCurriculum.getTbSchool().getSchoolId() != null)
						schoolIdsSet.add(tbCurriculum.getTbSchool()
								.getSchoolId());
					if (tbCurriculum.getTbBranchschool() != null
							&& tbCurriculum.getTbBranchschool()
									.getBranchSchoolId() != null)
						branschschoolIdsSet.add(tbCurriculum
								.getTbBranchschool().getBranchSchoolId());
				}
			}
		}
		if (tbBranchschool01 != null) {
			TbBranchschool tbBranchschool = baseDao.get(TbBranchschool.class,
					Integer.parseInt(branchschoolId));
			TbSchool tbSchool = tbBranchschool.getTbSchool();
			if (tbSchool != null && tbSchool.getSchoolId() != null) {
				DetachedCriteria bradc = DetachedCriteria
						.forClass(TbBranchschool.class);
				bradc.add(Restrictions.eq("tbSchool", tbSchool));
				if (StringUtils.isNotEmpty(title)) {
					TbSchool tbSchool2 = baseDao.get(TbSchool.class,
							tbSchool.getSchoolId());
					if (StringUtils.contains(tbSchool2.getSchoolName(), title))
						schoolIdsSet.add(tbSchool.getSchoolId());
					bradc.add(Restrictions.like("braschName", title,
							MatchMode.ANYWHERE));
				} else {
					schoolIdsSet.add(tbSchool.getSchoolId());
				}
				ArrayList<TbBranchschool> tbBranchschools = baseDao.dCList(
						TbBranchschool.class, bradc);//
				for (TbBranchschool tbBranchschool2 : tbBranchschools) {
					if (tbBranchschool2.getBranchSchoolId() != null)
						branschschoolIdsSet.add(tbBranchschool2
								.getBranchSchoolId());//
				}
			}
		} else {
			DetachedCriteria schdc = DetachedCriteria.forClass(TbSchool.class);
			if (StringUtils.isNotEmpty(title))
				schdc.add(Restrictions.like("schoolName", title,
						MatchMode.ANYWHERE));
			ArrayList<TbSchool> schools = baseDao.dCList(TbSchool.class, schdc);//
			for (TbSchool tbSchool : schools) {
				if (tbSchool.getSchoolId() != null)
					schoolIdsSet.add(tbSchool.getSchoolId());//
			}
			DetachedCriteria braDc = DetachedCriteria
					.forClass(TbBranchschool.class);//
			if (StringUtils.isNotEmpty(title))
				braDc.add(Restrictions.like("braschName", title,
						MatchMode.ANYWHERE));
			ArrayList<TbBranchschool> tbBranchschools = baseDao.dCList(
					TbBranchschool.class, braDc);//
			for (TbBranchschool tbBranchschool : tbBranchschools) {
				if (tbBranchschool.getBranchSchoolId() != null)
					branschschoolIdsSet.add(tbBranchschool.getBranchSchoolId());
			}
		}
		StringBuffer sql = new StringBuffer(
				"select s.schoolId,s.schoolName,s.schoolMinName,s.longitude,s.latitude,s2.type,s2.showType from tb_school s  left JOIN tb_school02 s2  on s.schoolId=s2.schoolId ");
		List<Object> paramList = new ArrayList<Object>();
		List<?> list1 = null;// ( s.schoolId not in (select distinct r.schoolId
								// from tb_branchschool r) ) and
		if (!schoolIdsSet.isEmpty()) {// s2.quantity=0 and
			sql.append("where  (  s.schoolId not in (select distinct r.schoolId from tb_branchschool r) ) and  s.schoolId in(");
			for (Iterator iterator = schoolIdsSet.iterator(); iterator
					.hasNext();) {
				Integer school_id = (Integer) iterator.next();
				sql.append("?,");
				paramList.add(school_id);
			}
			sql.delete(sql.length() - 1, sql.length());
			sql.append(")");
			list1 = baseDao.sqlList(sql.toString(), paramList);
		} 
		
		TbBranchschoolWeb tbBranchschoolWeb = null;
		if (list1 != null && !list1.isEmpty()) {
			for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				tbBranchschoolWeb = new TbBranchschoolWeb();
				tbBranchschoolWeb.setBranchschoolId((Integer) objects[0]);
				tbBranchschoolWeb.setBranchschoolName(objects[1] == null ? ""
						: (String) objects[1]);
				tbBranchschoolWeb
						.setBranchschoolMinName(objects[2] == null ? ""
								: (String) objects[2]);
				tbBranchschoolWeb
						.setBranchschoolLongitude(objects[3] == null ? "0.0"
								: ((Double) objects[3]).toString());
				tbBranchschoolWeb
						.setBranchschoolLatitude(objects[4] == null ? "0.0"
								: ((Double) objects[4]).toString());
				tbBranchschoolWeb.setIsNot(1);
				tbBranchschoolWeb.setType(objects[5] == null ? "0"
						: (String) objects[5]);
				tbBranchschoolWeb.setShowType(objects[6] == null ? 0
						: (Integer) objects[6]);
				listSchool.add(tbBranchschoolWeb);
			}
		}
		StringBuffer sql2 = new StringBuffer(
				"select b.branchSchoolId,b.braschName,b.braschMinName,b.longitude,b.latitude,b2.type,b2.showType from tb_branchschool b LEFT JOIN tb_branchschool02 b2 on b.branchSchoolId=b2.branchschoolId ");
		List<Object> paramList2 = new ArrayList<Object>();
		List<?> list2 = null;
		if (!branschschoolIdsSet.isEmpty()) {
			sql2.append("where b.branchSchoolId in(");
			Integer branchschool_id = null;
			for (Iterator iterator = branschschoolIdsSet.iterator(); iterator
					.hasNext();) {
				branchschool_id = (Integer) iterator.next();
				sql2.append("?,");
				paramList2.add(branchschool_id);
			}
			sql2.delete(sql2.length() - 1, sql2.length());
			sql2.append(")");
			list2 = baseDao.sqlList(sql2.toString(), paramList2);
		}

		if (list2 != null && !list2.isEmpty()) {
			TbBranchschoolWeb tbBranchschoolWeb2 = null;
			for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				tbBranchschoolWeb2 = new TbBranchschoolWeb();
				tbBranchschoolWeb2.setBranchschoolId((Integer) objects[0]);
				tbBranchschoolWeb2.setBranchschoolName(objects[1] == null ? ""
						: (String) objects[1]);
				tbBranchschoolWeb2
						.setBranchschoolMinName(objects[2] == null ? ""
								: (String) objects[2]);
				tbBranchschoolWeb2
						.setBranchschoolLongitude(objects[3] == null ? "0.0"
								: ((Double) objects[3]).toString());
				tbBranchschoolWeb2
						.setBranchschoolLatitude(objects[4] == null ? "0.0"
								: ((Double) objects[4]).toString());
				tbBranchschoolWeb2.setIsNot(0);
				tbBranchschoolWeb2.setType(objects[5] == null ? "0"
						: (String) objects[5]);
				tbBranchschoolWeb2.setShowType(objects[6] == null ? 0
						: (Integer) objects[6]);
				listSchool.add(tbBranchschoolWeb2);
			}
		}
		Set<String[]> set = new HashSet<String[]>();
		for (TbBranchschoolWeb tbBranchschoolWeb2 : listSchool) {
			String[] strs = new String[2];
			strs[0] = tbBranchschoolWeb2.getBranchschoolLongitude();
			strs[1] = tbBranchschoolWeb2.getBranchschoolLatitude();
			Boolean boo = false;
			if (set.isEmpty()) {
				if (StringUtils.isNotEmpty(strs[0])
						&& StringUtils.isNotEmpty(strs[1])
						&& specificRange01(Double.valueOf(childLongitude),
								Double.valueOf(childLatitude),
								Double.valueOf(strs[0]),
								Double.valueOf(strs[1]), Double.valueOf(range)))
					set.add(strs);
			} else {
				for (String[] str : set) {
					if (str[0].equals(strs[0]) && str[1].equals(strs[1])) {
						boo = true;
					}
				}
				if (!boo) {
					if (StringUtils.isNotEmpty(strs[0])
							&& StringUtils.isNotEmpty(strs[1])
							&& specificRange01(Double.valueOf(childLongitude),
									Double.valueOf(childLatitude),
									Double.valueOf(strs[0]),
									Double.valueOf(strs[1]),
									Double.valueOf(range)))
						set.add(strs);
				}
			}
		}
		List list = new ArrayList();
		for (String[] strs : set) {
			List<TbBranchschoolWeb> branchschoolWebs = new ArrayList<TbBranchschoolWeb>();
			for (TbBranchschoolWeb tbBranchschoolWeb3 : listSchool) {
				if (strs[0].equals(tbBranchschoolWeb3
						.getBranchschoolLongitude())
						&& strs[1].equals(tbBranchschoolWeb3
								.getBranchschoolLatitude()))
					branchschoolWebs.add(tbBranchschoolWeb3);
			}
			list.add(branchschoolWebs);
		}
		data.put("list", list);
		return ResultJson.crateSuccJson(data);
	}

	// public ResultJson mapShow(String branchschoolId, String childLongitude,
	// String childLatitude, String range, String title) {
	// Map<String, Object> data = new HashMap<String, Object>();
	// List<TbBranchschoolWeb> listSchool = new ArrayList<TbBranchschoolWeb>();
	// BaseDao baseDao = new BaseDao();
	// Set<Integer> schoolIdsSet = new HashSet<Integer>();// 总校
	// Set<Integer> branschschoolIdsSet = new HashSet<Integer>();// 分校
	// Integer branschschool_id = null;
	// TbBranchschool tbBranchschool01 = null;
	// if (StringUtils.isNotEmpty(branchschoolId)
	// && !"0".equals(branchschoolId)) {// 分校ID不为空
	// branschschool_id = Integer.parseInt(branchschoolId);// id转换int
	// tbBranchschool01 = new TbBranchschool();
	// tbBranchschool01.setBranchSchoolId(branschschool_id);// id给分校
	// }
	// if (StringUtils.isNotEmpty(title)) {// title不为空
	// DetachedCriteria subdc = DetachedCriteria.forClass(TbSubject.class);//
	// 查找TbSubject
	// subdc.add(Restrictions.like("subjectName", title,
	// MatchMode.ANYWHERE));// subjectName
	// ArrayList<TbSubject> subjects = baseDao.dCList(TbSubject.class,
	// subdc);//
	// DetachedCriteria curdc = DetachedCriteria
	// .forClass(TbCurriculum.class);// 查询TbCurriculum.class
	// if (subjects != null && !subjects.isEmpty()) {
	// curdc.add(Restrictions.or(Restrictions.like("courseName",
	// title, MatchMode.ANYWHERE), Restrictions.in(
	// "tbSubject", subjects)));// courseName
	// } else {
	// curdc.add(Restrictions.like("courseName", title,
	// MatchMode.ANYWHERE));
	// }
	// if (tbBranchschool01 != null) {
	// curdc.add(Restrictions.eq("tbBranchschool", tbBranchschool01));//
	// }
	// ArrayList<TbCurriculum> tbCurriculums = baseDao.dCList(
	// TbCurriculum.class, curdc);// tbCurriculums
	// if (tbCurriculums != null && !tbCurriculums.isEmpty()) {
	// for (TbCurriculum tbCurriculum : tbCurriculums) {
	// if (tbCurriculum.getTbSchool() != null
	// && tbCurriculum.getTbSchool().getSchoolId() != null
	// && tbCurriculum.getTbBranchschool() != null
	// && tbCurriculum.getTbBranchschool()
	// .getBranchSchoolId() != null) {
	// schoolIdsSet.add(tbCurriculum.getTbSchool()
	// .getSchoolId());
	// branschschoolIdsSet.add(tbCurriculum
	// .getTbBranchschool().getBranchSchoolId());
	//
	// }
	// if (tbCurriculum.getTbSchool() != null
	// && tbCurriculum.getTbSchool().getSchoolId() != null
	// && ((tbCurriculum.getTbBranchschool() == null
	// && tbCurriculum.getTbBranchschool()
	// .getBranchSchoolId() == null && ((tbCurriculum
	// .getTbBranchschool().equals("") && tbCurriculum
	// .getTbBranchschool().getBranchSchoolId()
	// .equals("")))))) {
	// branschschoolIdsSet.add(tbCurriculum
	// .getTbBranchschool().getBranchSchoolId());
	// }
	// if (tbCurriculum.getTbBranchschool() != null
	// && tbCurriculum.getTbBranchschool()
	// .getBranchSchoolId() != null)
	// branschschoolIdsSet.add(tbCurriculum
	// .getTbBranchschool().getBranchSchoolId());
	// }
	// }
	// }
	// if (tbBranchschool01 != null) {
	// TbBranchschool tbBranchschool = baseDao.get(TbBranchschool.class,
	// Integer.parseInt(branchschoolId));
	//
	// TbSchool tbSchool = tbBranchschool.getTbSchool();
	//
	// if (tbSchool != null && tbSchool.getSchoolId() != null) {
	// DetachedCriteria bradc = DetachedCriteria
	// .forClass(TbBranchschool.class);
	// bradc.add(Restrictions.eq("tbSchool", tbSchool));
	//
	// if (StringUtils.isNotEmpty(title)) {
	// TbSchool tbSchool2 = baseDao.get(TbSchool.class,
	// tbSchool.getSchoolId());// ?
	// if (StringUtils.contains(tbSchool2.getSchoolName(), title))
	// schoolIdsSet.add(tbSchool.getSchoolId());
	// bradc.add(Restrictions.like("braschName", title,
	// MatchMode.ANYWHERE));
	// } else {
	// schoolIdsSet.add(tbSchool.getSchoolId());
	// }
	// ArrayList<TbBranchschool> tbBranchschools = baseDao.dCList(
	// TbBranchschool.class, bradc);// tbBranchschools
	// for (TbBranchschool tbBranchschool2 : tbBranchschools) {
	// if (tbBranchschool2.getBranchSchoolId() != null)
	// branschschoolIdsSet.add(tbBranchschool2
	// .getBranchSchoolId());
	// }
	// }
	// } else {
	// DetachedCriteria schdc = DetachedCriteria.forClass(TbSchool.class);
	// if (StringUtils.isNotEmpty(title))
	// schdc.add(Restrictions.like("schoolName", title,
	// MatchMode.ANYWHERE));
	// ArrayList<TbSchool> schools = baseDao.dCList(TbSchool.class, schdc);
	// for (TbSchool tbSchool : schools) {
	// if (tbSchool.getSchoolId() != null) {
	// schoolIdsSet.add(tbSchool.getSchoolId());
	// }
	// }
	// DetachedCriteria braDc = DetachedCriteria
	// .forClass(TbBranchschool.class);
	// if (StringUtils.isNotEmpty(title))
	// braDc.add(Restrictions.like("braschName", title,
	// MatchMode.ANYWHERE));
	// ArrayList<TbBranchschool> tbBranchschools = baseDao.dCList(
	// TbBranchschool.class, braDc);
	// for (TbBranchschool tbBranchschool : tbBranchschools) {
	// if (tbBranchschool.getBranchSchoolId() != null)
	// branschschoolIdsSet.add(tbBranchschool.getBranchSchoolId());
	// }
	// }
	// StringBuffer sql = new StringBuffer(
	// "select s.schoolId,s.schoolName,s.schoolMinName,s.longitude,s.latitude,s2.type,s2.showType from tb_school s left JOIN tb_school02 s2 on s.schoolId=s2.schoolId ");
	// List<Object> paramList = new ArrayList<Object>();
	// List<?> list1 = null;
	// if (!schoolIdsSet.isEmpty()) {//s.schoolId not in (select distinct
	// r.schoolId from tb_branchschool r) and
	// sql.append("where s.schoolId not in (select distinct r.schoolId from tb_branchschool r) and  s.schoolId in(");
	// for (Iterator iterator = schoolIdsSet.iterator(); iterator
	// .hasNext();) {
	// Integer school_id = (Integer) iterator.next();
	// sql.append("?,");
	// paramList.add(school_id);
	// }
	// sql.delete(sql.length() - 1, sql.length());
	// sql.append(")");
	//
	// list1 = baseDao.sqlList(sql.toString(), paramList);
	// }
	// TbBranchschoolWeb tbBranchschoolWeb = null;
	// if (list1 != null && !list1.isEmpty()) {//
	// branschschoolIdsSet.isEmpty()加上后没有分校的总校没了
	// for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
	// Object[] objects = (Object[]) iterator.next();
	// tbBranchschoolWeb = new TbBranchschoolWeb();
	// tbBranchschoolWeb.setBranchschoolId((Integer) objects[0]);
	// tbBranchschoolWeb.setBranchschoolName(objects[1] == null ? ""
	// : (String) objects[1]);
	// tbBranchschoolWeb
	// .setBranchschoolMinName(objects[2] == null ? ""
	// : (String) objects[2]);
	// tbBranchschoolWeb
	// .setBranchschoolLongitude(objects[3] == null ? "0.0"
	// : ((Double) objects[3]).toString());
	// tbBranchschoolWeb
	// .setBranchschoolLatitude(objects[4] == null ? "0.0"
	// : ((Double) objects[4]).toString());
	// tbBranchschoolWeb.setIsNot(1);
	// tbBranchschoolWeb.setType(objects[5] == null ? "0"
	// : (String) objects[5]);
	// tbBranchschoolWeb.setShowType(objects[6] == null ? 0
	// : (Integer) objects[6]);
	// listSchool.add(tbBranchschoolWeb);
	// }
	// }
	// StringBuffer sql2 = new StringBuffer(
	// "select b.branchSchoolId,b.braschName,b.braschMinName,b.longitude,b.latitude,b2.type,b2.showType from tb_branchschool b LEFT JOIN tb_branchschool02 b2 on b.branchSchoolId=b2.branchschoolId ");
	// List<Object> paramList2 = new ArrayList<Object>();
	// List<?> list2 = null;
	// if (!branschschoolIdsSet.isEmpty()) {
	// sql2.append("where b.branchSchoolId in(");
	// Integer branchschool_id = null;
	// for (Iterator iterator = branschschoolIdsSet.iterator(); iterator
	// .hasNext();) {
	// branchschool_id = (Integer) iterator.next();
	// sql2.append("?,");
	// paramList2.add(branchschool_id);
	// }
	// sql2.delete(sql2.length() - 1, sql2.length());
	// sql2.append(")");
	// list2 = baseDao.sqlList(sql2.toString(), paramList2);
	// }
	//
	// if (list2 != null && !list2.isEmpty()) {
	// TbBranchschoolWeb tbBranchschoolWeb2 = null;
	// for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
	// Object[] objects = (Object[]) iterator.next();
	// tbBranchschoolWeb2 = new TbBranchschoolWeb();
	// tbBranchschoolWeb2.setBranchschoolId((Integer) objects[0]);
	// tbBranchschoolWeb2.setBranchschoolName(objects[1] == null ? ""
	// : (String) objects[1]);
	// tbBranchschoolWeb2
	// .setBranchschoolMinName(objects[2] == null ? ""
	// : (String) objects[2]);
	// tbBranchschoolWeb2
	// .setBranchschoolLongitude(objects[3] == null ? "0.0"
	// : ((Double) objects[3]).toString());
	// tbBranchschoolWeb2
	// .setBranchschoolLatitude(objects[4] == null ? "0.0"
	// : ((Double) objects[4]).toString());
	// tbBranchschoolWeb2.setIsNot(0);
	// tbBranchschoolWeb2.setType(objects[5] == null ? "0"
	// : (String) objects[5]);
	// tbBranchschoolWeb2.setShowType(objects[6] == null ? 0
	// : (Integer) objects[6]);
	// listSchool.add(tbBranchschoolWeb2);
	// }
	// }
	// Set<String[]> set = new HashSet<String[]>();
	// for (TbBranchschoolWeb tbBranchschoolWeb2 : listSchool) {
	// String[] strs = new String[2];
	// strs[0] = tbBranchschoolWeb2.getBranchschoolLongitude();
	// strs[1] = tbBranchschoolWeb2.getBranchschoolLatitude();
	// Boolean boo = false;
	// if (set.isEmpty()) {
	// if (StringUtils.isNotEmpty(strs[0])
	// && StringUtils.isNotEmpty(strs[1])
	// && specificRange01(Double.valueOf(childLongitude),
	// Double.valueOf(childLatitude),
	// Double.valueOf(strs[0]),
	// Double.valueOf(strs[1]), Double.valueOf(range)))
	// set.add(strs);
	// } else {
	// for (String[] str : set) {
	// if (str[0].equals(strs[0]) && str[1].equals(strs[1])) {
	// boo = true;
	// }
	// }
	// if (!boo) {
	// if (StringUtils.isNotEmpty(strs[0])
	// && StringUtils.isNotEmpty(strs[1])
	// && specificRange01(Double.valueOf(childLongitude),
	// Double.valueOf(childLatitude),
	// Double.valueOf(strs[0]),
	// Double.valueOf(strs[1]),
	// Double.valueOf(range)))
	// set.add(strs);
	// }
	// }
	// }
	// List list = new ArrayList();
	// for (String[] strs : set) {
	// List<TbBranchschoolWeb> branchschoolWebs = new
	// ArrayList<TbBranchschoolWeb>();
	// for (TbBranchschoolWeb tbBranchschoolWeb3 : listSchool) {
	// if (strs[0].equals(tbBranchschoolWeb3
	// .getBranchschoolLongitude())
	// && strs[1].equals(tbBranchschoolWeb3
	// .getBranchschoolLatitude()))
	// branchschoolWebs.add(tbBranchschoolWeb3);
	// }
	// list.add(branchschoolWebs);
	// }
	// data.put("list", list);
	// return ResultJson.crateSuccJson(data);
	// }

	private Boolean specificRange01(Double lon1, Double lat1, Double lon2,
			Double lat2, Double range) {
		range = range >= 1500.0 ? range : 1500.0;
		return LalDistance.getShortDistance(lon1, lat1, lon2, lat2) >= range ? false
				: true;
	}

	/**
	 * 退款接口
	 * 
	 * @param orderId
	 * @return
	 */
	public ResultJson chargeback(String orderId) {
		BaseDao baseDao = new BaseDao();
		TbOrder order = baseDao.get(TbOrder.class, Integer.parseInt(orderId));
		DetachedCriteria coudc = DetachedCriteria.forClass(TbCoupon.class);
		coudc.add(Restrictions.eq("orderId", order.getId()));
		ArrayList<TbCoupon> tbCoupons = baseDao.dCList(TbCoupon.class, coudc);
		if (tbCoupons == null || tbCoupons.isEmpty())
			return ResultJson.crateNullJson();
		TbCoupon tbCoupon = tbCoupons.get(0);
		tbCoupon.setConsume(2);
		boolean boo = baseDao.updateT(tbCoupon, tbCoupon.getId());
		return boo ? ResultJson.crateSuccJson(new HashMap<String, Object>(0))
				: ResultJson.createFailJson(1005, "修改失败");
	}

	/**
	 * 家长定位
	 * 
	 * @param userinfoId
	 *            家长Id
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @return
	 */
	public ResultJson parentsPositioning(String userinfoId, String longitude,
			String latitude) {
		BaseDao baseDao = new BaseDao();
		if (StringUtils.isEmpty(userinfoId) || StringUtils.isEmpty(longitude)
				|| StringUtils.isEmpty(latitude))
			return ResultJson.crate1001Json();
		Integer userinfo_id = Integer.parseInt(userinfoId);
		DetachedCriteria use2dc = DetachedCriteria.forClass(TbUserinfo2.class);
		use2dc.add(Restrictions.eq("userinfoId", userinfo_id));
		ArrayList<TbUserinfo2> tbUserinfo2s = baseDao.dCList(TbUserinfo2.class,
				use2dc);
		TbUserinfo2 parent = null;
		if (tbUserinfo2s == null || tbUserinfo2s.isEmpty()) {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			parent = new TbUserinfo2(null, userinfo_id, null, null, 1, null,
					time, time, Double.valueOf(latitude),
					Double.valueOf(longitude));
			Integer id = (Integer) baseDao.save(parent);
			parent.setId(id);
		} else
			parent = tbUserinfo2s.get(0);
		if (parent.getStudentId() == null) {
			DetachedCriteria reldc = DetachedCriteria
					.forClass(TbRelation.class);
			TbUserinfo tbUserinfo = new TbUserinfo();
			tbUserinfo.setUserInfoId(userinfo_id);
			reldc.add(Restrictions.eq("tbUserinfoByTbUserInfoId", tbUserinfo));
			ArrayList<TbRelation> relations = baseDao.dCList(TbRelation.class,
					reldc);
			if (relations != null && !relations.isEmpty()) {
				TbRelation tbRelation = relations.get(0);
				if (tbRelation.getTbUserinfoByUserInfoId() != null)
					parent.setStudentId(tbRelation.getTbUserinfoByUserInfoId()
							.getUserInfoId());
			}
		}
		parent.setLongitude(Double.valueOf(longitude));
		parent.setLatitude(Double.valueOf(latitude));
		return baseDao.updateT(parent, parent.getId()) ? ResultJson
				.crateSuccJson(new HashMap<String, Object>(0)) : ResultJson
				.createFailJson(1005, "修改失败");
	}

	/**
	 * @param schoolId
	 *            学校ID
	 * @param type
	 *            0-分校，1-总校
	 * @param pusherName
	 *            推广名称
	 * @param fileNames
	 *            图片路径
	 * @return
	 */
	public ResultJson uploadingSchoolPhotoCopy(String schoolId,
			String pusherName, String[] fileNames) {
		if (fileNames == null || fileNames.length == 0
				|| StringUtils.isEmpty(schoolId)
				|| StringUtils.isEmpty(pusherName))
			return ResultJson.crate1001Json();
		TbSchoolPhotoCopy[] tbSchoolPhotos = new TbSchoolPhotoCopy[fileNames.length];
		Timestamp time = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < fileNames.length; i++) {
			if (fileNames[i] != null)
				tbSchoolPhotos[i] = new TbSchoolPhotoCopy(
						Integer.parseInt(schoolId), null, fileNames[i], null,
						pusherName, 1, time, time);
		}
		Serializable[] serializables = new BaseDao().save(tbSchoolPhotos);
		return serializables != null && serializables.length > 0 ? ResultJson
				.crateSuccJson(new HashMap<String, Object>(0)) : ResultJson
				.createFailJson(1004, "添加失败");
	}

	/**
	 * 第一次登录安卓端接口
	 * 
	 * @param userName
	 *            账户
	 * @param userPassword
	 *            密码
	 * @param iMEI
	 *            孩子上传定位以最后一位登录用户为准，所以每次登录都覆盖下关系表
	 * @return
	 */
	public ResultJson firstLogin(String userName, String userPassword,
			String iMEI) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)
				|| StringUtils.isEmpty(iMEI))
			return ResultJson.crate1001Json();
		BaseDao baseDao = new BaseDao();
		DetachedCriteria dc = DetachedCriteria.forClass(TbUser.class);
		dc.add(Restrictions.eq("userName", userName));
		dc.add(Restrictions.eq("userPassword", userPassword));
		ArrayList<TbUser> tbUsers = baseDao.dCList(TbUser.class, dc);
		if (tbUsers == null || tbUsers.isEmpty())
			return ResultJson.createFailJson(1002, "帐户或密码错误");
		else {
			Map<String, Object> data = new HashMap<String, Object>(3);
			TbUser tbUser = tbUsers.get(0);
			Integer id = tbUser.getTbUserinfo().getUserInfoId();
			TbUserinfo tbUserinfo = baseDao.get(TbUserinfo.class, id);
			DetachedCriteria relaDc = DetachedCriteria
					.forClass(TbRelation.class);
			if (tbUserinfo.getUserInfoRoot() == 2)
				relaDc.add(Restrictions.eq("tbUserinfoByTbUserInfoId",
						tbUserinfo));
			else
				relaDc.add(Restrictions
						.eq("tbUserinfoByUserInfoId", tbUserinfo));
			relaDc.add(Restrictions.eq("isValid", 1));
			ArrayList<TbRelation> relations = baseDao.dCList(TbRelation.class,
					relaDc);
			if (relations != null && !relations.isEmpty()) {
				if (tbUserinfo.getUserInfoRoot() == 3) {
					TbRelation tbRelation = relations.get(0);
					tbRelation.setPhoneIMEI(iMEI);
					boolean boo = baseDao.updateT(tbRelation,
							tbRelation.getRelationId());
					if (boo)
						data.put("isIMEI", 1);
					else
						data.put("isIMEI", 0);
				} else
					data.put("isIMEI", 0);
				data.put("isParent", 1);
			} else {
				data.put("isIMEI", 0);
				data.put("isParent", 0);
			}
			data.put("userinfoId", id);
			data.put("isValid", tbUser.getIsValid());
			data.put("root", tbUserinfo.getUserInfoRoot());
			return ResultJson.crateSuccJson(data);
		}
	}
}
