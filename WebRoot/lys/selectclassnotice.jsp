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
                 var classNoticeId = "";
                     $("input:checked").each(function(){  
                              classNoticeId += $(this).val()+",";
                              str = classNoticeId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteclassNotice.action",{'str':str, 'a':Math.random()},function(){
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
					<td height="22" style="font-size:16px" colspan="16" align="center"
						>班级通知列表</td>
				</tr>
   			<tr align="center" bgcolor="#EEEEEE">
   				<td>学校</td>
   				<td>分校</td>
   				<td>通知班级</td>
   				<td>发布人</td>
   				<td>通知标题</td>
   				<td>通知时间</td>
   				<td>通知内容</td>
   				<td>操作</td>
   			</tr>
   			<s:iterator value="listclassnotice">
   			<tr align="center">
   				<td width="10%"><s:property value="tbSchool.schoolName"/></td>
   				<td><s:property value="tbBranchschool.braschName"/></td>
   				<td><s:property value="tbCourse.courseName"/></td>
   				<td><s:property value="tbUserinfo.userInfoName"/></td>
				<td><s:property value="clanotTitle"/></td>
   				<td><s:property value="clanotAddTime"/></td>
   				<td><textarea rows="3" cols="120" readonly="readonly"><s:property value="clanotContent"/></textarea> </td>
				<td align="center"><input type="checkbox" id="classNoticeId" value="<s:property value='classNoticeId'/>">
   				</td>
   			</tr>
   		</s:iterator>
   			<tr>
   				<td align="center"  colspan="10"><font size="3"><a href="javascript:deleteUserinfo()">删除</a>/<a href="selectaction!toaddClassnotice.action">添加</a></font></td>
<!--   			<td align="center"><font size="3"><a href="${pageContext.request.contextPath }/lys/adduserinfo.jsp">添加</a></font></td>-->
   			</tr>
   			<tr	class="TableData">
				<td align="center" colspan="10">${cyhPage }</td>
			</tr>
   		</table>
   </center>
  </body>
</html>
