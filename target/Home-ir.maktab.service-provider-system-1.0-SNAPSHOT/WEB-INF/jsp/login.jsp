<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<h3>Login Form</h3>
<body>
<div id="massage" style="color: red">${massage}</div>
<form:form action="doLogin" modelAttribute="loginData">
    user name: <form:input path="username"/>
    <br><br>
    password: <form:password path="password"/>
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>