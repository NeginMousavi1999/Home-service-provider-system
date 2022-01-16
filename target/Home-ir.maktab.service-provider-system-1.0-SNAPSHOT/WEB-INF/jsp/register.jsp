<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<h3>Register Form</h3>
<body>
<form:form action="doRegister" modelAttribute="registerData" method="post" enctype="multipart/form-data">
    register as:
    customer <form:radiobutton id="customer" value="CUSTOMER" path="userRole" onclick="customerFunction()"/>
    expert <form:radiobutton id="expert" value="EXPERT" path="userRole" onclick="expertFunction()"/>
    <br><br>
    first name: <form:input path="firstName"/>
    <br><br>
    last name: <form:input path="lastName"/>
    <br><br>
    email: <form:input path="email"/>
    <br><br>
    password: <form:password path="password"/>
    <br><br>
    <div id="expert_info" style="display: none">
        picture: <input type="file" name="file">
        <br><br>
    </div>
    <input type="submit" value="Submit"/>
    <br><br>
</form:form>
<script>
    function expertFunction() {
        document.getElementById("expert_info").style.display = "block";
    }

    function customerFunction() {
        document.getElementById("expert_info").style.display = "none";
    }
</script>
</body>
</html>