//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
	gogoServiceImpl.getPageHistoryquestion(page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.hisqueId+'"/>';},
		 function(obj){return obj.testName;},
		 function(obj){return obj.practiceName;},
		 function(obj){return obj.userinfoName;},
		 function(obj){return obj.answer;},
		 function(obj){
			 if(obj.trueOrFalse==0)
				 return "错误";
			 else if(obj.root==1)
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

//单条记录查询
function dataFind(obj){
	webService.getTbAdminWeb(obj,dataFindCallBack);
}
//单条记录查询的回调函数
function dataFindCallBack(data){
	$("#firstAttributeFind").text(data.id);
	$("#secondAttributeFind").text(data.adminName);
	$("#thirdAttributeFind").text(data.adminPassword);
	$("#fourthAttributeFind").text(data.schoolName);
	if(data.root==0)
		$("#fifthAttributeFind").text("系统管理员");
	 else if(data.root==1)
		$("#fifthAttributeFind").text("学校管理员");
	$("#sixthAttributeFind").text(data.enrolTime);
	$("#seventhAttributeFind").text(data.alterTime);
	showFinddiv();
}
//新增方法
function dataAdd(){
	var value=$("#fifthAttributeAdd").val();
	var schoolName=$("#citySchoolAdd").find("option:selected").text();
	var adminPassword=$("#thirdAttributeAdd").val();
	var adminName=$("#secondAttributeAdd").val();
	var schoolId=$("#citySchoolAdd").val();
	var tbAdminExm={
			'schoolId':schoolId,
			'adminName':adminName,
			'adminPassword':adminPassword,
			"schoolName":schoolName,
			'root':value
	};
	var result=checkForm();
	if(result){
	webService.addTbAdmin(tbAdminExm,dataAddCallBack);
	}
}


//修改页面
function dataUpdatePage(obj){
	webService.getTbAdminWeb(parseInt(obj),dataUpdatePageCallBack);
}
function dataUpdatePageCallBack(data){
	$("#firstAttributeUpdate").text(data.id);
	$("#secondAttributeUpdate").val(data.adminName);
	$("#thirdAttributeUpdate").val(data.adminPassword);
	$("#schoolNameUpdate").text(data.schoolName);
	if(data.root==0)
		$("#fifthAttributeUpdate").text("系统管理员");
	 else if(data.root==1)
		$("#fifthAttributeUpdate").text("学校管理员");
	$("#sixthAttributeUpdate").text(data.enrolTime);
	$("#seventhAttributeUpdate").text(data.alterTime);
	showUpadteDiv();
}

//修改方法
function dataUpdate(){
	var tbAdminWeb = {
		"id" : $("#firstAttributeUpdate").text(),
		"adminName" : $("#secondAttributeUpdate").val(),
		"adminPassword" : $("#thirdAttributeUpdate").val()
	};
	alert("修改");
	webService.updateTbAdmin(tbAdminWeb,dataUpadateCallBack);
}