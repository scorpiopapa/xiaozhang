<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加城市</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />


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
	var cityName=$id("cityName").value;
	var spell=$id("spell").value;
	
	
	if(!/^.{1,10}$/.test(cityName)){
		alert("请输入城市名称（小于10个字符）！");
		return false;
	}
	if(!/^.{1,10}$/.test(spell)){
		alert("请输入城市拼音！");
		return false;
	}
}

function getArea(city) {
			var url = 'getTownJson.action';
			var params = 'cityId=' + city;
			new Ajax.Request(url, {
				method : 'POST',
				parameters : params,
				onFailure : showError,
				onComplete : processLoginResponse
			})
			function processLoginResponse(response) {
				var objs = eval("(" + response.responseText + ")");
				var otions = $id("area");
				var count = otions.options.length;
				for ( var j = 0; j < count; j++) {
					otions.remove(0);
				}
				if(objs.town.length==0){
					var selectObj = $id("area");
					selectObj.options[0] = new Option("==请选择==", "");
				}else{
				
					for ( var i = 0; i < objs.town.length; i++) {
						var selectObj = $id("area");
						selectObj.options[selectObj.length] = new Option(
								objs.town[i].townName, objs.town[i].townId);
					}
				}
			}
			function showError(response) {
				alert("系统出错了");
				return;
			}
	}
</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>

</head>

<body class="ContentBody">
  <form action="addCityDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >城市添加页面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td align="left">
				<input type="submit" name="Submit" value="保存" class="button"/>　			
				<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>			</td>
		</tr>
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>添加城市</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr height="40">
					    <td width="16%" align="right" nowrap="nowrap">城市名称:</td>
					    <td width="34%">
					    	<input class="text" name='citys.cityName' style="width:154px" id="cityName"/>&nbsp;<span class="red">*</span>
					    </td>
					  	<td>&nbsp;</td>
					  	<td>&nbsp;</td>
					  </tr>
					  <tr height="40">
					    <td width="45%" align="right" nowrap="nowrap">城市拼音:</td>
					    <td width="44%">
					    	<input class="text" name='citys.spell' style="width:154px" id="spell"/>&nbsp;<span class="red">*</span>
					    </td>
					  	<td>&nbsp;</td>
					  	<td>&nbsp;</td>
					  </tr>
					  </table>
			  <br />
				</fieldset></TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr><TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value="保存" class="button"/>　
			
			<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/></TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>
</form>
</body>
</html>

