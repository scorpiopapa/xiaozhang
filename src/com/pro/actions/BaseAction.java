package com.pro.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.opensymphony.xwork2.ActionSupport;
import com.qiYang.model.TbAdmin;

public class BaseAction extends ActionSupport implements ServletRequestAware {
	
	protected static final long IMAGE_SIZE = 20480000;
	protected HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	protected String getSystemDate(int type){
		Date date = new Date();
		String fs = "yyyy-MM-dd HH:mm:ss";
		if(type==1)
			fs = "yyyy-MM-dd";
		SimpleDateFormat f = new SimpleDateFormat(fs);
		
		String nowDate = f.format(date);
		
		return nowDate;
	}
	protected Session session(){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		return session;
	}
	protected TbAdmin loginAdmin(){
		HttpSession session = request.getSession();
		TbAdmin admins1 = (TbAdmin)session.getAttribute("loginAdmin");
		return admins1;
	}
}
