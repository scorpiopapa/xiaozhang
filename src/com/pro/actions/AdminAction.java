package com.pro.actions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.SchoolManager;
import com.pro.util.Page;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbSchool;

public class AdminAction extends BaseAction {
	private int[] delid;
	private TbAdmin admin;
	private TbSchool shcool;
	private TbBranchschool branch;
	private AdminManager adminManager;
	private SchoolManager schoolManager;
	private BranchSchoolManager branchManager;
	private String schoolName;
	private String braschName;
	private Integer schoolId;
	
	
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getBraschName() {
		return braschName;
	}
	public void setBraschName(String braschName) {
		this.braschName = braschName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
	}
	public TbAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(TbAdmin admin) {
		this.admin = admin;
	}
	public TbSchool getShcool() {
		return shcool;
	}
	public void setShcool(TbSchool shcool) {
		this.shcool = shcool;
	}
	public TbBranchschool getBranch() {
		return branch;
	}
	public void setBranch(TbBranchschool branch) {
		this.branch = branch;
	}
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}
	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}
	
	public String modifyps(){
		try{
			HttpSession session = request.getSession();
			TbAdmin admins1 = this.loginAdmin();;
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc.add(Restrictions.eq("adminId", admins1.getAdminId()));
			List<TbAdmin> adminlist = adminManager.findByCriteria(dc);
			TbAdmin admin1 = adminlist.get(0);
			request.setAttribute("modifyadmin", admin1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String modifypsDo(){
		try{
			TbAdmin admin1 = (TbAdmin)adminManager.findById(admin.getAdminId());
			admin1.setAdminPassword(admin.getAdminPassword());
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			admin1.setAlterTime(nowdate);
			adminManager.update(admin1);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String checkLogin(){
			String path = "";
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			if(admin==null){
				request.setAttribute("loginFail", 1);
				return "fail";
			}	
			dc.add(Restrictions.eq("adminName", admin.getAdminName()));
			dc.add(Restrictions.eq("adminPassword", admin.getAdminPassword()));
			
			List<TbAdmin> admincheck = adminManager.findByCriteria(dc);
			if(admincheck.size()>0){
				HttpSession session = request.getSession();
				TbAdmin aadd = admincheck.get(0);
				
				session.setAttribute("loginAdmin", aadd);
				
				path = SUCCESS;
			}else{
				request.setAttribute("loginFail", 1);
				path = "fail";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return path;
	}
	
	public String logout(){
		try{
			request.getSession().removeAttribute("loginAdmin");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	//总校admin
	public String schoolAdmin(){
		try{
			
			TbAdmin admins1 = this.loginAdmin();
			int adminroot = admins1.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			if(schoolName!=null && !schoolName.equals("")){
				schoolName =new String(schoolName.getBytes("ISO-8859-1"),"utf-8");
				dc.createAlias("tbSchool", "tbschool");
				dc.add(Restrictions.like("tbschool.schoolName", schoolName,MatchMode.ANYWHERE ));
				request.setAttribute("schoolName", schoolName);
			}
			dc.add(Restrictions.eq("adminRoot", 1));
			if(adminroot==1){
				dc.add(Restrictions.eq("adminId", admins1.getAdminId()));
			}else{
				dc.add(Restrictions.ne("adminRoot", 3));
			}
			Page.getResult(request, adminManager, dc, "schooladmin", 10);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	//编辑或修改总校管理员
	public String addOrEditSchoolAdmin(){
		String path = "add";
		try{
			boolean ff = true;
			
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbAdmin.class);
			dc1.setFetchMode("tbSchool", FetchMode.JOIN);

			//总校管理员级别adminRoot为1
			dc1.add(Restrictions.eq("adminRoot", 1));
			if(admin!=null){
				if (admin.getAdminId()!=null) {
					dc1.add(Restrictions.eq("adminId", admin.getAdminId()));
					List<TbAdmin> adminlist = adminManager.findByCriteria(dc1);
					
					TbAdmin admins1 = adminlist.get(0);

					request.setAttribute("admins", admins1);
					ff = false;
					path = "edit";
				}
			}
			if(ff){
				List<TbAdmin> adminlist = adminManager.findByCriteria(dc1);
				List<TbSchool> schoolTemp = new ArrayList<TbSchool>();
				DetachedCriteria dc2 = DetachedCriteria.forClass(TbSchool.class);
				List<TbSchool> schoolist1 = schoolManager.findByCriteria(dc2);
				//获取没有添加管理员的总校
				for (TbSchool tbs1 : schoolist1) {
					boolean f = true;
					for (TbAdmin ta1 : adminlist) {
						if(ta1.getTbSchool().getSchoolId().equals(tbs1.getSchoolId())){
							f = false;
							break;
						}
					}
					if(f)
						schoolTemp.add(tbs1);
				}
				
				request.setAttribute("schoollist", schoolTemp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return path;
	}
	public String addSchoolAdminDo(){
		String path = SUCCESS;
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminName", admin.getAdminName()));
			List<TbAdmin> admintemp = adminManager.findByCriteria(dc);
			if(admintemp.size()>0){
				request.setAttribute("tip", 1);
				return "fail";
			}
			
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSchool.class);
			TbSchool school1 = (TbSchool)schoolManager.findById(shcool.getSchoolId());
			
			admin.setTbSchool(school1);
			admin.setAdminRoot(1);
			admin.setIsValid(1);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			admin.setTime(nowdate);
			admin.setAlterTime(nowdate);
			
			adminManager.save(admin);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return path;
	}
	public String editSchoolAdminDo(){
		try{
			TbAdmin admin1 = (TbAdmin)adminManager.findById(admin.getAdminId());

			admin1.setAdminPassword(admin.getAdminPassword());
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			admin1.setIsValid(admin.getIsValid());
			admin1.setAlterTime(nowdate);
			
			adminManager.update(admin1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String delSchoolAdmin(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbAdmin tbadmin1 = (TbAdmin)adminManager.findById(delid[i]);
				adminManager.delete(tbadmin1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String branchAdmin(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			
			dc.add(Restrictions.eq("adminRoot", 2));
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc.createAlias("tbSchool", "sc");
		    request.setAttribute("schoolid", shcool.getSchoolId());
		    Integer schoolid =   (Integer) request.getAttribute("schoolid");
			dc.add(Restrictions.eq("sc.schoolId", schoolid));
			Page.getResult(request, adminManager, dc, "branchadminlist", 10);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	public String branchAdminto(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			if(braschName!=null && !braschName.equals("")){
				braschName =new String(braschName.getBytes("ISO-8859-1"),"utf-8");
				dc.createAlias("tbBranchschool", "branchschool");
				dc.add(Restrictions.like("branchschool.braschName", braschName,MatchMode.ANYWHERE ));
				request.setAttribute("braschName", braschName);
			}
			dc.add(Restrictions.eq("adminRoot", 2));
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			Page.getResult(request, adminManager, dc, "branchadminlist", 10);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "branchAdminto";
	}
	public String addOrEditBranchAdmin(){
		String path = "add";
		try{
			boolean ff = true;
			TbSchool school1 = (TbSchool)schoolManager.findById(shcool.getSchoolId());
			
			
			if(admin!=null){
				if (admin.getAdminId()!=null) {
					TbAdmin admin1 = (TbAdmin)adminManager.findById(admin.getAdminId());
					request.setAttribute("admins", admin1);
					ff = false;
					path = "edit";
				}	
			}
			if(ff){
				
				
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbBranchschool.class);
				dc1.createAlias("tbSchool", "school");
				dc1.add(Restrictions.eq("school.schoolId", shcool.getSchoolId()));
				
				List<TbBranchschool> banchlist = branchManager.findByCriteria(dc1);
				List<TbBranchschool> banchlisttemp = new ArrayList<TbBranchschool>();
				
				DetachedCriteria dc2 = DetachedCriteria.forClass(TbAdmin.class);
				dc2.add(Restrictions.eq("adminRoot", 2));
				dc2.setFetchMode("tbBranchschool", FetchMode.JOIN);
				List<TbAdmin> tbadmin1 = adminManager.findByCriteria(dc2);
				
				for (TbBranchschool blt : banchlist) {
					boolean f = true;
					for (TbAdmin tba : tbadmin1) {
						if(blt.getBranchSchoolId().equals(tba.getTbBranchschool().getBranchSchoolId())){
							f = false;
							break;
						}
					}
					if(f)
						banchlisttemp.add(blt);
				}
				
				
				request.setAttribute("banchlist", banchlisttemp);
				
			}
			
			request.setAttribute("school", school1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	public String addBranchAdminDo(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbAdmin.class);
			dc.add(Restrictions.eq("adminName", admin.getAdminName()));
			List<TbAdmin> admintemp = adminManager.findByCriteria(dc);
			if(admintemp.size()>0){
				request.setAttribute("tip", 1);
				return "fail";
			}
			
			TbSchool school1 = (TbSchool)schoolManager.findById(shcool.getSchoolId());
			TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branch.getBranchSchoolId());

			admin.setTbBranchschool(branch1);
			admin.setTbSchool(school1);
			admin.setIsValid(1);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			admin.setTime(nowdate);
			admin.setAlterTime(nowdate);
			admin.setAdminRoot(2);

			adminManager.save(admin);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String editBranchAdminDo(){
		try{
			TbAdmin admin1 = (TbAdmin)adminManager.findById(admin.getAdminId());
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			admin1.setAlterTime(nowdate);
			
			admin1.setIsValid(admin.getIsValid());
			admin1.setAdminName(admin.getAdminName());
			admin1.setAdminPassword(admin.getAdminPassword());
			
			adminManager.update(admin1);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String delBranchAdmin(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbAdmin tbadmin1 = (TbAdmin)adminManager.findById(delid[i]);
				adminManager.delete(tbadmin1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
