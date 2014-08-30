package com.qiYang.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.web.TbPracticetitleWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.TWDataUtil;
import com.qiYang.util.TWObjectUtil;

/**
 * TbPracticetitle entity. @author MyEclipse Persistence Tools
 */

public class TbPracticetitle implements java.io.Serializable {

	// Fields

	private Integer practiceId;
	private TbTest tbTest;
	private Blob practiceTopic;
	private String answer;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbHistoryquestions = new HashSet(0);
	private Set tbPracticeoptions = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbPracticetitle() {
	}

	/** full constructor */
	public TbPracticetitle(TbTest tbTest, Blob practiceTopic, String answer,
			Integer isValid, Timestamp time, Timestamp alterTime,
			Set tbHistoryquestions, Set tbPracticeoptions) {
		this.tbTest = tbTest;
		this.practiceTopic = practiceTopic;
		this.answer = answer;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbHistoryquestions = tbHistoryquestions;
		this.tbPracticeoptions = tbPracticeoptions;
	}
	public TbPracticetitle(TbTest tbTest, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		super();
		this.tbTest = tbTest;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbPracticetitleWeb toWeb() {
		Map<String, Object> map=new HashMap<String, Object>();
		TbPracticetitle tbPracticetitle=new TbPracticetitle();
		tbPracticetitle.setPracticeId(this.practiceId);
		map.put("tbPracticetitle", tbPracticetitle);
		 List<TbPracticeoption> tbPracticeoptions = new DataBaseDaoImpl().getObjects(TbPracticeoption.class, map);
		 List list = TWObjectUtil.getListWeb(tbPracticeoptions, "TbPracticeoptionWeb");
		return new TbPracticetitleWeb(this.practiceId, TWDataUtil.getStringByBlob(this.practiceTopic), TWObjectUtil.getString(answer), list);
	}
	public TbPracticetitleWeb toPageWeb() {
		GogoServiceImpl gogoServiceImpl=new GogoServiceImpl();
		TbTest tbTest1=null;
		if(this.tbTest!=null&&this.tbTest.getTestId()!=null)
			tbTest1=gogoServiceImpl.getObjectByClazz(TbTest.class, this.tbTest.getTestId());
		if(tbTest1==null)
			tbTest1=new TbTest();
		return new TbPracticetitleWeb(this.practiceId, tbTest1.getTestId(), tbTest1.getTestName(),this.answer, TWDataUtil.getStringByBlob(this.practiceTopic), this.isValid, TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getPracticeId() {
		return this.practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}

	public TbTest getTbTest() {
		return this.tbTest;
	}

	public void setTbTest(TbTest tbTest) {
		this.tbTest = tbTest;
	}

	public Blob getPracticeTopic() {
		return this.practiceTopic;
	}

	public void setPracticeTopic(Blob practiceTopic) {
		this.practiceTopic = practiceTopic;
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

	public Set getTbHistoryquestions() {
		return this.tbHistoryquestions;
	}

	public void setTbHistoryquestions(Set tbHistoryquestions) {
		this.tbHistoryquestions = tbHistoryquestions;
	}

	public Set getTbPracticeoptions() {
		return this.tbPracticeoptions;
	}

	public void setTbPracticeoptions(Set tbPracticeoptions) {
		this.tbPracticeoptions = tbPracticeoptions;
	}

}