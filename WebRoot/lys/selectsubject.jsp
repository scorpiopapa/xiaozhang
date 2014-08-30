<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectsubject.jsp' starting page</title>
    
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
	
  	    function deleteSubject(){
  	    	var str="";
                 var subjectId = "";
                     $("input:checked").each(function(){  
                              subjectId += $(this).val()+",";
                              str = subjectId;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteSubject.action",{'str':str, 'a':Math.random()},function(){
					window.location.reload();
				});
				}   
             }
  	    
	</SCRIPT>
  </head>
  
  <body>
  	<center>
	    <table id="subtree1" style="DISPLAY: " width="70%"
						border="1" cellspacing="0" cellpadding="0">
	    		<tr align="center" class="CTitle">
					<td height="22" style="font-size:16px" colspan="2" align="center">
						<h4>科目列表</h4>
					</td>
				</tr>
	    	<tr align="center" bgcolor="#EEEEEE">
	    		<td>科目名称</td>
	    		<td>操作</td>
	    	</tr>
	    	
	    	<s:iterator value="listSubject">
		    	<tr align="center" class="TableHeader2">
		    		<td><s:property value="subjectName"/> </td>
		    		<td align="center">
  						<input type="checkbox" id="subjectId" value="<s:property value='subjectId'/>">/
  						<a href="selectaction!toupdateSubject.action?subjectId=<s:property value='subjectId'/>">修改</a>
  					</td>
		    	</tr>
	    	</s:iterator>
		    	<tr class="TableData" align="center" class="TableHeader2">
		  			<td align="center" colspan="2">
		  			<font size="3">
		  			<a href="selectaction!toaddsubject.action">添加</a>
		  			/<a href="javascript:deleteSubject()">删除</a></font>
		  			</td>
		  		</tr>
		  	<tr>
				<td align="center" colspan="2">${cyhPage }</td>
			</tr>
	    </table>
	    
    </center>
  </body>
</html>
