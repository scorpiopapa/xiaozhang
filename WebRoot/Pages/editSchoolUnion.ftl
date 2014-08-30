<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合作学校信息编辑</title>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
<script language="JavaScript" type="text/javascript">

function $id(id){
	var $i= document.getElementById(id);
	if($i==undefined){
		alert("找不到id："+id);
		return false;
	}
	return $i;
}

function checkForm(){
	var schoolname=$id("schoolname").value;
	if(schoolname==""){
		alert("请输入学校名称！");
		return false;
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
  <form action="updateSchoolUnion.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >合作学校信息编辑页面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td align="left">
				<input type="submit" name="Submit" value=" 保存" class="button"/>　			
				<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>			</td>
		</tr>
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>编辑合作学校信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr height="50">
					  	<td align="right">职位:</td>
					    <td>
					    	<input type="text" class="text" name='info.title' value="${detail.title?if_exists }"  style="width:154px" id="schoolname"/><span class="red">*</span>
					    	<input type="hidden" name="info.infoid" value="${detail.infoid?if_exists }" />
					    	<input type="hidden" name="info.infotype" value="${detail.infotype?if_exists }" />
					    </td>
					    <td colspan="2">&nbsp;</td>
					  </tr>
					  </table>
			  <br />
				</fieldset>
				</TD>
			</TR>
		</TABLE>
	
	
	 </td>
  </tr><TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value=" 保存 " class="button"/>　
			
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

