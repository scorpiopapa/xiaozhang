package com.qiYang.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Hibernate;

import com.qiYang.model.TbAttendance;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbUserinfo;
import com.qiYang.service.GogoServiceImpl;

public class TWDataUtil {
	private static Properties p = new Properties();
	static {
		InputStream inputStream = TWDataUtil.class.getClassLoader()
				.getResourceAsStream("url.properties");
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			System.out.println("读取配置文件IO错误！" + e1.getMessage());
			e1.printStackTrace();
		}
	}
	public static String getUTF8String(String s)
	{
	 byte[] b=null;
	try {
		b = s.getBytes("utf-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	String sTemp="";
	if(b!=null)
	 sTemp=new String(b);
	 return sTemp;
	}
	public static String replaceHtml(String html){
		if(StringUtils.isEmpty(html))
			return "";
        String str = html.replaceAll("<br />","\n\r").replaceAll("&nbsp;", "").replaceAll("●", "\n\r●").replaceAll("<[^>]+>", "").replaceAll("\\{[^\\}]+\\}", "").replaceAll("/[^/]+/", "");
        return str; 
    } 
	public static String dateFormat(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static String TimestampToDate(Timestamp time) {
		return time==null?"":new SimpleDateFormat("yyyy-MM-dd").format(time);
	}
	public static String dateDateyyyyMMdd(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String date(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	public static String dateMPointDFormat(Date date) {
		return new SimpleDateFormat("MM.dd").format(date);
	}

	public static String dateTimeFormat(Timestamp time) {
		return new SimpleDateFormat("MM.dd HH:mm").format(time);
	}
	public static String timestampToHHmm(Timestamp time) {
		return new SimpleDateFormat("HH:mm").format(time);
	}

	public static String TimestampFormat(Timestamp time) {
		if(time==null)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
	}
	

	public static String timeFormat(Time time) {
		return new SimpleDateFormat("HH:mm").format(time);
	}


	public static String getAndroidAppUrl() {
		return p.getProperty("androidAppUrl");
	}
	public static String getBaseUrl() {
		return p.getProperty("baseUrl");
	}
	public static String practiceUrl() {
		return p.getProperty("practiceUrl");
	}
	public static String getStudentMail(String mail){
		return mail.substring(0,mail.indexOf("@"));
	}
	public static String getStudentName(String cityName){
		return cityName.substring(0,cityName.indexOf("市"));
	}
	public static String replace(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		return str.replaceAll("　", ",").replaceAll("，", ",");
	}

	public static String dateFormatMD(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.MONTH) + 1) + "月"
				+ calendar.get(Calendar.DATE) + "日";
	}

	public static String timestampFormatMD(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		return (calendar.get(Calendar.MONTH) + 1) + "月"
				+ calendar.get(Calendar.DATE) + "日";
	}

	public static String dateFormatYMD(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) + "年"
				+ (calendar.get(Calendar.MONTH) + 1) + "月"
				+ calendar.get(Calendar.DATE) + "日";
	}

	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static Date getBeforeDate(Date nowDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		try {
			return DateUtils.parseDate(yesterday, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getLongBeforeDate(Date nowDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		cal.add(Calendar.DATE, -2);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		try {
			return DateUtils.parseDate(yesterday, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getAfterDate(Date nowDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		cal.add(Calendar.DATE, +1);
		String tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		try {
			return DateUtils.parseDate(tomorrow, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	//参数date
	public static Date addMonthEndDate(Date date,Integer month){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH,+month);
		return cal.getTime();
	}
	//参数date
	public static Date addDayEndDate(Date date,Integer day){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR,+day);
		return cal.getTime();
	}
	public static String getPassword(int count, boolean letters, boolean numbers) {
        return org.apache.commons.lang.RandomStringUtils.random(count, letters, numbers);
    }
	public static ArrayList<Integer> getJsonArray(String strArray) {
		JSONArray jsonArray = JSONArray.fromObject(strArray);
		String[] strPath = new String[jsonArray.size()];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < strPath.length; i++) {
			strPath[i] = jsonArray.getString(i);
			list.add(Integer.parseInt(strPath[i]));
		}
		return list;
	}

	public static TbAttendance[] getAttendanceVoJsonArray(String strArray,Integer coureId) {
		JSONObject jo = JSONObject.fromObject(strArray);
		String list = jo.getString("attendanceIds");
		JSONArray jsonArray = JSONArray.fromObject(list);
		GogoServiceImpl gogoServiceImpl=new GogoServiceImpl();
		TbCourse tbCourse = gogoServiceImpl.getObjectByClazz(TbCourse.class, coureId);
		Date date = new Date();
		String date1 = dateFormat(date);
		Timestamp time=new Timestamp(date.getTime());
		TbBranchschool tbBranchschool = gogoServiceImpl.getObjectByClazz(TbBranchschool.class, tbCourse.getTbBranchschool().getBranchSchoolId());
		TbAttendance[] tbAttendances = new TbAttendance[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			 TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(jsonObject.getInt("id"));
			TbAttendance tbAttendance1 = new TbAttendance(tbCourse, tbBranchschool.getTbSchool(),
					userinfo, tbBranchschool,jsonObject.getInt("type"), 1,date1, time, time);
			tbAttendances[i] = tbAttendance1;
		}
		return tbAttendances;
	}

	public static Integer getOrderNum(String str) {
		if ("第一节课".equals(str))
			return 1;
		else if ("第二节课".equals(str))
			return 2;
		else if ("第三节课".equals(str))
			return 3;
		else if ("第四节课".equals(str))
			return 4;
		else if ("第五节课".equals(str))
			return 5;
		else if ("第六节课".equals(str))
			return 6;
		else if ("第七节课".equals(str))
			return 7;
		else if ("第八节课".equals(str))
			return 8;
		else
			return 0;
	}

	public static String replaceBlank(String str) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}
	//Blob转String
	public static String getStringByBlob(Blob blob){
		try {
			if(blob==null||blob.length()==0)
				return "";
			try {
				byte[] tb = blob.getBytes(1,(int) blob.length());
				String str = new String(tb,"UTF-8");
//				System.out.println(str);
				return str;
			}  catch (SQLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	//String转Blob
	public static Blob getBlobByString(String str){
			if(StringUtils.isEmpty(str))
				return null;
			Blob blob=null;
			try {
				blob = Hibernate.createBlob(str.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return blob;
	}

	/**20140304
	 * 池长购
	 * 通过dateS时间(格式比如"2014-03-04")获取当天00:00:00
	 * @param date 时间段
	 * @return 当天起始时间
	 */
	public static Timestamp getStartTimestamp(String dateS){
		Date date = null;
		if(StringUtils.isEmpty(dateS)){
			date=new Date();
		}else{
		try {
			date = DateUtils.parseDate(dateS, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}}
		long nowTimeLong = date.getTime();
	    nowTimeLong = nowTimeLong / (86400000) * (86400000) - TimeZone.getDefault().getRawOffset()+86400000;  
	    return new Timestamp(nowTimeLong);
	}
	/**20140304
	 * 池长购
	 * 通过Date获取当天00:00:00
	 * @param date 
	 * @return 当天起始时间
	 */
	public static Timestamp getStartTimestamp(Date date){
		if(date==null)
			date=new Date();
		long nowTimeLong = date.getTime();
		nowTimeLong = nowTimeLong / (86400000) * (86400000) - TimeZone.getDefault().getRawOffset();  
		return new Timestamp(nowTimeLong);
	}
	/**20140304
	 * 池长购
	 * 通过dateS时间(格式比如"2014-03-04")获取当天23:59:59
	 * @param date 时间段
	 * @return 当天最后时间
	 */
	public static Timestamp getEndTimestamp(String dateS){
		Date date = null;
		if(StringUtils.isEmpty(dateS)){
			date=new Date();
		}else{
		try {
			date = DateUtils.parseDate(dateS, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}}
		long nowTimeLong = date.getTime();
		nowTimeLong = nowTimeLong / (86400000) * (86400000) - TimeZone.getDefault().getRawOffset()+172799999;  
		return new Timestamp(nowTimeLong);
	}
	/**20140304
	 * 池长购
	 * 通过dateS时间(格式比如"2014-03-04")获取当天23:59:59
	 * @param date 时间段
	 * @return 当天最后时间
	 */
	public static Timestamp getEndTimestamp(Date date){
		if(date==null)
			date=new Date();
		long nowTimeLong = date.getTime();
		nowTimeLong = nowTimeLong / (86400000) * (86400000) - TimeZone.getDefault().getRawOffset()+(86399999);  
		return new Timestamp(nowTimeLong);
	}
}
