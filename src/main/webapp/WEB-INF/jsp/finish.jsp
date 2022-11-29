<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Finish</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light container">
    <p class="navbar-brand">
        <img src="${pageContext.request.contextPath}/images/virus.svg" width="20" height="20"> Quest Game</p>
</nav>

<div class="container">
    <div class="row">
        <div class="flex-column w-25 vh-100 p-1">
            <div>
                <h2>Info: </h2>
                <ul>
                    <li>
                        User: ${user.getName()}
                    </li>
                    <li>
                        Number of parties: ${user.getNumberOfParties()}
                    </li>
                </ul>
            </div>
        </div>
        <div class="flex-column w-50 p-1">
            <h1>Game over!</h1>
            <hr>
            <p class="h4"> ${finishMessage} </p>
            <form action="${pageContext.request.contextPath}/entrance" method="post">
                <input type="hidden" name="username" value="${user.getName()}">
                <button type="submit" class="btn-group-lg">Restart</button>
            </form>

        </div>
        <div class="flex-column w-25 vh-100 p-1">

        </div>
    </div>
</div>
</body>
</html>
