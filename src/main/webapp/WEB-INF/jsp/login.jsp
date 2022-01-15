<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<h3>Login Form</h3>
<body>
<form:form action="doLogin" modelAttribute="loginData">
    user name: <form:input path="username"/>
    <br><br>
    password: <form:input path="password" type="password"/>
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>