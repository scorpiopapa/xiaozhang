<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectgrade.jsp' starting page</title>
    
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
	
  	 function deleteGrade(){
      			var str="";
                 var gradeId = "";
                     $("input:checked").each(function(){  
                              gradeId += $(this).val()+",";
                              str = gradeId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteGrade.action",{'str':str, 'a':Math.random()},function(){
					window.location.reload();
				});
				}   
	 }
	 
  </SCRIPT>
  </head>
  
  <body>
  <center>
	    <table id="subtree1" style="DISPLAY: " width="80%"
						border="1" cellspacing="0" cellpadding="0">
	   		 <tr align="center" class="CTitle">
				<td height="22" style="font-size:16px" colspan="4" align="center">
					<h4>年段信息</h4>
				</td>
			</tr>
	    	<tr align="center" bgcolor="#EEEEEE">
	    		<td>年级</td>
	    		<td>操作</td>
	    	</tr>
	    	<s:iterator value="listgrade">
	    	<tr  align="center" class="TableHeader2">
	    		<td><s:property value="gradeName"/></td>
	    		<td>
	    			<input type="checkbox" id="gradeId" value="<s:property value='gradeId'/>">
	    			/<a href="selectaction!toupdategrade.action?gradeId=<s:property value='gradeId'/>">修改</a>
	    		</td>
	    	</tr>
	    	</s:iterator>
	    		<tr height="20" class="TableData">
	   				<td align="right" colspan="4"><font size="4">
	   				<a href="javascript:deleteGrade()">删除</a>
	   				/<a href="selectaction!toaddgrade.action">添加</a>
	   				</font></td>
	   			</tr>
	   			<tr class="TableData">
					<td height="20" align="center" colspan="4">${cyhPage }</td>
			</tr>
	    </table>
    </center>
  </body>
</html>
