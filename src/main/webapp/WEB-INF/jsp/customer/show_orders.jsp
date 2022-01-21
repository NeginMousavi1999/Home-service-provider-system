<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>orders</title>
</head>
<h3>your orders</h3>
<body>
<form action="show_suggestions" method="post">
    <label>Choose an Order to see Suggestions:</label>
    <ul>
        <c:forEach var="order" items="${orders}">
            <li><label>
                <input type="radio" value="${order.identity}" name="orderIdentity"/>
            </label>${order.subService.name}
                ,
                    ${order.description}
            </li>
        </c:forEach>
    </ul>

    <br><br>
    <input type="submit" value="Submit"/>
</form>
<br/>
<a href="">back to dashboard</a>

</body>
</html>