<%--@elvariable id="error_massage" type="antlr"--%>
<%--@elvariable id="succ_massage" type="antlr"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Orders Status</title>
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
                <h3>Orders Form</h3>
                <hr>
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>

                <form:form modelAttribute="orderIdentityDto" action="update_orders" method="post">
                    <h3>Starting</h3>
                    <table class="table table-bordered table-striped text-dark">
                        <c:forEach var="order" items="${startingOrders}">
                            <tr>
                                <td>
                                    <label>
                                        <form:radiobutton value="${order.identity}" path="start"/>
                                    </label>
                                    to Be Done Date : ${order.toBeDoneDate}<br>
                                    description : ${order.description}<br>
                                    address
                                    : ${order.address.country}, ${order.address.city}, ${order.address.state}, ${order.address.postalCode}<br>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br><br>

                    <h3>Finishing</h3>
                    <table class="table table-bordered table-striped text-dark">
                        <c:forEach var="order" items="${finishingOrders}">
                            <tr>
                                <td>
                                    <label>
                                        <form:radiobutton value="${order.identity}" path="finish"/>
                                    </label>
                                    to Be Done Date : ${order.toBeDoneDate}<br>
                                    description : ${order.description}<br>
                                    address
                                    : ${order.address.country}, ${order.address.city}, ${order.address.state}, ${order.address.postalCode}<br>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="submit" value="Submit" class="btn btn-block btn-primary" onsubmit="orderFinction()"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%--<script>
    function orderFinction() {
        let finishRadios = document.getElementsByName('finish_identity');
        let finishChecked = false;
        for (var i = 0, length = finishRadios.length; i < length; i++) {
            if (finishRadios[i].checked) {
                finishChecked = true;
                break;
            }
        }
        if (!finishChecked) {
            finishRadios.value = -1;
        }
        let startRadios = document.getElementsByName('finish_identity');
        let startChecked = false;
        for (i = 0, length = startRadios.length; i < length; i++) {
            if (startRadios[i].checked) {
                startChecked = true;
                break;
            }
        }
        if (!startChecked) {
            startRadios.value = -1;
        }
    }
</script>--%>
</body>
</html>