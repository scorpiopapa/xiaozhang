//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
	gogoServiceImpl.getPageTbTestfinish(page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.testfinishId+'"/>';},
		 function(obj){return obj.testName;},
		 function(obj){return obj.userinfoName;},
		 function(obj){return obj.testfinishDate;},
		 function(obj){return obj.rightPercentage;},
		 function(obj){
			 if(obj.isfinish==1)
				 return "未完成";
			 else if(obj.isfinish==0)
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