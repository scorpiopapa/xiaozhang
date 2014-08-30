package com.qiYang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

public class LalDistance {
	private final static double DEF_PI = 3.14159265359; // PI
	private final static double DEF_2PI = 6.28318530712; // 2*PI
	private final static double DEF_PI180 = 0.01745329252; // PI/180.0
	private final static double DEF_R = 6370693.5; // radius of earth

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		Double distance = GetDistance(30.288718, 120.125592, 30.289093,
				120.13594);
		// System.out.println(getLal("西湖区文三路255号","杭州"));
		// double distance =
		// GetDistance(121.491909,31.233234,121.411994,31.206134);
		// double distance =
		// GetDistance(121.491909,31.233234,121.491909,31.224134);
		System.out.println("Distance is:" + distance.toString());
		Double distance1 = getLongDistance(30.288718, 120.125592, 30.289093,
				120.13594);
		System.out.println("LongDistance is:" + distance1.toString());
		Double distance2 = getShortDistance(30.288718, 120.125592, 30.289093,
				120.13594);
		System.out.println("ShortDistance is:" + distance2.toString());
	}

	public static String[] getLal(String address, String city) {
		String[] strs = new String[2];
		// System.out.print(address);
		String addre = null;
		String citys = null;
		try {
			addre = URLEncoder.encode(address, "utf-8");
			citys = URLEncoder.encode(city, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String URL = "http://api.map.baidu.com/geocoder/v2/?address=" + addre
				+ "&output=json&ak=1cd31b9aad674f0c5e22122cb3a08ae6&city="
				+ citys;
		URL url = null;
		try {
			url = new URL(URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection connection = null;
		InputStream urlStream = null;
		String sCurrentLine = "";
		String sTotalString = "";
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			urlStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlStream));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString += sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(sTotalString);
		String result = null;
		new JSONObject();
		JSONObject json = JSONObject.fromObject(sTotalString);
		if (json.getInt("status") == 0) {
			if (json.getString("result").length() > 5) {
				new JSONObject();
				// System.out.println(json.getJSONObject("result"));
				JSONObject json1 = JSONObject.fromObject(json
						.getJSONObject("result"));
				JSONObject obj = json1.getJSONObject("location");
				// System.out.println("obj.toString()--->"+obj.toString());
				strs[0] = obj.getString("lng");
				strs[1] = obj.getString("lat");
			}
		}
		// System.out.println("lng:"+strs[0]+"---->lat:"+strs[1]);
		return strs;
	}

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 
	 * 又经纬度1度=60分=3600秒 纬度1度 = 大约111km 纬度1分 = 大约1.85km 纬度1秒 = 大约30.9m
	 * 经度一度=85.39km 经度一分=1.42km 经度一秒=23.6m
	 * 对于A点周围的1000M以内任一点，你可以把其按经纬度方向分解，按照经纬度东西南北变化规律就可以推算出来了
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 *            经度
	 * @param lat1
	 *            纬度
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double GetDistance(double lng1, double lat1, double lng2,
			double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	public static Double getShortDistance(Double lon1, Double lat1, Double lon2,
			Double lat2) {
		double ew1, ns1, ew2, ns2;
		double dx, dy, dew;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 经度差
		dew = ew1 - ew2;
		// 若跨东经和西经180 度，进行调整
		if (dew > DEF_PI)
			dew = DEF_2PI - dew;
		else if (dew < -DEF_PI)
			dew = DEF_2PI + dew;
		dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		// 勾股定理求斜边长
		distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	public static Double getLongDistance(Double lon1, Double lat1, Double lon2,
			Double lat2) {
		double ew1, ns1, ew2, ns2;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 求大圆劣弧与球心所夹的角(弧度)
		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1)
				* Math.cos(ns2) * Math.cos(ew1 - ew2);
		// 调整到[-1..1]范围内，避免溢出
		if (distance > 1.0)
			distance = 1.0;
		else if (distance < -1.0)
			distance = -1.0;
		// 求大圆劣弧长度
		distance = DEF_R * Math.acos(distance);
		return distance;
	}

	// 通过经纬度获取地址，longitude经度，latitude纬度
	public static String getAddress(String longitude, String latitude) {
		String lonLat = latitude + "," + longitude;
		String URL = "http://api.map.baidu.com/geocoder?location=" + lonLat;
		String str = "";
		try {
			Connection con = Jsoup.connect(URL);
			Document doc = con.post();
			Elements tb = doc.getElementsByTag("formatted_address");
			str = Jsoup.parse(tb.toString()).text();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	// 腾网第三定位软件，通过url获取其经纬度
	public static String[] getLonAndLat(String url) {
		if (StringUtils.isEmpty(url))
			return null;
		String result = null;
		try {
			Document doc = Jsoup.connect(url).get();
			String html = doc.html();// jsoup得到的html代码
			Pattern p = Pattern.compile("\\<!--(.+)--\\>");
			Matcher m = p.matcher(html);
			while (m.find()) {
				result = m.group();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(result))
			return null;
		StringBuffer sb = new StringBuffer(result);
		int one = sb.indexOf("<div>");
		if (one > 0)
			sb.delete(0, one + 5);
		int two = sb.lastIndexOf("</div>");
		if (two > 0)
			sb.delete(two, sb.length());

		String str = sb.toString();
		String[] strs = StringUtils.splitByWholeSeparator(str, ",");
		return strs;
	}
}
