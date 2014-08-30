package com.qiYang.model;

import java.sql.Blob;
import java.sql.Timestamp;

import com.qiYang.model.web.TbPracticeoptionWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObjectUtil;

/**
 * TbPracticeoption entity. @author MyEclipse Persistence Tools
 */

public class TbPracticeoption implements java.io.Serializable {

	// Fields

	private Integer praoptId;
	private TbPracticetitle tbPracticetitle;
	private String praOption;
	private Blob optionContent;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbPracticeoption() {
	}

	/** full constructor */
	public TbPracticeoption(TbPracticetitle tbPracticetitle, String praOption,
			Blob optionContent, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		this.tbPracticetitle = tbPracticetitle;
		this.praOption = praOption;
		this.optionContent = optionContent;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbPracticeoption(TbPracticetitle tbPracticetitle,String praOption, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		super();
		this.tbPracticetitle = tbPracticetitle;
		this.praOption = praOption;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbPracticeoptionWeb toWeb() {
		return new TbPracticeoptionWeb(TWObjectUtil.getString(this.praOption),TWDataUtil.getStringByBlob(this.optionContent));
	}
	public TbPracticeoptionWeb toPageWeb() {
		GogoServiceImpl gogoServiceImpl=new GogoServiceImpl();
		TbPracticetitle tbPracticetitle1=null;
		if(this.tbPracticetitle!=null&&this.tbPracticetitle.getPracticeId()!=null)
			tbPracticetitle1=gogoServiceImpl.getObjectByClazz(TbPracticetitle.class, this.tbPracticetitle.getPracticeId());
		if(tbPracticetitle1==null)
			tbPracticetitle1=new TbPracticetitle();
		return new TbPracticeoptionWeb(this.praoptId, tbPracticetitle1.getPracticeId(), TWDataUtil.getStringByBlob(tbPracticetitle1.getPracticeTopic()), this.praOption, TWDataUtil.getStringByBlob(this.optionContent), null, this.isValid, TWObjectUtil.getTimestamp(time), TWObjectUtil.getTimestamp(alterTime));
	}
	// Property accessors

	public Integer getPraoptId() {
		return this.praoptId;
	}

	public void setPraoptId(Integer praoptId) {
		this.praoptId = praoptId;
	}

	public TbPracticetitle getTbPracticetitle() {
		return this.tbPracticetitle;
	}

	public void setTbPracticetitle(TbPracticetitle tbPracticetitle) {
		this.tbPracticetitle = tbPracticetitle;
	}

	public String getPraOption() {
		return this.praOption;
	}

	public void setPraOption(String praOption) {
		this.praOption = praOption;
	}

	public Blob getOptionContent() {
		return this.optionContent;
	}

	public void setOptionContent(Blob optionContent) {
		this.optionContent = optionContent;
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