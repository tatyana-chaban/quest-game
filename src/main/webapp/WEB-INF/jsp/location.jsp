<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>${currentLocation.getName()}</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Location: ${currentLocation.getName()}</h1>
<br>
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
<h3>Talk to:</h3>
<ul>
    <c:forEach items="${availableNpc}" var="npc">
        <li>
            <form action="${pageContext.request.contextPath}/dialog" method="post">
                <input type="hidden" name="dialog" value="${npc.getName()}">
                <button type="submit">${npc.getName()}</button>
            </form>
        </li>
    </c:forEach>
</ul>
<h3>Take:</h3>
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
</body>
</html>
