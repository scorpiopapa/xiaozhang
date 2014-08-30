package com.qiYang.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConvert extends StrutsTypeConverter{

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(arg1[0]==null || arg1[0].equals("")){
				return null;
			}
			return format.parse(arg1[0]);
		} catch (ParseException e) {
			return new Date();
		}
		
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		if (arg1 instanceof Date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format((Date) arg1);
		}
		return "";
	}

}
