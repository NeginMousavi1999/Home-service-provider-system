<%--@elvariable id="error_massage" type="antlr"--%>
<%--@elvariable id="succ_massage" type="antlr"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Suggestion</title>
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
                       href="${pageContext.request.contextPath}/expert/dashboard">Dashboard</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/service/login">Logout</a>
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
                <h3>Suggestion Form</h3>
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <form action="add_new_suggestion" method="post">
                    <table class="table table-bordered table-striped text-dark">
                        <tr>
                            <td>
                                <label>to be done date</label>
                            </td>
                            <td>
                                <label>
                                    <input type="date" placeholder="to be done date" name="date">
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>suggested price</label>
                            </td>
                            <td>
                                <label>
                                    <input type="number" placeholder="suggested price" name="suggestedPrice">
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>duration of work</label>
                            </td>
                            <td>
                                <label>
                                    <input type="number" placeholder="duration of work" name="durationOfWork">
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>orders</label>
                            </td>
                            <td>
                                <label>
                                    <div class="form-group first">
                                        <c:forEach var="order" items="${list}">
                                            <input type="radio" value="${order.identity}" name="orderDtoIdentity">
                                            description : ${order.description}
                                            <br>
                                            address : ${order.address.city}, ${order.address.state}, ${order.address.postalCode}
                                            <br>
                                            registrationDate : ${order.registrationDate}
                                            <br>
                                        </c:forEach>
                                    </div>
                                </label>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Submit" class="btn btn-block btn-primary"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>