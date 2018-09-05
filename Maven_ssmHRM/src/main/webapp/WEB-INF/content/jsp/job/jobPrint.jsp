<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-hover table-striped" style="text-align: center;" align="center" border="1" cellspacing="0" cellpadding="">
			<caption><h1>职位信息表</h1></caption>
			<tr><th>职位编号</th>
			<th>职位名称</th>
			<th>详细信息</th>
			<th>获得该职称人数</th>
			<th>创建日期</th>
			
			
			</tr>
			<!-- 数据展示 -->
			<d:forEach items="${allJob}" var="jobs">
			<tr><td>
				${jobs.id}
				</td>
				<td>${jobs.name}</td>
				<td>${jobs.remark}</td>
				<d:choose>
				<d:when test="${jobs.jobSum!=null}"><td>${jobs.jobSum}</td></d:when>
				<d:otherwise><td>0</td></d:otherwise>
				</d:choose>
				<td><f:formatDate value="${jobs.create_date}"
								pattern="yyyy年MM月dd日" /></td>
				</tr>
			</d:forEach>
		</table>
</body>
</html>