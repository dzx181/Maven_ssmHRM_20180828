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
<table class="table table-hover table-striped"
				style="font-size: 12px;text-align: center;" align="center" border="1" cellspacing="0">
			<caption><h1>员工信息表</h1></caption>	
				<tr>
					<th>员工编号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>手机号码</th>
					<th>职位</th>
					<th>学历</th>
					<th>身份证号码</th>
					<th>部门</th>
					<th>薪资&nbsp;&nbsp;<span
						class="glyphicon glyphicon-sort-by-attributes"
						style="cursor: pointer;" id="asc"></span>&nbsp;&nbsp;&nbsp; <span
						class="glyphicon glyphicon-sort-by-attributes-alt"
						style="cursor: pointer;" id="desc"></span></th>
					<th>地址</th>
					<th>政治面貌</th>
					<th>生日</th>
					<th>专业</th>

				</tr>
				<d:forEach items="${allEmploy}" var="employs">
					<tr>
						<td>${employs.id}</td>
						<td>${employs.name}</td>
						<td><d:if test="${employs.sex==0}">女</d:if> <d:if
								test="${employs.sex==1}">男</d:if></td>
						<td>${employs.phone}</td>
						<td>${employs.job.name}</td>
						<td>${employs.education}</td>
						<td>${employs.card_id}</td>
						<td>${employs.dept.name}</td>
						<td>${employs.salary}</td>
						<td>${employs.address}</td>
						<td>${employs.party}</td>
						<td><f:formatDate value="${employs.birthday}"
								pattern="yyyy年MM月dd日" /></td>
						<td>${employs.speciality}</td>
					</tr>
				</d:forEach>
			</table>
</body>
</html>