<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Service</title>
</head>
<h3>Service Form</h3>
<body>
<div id="massage" style="color: green">${succ_massage}</div>
<div id="massage" style="color: red">${error_massage}</div>
<form:form action="add_to_services" modelAttribute="dto" method="post">
    System Services:
    <ul>
        <c:forEach var="service" items="${dto.servicesName}">
            ${service}  <form:checkbox path="servicesName" value="${service}"/>
        </c:forEach>
    </ul>
    <br/>
    <input type="submit" value="Submit"/>
</form:form>
<br/>
<a href="${pageContext.request.contextPath}/expert/dashboard">Back to dashboard</a>
</body>
</html>