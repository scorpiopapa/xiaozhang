package com.qiYang.model.web;

public class TbReviewWeb {
	private Integer reviewId;
	private Integer studentId;
	private String studentName;
	private String courseName;
	private Integer teacherId;
	private String teacherName;
	private String reviewDate;
	private String reviewContent;
	private Integer reviewType;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbReviewWeb(Integer reviewId, Integer studentId, String studentName,
			Integer teacherId, String teacherName, String reviewDate,
			String reviewContent, Integer reviewType, Integer isValid,
			String time, String alterTime) {
		super();
		this.reviewId = reviewId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.reviewType = reviewType;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbReviewWeb() {
		super();
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Integer getReviewType() {
		return reviewType;
	}

	public void setReviewType(Integer reviewType) {
		this.reviewType = reviewType;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
