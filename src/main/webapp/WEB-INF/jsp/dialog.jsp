<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dialog</title>
</head>
<body>
<h3>${housekeeper.getName()}: ${currentQuestion.getText()}</h3>
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
</body>
</html>
