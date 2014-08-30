<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<LINK rel="stylesheet"
			href="${pageContext.request.contextPath }/css/page.css"
			type="text/css">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<SCRIPT type="text/javascript">
	
	function deleteUserinfo(){
			 var str="";
                 var userInfoId = "";
                     $("input:checked").each(function(){  
                              userInfoId += $(this).val()+",";
                              str = userInfoId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteprincipal.action",{'str':str, 'a':Math.random()},function(){
					window.location.reload();
			});
			}
  	    }
	</SCRIPT>
  
  <body>
   <center>
   		<c:if test="${tips eq 1 }">
		<script type="text/javascript">
		alert("用户名已存在!");
		</script>
		</c:if>
   		<table id="subtree1" style="DISPLAY: " width="100%"
						border="1" cellspacing="0" cellpadding="0">
				<tr class="CTitle">
					<td height="22" style="font-size:16px" colspan="12" align="center"
						>分校校长信息</td>
				</tr>
					<tr>
						
	   				</tr>
				
   			<tr align="center" bgcolor="#EEEEEE">
   				<td>总校名称</td>
   				<td>分校名称</td>
   				<td>真实姓名</td>
   				<td>用户名</td>
   				<td>密码</td>
   				<td>性别</td>
   				<td>生日</td>
   				<td>电话</td>
   				<td>邮箱</td>
   				<td>操作</td>
   			</tr>
   			
   			<s:iterator value="userlist1">
			<tr align="center">
				<td><s:property value="tbUserinfo.tbSchool.schoolName"/></td>
	   			<td><s:property value="tbUserinfo.tbBranchschool.braschName"/></td>
   				<td><s:property value="tbUserinfo.userInfoName"/></td>
   				<td><s:property value="userName"/></td>
   				<td><s:property value="userPassword"/></td>
				<td><s:property value="tbUserinfo.userInfoSex"/></td>
   				<td><s:property value="tbUserinfo.userInfoBirthday"/></td>
   				<td><s:property value="tbUserinfo.userInfoPhone"/></td>
   				<td><s:property value="tbUserinfo.userInfoEmail"/></td>
   				<td><input type="checkbox" id="tbUserinfo.userInfoId" value="<s:property value='tbUserinfo.userInfoId'/>">
   					/ <a href="selectaction!toupdateprincipal.action?userInfoId=<s:property value='tbUserinfo.userInfoId'/>">修改</a>
   				</td>
   			</tr>
   		</s:iterator>
   			<tr>
   				<td align="center"  colspan="12"><font size="3">
	   				<a href="selectaction!toaddprincipal.action">添加分校校长</a>
	   				/ <a href="javascript:deleteUserinfo()">删除</a>
   				</font></td>
   			</tr>
   			<tr	class="TableData">
					<td align="center" colspan="12">${cyhPage }</td>
			</tr>
   		</table>
   </center>
  </body>
</html>
