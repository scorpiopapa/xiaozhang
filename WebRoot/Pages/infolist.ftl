<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>信息列表页面</title>
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
										<td height="20">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" align="center"
														style="font-size:16px">信息列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<th width="5%" align="center" height="30">信息列表</th>
												</tr>
													<tr bgcolor="#FFFFFF" align="center">
														<th height="90">
															<input type="button" value="首页图片" onclick="location.href='showAboutImage.action?infoType=7';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="广告图片" onclick="location.href='showAboutImage.action?infoType=8';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="选课报班图片" onclick="location.href='showAboutImage.action?infoType=10';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="招聘信息" onclick="location.href='showRecruits.action';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="合作学校" onclick="location.href='showSchoolUnion.action';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="公司简介" onclick="location.href='viewInformation.action?infoType=1';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="合作服务" onclick="location.href='viewInformation.action?infoType=2';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="软件介绍" onclick="location.href='viewInformation.action?infoType=3';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="投诉建议" onclick="location.href='viewInformation.action?infoType=4';"/>&nbsp;&nbsp;&nbsp;
															<input type="button" value="联系我们" onclick="location.href='viewInformation.action?infoType=5';"/>&nbsp;&nbsp;&nbsp;
														</th>
													</tr>
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
									&nbsp;
									</td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
