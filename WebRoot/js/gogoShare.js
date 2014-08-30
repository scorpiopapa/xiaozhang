var countPerPage=10;
var currentPage;
$(document).ready(
		function() {
			//页面加载时启动
				//如果没登录，则跳回loginPage.jsp
			    var root=$("#adminRoot").val();
				if(root=="")
					top.location.href="login.jsp";
				if($("#dataTable").length>0)
				dwrProjectTable(1);//分页查询首页
				hiddenDiv();//隐藏各div
				//判断若为客户端，需要什么变法
				if(root==0){if($("#schoolFind").length>0)
					gogoServiceImpl.loadPage(0,null,loadPageCallBack);
				}
				if($("#adminRoot").val()==1){
					var schoolId= $("#adminSchoolId").val();
				if($("#schoolFind").length>0)
				gogoServiceImpl.loadPage(1,schoolId,loadPageCallBack);
				    var trs = $("tr[class='hid1']");   
			    for(i = 0; i < trs.length; i++){     
			            trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法   
			    }
			    var tds = $("td[class='hid1']");   
			    for(i = 0; i < tds.length; i++){     
			    	tds[i].style.display = "none";    
			    }
				}
				if($("#adminRoot").val()==2){
					var schoolId= $("#adminBranchSchoolId").val();
					if($("#branchschoolFind").length>0)
					gogoServiceImpl.loadPage(2,schoolId,loadPageCallBack);
				 	var trs = $("tr[class='hid1']");   
				    for(i = 0; i < trs.length; i++){     
				            trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法   
				    }
				    var tds = $("td[class='hid1']");   
				    for(i = 0; i < tds.length; i++){     
				    	tds[i].style.display = "none";    
				    }
					var trs = $("tr[class='hid2']");   
					for(i = 0; i < trs.length; i++){     
						trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法   
					}
					var tds = $("td[class='hid2']");   
					for(i = 0; i < tds.length; i++){     
						tds[i].style.display = "none";    
					}
				}
				//分页条件查找 学校下拉框，联动年段下拉框
				$("#schoolFind").live("change",function(){
					$("#branchschoolFind").empty();
					$("#branchschoolFind").append("<option value='请选择'>请选择</option>"); 
					$("#courseFind").empty();
					$("#courseFind").append("<option value='请选择'>请选择</option>"); 
					var schoolId=$("#schoolFind").val();
					var tbSchool={
							"schoolId":schoolId
					};
					if(null!=schoolId&&"请选择"!=schoolId){
						gogoServiceImpl.getTbSchoolTbGrad(tbSchool,schoolTbGradeCallBack);
					}
				});
				//分页条件查找 年段下拉框，联动班级下拉框
				$("#branchschoolFind").live("change",function(){
					$("#courseFind").empty();
					$("#courseFind").append("<option value='请选择'>请选择</option>"); 
					var gradeId=$("#branchschoolFind").val();
					var tbBranchschool = {
							"branchSchoolId" : gradeId
						};
					if(null!=gradeId&&"请选择"!=gradeId){
						gogoServiceImpl.getTbGradeTbClass(tbBranchschool,
								gradeClassCallBack);	
					}
				});	
				
				
			// 新增页面 总校下拉框，联动分校下拉框
			$("#citySchoolAdd").live(
					"change",
					function() {
						$("#schoolgradeAdd").empty();
						$("#schoolgradeAdd").append(
								"<option value='请选择'>请选择</option>");
						$("#gradeClassAdd").empty();
						$("#gradeClassAdd").append(
								"<option value='请选择'>请选择</option>");
						var schoolId = $("#citySchoolAdd").val();
						var tbSchool = {
							"schoolId" : schoolId
						};
						if (null != schoolId && "请选择" != schoolId) {
							gogoServiceImpl.getTbSchoolTbGrad(tbSchool,
									schoolTbGradeCallBackAdd);
						}
					});

			// 新增页面 分校下拉框 ，联动课程下拉框
			$("#schoolgradeAdd").live(
					"change",
					function() {
						$("#gradeClassAdd").empty();
						$("#gradeClassAdd").append(
								"<option value='请选择'>请选择</option>");
						var gradeId = $("#schoolgradeAdd").val();
						var tbBranchschool = {
							"branchSchoolId" : gradeId
						};
						if (null != gradeId && "请选择" != gradeId) {
							gogoServiceImpl.getTbGradeTbClass(tbBranchschool,
									gradeClassCallBackAdd);
						}
					});
			/*$("#gradeClassAdd").live(
					"change",
					function() {
						$("#classStudentAdd").empty();
						$("#classStudentAdd").append(
								"<option value='请选择'>请选择</option>");
						var courseId = $("#gradeClassAdd").val();
						var course = {
							"courseId" : courseId,
						};
						if (null != courseId && "" != courseId
								&& "请选择" != courseId)
							gogoServiceImpl.courseToStudent(course,
									courseToStudentCallBackAdd);
					});*/
		});

// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


var allPages;
var userinformationId;

// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function find(){
	//声明分页学校条件查询方法
		var schoolId=$("#branchschoolFind").val();
		if(schoolId!=null&&schoolId!="请选择")
		dwrProjectTable(1);
		else
		alert("请选定分校后查询");
}

function checkForm() { 
	var pass = true; 
	$("font:contains('*')").prev().each(function(){ 
		text = $(this).parent().prev().text(); 
	if(this.value == ''||this.value=='请选择') { 
	alert(text+"是必填项"); 
	this.focus(); 
	pass = false; 
	return false;//跳出each 
	} 
	}); 
	return pass; 
	}  
//分页查询 学校下拉框，联动年段下拉框的回调
function schoolTbGradeCallBack(data){
	$("#branchschoolFind").empty();
	if (data == "" || data == undefined)
		$("#branchschoolFind").append("<option value='请选择'>请选择</option>");
	for ( var property in data) {
		$("#branchschoolFind").append(
				"<option value='" + data[property].branchschoolId + "'>"
						+ data[property].branchschoolName + "</option>");
	}
	if($("#courseFind").length>0){
	if(data[0]!=undefined){
	var tbBranchschool={
			"branchschoolId":data[0].branchschoolId
	};
	if(null!=data[0].branchschoolId&&""!=data[0].branchschoolId&&"请选择"!=data[0].branchschoolId){
		gogoServiceImpl.getTbGradeTbClass(tbBranchschool,gradeClassCallBack);
	}}}
}
function gradeClassCallBack(data){
	if($("#courseFind").length>0){
	$("#courseFind").empty();
	if (data == "" || data == undefined)
		$("#courseFind").append("<option value='请选择'>请选择</option>");
	for ( var property in data) {
		$("#courseFind").append(
				"<option value='" + data[property].courseId + "'>"
						+ data[property].courseName + "</option>");
	}
	}
}
function loadPageCallBack(data){
	if($("#adminRoot").val()!=2){
		if($("#schoolFind").length>0){
			$("#schoolFind").empty();
			if (data == "" || data == undefined)
				$("#schoolFind").append("<option value='请选择'>请选择</option>");
			for ( var property in data) {
				$("#schoolFind").append(
						"<option value='" + data[property].schoolId + "'>"
								+ data[property].schoolName + "</option>");
			}
			var school={
					'schoolId':data[0].schoolId
			};
			if (null != data[0].schoolId && "" != data[0].schoolId
					&& "请选择" != data[0].schoolId)
		gogoServiceImpl.getTbSchoolTbGrad(school,schoolBranchschoolCallBack);
		}
	}else{
		if($("#branchschoolFind").length>0){
			$("#branchschoolFind").empty();
			if (data == "" || data == undefined)
				$("#branchschoolFind").append("<option value='请选择'>请选择</option>");
			for ( var property in data) {
				$("#branchschoolFind").append(
						"<option value='" + data[property].branchschoolId + "'>"
								+ data[property].branchschoolName + "</option>");
			}
		}
	}
}
function schoolBranchschoolCallBack(data){
	if($("#branchschoolFind").length>0){
	$("#branchschoolFind").empty();
	if (data == "" || data == undefined)
		$("#branchschoolFind").append("<option value='请选择'>请选择</option>");
	for ( var property in data) {
		$("#branchschoolFind").append(
				"<option value='" + data[property].branchschoolId + "'>"
						+ data[property].branchschoolName + "</option>");
	}
	if(data[0]!=undefined){
		if($("#courseFind").length>0){
		var tbBranchschool={
				"branchschoolId":data[0].branchschoolId
		};
		if(null!=data[0].branchschoolId&&""!=data[0].branchschoolId&&"请选择"!=data[0].branchschoolId){
			gogoServiceImpl.getTbGradeTbClass(tbBranchschool,gradeClassCallBack);
		}
		}}
}	}


// 新增页面Start++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// 第二块二级联动省市下拉框，省市级框变动带动学校下拉框产生的回调

function citySchoolCallBackAdd(data) {
	if ($("#citySchoolAdd").length > 0) {
		$("#citySchoolAdd").empty();
		if (data == "")
			$("#citySchoolAdd").append("<option value='请选择'>请选择</option>");
		for ( var property in data) {
			$("#citySchoolAdd").append(
					"<option value='" + data[property].schoolId + "'>"
							+ data[property].schoolName + "</option>");
		}
		if (data[0] != undefined) {
			var tbSchool = {
				"schoolId" : data[0].schoolId
			};
			if (null != data[0].schoolId && "" != data[0].schoolId
					&& "请选择" != data[0].schoolId) {
				gogoServiceImpl.getTbSchoolTbGrad(tbSchool,
						schoolTbGradeCallBackAdd);
			}
		}
	}
}
// 新增页面 学校下拉框，联动年段下拉框的回调
function schoolTbGradeCallBackAdd(data) {
	if ($("#schoolgradeAdd").length > 0) {
		$("#schoolgradeAdd").empty();
		if (data == "")
			$("#schoolgradeAdd").append("<option value='请选择'>请选择</option>");
		for ( var property in data) {
			$("#schoolgradeAdd").append(
					"<option value='" + data[property].branchschoolId + "'>"
							+ data[property].branchschoolName + "</option>");
		}
		if (data[0] != undefined) {
			var tbBranchschool = {
				"branchSchoolId" : data[0].branchschoolId
			};
			if (null != data[0].branchschoolId && "" != data[0].branchschoolId
					&& "请选择" != data[0].branchschoolId) {
				gogoServiceImpl.getTbGradeTbClass(tbBranchschool,
						gradeClassCallBackAdd);
				;
			}
		}
	}
}


// 新增页面 年段下拉框 ，联动班级下拉框的回调
function gradeClassCallBackAdd(data) {
	if ($("#gradeClassAdd").length > 0) {
		$("#gradeClassAdd").empty();
		if (data == "")
			$("#gradeClassAdd").append("<option value='请选择'>请选择</option>");
		for ( var property in data) {
			$("#gradeClassAdd").append(
					"<option value='" + data[property].courseId + "'>"
							+ data[property].courseName + "</option>");
		}
		if ($("#classStudentAdd").length > 0) {
			if (data[0] != undefined) {
				var course = {
					"courseId" : data[0].courseId
				};
				if (null != data[0].courseId && "" != data[0].courseId
						&& "请选择" != data[0].courseId)
					gogoServiceImpl.courseToStudent(course,
							courseToStudentCallBackAdd);
			}
		}
	}
}
function courseToStudentCallBackAdd() {
	if ($("#classStudentAdd").length > 0) {
		$("#classStudentAdd").empty();
		if (data == "" || data == undefined)
			$("#classStudentAdd").append("<option value='请选择'>请选择</option>");
		for ( var property in data) {
			$("#classStudentAdd").append(
					"<option value='" + data[property].userinfoId + "'>"
							+ data[property].userinfoName + "</option>");
		}
	}
}
// 新增结果提示
function dataAddCallBack(data) {
	alert(data);
	dwrProjectTable(1);
	cancel();
}
//修改方法的回调
function dataUpadateCallBack(data){
	alert(data);
	dwrProjectTable(1);
	cancel();
}
// 新增页面End++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

// 显示addDiv
function showAddDiv() {
	pageScroll();
	$("#addDiv").show();
}
// 显示upadteDiv
function showUpadteDiv() {
	pageScroll();
	$("#upadteDiv").show();
}
// 显示findDiv
function showFinddiv() {
	pageScroll();
	$("#findDiv").show();
}
function pageScroll() {
	window.scrollTo(0, 0);
	//	window.scrollBy(0,-10); 
}
// 隐藏div
function hiddenDiv() {
	$("#addDiv").hide();
	$("#upadteDiv").hide();
	$("#findDiv").hide();
	$("#findDiv2").hide();
	$("#findDiv3").hide();
}
function dataPage(data) {
	var reg = /^\d+$/;
	if (data == "start") {
		dwrProjectTable(1);
	}
	if (data == "up") {
		if (currentPage >= 2) {
			currentPage = parseInt(currentPage) - 1;
			dwrProjectTable(currentPage);
		}
	}
	if (data == "down") {
		if (currentPage < allPages) {
			currentPage = parseInt(currentPage) + 1;
			dwrProjectTable(currentPage);
		}
	}
	if (data == "end") {
		dwrProjectTable(allPages);
	}
	if (data == "currentPage") {
		currentPage = $("#currentPage").val();
		if (reg.test(currentPage)&&currentPage<allPages)
			dwrProjectTable(currentPage);
		else
			alert("特定页必须为数字而且小于总页！");
	}
}

function selectAll(data) {
	$("input[name="+data+"]").each(function() {
		this.checked = true;
	});
}
function unselectAll(data) {
	$("input[name="+data+"]").each(function() {
		this.checked = false;
	});
}
function callBack(data){
	alert(data);
	if(data.indexOf('成功')>0){
		dwrProjectTable(1);
		cancel();
	}else
	cancel();
}
function dataIsNull(data) {
	if (data == null || data.currentList.length == 0) {
		alert("此次查询无相关记录");
	}
}
function cancel() {
	document.fom.reset();
	hiddenDiv();
}
// 删除页面start++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// 删除方法
function dataDel(obj) {
	var r = confirm("你确定删除吗？");
	var dataIds = new Array();
	var num = 0;
	if (r == true) {
		$(":checkbox:checked").each(function() {
			dataIds[num] = $(this).val();
			num++;
		});
		if (num > 0)
			gogoServiceImpl.deleteObjects(obj, dataIds, dataDeleteCallBack);
		else
			alert("请确定选择，后再做删除操作!");
	}
}
// 删除方法的回调函数
function dataDeleteCallBack(data) {
	if ("success" == data) {
		$("input:checked").parent().parent().remove();
		alert("删除成功");
		dwrProjectTable(1);
	} else if ("fail" == data)
		alert("删除失败");
}
// 删除页面End++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

// 产生分页特定页下拉框
function pageSelect(data) {
	$("#pageAllCount").text(data.allPages);
	$("#pageCurrentPage").text(data.currentPage);
	allPages = data.allPages;
	currentPage = data.currentPage;
	DWREngine.setAsync(false);
}
function checkRoot(){
	   var root=$("#adminRoot").val();
		if(root=="")
		  window.location.href="login.jsp";
}
Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1,
        // month
        "d+": this.getDate(),
        // day
        "h+": this.getHours(),
        // hour
        "m+": this.getMinutes(),
        // minute
        "s+": this.getSeconds(),
        // second
        "q+": Math.floor((this.getMonth() + 3) / 3),
        // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
