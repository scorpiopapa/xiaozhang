package com.qiYang.model.web;

import java.sql.Timestamp;

import com.qiYang.model.TbSysconfig;

public class TbSysconfigWeb implements TransitionModel{
	private Integer sysconfigId;
	private String versionIos;
	private String versionAndroid;
	private String time;
	private String alterTime;

	public TbSysconfigWeb(Integer sysconfigId, String versionIos,
			String versionAndroid, String time, String alterTime) {
		super();
		this.sysconfigId = sysconfigId;
		this.versionIos = versionIos;
		this.versionAndroid = versionAndroid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public <T> T toModel(Class<T> clazz) {
		Timestamp nowTime=new Timestamp(System.currentTimeMillis());
		return (T) new TbSysconfig(this.versionIos, this.versionAndroid,1, nowTime, nowTime);
	}
	public TbSysconfigWeb() {
		super();
	}

	public Integer getSysconfigId() {
		return sysconfigId;
	}

	public void setSysconfigId(Integer sysconfigId) {
		this.sysconfigId = sysconfigId;
	}

	public String getVersionIos() {
		return versionIos;
	}

	public void setVersionIos(String versionIos) {
		this.versionIos = versionIos;
	}

	public String getVersionAndroid() {
		return versionAndroid;
	}

	public void setVersionAndroid(String versionAndroid) {
		this.versionAndroid = versionAndroid;
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
