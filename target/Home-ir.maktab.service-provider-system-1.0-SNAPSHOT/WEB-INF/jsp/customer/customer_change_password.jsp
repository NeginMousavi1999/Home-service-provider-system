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
    <input type="password" name="oldPass" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
</label><br><br>
    <label>new password:</label><label>
    <input type="password" name="newPass" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
</label><br><br>
    <input type="submit" value="update password">
</form>
</body>
</html>