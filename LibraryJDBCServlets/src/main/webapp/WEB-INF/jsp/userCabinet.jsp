<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome </title>
</head>
<body>
<c:out value="Welcome ${sessionScope.name}"></c:out><br>
<c:out value ="Your access is ${sessionScope.role}"></c:out>
<form action="${pageContext.request.contextPath}/front-controller/?Command=Bookshelf" method="post">
<input name="user_id" type = "hidden" value="${sessionScope.user_id}">
<input type="submit" value="view your bookshelf">
</form>
<c:if test="${sessionScope.role=='USER'}">
<form action="${pageContext.request.contextPath}/front-controller/?Command=UpdateUserForm" method="post">
					<input type="hidden" name="id" value="${sessionScope.user_id}" /> <input
						type="submit" value="Update">
				</form>
</c:if>
<c:if test="${sessionScope.role == 'ADMIN'}">
<form action="${pageContext.request.contextPath}/front-controller/?Command=Users" method="post">
<input type="submit" value="view users">
</form>
<form action="${pageContext.request.contextPath}/front-controller/?Command=BookInstances" method="post">
<input type="submit" value="books past due">
</form>
<form action="${pageContext.request.contextPath}/registration.jsp" >
<input type="submit" value="add new user">
</form>
<form action="${pageContext.request.contextPath}/front-controller/?Command=Messages" method="post">
<input type="submit" value="view messages">
</form>
<form action="${pageContext.request.contextPath}/addBook.jsp">
<input type="submit" value="add book">
</form>

</c:if>
</body>
</html>