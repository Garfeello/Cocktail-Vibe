<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../mainPage/header.jsp"/>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Searching Cocktails</title>
</head>
<body class="container">
<div class="container">
    <h1 class="container"> ${ empty searchedString ? "" : searchedString} -</h1>
</div>
<c:forEach items="${cocktail}" var="cocktail">
    <li class="list-group-item">
        <div class="container card">
            <img class="card-img-top" SRC="/pictureController/getPicture/${cocktail.id}" width="350px" height="600px">
            <div class="card-body">
                <h5 class="card-title">${cocktail.name}</h5>
                <p class="card-text">${cocktail.preparationDescription}</p>
            </div>
            <ul class="list-group list-group-flush">
                <c:forEach items="${cocktail.alcoholList}" var="alcohol">
                    <li class="list-group-item">${alcohol.name}</li>
                </c:forEach>
                <c:forEach items="${cocktail.ingredients}" var="ingredients">
                    <li class="list-group-item">${ingredients.name}</li>
                </c:forEach>
            </ul>
        </div>
    </li>
</c:forEach>
</body>