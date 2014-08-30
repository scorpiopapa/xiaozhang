package com.pro.actions;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.pro.manager.CourseManager;
import com.pro.manager.UserInfoClassManager;
import com.pro.manager.UserInfoManager;
import com.pro.manager.UserManager;
import com.qiYang.dao.UpdateDao;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.web.TbUserinfoWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.ExcelOperate;
import com.qiYang.util.TWDataUtil;

public class ImportAction extends BaseAction{
	final static  Logger log = Logger.getLogger(ImportAction.class);
	private File file;
	private String fileContentType;
	private String fileFileName;
	private UpdateDao updatedao = new UpdateDao();
	private TbUserinfo tbuserinfo=new TbUserinfo();
	private TbUserinfo tbuser = new TbUserinfo();
	private UserInfoClassManager userclassManager;
	private UserInfoManager userInfoManager;
	private CourseManager courseManager;
	private UserManager userManager;
	private TbCourse course;
	
	
	public TbCourse getCourse() {
		return course;
	}

	public void setCourse(TbCourse course) {
		this.course = course;
	}

	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}

	public void setUserclassManager(UserInfoClassManager userclassManager) {
		this.userclassManager = userclassManager;
	}
	
	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}

	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/*
	 * 导入信息
	 */
	public String addInfo() throws Exception{
		TbUserinfoWeb studentWeb = new TbUserinfoWeb();
		TbUserinfoWeb parentWeb = new TbUserinfoWeb();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String path = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
		HttpServletResponse response = ServletActionContext.getResponse(); 
		String str1="";
		  if(!upLoadFile()){
			  request.setAttribute("result", "fail");
			  return "fail";
		  }
		  List list=new ArrayList<Object>();
		  ExcelOperate eo = new ExcelOperate();
		  File fl =new File(path+"temp/upload.xls");
	      String[][] result =ExcelOperate.getData(fl,1);
	      int rowLength = result.length;
	      for(int i=0;i<rowLength;i++) {
	          for(int j=0;j<result[i].length-1;j++) {
	        	if(j==0){
	        		parentWeb.setUserinfoName(result[i][j]);//家长姓名
	        		log.info("result[i][j]-->parent-->UserinfoName--->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==1){
	        		 parentWeb.setUserinfoSex(result[i][j]);//家长性别
	        		 log.info("result[i][j]-->parent-->UserinfoSex-->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==2){
	        		 parentWeb.setUserinfoBirthday(result[i][j]);//家长生日
	        		 log.info("result[i][j]-->parent-->UserinfoBirthday--->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==3){
	        		 if(StringUtils.isEmpty(result[i][j]))
	        			 continue;
	        		 Double d = Double.parseDouble(result[i][j]);	 
	        		 parentWeb.setUserinfoPhone(String.valueOf(d.longValue()));//家长电话
	        		 log.info("result[i][j]-->parent-->UserinfoPhone--->"+i+j+"-->"+String.valueOf(d.longValue()));
	        	 }else if(j==4){
	        		 parentWeb.setUserinfoEmail(result[i][j]);// 家长邮箱
	        		 log.info("result[i][j]-->parent-->UserinfoEmail-->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==5){
	        		 studentWeb.setStudentClass(result[i][j]);//学生公立学校班级
	        		 log.info("result[i][j]-->student-->StudentClass-->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==6){
	        		 studentWeb.setStudentSchool(result[i][j]);//学生公立学校
	        		 log.info("result[i][j]-->student-->StudentSchool-->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==7){
	        		 studentWeb.setUserinfoName(result[i][j]);//学生名称
	        		 log.info("result[i][j]-->student-->UserinfoName"+i+j+"-->"+result[i][j]);
	        	 }else if(j==8){
	        		 studentWeb.setUserinfoSex(result[i][j]);//学生性别
	        		 log.info("result[i][j]-->student-->UserinfoSex-->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==9){
	        		 studentWeb.setUserinfoBirthday(result[i][j]);// 学生生日
	        		 log.info("result[i][j]-->student-->UserinfoBirthday-->"+i+j+"-->"+result[i][j]);
	        	 }else if(j==10){
	        		 if(StringUtils.isEmpty(result[i][j]))
	        			 continue;
	        		 Double d = Double.parseDouble(result[i][j]);	 
	        		 studentWeb.setUserinfoPhone(String.valueOf(d.longValue()));//学生电话
	        		 log.info("result[i][j]-->student-->UserinfoPhone-->"+i+j+"-->"+String.valueOf(d.longValue()));
	        	 }else if(j==11){
	        		 studentWeb.setUserinfoEmail(result[i][j]);//学生QQ
	        		 studentWeb.setUserinfoQQ(TWDataUtil.getStudentMail(result[i][j]));
	        		 log.info("result[i][j]-->student-->UserinfoQQ-->"+i+j+"-->"+result[i][j]);
	        	 }
	        	}
			String str2 = new GogoServiceImpl().addStudentAndParent(studentWeb, parentWeb, course.getCourseId());
			str1=str2;
			if(str2.indexOf("已存在")>0){
				str1=studentWeb.getUserinfoName()+"的QQ已经注册或"+parentWeb.getUserinfoName()+"的手机已经注册！";
				request.setAttribute("result", str2);
				return "success";
			}
	      }   
	    request.setAttribute("result", str1);
	    System.out.println(str1);
		return "success";
	}

	public boolean upLoadFile() throws Exception{
		String path = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
		//文件大于1M
		if(file.length()>524288){
			return false;
		}
		String extName=fileFileName.substring(fileFileName.lastIndexOf("."));
		if(".xls".equals(extName)){
			fileFileName = "upload.xls";
			File fileload = new File(path+"temp", fileFileName);
			if(!fileload.getParentFile().exists())
				fileload.getParentFile().mkdirs();
			FileUtils.copyFile(file, fileload);
			return true;
		}else{
			return false;
		}
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public UpdateDao getUpdatedao() {
		return updatedao;
	}
	public void setUpdatedao(UpdateDao updatedao) {
		this.updatedao = updatedao;
	}
	public TbUserinfo getTbuserinfo() {
		return tbuserinfo;
	}
	public void setTbuserinfo(TbUserinfo tbuserinfo) {
		this.tbuserinfo = tbuserinfo;
	}
	public TbUserinfo getTbuser() {
		return tbuser;
	}
	public void setTbuser(TbUserinfo tbuser) {
		this.tbuser = tbuser;
	}

}
