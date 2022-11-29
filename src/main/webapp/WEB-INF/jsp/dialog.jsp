<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dialog</title>
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
            <h1>Dialog</h1>
            <hr>
            <p class="h4"> <u>${housekeeper.getName()}: </u></p>
            <p class="h3 lead font-weight-light">${currentQuestion.getText()}</p>
            <hr>
            <ul>
                <c:forEach items="${availableAnswers}" var="answer">
                    <li>
                        <c:choose>
                            <c:when test="${answer.getNextQuestionId() != null}">
                                <form action="${pageContext.request.contextPath}/dialog" method="post">
                                    <input type="hidden" name="nextQuestionId" value="${answer.getNextQuestionId()}">
                                    <button type="submit">${answer.getText()}</button>
                                </form>
                            </c:when>

                            <c:when test="${answer.getNextQuestionId() == null && answer.getFinishMessage() != null}">
                                <form action="${pageContext.request.contextPath}/finish" method="get">
                                    <input type="hidden" name="finishMessage" value="${answer.getFinishMessage()}">
                                    <button type="submit">${answer.getText()}</button>
                                </form>
                            </c:when>

                            <c:when test="${answer.getNextQuestionId() == null && answer.getFinishMessage() == null}">
                                <form action="${pageContext.request.contextPath}/location" method="get">
                                    <button type="submit">${answer.getText()}</button>
                                </form>
                            </c:when>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="flex-column w-25">

        </div>
    </div>
</div>
</body>
</html>
