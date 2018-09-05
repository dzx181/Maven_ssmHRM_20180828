<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ssmHRM--值得您信赖的人事专家</title>
		<link href="${dzx}/css/bootstrap.css" type="text/css"  rel="stylesheet" />
		<link rel="shortcut icon" href="${dzx}/img/logo（icon）.png" />
		<link href="${dzx}/css/main.css" type="text/css" charset="UTF-8" rel="stylesheet"/>
	</head>
	<body style="overflow: hidden;">
		
		<!--其他分辨率下的显示效果-->
		<iframe width="100%" height="120px" src="top.html" frameborder="no" scrolling="no" name="top" class="hidden-sm hidden-xs" id="top"></iframe>

	<div class="main hidden-xs hidden-sm" style="width: 100%;">
		<iframe width="250px"  src="${dzx}/left" frameborder="no"   style="position: absolute;left: 0;top: 0px;" name="left" id="left"></iframe>
		<iframe  width="100%"  src="${dzx}/selectjobAndDept" frameborder="no" scrolling="no" marginheight="0" marginwidth="0"   name="right" style="margin-left: 250px" id="right"></iframe>
	</div>
	
	<!--超小屏幕分辨率下的显示效果-->
	<!--header-->
	
		<div style="background:#FF7F27;width:100%;height: 120px;" class="container hidden-lg hidden-md">
			<div  style="float: left;margin-top: 15px;">
				<!--配置超小分辨率下的显示效果，出现该图片-->
				<img src="${dzx}/img/logo2.png" class="img-responsive " style="margin-top: 15px;"/>
			</div>
			
		<!--配置超小分辨率下的显示效果，出现该按钮-->
		<button class="btn btn-default btn-lg " data-toggle="collapse" data-target="#myCollapse" style="position: absolute;right: 10px; top:40px">
  				<span  class="glyphicon glyphicon-align-justify"></span>
  			</button>
		
		</div>
		
		<!--配置超小分辨率下的显示效果，点击该按钮出现折叠框-->
		<div class="collapse hidden-lg hidden-md" id="myCollapse" style="background: #fff;width:100%;position: absolute; top:123px;z-index: 1001;">
  		<ul class="nav " >
  			<li ><a href="index.html" style="color: #000000;"><span class="glyphicon glyphicon-home" style="color: #000000;"></span>&nbsp;网站首页</a></li>
  			<li><a href="consult.html" style="color: #000000;"><span class="glyphicon glyphicon-log-out" style="color: #000000;"></span>&nbsp;注销退出</a></li>
  			<li><a href="example.html" style="color: #000000;"><span class="glyphicon glyphicon-fire" style="color: #000000;"></span>&nbsp;关于我们</a></li>
  		</ul>
  	</div>	
	</body>
	<!--引入js文件-->
	<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript" charset="UTF-8"></script>
	<script src="${dzx}/js/bootstrap.js" type="application/javascript" charset="UTF-8"></script>
	<script type="application/javascript">
$(function () {
	var window_width=$(window).width();
	var window_height=$(window).height();
	
	$("#right").attr("height",window_height);
	$("#right").attr("width",window_width-250);
	$("#left").attr("height",window_height);
	$("#top").attr("width",window_width);
	
});

</script>
</html>
