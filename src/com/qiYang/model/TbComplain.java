package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbComplain entity. @author MyEclipse Persistence Tools
 */

public class TbComplain implements java.io.Serializable {

	// Fields

	private Integer complainId;
	private TbCourse tbCourse;
	private TbSchool tbSchool;
	private TbUserinfo tbUserinfoByUserInfoId;
	private TbBranchschool tbBranchschool;
	private TbUserinfo tbUserinfoByTbUserInfoId;
	private String complainContent;
	private Integer isValid;
	private String complainStatus;
	private Integer isSatisfie;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbComplaindetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbComplain() {
	}

	/** full constructor */
	public TbComplain(TbCourse tbCourse, TbSchool tbSchool,
			TbUserinfo tbUserinfoByUserInfoId, TbBranchschool tbBranchschool,
			TbUserinfo tbUserinfoByTbUserInfoId, String complainContent,
			Integer isValid, String complainStatus, Integer isSatisfie,
			Timestamp time, Timestamp alterTime, Set tbComplaindetailses) {
		this.tbCourse = tbCourse;
		this.tbSchool = tbSchool;
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
		this.tbBranchschool = tbBranchschool;
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
		this.complainContent = complainContent;
		this.isValid = isValid;
		this.complainStatus = complainStatus;
		this.isSatisfie = isSatisfie;
		this.time = time;
		this.alterTime = alterTime;
		this.tbComplaindetailses = tbComplaindetailses;
	}

	// Property accessors

	public Integer getComplainId() {
		return this.complainId;
	}

	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}

	public TbCourse getTbCourse() {
		return this.tbCourse;
	}

	public void setTbCourse(TbCourse tbCourse) {
		this.tbCourse = tbCourse;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public TbUserinfo getTbUserinfoByUserInfoId() {
		return this.tbUserinfoByUserInfoId;
	}

	public void setTbUserinfoByUserInfoId(TbUserinfo tbUserinfoByUserInfoId) {
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public TbUserinfo getTbUserinfoByTbUserInfoId() {
		return this.tbUserinfoByTbUserInfoId;
	}

	public void setTbUserinfoByTbUserInfoId(TbUserinfo tbUserinfoByTbUserInfoId) {
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
	}

	public String getComplainContent() {
		return this.complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getComplainStatus() {
		return this.complainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
	}

	public Integer getIsSatisfie() {
		return this.isSatisfie;
	}

	public void setIsSatisfie(Integer isSatisfie) {
		this.isSatisfie = isSatisfie;
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

	public Set getTbComplaindetailses() {
		return this.tbComplaindetailses;
	}

	public void setTbComplaindetailses(Set tbComplaindetailses) {
		this.tbComplaindetailses = tbComplaindetailses;
	}

}