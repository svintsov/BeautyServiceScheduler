
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="adding_visit" method="post" action="controller">
        <input type="hidden" name="command" value="create_visit"/>
        Services
        <select name="services_select">
            <option value="AROMATHERAPY">AROMATHERAPY</option>
            <option value="MANICURE">MANICURE</option>
            <option value="PEDICURE">PEDICURE</option>
            <option value="REFLEXOLOGY">REFLEXOLOGY</option>
        </select><br/>
        <label><input name="day" type="date"></label>Day<br/>
        <label><input name="hour" type="time"></label>Hour<br/>
        <label><input name="customer" type="number"></label>Customer ID<br/>
        <label><input name="master" type="number"></label>Master ID<br/>
        States
        <select name="states_select">
            <option value="FREE">FREE</option>
            <option value="AGREED">AGREED</option>
            <option value="DONE">DONE</option>
        </select><br/>
    <input type="submit" value="Create"/>
    </form>
</body>
</html>
