<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>Login</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>


	<h1>Login</h1>

	<c:out value="${notfound}"></c:out>
	<br>
	<br>
	<form method="POST" action="${pageContext.request.contextPath}/front-controller/?Command=Login">
		name<input type="text" name="login"><br> <br>
		password<input type="password" name="password"><br> <br>
		<input type="submit" value="Login">
	</form>
</body>
</html>