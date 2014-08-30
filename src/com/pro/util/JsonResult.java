package com.pro.util;

import java.util.HashMap;
import java.util.Map;


public class JsonResult {
	private boolean succ=true;
	private int code = 0;
	private String message="成功";
	private Map<String, Object> data;
	public JsonResult(boolean succ,int code,String message,Map<String, Object> data){
		this.code = code;
		this.message = message;
		this.data = data;
		this.succ = succ;
	}
	
	public boolean isSucc() {
		return succ;
	}

	public void setSucc(boolean succ) {
		this.succ = succ;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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

	public static JsonResult succJson(Map<String, Object> data) {
		return new JsonResult(true, 0, "成功", data);
	}
	public static JsonResult nullJson(String errMsg) {
		return new JsonResult(true, 0, errMsg, new HashMap<String, Object>());
	}

	public static JsonResult failJson(int errcode, String errMsg) {
		return new JsonResult(false, errcode, errMsg,
				new HashMap<String, Object>());
	}
}	
