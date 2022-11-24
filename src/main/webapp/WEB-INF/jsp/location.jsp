<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="questRepository" value="${applicationScope.get('questRepository')}"/>
<c:set var="itemRepository" value="${applicationScope.get('itemRepository')}"/>

<!DOCTYPE html>
<html>
<head>
    <title>${currentLocation.getName()}</title>
    <meta charset="UTF-8">
</head>
<body>
<div>
    <h2>Quests: </h2>
    <ul>
        <c:forEach items="${user.getQuests()}" var="questId">
            <c:set var="quest" value="${questRepository.getByKey(questId)}" />
                <li>${quest.getText()} Is finished: ${quest.isFinished(user)}</li>
        </c:forEach>
    </ul>
</div>
<div>
    <h2>Inventory: </h2>
    <ul>
        <c:forEach items="${user.getInventory()}" var="itemFromInventory">
            <li>${itemFromInventory}</li>
        </c:forEach>
    </ul>
</div>
<div>
    <h1>Location: ${currentLocation.getName()}</h1>
    <h3>Go to:</h3>
    <ul>
        <c:forEach items="${availableLocations}" var="nextLocation">
            <li>
                <form action="${pageContext.request.contextPath}/location" method="post">
                    <input type="hidden" name="nextLocation" value="${nextLocation.getName()}">
                    <button type="submit">${nextLocation.getName()}</button>
                </form>
            </li>
        </c:forEach>
    </ul>
    <h3>Take:</h3>
    <ul>
        <c:forEach items="${availableItems}" var="item">
            <c:if test="${!user.containsInInventory(item.getName())}">
                <li>
                    <form action="${pageContext.request.contextPath}/location" method="post">
                        <input type="hidden" name="item" value="${item.getName()}">
                        <button type="submit">${item.getName()}</button>
                    </form>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>
</body>
</html>
