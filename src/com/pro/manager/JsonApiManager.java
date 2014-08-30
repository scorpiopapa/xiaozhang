package com.pro.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.pro.util.JsonResult;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbInformation;
import com.qiYang.model.TbLesson;
import com.qiYang.model.TbRegschool;
import com.qiYang.model.TbTown;

public class JsonApiManager {
	private InformationManager infoManager;
	private RegSchoolManager regSchoolManager;
	private CityManager cityManager;
	private TownManager townManager;
	private LessonManager lessonManager;
	private JdbcTemplate jdbcTemplate;
	private JsonResult jsonResult;
	private boolean succ = true;
	private int code = 1;
	private String message = "";
	
	
	public JsonResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	public void setInfoManager(InformationManager infoManager) {
		this.infoManager = infoManager;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setTownManager(TownManager townManager) {
		this.townManager = townManager;
	}

	public void setLessonManager(LessonManager lessonManager) {
		this.lessonManager = lessonManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setRegSchoolManager(RegSchoolManager regSchoolManager) {
		this.regSchoolManager = regSchoolManager;
	}
	protected String getSystemDate(int type){
		Date date = new Date();
		String fs = "yyyy-MM-dd HH:mm:ss";
		if(type==1)
			fs = "yyyy-MM-dd";
		SimpleDateFormat f = new SimpleDateFormat(fs);
		
		String nowDate = f.format(date);
		
		return nowDate;
	}

	/*查看招聘、合作学校
	 * 
	 * */
	public JsonResult infoList(int pageSize,int pageNo,int infotype){
		Map<String, Object> data = new HashMap<String, Object>();
		DetachedCriteria dc = DetachedCriteria.forClass(TbInformation.class);
		dc.add(Property.forName("infotype").eq(infotype));
		dc.addOrder(Order.asc("title"));
		try {
			if(pageNo<=0)
				pageNo = 1;
			List<TbInformation> list=infoManager.findByCriteria(pageNo, pageSize, dc);
			
			dc = DetachedCriteria.forClass(TbInformation.class);
			dc.add(Property.forName("infotype").eq(infotype));
			int count = infoManager.getRowCount(dc);
			int maxPage = (count+pageSize-1)/pageSize;
			
			
			data.put("list", list);
			
			if(count>0){
				message = "查询成功";
				succ = true;
				code = 1;
			}
				
			else
				message = "查询成功,没有数据";
			data.put("maxPage", maxPage);
			data.put("pageSize", pageSize);
			data.put("pageNo", pageNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			succ = false;
			code = -1;
			message = "查询失败,系统错误！";
			e.printStackTrace();
		}
		return new JsonResult(succ, code, message, data);
	}
	/*城市列表
	 * 
	 * */
	public JsonResult cityList(){
		Map<String, Object> data = new HashMap<String, Object>();
		DetachedCriteria dc = DetachedCriteria.forClass(TbCity.class);
		try {
			List<TbCity> list=cityManager.findByCriteria(dc);
			
			data.put("list", list);
			
			if(list.size()>0)
				message = "查询成功";
			else
				message = "查询成功,没有数据";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			succ = false;
			code = -1;
			message = "查询失败,系统错误！";
			e.printStackTrace();
		}
		return new JsonResult(succ, code, message, data);
	}
	/*注册学校
	 * 
	 * */
	public JsonResult regSchool(TbRegschool school,int townId){
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			TbTown town1 = (TbTown)townManager.findById(townId);
			school.setTbTown(town1);
			if(town1==null){
				succ = false;
				code = -2;
				message = "添加失败，townId为"+townId+"的地区不存在";
			}else if(school==null){
				succ = false;
				code = -2;
				message = "添加失败，数据不能为空";
			}else{
				school.setStatus(0);
				school.setIsValid(1);
				school.setAlterTime(Timestamp.valueOf(this.getSystemDate(0)));
				school.setTime(Timestamp.valueOf(this.getSystemDate(0)));
				regSchoolManager.save(school);
				succ = true;
				code = 1;
				message = "添加成功";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			succ = false;
			code = -1;
			message = "查询失败,系统错误！";
			e.printStackTrace();
		}
		
		return new JsonResult(succ, code, message, data);
	}
	/*获取绝对路径
	 * 
	 * */
	public JsonResult absolutePath(){
		Map<String, Object> data = new HashMap<String, Object>();
		try {	
				HttpServletRequest request = ServletActionContext.getRequest();
				String path = request.getContextPath();
				succ = false;
				code = 1;
				message = "查询成功";
				data.put("path", path);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			succ = false;
			code = -1;
			message = "查询失败,系统错误！";
			e.printStackTrace();
		}
		
		return new JsonResult(succ, code, message, data);
	}
	/*
	 * 按拼音对合作学校进行排序
	 * */
	@SuppressWarnings("unchecked")
	public JsonResult schoolUnion(int pageSize, int pageNo){
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			//List<String> list = infoManager.find("select title from TbInformation limit "+(pageNo-1)*pageSize+","+pageSize+" order by convert(title using gb2312) asc");
			String  sql = "select * from tb_information where infotype=9 order by convert(title using gb2312) asc limit ?,?";
			final List<TbInformation> list = new ArrayList<TbInformation>();
			if(pageNo<=0)
				pageNo = 1;
			jdbcTemplate.query(sql,new Object[]{(pageNo-1)*pageSize,pageSize}, new RowCallbackHandler() {
				
				public void processRow(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					TbInformation tbinfo = new TbInformation();
					tbinfo.setInfoid(rs.getInt("infoid"));
					tbinfo.setTitle(rs.getString("title"));
					tbinfo.setContent(rs.getString("content"));
					tbinfo.setImage(rs.getString("image"));
					tbinfo.setInfotype(rs.getInt("infotype"));
					tbinfo.setAddtime(rs.getTimestamp("addtime"));
					list.add(tbinfo);
				}
			});
			
			if(list.size()==0){
				message = "查询成功,没有数据";
				succ = true;
				code = 0;
			}else{
				message = "查询成功";
				succ = true;
				code = 1;

				data.put("list", list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			succ = false;
			code = -1;
			message = "查询失败,系统错误！";
			e.printStackTrace();
		}
		
		return new JsonResult(succ, code, message, data);
	}
	public JsonResult kemuKc(int subjectid){
		Map<String, Object> data = new HashMap<String, Object>();
		try {	
				DetachedCriteria dc = DetachedCriteria.forClass(TbLesson.class);
				dc.createAlias("tbSubject", "subs");
				dc.add(Restrictions.eq("subs.subjectId", subjectid));
				List<TbLesson> list = lessonManager.findByCriteria(dc);
				
				if(list.size()==0){
					message = "查询成功,没有数据";
					succ = true;
					code = 0;
				}	
				else{
					message = "查询成功";
					succ = true;
					code = 1;

					data.put("list", list);
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			succ = false;
			code = -1;
			message = "查询失败,系统错误！";
			e.printStackTrace();
		}
		
		return new JsonResult(succ, code, message, data);
	}
}
