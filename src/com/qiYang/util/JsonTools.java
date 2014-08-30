package com.qiYang.util;

import com.google.gson.Gson;

public class JsonTools {

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	public static String rendJson(Object value) {
		return new Gson().toJson(value);
	}
	public static String rendJson(ResultJson value) {
		return new Gson().toJson(value);
	}
}
