<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>招聘信息添加</title>
<script type="text/javascript" src="js/util.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
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
	var position=$id("position").value;
	var require=KE.util.getData('require');
	if(position==""){
		alert("请输入职位名称！");
		return false;
	}
	if(require==""){
		alert("请输入职位要求！");
		return false;
	}
}
KE.show({
		  	id : 'require',
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
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>

</head>

<body class="ContentBody">
  <form action="saveRecruits.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >招聘信息添加页面</th>
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
				<legend>添加招聘信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr height="50">
					  	<td align="right">职位:</td>
					    <td><input type="text" class="text" name='info.title' value=""  style="width:154px" id="position"/><span class="red">*</span></td>
					    <td colspan="2">&nbsp;</td>
					     </tr>
					  <tr>
					  	<td align="right">职位要求:</td>
					    <td colspan="3">
					    	<textarea type="text" class="text" name="info.content" id="require" style="width:700px; height:300px;visibility:hidden;"></textarea>
					    </td>
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

