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
	
	function deleteUserinfo(){
			 var str="";
                 var reviewId = "";
                     $("input:checked").each(function(){  
                              reviewId += $(this).val()+",";
                              str = reviewId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteReview.action",{'str':str, 'a':Math.random()},function(){
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
					<td height="22" style="font-size:16px" colspan="6" align="center"
						>评语列表</td>
				</tr>
   			<tr align="center" bgcolor="#EEEEEE">
   				<td>操作</td>
   				<td>学生</td>
   				<td>老师</td>
   				<td>评语日期</td>
   				<td>评语内容</td>
   				<td>评语类型</td>
   			</tr>
   			<s:iterator value="listreview">
   			<tr align="center">
   				<td align="center"><input type="checkbox" id="reviewId" value="<s:property value='reviewId'/>">
   				</td>
   				<td><s:property value="tbUserinfoByUserInfoId.userInfoName"/></td>
   				<td><s:property value="tbUserinfoByTbUserInfoId.userInfoName"/></td>
   				<td><s:property value="reviewDate"/></td>
   				<td><s:property value="reviewContent"/></td>
				<td><s:property value="reviewType"/></td>
   			</tr>
   		</s:iterator>
   			<tr align="center">
   				<td colspan="6"><font size="3"><a href="javascript:deleteUserinfo()">删除</a></td>
   			</tr>
   			<tr	class="TableData">
					<td align="center" colspan="6">${cyhPage }</td>
			</tr>
   		</table>
   </center>
  </body>
</html>
