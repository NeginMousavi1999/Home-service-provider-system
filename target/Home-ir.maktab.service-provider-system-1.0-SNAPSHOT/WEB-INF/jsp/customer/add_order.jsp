<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Order</title>
</head>
<h3>Order Form</h3>
<body>
<div id="massage" style="color: green">${succ_massage}</div>
<div id="massage" style="color: red">${error_massage}</div>

<form:form action="add_new_order" modelAttribute="order" method="post">
    <form:input path="country" placeholder='country'/>
    <br><br>
    <form:input path="city" placeholder='city'/>
    <br><br>
    <form:input path="state" placeholder='state'/>
    <br><br>
    <form:input path="postalCode" placeholder='postalCode'/>
    <br><br>
    <label><form:input path="description" placeholder='description'/></label>
    <br><br>
    <label>Choose a Sub Service:</label>

    <ul>
        <c:forEach var="service" items="${set}">
            <li>${service.name}</li>
            <ul>
                <c:forEach var="subservice" items="${service.subServices}">
                    <li><form:radiobutton value="${subservice.name}" path="subServiceName"/>${subservice.name}
                        ,
                            ${subservice.description}
                        ,
                            ${subservice.cost}
                    </li>
                </c:forEach>
            </ul>
        </c:forEach>
    </ul>

    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
<br/>
<a href="">back to dashboard</a>

</body>
</html>