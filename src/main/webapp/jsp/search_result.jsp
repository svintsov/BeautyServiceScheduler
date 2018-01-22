<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Result of search</h3>

<table>
    <tr>
        <th>ID</th>
        <th>Service</th>
        <th>Day</th>
        <th>Hour</th>
        <th>Master</th>
        <th>State</th>
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
                <c:out value="${visit.master.getId()}"/>
            </td>
            <td>
                <c:out value="${visit.state}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
${errorMessage}
<br/>
</body>
</html>
