<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Expert Dashboard</title>
</head>

<body>
This is ${expert.email} dashboard
<br/>
<a href="${pageContext.request.contextPath}/">logout</a>
<br/>
<a href="${pageContext.request.contextPath}/expert/add_service">add new service</a>
</body>
</html>
