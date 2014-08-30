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
<title>修改老师</title>
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
	var userName=$id("userName").value;
	var realName=$id("realName").value;
	var phoneNum=$id("phoneNum").value;
	var workYear=$id("workYear").value;
	var teachcourse=$id("teachcourse").value;
	var content = /^\w{6,18}$/;
	/* if(!content.test(userName)){
		alert("用户名由6到18个字符！(字母，数字或下划线组成)");
		return false;
	} */
	if(realName.length<2){
		alert("真实姓名不少于两个字！");
		return false;
	}
	if(phoneNum==""){
		alert("请输入电话号码！");
		return false;
	}
	if(teachcourse==""){
		alert("请输入任教课程！");
		return false;
	}
	if(isNaN(workYear)){
		alert("年限为数字！");
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
  <form action="editTeacherDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >老师修改页面</th>
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
				<legend>修改老师</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td align="right">所属总校:</td>
					    <td>
	                       	<input class="text" style="width:154px" value="${school.schoolName }" readonly/>
					    	<input type="hidden" name="schoolId" value="${school.schoolId }"/>
                        </td>
					    <td width="16%" align="right" nowrap="nowrap">分校:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" value="${branch.braschName }" readonly/>
					    	<input type="hidden" name="branchId" value="${branch.branchSchoolId }"/>
					    </td>
					  </tr>
					  <tr>
                        <td align="right">用户名:</td>
					    <td>
					    	<input class="text" name="user.userName" id="userName" style="width:154px" value="${user.userName }" readonly/>
					    	<input type="hidden" name="user.userId" value="${user.userId }" />
					    	<input type="hidden" name="usertype" value="${usertype }" />
                        </td>
                        <td align="right">真实姓名:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoName" id="realName" style="width:154px" value="${user.tbUserinfo.userInfoName }"/>
					    	<input type="hidden" name="usrInfo.userInfoId" value="${user.tbUserinfo.userInfoId }" />
					    	<span class="red">*</span>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">性别:</td>
					    <td>
					    	<input name="usrInfo.userInfoSex" type="radio" value="男" <c:if test="${user.tbUserinfo.userInfoSex eq '男' }">checked</c:if>/>男&nbsp;&nbsp;
					    	<input name="usrInfo.userInfoSex" type="radio" value="女" <c:if test="${user.tbUserinfo.userInfoSex eq '女' }">checked</c:if>/>女
					    	<span class="red">*</span>
                        </td>
                        <td align="right">生日:</td>
					    <td>
					    	<input class="text Wdate" name="usrInfo.userInfoBirthday" style="width:154px" onclick="WdatePicker()" value="${user.tbUserinfo.userInfoBirthday }"/>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">电话:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoPhone" style="width:154px" id="phoneNum" value="${user.tbUserinfo.userInfoPhone }"/>
					    	<span class="red">*</span>
                        </td>
					    <td align="right">邮箱:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoEmail" style="width:154px" value="${user.tbUserinfo.userInfoEmail }"/>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">任教课程:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoCourse" id="teachcourse" style="width:154px" value="${user.tbUserinfo.userInfoCourse }"/>
					    	<span class="red">*</span>
                        </td>
                        <td align="right">毕业院校:</td>
					    <td>
					    	<input class="text" name="usrInfo.studentSchool" style="width:154px" value="${user.tbUserinfo.studentSchool }"/>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">工作年限:</td>
					    <td>
					    	<input class="text" name="usrInfo.workYear" id="workYear" style="width:154px" value="${user.tbUserinfo.workYear }"/>
                        </td>
                        <td nowrap="nowrap" align="right">状&nbsp;&nbsp;态:</td>
					    <td>&nbsp;&nbsp;
					    	<input type="radio" name="user.isValid" 
					    		<c:if test="${user.isValid eq 1 }"> checked </c:if>
					    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<input type="radio" name="user.isValid" 
					    		<c:if test="${user.isValid eq 0 }"> checked </c:if>
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

