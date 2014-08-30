package com.qiYang.util;

import java.util.List;

public class PageModel<T> {
	private int pageSize;//页面大小
	private int currentPage;//当前页
	private int totalRecord;//总条数
	private List<T> list;//存储当前页面信息
	private String mark;//标志对什么进行分页
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	//总页数
	public int getTotalPages(){
		return (getTotalRecord()+pageSize-1)/pageSize;
	}
	
	//首页
	public int getPageIndex(){
		return 1;
	}
	//尾页
	public int getPageEnd(){
		return getTotalPages();
	}
	//上一页
	public int getPageUp() {
		if(currentPage>1)
			return currentPage-1;
		return 1;
	}
	//下一页
	public int getPageDown() {
		if(currentPage<getTotalPages())
			return currentPage+1;
		return currentPage;
	}
}
