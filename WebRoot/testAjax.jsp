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
<title>定时调用ajax</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/testajax.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">
function tishi()
{
  var a=confirm('数据库中保存有该人员基本信息，您可以修改或保留该信息。');
  if(a!=true)return false;
  window.open("冲突页.htm","","depended=0,alwaysRaised=1,width=800,height=400,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}

</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>

</head>

<body class="ContentBody" onload="invokeAjax();invokeAjax1();invokeAjax2();">
  <form action="addSchoolDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >定时调用测试接口页面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>接口定时调用测试</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					  	<td align="center" colspan="4" id="status">&nbsp;</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">次数:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" id="invoketime" readonly/>&nbsp;<span class="red">*</span>
					    </td>
					    <td nowrap="nowrap" align="right">调用的接口:</td>
					    <td><input class="text" value="找适合" style="width:154px" id="schoolMinName" readonly/>&nbsp;</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">分校:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" id="content1" readonly/>&nbsp;<span class="red">*</span>
					    </td>
					    <td nowrap="nowrap" align="right">班级:</td>
					    <td><input class="text" value="President" style="width:154px" id="content2" readonly/>&nbsp;</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">次数:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" id="invoketime1" readonly/>&nbsp;<span class="red">*</span> 
					    </td>
					    <td nowrap="nowrap" align="right">调用的接口:</td>
					    <td><input class="text"  value="全校学生" style="width:154px" id="schoolMinName" readonly/>&nbsp;</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">学生:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" id="content3" readonly/>&nbsp;<span class="red">*</span> 
					    </td>
					    <td nowrap="nowrap" align="right">家长:</td>
					    <td><input class="text" style="width:154px" id="content4" readonly/>&nbsp;</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">次数:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" id="invoketime2" readonly/>&nbsp;<span class="red">*</span> 
					    </td>
					    <td nowrap="nowrap" align="right">调用的接口:</td>
					    <td><input class="text"  value="学生到离校" style="width:154px" id="schoolMinName" readonly/>&nbsp;</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">学生id:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" id="content5" readonly/>&nbsp;<span class="red">*</span> 
					    </td>
					    <td nowrap="nowrap" align="right">学生:</td>
					    <td><input class="text" style="width:154px" id="content6" readonly/>&nbsp;</td>
					  </tr>
					  </table>
			  <br />
				</fieldset>			</TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>
</form>
</body>
</html>

