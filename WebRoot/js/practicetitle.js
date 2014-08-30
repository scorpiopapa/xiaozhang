//分页查询方法
function dwrProjectTable(sum) {
	DWRUtil.removeAllRows("dataTable");
	var page1 = {
		'currentPage' : sum,
		'countPerPage' : 10
	};
	gogoServiceImpl.getPageTbPracticetitle(page1, pageCallBack);
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
										+ obj.practiceId + '"/>';
							},
							function(obj) {
								return obj.testName;
							},
							function(obj) {
								return obj.practiceTopic;
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
							},
							function(obj) {
								return '<input type="button" value="编辑" name="update" onClick="dataUpdatePage('+ obj.practiceId + ')"/>';
							} ], {
						escapeHtml : false
					});
}
function showAddDiv1() {
	gogoServiceImpl.getAllTest(AllTestCallBackAdd);
	$("#addDiv").show();
}
function AllTestCallBackAdd(data) {
	$("#testAdd").empty();
	if (data == "")
		$("#testAdd").append("<option value='请选择'>请选择</option>");
	for ( var property in data) {
		$("#testAdd").append(
				"<option value='" + data[property].testId + "'>"
						+ data[property].testName + "</option>");
	}
}
function dataUpdatePage(data){
	gogoServiceImpl.findObject("TbPracticetitle",data,updateObjecetPageCallBack);
}
function updateObjecetPageCallBack(data){
	$("#firstAttributeUpdate").text(data.practiceId);
	$("#secondAttributeUpdate").val(data.practiceTopic);
	$("#thirdAttributeUpdate").val(data.answer);
	showUpadteDiv();
}

function dataUpdate(){
	var dataWeb = {
			"practiceId" : $("#firstAttributeUpdate").text(),
			"practiceTopic" : $("#secondAttributeUpdate").val(),
			"answer" : $("#thirdAttributeUpdate").val()
		
	};
	gogoServiceImpl.updateTbPracticetitle("TbPracticetitle",dataWeb, dataUpadateCallBack);
}
// 新增方法
function dataAdd() {
	var titleAdd = $("#titleAdd").val();
	var testAdd = $("#testAdd").val();
	var answerAdd = $("#answerAdd").val();
	var obj = {
		'testId' : testAdd,
		'practiceTopic' : titleAdd,
		'answer' : answerAdd,
	};
	gogoServiceImpl.addTbPracticetitle("TbPracticetitle", obj, dataAddCallBack);
}