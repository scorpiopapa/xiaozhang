<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css">
	<LINK rel="stylesheet"
			href="${pageContext.request.contextPath }/css/page.css"
			type="text/css">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <center>
    	<table class="tablestyle1" width="80%">
				<tr class="CTitle">
					<td colspan="2" align="center">
						<h4>用户帐号</h4>
					</td>
				</tr >
		    	<tr align="center" bgcolor="#EEEEEE">
		    		<td><font size="3">用户信息</font></td>
		    		<td><font size="3">用户帐号</font></td>
		    	</tr>
	    	<s:iterator value="listuser">
	    		<tr align="center"  class="TableData"><s:hidden name="tbUserinfo.userInfoId"></s:hidden>
	    			<td><font size="3"><a href="selectaction!findByUserinfo.action?tbUserinfo.userInfoId=<s:property value='tbUserinfo.userInfoId'/>"><s:property value="tbUserinfo.userInfoName"/> </a></font></td>
	    			<td><font size="3"><s:property value="userName"/></font></td>
	    		</tr>
	    	</s:iterator>
	    	<tr>
					<td align="center" colspan="2">${cyhPage }</td>
			</tr>
	    </table>
    </center>
  </body>
</html>
