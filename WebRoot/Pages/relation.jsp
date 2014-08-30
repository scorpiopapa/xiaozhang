<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">

    <title>亲子关系页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery1.8.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
	</script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body >
  		<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				<tr>
					<th class="tablestyle_title">亲子关系页面</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							
							<tr>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>学生信息</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">姓名:</td>
											<td width="35%" ><input type="text" value="${stu.tbUserinfo.userInfoName }" readonly/></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">性别:</td>
											<td width="35%" >
											<c:if test="${stu.tbUserinfo.userInfoSex eq '男' }">男</c:if>
					    						<c:if test="${stu.tbUserinfo.userInfoSex eq '女' }">女</c:if>
					    						</td>
											</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">生日:</td>
											<td width="35%" ><input type="text" size="6" value="${stu.tbUserinfo.userInfoBirthday }" readonly/></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">公立学校:</td>
											<td width="35%" ><input type="text" value="${stu.tbUserinfo.studentSchool }" readonly/></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">公立班级:</td>
											<td width="35%" ><input type="text" value="${stu.tbUserinfo.studentClass }" readonly/></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">QQ:</td>
											<td width="35%" ><input type="text" value="${stu.tbUserinfo.userinfoQq }" readonly/></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">手机:</td>
											<td width="35%" ><input type="text" value="${stu.tbUserinfo.userInfoPhone }" readonly/>&nbsp;</td>
											
										</tr>
									</table>
									<br />
								</fieldset>
							</TD>
							</TR>
						</TABLE>
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>家长信息</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">姓名:</td>
											<td width="35%" ><input type="text" value="${parent.tbUserinfo.userInfoName }" readonly/></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">性别:</td>
											<td width="35%" >
												<c:if test="${parent.tbUserinfo.userInfoSex eq '男' }">男</c:if>
					    						<c:if test="${parent.tbUserinfo.userInfoSex eq '女' }">女</c:if>
											</td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">生日:</td>
											<td width="35%" ><input type="text" size="6" value="${parent.tbUserinfo.userInfoBirthday }" readonly/></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">电话:</td>
											<td width="35%" ><input type="text" value="${parent.tbUserinfo.userInfoPhone }" readonly/></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">邮箱:</td>
											<td width="35%" ><input type="text" value="${parent.tbUserinfo.userInfoEmail }" readonly/></td>
										</tr>
									</table>
									<br />
								</fieldset>
							</TD>
							</TR>
							<TR>
					<TD colspan="2" align="center" height="50px">
						<input type="button" onclick="window.history.go(-1);" value="返回" class="button" />
					</TD>
				</TR>
						</TABLE>
					</td>
				</tr>
			</TABLE>
			</td>
			</tr>
			</table>
  </body>
</html>
