<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>软件版本</title>

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
	<script charset="utf-8" src="editor/kindeditor.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/jquery1.8.js"></script>
	<script type="text/javascript" src="js/gogoShare.js"></script>
	<script type="text/javascript" src="js/sysconfig.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/interface/page.js"></script>
	<script type="text/javascript" src="dwr/interface/gogoServiceImpl.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script>
		KE.show({
		  	id : 'content1',
			imageUploadJson : 'upload_json.jsp',
			fileManagerJson : 'file_manager_json.jsp',
			allowFileManager : true,
			urlType : 'domain',// urlType可指定relative, absolute, domain，分别代表相对路径、绝对路径、带域名的完整URL。
			afterCreate : function(id) {
				KE.event.ctrl(document, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
				KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
			}
	});
		</script>
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
										<td height="20"> <input name="Submit2" type="button"
											class="right-button08" value="修改" onclick="showUpadteDiv()" />
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="10" align="center"
														style="font-size:16px">更多信息</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="10%" align="center" height="50">软件介绍</th>
													<th width="90%" align="center" height="50"><textarea id="softwareDetailsMore" cols="150" disabled="disabled"></textarea></th>
												</tr>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="10%" align="center" height="50">关于我们</th>
													<th width="90%" align="center" height="50"><textarea id="aboutMeMore" cols="150" disabled="disabled"></textarea></th>
												</tr>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="10%" align="center" height="50">软件介绍</th>
													<th width="90%" align="center" height="50"><textarea id="complainPhoneMore" cols="150" disabled="disabled"></textarea></th>
												</tr>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="10%" align="center" height="50">软件介绍</th>
													<th width="90%" align="center" height="50"><textarea id="ourPhoneMore" cols="150" disabled="disabled"></textarea></th>
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
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>修改信息</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
											<tr><td align="center">
												<select id="selectOne">
													<option value="softwareDetails">软件介绍</option>
													<option value="aboutMe">关于我们</option>
													<option value="complainPhone">投诉电话</option>
													<option value="ourPhone">联系我们</option>
												</select>
												<td>
												<td  align="left">
													<input type="button" name="Submit" value="保存"
															class="button" onclick="dataUpdate()" /> <input
															type="button" name="Submit2" value="返回" class="button"
															onclick="cancel()" />
														</td></tr>
										<tr>
											<td colspan="2" align="center">
											<textarea id="content1" name="news.newsContent" style="width:700px; height:300px;visibility:hidden;">
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
