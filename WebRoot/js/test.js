function result() {
	var result = $("#result").val();
	if (result != "" && result != null)
		alert(result);
	if($("#teacherCourseFind").length>0){
		var userinfoId=$("#userinfoId").val();
		gogoServiceImpl.getTeacherClass(userinfoId,getTeacherClassCallBack)
	}
}
function importTemplate(){
	window.location.href="util/testImport.xls";
}
function getTeacherClassCallBack(data){
	if($("#teacherCourseFind").length>0){
	$("#teacherCourseFind").empty();
	if (data == "" || data == undefined)
		$("#teacherCourseFind").append("<option value='请选择'>请选择</option>");
	for ( var property in data) {
		$("#teacherCourseFind").append(
				"<option value='" + data[property].courseId + "'>"
						+ data[property].courseName + "</option>");
	}
	}
	if($("#addClassId").length>0){
	$("#addClassId").empty();
	if (data == "" || data == undefined)
		$("#addClassId").append("<option value='请选择'>请选择</option>");
	for ( var property in data) {
		$("#addClassId").append(
				"<option value='" + data[property].courseId + "'>"
				+ data[property].courseName + "</option>");
	}
	}
}
function findCourse(){
	dwrProjectTable(1);
}
//分页查询方法
function dwrProjectTable(sum) {
	DWRUtil.removeAllRows("dataTable");
	var page1 = {
		'currentPage' : sum,
		'countPerPage' : 10
	};
	var courseId=$("#teacherCourseFind").val();
//	alert("courseId-->"+courseId);
	if(courseId!=""&&courseId!="请选择"&&courseId!=null)
	gogoServiceImpl.getPageTbClass("TbTest",page1,courseId,pageCallBack);
}
// 分页的回调函数
function pageCallBack(data) {
	pageSelect(data);
	DWRUtil
			.addRows(
					"dataTable",// tbody的ID
					data.currentList,
					[
							function(obj) {
								return '<input type="checkbox" name="items" value="'
										+ obj.testId + '"/>';
							},
							function(obj) {
								return obj.courseName;
							},
							function(obj) {
								return '<a style="color:Orange;cursor:pointer;" onclick="dataFind('
								+ obj.testId + ')" >'+ obj.testName+'</a>';
							},
							function(obj) {
								return obj.testDate;
							},
							function(obj) {
								if (obj.isValid == 1)
									return "正常";
								else if (obj.isValid == 0)
									return "备份";
							},
							function(obj) {
								return obj.time;
							},
							function(obj) {
								return obj.alterTime;
							},
							function(obj) {
								return '<input type="button" value="编辑" name="update" onClick="dataUpdatePage('+ obj.testId + ')"/><input type="button" value="导入" onClick="leadInPage('+ obj.testId +')"/><input type="button" value="学生作业完成情况" onClick="studentTest('+ obj.testId +')"/>';
							} ], {
						escapeHtml : false
					});
}
//学生作业完成情况表Start+++++++++++++++++++++++++++++++++++++++++
var testId2;
function studentTest(data){
	testId2=data;
	dwrProjectTable4(1);
	$("#findDiv4").show();
//	alert("-----");
}
//------------分页查询方法
function dwrProjectTable4(sum){
	DWRUtil.removeAllRows("dataTable4");
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
	if(testId2!=""&&testId2!="请选择"&&testId2!=null)
	gogoServiceImpl.getPageTbTestfinish("TbTestfinish",page1,testId2,pageCallBack4);
	}

var currentPage4;
var allPages4;
function pageSelect4(data) {
	$("#pageAllCount4").text(data.allPages);
	$("#pageCurrentPage4").text(data.currentPage);
	allPages4 = data.allPages;
	currentPage4 = data.currentPage;
	DWREngine.setAsync(false);
}
//--------分页的回调函数
function pageCallBack4(data){
		 pageSelect4(data);
		 DWRUtil.addRows(
		 "dataTable4",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items4" value="'+obj.testfinishId+'"/>';},
		 function(obj){return obj.testName;},
		 function(obj){return obj.userinfoName;},
		 function(obj){return obj.testfinishDate;},
		 function(obj){
			 return '<a style="color:Orange;cursor:pointer;" onclick="dataFind4('
				+ obj.userinfoId + ')" >'+ obj.rightPercentage+'</a>';
			 },
		 function(obj){
			 if(obj.isfinish==0)
				 return "未完成";
			 else if(obj.isfinish==1)
				 return "完成";
		 },
		 function(obj){
			 if(obj.isValid==1)
				 return "正常";
			 else if(obj.isValid==0)
				 return "备份";
			},
		 function(obj){return obj.time;},
		 function(obj){return obj.alterTime;},
		 ],
		 {
		 escapeHtml:false
		 }
		 );
	}
function dataPage4(data) {
	var reg = /^\d+$/;
	if (data == "start") {
		dwrProjectTable4(1);
	}
	if (data == "up") {
		if (currentPage4 >= 2) {
			currentPage4 = parseInt(currentPage4) - 1;
			dwrProjectTable4(currentPage4);
		}
	}
	if (data == "down") {
		if (currentPage4 < allPages4) {
			currentPage4 = parseInt(currentPage4) + 1;
			dwrProjectTable4(currentPage4);
		}
	}
	if (data == "end") {
		dwrProjectTable4(allPages4);
	}
	if (data == "currentPage") {
		currentPage4 = $("#currentPage4").val();
		if (reg.test(currentPage4)&&currentPage4<allPages4)
			dwrProjectTable4(currentPage4);
		else
			alert("特定页必须为数字而且小于总页！");
	}
}
//学生作业完成情况表END++++++++++++++++++++++++++++++++++++++++++++++++++++


//学生作业历史情况表Start+++++++++++++++++++++++++++++++++++++++++
var userinfoId5;
function dataFind4(data){
	userinfoId5=data;
	dwrProjectTable5(1);
	$("#findDiv5").show();
}
//------------分页查询方法
function dwrProjectTable5(sum){
	DWRUtil.removeAllRows("dataTable5");
	var page1={
			'currentPage':sum,
			'countPerPage':10
	};
	if(userinfoId5!=""&&userinfoId5!="请选择"&&userinfoId5!=null)
		gogoServiceImpl.getPageHistoryquestion("TbHistoryquestion",page1,userinfoId5,pageCallBack5);
}

var currentPage5;
var allPages5;
function pageSelect5(data) {
	$("#pageAllCount5").text(data.allPages);
	$("#pageCurrentPage5").text(data.currentPage);
	allPages5 = data.allPages;
	currentPage5 = data.currentPage;
	DWREngine.setAsync(false);
}
//--------分页的回调函数
function pageCallBack5(data){
	pageSelect5(data);
	 DWRUtil.addRows(
			 "dataTable5",//tbody的ID
			 data.currentList,
			 [
			 function(obj){return '<input type="checkbox" name="items" value="'+obj.hisqueId+'"/>';},
			 function(obj){return obj.testName;},
			 function(obj){return obj.userinfoName;},
			 function(obj){
				 return '<a style="color:Orange;cursor:pointer;" onclick="dataFind5('
					+ obj.practiceId + ')" >'+ obj.practiceName+'</a>';
				 },
			 function(obj){return obj.answer;},
			 function(obj){
				 if(obj.trueOrFalse==0)
					 return "错误";
				 else if(obj.trueOrFalse==1)
					 return "正确";
				},
			function(obj){
					 if(obj.isValid==1)
						 return "正常";
					 else if(obj.isValid==0)
						 return "备份";
					},
			 function(obj){return obj.time;},
			 function(obj){return obj.alterTime;}
			 ],
			 {
			 escapeHtml:false
			 }
			 );
}
function dataPage5(data) {
	var reg = /^\d+$/;
	if (data == "start") {
		dwrProjectTable5(1);
	}
	if (data == "up") {
		if (currentPage5 >= 2) {
			currentPage5 = parseInt(currentPage5) - 1;
			dwrProjectTable5(currentPage5);
		}
	}
	if (data == "down") {
		if (currentPage5 < allPages5) {
			currentPage5 = parseInt(currentPage5) + 1;
			dwrProjectTable5(currentPage5);
		}
	}
	if (data == "end") {
		dwrProjectTable5(allPages5);
	}
	if (data == "currentPage") {
		currentPage5 = $("#currentPage5").val();
		if (reg.test(currentPage5)&&currentPage5<allPages5)
			dwrProjectTable5(currentPage5);
		else
			alert("特定页必须为数字而且小于总页！");
	}
}
//学生作业历史情况表END++++++++++++++++++++++++++++++++++++++++++++++++++++


function showAddDiv1() {
	$("#addDiv").show();
	
}

function leadInPage(data){
	gogoServiceImpl.findObject("TbTest",data,findObjecetCallBack);
}
function leadIn(){
		if (confirm("是否确认导入"))  { 
			if(document.getElementById("testFile").value!="")
			document.fom.submit();
			else
			alert("导入文件不能为空！")
		}  
}
function dataUpdatePage(data){
	gogoServiceImpl.findObject("TbTest",data,updateObjecetPageCallBack);
}
function updateObjecetPageCallBack(data){
	$("#firstAttributeUpdate").text(data.testId);
	$("#testNameUpdate").val(data.testName);
	showUpadteDiv();
}

function dataUpdate(){
	var dataWeb = {
		"testId" : $("#firstAttributeUpdate").text(),
		"testName" : $("#testNameUpdate").val()
	};
	gogoServiceImpl.updateTbTest("TbTest",dataWeb, dataUpadateCallBack);
}

function findObjecetCallBack(data){
	$("#firstAttributeFind").text(data.testId);
	$("#testId").val(data.testId);
	$("#secondAttributeFind").text(data.courseName);
	$("#thirdAttributeFind").text(data.testName);
	$("#fifthAttributeFind").text(data.testDate);
	if (data.isValid == 1)
		$("#sixthAttributeFind").text("正常");
	else if (data.isValid == 0)
		$("#sixthAttributeFind").text("备份");
	$("#seventhAttributeFind").text(data.time);
	$("#eighthAttributeFind").text(data.alterTime);
	showFinddiv();
}
// 新增方法
function dataAdd() {
	checkRoot();
	var gradeClassAdd = $("#addClassId").val();
	var testAdd = $("#testAdd").val();
	var userinfoId = $("#userinfoId").val();
//	alert("userinfoId"+userinfoId);
	var obj = {
		'courseId' : gradeClassAdd,
		'userinfoId':userinfoId,
		'testName' : testAdd,
	};
	gogoServiceImpl.addTbTest("TbTest", obj, dataAddCallBack);
}



var testId;
function dataFind(data){
	testId=data;
	dwrProjectTable1(1);
	$("#findDiv2").show();
}
var currentPage1;
var allPages1;
function pageSelect1(data) {
	$("#pageAllCount1").text(data.allPages);
	$("#pageCurrentPage1").text(data.currentPage);
	allPages1 = data.allPages;
	currentPage1 = data.currentPage;
	DWREngine.setAsync(false);
}
//分页查询方法
function dwrProjectTable1(sum) {
	DWRUtil.removeAllRows("dataTable1");
	var page1 = {
		'currentPage' : sum,
		'countPerPage' : 10
	};
	if(testId!=""&&testId!="请选择"&&testId!=null)
		gogoServiceImpl.getPracticetitleBytestId("TbPracticetitle",page1,testId,pageCallBack1);
}
// 分页的回调函数
function pageCallBack1(data) {
	pageSelect1(data);
	DWRUtil
			.addRows(
					"dataTable1",// tbody的ID
					data.currentList,
					[
							function(obj) {
								return '<input type="checkbox" name="items1" value="'
										+ obj.practiceId + '"/>';
							},
							function(obj) {
								return obj.testName;
							},
							function(obj) {
								return '<a style="color:Orange;cursor:pointer;" onclick="dataFind3('
								+ obj.practiceId + ')" >'+ obj.practiceTopic+'</a>';
							},
							function(obj) {
								return obj.answer;
							},
							function(obj) {
								if (obj.isValid == 1)
									return "正常";
								else if (obj.isValid == 0)
									return "备份";
							},
							function(obj) {
								return obj.time;
							},
							function(obj) {
								return obj.alterTime;
							} ], {
						escapeHtml : false
					});
}

function dataPage1(data) {
	var reg = /^\d+$/;
	if (data == "start") {
		dwrProjectTable1(1);
	}
	if (data == "up") {
		if (currentPage1 >= 2) {
			currentPage1 = parseInt(currentPage1) - 1;
			dwrProjectTable1(currentPage1);
		}
	}
	if (data == "down") {
		if (currentPage1 < allPages1) {
			currentPage1 = parseInt(currentPage1) + 1;
			dwrProjectTable1(currentPage1);
		}
	}
	if (data == "end") {
		dwrProjectTable1(allPages1);
	}
	if (data == "currentPage") {
		currentPage1 = $("#currentPage1").val();
		if (reg.test(currentPage1)&&currentPage1<allPages1)
			dwrProjectTable1(currentPage1);
		else
			alert("特定页必须为数字而且小于总页！");
	}
}




var practiceId;
var boo;
function dataFind3(data){
	boo="findDiv2";
	practiceId=data;
	$("#findDiv2").hide();
	dwrProjectTable3(1);
	$("#findDiv3").show();
}
function dataFind5(data){
	boo="findDiv5";
	practiceId=data;
	$("#findDiv5").hide();
	$("#findDiv4").hide();
	dwrProjectTable3(1);
	$("#findDiv3").show();
}
function backfindDiv3(){
	if(boo=="findDiv2"){
	$("#findDiv3").hide();
	$("#findDiv2").show();
	}else if(boo=="findDiv5"){
		$("#findDiv3").hide();
		$("#findDiv5").show();
	}
}

//分页查询方法
function dwrProjectTable3(sum) {
	DWRUtil.removeAllRows("dataTable3");
	if(practiceId!=""&&practiceId!="请选择"&&practiceId!=null)
		gogoServiceImpl.getTbPracticeoptionByPracticeId("TbPracticeoption",practiceId,pageCallBack3);
}
// 分页的回调函数
function pageCallBack3(data) {
	DWREngine.setAsync(false);
	DWRUtil
	.addRows(
			"dataTable3",// tbody的ID
			data,
			[
					function(obj) {
						return obj.praOption;
					},
					function(obj) {
						return obj.optionContent;
					} ], {
				escapeHtml : false
			});
}
