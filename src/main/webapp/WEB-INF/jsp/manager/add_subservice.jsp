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
<form:form action="add_new_subservice" modelAttribute="subservice" method="post">
    <form:input path="name" placeholder='sub service name'/>
    <br><br>
<label><form:input path="description" placeholder='description'/></label>
    <br><br>
    <label><form:input type="number" path="cost" placeholder='cost'/></label>
    <br><br>
    <label>Choose a Service:</label>
    <form:select path="serviceName" items="${list}" />
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
<br/>
<a href="${pageContext.request.contextPath}/portal/admin/doLogin">back to dashboard</a>

</body>
</html>