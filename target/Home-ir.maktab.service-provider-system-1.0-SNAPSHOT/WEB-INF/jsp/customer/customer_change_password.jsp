<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer</title>
</head>
<h3>Changing Password Form</h3>
<body>
<form action="${pageContext.request.contextPath}/customer/update_password">
    <label><input type="text" name="userName" placeholder='username'/>
</label><br><br>
    <label><input type="password" name="oldPass" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"
           placeholder='old password'/>
</label><br><br>
    <label><input type="password" name="newPass" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"
           placeholder='new password'/>
</label><br><br>
    <input type="submit" value="update password">
</form>
</body>
</html>