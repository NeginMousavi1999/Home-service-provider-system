<%--@elvariable id="error_massage" type="antlr"--%>
<%--@elvariable id="succ_massage" type="antlr"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Service</title>
</head>
<h3>Service Form</h3>
<body>
<div style="color: green">${succ_massage}</div>
<div style="color: red">${error_massage}</div>
<form action="add_new_suggestion" method="post">
    <label>
        <input type="date" placeholder="to be done date" name="date">
    </label>
    <br/>
    <label>
        <input type="number" placeholder="suggested price" name="suggestedPrice">
    </label>
    <br/>
    <label>
        <input type="number" placeholder="duration of work" name="durationOfWork">
    </label>
    <br/>
    <ul>
        <c:forEach var="order" items="${list}">
            <li><label>
                <input type="radio" value="${order.identity}" name="orderDtoIdentity">
            </label>description:${order.description},
                address:${order.address}, registrationDate:${order.registrationDate}</input></li>
        </c:forEach>
    </ul>
    <br/> <br/>
    <input type="submit" value="Submit"/>
</form>
<br/>
<a href="${pageContext.request.contextPath}/expert/dashboard">Back to dashboard</a>
</body>
</html>