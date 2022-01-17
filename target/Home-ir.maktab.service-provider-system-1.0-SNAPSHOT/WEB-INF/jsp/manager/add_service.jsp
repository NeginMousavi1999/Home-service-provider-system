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
<form:form action="add_new_service" modelAttribute="service" method="post">
    <form:input path="name" placeholder='service name'/>
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
<br/>
<a href="${pageContext.request.contextPath}/portal/admin/doLogin">back to dashboard</a>
</body>
</html>