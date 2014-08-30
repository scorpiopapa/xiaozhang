<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectuserinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 
  <body>
   <center>
   		<s:form action="selectaction!addClassnotice.action" name="myform" method="post" theme="simple">
   		
   		<table>
				<tr>
					<td height="22" style="font-size:16px" colspan="16" align="center">添加班级通知</td>
				</tr>
   			<tr>
   				<td>班&nbsp;&nbsp;级:
	   					<s:select list="listuserinfoclass" listKey="tbCourse.courseId" listValue="tbCourse.courseName" name="tbclassnotice.tbCourse.courseId"></s:select> 
	   				</td>
   				<td>通知标题:<s:textfield name="tbclassnotice.clanotTitle"/></td>
   			</tr>
   			<tr>
   				<td>通知内容:<s:textarea name="tbclassnotice.clanotContent" style="width:500px;height:100px;background:white;"/></td>
   			</tr>
   			<tr align="center">
   				<td colspan="2"><s:submit name="myform" value="添加" style="width:50px;height:25px;background:white;"></s:submit> 
   				<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/></td>
<!--   			<td align="center"><font size="3"><a href="${pageContext.request.contextPath }/lys/adduserinfo.jsp">添加</a></font></td>-->
   			</tr>
   		</table>
   		</s:form>
   </center>
  </body>
</html>
