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
<title>修改家长信息</title>
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
	var realName=$id("realName").value;
	var phoneNum=$id("userInfoPhone").value;
	if(realName.length<2){
		alert("真实姓名不少于两个字！");
		return false;
	}
	if(phoneNum==""){
		alert("请输入电话号码！");
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
  <form action="editParentInfoDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >家长修改页面</th>
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
				<legend>修改家长信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td align="right">所属总校:</td>
					    <td>
	                       	<input class="text" style="width:154px" value="${schools.schoolName }" readonly/>
					    	<input type="hidden" name="schoolId" value="${schools.schoolId }"/>
					    	<input type="hidden" name="usertype" value="${usertype }"/>
                        </td>
					    <td width="16%" align="right" nowrap="nowrap">分校:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" value="${branchs.braschName }" readonly/>
					    	<input type="hidden" name="branchId" value="${branchs.branchSchoolId }"/>
					    </td>
					  </tr>
					  <tr>
                        <td align="right">用户名:</td>
					    <td>
					    	<input class="text" name="user.userName" id="userName" style="width:154px" readonly value="${parent.userName }"/>
                        	<input type="hidden" name="user.userId" value="${parent.userId }"/>
                        </td>
                        <td align="right">真实姓名:</td>
					    <td>
					    	<input class="text" name="userInfo.userInfoName" id="realName" style="width:154px" value="${parent.tbUserinfo.userInfoName }"/>
					    	<input type="hidden" name="userInfo.userInfoId" value="${parent.tbUserinfo.userInfoId }"/>
					    	<input type="hidden" name="course.courseId" value="${courseid}"/>
					    	<span class="red">*</span>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">性别:</td>
					    <td>
					    	<input name="userInfo.userInfoSex" type="radio" value="男" <c:if test="${parent.tbUserinfo.userInfoSex eq '男' }">checked</c:if>/>男&nbsp;&nbsp;
					    	<input name="userInfo.userInfoSex" type="radio" value="女" <c:if test="${parent.tbUserinfo.userInfoSex eq '女' }">checked</c:if>/>女
					    	<span class="red">*</span>
                        </td>
                        <td align="right">生日:</td>
						    <td>
						    	<input class="text Wdate" name="userInfo.userInfoBirthday" value="${parent.tbUserinfo.userInfoBirthday }" style="width:154px" onclick="WdatePicker()"/>
	                        </td>
					    </tr>
					    <tr>
	                        <td align="right">手机:</td>
						    <td>
						    	<input class="text" name="userInfo.userInfoPhone" id="userInfoPhone" value="${parent.tbUserinfo.userInfoPhone }" style="width:154px"/>
						    	<span class="red">*</span>
	                        </td>
	                        <td align="right">邮箱:</td>
					    	<td>
					    		<input class="text" name="userInfo.userInfoEmail" id="userInfoEmail" value="${parent.tbUserinfo.userInfoEmail }" style="width:154px"/>
                        	</td>
					    </tr>
					    <tr>
                        <td align="right">状态:</td>
					    	<td>&nbsp;&nbsp;
					    	<input type="radio" name="userInfo.isValid" 
					    		<c:if test="${parent.isValid eq 1 }"> checked </c:if>
					    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<input type="radio" name="userInfo.isValid" 
					    		<c:if test="${parent.isValid eq 0 }"> checked </c:if>
					    	 value="0"/>&nbsp;&nbsp;无效
					    	</td>
					    	<td>&nbsp;</td><td>&nbsp;</td>
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

