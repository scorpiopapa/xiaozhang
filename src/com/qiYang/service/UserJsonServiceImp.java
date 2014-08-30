package com.qiYang.service;

import com.qiYang.util.ResultJson;

public interface UserJsonServiceImp {

	public ResultJson checkUserinfoJson(Integer userinfoId); // 查看个人中心

	public ResultJson updatePhoneJson(Integer userinfoId, String Phone); // 修改手机号码

	public ResultJson finduserphotoJson(Integer photoId); // 查看自己的相册

	public ResultJson updateSignJson(Integer userinfoId, String sign); // 修改签名

	public ResultJson updatePasswordTo(Integer userInfoId, String oldPassword,
			String newPassword); // 修改密码

	public ResultJson selectSchoolnotice(Integer branchschoolId); // 学校通知

	public ResultJson findBySchoolnotice(Integer schoolnoticeId); // 通告详情

	public ResultJson findBytbReview(Integer studentId, Integer teacherId,Integer classId,
			String reviewContent);// 添加评语

	public ResultJson selectReview(Integer userinfoId); // 查看评语

	public ResultJson addBytbComplain(Integer userinfoId,Integer classId,
			 Integer branchschoolId, String complainContent); // 投诉建议
	public ResultJson addcomplainDetails(Integer complainId,
			Integer userinfoId,String comdetContent); // 校长端回复投诉
	public ResultJson selectComplain(Integer complainId,Integer classId,Integer branchschoolId); // 投诉列表

	public ResultJson findByComplain(Integer complainId); // 查看投诉
	public ResultJson listComplain(Integer branchschoolId);// 
	public ResultJson updatecomplain(Integer userinfoId, Integer isSatisfie); // 满意，不满意

	public ResultJson findtbTestfinish(Integer userinfoId); // 查看练习列表

	public ResultJson listCourse(Integer branchSchoolId); // 学习课程

	public ResultJson findByCourse(Integer courseId); // 课程班级信息
	
	public ResultJson updateSchool(Integer userinfoId, String studentSchool);// 修改学校
	
	public ResultJson updateClass(Integer userinfoId, String studentClass);// 修改班级
	
	public ResultJson listtbCity(); //城市
	public ResultJson findCity(String cityName);// 
	public ResultJson findtbTown(Integer cityId);
//	public ResultJson findtbCourse(Integer townId);
	public ResultJson listtbGrade(); //
	public ResultJson listtbCourse(Integer branchSchoolId);//
	public ResultJson listcourse(Integer branchschoolId);
	public ResultJson listcourseScore();
	public ResultJson listcurriculum(Integer townId);
	public ResultJson tijiao(Integer branchschoolId,Integer cityId, Integer subjectId,Integer townId,Integer gradeId,String courseScore);
	public ResultJson newtijiao(Integer branchschoolId,Integer cityId, Integer subjectId,Integer townId,Integer gradeId,String courseScore);

	// 选课报班
	public ResultJson listGradecourseName(String courseName);
	public ResultJson listgradecourse(Integer branchschoolId,Integer subjectId,Integer id);
	public ResultJson password(String userName,String userinfoEmail);
	public ResultJson password01(String userName,String userinfoEmail);
}
