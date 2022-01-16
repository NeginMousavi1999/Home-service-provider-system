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
    email: <form:input path="email"
                       pattern="[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$"
                       title="invalid email!"/>
    <br><br>
    password: <form:password path="password" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
                             title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
    <br><br>
    <div id="expert_info" style="display: none">
        picture: <input id="file" type="file" name="file" accept="image/*" onchange="return fileValidation()">
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

    function fileValidation() {
        const fileInput =
            document.getElementById("file");
        const filePath = fileInput.value;
        const allowedExtensions =
            /(\.jpg|\.jpeg|\.png|\.gif)$/i;
        if (!allowedExtensions.exec(filePath)) {
            alert('Invalid file type');
            fileInput.value = '';
            return false;
        }
    }
</script>
</body>
</html>