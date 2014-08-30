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
<title>添加老师</title>
<c:if test="${not empty addTip }">
<script type="text/JavaScript">
	alert("用户名已存在！");
</script>
</c:if>
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
	var teachcourse=$id("teachcourse").value;
	var workYear=$id("workYear").value;
	var content = /^\w{6,18}$/;
	if(!content.test(userName)){
		alert("用户名由6到18个字符！(字母，数字或下划线组成)");
		return false;
	}
	if(checkName(userName)){
		alert("用户名已存在!");
		return false;
	}
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
function checkName(v){
	var name1 = /^\w{6,18}$/;
	if(!name1.test(v)){
		alert("用户名格式不正确(6到18位的数字，字母或下划线)！");
		return;
	}	
	var url = 'checkLoginName.action';
			var params = 'regname=' + v;
			new Ajax.Request(url, {
				method : 'POST',
				parameters : params,
				onFailure : showError,
				onComplete : processLoginResponse
			})
			function processLoginResponse(response) {
				var objs = eval("(" + response.responseText + ")");
				if(objs.teachersize>0){
					if($id("sametip")!=undefined){
						var sametip = $id("sametip");
						$id("sametip").parentNode.removeChild(sametip);
					}
					if($id("tipbtn")!=undefined){
						var tipbtn = $id("tipbtn");
						$id("tipbtn").parentNode.removeChild(tipbtn);
					}
					$("hiddentip").value=objs.userId;
					$("teachertip").style.display="block";
					//$("btntip").style.display="block";
				}else if(objs.size>0){
					$("teachertip").style.display="none";
					//$("btntip").style.display="none";
					if($id("sametip")!=undefined){
						var sametip = $id("sametip");
						$id("sametip").parentNode.removeChild(sametip);
					}
					if($id("tipbtn")!=undefined){
						var tipbtn = $id("tipbtn");
						$id("tipbtn").parentNode.removeChild(tipbtn);
					}
					var sametip = document.createElement("span");
					
					sametip.id="sametip";
					sametip.innerHTML="该用户名已存在!";
					
					$id("adminTip").appendChild(sametip);
				}else if(objs.size==0){
					$("teachertip").style.display="none";
					//$("btntip").style.display="none";
					if($id("sametip")!=undefined){
						var sametip = $id("sametip");
						$id("sametip").parentNode.removeChild(sametip);
					}
					if($id("tipbtn")!=undefined){
						var tipbtn = $id("tipbtn");
						$id("tipbtn").parentNode.removeChild(tipbtn);
					}
					var sametip = document.createElement("span");
					
					sametip.id="sametip";
					sametip.innerHTML="该用户名可用!";
					$id("adminTip").appendChild(sametip);
				}
			}
			function showError(response) {
				alert("系统出错了");
				return;
			}
}

function addTeacherToClass(){
	var uid =$("hiddentip").value;
	location.replace();
}
</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>

</head>

<body class="ContentBody">
  <form action="addTeacherDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >老师添加页面</th>
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
				<legend>添加老师</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr align="center" style="color:red;">
					  	<td  id="adminTip" colspan="4">
					  		<span id="teachertip" style="color:green;display:none;">该老师的用户信息已存在</span><!-- 
					  		<input id="btntip" type="button" style="display:none;" value="加    入" onclick="addTeacherToClass();"/> -->
					  		<input id="hiddentip" type="hidden"/>
					  	</td>
					  </tr>
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
					    	<input class="text" name="user.userName" id="userName" style="width:154px" onblur="checkName(this.value);"/>
					    	<span class="red">*</span>
                        </td>
                        <td align="right">真实姓名:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoName" id="realName" style="width:154px" />
					    	<span class="red">*</span>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">性别:</td>
					    <td>
					    	<input name="usrInfo.userInfoSex" type="radio" value="男" checked/>男&nbsp;&nbsp;
					    	<input name="usrInfo.userInfoSex" type="radio" value="女"/>女
					    	<span class="red">*</span>
                        </td>
                        <td align="right">生日:</td>
					    <td>
					    	<input class="text Wdate" name="usrInfo.userInfoBirthday" style="width:154px" onclick="WdatePicker()"/>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">电话:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoPhone" style="width:154px" id="phoneNum"/>
					    	<span class="red">*</span>
                        </td>
                        <td align="right">邮箱:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoEmail" style="width:154px"/>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">任教课程:</td>
					    <td>
					    	<input class="text" name="usrInfo.userInfoCourse" id="teachcourse" style="width:154px"/>
					    	<span class="red">*</span>
                        </td>
                        <td align="right">毕业院校:</td>
					    <td>
					    	<input class="text" name="usrInfo.studentSchool" style="width:154px"/>
                        </td>
					    </tr>
					    <tr>
                        <td align="right">工作年限:</td>
					    <td>
					    	<input class="text" name="usrInfo.workYear" id="workYear" style="width:154px"/>
                        </td>
                        <td align="right">&nbsp;</td>
					    <td>
					    	&nbsp;
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

