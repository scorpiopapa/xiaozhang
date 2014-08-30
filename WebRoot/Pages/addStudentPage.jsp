<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>单条添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery1.8.js"></script>
	<script type="text/javascript" src="js/gogoShare.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/interface/page.js"></script>
	<script type="text/javascript" src="dwr/interface/gogoServiceImpl.js"></script>
	<script type="text/javascript">
	function dataAdd(){
	var courseId=$("#teacherToClass").val();
	var data={
		'userinfoName':$("#studentName").val(),
		'userinfoSex':$("#studentSex").val(),
		'userinfoBirthday':$("#studentBrithday").val(),
		'studentSchool':$("#studentSchool").val(),
		'userinfoEmail':$("#studentMailbox").val()+"@qq.com",
		'userinfoQQ':$("#studentMailbox").val(),
		'studentClass':$("#studentClass").val(),
		'userinfoPhone':$("#studentPhone").val()
	};
	var data1={
		'userinfoName':$("#parentName").val(),
		'userinfoSex':$("#parentSex").val(),
		'userinfoBirthday':$("#parentBrithday").val(),
		'userinfoEmail':$("#parentMailbox").val(),
		'userinfoPhone':$("#parentPhone").val()
	}
	var result = checkForm();
	if (result) 
	gogoServiceImpl.addStudentAndParent(data,data1,courseId,addStudentAndParentCallBack);
	};
	function addStudentAndParentCallBack(data){
	window.history.go(-1);
	}
	
	</script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body >
  		<input type="hidden" value="${loginAdmin.adminId}" id="adminId">
		<input type="hidden" value="${loginAdmin.tbSchool.schoolId}" id="adminSchoolId">
		<input type="hidden" value="${loginAdmin.tbBranchschool.branchSchoolId}" id="adminBranchSchoolId">
		<input type="hidden" value="${loginAdmin.adminRoot}" id="adminRoot">	
		<input type="hidden" value="${loginAdmin.isValid}" id="userinfoId">	
  		<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">新增页面</th>
				</tr>
				
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							
							<tr>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>学生新增信息</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr >
									<td width="50%" nowrap="nowrap" class="hid" align="right" colspan="2">班级:</td>
									<td width="50%" align="left" colspan="2"><select id="teacherToClass" ><option value="${courses.courseId }">${courses.courseName }</option></select><font class="red">*</font></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">姓名:</td>
											<td width="35%" ><input type="text" id="studentName"><font class="red">*</font></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">性别:</td>
											<td width="35%" ><select  id="studentSex">
											<option value="男">男</option>
											<option value="女">女</option>
											</select><font class="red">*</font></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">生日:</td>
											<td width="35%" ><input type="text" id="studentBrithday"  readonly="true" onclick="WdatePicker()"><font class="red">*</font></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">公立学校:</td>
											<td width="35%" ><input type="text" id="studentSchool"><font class="red">*</font></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">公立班级:</td>
											<td width="35%" ><input type="text" id="studentClass"><font class="red">*</font></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">QQ:</td>
											<td width="35%" ><input type="text" id="studentMailbox"><font class="red">*</font></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">手机:</td>
											<td width="35%" ><input type="text" id="studentPhone"><font class="red">*</font></td>
											
										</tr>
									</table>
									<br />
								</fieldset>
							</TD>
							</TR>
						</TABLE>
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<RT>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>家长新增信息</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">姓名:</td>
											<td width="35%" ><input type="text" id="parentName"><font class="red">*</font></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">性别:</td>
											<td width="35%" ><select  id="parentSex">
											<option value="男">男</option>
											<option value="女">女</option>
											</select><font class="red">*</font></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">生日:</td>
											<td width="35%" ><input type="text" id="parentBrithday"  readonly="true" onclick="WdatePicker()"></td>
											<td width="15%" nowrap="nowrap" class="hid" align="right">电话:</td>
											<td width="35%" ><input type="text" id="parentPhone"><font class="red">*</font></td>
										</tr>
										<tr>
											<td width="15%" nowrap="nowrap" class="hid" align="right">邮箱:</td>
											<td width="35%" ><input type="text" id="parentMailbox"><font class="red">*</font></td>
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
					<TD colspan="2" align="center" height="50px"><input onclick="dataAdd()" type="button" name="Submit" value="保存" class="button" />
						<input type="button" onclick="window.history.go(-1);" value="返回" class="button" />
					</TD>
				</TR>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
			</TABLE>
			</td>
			</tr>
			</table>
  </body>
</html>
