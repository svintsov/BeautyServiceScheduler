<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="searching_visit" method="post" action="controller">
    <input type="hidden" name="command" value="search_visit"/>
    Services
    <select name="services_select" required>
        <option value="AROMATHERAPY">AROMATHERAPY</option>
        <option value="MANICURE">MANICURE</option>
        <option value="PEDICURE">PEDICURE</option>
        <option value="REFLEXOLOGY">REFLEXOLOGY</option>
    </select><br/>
    Day of visit:<label><input name="day" type="date" required placeholder="Day"></label><br/>
    <input type="submit" value="Search"/>
    <br/>
    ${errorSearchVisitMessage}
    <br/>
</form>
</body>
</html>
