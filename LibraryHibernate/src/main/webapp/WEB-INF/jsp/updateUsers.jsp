<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update user</title>
</head>
<body>
<h1>Update user</h1>

<form action="${pageContext.request.contextPath}/front-controller/?Command=UpdateUser" method="post">
ID<input type="text" readonly="readonly" name="user_id" value="${requestScope.user.id }"><br>
NAME<input type="text" name="user_name" value="${requestScope.user.name }"><br>
SURNAME<input type="text" name="user_surname" value="${requestScope.user.surname }"><br>
AGE<input type="text" name="user_age" value="${requestScope.user.age }"><br>
EMAIL<input type="text" name="user_email" value="${requestScope.user.email }"><br>
<c:if test="${sessionScope.role=='ADMIN' }">
ROLE<input type="text" name="user_role" value="${requestScope.user.role }"><br>
</c:if>
<c:if test="${sessionScope.role=='USER' }">
ROLE<input type="text" readonly="readonly" name="user_role" value="${requestScope.user.role }"><br>
</c:if>
<input type="hidden" name="user_authenticate_id" value="${requestScope.user.authenticate.id }">
LOGIN<input type="text" name="user_login" value="${requestScope.user.authenticate.login }"><br>
PASSWORD<input type="text" name="user_password" value="${requestScope.user.authenticate.password }"><br>
<c:if test="${sessionScope.role=='ADMIN' }">
PROFILE_ENABLE<input type="text" name="user_profile_enable" value="${requestScope.user.authenticate.profileEnabled }"><br>
</c:if>
<c:if test="${sessionScope.role=='USER' }">
PROFILE_ENABLE<input type="text" readonly="readonly" name="user_profile_enable" value="${requestScope.user.authenticate.profileEnabled }"><br>
</c:if>
<input type="submit" value="update user">
</form>

</body>
</html>