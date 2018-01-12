
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <p>Registration</p>
    <br />
    <form name="registration" method="post" action="controller">

        <input type="hidden" name="command" value="sign_up" />
        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br>
        <input type="text" required placeholder="email" name="email"><br>
        <input type="text" required placeholder="full name" name="full name"><br><br>
        <input class="button" type="submit" value="Sign up">

    </form>
    ${errorRegistrationMessage}
    <br/>
</body>
</html>
