<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="texts">
    <p><fmt:message key="text.admin.main.page.heading"/></p>
    <table>
        <tr>
            <th><fmt:message key="text.visit.id"/></th>
            <th><fmt:message key="text.visit.service"/></th>
            <th><fmt:message key="text.visit.day"/></th>
            <th><fmt:message key="text.visit.hour"/></th>
            <th><fmt:message key="text.visit.customer"/></th>
            <th><fmt:message key="text.visit.master"/></th>
            <th><fmt:message key="text.visit.state"/></th>
            <th><fmt:message key="text.visit.review"/></th>
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
                    <c:out value="${visit.customer.getId()}" default=""/>
                </td>
                <td>
                    <c:out value="${visit.master.getId()}"/>
                </td>
                <td>
                    <c:out value="${visit.state}"/>
                </td>
                <td>
                    <c:out value="${visit.review}" default=""/>
                </td>
                <td>
                    <form name="visit_element_delete" method="POST" action="controller">
                        <input type="hidden" name="command" value="delete_visit"/>
                        <input type="hidden" name="idvisit" value="${visit.id}"/>
                        <input type="submit" value="Delete"/>
                    </form>
                </td>
                <td>
                    <form name="visit_element_finish" method="POST" action="controller">
                        <input type="hidden" name="command" value="finish_visit"/>
                        <input type="hidden" name="idvisit" value="${visit.id}"/>
                        <input type="hidden" name="idcustomer" value="${visit.customer.getId()}"/>
                        <input type="submit" value="Finish"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    ${errorMessage}
    <br/>
    <a href="controller?command=logout"><fmt:message key="text.logout"/></a>
    <a href="controller?command=adding_form"><fmt:message key="text.admin.main.page.adding"/></a>
</fmt:bundle>

</body>
</html>
