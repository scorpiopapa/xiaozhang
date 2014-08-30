<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'image.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function fnInit(){
	    for (i=0; i<document.all.length; i++)
	            document.all(i).unselectable = "on";
	}

	var sInitColor = null;
	function callColorDlg(){
	 
	 if (sInitColor == null)
	 var sColor = dlgHelper.ChooseColorDlg();//display color dialog box
	 
	 else
	    var sColor = dlgHelper.ChooseColorDlg(sInitColor);
	 
	    sColor = sColor.toString(16); //change decimal to hex
	 
	    //add extra zeroes if hex number is less than 6 digits
	    if (sColor.length < 6) {
	    var sTempString = "000000".substring(0,6-sColor.length);
	    sColor = sTempString.concat(sColor);
	    }
	   
	   
	  //change color of the text in the div
	  oDiv.style.color= sColor;
	  sInitColor = sColor;
	 
	}
	</script>

  </head>
  
  <body onload="fnInit()">
  <div STYLE="padding:10px; background-color:#eeeeee; border:2px solid #cccccc">
    <BUTTON ID="ofntColor" TITLE="Choose Font Color" onclick="callColorDlg()">Choose Font color</BUTTON>
    <br>
    <br>
    <div id=oDiv CONTENTEDITABLE ALIGN=left STYLE="height:100;
                background-color:white; font-size:15pt; font-family:Arial; padding:10;  
                border:2px inset #eeeeee; overflow:auto;">
    Click the button to choose a font color using the choose color dialog.
    </div>
    </div>
    <OBJECT id=dlgHelper CLASSID="clsid:3050f819-98b5-11cf-bb82-00aa00bdce0b" width="0px" height="0px">
    </OBJECT> <!-- 这里的object很重要,在后面的js文件中会用到-->
</body>
</html>
