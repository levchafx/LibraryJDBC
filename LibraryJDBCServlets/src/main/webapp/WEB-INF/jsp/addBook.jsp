<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <jsp:include page="index.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add book</title>
</head>
<body>
<br>
<br>
<br>
<br>
<h1>add book</h1>
<form action="${pageContext.request.contextPath}/front-controller/?Command=AddBook" method="post"  >
Title<input required="required" type="text" name="title" ><br>
Description<textarea  rows="5" cols="40" name="description" required></textarea><br>
Quantity<input required type="text" name="quantity"><br>
Authors<input type="text"  name="authors" required><br>
ImageName<input type="text" name="image_name" ><br>
Image<input type="file" name="image" ><br>
<input type="submit" value="add book">
</form>
</body>
</html>