<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<h3>Register Form</h3>
<body>
<form:form action="doRegister" modelAttribute="registerData">
    user name: <form:input path="username"/>
    <br><br>
    password: <form:input path="password" type="password"/>
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>