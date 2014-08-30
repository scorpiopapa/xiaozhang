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
<title>添加分校</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
<script language="javascript">
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;   
function fileChange(target,id) {   
	    var fileSize = 0;        
	    if (isIE && !target.files) {    
		      var filePath = target.value;   
		      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		      
			  if(!fileSystem.FileExists(filePath)){
			     alert("附件不存在，请重新输入！");
				 var file=document.getElementById(id); 
		       	 file.outerHTML=file.outerHTML;
		       	 return;
			  }
		      var file = fileSystem.GetFile (filePath);
		      fileSize = file.Size;   
    	} else {   
	     	  fileSize = target.files[0].size; 
	    }  
	    
        var size = fileSize / 1024;   
        if(size>200){ 
       		 alert("附件大小不能大于200KB！"); 
       		 var file=document.getElementById(id); 
       		 file.outerHTML=file.outerHTML
        }  
        if(size<=0){
        	alert("附件大小不能为0KB！"); 
        	var file=document.getElementById(id); 
       		 file.outerHTML=file.outerHTML
        } 
     } 
</script>

<script language="JavaScript" type="text/javascript">


function $id(id){
	return document.getElementById(id);
}

function checkForm(){
	var schoolName=$id("schoolName").value;
	var braschName=$id("braschName").value;
	var city=$id("city").value;
	var area=$id("area").value;
	var braschMinName=$id("braschMinName").value;
	var braschAddress=$id("braschAddress").value;
	var braschPhone=$id("braschPhone").value;
	var braschIntroduce=$id("braschIntroduce").value;
	var images=$id("images").value.toLowerCase();
	var image1 =/^.+\.jpg$/;
	var image2 =/^.+\.gif$/;
	var image3 =/^.+\.png$/;
	var content = /^.+$/;
	if(schoolName==""){
		alert("请选择总校！");
		return false;
	}
	if(!content.test(braschName)){
		alert("请输入分校名称！");
		return false;
	}
	if(city==""){
		alert("请选择城市！");
		return false;
	}
	if(area==""){
		alert("请选择地区");
		return false;
	}
	if(!content.test(braschAddress)){
		alert("请输入地址！");
		return false;
	}
	if(braschPhone==""){
		alert("请输入电话号码！");
		return false;
	}
	if(images!=""&&!image1.test(images)&&!image2.test(images)&&!image3.test(images)){
		alert("图片格式只能是jpg，gif，或png格式！");
		return false;
	}
	if(braschIntroduce!=""&&braschIntroduce.length>500){
		alert("分校简介字数不大于500！(含空格)！");
		return false;
	}
}

function getArea(city) {
			var url = 'getTownJson.action';
			var params = 'cityId=' + city;
			new Ajax.Request(url, {
				method : 'POST',
				parameters : params,
				onFailure : showError,
				onComplete : processLoginResponse
			})
			function processLoginResponse(response) {
				var objs = eval("(" + response.responseText + ")");
				var otions = $id("area");
				var count = otions.options.length;
				for ( var j = 0; j < count; j++) {
					otions.remove(0);
				}
				if(objs.town.length==0){
					var selectObj = $id("area");
					selectObj.options[0] = new Option("==请选择==", "");
				}else{
				
					for ( var i = 0; i < objs.town.length; i++) {
						var selectObj = $id("area");
						selectObj.options[selectObj.length] = new Option(
								objs.town[i].townName, objs.town[i].townId);
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
  <form action="addBranchschoolDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >分校添加页面</th>
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
				<legend>添加分校</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					  	<td align="center" colspan="4">注：上传图片不大于200KB,建议640*288尺寸</td>
					  </tr>
					  <tr>
					    <td align="right">所属总校:</td>
					    <td>
					    <c:if test="${loginAdmin.adminRoot eq 0}">
					    <select name="schoolId" id="schoolName">
                          <option value="">==请选择==</option>
                          <c:forEach items="${schoollist }" var="sl">
                          <option value="${sl.schoolId }">${sl.schoolName }</option>
                          </c:forEach>
                        </select><span class="red">*</span>
                        </c:if>
                        <c:if test="${loginAdmin.adminRoot eq 1}">
                        	<input class="text" style="width:154px" value="${adminschool.schoolName }"   id="schoolName" readonly/>
					    	<input type="hidden" name="schoolId" value="${adminschool.schoolId }"/>
                        </c:if>
                        </td>
					    <td width="16%" align="right" nowrap="nowrap">分校名称:</td>
					    <td width="34%">
					    	<input class="text" name='tbBschool.braschName' style="width:154px" id="braschName"/>&nbsp;<span class="red">*</span>
					    	<input type="hidden" name="tbBschool.isValid" value="1"/>
					    </td>
					  </tr>
					  <tr>
                        <td align="right">城市:</td>
					    <td><select name="cityId" onchange="getArea(this.value);" id="city">
                          <option value="">==请选择==</option>
                          <c:forEach items="${citylist }" var="cl">
                          <option value="${cl.cityId }">${cl.cityName }</option>
                          </c:forEach>
                        </select><span class="red">*</span>
                        </td>
					    <td align="right">地区:</td>
					    <td><select name="townId" id="area" >
                          <option value="">==请选择==</option>
                        </select><span class="red">*</span>
                        </td>
					    </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">分校简称:</td>
					    <td><input class="text" name='tbBschool.braschMinName' style="width:154px" id="braschMinName"/></td>
					    <td align="right">分校地址:</td>
					    <td><input class="text" name='tbBschool.braschAddress' style="width:154px" id="braschAddress" />&nbsp;<span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">分校电话:</td>
					    <td><input class="text" name='tbBschool.braschPhone' style="width:154px" id="braschPhone"/>&nbsp;<span class="red">*</span></td>
					    <td align="right">分校公交路线:</td>
					    <td><input class="text" name='tbBschool.braschBusWay' style="width:154px"/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">分校停车位置:</td>
					    <td><input class="text" name='tbBschool.braschStopLocation' style="width:154px"/></td>
					    <td align="right">分校图片:</td>
					    <td><input type="file" class="text" name='img' style="width:154px" id="images" onchange="fileChange(this,'images');"/></td>
					  </tr>
					  <tr>
					    <td align="right">分校简介:</td>
					    <td colspan="3"><textarea name="tbBschool.braschIntroduce" id="braschIntroduce" cols="100" rows="8"></textarea></td>
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

