<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>employList</title>
<link href="${dzx}/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div
		style="border: #DDDDDD solid 1px; width: 100%; margin-top: 10px; padding: 10px;" id="content_box">
		<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; 当前位置：员工管理&nbsp;&gt;&nbsp;员工查询
		
		<div style="display: inline-block; cursor: pointer;float: right;margin-right: 50px;" id="print">
			<span class="glyphicon glyphicon-print"></span>&nbsp;打印
		</div>
		<div style="display: inline-block; cursor: pointer;float: right;margin-right: 150px;" id="importExcel">
		<a
			style="margin-left: 100px; cursor: pointer; text-decoration: none; color: #000000;"
			href="${dzx}/employImportExcel"><span
			class="glyphicon glyphicon-export"></span>&nbsp;导出</a>
		</div>
		<hr />


		<form:form id="employform" method="post" modelAttribute="employ">
			<!-- 从application 中获取职位 -->
			<form:select path="job.id" cssClass="inputCss" id="job_id">
				<form:option value="0">----请选择职位----</form:option>
				<form:options items="${contextJobList}" itemLabel="name"
					itemValue="id" />
			</form:select> &nbsp;
			姓名：<form:input path="name" cssClass="inputCss" />&nbsp;
			身份证号码：<form:input path="card_id" cssClass="inputCss" />
			<br />
			<br />
			<form:select cssClass="inputCss" id="sex" path="sex">
				<form:option value="-1">--请选择性别--</form:option>
				<form:option value="1">男</form:option>
				<form:option value="0">女</form:option>
			</form:select>&nbsp;
						手机：<form:input path="phone" cssClass="inputCss" /> &nbsp;
						所属部门：<!-- 从application 中获取部门 -->
			<form:select path="dept.id" cssClass="inputCss" id="dept_id">
				<form:option value="0">----请选择部门----</form:option>
				<form:options items="${contextDeptList}" itemLabel="name"
					itemValue="id" />
			</form:select>&nbsp;&nbsp; <input type="button" id="selectEmploy" value="搜索"
				class="btn btn-success" />
			<input type="button" id="dropEmploy" class="btn btn-danger"
				value="删除" />
			<div style="height: 10px;"></div>
			
			<table class="table table-hover table-striped"
				style="font-size: 12px; text-align: center;" id="employ_tab">
				<tr>
					<th><input type="checkbox" name="caption" value="0"
						id="checkAll"></th>
					<th>姓名</th>
					<th>性别</th>
					<th>手机号码</th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">固定电话</th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">邮箱</th>
					<th>职位</th>
					<th>学历</th>
					<th>身份证号码</th>
					<th>部门</th>
					<th>薪资&nbsp;&nbsp;<span
						class="glyphicon glyphicon-sort-by-attributes"
						style="cursor: pointer;" id="asc"></span>&nbsp;&nbsp;&nbsp; <span
						class="glyphicon glyphicon-sort-by-attributes-alt"
						style="cursor: pointer;" id="desc"></span></th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">建档日期</th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">备注</th>
					<th>地址</th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">QQ/微信</th>
					<th>政治面貌</th>
					<th>生日</th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">种族</th>
					<th>专业</th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">爱好</th>
					<th class="hidden-xs hidden-sm hidden-md hidden-lg">备注</th>
					<d:if test="${sessionScope.user.status==1}">
						<th>操作</th>
					</d:if>

				</tr>
				<d:forEach items="${employList}" var="employs">
					<tr>
						<td><input type="checkbox" name="caption"
							id="box_${employs.id}" value="${employs.id}" /></td>
						<td>${employs.name}</td>
						<td><d:if test="${employs.sex==0}">女</d:if> <d:if
								test="${employs.sex==1}">男</d:if></td>
						<td>${employs.phone}</td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg">${employs.tel}</td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg">${employs.email}</td>
						<td>${employs.job.name}</td>
						<td>${employs.education}</td>
						<td>${employs.card_id}</td>
						<td>${employs.dept.name}</td>
						<td>${employs.salary}</td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg"><f:formatDate
								value="${employs.create_date}" pattern="yyyy年MM月dd日" /></td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg">${employs.remark}</td>
						<td>${employs.address}</td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg">${employs.QQ_num}</td>
						<td>${employs.party}</td>
						<td><f:formatDate value="${employs.birthday}"
								pattern="yyyy年MM月dd日" /></td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg">${employs.race}</td>
						<td>${employs.speciality}</td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg">${employs.hobby}</td>
						<td class="hidden-xs hidden-sm hidden-md hidden-lg">${employs.remark}</td>
						<d:if test="${sessionScope.user.status==1}">
							<td><a href="${dzx}/editEmploy?employId=${employs.id}"><span
									class="glyphicon glyphicon-pencil" id="edit"></span></a></td>
						</d:if>
					</tr>
				</d:forEach>
			</table>
			<!-- 配置pageIndex的隐藏域 -->
			<form:hidden path="page" id="page_index" />
			<!--配置分页的总页数-->
			<form:hidden path="totalPageSum" id="total_PageSum" />
			<!--配置排序标志，若orderSign的value为0则没有选择任何的排序，若为true则选了升序，若为false则选择了降序-->
			<form:hidden id="orderSign" path="isOrder" />
		</form:form>
		<!-- 分页处理 -->
		<nav aria-label="..." style="text-align:center">
		<div style="display: inline;">共${employ.totalPageSum}
				页&nbsp;&nbsp;总记录${employ.totalRecord}条&nbsp;&nbsp;</div>
		<ul class="pagination">
			<d:choose>
				<d:when test="${employ.pageIndex==1}">
					<li class="disabled"><a href="#" aria-label="Previous"><span
							aria-hidden="true">上一页</span></a></li>

					<d:forEach begin="1" end="${employ.totalPageSum}" var="i">
						<d:choose>
							<d:when test="${employ.pageIndex==i}">
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
						<d:when test="${employ.pageIndex==employ.totalPageSum}">

							<li class="disabled"><a href="#" aria-label="Previous"><span
									aria-hidden="true">下一页</span></a></li>
						</d:when>
						<d:otherwise>
							<li class=""><a href="#" aria-label="Previous"
								onclick="toPage(${employ.pageIndex + 1})"><span
									aria-hidden="true">下一页</span></a></li>

						</d:otherwise>
					</d:choose>
				</d:when>

				<d:when test="${employ.pageIndex==employ.totalPageSum}">
					<li class=""><a href="#" aria-label="Previous"
						onclick="toPage(${employ.pageIndex-1})"><span
							aria-hidden="true">上一页</span></a></li>
					<d:forEach begin="1" end="${employ.totalPageSum}" var="i">
						<d:choose>
							<d:when test="${employ.pageIndex==i}">
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
					<d:forEach begin="1" end="${employ.totalPageSum}" var="i">
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
						onclick="toPage(${employ.pageIndex + 1})"><span
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


</body>
<!-- 打印iframe -->
<iframe id="printf" src="${dzx}/employPrint" width="0" height="0" frameborder="0"></iframe>
<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript"
	charset="UTF-8"></script>
<script type="text/javascript">
/* 打印 */
$("#print").click(function () {
        // 生成并打印ifrme  
        var f = document.getElementById('printf'); 
        f.contentWindow.print();
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
	$("#employ_tab").attr("width",window_width);
	var window_height=$(window).height();
	$("#content_box").attr("height",window_height);
	
});

$("#asc").click(function () {
	//点击降序按钮的标志
	
	$("#employform").attr("action", "${dzx}/orderByAscEmploy");
	$("#employform").submit();
	
});
$("#desc").click(function  () {
	//点击升序按钮的标志
	
	$("#employform").attr("action", "${dzx}/orderByDescEmploy");
	$("#employform").submit();
});
	/*提交查询*/
	$("#selectEmploy").click(function() {
		$("#employform").attr("action", "${dzx}/selectEmploy");
		$("#employform").submit();
	});
	/*提交删除*/
	$("#dropEmploy").click(function() {
		var chex = $("input[id^='box_']:checked");
		if (chex.length == 0) {
			alert("请选择你要删除的员工");
		} else {
			if (confirm("确定要删除该员工吗？")) {
				$("#employform").attr("action", "${dzx}/deleteEmploy");
				$("#employform").submit();
			}
		}

	});
	/* 全选和反选*/
	$("#checkAll").click(function() {

		$("input[id^='box_']").prop("checked", this.checked);
	});
	
	//上下页切换，具体页码
	function toPage(pageIndex) {
		var orderSign=$("#orderSign").val();
		 if (orderSign=='true') {
			$("#page_index").attr("value", pageIndex);
			$("#employform").attr("action", "${dzx}/orderByAscEmploy");
			$("#employform").submit();
		} else if (orderSign=='false') {
			$("#page_index").attr("value", pageIndex);
			$("#employform").attr("action", "${dzx}/orderByDescEmploy");
			$("#employform").submit();
		} else {
			
		$("#page_index").attr("value", pageIndex);
		$("#employform").attr("action", "${dzx}/selectEmploy");
		$("#employform").submit();
		}
	}
	//跳转页码
	function pagerJump() {
		var page_size = $('#pager_jump_page_size').val();
		var total_PageSum = $("#total_PageSum").val();
		var orderSign=$("#orderSign").val();
		var regu = /^[1-9]\d*$/;
		if (!regu.test(page_size) || (page_size < 1)
				|| (page_size > total_PageSum)) {
			alert('请输入[1-' + total_PageSum + ']之间的页码！');
		}else if (orderSign=='true') {
			$("#page_index").attr("value", page_size);
			$("#employform").attr("action", "${dzx}/orderByAscEmploy");
			$("#employform").submit();
		} else if (orderSign=='false') {
			$("#page_index").attr("value", page_size);
			$("#employform").attr("action", "${dzx}/orderByDescEmploy");
			$("#employform").submit();
		} else {
			$("#page_index").attr("value", page_size);
			$("#employform").attr("action", "${dzx}/selectEmploy");
			$("#employform").submit();
		}
	}
</script>

</html>