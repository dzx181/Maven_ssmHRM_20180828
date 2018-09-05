<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jobEdit</title>
		<link href="${dzx}/css/bootstrap.css" type="text/css"  rel="stylesheet" />
	<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		
		<div style="border:#DDDDDD solid 1px;width: 100%;height: 600px;margin-top: 10px;padding: 10px;">
			<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;
			当前位置：职位管理&nbsp;&gt;&nbsp;更改职位
		<hr />
		<div id="error" style="color:#FF0000;width: 400px;height: 15px;"></div><br />
		<form:form  id="jobform" modelAttribute="editJob" method="post">
		    职位名称：<form:input  path="name" cssClass="inputCss" id="job_name"/>&nbsp;<br /><br />
			详细信息：<form:input  path="remark" id="job_remark" cssClass="inputCss"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<form:hidden  path="id" id="job_remark"/>
			<br/><br/><input type="button"  value="修改" class="btn btn-success"  id="editId"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset"   class="btn btn-danger" value="取消"/>
		</form:form><br />
		</div>
		<!-- 判断数据是否符合要求，提交表单的标志 -->
		<input type="hidden"  id="submitInfo" value="true"/>
	</body>
	<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
	/* 职位名称不能重复 */
	 $("#job_name").change(function () {
		$.ajax("${dzx}/isRejobName",// 发送请求的URL字符串。
				{
				dataType : "json", // 预期服务器返回的数据类型。
	   			type : "post", //  请求方式 POST或GET
			   contentType:"application/json", //  发送信息至服务器时的内容编码类型
			   // 发送到服务器的数据。
			   data:JSON.stringify({
				   name:$("#job_name").val()}),
			   async:  true , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
			   // 请求成功后的回调函数。
			   success :function(data){
				  if (data.isSuccess) {
					 $("#error").text(" ");
					$("#submitInfo").val("true");
				} else {
					$("#submitInfo").val("false");
					$("#error").text("*该职位名称已经添加过，请重新输入职位名称");
				}
			   },
			   // 请求出错时调用的函数
			   error:function(){
				   alert("数据发送失败");
			   }
		});
	}); 
	$("#editId").click(function(){
		
		var job_name=$("#job_name").val();
		var job_remark=$("#job_remark").val();
		var re =/[`~!@#$%^&*_+<>{}\\/'[\]]/im;
		if (job_name=="") {
			$("#error").text("*职位名称不能为空");
			
		}else if (job_remark=="") {
			$("#error").text("*职位详细信息不能为空");
		}else if (re.test(job_name)) {
			$("#error").text("*职位名称存在`~!@#$%^&*_+<>{}\'[]/等的非法字符");
		}else if ($("#submitInfo").val()=='false'){
			$("#error").text("*该职位已经添加过，请重新输入职位");
		}else {
			$("#jobform").attr("action", "${dzx}/saveJob");
		   $("#jobform").submit();
		}

		 
	});
	
	</script>
		

</html>