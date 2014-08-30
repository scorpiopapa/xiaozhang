<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		
		<title>老师管理</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script type="text/javascript" src="dwr/interface/page.js"></script>
		
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" rev="stylesheet" href="css/style.css"
			type="text/css" media="all" />
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
		<script type="text/javascript">
			function  text() {
				if(file()){
	  				document.myform.submit();
					}
			}
		
			function file(){
				var file = $('#file')[0];
				var a = file.value;
				if(a.length==0){
					alert("请选择文件");
					return false;
				}
				return true;
			}
		
function importTemplate(){
	window.location.href="util/teacherImport.xls";
}
		</script>
		
	</head>
	<body>
	<center>
	<s:form action="addinfo!addteacherInfo.action" name="myform" method="post" theme="simple" enctype="multipart/form-data">
		<table>
   				<tr>
					<td>老师信息:</td>
					<td colspan="5"><input id="file" type="file" name="file"  style="width:300px;height:25px;background:white;"/><font class="red" style="width:50px;height:25px;background:white;">*</font></td>
				</tr>
				<tr>
				<td><input type="button" value="老师导入模板下载" onclick="importTemplate()" /></td>
					<td colspan="5" align="center" height="50px">
					
					<input type="button" value="添加" onclick="text()" style="width:50px;height:25px;background:white;">
					<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
					</td>
				</tr>
   			</table>
	</s:form>
	</center>
</body>
</html>
