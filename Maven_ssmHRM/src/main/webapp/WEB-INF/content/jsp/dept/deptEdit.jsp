<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deptEdit</title>
		<link href="${dzx}/css/bootstrap.css" type="text/css"  rel="stylesheet" />
	<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<div style="border:#DDDDDD solid 1px;width: 100%;height: 600px;margin-top: 10px;padding: 10px;">
			<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;
			当前位置：部门管理&nbsp;&gt;&nbsp;更改部门
		<hr /><br />
		<div id="error" style="color:#FF0000;width: 400px;height: 15px;"></div><br />
		<form:form id="dept_form" method="post" modelAttribute="editDept">
			部&nbsp;&nbsp;&nbsp;门&nbsp;&nbsp;&nbsp;名：&nbsp;<form:input   path="name" id="dept_name" cssClass="inputCss"  maxlength="20"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<br /><br />部门描述名：<form:input  path="remark" cssClass="inputCss" id="dept_remark"  maxlength="20"/>&nbsp;<br /><br />
									<form:hidden  path="id" id="depart_id" />
			<input type="button" name="editDepart" value="修改" class="btn btn-success" id="btn"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" name="cancel" class="btn btn-danger" value="取消"/>
		</form:form>
</div>
		<!-- 判断数据是否符合要求，提交表单的标志 -->
		<input type="hidden"  id="submitInfo" value="true"/>
	</body>
	<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
	/* 部门名称不能重复 */
	 $("#dept_name").change(function () {
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
				} else {
					$("#submitInfo").val("false");
					$("#error").text("*该部门名称已经添加过，请重新输入部门名");
				}
			   },
			   // 请求出错时调用的函数
			   error:function(){
				   alert("数据发送失败");
			   }
		});
	}); 
	$("#btn").click(function(){
		
		var dept_name=$("#dept_name").val();
		var dept_remark=$("#dept_remark").val();
		var re =/[`~!@#$%^&*_+<>{}\\/'[\]]/im;
		if (dept_name=="") {
			$("#error").text("*部门名称不能为空");
		}else if (dept_remark=="") {
			$("#error").text("*部门详细信息不能为空");
		}else if (re.test(dept_name)) {
			$("#error").text("*部门名称存在`~!@#$%^&*_+<>{}\'[]/等的非法字符");
		}else if ($("#submitInfo").val()=='false'){
			$("#error").text("*该部门已经添加过，请重新输入部门");
		}else{
		  $("#dept_form").attr("action", "${dzx}/saveDept");
		   $("#dept_form").submit();
		}

	});
	
	</script>
		

</html>