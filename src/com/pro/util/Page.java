package com.pro.util;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;

import com.pro.manager.BaseManager;





public class Page {
	
	public static final int PAGESIZE = 5;
	
	/**
	 * 
	 * @param request 	请求对象
	 * @param baseManager	业务接口	
	 * @param dc	条件
	 * @param resultListName  调用dao后得到的结果集名字
	 * @param pageName	分页请求地址
	 * @param pagesize	页行数
	 * @throws Exception 
	 */
	//该函数的参数依次为request，当前调用的业务层的变量名，查询对象dc,存放结果集的名称，页面大小，页码显示数量
	public static void getResult(HttpServletRequest request,BaseManager baseManager,DetachedCriteria dc,String resultListName,int pageSize) throws Exception{
		String currentPage = request.getParameter("currentPage");//获得当前页
		if (currentPage == null||"".equals(currentPage)) {
			currentPage = "1";
			}
		//1,取得查询结果并放入request中
		request.setAttribute(resultListName, baseManager.findByCriteria(Integer.parseInt(currentPage), pageSize, dc));
		//2,求出满足条件的总行数
		
		int totalRecord = baseManager.getRowCount(dc);//求出满足条件的总行数
		//总页数
		int totalPage = (totalRecord+pageSize-1)/pageSize;
		int cp = Integer.parseInt(currentPage);
		
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", Integer.parseInt(currentPage));
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("totalPage", totalPage);
	}
}
