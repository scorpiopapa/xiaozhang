package com.qiYang.model.web;

public class TbCourseWeb {
	private Integer courseId;
	private Integer townId;
	private String townName;
	private String subjectName;
	private Integer subjectId;
	private Integer gradeId;
	private String gradeName;
	private Integer branchschoolId;
	private String branchschoolName;
	private String courseName;
	private String courseScore;
	private Integer isGood;
	private String courseIntroduce;
	private String courseGoodness;
	private String enterNeed;
	private Integer isValid;
	private String studyGoal;
	private String courseBook;
	private String startTerm;
	private String endTerm;
	private Integer studyTime;
	private Integer totalStudyTime;
	private Integer studyCosts;
	private Integer textbookCost;
	private String direction;
	private String phone;
	private String time;
	private String alterTime;
	
	public TbCourseWeb(Integer courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public TbCourseWeb(Integer courseId, Integer townId, String townName,
			String subjectName, Integer subjectId, Integer gradeId,
			String gradeName, Integer branchschoolId, String branchschoolName,
			String courseName, String courseScore, Integer isGood,
			String courseIntroduce, String courseGoodness, String enterNeed,
			Integer isValid, String studyGoal, String courseBook,
			String startTerm, String endTerm, Integer studyTime,
			Integer totalStudyTime, Integer studyCosts, Integer textbookCost,
			String direction, String phone, String time, String alterTime) {
		super();
		this.courseId = courseId;
		this.townId = townId;
		this.townName = townName;
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.courseName = courseName;
		this.courseScore = courseScore;
		this.isGood = isGood;
		this.courseIntroduce = courseIntroduce;
		this.courseGoodness = courseGoodness;
		this.enterNeed = enterNeed;
		this.isValid = isValid;
		this.studyGoal = studyGoal;
		this.courseBook = courseBook;
		this.startTerm = startTerm;
		this.endTerm = endTerm;
		this.studyTime = studyTime;
		this.totalStudyTime = totalStudyTime;
		this.studyCosts = studyCosts;
		this.textbookCost = textbookCost;
		this.direction = direction;
		this.phone = phone;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbCourseWeb() {
		super();
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getBranchschoolId() {
		return branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public String getBranchschoolName() {
		return branchschoolName;
	}

	public void setBranchschoolName(String branchschoolName) {
		this.branchschoolName = branchschoolName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseScore() {
		return courseScore;
	}

	public void setCourseScore(String courseScore) {
		this.courseScore = courseScore;
	}

	public Integer getIsGood() {
		return isGood;
	}

	public void setIsGood(Integer isGood) {
		this.isGood = isGood;
	}

	public String getCourseIntroduce() {
		return courseIntroduce;
	}

	public void setCourseIntroduce(String courseIntroduce) {
		this.courseIntroduce = courseIntroduce;
	}

	public String getCourseGoodness() {
		return courseGoodness;
	}

	public void setCourseGoodness(String courseGoodness) {
		this.courseGoodness = courseGoodness;
	}

	public String getEnterNeed() {
		return enterNeed;
	}

	public void setEnterNeed(String enterNeed) {
		this.enterNeed = enterNeed;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getStudyGoal() {
		return studyGoal;
	}

	public void setStudyGoal(String studyGoal) {
		this.studyGoal = studyGoal;
	}

	public String getCourseBook() {
		return courseBook;
	}

	public void setCourseBook(String courseBook) {
		this.courseBook = courseBook;
	}

	public String getStartTerm() {
		return startTerm;
	}

	public void setStartTerm(String startTerm) {
		this.startTerm = startTerm;
	}

	public String getEndTerm() {
		return endTerm;
	}

	public void setEndTerm(String endTerm) {
		this.endTerm = endTerm;
	}

	public Integer getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(Integer studyTime) {
		this.studyTime = studyTime;
	}

	public Integer getTotalStudyTime() {
		return totalStudyTime;
	}

	public void setTotalStudyTime(Integer totalStudyTime) {
		this.totalStudyTime = totalStudyTime;
	}

	public Integer getStudyCosts() {
		return studyCosts;
	}

	public void setStudyCosts(Integer studyCosts) {
		this.studyCosts = studyCosts;
	}

	public Integer getTextbookCost() {
		return textbookCost;
	}

	public void setTextbookCost(Integer textbookCost) {
		this.textbookCost = textbookCost;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
