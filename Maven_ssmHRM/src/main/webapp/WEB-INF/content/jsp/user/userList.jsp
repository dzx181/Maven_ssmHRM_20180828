<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userList</title>
<link href="${dzx}/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />

</head>
<body>
	<div
		style="border: #DDDDDD solid 1px; width: 100%; margin-top: 10px; padding: 10px;" id="content_box">
		<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; 当前位置：用户管理&nbsp;&gt;&nbsp;用户查询
		<div style="display: inline-block; cursor: pointer;float: right;margin-right: 50px;" id="print">
			<span class="glyphicon glyphicon-print"></span>&nbsp;打印
		</div>
		<div style="display: inline-block; cursor: pointer;float: right;margin-right: 150px;" id="importExcel">
		<a
			style="margin-left: 100px; cursor: pointer; text-decoration: none; color: #000000;"
			href="${dzx}/userImportExcel"><span
			class="glyphicon glyphicon-export"></span>&nbsp;导出</a>
		</div>
		<hr />

		<form:form id="userform" method="post" modelAttribute="user">
		
			用户名：<form:input path="username" id="username" style="height: 35px;"
				cssClass="inputCss" />&nbsp;&nbsp; 
			用户状态：<form:select path="status" id="status" style="height: 35px;"
				cssClass="inputCss">
				<form:option value="0">请选择状态</form:option>
				<form:option value="1">超级管理员</form:option>
				<form:option value="2">普通用户</form:option>
			</form:select>
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="搜索"
				class="btn btn-success" id="selectUser" />&nbsp; 
				<d:if test="${sessionScope.user.status==1}">
				<input type="button" class="btn btn-danger" value="删除" id="dropUser" />
				
			</d:if>
			<br /><br />
			
			<table cellspacing="0" cellpadding="" width="100%" 
				class="table table-hover table-striped"
				style="text-align: center;"id="user_tab">
				<tr id="tr_th" style="height: 40px;">
					<th><input type="checkbox" value="0" id="checkAll" /></th>
					<th>登录名</th>
					<d:if test="${sessionScope.user.status==1}">
						<th>密码</th>
					</d:if>
					<th>用户名</th>
					<th>状态</th>
					<th>创建时间</th>
					<d:if test="${sessionScope.user.status==1}">
						<th>操作</th>
					</d:if>

				</tr>
				<d:forEach items="${userList}" var="users">
					<tr >
						<td><input type="checkbox" name="caption"
							id="box_${users.id}" value="${users.id}" /></td>
						<td style="overflow: hidden;">${users.loginname}</td>
						<d:if test="${sessionScope.user.status==1}">
						<td>${users.password}</td>
						</d:if>
						<td style="overflow: hidden;">${users.username}</td>
						<d:choose>
							<d:when test="${users.status==1}">
								<td>超级管理员</td>
							</d:when>
							<d:otherwise>
								<td>普通用户</td>
							</d:otherwise>
						</d:choose>

						<td><f:formatDate value="${users.createDate}"
								pattern="yyyy年MM月dd日" /></td>
						<d:if test="${sessionScope.user.status==1}">
							<td><a href="${dzx}/editUser?userId=${users.id}"><span
									class="glyphicon glyphicon-pencil" id="edit"></span></a></td>
						</d:if>
					</tr>


				</d:forEach>

			</table>
			
			<!-- 配置pageIndex的隐藏域 -->
			<form:hidden path="page" id="page_index" />
			<!--配置分页的总页数-->
			<form:hidden path="totalPageSum" id="total_PageSum" />
		</form:form>
	
		<!-- 分页处理 -->
		<nav aria-label="..." style="text-align:center" id="nav_style" >
		<div style="display: inline;">共${user.totalPageSum}
				页&nbsp;&nbsp;总记录${user.totalRecord}条&nbsp;&nbsp;</div>
		<ul class="pagination">
			
			
			<d:choose>
				<d:when test="${user.pageIndex==1}">
					<li class="disabled"><a href="#" aria-label="Previous"><span
							aria-hidden="true">上一页</span></a></li>

					<d:forEach begin="1" end="${user.totalPageSum}" var="i">
						<d:choose>
							<d:when test="${user.pageIndex==i}">
								<li class="active" id="page_${i}"><a href="#">${i}<span
										class="sr-only"></span></a></li>

							</d:when>
							<d:otherwise>
								<li class="" id="page_${i}"><a href="#"
									onclick="toPage( ${i} )">${i}<span class="sr-only"></span></a></li>

							</d:otherwise>
						</d:choose>
					</d:forEach>
					<d:choose>
						<d:when test="${user.pageIndex==user.totalPageSum}">

							<li class="disabled"><a href="#" aria-label="Previous"><span
									aria-hidden="true">下一页</span></a></li>
						</d:when>
						<d:otherwise>
							<li class=""><a href="#" aria-label="Previous"
								onclick="toPage(${user.pageIndex + 1})"><span
									aria-hidden="true">下一页</span></a></li>

						</d:otherwise>
					</d:choose>
				</d:when>

				<d:when test="${user.pageIndex==user.totalPageSum}">
					<li class=""><a href="#" aria-label="Previous"
						onclick="toPage(${user.pageIndex-1})"><span aria-hidden="true">上一页</span></a></li>
					<d:forEach begin="1" end="${user.totalPageSum}" var="i">
						<d:choose>
							<d:when test="${user.pageIndex==i}">
								<li class="active" id="page_${i}"><a href="#">${i}<span
										class="sr-only"></span></a></li>
							</d:when>
							<d:otherwise>
								<li class="active" id="page_${i}"><a href="#"
									onclick="toPage(${i})">${i}<span class="sr-only"></span></a></li>
							</d:otherwise>
						</d:choose>
					</d:forEach>
					<li class="disabled"><a href="#" aria-label="Previous"><span
							aria-hidden="true">下一页</span></a></li>
				</d:when>
				<d:otherwise>
					<li class=""><a href="#" aria-label="Previous"
						onclick="toPage(${i-1})"><span aria-hidden="true">上一页</span></a></li>
					<d:forEach begin="1" end="${user.totalPageSum}" var="i">
						<d:choose>
							<d:when test="${pageIndex==i}">
								<li class="active" id="page_${i}"><a href="#">${i}<span
										class="sr-only"></span></a></li>
							</d:when>
							<d:otherwise>
								<li class="active" id="page_${i}"><a href="#"
									onclick="toPage( ${i} )">${i}<span class="sr-only"></span></a></li>

							</d:otherwise>
						</d:choose>
					</d:forEach>
					<li class=""><a href="#" aria-label="Previous"
						onclick="toPage(${user.pageIndex + 1})"><span
							aria-hidden="true">下一页</span></a></li>

				</d:otherwise>
			</d:choose>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;跳转到&nbsp;&nbsp; <input
				style='text-align: center; BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none; height: 32px'
				type='text' size='2' id='pager_jump_page_size' /> &nbsp; <input
				type='button' value='确定' id='pager_jump_btn' onclick="pagerJump()"
				class="btn btn-info" style="" />
			<li>
		</ul>
		
		</nav>
		
	</div>
	<!-- 保存session中的user的ID -->
	<input type="hidden" value="${sessionScope.user.id}" id="session_userID"/>
	<!-- 提交删除的标志 -->
	<input type="hidden" value="true" id="remove_sign"/>
	
</body>
<!-- 打印iframe -->
<iframe id="printf" src="${dzx}/userPrint" width="0" height="0" frameborder="0"></iframe>
<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript"
	charset="UTF-8"></script>
<script type="text/javascript">
	/* 打印 */
	$("#print").click(function () {
        // 生成并打印ifrme
         var f = document.getElementById('printf'); 
          f.contentWindow.print();
	});
	/*提交查询*/
		$("#selectUser").click(function() {
			$("#userform").attr("action", "${dzx}/selectUser");
			$("#userform").submit();
		});
	
	$(function () {

		/*表头去除背景色  */
		$("th").css("background-color","#F9F9F9");
		/* 表头内容居中 */
		$("th").css("text-align","center");
		//设置分页页码的样式，点击那个页码数，就给该数字添加"active"样式
		var pageindex=$("#page_index").val();
		$("li[class^='active']").removeClass();
		var str="page_"+pageindex;
		$("#"+str).attr("class","active");
		/* 自适应 */
		var window_width=$(window).width();
		var window_height=$(window).height();
		$("#user_tab").attr("width",window_width);
		$("#content_box").attr("height",window_height);
		
		
		
	});
	
		/*提交删除*/
		$("#dropUser").click(function() {
			var chex = $("input[id^='box_']:checked").val();
			var checked = [];
			var session_userID=$("#session_userID").val();
			var remove_sign=$("#remove_sign").val();
			 if (chex.length == 0){
				alert("请选择你要删除的用户");
			} else {
				
				if (confirm("确定要删除该用户以及该用户所上传的文件吗？")) {
			        $("input[id^='box_']:checked").each(function() {
			           if ($(this).val()==session_userID) {
			        	   $("#remove_sign").attr("value", "fasle");
			        	   alert("不能删除正在登录的用户");
			        	   return true;
						}else {
							$("#remove_sign").attr("value", "true");
						}
			        });
			       if ($("#remove_sign").val()=='true') {
			    	   $("#userform").attr("action", "${dzx}/deleteUsers");
						$("#userform").submit();
						
				}
				}
			} 

		});

		/* 全选和反选*/
		$("#checkAll").click(function() {
			$("input[id^='box_']").prop("checked", this.checked);
		});
		
		//上下页切换，具体页码
		function toPage(pageIndex) {
			$("#page_index").attr("value", pageIndex);
			$("#userform").attr("action", "${dzx}/selectUser");
			$("#userform").submit();

		}
		//跳转页码
		function pagerJump() {
			var page_size = $('#pager_jump_page_size').val();
			var total_PageSum = $("#total_PageSum").val();
			var regu = /^[1-9]\d*$/;
			if (!regu.test(page_size) || (page_size < 1)
					|| (page_size > total_PageSum)) {
				alert('请输入[1-' + total_PageSum + ']之间的页码！');
			} else {
				$("#page_index").attr("value", page_size);
				$("#userform").attr("action", "${dzx}/selectUser");
				$("#userform").submit();
			}
		}
	</script>

</html>
