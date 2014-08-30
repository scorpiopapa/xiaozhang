package com.pro.actions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.UserInfoManager;
import com.pro.manager.ViptimeManager;
import com.pro.util.Page;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.TbViptime;

public class ViptimeAction extends BaseAction {
	private TbViptime viptime;
	private TbAdmin admin;
	private TbBranchschool branch;
	private TbSchool school;
	private TbUserinfo userInfo;
	private int[] delid;
	
	private ViptimeManager viptimeManager;
	private AdminManager adminManager;
	private BranchSchoolManager branchManager;
	private SchoolManager schoolManager;
	private UserInfoManager userInfoManager;
	public TbViptime getViptime() {
		return viptime;
	}
	public void setViptime(TbViptime viptime) {
		this.viptime = viptime;
	}
	public TbAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(TbAdmin admin) {
		this.admin = admin;
	}
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
	}
	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}
	public TbBranchschool getBranch() {
		return branch;
	}
	public void setBranch(TbBranchschool branch) {
		this.branch = branch;
	}
	public TbSchool getSchool() {
		return school;
	}
	public void setSchool(TbSchool school) {
		this.school = school;
	}
	public TbUserinfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(TbUserinfo userInfo) {
		this.userInfo = userInfo;
	}
	public void setViptimeManager(ViptimeManager viptimeManager) {
		this.viptimeManager = viptimeManager;
	}
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}
	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
	
	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}
	public String viptimeshow(){
		try{
			TbAdmin admins1 = this.loginAdmin();
			int adminroot = admins1.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbViptime.class);
			if(adminroot==1||adminroot==2){
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbAdmin.class);
				dc1.add(Restrictions.eq("adminId", admins1.getAdminId()));
				
			
				dc1.setFetchMode("tbSchool", FetchMode.JOIN);
				List<TbAdmin> tbadminlist = adminManager.findByCriteria(dc1);
				TbAdmin admin11 = tbadminlist.get(0);
				TbSchool school11 = admin11.getTbSchool();
				
				dc.createAlias("tbBranchschool", "branch");
				if(adminroot==1){	
					DetachedCriteria dc2 = DetachedCriteria.forClass(TbBranchschool.class);
					dc2.createAlias("tbSchool", "school");
					dc2.add(Restrictions.eq("school.schoolId", school11.getSchoolId()));
					List<TbBranchschool> tbbranchlist = branchManager.findByCriteria(dc2);
					
					List<Object> branchid = new ArrayList<Object>();
					for (TbBranchschool tbb : tbbranchlist) {
						branchid.add(tbb.getBranchSchoolId());
					}
					dc.add(Restrictions.in("branch.branchSchoolId", branchid));
					
				}else{

					dc.add(Restrictions.eq("branch.branchSchoolId", admin11.getTbBranchschool().getBranchSchoolId()));
				}	
			}
			if(adminroot==0)
				dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
			Page.getResult(request, viptimeManager, dc, "viplist", 15);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public String addOrEditViptime(){
		String path = "add";
		try{
			TbAdmin admins1 = this.loginAdmin();
			int adminroot = admins1.getAdminRoot();
			
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbAdmin.class);
			dc1.setFetchMode("tbSchool", FetchMode.JOIN);
			dc1.setFetchMode("tbBranchschool", FetchMode.JOIN);
			dc1.add(Restrictions.eq("adminId", admins1.getAdminId()));
			
			List<TbAdmin> adminlist1 = adminManager.findByCriteria(dc1);
			TbAdmin schooladmin = adminlist1.get(0);
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbSchool.class);
			List<TbSchool> schoolist = null;
			
			if(adminroot==0){
				schoolist = schoolManager.findByCriteria(dc);
				request.setAttribute("schoolist", schoolist);
			}else if(adminroot==1){
				dc.add(Restrictions.eq("schoolId", schooladmin.getTbSchool().getSchoolId()));
				schoolist = schoolManager.findByCriteria(dc);
				
				request.setAttribute("oneschool", schoolist.get(0));
				
				DetachedCriteria dcs = DetachedCriteria.forClass(TbBranchschool.class);
				dcs.createAlias("tbSchool", "school");
				dcs.add(Restrictions.eq("school.schoolId", schooladmin.getTbSchool().getSchoolId()));
				List<TbBranchschool> branchsq = branchManager.findByCriteria(dcs);
				
				request.setAttribute("branchlist", branchsq);
			}else {
				DetachedCriteria dcu = DetachedCriteria.forClass(TbUserinfo.class);
				dcu.createAlias("tbBranchschool", "branch");
				dcu.add(Restrictions.eq("branch.branchSchoolId", schooladmin.getTbBranchschool().getBranchSchoolId()));
				List<TbUserinfo> userlist = userInfoManager.findByCriteria(dcu);
				request.setAttribute("userlist",userlist);
				
				request.setAttribute("oneschool", schooladmin.getTbSchool());
				request.setAttribute("onebranch", schooladmin.getTbBranchschool());
			}	
			/*if(adminroot==1){
				DetachedCriteria dc2 = DetachedCriteria.forClass(TbBranchschool.class);
				dc2.createAlias("tbSchool", "school");
				dc2.add(Restrictions.eq("school.schoolId", schooladmin.getTbSchool().getSchoolId()));
				List<TbBranchschool> branchlist = branchManager.findByCriteria(dc2);
				request.setAttribute("branchschool", branchlist);
			}*/
			
			
			boolean j = true;
			
			request.setAttribute("schoolist", schoolist);
			
			if(viptime!=null){
				if(viptime.getId()!=null){
					
					DetachedCriteria dc4 = DetachedCriteria.forClass(TbViptime.class);
					dc4.setFetchMode("tbBranchschool", FetchMode.JOIN);
					dc4.setFetchMode("tbUserinfo", FetchMode.JOIN);
					dc4.add(Restrictions.eq("id", viptime.getId()));
					TbViptime viptime22 = (TbViptime)viptimeManager.findByCriteria(dc4).get(0);
					request.setAttribute("viptime", viptime22);
					
					if(adminroot==0){
						DetachedCriteria dc5 = DetachedCriteria.forClass(TbBranchschool.class);
						dc5.setFetchMode("tbSchool", FetchMode.JOIN);
						dc5.add(Restrictions.eq("branchSchoolId", viptime22.getTbBranchschool().getBranchSchoolId()));
						List<TbBranchschool> branclist11 = branchManager.findByCriteria(dc5);
						
						TbSchool school11 = branclist11.get(0).getTbSchool();
						request.setAttribute("school", school11);
					}else if(adminroot==1){
						
					}else{
						
					}
					
					path = "edit";
					
					j = false;
				}
			}
			if(j){
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return path;
	}
	
	public String addViptimeDo(){
		try{
			TbUserinfo userinfo1 = (TbUserinfo)userInfoManager.findById(userInfo.getUserInfoId());
			TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branch.getBranchSchoolId());
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			viptime.setTime(nowdate);
			viptime.setAlterTime(nowdate);
			viptime.setIsValid(1);
			viptime.setTbBranchschool(branch1);
			viptime.setTbUserinfo(userinfo1);
			
			viptimeManager.save(viptime);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String editViptimeDo(){
		try{
			TbViptime viptime1 = (TbViptime)viptimeManager.findById(viptime.getId());
			TbUserinfo userinfo1 = (TbUserinfo)userInfoManager.findById(userInfo.getUserInfoId());
			TbBranchschool branch1 = (TbBranchschool)branchManager.findById(branch.getBranchSchoolId());
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			viptime.setTime(viptime1.getTime());
			viptime.setAlterTime(nowdate);
			viptime.setTbBranchschool(branch1);
			viptime.setTbUserinfo(userinfo1);
			
			viptimeManager.update(viptime);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String delViptime(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbViptime vip1 = (TbViptime)viptimeManager.findById(delid[i]);
				viptimeManager.delete(vip1);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
