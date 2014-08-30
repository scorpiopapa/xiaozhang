package com.qiYang.util;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class StrutsUpload {
	//上传
	public static String upLoadFile(File img,String imgContentType,String imgFileName) throws Exception{

			HttpServletRequest requests = ServletActionContext.getRequest();
			String path = requests.getSession().getServletContext().getRealPath("/images")+"/nomal";
			imgFileName = rename(imgFileName);
			File file = new File(path, imgFileName);
			
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();

			FileUtils.copyFile(img, file);
			
			//path = path+"\\"+imgFileName;
			//System.out.println(path);
			return imgFileName;
	}
//重命名
public static String rename(String name){
	int n = (int)(Math.random()*1000000+99999);
	String str = n+"";
	String re = new Date().getTime()+str+name.substring(name.lastIndexOf("."));
	return re;
}
}
