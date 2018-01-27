<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Customer page</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="texts">
    <p><fmt:message key="text.customer.page.heading"/> </p>
    <table>
        <tr>
            <th><fmt:message key="text.visit.id"/></th>
            <th><fmt:message key="text.visit.service"/></th>
            <th><fmt:message key="text.visit.day"/></th>
            <th><fmt:message key="text.visit.hour"/></th>
            <th><fmt:message key="text.visit.master"/></th>
            <th><fmt:message key="text.visit.state"/></th>
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
                    <form name="write_review" method="post" action="controller">
                        <input type="hidden" name="command" value="review_form"/>
                        <input type="hidden" name="idvisit" value="${visit.id}">
                        <input type="submit" value="<fmt:message key="text.review"/> "/>
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
    <br/>
    ${errorMessage}
    <br/>
    <a href="controller?command=logout"><fmt:message key="text.logout"/> </a>
    <a href="controller?command=searching_form"><fmt:message key="text.search"/> </a>
</fmt:bundle>
</body>
</html>
