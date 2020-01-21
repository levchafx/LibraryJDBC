<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>
</head>
<body>
<h1>Messages</h1>
<c:forEach items="${requestScope.messages }" var="message">
<c:out value="${message }"></c:out><br>
<form action="${pageContext.request.contextPath}/front-controller/?Command=DeleteMessage" method="post">
<input name="id" type = "hidden" value="${message.id}">
<input type="submit" value="delete message"><br>
</form>
<form action="${pageContext.request.contextPath}/front-controller/?Command=UpdateUserForm" method="post">
<input name="id" type = "hidden" value="${message.userId}">
<input type="submit" value="update user ">
</form>
<c:if test="${sessionScope.user_id ne message.userId }">
<form action="${pageContext.request.contextPath}/front-controller/?Command=DeleteUser" method="post">
<input name="user_id" type = "hidden" value="${message.userId}">
<input type="submit" value="delete user "><br>
</form>
</c:if>
<form action="${pageContext.request.contextPath}/front-controller/?Command=Bookshelf" method="post">
<input name="user_id" type = "hidden" value="${message.userId}">
<input type="submit" value="view  bookshelf">
</form>
</c:forEach>
</body>
</html>