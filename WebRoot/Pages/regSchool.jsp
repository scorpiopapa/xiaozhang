<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>总校页面</title>
<script type="text/javascript">
function $id(id){
	return document.getElementById(id);
}

function ff(v) {
	var searchForm = $id("searchForm");
	$id.value = v;
	searchForm.submit();
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
	function del(t,delid){
		var tip = "";
		
		if(t==1)
			tip="确认删除？";
		else if(t==2)
			tip="确认删除所选？";
		
		var c = confirm(tip);
		if(c){
			var url = "";
			if(t==1){
				url="delRegSchoolDo.action?delid="+delid;
				location.replace(url);
			}else if(t==2){
				url="delRegSchoolDo.action";
			
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
		location.href="Pages/AddBranchSchool?type=0";
	}

	function likename(){
		var schoolName=$id("schoolName").value;
		if(schoolName!=""){
			location.href="Pages/viewRegSchool.action?schoolName="+schoolName;
		}else{
			alert("请输入学校名称");
		}
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
										<td height="20"><span class="newfont07">
										<c:if test="${loginAdmin.adminRoot eq 0}">
										选择：<a
												href="java" class="right-font08"
												onclick="selectAll();return false;">全选</a>-<a href="#"
												class="right-font08" onclick="unselectAll();return false;">反选</a>
										</span> 
											
											<input name="Submit" type="button" class="right-button08"
											onclick="del(2,0);" value="删除所选任务" />
											</c:if>
											&nbsp;&nbsp;	
										<span>学校名称：</span><input type="text" name="schoolName" id="schoolName"  value="<c:out value='${schoolName }'/>">
										<input type="button" class="right-button08" onclick="likename()" value="搜索">
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="8" align="center"
														style="font-size:16px">注册学校列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">
													<c:if test="${loginAdmin.adminRoot eq 0}">
													选择
													</c:if>
													&nbsp;
													</th>
													<th width="17%">学校名称</th>
													<th width="6%">分校数量</th>
													<th width="23%">地址</th>
													<th width="15%">联系方式</th>
													<th width="13%">申请日期</th>
													<th width="13%">审核状态</th>
													<th>操作</th>
												</tr>
												<c:forEach items="${schoolList }" var="sl">
													<tr bgcolor="#FFFFFF" align="center">
														<th height="20">
														<c:if test="${loginAdmin.adminRoot eq 0}">
														<input type="checkbox" name="delid" value="${sl.schoolId }" />
														</c:if>
														&nbsp;
														</th>
														<td>${sl.schoolName }</td>
														<td>${sl.subSchoolNum }</td>
														<td>${sl.schoolAddress }</td>
														<td>${sl.schoolPhone }</td><td><fmt:formatDate value="${sl.time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
														<td>
															<c:if test="${sl.status eq -1}">未通过审核</c:if>
															<c:if test="${sl.status eq 0}">未审核</c:if>
															<c:if test="${sl.status eq 1}">已通过审核</c:if>
														</td>
														<td><a
															href="RegSchoolDetail.action?regschool.schoolId=${sl.schoolId }">
																查看 </a> | <a href="javascript:void(0);"
															onclick="del(1,${sl.schoolId });return false;">
																删除 </a>
																</td>
													</tr>
												</c:forEach>
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
	<form action="viewRegSchool.action" method="post" id="searchForm">
		<input type="hidden" name="currentPage" id="currentPage" />
	</form>
</body>
</html>
