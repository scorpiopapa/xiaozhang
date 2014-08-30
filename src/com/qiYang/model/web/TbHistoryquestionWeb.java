package com.qiYang.model.web;

import java.sql.Timestamp;

import com.qiYang.model.TbHistoryquestion;
import com.qiYang.model.TbPracticetitle;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbUserinfo;

public class TbHistoryquestionWeb implements TransitionModel{
	private Integer hisqueId;
	private Integer practiceId;
	private String practiceName;
	private Integer testId;
	private String testName;
	private Integer userinfoId;
	private String answer;
	private String userinfoName;
	private Integer isValid;
	private Integer trueOrFalse;
	private String time;
	private String alterTime;

	
	
	public Integer getTrueOrFalse() {
		return trueOrFalse;
	}

	public void setTrueOrFalse(Integer trueOrFalse) {
		this.trueOrFalse = trueOrFalse;
	}
	
	public TbHistoryquestionWeb(Integer hisqueId, Integer practiceId,
			String practiceName, Integer testId, String testName,
			Integer userinfoId, String answer, String userinfoName,
			Integer isValid, Integer trueOrFalse, String time, String alterTime) {
		super();
		this.hisqueId = hisqueId;
		this.practiceId = practiceId;
		this.practiceName = practiceName;
		this.testId = testId;
		this.testName = testName;
		this.userinfoId = userinfoId;
		this.answer = answer;
		this.userinfoName = userinfoName;
		this.isValid = isValid;
		this.trueOrFalse = trueOrFalse;
		this.time = time;
		this.alterTime = alterTime;
	}
	
	public TbHistoryquestionWeb(Integer hisqueId, Integer practiceId,
			String practiceName, Integer userinfoId, String answer,
			String userinfoName, Integer isValid, String time, String alterTime) {
		super();
		this.hisqueId = hisqueId;
		this.practiceId = practiceId;
		this.practiceName = practiceName;
		this.userinfoId = userinfoId;
		this.answer = answer;
		this.userinfoName = userinfoName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbHistoryquestionWeb() {
		super();
	}

	

	public Integer getHisqueId() {
		return hisqueId;
	}

	public void setHisqueId(Integer hisqueId) {
		this.hisqueId = hisqueId;
	}

	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public Integer getUserinfoId() {
		return userinfoId;
	}

	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}

	public String getUserinfoName() {
		return userinfoName;
	}

	public void setUserinfoName(String userinfoName) {
		this.userinfoName = userinfoName;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public <T> T toModel(Class<T> clazz) {
		TbPracticetitle tbPracticetitle=new TbPracticetitle();
		tbPracticetitle.setPracticeId(this.practiceId);
		Timestamp nowTime=new Timestamp(System.currentTimeMillis());
		TbUserinfo tbUserinfo=new TbUserinfo();
		tbUserinfo.setUserInfoId(this.userinfoId);
		TbTest tbTest=new TbTest();
		tbTest.setTestId(testId);
		return  (T) new TbHistoryquestion(tbPracticetitle, tbUserinfo,tbTest, this.answer, 1,this.trueOrFalse, nowTime, nowTime);
	}
}
