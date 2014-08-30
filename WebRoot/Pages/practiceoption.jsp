<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>练习选项</title>

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
	<script type="text/javascript" src="js/practiceoption.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/interface/page.js"></script>
	<script type="text/javascript" src="dwr/interface/gogoServiceImpl.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<form name="fom" id="fom" method="post" action="">
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
											value="删除所选" onclick="dataDel('TbPracticeoption')"/> 
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="10" align="center"
														style="font-size:16px">题目选项列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">选择</th>
													<th width="10%">练习题目</th>
													<th width="5%">选项标识</th>
													<th width="40%">选项内容</th>
													<th width="10%">是否备份</th>
													<th width="15%">注册时间</th>
													<th width="15%">信息更新</th>
													<th>操作</th>
												</tr>
												<tbody id="dataTable" bgcolor="#FFFFFF" align="center">
												</tbody>
											</table>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
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
					
		<%-- <!--新增页面 start-->
		<div class="addDiv" id="addDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">新增页面</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>添加练习选项</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">练习名称:</td>
											<td width="35%" colspan="2"><select
												id="optionAdd" ><option value="请选择">请选择</option></select><font class="red">*</font>
										</tr>
										<tr>
											<td nowrap align="right" >选项标识:</td>
											<td width="35%" colspan="2"><input id="optionLogoAdd"
												type="text" class="text" style="width:154px" value="" /> <font
												class="red">*</font></td>
										</tr>
										<tr>
											<td nowrap align="right" >选项内容:</td>
											<td width="35%" colspan="2"><input id="contentAdd"
												type="text" class="text" style="width:154px" value="" /> <font
												class="red">*</font></td>
										</tr>

										<tr>
											<td  align="right" nowrap="nowrap">发布日期:</td>
											<td  colspan="2"><input type="text"
												id="DateAdd" name='datevalue217'
												style="width:154px" value="" onclick="WdatePicker()"/><font class="red">*</font></td>
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
						onclick="dataAdd()" type="button" name="Submit" value="保存" class="button" />
						<input type="button" onclick="cancel()" value="返回" class="button" />
					</TD>
				</TR>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
			</TABLE>
			</td>
			</tr>
			</table>
		</div>
		<!--新增页面 End--> --%>
		
		<!--修改页面 start-->
		<div class="upadteDiv" id="upadteDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">修改题目</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>修改题目</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td nowrap align="right" width="15%">id:</td>
											<td width="35%"><span class="red"
												id="firstAttributeUpdate"></span></td>
										</tr>
										<tr>
											<td nowrap align="right" width="15%">选项标识:</td>
											<td width="35%"> <input type="text" id="secondAttributeUpdate" ></td>
										</tr>
										<tr>
											<td nowrap align="right" width="15%">选项内容:</td>
											<td width="35%"> <input type="text" id="thirdAttributeUpdate" ></td>
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
						 type="button" name="Submit" value="保存"
						class="button" onclick="dataUpdate()"/> <input type="button" name="Submit2"
						value="返回" class="button" onclick="cancel()" /></TD>
				</TR>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
			</TABLE>
			</td>
			</tr>
			</table>
		</div>
		<!--修改页面 End-->
		</form>
</body>
</html>
