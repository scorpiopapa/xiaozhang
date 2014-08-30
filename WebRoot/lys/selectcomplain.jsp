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
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/page.css" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<SCRIPT type="text/javascript">
	
	function deleteComplain(){
  	    var str="";
                 var complainId = "";
                     $("input:checked").each(function(){  
                              complainId += $(this).val()+",";
                              str = complainId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteComplain.action",{'str':str, 'a':Math.random()},function(){
					window.location.reload();
				});
				}   
			}
	</SCRIPT>
  
  <body>
   <center>
   		
   		<table id="subtree1" style="border-collapse:collapse;" width="100%"
						border="1" cellspacing="0" cellpadding="0">
				<tr class="CTitle">
					<td height="22" style="font-size:16px" colspan="7" align="center"
						>投诉列表</td>
				</tr>
   			<tr align="center"  bgcolor="#EEEEEE">
   				<td>操作</td>
   				<td>投诉人</td>
   				<td>处理人</td>
   				<td>学校</td>
   				<td>分校</td>
   				<td>投诉内容</td>
   				<td>投诉状态</td>
   			</tr>
   			<s:iterator value="listcomplain">
   			<tr align="center">
   				<td align="center"><input type="checkbox" id="complainId" value="<s:property value='complainId'/>"/>
   				</td>
   				<td><s:property value="tbUserinfoByUserInfoId.userInfoName"/></td>
   				<td>
   					<s:if test="tbUserinfoByTbUserInfoId.userInfoName==null">
   						暂无人处理
   					</s:if>
   					<s:else>
   						<s:property value="tbUserinfoByTbUserInfoId.userInfoName"/>
   					</s:else>
   				</td>
   				<td><s:property value="tbSchool.schoolName"/></td>
   				<td><s:property value="tbBranchschool.braschName"/></td>
				<td><a href="#"><s:property value="complainContent"/></a></td>
   				<td>
   					<s:if test="complainStatus==0">已回复</s:if>
   					<s:if test="complainStatus==1">未回复</s:if>
   				</td>
   				<td><s:property value="clanotSige"/></td>
   			</tr>
   		</s:iterator>
   			<tr>
   				<td align="center"  colspan="7"><font size="3"><a href="javascript:deleteComplain()">删除</a></font></td>
   			</tr>
   			<tr	class="TableData">
					<td align="center" colspan="7">${cyhPage }</td>
			</tr>
   		</table>
   </center>
  </body>
</html>
