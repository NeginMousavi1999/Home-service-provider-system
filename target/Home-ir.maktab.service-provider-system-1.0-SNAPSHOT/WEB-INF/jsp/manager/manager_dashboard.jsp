<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Dashboard</title>
</head>

<body>
This is ${managerDto.email} dashboard
<br/>
<a href="${pageContext.request.contextPath}/portal/admin/dashboard/add_service">Add new Service</a>
<br/>
<a href="${pageContext.request.contextPath}/portal/admin/dashboard/add_subservice">Add new Sub Service</a>
<br/>
<a href="${pageContext.request.contextPath}/portal/admin/dashboard/search">Search</a>
<br/>
<a href="${pageContext.request.contextPath}/">logout</a>
</body>
</html>
