<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/front-controller/?Command=Search" method="post">
<input type="text" name="search" value="enter title or author">
<input type="submit" value="Search">
</form>
</body>
</html>