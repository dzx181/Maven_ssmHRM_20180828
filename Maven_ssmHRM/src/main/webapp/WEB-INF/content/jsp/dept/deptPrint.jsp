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
<table cellspacing="0" cellpadding=""  class="table table-hover table-striped" style="text-align: center;" align="center" border="1">
			
		<caption><h1>部门信息表</h1></caption>
			<tr><th>部门编号</th>
			<th>部门名称</th>
			<th>部门描述</th>
			<th>部门总人数</th>
			<th>创建日期</th>
			</tr>
			<!-- 数据展示 -->
			<d:forEach items="${allDept}" var="depts">
			<tr><td>
				${depts.id}
				</td>
				<td>${depts.name}</td>
				<td>${depts.remark}</td>
				<d:choose>
				<d:when test="${depts.dept_sum!=null}"><td>${depts.dept_sum}</td></d:when>
				<d:otherwise><td>0</td></d:otherwise>
				</d:choose>
				<td><f:formatDate value="${depts.create_date}"
								pattern="yyyy年MM月dd日" /></td>
				</tr>
			</d:forEach>
		</table>
</body>
</html>