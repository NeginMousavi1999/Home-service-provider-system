<%--@elvariable id="error_massage" type="antlr"--%>
<%--@elvariable id="succ_massage" type="antlr"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer</title>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/customer/bank">Increase credit</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/customer/dashboard">Dashboard</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br><br>
<div class="contents order-2 order-md-1">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-7">
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                    <table class="table table-bordered table-striped text-dark">
                        <thead>
                        <tr>
                            <th colspan="5" style="text-align: center">
                                order information
                            </th>
                        </tr>
                        <tr>
                            <th>
                                subservice
                            </th>
                            <th>
                                cost
                            </th>
                            <th>
                                description
                            </th>
                            <th colspan="2">
                                payment method
                            </th>
                        </tr>
                        </thead>
                        <c:forEach var="order" items="${done_orders}">
                            <tr>
                                <td>
                                        ${order.subService.name}
                                </td>
                                <td>
                                        ${order.finalPrice}
                                </td>
                                <td>
                                        ${order.description}
                                </td>
                                <td>
                                    <a class="btn btn-outline-primary my-2 my-sm-0"
                                       href="${pageContext.request.contextPath}/customer/paying_from_credit/${order.identity}">from
                                        your credit</a>

                                </td>
                                <td>
                                    <a class="btn btn-outline-primary my-2 my-sm-0"
                                       href="${pageContext.request.contextPath}/customer/paying_online/${order.identity}">online</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>