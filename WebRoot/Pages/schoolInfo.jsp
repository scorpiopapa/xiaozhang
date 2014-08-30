<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>学校简介</title>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
-->
</style>

<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script charset="utf-8" src="editor/kindeditor.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/jquery1.8.js"></script>
	<script type="text/javascript" src="js/gogoShare.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/interface/page.js"></script>
	<script type="text/javascript" src="dwr/interface/secondBackstage.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script>
		KE.show({
		  	id : 'content1',
			imageUploadJson : 'upload_json.jsp',
			fileManagerJson : 'file_manager_json.jsp',
			allowFileManager : true,
			urlType : 'domain',// urlType可指定relative, absolute, domain，分别代表相对路径、绝对路径、带域名的完整URL。
			afterCreate : function(id) {
				KE.event.ctrl(document, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
				KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
			}
	});
		function dwrProjectTable(){
				var root=$("#adminRoot").val();
				KE.html('content1', "");
				$("#schoolInfoId").val("");
				if(root==1)
					secondBackstage.findDetailTbSchool02($("#adminSchoolId").val(),pageCallBack);
				else 
					secondBackstage.findDetailTbBranchschool02($("#adminBranchSchoolId").val(),pageCallBack);
			}
		//分页的回调函数
		function pageCallBack(data){
			$("#schoolInfoId").val(data.id);
			if(data.overview!=null)
			KE.html('content1', data.overview);
			}
		function dataUpdate(){
			var root=$("#adminRoot").val();
			var content=  KE.util.getData('content1');
			var data={
					"id":$("#schoolInfoId").val(),
					"overview":content
			};
			if(root==1)
				secondBackstage.saveTbSchool02(data,callBack);
			else 
				secondBackstage.saveTbBranchschool02(data,callBack);
			
		}
		function cancel2(){
			KE.html('content1', "");
		}
		</script>
</head>
<body onload="dwrProjectTable()">
	<form name="fom" id="fom" method="post" action="">
			<input type="hidden" value="${loginAdmin.adminId}" id="adminId">
		<input type="hidden" value="${loginAdmin.tbSchool.schoolId}"
			id="adminSchoolId"> <input type="hidden"
			value="${loginAdmin.tbBranchschool.branchSchoolId}"
			id="adminBranchSchoolId"> <input type="hidden"
			value="${loginAdmin.adminRoot}" id="adminRoot"> <input
			type="hidden" value="${loginAdmin.isValid}" id="userinfoId">	
			<input type="hidden" id="schoolInfoId">		
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">学校简介信息</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<tr>
							<TD width="100%">
								<fieldset style="height:100%;">
									<legend>简介信息</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width:100%">
										<tr>
											<td colspan="2" align="center">
											<textarea id="content1" name="news.newsContent" style="width:700px; height:300px;visibility:hidden;" ></textarea>
											</td>
										</tr>

									</table>
									<br />
								</fieldset>
							</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
				<TR>
				<td align="center">
						<input type="button" name="Submit" value="修改"
								class="button" onclick="dataUpdate()" /> <input
								type="button" name="Submit2" value="重置" class="button"
								onclick="cancel2()" />
							</td>
				</TR>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
				<tr height="100px"><td>&nbsp;<td></tr>
			</TABLE>
		</form>
</body>
</html>
