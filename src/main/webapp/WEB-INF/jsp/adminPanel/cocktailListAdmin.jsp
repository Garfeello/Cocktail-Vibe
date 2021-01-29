<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../mainPage/header.jsp"/>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Cocktail list</title>
</head>
<body>
<div class="container-fluid" style="padding: 30px 5% 15px 5%">
    <h1 style="text-align: center">${cocktailListSize}</h1>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach items="${cocktailList}" var="cocktailDto">
            <div class="col">
                <div class="card-body">
                    <h5 class="card-title">${cocktailDto.name}</h5>
                    <small class="card-text">${cocktailDto.preparationDescription}</small>
                </div>
                <ul class="list-group list-group-flush">
                    <c:forEach items="${cocktailDto.ingredientDTOList}" var="ingredients">
                        <div style="color:#949494; margin-right: 10px;">${ingredients.name}</div>
                    </c:forEach>
                </ul>
                <ul class="list-group list-group-flush">
                    <c:forEach items="${cocktailDto.alcoholDTOList}" var="alcohol">
                        <div style="color:#949494; margin-right: 10px;">${alcohol.name}</div>
                    </c:forEach>
                </ul>
                <h4>Picture loaded : ${cocktailDto.pictureDTO.pictureLoaded}</h4>
            </div>
        </c:forEach>
    </div>
</div>
</body>
