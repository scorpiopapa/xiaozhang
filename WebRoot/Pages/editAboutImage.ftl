<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${typeName?if_exists }编辑</title>
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
	var images=$id("images").value;
	var image1 =/^.+\.jpg$/;
	var image2 =/^.+\.gif$/;
	var image3 =/^.+\.png$/;
	if(images!=""&&!image1.test(images)&&!image2.test(images)&&!image3.test(images)){
		alert("图片格式只能是jpg，gif，或png格式！");
		return false;
	}
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
  <form action="updateAboutImage.action" method="post" enctype="multipart/form-data" name="form" onsubmit="return checkForm();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >${typeName?if_exists }编辑页面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td align="left">
				<input type="submit" name="Submit" value=" 保存" class="button"/>　			
				<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>			</td>
		</tr>
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>添加${typeName?if_exists }</legend>
				<table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					  	<td align="right">${typeName?if_exists }:</td>
					    <td colspan="3">
					    	<input type="hidden" name="info.infotype" value="${detail.infotype?if_exists }" />
					    	<input type="hidden" name="infoType" value="${detail.infotype?if_exists }" />
					    	<input type="hidden" name="info.infoid" value="${detail.infoid?if_exists }" />
					    	<input type="hidden" name="info.image" value="${detail.image?if_exists }" />
					    	<input type="file" class="text" name="img" id="images" onchange="fileChange(this,'images');"/>
					    </td>
					   </tr>
					   <tr>
					  	<td align="right">${typeName?if_exists }显示:</td>
					    <td colspan="3">
					    	<img src="images/nomal/${detail.image?if_exists}" height="100"/></td>
					   </tr>
					   <#if detail.infotype==7>
					   <tr>
					   	   <td align="right">链接地址:</td>
					   	   <td colspan="3">
					   	   <input type="text" size="45" class="text" name="info.content" id="weburl" value="${detail.content?if_exists }"/>
					   	   </td>
					   </tr>
					   </#if>
					  </table>
			  <br />
				</fieldset>
				</TD>
			</TR>
		</TABLE>
	 </td>
  </tr><TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value=" 保存 " class="button"/>
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

