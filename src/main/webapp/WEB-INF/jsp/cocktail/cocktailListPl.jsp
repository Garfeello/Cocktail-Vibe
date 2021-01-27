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
    <h1 style="text-align: center;"> - Lista Koktajli PL ! - </h1>
    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
        <form action="${pageContext.request.contextPath}/cocktailVibe/cocktailList">
            <button class="btn-group btn-group-sm btn btn-outline-dark">Cocktail List English</button>
        </form>
    </div>
    <div class="row">
        <c:forEach items="${cocktailList}" var="cocktail">
            <div class="col-4">
                <div class="card" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h3 class="card-title" style="margin-bottom: 0;">${cocktail.name}</h3>
                    </div>
                    <div style="position: relative;">
                        <img class="card-img" SRC="/pictureController/getPicture/${cocktail.id}"
                             style="object-fit: cover;" height="300" alt="${cocktail.name}">
                        <div style="width: 100%; height: 100%; position: absolute; left: 0; bottom: 0;
                            background-image: linear-gradient(rgba(0,0,0,0.1), rgba(0,0,0,0.3) 80%);">
                        </div>
                    </div>
                    <div class="card-body">
                        <div style="margin-bottom: 10px;" class="card-text">${cocktail.preparationDescription}</div>
                        <div>
                            <h4>Alkohole Koktajlu:</h4>
                            <div style="display: flex; flex-direction: row; flex-wrap: wrap; margin-bottom: 10px;">
                                <c:forEach items="${cocktail.alcoholList}" var="alcohol">
                                    <div style="color:#949494; margin-right: 10px;">${alcohol.name}</div>
                                </c:forEach>
                            </div>
                            <h4>Składniki Koktajlu:</h4>
                            <div style="display: flex; flex-direction: row; flex-wrap: wrap;">
                                <c:forEach items="${cocktail.ingredients}" var="ingredients">
                                    <div style="color:#949494; margin-right: 10px;">${ingredients.name}</div>
                                </c:forEach>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted"><a>Stworzono przez: @${cocktail.user.nickName}</a></small>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">
                                    <a href="${pageContext.request.contextPath}/cocktailVibe/translateCocktailToEn?cocktailName=${cocktail.name}&translateFrom=pl&translateTo=en">
                                        Translate to english</a></small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
