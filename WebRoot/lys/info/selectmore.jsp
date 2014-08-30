<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
								<td height="40" class="font42">
								<table width="100%" border="0" cellpadding="4"
									cellspacing="1" bgcolor="#464646" class="newfont03">
									<tr class="CTitle">
										<td height="22" colspan="10" align="center"
											style="font-size:16px">更多信息</td>
									</tr>
									<s:iterator value="listmore">
									<tr bgcolor="#EEEEEE">
										<td align="center" height="50"><s:property value="title" /></td>
										<td align="center" height="50"><textarea rows="4" cols="150" disabled="disabled"><s:property value="content" /></textarea>
										</td>
										<td><a href="moreaction!toupdateMore.action?moreId=<s:property value="id" />"><h3>修改</h3></a></td>
									</tr>
									
									
									</s:iterator>
								</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
</body>
</html>
