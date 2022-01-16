<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/index_style.css">
</head>
<body>
<div>
    <div class="main-div justify-content-center align-items-center">
        <div id="tytle"><h2 class="text-center">Welcome to Home service provider system</h2></div>
        <div class="sub-div row text-center justify-content-center">
            <div class="a-div col-6"><a href="${pageContext.request.contextPath}/service/login">login</a></div>
            <div class="a-div col-6"><a href="${pageContext.request.contextPath}/service/register">register</a></div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/static/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>
</body>
</html>
