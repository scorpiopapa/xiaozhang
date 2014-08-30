<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addsubject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css">
	<LINK rel="stylesheet"
			href="${pageContext.request.contextPath }/css/page.css"
			type="text/css">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
			function  text() {
				if(subjectName()){
	  				document.myform.submit();
					}
			}
		
			function subjectName(){
				var subjectName = $('#subjectName')[0];
				var a = subjectName.value;
				if(a.length==0){
					alert("请输入数据");
					return false;
				}
				return true;
			}
		
		</script>
  </head>
  
  <body>
   		<center>
    <s:form action="selectaction!updateSubject.action" name="myform" method="post" theme="simple">
    	<table class="tablestyle1" >
    	<s:hidden name="tbsubject.subjectId"></s:hidden>
    		 <tr>
				<td align="center" colspan="2">
					<h4>添加科目</h4>
				</td>
			</tr>
    		<tr>
    			<td>科目名称</td>
    			<td><s:textfield name="tbsubject.subjectName" id="subjectName"/></td>
    		</tr>
    		<tr class="TableData">
				<td colspan="2" align="center">
				<input type="button" value="修改" onclick="text()" style="width:50px;height:25px;background:white;">
				<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
				</td> 
			</tr>
    	</table>
      </s:form>
    </center>
  </body>
</html>
	