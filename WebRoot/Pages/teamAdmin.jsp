<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>优惠审批管理</title>

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
	<script type="text/javascript" src="js/jquery1.8.js"></script>
	<script type="text/javascript" src="js/gogoShare.js"></script>
	<script type="text/javascript" src="js/teamAdmin.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/interface/page.js"></script>
	<script type="text/javascript" src="dwr/interface/gogoServiceImpl.js"></script>
	<script type="text/javascript" src="dwr/interface/secondBackstage.js"></script>
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
											value="删除所选" onclick="dataDel('TbTeam')"/>
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
													<th width="10%">学校名称</th>
													<th width="15%">优惠标题</th>
													<th width="25%">摘要</th>
													<th width="14%">状态</th>
													<th width="8%">市场价</th>
													<th width="8%">优惠价</th>
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
		<!--查看页面 start-->
		<div class="findDiv" id="findDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">查看信息</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>查看信息</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
											<tr>
												<td nowrap align="right" width="15%">学校:</td>
												<td width="35%"><span class="red"
													id="detailSchoolName">gogo1</span></td>
											</tr>
											<tr>
												<td width="25%" align="right" nowrap="nowrap">标题:</td>
												<td width="35%"><span class="red"
													id="detailTitle">321</span></td>
											</tr>
											<tr>
												<td nowrap="nowrap" align="right">市场价:</td>
												<td width="34%"><span class="red"
													id="detailPrice">星光幼儿园</span></td>
											</tr>
											<tr>
												<td align="right">优惠价:</td>
												<td width="34%"><span class="red"
													id="detailTeamPrice">管理员</span></td>
											</tr>
											<tr>
												<td align="right">现购数量:</td>
												<td width="34%"><span class="red"
													id="detailNowNumber">管理员</span></td>
											</tr>
											<tr>
												<td align="right">详情:</td>
												<td width="34%"><span class="red"
													id="detailDetail">管理员</span></td>
											</tr>
											<tr>
												<td align="right">摘要:</td>
												<td width="34%"><span class="red"
													id="detailSummary">管理员</span></td>
											</tr>
											<tr>
												<td align="right">优惠卷开始时间:</td>
												<td width="34%"><span class="red"
													id="detailStarttime">管理员</span></td>
											</tr>
											<tr>
												<td align="right">优惠券结束时间:</td>
												<td width="34%"><span class="red"
													id="detailExpiretime">管理员</span></td>
											</tr>
											<tr>
												<td align="right">开始时间:</td>
												<td width="34%"><span class="red"
													id="detailBegintime">添加时间</span>
												</td>
											</tr>
											<tr>
												<td align="right">结束时间:</td>
												<td width="34%"><span class="red"
													id="detailEndtime"></span></td>
											</tr>
											<tr>
												<td align="right">未使用退款:</td>
												<td width="34%"><span class="red"
													id="detailIsuserefund"></span></td>
											</tr>
											<tr>
												<td align="right">过期退款:</td>
												<td width="34%"><span class="red"
													id="detailIslaterefund"></span></td>
											</tr>
											<tr>
												<td align="right">已使用，未就读退款:</td>
												<td width="34%"><span class="red"
													id="detailIsuseunreadrefund"></span></td>
											</tr>
											<tr>
												<td align="right">已就读退款:</td>
												<td width="34%"><span class="red"
													id="detailIsreadrefund"></span></td>
											</tr>
											<tr>
												<td align="right">状态:</td>
												<td width="34%"><span class="red"
													id="detailStatus"></span></td>
											</tr>
											<tr>
												<td align="right">库存:</td>
												<td width="34%"><span class="red"
													id="detailInventory"></span></td>
											</tr>
											<tr>
												<td align="right">录入时间:</td>
												<td width="34%"><span class="red"
													id="detailTime"></span></td>
											</tr>
											<tr>
												<td align="right">备注:</td>
												<td width="34%"><span class="red"
													id="detailRemark"></span></td>
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
						type="button" name="Submit2" value="返回" class="button"
						onclick="cancel()" /></TD>
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
		<!--查看页面 End-->
		</form>
</body>
</html>
