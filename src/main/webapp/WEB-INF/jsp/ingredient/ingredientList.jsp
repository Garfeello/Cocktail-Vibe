<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../mainPage/header.jsp"/>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Ingredient List</title>
</head>
<body>
<div class="container-fluid" style="padding: 30px 5% 15px 5%">
    <h1 style="text-align: center;"> - Ingredient List! - </h1>
    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
        <form action="${pageContext.request.contextPath}/cocktailVibe/ingredient/ingredientListPl">
            <button class="btn-group btn-group-sm btn btn-outline-dark">Cocktail List Polish</button>
        </form>
        <form action="${pageContext.request.contextPath}/cocktailVibe/ingredient/ingredientList">
            <button class="btn-group btn-group-sm btn btn-outline-dark">Cocktail List English</button>
        </form>
    </div>
    <h4> - Pl -</h4>
    <div class="card-group">
        <c:forEach items="${ingredientList}" var="ingredient">
            <div class="col-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Name: ${ingredient.name}</h5>
                        <p class="card-text">Type: ${ingredient.type}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>