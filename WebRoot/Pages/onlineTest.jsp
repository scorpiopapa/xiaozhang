<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>在线练习</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery1.8.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	var historyanswer=$("#historyanswer").val();
	if(historyanswer!=""){
	$(":radio").attr("disabled","disabled");
	}
	})
	function addHistoryquestion(obj){
	var historyanswer=$("#historyanswer").val();
	$(":radio").attr("disabled","disabled");
/* 	 alert(historyanswer);
	alert(historyanswer==null||historyanswer=="");  */
	if(historyanswer==null||historyanswer==""){
	var answerOnly=$("#answerOnly").val();
	var practiceId=$("#practiceId").val();
	var userinfoId=$("#userinfoId").val();
	var testId=$("#testId").val();
	var nowTime=new Date();
	var trueOrFalse;
	if(answerOnly==obj)
	trueOrFalse=1;
	else
	trueOrFalse=0;
	/* alert(trueOrFalse); */
	var tbHistoryquestionWeb={
		'practiceId':practiceId,
		'userinfoId':userinfoId,
		'testId':testId,
		'answer':obj,
		'trueOrFalse':trueOrFalse
	};
	gogoServiceImpl.addTbHistoryquestion("TbHistoryquestion",tbHistoryquestionWeb,callbackAddTbHistoryquestion);
	}
	}
	function callbackAddTbHistoryquestion(data){
	var answerOnly=$("#answerOnly").val();
	var allCount=$("#allCount").val();
	var currentPage=$("#currentPage").text();
	var userinfoId=$("#userinfoId").val();
	var testId=$("#testId").val();
	var hisCount=$("#hisCount").val();
	 /* alert("data=="+data+"---------answerOnly"+"answerOnly"+"111"+(data=="添加成功"));  */
	
	if(data=="添加成功"){
	$("#answer").text(answerOnly);
	/* alert("allCount:"+allCount+"   -------------hisCount:"+hisCount); */
	if(allCount==(parseInt(hisCount)+1)){
	gogoServiceImpl.statisticsTbHistoryquestion(userinfoId,testId,callbackstatisticsTbHistoryquestion);
	}
	}
	}
	function callbackstatisticsTbHistoryquestion(data){
	alert("答对了"+data.countTrue+"个，错误了"+(data.allcount-data.countTrue)+"个，正确率："+data.rightPercentage);
	}
	</script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/interface/page.js"></script>
	<script type="text/javascript" src="dwr/interface/gogoServiceImpl.js"></script>
  </head>
  
  <body>
    <table>
    <tr><td colspan="3"><span id="currentPage">${currentPage}</span>、<input type="hidden" value="${tbPracticetitleWeb.practiceId}" id="practiceId"/>${tbPracticetitleWeb.practiceTopic}( )</td></tr>
  	 <c:forEach var="tbPracticeoptionWeb" items="${tbPracticetitleWeb.tbPracticeoptionWeb}">
    <tr>
    
    <td align="left">
    <c:choose>
    	<c:when test="${historyanswer==tbPracticeoptionWeb.praOption}">
    	 <input type="radio"  name="selectSubject" checked="checked" onclick="addHistoryquestion('${tbPracticeoptionWeb.praOption}')" value="${tbPracticeoptionWeb.praOption}">
    	</c:when>
    	<c:otherwise>
    	<input type="radio"  name="selectSubject"  onclick="addHistoryquestion('${tbPracticeoptionWeb.praOption}')" value="${tbPracticeoptionWeb.praOption}">
    	</c:otherwise>
    </c:choose>
   <c:out value="${tbPracticeoptionWeb.praOption}" />、<c:out value="${tbPracticeoptionWeb.optionContent}" /></td>
    </tr>
    </c:forEach>
    <tr><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td colspan="3" >
   
   	
    </td></tr>
    <%-- <input type="hidden" value="${userinfoId}" id="userinfoId"/> --%>
    <input type="hidden" value="${testId}" id="testId"/>
    <input type="hidden" value="${userinfoId}" id="userinfoId"/>
    <input type="hidden" value="${historyanswer}" id="historyanswer"/>
    <input type="hidden" value="${tbPracticetitleWeb.answer}" id="answerOnly"/>
    <input type="hidden" value="${allCount}" id="allCount"/>
    <input type="hidden" value="${hisCount}" id="hisCount"/>
    </table> 
     <div style="position:absolute;bottom:20%">正确答案：
    <c:choose>
    	<c:when test="${historyanswer!=''&&historyanswer!=null}">
    	 <span > ${tbPracticetitleWeb.answer}</span>
    	</c:when>
    	<c:otherwise>
  		  <span id="answer"></span>
    	</c:otherwise>
    </c:choose></div>
  </body>
</html>
