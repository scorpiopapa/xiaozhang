package com.qiYang.convert;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class MyDoubleTypeConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		try {
			return  Double.parseDouble(values[0]);
		} catch (NumberFormatException e) {
			return new Double(0);
		}
	}

	@Override
	public String convertToString(Map context, Object obj) {
		return obj.toString();
	}

}