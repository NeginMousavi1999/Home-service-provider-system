<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Dashboard</title>
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
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/">Logout</a>
                </li>

            </ul>
        </div>

    </div>
</nav>
<div class="main mt-5">
    <div class="row">
        <div class="col-12">
            <div class="w-100 h-100 rounded pricing-text">
                <h1>Admin Dashboard</h1>
            </div>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-3 p-5 w-100 h-100 rounded">
            <div class="row p-5">
                <div class="outer-box w-100">
                    <div class="col-12 top-box">
                        <h4><a href="${pageContext.request.contextPath}/portal/admin/dashboard/add_service">add new Service to System</a></h4>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-3 p-5 w-100 h-100 rounded">
            <div class="row p-5">
                <div class="outer-box w-100">
                    <div class="col-12 top-box">
                        <h4><a href="${pageContext.request.contextPath}/portal/admin/dashboard/add_subservice">Add new Sub Service to System</a>
                        </h4>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-3 p-5 w-100 h-100 rounded">
            <div class="row p-5">
                <div class="outer-box w-100">
                    <div class="col-12 top-box">
                        <h4><a href="${pageContext.request.contextPath}/portal/admin/dashboard/search">Search</a></h4>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-3 p-5 w-100 h-100 rounded">
            <div class="row p-5">
                <div class="outer-box w-100">
                    <div class="col-12 top-box">
                        <h4><a href="${pageContext.request.contextPath}/portal/admin/dashboard/confirm_user">Confirm new users</a></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
