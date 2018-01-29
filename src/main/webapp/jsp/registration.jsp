<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Registration</title>
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
        <h2><fmt:message key="text.registration.page.heading"/></h2>
        <br/>
        <form name="registration" method="post" action="controller">

            <input type="hidden" name="command" value="sign_up">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="login2"
                       name="login" pattern="^[A-Za-z0-9_-]{3,16}$">
                <label class="mdl-textfield__label" for="login2"><fmt:message
                        key="text.registration.page.login"/></label>
                <span class="mdl-textfield__error">Example:H_ello3,h_ell4o</span>
            </div><br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="password2"
                       name="password" pattern="^[a-z0-9_-]{4,18}$">
                <label class="mdl-textfield__label" for="password2"><fmt:message
                        key="text.registration.page.password"/></label>
                <span class="mdl-textfield__error">Example:pass,co_rre6t</span>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="email"
                       name="email" pattern="^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$">
                <label class="mdl-textfield__label" for="email"><fmt:message
                        key="text.registration.page.email"/></label>
                <span class="mdl-textfield__error">Example: mail@corp.com</span>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="name"
                        name="full name" pattern="^[A-Z][a-zA-Z]{2,}(?: [A-Z][a-zA-Z]*){0,2}$">
                <label class="mdl-textfield__label" for="name"><fmt:message
                        key="text.registration.page.name"/></label>
                <span class="mdl-textfield__error">Example: Martin Luther</span>
            </div>
            <br/>

            <input type="submit"
                   value="<fmt:message key="text.registration.page.submit"/>"
                   class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">

        </form>
        <br/>
        ${errorRegistrationMessage}
        <br/>
    </fmt:bundle>
</div>

</body>
</html>
