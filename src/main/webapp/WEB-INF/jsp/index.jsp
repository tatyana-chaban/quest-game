<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quest-Game</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light container">
    <p class="navbar-brand">
        <img src="${pageContext.request.contextPath}/images/virus.svg" width="20" height="20"> Quest Game</p>
</nav>
<div class="container">
    <div class="row">
        <div class="flex-column w-25">

        </div>
        <div class="flex-column w-50">
            <h1>Welcome!</h1>
            <hr>
            <p class="text-justify">
                You became a secret agent for a very unknown secret organization and received your first assignment.</p>
            <p class="text-justify font-weight-light">
                A scientist Mr H. from a local university illegally conducted experiments in his
                home laboratory and the result of these experiments was the invention of a new virus.
                And your mission is to prevent another pandemic.
                It will be necessary to get into the house and find everything that may relate to this dangerous
                invention.
                Mr H. has no idea that he has been followed and hasn't taken protective measures yet, and the most
                difficult thing will be to talk to the housekeeper and get her to let you into the house. Apply all your
                charm. Once inside, you will receive further instructions.
            </p>
            <p>
                Good luck!
            </p>

            <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/entrance" method="post">
                <label for="name">Enter your name:</label> <br>
                    <input type="text" id="name" name="username">
                    <input type="submit" value="ОК"/>
                <c:if test="${blankName == true}">
                        <p class="text-danger"> <small>field can't be empty </small></p>
                </c:if>
            </form>
        </div>
        <div class="flex-column w-25">

        </div>
    </div>
</div>
</body>
</html>
