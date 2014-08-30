package com.pro.actions;

import com.pro.manager.JsonApiManager;
import com.pro.util.JsonResult;
import com.qiYang.model.TbRegschool;

public class JsonApiAction {
	private JsonApiManager jsonApiManager;
	private int pageNo;
	private int pageSize;
	private int maxPage;
	private int infotype;
	private int townId;
	private int subjectid;
	private TbRegschool school;
	private JsonResult jsonResult;
	
	
	public int getTownId() {
		return townId;
	}
	public void setTownId(int townId) {
		this.townId = townId;
	}
	public int getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	public JsonResult getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getInfotype() {
		return infotype;
	}
	public void setInfotype(int infotype) {
		this.infotype = infotype;
	}
	public TbRegschool getSchool() {
		return school;
	}
	public void setSchool(TbRegschool school) {
		this.school = school;
	}
	public void setJsonApiManager(JsonApiManager jsonApiManager) {
		this.jsonApiManager = jsonApiManager;
	}
	public String showRecruitApi(){
		try{
			if(infotype==9)
				this.jsonResult = jsonApiManager.schoolUnion(pageSize, pageNo);
			else
				this.jsonResult = jsonApiManager.infoList(pageSize, pageNo, infotype);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	public String showCityApi(){
		try{
			this.jsonResult = jsonApiManager.cityList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	public String regSchool(){
		try{
			this.jsonResult = jsonApiManager.regSchool(school,townId);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	public String absolutePath(){
		try{
			this.jsonResult = jsonApiManager.absolutePath();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	public String getKcByKemu(){
		try{
			this.jsonResult = jsonApiManager.kemuKc(subjectid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
}
