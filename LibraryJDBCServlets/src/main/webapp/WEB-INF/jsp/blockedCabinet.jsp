<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blocked Cabinet</title>
</head>
<body>
<h2>Your account has been suspended by admin</h2>
<form action="${pageContext.request.contextPath}/frontController/?Command=SendMessage" method="post">
<input name="userId" value ="${sessionScope.user_id}" type="hidden">
<br>
type your message here<br>
<textarea name="message" cols="40" rows="3" ></textarea><br>
<input type="submit" value="Send message">
</form>
</body>
</html>