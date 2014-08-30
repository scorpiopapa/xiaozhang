package com.pro.actions;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
  
import com.pro.manager.InformationManager;
import com.pro.util.Page;
import com.qiYang.model.TbInformation;
import com.qiYang.util.StrutsUpload;

public class InfoAction extends BaseAction {
	private int infoType;
	private TbInformation info;
	private File img;
	private String imgContentType;
	private String imgFileName;
	private int[] delid;
	public int getInfoType() {
		return infoType;
	}
	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}
	public TbInformation getInfo() {
		return info;
	}
	public void setInfo(TbInformation info) {
		this.info = info;
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
	private InformationManager infoManager;

	public void setInfoManager(InformationManager infoManager) {
		this.infoManager = infoManager;
	}
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
	}
	/*
	 * 1为公司简介，2为合作服务，3为软件介绍，
	 * 4为投诉建议，5为联系我们，6为招聘，
	 * 7为首页图片，8为广告图片，9为合作学校,10为选课报班图片
	 * 
	 * */
	public String viewInformation() throws Exception{
		String path = "showInfo";
		String typetitle = "";
		DetachedCriteria dc = null;
		if(infoType!=0){
			String btip = "";
			dc = DetachedCriteria.forClass(TbInformation.class);
			dc.add(Restrictions.eq("infotype", infoType));
			List<TbInformation> list = infoManager.findByCriteria(dc);
			if(list.size()>0){
				request.setAttribute("information", list.get(0));
				btip = "修改";
			}else{
				btip = "添加";
				request.setAttribute("information", new TbInformation());
			}	
			request.setAttribute("btip", btip);
			request.setAttribute("infoType", infoType);
		}	
		
		switch(infoType){
			case 0: path=SUCCESS; break;
			case 1:
					typetitle = "公司简介";
					break;
			case 2:
					typetitle = "合作服务";
					break;
			case 3:
					typetitle = "软件介绍";
					break;
			case 4:
					typetitle = "投诉建议";
					break;
			case 5:
					typetitle = "联系我们";
					break;
		}
		request.setAttribute("typetitle", typetitle);
		return path;
	}
	
	public String saveOrUpdateInfo() throws Exception{
		TbInformation info1 = new TbInformation();
		
		if(img!=null){
			if(img.length()>IMAGE_SIZE)
				return "fail";
			imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
			info1.setImage(imgFileName);
		  }else if(info.getInfoid()==null){
			  info1.setImage("root2.png");
		  }
		
		if(info.getInfoid()==null){
			info.setAddtime(Timestamp.valueOf(this.getSystemDate(0)));
			info.setImage(info1.getImage());
			info.setInfotype(infoType);
			infoManager.save(info);
		}else {
			TbInformation info2 = (TbInformation)infoManager.findById(info.getInfoid());
			info.setAddtime(info2.getAddtime());
			if(info1.getImage()!=null)
				info.setImage(info1.getImage());
			else 
				info.setImage(info2.getImage());
			info.setInfotype(info2.getInfotype());
			info.setInfotype(infoType);
			infoManager.update(info);
		}	
		return SUCCESS;
	}
	/*显示招聘信息
	 * 
	 * */
	public String showRecruits(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbInformation.class);
			dc.add(Property.forName("infotype").eq(6));
			Page.getResult(request, infoManager, dc, "list", 20);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String addRecruits(){
		
		
		return SUCCESS;
	}
	public String saveRecruits(){
		try{
			info.setAddtime(Timestamp.valueOf(this.getSystemDate(0)));
			info.setInfotype(6);
			infoManager.save(info);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editRecruits(){
		try{
			TbInformation info1 = (TbInformation)infoManager.findById(info.getInfoid());
			request.setAttribute("detail", info1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String updateRecruits(){
		try{
			info.setAddtime(Timestamp.valueOf(this.getSystemDate(0)));
			infoManager.update(info);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String deleteRecruits(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbInformation tinfo = new TbInformation();
				tinfo.setInfoid(delid[i]);
				infoManager.delete(tinfo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/*合作学校
	 * 
	 * */
	public String showSchoolUnion(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbInformation.class);
			dc.add(Property.forName("infotype").eq(9));
			Page.getResult(request, infoManager, dc, "list", 20);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String addSchoolUnion(){
		
		
		return SUCCESS;
	}
	public String saveSchoolUnion(){
		try{
			info.setAddtime(Timestamp.valueOf(this.getSystemDate(0)));
			info.setInfotype(9);
			infoManager.save(info);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editSchoolUnion(){
		try{
			TbInformation info1 = (TbInformation)infoManager.findById(info.getInfoid());
			request.setAttribute("detail", info1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String updateSchoolUnion(){
		try{
			
			info.setAddtime(Timestamp.valueOf(this.getSystemDate(0)));
			infoManager.update(info);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String deleteSchoolUnion(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbInformation tinfo = new TbInformation();
				tinfo.setInfoid(delid[i]);
				infoManager.delete(tinfo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/*首页图片，广告图片，选课报班图片
	 * 
	 * */
	public String showAboutImage(){
		try{
			String typeName = "";
			if(infoType==7)
				typeName = "首页图片";
			else if(infoType==8)
				typeName = "广告图片";
			else if(infoType==10)
				typeName = "选课报班图片";
			request.setAttribute("typeName", typeName);
			DetachedCriteria dc = DetachedCriteria.forClass(TbInformation.class);
			dc.add(Property.forName("infotype").eq(infoType));
			Page.getResult(request, infoManager, dc, "list", 20);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String addAboutImage(){
		String typeName = "";
		if(infoType==7)
			typeName = "首页图片";
		else if(infoType==8)
			typeName = "广告图片";
		else if(infoType==10)
			typeName = "选课报班图片";
		request.setAttribute("typeName", typeName);
		
		return SUCCESS;
	}
	public String saveAboutImage(){
		try{
			String typeName = "";
			if(img!=null){
				if(img.length()>IMAGE_SIZE)
					return "fail";
				imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
				info.setImage(imgFileName);
			  }else if(info.getInfoid()==null){
				  info.setImage("root2.png");
			  }
			if(infoType==7)
				typeName = "首页图片";
			else if(infoType==8)
				typeName = "广告图片";
			info.setTitle(typeName);
			info.setAddtime(Timestamp.valueOf(this.getSystemDate(0)));
			infoManager.save(info);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editAboutImage(){
		try{
			TbInformation info1 = (TbInformation)infoManager.findById(info.getInfoid());
			String typeName = "";
			if(info1.getInfotype()==7)
				typeName = "首页图片";
			else if(info1.getInfotype()==8)
				typeName = "广告图片";
			request.setAttribute("typeName", typeName);
			request.setAttribute("detail", info1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String updateAboutImage(){
		try{
			String typeName = "";
			if(img!=null){
				if(img.length()>IMAGE_SIZE)
					return "fail";
				imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
				info.setImage(imgFileName);
			  }else if(info.getInfoid()==null){
				  info.setImage("root2.png");
			  }
			if(infoType==7)
				typeName = "首页图片";
			else if(infoType==8)
				typeName = "广告图片";
			info.setTitle(typeName);
			info.setAddtime(Timestamp.valueOf(this.getSystemDate(0)));
			infoManager.update(info);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String deleteAboutImage(){
		try{
			String typeName = "";
			if(infoType==7)
				typeName = "首页图片";
			else if(infoType==8)
				typeName = "广告图片";
			request.setAttribute("typeName", typeName);
			
			for (int i = 0; i < delid.length; i++) {
				TbInformation tinfo = new TbInformation();
				tinfo.setInfoid(delid[i]);
				infoManager.delete(tinfo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
