package com.pro.actions;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.AdminManager;
import com.pro.manager.AttendManager;
import com.pro.manager.BranchSchoolManager;
import com.pro.manager.CourseManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.UserInfoManager;
import com.pro.util.Page;
import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbAttendance;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbSchool;

public class AttendAction extends BaseAction {
	
	private int[] delid;
	private TbAttendance attends;
	private TbSchool school;
	private TbBranchschool branch;
	
	private AdminManager adminManager;
	private SchoolManager schoolManager;
	private BranchSchoolManager branchManager;
	private AttendManager attendManager;
	private CourseManager courseManager;
	private UserInfoManager userInfoManager;
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
	}
	public TbAttendance getAttends() {
		return attends;
	}
	public void setAttends(TbAttendance attends) {
		this.attends = attends;
	}
	public TbSchool getSchool() {
		return school;
	}
	public void setSchool(TbSchool school) {
		this.school = school;
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
	public void setAttendManager(AttendManager attendManager) {
		this.attendManager = attendManager;
	}
	
	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}
	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}
	public String attendance(){
		try{
			TbAdmin admins1 = this.loginAdmin();
			int adminroot = admins1.getAdminRoot();
			
			DetachedCriteria dc = DetachedCriteria.forClass(TbAttendance.class);
			dc.setFetchMode("tbCourse", FetchMode.JOIN);
			dc.setFetchMode("tbSchool", FetchMode.JOIN);
			dc.setFetchMode("tbUserinfo", FetchMode.JOIN);
			dc.setFetchMode("tbBranchschool", FetchMode.JOIN);
			if(adminroot==1||adminroot==2){
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbAdmin.class);
				dc1.setFetchMode("tbSchool", FetchMode.JOIN);
				dc1.setFetchMode("tbBranchschool", FetchMode.JOIN);
				dc1.add(Restrictions.eq("adminId", admins1.getAdminId()));
				
				List<TbAdmin> adminlist1 = adminManager.findByCriteria(dc1);
				TbAdmin admin123 = adminlist1.get(0);
				
				if(adminroot==1){
					
					dc.createAlias("tbSchool", "school");
					dc.add(Restrictions.eq("school.schoolId", admin123.getTbSchool().getSchoolId()));
				}	
				else if(adminroot==2){
					dc.createAlias("tbBranchschool", "branch");
					dc.add(Restrictions.eq("branch.branchSchoolId", admin123.getTbBranchschool().getBranchSchoolId()));
				}
			}
			Page.getResult(request, attendManager, dc, "attendlist", 10);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//添加考勤
	public String addOrEditAttendance(){
		try{
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String delAttendance(){
		try{
			for (int i = 0; i < delid.length; i++) {
				Property p = Property.forName("attendanceId");
				DetachedCriteria dc = DetachedCriteria.forClass(TbAttendance.class);
				dc.add(p.eq(delid[i]));
				//TbAttendance attends1 = (TbAttendance)attendManager.findById(delid[i]);
				List<TbAttendance> attendslist1 = attendManager.findByCriteria(dc);
				
				attendManager.delete(attendslist1.get(0));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
