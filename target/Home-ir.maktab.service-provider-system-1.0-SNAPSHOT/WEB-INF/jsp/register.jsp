<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/login_style.css">
</head>
<body>
<div class="d-lg-flex half">
    <div class="bg order-1 order-md-2" style="background-image: url('../../resources/static/images/bg_1.jpg');"></div>
    <div class="contents order-2 order-md-1">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7">
                    <h3>Register to <strong>Home Service Provider System</strong></h3>
                    <p class="mb-4">Thanks for choosing us ^_^</p>

                    <form:form action="doRegister" modelAttribute="registerData" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>register as</label>
                            <label>customer</label>
                            <form:radiobutton id="customer" value="CUSTOMER" path="userRole" onclick="customerFunction()"/>
                            <label>expert</label>
                            <form:radiobutton id="expert" value="EXPERT" path="userRole" onclick="expertFunction()"/>
                        </div>

                        <div class="form-group">
                            <label>first name</label>
                            <form:input path="firstName"/>
                        </div>

                        <div class="form-group">
                            <label>last name</label>
                            <form:input path="lastName"/>
                        </div>

                        <div class="form-group">
                            <label>email</label>
                            <form:input path="email"
                                        pattern="[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$"
                                        title="invalid email!"/>
                        </div>

                        <div class="form-group mb-3">
                            <label for="password">password </label>
                            <form:password path="password" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
                                           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
                        </div>

                        <div id="expert_info" class="form-group mb-3" style="display: none">
                            <label class="form-label">picture</label>
                            <input id="formFileSm" class="form-control form-control-sm" type="file" name="file" accept="image/*" onchange="return fileValidation()">
                        </div>
                        <input type="submit" value="Login" class="btn btn-block btn-primary"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/static/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/register.js"></script>
</body>
</html>