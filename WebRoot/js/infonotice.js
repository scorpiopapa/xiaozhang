//分页查询方法
function dwrProjectTable(sum) {
	DWRUtil.removeAllRows("dataTable");
	var page1 = {
		'currentPage' : sum,
		'countPerPage' : 10
	};
	gogoServiceImpl.getPageObject("TbInfonotice", page1,pageCallBack);
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
										+ obj.id + '"/>';
							},
					
							function(obj) {
								return obj.headline;
							},
							function(obj) {
								return obj.content;
							},
							function(obj) {
								return obj.adminName;
							},
							function(obj) {
								return '<a href="'+obj.pictrueUrl+'">'+obj.pictrueUrl+'<a>';
							},
							function(obj) {
								return obj.starttime;
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
										+ obj.id + ')"/>';
							} ], {
						escapeHtml : false
					});
}

function showAddDiv1() {
	gogoServiceImpl.getAllSchool(citySchoolCallBackAdd);
	$("#addDiv").show();
}

// 新增方法
function dataAdd() {
	var gradeClassAdd = $("#gradeClassAdd").val();
	var testAdd = $("#testAdd").val();
	var DateAdd = $("#DateAdd").val();
	var obj = {
		'courseId' : gradeClassAdd,
		'testName' : testAdd,
		'testDate' : DateAdd,
	};
	gogoServiceImpl.addTbTest("TbTest", obj, dataAddCallBack);
}