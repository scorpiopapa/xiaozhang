package com.qiYang.model.web;

import java.sql.Timestamp;
import java.util.Date;

import com.qiYang.model.TbPracticeoption;
import com.qiYang.model.TbPracticetitle;
import com.qiYang.util.TWDataUtil;

public class TbPracticeoptionWeb implements TransitionModel{
	private Integer praoptId;
	private Integer practicetitleId;
	private String practicetitleName;
	private String praOption;
	private String optionContent;
	private String answer;
	private Integer isValid;
	private String time;
	private String alterTime;

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public TbPracticeoptionWeb(String praOption, String optionContent) {
		super();
		this.praOption = praOption;
		this.optionContent = optionContent;
	}

	public TbPracticeoptionWeb(Integer praoptId, Integer practicetitleId,
			String practicetitleName, String praOption, String optionContent,
			String answer, Integer isValid, String time, String alterTime) {
		super();
		this.praoptId = praoptId;
		this.practicetitleId = practicetitleId;
		this.practicetitleName = practicetitleName;
		this.praOption = praOption;
		this.optionContent = optionContent;
		this.answer = answer;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public <T> T toModel(Class<T> clazz) {
		TbPracticetitle tbPracticetitle=new TbPracticetitle();
		tbPracticetitle.setPracticeId(this.practicetitleId);
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		return (T) new TbPracticeoption(tbPracticetitle, this.praOption, TWDataUtil.getBlobByString(this.optionContent), 1, timestamp, timestamp);
	}

	public TbPracticeoptionWeb() {
		super();
	}

	public Integer getPraoptId() {
		return praoptId;
	}

	public void setPraoptId(Integer praoptId) {
		this.praoptId = praoptId;
	}

	public Integer getPracticetitleId() {
		return practicetitleId;
	}

	public void setPracticetitleId(Integer practicetitleId) {
		this.practicetitleId = practicetitleId;
	}

	public String getPracticetitleName() {
		return practicetitleName;
	}

	public void setPracticetitleName(String practicetitleName) {
		this.practicetitleName = practicetitleName;
	}

	public String getPraOption() {
		return praOption;
	}

	public void setPraOption(String praOption) {
		this.praOption = praOption;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
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
