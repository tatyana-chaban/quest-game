<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Finish</title>
</head>
<body>
<p>
    ${finishMessage}
</p>

<p>
    Info:
    User: ${user.getName()}
    Number of parties: ${user.getNumberOfParties()}
</p>

<form action="${pageContext.request.contextPath}/entrance" method="post">
    <input type="hidden" name="username" value="${user.getName()}">
    <button type="submit">Restart</button>
</form>
</body>
</html>
