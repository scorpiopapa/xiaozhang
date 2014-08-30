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
<title>城市区域页面</title>
<script type="text/javascript">
function $id(id){
	return document.getElementById(id);
}

function ps(v){
	$id("currentPage").value=v;
	$id("myform").submit();
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
			tip="此操作会删除城市以及所属地区，确认删除？";
		else if(t==2)
			tip="此操作会删除城市以及所属地区，确认删除所选？";
		
		var c = confirm(tip);
		if(c){
			var url = "";
			if(t==1){
				url="delCity.action?delid="+delid;
				location.replace(url);
			}else if(t==2){
				url="delCity.action";
			
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
		location.href="Pages/addCity.jsp";
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
												href="java" class="right-font08"
												onclick="selectAll();return false;">全选</a>-<a href="#"
												class="right-font08" onclick="unselectAll();return false;">反选</a>
										</span> <input name="Submit" type="button" class="right-button08" onclick="del(2,0);" value="删除所选任务" />
											<input name="Submit2" type="button" class="right-button08" value="添加城市" onclick="link();" /></td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="8" align="center"
														style="font-size:16px">城市列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">选择</th>
													<th width="12%">城市名称</th>
													<th width="10%">拼音</th>
													<th width="11%">地区</th>
													<th width="11%">状态</th>
													<th width="16%">添加时间</th>
													<th width="16%">更新时间</th>
													<th>操作</th>
												</tr>
												<c:forEach items="${citylist }" var="cv">
													<tr bgcolor="#FFFFFF" align="center">
														<th height="20">
															<input type="checkbox" name="delid" value="${cv.cityId }" />
														</th>
														<td>${cv.cityName }</td>
														<td>${cv.spell }</td>
														<td>
															<a href="viewTown.action?citys.cityId=${cv.cityId }">查看地区</a>
														</td>
														<td>
														<c:if test="${cv.isValid eq 1 }">有效</c:if>
														<c:if test="${cv.isValid eq 0 }">无效</c:if>
														</td>
														<td>${cv.time }</td>
														<td>${cv.alterTime }</td>
														<td><a
															href="addOrEditCity.action?citys.cityId=${cv.cityId }">
																编辑 </a> | <a href="addOrEditTown.action?citys.cityId=${cv.cityId }">添加地区</a> | <a href="javascript:void(0);"
															onclick="del(1,${cv.cityId });return false;">
																删除 </a></td>
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

	<form action="cityView.action" method="post" id="searchForm">
			<input type="hidden" name="currentPage" id="currentPage" />
	</form>
</body>
</html>
