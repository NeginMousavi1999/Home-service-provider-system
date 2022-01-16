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
    password: <form:password path="password" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
                             title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>