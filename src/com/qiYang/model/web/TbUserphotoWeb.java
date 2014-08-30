package com.qiYang.model.web;

public class TbUserphotoWeb {
	private Integer photoId;
	private Integer userinfoId;
	private String userinfoName;
	private String photoName;
	private String photoPath;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbUserphotoWeb(Integer photoId, Integer userinfoId,
			String userinfoName, String photoName, String photoPath,
			Integer isValid, String time, String alterTime) {
		super();
		this.photoId = photoId;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.photoName = photoName;
		this.photoPath = photoPath;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	
	public TbUserphotoWeb() {
		super();
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
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

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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
