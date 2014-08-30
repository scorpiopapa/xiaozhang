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
<title>修改班排名</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css"
	type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">

function $id(id){
	return document.getElementById(id);
}

function checkForm(){
	var firstorder=$id("firstorder").value;
	var secondorder=$id("secondorder").value;
	
	if(!/^\d+$/.test(firstorder)){
		alert("第一顺序为一个整数！");
		return false;
	}
	if(secondorder!=""&&!/^\d+$/.test(secondorder)){
		alert("第二顺序为一个整数！");
		return false;
	}
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
	<form action="editbanrankDo.action" method="post"
		enctype="multipart/form-data" name="form"
		onsubmit="return checkForm();">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">班排名修改页面</th>
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
										<legend>修改班排名</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">总&nbsp;&nbsp;&nbsp;&nbsp;校:</th>
												<td width="60%" align="left">
													<input class="text" style="width:154px" value="${banrank.tbSchool.schoolName }" readonly />
												</td>
											</tr><tr height="50">
												<th width="40%" align="right" nowrap="nowrap">分&nbsp;&nbsp;&nbsp;&nbsp;校:</th>
												<td width="60%" align="left">
													<input class="text" style="width:154px" value="${banrank.tbBranchschool.braschName }" readonly />
												</td>
											</tr>
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">所属科目:</th>
												<td width="60%" align="left">
													<input class="text" style="width:154px" value="${banrank.tbSubject.subjectName }" readonly/>
												</td>
											</tr>
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">所属课程:</th>
												<td width="60%" align="left">
													<input class="text" style="width:154px" value="${banrank.tbLesson.lessonName }" readonly/>
												</td>
											</tr>
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">班名称:</th>
												<td width="60%" align="left">
													<input class="text" style="width:154px" value="${banrank.courseName }" readonly/>
													<input type="hidden" name="kecheng.courseId" value="${banrank.courseId }"/>
												</td>
											</tr>
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">第一顺序:</th>
												<td width="60%" align="left">
													<input class="text" name='kecheng.defineSort' style="width:154px" value="${banrank.defineSort }" id="firstorder" />&nbsp;<span class="red">*</span>
												</td>
											</tr>
											<tr height="50">
												<th width="40%" align="right" nowrap="nowrap">第二顺序:</th>
												<td width="60%" align="left">
													<input type="text" class="text" name="kecheng.defineSort2" style="width:154px" value="${banrank.defineSort2 }" id="secondorder"/>
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

