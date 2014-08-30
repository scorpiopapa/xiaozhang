//分页查询方法
function dwrProjectTable(sum) {
	DWRUtil.removeAllRows("dataTable");
	var page1 = {
		'currentPage' : sum,
		'countPerPage' : 10
	};
	gogoServiceImpl.getPageTbPracticeoption(page1, pageCallBack);
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
										+ obj.praoptId + '"/>';
							},
							function(obj) {
								return obj.practicetitleName;
							},
							function(obj) {
								return obj.praOption;
							},
							function(obj) {
								return obj.optionContent;
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
								return '<input type="button" value="编辑" name="update" onClick="dataUpdatePage('
										+ obj.praoptId + ')"/>';
							} ], {
						escapeHtml : false
					});
}
function showAddDiv1() {
	gogoServiceImpl.getAllTitle(AllTestCallBackAdd);
	$("#addDiv").show();
}
function AllTestCallBackAdd(data) {
	$("#optionAdd").empty();
	if (data == "")
		$("#optionAdd").append("<option value='请选择'>请选择</option>");
	for ( var property in data) {
		$("#optionAdd").append(
				"<option value='" + data[property].practiceId + "'>"
						+ data[property].practiceTopic + "</option>");
	}

}
function dataUpdatePage(data){
	gogoServiceImpl.findObject("TbPracticeoption",data,updateObjecetPageCallBack);
}
function updateObjecetPageCallBack(data){
	$("#firstAttributeUpdate").text(data.praoptId);
	$("#secondAttributeUpdate").val(data.praOption);
	$("#thirdAttributeUpdate").val(data.optionContent);
	showUpadteDiv();
}

function dataUpdate(){
	var dataWeb = {
			"praoptId" : $("#firstAttributeUpdate").text(),
			"praOption" : $("#secondAttributeUpdate").val(),
			"optionContent" : $("#thirdAttributeUpdate").val()
		
	};
	gogoServiceImpl.updateTbPracticeoption("TbPracticeoption",dataWeb, dataUpadateCallBack);
}
// 新增方法
function dataAdd() {
	var practicetitleId = $("#optionAdd").val();
	var praOption = $("#optionLogoAdd").val();
	var optionContent = $("#contentAdd").val();
	var obj = {
		'practicetitleId' : practicetitleId,
		'praOption' : praOption,
		'optionContent' : optionContent
	};
	gogoServiceImpl.addTbPracticeoption("TbPracticeoption", obj,dataAddCallBack);
}