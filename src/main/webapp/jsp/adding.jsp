
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
        <select name="services_select" required>
            <option value="1">AROMATHERAPY</option>
            <option value="2">MANICURE</option>
            <option value="3">PEDICURE</option>
            <option value="4">REFLEXOLOGY</option>
        </select><br/>
        <label><input name="day" type="date" required placeholder="Day"></label>Day<br/>
        <label><input name="hour" type="time" required placeholder="Time"></label>Hour<br/>
        <label><input name="customer" type="number" required placeholder="Customer ID"></label>Customer ID<br/>
        <label><input name="master" type="number" required placeholder="Master ID"></label>Master ID<br/>
        States
        <select name="states_select" required>
            <option value="FREE">FREE</option>
            <option value="AGREED">AGREED</option>
            <option value="DONE">DONE</option>
        </select><br/>
    <input type="submit" value="Create"/>
    </form>
</body>
</html>
