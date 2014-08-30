//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
//	alert(sum);
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
	gogoServiceImpl.getPageObject("TbCharge", page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.id+'"/>';},
		 function(obj){return obj.branchschoolName;},
		 function(obj){return obj.chargeName;},
		 function(obj){return obj.vipCharge+"元";},
		 function(obj) {
				if (obj.isValid == 1)
					return "正常";
				else if (obj.isValid == 0)
					return "备份";
			},
		 function(obj){return obj.time;},
		 function(obj){return obj.alterTime;},
		 function(obj) {
				return '<input type="button" value="编辑" name="update" onClick="dataUpdatePage('
						+ obj.id + ')"/>';
			} 
		 ],
		 {
		 escapeHtml:false
		 }
		 );
	}
