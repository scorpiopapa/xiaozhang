<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addclassinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		function seluser(){
			var course = document.getElementById("course").value;
			if(course!=0){
			window.location.href="selectaction!addclassinfo.action?tbCourse.courseId="+course;
			}
		}	
		
		 function addinfoclass(){
	 	 var userInfoId=document.getElementsByName("userInfoId");
	 	 var str=[];
	 	 
	 	 for(var i=0;i<userInfoId.length;i++ ){
	 	 	if(userInfoId[i].checked==true){
	 	 		str=str+userInfoId[i].value+",";
	 	 	}
	 	 }
	 	 if(str==""){
      		alert("请选中你要添加的学生");
      		}else{
      			$.post("selectaction!addclassinfo.action",{'str':str, 'a':Math.random()},function(){
					 alert("添加成功");
					window.location.reload();
				});
      		}
	 }
	</SCRIPT>
  </head>
  
  <body>
  		<table>
  			<tr>
				<td>学&nbsp;&nbsp;校</td>
   				<td>
   				<select id="school" onchange="selschool()" name="tbCourse.tbSchool.schoolId">
   					<option value="">--请选择--</option>
   					<s:iterator value="listschool">
   						<option value='<s:property value="schoolId"/>'><s:property value="schoolName"/></option>
   					</s:iterator>
   				</select></td>
  					<td>分&nbsp;&nbsp;校</td>
   					<TD>
	   					<select id="branchSchool" onchange="selcourse()" name="tbCourse.tbBranchschool.branchSchoolId">
		   					<option value="--请选择--">--请选择--</option>
		   				</select>
	   				</TD>
  					<td>课&nbsp;&nbsp;程</td>
   				<td>
   					<select id="course" name="courseId">
	   					<option value="--请选择--">--请选择--</option>
	   				</select>
   				</td>
 				</tr>
  		</table>
  	 <s:form action="selectaction!addclassinfo.action" name="myform" method="post" theme="simple" enctype="multipart/form-data">
  	 <table class="tablestyle1" width="100%" border="1">
				<tr align="center" class="CTitle">
					<td height="22" style="font-size:16px" colspan="5" align="center">
						<h4>班级导入学生</h4>
					</td>
				</tr>
    	<tr align="center" class="TableHeader2" bgcolor="#EEEEEE">
    		<td>学校</td>
    		<td>分校</td>
    		<td>用户信息</td>
    		<td>所选课程</td>
    		<td>操作</td>
    	</tr>
    	<s:iterator value="listUserinfo">
    		<tr align="center" class="TableHeader2" >
    			<td>
    				<s:property value="tbSchool.schoolName"/>
    			</td>
    			<td>
    				<s:property value="tbBranchschool.braschName"/>
    			</td>
    			<td>
    				<s:property value="userInfoName"/>
    			</td>
    			<td>
    				<s:property value="tbCourse.courseName"/>
    			</td>
    			<td>
    				<input value="<s:property value='userInfoId'/>">
    				<input type="checkbox" id="userInfoId" value="<s:property value='userInfoId'/>">
   				</td>
    		</tr>
    	</s:iterator>
    	<tr>
    		<td colspan="5" align="center"> 
				<input type="button" onclick="addinfoclass()" value="添加"/>
    		</td>
    	</tr>
    </table>
   </s:form>
  </body>
</html>
