//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
	var page1={
			'currentPage':sum,
			'countPerPage':countPerPage
		};
	secondBackstage.findTeamAdminPage(page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 var num=(data.currentPage-1)*countPerPage;
		 pageSelect(data);
		 dataIsNull(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.id+'"/>';},
		 function(obj){num=parseInt(num)+1;
			return  num;},
		 function(obj){return obj.schoolName ;},
		 function(obj){return '<a style="color:Orange;cursor:pointer;" onclick="dataFind('
				+ obj.id + ')">' +obj.title+ '</a>';},
		 function(obj){return obj.summary;},
		 function(obj){if(obj.status==1)
			 return "送审";
		 else if(obj.status==0)
			 return "录入";
		 else if(obj.status==2)
			 return "上架";
		 else if(obj.status==3)
			 return "下架";
		 },
		 function(obj){return obj.price+"元";},
		 function(obj){return obj.teamPrice+"元";},
		 function(obj){
			 return '<input type="button" value="通过" name="update" onClick="sendPass('
				+ obj.id +')"/><input type="button" value="驳回" name="send" onClick="sendBack('
					+ obj.id +')"/>';
			 }
		 ],
		 {
		 escapeHtml:false
		 }
		 );
	}
function dataFind(data){
	$("#detailSchoolName").text("");
	$("#detailTitle").text("");
	$("#detailPrice").text("");
	$("#detailTeamPrice").text("");
	$("#detailNowNumber").text("");
	$("#detailDetail").text("");
	$("#detailSummary").text("");
	$("#detailStarttime").text("");
	$("#detailExpiretime").text("");
	$("#detailBegintime").text("");
	$("#detailEndtime").text("");
	$("#detailIsuserefund").text("");
	$("#detailIslaterefund").text("");
	$("#detailIsuseunreadrefund").text("");
	$("#detailIsreadrefund").text("");
	$("#detailStatus").text("");
	$("#detailInventory").text("");
	$("#detailTime").text("");
	$("#detailRemark").text("");
	secondBackstage.findTbTeamDetail(data,findTbTeamDetailCallBack);
}
function findTbTeamDetailCallBack(data){
	$("#detailSchoolName").text(data.schoolName);
	$("#detailTitle").text(data.title);
	$("#detailPrice").text(data.price+"元");
	$("#detailTeamPrice").text(data.teamPrice+"元");
	$("#detailNowNumber").text(data.nowNumber);
	$("#detailDetail").text(data.detail);
	$("#detailSummary").text(data.summary);
	if(data.startTime==null)
		$("#detailStarttime").text("");
	else
	$("#detailStarttime").text((new Date(data.startTime)).format("yyyy-MM-dd"));
	if(data.expireTime==null)
		$("#detailExpiretime").text("");
	else
	$("#detailExpiretime").text((new Date(data.expireTime)).format("yyyy-MM-dd"));
	if(data.beginTime==null)
		$("#detailBegintime").text("");
	else
	$("#detailBegintime").text((new Date(data.beginTime)).format("yyyy-MM-dd"));
	if(data.endTime==null)
		$("#detailEndtime").text("");
	else
	$("#detailEndtime").text((new Date(data.endTime)).format("yyyy-MM-dd"));
	if(data.isUseRefund==1)
		$("#detailIsuserefund").text("支持");
	else
		$("#detailIsuserefund").text("不支持");
	if(data.isLateRefund==1)
		$("#detailIslaterefund").text("支持");
	else
		$("#detailIslaterefund").text("不支持");
	if(data.isUseUnreadRefund==1)
		$("#detailIsuseunreadrefund").text("支持");
	else
		$("#detailIsuseunreadrefund").text("不支持");
	if(data.isReadRefund==1)
		$("#detailIsreadrefund").text("支持");
	else
		$("#detailIsreadrefund").text("不支持");
	if(data.status==0)
		$("#detailStatus").text("录入");
	else if(data.status==1)
		$("#detailStatus").text("送审");
	else if(data.status==2)
		$("#detailStatus").text("上架");
	else
		$("#detailStatus").text("下架");
	$("#detailInventory").text(data.inventory);
	$("#detailTime").text((new Date(data.time)).format("yyyy-MM-dd hh:mm:ss"));
	$("#detailRemark").text(data.remark);
	showFinddiv();
}
function sendPass(data){
	secondBackstage.updateTbTeamPass(data,$("#adminId").val(),callBack);
}
function sendBack(data){
	var str=prompt("送审驳回原因","");
	var data={
			"id":data,
			"adminid":$("#adminId").val(),
			"imgurl":str
	};
	secondBackstage.updateTbTeamBack(data,callBack);
}