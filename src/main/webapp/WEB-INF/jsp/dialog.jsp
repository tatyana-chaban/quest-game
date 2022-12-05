<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dialog</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<jsp:include page="parts/nav.jsp"/>

<div class="container">
    <div class="row">
        <div class="flex-column w-25">
            <div>
                <h2>Info: </h2>
                <ul>
                    <li>User: ${user.getName()}</li>
                    <li>Number of parties: ${user.getNumberOfParties()}</li>
                </ul>
            </div>
        </div>
        <div class="flex-column w-50">
            <h1>Dialog</h1>
            <hr>
            <p class="h4"><u>${character.getName()}: </u></p>
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
