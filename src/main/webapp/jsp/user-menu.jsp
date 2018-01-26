<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Customer</title></head>
<body>
    <h3>Welcome,customer</h3>
    <hr/>

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
                    <form name="write_review" method="post" action="controller">
                        <input type="hidden" name="command" value="review_form" />
                        <input type="hidden" name="idvisit" value="${visit.id}">
                        <input type="submit" value="Write review"/>
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
    <br/>
    ${errorMessage}
    <br/>
    <a href="controller?command=logout">Logout</a>
    <a href="controller?command=searching_form">Search</a>
</body>
</html>
