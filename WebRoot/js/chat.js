//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
//	alert(sum);
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
	gogoServiceImpl.getPageObject("TbChat", page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.chatId+'"/>';},
		 function(obj){return obj.userFromName;},
		 function(obj){return obj.userToName;},
		 function(obj){return obj.chatContent;},
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