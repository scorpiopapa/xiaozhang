<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty loginout }">
  	<script type="text/javascript">alert("登录超时！");top.location.replace("${pageContext.servletContext.contextPath}/login.jsp");</script>
</c:if>
<c:if test="${not empty loginFail }">
  	<script type="text/javascript">alert("用户名或密码错误！");top.location.replace("${pageContext.servletContext.contextPath}/login.jsp");</script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校掌后台登录界面</title>
<script type="text/javascript">
	function $id(id){
		return document.getElementById(id);
	}
	function checkForm(roots){
		var adminName=$id("adminName").value;
		var adminPassword=$id("adminPassword").value;
		var content = /^.*\w+.$/;
		if(!content.test(adminName)){
			alert("请输入用户名！");
			return;
		}
		if(!content.test(adminPassword)){
			alert("请输入密码！");
			return;
		}
		if($id("admintype").checked==true)
			$id("userlogin").action=roots+"/Pages/checkLogin.action";
		else if($id("teachertype").checked==true)
			$id("userlogin").action=roots+"/Pages/teacherLogin.action";
		$id("userlogin").submit();	
	}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="147" background="images/top02.gif"><img src="images/top03.gif" width="776" height="147" /></td>
  </tr>
</table>
<form id="userlogin" method="post">
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="images/ico13.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
          
        </table></td>
        <td><img src="images/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="31%" height="35" class="login-text02">用户名：<br /></td>
        <td width="69%"><input name="admin.adminName" id="adminName" type="text" size="25" /></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">密　码：<br /></td>
        <td><input name="admin.adminPassword" id="adminPassword" type="password" size="25" /></td>
      </tr>
     <tr>
        <th colspan="2">
        	<input type="radio" name="logintype" id="admintype" value="0" checked/>管理员&nbsp;&nbsp;
            <input type="radio" name="logintype" id="teachertype" value="1" />老师
        </th>
      </tr>
      <tr>
        <td height="35">&nbsp;</td>
        <td><input type="submit" class="right-button01" value=" 登 录 " onclick="checkForm('${pageContext.servletContext.contextPath }');return false;"/>
          <input type="reset" class="right-button02" value="重 置" /></td>
      </tr>

    </table></td>
  </tr>
</table>
</form>
</body>
</html>