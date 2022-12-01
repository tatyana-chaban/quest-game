<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>${currentLocation.getName()}</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<jsp:include page="parts/nav.jsp" />

<div class="container">
    <div class="row">
        <div class="flex-column w-25 vh-100 p-1">
            <div>
                <h2>Info: </h2>
                <ul>
                    <li>User: ${user.getName()}</li>
                    <li>Number of parties: ${user.getNumberOfParties()}</li>
                </ul>
            </div>
            <div>
                <h2>Quests: </h2>
                <ul>
                    <c:forEach items="${user.getQuests().values()}" var="quest">
                        <c:choose>
                            <c:when test="${quest.isFinished(user)}">
                                <li>
                                    <img src="${pageContext.request.contextPath}/images/check2-square.svg" width="15"
                                         height="15"> ${quest.getText()}
                                </li>
                            </c:when>
                            <c:when test="${!quest.isFinished(user)}">
                                <li>
                                    <img src="${pageContext.request.contextPath}/images/square.svg" width="15"
                                         height="15"> ${quest.getText()}
                                </li>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <img src="${pageContext.request.contextPath}/images/game_map.png" class="img-thumbnail" width="100%"
                     height="60%">
            </div>
        </div>
        <div class="flex-column w-50 p-1">
            <h1>Location: ${currentLocation.getName()}</h1>
            <hr>
            <p class="h4"><u>Go to: </u></p>
            <hr>
            <ul>
                <c:forEach items="${availableLocations}" var="nextLocation">
                    <c:choose>
                        <c:when test="${nextLocation.isOpen(user)}">
                            <li>
                                <form action="${pageContext.request.contextPath}/location" method="post">
                                    <input type="hidden" name="nextLocation" value="${nextLocation.getName()}">
                                    <button type="submit">${nextLocation.getName()} </button>
                                </form>
                            </li>
                        </c:when>
                        <c:when test="${!nextLocation.isOpen(user)}">
                            <li>
                               <button>${nextLocation.getName()}</button>  closed
                            </li>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </ul>
            <p class="h4"><u>Take: </u></p>
            <hr>
            <ul>
                <c:forEach items="${availableItems}" var="item">
                        <li>
                            <form action="${pageContext.request.contextPath}/location" method="post">
                                <input type="hidden" name="item" value="${item.getName()}">
                                <button type="submit">${item.getName()}</button>
                            </form>
                        </li>
                </c:forEach>
            </ul>
        </div>
        <div class="flex-column w-25 vh-100 p-1">
            <div>
                <h2>Inventory: </h2>
                <ul>
                    <c:forEach items="${user.getInventory()}" var="itemFromInventory">
                        <li>${itemFromInventory}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
