//分页查询方法
function dwrProjectTable(sum) {
	DWRUtil.removeAllRows("dataTable");
	var page1 = {
		'currentPage' : sum,
		'countPerPage' : 10
	};
	gogoServiceImpl.getPageTbSysconfig(page1, pageCallBack);
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
								return obj.systemType;
							},
							function(obj) {
								return obj.minVersion;
							},
							function(obj) {
								return obj.maxVersion;
							},
							function(obj) {
								return obj.time;
							},
							function(obj) {
								return obj.alterTime;
							},
							function(obj) {
								return '<input type="button" value="编辑" name="update" onClick="dataUpdatePage('
										+ obj.id + ')"/>';
							} ], {
						escapeHtml : false
					});
}
function showAddDiv1() {
	$("#addDiv").show();
}


/*// 新增方法
function dataAdd() {
	var versionIosAdd = $("#versionIosAdd").val();
	var versionAndroidAdd = $("#versionAndroidAdd").val();
	var obj = {
		'versionIos' : versionIosAdd,
		'versionAndroid' : versionAndroidAdd
	};
	gogoServiceImpl.addTbSysconfig("TbSysconfig", obj,dataAddCallBack);
}*/
function dataUpdatePage(obj) {
	gogoServiceImpl.getOneTbSysconfig(parseInt(obj), dataUpdatePageCallBack);
}
function dataUpdatePageCallBack(data) {
	$("#firstAttributeUpdate").text(data.id);
	$("#secondAttributeUpdate").val(data.minVersion);
	$("#thirdAttributeUpdate").val(data.maxVersion);
	showUpadteDiv();
}

function dataUpdate() {
	var dataWeb = {
		"id":$("#firstAttributeUpdate").val(),
		"minVersion" : $("#secondAttributeUpdate").val(),
		"maxVersion" : $("#thirdAttributeUpdate").val()
	};
	gogoServiceImpl.updateTbSysconfig("TbSystemconfig",dataWeb, dataUpadateCallBack);
}