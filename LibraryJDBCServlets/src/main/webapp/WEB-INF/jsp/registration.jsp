<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>

	<h1>Register form</h1>
	<br>
	<br>
	<c:out value="${error}"></c:out>

	<br>
	<br>
	<form method="POST"
		action="${pageContext.request.contextPath}/front-controller/?Command=Registration">
		
		name <input type="text" name="name" required ><br> <br>
		surname <input type="text" name="surname" required ><br> <br>
		email <input type="text" name="email" value="${requestScope.emailexists }"required ><br> <br>
		age <input type="text" name="age"><br> <br>
		login <input type="text" name="login" value="${requestScope.userexists }" required ><br> <br>
		password <input type="password" name="password" value="${requestScope.passworddoesntmatch }"required ><br> <br>
		confirm password<input type="password" name="confirmpassword" required ><br>
		<c:if test="${sessionScope.role== 'ADMIN'}">
		role<select name="role">
					<option>ADMIN</option>
					<option>USER</option>
				</select>
		</c:if>
		<br> <input type="submit" value="Register">
	</form>
</body>
</html>