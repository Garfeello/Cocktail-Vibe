<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../mainPage/header.jsp"/>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Alcohol list</title>
</head>
<body>
<div class="container-fluid" style="padding: 30px 5% 15px 5%">
    <h1 style="text-align: center;"> - List ALkoholi Pl! - </h1>
    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
        <form action="${pageContext.request.contextPath}/cocktailVibe/alcoholList">
            <button class="btn-group btn-group-sm btn btn-outline-dark">Alcohol List English</button>
        </form>
    </div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach items="${alcoholList}" var="alcohol">
            <div class="col">
                <div class="card ">
                    <img SRC="/pictureController/getAlcoholPicture/${alcohol.id}" width="350px" height="600px"
                         alt="${alcohol.name}">
                    <div class="card-body">
                        <h5 class="card-title">${alcohol.name}</h5>
                        <p class="card-text">${alcohol.description}</p>
                    </div>
                    <ul class="list-group list-group-flush"></ul>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Typ: ${alcohol.alcoholType}</li>
                        <li class="list-group-item">Wiek: ${alcohol.age}</li>
                    </ul>
                    <div class="card-footer">
                        <small class="text-muted">
                            <a href="${pageContext.request.contextPath}/cocktailVibe/translateAlcoholToEn?alcoholName=${alcohol.name}&translateFrom=pl&translateTo=en">
                                Translate to english</a></small>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
