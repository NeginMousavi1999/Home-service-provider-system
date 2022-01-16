<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
                    <h3>Login to <strong>Home Service Provider System</strong></h3>
                    <p class="mb-4">Thanks for choosing us ^_^</p>
                    <div id="massage" style="color: red">${massage}</div>

                    <form:form action="doLogin" modelAttribute="loginData">
                        <div class="form-group first">
                            <label for="username">username</label>
                            <form:input path="username"/>
                        </div>
                        <div class="form-group last mb-3">
                            <label for="password">password</label>
                            <form:password path="password" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
                                           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
                        </div>

                        <div class="d-flex mb-5 align-items-center">
                            <label class="control control--checkbox mb-0"><span class="caption">Remember me</span>
                                <input type="checkbox" checked="checked"/>
                                <div class="control__indicator"></div>
                            </label>

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
</body>
</html>