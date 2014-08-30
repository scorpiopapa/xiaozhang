<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html>
<head>
<title>uploadify-实例</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 %>
<meta content="http://schemas.microsoft.com/intellisense/ie5" name="vs_targetSchema">
<link href="uploadify/css/default.css" rel="stylesheet" type="text/css" />
<link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.v2.1.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 	var root=$("#adminRoot").val();
		if(root=="")
			top.location.href="login.jsp";
	$("#uploadify").uploadify({
		'uploader'       : 'js/uploadify.swf',
		'script'         : 'upload.jsp',
		'cancelImg'      : 'js/cancel.png',
		'buttonImg'		 : 'js/xzButton.gif',
		'folder'         : '/images/nomal',
		'queueID'        : 'fileQueue',
		'auto'           : false,
		'multi'          : true,
		'wmode'			 : 'transparent',
		'simUploadLimit' : 999,
		'fileExt'		 : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
		'fileDesc'		 : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
		'onSelectOnce' : function(event,data,data)
        {
          filesSelected:true;
        },
        'onComplete' : function(event,queueId,fileObj,response,data)
        {
        	var inputDom=$("<input type='hidden' name='hiddenDom' value='"+response+"' />");
        	var p='<%=basePath%>'+response;
            var $image=$("<img src='"+p+"'  width='500px' height='300px'/>");
        	inputDom.appendTo("#box");
        	$image.appendTo("#box");
        },
		'onAllComplete' : function(event,data) 
		{
		   alert('一共上传了'+data.filesUploaded+'张图片');
		}  
	});
});
function submitPhoto(){
	document.fom.submit();
}
</script>
	
<style type="text/css">
.inputcss
{
	color:#333333;
	font-family: "Tahoma"; 
	font-size: 12px; 
	border:solid 1px #CCCCCC;
}
.buttoncss
{
	color:#333333;
	font-family: "Tahoma"; 
	font-size: 12px; 
	background-color:#FFFFFF;
	border:solid 1px #CCCCCC;
}
</style>
</head>
<body>
		<div style="width: 90%;">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">上传图片</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>学校相册</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
											<tr>
												<td style="width: 100px;">
													<input type="file" name="uploadify" id="uploadify" />
												</td>
												<td align="left">
													<a href="javascript:$('#uploadify').uploadifyUpload()" >开始上传</a>|
												<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()" >取消上传</a>|<a href="#" onclick="submitPhoto()">提交</a>
												<span id="result" style="font-size: 13px;color: red">注：先选择文件，再开始上传，最后出预览后再提交</span>
												</td>
											</tr>
										</table>
										<br />
									<div id="fileQueue" style="width:700px;height: 100px; border: 2px solid green;"></div>
									</fieldset>
								</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
				
			</TABLE>
		</div>
		<form action="servlet/GogoServlet" method="post" name="fom" id="fom">
		   <div id="box"></div>
		   <input type="hidden" value="${loginAdmin.adminId}" id="adminId" name="adminId">
			<input type="hidden" value="${loginAdmin.tbSchool.schoolId}"
			id="adminSchoolId" name="schoolId"> <input type="hidden"
			value="${loginAdmin.tbBranchschool.branchSchoolId}"
			id="adminBranchSchoolId" name="branchSchoolId"> <input type="hidden"
			value="${loginAdmin.adminRoot}" id="adminRoot"> <input
			type="hidden" value="${loginAdmin.isValid}" id="userinfoId">
			<input type="hidden" name="action_flag" value="schoolPhoto"/> 
		</form>
</body>
</html>