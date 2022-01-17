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
    <form:input path="firstName" placeholder='first name'/>
    <br><br>
    <form:input path="lastName" placeholder='last name'/>
    <br><br>
    <form:input path="email" placeholder='email'/>
    <br><br>
    role:
    customer <form:radiobutton id="customer" value="CUSTOMER" path="userRole" onclick="customerFunction()"/>
    expert <form:radiobutton id="expert" value="EXPERT" path="userRole" onclick="expertFunction()"/>
    <br><br>
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