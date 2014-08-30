<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectcourse.jsp' starting page</title>
    
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
	
	<SCRIPT type="text/javascript">
	
  	 function deleteCourse(){
      			var str="";
                 var courseId = "";
                     $("input:checked").each(function(){  
                              courseId += $(this).val()+",";
                              str = courseId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteCourse.action",{'str':str, 'a':Math.random()},function(){
					window.location.reload();
				});
				}   
	 }
	 
  </SCRIPT>

  </head>
  
  <body>
  	<center>
   		<table class="tablestyle1" width="100%" border="1">
				<tr align="center" class="CTitle">
					<td height="22" style="font-size:16px" colspan="8" align="center">
						<h4>课程信息</h4>
					</td>
				</tr>
   		<tr align="center" height="20" class="TableHeader2" bgcolor="#EEEEEE">
   			<td>城区</td>
   			<td>分校</td>
   			<td>科目</td>
   			<td>课程</td>
   			<td>课程成绩</td>
   			<td>删除</td>
   		</tr>
   		<s:iterator value="listCourse">
   			<tr align="center" class="TableHeader2" height="20">
   				<td><s:property value="tbTown.townName"/></td>
   				<td><s:property value="tbBranchschool.braschName"/></td>
   				<td><s:property value="tbSubject.subjectName"/></td>
   				<td><s:property value="courseName"/></td>
   				<td>
					<s:if test="courseScore==0">不限</s:if>
					<s:if test="courseScore==1">优</s:if>
					<s:if test="courseScore==2">良</s:if>
					<s:if test="courseScore==3">中</s:if>
					<s:if test="courseScore==4">差</s:if>
   				</td>
   				<td align="center"><input type="checkbox" name="subBox" id="courseId" value="<s:property value='courseId'/>">
   					<a href="selectaction!toupdateCourse.action?courseId=<s:property value='courseId'/>">修改</a>
   				</td>
   			</tr>
   		</s:iterator>
   			<tr height="20" class="TableData">
   				<td align="right" colspan="8"><font size="4"><a href="javascript:deleteCourse()">删除</a>
   				<a href="selectaction!toaddCourse.action">添加</a></font></td>
   			</tr>
   			<tr class="TableData">
					<td height="20" align="center" colspan="8">${cyhPage }</td>
			</tr>
   	</table>
   	</center>
  </body>
</html>
