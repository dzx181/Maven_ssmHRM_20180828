<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deptAdd</title>
<link href="${dzx}/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div
		style="border: #DDDDDD solid 1px; width: 100%; height: 600px; margin-top: 10px; padding: 10px;">
		<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; 当前位置：部门管理&nbsp;&gt;&nbsp;添加部门

		<hr />
		<!--添加成功字样-->
		<div id="addInfo"></div>
		<div id="error" style="color: #FF0000; font-size: 14px; height: 20px;"></div>
		<form action="${dzx}/addDept" id="departmentAddform">
			部门名称：<input type="text" name="name" id="dept_name" class="inputCss"
				maxlength="20" placeholder="请输入长度为20的字符" />&nbsp;&nbsp;&nbsp;&nbsp;
			详细描述：<input type="text" name="remark" class="inputCss"
				id="dept_remark" maxlength="20" placeholder="请输入长度为20的字符" />&nbsp;</br>
			</br> <input type="button" value="添加" class="btn btn-success" id="btn" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" class="btn btn-danger" value="取消" />

		</form>

	</div>
	<!-- 判断数据是否符合要求，提交表单的标志 -->
	<input type="hidden" id="submitInfo" value="false" />
	<!-- 判断是否添加成功 -->
	<input type="hidden" value="${type}" id="typeInfo" />

</body>
<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript"
	charset="UTF-8"></script>
<script type="text/javascript">
	 /* 部门名称不能重复 */
	 $("#dept_name").blur(function () {
		$.ajax("${dzx}/isRedeptName",// 发送请求的URL字符串。
				{
				dataType : "json", // 预期服务器返回的数据类型。
	   			type : "post", //  请求方式 POST或GET
			   contentType:"application/json", //  发送信息至服务器时的内容编码类型
			   // 发送到服务器的数据。
			   data:JSON.stringify({
				   name:$("#dept_name").val()}),
			   async:  true , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
			   // 请求成功后的回调函数。
			   success :function(data){
				  if (data.isSuccess) {
					 $("#error").text(" ");
					$("#submitInfo").val("true");
				}else {
					$("#submitInfo").val("false");
					$("#error").text("*该部门已经添加过，请重新输入部门");
				}
			   },
			   // 请求出错时调用的函数
			   error:function(){
				   alert("数据发送失败");
			   }
		});
	}); 
	/* type */
	 $(function () {
		if ($("#typeInfo").val()=='yes') {
			$("#addInfo").html('<h3 style="color: green;height: 30px;">添加成功</h3>');
		} else if ($("#typeInfo").val()=='no') {
			$("#addInfo").html('<h3 style="color: green;height: 30px;">添加失败</h3>');
		}
		setTimeout(function () {
			
			$("#addInfo").text('');
			
		}, 5000);
	}) 
	
	
	/*判断是否为空*/
			$("#btn").click(function(){
				var valu1=$("#dept_name").val();
				var valu2=$("#dept_remark").val();
				var re =/[`~!@#$%^&*_+<>{}\\/'[\]]/im;
				if (valu1.length==0) {
					$("#error").text("请输入部门名称");
				}else if (valu2.length==0) {
					$("#error").text("请输入描述");
				}else if (re.test(valu1)) {
					$("#error").text("部门名称存在`~!@#$%^&*_+<>{}\'[]/等的非法字符");
				}else if ($("#submitInfo").val()=='false'){
					$("#error").text("*该部门已经添加过，请重新输入部门");
				}else {
					$("#departmentAddform").submit();
				}
 		 		
			});
		 

	</script>
</html>