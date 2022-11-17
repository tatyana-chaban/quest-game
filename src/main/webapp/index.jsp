<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quest-Game</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Привет</h1>
<hr>
<form accept-charset="UTF-8" action="start" method="POST">
    <label for="name">Введите имя
        <input type="text" id="name" name="username">
        <input type="submit" value="ОК" />
    </label>
</form>
<c:if test="${blankName == true}">
    <p>Name can't be blank</p>
</c:if>

</body>
</html>
