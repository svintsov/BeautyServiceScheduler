<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="texts">
    <p><fmt:message key="text.registration.page.heading"/></p>
    <br/>
    <form name="registration" method="post" action="controller">

        <input type="hidden" name="command" value="sign_up">
        <fmt:message key="text.registration.page.login"/>:<br/>
        <input type="text" required placeholder="login" name="login"><br>
        <fmt:message key="text.registration.page.password"/>:<br/>
        <input type="password" required placeholder="password" name="password"><br>
        <fmt:message key="text.registration.page.email"/>:<br/>
        <input type="text" required placeholder="email" name="email"><br>
        <fmt:message key="text.registration.page.name"/>:<br/>
        <input type="text" required placeholder="full name" name="full name"><br>
        <input class="button" type="submit" value="<fmt:message key="text.registration.page.submit"/>">

    </form>
    ${errorRegistrationMessage}
    <br/>
</fmt:bundle>
</body>
</html>
