<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<body>
	<h1>Description</h1>
	<c:out value="${requestScope.description }"></c:out>
	<br>
	<c:if test="${requestScope.image ne null}">
	<img src="data:image/jpg;base64,${requestScope.image}" >
	</c:if>
</body>
</html>