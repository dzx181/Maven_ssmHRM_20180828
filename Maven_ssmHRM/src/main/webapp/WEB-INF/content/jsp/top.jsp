<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
		<link href="${dzx}/css/bootstrap.css" type="text/css"  rel="stylesheet" />
		<link href="${dzx}/css/main.css" type="text/css" charset="UTF-8" rel="stylesheet"/>
	</head>
	<body>
		
		
			<!--header-->
		<div style="background:#FF7F27;height: 120px;" >
			<div  style="float: left;margin-top: 15px;">
				<img src="img/banner.png" class="img-responsive hidden-xs">
				
			</div>
			
			<div style="text-align: right;margin-right: 30px;" >
			</br></br></br>
				<span class="glyphicon glyphicon-home hidden-xs hidden-sm" style="text-decoration: none;color: #fff;font-weight: bold;font-size: 16px;" ></span><a href="#" style="text-decoration: none;color: #fff;font-weight: bold;font-size: 16px;"  class="hidden-xs hidden-sm" >&nbsp;网站首页</a>
				&nbsp;	&nbsp;	&nbsp;	
				<span class="glyphicon glyphicon glyphicon-log-out hidden-xs hidden-sm" style="text-decoration: none;color: #fff;font-weight: bold;font-size: 16px;"  ></span><a href="${dzx}/logOff" style="text-decoration: none;color: #fff;font-weight: bold;font-size: 16px;" target="_blank" class="hidden-xs hidden-sm" >&nbsp;注销退出</a>
				&nbsp;	&nbsp;	&nbsp;	
				<span style="color: #fff;font-weight: bold;font-size: 16px;" class="hidden-xs" ><span class="glyphicon glyphicon-user"></span>&nbsp;当前用户：${sessionScope.user.username} </span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="glyphicon glyphicon-time hidden-xs hidden-sm" style="color: #fff;font-weight: bold;font-size: 16px;" ></span>&nbsp;<span id="time" style="color: #fff;font-weight: bold;font-size: 16px;" class="hidden-xs hidden-sm" ></span>
			</div>
		
			
			
		
	
	</body>
	<!--引入js文件-->
	<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript" charset="UTF-8"></script>
	<script src="${dzx}/js/bootstrap.js" type="application/javascript" charset="UTF-8"></script>
	<script type="application/javascript">
	window.onload=function(){
		setInterval("document.getElementById('time').innerText=new Date  ().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay  ());",1000); 
	
	}
</script>
</html>
