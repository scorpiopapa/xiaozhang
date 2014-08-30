package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbUserphotoWeb;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

/**
 * TbUserphoto entity. @author MyEclipse Persistence Tools
 */

public class TbUserphoto implements java.io.Serializable {

	// Fields

	private Integer photoId;
	private TbUserinfo tbUserinfo;
	private TbBranchschool tbBranchschool;
	private String photoName;
	private String photoPath;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbUserphoto() {
	}

	/** full constructor */
	public TbUserphoto(TbUserinfo tbUserinfo, TbBranchschool tbBranchschool,
			String photoName, String photoPath, Integer isValid,
			Timestamp time, Timestamp alterTime) {
		this.tbUserinfo = tbUserinfo;
		this.tbBranchschool = tbBranchschool;
		this.photoName = photoName;
		this.photoPath = photoPath;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbUserphotoWeb toPageWeb(){
		TbUserinfo tbUserinfo = new TWObject().isNullTbUserinfo(this.tbUserinfo);
		return new TbUserphotoWeb(this.photoId, tbUserinfo.getUserInfoId(), tbUserinfo.getUserInfoName(), this.photoName, TWObjectUtil.getNomalPicPath(this.photoPath), this.isValid, TWObjectUtil.getTimestamp(this.time),  TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
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

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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