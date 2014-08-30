<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'classinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
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
   		<table class="tablestyle1" width="100%" border="1">
				<tr align="center" class="CTitle">
					<td height="22" style="font-size:16px" colspan="7" align="center">
						<h4>课程信息</h4>
					</td>
				</tr>
   		<tr align="center" height="20" class="TableHeader2" bgcolor="#EEEEEE">
   			<td>学校</td>
   			<td>分校</td>
   			<td>课程</td>
   			<td>用户</td>
   			<td>角色</td>
   			<td> 是否是班主任</td>
   		</tr>
   		<s:iterator value="listinfoclass">
   			<tr align="center" class="TableHeader2" height="20">
   				<td><s:property value="tbSchool.schoolName"/></td>
   				<td><s:property value="tbBranchschool.braschName"/></td>
   				<td><s:property value="tbCourse.courseName"/></td>
   				<td><s:property value="tbUserinfo.userInfoName"/></td>
   				<td>
   					<s:if test="tbUserinfo.userInfoRoot==0">校长</s:if>
   					<s:if test="tbUserinfo.userInfoRoot==1">老师</s:if>
   					<s:if test="tbUserinfo.userInfoRoot==2">家长</s:if>
   					<s:if test="tbUserinfo.userInfoRoot==3">学生</s:if>
   				 </td>
   				 <td>
   					<s:if test="isHeadTeacher==0">是</s:if>
   					<s:if test="isHeadTeacher==1">否</s:if>
   				 </td>
   				
   			</tr>
   		</s:iterator>
   			<tr height="20" class="TableData">
   				<td align="right" colspan="7"><font size="4"><a href="javascript:deleteCourse()"></a>
   				<a href="selectaction!toCourse.action">添加</a></font></td>
   			</tr>
   			<tr class="TableData">
					<td height="20" align="center" colspan="7">${cyhPage }</td>
			</tr>
   	</table>
   	</center>
  </body>
</html>
