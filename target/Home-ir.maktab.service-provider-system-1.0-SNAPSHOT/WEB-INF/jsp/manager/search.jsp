<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>search and filter users</title>
</head>
<h3>search and filter users Form</h3>
<body>
<form:form action="doFilter" modelAttribute="filterData" method="post">
    first name: <form:input path="firstName"/>
    <br><br>
    last name: <form:input path="lastName"/>
    <br><br>
    email: <form:input path="email"/>
    <br><br>
    role:
    customer <form:radiobutton id="customer" value="CUSTOMER" path="userRole" onclick="customerFunction()"/>
    expert <form:radiobutton id="expert" value="EXPERT" path="userRole" onclick="expertFunction()"/>
    <br><br>

<%--    <div id="expert_info" style="display: none">
        <c:forEach var="meal" items="${loginData.food}">
            ${service}<form:checkbox path="" value="${service}"/>
        </c:forEach>
    </div>--%>


    <input type="submit" value="Submit"/>
</form:form>

<div>
    <ul>
    <c:forEach var="user" items="${users}">
        <li>${user}</li>
    </c:forEach>
    </ul>
</div>


<script>
    function expertFunction() {
        document.getElementById("expert_info").style.display = "block";
    }

    function customerFunction() {
        document.getElementById("expert_info").style.display = "none";
    }
</script>
</body>
</html>