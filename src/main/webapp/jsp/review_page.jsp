<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Review page</title>
</head>
<body>
    <h1>Write your review for visit</h1>
    <form name="review" method="post" action="controller">
        <input type="hidden" name="command" value="write_review"/>
        <input  type="text" name="review" required placeholder="review" value=""/><br/>
        <input class="button" type="submit" value="Send">
    </form>
</body>
</html>
