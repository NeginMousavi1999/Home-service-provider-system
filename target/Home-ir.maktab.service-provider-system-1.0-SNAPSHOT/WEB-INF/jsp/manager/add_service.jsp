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
    service name: <form:input path="name"/>
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
<br/>
<a href="/portal/admin/doLogin">back to dashboard</a>

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