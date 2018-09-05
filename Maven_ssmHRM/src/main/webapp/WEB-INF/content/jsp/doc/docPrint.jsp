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
<table  cellspacing="0" cellpadding="" width="100%"
				class="table table-hover table-striped"
				style="text-align: center;" border="1" align="center">
			<tr><th>文件编号</th>
			<th>标题</th><th>文件名</th><th>创建时间</th><th>创建人</th><th>描述</th></tr>
			<d:forEach items="${allDoc}" var="docs">
			<tr><td>${docs.id}</td>
			<td>${docs.title}</td>
			<td>${docs.filename}</td>
			<td><f:formatDate value="${docs.create_date}" pattern="yyyy年MM月dd日"/></td>
			<td>${docs.user.username}</td>
			<td>${docs.remark}</td>
			</d:forEach>
		</table>
</body>
</html>