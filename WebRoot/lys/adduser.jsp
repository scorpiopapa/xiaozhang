<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'adduserinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   		
   			<s:form action="selectaction!addUser.action" name="myform" method="post" theme="simple">
   			<table>
   				<tr>
	   				<td>学&nbsp;&nbsp;校</td>
	   				<td><s:select list="listschool" listKey="schoolId" listValue="schoolName" name="tbUserinfo.tbSchool.schoolId" headerKey="" headerValue="--请选择--"></s:select></td>
   					<td>分&nbsp;&nbsp;校</td>
   					<td><s:select list="listbranchschool" listKey="branchSchoolId" listValue="braschName" name="tbUserinfo.tbBranchschool.branchSchoolId" headerKey="" headerValue="--请选择--"></s:select></td>
   					<td>课&nbsp;&nbsp;程</td>
	   				<td><s:select list="listCourse" listKey="courseId" listValue="courseName" name="tbUserinfo.tbCourse.courseId" headerKey="" headerValue="--请选择--"></s:select></td>
   				</tr>
   			</table><br/><br/>
   			<center>
   			<table width="100%">
   				<tr>
   					<td>帐号名称</td>
   					<td><s:textfield name="tbuser.userName" id="userName"/></td>
   					<td>密码</td>
   					<td><s:password name="tbuser.userPassword" id="userPassword"/></td>
   				</tr>
   				<tr>
   					<td>公立学校班级</td>
	   				<td><s:textfield name="tbUserinfo.studentClass" id="studentClass"/></td>
	   				<td>公立学校</td>
	   				<td><s:textfield name="tbUserinfo.studentSchool" id="studentSchool"/></td>
   				</tr>
   				<tr>
   					<td>用户名</td>
   					<td><s:textfield name="tbUserinfo.userInfoName" id="userInfoName"/></td>
	   				<td>性&nbsp;&nbsp;别</td>
	   				<td>
	   					<s:radio list="#{'男':'男','女':'女'}" name="tbUserinfo.userInfoSex" value="'男'"></s:radio>
	   				<td>生&nbsp;&nbsp;日</td>
	   				<td><s:textfield name="tbUserinfo.userInfoBirthday" id="userInfoBirthday" readonly="true" onclick="WdatePicker()"/></td>
	   			</tr>
	   			<tr>
	   				<td>电&nbsp;&nbsp;话</td>
	   				<td><s:textfield name="tbUserinfo.userInfoPhone" id="userInfoPhone"/></td>
	   				<td>头&nbsp;&nbsp;像</td>
	   				<td><s:textfield name="tbUserinfo.userInfoAvatar" id="userInfoAvatar"/></td>
	   			</tr>
	   			<tr>
	   				<td>签&nbsp;&nbsp;名</td>
	   				<td><s:textfield name="tbUserinfo.userInfoSign" id="userInfoSign"/></td>
	   				<td>vip到期时间</td>
	   				<td><s:textfield name="tbUserinfo.userInfoVip" id="userInfoVip" readonly="true" onclick="WdatePicker()" /></td>
	   			</tr>
	   			<tr>
	   				<td>老师毕业时间</td>
	   				<td><s:textfield name="tbUserinfo.graduateSchool" id="graduateSchool" readonly="true" onclick="WdatePicker()"/></td>
	   				<td>用户邮箱</td>
	   				<td><s:textfield name="tbUserinfo.userInfoEmail" id="userInfoEmail"/></td>
	   			</tr>
	   			<tr>
	   				<td>老师工作年限</td>
	   				<td><s:textfield name="tbUserinfo.workYear" id="workYear"/></td>
	   				<td>用户信息课程</td>
	   				<td><s:textfield name="tbUserinfo.userInfoCourse" id="userInfoCourse"/></td>
	   			</tr>
	   			<tr>
	   				<td>用户权限</td>
	   				<td>
	   					<s:radio list="#{'0':'校长','1':'老师','2':'家长','3':'学生'}" name="tbUserinfo.userInfoRoot"></s:radio>
	   				<td>数据是否有效</td><br/>
	   				<td>
						<s:radio list="#{'0':'无效','1':'有效'}" name="tbUserinfo.isValid" value="'1'"></s:radio>
					</td>
	   			</tr>
	   			<tr>
	   				<td colspan="4" align="right">
	   					<s:submit name="myform" value="添加" style="width:50px;height:25px;background:white;"></s:submit>
	   					<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
	   				</td>
	   			</tr>
   			</table>
   		</center>
   			</s:form>
  </body>
</html>
