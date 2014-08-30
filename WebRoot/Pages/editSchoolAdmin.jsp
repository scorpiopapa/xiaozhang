<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改总校管理员</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css"
	type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">
function tishi()
{
  var a=confirm('数据库中保存有该人员基本信息，您可以修改或保留该信息。');
  if(a!=true)return false;
  window.open("冲突页.htm","","depended=0,alwaysRaised=1,width=800,height=400,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}

function $id(id){
	return document.getElementById(id);
}

function checkForm(){
	var adminPassword=$id("adminPassword").value;
	var password=$id("password").value;
	var content = /^\w{6,18}$/;
	if(!content.test(adminPassword)){
		alert("密码格式不正确！");
		return false;
	}
	if(adminPassword!=password){
		alert("两次密码输入不一致！");
		return false;
	}
}


</script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>

</head>

<body class="ContentBody">
	<form action="editSchoolAdminDo.action" method="post"
		enctype="multipart/form-data" name="form"
		onsubmit="return checkForm();">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">总校管理员修改页面</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<tr>
								<td align="left"><input type="submit" name="Submit"
									value="保存" class="button" /> <input type="button"
									name="Submit2" value="返回" class="button"
									onclick="window.history.go(-1);" /></td>
							</tr>
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>修改总校管理员</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
										<tr height="40">
											<td colspan="2" align="center">密码由6到18的数字，字母或下划线组成</td>
										</tr>	
											<tr>
												<th align="right">总&nbsp;&nbsp;&nbsp;&nbsp;校:</th>
												<td>${admins.tbSchool.schoolName }
												</td>
											</tr>	
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">用户名:</th>
												<td width="60%" align="left">
													<input class="text" name='admin.adminName' style="width:154px" readonly value="${admins.adminName }"/>&nbsp;<span class="red">*</span>
													<input type="hidden" name="admin.adminId" value="${admins.adminId }"/>
												</td>
											</tr>
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">密&nbsp;&nbsp;&nbsp;码:</th>
												<td width="60%" align="left">
													<input type="password" class="text" name='admin.adminPassword' style="width:154px" id="adminPassword"/>&nbsp;<span class="red">*</span>
												</td>
											</tr>
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">确认密码:</th>
												<td width="60%" align="left">
													<input type="password" class="text" style="width:154px" id="password"/>&nbsp;<span class="red">*</span>
												</td>
											</tr>
											<tr>
												<th align="right">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</th>
												<td>&nbsp;&nbsp;
											    	<input type="radio" name="admin.isValid" 
											    		<c:if test="${admins.isValid eq 1 }"> checked </c:if>
											    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											    	<input type="radio" name="admin.isValid" 
											    		<c:if test="${admins.isValid eq 0 }"> checked </c:if>
											    	 value="0"/>&nbsp;&nbsp;无效
						   						</td>
											</tr>	
										</table>
										<br />
									</fieldset></TD>
							</TR>
						</TABLE></td>
				</tr>





				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="submit" name="Submit" value="保存" class="button" /> <input
						type="button" name="Submit2" value="返回" class="button"
						onclick="window.history.go(-1);" />
					</TD>
				</TR>
			</TABLE>


			</td>
			</tr>



			</table>

		</div>
	</form>
</body>
</html>

