//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
//	alert(sum);
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
	gogoServiceImpl.getPageObject("TbViptime", page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.id+'"/>';},
		 function(obj){return obj.userinfoName;},
		 function(obj){return obj.branchschoolName;},
		 function(obj){return obj.startTime;},
		 function(obj){return obj.endTime;},
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