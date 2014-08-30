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
<title>注册学校审核</title>
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
function checkReg(id,s){
	var tip = "";
	if(s==1)
		tip = "确认通过审核";
	if(s==-1)
		tip = "确认不通过审核？";
	if(confirm(tip))	
		location.replace("checkRegSchool.action?regschool.schoolId="+id+"&regschool.status="+s);
}
</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>

</head>

<body class="ContentBody">
  <form action="checkRegSchool.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >注册学校审核页面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td align="left">
			<input type="button" name="Submit" value="通过审核"  onclick="checkReg(${school.schoolId },1);"
			<c:if test="${school.status ne 1 }"> class="button" </c:if><c:if test="${school.status eq 1 }"> disabled </c:if>
			/>　
			<input type="button" name="Submit" value="不通过审核" onclick="checkReg(${school.schoolId },-1);"
			<c:if test="${school.status eq 0 }"> class="button" </c:if><c:if test="${school.status ne 0}"> disabled </c:if>
			/>
				<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>			</td>
		</tr>
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>审核注册学校</legend>
					 <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">学校名称:</td>
					    <td width="34%">
					    	<input class="text" name='school.schoolName' style="width:154px" id="schoolName" value="${school.schoolName }" readonly/>&nbsp;
					    </td>
					    <td nowrap="nowrap" align="right">学校简称:</td>
					    <td><input class="text" name='school.schoolMinName' style="width:154px" id="schoolMinName" value="${school.schoolMinName }" readonly/>&nbsp;</td>
					  </tr>
					  <tr>
                        <td align="right">城市:</td>
					    <td><select name="x" onchange="getArea(this.value);" id="city">
                          <c:forEach items="${cityList }" var="cl">
                          <c:if test="${cl.cityId eq cityid }">
                           <option value="${cl.cityId }" >${cl.cityName }</option>
                           </c:if>
                          </c:forEach>
                        </select>
                        </td>
					    <td align="right">地区:</td>
					    <td><select name="townId" id="area" >
                          <c:forEach items="${townList }" var="tl"> 
                          <c:if test="${tl.townId eq school.tbTown.townId }">
                           <option value="${tl.townId }" >${tl.townName }</option>
                          </c:if>
                          </c:forEach>
                        </select>
                        </td>
					    </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">学校电话:</td>
					    <td><input class="text" name='school.schoolPhone' style="width:154px" id="schoolPhone" value="${school.schoolPhone }" readonly/>&nbsp;</td>
					    <td align="right">学校地址:</td>
					    <td><input class="text" name='school.schoolAddress' style="width:154px" id="schoolAddress" value="${school.schoolAddress }" readonly/>&nbsp;</td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">联系人:</td>
					    <td><input class="text" name='school.linemanName' style="width:154px" value="${school.linemanName }" readonly/></td>
					    <td colspan="3">&nbsp;</td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">联系电话:</td>
					    <td><input class="text" name='school.linemanPhone' style="width:154px" value="${school.linemanPhone }" readonly/></td>
					    <td align="right">Email:</td>
					    <td><input type="text" class="text" name='school.linemanEmail' style="width:154px" id="images" value="${school.linemanEmail }" readonly/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">QQ:</td>
					    <td><input class="text" name='school.linemanQq' style="width:154px" value="${school.linemanPhone }" readonly/></td>
					    <td nowrap="nowrap" align="right">校长:</td>
					    <td><input class="text" name='school.rectorName' style="width:154px" value="${school.rectorName }" readonly/></td>
					  </tr>
					  <tr>
					    <td align="right">校长电话:</td>
					    <td><input type="text" class="text" name='school.rectorPhone' style="width:154px" id="images" value="${school.rectorPhone }" readonly/></td>
					  	<td nowrap="nowrap" align="right">校长Email:</td>
					    <td><input class="text" name='school.rectorEmail' style="width:154px" value="${school.rectorEmail }" readonly/></td>
					  </tr>
					  <tr>
					    <td align="right">校长QQ:</td>
					    <td><input type="text" class="text" name='school.rectorQq' style="width:154px" id="images" value="${school.rectorQq }" readonly/></td>
					  	<td nowrap="nowrap" align="right">分校数量:</td>
					    <td><input class="text" name='school.subSchoolNum' style="width:154px" value="${school.subSchoolNum }" id="subSchoolNum" readonly/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">学生人数:</td>
					    <td><input class="text" name='school.studentNum' style="width:154px" value="${school.studentNum }" id="studentNum" readonly/></td>
					  	<td align="right">营业执照:</td>
					    <td><input type="text" class="text" name='school.schoolCertificate' style="width:154px" value="${school.schoolCertificate }" readonly/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">单位:</td>
					    <td><input class="text" name='school.schoolUnit' style="width:154px" value="${school.schoolUnit }" readonly/></td>
					  	<td nowrap="nowrap" align="right">学校网址:</td>
					    <td><input class="text" name='school.schoolUrl' style="width:154px" value="${school.schoolUrl }" readonly/></td>
					  </tr>
					  <tr>
					    <td align="right">开设课程:</td>
					    <td colspan="3"><textarea name="school.schoolCourse" id="schoolCourse" cols="100" rows="8" readonly>${school.schoolCourse }</textarea></td>
					  </tr>
					  </table>
			  <br />
				</fieldset>			</TD>
		</TR>
		</TABLE>
	 </td>
  </tr>
			<TD colspan="2" align="center" height="50px">
			<input type="button" name="Submit" value="通过审核"  onclick="checkReg(${school.schoolId },1);"
			<c:if test="${school.status ne 1 }"> class="button" </c:if><c:if test="${school.status eq 1 }"> disabled </c:if>
			/>　
			<input type="button" name="Submit" value="不通过审核" onclick="checkReg(${school.schoolId },-1);"
			<c:if test="${school.status eq 0 }"> class="button" </c:if><c:if test="${school.status ne 0}"> disabled </c:if>
			/>　
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

