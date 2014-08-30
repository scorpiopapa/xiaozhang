<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addcourse.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.8.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function seltown(){
			var town = document.getElementById("town").value;
			var branchSchool = document.getElementById("branchSchool");
			branchSchool.options.length=0;
			if(town!=0){
				var option = new Option("--请选择--","0");
				branchSchool.add(option);
			$.getJSON("selectaction!selectb.action",{"tbtown.townId":town,"a":Math.random()},function(aaa){
				for(var i=0;i<aaa.length;i++){
					var option = new Option(aaa[i].braschName,aaa[i].branchSchoolId);
					branchSchool.add(option);
				}
			});
			}else{
				var option = new Option("--请选择--","0");
				branchSchool.add(option);
				}
		}
		function selgrade(){
			var branchSchool = document.getElementById("branchSchool").value;
			var grade = document.getElementById("grade");
			grade.options.length=0;
			if(branchSchool!=0){
				
				var option = new Option("--请选择--","0");
				grade.add(option);
			$.getJSON("selectaction!selectg.action",{"tbbranchschool.branchSchoolId":branchSchool,"a":Math.random()},function(bbb){
				for(var i=0;i<bbb.length;i++){
					var option = new Option(bbb[i].gradeName,bbb[i].gradeId);
					grade.add(option);
				}
			});
			}else{
				var option = new Option("--请选择--","0");
				grade.add(option);
				}
		}
		
		
		
		function  text() {
			if(studyCosts() && textbookCost()){
  				document.myform.submit();
				}
		}
		
		function studyCosts(){
			var studyCosts = $('#studyCosts')[0];
			var a = studyCosts.value;
			var studyCostsTest=/^[1-9]+$/;
			if(a.length==0){
				alert("学习费用不能为空");
				return false;
			}
			if(!studyCostsTest.test(a)){
 				alert("学习费用只能是数字");
				return false;
	 		}
			return true;
		}
		function textbookCost(){
			var textbookCost = $('#textbookCost')[0];
			var a = textbookCost.value;
			var textbookCostTest=/^[1-9]+$/;
			if(a.length==0){
				alert("教材费用不能为空");
				return false;
			}
			if(!textbookCostTest.test(a)){
 				alert("教材费用只能是数字");
				return false;
	 		}
			return true;
		}
	</script>
  </head>
  
  <body>
    <center>
    	<h3>课程</h3>
    <s:form action="selectaction!addCourse.action" name="myform" method="post" theme="simple">
    	<table>
    		<tr>
    			<td>城区</td>
    			<td>
    				<select id="town" onchange="seltown()" name="tbCourse.tbTown.townId">
	   					<option value="0">--请选择--</option>
	   					<s:iterator value="listtown">
	   						<option value='<s:property value="townId"/>'><s:property value="townName"/></option>
	   					</s:iterator>
	   				</select>
	   			</td>
	   			<td>分校</td>
	   			<td>
   					<select id="branchSchool" onchange="selgrade()" name="tbCourse.tbBranchschool.branchSchoolId">
	   					<option value="0">--请选择--</option>
	   				</select>
		   		</td>
	   		</tr>
	   		<tr>
	   			<td>年级</td>
	   			<td>
   					<select id="grade" name="tbCourse.tbGrade.gradeId">
	   					<option value="0">--请选择--</option>
	   				</select>
		   		</td>
	   			<td>科目</td>
	   			<td>
	   				<s:select list="listsubjectinbranchschool" listKey="subjectId" listValue="tbSubject.subjectName" name="tbCourse.tbSubject.subjectName"
						 headerKey="" headerValue="--请选择--"></s:select>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>课程</td>
	   			<td><s:textfield name="tbCourse.courseName" id="courseName"/></td>
	   			<td>课程成绩</td>
	   			<td>
	   				<s:radio list="#{'0':'不限','1':'优','2':'良','3':'中','4':'差'}" value="'0'" name="tbCourse.courseScore" id="isGood"></s:radio>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>是否推荐</td>
	   			<td>
	   				<s:radio list="#{'0':'是','1':'否'}" value="'1'" name="tbCourse.isGood" id="isGood"></s:radio>
	   			</td>
	   			<td>课程介绍</td>
	   			<td><s:textarea name="tbCourse.courseIntroduce" id="courseIntroduce"/></td>
   			</tr>
	   		<tr>
	   			<td>课程特色</td>
	   			<td><s:textfield name="tbCourse.courseGoodness" id="courseGoodness"/></td>
	   			<td>入学要求</td>
	   			<td><s:textfield name="tbCourse.enterNeed" id="enterNeed"/></td>
	   		</tr>
	   		<tr>
	   			<td>学习目标</td>
	   			<td><s:textfield name="tbCourse.studyGoal" id="studyGoal"/></td>
	   			<td>辅导教材</td>
	   			<td><s:textfield name="tbCourse.courseBook" id="courseBook"/></td>
   			</tr>
	   		<tr>
	   			<td>学期开始时间</td>
	   			<td><s:textfield name="tbCourse.startTerm" id="startTerm" readonly="true" onclick="WdatePicker()"/></td>
	   			<td>学期结束时间</td>
	   			<td><s:textfield name="tbCourse.endTerm" id="endTerm" readonly="true" onclick="WdatePicker()"/></td>
	   		</tr>
	   		<tr>
	   			<td>学习时间</td>
	   			<td><s:textfield name="tbCourse.studyTime" id="studyTime"/></td>
	   			<td>总学时</td>
	   			<td><s:textfield name="tbCourse.totalStudyTime" id="totalStudyTime"/></td>
   			</tr>
	   		<tr>
	   			<td>学习费用</td>
	   			<td><s:textfield name="tbCourse.studyCosts" id="studyCosts"/></td>
	   			<td>教材费用</td>
	   			<td><s:textfield name="tbCourse.textbookCost" id="textbookCost"/></td>
	   		</tr>
	   		<tr>
	   			<td>学后方向</td>
	   			<td><s:textfield name="tbCourse.direction" id="direction"/></td>
	   			<td>咨询电话</td>
	   			<td><s:textfield name="tbCourse.phone" id="phone"/></td>
	   		</tr>
    		<tr>
    			<td colspan="4" align="right">
<!--    				<s:submit name="myform" value="添加" style="width:50px;height:25px;background:white;"></s:submit>-->
    				<input type="button" value="添加" onclick="text()" style="width:50px;height:25px;background:white;">
    				<input type="button" value="返回" onclick="window.history.back()" style="width:50px;height:25px;background:white;"/>
    			</td>
    		</tr>
    	</table>
    	</s:form>
    </center>
  </body>
</html>
