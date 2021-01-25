<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../mainPage/header.jsp"/>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>My Cocktails</title>
</head>
<body>
<h1 class="containter">- My cocktails -</h1>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <c:forEach items="${alcoholList}" var="alcohol">
        <div class="col">
            <div class="card ">
                <img SRC="/pictureController/getAlcoholPicture/${alcohol.id}" width="350px" height="600px">
                <div class="card-body">
                    <h5 class="card-title">${alcohol.name}</h5>
                    <p class="card-text">${alcohol.description}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Type: ${alcohol.alcoholType}</li>
                    <li class="list-group-item">Age: ${alcohol.age}</li>
                </ul>
                <div class="card-footer">
                    <small class="text-muted">
                        <a class="btn btn-light" href="/cocktailVibe/editCocktail?cocktailId=${cocktail.id}">edit</a>
                    </small>
                    <small class="text-muted">
                        <a class="btn btn-light" href="/cocktailVibe/deleteCocktail?cocktailId=${cocktail.id}">delete</a>
                    </small>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
