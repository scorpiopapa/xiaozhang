//分页查询方法
function dwrProjectTable(sum){
	DWRUtil.removeAllRows("dataTable");
	var page1={
			'currentPage':sum,
			'countPerPage':countPerPage
		};
	var root=$("#adminRoot").val();
	if(root==1)
	secondBackstage.findTeamSchoolPage(parseInt($("#adminSchoolId").val()),page1,pageCallBack);
	}
//分页的回调函数
function pageCallBack(data){
		 var num=(data.currentPage-1)*countPerPage;
		 dataIsNull(data);
		 pageSelect(data);
		 DWRUtil.addRows(
		 "dataTable",//tbody的ID
		 data.currentList,
		 [
		 function(obj){return '<input type="checkbox" name="items" value="'+obj.id+'"/>';},
		 function(obj){num=parseInt(num)+1;
			return  num ;},
		 
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
		 function(obj){return obj.imgurl;},
		 function(obj){
			 if(obj.status==0)
			 return '<input type="button" value="编辑" name="update" onClick="dataUpdatePage('
				+ obj.id +')"/><input type="button" value="送审" name="send" onClick="dataSend('
					+ obj.id +')"/>';
			 if(obj.status==2)
				 return '<input type="button" value="下架" onClick="downTeam('
				 + obj.id +')"/>';
			 }
		 ],
		 {
		 escapeHtml:false
		 }
		 );
	}
function downTeam(data){
	secondBackstage.updateDownTbTeam(data,callBack);
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
	$("#detailImgurl").text("");
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
	if(data.imgurl!=null)
	$("#detailImgurl").text(data.imgurl);
	showFinddiv();
}
function savePage(){
	showAddDiv();
}
function saveData(){
	var data={
			"schoolId":$("#adminSchoolId").val(),
			"adminid":$("#adminId").val(),
			"title":$("#saveTitle").val(),
			"remark":$("#saveRemark").val(),
			"price":$("#savePrice").val(),
			"teamPrice":$("#saveTeamPrice").val(),
			"detail":$("#saveDetail").val(),
			"summary":$("#saveSummary").val(),
			"startTime1":$("#saveStarttime").val(),
			"expireTime1":$("#saveExpiretime").val(),
			"beginTime1":$("#saveBegintime").val(),
			"endTime1":$("#saveEndtime").val(),
			"isUseRefund":1,
			"isLateRefund":1,
			"isUseUnreadRefund":$('input:radio[name=saveIsuseunreadrefund]:checked').val(),
			"isReadRefund":$('input:radio[name=saveIsreadrefund]:checked').val(),
			"inventory":$("#saveInventory").val()
	};
	secondBackstage.saveTbTeam(data,callBack);
}

var teamId;
function dataUpdatePage(data){
	teamId=data;
	secondBackstage.findTbTeamDetail(data,dataUpdatePageCallBack);
}

function dataUpdatePageCallBack(data){
	$("#updateTitle").val(data.title);
	$("#updatePrice").val(data.price);
	$("#updateTeamPrice").val(data.teamPrice);
	$("#updateDetail").val(data.detail);
	$("#updateSummary").val(data.summary);
	if(data.startTime==null)
		$("#updateStarttime").val("");
	else
	$("#updateStarttime").val((new Date(data.startTime)).format("yyyy-MM-dd"));
	if(data.expireTime==null)
		$("#updateExpiretime").val("");
	else
	$("#updateExpiretime").val((new Date(data.expireTime)).format("yyyy-MM-dd"));
	if(data.beginTime==null)
		$("#updateBegintime").val("");
	else
	$("#updateBegintime").val((new Date(data.beginTime)).format("yyyy-MM-dd"));
	if(data.endTime==null)
		$("#updateEndtime").val("");
	else
	$("#updateEndtime").val((new Date(data.endTime)).format("yyyy-MM-dd"));
	if(data.isUseUnreadRefund==1)
		$("input[name='updateIsuseunreadrefund'][value=1]").attr("checked",true);
	else
		$("input[name='updateIsuseunreadrefund'][value=0]").attr("checked",true);
	if(data.isReadRefund==1)
		$("input[name='updateIsreadrefund'][value=1]").attr("checked",true);
	else
		$("input[name='updateIsreadrefund'][value=0]").attr("checked",true);
	$("#updateInventory").val(data.inventory);
	$("#updateRemark").val(data.remark);
	showUpadteDiv();
}
function updateData(){
	var data={
			"id":teamId,
			"adminid":$("#adminId").val(),
			"title":$("#updateTitle").val(),
			"remark":$("#updateRemark").val(),
			"price":$("#updatePrice").val(),
			"teamPrice":$("#updateTeamPrice").val(),
			"detail":$("#updateDetail").val(),
			"summary":$("#updateSummary").val(),
			"startTime1":$("#updateStarttime").val(),
			"expireTime1":$("#updateExpiretime").val(),
			"beginTime1":$("#updateBegintime").val(),
			"endTime1":$("#updateEndtime").val(),
			"isUseUnreadRefund":$('input:radio[name=updateIsuseunreadrefund]:checked').val(),
			"isReadRefund":$('input:radio[name=updateIsreadrefund]:checked').val(),
			"inventory":$("#updateInventory").val()
	};
	secondBackstage.updateTbTeam(data,callBack);
}
function dataSend(data){
	secondBackstage.updateTbTeamSend(data,callBack);
}