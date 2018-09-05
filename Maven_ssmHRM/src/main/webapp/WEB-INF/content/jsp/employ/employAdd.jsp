<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>employAdd</title>
<link href="${dzx}/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />

</head>
<body>

	<div
		style="border: #DDDDDD solid 1px; width: 100%; height: 620px; margin-top: 10px; padding: 10px;">
		<div id="schedule-box" class="boxshaw hidden "></div>
		<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; 当前位置：员工管理&nbsp;&gt;&nbsp;添加员工
		<hr />

		<!--添加成功字样-->
		<div id="addInfo"></div>
		<div id="error" style="color: #FF0000; font-size: 14px; height: 20px;"></div>
		<form:form id="employform" modelAttribute="employ" method="post">
			<table width="82%">
				<tr>
					<td>职位：</td>
					<td>
						<!-- 从application 中获取职位 --> <form:select path="job.id"
							cssClass="inputCss" id="job_id" >
							<form:option value="0" >----请选择职位----</form:option>
							<form:options items="${contextJobList}" itemLabel="name"
								itemValue="id" />
						</form:select>
					</td>



					<td>所属部门：</td>
					<td>
						<!-- 从application 中获取部门 --> <form:select path="dept.id"
							cssClass="inputCss" id="dept_id">
							<form:option value="0">----请选择部门----</form:option>
							<form:options items="${contextDeptList}" itemLabel="name"
								itemValue="id" />
						</form:select>
					</td>
					<td>性别：</td>
					<td><form:select cssClass="inputCss" id="sex" path="sex" >
							<form:option value="-1">--请选择性别--</form:option>
							<form:option value="1">男</form:option>
							<form:option value="0">女</form:option>
						</form:select></td>

				</tr>
				<tr style="height: 10px;">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>姓&nbsp;名：</td>
					<td><form:input path="name" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的姓名" maxlength="20"
							id="employ_name" /></td>
					<td>学&nbsp;历：</td>
					<td><form:input path="education" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的学历"
							id="employ_education" /></td>

					<td>身份证号码：</td>
					<td><form:input path="card_id" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的身份证号码"
							id="employ_cardId" maxlength="18" /></td>
				</tr>
				<tr style="height: 10px;">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>邮&nbsp;箱：</td>
					<td><form:input path="email" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您常用的邮箱" id="employ_email" /></td>

					<td>手&nbsp;机：</td>
					<td><form:input path="phone" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的手机" id="employ_phone" /></td>
					<td>固定电话：</td>
					<td><form:input path="tel" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的电话" id="employ_tel" /></td>


				</tr>
				<tr style="height: 10px;">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>政治面貌：</td>
					<td><form:input path="party" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的政治面貌" id="employ_party" /></td>
					<td>QQ账号/微信账号：</td>
					<td><form:input path="QQ_num" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的QQ账号/微信账号"
							id="employ_qqNum" />&nbsp;</td>

					<td>联系地址：</td>
					<td><form:input path="address" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您最近的居住地址"
							id="employ_address" /></td>

				</tr>
				<tr style="height: 10px;">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>月薪：</td>
					<td><form:input path="salary" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的月薪" id="salary" /></td>
					<td>出生日期：</td>
					<td><form:input path="birthday" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的出生日期"
							id="employ_birthday" /></td>
					<td>名族：</td>
					<td><form:input path="race" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您所属的名族" id="employ_race" /></td>



				</tr>
				<tr style="height: 10px;">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>所学专业：</td>
					<td><form:input path="speciality" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您所学的专业"
							id="employ_speciality" /></td>
					<td>爱好：</td>
					<td><form:input path="hobby" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入您的爱好" id="employ_hobby" /></td>
					<td>备注：</td>
					<td><form:input path="remark" cssClass="inputCss"
							cssStyle="width:205px;" placeholder="请输入备注" id="employ_remark" /></td>

				</tr>


				<tr style="height: 10px;">
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="5" align="center"><input type="button" value="添加"
						class="btn btn-success" id="addbtn" />&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="reset" name="cancel" class="btn btn-danger" value="取消" /></td>
				</tr>
			</table>
		</form:form>
		<!-- 判断数据是否符合要求，提交表单的标志 -->
		<input type="hidden" id="submitInfo" value="false" />
		<!-- 判断是否添加成功 -->
		<input type="hidden" value="${type}" id="typeInfo" />
	</div>

	<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript"
		charset="UTF-8"></script>
	<script src="${dzx}/js/schedule.js" type="application/javascript"
		charset="UTF-8"></script>
	<script type="text/javascript">
		/* 登录名不能重复 */
		$("#employ_cardId").blur(function() {
			$.ajax("${dzx}/isRecardId",// 发送请求的URL字符串。
			{
				dataType : "json", // 预期服务器返回的数据类型。
				type : "post", //  请求方式 POST或GET
				contentType : "application/json", //  发送信息至服务器时的内容编码类型
				// 发送到服务器的数据。
				data : JSON.stringify({
					card_id : $("#employ_cardId").val()
				}),
				async : true, // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
				// 请求成功后的回调函数。
				success : function(data) {
					if (data.isSuccess) {
						$("#error").text(" ");
						$("#submitInfo").val("true");
					} else {
						$("#submitInfo").val("false");
						$("#error").text("*该身份证已经添加过，请重新输入身份证号码");
					}
				},
				// 请求出错时调用的函数
				error : function() {
					alert("数据发送失败");
				}
			});
		});
		/* type */
		$(function() {
			if ($("#typeInfo").val() == 'yes') {
				$("#addInfo").html(
						'<h3 style="color: green;height: 30px;">添加成功</h3>');
			} else if ($("#typeInfo").val() == 'no') {
				$("#addInfo").html(
						'<h3 style="color: green;height: 30px;">添加失败</h3>');
			}
			/* 定时清除添加成功字样 */
			setTimeout(function () {
						
						$("#addInfo").text('');
						
					}, 5000);
		});

		$("#addbtn").click(
						function() {
							//获取表单值
							var name = $("#employ_name").val();
							var dept_id = $("#dept_id").val();
							var sex = $("#sex").val();
							var job_id = $("#job_id").val();
							var education = $("#employ_education").val();
							var email = $("#employ_email").val();
							var phone = $("#employ_phone").val();
							var tel = $("#employ_tel").val();
							var party = $("#employ_party").val();
							var qqNum = $("#employ_qqNum").val();
							var address = $("#employ_address").val();
							var salary = $("#salary").val();
							var birthday = $("#employ_birthday").val();
							var race = $("#employ_race").val();
							var speciality = $("#employ_speciality").val();
							var hobby = $("#employ_hobby").val();
							var remark = $("#employ_remark").val();
							var cardId = $("#employ_cardId").val();
							//创建正则模板 
							var re = /[`~!@#$%^&*_+<>{}\\/'[\]]/im;
							var card_regx = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(X|x)$)/;
							/*var name_regx=/^[\u4e00-\u9fa5]{2,4}$/;*/
							var phone_regx = /^1(3|4|5|7|8)\d{9}$/;
							var email_regx = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
							var tel_regx = /^0\d{2,3}-?\d{7,8}$/;
							/* 判断日期的合法性，仅限于格式上的校验，1900-2099 */
							var birth_reg = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
							var salary_regx = /^([0-9.]+)$/;
							if (job_id.trim() == "0") {
								$("#error").text("*请选择职位");
							} else if (dept_id.trim() == "0") {
								$("#error").text("*请选择部门");
							} else if (sex.trim() == "-1") {
								$("#error").text("*请选择性别");
							} else if (name.trim() == "") {
								$("#error").text("*姓名不能为空，请补充完整");
							} else if (re.test(name.trim())) {
								$("#error").text(
										"*姓名存在`~!@#$%^&*_+<>{}\'[]/等的非法字符");
							} else if (education.trim() == "") {
								$("#error").text("*学历不能为空，请补充完整");
							} else if (cardId.trim() == "") {
								$("#error").text("*身份证号码不能为空，请补充完整");
							} else if (!card_regx.test(cardId.trim())) {
								$("#error").text("*身份证号码格式不正确，请正确填写格式");
							} else if (re.test(cardId.trim())) {
								$("#error").text(
										"*身份证号码存在`~!@#$%^&*_+<>{}\'[]/等的非法字符");
							} else if (email.trim() == "") {
								$("#error").text("*邮箱不能为空，请补充完整");
							} else if (!email_regx.test(email.trim())) {
								$("#error").text("*邮箱格式不正确，请正确填写邮箱格式");
							} else if (phone.trim() == "") {
								$("#error").text("*手机不能为空，请补充完整");
							} else if (re.test(phone.trim())) {
								$("#error").text(
										"*手机存在`~!@#$%^&*_+<>{}\'[]/等的非法字符");
							} else if (!phone_regx.test(phone.trim())) {
								$("#error").text("*输入的手机格式不对，请正确输入手机格式");
							} else if (tel.trim() == "") {
								$("#error").text("*电话不能为空，请补充完整");
							} else if (!tel_regx.test(tel.trim())) {
								$("#error").text("*固定电话格式不对，请正确输入固定电话");
							} else if (party.trim() == "") {
								$("#error").text("*政治面貌不能为空，请补充完整");
							} else if (qqNum.trim() == "") {
								$("#error").text("*qq账号/微信账号不能为空，请补充完整");
							} else if (address.trim() == "") {
								$("#error").text("*联系地址不能为空，请补充完整");
							} else if (salary.trim() == "") {
								$("#error").text("*月薪不能为空，请补充完整");
							} else if (!salary_regx.test(salary.trim())) {
								$("#error").text("*月薪输入不合法，请输入数字");
							} else if (birthday.trim() == "") {
								$("#error").text("*出生日期不能为空，请补充完整");
							} else if (!birth_reg.test(birthday.trim())) {
								$("#error").text("*出生日期格式不对，请补充完整");
							} else if (race.trim() == "") {
								$("#error").text("*名族不能为空，请补充完整");
							} else if (speciality.trim() == "") {
								$("#error").text("*专业不能为空，请补充完整");
							} else if (hobby.trim() == "") {
								$("#error").text("*爱好不能为空，请补充完整");
							} else if (remark.trim() == "") {
								$("#error").text("*备注不能为空，请补充完整");
							} else if ($("#submitInfo").val() == 'false') {
								$("#error").text("*该身份证已经添加过，请重新输入身份证号码");
							}else  {
								$("#employform").attr("action",
										"${dzx}/addEmploy");
								$("#employform").submit();
							}

						});

		/*时间插件*/
		var mySchedule = new Schedule({
			el : '#schedule-box',
			//date: '2018-9-20',
			clickCb : function(y, m, d) {
				document.querySelector('#employ_birthday').value = y + '-' + m
						+ '-' + d
			},
			nextMonthCb : function(y, m, d) {
				document.querySelector('#employ_birthday').value = y + '-' + m
						+ '-' + d
			},
			nextYeayCb : function(y, m, d) {
				document.querySelector('#employ_birthday').value = y + '-' + m
						+ '-' + d
			},
			prevMonthCb : function(y, m, d) {
				document.querySelector('#employ_birthday').value = y + '-' + m
						+ '-' + d
			},
			prevYearCb : function(y, m, d) {
				document.querySelector('#employ_birthday').value = y + '-' + m
						+ '-' + d
			}
		});
		$("#employ_birthday").focus(function() {
			$("#schedule-box").removeClass("hidden");
			$("#schedule-box").addClass("display", "block");
		});
		$("#schedule-box").mouseover(function() {
			$("#schedule-box").removeClass("hidden");
			$("#schedule-box").addClass("display", "block");
		});
		$("#schedule-box").mouseout(function() {
			$("#schedule-box").removeClass("display", "block");
			$("#schedule-box").addClass("hidden");
		})
	</script>
</body>
</html>