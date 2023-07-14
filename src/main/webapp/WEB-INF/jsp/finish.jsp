<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Finish</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<jsp:include page="parts/nav.jsp"/>

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
