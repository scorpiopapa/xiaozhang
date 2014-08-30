<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>总校地图显示调整</title>

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
<script type="text/javascript" src="js/jquery1.8.js"></script>
<script type="text/javascript" src="js/gogoShare.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/interface/page.js"></script>
<script type="text/javascript" src="dwr/interface/gogoServiceImpl.js"></script>
<script type="text/javascript" src="dwr/interface/secondBackstage.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	//分页查询方法
	function $id(id) {
		return document.getElementById(id);
	}
	function ff(v) {
		var searchForm = $id("searchForm");
		$id.value = v;
		searchForm.submit();
	}
	function dwrProjectTable(sum) {
		DWRUtil.removeAllRows("dataTable");
		var page1 = {
			'currentPage' : sum,
			'countPerPage' : countPerPage
		};
		secondBackstage.findPageTbSchool(page1, pageCallBack);
	}
	function link() {
		location.href = "Pages/AddBranchSchool?type=0";
	}
	function likename() {
		var schoolName = $id("schoolName").value;
		if (schoolName != "") {
			location.href = "Pages/viewRegSchool.action?schoolName="
					+ schoolName;
		} else {
			alert("请输入学校名称");
		}
	}
	//分页的回调函数
	function pageCallBack(data) {
		var num = (data.currentPage - 1) * countPerPage;
		pageSelect(data);
		dataIsNull(data);
		DWRUtil
				.addRows(
						"dataTable",//tbody的ID
						data.currentList,
						[
								function(obj) {
									num = parseInt(num) + 1;
									return num;
								},
								function(obj) {
									return obj.schoolName;
								},
								function(obj) {
									if (obj.showType == 1)
										return "显示";
									else
										return "不显示";
								},
								function(obj) {
									return '<img src="'+obj.type+'"  width="120px" height="80px"/>';
								},
								function(obj) {
									if (obj.branchschoolId != null)
										return '<input type="button" value="修改" onClick="updatePage('
												+ obj.branchschoolId + ')"/>';
								} ], {
							escapeHtml : false
						});
	}
	function updatePage(data) {
		$("#updateSchoolName").text("");
		$("#updateType").val(0);
		$("#updateTypeShow").val(0);
		secondBackstage.findDetailTbSchool(data, dataUpdatePageCallBack);
	}
	var id;
	function dataUpdatePageCallBack(data) {
		if (data.branchschoolId != null) {
			id = data.branchschoolId;
			$("#updateSchoolName").text(data.schoolName);
			$("#updateType").val(data.type);
			$("#updateTypeShow").val(data.showType);
		}
		showUpadteDiv();
	}
	function updateData() {
		var data = {
			"id" : id,
			"type" : $("#updateType").val(),
			"showType" : $("#updateTypeShow").val()
		};
		secondBackstage.updateTbSchool02(data, callBack);
	}
</script>
</head>

<body>
	<form name="fom" id="fom" method="post" action="">
		<input type="hidden" value="${loginAdmin.adminId}" id="adminId">
		<input type="hidden" value="${loginAdmin.tbSchool.schoolId}"
			id="adminSchoolId"> <input type="hidden"
			value="${loginAdmin.tbBranchschool.branchSchoolId}"
			id="adminBranchSchoolId"> <input type="hidden"
			value="${loginAdmin.adminRoot}" id="adminRoot"> <input
			type="hidden" value="${loginAdmin.isValid}" id="userinfoId">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="images/nav04.gif">
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
								</table> <span>学校名称：</span> <input type="text" name="schoolName"
								id="schoolName" value='${schoolName }' /> <input type="button"
								class="right-button08" onclick="likename()" value="搜索">
							</td>
						</tr>
					</table></td>
			</tr>

			<tr>
				<td><table id="subtree1" style="DISPLAY: " width="100%"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><table width="95%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="10" align="center"
														style="font-size:16px">列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="10%">序号</th>
													<th width="40%">总校名称</th>
													<th width="20%">是否显示</th>
													<th width="20%">颜色级别</th>
													<th width="10%">操作</th>
												</tr>

												<tbody id="dataTable" bgcolor="#FFFFFF" align="center">
												</tbody>
											</table>
										</td>
									</tr>

								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="6"><img src="images/spacer.gif" width="1"
											height="1" />
										</td>
									</tr>
									<tr>
										<td height="33"><table width="100%" border="0"
												align="center" cellpadding="0" cellspacing="0"
												class="right-font08">
												<tr>
													<td width="50%">共 <font class="right-text09"
														id="pageAllCount">5</font> 页 | 第 <font
														class="right-text09" id="pageCurrentPage">1</font> 页</td>
													<td width="49%" align="right">[<a href="javascript:"
														class="right-font08" onclick="dataPage('start')">首页</a> |
														<a href="javascript:" class="right-font08"
														onclick="dataPage('up')">上一页</a> | <a href="javascript:"
														class="right-font08" onclick="dataPage('down')">下一页</a> |
														<a href="javascript:" class="right-font08"
														onclick="dataPage('end')">末页</a>] 转至：</td>
													<td width="1%"><table width="20" border="0"
															cellspacing="0" cellpadding="0">
															<tr>
																<td width="1%"><input name="textfield3" type="text"
																	class="right-textfield03" size="1" id="currentPage" />
																</td>
																<td width="87%"><input name="Submit23222"
																	type="submit" class="right-button06"
																	onclick="dataPage('currentPage');return false;"
																	value="跳转" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--修改页面 start-->
		<div class="upadteDiv" id="upadteDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">修改信息</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%;">
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>修改信息</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%;">
											<tr>
												<td width="25%" align="right" nowrap="nowrap">学校名称:</td>
												<td width="35%"><span id="updateSchoolName"></span></td>
											</tr>
											<tr>
												<td align="right">是否显示:</td>
												<td width="34%"><select id="updateTypeShow">
														<option value="0">不显示</option>
														<option value="1">显示</option>
												</select></td>
											</tr>
											<tr>
												<td align="right">显示颜色:</td>
												<td width="34%"><select id="updateType">
														<option value="0">红色</option>
														<option value="1">紫色</option>
														<option value="2">黄色</option>
														<option value="3">粉色</option>
														<option value="4">草绿色</option>
														<option value="5">蓝色</option>
														<option value="6">深蓝色</option>
														<option value="7">橙色</option>
														<option value="8">墨绿色</option>
														<option value="9">土黄色</option>
												</select></td>


											</tr>
										</table>
										<br />
									</fieldset>
								</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"><input
						onclick="updateData()" type="button" name="Submit" value="保存"
						class="button" /> <input type="button" name="Submit2" value="返回"
						class="button" onclick="cancel()" /></TD>
				</TR>
				<tr height="100px">
					<td>&nbsp;
					<td>
				</tr>
				<tr height="100px">
					<td>&nbsp;
					<td>
				</tr>
				<tr height="100px">
					<td>&nbsp;
					<td>
				</tr>
				<tr height="100px">
					<td>&nbsp;
					<td>
				</tr>
				<tr height="100px">
					<td>&nbsp;
					<td>
				</tr>
			</TABLE>
		</div>
		<!--修改页面 End-->
	</form>
</body>
</html>
