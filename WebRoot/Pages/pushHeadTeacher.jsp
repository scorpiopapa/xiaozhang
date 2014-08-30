<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>推送——校长</title>

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
	function dwrProjectTable(sum){
		DWRUtil.removeAllRows("dataTable");
		var page1={
				'currentPage':sum,
				'countPerPage':50
			};
		secondBackstage.findPageHeadTeacher(page1,$("#cityNameFind").val(),$("#townNameFind").val(), $("#schoolNameFind").val(), $("#subjectNameFind").val(),
				$("#lessonNameFind").val(),pageCallBack);
		}
	//分页的回调函数
	function pageCallBack(data){
			 var num=(data.currentPage-1)*50;
			 pageSelect(data);
			 dataIsNull(data);
			 DWRUtil.addRows(
			 "dataTable",//tbody的ID
			 data.currentList,
			 [
			 function(obj){return '<input type="checkbox" name="items" value="'+obj.userinfoId+'"/>';},
			 function(obj){num=parseInt(num)+1;
				return  num;},
			 function(obj){
					return obj.userinfoName;},
			 function(obj){return obj.userinfoPhone;}
			 ],
			 {
			 escapeHtml:false
			 }
			 );
		}
	function saveData(){
		var r = confirm("你确定发送吗？");
		var dataIds = new Array();
		var num = 0;
		if (r == true){
			$(":checkbox:checked").each(function() {
				dataIds[num] = $(this).val();
				num++;
			});
			if (num > 0)
				secondBackstage.addTbPushRecords(dataIds,$("#adminId").val(),$("#addtitle").val(), $("#addcontent").val(),callBack);
			else
				alert("请确定选择，后再做发送操作!");
		}
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
									<tr>
									<td width="20" nowrap="nowrap"><img
											src="images/ico07.gif" width="20" height="18" />
										</td>
										<td width="60" align="right">城市:</td>
										<td width="60"><input type="text" id="cityNameFind" /></td>
										<td width="60" align="right">城区:</td>
										<td width="60"><input type="text" id="townNameFind" /></td>
										<td width="60" align="right">学校:</td>
										<td width="60"><input type="text" id="schoolNameFind" /></td>
										<td width="60" align="right">科目:</td>
										<td width="60"><input type="text" id="subjectNameFind" /></td>
										<td width="60" align="right">课程:</td>
										<td width="60"><input type="text" id="lessonNameFind" /></td>
										<td ><input onclick="dwrProjectTable(1)" type="button"
											class="right-button02" value="查 询" />
										</td>
										<td><input onclick="saveData()" type="button"
												class="right-button02" value="发送" /></td>
										<td><input  type="reset"
											class="right-button02" value="重置" /></td>
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
							<TD width="50%" colspan="3">
							<div style="position:absolute; height:400px;width:50%; overflow:auto">
														<fieldset style="height:100%;">
															<legend>发送内容</legend>
															<table border="0" cellpadding="2" cellspacing="1"
																style="width:100%">
																<tr>
																	<td nowrap align="right" width="5%" >标题:</td>
																	<td ><input type="text"
																		id="addtitle" size="68"/></td>
																</tr>
																<tr>
																	<td width="5%" align="right" nowrap="nowrap">内容:</td>
																	<td ><textarea rows="20" cols="75" id="addcontent"></textarea> </td>
																</tr>
																
															</table>
															<br />
														</fieldset>
														</div>
													</TD>	
							<td>
							<div style="position:absolute; height:400px;width:50%; overflow:auto">
							<table width="95%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="20"><span class="newfont07">选择：<a
												href="javascript:" class="right-font08" onclick="selectAll('items');">全选</a>-<a
												href="javascript:" class="right-font08" onclick="unselectAll('items');">全不选</a>
										</span> 
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="10" align="center"
														style="font-size:16px">列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">选择</th>
													<th width="5%">序号</th>
													<th width="40%">姓名</th>
													<th width="50%">手机号</th>
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
													<td width="50%">共 <font class="right-text09" id="pageAllCount">1</font>
														页 | 第 <font class="right-text09" id="pageCurrentPage">1</font> 页</td>
													<td width="49%" align="right">[<a href="javascript:"
														class="right-font08" onclick="dataPage('start')">首页</a> | <a href="javascript:"
														class="right-font08" onclick="dataPage('up')">上一页</a> | <a href="javascript:"
														class="right-font08" onclick="dataPage('down')">下一页</a> | <a href="javascript:"
														class="right-font08" onclick="dataPage('end')">末页</a>] 转至：</td>
													<td width="1%"><table width="20" border="0"
															cellspacing="0" cellpadding="0">
															<tr>
																<td width="1%"><input name="textfield3" type="text"
																	class="right-textfield03" size="1" id="currentPage"/>
																</td>
																<td width="87%"><input name="Submit23222"
																	type="submit" class="right-button06" onclick="dataPage('currentPage');return false;" value="跳转"/> </td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</div>
							</td>
						</tr>
					</table>
					</td>
					</tr>
					</table>
		</form>
	</body>
</html>