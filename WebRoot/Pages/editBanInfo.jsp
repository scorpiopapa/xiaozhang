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
<title>修改班信息</title>
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
	var $i= document.getElementById(id);
	if($i==undefined){
		alert("找不到id："+id);
		return false;
	}
	return $i;
}

function checkForm(){
	var branchs=$id("branchs").value;
	var subjects=$id("subjects").value;
	var lesson=$id("lesson").value;
	var courseName=$id("courseName").value;
	var courseScore=$id("courseScore").value;
	var totalStudyTime=$id("totalStudyTime").value;
	var phone=$id("phone").value;
	var studyCosts=$id("studyCosts").value;
	var textbookCost=$id("textbookCost").value;
	var studyTime=$id("studyTime").value;
	var images=$id("images").value.toLowerCase();
	var image1 =/^.+\.jpg$/;
	var image2 =/^.+\.gif$/;
	var image3 =/^.+\.png$/;
	var content = /^.+$/;
	if(branchs==""){
		alert("请选择分校！");
		return false;
	}
	if(subjects==""){
		alert("请选择科目！");
		return false;
	}
	if(lesson==""){
		alert("请选择课程");
		return false;
	}
	if(courseName==""){
		alert("请输入班名！");
		return false;
	}
	
	if(images!=""&&!image1.test(images)&&!image2.test(images)&&!image3.test(images)){
		alert("图片格式只能是jpg，gif，或png格式！");
		return false;
	}
	if(totalStudyTime==""){
		alert("请输入总学时！");
		return false;
	}
	if(studyTime==""){
		alert("请输入学习时间！");
		return false;
	}
	if(studyCosts==""){
		alert("请输入学习费用！");
		return false;
	}
	if(textbookCost==""){
		alert("请输入教材费用！");
		return false;
	}
	if(phone==""){
		alert("请输入咨询电话！");
		return false;
	}
}

function check()
{
	$id("aa").style.display="";
}
function getKecheng(subject) {
			if(subject=="")
				subject=0;
			var url = 'getKechengJson.action';
			var params = 'subjectid=' + subject;
			new Ajax.Request(url, {
				method : 'POST',
				parameters : params,
				onFailure : showError,
				onComplete : processLoginResponse
			})
			function processLoginResponse(response) {
				var objs = eval("(" + response.responseText + ")");
				var otions = $id("lesson");
				var count = otions.options.length;
				for ( var j = 0; j < count; j++) {
					otions.remove(0);
				}
				if(objs.kecheng.length==0){
					var selectObj = $id("lesson");
					selectObj.options[0] = new Option("==请选择==", "");
				}else{
				
					for ( var i = 0; i < objs.kecheng.length; i++) {
						var selectObj = $id("lesson");
						selectObj.options[selectObj.length] = new Option(
								objs.kecheng[i].lessonName, objs.kecheng[i].id);
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
  <form action="editBanInfoDo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >班信息修改页面</th>
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
				<legend>修改班信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					  	<td align="center" colspan="4">注：上传图片不大于200KB,建议640*288尺寸</td>
					  </tr>
					  <tr>
					    <td width="16%" align="right" nowrap="nowrap">所属总校:</td>
					    <td width="34%">
					    	<input class="text" style="width:154px" value="${schools.schoolName }" readonly/>
					    	<input type="hidden" name="schoolId" value="${schools.schoolId }"/>
					    </td>
					    <td width="16%" align="right" nowrap="nowrap">分校:</td>
					    <td width="34%">
					    	<select name="branch.branchSchoolId" id="branchs">
                          <option value="">==请选择==</option>
                          	<c:forEach items="${branchlist }" var="sl">
                          	<option value="${sl.branchSchoolId }" 
                          	<c:if test="${sl.branchSchoolId eq baninfo.tbBranchschool.branchSchoolId }">selected</c:if>
                          	>${sl.braschName }</option>
                          	</c:forEach>
                        </select><span class="red">*</span>
					    </td>
					  </tr>
					  <tr>
                        <td align="right">科目:</td>
					    <td><select name="subjectId" onchange="getKecheng(this.value);" id="subjects">
                          <option value="">==请选择==</option>
                          	<c:forEach items="${subject }" var="sl">
                          	<option value="${sl.subjectId }" 
                          	<c:if test="${sl.subjectId eq baninfo.tbSubject.subjectId }">selected</c:if>
                          	>${sl.subjectName }</option>
                          	</c:forEach>
                        </select><span class="red">*</span>
                        </td>
					    <td align="right">课程:</td>
					    <td><select name="lesson.id" id="lesson" >
                          <option value="${baninfo.tbLesson.id }">${baninfo.tbLesson.lessonName }</option>
                        </select><span class="red">*</span>
                        </td>
					    </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">班名:</td>
					    <td>
					    	<input class="text" name='kecheng.courseName' style="width:154px" id="courseName" value="${baninfo.courseName }"/>&nbsp;<span class="red">*</span>
					    	<input type="hidden" name="kecheng.courseId" value="${baninfo.courseId }" />
					    </td>
					    <td align="right">是否推荐:</td>
					    <td>
					    	<input type="radio" name="kecheng.isGood" value="0" 
					    	<c:if test="${baninfo.isGood eq 0 }"> checked </c:if>
					    	/>是
					    	<input type="radio" name="kecheng.isGood" value="1" 
							<c:if test="${baninfo.isGood eq 1 }"> checked </c:if>
							/>否
							<span class="red">*</span></td>
					  </tr>
					  <tr height="40">
					    <td align="right">年级:</td>
						<td colspan="3">
							<c:forEach items="${gradelist }" var="gl" varStatus="i">
								<input type="checkbox" name="grades" value="${gl.gradeId }" 
									<c:forEach items="${mygrades }" var="mg">
										<c:if test="${gl.gradeId eq mg }">checked</c:if>
									</c:forEach>
								/>${gl.gradeName }&nbsp;&nbsp;
							<c:if test="${i.count%10 eq 0 }"><br /></c:if>
						</c:forEach>
						</td>
					  </tr>
						<tr>
						<td align="right">入学要求:</td>
					    <td><input value="${baninfo.enterNeed }" type="text" class="text" name='kecheng.enterNeed' style="width:154px" id="enterNeed"/><span class="red">*</span></td> 
					    <td align="right">班图片:</td>
					    <td><input type="file" class="text" name='img' style="width:154px" id="images" onchange="fileChange(this,'images');"/></td>
					   </tr>
					  <tr>
					  	 <td align="right">总学时:</td>
					    <td><input value="${baninfo.totalStudyTime }" class="text" name='kecheng.totalStudyTime' id="totalStudyTime" style="width:154px"/><span class="red">*</span></td>
					    <td align="right">学习时间:</td>
					    <td><input value="${baninfo.studyTime }" type="text" class="text" name='kecheng.studyTime' style="width:154px" id="studyTime" onchange="fileChange(this,'images');"/><span class="red">*</span></td>
					  	</tr>
					  <tr>
					  	<td nowrap="nowrap" align="right">学习费用:</td>
					    <td><input value="${baninfo.studyCosts }" class="text" name='kecheng.studyCosts' style="width:154px" id="studyCosts"/><span class="red">*</span></td>
					    <td nowrap="nowrap" align="right">教材费用:</td>
					    <td><input value="${baninfo.textbookCost }" class="text" name='kecheng.textbookCost' id="textbookCost" style="width:154px"/><span class="red">*</span></td>
					  </tr>
					  <tr>
					  	<td align="right">辅导教材:</td>
					    <td><input value="${baninfo.courseBook }" type="text" class="text" name='kecheng.courseBook' style="width:154px" id="courseBook"/></td>
					    <td nowrap="nowrap" align="right">学期开始时间:</td>
					    <td><input value="${baninfo.startTerm }" class="text Wdate" name='kecheng.startTerm' id="startTerm" style="width:154px" onclick="WdatePicker()"/></td>
					  </tr>
					  <tr>
					  	<td nowrap="nowrap" align="right">学期结束时间:</td>
					    <td><input value="${baninfo.endTerm }" class="text Wdate" name='kecheng.endTerm' id="endTerm" style="width:154px" onclick="WdatePicker()"/></td>
					    <td align="right">咨询电话:</td>
					    <td><input value="${baninfo.phone }" type="text" class="text" name='kecheng.phone' style="width:154px" id="phone"/><span class="red">*</span></td>
					  </tr>
					  <tr>
					  	<td nowrap="nowrap" align="right">学后方向:</td>
					    <td><input value="${baninfo.direction }" class="text" name='kecheng.direction' id="direction" style="width:154px"/></td>
					    <td align="right">课程成绩:</td>
					    <td>
					    	<select name="kecheng.courseScore" id="courseScore">
					    		<option value="0" <c:if test="${baninfo.courseScore eq 0 }"> selected </c:if>>不限</option>
						    	<option value="1" <c:if test="${baninfo.courseScore eq 1 }"> selected </c:if>>优</option>
						    	<option value="2" <c:if test="${baninfo.courseScore eq 2 }"> selected </c:if>>良</option>
						    	<option value="3" <c:if test="${baninfo.courseScore eq 3 }"> selected </c:if>>中</option>
						    	<option value="4" <c:if test="${baninfo.courseScore eq 4 }"> selected </c:if>>差</option>
					    	</select>
					    </td>
					  </tr>
					  <tr height="30">
					  	<td align="right">状&nbsp;&nbsp;态:</td>
					  	<td>&nbsp;&nbsp;
					    	<input type="radio" name="kecheng.isValid" 
					    		<c:if test="${baninfo.isValid eq 1 }"> checked </c:if>
					    	 value="1"/>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<input type="radio" name="kecheng.isValid" 
					    		<c:if test="${baninfo.isValid eq 0 }"> checked </c:if>
					    	 value="0"/>&nbsp;&nbsp;无效
					    </td>
					    <td colspan="2">&nbsp;</td>
					  </tr>
					  <tr>
					  <td align="right">班图片:</td>
					  <td>&nbsp;</td>
					  <td>&nbsp;</td>
					  <td>&nbsp;</td>
					  </tr>
					   <tr>
					    <th colspan="4">
					    	<img height="288" src="${pageContext.servletContext.contextPath }/images/nomal/${baninfo.courseUrl }" title="${baninfo.courseUrl }"/>
					    </th>
					  </tr>
					  <tr>
					    <td align="right">课程介绍:</td>
					    <td colspan="3"><textarea name="kecheng.courseIntroduce" id="courseIntroduce" cols="100" rows="8">${baninfo.courseIntroduce }</textarea></td>
					 </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">课程特色:</td>
					    <td colspan="3"><textarea name="kecheng.courseGoodness" id="courseGoodness" cols="100" rows="8">${baninfo.courseGoodness }</textarea></td>
					 </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">学习目标:</td>
					    <td colspan="3"><textarea name="kecheng.studyGoal" id="studyGoal" cols="100" rows="8">${baninfo.studyGoal }</textarea></td>
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

