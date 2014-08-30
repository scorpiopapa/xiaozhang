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
<title>修改班级</title>
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

function checkForm(){
	var subjectid = $id("subjectid").value;
	var kecheng = $id("kecheng").value;
	var lesson = $id("lesson").value;
	var courseName = $id("courseName").value;
	var classNumber = $id("classNumber").value;
	var studyTime = $id("studyTime").value;
	var address = $id("address").value;
	if(subjectid==""){
		alert("请选择科目");
		return false;
	}
	if(lesson==""){
		alert("请选择课程");
		return false;
	}
	if(kecheng==""){
		alert("请选择班");
		return false;
	}
	if(classNumber==""){
		alert("请输入班级编号");
		return false;
	}
	if(courseName==""){
		alert("请输入班级名称");
		return false;
	}
	if(studyTime==""){
		alert("请输入上课时间");
		return false;
	}
	if(address==""){
		alert("请输入上课地址");
		return false;
	}
}

function getKecheng(subject) {
	if(subject=="")
		subject=0;
	var url = 'getKechengJsons.action';
	var params = 'subjectid=' + subject;
	new Ajax.Request(url, {
		method : 'POST',
		parameters : params,
		onFailure : showError,
		onComplete : processLoginResponse
	})
	function processLoginResponse(response) {
		var objs = eval("(" + response.responseText + ")");
		var otions = $id("lesson");
		var count = otions.options.length;
		for ( var j = 0; j < count; j++) {
			otions.remove(0);
		}
		if(objs.kecheng.length==0){
			var selectObj = $id("lesson");
			selectObj.options[0] = new Option("==请选择==", "");
		}else{
			var selectObj = $id("lesson");
				selectObj.options[0] = new Option("==请选择==", "");
			for ( var i = 0; i < objs.kecheng.length; i++) {
				var selectObj = $id("lesson");
				selectObj.options[selectObj.length] = new Option(
						objs.kecheng[i].lessonName, objs.kecheng[i].id);
			}
		}
	}
		function showError(response) {
			alert("系统出错了");
			return;
		}
}
function getBanInfo(lesson) {
if(lesson=="")
	lesson=0;
var url = 'getLessonJsons.action';
var params = 'lessonid=' + lesson;
new Ajax.Request(url, {
	method : 'POST',
	parameters : params,
	onFailure : showError,
	onComplete : processLoginResponse
})
function processLoginResponse(response) {
	var objs = eval("(" + response.responseText + ")");
	var otions = $id("kecheng");
	var count = otions.options.length;
	for ( var j = 0; j < count; j++) {
		otions.remove(0);
	}
	if(objs.ban.length==0){
		var selectObj = $id("kecheng");
		selectObj.options[0] = new Option("==请选择==", "");
	}else{
	
		for ( var i = 0; i < objs.ban.length; i++) {
			var selectObj = $id("kecheng");
			selectObj.options[selectObj.length] = new Option(
					objs.ban[i].courseName, objs.ban[i].courseId);
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
  <form action="editClassDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >班级修改页面</th>
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
				<legend>修改班级</legend>
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
					  <tr>
                        <td align="right">科目:</td>
					    <td>
					    	<select name="subjectId" id="subjectid" onchange="getKecheng(this.value);">
                          	<option value="">==请选择==</option>
                          	<c:forEach items="${subject }" var="sl">
                          	<option value="${sl.subjectId }" 
                          	<c:if test="${subjets.subjectId eq sl.subjectId }"> selected </c:if>
                          	>${sl.subjectName }</option>
                          	</c:forEach>
                        	</select>
					    	<span class="red">*</span>
                        </td>
                        <td align="right">课程:</td>
					    <td>
					    	<select name="lesson.id" id="lesson" onchange="getBanInfo(this.value);">
                          	<option value="${lessons.id }">${lessons.lessonName }</option>
                        	</select>
					    	<span class="red">*</span>
                        </td>
                        </tr>
                        <tr>
					    <td align="right">班:</td>
					    <td>
					    	<select name="kechengid" id="kecheng">
                          	<option value="${userclass.tbCurriculum.courseId }">${userclass.tbCurriculum.courseName }</option>
                        	</select>
					    	<span class="red">*</span>
                        </td>
                        <td align="right">班级编号:</td>
					    <td><input class="text" name='course.classNumber' id="classNumber" style="width:154px" value="${course.classNumber }"/><span class="red">*</span></td>          
                        </tr>
                        <tr>
                        <td align="right">班级名称:</td>
                        <td>
					    	<input class="text" name='course.courseName' id="courseName" style="width:154px" value="${course.courseName }"/><span class="red">*</span>
					    	<input type="hidden" name="course.courseId" value="${course.courseId }"/>
					    	<input type="hidden" name="userClass.useclaId" value="${userclass.useclaId }"/>
					    </td>
					    <td align="right">上课时间:</td>
					    <td><input class="text" name='course.studyTime' id="studyTime" style="width:154px" value="${course.studyTime }"/><span class="red">*</span></td>
                        </tr>
                        <tr>
                        <td align="right">上课教室:</td>
					    <td><input class="text" name='course.address' id="address" style="width:154px" value="${course.address }"/><span class="red">*</span></td>
                        <td align="right">状态：</td>
                        <td>&nbsp;&nbsp;
					    	<input type="radio" name="course.isValid" 
					    		<c:if test="${course.isValid eq 1 }"> checked </c:if>
					    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<input type="radio" name="course.isValid" 
					    		<c:if test="${course.isValid eq 0 }"> checked </c:if>
					    	 value="0"/>&nbsp;&nbsp;无效
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

