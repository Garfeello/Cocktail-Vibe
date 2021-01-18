<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="header.jsp"></jsp:include>


<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Swietna aplikacja Kamila</title>
</head>
<body>


<form class="form-inline my-2 my-lg-0 container">
    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
</form>
<ul class="container">
    <c:forEach items="${fiveNewestCocktails}" var="cocktail">

        <li class="list-group-item">
            <div class="container card" style="width: 30rem;">
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

    <li class="list-group-item">
        <div class="card container" style="width: 30rem;">
            <img class="card-img-top" SRC="https://upload.wikimedia.org/wikipedia/commons/3/3f/Margarita.jpg"
                 width="350px"
                 height="600px">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's
                    content.</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Cras justo odio</li>
                <li class="list-group-item">Dapibus ac facilisis in</li>
                <li class="list-group-item">Vestibulum at eros</li>
            </ul>
        </div>
    </li>

</ul>
</body>
</html>