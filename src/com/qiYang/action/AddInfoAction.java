package com.qiYang.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;
import com.qiYang.dao.FindUserDao;
import com.qiYang.dao.UpdateDao;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbUser;
import com.qiYang.model.TbUserinfo;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.ExcelOperate;

public class AddInfoAction extends ActionSupport{
	final static  Logger log = Logger.getLogger(AddInfoAction.class);
	private File file;
	private String fileContentType;
	private String fileFileName;
	private TbUserinfo tbuserinfo=new TbUserinfo();
	private TbUserinfo tbuser = new TbUserinfo();
	private UpdateDao updatedao = new UpdateDao();
	private FindUserDao finddao = new FindUserDao();
	private TbUser user = new TbUser();
	private List listuserinfo;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	String path = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
	private Date date;
	/*
	 * 导入信息
	 */
	
	public String addteacherInfo() throws Exception{
		 
		 String str="";
		 if(!upLoadFile()){
			  request.setAttribute("result", "fail");
			  return "fail";
		  }
		  ExcelOperate eo = new ExcelOperate();
		  File fl =new File(path+"temp/upload.xls");
	      String[][] result =ExcelOperate.getData(fl,1);
	      int rowLength = result.length;
	      for(int i=0;i<rowLength;i++) {
	          for(int j=0;j<result[i].length-1;j++) {
	        	if(j==0){
	        		 tbuser.setUserInfoName(result[i][j]);//用户名称
	        	 }else if(j==1){
	        		 tbuser.setUserInfoSex(result[i][j]);//性别
	        	 }else if(j==2){
	        		  Date date = DateUtils.parseDate(result[i][j], "yyyy-MM-dd");
	        		 tbuser.setUserInfoBirthday(date);// 生日
	        	 }else if(j==3){
	        		  Double b= Double.parseDouble(result[i][j]);
		        		 tbuser.setUserInfoPhone(String.valueOf(b.longValue()));//手机（用户名）
	        	 }else if(j==4){
	        		 tbuser.setGraduateSchool(result[i][j]);// 老师毕业学校
	        	 }else if(j==5){
	        		 tbuser.setUserInfoEmail(result[i][j]);//用户邮箱
	        	 }else if(j==6){
	        		 Double b=Double.parseDouble(result[i][j]);
	        		 tbuser.setWorkYear(String.valueOf(b.intValue()));//老师工作年限
	        	 }else if(j==7){
	        		 tbuser.setUserInfoCourse(result[i][j]);//任教课程
	        	 }else if(j==8){
	        		 Double b=Double.parseDouble(result[i][j]);
	        		 tbuser.setUserInfoRoot(b.intValue());         //用户权限
	        	 }else if(j==9){
	        		 user.setUserName(result[i][j]);
	        	 }
	        	}
	      	  TbAdmin tbadmin = (TbAdmin) ServletActionContext.getRequest().getSession().getAttribute("loginAdmin");
	      	  log.info("tbadmin--->"+tbadmin);
	          TbBranchschool branchschool = new GogoServiceImpl().getObjectByClazz(TbBranchschool.class, tbadmin.getTbBranchschool().getBranchSchoolId());
	          TbSchool tbschool = new TbSchool();
	        
	          tbschool.setSchoolId(branchschool.getTbSchool().getSchoolId());
	          log.info("tbadmin.getTbBranchschool--->"+branchschool.getTbSchool().getSchoolId());
	          tbuser.setTbSchool(tbschool);
	          tbuser.setTbBranchschool(branchschool);
	          
	          str +=updatedao.addTeacherinfo(tbuser,user);
	      }   
	    request.setAttribute("result", str);
		return "teachersuccess";
		
	}

	public boolean upLoadFile() throws Exception{
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
	public FindUserDao getFinddao() {
		return finddao;
	}
	public void setFinddao(FindUserDao finddao) {
		this.finddao = finddao;
	}
	public List getListuserinfo() {
		return listuserinfo;
	}
	public void setListuserinfo(List listuserinfo) {
		this.listuserinfo = listuserinfo;
	}
	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

}
