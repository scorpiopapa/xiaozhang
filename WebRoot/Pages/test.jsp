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
<title>练习库管理</title>

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
<script type="text/javascript" src="js/test.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/interface/page.js"></script>
<script type="text/javascript" src="dwr/interface/gogoServiceImpl.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body onload="result()">
	<form name="fom" id="fom" method="post" action="addtest.action"
		enctype="multipart/form-data">
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
									<td width="100" align="right">班级:</td>
									<td nowrap="nowrap" width="200"><select id="teacherCourseFind"></select>
									</td>
									<td align="left"><input type="button" class="right-button02"
										value="查 询" onclick="findCourse()" /></td>
								</table></td>
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
										<td height="20"><span class="newfont07">选择：<a
												href="javascript:" class="right-font08"
												onclick="selectAll('items');">全选</a>-<a href="javascript:"
												class="right-font08" onclick="unselectAll('items');">全不选</a>
										</span> <input name="Submit" type="button" class="right-button08"
											value="删除所选" onclick="dataDel('TbTest')" /> <input
											name="Submit2" type="button" class="right-button08"
											value="添加" onclick="showAddDiv1()" /></td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="10" align="center"
														style="font-size:16px">练习列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">选择</th>
													<th width="10%">课程名称</th>
													<th width="15%">练习名称</th>
													<th width="10%">发布日期</th>
													<th width="10%">是否备份</th>
													<th width="15%">注册时间</th>
													<th width="15%">信息更新</th>
													<th>操作</th>
												</tr>
												<tbody id="dataTable" bgcolor="#FFFFFF" align="center">
												</tbody>
											</table></td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="6"><img src="images/spacer.gif" width="1"
											height="1" /></td>
									</tr>
									<tr>
										<td height="33"><table width="100%" border="0"
												align="center" cellpadding="0" cellspacing="0"
												class="right-font08">
												<tr>
													<td width="50%">共 <font class="right-text09"
														id="pageAllCount"></font> 页 | 第 <font
														class="right-text09" id="pageCurrentPage"></font> 页</td>
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
																	type="button" class="right-button06"
																	onclick="dataPage('currentPage');return false;" /></td>
															</tr>
														</table></td>
												</tr>
											</table></td>
									</tr>
								</table></td>
						</tr>
					</table> <!--新增页面 start-->
					<div class="addDiv" id="addDiv" style="display:none;">
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
												<legend>添加题库</legend>
												<table border="0" cellpadding="2" cellspacing="1"
													style="width:100%">
													<tr>
														<td width="15%" nowrap="nowrap" class="hid" align="right">班级:</td>
														<td width="35%" colspan="2"><select id="addClassId"><option
																	value="请选择">请选择</option>
														</select><font class="red">*</font>
													</tr>
													<tr>
														<td nowrap align="right">练习名称:</td>
														<td width="35%" colspan="2"><input id="testAdd"
															type="text" class="text" style="width:154px" value="" />
															<font class="red">*</font>
														</td>
													</tr>
												</table>
												<br />
											</fieldset></TD>
										</TR>
									</TABLE></td>
							</tr>
							<TR>
								<TD colspan="2" align="center" height="50px"><input
									onclick="dataAdd()" type="button" name="Submit" value="保存"
									class="button" /> <input type="button" onclick="cancel()"
									value="返回" class="button" /></TD>
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
				</td>
			</tr>
		</table>
		</div>
		<!--新增页面 End-->

		<!--修改页面 start-->
		<div class="upadteDiv" id="upadteDiv" style="display:none;">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">修改题库</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>修改题库</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td nowrap align="right" width="15%">id:</td>
											<td width="35%"><span class="red"
												id="firstAttributeUpdate"></span>
											</td>
										</tr>
										<tr>
											<td nowrap align="right" width="15%">题库名称:</td>
											<td width="35%"><input type="text" id="testNameUpdate">
											</td>
										</tr>
									</table>
									<br />
								</fieldset></TD>
							</TR>
						</TABLE></td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="button" name="Submit" value="保存" class="button"
						onclick="dataUpdate()" /> <input type="button" name="Submit2"
						value="返回" class="button" onclick="cancel()" />
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
			</td>
			</tr>
			</table>
		</div>
		<!--修改页面 End-->

		<!--查看页面 start-->
		<div class="findDiv" id="findDiv" style="display:none;">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">导入练习内容</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>导入练习内容</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">

										<tr>
											<td nowrap align="right" width="15%">练习Id:</td>
											<td width="35%"><span class="red"
												id="firstAttributeFind"></span><input type="hidden"
												name="tbTest.testId" id="testId" value="">
											</td>
										</tr>
										<tr>
											<td nowrap align="right" width="15%">班级名称:</td>
											<td width="35%"><span class="red"
												id="secondAttributeFind"></span>
											</td>
										</tr>
										<tr>
											<td width="16%" align="right" nowrap="nowrap">练习名称:</td>
											<td width="34%"><span class="red"
												id="thirdAttributeFind"></span>
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right">录入日期:</td>
											<td width="34%"><span class="red"
												id="fifthAttributeFind"></span>
											</td>
										</tr>
										<tr>
											<td align="right">是否备份:</td>
											<td width="34%"><span class="red"
												id="sixthAttributeFind"></span>
											</td>
										</tr>
										<tr>
											<td align="right">录入时间:</td>
											<td width="34%"><span class="red"
												id="seventhAttributeFind"></span>
											</td>
										</tr>
										<tr>
											<td align="right">更新时间:</td>
											<td width="34%"><span class="red"
												id="eighthAttributeFind"></span></td>
										</tr>
										<td nowrap align="right" colspan="2">&nbsp;&nbsp;</td>
										</tr>
									</table>
									<br />
								</fieldset></TD>
							</TR>
						</TABLE>
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>导入练习题</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td nowrap align="right" width="15%">导入练习题:</td>
											<td width="35%"><input type="file" name="file" id="testFile">
											</td>
											
										</tr>
										<input type="hidden" id="result" value="${result}">
									</table>
									<br />
								</fieldset></TD>
							</TR>
						</TABLE></td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px">
					<input type="button" value="在线练习导入模板下载" onclick="importTemplate()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input
						type="button" name="Submit2" value="导入" class="button"
						onclick="leadIn()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="Submit2"
						value="返回" class="button" onclick="cancel()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</TD>
				</TR>
			</td>
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
		
		<!--查看页面 Start-->
		<div class="findDiv3" id="findDiv3" style="display:none;">
		<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">题目内容</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>题目内容</legend>
									<table border="1" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="0" cellpadding="4"
													cellspacing="1" bgcolor="#464646" class="newfont03">
													<tr class="CTitle">
														<td height="22" colspan="10" align="center"
															style="font-size:16px">选项列表</td>
													</tr>
													<tr bgcolor="#EEEEEE">
														<th width="20%">选项</th>
														<th width="80%">内容</th>
													</tr>
													<tbody id="dataTable3" bgcolor="#FFFFFF" align="center">
													</tbody>
												</table></td>
										</tr>
									</table>
									<br />
								</fieldset></TD>
							</TR>
						</TABLE></td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="button" name="Submit2" value="返回" class="button"
						onclick="backfindDiv3()" />
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
			</TABLE>
		</div>
		<!--查看页面 End-->

		<!--查看页面 start-->
		<div class="findDiv2" id="findDiv2" style="display:none;">
		<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">题目列表</th>
				</tr>
				<tr>
					<td class="CPanel">
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
				<RT>
				<TD width="100%">
					<fieldset style="height:100%;">
						<legend>题目列表</legend>
						<table id="subtree2" style="DISPLAY: " width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<tr>
								<td><table width="95%" border="0" align="center"
										cellpadding="0" cellspacing="0">
										<tr>
											<td height="20"><span class="newfont07">选择：<a
													href="javascript:" class="right-font08"
													onclick="selectAll('items1');">全选</a>-<a href="javascript:"
													class="right-font08" onclick="unselectAll('items1');">全不选</a>
											</span> <input name="Submit" type="button" class="right-button08"
												value="删除所选" onclick="dataDel('TbPracticetitle')" /></td>
										</tr>
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="0" cellpadding="4"
													cellspacing="1" bgcolor="#464646" class="newfont03">
													<tr class="CTitle">
														<td height="22" colspan="10" align="center"
															style="font-size:16px">题目列表</td>
													</tr>
													<tr bgcolor="#EEEEEE">
														<th width="5%" align="center" height="30">选择</th>
														<th width="15%">练习名称</th>
														<th width="20%">练习题目</th>
														<th width="10%">标准答案</th>
														<th width="10%">是否备份</th>
														<th width="20%">注册时间</th>
														<th width="20%">信息更新</th>
													</tr>
													<tbody id="dataTable1" bgcolor="#FFFFFF" align="center">
													</tbody>
												</table></td>
										</tr>
									</table>
									<table width="95%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="6"><img src="images/spacer.gif" width="1"
												height="1" /></td>
										</tr>
										<tr>
											<td height="33"><table width="100%" border="0"
													align="center" cellpadding="0" cellspacing="0"
													class="right-font08">
													<tr>
														<td width="50%">共 <font class="right-text09"
															id="pageAllCount1"></font> 页 | 第 <font
															class="right-text09" id="pageCurrentPage1"></font> 页</td>
														<td width="49%" align="right">[<a href="javascript:"
															class="right-font08" onclick="dataPage1('start')">首页</a>
															| <a href="javascript:" class="right-font08"
															onclick="dataPage1('up')">上一页</a> | <a href="javascript:"
															class="right-font08" onclick="dataPage1('down')">下一页</a>
															| <a href="javascript:" class="right-font08"
															onclick="dataPage1('end')">末页</a>] 转至：</td>
														<td width="1%"><table width="20" border="0"
																cellspacing="0" cellpadding="0">
																<tr>
																	<td width="1%"><input name="textfield3"
																		type="text" class="right-textfield03" size="1"
																		id="currentPage1" /></td>
																	<td width="87%"><input name="Submit23222"
																		type="button" class="right-button06"
																		onclick="dataPage1('currentPage')" /></td>
																</tr>
															</table></td>
													</tr>
												</table></td>
										</tr>
									</table></td>
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
						</table>
					</fieldset></TD>
				</RT>
			</table>
			</td>
			</tr>
			</table>
		</div>
		<!--查看页面 End-->
		<!--查看页面 start-->
		<div class="findDiv4" id="findDiv4" style="display:none;">
		<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">学生作业情况</th>
				</tr>
				<tr>
					<td class="CPanel">
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
				<RT>
				<TD width="100%">
					<fieldset style="height:100%;">
						<legend>学生作业完成情况</legend>
						<table id="subtree4" style="DISPLAY: " width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<tr>
								<td><table width="95%" border="0" align="center"
										cellpadding="0" cellspacing="0">
										<tr>
											<td height="20"><span class="newfont07">选择：<a
													href="javascript:" class="right-font08"
													onclick="selectAll('items4');">全选</a>-<a href="javascript:"
													class="right-font08" onclick="unselectAll('items4');">全不选</a>
											</span> <input name="Submit" type="button" class="right-button08"
												value="删除所选" onclick="dataDel('TbPracticetitle')" /></td>
										</tr>
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="0" cellpadding="4"
													cellspacing="1" bgcolor="#464646" class="newfont03">
													<tr class="CTitle">
														<td height="22" colspan="10" align="center"
															style="font-size:16px">作业情况表</td>
													</tr>
													<tr bgcolor="#EEEEEE">
														<th width="5%" align="center" height="30">选择</th>
														<th width="10%">练习名称</th>
														<th width="10%">个人名称</th>
														<th width="15%">完成时间</th>
														<th width="10%">个人答案</th>
														<th width="10%">是否正确</th>
														<th width="10%">是否备份</th>
														<th width="15%">注册时间</th>
														<th width="15%">信息更新</th>
													</tr>
													<tbody id="dataTable4" bgcolor="#FFFFFF" align="center">
													</tbody>
												</table></td>
										</tr>
									</table>
									<table width="95%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="6"><img src="images/spacer.gif" width="1"
												height="1" /></td>
										</tr>
										<tr>
											<td height="33"><table width="100%" border="0"
													align="center" cellpadding="0" cellspacing="0"
													class="right-font08">
													<tr>
														<td width="50%">共 <font class="right-text09"
															id="pageAllCount4"></font> 页 | 第 <font
															class="right-text09" id="pageCurrentPage4"></font> 页</td>
														<td width="49%" align="right">[<a href="javascript:"
															class="right-font08" onclick="dataPage4('start')">首页</a>
															| <a href="javascript:" class="right-font08"
															onclick="dataPage4('up')">上一页</a> | <a href="javascript:"
															class="right-font08" onclick="dataPage4('down')">下一页</a>
															| <a href="javascript:" class="right-font08"
															onclick="dataPage4('end')">末页</a>] 转至：</td>
														<td width="1%"><table width="20" border="0"
																cellspacing="0" cellpadding="0">
																<tr>
																	<td width="1%"><input name="textfield3"
																		type="text" class="right-textfield03" size="1"
																		id="currentPage4" /></td>
																	<td width="87%"><input name="Submit23222"
																		type="button" class="right-button06"
																		onclick="dataPage4('currentPage')" /></td>
																</tr>
															</table></td>
													</tr>
												</table></td>
										</tr>
									</table></td>
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
						</table>
					</fieldset></TD>
				</RT>
			</table>
			</td>
			</tr>
			</table>
		</div>
		<!--查看页面 End-->
		<!--查看页面 start-->
		<div class="findDiv5" id="findDiv5" style="display:none;">
		<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">学生作业情况</th>
				</tr>
				<tr>
					<td class="CPanel">
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
				<RT>
				<TD width="100%">
					<fieldset style="height:100%;">
						<legend>学生作业完成情况</legend>
						<table id="subtree5" style="DISPLAY: " width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<tr>
								<td><table width="95%" border="0" align="center"
										cellpadding="0" cellspacing="0">
										<tr>
											<td height="20"><span class="newfont07">选择：<a
													href="javascript:" class="right-font08"
													onclick="selectAll('items4');">全选</a>-<a href="javascript:"
													class="right-font08" onclick="unselectAll('items4');">全不选</a>
											</span> <input name="Submit" type="button" class="right-button08"
												value="删除所选" onclick="dataDel('TbPracticetitle')" /></td>
										</tr>
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="0" cellpadding="4"
													cellspacing="1" bgcolor="#464646" class="newfont03">
													<tr class="CTitle">
														<td height="22" colspan="10" align="center"
															style="font-size:16px">作业情况表</td>
													</tr>
													<tr bgcolor="#EEEEEE">
														<th width="5%" align="center" height="30">选择</th>
														<th width="10%">练习名称</th>
														<th width="10%">个人名称</th>
														<th width="15%">题目</th>
														<th width="10%">正确率</th>
														<th width="10%">是否完成</th>
														<th width="10%">是否备份</th>
														<th width="15%">注册时间</th>
														<th width="15%">信息更新</th>
													</tr>
													<tbody id="dataTable5" bgcolor="#FFFFFF" align="center">
													</tbody>
												</table></td>
										</tr>
									</table>
									<table width="95%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="6"><img src="images/spacer.gif" width="1"
												height="1" /></td>
										</tr>
										<tr>
											<td height="33"><table width="100%" border="0"
													align="center" cellpadding="0" cellspacing="0"
													class="right-font08">
													<tr>
														<td width="50%">共 <font class="right-text09"
															id="pageAllCount5"></font> 页 | 第 <font
															class="right-text09" id="pageCurrentPage5"></font> 页</td>
														<td width="49%" align="right">[<a href="javascript:"
															class="right-font08" onclick="dataPage5('start')">首页</a>
															| <a href="javascript:" class="right-font08"
															onclick="dataPage5('up')">上一页</a> | <a href="javascript:"
															class="right-font08" onclick="dataPage5('down')">下一页</a>
															| <a href="javascript:" class="right-font08"
															onclick="dataPage5('end')">末页</a>] 转至：</td>
														<td width="1%"><table width="20" border="0"
																cellspacing="0" cellpadding="0">
																<tr>
																	<td width="1%"><input name="textfield3"
																		type="text" class="right-textfield03" size="1"
																		id="currentPage5" /></td>
																	<td width="87%"><input name="Submit23222"
																		type="button" class="right-button06"
																		onclick="dataPage5('currentPage')" /></td>
																</tr>
															</table></td>
													</tr>
												</table></td>
										</tr>
									</table></td>
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
						</table>
					</fieldset></TD>
				</RT>
			</table>
			</td>
			</tr>
			</table>
		</div>
		<!--查看页面 End-->

	</form>
</body>
</html>
