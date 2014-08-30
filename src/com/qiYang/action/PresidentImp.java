package com.qiYang.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiYang.service.UserJsonService;
import com.qiYang.service.UserJsonServiceImp;
import com.qiYang.util.JsonTools;
import com.qiYang.util.ResultJson;

public class PresidentImp extends HttpServlet implements President {

	/**
	 * 
	 */
	private static final long serialVersionUID = -607367893225071371L;
	private UserJsonServiceImp userservice;

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
//		System.out.println(encoding);
		
		String userinfoId = request.getParameter("userinfoId");
		String userinfoPhone = request.getParameter("userinfoPhone");
		String action_flag = request.getParameter("action_flag");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String userinfoSign = request.getParameter("userinfoSign");
		String schoolnoticeId = request.getParameter("schoolnoticeId");
		String complainId = request.getParameter("complainId");
		String studentId = request.getParameter("studentId");
		String teacherId = request.getParameter("teacherId");
		String reviewContent = request.getParameter("reviewContent");
		String complainContent = request.getParameter("complainContent");
		String branchschoolId = request.getParameter("branchschoolId");
		String isSatisfie = request.getParameter("isSatisfie");
		String courseId = request.getParameter("courseId");
		String studentSchool = request.getParameter("studentSchool");
		String studentClass = request.getParameter("studentClass");
		String cityId = request.getParameter("cityId");
		String townId = request.getParameter("townId");
		String id = request.getParameter("id");
		String gradeId = request.getParameter("gradeId");
		String cityName = request.getParameter("cityName");
		String courseName = request.getParameter("courseName");
        String courseScore = request.getParameter("courseScore");
		String userName = request.getParameter("userName");
		String userinfoEmail = request.getParameter("userinfoEmail");
        String subjectId = request.getParameter("subjectId");
        String comdetContent = request.getParameter("comdetContent");
        String classId = request.getParameter("classId");
		String jsonSting = "";
		if (StringUtils.isBlank(action_flag)) {
			System.out.println("action_flag为空，方法指定不明确");
			jsonSting = JsonTools.rendJson(ResultJson.createFailJson(-1,
					"action_flag为空，方法指定不明确"));
		}
		// 个人中心
		else if ("checkUserinfoJson".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice
					.checkUserinfoJson(Integer.parseInt(userinfoId)));
		// 修改手机号
		else if ("updatePhoneJson".equals(action_flag)) {
			if (null != userinfoPhone) {
				jsonSting = JsonTools.rendJson(userservice.updatePhoneJson(
						Integer.parseInt(userinfoId), userinfoPhone));
			}
			// 修改签名
			else if (null != userinfoSign) {
				jsonSting = JsonTools.rendJson(userservice.updateSignJson(
						Integer.parseInt(userinfoId), userinfoSign));
			}
			// 查看相册
		} else if ("finduserphotoJson".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice
					.finduserphotoJson(Integer.parseInt(userinfoId)));
		// 修改密码
		else if ("updatePasswordTo".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.updatePasswordTo(Integer
					.parseInt(userinfoId), oldPassword, newPassword));

		// 学校通知
		else if ("selectSchoolnotice".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice
					.selectSchoolnotice(Integer.parseInt(branchschoolId)));
		// 通告详情
		else if ("findBySchoolnotice".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice
					.findBySchoolnotice(Integer.parseInt(schoolnoticeId)));
		// 老师评语
		else if ("findBytbReview".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.findBytbReview(Integer
					.parseInt(studentId), Integer.parseInt(teacherId),Integer.parseInt(classId),
					reviewContent));
		// 查看评语
		else if ("selectReview".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.selectReview(Integer
					.parseInt(userinfoId)));
		// 投诉列表
		else if ("selectComplain".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.selectComplain(Integer
					.parseInt(userinfoId),Integer
					.parseInt(classId),Integer
					.parseInt(branchschoolId)));

		// 查看投诉
		else if ("findByComplain".endsWith(action_flag))
			jsonSting = JsonTools.rendJson(userservice.findByComplain(Integer
					.parseInt(complainId)));
		// 校长端查看投诉
		else if ("listComplain".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listComplain(Integer
					.parseInt(branchschoolId)));
		// 投诉建议
		else if ("addBytbComplain".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.addBytbComplain(Integer
					.parseInt(userinfoId),Integer.parseInt(classId),Integer.parseInt(branchschoolId),complainContent));
		// 校长端回复投诉
		else if("addcomplainDetails".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.addcomplainDetails(Integer.parseInt(complainId), Integer.parseInt(userinfoId), comdetContent));
		// 家长回复满意，不满意
		else if ("updatecomplain".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.updatecomplain(Integer
					.parseInt(complainId), Integer.parseInt(isSatisfie)));
		// 学习课程
		else if ("listCourse".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listCourse(Integer.parseInt(branchschoolId)));
		// 课程班级信息
		else if ("findByCourse".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.findByCourse(Integer
					.parseInt(courseId)));
		// 修改学校
		else if("updateSchool".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.updateSchool(Integer
					.parseInt(userinfoId), studentSchool));
		// 修改班级
		else if("updateClass".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.updateClass(Integer
					.parseInt(userinfoId), studentClass));
		// 查看练习列表
		else if ("findtbTestfinish".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.findtbTestfinish(Integer
					.parseInt(userinfoId)));
		//市区
		else if("findtbCity".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listtbCity());	
		// 城区
		else if("findtbTown".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.findtbTown(Integer.parseInt(cityId)));
		//城区下的所有班级
//		else if("findtbCourse".equals(action_flag))
//			jsonSting = JsonTools.rendJson(userservice.findtbCourse(Integer.parseInt(townId)));
		else if("listcourse".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listcourse(Integer.parseInt(branchschoolId)));
		else if("listtbCourse".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listtbCourse(Integer.parseInt(branchschoolId)));
		else if("listtbGrade".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listtbGrade());
		else if("findCity".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.findCity(cityName));
		else if("listcourseName".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listcurriculum(Integer.parseInt(townId)));
		else if("listcourseScore".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listcourseScore());
		else if("tijiao".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.tijiao(Integer.parseInt(branchschoolId),Integer.parseInt(cityId),
					Integer.parseInt(townId),Integer.parseInt(subjectId),Integer.parseInt(gradeId),courseScore));
		else if("newtijiao".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.newtijiao(Integer.parseInt(branchschoolId),Integer.parseInt(cityId),
					Integer.parseInt(townId),Integer.parseInt(subjectId),Integer.parseInt(gradeId),courseScore));
		
		else if("listGradecourseName".equals(action_flag))	
			jsonSting = JsonTools.rendJson(userservice.listGradecourseName(courseName));
		else if("listgradecourse".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.listgradecourse(Integer.parseInt(branchschoolId),Integer.parseInt(subjectId),Integer.parseInt(id)));
		else if("password".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.password(userName,userinfoEmail));
		else if("password01".equals(action_flag))
			jsonSting = JsonTools.rendJson(userservice.password01(userName,userinfoEmail));
		
//		System.out.println(jsonSting);
		pw.write(jsonSting);
		pw.flush();
		pw.close();
	}
	
	@Override
	public void init() throws ServletException {
		userservice = new UserJsonService();
	}
}
