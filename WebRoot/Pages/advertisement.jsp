<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>首页广告管理</title>

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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
				'countPerPage':countPerPage
			};
		secondBackstage.findTbAdvertisementPage(page1,pageCallBack);
		}
	//分页的回调函数
	function pageCallBack(data){
			 var num=(data.currentPage-1)*countPerPage;
			 pageSelect(data);
			 dataIsNull(data);
			 DWRUtil.addRows(
			 "dataTable",//tbody的ID
			 data.currentList,
			 [
			 function(obj){return '<input type="checkbox" name="items" value="'+obj.id+'"/>';},
			 function(obj){num=parseInt(num)+1;
				return  num;},
			 function(obj){
					return "<img src='"+obj.url+"'  width='240px' height='160px'/>";},
			 function(obj){
				 return obj.hyperlink;},
			 function(obj){
				 if(obj.type==0)
				 	return "超链接";
				 else if(obj.type==1)
				 return "课程";
				 else if(obj.type==2)
				 return "优惠";
				 else if(obj.type==3)
				 return "总校";
				 else if(obj.type==4)
				 return "分校";
				 },
				 function(obj){
					 return '<input type="button" value="编辑" name="update" onClick="dataUpdatePage('
						+ obj.id +')"/>';
					
					 }
			 ],
			 {
			 escapeHtml:false
			 }
			 );
		}
	function saveData(){
		$("#action_flag").val("saveAdvertisement");
		var saveHyperlink=$("#saveHyperlink").val();
		$("#hyperlink").val(saveHyperlink);
		var saveType=$("#saveType").val();
		$("#photoType").val(saveType);
		document.fom.action="servlet/GogoServlet";
		document.fom.submit();
	}
	var id;
	function dataUpdatePage(data){
		id=data;
		secondBackstage.findDetailTbAdvertisement(data,dataUpdateCallBack);
	}
	function dataUpdateCallBack(data){
		$("#updatePhoto").empty();
		$("#updatePhoto").append(("<img src='"+data.url+"'  width='240px' height='160px'/>"));
		$("#updateHyperlink").val(data.hyperlink);
		$("#updateType").empty();
		if(data.type==0)
		$("#updateType").append("<option value='0' selected='selected'>超链接</option>");
		else
		$("#updateType").append("<option value='0'>超链接</option>");
		if(data.type==1)
		$("#updateType").append("<option value='1' selected='selected'>课程</option>");
		else
		$("#updateType").append("<option value='1'>课程</option>");
		if(data.type==2)
		$("#updateType").append("<option value='2' selected='selected'>优惠</option>");
		else
		$("#updateType").append("<option value='2'>优惠</option>");
		if(data.type==3)
		$("#updateType").append("<option value='3' selected='selected'>总校</option>");
		else
		$("#updateType").append("<option value='3'>总校</option>");
		if(data.type==4)
		$("#updateType").append("<option value='4' selected='selected'>分校</option>");
		else
		$("#updateType").append("<option value='4'>分校</option>");
		showUpadteDiv();
	}
	function updateData(){
		var data={
				"id":id,
				"hyperlink":$("#updateHyperlink").val(),
				"type":$("#updateType").val()
		};
		secondBackstage.updateTbAdvertisement(data,callBack);
	}
	</script>
</head>

<body>
	<form name="fom" id="fom" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" value="${loginAdmin.adminId}" id="adminId">
		<input type="hidden" value="${loginAdmin.tbSchool.schoolId}"
			id="adminSchoolId"> <input type="hidden"
			value="${loginAdmin.tbBranchschool.branchSchoolId}"
			id="adminBranchSchoolId"> <input type="hidden"
			value="${loginAdmin.adminRoot}" id="adminRoot"> <input
			type="hidden" value="${loginAdmin.isValid}" id="userinfoId">
			<input type="hidden" name="action_flag" id="action_flag">
			<input type="hidden" name="hyperlink" id="hyperlink">
			<input type="hidden" name="photoType" id="photoType">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="images/nav04.gif">

								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
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
							<td><table width="95%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="20"><span class="newfont07">选择：<a
												href="javascript:" class="right-font08" onclick="selectAll('items');">全选</a>-<a
												href="javascript:" class="right-font08" onclick="unselectAll('items');">全不选</a>
										</span> <input name="Submit" type="button" class="right-button08"
											value="删除所选" onclick="dataDel('TbAdvertisement')"/>
											<input onclick="showAddDiv()" type="button" class="right-button08"
											value="添加信息" />
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
													<th width="35%">图片</th>
													<th width="30%">链接路径</th>
													<th width="10%">链接类型</th>
													<th width="15%">操作</th>
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
													<td width="50%">共 <font class="right-text09" id="pageAllCount">5</font>
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
							</td>
						</tr>
					</table>
					</td></tr></table>
		<!--新增页面 start-->
		<div class="addDiv" id="addDiv">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">新增信息</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%;">
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>添加信息</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
											<tr>
												<td width="25%" align="right" nowrap="nowrap">图片:</td>
												<td width="35%"> 
												<input type="file" id="savePhoto" name="savePhoto">
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">链接路径:</td>
												<td width="34%"><input type="text"
													id="saveHyperlink" /></td>
											</tr>
											<tr>
												<td align="right">链接类型:</td>
												<td width="34%"><select id="saveType">
												<option value="0">超链接</option>
												<option value="1">课程</option>
												<option value="2">优惠</option>
												<option value="3">总校</option>
												<option value="4">分校</option>
												</select>
											</td>
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
						onclick="saveData()" type="button" name="Submit" value="保存" class="button" />
						<input type="button" onclick="cancel()" value="返回" class="button" />
					</TD>
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
		<!--新增页面 End-->
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
												<td nowrap="nowrap" align="right">图片:</td>
												<td width="60%" id="updatePhoto">
													</td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">链接路径:</td>
												<td width="60%"><input type="text"
													id="updateHyperlink" /></td>
											</tr>
											<tr>
												<td align="right">链接类型:</td>
												<td width="60%"><select id="updateType">
												<option value="0">超链接</option>
												<option value="1">课程</option>
												<option value="2">优惠</option>
												<option value="3">总校</option>
												<option value="4">分校</option>
												</select>
											</td>
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
