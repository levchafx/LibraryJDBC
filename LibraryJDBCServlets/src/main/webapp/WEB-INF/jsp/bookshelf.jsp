<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>Bookshelf</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>


	<h1>Bookshelf</h1>

	<c:out value="${requestScope.bookshelfempty}"></c:out>
	<br>
	<br>
	<c:forEach items="${books}" var="book">
	<c:out value="${book}"></c:out>
	<br>
	<br>
<form action="${pageContext.request.contextPath}/front-controller/?Command=Return" method="post">
<input name="bookinstance_id" value="${book.id}" type="hidden">
<input type="submit" value="Return">
</form>	
	 </c:forEach>
</body>
</html>