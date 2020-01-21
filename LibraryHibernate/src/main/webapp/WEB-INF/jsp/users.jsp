<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
</head>
<body>
<h1>Users</h1>
<c:forEach items="${requestScope.users }" var="user">
<c:out value="${user}"></c:out>
<form action="${pageContext.request.contextPath}/front-controller/?Command=UpdateUserForm" method="post">
					<input type="hidden" name="id" value="${user.id}" /> <input
						type="submit" value="Update">
				</form>
<c:if test="${sessionScope.user_id ne user.id }">
<form action="${pageContext.request.contextPath}/front-controller/?Command=DeleteUser" method="post">
<input type="hidden" name="user_id" value="${user.id }">
<input type="submit" value="delete user">
</form>
</c:if>
</c:forEach>
</body>
</html>