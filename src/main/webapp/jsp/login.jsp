
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <fmt:setLocale value = "uk_UA"/>
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
