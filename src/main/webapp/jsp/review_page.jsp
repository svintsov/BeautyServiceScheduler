<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Review page</title>
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
        <h2><fmt:message key="text.review.page.heading"/></h2>
        <form name="review" method="post" action="controller">
            <input type="hidden" name="command" value="write_review"/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <textarea class="mdl-textfield__input" type="text" rows="1" id="rev"
                          name="review"></textarea>
                <label class="mdl-textfield__label" for="rev"><fmt:message
                        key="text.review"/></label>
            </div>
            <br/>
            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                   type="submit" value="<fmt:message key="text.review.page.submit"/> ">
        </form>
    </fmt:bundle>

</div>
</body>
</html>
