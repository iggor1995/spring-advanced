,%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Ticket JSP View</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Date</td>
        <td>User</td>
    </tr>
    <tr>
        <td>${ticket.event.name}</td>
        <td>${ticket.dateTime}</td>
        <td>${ticket.user.name}</td>
    </tr>
</table>
</body>
</html>