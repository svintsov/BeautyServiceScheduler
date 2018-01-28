<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>New Visit</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js">
    </script>
    <script type="text/javascript" src="js/scripts.js"></script>
    <link rel="stylesheet" href="css/material.min.css">
    <script src="js/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<div class="container_my">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="texts">
        <p><fmt:message key="text.admin.add.page.heading"/></p>
        <form name="adding_visit" method="post" action="controller">
            <input type="hidden" name="command" value="create_visit"/>
            <fmt:message key="text.visit.service"/>:
            <select name="services_select" required>
                <option value="1"><fmt:message key="text.service.aromatherapy"/></option>
                <option value="2"><fmt:message key="text.service.manicure"/></option>
                <option value="3"><fmt:message key="text.service.pedicure"/></option>
                <option value="4"><fmt:message key="text.service.reflexology"/></option>
            </select><br/>
            <fmt:message key="text.visit.day"/>:<input name="day" type="date" required
                                                              placeholder="Day"><br/>
            <fmt:message key="text.visit.hour"/>: <input name="hour" type="time" required
                                                                placeholder="Time"><br/>
            <fmt:message key="text.visit.state"/>:
            <select name="states_select" required>
                <option value="FREE"><fmt:message key="text.state.free"/></option>
                <option value="AGREED"><fmt:message key="text.state.agreed"/></option>
                <option value="DONE"><fmt:message key="text.state.done"/></option>
            </select><br/>
            <fmt:message key="text.visit.master"/>:
            <select name="master">
                <c:forEach var="master" items="${requestScope.masters}">
                    <option value="${master.getId()}"><c:out value="${master.getId()}"/></option>
                </c:forEach>
            </select><br/>
            <fmt:message key="text.visit.customer"/>:
            <select name="customer">
                <option value="-1"><fmt:message key="text.empty"/></option>
                <c:forEach var="customer" items="${requestScope.customers}">
                    <option value="${customer.getId()}"><c:out
                            value="${customer.getId()}"/></option>
                </c:forEach>
            </select></br>
            <input type="submit" value="<fmt:message key="text.add"/>"
                   class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"/>
            <br/>
                ${errorCreateVisitMessage}
            <br/>
        </form>
    </fmt:bundle>
</div>

</body>
</html>
