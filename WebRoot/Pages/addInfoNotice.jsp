<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加信息公告</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css"
	type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">
function tishi()
{
  var a=confirm('数据库中保存有该人员基本信息，您可以修改或保留该信息。');
  if(a!=true)return false;
  window.open("冲突页.htm","","depended=0,alwaysRaised=1,width=800,height=400,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}

function $id(id){
	return document.getElementById(id);
}

function checkForm(){
	var headline=$id("headline").value;
	var infoContent=$id("infoContent").value;
	var images=$id("images").value.toLowerCase();
	var image1 =/^.+\.jpg$/;
	var image2 =/^.+\.gif$/;
	var image3 =/^.+\.png$/;
	var content = /^.{1,1000}$/;
	if(!content.test(headline)){
		alert("请输入公告标题！");
		return false;
	}
	if(images!=""&&!image1.test(images)&&!image2.test(images)&&!image3.test(images)){
		alert("图片格式只能是jpg，gif，或png格式！");
		return false;
	}
	if(infoContent.length>1000){
		alert("请输入公告内容且字符小于1000(含空格)！");
		return false;
	}
}

function check()
{
	$id("aa").style.display="";
}
</script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>

</head>

<body class="ContentBody">
	<form action="addInfoNoticeDo.action" method="post"
		enctype="multipart/form-data" name="form"
		onsubmit="return checkForm();">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">信息公告添加页面</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<tr>
								<td align="left"><input type="submit" name="Submit"
									value="保存" class="button" /> <input type="button"
									name="Submit2" value="返回" class="button"
									onclick="window.history.go(-1);" /></td>
							</tr>
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>添加信息公告</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
											<tr>
					  							<td align="center" colspan="4">注：上传图片不大于50KB</td>
					  						</tr>
											<tr height="50">
												<th width="45%" align="right" nowrap="nowrap">公告标题:</th>
												<td width="55%" align="left">
													<input class="text" name='infoNotice.headline' style="width:154px" id="headline"/>&nbsp;<span class="red">*</span>
												</td>
											</tr>
											<tr>
												<th align="right">公告图片:</th>
													<td width="55%" align="left">
													<input type="file" class="text" name='img' style="width:154px" id="images"/><span class="red">*</span>
												</td>
											</tr>
											<tr>
												<th align="left">公告内容:<span class="red">*</span></th>
													<td width="55%" align="right">
													&nbsp;
												</td>
											</tr>
											<tr>
													<td width="55%" colspan="2" align="center">公告内容:
													<textarea name="infoNotice.content" cols="100" rows="8" id="infoContent"></textarea>
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
						type="submit" value="保存" class="button" /> <input
						type="button" value="返回" class="button"
						onclick="window.history.go(-1);" />
					</TD>
				</TR>
			</TABLE>


			</td>
			</tr>



			</table>

		</div>
	</form>
</body>
</html>

