package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbInfonoticeWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.TWObjectUtil;

/**
 * TbInfonotice entity. @author MyEclipse Persistence Tools
 */

public class TbInfonotice implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbAdmin tbAdmin;
	private String starttime;
	private String headline;
	private String content;
	private String pictrueUrl;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbInfonotice() {
	}

	/** full constructor */
	public TbInfonotice(TbAdmin tbAdmin, String starttime, String headline,
			String content, String pictrueUrl, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.tbAdmin = tbAdmin;
		this.starttime = starttime;
		this.headline = headline;
		this.content = content;
		this.pictrueUrl = pictrueUrl;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbInfonoticeWeb toJson() {
		TbAdmin tbAdmin=new TbAdmin();
		if(this.tbAdmin!=null&&this.tbAdmin.getAdminId()!=null)
			tbAdmin=new GogoServiceImpl().getObjectByClazz(TbAdmin.class, this.tbAdmin.getAdminId());
		return new TbInfonoticeWeb(this.id, this.starttime, TWObjectUtil.getString(this.headline), TWObjectUtil.getString(tbAdmin.getAdminName()));
	}
	public TbInfonoticeWeb toPageWeb() {
		TbAdmin tbAdmin=new TbAdmin();
		if(this.tbAdmin!=null&&this.tbAdmin.getAdminId()!=null)
			tbAdmin=new GogoServiceImpl().getObjectByClazz(TbAdmin.class, this.tbAdmin.getAdminId());
		return new TbInfonoticeWeb(this.id, this.starttime, TWObjectUtil.getString(this.headline),this.content,tbAdmin.getAdminId(),TWObjectUtil.getString(tbAdmin.getAdminName()),TWObjectUtil.getNomalPicPath(this.pictrueUrl),this.isValid,TWObjectUtil.getTimestamp(this.time),TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbAdmin getTbAdmin() {
		return this.tbAdmin;
	}

	public void setTbAdmin(TbAdmin tbAdmin) {
		this.tbAdmin = tbAdmin;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPictrueUrl() {
		return this.pictrueUrl;
	}

	public void setPictrueUrl(String pictrueUrl) {
		this.pictrueUrl = pictrueUrl;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getAlterTime() {
		return this.alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

}