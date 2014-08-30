package com.qiYang.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultJson {
	public Boolean succ;
	public Integer code;
	public String message;
	public Map<String, Object> data;

	public Boolean getSucc() {
		return succ;
	}

	public void setSucc(Boolean succ) {
		this.succ = succ;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public ResultJson(Boolean succ, Integer code, String message,
			Map<String, Object> data) {
		super();
		this.succ = succ;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	

	public ResultJson() {
		super();
	}

	public static ResultJson crateSuccJson(Map<String, Object> data) {
		return new ResultJson(true, 0, "成功", data);
	}
	
	public static ResultJson crateNullJson(String errMsg) {
		return new ResultJson(true, 0, errMsg, new HashMap<String, Object>(0));
	}
	public static ResultJson crateNullJson() {
		return new ResultJson(true, 1111, "空记录", new HashMap<String, Object>(0));
	}
	public static ResultJson crate1001Json() {
		return new ResultJson(true, 1001, "输入参数不合规格", new HashMap<String, Object>(0));
	}

	public static ResultJson createFailJson(int errcode, String errMsg) {
		return new ResultJson(false, errcode, errMsg,
				new HashMap<String, Object>(0));
	}

	@Override
	public String toString() {
		return "ResultJson [succ=" + succ + ", code=" + code + ", mesage="
				+ message + ", data=" + data  +"]";
	}
	public static ResultJson crateSuccJson(Page page) {
		HashMap<String, Object> map = new HashMap<String, Object>(5);
		map.put("currentPage", page.getCurrentPage()==null?1:page.getCurrentPage());
		map.put("allPages", page.getAllPages()==null?1:page.getAllPages());
		map.put("countPerPage", page.getCountPerPage());
		map.put("allCount", page.getAllCount()==null?0:page.getAllCount());
		map.put("currentList", page.getCurrentList()==null?new ArrayList<Object>(0):page.getCurrentList());
		return new ResultJson(true, 0, "成功", map);
	}

}
