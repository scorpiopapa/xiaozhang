package com.qiYang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.qiYang.model.TbTest;
import com.qiYang.model.TbUserinfo;
import com.qiYang.model.web.TbPracticetitleWeb;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.Page;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

public class onLineTestAction extends ActionSupport{
	private TbPracticetitleWeb tbPracticetitleWeb;
	private String historyanswer;
	private Integer currentPage;
	private Integer testId;
	private Integer userinfoId;
	private Integer allCount;
	private Integer hisCount;
	
	
	
	public String getHistoryanswer() {
		return historyanswer;
	}
	public void setHistoryanswer(String historyanswer) {
		this.historyanswer = historyanswer;
	}
	public Integer getUserinfoId() {
		return userinfoId;
	}
	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public TbPracticetitleWeb getTbPracticetitleWeb() {
		return tbPracticetitleWeb;
	}
	public void setTbPracticetitleWeb(TbPracticetitleWeb tbPracticetitleWeb) {
		this.tbPracticetitleWeb = tbPracticetitleWeb;
	}
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	
	public Integer getHisCount() {
		return hisCount;
	}
	public void setHisCount(Integer hisCount) {
		this.hisCount = hisCount;
	}
	@Override
	public String execute(){
		 GogoServiceImpl gogoServiceImpl = new GogoServiceImpl();
		 TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(userinfoId);
		 TbTest test=new TbTest();
		 test.setTestId(testId);
		 allCount=gogoServiceImpl.getTestAllCount(test).intValue();
		 hisCount=gogoServiceImpl.getHistoryAllCount(test, userinfo).intValue();
		 if(allCount>=currentPage){
		Page page = new Page();
		page.setCurrentPage(currentPage);
		Page page1 =gogoServiceImpl.onLinePage(page, testId);
		tbPracticetitleWeb = new TWObject().getT(TbPracticetitleWeb.class, page1.getCurrentList().get(0));
		historyanswer=gogoServiceImpl.getHistoryAnswer(tbPracticetitleWeb,userinfoId);}
		return "success";
		}
}
