
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <a href="controller?command=setLocale&language=eng">eng</a>
    <a href="controller?command=setLocale&language=ua">ua</a>

    <fmt:setLocale value = "${sessionScope.locale}"/>
    <fmt:bundle basename="texts">
        <p><fmt:message key="text.login"/> </p>
        <br />
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login" />
            Login:<br/>
            <input type="text" name="login" value=""/>
            <br/>Password:<br/>
            <input type="password" name="password" value=""/>
            <br/>
                ${errorLoginPassMessage}
            <br/>
                ${wrongAction}
            <br/>
                ${nullPage}
            <br/>
            <input type="submit" value="Log in"/>
        </form><hr/>

        <a href="controller?command=registrationForm">Sign up</a>
    </fmt:bundle>

</body>
</html>
