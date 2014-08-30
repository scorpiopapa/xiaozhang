package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.web.TbTestWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.TWObjectUtil;

/**
 * TbTest entity. @author MyEclipse Persistence Tools
 */

public class TbTest implements java.io.Serializable {

	// Fields

	private Integer testId;
	private TbCourse tbCourse;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private String testName;
	private String addDate;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbTestfinishs = new HashSet(0);
	private Set tbPracticetitles = new HashSet(0);
	private Set tbHistoryquestions = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbTest() {
	}

	/** full constructor */
	public TbTest(TbCourse tbCourse, TbUserinfo tbUserinfo,
			TbBranchschool tbBranchschool, String testName, String addDate,
			Integer isValid, Timestamp time, Timestamp alterTime,
			Set tbTestfinishs, Set tbPracticetitles, Set tbHistoryquestions) {
		this.tbCourse = tbCourse;
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.testName = testName;
		this.addDate = addDate;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbTestfinishs = tbTestfinishs;
		this.tbPracticetitles = tbPracticetitles;
		this.tbHistoryquestions = tbHistoryquestions;
	}
	public TbTestWeb toPageWeb() {
		GogoServiceImpl gogoServiceImpl=new GogoServiceImpl();
		TbCourse tbCourse1=null;
		if(this.tbCourse!=null&&this.tbCourse.getCourseId()!=null)
			tbCourse1=gogoServiceImpl.getObjectByClazz(TbCourse.class, this.tbCourse.getCourseId());
		if(tbCourse1==null)
			tbCourse1=new TbCourse();
		return new TbTestWeb(this.testId, tbCourse1.getCourseId(), tbCourse1.getCourseName(), this.testName, TWObjectUtil.getString(this.addDate), this.isValid, TWObjectUtil.getTimestamp(time), TWObjectUtil.getTimestamp(alterTime));
	}
	public TbTestWeb toListWeb(TbUserinfo tbUserinfo) {
		DataBaseDaoImpl daoImpl = new DataBaseDaoImpl();
		TbTest tbTest=new TbTest();
		tbTest.setTestId(this.testId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tbUserinfo", tbUserinfo);
		map.put("tbTest", tbTest);
		List<TbTestfinish> list = daoImpl.getObjects(TbTestfinish.class, map);
		TbTestfinish tbTestfinish=null;
		if(list==null||list.isEmpty())
			tbTestfinish=new TbTestfinish();
		else
			tbTestfinish=list.get(0);
		if(tbTestfinish==null)
			tbTestfinish=new TbTestfinish();
		int isfin = tbTestfinish.getIsfinish()==null?0:tbTestfinish.getIsfinish();
		return new TbTestWeb(this.testId, TWObjectUtil.getString(this.testName), TWObjectUtil.getString(this.addDate), TWObjectUtil.getString(tbTestfinish.getRightPercentage()), isfin);
	}
	// Property accessors

	public Integer getTestId() {
		return this.testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public TbCourse getTbCourse() {
		return this.tbCourse;
	}

	public void setTbCourse(TbCourse tbCourse) {
		this.tbCourse = tbCourse;
	}

	public TbUserinfo getTbUserinfo() {
		return this.tbUserinfo;
	}

	public void setTbUserinfo(TbUserinfo tbUserinfo) {
		this.tbUserinfo = tbUserinfo;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
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

	public Set getTbTestfinishs() {
		return this.tbTestfinishs;
	}

	public void setTbTestfinishs(Set tbTestfinishs) {
		this.tbTestfinishs = tbTestfinishs;
	}

	public Set getTbPracticetitles() {
		return this.tbPracticetitles;
	}

	public void setTbPracticetitles(Set tbPracticetitles) {
		this.tbPracticetitles = tbPracticetitles;
	}

	public Set getTbHistoryquestions() {
		return this.tbHistoryquestions;
	}

	public void setTbHistoryquestions(Set tbHistoryquestions) {
		this.tbHistoryquestions = tbHistoryquestions;
	}

}