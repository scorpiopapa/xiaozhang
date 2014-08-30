<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectuserinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
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
	
	</script>
  
  <body>
   <center>
   		<s:form action="selectaction!addSchoolnotice.action" name="myform" method="post" theme="simple">
   		<table id="subtree1" style="DISPLAY: " width="100%"
						border="1" cellspacing="0" cellpadding="0">
				<tr class="CTitle">
					<td height="22" style="font-size:16px" colspan="16" align="center">学校通告列表</td>
				</tr>
   			<tr align="center" bgcolor="#EEEEEE">
   				<td>学校</td>
   				<td>
	   				<select id="school" onchange="selschool()" name="tbschoolnotice.tbSchool.schoolId">
	   					<option value="0">--请选择--</option>
	   					<s:iterator value="listschool">
	   						<option value='<s:property value="schoolId"/>'><s:property value="schoolName"/></option>
	   					</s:iterator>
	   				</select></td>
   					<td>分&nbsp;&nbsp;校</td>
	   					<TD>
	   					<select id="branchSchool" name="tbschoolnotice.tbBranchschool.branchSchoolId">
		   					<option value="--请选择--">--请选择--</option>
		   				</select>
		   		</TD>
   				<td>发布人</td>
   				<td><s:textfield name="tbschoolnotice.tbUserinfo.userInfoName"/></td>
   				</tr>
   				<tr align="center" >
   				<td>公告标题</td>
   				<td><s:textfield name="tbschoolnotice.schnotTitle"/></td>
   				<td>公告内容</td>
   				<td colspan="6"><s:textarea style="width:100%; height:30px;" name="tbschoolnotice.schnotContent"/></td>
   			</tr>
   			<tr align="center">
   				<td colspan="6"><s:submit value="添加" style="width:50px;height:25px;background:white;"></s:submit> 
   				<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/></td>
   			</tr>
   		</table>
   	</s:form>
   </center>
  </body>
</html>
