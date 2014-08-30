
function $id(id){
	return document.getElementById(id);
}
function getRandom(min,max){
    return Math.floor(Math.random()*(max-min)+min);
}
var nn = 1;
var ii = 0;
function invokeAjax() {
			var url = 'servlet/President';
			var params = 'branchschoolId=0&cityId=0&townId=0&subjectId=0&gradeId=0&courseScore=0&action_flag=tijiao';
			new Ajax.Request(url, {
				method : 'POST',
				parameters : params,
				onFailure : showError,
				onComplete : processLoginResponse
			})
			function processLoginResponse(response) {
				var datas = eval("(" + response.responseText + ")");
				var len = datas.data.tbcourse.length;
				$id("content1").value = datas.data.tbcourse[ii].branchschoolName;
				$id("content2").value = datas.data.tbcourse[ii].courseName;
				ii++;
				if(ii==len)
					ii=0;
				$id("invoketime").value = nn;
				nn++;
				setTimeout("invokeAjax()", getRandom(800,1200));
			}
			function showError(response) {
				$id("status").innerHTML = "系统出错,已停止调用！";
				return;
			}
	
	}
var nn1 = 1;
var ii1 = 0;
function invokeAjax1() {
	var url = 'servlet/GogoServlet';
	var params = 'currentPage=1&countPerPage=5&branchschoolId=2&action_flag=classToStudent';
	new Ajax.Request(url, {
		method : 'POST',
		parameters : params,
		onFailure : showError,
		onComplete : processLoginResponse
	})
	function processLoginResponse(response) {
		var datas = eval("(" + response.responseText + ")");
		var len = datas.data.currentList.length;
		$id("content3").value = datas.data.currentList[ii1].userinfoName;
		$id("content4").value = datas.data.currentList[ii1].parentName;
		ii1++;
		if(ii1==len)
			ii1=0;
		
		$id("invoketime1").value = nn1;
		nn1++;
		setTimeout("invokeAjax1()", getRandom(750,1050));
	}
	function showError(response) {
		$id("status").innerHTML = "系统出错,已停止调用！";
		return;
	}

}
var nn2 = 1;
var ii2 = 0;
function invokeAjax2() {
	var url = 'servlet/GogoServlet';
	var params = 'GogoServlet?courseId=1&action_flag=tbAttendanceStudent';
	new Ajax.Request(url, {
		method : 'POST',
		parameters : params,
		onFailure : showError,
		onComplete : processLoginResponse
	})
	function processLoginResponse(response) {
		var datas = eval("(" + response.responseText + ")");
		var len = datas.data.list.length;
		$id("content5").value = datas.data.list[ii2].userinfoId;
		$id("content6").value = datas.data.list[ii2].userinfoName;
		ii2++;
		if(ii2==len)
			ii2=0;
		
		$id("invoketime2").value = nn2;
		nn2++;
		setTimeout("invokeAjax2()", getRandom(880,1180));
	}
	function showError(response) {
		$id("status").innerHTML = "系统出错,已停止调用！";
		return;
	}

}