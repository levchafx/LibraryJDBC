<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Repository</title>
</head>
<body>
	<h1>Books</h1>
	<table>
	
	<tr>
	
	<td>Title</td>
	<td>Authors</td>
	<td>Quantity</td>
	<td>Description</td>
	<td>Get</td>
	</tr>
	<c:forEach items="${requestScope.books }" var="book">
	<tr><td>${book.title}</td><td><c:forEach items="${book.authors }" var ="author">
<c:out value="${author.name } ${author.surname }"></c:out><br>
	</c:forEach>
	
	</td><td>${book.quantity }</td><td><form
			action="${pageContext.request.contextPath}/front-controller/?Command=Description" method="post">
			<input name="description" type="hidden" value="${book.description}">
			<input name="image_id" type="hidden" value="${book.id}"> <input
				type="submit" value="Read more">
		</form></td><td><c:if test="${sessionScope.user_id ne null and book.quantity>0 }">
		<form action="${pageContext.request.contextPath}/front-controller/?Command=GetBook"
			method="post">
			<input name="book_id" type="hidden" value="${book.id}"> <input
				name="user_id" type="hidden" value="${sessionScope.user_id}">
			how many weeks would you like to read it for?<br>
			<select name="weeks">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
			</select> <input type="submit" value="Get book"> <br>
		</form>
		</c:if></td></tr>
	</c:forEach>
	</table>
	
</body>
</html>