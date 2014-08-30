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
	KE.show({
		  	id : 'content2',
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
		KE.show({
			  	id : 'content3',
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
		KE.show({
			  	id : 'content4',
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
	<SCRIPT type="text/javascript">
	function div1() {
		alert("");
			$("#div1").show();
			$("#div2").hide();
			$("#div3").hide();
			$("#div4").hide();
		}
	
	function div2() {
			$("#div2").show();
			$("#div1").hide();
			$("#div3").hide();
			$("#div4").hide();
		}
	function div3() {
			$("#div3").show();
			$("#div1").hide();
			$("#div2").hide();
			$("#div4").hide();
		}
	function div4() {
			$("#div4").show();
			$("#div1").hide();
			$("#div2").hide();
			$("#div3").hide();
		}
	</SCRIPT>
</head>

<body>
	<s:form action="moreaction!updateMore.action" name="myform" method="post" theme="simple">
		
		<!--修改页面 start-->
		
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
										<s:hidden name="tbmore.id"></s:hidden>
											<tr><td align="center">
<!--												<select id="selectOne">-->
<!--													<option value="softwareDetails">软件介绍</option>-->
<!--													<option value="aboutMe" ondblclick="div2()">关于我们</option>-->
<!--													<option value="complainPhone" onclick="div3()">投诉电话</option>-->
<!--													<option value="ourPhone" onclick="div4()">联系我们</option>-->
<!--												</select>-->
<!--												<input type="button" value="软件介绍" width="20" onclick="div1()" style="width:50px;height:25px;background:white;"/>-->
<!--												<input type="button" value="关于我们" width="20" onclick="div2()" style="width:50px;height:25px;background:white;"/>-->
<!--												<input type="button" value="投诉电话" width="20" onclick="div3()" style="width:50px;height:25px;background:white;"/>-->
<!--												<input type="button" value="联系我们" width="20" onclick="div4()" style="width:50px;height:25px;background:white;"/>-->
												<td  align="left">
													<s:submit name="myform" value="修改" style="width:50px;height:25px;background:white;"></s:submit>
													<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
												</td></tr>
										<tr>
										<td colspan="2" align="center">
											<s:textfield  name="tbmore.title" readonly="true"></s:textfield>
											<s:textarea id="content1" name="tbmore.content" style="width:700px; height:300px;visibility:hidden;"/>
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
	
		<!--修改页面 End-->
		</s:form>
</body>
</html>
