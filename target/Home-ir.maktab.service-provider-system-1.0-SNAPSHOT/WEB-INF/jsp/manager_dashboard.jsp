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
<a href="/portal/admin/dashboard/add_service">Add new Service</a>
<br/>
<a href="/portal/admin/dashboard/search">Search</a>
<br/>
<a href="/">logout</a>
</body>
</html>
