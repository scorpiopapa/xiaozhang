package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbSubjectinbranchschoolWeb;
import com.qiYang.service.GogoServiceImpl;

/**
 * TbSubjectinbranchschool entity. @author MyEclipse Persistence Tools
 */

public class TbSubjectinbranchschool implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbSubject tbSubject;
	private TbBranchschool tbBranchschool;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbSubjectinbranchschool() {
	}

	/** full constructor */
	public TbSubjectinbranchschool(TbSubject tbSubject,
			TbBranchschool tbBranchschool, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.tbSubject = tbSubject;
		this.tbBranchschool = tbBranchschool;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbSubjectinbranchschoolWeb toWeb() {
		TbSubjectinbranchschoolWeb tbSubjectinbranchschoolWeb = new TbSubjectinbranchschoolWeb();
		if(this.tbSubject==null)
			this.tbSubject=new TbSubject();
		if(this.id==null)
			return tbSubjectinbranchschoolWeb;
		if(this.tbSubject.getSubjectId()!=null){
		TbSubject tbSubject = new GogoServiceImpl().getObjectByClazz(TbSubject.class, this.tbSubject.getSubjectId());
		tbSubjectinbranchschoolWeb.setSubjectId(tbSubject.getSubjectId());
		if(tbSubject.getSubjectName()!=null)
		tbSubjectinbranchschoolWeb.setSubjectName(tbSubject.getSubjectName());
		}
		return tbSubjectinbranchschoolWeb;
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbSubject getTbSubject() {
		return this.tbSubject;
	}

	public void setTbSubject(TbSubject tbSubject) {
		this.tbSubject = tbSubject;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
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