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
<title>添加学生</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
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
function importStudent(){
if (confirm("是否确认导入"))  { 
		if(document.getElementById("file").value!="")
		document.fom.submit();
		else
		alert("导入文件不能为空！")
		}  
}
function importTemplate(){
	window.location.href="util/studentImport.xls";
}
function checkForm(){
	var file=$id("file").value.toLowerCase();
	var file1 =/^.+\.xls$/;
	if(!file1.test(file)){
		alert("导入文件为xls格式(excel)！");
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
  <form action="importStudenttoClassDo.action" name="fom" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >班级添加学生页面</th>
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
				<legend>班级添加学生</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td align="right">所属总校:</td>
					    <td>
	                       	<input class="text" style="width:154px" value="${schools.schoolName }" readonly/>
					    	<input type="hidden" name="schoolId" value="${schools.schoolId }"/>
                        </td>
					    <td width="16%" align="right" nowrap="nowrap">分校:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" value="${branchs.braschName }" readonly/>
					    	<input type="hidden" name="branchId" value="${branchs.branchSchoolId }"/>
					    </td>
					  </tr>
					    <tr height="40">
					    <td align="right">班级:</td>
					    <td><input type="text" class="text" value="${courses.courseName }"style="width:154px" readonly/>
								<input type="hidden" name="course.courseId" value="${courses.courseId }"/>
							</td>
                        <td align="right">学生:</td>
					    <td>
					    	<input type="file" class="text" id="file" name="file" style="width:154px"/>
					    	<span class="red">*</span>
                        </td>
					    </tr>
					    
					  </table>
			  <br />
				</fieldset>			</TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr><TR>
			<TD colspan="2" align="center" height="50px">
			<input type="button" value="学生家长导入模板下载" onclick="importTemplate()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<input type="button" name="Submit" value="导入" class="button" onclick="importStudent()"/>　
			
			<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>
</form>
</body>
</html>

