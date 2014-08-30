package com.qiYang.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.dao.FindUserDao;
import com.qiYang.dao.UpdateDao;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbComplain;
import com.qiYang.model.TbComplaindetails;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbCurriculum;
import com.qiYang.model.TbGrade;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbReview;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSchoolnotice;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbUserinfoclass;
import com.qiYang.model.TbUserphoto;
import com.qiYang.model.web.TbCityWeb;
import com.qiYang.model.web.TbComplainWeb;
import com.qiYang.model.web.TbComplaindetailsWeb;
import com.qiYang.model.web.TbCurriculumWeb;
import com.qiYang.model.web.TbGradeWeb;
import com.qiYang.model.web.TbLessonWeb;
import com.qiYang.model.web.TbReviewWeb;
import com.qiYang.model.web.TbSchoolnoticeWeb;
import com.qiYang.model.web.TbSubjectWeb;
import com.qiYang.model.web.TbTestWeb;
import com.qiYang.model.web.TbTownWeb;
import com.qiYang.model.web.TbUserinfoclassWeb;
import com.qiYang.util.ResultJson;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObjectUtil;
import com.qiYang.util.TWPictureUtil;
import com.util.mail.SendEmail;

public class UserJsonService implements UserJsonServiceImp {

	
	private final FindUserDao findDao = new FindUserDao();

	// 个人中心
	public ResultJson checkUserinfoJson(Integer userinfoId) {
		Map<String, Object> data = new HashMap<String, Object>();
		TbUserinfo tbUserinfo = findDao.findByUserId(userinfoId);
		if(tbUserinfo!=null){
			TbUser tbuser = findDao.getTbUser(tbUserinfo);
		if (tbUserinfo.getUserInfoRoot() == 0 || tbUserinfo.getUserInfoRoot() == 4) { // 校长个人中心
				data.put("userName", TWObjectUtil.getString(tbuser.getUserName()));
				data.put("userinfoAvatar", TWObjectUtil.getString(TWPictureUtil.getNomalPicPath(tbUserinfo.getUserInfoAvatar())));
				data.put("userinfoSign", TWObjectUtil.getString(tbUserinfo.getUserInfoSign()));
				data.put("name", TWObjectUtil.getString(tbUserinfo.getUserInfoName()));
				data.put("sex", TWObjectUtil.getString(tbUserinfo.getUserInfoSex()));
				if(tbUserinfo.getUserInfoBirthday()==null){
					data.put("birthday", "");
				}else{
					String birthday = TWDataUtil.dateFormat(tbUserinfo.getUserInfoBirthday());
					data.put("birthday", birthday);
				}
				data.put("phone", TWObjectUtil.getString(tbUserinfo.getUserInfoPhone()));
				data.put("courseName",TWObjectUtil.getString(tbUserinfo.getUserInfoCourse()));

			if (tbUserinfo.getUserInfoId() != null) {
				tbUserinfo = findDao.getTbUserinfo(userinfoId);
				ArrayList<TbUserphoto> list = findDao.findbyPhotoAll(tbUserinfo);
				ArrayList<Object> strListNomals = new ArrayList<Object>();
				for (TbUserphoto tbUserphoto : list) {
					Map<String, String> mapPic = new HashMap<String, String>();
					mapPic.put("min", TWObjectUtil.getNomalPicPath(tbUserphoto
							.getPhotoPath()));
					strListNomals.add(mapPic);
				}
				data.put("list_full", strListNomals);
				data.put("num", list.size());
			}
		} else if (tbUserinfo.getUserInfoRoot() == 1) { // 老师个人中心
				data.put("userName", TWObjectUtil.getString(tbuser.getUserName()));
				data.put("userinfoAvatar", TWObjectUtil.getString(TWPictureUtil.getNomalPicPath(tbUserinfo
						.getUserInfoAvatar())));
				data.put("userinfoSign", TWObjectUtil.getString(tbUserinfo.getUserInfoSign()));
				data.put("name", TWObjectUtil.getString(tbUserinfo.getUserInfoName()));
				data.put("sex", TWObjectUtil.getString(tbUserinfo.getUserInfoSex()));
				if(tbUserinfo.getUserInfoBirthday()==null){
					data.put("birthday", "");
				}else{
					String birthday = TWDataUtil.dateFormat(tbUserinfo.getUserInfoBirthday());
					data.put("birthday", birthday);
				}
				data.put("phone", TWObjectUtil.getString(tbUserinfo.getUserInfoPhone()));
				data.put("courseName",TWObjectUtil.getString(tbUserinfo.getUserInfoCourse()));
			if (tbUserinfo.getUserInfoId() != null) {
				tbUserinfo = findDao.getTbUserinfo(userinfoId);
				ArrayList<TbUserphoto> list = findDao.findbyPhotoAll(tbUserinfo);
				ArrayList<Object> strListNomals = new ArrayList<Object>();
				for (TbUserphoto tbUserphoto : list) {
					Map<String, String> mapPic = new HashMap<String, String>();
					mapPic.put("min", TWObjectUtil.getNomalPicPath(tbUserphoto.getPhotoPath()));
					strListNomals.add(mapPic);
				}
				// String[] str = new String[list.size()];
				// for (int i = 0; i < str.length; i++) {
				// str[i] =
				// TWPictureUtil.getNomalPicPath(list.get(i).getPhotoPath());
				// }
				data.put("list_full", strListNomals);
				data.put("num", list.size());
			}
		} else if (tbUserinfo.getUserInfoRoot() == 2) { // 家长个人中心
			TbUserinfo tbuserinfo = TWObjectUtil.getChildByParent(tbUserinfo);
			if(tbuserinfo!=null){
					data.put("userName", TWObjectUtil.getString(tbuser.getUserName()));
					data.put("userinfoAvatar", TWObjectUtil.getString(TWPictureUtil.getNomalPicPath(tbUserinfo
							.getUserInfoAvatar())));
					data.put("userinfoSign", TWObjectUtil.getString(tbUserinfo.getUserInfoSign()));
					data.put("studentname", TWObjectUtil.getString(tbuserinfo.getUserInfoName()));
					data.put("name", TWObjectUtil.getString(tbUserinfo.getUserInfoName()));
					data.put("sex", TWObjectUtil.getString(tbUserinfo.getUserInfoSex()));
					if(tbUserinfo.getUserInfoBirthday()==null){
						data.put("birthday", "");
					}else{
						String birthday = TWDataUtil.dateFormat(tbUserinfo.getUserInfoBirthday());
						data.put("birthday", birthday);
					}
					data.put("phone", TWObjectUtil.getString(tbUserinfo.getUserInfoPhone()));
			}
			return ResultJson.crateSuccJson(data);

		} else if (tbUserinfo.getUserInfoRoot() == 3) { // 学生个人中心
			TbUserinfo userinfo = TWObjectUtil.getParentByChild(tbUserinfo);
			TbUser user = findDao.getTbUser(tbUserinfo);
			if(userinfo!=null && user!=null){
					data.put("studentName", TWObjectUtil.getString(tbuser.getUserName()));
					data.put("gradeName", TWObjectUtil.getString(tbUserinfo.getStudentClass()));
					data.put("userName", TWObjectUtil.getString(user.getUserName()));
					data.put("personfoAvatar",TWObjectUtil.getString(TWPictureUtil.getNomalPicPath(userinfo
							.getUserInfoAvatar())));
					data.put("userinfoAvatar", TWObjectUtil.getString(TWPictureUtil.getNomalPicPath(tbUserinfo
							.getUserInfoAvatar())));
					data.put("userinfoSign", TWObjectUtil.getString(tbUserinfo.getUserInfoSign()));
					data.put("personName" , TWObjectUtil.getString(userinfo.getUserInfoName()));
					data.put("name", TWObjectUtil.getString(tbUserinfo.getUserInfoName()));
					data.put("sex", TWObjectUtil.getString(tbUserinfo.getUserInfoSex()));
					if(tbUserinfo.getUserInfoBirthday()==null){
						data.put("birthday", "");
					}else{
						String birthday = TWDataUtil.dateFormat(tbUserinfo.getUserInfoBirthday());
						data.put("birthday", birthday);
					}
					data.put("phone", TWObjectUtil.getString(tbUserinfo.getUserInfoPhone()));
					data.put("courseName",TWObjectUtil.getString(tbUserinfo.getUserInfoCourse()));
					data.put("schoolName", TWObjectUtil.getString(tbUserinfo.getStudentSchool()));
			if (tbUserinfo.getUserInfoId() != null) {
				tbUserinfo = findDao.getTbUserinfo(userinfoId);
				ArrayList<TbUserphoto> list = findDao
						.findbyPhotoAll(tbUserinfo);
				ArrayList<Object> strListNomals = new ArrayList<Object>();
				for (TbUserphoto tbUserphoto : list) {
					Map<String, String> mapPic = new HashMap<String, String>();
					mapPic.put("min", TWObjectUtil.getNomalPicPath(tbUserphoto
							.getPhotoPath()));
					strListNomals.add(mapPic);
				}
				String[] str = new String[list.size()];
				for (int i = 0; i < str.length; i++) {
					str[i] = TWPictureUtil.getNomalPicPath(list.get(i)
							.getPhotoPath());
				}
				
				data.put("list_full", strListNomals);
				data.put("num", list.size());
				}
			
			}
			}
		}
		return ResultJson.crateSuccJson(data);
	}

	// 修改手机号码
	public ResultJson updatePhoneJson(Integer userinfoId, String phone) {
		String result = new UpdateDao().updateUserinfophone(userinfoId, phone,
				new Timestamp(new Date().getTime()), "phone");
		if ("success".equals(result))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "修改失败");
	}

	// 修改签名
	public ResultJson updateSignJson(Integer userinfoId, String sign) {
		String result = new UpdateDao().updateUserinfoSign(userinfoId, sign,
				new Timestamp(new Date().getTime()), "sign");
		if ("success".equals(result))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "修改失败");
	}

	// 查看个人相册
	public ResultJson finduserphotoJson(Integer userinfoId) {
		TbUserinfo tbUserinfo = findDao.getTbUserinfo(userinfoId);
		Map<String, Object> data = new HashMap<String, Object>();
		if(tbUserinfo!=null){
			ArrayList<TbUserphoto> list = findDao.findbyPhotoAll(tbUserinfo);
			List<Object> strListNomals = new ArrayList<Object>();
			for (TbUserphoto tbUserphoto : list) {
				Map<String, String> mapPic = new HashMap<String, String>();
				mapPic.put("id", TWObjectUtil.getString(String.valueOf(tbUserphoto.getPhotoId())));
				mapPic.put("min",TWObjectUtil.getNomalPicPath(tbUserphoto.getPhotoPath()));
				mapPic.put("normal", TWObjectUtil.getNomalPicPath(tbUserphoto
						.getPhotoPath()));
				strListNomals.add(mapPic);
			}
			data.put("listNum", list.size());
			data.put("list_full", strListNomals);
		}
		return ResultJson.crateSuccJson(data);
	}

	// 修改个人密码
	public ResultJson updatePasswordTo(Integer userinfoId, String oldPassword,
			String newPassword) {
		
		TbUserinfo tbuserinfo = findDao.getTbUserinfo(userinfoId);
		TbUser tbUser = findDao.getTbUser(tbuserinfo);
		UpdateDao updateDao = new UpdateDao();
		TbUser tbUsers = updateDao.checkUserPassword(tbUser.getUserId(), oldPassword);
		String str = null;
		if (oldPassword.equals(tbUsers.getUserPassword())) {
			str = updateDao.upUserPassword(tbUser.getUserId(), newPassword,
					new Timestamp(new Date().getTime()), "password");
			if ("success".equals(str)) {
				return ResultJson.crateSuccJson(new HashMap<String, Object>());
			}
		}
		return ResultJson.createFailJson(-1, "用户名或密码不正确修改失败");
	}

	// 学校通知
	public ResultJson selectSchoolnotice(Integer branchschoolId) {
		TbBranchschool branchschool = findDao.getbranchSchool(branchschoolId);
//		TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
		Map<String, Object> data = new HashMap<String, Object>();
		if(null!=branchschoolId && 0!=branchschoolId){
			ArrayList<TbSchoolnotice> list = findDao.listSchoolNotice(branchschool);
			ArrayList<Object> strList = new ArrayList<Object>();
			for (TbSchoolnotice tbSchoolnotice : list) {
				TbSchoolnoticeWeb tbschoolnoticeweb = new TbSchoolnoticeWeb();
				tbschoolnoticeweb.setSchoolNoticeId(TWObjectUtil.getInteger(tbSchoolnotice.getSchoolNoticeId()));
				tbschoolnoticeweb.setSchoolnoticeTitle(TWObjectUtil.getString(tbSchoolnotice.getSchnotTitle()));
				tbschoolnoticeweb.setSchoolnoticeContent(TWObjectUtil.getString(tbSchoolnotice.getSchnotContent()));
				strList.add(tbschoolnoticeweb);
			}
			data.put("schoolnoticele", strList);
		}
		return ResultJson.crateSuccJson(data);
	}

	// 通告详情
	public ResultJson findBySchoolnotice(Integer schoolnoticeId) {
		TbSchoolnotice tbSchoolnotice = findDao.findByTbSchoolnotice(schoolnoticeId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != tbSchoolnotice){
			map.put("schnotTitle", TWObjectUtil.getString(tbSchoolnotice.getSchnotTitle()));
			TbUserinfo tbuserinfo = findDao.getTbUserinfo(tbSchoolnotice.getTbUserinfo().getUserInfoId());
			map.put("userinfoName", TWObjectUtil.getString(tbuserinfo.getUserInfoName()));
			String schnotAddTime = TWDataUtil.TimestampFormat(tbSchoolnotice.getTime());
			map.put("schnotAddTime", schnotAddTime);
			map.put("schnotContent", TWObjectUtil.getString(tbSchoolnotice.getSchnotContent()));
		}
		return ResultJson.crateSuccJson(map);
	}

	// 老师评语
	public ResultJson findBytbReview(Integer studentId, Integer teacherId,Integer classId,
			String reviewContent) {
		String str = null;
		str = new UpdateDao().addReview(studentId, teacherId,classId, reviewContent);
		if ("success".equals(str))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "添加失败");
	}

	// 查看评语
	public ResultJson selectReview(Integer userinfoId) {
		TbUserinfo tbuserinfo = findDao.getTbUserinfo(userinfoId);
		TbUserinfo userinfo = TWObjectUtil.getChildByParent(tbuserinfo);
		Map<String, Object> data = new HashMap<String, Object>();
		if(userinfo!=null){
			ArrayList<TbReview> list = findDao.listTbreview(userinfo);
			ArrayList<Object> strList = new ArrayList<Object>();
			for (TbReview review : list) {
				if (userinfoId.equals(tbuserinfo.getUserInfoId())) {
					TbReviewWeb tbreviews = new TbReviewWeb();
					TbUserinfo tbuserinfos = findDao.getTbUserinfo(review.getTbUserinfoByTbUserInfoId().getUserInfoId());
					TbCourse tbcourse = findDao.findByCourse(review.getTbCourse().getCourseId());
					tbreviews.setTeacherName(TWObjectUtil.getString(tbuserinfos.getUserInfoName()));
					;
					if(tbcourse!=null){
						tbreviews.setCourseName(TWObjectUtil.getString(tbcourse.getCourseName()));
					}
					tbreviews.setReviewDate(review.getAddDate());
					tbreviews.setReviewContent(TWObjectUtil.getString(review.getReviewContent()));
					strList.add(tbreviews);
				}
			}
			data.put("review", strList);
		}
		return ResultJson.crateSuccJson(data);
	}

	// ------------------------------------家长端---------------------------------------------
	// 投诉列表
	public ResultJson selectComplain(Integer userinfoId,Integer classId,Integer branchschoolId) {
		TbUserinfo tbUserinfo = findDao.getTbUserinfo(userinfoId);
		TbBranchschool tbbranchschool = findDao.getbranchSchool(branchschoolId);
		TbCourse tbcoures = findDao.findByCourse(classId);
		Map<String, Object> data = new HashMap<String, Object>();
		
		if(tbUserinfo!=null&&tbUserinfo.getUserInfoRoot()==2 && tbbranchschool!=null){
			ArrayList<TbComplain> list = findDao.listComplain(tbUserinfo,tbcoures,tbbranchschool);
			ArrayList<Object> strList = new ArrayList<Object>();
			for (TbComplain tbcomplaine : list) {
				TbUserinfo tbUserinfo1 = findDao.getTbUserinfo(tbcomplaine.getTbUserinfoByUserInfoId().getUserInfoId());
				TbUserinfo userinfo = TWObjectUtil.getChildByParent(tbUserinfo);
				TbComplainWeb tbcomplainweb = new TbComplainWeb();
				tbcomplainweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcomplainweb.setComplainId(TWObjectUtil.getInteger(tbcomplaine.getComplainId()));
				tbcomplainweb.setStudentName(TWObjectUtil.getString(tbUserinfo1.getUserInfoName()));
				tbcomplainweb.setParentName(TWObjectUtil.getString(userinfo.getUserInfoName()));
				String time = TWDataUtil.date(tbcomplaine.getTime());
				tbcomplainweb.setTime(time);
				tbcomplainweb.setComplainContent(TWObjectUtil.getString(tbcomplaine.getComplainContent()));
				tbcomplainweb.setComplainStatus(TWObjectUtil.getString(tbcomplaine.getComplainStatus()));
				strList.add(tbcomplainweb);
			}
			data.put("complaine", strList);
		}
		return ResultJson.crateSuccJson(data);
	}

	// 校长端查看投诉
	public ResultJson listComplain(Integer branchschoolId){
		TbBranchschool branchschool = findDao.findByBranchschool(branchschoolId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(branchschool!=null&&branchschool.getBranchSchoolId()!=0){
		ArrayList<Object> strList = new ArrayList<Object>();
			ArrayList<TbComplain> list = findDao.listComplain(branchschool);
			if(list.size()==0){
				return ResultJson.createFailJson(-1, "没有数据");
			}
			for(TbComplain tbcomplain : list){
					TbUserinfo tbUserinfo = findDao.getTbUserinfo(tbcomplain.getTbUserinfoByUserInfoId().getUserInfoId());
					TbUserinfo userinfo = TWObjectUtil.getChildByParent(tbUserinfo);
					TbCourse tbcourse = findDao.findByCourse(tbcomplain.getTbCourse().getCourseId());
					TbComplainWeb tbcomplainweb = new TbComplainWeb();
					tbcomplainweb.setCourseName(TWObjectUtil.getString(tbcourse.getCourseName()));
					tbcomplainweb.setComplainId(TWObjectUtil.getInteger(tbcomplain.getComplainId()));
					tbcomplainweb.setStudentName(TWObjectUtil.getString(tbUserinfo.getUserInfoName()));
					tbcomplainweb.setParentName(TWObjectUtil.getString(userinfo.getUserInfoName()));
					tbcomplainweb.setComplainContent(TWObjectUtil.getString(tbcomplain.getComplainContent()));
					tbcomplainweb.setComplainStatus(TWObjectUtil.getString(tbcomplain.getComplainStatus()));
					String time = TWDataUtil.date(tbcomplain.getTime());
					
					tbcomplainweb.setTime(time);
					tbcomplainweb.setIsSatisfie(tbcomplain.getIsSatisfie());
					strList.add(tbcomplainweb);
			}
			map.put("tbcomplain", strList);
		}
			return ResultJson.crateSuccJson(map);
		}
	
	public ResultJson findByComplain(Integer complainId) {
		TbComplain tbcomplain = findDao.getComplain(complainId);
		
		ArrayList<TbComplaindetails> list = findDao.getComplaindetails(tbcomplain);
		ArrayList<Object> strList = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(tbcomplain==null)
			return ResultJson.createFailJson(-1, "没有此条记录");
		else{
		if(complainId!=0){
			TbUserinfo tbUserinfo = findDao.getTbUserinfo(tbcomplain.getTbUserinfoByUserInfoId().getUserInfoId());
			TbUserinfo userinfo = TWObjectUtil.getChildByParent(tbUserinfo);
			TbCourse tbcourse = findDao.findByCourse(tbcomplain.getTbCourse().getCourseId());
			map.put("studentName", TWObjectUtil.getString(userinfo.getUserInfoName()));
			map.put("parentName", TWObjectUtil.getString(tbUserinfo.getUserInfoName()));
			map.put("courseName", TWObjectUtil.getString(tbcourse.getCourseName()));
			map.put("complainContent", tbcomplain.getComplainContent());
			String date = TWDataUtil.date(tbcomplain.getTime());
			map.put("time", date);
			map.put("complainStatus", TWObjectUtil.getString(tbcomplain.getComplainStatus()));
			}
		if (list != null ) {
			for (TbComplaindetails tbcomplaindetails : list) {
				TbUserinfo tbUserinfo1 = findDao.getTbUserinfo(tbcomplaindetails.getTbUserinfo().getUserInfoId());
				TbComplaindetailsWeb tbcomplaindetailsweb = new TbComplaindetailsWeb();
				
				tbcomplaindetailsweb.setTeacherName(TWObjectUtil.getString(tbUserinfo1.getUserInfoName()));
				tbcomplaindetailsweb.setComdetContent(TWObjectUtil.getString(tbcomplaindetails.getComdetContent()));
				String time = TWDataUtil.date(tbcomplaindetails.getTime());
				tbcomplaindetailsweb.setTime(time);
//				TbComplain tbcomplain1 = findDao.getComplain(complainId);
				if(tbcomplain.getIsSatisfie()!=null){
					tbcomplaindetailsweb.setIsSatisfie(TWObjectUtil.getInteger(tbcomplain.getIsSatisfie()));
					
					String altertime = TWDataUtil.date(tbcomplain.getAlterTime());
					tbcomplaindetailsweb.setAlterTime(altertime);
				}
				strList.add(tbcomplaindetailsweb);
			}
			map.put("tbcomplaindetails", strList);
			}
		}
		return ResultJson.crateSuccJson(map);
	}

	// 投诉建议
	public ResultJson addBytbComplain(Integer userinfoId,Integer classId,
			 Integer branchschoolId, String complainContent) {
		String str = null;
		str = new UpdateDao().addtbComplain(userinfoId,classId,branchschoolId, complainContent);
		if ("success".equals(str))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "添加失败");
	}
	
	// 校长端回复投诉
	public ResultJson addcomplainDetails(Integer complainId,
			Integer userinfoId,String comdetContent) {
		String str = null;
		//池长购修改：处理前先检测是否已经被处理过了。处理过就否决。
		TbComplain tbComplain=null;
		if(complainId!=null){
			tbComplain=new TbComplain();
			tbComplain.setComplainId(complainId);
		}
		DetachedCriteria dc=DetachedCriteria.forClass(TbComplaindetails.class);
		dc.add(Restrictions.eq("tbComplain", tbComplain));
		Integer num = new DataBaseDaoImpl().webObjectsNumber(dc);
		if(num>0)
			return ResultJson.crateNullJson("投诉已经处理!");
		str = new UpdateDao().addcomplainDetails(complainId,userinfoId,comdetContent);
		if ("success".equals(str))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "回复失败");
	}

	// 家长回复满意，不满意
	public ResultJson updatecomplain(Integer complainId, Integer isSatisfie) {
		String result = new UpdateDao().updatecomplain(complainId, isSatisfie,
				new Timestamp(new Date().getTime()));
		if ("success".equals(result))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "回复失败");
	}

	// 学习课程(全部课程)
	public ResultJson listCourse(Integer branchschoolId) {
		Map<String, Object> data = new HashMap<String, Object>();
		if(branchschoolId!=0){
		TbBranchschool branchschool = findDao.getbranchSchool(branchschoolId);
		if(branchschool!=null){
		TbSchool tbschool = findDao.findSchool(branchschool.getTbSchool().getSchoolId());
		ArrayList<TbCurriculum> list = findDao.selectCurriculum(tbschool);
		ArrayList<Object> strList = new ArrayList<Object>();
		for (TbCurriculum tbcurriculum : list) {
			TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
			TbBranchschool tbbranchschool1 = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
			TbCurriculumWeb curriculumweb = new TbCurriculumWeb();
			curriculumweb.setSchoolName(school.getSchoolName());
			curriculumweb.setBranchschoolName(tbbranchschool1.getBraschName());
			curriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool1.getBranchSchoolId()));
			curriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
			curriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
			strList.add(curriculumweb);
			data.put("list", strList);
		}
		}
		}else{
			ArrayList<TbCurriculum> list = findDao.listCurriculum();
			ArrayList<Object> strList = new ArrayList<Object>();
			for (TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool1 = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb curriculumweb = new TbCurriculumWeb();
				curriculumweb.setSchoolName(school.getSchoolName());
				curriculumweb.setBranchschoolName(tbbranchschool1.getBraschName());
				curriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool1.getBranchSchoolId()));
				curriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				curriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(curriculumweb);
				data.put("list", strList);
			}
		}
		return ResultJson.crateSuccJson(data);
	}

	// 课程(班级)介绍
	public ResultJson findByCourse(Integer courseId) {
		TbCourse course = findDao.findByCourse(courseId);
		Map<String, Object> data = new HashMap<String, Object>();
		if (courseId!=0 && course != null) {
			TbCurriculum tbcurriculum = findDao.findByCurriculum(course.getTbCurriculum().getCourseId());
			data.put("coursename", TWObjectUtil.getString(course.getCourseName()));
			data.put("address", course.getAddress());
			data.put("studyTime", course.getStudyTime());
			TbBranchschool branchSchool = findDao.getbranchSchool(course.getTbBranchschool().getBranchSchoolId());
			data.put("branchSchoolName", TWObjectUtil.getString(branchSchool.getBraschName()));
			data.put("schoolAddress", TWObjectUtil.getString(branchSchool.getBraschAddress()));
			data.put("startTerm", tbcurriculum.getStartTerm());
			data.put("endTerm", tbcurriculum.getEndTerm());	
			data.put("totalStudyTime", tbcurriculum.getTotalStudyTime());
			
			ArrayList<TbUserinfoclass> list = findDao.finduserinfoclass(course);
			if(list.size()!=0){
			ArrayList<Object> strList = new ArrayList<Object>();
			for (TbUserinfoclass tbuserinfoclass : list) {
				TbUserinfo tbuserinfo = findDao.findByUserId(tbuserinfoclass.getTbUserinfo().getUserInfoId());
				TbUser tbuser = findDao.getTbUser(tbuserinfo);
				TbUserinfoclassWeb tbuserinfoweb = new TbUserinfoclassWeb();
				tbuserinfoweb.setUserinfoName(TWObjectUtil.getString(tbuserinfo.getUserInfoName()));
				
				tbuserinfoweb.setUserinfoAvatar(TWObjectUtil.getNomalPicPath(tbuserinfo.getUserInfoAvatar()));
				if(tbuser!=null){
					tbuserinfoweb.setUserName(TWObjectUtil.getString(tbuser.getUserName()));
				}
				strList.add(tbuserinfoweb);
			} 
			data.put("userinfoName", strList.get(0));
			ArrayList<TbUserinfoclass> list1 = findDao.findinfoclass(course);
		
			ArrayList<Object> strList1 = new ArrayList<Object>();
			
			if(list1.size()!=0){
					for (TbUserinfoclass tbuserinfoclass : list1) {
						if(tbuserinfoclass.getUserRoot()==1){
						TbUserinfo tbuserinfo = findDao.findByUserId(tbuserinfoclass.getTbUserinfo().getUserInfoId());
						TbUser tbuser = findDao.getTbUser(tbuserinfo);
						TbUserinfoclassWeb tbuserinfoweb = new TbUserinfoclassWeb();
						tbuserinfoweb.setUserinfoName(TWObjectUtil.getString(tbuserinfo.getUserInfoName()));
						tbuserinfoweb.setUserinfoAvatar(TWObjectUtil.getNomalPicPath(tbuserinfo.getUserInfoAvatar()));
						if(tbuser!=null){
							tbuserinfoweb.setUserName(TWObjectUtil.getString(tbuser.getUserName()));
						}
						strList1.add(tbuserinfoweb);
						}
						data.put("tacherName", strList.get(0));
					}
				}
			}
			data.put("courseUrl", TWObjectUtil.getString(TWPictureUtil.getNomalPicPath(tbcurriculum.getCourseUrl())));
			data.put("courseIntroduce", TWObjectUtil.getString(tbcurriculum.getCourseIntroduce()));
			data.put("courseGoodness", TWObjectUtil.getString(tbcurriculum.getCourseGoodness()));
			data.put("enterNeed", TWObjectUtil.getString(tbcurriculum.getEnterNeed()));
			data.put("studyGoal", TWObjectUtil.getString(tbcurriculum.getStudyGoal()));
			data.put("textbookCost", TWObjectUtil.getString(tbcurriculum.getTextbookCost()));
			data.put("courseBook", TWObjectUtil.getString(tbcurriculum.getCourseBook()));
			data.put("studyCosts", TWObjectUtil.getString(tbcurriculum.getStudyCosts()));
			data.put("direction", TWObjectUtil.getString(tbcurriculum.getDirection()));
			data.put("phone", TWObjectUtil.getString(tbcurriculum.getPhone()));
		 }
		return ResultJson.crateSuccJson(data);
	}
	// 修改学校
	public ResultJson updateSchool(Integer userinfoId, String studentSchool) {
		String result = new UpdateDao().updateSchool(userinfoId, studentSchool,
				new Timestamp(new Date().getTime()), "studentSchool");
		if ("success".equals(result))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "修改失败");
	}
	// 修改班级
	public ResultJson updateClass(Integer userinfoId, String studentClass) {
		String result = new UpdateDao().updateClass(userinfoId, studentClass,
				new Timestamp(new Date().getTime()), "studentClass");
		if ("success".equals(result))
			return ResultJson.crateSuccJson(new HashMap<String, Object>());
		else
			return ResultJson.createFailJson(-1, "修改失败");
	}
	// 查看练习列表
	/*public ResultJson findtbTestfinish(Integer userInfoId) {
		TbUserinfo userinfo = findDao.getTbUserinfo(userInfoId);
		TbUserinfo tbuserinfo = TWObjectUtil.getChildByParent(userinfo);
		ArrayList<Object> strList = new ArrayList<Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		if(userinfo!=null && userInfoId!=null){
			if(userinfo.getUserInfoRoot()==2){
				ArrayList<TbTestfinish> list = findDao.listTestfinish(tbuserinfo);
				for (TbTestfinish tbTestfinish : list) {
					TbTestfinishWeb tbtestfinishweb = new TbTestfinishWeb();
					TbTest tbtest = findDao.gettest(tbTestfinish.getTbTest().getTestId());
					tbtestfinishweb.setTestId(tbtest.getTestId());
					tbtestfinishweb.setTestName(tbtest.getTestName());
					tbtestfinishweb.setRightPercentage(tbTestfinish.getRightPercentage());
					String time = TWDataUtil.dateFormat(tbTestfinish.getTestfinishDate());
					tbtestfinishweb.setTestfinishDate(time);
					tbtestfinishweb.setIsfinish(tbTestfinish.getIsfinish());
					strList.add(tbtestfinishweb);
				} 
			}
			if(userinfo.getUserInfoRoot()==3){
				ArrayList<TbTestfinish> list = findDao.listTestfinish(userinfo);
				for (TbTestfinish tbTestfinish : list) {
					TbTestfinishWeb tbtestfinishweb = new TbTestfinishWeb();
					TbTest tbtest = findDao.gettest(tbTestfinish.getTbTest().getTestId());
					tbtestfinishweb.setTestName(tbtest.getTestName());
					tbtestfinishweb.setTestId(tbtest.getTestId());
					tbtestfinishweb.setRightPercentage(tbTestfinish.getRightPercentage());
					String time = TWDataUtil.dateFormat(tbTestfinish.getTestfinishDate());
					tbtestfinishweb.setTestfinishDate(time);
					tbtestfinishweb.setIsfinish(tbTestfinish.getIsfinish());
					strList.add(tbtestfinishweb);
				}
			}
		}
		data.put("schoolnoticele", strList);
		return ResultJson.crateSuccJson(data);
	}*/
	public ResultJson findtbTestfinish(Integer userInfoId) {
		DataBaseDaoImpl daoImpl=new DataBaseDaoImpl();
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<TbTestWeb> listOut = new ArrayList<TbTestWeb>();
		 TbUserinfo userinfo = new GogoServiceImpl().getObjectByClazz(TbUserinfo.class, userInfoId);
		 if(userinfo.getUserInfoRoot()==2)
			 userinfo=TWObjectUtil.getChildByParent(userinfo);
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("tbUserinfo", userinfo);
		 map.put("isValid", 1);
		List<TbUserinfoclass> list = daoImpl.getObjects(TbUserinfoclass.class, map);
		if(list==null||list.isEmpty())
			return ResultJson.crateNullJson("此用户无班级关联");
		for (TbUserinfoclass tbUserinfoclass : list) {
			TbCourse course = tbUserinfoclass.getTbCourse();
			Map<String, Object> map1=new HashMap<String, Object>();
			map1.put("tbCourse", course);
			map1.put("倒", "alterTime");
			List<TbTest> list1 = daoImpl.getObjects(TbTest.class, map1);
			if(list1==null||list1.isEmpty())
				continue;
			else{
				for (TbTest tbTest : list1) {
					listOut.add(tbTest.toListWeb(userinfo));
				}
			}
				
		}
		
		data.put("schoolnoticele", listOut);
		return ResultJson.crateSuccJson(data);
	}
	
	//市区
	public ResultJson listtbCity(){
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<TbCity> list = findDao.listCity();
		ArrayList<Object> strList = new ArrayList<Object>();
		for(TbCity tbCity : list) {
			TbCityWeb tbcityweb = new TbCityWeb();
			tbcityweb.setCityId(TWObjectUtil.getInteger(tbCity.getCityId()));
			tbcityweb.setCityName(TWObjectUtil.getString(tbCity.getCityName()));
			tbcityweb.setPinyin(TWObjectUtil.getString(tbCity.getSpell()));
			strList.add(tbcityweb);
		} 
		data.put("City", strList);
		return ResultJson.crateSuccJson(data);
	}
	public ResultJson findCity(String cityName){
		Map<String, Object> data = new HashMap<String, Object>();
		if(cityName.length()>2){
			TbCity tbcity = findDao.findTbCity(TWDataUtil.getStudentName(cityName));
			if(null != tbcity){
				data.put("cityId", TWObjectUtil.getInteger(tbcity.getCityId()));
			}else {
				return ResultJson.createFailJson(-1, "没有加盟学校的信息,请切换城市");
			}
		}else{
			TbCity tbcity = findDao.findTbCity(cityName);
			if(null != tbcity){
				data.put("cityId", TWObjectUtil.getInteger(tbcity.getCityId()));
			}else {
				return ResultJson.createFailJson(-1, "没有加盟学校的信息,请切换城市");
			}
		}
    	return ResultJson.crateSuccJson(data); 
	}
	// 城区
	public ResultJson findtbTown(Integer cityId){
		TbCity tbcity = findDao.findTbCity(cityId);
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<Object> strList = new ArrayList<Object>();
		if(cityId==null||cityId==0){
			ArrayList<TbTown> list1 = findDao.listtbTown();
			for(TbTown tbtown : list1) {
				TbTownWeb tbtownweb = new TbTownWeb();
				tbtownweb.setCityId(TWObjectUtil.getInteger(tbtown.getTownId()));
				tbtownweb.setCityName(TWObjectUtil.getString(tbtown.getTownName()));
				strList.add(tbtownweb);
			}
			data.put("tbtown", strList);
		}else {
			ArrayList<TbTown> list = findDao.listTown(tbcity);
			for(TbTown tbtown : list) {
				TbTownWeb tbtownweb = new TbTownWeb();
				tbtownweb.setCityId(TWObjectUtil.getInteger(tbtown.getTownId()));
				tbtownweb.setCityName(TWObjectUtil.getString(tbtown.getTownName()));
				strList.add(tbtownweb);
			} 
			data.put("tbtown", strList);
		}
		return ResultJson.crateSuccJson(data);
	}
	//城区下的所有班
//	public ResultJson findtbCourse(Integer townId){
//		TbTown tbTown = findDao.findTown(townId);
//		Map<String, Object> data = new HashMap<String, Object>();
//		ArrayList<TbCurriculum> list = findDao.listcurriculum(tbTown);
//		System.out.println(list.size());
//		ArrayList<Object> strList = new ArrayList<Object>();
//		if(townId==0){
//			ArrayList<TbCurriculum> list1 = findDao.listCurriculum();
//			for(TbCurriculum tbcurriculum : list1) {
//				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
//				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
//				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
//				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
//				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
//				strList.add(tbcurriculumweb);
//			} 
//			data.put("tbcurriculum", strList);
//		}else {
//		for(TbCurriculum tburriculum : list) {
//			TbBranchschool tbbranchschool = findDao.getbranchSchool(tburriculum.getTbBranchschool().getBranchSchoolId());
//			TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
//			tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
//			tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tburriculum.getCourseId()));
//			tbcurriculumweb.setCourseName(TWObjectUtil.getString(tburriculum.getCourseName()));
//			strList.add(tbcurriculumweb);
//		} 
//		data.put("tbcurriculum", strList);
//		}
//		return ResultJson.crateSuccJson(data);
//	}
	
	// 年段
	public ResultJson listtbGrade(){
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<Object> strList = new ArrayList<Object>();
			ArrayList<TbGrade> list = findDao.listGrade();
			for(TbGrade tbgrade : list) {
//				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbgrade.getTbBranchschool().getBranchSchoolId());
				TbGradeWeb tbgradeweb = new TbGradeWeb();	
				tbgradeweb.setBranchschoolId(TWObjectUtil.getInteger(0));
				tbgradeweb.setGradeId(TWObjectUtil.getInteger(tbgrade.getGradeId()));
				tbgradeweb.setGradeName(TWObjectUtil.getString(tbgrade.getGradeName()));
				strList.add(tbgradeweb);
			}
			data.put("tbcourse", strList);
		return ResultJson.crateSuccJson(data);
	}
	
	//推荐课程
	public ResultJson listtbCourse(Integer branchschoolId){
		Map<String, Object> data = new HashMap<String, Object>();
		if(branchschoolId!=0){
			TbBranchschool tbbranchschool = findDao.findByBranchschool(branchschoolId);
//			TbSchool tbschool = findDao.findSchool(tbbranchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.listCurriculumtuijian(tbbranchschool);
			ArrayList<Object> strList = new ArrayList<Object>();
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool1 = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb curriculumweb = new TbCurriculumWeb();
				curriculumweb.setSchoolName(school.getSchoolName());
				curriculumweb.setBranchschoolName(tbbranchschool1.getBraschName());
				curriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool1.getBranchSchoolId()));
				curriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				curriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(curriculumweb);
				data.put("tbcourse", strList);
			}
		} else{
			ArrayList<TbCurriculum> list1 = findDao.listtuijian();
			ArrayList<Object> strList1 = new ArrayList<Object>();
			for(TbCurriculum tbcurriculum : list1) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool1 = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb curriculumweb = new TbCurriculumWeb();
				curriculumweb.setSchoolName(school.getSchoolName());
				curriculumweb.setBranchschoolName(tbbranchschool1.getBraschName());
				curriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool1.getBranchSchoolId()));
				curriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				curriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList1.add(curriculumweb);
				data.put("tbcourse", strList1);
			}
		}
		return ResultJson.crateSuccJson(data);
	}
	
	// 所有课科目(选课报班)
	public ResultJson listcourse(Integer branchschoolId){
		Map<String, Object> data = new HashMap<String, Object>();
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		if(branchschoolId==null||branchschoolId==0){
		ArrayList<TbSubject> list = findDao.listsubject();
		ArrayList<Object> strList = new ArrayList<Object>();
		for(TbSubject subject : list) {
			TbSubjectWeb tbsubjectwen = new TbSubjectWeb();
			tbsubjectwen.setSubjectId(TWObjectUtil.getInteger(subject.getSubjectId()));
			tbsubjectwen.setSubjectName(TWObjectUtil.getString(subject.getSubjectName()));
			strList.add(tbsubjectwen);
			ArrayList<TbLesson> list1 = findDao.tofindBylesson(subject);
			ArrayList<Object> strList1 = new ArrayList<Object>();
			for(TbLesson lesson : list1) {
				TbSchool school = findDao.findBySchool(lesson.getTbSchool().getSchoolId());
				TbLessonWeb lessonweb = new TbLessonWeb();
				lessonweb.setSchoolName(school.getSchoolName());
				lessonweb.setId(TWObjectUtil.getInteger(lesson.getId()));
				lessonweb.setLessonName(TWObjectUtil.getString(lesson.getLessonName()));
				strList1.add(lessonweb);
				map.put(subject.getSubjectId(), strList1);
			}
		}
		data.put("tblesson", map);
		data.put("tbsubject", strList);
		}else{
			if(branchschoolId!=null || branchschoolId!=0){
				TbBranchschool tbbranchschool = findDao.findByBranchschool(branchschoolId);
				TbSchool tbschool = findDao.findBySchool(tbbranchschool.getTbSchool().getSchoolId());
				List<TbLesson> list = findDao.listLesson(tbschool);
				List<Integer> allid = new ArrayList<Integer>();
			for(TbLesson lesson1:list){
					allid.add(lesson1.getTbSubject().getSubjectId());
				}
			List<Integer> difid = new ArrayList<Integer>();
			ArrayList<Object> strList = new ArrayList<Object>();
			for(Integer id1:allid){
			  if(!difid.contains(id1)){
					difid.add(id1);
					TbSubject tbsubject = findDao.findBysubject(id1);
					TbLessonWeb lessonweb = new TbLessonWeb();
					lessonweb.setSubjectId(TWObjectUtil.getInteger(tbsubject.getSubjectId()));
					lessonweb.setSubjectName(TWObjectUtil.getString(tbsubject.getSubjectName()));
					ArrayList<TbLesson> list1 = findDao.findBylesson(tbsubject,tbschool);
					ArrayList<Object> strList1 = new ArrayList<Object>();
				for(TbLesson lesson : list1) {
					TbSchool school = findDao.findBySchool(lesson.getTbSchool().getSchoolId());
					TbBranchschool branschool = findDao.findByBranchschool(branchschoolId);
					TbLessonWeb tblessonweb = new TbLessonWeb();
					tblessonweb.setSchoolName(school.getSchoolName());
					lessonweb.setBranchName(branschool.getBraschName());
					tblessonweb.setId(TWObjectUtil.getInteger(lesson.getId()));
					tblessonweb.setLessonName(TWObjectUtil.getString(lesson.getLessonName()));
					strList1.add(tblessonweb);
					map.put(id1, strList1);
				}
					strList.add(lessonweb);
				}
				data.put("tblesson", map);
				data.put("tbsubject", strList);
				}
			}
		}
			
//			ArrayList<Object> strList = new ArrayList<Object>();
//			for(TbLesson tblesson : list) {
//				TbSubject tbsubject = findDao.findBysubject(tblesson.getTbSubject().getSubjectId());
//				TbLessonWeb lessonweb = new TbLessonWeb();
//				lessonweb.setSubjectId(TWObjectUtil.getInteger(tbsubject.getSubjectId()));
//				lessonweb.setSubjectName(TWObjectUtil.getString(tbsubject.getSubjectName()));
//				ArrayList<TbLesson> list1 = findDao.findBylesson(tbsubject);
//				ArrayList<Object> strList1 = new ArrayList<Object>();
//				for(TbLesson lesson : list1) {
//					TbLessonWeb tblessonweb = new TbLessonWeb();
//					tblessonweb.setId(TWObjectUtil.getInteger(lesson.getId()));
//					tblessonweb.setLessonName(TWObjectUtil.getString(lesson.getLessonName()));
//					strList1.add(tblessonweb);
//					map.put(tblesson.getTbSubject().getSubjectId(), strList1);
//				}
//				strList.add(lessonweb);
//			}
//			data.put("tblesson", map);
//			data.put("tbsubject", strList);
//			}
//		}
		return ResultJson.crateSuccJson(data);
	}
	
	
	// 所有成绩
	public ResultJson listcourseScore(){
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<TbCurriculum> list = findDao.listtbcourse();
		ArrayList<Object> strList = new ArrayList<Object>();
		for(TbCurriculum tbcourse : list) {
			TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcourse.getTbBranchschool().getBranchSchoolId());
			TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
			tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
			tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcourse.getCourseId()));
			tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcourse.getCourseScore()));
			strList.add(tbcurriculumweb);
		} 
		data.put("tbcourse", strList);
		return ResultJson.crateSuccJson(data);
	}
	
	// 根据科目查找
	public ResultJson listcurriculum(Integer townId){
		TbTown tbtown = findDao.findTown(townId);
		Map<String, Object> data = new HashMap<String, Object>();
		if(townId!=0){
			ArrayList<TbCurriculum> list = findDao.list(tbtown);
			ArrayList<Object> strList = new ArrayList<Object>();
			List<Integer> allid = new ArrayList<Integer>();
			for(TbCurriculum lesson1:list){
				allid.add(lesson1.getTbSubject().getSubjectId());
			}
			List<Integer> difid = new ArrayList<Integer>();
			for(Integer id1:allid){
			  if(!difid.contains(id1)){
				difid.add(id1);
				TbSubject tbsubject = findDao.findBysubject(id1);
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSubjectId(TWObjectUtil.getInteger(tbsubject.getSubjectId()));
				tbcurriculumweb.setSubjectName(TWObjectUtil.getString(tbsubject.getSubjectName()));
				strList.add(tbcurriculumweb);
			  }
			}
			data.put("tbsubject", strList);
		}
		if(townId==0){
			ArrayList<TbSubject> list = findDao.listsubject();
			ArrayList<Object> strList = new ArrayList<Object>();
			for(TbSubject tbsubject : list) {
				TbSubjectWeb tbsubjectweb = new TbSubjectWeb();
				tbsubjectweb.setSubjectId(TWObjectUtil.getInteger(tbsubject.getSubjectId()));
				tbsubjectweb.setSubjectName(TWObjectUtil.getString(tbsubject.getSubjectName()));
				strList.add(tbsubjectweb);
			} 
			data.put("tbsubject", strList);
		}
		return ResultJson.crateSuccJson(data);
	}
	
	// 找合适提交
	public ResultJson newtijiao(Integer branchschoolId,Integer cityId,Integer townId, Integer subjectId,Integer gradeId,String courseScore){
		Map<String, Object> data = new HashMap<String, Object>();
		TbTown tbtown = findDao.findTown(townId);
		TbBranchschool branchschool = findDao.findByBranchschool(branchschoolId);

		TbSubject tbsubject = findDao.findBysubject(subjectId);
		ArrayList<Object> strList = new ArrayList<Object>();
		
		if(branchschoolId!=0 && cityId!=0 && townId!=0 && subjectId!=0 && gradeId!=0 && courseScore!=null && !"".equals(courseScore)){
			String grades=gradeId.toString();
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
				ArrayList<TbCurriculum> list = findDao.tijiaocourse(tbschool,tbTowns,tbtown,grades,tbsubject,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId!=0 && cityId!=0 && townId!=0 && subjectId==0 && gradeId!=0 && !"".equals(courseScore)){
			String grades=gradeId.toString();
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
				ArrayList<TbCurriculum> list = findDao.tijiaocourse(tbschool,tbTowns,tbtown,grades,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId==0 && cityId!=0 && townId==0 && subjectId==0 && gradeId==0 && courseScore.equals("0")){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
			List<TbCurriculum> list = findDao.listtbc(tbTowns);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					if(tbbranchschool==null)
						tbbranchschool=new TbBranchschool();
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				} 
			}
		}
	   if(branchschoolId!=0 && townId==0 && cityId!=0 && !"".equals(courseScore) && subjectId!=0 && gradeId!=0 ){
		   String grades = gradeId.toString();
		   TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
			   TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
			   ArrayList<TbCurriculum> list = findDao.listtsg(tbschool,tbTowns,tbsubject,grades,courseScore);
					for(TbCurriculum tbcurriculum : list) {
						TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
						TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
						TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
						tbcurriculumweb.setSchoolName(school.getSchoolName());
						tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
						tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
						tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
						tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
						strList.add(tbcurriculumweb);
					} 
				}
	   		}
	   if(branchschoolId==0 && townId==0 && cityId!=0 && !"".equals(courseScore) && subjectId!=0 && gradeId!=0 ){
		   String grades=gradeId.toString();
		   TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.listtsg(tbTowns,tbsubject,grades,courseScore);
					for(TbCurriculum tbcurriculum : list) {
						TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
						TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
						TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
						tbcurriculumweb.setSchoolName(school.getSchoolName());
						tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
						tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
						tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
						tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
						strList.add(tbcurriculumweb);
					} 
				}
			}
		if(branchschoolId==0 && cityId!=0 && !"".equals(courseScore) && subjectId!=0 && townId!=0 && gradeId!=0 ){
			  String grades=gradeId.toString();
		  	TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.listtsg(tbTowns,tbtown,tbsubject,grades,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				} 
			}
		}
//		if(branchschoolId==0 && cityId==0 && townId!=0 && subjectId==0 && gradeId==0 && !courseScore.equals("")){
//			ArrayList<TbCurriculum> list = findDao.listcurriculum(tbtown,courseScore);
//			for(TbCurriculum tbcurriculum : list) {
//				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
//				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
//				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
//				tbcurriculumweb.setSchoolName(school.getSchoolName());
//				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
//				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
//				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
//				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
//				strList.add(tbcurriculumweb);
//			} 
//		}
		if(branchschoolId==0 && cityId!=0 && townId==0 && gradeId==0 && subjectId==0 &&  !"0".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.tijiaocourse2(tbTowns,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId!=0 && cityId!=0 && townId==0 && gradeId==0 && subjectId==0 &&  !"".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
				ArrayList<TbCurriculum> list = findDao.tijiaocourse2(tbTowns,tbschool,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId!=0 && cityId!=0 && townId!=0 && gradeId==0 && subjectId==0 &&  !"".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
				ArrayList<TbCurriculum> list = findDao.tijiaocourse2(tbTowns,tbschool,tbtown,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId==0 && cityId!=0 && townId!=0 && gradeId==0 && subjectId==0 &&  !"".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.tijiaocourse2(tbTowns,tbtown,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId==0 && cityId!=0 && townId!=0 && gradeId==0 && subjectId!=0 &&  !"".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.tijiaocourse2(tbTowns,tbtown,tbsubject,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId==0 && cityId!=0 && townId==0 && gradeId==0 && subjectId!=0 &&  !"".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.tijiaocourse2(tbTowns,tbsubject,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId!=0 && cityId!=0 && townId==0 && gradeId==0 && subjectId!=0 &&  !"".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
				ArrayList<TbCurriculum> list = findDao.tijiaocourse2(tbTowns,tbschool,tbsubject,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				}
			}
		}
		if(branchschoolId==0 && cityId!=0 && townId==0 && gradeId!=0 && subjectId==0 && !"".equals(courseScore) && courseScore!=null){
			String grades = gradeId.toString();
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.tijiaocourse3(tbTowns,grades,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
					}
				}
			}
		if(branchschoolId==0 && cityId!=0 && townId!=0 && gradeId!=0 && subjectId==0 && !"".equals(courseScore) && courseScore!=null){
			String grades = gradeId.toString();
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				ArrayList<TbCurriculum> list = findDao.tijiaocourse3(tbTowns,tbtown,grades,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
					}
				}
			}
		if(branchschoolId!=0 && cityId!=0 && townId!=0 && gradeId==0 && subjectId!=0 && !"".equals(courseScore)){
			TbCity tbCitys = findDao.findTbCity(cityId);
			List<TbTown> tbTowns = findDao.findTbtown(tbCitys);
			if(tbTowns.size()!=0){
				TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
				ArrayList<TbCurriculum> list = findDao.tijiaocourse3(tbTowns,tbschool,tbtown,tbsubject,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
					}
				}
			}
		data.put("tbcourse", strList);
		return ResultJson.crateSuccJson(data);
	}
	
	public ResultJson listGradecourseName(String courseName){
			Map<String, Object> data = new HashMap<String, Object>();
			ArrayList<TbCurriculum> list = findDao.listCourseName(courseName);
			ArrayList<Object> strList = new ArrayList<Object>();
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			} 
			data.put("tbcourse", strList);
			return ResultJson.crateSuccJson(data);
		}
	
	// 选课报班课程下面的班
	public ResultJson listgradecourse(Integer branchschoolId,Integer subjectId,Integer id){
		Map<String, Object> data = new HashMap<String, Object>();
		if(branchschoolId==0 && subjectId!=0 && id!=0){
		TbSubject tbsubject = findDao.findBysubject(subjectId);
		TbLesson lesson = findDao.findBylesson(id);
		ArrayList<TbCurriculum> list = findDao.listGradeCourse(tbsubject,lesson);
		ArrayList<Object> strList = new ArrayList<Object>();
		for(TbCurriculum tbcurriculum : list) {
			TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
			TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
			TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
			tbcurriculumweb.setSchoolName(school.getSchoolName());
			tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
			tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
			tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
			tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
			strList.add(tbcurriculumweb);
		}
		data.put("tbcourse", strList);
		}else{
			TbSubject tbsubject = findDao.findBysubject(subjectId);
			TbLesson lesson = findDao.findBylesson(id);
			TbBranchschool tbbranchschool = findDao.findByBranchschool(branchschoolId);
			TbSchool tbschool = findDao.findSchool(tbbranchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.listGradeCourse(tbschool,tbsubject,lesson);
			ArrayList<Object> strList = new ArrayList<Object>();
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool branchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(branchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			} 
			data.put("tbcourse", strList);
		}
		
		return ResultJson.crateSuccJson(data);
	}
	
	// 找回密码
	public ResultJson password(String userName,String userinfoEmail){
		Map<String, Object> data = new HashMap<String, Object>();
		TbUser tbuser = findDao.selectUserinfoName(userName);
		if(tbuser!=null && tbuser.getTbUserinfo()!=null){
			TbUserinfo tbuserinfo = findDao.selecttbuser(tbuser.getTbUserinfo().getUserInfoId(),userinfoEmail);
			String rd = RandomStringUtils.randomNumeric(6);
			findDao.updatePassword(TWObjectUtil.getInteger(tbuser.getUserId()),rd);
			SendEmail se = new SendEmail();
			if(tbuserinfo!=null)
			se.sendEmail(TWObjectUtil.getString(tbuserinfo.getUserInfoEmail()),rd);
		}else {
			return ResultJson.createFailJson(-1, "你输入的用户名或邮箱不对");
		}
		return ResultJson.crateSuccJson(data);
	}
	
	 //  找适合
	public ResultJson tijiao(Integer branchschoolId,Integer cityId,Integer townId, Integer subjectId,Integer gradeId,String courseScore){
		Map<String, Object> data = new HashMap<String, Object>();
		TbTown tbtown = findDao.findTown(townId);
		TbBranchschool branchschool = findDao.findByBranchschool(branchschoolId);

		TbSubject tbsubject = findDao.findBysubject(subjectId);
		ArrayList<Object> strList = new ArrayList<Object>();
		
		if(branchschoolId!=0 && cityId==0 && townId!=0 && subjectId!=0 && gradeId!=0 && courseScore!=null && !"".equals(courseScore)){
			String grades=gradeId.toString();
			TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.tijiao1(tbschool,tbtown,grades,tbsubject,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId!=0 && cityId==0 && townId!=0 && subjectId==0 && gradeId!=0 && !"".equals(courseScore)){
			String grades=gradeId.toString();
			TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.tijiao2(tbschool,tbtown,grades,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId==0 && cityId==0 && townId==0 && subjectId==0 && gradeId==0 && courseScore.equals("0")){
			ArrayList<TbCurriculum> list = findDao.listtbcourse();
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				if(tbbranchschool==null)
					tbbranchschool=new TbBranchschool();
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			} 
		}

	   if(branchschoolId!=0 && townId==0 && cityId==0 && !"".equals(courseScore) && subjectId!=0 && gradeId!=0 ){
		   String grades = gradeId.toString();
		   TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
		   ArrayList<TbCurriculum> list = findDao.tijiao4(tbschool,tbsubject,grades,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				} 
			}
	   if(branchschoolId==0 && townId==0 && cityId==0 && !"".equals(courseScore) && subjectId!=0 && gradeId!=0 ){
		   String grades=gradeId.toString();
		   ArrayList<TbCurriculum> list = findDao.tijiao3(tbsubject,grades,courseScore);
				for(TbCurriculum tbcurriculum : list) {
					TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
					TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
					TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
					tbcurriculumweb.setSchoolName(school.getSchoolName());
					tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
					tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
					tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
					tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
					strList.add(tbcurriculumweb);
				} 
			}
		if(branchschoolId==0 && cityId==0 && !"".equals(courseScore) && subjectId!=0 && townId!=0 && gradeId!=0 ){
			  String grades=gradeId.toString();
			  ArrayList<TbCurriculum> list = findDao.tijiao5(tbtown,tbsubject,grades,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			} 
		}
//		if(branchschoolId==0 && cityId==0 && townId!=0 && subjectId==0 && gradeId==0 && !courseScore.equals("")){
//			ArrayList<TbCurriculum> list = findDao.listcurriculum(tbtown,courseScore);
//			for(TbCurriculum tbcurriculum : list) {
//				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
//				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
//				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
//				tbcurriculumweb.setSchoolName(school.getSchoolName());
//				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
//				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
//				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
//				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
//				strList.add(tbcurriculumweb);
//			} 
//		}
		if(branchschoolId==0 && cityId==0 && townId==0 && gradeId==0 && subjectId==0 &&  !"0".equals(courseScore)){
			ArrayList<TbCurriculum> list = findDao.tijiaocourse3(courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId!=0 && cityId==0 && townId==0 && gradeId==0 && subjectId==0 &&  !"".equals(courseScore)){
			TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.tijiaocourse4(tbschool,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId!=0 && cityId==0 && townId!=0 && gradeId==0 && subjectId==0 &&  !"".equals(courseScore)){
			TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.tijiaocourse6(tbschool,tbtown,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId==0 && cityId==0 && townId!=0 && gradeId==0 && subjectId==0 &&  !"".equals(courseScore)){
			ArrayList<TbCurriculum> list = findDao.tijiaocourse7(tbtown,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId==0 && cityId==0 && townId!=0 && gradeId==0 && subjectId!=0 &&  !"".equals(courseScore)){
			ArrayList<TbCurriculum> list = findDao.tijiaocourse8(tbtown,tbsubject,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId==0 && cityId==0 && townId==0 && gradeId==0 && subjectId!=0 &&  !"".equals(courseScore)){
			ArrayList<TbCurriculum> list = findDao.tijiaocourse9(tbsubject,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId!=0 && cityId==0 && townId==0 && gradeId==0 && subjectId!=0 &&  !"".equals(courseScore)){
			TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.tijiaocourse11(tbschool,tbsubject,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
			}
		}
		if(branchschoolId==0 && cityId==0 && townId==0 && gradeId!=0 && subjectId==0 && !"".equals(courseScore) && courseScore!=null){
			String grades = gradeId.toString();
			ArrayList<TbCurriculum> list = findDao.tijiaocourse12(grades,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
				}
			}
		if(branchschoolId==0 && cityId==0 && townId!=0 && gradeId!=0 && subjectId==0 && !"".equals(courseScore) && courseScore!=null){
			String grades = gradeId.toString();
			ArrayList<TbCurriculum> list = findDao.tijiaocourse13(tbtown,grades,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
				}
			}
		if(branchschoolId!=0 && cityId==0 && townId!=0 && gradeId==0 && subjectId!=0 && !"".equals(courseScore)){
			TbSchool tbschool = findDao.findBySchool(branchschool.getTbSchool().getSchoolId());
			ArrayList<TbCurriculum> list = findDao.tijiaocourse14(tbschool,tbtown,tbsubject,courseScore);
			for(TbCurriculum tbcurriculum : list) {
				TbSchool school = findDao.findBySchool(tbcurriculum.getTbSchool().getSchoolId());
				TbBranchschool tbbranchschool = findDao.getbranchSchool(tbcurriculum.getTbBranchschool().getBranchSchoolId());
				TbCurriculumWeb tbcurriculumweb = new TbCurriculumWeb();
				tbcurriculumweb.setSchoolName(school.getSchoolName());
				tbcurriculumweb.setBranchschoolName(tbbranchschool.getBraschName());
				tbcurriculumweb.setBranchschoolId(TWObjectUtil.getInteger(tbbranchschool.getBranchSchoolId()));
				tbcurriculumweb.setCourseId(TWObjectUtil.getInteger(tbcurriculum.getCourseId()));
				tbcurriculumweb.setCourseName(TWObjectUtil.getString(tbcurriculum.getCourseName()));
				strList.add(tbcurriculumweb);
				}
			}
		data.put("tbcourse", strList);
		return ResultJson.crateSuccJson(data);
	}

	public ResultJson password01(String userName, String userinfoEmail) {
		Map<String, Object> data = new HashMap<String, Object>();
		TbUser tbuser = findDao.selectUserinfoName01(userName);
		if(tbuser==null)
			return ResultJson.crateNullJson();
		TbUserinfo tbuserinfo = findDao.selecttbuser01(tbuser.getTbUserinfo().getUserInfoId(),userinfoEmail);
		if(tbuserinfo==null)
			return ResultJson.crateNullJson();
		if(tbuser!=null && tbuserinfo!=null){
			String rd = RandomStringUtils.randomNumeric(6);
			findDao.updatePassword(TWObjectUtil.getInteger(tbuser.getUserId()),rd);
			SendEmail se = new SendEmail();
			se.sendEmail(TWObjectUtil.getString(tbuserinfo.getUserInfoEmail()),rd);
		}else {
			return ResultJson.createFailJson(-1, "你输入的用户名或邮箱不对");
		}
		return ResultJson.crateSuccJson(data);
	}
	
	
	
}