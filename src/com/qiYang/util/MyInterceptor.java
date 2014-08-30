package com.qiYang.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qiYang.model.TbAdmin;

@SuppressWarnings("serial")
public class MyInterceptor extends MethodFilterInterceptor {

	//客户未登录拦截器
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getActionName();
		
		String methodName = invocation.getProxy().getMethod();
		
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		
		TbAdmin adminInfo = (TbAdmin)session.get("loginAdmin");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String path = null;
		//已登录则不拦截
		if(adminInfo!=null){
			path = invocation.invoke();
		//未登录则跳转到登录界面
		}else {
			request.setAttribute("loginout", 1);
			path = "nologin";
		}
		
		return path;
	}

}
