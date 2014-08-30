<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改分校科目</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css"
	type="text/css" media="all" />


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
var subjects=$id("subjects").value;
	if(subjects==""){
		alert("请选择课程！");
		return false;
	}
}

function check()
{
	$id("aa").style.display="";
}
</script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>

</head>

<body class="ContentBody">
	<form action="editSubBranchDo.action" method="post"
		enctype="multipart/form-data" name="form"
		onsubmit="return checkForm();">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">分校科目修改页面</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<tr>
								<td align="left"><input type="submit" name="Submit"
									value="保存" class="button" /> <input type="button"
									name="Submit2" value="返回" class="button"
									onclick="window.history.go(-1);" /></td>
							</tr>
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>修改分校科目</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
											<tr height="50">
												<th width="45%" align="right" nowrap="nowrap">分校名称:</th>
												<td width="55%" align="left">
													${branchschool.braschName } 
													<input type="hidden" name="tbBschool.branchSchoolId" value="${branchschool.branchSchoolId }" />
													<input type="hidden" name="subjectbranch.id" value="${subbranch.id }"/></td>
											</tr>
											<tr>
												<th align="right">科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目:</th>
												<td><select name="subjects.subjectId" id="subjects">
														<option value="">==请选择==</option>
														<c:forEach items="${newsublist }" var="nl">
															<option value="${nl.subjectId }" 
															<c:if test="${nl.subjectId eq subbranch.tbSubject.subjectId }">selected</c:if>
															>${nl.subjectName }</option>
														</c:forEach>
												</select><span class="red">*</span>
												</td>
											</tr>
											<tr>
												<th align="right">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</th>
												<td>&nbsp;&nbsp;
											    	<input type="radio" name="subjectbranch.isValid" 
											    		<c:if test="${subbranch.isValid eq 1 }"> checked </c:if>
											    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											    	<input type="radio" name="subjectbranch.isValid" 
											    		<c:if test="${subbranch.isValid eq 0 }"> checked </c:if>
											    	 value="0"/>&nbsp;&nbsp;无效
					    						</td>
											</tr>		
										</table>
										<br />
									</fieldset></TD>
							</TR>
						</TABLE></td>
				</tr>





				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="submit" name="Submit" value="保存" class="button" /> <input
						type="button" name="Submit2" value="返回" class="button"
						onclick="window.history.go(-1);" />
					</TD>
				</TR>
			</TABLE>


			</td>
			</tr>



			</table>

		</div>
	</form>
</body>
</html>

