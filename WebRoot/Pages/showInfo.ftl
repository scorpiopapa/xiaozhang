<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息详情</title>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">

function $id(id){
	var $i= document.getElementById(id);
	if($i==undefined){
		alert("找不到id："+id);
		return false;
	}
	return $i;
}

function checkForm(){
	var title=$id("title").value;
	var content=KE.util.getData('content');
	var images=$id("images").value;
	var image1 =/^.+\.jpg$/;
	var image2 =/^.+\.gif$/;
	var image3 =/^.+\.png$/;
	if(title==""){
		alert("请输入信息标题！");
		return false;
	}
	if(content==""){
		alert("请输入信息内容！");
		return false;
	}
	
	if(images!=""&&!image1.test(images)&&!image2.test(images)&&!image3.test(images)){
		alert("图片格式只能是jpg，gif，或png格式！");
		return false;
	}
}
KE.show({
		  	id : 'content',
			allowFileManager : true,
			urlType : 'domain',// urlType可指定relative, absolute, domain，分别代表相对路径、绝对路径、带域名的完整URL。
			afterCreate : function(id) {
				KE.event.ctrl(document, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
				KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
			}
	});
function check()
{
	$id("aa").style.display="";
}

var isIE = /msie/i.test(navigator.userAgent) && !window.opera;   
function fileChange(target,id) {   
	    var fileSize = 0;        
	    if (isIE && !target.files) {    
		      var filePath = target.value;   
		      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		      
			  if(!fileSystem.FileExists(filePath)){
			     alert("附件不存在，请重新输入！");
				 var file=document.getElementById(id); 
		       	 file.outerHTML=file.outerHTML;
		       	 return;
			  }
		      var file = fileSystem.GetFile (filePath);
		      fileSize = file.Size;   
    	} else {   
	     	  fileSize = target.files[0].size; 
	    }  
	    
        var size = fileSize / 1024;   
        if(size>200){ 
       		 alert("附件大小不能大于200KB！"); 
       		 var file=document.getElementById(id); 
       		 file.outerHTML=file.outerHTML
        }  
        if(size<=0){
        	alert("附件大小不能为0KB！"); 
        	var file=document.getElementById(id); 
       		 file.outerHTML=file.outerHTML
        } 
     } 
</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>

</head>

<body class="ContentBody">
  <form action="saveOrUpdateInfo.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >${typetitle?if_exists }</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td align="left">
				<input type="submit" name="Submit" value=" ${btip?if_exists } " class="button"/>　			
				<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>			</td>
		</tr>
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>${typetitle?if_exists}页面(${btip?if_exists })</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					  	<td align="center" colspan="4">注：上传图片不大于200KB</td>
					  </tr>
					  <tr height="50">
					  	<td align="right">信息标题:
					  	<input type="hidden" value="${infoType?if_exists}" name="infoType"/>
					  	<input type="hidden" value="${information.infoid?if_exists}" name="info.infoid"/>
					  	</td>
					    <td><input type="text" class="text" name='info.title' value="${typetitle?if_exists }"  style="width:154px" readonly id="title"/><span class="red">*</span></td>
					    <td nowrap="nowrap" align="right">左侧图片:</td>
					    <td><input type="file" class="text" name='img' style="width:154px" id="images" onchange="fileChange(this,'images');"/><span class="red">*</span></td>
					  </tr>
					  <#if information.infoid?exists>
					  <tr>
					  	<td align="right">左侧图片:</td>
					    <td colspan="3">
					    	<img src="images/nomal/${information.image?if_exists}" height="100"/></td>
					   </tr>
					   </#if>
					  <tr>
					  	<td align="right">信息内容:</td>
						    <td colspan="3">
						   		<textarea type="text" class="text" name="info.content" id="content" style="width:700px; height:300px;visibility:hidden;">${information.content?if_exists}</textarea>
						   </td>
					   </tr>
					  </table>
			  <br />
				</fieldset>
				</TD>
			</TR>
		</TABLE>
	
	
	 </td>
  </tr><TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value=" ${btip?if_exists } " class="button"/>　
			
			<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/></TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>
</form>
</body>
</html>

