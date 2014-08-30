package com.qiYang.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.CityManager;
import com.pro.manager.CourseManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.TownManager;
import com.pro.manager.UserInfoManager;
import com.pro.util.Page;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbCourse;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbTown;
import com.qiYang.model.TbUserinfo;
import com.qiYang.util.BaseAction;
import com.qiYang.util.LalDistance;
import com.qiYang.util.StrutsUpload;

public class SchoolAction extends BaseAction {
	/*public DataBaseDao dbd = new DataBaseDaoImpl();
	public GogoServiceImpl ggs = new GogoServiceImpl();*/
	private int currentPage;
	private int pageSize;
	private File img;
	private String imgContentType;
	private String imgFileName;
	private TbBranchschool tbBschool;
	private int schoolId;
	private int townId;
	private int cityId;
	private SchoolManager schoolManager;
	private BranchSchoolManager branchManager;
	private TownManager townManager;
	private CityManager cityManager;
	private AdminManager adminManger;
	private CourseManager courseManager;
	private UserInfoManager userInfoManager;
	private String braschName;
	
	public String getBraschName() {
		return braschName;
	}

	public void setBraschName(String braschName) {
		this.braschName = braschName;
	}

	private int[] delid;
	
	public int[] getDelid() {
		return delid;
	}

	public void setDelid(int[] delid) {
		this.delid = delid;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public TbBranchschool getTbBschool() {
		return tbBschool;
	}

	public void setTbBschool(TbBranchschool tbBschool) {
		this.tbBschool = tbBschool;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getTownId() {
		return townId;
	}

	public void setTownId(int townId) {
		this.townId = townId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}

	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}

	public void setTownManager(TownManager townManager) {
		this.townManager = townManager;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}
	
	public void setAdminManger(AdminManager adminManger) {
		this.adminManger = adminManger;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}

	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}

	public String addBranchschoolDo(){
		
		try {
			//200KB
		  if(img!=null){
			if(img.length()>IMAGE_SIZE)
				return "fail";
			imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
			tbBschool.setBraschPictureUrl(imgFileName);
		  }else{
			  tbBschool.setBraschPictureUrl("root2.png");
		  }
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate());
			tbBschool.setTime(nowdate);
			tbBschool.setAlterTime(nowdate);
			
			TbTown town1 = (TbTown)townManager.findById(townId);
			
			TbCity city1 = (TbCity)cityManager.findById(cityId);
			
			String[] coordinate = LalDistance.getLal(tbBschool.getBraschAddress(), city1.getCityName());
			if(coordinate[0]!=null&&coordinate[1]!=null){
				tbBschool.setLongitude(Double.parseDouble(coordinate[0]));
				tbBschool.setLatitude(Double.parseDouble(coordinate[1]));
			}
			
			TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
			tbBschool.setTbSchool(school1);
			tbBschool.setTbTown(town1);
			
			branchManager.save(tbBschool);
			
			//out.print("<script language=\"javascript\" type=\"text/javascript\">alert('保存成功！')</script>");
			
		} catch (Exception e) {
			//out.write("<script language=\"JavaScript\" type=\"text/javascript\">alert('保存错误！')</script>");
			e.printStackTrace();
			return "fail";
		}
		
		
		return "success";
	}
	
	public String branchSchool(){
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(TbBranchschool.class);
			if(braschName!=null && !braschName.equals("")){
				braschName =new String(braschName.getBytes("ISO-8859-1"),"utf-8");
				braschName = braschName.replaceAll("[　\\s\\t\\n\\r\\f]", "");
				dc.add(Restrictions.like("braschName", braschName,MatchMode.ANYWHERE));
				request.setAttribute("braschName", braschName);
			}
			TbAdmin loginadmin = (TbAdmin)request.getSession().getAttribute("loginAdmin");
			int adminroot = loginadmin.getAdminRoot();
			
			if(adminroot==1||adminroot==2){
				DetachedCriteria dc2 = DetachedCriteria.forClass(TbAdmin.class);
				
				dc2.add(Restrictions.eq("adminId", loginadmin.getAdminId()));
				
				
				if(adminroot==1){
					dc2.setFetchMode("tbSchool", FetchMode.JOIN);
					List<TbAdmin> admintemp1 = adminManger.findByCriteria(dc2);
					TbAdmin aadd1 = admintemp1.get(0);
					
					dc.createAlias("tbSchool", "school");
					dc.add(Restrictions.eq("school.schoolId", aadd1.getTbSchool().getSchoolId()));
				}else{
					dc2.setFetchMode("tbBranchschool", FetchMode.JOIN);
					List<TbAdmin> admintemp = adminManger.findByCriteria(dc2);
					TbAdmin aadd = admintemp.get(0);
					dc.add(Restrictions.eq("branchSchoolId", aadd.getTbBranchschool().getBranchSchoolId()));
				}
			}

			dc.setFetchMode("tbTown", FetchMode.JOIN);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			Page.getResult(request, branchManager, dc, "branchlist", 15);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		}
		
		return "success";
	}
	// 搜索
	
	//删除分校
	public String delBranchSchool() throws Exception{
		for (int i = 0; i < delid.length; i++) {
			TbBranchschool branch1 = (TbBranchschool)branchManager.findById(delid[i]);
			
			Property p1 = Property.forName("tbBranchschool");
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbUserinfo.class);
			dc1.add(p1.eq(branch1));
			List<TbUserinfo> userinfo1list = userInfoManager.findByCriteria(dc1);
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbCourse.class);
			dc.add(Restrictions.eq("tbBranchschool", branch1));
			List<TbCourse> course1 = courseManager.findByCriteria(dc);
			if (course1.size()>0||userinfo1list.size()>0) {
				request.setAttribute("delTip", 1);
			}else
				branchManager.delete(branch1);
		}
		
		return "success";
	}
	//编辑分校
	public String editBranchSchool(){
		/*DataBaseDao dbd = new DataBaseDaoImpl();
		Map map = new HashMap();
		
		List<TbCity> list = dbd.getObjects(TbCity.class, map);
		List<TbCityWeb> citylist = new ArrayList<TbCityWeb>();
		for (TbCity t1 : list) {
			TbCityWeb tcw = new TbCityWeb();
			tcw.setCityId(t1.getCityId());
			tcw.setCityName(t1.getCityName());
			
			citylist.add(tcw);
		}
		
		List<TbSchool> list1 = dbd.getObjects(TbSchool.class, map);
		List<TbSchoolWeb> schoollist = new ArrayList<TbSchoolWeb>();
		for (TbSchool t2 : list1) {
			TbSchoolWeb tsw = new TbSchoolWeb();
			tsw.setSchoolId(t2.getSchoolId());
			tsw.setSchoolName(t2.getSchoolName());
			
			schoollist.add(tsw);
		}*/
		try {
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSchool.class);
			List<TbSchool> schoollist=schoolManager.findByCriteria(dc1);
			
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbCity.class);
			
			List<TbCity> citylist = cityManager.findByCriteria(dc2);
			
			request.setAttribute("schoollist", schoollist);
			request.setAttribute("citylist", citylist);
			
			DetachedCriteria dc3 = DetachedCriteria.forClass(TbBranchschool.class);
			dc3.setFetchMode("tbTown", FetchMode.JOIN);
			dc3.setFetchMode("tbSchool", FetchMode.JOIN);
			
			dc3.add(Restrictions.eq("branchSchoolId", tbBschool.getBranchSchoolId()));
			
			
			List<TbBranchschool> tblist = branchManager.findByCriteria(dc3);
			TbBranchschool  branchscool1 = tblist.get(0);
			
			DetachedCriteria dc4 = DetachedCriteria.forClass(TbTown.class);
			
			
			dc4.setFetchMode("tbCity", FetchMode.JOIN);
			dc4.add(Restrictions.eq("townId", branchscool1.getTbTown().getTownId()));
			List<TbTown> twlist = townManager.findByCriteria(dc4);
			TbTown ttown = twlist.get(0);
			
			request.setAttribute("cityId", ttown.getTbCity().getCityId());
			
			request.setAttribute("branchscool", branchscool1);
			
			DetachedCriteria dc5 = DetachedCriteria.forClass(TbTown.class);
			dc5.createAlias("tbCity", "city");
			dc5.add(Restrictions.eq("city.cityId", ttown.getTbCity().getCityId()));
			List<TbTown> twlist1 = cityManager.findByCriteria(dc5);
			
			
			request.setAttribute("townlist", twlist1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		}
		
		return "success";
	}
	
	//保存编辑的分校
	public String editBranchSchoolDo(){

		try {

			TbTown town1 = (TbTown)townManager.findById(townId);

			TbSchool school1 = (TbSchool)schoolManager.findById(schoolId);
			
			TbBranchschool branch1 = (TbBranchschool)branchManager.findById(tbBschool.getBranchSchoolId());
			
			
			branch1.setTbTown(town1);
			branch1.setTbSchool(school1);
			branch1.setBraschAddress(tbBschool.getBraschAddress());
			branch1.setBraschBusWay(tbBschool.getBraschBusWay());
			branch1.setBraschMinName(tbBschool.getBraschMinName());
			branch1.setBraschName(tbBschool.getBraschName());
			branch1.setBraschPhone(tbBschool.getBraschPhone());
			branch1.setBraschIntroduce(tbBschool.getBraschIntroduce());
			if(img!=null){
				//200KB
				if(img.length()>IMAGE_SIZE)
					return "fail";
			
				imgFileName = StrutsUpload.upLoadFile(img, imgContentType, imgFileName);
				branch1.setBraschPictureUrl(imgFileName);
			}
			branch1.setBraschStopLocation(tbBschool.getBraschStopLocation());
			
			TbCity city1 = (TbCity)cityManager.findById(cityId);
			
			String[] coordinate = LalDistance.getLal(tbBschool.getBraschAddress(), city1.getCityName());
			if(coordinate[0]!=null&&coordinate[1]!=null){
				branch1.setLongitude(Double.parseDouble(coordinate[0]));
				branch1.setLatitude(Double.parseDouble(coordinate[1]));
			}
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate());
			branch1.setAlterTime(nowdate);
			branch1.setIsValid(tbBschool.getIsValid());
			
			branchManager.update(branch1);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally{

		}
		
		
		return "success";
	}
}
