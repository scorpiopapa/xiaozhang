<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'selectlesson.jsp' starting page</title>
    
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
	
  	    function deleteLesson(){
  	    	var str="";
                 var id = "";
                     $("input:checked").each(function(){  
                              id += $(this).val()+",";
                              str = id;
                      });
             if(str==""){
			   		 alert("请选择您要删除的信息");
					return;
				}
			 if(window.confirm("你确定要删除吗？")){
				$.get("selectaction!deleteLesson.action",{'str':str, 'a':Math.random()},function(){
					window.location.reload();
				});
				}   
             }
	</SCRIPT>
  </head>
  
  <body>
  	<center>
	    <table class="tablestyle1" width="70%" border="1">
	    		<tr align="center" class="CTitle">
					<td height="22" style="font-size:16px" colspan="6" align="center">
						<h4>课程列表</h4>
					</td>
				</tr>
	    	<tr class="TableHeader2" bgcolor="#EEEEEE" align="center">
	    		<td>所属分校</td>
	    		<td>所属科目</td>
	    		<td>课程名称</td>
	    		<td>自定义倒序</td>
	    		<td>自定义倒序</td>
	    		<td>操作</td>
	    	</tr>
	    	
	    	<s:iterator value="listlesson">
		    	<tr align="center" class="TableHeader2">
		    		<td><s:property value="tbBranchschool.braschName"/></td>
		    	    <td><s:property value="tbSubject.subjectName"/></td>
		    		<td><s:property value="lessonName"/></td>
		    		<td><s:property value="defineSort"/></td>
		    		<td><s:property value="defineSort2"/></td>
		    		<td align="center">
  						<input type="checkbox" id="id" value="<s:property value='id'/>">/
  						<a href="selectaction!toupdateLesson.action?id=<s:property value='id'/>">修改</a>
  					</td>
		    	</tr>
	    	</s:iterator>
		    	<tr class="TableData" align="center" class="TableHeader2">
		  			<td align="center" colspan="6">
		  			<font size="3">
		  			<a href="selectaction!toaddLesson.action">添加</a>
		  			/<a href="javascript:deleteLesson()">删除</a></font>
		  			</td>
		  		</tr>
		  	<tr>
				<td align="center" colspan="6">${cyhPage }</td>
			</tr>
	    </table>
	    
    </center>
  </body>
</html>
