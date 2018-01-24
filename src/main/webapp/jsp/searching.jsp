<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="searching_visit" method="post" action="controller">
    <input type="hidden" name="command" value="search_visit"/>
    Services
    <select name="services_select" required>
        <option value="AROMATHERAPY">AROMATHERAPY</option>
        <option value="MANICURE">MANICURE</option>
        <option value="PEDICURE">PEDICURE</option>
        <option value="REFLEXOLOGY">REFLEXOLOGY</option>
    </select><br/>
    Day of visit:<label><input name="day" type="date" required placeholder="Day"></label><br/>
    <input type="submit" value="Search"/>
    <br/>
    ${errorSearchVisitMessage}
    <br/>
</form>

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
            <td>
                <form name="visit_element_reserve" method="POST" action="controller">
                    <input type="hidden" name="command" value="reserve_visit" />
                    <input type="hidden" name="idvisit" value="${visit.id}" />
                    <input type="submit" value="Reserve"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
${errorMessage}
<br/>
</body>
</html>
