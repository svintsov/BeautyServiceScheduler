<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Hello,Admin</p>
<table>
    <tr>
        <th>ID</th>
        <th>Service</th>
        <th>Day</th>
        <th>Hour</th>
        <th>Customer</th>
        <th>Master</th>
        <th>State</th>
        <th>Review</th>
    </tr>
    <c:forEach var="visit" items="${requestScope.visits}">
        <tr>
            <td>
                <c:out value="${visit.id}"/>
            </td>
            <td>
                <c:out value="${visit.beautyServiceType}"/>
            </td>
            <td>
                <c:out value="${visit.getStringDay()}"/>
            </td>
            <td>
                <c:out value="${visit.start.toString()}"/>
            </td>
            <td>
                <c:out value="${visit.customer.getId()}" default="null"/>
            </td>
            <td>
                <c:out value="${visit.master.getId()}"/>
            </td>
            <td>
                <c:out value="${visit.state}"/>
            </td>
            <td>
                <c:out value="${visit.review}" default="null"/>
            </td>
            <td>
                <form name="visit_element" method="POST" action="controller">
                    <input type="hidden" name="command" value="delete_visit" />
                    <input type="hidden" name="idvisit" value="${visit.id}" />
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
    <a href="controller?command=logout">Logout</a>
</body>
</html>
