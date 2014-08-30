package com.pro.actions;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.hibernate.criterion.DetachedCriteria;

import com.pro.manager.AdminManager;
import com.pro.manager.InfoNoticeManager;
import com.pro.util.Page;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbInfonotice;
import com.qiYang.util.StrutsUpload;

public class InfoNoticeAction extends BaseAction {
	
	private TbAdmin admin;
	private TbInfonotice infoNotice;
	private int[] delid; 
	private File img;
	private String imgContentType;
	private String imgFileName;
	
	public TbAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(TbAdmin admin) {
		this.admin = admin;
	}
	public TbInfonotice getInfoNotice() {
		return infoNotice;
	}
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
	}
	public void setInfoNotice(TbInfonotice infoNotice) {
		this.infoNotice = infoNotice;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getImgContentType() {
		return imgContentType;
	}
	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	private AdminManager adminManager;
	private InfoNoticeManager infoNoticeManager;
	public void setInfoNoticeManager(InfoNoticeManager infoNoticeManager) {
		this.infoNoticeManager = infoNoticeManager;
	}
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}
	
	public String infoNotice(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbInfonotice.class);
			Page.getResult(request, infoNoticeManager, dc, "infonoticelist", 15);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String addOrEditInfoNotice(){
		try{
			TbInfonotice infonotice1 = (TbInfonotice)infoNoticeManager.findById(infoNotice.getId());
			request.setAttribute("infoNotice", infonotice1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String  editInfoNoticeDo(){
		try{
			TbInfonotice infonotice1 = (TbInfonotice)infoNoticeManager.findById(infoNotice.getId());
			//50KB
			if(img!=null){
				if(img.length()>5120000)
					return "fail";
				imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
				infonotice1.setPictrueUrl(imgFileName);
			  }
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));

			infonotice1.setHeadline(infoNotice.getHeadline());
			infonotice1.setContent(infoNotice.getContent());
			infonotice1.setIsValid(infoNotice.getIsValid());
			
			infonotice1.setAlterTime(nowdate);
			
			infoNoticeManager.update(infonotice1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String  addInfoNoticeDo(){
		try{
			TbAdmin admins1 = this.loginAdmin();
			infoNotice.setTbAdmin(admins1);
			//50KB
			if(img!=null){
				if(img.length()>5120000)
					return "fail";
				imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
				infoNotice.setPictrueUrl(imgFileName);
			  }else{
				  infoNotice.setPictrueUrl("root2.png");
			  }
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			
			infoNotice.setTime(nowdate);
			infoNotice.setAlterTime(nowdate);
			
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			infoNotice.setStarttime(this.getSystemDate(0));
			
			infoNotice.setIsValid(1);
			
			infoNoticeManager.save(infoNotice);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String delInfoNotice(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbInfonotice infonotice1 = (TbInfonotice)infoNoticeManager.findById(delid[i]);
				infoNoticeManager.delete(infonotice1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
