package com.qiYang.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.qiYang.dao.BaseDao;

/**
 * TbCurriculumCopy entity. @author MyEclipse Persistence Tools
 */

public class TbCurriculumCopy implements java.io.Serializable {

	// Fields

	private Integer courseId;
	private TbTown tbTown;
	private TbLesson tbLesson;
	private TbSchool tbSchool;
	private TbSubject tbSubject;
	private TbGrade tbGrade;
	private TbBranchschool tbBranchschool;
	private Long defineSort;
	private Long defineSort2;
	private String courseName;
	private String courseScore;
	private Integer isGood;
	private String courseUrl;
	private String courseIntroduce;
	private String courseGoodness;
	private String enterNeed;
	private Integer isValid;
	private String studyGoal;
	private String grades;
	private String courseBook;
	private String startTerm;
	private String endTerm;
	private String studyTime;
	private String totalStudyTime;
	private String studyCosts;
	private String textbookCost;
	private String direction;
	private Integer quantity;
	private String teamPrice;
	private String phone;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbCurriculumCopy() {
	}

	/** full constructor */
	public TbCurriculumCopy(TbTown tbTown, TbLesson tbLesson,
			TbSchool tbSchool, TbSubject tbSubject, TbGrade tbGrade,
			TbBranchschool tbBranchschool, Long defineSort, Long defineSort2,
			String courseName, String courseScore, Integer isGood,
			String courseUrl, String courseIntroduce, String courseGoodness,
			String enterNeed, Integer isValid, String studyGoal, String grades,
			String courseBook, String startTerm, String endTerm,
			String studyTime, String totalStudyTime, String studyCosts,
			String textbookCost, String direction, Integer quantity,
			String teamPrice, String phone, Timestamp time, Timestamp alterTime) {
		this.tbTown = tbTown;
		this.tbLesson = tbLesson;
		this.tbSchool = tbSchool;
		this.tbSubject = tbSubject;
		this.tbGrade = tbGrade;
		this.tbBranchschool = tbBranchschool;
		this.defineSort = defineSort;
		this.defineSort2 = defineSort2;
		this.courseName = courseName;
		this.courseScore = courseScore;
		this.isGood = isGood;
		this.courseUrl = courseUrl;
		this.courseIntroduce = courseIntroduce;
		this.courseGoodness = courseGoodness;
		this.enterNeed = enterNeed;
		this.isValid = isValid;
		this.studyGoal = studyGoal;
		this.grades = grades;
		this.courseBook = courseBook;
		this.startTerm = startTerm;
		this.endTerm = endTerm;
		this.studyTime = studyTime;
		this.totalStudyTime = totalStudyTime;
		this.studyCosts = studyCosts;
		this.textbookCost = textbookCost;
		this.direction = direction;
		this.quantity = quantity;
		this.teamPrice = teamPrice;
		this.phone = phone;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbCurriculumCopy(TbCurriculum tbCurriculum) {
		if(tbCurriculum!=null){
		this.tbTown = tbCurriculum.getTbTown();
		this.tbLesson = tbCurriculum.getTbLesson();
		this.tbSchool = tbCurriculum.getTbSchool();
		this.tbSubject = tbCurriculum.getTbSubject();
		this.tbGrade = tbCurriculum.getTbGrade();
		this.tbBranchschool = tbCurriculum.getTbBranchschool();
		this.defineSort = tbCurriculum.getDefineSort();
		this.defineSort2 = tbCurriculum.getDefineSort2();
		this.courseName = tbCurriculum.getCourseName();
		this.courseScore = tbCurriculum.getCourseScore();
		this.isGood = tbCurriculum.getIsGood();
		this.courseUrl = tbCurriculum.getCourseUrl();
		this.courseIntroduce = tbCurriculum.getCourseIntroduce();
		this.courseGoodness = tbCurriculum.getCourseGoodness();
		this.enterNeed = tbCurriculum.getEnterNeed();
		this.isValid = tbCurriculum.getIsValid();
		this.studyGoal = tbCurriculum.getStudyGoal();
		this.grades = tbCurriculum.getGrades();
		this.courseBook = tbCurriculum.getCourseBook();
		this.startTerm = tbCurriculum.getStartTerm();
		this.endTerm = tbCurriculum.getEndTerm();
		this.studyTime = tbCurriculum.getStudyTime();
		this.totalStudyTime = tbCurriculum.getTotalStudyTime();
		this.studyCosts = tbCurriculum.getStudyCosts();
		this.textbookCost = tbCurriculum.getTextbookCost();
		this.direction = tbCurriculum.getDirection();
		this.phone = tbCurriculum.getPhone();
		this.time = tbCurriculum.getTime();
		this.alterTime = tbCurriculum.getAlterTime();
		DetachedCriteria dc=DetachedCriteria.forClass(TbCurriculum02.class);
		dc.add(Restrictions.eq("curriculumId", tbCurriculum.getCourseId()));
		ArrayList<TbCurriculum02> curriculum02s = new BaseDao().dCList(TbCurriculum02.class, dc);
		if(curriculum02s==null||curriculum02s.isEmpty()){
			this.quantity = 0;
			this.teamPrice = tbCurriculum.getStudyCosts();
		}else{
			TbCurriculum02 curriculum02 = curriculum02s.get(0);
			this.quantity = curriculum02.getQuantity()==null?0:curriculum02.getQuantity();
			this.teamPrice = curriculum02.getTeamPrice()==null?tbCurriculum.getStudyCosts():curriculum02.getTeamPrice();
		}
		}
	}

	// Property accessors

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public TbTown getTbTown() {
		return this.tbTown;
	}

	public void setTbTown(TbTown tbTown) {
		this.tbTown = tbTown;
	}

	public TbLesson getTbLesson() {
		return this.tbLesson;
	}

	public void setTbLesson(TbLesson tbLesson) {
		this.tbLesson = tbLesson;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public TbSubject getTbSubject() {
		return this.tbSubject;
	}

	public void setTbSubject(TbSubject tbSubject) {
		this.tbSubject = tbSubject;
	}

	public TbGrade getTbGrade() {
		return this.tbGrade;
	}

	public void setTbGrade(TbGrade tbGrade) {
		this.tbGrade = tbGrade;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public Long getDefineSort() {
		return this.defineSort;
	}

	public void setDefineSort(Long defineSort) {
		this.defineSort = defineSort;
	}

	public Long getDefineSort2() {
		return this.defineSort2;
	}

	public void setDefineSort2(Long defineSort2) {
		this.defineSort2 = defineSort2;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseScore() {
		return this.courseScore;
	}

	public void setCourseScore(String courseScore) {
		this.courseScore = courseScore;
	}

	public Integer getIsGood() {
		return this.isGood;
	}

	public void setIsGood(Integer isGood) {
		this.isGood = isGood;
	}

	public String getCourseUrl() {
		return this.courseUrl;
	}

	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}

	public String getCourseIntroduce() {
		return this.courseIntroduce;
	}

	public void setCourseIntroduce(String courseIntroduce) {
		this.courseIntroduce = courseIntroduce;
	}

	public String getCourseGoodness() {
		return this.courseGoodness;
	}

	public void setCourseGoodness(String courseGoodness) {
		this.courseGoodness = courseGoodness;
	}

	public String getEnterNeed() {
		return this.enterNeed;
	}

	public void setEnterNeed(String enterNeed) {
		this.enterNeed = enterNeed;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getStudyGoal() {
		return this.studyGoal;
	}

	public void setStudyGoal(String studyGoal) {
		this.studyGoal = studyGoal;
	}

	public String getGrades() {
		return this.grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getCourseBook() {
		return this.courseBook;
	}

	public void setCourseBook(String courseBook) {
		this.courseBook = courseBook;
	}

	public String getStartTerm() {
		return this.startTerm;
	}

	public void setStartTerm(String startTerm) {
		this.startTerm = startTerm;
	}

	public String getEndTerm() {
		return this.endTerm;
	}

	public void setEndTerm(String endTerm) {
		this.endTerm = endTerm;
	}

	public String getStudyTime() {
		return this.studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public String getTotalStudyTime() {
		return this.totalStudyTime;
	}

	public void setTotalStudyTime(String totalStudyTime) {
		this.totalStudyTime = totalStudyTime;
	}

	public String getStudyCosts() {
		return this.studyCosts;
	}

	public void setStudyCosts(String studyCosts) {
		this.studyCosts = studyCosts;
	}

	public String getTextbookCost() {
		return this.textbookCost;
	}

	public void setTextbookCost(String textbookCost) {
		this.textbookCost = textbookCost;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTeamPrice() {
		return this.teamPrice;
	}

	public void setTeamPrice(String teamPrice) {
		this.teamPrice = teamPrice;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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