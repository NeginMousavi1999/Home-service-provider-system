<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Order</title>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/customer/all_orders">Back</a>
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
                <h3>Order Form</h3>
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <form:form action="add_new_order" modelAttribute="order" method="post">
                    <table class="table table-bordered table-striped text-dark">
                        <tr>
                            <td>
                                <label>country</label>
                            </td>
                            <td>
                                <label>
                                    <form:input path="country"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>city</label>
                            </td>
                            <td>
                                <label>
                                    <form:input path="city"/>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>state</label>
                            </td>
                            <td>
                                <label>
                                    <form:input path="state"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Postal Code</label>
                            </td>
                            <td>
                                <label>
                                    <form:input path="postalCode"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Description</label>
                            </td>
                            <td>
                                <label>
                                    <form:input path="description"/>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>Sub Services</label>
                            </td>
                            <td>
                                <label>
                                    <div class="form-group first">
                                        <c:forEach var="service" items="${set}">
                                            ${service.name}
                                            <ul>
                                                <c:forEach var="subservice" items="${service.subServices}">
                                                    <li><form:radiobutton value="${subservice.name}"
                                                                          path="subServiceName"/>
                                                        name : ${subservice.name}
                                                        <br>
                                                        description : ${subservice.description}
                                                        <br>
                                                        cost : ${subservice.cost}
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </c:forEach>
                                    </div>
                                </label>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Submit" class="btn btn-block btn-primary"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>