package com.qiYang.action;

import java.io.File;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;
import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.TbPracticeoption;
import com.qiYang.model.TbPracticetitle;
import com.qiYang.model.TbTest;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.ExcelOperate;
import com.qiYang.util.TWDataUtil;

public class AddFileDataAction extends ActionSupport{
	
	private File file;
	private String fileContentType;
	private String fileFileName;
	private TbTest tbTest;
	
	DataBaseDaoImpl daoImpl=new DataBaseDaoImpl();
	GogoServiceImpl gogoService=new GogoServiceImpl();
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	String path = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
	/*
	 * 导入学生信息2013.6.18 洪明
	 */
	public String addtest() throws Exception{
		  String str="";
		  if(!upLoadFile()){
			  request.setAttribute("result", "fail");
			  return "fail";
		  }
		  ExcelOperate eo = new ExcelOperate();
		  File fl =new File(path+"temp/upload.xls");
	      String[][] result =ExcelOperate.getData(fl, 1);
	      int rowLength = result.length;
	      Timestamp timestamp=new Timestamp(System.currentTimeMillis());
	      TbPracticetitle tbPracticetitle= null;
	     for(int i=0;i<rowLength;i++) {
	    	 TbPracticeoption[] tbPracticeoptions =new TbPracticeoption[result[i].length-3];
	    	 tbPracticetitle= new TbPracticetitle(tbTest,1, timestamp, timestamp);
	          for(int j=0;j<result[i].length-1;j++) {
	        	if(j==0){
	        		tbPracticetitle.setPracticeTopic(TWDataUtil.getBlobByString(result[i][j]));//题目
	        	 }else if(j==1){
	        		 tbPracticetitle.setAnswer(result[i][j]);//答案
	        	 }else if(j>=2){
	        		 if(StringUtils.isNotEmpty(result[i][j])){
	        		 char ch=(char) (63+j);
	        		tbPracticeoptions[j-2] = new TbPracticeoption(tbPracticetitle,Character.toString(ch), 1, timestamp, timestamp);
	        		tbPracticeoptions[j-2].setOptionContent(TWDataUtil.getBlobByString(result[i][j]));//选项
	        		}
	        	 }
	          }
	          String resultString1 = daoImpl.addObjects(tbPracticetitle,tbPracticeoptions);
	          str="success".equals(resultString1)?"导入成功":"导入失败";
	      }   
	    request.setAttribute("result", str);
		return "success";
	}
	/*
	 * 导入老师信息 2013.6.19洪明
	 */
	public String addTeacher() throws Exception{
			String str="";
		  if(!upLoadFile()){
			  request.setAttribute("result", "fail");
			  return "fail";
		  }
		  ExcelOperate eo = new ExcelOperate();
		  File fl =new File(path+"temp/upload.xls");
	      String[][] result =ExcelOperate.getData(fl, 1);
	      int rowLength = result.length;
	      request.setAttribute("result", str);
		  return "success";
	}
	//上传图片
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
	public TbTest getTbTest() {
		return tbTest;
	}
	public void setTbTest(TbTest tbTest) {
		this.tbTest = tbTest;
	}
	
	
}
