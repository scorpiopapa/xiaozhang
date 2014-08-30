<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addgrade.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css">
	<LINK rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
			function  text() {
				if(gradename()){
	  				document.myform.submit();
					}
			}
		
			function gradename(){
				var gradename = $('#gradename')[0];
				var a = gradename.value;
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
   		<s:form action="selectaction!addgrade.action" name="myform" method="post" theme="simple">
	    <table  class="tablestyle1" width="80%" >
	   		 <tr  class="CTitle">
				<td align="center" colspan="10">
					<h4>添加年级</h4>
				</td>
			</tr>
	    	<tr align="center" class="TableHeader2">
	    		<td>年&nbsp;&nbsp;级</td>
	    		<td><s:textfield name="tbgrade.gradeName" id="gradename"/></td>
	    	</tr>
	 		<tr>
	 		
	 			<td colspan="6" align="right">
	 			<input type="button" value="添加" onclick="text()" style="width:50px;height:25px;background:white;"/>
	 			<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
	 			</td>
	 		</tr>
	    </table>
	    </s:form>
    </center>
  </body>
</html>
