<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		
		<title>学生帐号管理</title>
		
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
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
		
		<script type="text/javascript">
		function selschool(){
			var school = document.getElementById("school").value;
			var branchSchool = document.getElementById("branchSchool");
			branchSchool.options.length=0;
			if(school!=0){
				var option = new Option("--请选择--","0");
				branchSchool.add(option);
			$.getJSON("selectaction!select.action",{"tbschool.schoolId":school,"a":Math.random()},function(aaa){
				for(var i=0;i<aaa.length;i++){
					var option = new Option(aaa[i].braschName,aaa[i].branchSchoolId);
					branchSchool.add(option);
				}
			});
			}else{
				var option = new Option("--请选择--","0");
				branchSchool.add(option);
				}
		}
		
		function selcourse(){
			var branchSchool = document.getElementById("branchSchool").value;
			var course = document.getElementById("course");
			course.options.length=0;
			if(branchSchool!=0){
				var option = new Option("--请选择--","0");
				course.add(option);
			$.getJSON("selectaction!listCourse.action",{"tbbranchschool.branchSchoolId":branchSchool,"a":Math.random()},function(bbb){
				for(var i=0;i<bbb.length;i++){
					var option = new Option(bbb[i].courseName,bbb[i].courseId);
					course.add(option);
				}
			});
			}else{
				var option = new Option("--请选择--","0");
				course.add(option);
				}
		}
		</SCRIPT>
		
	</head>
	<body>
	<center>
	<s:form action="addinfo!addInfo.action" name="myform" method="post" theme="simple" enctype="multipart/form-data">
		<table>
   				<tr>
	   				<td>学&nbsp;&nbsp;校</td>
	   				<td>
	   				<select id="school" onchange="selschool()" name="tbuser.tbSchool.schoolId">
	   					<option value="">--请选择--</option>
	   					<s:iterator value="listschool">
	   						<option value='<s:property value="schoolId"/>'><s:property value="schoolName"/></option>
	   					</s:iterator>
	   				</select></td>
   					<td>分&nbsp;&nbsp;校</td>
	   					<TD>
	   					<select id="branchSchool" onchange="selcourse()" name="tbuser.tbBranchschool.branchSchoolId">
		   					<option value="--请选择--">--请选择--</option>
		   				</select>
		   				</TD>
   					<td>班&nbsp;&nbsp;级</td>
	   				<td>
	   					<select id="course" name="tbuser.tbCourse.courseId">
		   					<option value="--请选择--">--请选择--</option>
		   				</select>
	   				</td>
   				</tr>
   				<tr>
					<td>家长学生信息:</td>
					<td colspan="5"><input type="file" name="file" /><font class="red">*</font></td>
				</tr>
				<tr>
					<td colspan="6" align="center" height="50px">
					<s:submit name="myform" value="添加" style="width:50px;height:25px;background:white;"></s:submit>
					
<!--					<input-->
<!--						 type="button" onclick="addInfoData()" name="Submit" value="保存" style="width:50px;height:25px;background:white;" />-->
<!--						<input type="button"  value="重置" onclick="cancel()" style="width:50px;height:25px;background:white;"/>-->
					</td>
				</tr>
   			</table>
	</s:form>
	</center>
</body>
</html>
