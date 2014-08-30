package com.pro.actions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.pro.manager.BranchSchoolManager;
import com.pro.manager.CityManager;
import com.pro.manager.SchoolManager;
import com.pro.manager.SubBranchManager;
import com.pro.manager.SubjectManager;
import com.pro.manager.TownManager;
import com.pro.util.Page;
import com.qiYang.model.TbBranchschool;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbSchool;
import com.qiYang.model.TbSubject;
import com.qiYang.model.TbSubjectinbranchschool;
import com.qiYang.model.TbTown;

public class SubBranchAction extends BaseAction {
	
	private int cityId;
	private int townId;
	private int[] delid;
	private TbCity citys;
	private TbTown towns;
	private TbSchool school;
	private TbBranchschool tbBschool;
	private TbSubject subjects;
	private TbSubjectinbranchschool subjectbranch;
	private SchoolManager schoolManager;
	private BranchSchoolManager branchManager;
	private TownManager townManager;
	private CityManager cityManager;
	private SubBranchManager subBranchManager;
	private SubjectManager subjectManager;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public TbCity getCitys() {
		return citys;
	}
	public void setCitys(TbCity citys) {
		this.citys = citys;
	}
	
	public TbTown getTowns() {
		return towns;
	}
	public void setTowns(TbTown towns) {
		this.towns = towns;
	}
	public int getTownId() {
		return townId;
	}
	public void setTownId(int townId) {
		this.townId = townId;
	}
	
	public int[] getDelid() {
		return delid;
	}
	public void setDelid(int[] delid) {
		this.delid = delid;
	}
	public TbBranchschool getTbBschool() {
		return tbBschool;
	}
	public void setTbBschool(TbBranchschool tbBschool) {
		this.tbBschool = tbBschool;
	}
	public TbSchool getSchool() {
		return school;
	}
	
	public TbSubjectinbranchschool getSubjectbranch() {
		return subjectbranch;
	}
	public void setSubjectbranch(TbSubjectinbranchschool subjectbranch) {
		this.subjectbranch = subjectbranch;
	}
	public void setSchool(TbSchool school) {
		this.school = school;
	}
	public TbSubject getSubjects() {
		return subjects;
	}
	public void setSubjects(TbSubject subjects) {
		this.subjects = subjects;
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
	
	public void setSubBranchManager(SubBranchManager subBranchManager) {
		this.subBranchManager = subBranchManager;
	}
	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}
	public String subBranch(){
		
		try{
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSubjectinbranchschool.class);
			dc1.setFetchMode("tbSubject", FetchMode.JOIN);
			dc1.createAlias("tbBranchschool", "sub");
			dc1.add(Restrictions.eq("sub.branchSchoolId", tbBschool.getBranchSchoolId()));
			request.setAttribute("branchid", tbBschool.getBranchSchoolId());
			Page.getResult(request, subBranchManager, dc1, "sublist", 10);
			
		}catch(Exception e){
			
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String addOrEditSubBranch(){
		
		String url = "";
		try{
			//指定分校
			TbBranchschool branchSchool = (TbBranchschool)branchManager.findById(tbBschool.getBranchSchoolId());
			
			//科目
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbSubject.class);
			List<TbSubject> subjectList = subjectManager.findByCriteria(dc1);

			//分校的科目
			DetachedCriteria dc2 = DetachedCriteria.forClass(TbSubjectinbranchschool.class);
			dc2.setFetchMode("tbSubject", FetchMode.JOIN);
			dc2.createAlias("tbBranchschool", "branch");
			dc2.add(Restrictions.eq("branch.branchSchoolId", tbBschool.getBranchSchoolId()));
			List<TbSubjectinbranchschool> subbranclist = subBranchManager.findByCriteria(dc2);
			
			List<TbSubject> newsub = new ArrayList<TbSubject>();
			
			//去掉分校已有的科目
			for (TbSubject sub1 : subjectList) {
				boolean mark = true;
				for (TbSubjectinbranchschool sub2 : subbranclist) {
					if(sub1.getSubjectId().equals(sub2.getTbSubject().getSubjectId())){
						mark = false;
						break;
					}
				}
				if(mark)
					newsub.add(sub1);
			}
			request.setAttribute("branchschool", branchSchool);
			
			if(subjectbranch!=null){
				
				DetachedCriteria dc3 = DetachedCriteria.forClass(TbSubjectinbranchschool.class);
				dc3.setFetchMode("tbSubject", FetchMode.JOIN);
				dc3.add(Restrictions.eq("id", subjectbranch.getId()));
				List<TbSubjectinbranchschool> subject2List = subBranchManager.findByCriteria(dc3);
				TbSubjectinbranchschool subbranch1 = subject2List.get(0);
				//查找当前修改的分校科目
				TbSubject subject4 = subbranch1.getTbSubject();
				
				newsub.add(subject4);
				
				request.setAttribute("subbranch", subbranch1);
				url = "edit";
			}else
				url = "add";
			request.setAttribute("newsublist", newsub);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return url;
	}
	
	public String addSubBranchDo(){
		try{
			TbBranchschool branchschool = (TbBranchschool)branchManager.findById(tbBschool.getBranchSchoolId());
			TbSubject subject1 = (TbSubject)subjectManager.findById(subjects.getSubjectId());
			TbSubjectinbranchschool newsubbranch = new TbSubjectinbranchschool();
			
			newsubbranch.setTbBranchschool(branchschool);
			newsubbranch.setTbSubject(subject1);
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			newsubbranch.setTime(nowdate);
			newsubbranch.setAlterTime(nowdate);
			newsubbranch.setIsValid(1);
			
			subBranchManager.save(newsubbranch);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}

	public String editSubBranchDo(){
		
		try{
			TbBranchschool branchschool = (TbBranchschool)branchManager.findById(tbBschool.getBranchSchoolId());
			TbSubject subject1 = (TbSubject)subjectManager.findById(subjects.getSubjectId());
			TbSubjectinbranchschool newsubbranch = (TbSubjectinbranchschool)subBranchManager.findById(subjectbranch.getId());
			
			newsubbranch.setTbBranchschool(branchschool);
			newsubbranch.setTbSubject(subject1);
			
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			newsubbranch.setAlterTime(nowdate);
			newsubbranch.setIsValid(subjectbranch.getIsValid());
			
			subBranchManager.update(newsubbranch);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String delSubBranch(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbSubjectinbranchschool tsubbranch = (TbSubjectinbranchschool)subBranchManager.findById(delid[i]);
				subBranchManager.delete(tsubbranch);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return SUCCESS;
	}
	
	public String cityView(){
		try{
			DetachedCriteria dc1 = DetachedCriteria.forClass(TbCity.class);
			
			Page.getResult(request, cityManager, dc1, "citylist", 10);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	public String editCityDo(){
		try{
			TbCity city1 = (TbCity)cityManager.findById(citys.getCityId());
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			citys.setTime(city1.getTime());
			citys.setAlterTime(nowdate);
			cityManager.update(citys);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String addCityDo(){
		try{
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			citys.setTime(nowdate);
			citys.setAlterTime(nowdate);
			citys.setIsValid(1);
			cityManager.save(citys);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		
		return SUCCESS;
	}
	
	public String addOrEditCity(){
		try{
			TbCity city1 = (TbCity)cityManager.findById(citys.getCityId());
			
			request.setAttribute("citys", city1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String delCity(){
		try{
			for (int i = 0; i < delid.length; i++) {
				DetachedCriteria dc1 = DetachedCriteria.forClass(TbTown.class);
				dc1.createAlias("tbCity", "city");
				dc1.add(Restrictions.eq("city.cityId", delid[i]));
				List<TbTown> townlist1 = townManager.findByCriteria(dc1);
				for (TbTown tw1 : townlist1) {
					townManager.delete(tw1);
				}
				
				TbCity city11 = (TbCity)cityManager.findById(delid[i]);
				cityManager.delete(city11);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String viewTown(){
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(TbTown.class);
			dc.createAlias("tbCity", "city");
			dc.add(Restrictions.eq("city.cityId", citys.getCityId()));
			request.setAttribute("cityid", citys.getCityId());
			Page.getResult(request, townManager, dc, "townlist", 10);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String delTown(){
		try{
			for (int i = 0; i < delid.length; i++) {
				TbTown towns1 = (TbTown)townManager.findById(delid[i]);
				townManager.delete(towns1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		
		return SUCCESS;
	}
	
	public String addTownDo(){
		try{
			TbCity citys1 = (TbCity)cityManager.findById(citys.getCityId());
			towns.setTbCity(citys1);
			towns.setIsValid(1);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			towns.setTime(nowdate);
			towns.setAlterTime(nowdate);
			townManager.save(towns);
			
			request.setAttribute("citys", citys1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return SUCCESS;
	}
	
	public String addOrEditTown(){
		String path = "add";
		try{
			
			TbCity citys1 = (TbCity)cityManager.findById(citys.getCityId());
			
			request.setAttribute("citys", citys1);
			if(towns!=null){
				if(towns.getTownId()!=0){
					TbTown towns1 = (TbTown)townManager.findById(towns.getTownId());
					request.setAttribute("towns", towns1);

					path = "edit";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return path;
	}
	public String editTownDo(){
		try{
			TbTown town1 = (TbTown)townManager.findById(towns.getTownId());
			TbCity city1 = (TbCity)cityManager.findById(citys.getCityId());
			
			towns.setTbCity(city1);
			Timestamp nowdate = Timestamp.valueOf(this.getSystemDate(0));
			
			towns.setTime(town1.getTime());
			towns.setAlterTime(nowdate);
			
			townManager.update(towns);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
}
