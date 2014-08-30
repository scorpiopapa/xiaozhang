//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
	var page1={
			'currentPage':sum,
			'countPerPage':10
		};
		var billsNo=$("#billsNO").val();
		if(billsNo!=""&&billsNo!=null)
		gogoServiceImpl.getPageByBills("TbBills", page1,billsNo,pageCallBack);
		else
		gogoServiceImpl.getPageByBills("TbBills",page1,null,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.billsId+'"/>';},
		 function(obj){return '<a style="color:Orange" onClick="findPage('+ obj.billsId + ')">'+obj.outTradeNo+'<a>';},
		 function(obj){return obj.tradeNo;},
		 function(obj){return obj.branchschoolName;},
		 function(obj){return obj.userinfoName;},
		 function(obj){return obj.goodsName;},
		 function(obj){return obj.totalMonth+"月";},
		 function(obj){return obj.totalFee+"元";},
		 function(obj){return obj.time;},
		 function(obj){return obj.alterTime;},
		 ],
		 {
		 escapeHtml:false
		 }
		 );
	}
function findPage(data){
	gogoServiceImpl.findObject("TbBills",data,findObjecetCallBack);
}
function findObjecetCallBack(data){
	$("#firstAttributeFind").text(data.outTradeNo);
	$("#secondAttributeFind").text(data.branchschoolName);
	$("#thirdAttributeFind").text(data.userinfoName);
	$("#fourthAttributeFind").text(data.goodsName);
	$("#fifthAttributeFind").text(data.totalMonth+"月");
	$("#sixthAttributeFind").text(data.totalFee+"元");
	$("#seventhAttributeFind").text(data.goodsDescription);
	$("#eighthAttributeFind").text(data.tradeNo);
	$("#ninthAttributeFind").text(data.successTime);
	$("#tenthAttributeFind").text(data.time);
	$("#eleventhAttributeFind").text(data.alterTime);
	showFinddiv();
}