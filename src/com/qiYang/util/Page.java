package com.qiYang.util;

import java.util.List;

public class Page {
	private Integer currentPage = 1;
	private Integer allPages=1;
	private Integer countPerPage = 6;
	private Integer allCount=0;
	private List currentList;
	
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage==null||currentPage<=0?1:currentPage;
	}

	public Integer getAllPages() {
		return allPages;
	}

	public void setAllPages(Integer allPages) {
		this.allPages = allPages;
	}

	public Integer getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(Integer countPerPage) {
		this.countPerPage = countPerPage;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public List getCurrentList() {
		return currentList;
	}

	public void setCurrentList(List currentList) {
		this.currentList = currentList;
	}
	
}
