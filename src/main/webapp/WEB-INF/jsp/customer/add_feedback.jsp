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
                <h3>Choose your Order to feedback</h3>
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <form action="${pageContext.request.contextPath}/customer/feedback_page" method="post">
                    <table class="table table-bordered table-striped text-dark">
                        <c:forEach var="order" items="${paid_orders}">
                            <tr>
                                <td>
                                    <label>
                                        <div class="form-group first">
                                            sub service : ${order.subService.name}
                                            <br>
                                            cost : ${order.finalPrice}
                                            <br>
                                            expert : ${order.expert.firstName} ${order.expert.firstName} , score
                                            : ${order.expert.score}
                                            <br>
                                            registration date : ${order.registrationDate}
                                            <br>
                                            done date : ${order.toBeDoneDate}
                                        </div>
                                    </label>
                                </td>
                                <td>
                                    <label>
                                        <input type="radio" value="${order.identity}"
                                               name="orderIdentity"/>
                                    </label>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="submit" value="I wanna add feddback for this order" class="btn btn-block btn-primary"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>