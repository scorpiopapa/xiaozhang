package com.qiYang.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.qiYang.action.GogoServletImpl;
import com.qiYang.dao.BaseDao;
import com.qiYang.dao.TransactionDao;
import com.qiYang.model.Ofuser;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbAdvertisement;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbBranchschool02;
import com.qiYang.model.TbBranchschoolPopularity;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbCoupon;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbCurriculum02;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbPush;
import com.qiYang.model.TbPushRecord;
import com.qiYang.model.TbPusher;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchool02;
import com.qiYang.model.TbSchoolApplication;
import com.qiYang.model.TbSchoolPhoto;
import com.qiYang.model.TbSchoolPhotoCopy;
import com.qiYang.model.TbSchoolPopularity;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbTeam;
import com.qiYang.model.TbTeamCopy;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.web.TbBranchschoolWeb;
import com.qiYang.model.web.TbCurriculumWeb;
import com.qiYang.model.web.TbSchoolWeb;
import com.qiYang.model.web.TbUserinfoWeb;
import com.qiYang.model.web.TbUserinfoclassWeb;
import com.qiYang.util.JpushThread;
import com.qiYang.util.LalDistance;
import com.qiYang.util.Page;
import com.qiYang.util.ResultJson;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObjectUtil;
import com.qiYang.util.TWPictureUtil;

public class SecondBackstage {
//	public static void main(String[] args) {
//		System.out.println(new SecondBackstage().findPageTbPusher(new Page()));
//	}

	/**
	 * 优惠分页列表
	 * 
	 * @param schoolId
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findTeamSchoolPage(Integer schoolId, Page page) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria teamSchoolDc = DetachedCriteria.forClass(TbTeam.class);
		teamSchoolDc.add(Restrictions.eq("schoolId", schoolId));
		teamSchoolDc.addOrder(Order.desc("id"));
		page = baseDao.dCPage(page, teamSchoolDc);
		List list = page.getCurrentList();
		TbTeam tbTeam = null;
		for (Object object : list) {
			if (object instanceof TbTeam) {
				tbTeam = (TbTeam) object;
				tbTeam.setDetail(StringUtils.abbreviate(tbTeam.getDetail(), 15));
				tbTeam.setImgurl(StringUtils.abbreviate(
						TWObjectUtil.getString(tbTeam.getImgurl()), 10));
			}
		}
		page.setCurrentList(list);
		return page;
	}

	/**
	 * 消费单表分页列表
	 * 
	 * @param schoolId
	 *            总校ID
	 * @param couponId
	 *            消费Id
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findOrderSchoolPage(Integer schoolId, String couponId, Page page) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria orderSchoolDc = DetachedCriteria
				.forClass(TbCoupon.class);
		orderSchoolDc.add(Restrictions.eq("schoolId", schoolId));
		if (StringUtils.isNotEmpty(couponId))
			orderSchoolDc.add(Restrictions.like("id", couponId,
					MatchMode.ANYWHERE));
		orderSchoolDc.add(Restrictions.eq("isValid", 1));
		orderSchoolDc.addOrder(Order.asc("consume"));
		page = baseDao.dCPage(page, orderSchoolDc);
		List list = page.getCurrentList();
		TbCoupon tbCoupon=null;
		for (Object object : list) {
			if(object instanceof TbCoupon){
				tbCoupon=(TbCoupon)object;
				tbCoupon.setSecret(null);
			}
		}
		page.setCurrentList(list);
		return page;
	}

	/**
	 * 学校注册申请分页
	 * 
	 * @param page
	 * @return
	 */
	public Page findPageTbSchoolApplication(Page page) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria schoolApplicationDc = DetachedCriteria
				.forClass(TbSchoolApplication.class);
		schoolApplicationDc.add(Restrictions.eq("isValid", 1));
		schoolApplicationDc.addOrder(Order.desc("id"));
		page = baseDao.dCPage(page, schoolApplicationDc);
		return page;
	}

	/**
	 * 相册分页列表
	 * 
	 * @param schoolId
	 *            学校ID
	 * @param type
	 *            1-总校 ，2-分校
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findTbSchoolPhotoPage(Integer schoolId, Integer type, Page page) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria schoolPhotoDc = DetachedCriteria
				.forClass(TbSchoolPhoto.class);
		if (type == 2) {
			schoolPhotoDc.add(Restrictions.eq("branchschoolId", schoolId));
		} else {
			schoolPhotoDc.add(Restrictions.eq("schoolId", schoolId));
		}
		schoolPhotoDc.add(Restrictions.eq("isValid", 1));
		schoolPhotoDc.addOrder(Order.desc("id"));
		page = baseDao.dCPage(page, schoolPhotoDc);
		List list = page.getCurrentList();
		for (Object object : list) {
			if (object instanceof TbSchoolPhoto) {
				TbSchoolPhoto photo = (TbSchoolPhoto) object;
				photo.setPhotoPath(TWPictureUtil.getMinPicPath(photo
						.getPhotoPath()));
			}
		}
		page.setCurrentList(list);
		return page;
	}

	/**
	 * 广告分页
	 * 
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findTbAdvertisementPage(Page page) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria AdvertisementDc = DetachedCriteria
				.forClass(TbAdvertisement.class);
		AdvertisementDc.add(Restrictions.eq("isValid", 1));
		AdvertisementDc.addOrder(Order.desc("id"));
		page = baseDao.dCPage(page, AdvertisementDc);
		List list = page.getCurrentList();
		for (Object object : list) {
			if (object instanceof TbAdvertisement) {
				TbAdvertisement advertisement = (TbAdvertisement) object;
				advertisement.setUrl(TWPictureUtil
						.getNomalPicPath(advertisement.getUrl()));
			}
		}
		page.setCurrentList(list);
		return page;
	}

	/**
	 * 消费订单复制单详情
	 * 
	 * @param teamId
	 * @return
	 */
	public TbTeamCopy findTbTeamCopyDetail(Integer teamId) {
		BaseDao baseDao = new BaseDao();
		TbTeamCopy tbTeam = baseDao.get(TbTeamCopy.class, teamId);
		TbSchool tbSchool = baseDao.get(TbSchool.class, tbTeam.getSchoolId());
		tbTeam.setSchoolName(tbSchool.getSchoolName());
		return tbTeam;
	}

	/**
	 * 广告详情
	 * 
	 * @param advertisementId
	 * @return
	 */
	public TbAdvertisement findDetailTbAdvertisement(Integer advertisementId) {
		TbAdvertisement tbAdvertisement = new BaseDao().get(
				TbAdvertisement.class, advertisementId);
		tbAdvertisement.setUrl(TWPictureUtil.getMinPicPath(tbAdvertisement
				.getUrl()));
		return tbAdvertisement;
	}

	/**
	 * 消费订单详情
	 * 
	 * @param teamId
	 * @return
	 */
	public TbTeam findTbTeamDetail(Integer teamId) {
		BaseDao baseDao = new BaseDao();
		TbTeam tbTeam = baseDao.get(TbTeam.class, teamId);
		TbSchool tbSchool = baseDao.get(TbSchool.class, tbTeam.getSchoolId());
		tbTeam.setSchoolName(tbSchool.getSchoolName());
		return tbTeam;
	}
	public String findCoupon(String id,String secret){
		DetachedCriteria couDc=DetachedCriteria.forClass(TbCoupon.class);
		couDc.add(Restrictions.and(Restrictions.eq("id", id), Restrictions.eq("secret", secret)));
		return new BaseDao().dCRowCount(couDc)>0?"通过":"密码错误";
		
	}

	/**
	 * 查询待审学校详情
	 * 
	 * @param schoolApplicationId
	 * @return
	 */
	public TbSchoolApplication findDetailTbSchoolApplication(
			Integer schoolApplicationId) {
		return new BaseDao()
				.get(TbSchoolApplication.class, schoolApplicationId);
	}

	/**
	 * 报班复制单详情
	 * 
	 * @param orderId
	 * @return
	 */
	public TbCurriculumWeb findTbCurriculumCopyDetail(Integer orderId) {
		ResultJson resultJson = new SecondaryDevelopmen()
				.myTeamOrCurriculum(orderId.toString());
		Map<String, Object> map = resultJson.getData();
		if (map.isEmpty()) {
			return new TbCurriculumWeb();
		}
		TbCurriculumWeb tbCurriculumWeb = new TbCurriculumWeb();
		tbCurriculumWeb.setCouponId((String) map.get("couponId"));
		tbCurriculumWeb.setStudyCost((String) map.get("studyCosts"));
		tbCurriculumWeb.setTeamPrice((String) map.get("teamPrice"));
		tbCurriculumWeb.setCourseName((String) map.get("coursename"));
		tbCurriculumWeb.setPhone((String) map.get("phone"));
		tbCurriculumWeb.setBranchschoolName((String) map
				.get("branchSchoolName"));
		tbCurriculumWeb.setSchoolAddress((String) map.get("schoolAddress"));
		tbCurriculumWeb.setCourseIntroduce((String) map.get("courseIntroduce"));
		tbCurriculumWeb.setQuantity((Integer) map.get("quantity"));
		tbCurriculumWeb.setStartTerm((String) map.get("startTerm"));
		tbCurriculumWeb.setEndTerm((String) map.get("endTerm"));
		tbCurriculumWeb.setTotalStudyTime1((String) map.get("totalStudyTime"));
		tbCurriculumWeb.setEnterNeed((String) map.get("enterNeed"));
		tbCurriculumWeb.setDirection((String) map.get("direction"));
		tbCurriculumWeb.setStudyGoal((String) map.get("studyGoal"));
		tbCurriculumWeb.setCourseGoodness((String) map.get("courseGoodness"));
		tbCurriculumWeb.setStudyTime1((String) map.get("studyTime"));
		tbCurriculumWeb.setTextbookCost1((String) map.get("textbookCost"));
		tbCurriculumWeb.setCourseBook((String) map.get("courseBook"));
		return tbCurriculumWeb;
	}

	/**
	 * 修改优惠单
	 * 
	 * @param tbTeam
	 * @return
	 */
	public String updateTbTeam(TbTeam tbTeam) {
		tbTeam.setAlterTime(new Timestamp(System.currentTimeMillis()));
		tbTeam.setStatus(0);
		return updateResult(new BaseDao().updateT(tbTeam, tbTeam.getId()));
	}

	/**
	 * 待审学校通审
	 * 
	 * @param tbSchoolApplication
	 * @param adminId
	 * @return
	 */
	public String saveRegisterTbSchool(Integer schoolApplicationId,
			Integer adminId) {
		BaseDao baseDao = new BaseDao();
		Date date = new Date();
		TbSchoolApplication tbSchoolApplication = baseDao.get(
				TbSchoolApplication.class, schoolApplicationId);
		
		Timestamp nowTime = new Timestamp(date.getTime());
		String[] strings = LalDistance.getLal(tbSchoolApplication.getAddress(),
				tbSchoolApplication.getCityName());
		if (strings == null || StringUtils.isEmpty(strings[0])
				|| StringUtils.isEmpty(strings[1]))
			return "无法定位此学校经纬度，请核实地址跟城市！";
		DetachedCriteria cityDc = DetachedCriteria.forClass(TbCity.class);
		cityDc.add(Restrictions.like("cityName",
				tbSchoolApplication.getCityName(), MatchMode.ANYWHERE));
		ArrayList<TbCity> tbCities = baseDao.dCList(TbCity.class, cityDc);
		if (tbCities == null || tbCities.isEmpty())
			return "无法找到城市关联";
		DetachedCriteria towndc = DetachedCriteria.forClass(TbTown.class);
		towndc.add(Restrictions.in("tbCity", tbCities));
		ArrayList<TbTown> tbTowns = baseDao.dCList(TbTown.class, towndc);
		if (tbTowns == null || tbTowns.isEmpty())
			return "无法找到城区关联";
		TbSchool tbSchool = new TbSchool(tbTowns.get(0),
				tbSchoolApplication.getSchoolName(), StringUtils.substring(
						tbSchoolApplication.getSchoolName(), 0, 2), "",
				tbSchoolApplication.getAddress(), Double.valueOf(strings[0]),
				Double.valueOf(strings[1]), tbSchoolApplication.getPhone(), "",
				"", tbSchoolApplication.getUserName(),
				tbSchoolApplication.getEmail(), "",
				tbSchoolApplication.getName(), tbSchoolApplication.getEmail(),
				tbSchoolApplication.getUserName(), "",
				tbSchoolApplication.getName(), "1", "", "", "", "", "", "", "",
				null, 1, nowTime, nowTime);
		TbSchool02 tbSchool02 = new TbSchool02(null, "", 0, "0", 0, 1, nowTime,
				nowTime);
		TbUserinfo tbUserinfo = new TbUserinfo(tbSchool, null, "", "", "",
				tbSchoolApplication.getName(), "", date,
				tbSchoolApplication.getUserName(), "default.png", "", null,
				null, tbSchoolApplication.getEmail(), null, null, 4, 1,
				nowTime, nowTime);
		TbUser tbUser = new TbUser(
				tbUserinfo,
				tbUserinfo.getUserInfoPhone(),
				StringUtils.isEmpty(tbSchoolApplication.getPassword()) ? "000000"
						: tbSchoolApplication.getPassword(), 1, nowTime,
				nowTime);
		Ofuser ofuser = new Ofuser(tbUserinfo.getUserinfoQq(), "111111", null,
				tbUserinfo.getUserInfoName(), tbUserinfo.getUserInfoEmail(),
				Long.toString(nowTime.getTime()), "0");
		DetachedCriteria userdc = DetachedCriteria.forClass(TbUser.class);
		userdc.add(Restrictions.eq("userName", tbUserinfo.getUserInfoPhone()));
		Integer num = baseDao.dCRowCount(userdc);
		if (num > 0)
			return new StringBuffer(tbUserinfo.getUserInfoPhone()).append(
					"该账户已被注册").toString();
		DetachedCriteria copyDc = DetachedCriteria
				.forClass(TbSchoolPhotoCopy.class);
		copyDc.add(Restrictions.eq("schoolId", schoolApplicationId));
		ArrayList<TbSchoolPhotoCopy> tbSchoolPhotoCopies = baseDao.dCList(
				TbSchoolPhotoCopy.class, copyDc);
		TbSchoolPhoto[] tbSchoolPhotos = null;
		if (tbSchoolPhotoCopies != null && !tbSchoolPhotoCopies.isEmpty()) {
			tbSchoolPhotos = new TbSchoolPhoto[tbSchoolPhotoCopies.size()];
			TbSchoolPhoto tbSchoolPhoto = null;
			for (int i = 0; i < tbSchoolPhotoCopies.size(); i++) {
				tbSchoolPhoto = new TbSchoolPhoto(null, null,
						tbSchoolPhotoCopies.get(i).getPhotoPath(), adminId,
						tbSchoolPhotoCopies.get(i).getPusherName(), 1, nowTime,
						nowTime);
				tbSchoolPhotos[i] = tbSchoolPhoto;
			}
		}
		Boolean boo = new TransactionDao().saveRegisterTbSchool(tbSchool,
				tbSchool02, tbUserinfo, tbUser, ofuser, tbSchoolPhotos);
		if (!boo) {
			TbSchoolApplication schoolApplication = new TbSchoolApplication();
			schoolApplication.setId(schoolApplicationId);
			schoolApplication.setIsValid(0);
			boo = baseDao.updateT(schoolApplication, schoolApplicationId);
		}
		return boo ? "审批成功" : "审批失败";
	}

	/**
	 * 修改广告
	 * 
	 * @param tbAdvertisement
	 * @return
	 */
	public String updateTbAdvertisement(TbAdvertisement tbAdvertisement) {
		tbAdvertisement.setAlterTime(new Timestamp(System.currentTimeMillis()));
		return updateResult(new BaseDao().updateT(tbAdvertisement,
				tbAdvertisement.getId()));
	}

	/**
	 * 修改学校待审信息
	 * 
	 * @param tbSchoolApplication
	 * @return
	 */
	public String updateTbSchoolApplication(
			TbSchoolApplication tbSchoolApplication) {
		tbSchoolApplication.setAlterTime(new Timestamp(System
				.currentTimeMillis()));
		return updateResult(new BaseDao().updateT(tbSchoolApplication,
				tbSchoolApplication.getId()));
	}

	/**
	 * 优惠单送审
	 * 
	 * @param teamId
	 * @return
	 */
	public String updateTbTeamSend(Integer teamId) {
		TbTeam tbTeam = new TbTeam();
		tbTeam.setId(teamId);
		tbTeam.setAlterTime(new Timestamp(System.currentTimeMillis()));
		tbTeam.setStatus(1);
		boolean boo = new BaseDao().updateT(tbTeam, tbTeam.getId());
		return boo ? "送审成功!" : "送审失败!";
	}
	public String updateDownTbTeam(Integer teamId) {
		TbTeam tbTeam = new TbTeam();
		tbTeam.setId(teamId);
		tbTeam.setAlterTime(new Timestamp(System.currentTimeMillis()));
		tbTeam.setStatus(3);
		boolean boo = new BaseDao().updateT(tbTeam, tbTeam.getId());
		return boo ? "下架成功!" : "下架失败!";
	}

	/**
	 * 消费单现场报名
	 * 
	 * @param couponId
	 * @param adminId
	 * @return
	 */
	public String updateTbCouponApply(String couponId, Integer adminId) {
		TbCoupon tbCoupon = new TbCoupon();
		tbCoupon.setId(couponId);
		tbCoupon.setAdminId(adminId);
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		tbCoupon.setAlterTime(nowTime);
		tbCoupon.setConsumeTime(nowTime);
		tbCoupon.setConsume(1);
		boolean boo = new BaseDao().updateT(tbCoupon, tbCoupon.getId());
		return boo ? "报名成功!" : "报名失败!";
	}

	/**
	 * 优惠单上架审核
	 * 
	 * @param teamId
	 * @param adminId
	 * @return
	 */
	public String updateTbTeamPass(Integer teamId, Integer adminId) {
		TbTeam tbTeam = new TbTeam();
		tbTeam.setId(teamId);
		tbTeam.setAdminid(adminId);
		tbTeam.setAlterTime(new Timestamp(System.currentTimeMillis()));
		tbTeam.setStatus(2);
		boolean boo = new BaseDao().updateT(tbTeam, tbTeam.getId());
		return boo ? "上架成功!" : "上架失败!";
	}

	/**
	 * 优惠单驳回
	 * 
	 * @param tbTeam
	 * @return
	 */
	public String updateTbTeamBack(TbTeam tbTeam) {
		tbTeam.setAlterTime(new Timestamp(System.currentTimeMillis()));
		tbTeam.setStatus(0);
		boolean boo = new BaseDao().updateT(tbTeam, tbTeam.getId());
		return boo ? "拒回成功!" : "拒回失败!";
	}

	/**
	 * 新增优惠单
	 * 
	 * @param tbTeam
	 * @return
	 */
	public String saveTbTeam(TbTeam tbTeam) {
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		tbTeam.setTime(nowTime);
		tbTeam.setAlterTime(nowTime);
		tbTeam.setIsValid(1);
		tbTeam.setStatus(0);
		tbTeam.setNowNumber(0);
		return saveResult((Integer) (new BaseDao().save(tbTeam)));
	}

	public String saveResult(Integer id) {
		return id > 0 ? "添加成功!" : "添加失败!";
	}

	public String updateResult(Boolean boo) {
		return boo ? "修改成功!" : "修改失败!";
	}

	/**
	 * 总管理员查询送审优惠单
	 * 
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findTeamAdminPage(Page page) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria teamSchoolDc = DetachedCriteria.forClass(TbTeam.class);
		teamSchoolDc.add(Restrictions.eq("status", 1));
		teamSchoolDc.addOrder(Order.desc("id"));
		page = baseDao.dCPage(page, teamSchoolDc);
		List list = page.getCurrentList();
		if (list == null || list.isEmpty()) {
			list = new ArrayList(0);
			page.setCurrentList(list);
			return page;
		}
		Set<Integer> schools = new HashSet<Integer>();
		TbTeam tbTeam = null;
		for (Object object : list) {
			if (object instanceof TbTeam) {
				tbTeam = (TbTeam) object;
				schools.add(tbTeam.getSchoolId());
			}
		}
		DetachedCriteria schoolDc = DetachedCriteria.forClass(TbSchool.class);
		schoolDc.add(Restrictions.in("schoolId", schools));
		ArrayList<TbSchool> schoolList = baseDao.dCList(TbSchool.class,
				schoolDc);
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (TbSchool tbSchool : schoolList) {
			map.put(tbSchool.getSchoolId(), tbSchool.getSchoolName());
		}
		for (Object object : list) {
			if (object instanceof TbTeam) {
				tbTeam = (TbTeam) object;
				tbTeam.setSchoolName(map.get(tbTeam.getSchoolId()));
				tbTeam.setDetail(StringUtils.abbreviate(tbTeam.getDetail(), 20));
			}
		}
		page.setCurrentList(list);
		return page;
	}

	/**
	 * 学校相册上传
	 * 
	 * @param schoolId
	 * @param urls
	 * @param adminId
	 * @return
	 */
	public String schoolPhoto(String[] urls, String adminId) {
		BaseDao baseDao = new BaseDao();
		Integer admin_id = Integer.parseInt(adminId);
		TbAdmin tbAdmin = baseDao.get(TbAdmin.class, admin_id);
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		TbSchoolPhoto schoolPhoto = null;
		String url = null;
		TbSchoolPhoto[] tbSchoolPhotos = new TbSchoolPhoto[urls.length];
		for (int i = 0; i < urls.length; i++) {
			url = StringUtils.substringAfter(urls[i], "nomal/").replace("\r\n",
					"");
			if (tbAdmin.getAdminRoot() == 1) {
				schoolPhoto = new TbSchoolPhoto(tbAdmin.getTbSchool()
						.getSchoolId(), null, url, admin_id, null, 1, nowTime,
						nowTime);
			} else if (tbAdmin.getAdminRoot() == 2) {
				schoolPhoto = new TbSchoolPhoto(null, tbAdmin
						.getTbBranchschool().getBranchSchoolId(), url,
						admin_id, null, 1, nowTime, nowTime);
			}
			tbSchoolPhotos[i] = schoolPhoto;
		}
		baseDao.save(tbSchoolPhotos);
		return "/qiyangs/Pages/photoSchool.jsp";
	}

	/**
	 * 新增广告接口
	 * 
	 * @param hyperlink
	 *            超链接路径
	 * @param photoType
	 *            链接类型
	 * @param items
	 *            图片数据
	 * @param basePicUrl
	 *            根目录路径
	 * @return
	 */
	public String saveAdvertisement(String hyperlink, String photoType,
			List<FileItem> items, String basePicUrl) {
		String istrueResult = null;
		Long date1 = System.currentTimeMillis();
		Timestamp time = new Timestamp(date1);
		if (null != items)
			istrueResult = TWPictureUtil.getUploadedFile(basePicUrl, items,
					date1.toString());
		if (!"fail".equals(istrueResult)) {
			TbAdvertisement tbAdvertisement = new TbAdvertisement(istrueResult,
					hyperlink, Integer.parseInt(photoType), 1, time, time);
			new BaseDao().save(tbAdvertisement);
		}
		return "/qiyangs/Pages/advertisement.jsp";
	}

	/**
	 * 总校简介详情
	 * 
	 * @param schoolId
	 * @return
	 */
	public TbSchool02 findDetailTbSchool02(Integer schoolId) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria sch2Dc = DetachedCriteria.forClass(TbSchool02.class);
		sch2Dc.add(Restrictions.eq("schoolId", schoolId));
		ArrayList<TbSchool02> school02s = baseDao.dCList(TbSchool02.class,
				sch2Dc);
		TbSchool02 school2 = null;
		if (school02s == null || school02s.isEmpty()) {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			school2 = new TbSchool02(schoolId, "", 0, "0", 0, 1, time, time);
			Integer school_Id = (Integer) baseDao.save(school2);
			school2.setId(school_Id);
		} else {
			school2 = school02s.get(0);
		}
		return school2;
	}

	/**
	 * 分校简介详情
	 * 
	 * @param branchschoolId
	 * @return
	 */
	public TbBranchschool02 findDetailTbBranchschool02(Integer branchschoolId) {
		BaseDao baseDao = new BaseDao();
		DetachedCriteria sch2Dc = DetachedCriteria
				.forClass(TbBranchschool02.class);
		sch2Dc.add(Restrictions.eq("branchschoolId", branchschoolId));
		ArrayList<TbBranchschool02> datas = baseDao.dCList(
				TbBranchschool02.class, sch2Dc);
		TbBranchschool02 data2 = null;
		if (datas == null || datas.isEmpty()) {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			data2 = new TbBranchschool02(branchschoolId, "", 0, "0", 0, 1,
					time, time);
			Integer id = (Integer) baseDao.save(data2);
			data2.setId(id);
		} else {
			data2 = datas.get(0);
		}
		return data2;
	}

	/**
	 * 分校简介修改
	 * 
	 * @param branchschool02
	 * @return
	 */
	public String saveTbBranchschool02(TbBranchschool02 data02) {
		data02.setQuantity(null);
		data02.setShowType(null);
		data02.setType(null);
		data02.setAlterTime(new Timestamp(System.currentTimeMillis()));
		return updateResult(new BaseDao().updateT(data02, data02.getId()));
	}

	/**
	 * 总校简介修改
	 * 
	 * @param data02
	 * @return
	 */
	public String saveTbSchool02(TbSchool02 data02) {
		data02.setQuantity(null);
		data02.setShowType(null);
		data02.setType(null);
		data02.setAlterTime(new Timestamp(System.currentTimeMillis()));
		return updateResult(new BaseDao().updateT(data02, data02.getId()));
	}

	/**
	 * 分页班校掌优惠价
	 * 
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findPageTbCurriculum02(Page page) {
		BaseDao baseDao = new BaseDao();
		page = baseDao
				.sqlPage(
						page,
						"select s.schoolName,c.courseName,c.studyCosts,c2.teamPrice,c2.id,c.courseId from tb_school s INNER JOIN tb_curriculum c ON s.schoolId=c.schoolId LEFT JOIN tb_curriculum02 c2 ON c2.curriculumId=c.courseId");
		List list = page.getCurrentList();
		List<TbCurriculumWeb> tbCurriculumWebs = new ArrayList<TbCurriculumWeb>();
		if (list != null && !list.isEmpty()) {
			TbCurriculumWeb tbCurriculumWeb = null;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				tbCurriculumWeb = new TbCurriculumWeb();
				tbCurriculumWeb.setSchoolName(objects[0] == null ? ""
						: (String) objects[0]);
				tbCurriculumWeb.setCourseName(objects[1] == null ? ""
						: (String) objects[1]);
				String studyCost = objects[2] == null ? ""
						: (String) objects[2];
				tbCurriculumWeb.setStudyCost(studyCost);
				tbCurriculumWeb.setTeamPrice(objects[3] == null ? studyCost
						: (String) objects[3]);
				if (objects[4] == null) {
					Timestamp time = new Timestamp(System.currentTimeMillis());
					TbCurriculum02 curriculum02 = new TbCurriculum02(
							(Integer) objects[5], 0, (String) objects[2], 1,
							time, time);
					Integer id = (Integer) baseDao.save(curriculum02);
					tbCurriculumWeb.setCourseId(id);
				} else
					tbCurriculumWeb.setCourseId((Integer) objects[4]);
				tbCurriculumWebs.add(tbCurriculumWeb);
			}
			page.setCurrentList(tbCurriculumWebs);
		}
		return page;
	}

	/**
	 * 读取班的详情
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TbCurriculumWeb findDetailTbCurriculumWeb(Integer id) {
		BaseDao baseDao = new BaseDao();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<?> list = baseDao
				.sqlList(
						"select s.schoolName,c.courseName,c.studyCosts,c2.teamPrice,c2.id,c.courseId from tb_school s INNER JOIN tb_curriculum c ON s.schoolId=c.schoolId LEFT JOIN tb_curriculum02 c2 ON c2.curriculumId=c.courseId where c2.id=?",
						paramList);
		if (list == null || list.isEmpty())
			return new TbCurriculumWeb();
		TbCurriculumWeb tbCurriculumWeb = null;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			tbCurriculumWeb = new TbCurriculumWeb();
			tbCurriculumWeb.setSchoolName(objects[0] == null ? ""
					: (String) objects[0]);
			tbCurriculumWeb.setCourseName(objects[1] == null ? ""
					: (String) objects[1]);
			String studyCost = objects[2] == null ? "" : (String) objects[2];
			tbCurriculumWeb.setStudyCost(studyCost);
			tbCurriculumWeb.setTeamPrice(objects[3] == null ? studyCost
					: (String) objects[3]);
			if (objects[4] == null) {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				TbCurriculum02 curriculum02 = new TbCurriculum02(
						(Integer) objects[5], 0, (String) objects[2], 1, time,
						time);
				Integer id1 = (Integer) baseDao.save(curriculum02);
				tbCurriculumWeb.setCourseId(id1);
			} else
				tbCurriculumWeb.setCourseId((Integer) objects[4]);
		}
		return tbCurriculumWeb;
	}

	/**
	 * 修改班的优惠价
	 * 
	 * @param id
	 * @param teamPrice
	 * @return
	 */
	public String updateTbCurriculumWeb(Integer id, String teamPrice) {
		TbCurriculum02 tbCurriculum02 = new TbCurriculum02();
		tbCurriculum02.setId(id);
		tbCurriculum02.setTeamPrice(teamPrice);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		tbCurriculum02.setAlterTime(time);
		return updateResult(new BaseDao().updateT(tbCurriculum02, id));
	}

	/**
	 * 显示地图颜色：总校分页列表
	 * 
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findPageTbSchool(Page page) {
		BaseDao baseDao = new BaseDao();
		page = baseDao
				.sqlPage(
						page,
						"select s.schoolId,s.schoolName,s2.id,s2.type,s2.showType from tb_school s LEFT JOIN tb_school02 s2 on s.schoolId=s2.schoolId");
		List list = page.getCurrentList();
		List<TbBranchschoolWeb> datas = new ArrayList<TbBranchschoolWeb>();
		if (list != null && !list.isEmpty()) {
			TbBranchschoolWeb data = null;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				data = new TbBranchschoolWeb();
				data.setSchoolName(objects[1] == null ? ""
						: (String) objects[1]);
				if (objects[2] == null) {
					Timestamp time = new Timestamp(System.currentTimeMillis());
					TbSchool02 data02 = new TbSchool02((Integer) objects[0],
							"", 0, "0", 0, 1, time, time);
					Integer id = (Integer) baseDao.save(data02);
					data.setBranchschoolId(id);
					data.setShowType(0);
					data.setType(new StringBuffer(TWDataUtil.getBaseUrl())
							.append("images/1.png").toString());
				} else {
					if (objects[3] == null)
						data.setType(new StringBuffer(TWDataUtil.getBaseUrl())
								.append("images/1.png").toString());
					else
						data.setType(new StringBuffer(TWDataUtil.getBaseUrl())
								.append("images/")
								.append(Integer.parseInt((String) objects[3]) + 1)
								.append(".png").toString());
					data.setShowType(objects[4] == null ? 0
							: (Integer) objects[4]);
					data.setBranchschoolId((Integer) objects[2]);
				}
				datas.add(data);
			}
			page.setCurrentList(datas);
		}
		return page;
	}

	/**
	 * 获取总校详情
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TbBranchschoolWeb findDetailTbSchool(Integer id) {
		BaseDao baseDao = new BaseDao();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<?> list = baseDao
				.sqlList(
						"select s.schoolId,s.schoolName,s2.id,s2.type,s2.showType from tb_school s LEFT JOIN tb_school02 s2 on s.schoolId=s2.schoolId where s2.id=?",
						paramList);
		if (list == null || list.isEmpty())
			return new TbBranchschoolWeb();
		TbBranchschoolWeb data = null;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			data = new TbBranchschoolWeb();
			data.setSchoolName(objects[1] == null ? "" : (String) objects[1]);
			if (objects[2] == null) {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				TbSchool02 data02 = new TbSchool02((Integer) objects[0], "", 0,
						"0", 0, 1, time, time);
				Integer id1 = (Integer) baseDao.save(data02);
				data.setBranchschoolId(id1);
				data.setShowType(0);
				data.setType("0");
			} else {
				data.setType(objects[3] == null ? "" : (String) objects[3]);
				data.setShowType(objects[4] == null ? 0 : (Integer) objects[4]);
				data.setBranchschoolId((Integer) objects[2]);
			}
		}
		return data;
	}

	/**
	 * 修改总校显示，颜色属性
	 * 
	 * @param data
	 * @return
	 */
	public String updateTbSchool02(TbSchool02 data) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		data.setAlterTime(time);
		return updateResult(new BaseDao().updateT(data, data.getId()));
	}

	/**
	 * 显示地图颜色：分校分页列表
	 * 
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findPageTbBranchschool(Page page) {
		BaseDao baseDao = new BaseDao();
		page = baseDao
				.sqlPage(
						page,
						"select b.branchSchoolId,b.braschName,b2.id,b2.type,b2.showType from tb_branchschool b LEFT JOIN tb_branchschool02 b2 on b.branchSchoolId=b2.branchschoolId");
		List list = page.getCurrentList();
		List<TbBranchschoolWeb> datas = new ArrayList<TbBranchschoolWeb>();
		if (list != null && !list.isEmpty()) {
			TbBranchschoolWeb data = null;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				data = new TbBranchschoolWeb();
				data.setSchoolName(objects[1] == null ? ""
						: (String) objects[1]);
				if (objects[2] == null) {
					Timestamp time = new Timestamp(System.currentTimeMillis());
					TbSchool02 data02 = new TbSchool02((Integer) objects[0],
							"", 0, "0", 0, 1, time, time);
					Integer id = (Integer) baseDao.save(data02);
					data.setBranchschoolId(id);
					data.setShowType(0);
					data.setType(new StringBuffer(TWDataUtil.getBaseUrl())
							.append("images/1.png").toString());
				} else {
					if (objects[3] == null)
						data.setType(new StringBuffer(TWDataUtil.getBaseUrl())
								.append("images/1.png").toString());
					else
						data.setType(new StringBuffer(TWDataUtil.getBaseUrl())
								.append("images/")
								.append(Integer.parseInt((String) objects[3]) + 1)
								.append(".png").toString());
					data.setShowType(objects[4] == null ? 0
							: (Integer) objects[4]);
					data.setBranchschoolId((Integer) objects[2]);
				}
				datas.add(data);
			}
			page.setCurrentList(datas);
		}
		return page;
	}

	/**
	 * 获取分校详情
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public TbBranchschoolWeb findDetailTbBranchschool(Integer id) {
		BaseDao baseDao = new BaseDao();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<?> list = baseDao
				.sqlList(
						"select b.branchSchoolId,b.braschName,b2.id,b2.type,b2.showType from tb_branchschool b LEFT JOIN tb_branchschool02 b2 on b.branchSchoolId=b2.branchschoolId where b2.id=?",
						paramList);
		if (list == null || list.isEmpty())
			return new TbBranchschoolWeb();
		TbBranchschoolWeb data = null;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			data = new TbBranchschoolWeb();
			data.setSchoolName(objects[1] == null ? "" : (String) objects[1]);
			if (objects[2] == null) {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				TbSchool02 data02 = new TbSchool02((Integer) objects[0], "", 0,
						"0", 0, 1, time, time);
				Integer id1 = (Integer) baseDao.save(data02);
				data.setBranchschoolId(id1);
				data.setShowType(0);
				data.setType("0");
			} else {
				data.setType(objects[3] == null ? "" : (String) objects[3]);
				data.setShowType(objects[4] == null ? 0 : (Integer) objects[4]);
				data.setBranchschoolId((Integer) objects[2]);
			}
		}
		return data;
	}

	/**
	 * 修改分校显示，颜色属性
	 * 
	 * @param data
	 * @return
	 */
	public String updateTbBranchschool02(TbBranchschool02 data) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		data.setAlterTime(time);
		return updateResult(new BaseDao().updateT(data, data.getId()));
	}

	/**
	 * 发送推送信息
	 * 
	 * @param acceptIds
	 *            接受者ID
	 * @param adminId
	 *            发送者ID
	 * @param title
	 *            题目
	 * @param content
	 *            内容
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public String addTbPushRecords(Integer[] acceptIds, Integer adminId,
			String title, String content) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (acceptIds == null || acceptIds.length == 0)
			return "发送推送失败";
		Set<Integer> idsSet=new HashSet<Integer>();
		List<Integer> idsList=new ArrayList<Integer>();
		for (Integer id : acceptIds) {
			idsSet.add(id);
		}
		TbPushRecord[] tbPushRecords = new TbPushRecord[idsSet.size()];
		Integer i=0;
		Integer id =null;
		for (Iterator iterator = idsSet.iterator(); iterator.hasNext();) {
			id = (Integer) iterator.next();
			idsList.add(id);
			tbPushRecords[i] = new TbPushRecord(adminId, id, title,
					content, 1, time, time);
			i++;
		}
		Serializable[] ids = new BaseDao().save(tbPushRecords);
		if(ids == null || ids.length == 0 ){
			return "发送推送失败";
		}else{
			new JpushThread(idsList, content, "校掌推送", GogoServletImpl.getPath());
			return "发送推送成功";
		}
	}

	/**
	 * 分页推送查询校长
	 * 
	 * @param page
	 *            分页属性
	 * @param cityName
	 *            城市名称
	 * @param townName
	 *            城区名称
	 * @param schoolName
	 *            学校名称
	 * @param subjectName
	 * @param lessonName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findPageHeadTeacher(Page page, String cityName,
			String townName, String schoolName, String subjectName,
			String lessonName) {
		Set<Integer> schoolIds = new HashSet<Integer>();
		Set<Integer> branschoolIds = new HashSet<Integer>();
		BaseDao baseDao = new BaseDao();

		DetachedCriteria curdc = DetachedCriteria.forClass(TbCurriculum.class);
		if (StringUtils.isNotEmpty(cityName)
				|| StringUtils.isNotEmpty(townName)) {
			DetachedCriteria townDc = DetachedCriteria.forClass(TbTown.class);
			townDc.createAlias("tbCity", "c");
			if (StringUtils.isNotEmpty(cityName))
				townDc.add(Restrictions.like("c.cityName", cityName,
						MatchMode.ANYWHERE));
			if (StringUtils.isNotEmpty(townName))
				townDc.add(Restrictions.like("townName", townName,
						MatchMode.ANYWHERE));
			ArrayList<TbTown> tbTowns = baseDao.dCList(TbTown.class, townDc);
			if (tbTowns != null && !tbTowns.isEmpty()) {
				curdc.add(Restrictions.in("tbTown", tbTowns));
			} else {
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
		}
		if (StringUtils.isNotEmpty(subjectName)) {
			DetachedCriteria subDc = DetachedCriteria.forClass(TbSubject.class);
			subDc.add(Restrictions.like("subjectName", subjectName,
					MatchMode.ANYWHERE));
			ArrayList<TbSubject> tbSubjects = baseDao.dCList(TbSubject.class,
					subDc);
			if (tbSubjects != null && !tbSubjects.isEmpty()) {
				curdc.add(Restrictions.in("tbSubject", tbSubjects));
			} else {
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
		}
		if (StringUtils.isNotEmpty(lessonName)) {
			DetachedCriteria lessdc = DetachedCriteria.forClass(TbLesson.class);
			lessdc.add(Restrictions.like("lessonName", lessonName,
					MatchMode.ANYWHERE));
			ArrayList<TbLesson> tbLessons = baseDao.dCList(TbLesson.class,
					lessdc);
			if (tbLessons != null && !tbLessons.isEmpty()) {
				curdc.add(Restrictions.in("tbLesson", tbLessons));
			} else {
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
		}
		if (StringUtils.isNotEmpty(schoolName)) {
			DetachedCriteria schDc = DetachedCriteria.forClass(TbSchool.class);
			schDc.add(Restrictions.like("schoolName", schoolName,
					MatchMode.ANYWHERE));
			ArrayList<TbSchool> schools = baseDao.dCList(TbSchool.class, schDc);
			DetachedCriteria braDc = DetachedCriteria
					.forClass(TbBranchschool.class);
			braDc.add(Restrictions.like("braschName", schoolName,
					MatchMode.ANYWHERE));
			ArrayList<TbBranchschool> branchschools = baseDao.dCList(
					TbBranchschool.class, braDc);
			if (schools != null && !schoolIds.isEmpty()) {
				if (branchschools != null && !branchschools.isEmpty())
					curdc.add(Restrictions.or(
							Restrictions.in("tbSchool", schools),
							Restrictions.in("tbBranchschool", branchschools)));
				else
					curdc.add(Restrictions.in("tbSchool", schools));
			} else {
				if (branchschools != null && !branchschools.isEmpty())
					curdc.add(Restrictions.in("tbBranchschool", branchschools));
				else {
					page = new Page();
					page.setCurrentList(new ArrayList(0));
					return page;
				}

			}
		}
		ArrayList<TbCurriculum> curriculums = baseDao.dCList(
				TbCurriculum.class, curdc);
		if (curriculums != null && !curriculums.isEmpty()) {
			for (TbCurriculum tbCurriculum : curriculums) {
				if (tbCurriculum.getTbSchool() != null
						&& tbCurriculum.getTbSchool().getSchoolId() != null)
					schoolIds.add(tbCurriculum.getTbSchool().getSchoolId());
				if (tbCurriculum.getTbBranchschool() != null
						&& tbCurriculum.getTbBranchschool().getBranchSchoolId() != null)
					branschoolIds.add(tbCurriculum.getTbBranchschool()
							.getBranchSchoolId());
			}
		} else {
			page = new Page();
			page.setCurrentList(new ArrayList(0));
			return page;
		}
		DetachedCriteria pushdc = DetachedCriteria.forClass(TbUserinfo.class);
//		pushdc.createAlias("tbUserinfo", "u");
		List<TbSchool> listsch = null;
		List<TbBranchschool> listbra = null;
		if (!schoolIds.isEmpty()) {
			listsch = new ArrayList<TbSchool>();
			TbSchool tbSchool = null;
			Integer id = null;
			for (Iterator iterator = schoolIds.iterator(); iterator.hasNext();) {
				id = (Integer) iterator.next();
				tbSchool = new TbSchool();
				tbSchool.setSchoolId(id);
				listsch.add(tbSchool);
			}
		}
		if (!branschoolIds.isEmpty()) {
			listbra = new ArrayList<TbBranchschool>();
			Integer id = null;
			TbBranchschool tbBranchschool = null;
			for (Iterator iterator = branschoolIds.iterator(); iterator
					.hasNext();) {
				id = (Integer) iterator.next();
				tbBranchschool = new TbBranchschool();
				tbBranchschool.setBranchSchoolId(id);
				listbra.add(tbBranchschool);
			}
		}
		if (listsch != null) {
			if (listbra != null)
				pushdc.add(Restrictions.or(Restrictions.and(
						Restrictions.in("tbSchool", listsch),
						Restrictions.eq("userInfoRoot", 4)), Restrictions
						.and(Restrictions.in("tbBranchschool", listbra),
								Restrictions.eq("userInfoRoot", 0))));
			else
				pushdc.add(Restrictions.and(
						Restrictions.in("tbSchool", listsch),
						Restrictions.eq("userInfoRoot", 4)));
		} else {
			if (listbra != null)
				pushdc.add(Restrictions.and(
						Restrictions.in("tbBranchschool", listbra),
						Restrictions.eq("userInfoRoot", 0)));
			else {
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
		}
		page = baseDao.dCPage(page, pushdc);
		List list = page.getCurrentList();
		TbUserinfo tbPush = null;
		TbUserinfoWeb tbUserinfoWeb = null;
		List<TbUserinfoWeb> userinfoWebs = new ArrayList<TbUserinfoWeb>();
		for (Object object : list) {
			if (object instanceof TbUserinfo) {
				tbPush = (TbUserinfo) object;
				tbUserinfoWeb = new TbUserinfoWeb();
				tbUserinfoWeb.setUserinfoId(tbPush.getUserInfoId());
				tbUserinfoWeb.setUserinfoName(tbPush
						.getUserInfoName());
				tbUserinfoWeb.setUserinfoPhone(tbPush
						.getUserInfoPhone());
//				tbUserinfoWeb.setPushToken(tbPush.getPushToken());
//				tbUserinfoWeb.setPushType(tbPush.getPushType());
				userinfoWebs.add(tbUserinfoWeb);
			}
		}
		page.setCurrentList(userinfoWebs);
		return page;
	}

	/**
	 * 家长分页信息查询
	 * 
	 * @param page
	 *            分页属性
	 * @param cityName
	 *            城市
	 * @param townName
	 *            城镇
	 * @param gradeName
	 *            年段
	 * @param schoolId
	 *            学校定位
	 * @param schoolType
	 *            0-schoolId分校ID，1-schoolId总校ID 默认分校
	 * @param range
	 *            范围（有值时查范围，无值时不查）
	 * @param type
	 *            0-查看学校被点击记录人员，1-不查看，默认不查看
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findPagePatriarch(Page page, String cityName, String townName,
			String gradeName, Integer schoolId, Integer schoolType,
			String range, Integer type) {
		Set<Integer> schoolIds = new HashSet<Integer>();
		Set<Integer> branschoolIds = new HashSet<Integer>();
		Set<Integer> userInfoIds = new HashSet<Integer>();
		BaseDao baseDao = new BaseDao();
		TbSchool school = null;
		TbBranchschool branchschool = null;
		//锁定城市城区
		if (StringUtils.isNotEmpty(cityName)
				|| StringUtils.isNotEmpty(townName)) {
			DetachedCriteria braDc = DetachedCriteria
					.forClass(TbBranchschool.class);
			DetachedCriteria townDc = DetachedCriteria.forClass(TbTown.class);
			townDc.createAlias("tbCity", "c");
			if (StringUtils.isNotEmpty(cityName))
				townDc.add(Restrictions.like("c.cityName", cityName,
						MatchMode.ANYWHERE));
			if (StringUtils.isNotEmpty(townName))
				townDc.add(Restrictions.like("townName", townName,
						MatchMode.ANYWHERE));
			ArrayList<TbTown> tbTowns = baseDao.dCList(TbTown.class, townDc);
			if (tbTowns != null && !tbTowns.isEmpty()) {
				braDc.add(Restrictions.in("tbTown", tbTowns));
			} else {
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
			ArrayList<TbBranchschool> tbBranchschools = baseDao.dCList(
					TbBranchschool.class, braDc);
			if (tbBranchschools == null ||tbBranchschools.isEmpty()) {
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
			//锁定总校分校ID
			for (TbBranchschool tbBranchschool : tbBranchschools) {
				if (tbBranchschool.getTbSchool() != null
						&& tbBranchschool.getTbSchool().getSchoolId() != null)
					schoolIds.add(tbBranchschool.getTbSchool().getSchoolId());
				if (tbBranchschool.getBranchSchoolId() != null)
					branschoolIds.add(tbBranchschool.getBranchSchoolId());
			}
		}

		//三个查询条件
		List<TbSchool> listsch = null;
		List<TbBranchschool> listbra = null;
		ArrayList<TbUserinfo> listuser =null;
		ArrayList<TbGrade> listgrade =null;
		if(StringUtils.isNotEmpty(gradeName)){
			DetachedCriteria gradc=DetachedCriteria.forClass(TbGrade.class);
			gradc.add(Restrictions.like("gradeName", gradeName,
					MatchMode.ANYWHERE));
			listgrade=baseDao.dCList(TbGrade.class, gradc);
			if(listgrade==null||listgrade.isEmpty()){
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
		}
		//锁定总校ID
		if (!schoolIds.isEmpty()) {
			listsch = new ArrayList<TbSchool>();
			TbSchool tbSchool = null;
			Integer id = null;
			for (Iterator iterator = schoolIds.iterator(); iterator
					.hasNext();) {
				id = (Integer) iterator.next();
				tbSchool = new TbSchool();
				tbSchool.setSchoolId(id);
				listsch.add(tbSchool);
			}
		}
		//锁定分校ID
		if (!branschoolIds.isEmpty()) {
			listbra = new ArrayList<TbBranchschool>();
			Integer id = null;
			TbBranchschool tbBranchschool = null;
			for (Iterator iterator = branschoolIds.iterator(); iterator
					.hasNext();) {
				id = (Integer) iterator.next();
				tbBranchschool = new TbBranchschool();
				tbBranchschool.setBranchSchoolId(id);
				listbra.add(tbBranchschool);
			}
		}
		//schoolType为1是总校
		if (schoolType != null && schoolType == 1) {
			if (schoolId != null)
				school = baseDao.get(TbSchool.class, schoolId);
		} else {
			if (schoolId != null)
				branchschool = baseDao.get(TbBranchschool.class, schoolId);
		}
		//0-查看学校被点击记录人员，锁定浏览过schoolID学校的用户
		if (type != null && type == 0) {
			if (branchschool != null) {
				DetachedCriteria bradc = DetachedCriteria
						.forClass(TbBranchschoolPopularity.class);
				bradc.add(Restrictions.eq("branchschoolId", schoolId));
				ArrayList<TbBranchschoolPopularity> tbBranchschoolPopularities = baseDao
						.dCList(TbBranchschoolPopularity.class, bradc);
				for (TbBranchschoolPopularity tbBranchschoolPopularity : tbBranchschoolPopularities) {
					if (tbBranchschoolPopularity.getUserInfoId() != null)
						userInfoIds.add(tbBranchschoolPopularity
								.getUserInfoId());
				}
			} else if (school != null) {
				DetachedCriteria schPdc = DetachedCriteria
						.forClass(TbSchoolPopularity.class);
				schPdc.add(Restrictions.eq("schoolId", schoolId));
				ArrayList<TbSchoolPopularity> schoolPopularities = baseDao
						.dCList(TbSchoolPopularity.class, schPdc);
				for (TbSchoolPopularity tbSchoolPopularity : schoolPopularities) {
					if (tbSchoolPopularity.getUserInfoId() != null)
						userInfoIds.add(tbSchoolPopularity.getUserInfoId());
				}
			}
			if (!userInfoIds.isEmpty()) {
				listuser = new ArrayList<TbUserinfo>();
				TbUserinfo tbUserinfo = null;
				Integer id = null;
				for (Iterator iterator = userInfoIds.iterator(); iterator
						.hasNext();) {
					id = (Integer) iterator.next();
					tbUserinfo = new TbUserinfo();
					tbUserinfo.setUserInfoId(id);
					listuser.add(tbUserinfo);
				}
			}else{
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
		}
		StringBuffer sqlBuffer=new StringBuffer("select u.userInfoId,u2.longitude,u2.latitude,u.userInfoName,u.userInfoPhone from tb_userinfo u LEFT JOIN tb_userinfo2 u2 on u.userInfoId=u2.userinfoId where u.userInfoRoot=? ");
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(2);
		if(listsch!=null&&!listsch.isEmpty()){
			if(listbra!=null&&!listbra.isEmpty())
				sqlBuffer.append("and ( u.schoolId in(");
			else
				sqlBuffer.append("and u.schoolId in(");
			for (int i = 0; i < listsch.size(); i++) {
				if(i==0)
					sqlBuffer.append("?");
				else
					sqlBuffer.append(",?");
				paramList.add(listsch.get(i).getSchoolId());
			}
			if(listbra!=null&&!listbra.isEmpty())
				sqlBuffer.append(") or ");
			else
				sqlBuffer.append(") ");
		}
		if(listbra!=null&&!listbra.isEmpty()){
			sqlBuffer.append("u.branchSchoolId in(");
			for (int i = 0; i < listbra.size(); i++) {
				if(i==0)
					sqlBuffer.append("?");
				else
					sqlBuffer.append(",?");
				paramList.add(listbra.get(i).getBranchSchoolId());
			}
			if(listsch!=null&&!listsch.isEmpty())
				sqlBuffer.append(") ) ");
			else
				sqlBuffer.append(") ");
		}
		if(listuser!=null&&!listuser.isEmpty()){
			sqlBuffer.append("and u.userInfoId in(");
			for (int i = 0; i < listuser.size(); i++) {
				if(i==0)
					sqlBuffer.append("?");
				else
					sqlBuffer.append(",?");
				paramList.add(listuser.get(i).getUserInfoId());
			}
			sqlBuffer.append(") ");
		}
		if(listgrade!=null&&!listgrade.isEmpty()){
			sqlBuffer.append("and u2.gradeId in(");
			for (int i = 0; i < listgrade.size(); i++) {
				if(i==0)
					sqlBuffer.append("?");
				else
					sqlBuffer.append(",?");
				paramList.add(listgrade.get(i).getGradeId());
			}
			sqlBuffer.append(") ");
		}
		TbUserinfoWeb tbUserinfoWeb=null;
		List<TbUserinfoWeb> tbUserinfoWebs=new ArrayList<TbUserinfoWeb>();
		//有范围的情况
		if (StringUtils.isNotEmpty(range)) {
			Double longitude = 0.0;
			Double latitude = 0.0;
			Double range_dou = Double.valueOf(range);
			if (school != null) {
				longitude = school.getLongitude();
				latitude = school.getLatitude();
			} else {
				longitude = branchschool.getLongitude();
				latitude = branchschool.getLatitude();
			}
			List<?> list = baseDao.sqlList(sqlBuffer.toString(), paramList);
			if(list!=null&&!list.isEmpty()){
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Object[] objects = (Object[]) iterator.next();
					if(objects[2]!=null||objects[1]!=null){
						Double longitude_parent = (Double)objects[1];
						Double latitude_parent = (Double)objects[2];
						if(specificRange01(longitude, latitude, longitude_parent, latitude_parent,range_dou)){
					tbUserinfoWeb = new TbUserinfoWeb();
					tbUserinfoWeb.setUserinfoId((Integer)objects[0]);
					tbUserinfoWeb.setUserinfoName(objects[3]==null?"":(String)objects[3]);
					tbUserinfoWeb.setUserinfoPhone(objects[4]==null?"":(String)objects[4]);
					tbUserinfoWeb.setDistance(LalDistance.getShortDistance(longitude, latitude, longitude_parent, latitude_parent));
					tbUserinfoWebs.add(tbUserinfoWeb);
						}
					}
				}
				Collections.sort(tbUserinfoWebs, new Comparator<TbUserinfoWeb>() {
		            public int compare(TbUserinfoWeb arg0, TbUserinfoWeb arg1) {
		                return arg0.getDistance().compareTo(arg1.getDistance());
		            }
		        });	
				
				page.setCurrentList(tbUserinfoWebs);
			}
		else{
			page = new Page();
			page.setCurrentList(new ArrayList(0));
			return page;
		}
		}//无范围要求的情况
		else {
			page=baseDao.sqlPage(page, sqlBuffer.toString(), paramList);
			List list = page.getCurrentList();
			if(list!=null&&!list.isEmpty()){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				tbUserinfoWeb = new TbUserinfoWeb();
				tbUserinfoWeb.setUserinfoId((Integer)objects[0]);
				tbUserinfoWeb.setUserinfoName(objects[3]==null?"":(String)objects[3]);
				tbUserinfoWeb.setUserinfoPhone(objects[4]==null?"":(String)objects[4]);
				tbUserinfoWebs.add(tbUserinfoWeb);
				}
			}else{
				page = new Page();
				page.setCurrentList(new ArrayList(0));
				return page;
			}
			page.setCurrentList(tbUserinfoWebs);
			}
			return page;
	}

	private Boolean specificRange01(Double lon1, Double lat1, Double lon2,
			Double lat2, Double range) {
		return LalDistance.getShortDistance(lon1, lat1, lon2, lat2) >= range ? false
				: true;
	}
	@SuppressWarnings({ "rawtypes" })
	public Page findPageSchool(Page page,String schoolName){
		BaseDao baseDao = new BaseDao();
		DetachedCriteria schDc=DetachedCriteria.forClass(TbSchool.class);
		schDc.add(Restrictions.like("schoolName", schoolName, MatchMode.ANYWHERE));
		page=baseDao.dCPage(page, schDc);
		List list = page.getCurrentList();
		if(list!=null&&!list.isEmpty()){
			TbSchool tbSchool=null;
			TbSchoolWeb tbSchoolWeb=null;
			List<TbSchoolWeb> tbSchoolWebs=new ArrayList<TbSchoolWeb>();
			for (Object object : list) {
				if(object instanceof TbSchool){
					tbSchool=(TbSchool)object;
					tbSchoolWeb=new TbSchoolWeb();
					tbSchoolWeb.setSchoolId(tbSchool.getSchoolId());
					tbSchoolWeb.setSchoolAddress(tbSchool.getSchoolAddress());
					tbSchoolWeb.setSchoolName(tbSchool.getSchoolName());
					tbSchoolWebs.add(tbSchoolWeb);
				}
				
			}
			page.setCurrentList(tbSchoolWebs);
		}
		return page;
	}
	@SuppressWarnings({ "rawtypes" })
	public Page findPageBranchschool(Page page,String schoolName){
		BaseDao baseDao = new BaseDao();
		DetachedCriteria schDc=DetachedCriteria.forClass(TbBranchschool.class);
		schDc.add(Restrictions.like("braschName", schoolName, MatchMode.ANYWHERE));
		page=baseDao.dCPage(page, schDc);
		List list = page.getCurrentList();
		if(list!=null&&!list.isEmpty()){
			TbBranchschool tbSchool=null;
			TbSchoolWeb tbSchoolWeb=null;
			List<TbSchoolWeb> tbSchoolWebs=new ArrayList<TbSchoolWeb>();
			for (Object object : list) {
				if(object instanceof TbBranchschool){
					tbSchool=(TbBranchschool)object;
					tbSchoolWeb=new TbSchoolWeb();
					tbSchoolWeb.setSchoolId(tbSchool.getBranchSchoolId());
					tbSchoolWeb.setSchoolAddress(tbSchool.getBraschAddress());
					tbSchoolWeb.setSchoolName(tbSchool.getBraschName());
					tbSchoolWebs.add(tbSchoolWeb);
				}
				
			}
			page.setCurrentList(tbSchoolWebs);
		}
		return page;
	}
	@SuppressWarnings({ "rawtypes" })
	public Page findPageTbPusher(Page page){
		BaseDao baseDao = new BaseDao();
		page=baseDao.sqlPage(page, "select pr.id,u.userInfoName,pr.title,pr.content from tb_userinfo u,tb_push_record pr where u.userInfoId=pr.acceptId ORDER BY pr.id DESC");
		List list = page.getCurrentList();
		if(list!=null&&!list.isEmpty()){
		TbPushRecord tbPushRecord=null;
		List<TbPushRecord> tbPushRecords=new ArrayList<TbPushRecord>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			tbPushRecord=new TbPushRecord();
			tbPushRecord.setId((Integer)objects[0]);
			tbPushRecord.setName(objects[1]==null?"":(String)objects[1]);
			tbPushRecord.setTitle(objects[2]==null?"":(String)objects[2]);
			tbPushRecord.setContent(objects[3]==null?"":StringUtils.abbreviate((String)objects[3], 100));
			tbPushRecords.add(tbPushRecord);
			}
		page.setCurrentList(tbPushRecords);
		}
		return page;
	}
	
}
