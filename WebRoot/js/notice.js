//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
//	alert(sum);
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
	gogoServiceImpl.getPageObject("TbNotice", page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.id+'"/>';},
		 function(obj){return obj.parentName;},
		 function(obj){return obj.issuerName;},
		 function(obj){return obj.noticeContent;},
		 function(obj){return obj.time;},
		 function(obj){return obj.alterTime;},
		 ],
		 {
		 escapeHtml:false
		 }
		 );
	}