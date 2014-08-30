<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addprincipal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
			function  text() {
				if(userInfoName() && userInfoPhone() && userInfoEmail() && userName()){
	  				document.myform.submit();
					}
			}
		
			function userInfoName(){
				var userInfoName = $('#userInfoName')[0];
				var a = userInfoName.value;
				if(a.length==0){
					alert("请输入您的名字");
					return false;
				}
				return true;
			}
			function userInfoPhone(){
				var userInfoPhone = $('#userInfoPhone')[0];
				var a = userInfoPhone.value;
				if(a.length==0){
					alert("请输入您的电话");
					return false;
				}
				return true;
			}
			function userInfoEmail(){
				var userInfoEmail = $('#userInfoEmail')[0];
				var a = userInfoEmail.value;
				if(a.length==0){
					alert("请输入邮箱号");
					return false;
				}
				return true;
			}
			function userName(){
				var userName = $('#userName')[0];
				var a = userName.value;
				if(a.length==0){
					alert("请输入用户帐号名");
					return false;
				}
				return true;
			}
		</script>
  </head>
  
  <body>
    <center>
    	<s:form action="selectaction!addprincipal.action" name="myform" method="post" theme="simple">
	    	<table>
				<tr>
					 <td>分校名称:</td>
					 <td>
			 			<s:select list="listbranchschool" listKey="branchSchoolId" listValue="braschName" name="tbUserinfo.tbBranchschool.branchSchoolId"></s:select> 
					 </td>
	                 <td>真实姓名:</td>
	                 <td>
	                 	<s:textfield name="tbUserinfo.userInfoName" id="userInfoName"/>
					 </td>
				 </tr>
				 <tr>	 
	                 <td>性别:</td>
	                  <td><s:radio list="#{'男':'男','女':'女'}" name="tbUserinfo.userInfoSex" value="'男'"></s:radio>
	                   </td>
	                 <td>生日:</td>
	                  <td>
	                  	<s:textfield name="tbUserinfo.userInfoBirthday" id="startTerm" readonly="true" onclick="WdatePicker()"/>
	                  </td>
                 </tr>
				 <tr>
	                 <td>电话:</td>
	                  <td><s:textfield name="tbUserinfo.userInfoPhone" id="userInfoPhone"/> </td>
	                 <td>邮箱:</td>
	                  <td><s:textfield name="tbUserinfo.userInfoEmail" id="userInfoEmail"/> </td>
	             </tr>
				 <tr>
	                 <td>任教课程:</td>
	                 <td><s:textfield name="tbUserinfo.userInfoCourse"/> </td>
	                 <td>毕业院校:</td>
	                 <td><s:textfield name="tbUserinfo.graduateSchool"/> </td>
	             </tr>
				 <tr>
					 <td>用户名:</td>
					 <td><s:textfield name="user.userName" id="userName"/> </td>
				</tr>  
				<tr>
	                 <td align="center" colspan="4">
	                 <input type="button" value="添加" onclick="text()" style="width:50px;height:25px;background:white;"/>
	                 <input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
	                 </td>
				</tr>   	
	    	</table>
    	</s:form>
    </center>
  </body>
</html>
