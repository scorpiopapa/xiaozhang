package com.pro.actions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.ChargeManager;
import com.pro.manager.SchoolManager;
import com.pro.util.Page;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCharge;
import com.qiYang.model.TbSchool;

public class ChargeAction extends BaseAction {
	private TbCharge charge;
	private TbBranchschool branch;
	private TbAdmin admin;
	private int[] delid;
	
	private AdminManager adminManager;
	private ChargeManager chargeManager;
	private BranchSchoolManager branchManager;
	private SchoolManager schoolManager;
	
	public TbCharge getCharge() {
		return charge;
	}
	public void setCharge(TbCharge charge) {
		this.charge = charge;
	}
	public TbBranchschool getBranch() {
		return branch;
	}
	public void setBranch(TbBranchschool branch) {
		this.branch = branch;
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
	public void setChargeManager(ChargeManager chargeManager) {
		this.chargeManager = chargeManager;
	}
	public void setBranchManager(BranchSchoolManager branchManager) {
		this.branchManager = branchManager;
	}
	
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}
	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
	public String charges(){
		try{
			TbAdmin admins1 = this.loginAdmin();
			int adminroot = admins1.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbCharge.class);
				if(adminroot==1||adminroot==2){
					DetachedCriteria dc1 = DetachedCriteria.forClass(TbAdmin.class);
					dc1.setFetchMode("tbSchool", FetchMode.JOIN);
					dc1.add(Restrictions.eq("adminId", admins1.getAdminId()));
					List<TbAdmin> admin1list = adminManager.findByCriteria(dc1);
					TbAdmin tbadmin1 = admin1list.get(0);
					
					
					dc.createAlias("tbBranchschool", "branch");
					
					if(adminroot==1){
						DetachedCriteria dc2 = DetachedCriteria.forClass(TbBranchschool.class);
						dc2.createAlias("tbSchool", "school");
						dc2.add(Restrictions.eq("school.schoolId", tbadmin1.getTbSchool().getSchoolId()));
						List<TbBranchschool> tbbranchlist = branchManager.findByCriteria(dc2);
						
						List<Object> branchid = new ArrayList<Object>();
						for (TbBranchschool tbb : tbbranchlist) {
							branchid.add(tbb.getBranchSchoolId());
						}
						dc.add(Restrictions.in("branch.branchSchoolId", branchid));
						
					}else
						dc.add(Restrictions.eq("branch.branchSchoolId", tbadmin1.getTbBranchschool().getBranchSchoolId()));
			}
				
			if(adminroot==0)
				dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			
			Page.getResult(request, chargeManager, dc, "chargelist", 15);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String addOrEditCharges(){
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
			
			if(charge!=null){
				if(charge.getId()!=null){
					
					DetachedCriteria dc4 = DetachedCriteria.forClass(TbCharge.class);
					dc4.setFetchMode("tbBranchschool", FetchMode.JOIN);
					dc4.add(Restrictions.eq("id", charge.getId()));
					TbCharge charge22 = (TbCharge)chargeManager.findByCriteria(dc4).get(0);
					request.setAttribute("charge", charge22);
					
					if(adminroot==0){
						DetachedCriteria dc5 = DetachedCriteria.forClass(TbBranchschool.class);
						dc5.setFetchMode("tbSchool", FetchMode.JOIN);
						dc5.add(Restrictions.eq("branchSchoolId", charge22.getTbBranchschool().getBranchSchoolId()));
						List<TbBranchschool> branclist11 = branchManager.findByCriteria(dc5);
						
						TbSchool school11 = branclist11.get(0).getTbSchool();
						request.setAttribute("school", school11);
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
	
	public String addChargesDo(){
		try{
			TbBranchschool branch11 = (TbBranchschool)branchManager.findById(branch.getBranchSchoolId());
			charge.setTbBranchschool(branch11);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			charge.setIsValid(1);
			charge.setTime(nowdate);
			charge.setAlterTime(nowdate);
			chargeManager.save(charge);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String editChargesDo(){
		try{
			TbBranchschool branch11 = (TbBranchschool)branchManager.findById(branch.getBranchSchoolId());
			charge.setTbBranchschool(branch11);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			charge.setAlterTime(nowdate);
			charge.setTime(branch11.getTime());
			
			chargeManager.update(charge);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String delCharges(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbCharge tbcharge1 = (TbCharge)chargeManager.findById(delid[i]);
				chargeManager.delete(tbcharge1);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
