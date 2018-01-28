<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
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

<a href="controller?command=setLocale&language=eng">eng</a>
<a href="controller?command=setLocale&language=ua">ua</a>

<div class="container_my">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="texts">
        <h2><fmt:message key="text.login.page.heading"/></h2>

        <br/>
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login"/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="login1" name="login">
                <label class="mdl-textfield__label" for="login1"><fmt:message
                        key="text.login.page.login"/></label>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" id="password1" name="password">
                <label class="mdl-textfield__label" for="password1"><fmt:message
                        key="text.login.page.password"/></label>
            </div>
            <br/>
            <br/>
                ${errorLoginPassMessage}
            <br/>
                ${wrongAction}
            <br/>
                ${nullPage}
            <br/>
            <input type="submit" value="<fmt:message key="text.login.page.submit"/>"
                   class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"/>
        </form>
        <hr/>

        <a href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message
                key="text.login.page.registration"/></a>
    </fmt:bundle>
</div>

</body>
</html>
