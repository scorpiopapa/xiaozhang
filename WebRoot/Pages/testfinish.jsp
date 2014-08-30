<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>个人练习情况</title>

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
	<script type="text/javascript" src="js/testfinish.js"></script>
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
											value="删除所选" onclick="dataDel('TbTestfinish')"/>
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="10" align="center"
														style="font-size:16px">个人完成情况列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">选择</th>
													<th width="10%">练习名称</th>
													<th width="10%">个人名称</th>
													<th width="15%">完成时间</th>
													<th width="10%">正确率</th>
													<th width="10%">是否完成</th>
													<th width="10%">是否备份</th>
													<th width="15%">注册时间</th>
													<th width="15%">信息更新</th>
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
		</form>
</body>
</html>
