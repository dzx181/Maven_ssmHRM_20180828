<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>right</title>
		<link href="${dzx}/css/bootstrap.css" type="text/css"  rel="stylesheet" />
		<link href="${dzx}/css/main.css" type="text/css" charset="UTF-8" rel="stylesheet"/>
	</head>
	<body >
		<div style="border:#DDDDDD solid 1px;width: 100%;height: 600px;margin-top: 10px;padding: 10px;" id="content_box">
<div style="text-align: center;">
<h1 style="margin-left: 50px;">欢迎您的登录</h1>
<img src="${dzx}/img/welcome.gif"></div>
</div>

		
	</body>
	<!--引入js文件-->
	<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript" charset="UTF-8"></script>
	<script src="${dzx}/js/bootstrap.js" type="application/javascript" charset="UTF-8"></script>
	<script type="application/javascript">
	$(function () {
		var window_width=$(window).width();
		var window_height=$(window).height();
		
		$("#content_box").attr("height",window_height);
		$("#content_box").attr("width",window_width-250);
		
		
	});
</script>
</html>
