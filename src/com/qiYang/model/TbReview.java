package com.qiYang.model;

import java.sql.Timestamp;

/**
 * TbReview entity. @author MyEclipse Persistence Tools
 */

public class TbReview implements java.io.Serializable {

	// Fields

	private Integer reviewId;
	private TbCourse tbCourse;
	private TbUserinfo tbUserinfoByUserInfoId;
	private TbUserinfo tbUserinfoByTbUserInfoId;
	private String addDate;
	private String reviewContent;
	private Integer reviewType;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbReview() {
	}

	/** full constructor */
	public TbReview(TbCourse tbCourse, TbUserinfo tbUserinfoByUserInfoId,
			TbUserinfo tbUserinfoByTbUserInfoId, String addDate,
			String reviewContent, Integer reviewType, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.tbCourse = tbCourse;
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
		this.addDate = addDate;
		this.reviewContent = reviewContent;
		this.reviewType = reviewType;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	// Property accessors

	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public TbCourse getTbCourse() {
		return this.tbCourse;
	}

	public void setTbCourse(TbCourse tbCourse) {
		this.tbCourse = tbCourse;
	}

	public TbUserinfo getTbUserinfoByUserInfoId() {
		return this.tbUserinfoByUserInfoId;
	}

	public void setTbUserinfoByUserInfoId(TbUserinfo tbUserinfoByUserInfoId) {
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
	}

	public TbUserinfo getTbUserinfoByTbUserInfoId() {
		return this.tbUserinfoByTbUserInfoId;
	}

	public void setTbUserinfoByTbUserInfoId(TbUserinfo tbUserinfoByTbUserInfoId) {
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
	}

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getReviewContent() {
		return this.reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Integer getReviewType() {
		return this.reviewType;
	}

	public void setReviewType(Integer reviewType) {
		this.reviewType = reviewType;
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