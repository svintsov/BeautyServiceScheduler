<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="adding_visit" method="post" action="controller">
        <input type="hidden" name="command" value="create_visit"/>
        Services
        <select name="services_select" required>
            <option value="1">AROMATHERAPY</option>
            <option value="2">MANICURE</option>
            <option value="3">PEDICURE</option>
            <option value="4">REFLEXOLOGY</option>
        </select><br/>
        Day of visit:<label><input name="day" type="date" required placeholder="Day"></label><br/>
        Time: <label><input name="hour" type="time" required placeholder="Time"></label><br/>
        States
        <select name="states_select" required>
            <option value="FREE">FREE</option>
            <option value="AGREED">AGREED</option>
            <option value="DONE">DONE</option>
        </select><br/>
        Master ID
        <select name="master">
            <c:forEach var="master" items="${requestScope.masters}">
                <option value="${master.getId()}"><c:out value="${master.getId()}"/></option>
            </c:forEach>
        </select><br/>
        Customer ID
        <select name="customer">
            <c:forEach var="customer" items="${requestScope.customers}">
                <option value="${customer.getId()}"><c:out value="${customer.getId()}"/></option>
            </c:forEach>
        </select></br>
        <input type="submit" value="Create"/>
        <br/>
        ${errorCreateVisitMessage}
        <br/>
    </form>
</body>
</html>
