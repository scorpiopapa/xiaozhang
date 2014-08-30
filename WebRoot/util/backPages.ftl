<script type="text/javascript">
	function ff(v) {
		var searchForm = document.getElementById("searchForm");
		document.getElementById("currentPage").value = v;
		searchForm.submit();
	}
	
	function ss(tp) {
		if(tp==undefined||tp==""){
			alert("查询错误！");
			return false;
		}
		var sp = document.getElementById("pp").value;
		var space = /^\s*$/;
		var searchForm = document.getElementById("searchForm");
		if(space.test(sp))
			alert("请输入页码！");
		else {
			if(isNaN(sp))
				alert("页码只能为数字！");
			else if(sp>tp)
				alert("页码不能大于  "+tp);
			else{
				document.getElementById("currentPage").value = sp;
				searchForm.submit();
			}	
		}
		
	}
</script>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
  <tr>
    <td width="50%">共 <span class="right-text09">${totalPage?if_exists}</span> 页 | 第 <span class="right-text09">${currentPage?if_exists }</span> 页</td>
    <td width="49%" align="right">
    	
    	<#if currentPage?default(1) gt 1>
    	[<a href="#" class="right-font08" onclick="ff(1);return false;">首页</a> | <a href="#" class="right-font08" onclick="ff(${currentPage-1?if_exists});return false;">上一页</a> |
    	</#if> 
    	<#if currentPage?default(1) lte 1>
    	[<a class="right-font08">首页</a> | <a class="right-font08">上一页</a> |
    	</#if> 
    	
    	<#if currentPage?default(1) lt totalPage?default(1)>
    	<a href="#" class="right-font08" onclick="ff(${currentPage?if_exists});return false;">下一页</a> | <a href="#" class="right-font08" onclick="ff(${totalPage?if_exists});return false;">末页</a>
    	</#if>
    	<#if currentPage?default(1) gte totalPage?default(1)>
    	<a class="right-font08">下一页</a> | <a class="right-font08">末页</a>
    	</#if>
    	] 转至：
    
    </td>
    <td width="1%">
     <table width="20" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="1%"><input name="textfield3" type="text" class="right-textfield03" size="1" id="pp"/></td>
        <td width="87%"><input name="Submit23222" onclick="ss(${totalPage?if_exists});" type="button" class="right-button06" value=" " /></td>
      </tr>
     </table>
    </td>
  </tr>
</table>