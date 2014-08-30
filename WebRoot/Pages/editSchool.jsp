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
<title>修改总校</title>
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
	var schoolName=$id("schoolName").value;
	var schoolMinName=$id("schoolMinName").value;
	var city=$id("city").value;
	var area=$id("area").value;
	var schoolPhone=$id("schoolPhone").value;
	var schoolAddress=$id("schoolAddress").value;
	var schoolBusWay=$id("schoolBusWay").value;
	var studentNum=$id("studentNum").value;
	var subSchoolNum=$id("subSchoolNum").value;
	var schoolCourse=$id("schoolCourse").value;
	var schoolIntroduce=$id("schoolIntroduce").value;
	var logo=$id("logo").value.toLowerCase();
	var images=$id("images").value.toLowerCase();
	var image1 =/^.+\.jpg$/;
	var image2 =/^.+\.gif$/;
	var image3 =/^.+\.png$/;
	var content = /^.+$/;
	if(!content.test(schoolName)){
		alert("请输入总校名称！");
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
	if(!content.test(schoolAddress)){
		alert("请输入地址！");
		return false;
	}
	
	if(!/^.+$/.test(schoolPhone)){
		alert("请输入电话号码！");
		return false;
	}
	if(images!=""&&!image1.test(images)&&!image2.test(images)&&!image3.test(images)){
		alert("总校图片格式只能是jpg，gif，或png格式！");
		return false;
	}
	if(!/^\d+$/.test(subSchoolNum)){
		alert("请输入分校数量！");
		return false;
	}
	if(!/^\d+$/.test(studentNum)){
		alert("请输入学生数量！");
		return false;
	}
	if(logo!=""&&!image1.test(logo)&&!image2.test(logo)&&!image3.test(logo)){
		alert("logo图片格式只能是jpg，gif，或png格式！");
		return false;
	}	
	if(schoolCourse!=""&&schoolCourse.length>500){
		alert("开设课程字数不大于500！(含空格)！");
		return false;
	}
	if(schoolIntroduce!=""&&schoolIntroduce.length>500){
		alert("总校简介字数不大于500！(含空格)！");
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
				var otions = document.getElementById("area");
				var count = otions.options.length;
				for ( var j = 0; j < count; j++) {
					otions.remove(0);
				}
				if(objs.town.length==0){
					var selectObj = document.getElementById("area");
					selectObj.options[0] = new Option("==请选择==", "");
				}else{
				
					for ( var i = 0; i < objs.town.length; i++) {
						var selectObj = document.getElementById("area");
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
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>

</head>

<body class="ContentBody">
  <form action="editSchoolDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >总校修改页面</th>
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
				<legend>修改总校信息</legend>
					 <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					  	<td align="center" colspan="4">注：上传图片不大于200KB,建议640*288尺寸</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">总校名称:</td>
					    <td width="34%">
					    	<input class="text" name='school.schoolName' style="width:154px" id="schoolName" value="${school.schoolName }"/>&nbsp;<span class="red">*</span>
					    	<input type="hidden" name="school.schoolId" value="${school.schoolId }"/>
					    </td>
					    <td nowrap="nowrap" align="right">总校简称:</td>
					    <td><input class="text" name='school.schoolMinName' style="width:154px" id="schoolMinName" value="${school.schoolMinName }"/>&nbsp;</td>
					  </tr>
					  <tr>
                        <td align="right">城市:</td>
					    <td><select name="cityId" onchange="getArea(this.value);" id="city">
                          <option value="">==请选择==</option>
                          <c:forEach items="${cityList }" var="cl">
                          <option value="${cl.cityId }" 
                          <c:if test="${cl.cityId eq cityid }">selected</c:if>
                           >${cl.cityName }</option>
                          </c:forEach>
                        </select><span class="red">*</span>
                        </td>
					    <td align="right">地区:</td>
					    <td><select name="townId" id="area" >
                          <option value="">==请选择==</option>
                          <c:forEach items="${townList }" var="tl"> 
                          <option value="${tl.townId }" 
                          <c:if test="${tl.townId eq school.tbTown.townId }">selected</c:if>
                           >${tl.townName }</option>
                          </c:forEach>
                        </select><span class="red">*</span>
                        </td>
					    </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">总校电话:</td>
					    <td><input class="text" name='school.schoolPhone' style="width:154px" id="schoolPhone" value="${school.schoolPhone }"/>&nbsp;<span class="red">*</span></td>
					    <td align="right">总校地址:</td>
					    <td><input class="text" name='school.schoolAddress' style="width:154px" id="schoolAddress" value="${school.schoolAddress }" />&nbsp;<span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">总校公交路线:</td>
					    <td><input class="text" name='school.schoolBusWay' style="width:154px" id="schoolBusWay" value="${school.schoolBusWay }"/>&nbsp;</td>
					    <td align="right">总校停车位置:</td>
					    <td><input class="text" name='school.schoolStopLocation' style="width:154px" value="${school.schoolStopLocation }"/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">联系人:</td>
					    <td><input class="text" name='school.linemanName' style="width:154px" value="${school.linemanName }"/></td>
					    <td align="right">总校图片:</td>
					    <td><input type="file" class="text" name='img' style="width:154px" id="images" onchange="fileChange(this,'images');"/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">联系电话:</td>
					    <td><input class="text" name='school.linemanPhone' style="width:154px" value="${school.linemanPhone }"/></td>
					    <td align="right">Email:</td>
					    <td><input type="text" class="text" name='school.linemanEmail' style="width:154px" id="images" value="${school.linemanEmail }"/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">QQ:</td>
					    <td><input class="text" name='school.linemanQq' style="width:154px" value="${school.linemanQq }"/></td>
					    <td nowrap="nowrap" align="right">校长:</td>
					    <td><input class="text" name='school.rectorName' style="width:154px" value="${school.rectorName }"/></td>
					  </tr>
					  <tr>
					    <td align="right">校长电话:</td>
					    <td><input type="text" class="text" name='school.rectorPhone' style="width:154px" id="images" value="${school.rectorPhone }"/></td>
					  	<td nowrap="nowrap" align="right">校长Email:</td>
					    <td><input class="text" name='school.rectorEmail' style="width:154px" value="${school.rectorEmail }"/></td>
					  </tr>
					  <tr>
					    <td align="right">校长QQ:</td>
					    <td><input type="text" class="text" name='school.rectorQq' style="width:154px" id="images" value="${school.rectorQq }"/></td>
					  	<td nowrap="nowrap" align="right">分校数量:</td>
					    <td><input class="text" name='school.subSchoolNum' style="width:154px" value="${school.subSchoolNum }" id="subSchoolNum"/><span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">学生人数:</td>
					    <td><input class="text" name='school.studentNum' style="width:154px" value="${school.studentNum }" id="studentNum"/><span class="red">*</span></td>
					  	<td align="right">营业执照:</td>
					    <td><input type="text" class="text" name='school.schoolCertificate' style="width:154px" value="${school.schoolCertificate }"/><span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">单位:</td>
					    <td><input class="text" name='school.schoolUnit' style="width:154px" value="${school.schoolUnit }"/></td>
					  	<td align="right">总校logo:</td>
					    <td><input type="file" class="text" name='logo' style="width:154px" id="logo" onchange="fileChange(this,'logo');"/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">总校网址:</td>
					    <td><input class="text" name='school.schoolUrl' style="width:154px" value="${school.schoolUrl }"/></td>
					  	<td nowrap="nowrap" align="right">总校状态:</td>
					    <td>&nbsp;&nbsp;
					    	<input type="radio" name="school.isValid" 
					    		<c:if test="${school.isValid eq 1 }"> checked </c:if>
					    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<input type="radio" name="school.isValid" 
					    		<c:if test="${school.isValid eq 0 }"> checked </c:if>
					    	 value="0"/>&nbsp;&nbsp;无效
					    </td>
					  </tr>
					  <tr>
					  <td align="right">总校图片:</td>
					  <td>&nbsp;</td>
					  <td>&nbsp;</td>
					  <td>&nbsp;</td>
					  </tr>
					   <tr>
					    <th colspan="4">
					    	<img height="288" src="${pageContext.servletContext.contextPath }/images/nomal/${school.schoolPicture }" title="${school.schoolPicture }"/>
					    </th>
					  </tr>
					  <tr>
					  <td align="right">总校logo图片:</td>
					  <td>&nbsp;</td>
					  <td>&nbsp;</td>
					  <td>&nbsp;</td>
					  </tr>
					   <tr>
					    <th colspan="4">
					    	<img height="288" src="${pageContext.servletContext.contextPath }/images/nomal/${school.schoolLogo }" title="${school.schoolLogo }"/>
					    </th>
					  </tr>
					  <!-- 
					  <tr>
					    <td align="right">开设课程:</td>
					    <td colspan="3"><textarea name="school.schoolCourse" id="schoolCourse" cols="100" rows="8">${school.schoolCourse }</textarea></td>
					  </tr>
					   -->
					  <tr>
					  <td align="right">总校简介:</td>
					    <td colspan="3"><textarea name="school.schoolIntroduce" id="schoolIntroduce" cols="100" rows="8">${school.schoolIntroduce }</textarea></td>
					    </tr>
					  </table>
			  <br />
				</fieldset>			</TD>
		</TR>
		</TABLE>
	 </td>
  </tr>
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

