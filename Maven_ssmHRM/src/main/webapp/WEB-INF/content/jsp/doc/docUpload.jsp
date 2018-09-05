<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>docUpload</title>
	<link href="${dzx}/css/bootstrap.css" type="text/css"  rel="stylesheet" />
	<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />
	</head>
<body>
<div style="border:#DDDDDD solid 1px;width: 100%;height: 600px;margin-top: 10px;padding: 10px;">
			<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;
			当前位置：文档管理&nbsp;&gt;&nbsp;上传文档
		<hr />
		<!--添加成功字样-->
		<input type="hidden" value="${message}" id="message"/>
		<div id="addInfo"></div>
		<div id="error" style="color:#FF0000;font-size:14px ;height: 20px;"></div>
		<table>
		
		<form  enctype="multipart/form-data" id="uploadform" method="post" >
			<tr><td style="width: 85px;">文档标题：</td><td><input type="text"  name="title"  id="file_title"  class="inputCss" maxlength="20" placeholder="*请输入长度不大于20的字符" style="width:200px;" /></td></tr>
			<tr style="height: 10px;"><td></td><td></td></tr>
			<tr><td>文档描述：</td><td><textarea rows="7" cols="80" id="text_area" name="remark" maxlength="200" placeholder="请输入长度不大于200的字符..." class="inputCss"></textarea></td></tr>
			<tr style="height: 10px;"><td></td><td></td></tr>
			<tr><td>文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;档：</td><td><input type="file" name="srcfile"  id="file_document"  style="height: 35px;"  /></td></tr>
			<tr style="height: 10px;"><td></td><td></td></tr>
			<tr><td colspan="2"><input  type="button"  id="uploadbtn" value="上传" class="btn btn-success" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" class="btn btn-danger" value="重置"/></td></tr>
		</form>
</table>
		<!-- 判断数据是否符合要求，提交表单的标志 -->
		<input type="hidden" id="submitInfo" value="false" />
	</div>	
	<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$(function () {
			var mess=$("#message").val();
			if (mess!='') {
				$("#addInfo").html('<h3 style="color: green;height: 30px;">上传成功</h3>');
			}
			setTimeout(function () {
				
				$("#addInfo").text('');
				
			}, 5000);
			
		});
		//判断文件大小
		$("#file_document").blur(function () {
			var file_size = $("#file_document")[0].files[0].size;
			var fileComment=10*1024*1024;//10mb
			if (file_size>=fileComment){
				$("#submitInfo").val('false');
				$("#error").text("上传内容不能大于20MB");
			}else {
				$("#submitInfo").val('true');
			}
		});
		//表单校验
			$("#uploadbtn").click(function () {
				var file_title=$("#file_title").val();
				var text_area=$("#text_area").val();
				var file_document=$("#file_document").val();
				
				
				var re =/[`~!@#$%^&*_+<>{}\\/'[\]]/im;
				
				
				
				if (file_title=="") {
					$("#error").text("请输入文档标题");
				} else if (re.test(file_title)) {
					$("#error").text("文档标题存在`~!@#$%^&*_+<>{}\'[]/等的非法字符");
				}  else if (text_area=="") {
					$("#error").text("请输入文档描述");
				} else if (file_document=="") {
					$("#error").text("上传文档不能为空");
				}else if ($("#submitInfo").val()=='true'){
					$("#uploadform").attr("action","${dzx}/uploadFile");
					$("#uploadform").submit();
				}
			});		
	</script>
	</body>
</html>