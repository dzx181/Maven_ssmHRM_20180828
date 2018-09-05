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
			<table cellspacing="0" cellpadding="" width="100%"
				class="table table-hover table-striped"
				style="text-align: center;" border="1" align="center">
				<caption><h1>用户信息表</h1></caption>
				<tr id="tr_th">
					<th>用户编号</th>
					<th>登录名</th>
					<d:if test="${sessionScope.user.status==1}">
					<th>密码</th>
					</d:if>
					<th>用户名</th>
					<th>状态</th>
					<th>创建时间</th>
					
				</tr>
				<d:forEach items="${allUser}" var="users">
				<tr><td>
				${users.id}
				</td>
				<td>${users.loginname}</td>
				<d:if test="${sessionScope.user.status==1}">
				<td>${users.password}</td>
				</d:if>
				<td>${users.username}</td>
				<d:choose>
				<d:when test="${users.status==1}"><td>超级管理员</td></d:when>
				<d:otherwise><td>普通用户</td></d:otherwise>
				</d:choose>
				
				<td>
				<f:formatDate value="${users.createDate}" pattern="yyyy年MM月dd日"/>
				</td>
				</tr>
			
		
				</d:forEach>
				
			</table>
</body>
</html>