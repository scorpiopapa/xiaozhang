package com.qiYang.model.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.qiYang.model.TbPracticetitle;
import com.qiYang.model.TbTest;
import com.qiYang.util.TWDataUtil;

public class TbPracticetitleWeb implements TransitionModel{
	private Integer practiceId;
	private Integer testId;
	private String testName;
	private String practiceTopic;
	private String answer;
	private String historyAnswer;
	private List<TbPracticeoptionWeb> tbPracticeoptionWeb;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbPracticetitleWeb(Integer practiceId, Integer testId,
			String testName,String answer, String practiceTopic, Integer isValid,
			String time, String alterTime) {
		super();
		this.practiceId = practiceId;
		this.testId = testId;
		this.answer = answer;
		this.testName = testName;
		this.practiceTopic = practiceTopic;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public <T> T toModel(Class<T> clazz) {
		TbTest test=new TbTest();
		test.setTestId(this.testId);
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		return (T) new TbPracticetitle(test, TWDataUtil.getBlobByString(this.practiceTopic), this.answer, 1, timestamp, timestamp, null, null);
	}
	
	public String getAnswer() {
		return answer;
	}

	
	public String getHistoryAnswer() {
		return historyAnswer;
	}
	public void setHistoryAnswer(String historyAnswer) {
		this.historyAnswer = historyAnswer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	


	public TbPracticetitleWeb(Integer practiceId, String practiceTopic,
			String answer, List<TbPracticeoptionWeb> tbPracticeoptionWeb) {
		super();
		this.practiceId = practiceId;
		this.practiceTopic = practiceTopic;
		this.answer = answer;
		this.tbPracticeoptionWeb = tbPracticeoptionWeb;
	}


	public List<TbPracticeoptionWeb> getTbPracticeoptionWeb() {
		return tbPracticeoptionWeb;
	}


	public void setTbPracticeoptionWeb(List<TbPracticeoptionWeb> tbPracticeoptionWeb) {
		this.tbPracticeoptionWeb = tbPracticeoptionWeb;
	}


	public TbPracticetitleWeb() {
		super();
	}

	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
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

	public String getPracticeTopic() {
		return practiceTopic;
	}

	public void setPracticeTopic(String practiceTopic) {
		this.practiceTopic = practiceTopic;
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
}
