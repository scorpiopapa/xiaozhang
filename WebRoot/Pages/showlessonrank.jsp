<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>课程排名页面</title>
<c:if test="${not empty delTip }">
<script type="text/JavaScript">
	alert("请先删除下属的班级！");
</script>
</c:if>
<script type="text/javascript">
function $id(id){
	return document.getElementById(id);
}
		
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
-->
</style>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="images/nav04.gif">

								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="40">
										&nbsp;
										</td>
										<td width="500">
											<form action="showlessonrank.action" method="post">
												科目：<select name="subject.subjectId">
													<option value="0">==请选择==</option>
													<c:forEach items="${subjects }" var="subs">
													<option value="${subs.subjectId }">${subs.subjectName }</option>
													</c:forEach>
												</select>&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="submit" value="查询"/>
											</form>	
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><table id="subtree1" style="DISPLAY: " width="100%"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<form name="fom" id="fom" method="post" action="">
							<table width="95%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="20">
										&nbsp;	
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="7" align="center"
														style="font-size:16px">课程排名列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="9%" align="center" height="30">序号</th>
													<th width="18%">课程名称</th>
													<th width="18%">所属科目</th>
													<th width="14%">所属总校</th>
													<th width="14%">第一顺序</th>
													<th width="14%">第二顺序</th>
													<th>操作</th>
												</tr>
												<c:forEach items="${lessonranklist }" var="bl" varStatus="i">
												<tr bgcolor="#FFFFFF" align="center">
													<th>${i.count }</th>
													<td>${bl.lessonName }</td>
													<td>${bl.tbSubject.subjectName }</td>
													<td>${bl.tbSchool.schoolName }</td>
													<td>${bl.defineSort }</td>
													<td>${bl.defineSort2 }</td>
													<td>
														<a href="editlessonrank.action?lesson.id=${bl.id }"> 编辑 </a> 
													</td>
												</tr>
												</c:forEach>
												<tbody id="branchschoolList">
												</tbody>
											</table>
										</td>
									</tr>
								</table>
								</form>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="6"><img src="images/spacer.gif" width="1"
											height="1" />
										</td>
									</tr>
									<tr>
										<td height="33">
										<%@include file="../util/backPages.jsp" %>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
  	<form action="showlessonrank.action" method="post" id="searchForm">
		<input type="hidden" name="currentPage" id="currentPage" />
		<input type="hidden" name="subject.subjectId" value="${subjectId }" />
	</form>
</body>
</html>
