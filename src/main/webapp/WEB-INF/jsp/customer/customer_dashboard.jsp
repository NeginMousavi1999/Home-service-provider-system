<%--@elvariable id="customerDto" type="antlr"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/dashboard_style.css">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light box-shadow-style">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">Home Service Provider System</a>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        </ul>
        <div class="d-flex align-items-center">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/customer/change_password">change password</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0" href="${pageContext.request.contextPath}/">Logout</a>
                </li>

            </ul>
        </div>

    </div>
</nav>
<div class="main mt-5">
    <div class="row">
        <div class="col-12">
            <div class="w-100 h-100 rounded pricing-text">
                <h1>Hi ${customerDto.firstName}</h1>
            </div>
        </div>
        <div class="col-12">
            <div class="w-100 h-100 rounded Quickly-text">
                <p>We are so glad to have you here.</p>
            </div>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 p-5 w-100 h-100 rounded">
            <div class="row p-5">
                <div class="outer-box w-100">
                    <div class="col-12 top-box">
                        <h3><a href="${pageContext.request.contextPath}/customer/add_order">Add Order</a></h3>
                    </div>
<%--                    <div class="card-body d-flex flex-column bottom-box">
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>10 users included</li>
                            <li>2 GB of storage</li>
                            <li>Email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary mt-auto">Sign up for
                            free</button>
                    </div>--%>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 p-5 w-100 h-100 rounded">
            <div class="row p-5">
                <div class="outer-box w-100">
                    <div class="col-12 top-box">
                        <h3><a href="${pageContext.request.contextPath}/customer/show_orders">Your Suggestions</a></h3>
                    </div>
                    <%--                    <div class="card-body d-flex flex-column bottom-box">
                                            <ul class="list-unstyled mt-3 mb-4">
                                                <li>10 users included</li>
                                                <li>2 GB of storage</li>
                                                <li>Email support</li>
                                                <li>Help center access</li>
                                            </ul>
                                            <button type="button" class="btn btn-lg btn-block btn-outline-primary mt-auto">Sign up for
                                                free</button>
                                        </div>--%>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 p-5 w-100 h-100 rounded">
            <div class="row p-5">
                <div class="outer-box w-100">
                    <div class="col-12 top-box">
                        <h3><a href="#">Add Feedback</a></h3>
                    </div>
                    <%--                    <div class="card-body d-flex flex-column bottom-box">
                                            <ul class="list-unstyled mt-3 mb-4">
                                                <li>10 users included</li>
                                                <li>2 GB of storage</li>
                                                <li>Email support</li>
                                                <li>Help center access</li>
                                            </ul>
                                            <button type="button" class="btn btn-lg btn-block btn-outline-primary mt-auto">Sign up for
                                                free</button>
                                        </div>--%>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
