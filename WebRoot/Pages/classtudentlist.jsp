<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>班级学生页面</title>
<script type="text/javascript">
function $id(id){
	return document.getElementById(id);
}

		
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}	
	function del(t,delid,cid){
		var tip = "";
		
		if(t==1)
			tip="此操作将会删除学生在该班的信息，确认删除？";
		else if(t==2)
			tip="此操作将会删除学生在对应班的信息，确认删除所选？";
		
		var c = confirm(tip);
		if(c){
			var url = "";
			if(t==1){
				url="delstuparInfoDo.action?delid="+delid+"&course.courseId="+cid;
				location.replace(url);
			}else if(t==2){
				url="delstuparInfoDo.action";
			
				var id = document.getElementsByName("delid");
				var j = 0;
				for(var i=0;i<id.length;i++){
				    if(id[i].checked==true){
				        j = j+1;
				    }
				}
				if(j<1){
				    alert("请选择您要删除的信息");
					return;
				}
				$id("fom").action=url;
				$id("fom").submit();
			}
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
<script type="text/JavaScript">
	function link(){
		location.href="addClass.action";
	}
	function add(cid){
		location.href="addStudenttoClass.action?course.courseId="+cid;
	}
	function imports(cid){
		location.href="importStudenttoClass.action?course.courseId="+cid;
	}
</script>
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
									<td width="21">&nbsp;</td>
									<td width="538">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td><table id="subtree1" style="DISPLAY: " width="100%"
					border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<form name="fom" id="fom" method="post" action="">
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="20"><span class="newfont07">选择：<a
												href="java" class="right-font08" onclick="selectAll();return false;">全选</a>-<a href="#"
												class="right-font08" onclick="unselectAll();return false;">反选</a>
										</span> <input name="Submit" type="button" class="right-button08" onclick="del(2,0,${courseid });" value="删除所选任务" />
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="9" align="center" style="font-size:16px">班级学生列表
													<input type="hidden" name="course.courseId" value="${courseid }"/>
													</td>
												
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">选择</th>
													<th width="11%">学生姓名</th>
													<th width="12%">班级</th>
													<th width="14%">课程名</th>
													<th width="12%">分校名</th>
													<th width="14%">添加时间</th>
													<th width="14%">更新时间</th>
													<th>操作</th>
												</tr>
												<c:forEach items="${studentlist }" var="cl">
													<tr bgcolor="#FFFFFF" align="center">
														<th height="20">
															<input type="checkbox" name="delid" value="${cl.useclaId }" />
														</th>
														<td>${cl.tbUserinfo.userInfoName }</td>
														<td>${cl.tbCourse.courseName }</td>
														<td>${cl.tbCurriculum.courseName }</td>
														<td>${cl.tbBranchschool.braschName }</td>
														<td>${cl.tbUserinfo.time }</td>
														<td>${cl.tbUserinfo.alterTime }</td>
														<td>
															<a href="editStudentInfo.action?userInfo.userInfoId=${cl.tbUserinfo.userInfoId }">编辑</a> | 
															<a href="viewRelation.action?userInfo.userInfoId=${cl.tbUserinfo.userInfoId }">亲子关系</a> |   
															<a href="javascript:void(0);" onclick="del(1,${cl.useclaId },${courseid });return false;">删除 </a>
														</td>
													</tr>
												</c:forEach>
												<tbody id="branchschoolList">
												</tbody>
											</table></td>
									</tr>
								</table>
							</form>
							<table width="95%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="6"><img src="images/spacer.gif" width="1"
										height="1" /></td>
								</tr>
								<tr>
									<td height="33">
										<%@include file="../util/backPages.jsp" %>
									</td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>

	<form action="viewclassstudent.action" method="post" id="searchForm">
			<input type="hidden" name="currentPage" id="currentPage" />
			<input type="hidden" name="course.courseId" value="${courseid }"/>
	</form>
</body>
</html>
