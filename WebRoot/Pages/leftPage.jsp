<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<metahttp-equiv ="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(../images/left.gif);
}
-->
</style>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">

</script>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery1.8.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/gogoShare.js"></script>
<%-- <script type="text/javascript" src="../js/leftPage.js"></script> --%>
<SCRIPT language=JavaScript>

	function tupian(idt) {
		var nametu = "xiaotu" + idt;
		var tp = document.getElementById(nametu);
		tp.src = "../images/ico05.gif";//图片ico04为白色的正方形

		for ( var i = 1; i < 55; i++) {

			var nametu2 = "xiaotu" + i;
			if (i != idt * 1) {
				var tp2 = document.getElementById('xiaotu' + i);
				if (tp2 != undefined) {
					tp2.src = "../images/ico06.gif";
				}//图片ico06为蓝色的正方形
			}
		}
	}

	function list(idstr) {
		var name1 = "subtree" + idstr;
		var name2 = "img" + idstr;
		var objectobj = document.getElementById(name1);
		var imgobj = document.getElementById(name2);

		//alert(imgobj);

		if (objectobj.style.display == "none") {
			for (i = 1; i < 19; i++) {
				var name3 = "img" + i;
				var name = "subtree" + i;
				var o = document.getElementById(name);
				if (o != undefined) {
					o.style.display = "none";
					var image = document.getElementById(name3);
					//alert(image);
					image.src = "../images/ico04.gif";
				}
			}
			objectobj.style.display = "";
			imgobj.src = "../images/ico03.gif";
		} else {
			objectobj.style.display = "none";
			imgobj.src = "../images/ico04.gif";
		}
	}
	function loginOut(){
		document.loginOutForm.submit;
	}
</SCRIPT>
</head>
<body>
	<form action="logOut.action" method="post" name="loginOutForm">
	<table width="198" border="0" cellpadding="0" cellspacing="0"
		class="left-table01">
		<tr>
			<TD>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="207" height="55" background="../images/nav01.gif">
							<table width="90%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="15%" rowspan="2"><img src="../images/ico02.gif"
										width="35" height="35" /><input type="hidden"
										value="${loginAdmin.adminRoot }" id="adminRoot"></td>
									<td width="35%" class="left-font01" align="right">您好，</td>
									<td width="50%" align="left" style="font-weight:800; color:red;">${loginAdmin.adminName }</td>
								</tr>
								<tr>
									<td height="22" class="left-font01">
									[&nbsp;<a href="${pageContext.servletContext.contextPath }/logout.action" target="_top" class="left-font01">退出</a>&nbsp;]
									</td>
									<td colspan="2" class="left-font01">
									<c:if test="${loginAdmin.adminRoot ne 3 }">
									[&nbsp;<a href="${pageContext.servletContext.contextPath }/modifyps.action" target="mainFrame" class="left-font01">修改密码</a>&nbsp;]
									</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<c:if test="${loginAdmin.adminRoot eq 0 || loginAdmin.adminRoot eq 1 }">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img1" id="img1"
										src="../images/ico04.gif" width="8" height="11" /></td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('1');">账户管理</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</TABLE>
				<table id="subtree1" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu1"
							src="../images/ico06.gif" width="8" height="12" /></td>

						<td width="91%"><a href="${pageContext.servletContext.contextPath }/schoolAdmin.action" target="mainFrame"
							class="left-font03" onClick="tupian('1')";>总校管理员</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot ne 3}">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img2" id="img2"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('2');">
										<c:if test="${loginAdmin.adminRoot ne 0}">
										学校管理
										</c:if>
										<c:if test="${loginAdmin.adminRoot eq 0}">
										学校及城市地区管理
										</c:if>
										</a>
									</td>
								</tr>
							
							</table>
						</td>
					</tr>
				</TABLE>
				<table id="subtree2" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<c:if test="${loginAdmin.adminRoot ne 2}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu3"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath }/parentSchool.action" target="mainFrame"
							class="left-font03" onClick="tupian('3');">总校信息管理 </a>
						</td>
					</tr>
					</c:if>
					<tr>
						<td width="9%" height="20"><img id="xiaotu4"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath }/branchSchool.action" target="mainFrame"
							class="left-font03" onClick="tupian('4');">分校信息管理</a>
						</td>
					</tr>
					<c:if test="${loginAdmin.adminRoot eq 1||loginAdmin.adminRoot eq 2}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu44"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="photoSchool.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('44');">学校相册</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu47"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="schoolInfo.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('47');">学校简介</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${loginAdmin.adminRoot eq 0}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu5"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath }/cityView.action" target="mainFrame"
							class="left-font03" onClick="tupian('5');">城市地区管理 </a>
						</td>
					</tr>
					</c:if>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 0 || loginAdmin.adminRoot eq 1}">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img3" id="img3"
										src="../images/ico04.gif" width="8" height="11" /></td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('3');">
									<c:if test="${loginAdmin.adminRoot eq 0}">	
										年级科目排名管理
									</c:if>	
									<c:if test="${loginAdmin.adminRoot eq 1}">	
										班课程班级管理
									</c:if>	
										</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</TABLE>
				<table id="subtree3" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<c:if test="${loginAdmin.adminRoot eq 0}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu7"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/selectaction!selectGrade.action" target="mainFrame"
							class="left-font03" onClick="tupian('7');">年级</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu8"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/selectaction!selectSubject.action" target="mainFrame"
							class="left-font03" onClick="tupian('8');">科目</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu35"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/showlessonrank.action" target="mainFrame"
							class="left-font03" onClick="tupian('35');">课程排名</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu6"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/showbanrank.action" target="mainFrame"
							class="left-font03" onClick="tupian('6');">班排名</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${loginAdmin.adminRoot eq 1}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu35"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/showlesson.action" target="mainFrame"
							class="left-font03" onClick="tupian('35');">课程</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu6"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/showBanInfo.action" target="mainFrame"
							class="left-font03" onClick="tupian('6');">班</a>
						</td>
					</tr>
					</c:if>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 2}">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img4" id="img4"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('4');">老师管理</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree4" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu9"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/viewUsers.action?usertype=1" target="mainFrame"
							class="left-font03" onClick="tupian('9');">老师信息</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 3 }">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img15" id="img15"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('15');">班级管理</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree15" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu30"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/viewClass.action" target="mainFrame"
							class="left-font03" onClick="tupian('30');">班级信息</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 1 }" >
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img16" id="img16"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('16');">校长管理</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree16" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu40"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath }/selectaction!selectprincipal.action" target="mainFrame"
							class="left-font03" onClick="tupian('40');">分校校长信息管理</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img5" id="img5"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('5');">信息公告管理</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</TABLE>
				<table id="subtree5" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<c:if test="${loginAdmin.adminRoot eq 0}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu11"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath }/infoNotice.action" target="mainFrame"
							class="left-font03" onClick="tupian('11');">信息公告</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu45"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="advertisement.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('45');">广告维护</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${loginAdmin.adminRoot eq 1 || loginAdmin.adminRoot eq 2}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu12"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/selectaction!selectSchoolnotice.action" target="mainFrame"
							class="left-font03" onClick="tupian('12');">学校公告管理</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${loginAdmin.adminRoot eq 3}">
					<tr>
						<td width="9%" height="20"><img id="xiaotu13"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/selectaction!selectClassnotice.action" target="mainFrame"
							class="left-font03" onClick="tupian('13');">班级通知管理</a>
						</td>
					</tr>
					</c:if>
				</table> 
				<c:if test="${loginAdmin.adminRoot eq 2 }">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img6" id="img6"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('6');">投诉系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree6" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu15"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/selectaction!selectComplain.action" target="mainFrame"
							class="left-font03" onClick="tupian('15');">投诉列表</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 2 }">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr class="hid1">
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img7" id="img7"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('7');">个人相册</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree7" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu16"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="userphoto.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('16');">个人相册</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 2 }">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img8" id="img8"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('8');">到离校管理系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree8" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu17"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath}/attendance.action" target="mainFrame"
							class="left-font03" onClick="tupian('17');">出勤表</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 2 }">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img9" id="img9"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('9');">评语系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree9" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu18"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/selectaction!selectReview.action" target="mainFrame"
							class="left-font03" onClick="tupian('18');">评语管理</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 3 }">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr class="hid1">
						<td height="29"><table width="85%" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td width="8%" height="12"><img name="img10" id="img10"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('10');">在线练习</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<table id="subtree10" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu19"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="test.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('19');">练习库管理</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 2}">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img11" id="img11"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('11');">报名系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree11" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu43"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="orderSchool.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('43');">现场报名</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 0 || loginAdmin.adminRoot eq 1}">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img11" id="img11"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('11');">收费系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				
				<table id="subtree11" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<c:if test="${loginAdmin.adminRoot eq 1 }">
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu24"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath }/charges.action" target="mainFrame"
							class="left-font03" onClick="tupian('24');">课程费用</a>
						</td>
						
					</tr>
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu41"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="teamSchool.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('41');">优惠课程</a>
						</td>
					</tr>
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu43"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="orderSchool.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('43');">现场报名</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${loginAdmin.adminRoot eq 0 }">
					<tr>
						<td width="9%" height="20"><img id="xiaotu25"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.servletContext.contextPath }/viptimeshow.action" target="mainFrame"
							class="left-font03" onClick="tupian('25');">Vip时间</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu26"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="bills.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('26');">支付单表</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu42"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="teamAdmin.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('42');">优惠批审</a>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu48"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="curriculum02TeamPrice.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('48');">班优惠价</a>
						</td>
					</tr>
					</c:if>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 0}">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03" >
					<tr class="hid1">
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img12" id="img12"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('12');">聊天系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</table>
				<table id="subtree12" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr class="hid1">
						<td width="9%" height="20"><img id="xiaotu27"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="chat.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('27');">聊天记录</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 0}">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img13" id="img13"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('13');">推送系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</TABLE>
				<table id="subtree13" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu28"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="push.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('28');">推送信息</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 0 }">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr class="hid1">
						<td height="29"><table width="85%" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr >
									<td width="8%"><img name="img14" id="img14"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('14');">信息及学校注册管理</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<table id="subtree14" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu29"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="sysconfig.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('29');">版本信息管理</a>
						</td>
					</tr>
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu34"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/moreaction!selectMore.action" target="mainFrame"
							class="left-font03" onClick="tupian('34');">更多信息</a>
						</td>
					</tr>
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu36"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/viewInformation.action?infoType=0" target="mainFrame"
							class="left-font03" onClick="tupian('36');">综合信息</a>
						</td>
					</tr>
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu37"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="${pageContext.request.contextPath}/viewRegSchool.action" target="mainFrame"
							class="left-font03" onClick="tupian('37');">学校注册信息</a>
						</td>
					</tr>
					<tr class="hid">
						<td width="9%" height="20"><img id="xiaotu46"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="schoolApplication.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('46');">手机端学校注册审批</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 0}">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03" >
					<tr class="hid1">
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img15" id="img15"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('15');">地图显示系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</table>
				<table id="subtree15" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr class="hid1">
						<td width="9%" height="20"><img id="xiaotu49"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="mapShow.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('49');">总校地图设置</a>
						</td>
					</tr>
					<tr class="hid1">
						<td width="9%" height="20"><img id="xiaotu50"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="mapShow2.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('50');">分校校地图设置</a>
						</td>
					</tr>
				</table> 
				</c:if>
				<c:if test="${loginAdmin.adminRoot eq 0}">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03" >
					<tr class="hid1">
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img16" id="img16"
										src="../images/ico04.gif" width="8" height="11" />
									</td>
									<td width="92%"><a href="javascript:;" target="mainFrame"
										class="left-font03" onClick="list('16');">推送系统</a>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</table>
				<table id="subtree16" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr class="hid1">
						<td width="9%" height="20"><img id="xiaotu50"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="pushHeadTeacher.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('50');">推送-校长</a>
						</td>
					</tr>
					<tr class="hid1">
						<td width="9%" height="20"><img id="xiaotu51"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="pushPaterarchS.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('51');">推送-家长（总校版）</a>
						</td>
					</tr>
					<tr class="hid1">
						<td width="9%" height="20"><img id="xiaotu52"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="pushPaterarchB.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('52');">推送-家长（分校版）</a>
						</td>
					</tr>
					<tr class="hid1">
						<td width="9%" height="20"><img id="xiaotu53"
							src="../images/ico06.gif" width="8" height="12" />
						</td>
						<td width="91%"><a href="pushRecord.jsp" target="mainFrame"
							class="left-font03" onClick="tupian('53');">推送记录</a>
						</td>
					</tr>
				</table> 
				</c:if>
		  </TD>
		</tr>
	</table>
	</form>
</body>
</html>