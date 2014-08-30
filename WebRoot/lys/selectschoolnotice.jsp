<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectuserinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css">
	<LINK rel="stylesheet"
			href="${pageContext.request.contextPath }/css/page.css"
			type="text/css">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<SCRIPT type="text/javascript">
	
	function deleteschoolNotice(){
			 var str="";
                 var schoolNoticeId = "";
                     $("input:checked").each(function(){  
                              schoolNoticeId += $(this).val()+",";
                              str = schoolNoticeId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteschoolNotice.action",{'str':str, 'a':Math.random()},function(){
					window.location.reload();
			});
			}
  	    }
	</SCRIPT>
  
  <body>
   <center>
   		
   		<table id="subtree1" style="DISPLAY: " width="100%"
						border="1" cellspacing="0" cellpadding="0">
				<tr class="CTitle">
					<td height="22" style="font-size:16px" colspan="16" align="center"
						>学校通知列表</td>
				</tr>
   			<tr align="center"  bgcolor="#EEEEEE">
   				<td>操作</td>
   				<td>学校</td>
   				<td>分校</td>
   				<td>发布人</td>
   				<td>公告标题</td>
   				<td>公告内容</td>
   				<td>公告发布时间</td>
   			</tr>
   			<s:iterator value="listschoolnotice">
   			<tr align="center">
   				<td align="center"><input type="checkbox" id="schoolNoticeId" value="<s:property value='schoolNoticeId'/>">
<!--   					<a href="selectaction!toupdateSchoolnotice.action?schoolNoticeId=<s:property value='schoolNoticeId'/>">修改</a>-->
   				</td>
   				<td><s:property value="tbSchool.schoolName"/></td>
   				<td><s:property value="tbBranchschool.braschName"/></td>
   				<td><s:property value="tbUserinfo.userInfoName"/></td>
   				<td><s:property value="schnotTitle"/></td>
   				<td><s:property value="schnotContent"/></td>
   				<td><s:property value="schnotAddTime"/></td>
   			</tr>
   		</s:iterator>
   			<tr>
   				<td align="center" colspan="7"><font size="3"><a href="javascript:deleteschoolNotice()">删除</a>
<!--   				<a href="selectaction!toaddSchoolnotice.action">添加</a></font>-->
					</font>
   				</td>
   			</tr>
   			<tr	class="TableData">
					<td align="center" colspan="7">${cyhPage }</td>
			</tr>
   		</table>
   </center>
  </body>
</html>
