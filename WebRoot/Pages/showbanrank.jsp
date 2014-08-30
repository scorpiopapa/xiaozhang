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
<title>班排名页面</title>
<script type="text/javascript" src="js/prototype.js"></script>
<c:if test="${not empty delTip }">
<script type="text/JavaScript">
	alert("请先删除下属的班级！");
</script>
</c:if>
<script type="text/javascript">
function $id(id){
	return document.getElementById(id);
}
function getKecheng(subject) {
			if(subject=="")
				subject=0;
			var url = 'getKechengJson.action';
			var params = 'subjectid=' + subject;
			new Ajax.Request(url, {
				method : 'POST',
				parameters : params,
				onFailure : showError,
				onComplete : processLoginResponse
			})
			function processLoginResponse(response) {
				var objs = eval("(" + response.responseText + ")");
				var otions = $id("lesson");
				var count = otions.options.length;
				for ( var j = 0; j < count; j++) {
					otions.remove(0);
				}
				if(objs.kecheng.length==0){
					var selectObj = $id("lesson");
					selectObj.options[0] = new Option("==请选择==", "0");
				}else{
					var selectObj = $id("lesson");
					selectObj.options[0] = new Option("==请选择==", "0");
					for ( var i = 0; i < objs.kecheng.length; i++) {
						var selectObj = $id("lesson");
						selectObj.options[selectObj.length] = new Option(
								objs.kecheng[i].lessonName, objs.kecheng[i].id);
					}
				}
			}
			function showError(response) {
				alert("系统出错了");
				return;
			}
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
										<td width="40">&nbsp;
										</td>
										<td width="500">
											<form action="showbanrank.action" method="post">
												科目：<select name="subject.subjectId" onchange="getKecheng(this.value);">
													<option value="0">==请选择==</option>
													<c:forEach items="${subjects }" var="subs">
													<option value="${subs.subjectId }">${subs.subjectName }</option>
													</c:forEach>
												</select>&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												课程：<select name="lesson.id" id="lesson">
														<option value="0">==请选择==</option>
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
													<td height="22" colspan="9" align="center"
														style="font-size:16px">班排名列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">序号</th>
													<th width="14%">班名称</th>
													<th width="14%">所属课程</th>
													<th width="14%">所属科目</th>
													<th width="14%">所属分校</th>
													<th width="14%">所属总校</th>
													<th width="9%">第一顺序</th>
													<th width="9%">第二顺序</th>
													<th>操作</th>
												</tr>
												<c:forEach items="${banranklist }" var="bl" varStatus="i">
												<tr bgcolor="#FFFFFF" align="center">
													<th>${i.count }</th>
													<td>${bl.courseName }</td>
													<td>${bl.tbLesson.lessonName }</td>
													<td>${bl.tbSubject.subjectName }</td>
													<td>${bl.tbBranchschool.braschName }</td>
													<td>${bl.tbSchool.schoolName }</td>
													<td>${bl.defineSort }</td>
													<td>${bl.defineSort2 }</td>
													<td>
														<a href="editbanrank.action?course.courseId=${bl.courseId }"> 编辑 </a> 
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
					
  	<form action="showbanrank.action" method="post" id="searchForm">
		<input type="hidden" name="currentPage" id="currentPage" />
		<input type="hidden" name="lesson.id" value="${lessonid }" />
		<input type="hidden" name="subject.subjectId" value="${subjectId }" />
	</form>
</body>
</html>
