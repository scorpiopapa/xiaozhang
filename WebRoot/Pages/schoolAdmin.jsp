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
<title>总校管理员页面</title>
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
				url="delSchoolAdmin.action?delid="+delid;
				location.replace(url);
			}else if(t==2){
				url="delSchoolAdmin.action";
			
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
		location.href="addOrEditSchoolAdmin.action";
	}
	function likename(){
		var schoolName=$id("schoolName").value;
		if(schoolName!=""){
			location.href="Pages/schoolAdmin.action?schoolName="+schoolName;
		}else{
			alert("请输入总校名称");
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
										</span> <input name="Submit" type="button" class="right-button08"
											onclick="del(2,0);" value="删除所选任务" /> <input name="Submit2"
											type="button" class="right-button08" value="添加总校管理员" onclick="link();" />
										</c:if>
										 &nbsp;&nbsp;	
										<span>总校名称：</span><input type="text" name="schoolName" id="schoolName"  value="<c:out value='${schoolName }'/>">
										<input type="button" class="right-button08" onclick="likename()" value="搜索">
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="8" align="center" style="font-size:16px">总校管理员列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">
													<c:if test="${loginAdmin.adminRoot eq 0}">
													选择
													</c:if>
													&nbsp;
													</th>
													<th width="14%">所属总校</th>
													<th width="14%">用户名</th>
													<th width="14%">密码</th>
													<th width="12%">分校管理员</th>
													<th width="12%">添加时间</th>
													<th width="12%">更新时间</th>
													<th>操作</th>
												</tr>
												<c:forEach items="${schooladmin }" var="sa">
													<tr bgcolor="#FFFFFF" align="center">
														<th height="20">
														<c:if test="${loginAdmin.adminRoot eq 0}">
														<input type="checkbox" name="delid" value="${sa.adminId }" />
														</c:if>
														&nbsp;
														</th>
														<td>${sa.tbSchool.schoolName }</td>
														<td>${sa.adminName }</td>
														<td>${sa.adminPassword }</td>
														<td>
															<a href="branchAdmin.action?shcool.schoolId=${sa.tbSchool.schoolId }">查看分校管理员</a>
														</td>
														<td>${sa.time }</td>
														<td>${sa.alterTime }</td>
														<td>
															<a href="addOrEditSchoolAdmin.action?admin.adminId=${sa.adminId }">编辑 </a> | 
															<a href="addOrEditBranchAdmin.action?shcool.schoolId=${sa.tbSchool.schoolId }">添加分校管理员 </a>
															<c:if test="${loginAdmin.adminRoot eq 0}">
															 | 
															<a href="javascript:void(0);" onclick="del(1,${sa.adminId });return false;">删除 </a>
															</c:if>
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
	<form action="schoolAdmin.action" method="post" id="searchForm">
		<input type="hidden" name="currentPage" id="currentPage" />
	</form>
</body>
</html>
