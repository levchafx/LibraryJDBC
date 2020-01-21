<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>MyLibrary</title>
<link rel="stylesheet" type="text/css" href="mylibrary.css" />
</head>
<body>
	<h1>My Library</h1>
	<ul>
		<li><a href="userCabinet.jsp">Profile</a></li>
		<li><a href="${pageContext.request.contextPath}/front-controller/?Command=BookRepository">Repository</a></li>
		<li><a href="search.jsp">Search</a></li>
		<li><a href="registration.jsp">Register</a></li>
		<c:if test="${sessionScope.user_id!=null }">
			<li><a href="${pageContext.request.contextPath}/front-controller/?Command=Logout">Logout</a></li>
		</c:if>
		<c:if test="${sessionScope.user_id==null }">
			<li><a href="login.jsp">Login</a></li>
		</c:if>
	</ul>
</body>
</html>