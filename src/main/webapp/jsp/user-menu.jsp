<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Customer page</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js">
    </script>
    <script src="js/paginathing.js"></script>
    <script type="text/javascript" src="js/scripts.js"></script>
    <link rel="stylesheet" href="css/material.min.css">
    <script src="js/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="texts">
    <h2><fmt:message key="text.customer.page.heading"/></h2>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
        <tr>
            <th><fmt:message key="text.visit.id"/></th>
            <th class="mdl-data-table__cell--non-numeric"><fmt:message
                    key="text.visit.service"/></th>
            <th><fmt:message key="text.visit.day"/></th>
            <th><fmt:message key="text.visit.hour"/></th>
            <th><fmt:message key="text.visit.master"/></th>
            <th class="mdl-data-table__cell--non-numeric"><fmt:message key="text.visit.state"/></th>
        </tr>
        <c:forEach var="visit" items="${requestScope.visits}">
            <tr>
                <td>
                    <c:out value="${visit.id}"/>
                </td>
                <td class="mdl-data-table__cell--non-numeric">
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
                <td class="mdl-data-table__cell--non-numeric">
                    <c:out value="${visit.state}"/>
                </td>
                <td>
                    <form name="write_review" method="post" action="controller">
                        <input type="hidden" name="command" value="review_form"/>
                        <input type="hidden" name="idvisit" value="${visit.id}">
                        <input type="submit" value="<fmt:message key="text.review"/> "
                               class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"/>
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
