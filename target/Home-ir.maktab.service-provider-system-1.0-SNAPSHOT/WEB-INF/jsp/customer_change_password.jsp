<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer</title>
</head>
<h3>Changing Password Form</h3>
<body>
<form action="/customer/update_password">
    <label>username:</label><label>
    <input type="text" name="userName"/>
</label><br><br>
    <label>old password:</label><label>
    <input type="password" name="oldPass"/>
</label><br><br>
    <label>new password:</label><label>
    <input type="password" name="newPass"/>
</label><br><br>
    <input type="submit" value="update password">
</form>
</body>
</html>