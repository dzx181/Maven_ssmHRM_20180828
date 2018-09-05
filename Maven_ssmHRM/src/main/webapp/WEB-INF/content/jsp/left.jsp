<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>left</title>
		<link href="${dzx}/css/bootstrap.css" type="text/css"  rel="stylesheet" />
	</head>
	<body style="padding-left: 10px; padding-top: 10px; padding-right: 10px" >
<div class="panel-group " id="accordion" role="tablist" aria-multiselectable="true"  >
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="text-decoration: none;">
          <span class="glyphicon glyphicon-user">
         	
         </span>&nbsp;&nbsp;<span >用户管理 </span >
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
      <ul>
		<li id="user_Select"  style="list-style: none; cursor: pointer;"><span class="glyphicon glyphicon-search"></span>&nbsp;<a href="${dzx}/selectUser" target="right" style="text-decoration: none;cursor: pointer;">用户查询</a></li>
		<d:if test="${sessionScope.user.status==1}">
			<li id="user_Add" style="list-style: none;cursor: pointer;">
			<span class="glyphicon glyphicon-send"></span>&nbsp;<a href="${dzx}/userAdd" target="right" style="text-decoration: none;cursor: pointer;">添加用户</a>
			</li>
		</d:if>
	</ul>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" style="text-decoration: none;">
		 <span class="glyphicon glyphicon-tags">
         	
         </span>&nbsp;&nbsp;部门管理
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
		<ul style="list-style: none;cursor: pointer;">
		<li id="depart_Select"><span class="glyphicon glyphicon-search"></span>&nbsp;<a href="${dzx}/selectDept" target="right" style="text-decoration: none;cursor: pointer;">部门查询</a></li>
		<d:if test="${sessionScope.user.status==1}">
		<li id="depart_Add"><span class="glyphicon glyphicon-send"></span>&nbsp;<a href="${dzx}/deptAdd" target="right" style="text-decoration: none;cursor: pointer;">添加部门</a></li>
		</d:if>
	</ul>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="text-decoration: none;">
		<span class="glyphicon glyphicon-bookmark">
         	
         </span>&nbsp;&nbsp;职位管理
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
      <ul style="list-style: none;cursor: pointer;">
		<li id="jop_Select"><span class="glyphicon glyphicon-search"></span>&nbsp;<a href="${dzx}/selectJob" target="right" style="text-decoration: none;cursor: pointer;">职位查询</a></li>
		<d:if test="${sessionScope.user.status==1}">
		<li id="jop_Add"><span class="glyphicon glyphicon-send"></span>&nbsp;<a href="${dzx}/jobAdd" target="right" style="text-decoration: none;cursor: pointer;">添加职位</a></li>
		</d:if>
	</ul>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingFour">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour" style="text-decoration: none;">
		<span class="glyphicon glyphicon-wrench">
         	
         </span>&nbsp;&nbsp;员工管理
        </a>
      </h4>
    </div>
    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
      <div class="panel-body">
     <ul style="list-style: none;cursor: pointer;">
		<li id="employ_Select"><span class="glyphicon glyphicon-search"></span>&nbsp;<a href="${dzx}/selectEmploy" target="right" style="text-decoration: none;cursor: pointer;">员工查询</a></li>
		<d:if test="${sessionScope.user.status==1}">                                               
		<li id="employ_Add"><span class="glyphicon glyphicon-send"></span>&nbsp;<a href="${dzx}/employAdd" target="right" style="text-decoration: none;cursor: pointer;">添加员工</a></li>
		</d:if>
	</ul>
      </div>
    </div>
  </div>
  
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingSix">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix" style="text-decoration: none;">
		<span class="glyphicon glyphicon-save">
         	
         </span>&nbsp;&nbsp;下载中心
        </a>
      </h4>
    </div>
    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
      <div class="panel-body">
     <ul style="list-style: none;cursor: pointer;">
		<li id="document_Select" ><span class="glyphicon glyphicon-search"></span>&nbsp;<a href="${dzx}/selectFile" target="right" style="text-decoration: none;cursor: pointer;">文档查询</a></li>
		<d:if test="${sessionScope.user.status==1}">
		<li id="document_Add"><span class="glyphicon glyphicon-open"></span>&nbsp;<a href="${dzx}/toUploadFile" target="right" style="text-decoration: none;cursor: pointer;">上传文档</a></li>
		</d:if>
	</ul>
      </div>
    </div>
  </div>
</div>
</body>
<script type="application/javascript" charset="UTF-8" src="${dzx}/js/jquery-1.12.4.js"></script>
<script type="application/javascript" charset="UTF-8" src="${dzx}/js/bootstrap.js"></script>
</html>
