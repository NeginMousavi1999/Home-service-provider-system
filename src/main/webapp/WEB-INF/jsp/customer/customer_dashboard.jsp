<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
</head>

<body>
This is ${customer.email} dashboard
<br/>
<a href="${pageContext.request.contextPath}/customer/change_password">change password</a>
<br/>
<a href="${pageContext.request.contextPath}/customer/add_order">Add order</a>
<br/>
<a href="${pageContext.request.contextPath}/">logout</a>
</body>
</html>
