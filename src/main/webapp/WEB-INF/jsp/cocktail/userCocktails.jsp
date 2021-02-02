<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../mainPage/header.jsp"/>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>CocktailVibe</title>
</head>
<body>
<h1 class="containter">- <spring:message code="lang.myCocktails"/> -</h1>
<h3>${empty cocktailList ? "Nothing to display here :(" : ""}</h3>
<div class="row row-cols-1 row-cols-md-3 g-4">
    <c:forEach items="${cocktailList}" var="cocktail">
        <div class="col">
            <div class="card ">
                <img SRC="/pictureController/getPicture/${cocktail.id}" width="350px"
                     height="600px">
                <div class="card-body">
                    <h5 class="card-title">${cocktail.name}</h5>
                    <p class="card-text">${cocktail.preparationDescription}</p>
                </div>
                <ul class="list-group list-group-flush"><spring:message code="lang.allAlcohols"/></ul>
                <ul class="list-group list-group-flush">
                    <c:forEach items="${cocktail.alcoholList}" var="alcohol">
                        <li class="list-group-item">${alcohol.name}</li>
                    </c:forEach>
                </ul>
                <ul class="list-group list-group-flush"><spring:message code="lang.allIngredients"/></ul>
                <ul class="list-group list-group-flush">
                    <c:forEach items="${cocktail.ingredients}" var="ingredients">
                        <li class="list-group-item">${ingredients.name}</li>
                    </c:forEach>
                </ul>
                <div class="card-footer">
                    <small class="text-muted">
                        <a class="btn btn-light" href="/cocktailVibe/editCocktail?cocktailId=${cocktail.id}">
                            <spring:message code="lang.edit"/>
                        </a>
                    </small>
                    <small class="text-muted">
                        <form action="/cocktailVibe/deleteCocktail" method="post">
                            <button class="btn btn-light" value="${cocktail.id}" name="cocktailId">
                                <spring:message code="lang.delete"/>
                            </button>
                        </form>
                    </small>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
