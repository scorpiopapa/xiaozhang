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
<title>修改费用信息</title>
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
	var branch = $id("branch").value;
	var chargeName = $id("chargeName").value;
	var vipCharge = $id("vipCharge").value;
	if(branch==""){
		alert("请选择分校！");
		return false;
	}
	if(chargeName==""){
		alert("请选输入费用名称！");
		return false;
	}
	if(vipCharge==""){
		alert("请输入费用金额！");
		return false;
	}
	if(isNaN(vipCharge)){
		alert("金额为数字！");
		return false;
	}
}

function getBranch(school) {
			if(school=="")
				school=0;
			var url = 'getBranchJson.action';
			var params = 'schoolid='+school;
			new Ajax.Request(url, {
				method : 'POST',
				parameters : params,
				onFailure : showError,
				onComplete : processLoginResponse
			})
			function processLoginResponse(response) {
				var objs = eval("(" + response.responseText + ")");
				var otions = $id("branch");
				var count = otions.options.length;
				for ( var j = 0; j < count; j++) {
					otions.remove(0);
				}
				if(objs.branch.length==0){
					var selectObj = $id("branch");
					selectObj.options[0] = new Option("==请选择==", "");
				}else{
				
					for ( var i = 0; i < objs.branch.length; i++) {
						var selectObj = $id("branch");
						selectObj.options[selectObj.length] = new Option(
								objs.branch[i].braschName, objs.branch[i].branchSchoolId);
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
  <form action="editChargesDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >费用信息修改页面</th>
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
				<legend>修改费用信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td align="right">所属总校:</td>
					    <td>
					    <c:if test="${loginAdmin.adminRoot eq 0}">
					    <select id="school" onchange="getBranch(this.value);" >
                          <option value="">==请选择==</option>
                          <c:forEach items="${schoolist }" var="sl">
                          <option value="${sl.schoolId }" 
                          <c:if test="${school.schoolId eq sl.schoolId}">
                          selected
                          </c:if>
                          >${sl.schoolName }</option>
                          </c:forEach>
                        </select><span class="red">*</span>
                        </c:if>
                        <c:if test="${loginAdmin.adminRoot ne 0}">
                        	<input class="text" style="width:154px" value="${oneschool.schoolName }" readonly/>
                        </c:if>
                        </td>
					    <td align="right">分校:</td>
					    <td>
					    <c:if test="${loginAdmin.adminRoot eq 0}">
					    <select name="branch.branchSchoolId" id="branch">
                          <c:forEach items="${branchlist }" var="bs">
                          <option value="${bs.branchSchoolId }" 
                          <c:if test="${bs.branchSchoolId eq charge.tbBranchschool.branchSchoolId}">
                          selected
                          </c:if>
                          >${bs.braschName }</option>
                          </c:forEach>
                       </select><span class="red">*</span>
                        </c:if>
                        <c:if test="${loginAdmin.adminRoot eq 1}">
					    <select name="branch.branchSchoolId" id="branch">
                          <option value="${charge.tbBranchschool.branchSchoolId }">${charge.tbBranchschool.braschName }</option>
                        </select><span class="red">*</span>
                        </c:if>
                        <c:if test="${loginAdmin.adminRoot eq 2}">
                        	<input class="text" style="width:154px" id="branch" value="${onebranch.braschName }" readonly/>
                        	<input type="hidden" name="branch.branchSchoolId" value="${onebranch.branchSchoolId }"/>
                        </c:if>
                        </td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">费用名称:</td>
					    <td>
					    	<input class="text" name='charge.chargeName' style="width:154px" id="chargeName" value="${charge.chargeName}" />
					    	<input type="hidden" name='charge.id' value="${charge.id}" />
					    	&nbsp;<span class="red">*</span></td>
					    <td align="right">金额:</td>
					    <td><input class="text" name='charge.vipCharge' style="width:154px" id="vipCharge" value="${charge.vipCharge}"/>&nbsp;<span class="red">*</span></td>
					  </tr>
					  <tr>
					    <th align="right">状&nbsp;&nbsp;&nbsp;&nbsp;态:</th>
					    <td>&nbsp;&nbsp;
					    	<input type="radio" name="charge.isValid" 
					    		<c:if test="${charge.isValid eq 1 }"> checked </c:if>
					    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<input type="radio" name="charge.isValid" 
					    		<c:if test="${charge.isValid eq 0 }"> checked </c:if>
					    	 value="0"/>&nbsp;&nbsp;无效
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

