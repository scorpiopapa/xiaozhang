package com.qiYang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Security;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.qiYang.service.GogoServiceImpl;
import com.qiYang.service.SecondBackstage;
import com.qiYang.service.SecondaryDevelopmen;
import com.qiYang.util.JsonTools;
import com.qiYang.util.Page;
import com.qiYang.util.ResultJson;
import com.qiYang.util.TWDataUtil;

public class GogoServletImpl extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2453589162591019208L;
	private static String path;
	private GogoServiceImpl gogoservice;
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String encoding = request.getCharacterEncoding();
		response.setContentType("text/json;charset=" + encoding);
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		PrintWriter pw = response.getWriter();
		
		// System.out.println(encoding);

		
		
		
		String userInfoId = request.getParameter("userinfoId");
		String teacherIdString = request.getParameter("teacherId");
		String action_flag = request.getParameter("action_flag");
		String childLongitudeString = request.getParameter("childLongitude");
		String childLatitudeString = request.getParameter("childLatitude");
		String currentPageString = request.getParameter("currentPage");
		String countPerPageString = request.getParameter("countPerPage");
		String courseIdString = request.getParameter("courseId");
		String subjectIdString = request.getParameter("subjectId");
		String classNoticeIdString = request.getParameter("classNoticeId");
		String attendanceIds = request.getParameter("attendanceIds");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String deletePhoto = request.getParameter("deletePhoto");
		String noticeIds = request.getParameter("noticeIds");
		String branchschoolString = request.getParameter("branchschoolId");
		String schoolIdString = request.getParameter("schoolId");
		String dateString = request.getParameter("date");
		String branchschoolNoticeIdString = request.getParameter("branchschoolNoticeId");
		String infonoticeIdString = request.getParameter("infonoticeId");
		String testIdString = request.getParameter("testId");
		String machine = request.getParameter("machine");
		String type = request.getParameter("type");
		String messageContent = request.getParameter("messageContent");
		String messageFrom = request.getParameter("messageFrom");
		String createDate = request.getParameter("createDate");
		String messageTo = request.getParameter("messageTo");
		String curriculumI = request.getParameter("curriculumId");
		String versions = request.getParameter("versions");
		String practiceId = request.getParameter("practiceId");
		String answer = request.getParameter("answer");
		String iMEI = request.getParameter("iMEI");
		String hyperlink =null;
		String photoType =null;
		//图片获取 start
		List<FileItem> items = new ArrayList<FileItem>();
		@SuppressWarnings("deprecation")
		boolean isMultipart = FileUploadBase.isMultipartContent(request);
//		System.out.println("isMultipart"+isMultipart);
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) {
				if ("action_flag".equals(item.getFieldName()))
					action_flag = item.getString("utf-8");
				if ("userinfoId".equals(item.getFieldName()))
					userInfoId = item.getString("utf-8");
				if ("hyperlink".equals(item.getFieldName()))
					hyperlink = item.getString("utf-8");
				if ("photoType".equals(item.getFieldName()))
					photoType = item.getString("utf-8");
			}
		}
		}
		//图片获取 end
		
		Integer userinfoId = null;
		Double childLongitude = null;
		Double childLatitude = null;
		Integer currentPage = null;
		Integer countPerPage = null;
		Integer courseId = null;
		Integer classNoticeId = null;
		Integer branchschoolId = null;
		Integer teacherId = null;
		Integer branchschoolNoticeId = null;
		Integer infonoticeId = null;
		Integer testId = null;
		Integer curriculumId = null;
		Integer schoolId = null;
		Integer practiceId01=null;
		Date date=null;
		if (StringUtils.isNotBlank(userInfoId))
			userinfoId = Integer.parseInt(userInfoId);
		if (StringUtils.isNotBlank(teacherIdString))
			teacherId = Integer.parseInt(teacherIdString);
		if (StringUtils.isNotBlank(childLongitudeString))
			childLongitude = Double.parseDouble(childLongitudeString);
		if (StringUtils.isNotBlank(childLatitudeString))
			childLatitude = Double.parseDouble(childLatitudeString);
		if (StringUtils.isNotBlank(currentPageString))
			currentPage = Integer.parseInt(currentPageString);
		if (StringUtils.isNotBlank(countPerPageString))
			countPerPage = Integer.parseInt(countPerPageString);
		if (StringUtils.isNotBlank(courseIdString))
			courseId = Integer.parseInt(courseIdString);
		if (StringUtils.isNotBlank(classNoticeIdString))
			classNoticeId = Integer.parseInt(classNoticeIdString);
		if (StringUtils.isNotBlank(branchschoolString))
			branchschoolId= Integer.parseInt(branchschoolString);
		if (StringUtils.isNotBlank(subjectIdString)) {
		}
		if (StringUtils.isNotBlank(branchschoolNoticeIdString))
			branchschoolNoticeId= Integer.parseInt(branchschoolNoticeIdString);
		if (StringUtils.isNotBlank(infonoticeIdString))
			infonoticeId= Integer.parseInt(infonoticeIdString);
		if (StringUtils.isNotBlank(curriculumI))
			curriculumId= Integer.parseInt(curriculumI);
		if (StringUtils.isNotBlank(testIdString))
			testId= Integer.parseInt(testIdString);
		if (StringUtils.isNotBlank(schoolIdString))
			schoolId= Integer.parseInt(schoolIdString);
	
		if (StringUtils.isNotBlank(practiceId))
			practiceId01= Integer.parseInt(practiceId);
		if (StringUtils.isNotBlank(dateString)){
			try {
				date=DateUtils.parseDate(dateString, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page page = new Page();
		String basePicUrl = request.getSession().getServletContext()
				.getRealPath("images/").replace("\\", "/");
//		System.out.println("basePicUrl--->"+basePicUrl);
		page.setCountPerPage(countPerPage);
		page.setCurrentPage(currentPage);
		String jsonSting = "";
		//		System.out.println("action_flag:--->" + action_flag+"--------------------------------------");
		if (StringUtils.isBlank(action_flag)) {
//			System.out.println("action_flag为空，方法指定不明确");
			jsonSting = JsonTools.rendJson(ResultJson.createFailJson(-1,
					"action_flag为空，方法指定不明确"));
		}
		// 手机登录页面及首页页面
		else if ("phoneLogin".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.phoneLogin(request.getParameter("userName"),
					request.getParameter("userPassword")));
		else if ("phoneLogin01".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.phoneLogin01(request.getParameter("userName"),
					request.getParameter("userPassword"),iMEI));
		// 学生位置标识
		else if ("childLocation".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.childLocation(
					userinfoId, childLongitude, childLatitude));
		else if ("childLocation01".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.childLocation01(
					userinfoId, childLongitude, childLatitude,iMEI));
		else if ("childLocation02".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().childLocation02(
					request.getParameter("userinfoId"),request.getParameter("type")));
		// 版本
		else if ("versions".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.versions());
		// 更多
		else if ("theMore".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.theMore(type));
		// 我的同事
		else if ("myWorkmate".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.myWorkmate(page,
					userinfoId));
		// 我的同学
		else if ("mySchoolmate".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.mySchoolmate(page,userinfoId,courseId));
		// 我的班级(老师端)
		else if ("myClass".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.myClass(userinfoId));
		//我的班级（学生端跟家长端）
		else if ("myClassStudent".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.myClassStudent(branchschoolId,userinfoId));
		// 单位班级学生
		else if ("classToStudent".equals(action_flag))
			jsonSting = courseId!=null?JsonTools.rendJson(gogoservice.classToStudent(page,courseId,null)):JsonTools.rendJson(gogoservice.classToStudent(page,null,branchschoolId));
		// 到离校-单位班级学生
		else if ("tbAttendanceStudent".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.tbAttendanceStudent(courseId));
		//到离校-单位班级学生
		else if ("tbAttendanceUpdate".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.tbAttendanceUpdate(attendanceIds,courseId,userinfoId,basePicUrl));
		//到离校-离校
		else if ("outSchool".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.outSchool(attendanceIds,courseId,userinfoId,basePicUrl));
		//班级通知列表
		else if ("classNoticeList".equals(action_flag))
			jsonSting =courseId!=null? JsonTools.rendJson(gogoservice.classNoticeList(page, courseId,null)):JsonTools.rendJson(gogoservice.classNoticeList(page, null,branchschoolId));
		//班级通知详情
		else if ("classNoticeDetail".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.classNoticeDetail(classNoticeId));
		//班级通知发布
		else if ("addTbClassnotices".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.addTbClassnotices(courseId, userinfoId, title, content,basePicUrl));
		//学校公告发布
		else if ("addBranchschoolnotices".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.addBranchschoolnotices(userinfoId, title, content,basePicUrl));
		//学校公告修改
		else if ("updateBranchschoolnotices".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.updateBranchschoolnotices(branchschoolNoticeId,userinfoId,basePicUrl,title,content));
		//班级通知修改
		else if ("classNoticeDetailUpdate".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.classNoticeDetailUpdate(classNoticeId,userinfoId,title,content,basePicUrl));
		//相册批量删除
		else if ("deleteUserphotoToJson".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.deleteUserphotoToJson(deletePhoto));
		//头像上传
		else if ("updateGravatarToJson".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.updateGravatarToJson(items,null, userinfoId, basePicUrl));
		//相册上传
		else if ("userphotoAddToJson".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.userphotoAddToJson(items,null, userinfoId, basePicUrl));
		//家长-在校状态
		else if("parentNotice".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.parentNotice(page,userinfoId));
		//删除推送通知
		else if("deleteParentNotice".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.deleteParentNotice(noticeIds));
		//我的老师
		else if("myTeacher".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.myTeacher(branchschoolId,userinfoId));
		//班级选择页面（校长）
		else if("classSelect".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.classSelect(branchschoolId));
		//工作汇总（校长）
		else if("workCollect".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.workCollect(teacherId,dateString));
		//到离校统计（校长）
		else if("statistics".equals(action_flag))
			jsonSting = courseId!=null?JsonTools.rendJson(gogoservice.statistics(null,courseId,date)):JsonTools.rendJson(gogoservice.statistics(branchschoolId,null,date));
		//信息公告列表
		else if("infonoticeList".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.infonoticeList(page));
		//信息公告详情
		else if("infonoticeDetail".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.infonoticeDetail(infonoticeId));
		//在线练习
		else if("onLineTest".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.onLineTest(page,testId,userinfoId));
		//个人习题
		else if("addTbHistoryquestionPC".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.addTbHistoryquestionPC(answer,practiceId01,testId,userinfoId));
		
		else if("onLineTestPC".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.onLineTestPC(page,testId,userinfoId));
		//vip是否获取
		else if("vipTime".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.vipTime(branchschoolId,userinfoId));
		//校区地图
		else if("CampusMap".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.campusMap(branchschoolId));
		else if("CampusMapNew".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.campusMapNew(branchschoolId));
		else if("CampusMap02".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.campusMap02(branchschoolId,childLongitude,childLatitude));
		else if("CampusMap03".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.campusMap03(branchschoolId,childLongitude,childLatitude,request.getParameter("range")));
		//校区地图分校详情
		else if("branchschoolDetail".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.branchschoolDetail(branchschoolId));
		//校区地图总校详情
		else if("schoolDetail".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.schoolDetail(schoolId));
		//订单资费查询
		else if("findCharge".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.findCharge(branchschoolId));
		//课程Id查询旗下的班级
		else if("courseToClass".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.courseToClass(curriculumId));
		//课程详情
		else if("curriculumDetail".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.curriculumDetail(curriculumId));
		//新增聊天
		else if("addChat".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.addChat(messageContent,messageFrom,createDate,messageTo));
		//设备码核实
		else if ("machineCheck".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.machineCheck(userinfoId, machine, type));
		//设备码删除
		else if ("machineDelete".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.machineDelete(userinfoId, machine));
		//版本升级与否
		else if ("updateVersions".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.updateVersions(type,versions,basePicUrl));
		//20131106增加：校掌IPAD端接口
		else if ("recentLinkman".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.recentLinkman(request.getParameter("userNames")));
		//20131107增加：学校公告端接口
		else if ("schoolNoticeList".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.schoolNoticeList(page,branchschoolId));
		//20131108增加：投诉处理接口
		else if ("complainPage".equals(action_flag))
			jsonSting = JsonTools.rendJson(gogoservice.complainPage(page,branchschoolId,courseId,userinfoId));
		//20140409增加：获取城市跟年级
		else if ("cityAndGrage".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().cityAndGrage());
		//20140410增加：手机家长用户注册接口
		else if ("register".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().register(request.getParameter("phone"),request.getParameter("password"),request.getParameter("email"),request.getParameter("gradeId"),request.getParameter("cityId")));
		//20140414增加：手机孩子注册接口
		else if ("registerChild".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().registerChild(request.getParameter("phone"),request.getParameter("password"),request.getParameter("email"),request.getParameter("gradeId"),request.getParameter("sex"),request.getParameter("userinfoId")));
		//20140415增加：首次登陆
		else if ("firstLogin".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().firstLogin(request.getParameter("userName"),request.getParameter("userPassword")));
		//20140415增加：首次登陆
		else if ("firstLogin01".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().firstLogin(request.getParameter("userName"),
					request.getParameter("userPassword"),iMEI));
		//20140429增加：学校注册
		else if ("schoolLogin".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().schoolLogin(request.getParameter("cityName"),request.getParameter("schoolName"),request.getParameter("address"),request.getParameter("phone"),request.getParameter("name"),request.getParameter("userName"),request.getParameter("password"),request.getParameter("email"),request.getParameter("pusher")));
		//五广告
		else if ("fiveTbAdvertisement".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().fiveTbAdvertisement());
		//修改学生及家长信息
		else if ("updateMyInfoParentAndStudent".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().updateMyInfoParentAndStudent(request.getParameter("userinfoId"),request.getParameter("password"),request.getParameter("email"),request.getParameter("gradeId"),request.getParameter("cityId"),request.getParameter("studentSex"),request.getParameter("studentId"),request.getParameter("studentPassword"),request.getParameter("studentEmail")));
		//修改学生
		else if ("updateMyInfoStudent".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().updateMyInfoStudent(request.getParameter("gradeId"),request.getParameter("studentSex"),request.getParameter("studentId"),request.getParameter("studentPassword"),request.getParameter("studentEmail")));
		//查看个人推送信息
		else if ("findTbPushRecord".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().findTbPushRecord(request.getParameter("userinfoId"),request.getParameter("currentPage"), request.getParameter("countPerPage")));
		//我的_修改页面获得信息接口
		else if ("findMyInfo".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().findMyInfo(request.getParameter("userinfoId"),request.getParameter("root")));
		//宝宝去哪_app下载
		else if ("whereTheBabyTo".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().whereTheBabyTo(request.getParameter("type")));
		//我的_修改校长个人信息
		else if ("updateRectorInfo".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().updateRectorInfo(request.getParameter("email"),request.getParameter("phone"),request.getParameter("userinfoId"),request.getParameter("password")));
		//我的_获取用户总校信息接口
		else if ("findSchoolInfo".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().findSchoolInfo(request.getParameter("userinfoId")));
		//我的_修改总校信息接口
		else if ("updateSchoolInfo".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().updateSchoolInfo(request.getParameter("schoolId"),request.getParameter("schoolName"),request.getParameter("schoolStopLocation"),request.getParameter("schoolBusWay"),request.getParameter("schoolMinName"),request.getParameter("schoolPhone"),request.getParameter("townId"),request.getParameter("schoolAddress")));
		//找学校
		else if ("findSchools".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().findSchools(request.getParameter("schoolName"),request.getParameter("cityName"),request.getParameter("longitude"),request.getParameter("latitude"),request.getParameter("orderType"),request.getParameter("currentPage"), request.getParameter("countPerPage"), request.getParameter("gradeId"), request.getParameter("subjectId"), request.getParameter("courseName"), request.getParameter("townId"), request.getParameter("branchschoolId")));//townId
		//学校详情接口及其人气累计
		else if ("schoolDetail01".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().schoolDetail(request.getParameter("schoolId"),request.getParameter("type"),request.getParameter("userinfoId")));
		//课程详情接口及其人气累计
		else if ("curriculumDetail01".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().curriculumDetail(request.getParameter("curriculumId"),request.getParameter("userinfoId")));
		//选课报班添加城市条件
		else if ("findCurriculum".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().findCurriculum(request.getParameter("branchschoolId"),request.getParameter("cityId")));
		//学校相册列表
		else if ("findSchoolPhoto".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().findSchoolPhoto(request.getParameter("schoolId"),request.getParameter("type"),request.getParameter("currentPage"), request.getParameter("countPerPage")));
		//优惠专区查询接口
		else if ("teamfind".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().teamfind(request.getParameter("title"),request.getParameter("cityId"),request.getParameter("townId"),request.getParameter("schoolId"),request.getParameter("longitude"),request.getParameter("latitude"),request.getParameter("orderType"),request.getParameter("currentPage"), request.getParameter("countPerPage"),request.getParameter("cityName")));
		//优惠专区详情接口
		else if ("teamDetail".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().teamDetail(request.getParameter("teamId")));
		//我的_优惠分页列表接口
		else if ("myTeam".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().myTeam(request.getParameter("userinfoId"),request.getParameter("longitude"),request.getParameter("latitude"),request.getParameter("currentPage"), request.getParameter("countPerPage")));
		//我的_报班统计接口
		else if ("myStatistics".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().myStatistics(request.getParameter("userinfoId"),request.getParameter("startTime"), request.getParameter("endTime")));
		//我的_报班下单或优惠详情接口
		else if ("myTeamOrCurriculum".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().myTeamOrCurriculum(request.getParameter("orderId")));
		//地图显示颜色显示与否接口
		else if ("mapShow".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().mapShow(request.getParameter("branchschoolId"),request.getParameter("childLongitude"),request.getParameter("childLatitude"),request.getParameter("range"),request.getParameter("title")));
		//支付接口
		else if ("alipaySuccess".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().alipaySuccess(request.getParameter("userinfoId"),request.getParameter("quantity"),request.getParameter("curriculumId"),request.getParameter("teamId"),request.getParameter("type"),request.getParameter("origin")));
		//支付接口
		else if ("alipaySuccess1".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().alipaySuccess1(request.getParameter("userinfoId"),request.getParameter("quantity"),request.getParameter("curriculumId"),request.getParameter("teamId"),request.getParameter("type"),request.getParameter("origin")));
		//退款状态改变接口
		else if ("chargeback".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().chargeback(request.getParameter("orderId")));
		//家长定位接口
		else if ("parentsPositioning".equals(action_flag))
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().parentsPositioning(request.getParameter("userinfoId"),request.getParameter("longitude"),request.getParameter("latitude")));
		//上传学校相册
		else if ("schoolPhoto".equals(action_flag)){
			response.sendRedirect(new SecondBackstage().schoolPhoto(request.getParameterValues("hiddenDom"),request.getParameter("adminId"))); 
		}
		//上传广告
		else if ("saveAdvertisement".equals(action_flag)){
			response.sendRedirect(new SecondBackstage().saveAdvertisement(hyperlink,photoType,items,basePicUrl)); 
		}
		pw.write(jsonSting);
		pw.flush();
		pw.close();
	}

	@Override
	public void init() throws ServletException {
		gogoservice = new GogoServiceImpl();
		path= this.getServletConfig().getServletContext().getRealPath("images/").replace("\\", "/");
	}

	public static String getPath() {
		return path;
	}
	
}
