package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbHistoryquestionWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObjectUtil;

/**
 * TbHistoryquestion entity. @author MyEclipse Persistence Tools
 */

public class TbHistoryquestion implements java.io.Serializable {

	// Fields

	private Integer hisqueId;
	private TbPracticetitle tbPracticetitle;
	private TbUserinfo tbUserinfo;
	private TbTest tbTest;
	private String answer;
	private Integer isValid;
	private Integer trueOrFalse;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbHistoryquestion() {
	}

	/** full constructor */
	public TbHistoryquestion(TbPracticetitle tbPracticetitle,
			TbUserinfo tbUserinfo, TbTest tbTest, String answer,
			Integer isValid, Integer trueOrFalse, Timestamp time,
			Timestamp alterTime) {
		this.tbPracticetitle = tbPracticetitle;
		this.tbUserinfo = tbUserinfo;
		this.tbTest = tbTest;
		this.answer = answer;
		this.isValid = isValid;
		this.trueOrFalse = trueOrFalse;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbHistoryquestionWeb toPageWeb() {
		GogoServiceImpl gogoServiceImpl = new GogoServiceImpl();
		TbTest tbTest1=null;
		if(this.tbTest!=null&&this.tbTest.getTestId()!=null)
			tbTest1=gogoServiceImpl.getObjectByClazz(TbTest.class, this.tbTest.getTestId());
		if(tbTest1==null)
			tbTest1=new TbTest();
		TbPracticetitle tbPracticetitle1=null;
		if(this.tbPracticetitle!=null&&this.tbPracticetitle.getPracticeId()!=null)
			tbPracticetitle1=gogoServiceImpl.getObjectByClazz(TbPracticetitle.class, this.tbPracticetitle.getPracticeId());
		if(tbPracticetitle1==null)
			tbPracticetitle1=new TbPracticetitle();
		TbUserinfo tbUserinfo1=null;
		if(this.tbUserinfo!=null&&this.tbUserinfo.getUserInfoId()!=null)
			tbUserinfo1=gogoServiceImpl.getObjectByClazz(TbUserinfo.class, this.tbUserinfo.getUserInfoId());
		if(tbUserinfo1==null)
			tbUserinfo1=new TbUserinfo();
		return new TbHistoryquestionWeb(this.hisqueId, tbPracticetitle1.getPracticeId(), TWDataUtil.getStringByBlob(tbPracticetitle1.getPracticeTopic()),tbTest1.getTestId(), tbTest1.getTestName(), tbUserinfo1.getUserInfoId(), this.answer, tbUserinfo1.getUserInfoName(), this.isValid,this.trueOrFalse,TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(alterTime));
	}
	// Property accessors

	public Integer getHisqueId() {
		return this.hisqueId;
	}

	public void setHisqueId(Integer hisqueId) {
		this.hisqueId = hisqueId;
	}

	public TbPracticetitle getTbPracticetitle() {
		return this.tbPracticetitle;
	}

	public void setTbPracticetitle(TbPracticetitle tbPracticetitle) {
		this.tbPracticetitle = tbPracticetitle;
	}

	public TbUserinfo getTbUserinfo() {
		return this.tbUserinfo;
	}

	public void setTbUserinfo(TbUserinfo tbUserinfo) {
		this.tbUserinfo = tbUserinfo;
	}

	public TbTest getTbTest() {
		return this.tbTest;
	}

	public void setTbTest(TbTest tbTest) {
		this.tbTest = tbTest;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getTrueOrFalse() {
		return this.trueOrFalse;
	}

	public void setTrueOrFalse(Integer trueOrFalse) {
		this.trueOrFalse = trueOrFalse;
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