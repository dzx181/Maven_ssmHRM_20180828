<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ssmHRM--值得您信赖的人事专家</title>
<link href="${dzx}/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="${dzx}/img/logo（icon）.png" />
<link href="${dzx}/css/main.css" type="text/css" charset="UTF-8" rel="stylesheet" />
</head>

<body style="background: #F0F4F3;">
	<!--header-->
	<div style="background: #FF7F27; height: 120px;">
		<div style="float: left; margin-top: 15px;">
			<img src="${dzx}/img/banner.png" class="img-responsive hidden-xs">
			<!--配置超小分辨率下的显示效果，出现该图片-->
			<img src="${dzx}/img/logo2.png" class="img-responsive visible-xs"
				style="margin-top: 15px;" />
		</div>
		<div
			style="float: right; font-size: 16px; color: #fff; margin-top: 50px; margin-right: 50px;"
			class=" hidden-xs">
			<a href="#" style="color: #fff">联系我们</a>&nbsp;/<a href="#"
				style="color: #fff">帮助中心</a>&nbsp;/<a href="#" style="color: #fff">关于我们</a>
		</div>

		<button class="btn btn-default btn-lg visible-xs"
			data-toggle="collapse" data-target="#myCollapse"
			style="position: absolute; right: 10px; top: 40px">
			<span class="glyphicon glyphicon-align-justify"></span>
		</button>

	</div>

	<!--配置超小分辨率下的显示效果，点击该按钮出现折叠框-->
	<div class="collapse visible-xs" id="myCollapse"
		style="background: #fff; width: 100%; position: absolute; top: 123px; z-index: 1001;">
		<ul class="nav ">
			<li><a href="${dzx}/main" style="color: #000000;"><span
					class="glyphicon glyphicon-earphone" style="color: #000000;"></span>&nbsp;联系我们</a></li>
			<li><a href="${dzx}/consult" style="color: #000000;"><span
					class="glyphicon glyphicon-list" style="color: #000000;"></span>&nbsp;帮助中心</a></li>
			<li><a href="${dzx}/example" style="color: #000000;"><span
					class="glyphicon glyphicon-fire" style="color: #000000;"></span>&nbsp;关于我们</a></li>
		</ul>
	</div>
	<!--大，中屏幕分辨率下的登录框-->
	<div
		style="width: 600px; height: 400px; background: #fff; box-shadow: -2px 0 2px #ebefee, 4px 0 4px #e1e5e4, 0 -1px 1px #eef2f1, 0 5px 5px #dce0df; margin: 50px auto auto;"
		class="hidden-sm hidden-xs">

		<form action="login"
			style="width: 300px; height: 400px; margin: 0px auto auto; padding-top: 10px;"
			id="loginForm" method="post">

			<h1 style="text-align: center;">登录</h1>
			<div class="form-group has-feedback">
				<label class="control-label">登录名:</label> <input
					class="form-control" name="loginname" id="user_name"
					placeholder="请输入登录名" /> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>

			<div class="form-group has-feedback">

				<label class="control-label">密码:</label> <input type="password"
					class="form-control" name="password" id="pass_word"
					placeholder="请输入6-12位的数字和字母密码" /> <span
					class="glyphicon glyphicon-pencil form-control-feedback"></span>

			</div>

			<div class="form-group">
				<div class="random_font">
					<b>验证码:</b>
				</div>
				<input type="text" id="random_box" placeholder="请输入验证码" /> <input
					type="text" disabled id="random_char" /> <img src="${dzx}/img/upda1.png"
					class="update" onclick="" id="img1" />
			</div>

			<span id="alertinfo" style="color: #FF0000; font-size: 14px;">
			</span><br /> <input type="button" id="login_btn" class="btn btn-group-lg "
				value="登录" />
		</form>

	</div>

	<!--小，超小屏幕分辨率下的登录框-->
	<div
		style="width: 400px; height: 400px; background: #fff; box-shadow: -2px 0 2px #ebefee, 4px 0 4px #e1e5e4, 0 -1px 1px #eef2f1, 0 5px 5px #dce0df; margin: 50px auto auto;"
		class="hidden-lg hidden-md">
		<form action="login"
			style="width: 200px; height: 400px; margin: 0px auto auto; padding-top: 10px;"
			id="loginForm1" method="post">

			<h1 style="text-align: center;">登录</h1>
			<div class="form-group has-feedback">
				<label class="control-label">登录名:</label> <input
					class="form-control" name="loginname" id="user_name1"
					placeholder="请输入登录名" /> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>

			<div class="form-group has-feedback">

				<label class="control-label">密码:</label> <input type="password"
					class="form-control" name="password" id="pass_word1"
					placeholder="请输入密码" /> <span
					class="glyphicon glyphicon-pencil form-control-feedback"></span>

			</div>

			<div class="form-group">
				<div class="random_font">
					<b>验证码:</b>
				</div>
				<input type="text" id="random_box1" /> <input type="text" disabled
					id="random_char1" /> <img src="${dzx}/img/upda1.png" class="update"
					onclick="" id="img2" />
			</div>



			<span id="alertinfo1" style="color: #FF0000; font-size: 14px;">
			</span><br /> <input type="button" id="login_btn1"
				class="btn btn-group-lg " value="登录" />

		</form>

	</div>
	</div>
	<!-- 登录失败信息 -->
	<input type="hidden" value="${type}" id="reLogin">
</body>
<!--引入js文件-->
<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript" charset="UTF-8"></script>
<script src="${dzx}/js/bootstrap.js" type="application/javascript" charset="UTF-8"></script>
<script type="application/javascript">
	
		
	//产生随机验证码
	function random() {
		var arraysChar = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
		//产生随机验证码
		var str = '';
		for(var i = 0; i < 4; i++) {
			str += arraysChar[Math.floor(Math.random() * arraysChar.length)];
		}
		$("#random_char").val(str);
		$("#random_char1").val(str);
	};
	
	//页面加载函数
	$(function() {
		if ($("#reLogin").val()=='no') {
			$("#alertinfo").text("*登录名或者密码输入不正确");
		}
		random();
	});
	
	//点击update图片更新随机验证码
	$("#img1").click(function() {
		random();
	});
	$("#img2").click(function() {
		random();
	});
	//点击提交按钮触发该函数
	$("#login_btn").click(
	function check() {
		//获取所需要的参数的值
		var username = $("#user_name").val();
		var pass_word = $("#pass_word").val();
		var random_box = $("#random_box").val();
		var random_char = $("#random_char").val();
		
		var regx=/^(\w){6,12}$/g;
		
		if (username.trim()=='') {
			$("#alertinfo").text("*登录名不能为空");
		}else if(pass_word.trim()==''){
			$("#alertinfo").text("*密码不能为空");
		}else if(random_box.trim()==''){
			$("#alertinfo").text("*验证码不能为空");
		}else if (!regx.test(pass_word.trim())) {
			$("#alertinfo").text("*输入的密码不符合要求");
		}else if(!(random_box.trim().toLocaleLowerCase()==random_char.toLocaleLowerCase())){
			$("#alertinfo").text("*输入的验证码错误");
		}else{
			$("#loginForm").attr("action","${dzx}/loginTo");
			$("#loginForm").submit();
		}
	
	});
	
	
	$("#login_btn1").click(
	function check1() {
		//获取所需要的参数的值
		var user_name1 = $("#user_name1").val();
		var pass_word1 = $("#pass_word1").val();
		var random_box1 = $("#random_box1").val();
		var random_char1 = $("#random_char1").val();
		
		var regx=/^(\w){6,12}$/g;
		
		if (user_name1.trim()=='') {
			$("#alertinfo1").text("*登录名不能为空");
		}else if(pass_word1.trim()==''){
			$("#alertinfo1").text("*密码不能为空");
		}else if(random_box1.trim()==''){
			$("#alertinfo1").text("*验证码不能为空");
		}else if (!regx.test(pass_word1.trim())) {
			$("#alertinfo1").text("*输入的密码不符合要求");
		}else if(!(random_box1.trim().toLocaleLowerCase()==random_char1.toLocaleLowerCase())){
			$("#alertinfo1").text("*输入的验证码错误");
		}else{
			/* $("#user_name").val($("#user_name1").val());
			$("#pass_word").val($("#pass_word1").val()); */
			$("#loginForm1").attr("action","${dzx}/loginTo");
			$("#loginForm1").submit();
		}
	
	});

</script>
</html>
