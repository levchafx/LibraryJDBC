<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Past Due Books</title>
</head>
<body>
<h1>Past Due</h1>
<c:forEach items="${books}" var="book">
<c:out value="${book }"></c:out>
<form action="${pageContext.request.contextPath}/front-controller/?Command=Return" method="post">
<input name="bookinstance_id" value="${book.id}" type="hidden">
<input type="submit" value="Return">
</form>
</c:forEach>
</body>
</html>