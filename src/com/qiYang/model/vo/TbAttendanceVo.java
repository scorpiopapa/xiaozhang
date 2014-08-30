package com.qiYang.model.vo;

import com.qiYang.model.TbUserinfo;

public class TbAttendanceVo {
	private TbUserinfo tbUserinfo;
	private Integer type;
	
	
	public TbAttendanceVo(Integer id, Integer type) {
		super();
		TbUserinfo userinfo=new TbUserinfo();
		userinfo.setUserInfoId(id);
		this.tbUserinfo = userinfo;
		this.type = type;
	}
	public TbUserinfo getTbUserinfo() {
		return tbUserinfo;
	}
	public void setTbUserinfo(TbUserinfo tbUserinfo) {
		this.tbUserinfo = tbUserinfo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
