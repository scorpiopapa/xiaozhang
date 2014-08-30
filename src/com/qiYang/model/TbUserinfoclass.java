package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbUserinfoclassWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;
import com.qiYang.util.TWPictureUtil;

/**
 * TbUserinfoclass entity. @author MyEclipse Persistence Tools
 */

public class TbUserinfoclass implements java.io.Serializable {

	// Fields

	private Integer useclaId;
	private TbCourse tbCourse;
	private TbCurriculum tbCurriculum;
	private TbSchool tbSchool;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private Integer userRoot;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbUserinfoclass() {
	}

	/** full constructor */
	public TbUserinfoclass(TbCourse tbCourse, TbCurriculum tbCurriculum,
			TbSchool tbSchool, TbUserinfo tbUserinfo,
			TbBranchschool tbBranchschool, Integer userRoot, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.tbCourse = tbCourse;
		this.tbCurriculum = tbCurriculum;
		this.tbSchool = tbSchool;
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.userRoot = userRoot;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbUserinfoclassWeb toLoginWeb() {
		TbBranchschool branchschool = this.tbBranchschool == null ? new TbBranchschool()
				: this.tbBranchschool;
		Integer branchschoolId = branchschool.getBranchSchoolId();
		Object object = null;
		if (branchschoolId != null)
			object = new GogoServiceImpl().getObjectByClazz(TbBranchschool.class,
					branchschoolId);
		if (object != null) {
			if (object instanceof TbBranchschool) {
				TbBranchschool tbBranchschool = (TbBranchschool) object;
				String braschPictureUrl = tbBranchschool.getBraschPictureUrl() == null ? ""
						: TWPictureUtil.getNomalPicPath(tbBranchschool
								.getBraschPictureUrl());
				return new TbUserinfoclassWeb(
						tbBranchschool.getBranchSchoolId(),
						tbBranchschool.getBraschName(),
						tbBranchschool.getBraschMinName(), braschPictureUrl);
			}
		}
		return null;
	}

	public TbUserinfoclassWeb toCourseWeb() {
		if (this.tbCourse == null)
			this.tbCourse = new TbCourse();
		Object object = new GogoServiceImpl().getObjectByClazz(TbCourse.class,
				this.tbCourse.getCourseId());
		TbCourse course = new TWObject().getT(TbCourse.class,object);
		return new TbUserinfoclassWeb(course.getCourseId(),
				course.getCourseName());
	}

	public TbUserinfoclassWeb toStudentWeb() {
		TbUserinfo userinfo = this.tbUserinfo == null ? new TbUserinfo()
				: this.tbUserinfo;
		Integer childId = userinfo.getUserInfoId();
		if (childId == null)
			return null;
		TbUserinfo tbUserinfo1 = TWObjectUtil.getTbUserinfo(childId);
		TbUserinfo parent = TWObjectUtil.getParentByChild(tbUserinfo1);
		return new TbUserinfoclassWeb(tbUserinfo1.getUserInfoId(),
				tbUserinfo1.getUserInfoName(),
				TWObjectUtil.getNomalPicPath(tbUserinfo1.getUserInfoAvatar()),TWObjectUtil.getUserName(tbUserinfo1),TWObjectUtil.getUserName(parent),
				tbUserinfo1.getUserInfoSign(), TWObjectUtil.getString(parent
						.getUserInfoName()),TWObjectUtil.getNomalPicPath(parent.getUserInfoAvatar()));
	}
	
	public TbUserinfoclassWeb courseToStudent() {
		TbUserinfo userinfo = this.tbUserinfo == null ? new TbUserinfo()
		: this.tbUserinfo;
		Integer childId = userinfo.getUserInfoId();
		if (childId == null)
			return null;
		TbUserinfo tbUserinfo1 = TWObjectUtil.getTbUserinfo(childId);
		TbUserinfo parent = TWObjectUtil.getParentByChild(tbUserinfo1);
		return new TbUserinfoclassWeb(userinfo.getUserInfoId(), userinfo.getUserInfoName(), userinfo.getUserInfoRoot());
	}
	public TbUserinfoclassWeb toPageWeb() {
		TWObject twObject = new TWObject();
		TbSchool tbSchool = twObject.isNullTbSchool(this.tbSchool);
		TbBranchschool tbBranchschool = twObject.isNullTbBranchschool(this.tbBranchschool);
		TbCourse tbCourse = twObject.isNullTbCourse(this.tbCourse);
		TbUserinfo tbUserinfo = twObject.isNullTbUserinfo(this.tbUserinfo);
		return new TbUserinfoclassWeb(this.useclaId, tbCourse.getCourseName(), tbSchool.getSchoolName(), tbUserinfo.getUserInfoName(), tbBranchschool.getBraschName(), this.userRoot,0, this.isValid, TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	

	public TbUserinfoclassWeb toAttendanceWeb() {
		TbUserinfo tbUserinfo1 = TWObjectUtil.isNullTbUserinfo(this.tbUserinfo);
		TbUserinfoclassWeb tbUserinfoclassWeb = new TbUserinfoclassWeb(tbUserinfo1.getUserInfoId(),
				tbUserinfo1.getUserInfoName(),1,1
				);
		 tbUserinfoclassWeb.setUserinfoAvatar(TWPictureUtil.getNomalPicPath(tbUserinfo1.getUserInfoAvatar()));
		return tbUserinfoclassWeb;
	}
	// Property accessors

	public Integer getUseclaId() {
		return this.useclaId;
	}

	public void setUseclaId(Integer useclaId) {
		this.useclaId = useclaId;
	}

	public TbCourse getTbCourse() {
		return this.tbCourse;
	}

	public void setTbCourse(TbCourse tbCourse) {
		this.tbCourse = tbCourse;
	}

	public TbCurriculum getTbCurriculum() {
		return this.tbCurriculum;
	}

	public void setTbCurriculum(TbCurriculum tbCurriculum) {
		this.tbCurriculum = tbCurriculum;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
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

	public Integer getUserRoot() {
		return this.userRoot;
	}

	public void setUserRoot(Integer userRoot) {
		this.userRoot = userRoot;
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