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
  	<s:form action="selectaction!addLesson.action" name="myform" method="post" theme="simple">
	    <table>
	    		<tr>
					<td height="22" style="font-size:16px" colspan="4" align="center">
						<h4>课程列表</h4>
					</td>
				</tr>
	    	<tr>
	    		<td>所属分校</td>
	    		<td>
<!--	    		<s:iterator value="listbranchschool">-->
<!--	    		<s:property value="tbBranchschool.braschName"/>-->
<!--	    		</s:iterator>-->
<!--	    		</td>-->
	    		<s:select list="listbranchschool" listKey="branchSchoolId" listValue="braschName" name="tblesson.tbBranchschool.branchSchoolId"></s:select></td>
	    		<td>所属科目</td>
	    		<td><s:select list="listSubject" listKey="subjectId" listValue="subjectName" name="tblesson.tbSubject.subjectId"></s:select></td>
	    	</tr>
	    	<tr>
	    		<td>自定义倒序</td>
	    		<td><s:textfield name="tblesson.defineSort"/></td>
	    		<td>自定义倒序</td>
	    		<td><s:textfield name="tblesson.defineSort2"/></td>
	    	</tr>
	    	<tr>
	    		<td>课程名称</td>
	    		<td><s:textfield name="tblesson.lessonName"/></td>
	    	</tr>
		    	<tr>
		  			<td colspan="4" align="right">
		  			<s:submit name="myform" value="添加" style="width:50px;height:25px;background:white;"></s:submit>
		  			<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
		  			</td>
		  		</tr>
	    </table>
	    </s:form>
    </center>
  </body>
</html>
