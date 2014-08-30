<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<c:if test="${empty loginAdmin }">
  	<script type="text/javascript">alert("请登录！");top.location.replace("${pageContext.servletContext.contextPath}/login.jsp");</script>
</c:if>
<title>校掌后台管理页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<frameset rows="59,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="javascript:;" name="topFrame" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="213,*" frameborder="no" border="0" framespacing="0">
		<frame src="leftPage.jsp" name="leftFrame" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="mainfra.html" name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</noframes></body>
</html>
