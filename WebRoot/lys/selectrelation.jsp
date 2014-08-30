<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectrelation.jsp' starting page</title>
    
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
   		 <table id="subtree1" style="DISPLAY: " width="50%"
				border="1" cellspacing="0" cellpadding="0">
				<tr class="CTitle">
					<td height="22" style="font-size:16px" colspan="2" align="center">
						课程信息
					</td>
			</tr>
    	<tr bgcolor="#EEEEEE">
    		<td>家长名称</td>
    		<td>学生名称</td>
    	</tr>
   		<s:iterator value="listrelation">
    		<tr>
    			<td><a href="selectaction!findByUserinfo.action?tbUserinfo.userInfoId=<s:property value='tbUserinfoByUserInfoId.userInfoId'/>"><s:property value="tbUserinfoByTbUserInfoId.userInfoName"/> </a></td>
    			<td><a href="selectaction!findByUserinfo.action?tbUserinfo.userInfoId=<s:property value='tbUserinfoByTbUserInfoId.userInfoId'/>"><s:property value="tbUserinfoByUserInfoId.userInfoName"/> </a></td>
    		</tr>
    	</s:iterator>
    	<tr	class="TableData">
					<td align="center" colspan="2">${cyhPage }</td>
			</tr>
    </table>
    </center>
  </body>
</html>
