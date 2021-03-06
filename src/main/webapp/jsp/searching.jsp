<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Searching</title>
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
<div class="container_my">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="texts">
        <form name="searching_visit" method="post" action="controller">
            <input type="hidden" name="command" value="search_visit"/>
            <fmt:message key="text.visit.service"/>:
            <select name="services_select" required>
                <option value="AROMATHERAPY"><fmt:message key="text.service.aromatherapy"/></option>
                <option value="MANICURE"><fmt:message key="text.service.manicure"/></option>
                <option value="PEDICURE"><fmt:message key="text.service.pedicure"/></option>
                <option value="REFLEXOLOGY"><fmt:message key="text.service.reflexology"/></option>
            </select><br/>
            <fmt:message key="text.visit.day"/>::<label><input name="day" type="date" required
                                                               placeholder="Day"></label><br/>
            <input type="submit" value="<fmt:message key="text.search"/>" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"/>
            <br/>
                ${errorSearchVisitMessage}
            <br/>
        </form>

        <hr/>
        <h3><fmt:message key="text.search.result"/></h3>

        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
            <thead>
            <tr>
                <th><fmt:message key="text.visit.id"/></th>
                <th class="mdl-data-table__cell--non-numeric"><fmt:message
                        key="text.visit.service"/></th>
                <th><fmt:message key="text.visit.day"/></th>
                <th><fmt:message key="text.visit.hour"/></th>
                <th><fmt:message key="text.visit.master"/></th>
                <th class="mdl-data-table__cell--non-numeric"><fmt:message
                        key="text.visit.state"/></th>
            </tr>
            </thead>
            <tbody>
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
                        <form name="visit_element_reserve" method="POST" action="controller">
                            <input type="hidden" name="command" value="reserve_visit"/>
                            <input type="hidden" name="idvisit" value="${visit.id}"/>
                            <input type="submit" value="<fmt:message key="text.reserve"/> "
                                   class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br/>
        ${errorMessage}
        <br/>
    </fmt:bundle>

</div>
</body>
</html>
