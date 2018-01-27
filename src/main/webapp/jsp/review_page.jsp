<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Review page</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="texts">
    <p><fmt:message key="text.review.page.heading"/> </p>
    <form name="review" method="post" action="controller">
        <input type="hidden" name="command" value="write_review"/>
        <input  type="text" name="review" required placeholder="review" value=""/><br/>
        <input class="button" type="submit" value="<fmt:message key="text.review.page.submit"/> ">
    </form>
</fmt:bundle>
</body>
</html>
