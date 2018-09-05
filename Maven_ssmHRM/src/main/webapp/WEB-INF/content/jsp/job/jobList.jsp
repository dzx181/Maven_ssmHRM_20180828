<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jobList</title>
	<link href="${dzx}/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${dzx}/css/main.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		
		<div style="border:#DDDDDD solid 1px;width: 100%;margin-top: 10px;padding: 10px;" id="content_box" >
			<span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;
			当前位置：职位管理&nbsp;&gt;&nbsp;职位查询
		<div style="display: inline-block; cursor: pointer;float: right;margin-right: 50px;" id="print"><span class="glyphicon glyphicon-print"></span>&nbsp;打印</div>
			<div style="display: inline-block; cursor: pointer;float: right;margin-right: 150px;" id="importExcel">
			<a style="margin-left: 100px;cursor: pointer;text-decoration: none;color: #000000;" href="${dzx}/jobImportExcel"><span class="glyphicon glyphicon-export" ></span>&nbsp;导出</a>
		</div>
		<hr />
	
	
		<form:form  id="jobform" method="post" modelAttribute="job">
			职位名称：<form:input  path="name" cssClass="inputCss"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button"  id="selectJob" value="搜索" class="btn btn-success"/>&nbsp;
			
			<input type="button"  class="btn btn-danger" value="删除" id="dropJob"/>
		
		<br /><br />
		<table cellspacing="0" cellpadding=""  class="table table-hover table-striped" style="text-align: center;"id="job_tab">
			<tr><th><input type="checkbox" name="caption" id="checkAll" value="0"></th>
			<th>职位名称</th>
			<th>详细信息</th>
			<th>获得该职称人数</th>
			<th>创建日期</th>
			<d:if test="${sessionScope.user.status==1}">
			<th>操作</th>
			</d:if>
			
			</tr>
			<!-- 数据展示 -->
			<d:forEach items="${jobList}" var="jobs">
			<tr><td>
				<input type="checkbox" name="caption" 
						id="box_${jobs.id}"
						value="${jobs.id}" />
				</td>
				<td>${jobs.name}</td>
				<td style="overflow: hidden;">${jobs.remark}</td>
				<d:choose>
				<d:when test="${jobs.jobSum!=null}"><td>${jobs.jobSum}</td></d:when>
				<d:otherwise><td>0</td></d:otherwise>
				</d:choose>
				<td><f:formatDate value="${jobs.create_date}"
								pattern="yyyy年MM月dd日" /></td>
				<d:if test="${sessionScope.user.status==1}">
				<td><a href="${dzx}/editJob?jobId=${jobs.id}"><span class="glyphicon glyphicon-pencil" id="edit" ></span></a></td>
				</d:if>
				</tr>
			</d:forEach>
		</table>
		<!-- 配置pageIndex的隐藏域 -->
			<form:hidden  path="page" id="page_index"/>
			 <!--配置分页的总页数-->
			<form:hidden  path="totalPageSum" id="total_PageSum" /> 
		</form:form>
		<!-- 分页处理 -->
	<nav aria-label="..." style="text-align:center">
		  <div style="display: inline;">共${job.totalPageSum}
				页&nbsp;&nbsp;总记录${job.totalRecord}条&nbsp;&nbsp;</div>
		  <ul class="pagination" >
				<d:choose>
				<d:when test="${job.pageIndex==1}">
				 <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
					
					<d:forEach begin="1" end="${job.totalPageSum}" var="i">
						<d:choose>
						<d:when test="${job.pageIndex==i}">
						<li class="active" id="page_${i}"><a href="#">${i}<span class="sr-only"></span></a></li>
							
						</d:when>
						<d:otherwise>
						 <li class="" id="page_${i}"><a href="#" onclick="toPage( ${i} )">${i}<span class="sr-only"></span></a></li>
							
						</d:otherwise>
						</d:choose>
					</d:forEach>
					<d:choose>
					<d:when test="${job.pageIndex==job.totalPageSum}">
						
						 <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">下一页</span></a></li>
					</d:when>
					<d:otherwise>
					 <li class=""><a href="#" aria-label="Previous" onclick="toPage(${job.pageIndex + 1})"><span aria-hidden="true">下一页</span></a></li>
						
					</d:otherwise>
					</d:choose>
				</d:when>
				
				<d:when test="${job.pageIndex==job.totalPageSum}">
					 <li class=""><a href="#" aria-label="Previous" onclick="toPage(${job.pageIndex-1})"><span aria-hidden="true">上一页</span></a></li>
					<d:forEach begin="1" end="${job.totalPageSum}" var="i">
					<d:choose>
					<d:when test="${job.pageIndex==i}">
							 <li class="active" id="page_${i}"><a href="#">${i}<span class="sr-only"></span></a></li>
					</d:when>
						<d:otherwise>
						<li class="active" id="page_${i}"><a href="#" onclick="toPage(${i})">${i}<span class="sr-only"></span></a></li>
						</d:otherwise>
					</d:choose>
					</d:forEach>
					<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">下一页</span></a></li>
				</d:when>
				<d:otherwise>
				<li class=""><a href="#" aria-label="Previous" onclick="toPage(${i-1})"><span aria-hidden="true">上一页</span></a></li>
					<d:forEach begin="1" end="${job.totalPageSum}" var="i">
					<d:choose>
						<d:when test="${pageIndex==i}">
						 <li class="active" id="page_${i}"><a href="#">${i}<span class="sr-only"></span></a></li>
						</d:when>
						<d:otherwise>
						 <li class="active" id="page_${i}"><a href="#" onclick="toPage( ${i} )">${i}<span class="sr-only"></span></a></li>
						</d:otherwise>
						</d:choose>
					</d:forEach>
					 <li class=""><a href="#" aria-label="Previous" onclick="toPage(${job.pageIndex + 1})"><span aria-hidden="true">下一页</span></a></li>
					
				</d:otherwise>
				</d:choose>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;跳转到&nbsp;&nbsp;
				<input
					style='text-align: center; BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none;height:32px'
					type='text' size='2' id='pager_jump_page_size' />
				&nbsp;
				<input type='button' value='确定' id='pager_jump_btn' onclick="pagerJump()" class="btn btn-info" style="" />
				<li>
 </ul>
		</nav>
		</div>
	
	</body>
		<!-- 打印iframe -->
		<iframe id="printf" src="${dzx}/jobPrint" width="0" height="0" frameborder="0"></iframe>
	
		<script src="${dzx}/js/jquery-1.12.4.js" type="application/javascript"charset="UTF-8"></script>
		<script type="text/javascript">
		/* 打印 */
		$("#print").click(function () {
	            // 生成并打印ifrme  
	            var f = document.getElementById('printf'); 
	            f.contentWindow.print();
		});
		//页面加载函数
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
			$("#job_tab").attr("width",window_width);
			var window_height=$(window).height();
			$("#content_box").attr("height",window_height);
			
		});
		//上下页切换，具体页码
		function toPage(pageIndex) {
			$("#page_index").attr("value", pageIndex);
			$("#jobform").attr("action", "${dzx}/selectJob");
			$("#jobform").submit();

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
				$("#jobform").attr("action", "${dzx}/selectJob");
				$("#jobform").submit();
			}
		}
			/*提交查询*/
			$("#selectJob").click(function(){
				$("#jobform").attr("action", "${dzx}/selectJob");
 		 		$("#jobform").submit();
			});
			/*提交删除*/ 
			$("#dropJob").click(function(){
				var chex=$("input[id^='box_']:checked");
				if (chex.length==0) {
					alert("请选择你要删除的职位");
				} else {
					if(confirm("确定要删除该职位以及该职位下的所有员工吗？")){
						$("#jobform").attr("action", "${dzx}/deleteJob");
		 		 		$("#jobform").submit();
					}
				}
				
			});
			/* 全选和反选*/
			$("#checkAll").click(function(){
				
				$("input[id^='box_']").prop("checked",this.checked);
			});
			
			
		</script>
		
	
</html>
